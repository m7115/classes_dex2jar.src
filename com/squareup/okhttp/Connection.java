package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.internal.http.HttpConnection;
import com.squareup.okhttp.internal.http.HttpEngine;
import com.squareup.okhttp.internal.http.HttpTransport;
import com.squareup.okhttp.internal.http.Request;
import com.squareup.okhttp.internal.http.Response;
import com.squareup.okhttp.internal.http.Response.Builder;
import com.squareup.okhttp.internal.http.SpdyTransport;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.spdy.SpdyConnection;
import com.squareup.okhttp.internal.spdy.SpdyConnection.Builder;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class Connection
  implements Closeable
{
  private boolean connected = false;
  private Handshake handshake;
  private HttpConnection httpConnection;
  private int httpMinorVersion = 1;
  private long idleStartTimeNs;
  private InputStream in;
  private OutputStream out;
  private Object owner;
  private final ConnectionPool pool;
  private int recycleCount;
  private final Route route;
  private BufferedSink sink;
  private Socket socket;
  private BufferedSource source;
  private SpdyConnection spdyConnection;

  public Connection(ConnectionPool paramConnectionPool, Route paramRoute)
  {
    this.pool = paramConnectionPool;
    this.route = paramRoute;
  }

  private void initSourceAndSink()
    throws IOException
  {
    this.source = Okio.buffer(Okio.source(this.in));
    this.sink = Okio.buffer(Okio.sink(this.out));
  }

  private void makeTunnel(TunnelRequest paramTunnelRequest)
    throws IOException
  {
    BufferedSource localBufferedSource = Okio.buffer(Okio.source(this.in));
    BufferedSink localBufferedSink = Okio.buffer(Okio.sink(this.out));
    HttpConnection localHttpConnection = new HttpConnection(this.pool, this, localBufferedSource, localBufferedSink);
    Request localRequest = paramTunnelRequest.getRequest();
    String str = paramTunnelRequest.requestLine();
    while (true)
    {
      localHttpConnection.writeRequest(localRequest.headers(), str);
      localHttpConnection.flush();
      Response localResponse = localHttpConnection.readResponse().request(localRequest).build();
      localHttpConnection.emptyResponseBody();
      switch (localResponse.code())
      {
      default:
        throw new IOException("Unexpected response code for CONNECT: " + localResponse.code());
      case 200:
        if (localBufferedSource.buffer().size() <= 0L)
          break;
        throw new IOException("TLS tunnel buffered too many bytes!");
      case 407:
        localRequest = HttpAuthenticator.processAuthHeader(this.route.address.authenticator, localResponse, this.route.proxy);
        if (localRequest != null)
          continue;
        throw new IOException("Failed to authenticate with proxy");
      }
    }
  }

  private void upgradeToTls(TunnelRequest paramTunnelRequest)
    throws IOException
  {
    Platform localPlatform = Platform.get();
    if (requiresTunnel())
      makeTunnel(paramTunnelRequest);
    this.socket = this.route.address.sslSocketFactory.createSocket(this.socket, this.route.address.uriHost, this.route.address.uriPort, true);
    SSLSocket localSSLSocket = (SSLSocket)this.socket;
    int i;
    if (this.route.modernTls)
    {
      localPlatform.enableTlsExtensions(localSSLSocket, this.route.address.uriHost);
      if ((!this.route.modernTls) || ((!this.route.address.protocols.contains(Protocol.HTTP_2)) && (!this.route.address.protocols.contains(Protocol.SPDY_3))))
        break label288;
      i = 1;
      label146: if (i != 0)
      {
        if ((!this.route.address.protocols.contains(Protocol.HTTP_2)) || (!this.route.address.protocols.contains(Protocol.SPDY_3)))
          break label294;
        localPlatform.setNpnProtocols(localSSLSocket, Util.HTTP2_SPDY3_AND_HTTP);
      }
    }
    while (true)
    {
      localSSLSocket.startHandshake();
      if (this.route.address.hostnameVerifier.verify(this.route.address.uriHost, localSSLSocket.getSession()))
        break label337;
      throw new IOException("Hostname '" + this.route.address.uriHost + "' was not verified");
      localPlatform.supportTlsIntolerantServer(localSSLSocket);
      break;
      label288: i = 0;
      break label146;
      label294: if (this.route.address.protocols.contains(Protocol.HTTP_2))
      {
        localPlatform.setNpnProtocols(localSSLSocket, Util.HTTP2_AND_HTTP_11);
        continue;
      }
      localPlatform.setNpnProtocols(localSSLSocket, Util.SPDY3_AND_HTTP11);
    }
    label337: this.out = localSSLSocket.getOutputStream();
    this.in = localSSLSocket.getInputStream();
    this.handshake = Handshake.get(localSSLSocket.getSession());
    initSourceAndSink();
    Protocol localProtocol = Protocol.HTTP_11;
    if (i != 0)
    {
      ByteString localByteString = localPlatform.getNpnSelectedProtocol(localSSLSocket);
      if (localByteString != null)
        localProtocol = Util.getProtocol(localByteString);
    }
    if (localProtocol.spdyVariant)
    {
      localSSLSocket.setSoTimeout(0);
      this.spdyConnection = new SpdyConnection.Builder(this.route.address.getUriHost(), true, this.source, this.sink).protocol(localProtocol).build();
      this.spdyConnection.sendConnectionHeader();
      return;
    }
    this.httpConnection = new HttpConnection(this.pool, this, this.source, this.sink);
  }

  public boolean clearOwner()
  {
    synchronized (this.pool)
    {
      if (this.owner == null)
        return false;
      this.owner = null;
      return true;
    }
  }

  public void close()
    throws IOException
  {
    this.socket.close();
  }

  public void closeIfOwnedBy(Object paramObject)
    throws IOException
  {
    if (isSpdy())
      throw new IllegalStateException();
    synchronized (this.pool)
    {
      if (this.owner != paramObject)
        return;
      this.owner = null;
      this.socket.close();
      return;
    }
  }

  public void connect(int paramInt1, int paramInt2, TunnelRequest paramTunnelRequest)
    throws IOException
  {
    if (this.connected)
      throw new IllegalStateException("already connected");
    Socket localSocket;
    if (this.route.proxy.type() != Proxy.Type.HTTP)
    {
      localSocket = new Socket(this.route.proxy);
      this.socket = localSocket;
      Platform.get().connectSocket(this.socket, this.route.inetSocketAddress, paramInt1);
      this.socket.setSoTimeout(paramInt2);
      this.in = this.socket.getInputStream();
      this.out = this.socket.getOutputStream();
      if (this.route.address.sslSocketFactory == null)
        break label140;
      upgradeToTls(paramTunnelRequest);
    }
    while (true)
    {
      this.connected = true;
      return;
      localSocket = new Socket();
      break;
      label140: initSourceAndSink();
      this.httpConnection = new HttpConnection(this.pool, this, this.source, this.sink);
    }
  }

  public Handshake getHandshake()
  {
    return this.handshake;
  }

  public int getHttpMinorVersion()
  {
    return this.httpMinorVersion;
  }

  public long getIdleStartTimeNs()
  {
    if (this.spdyConnection == null)
      return this.idleStartTimeNs;
    return this.spdyConnection.getIdleStartTimeNs();
  }

  public Object getOwner()
  {
    synchronized (this.pool)
    {
      Object localObject2 = this.owner;
      return localObject2;
    }
  }

  public Route getRoute()
  {
    return this.route;
  }

  public Socket getSocket()
  {
    return this.socket;
  }

  public void incrementRecycleCount()
  {
    this.recycleCount = (1 + this.recycleCount);
  }

  public boolean isAlive()
  {
    return (!this.socket.isClosed()) && (!this.socket.isInputShutdown()) && (!this.socket.isOutputShutdown());
  }

  public boolean isConnected()
  {
    return this.connected;
  }

  public boolean isExpired(long paramLong)
  {
    return getIdleStartTimeNs() < System.nanoTime() - paramLong;
  }

  public boolean isIdle()
  {
    return (this.spdyConnection == null) || (this.spdyConnection.isIdle());
  }

  public boolean isReadable()
  {
    if (this.source == null);
    do
      return true;
    while (isSpdy());
    try
    {
      int i = this.socket.getSoTimeout();
      try
      {
        this.socket.setSoTimeout(1);
        boolean bool = this.source.exhausted();
        return !bool;
      }
      finally
      {
        this.socket.setSoTimeout(i);
      }
    }
    catch (IOException localIOException)
    {
      return false;
    }
    catch (SocketTimeoutException localSocketTimeoutException)
    {
    }
    return true;
  }

  public boolean isSpdy()
  {
    return this.spdyConnection != null;
  }

  public Object newTransport(HttpEngine paramHttpEngine)
    throws IOException
  {
    if (this.spdyConnection != null)
      return new SpdyTransport(paramHttpEngine, this.spdyConnection);
    return new HttpTransport(paramHttpEngine, this.httpConnection);
  }

  public int recycleCount()
  {
    return this.recycleCount;
  }

  public boolean requiresTunnel()
  {
    return (this.route.address.sslSocketFactory != null) && (this.route.proxy.type() == Proxy.Type.HTTP);
  }

  public void resetIdleStartTime()
  {
    if (this.spdyConnection != null)
      throw new IllegalStateException("spdyConnection != null");
    this.idleStartTimeNs = System.nanoTime();
  }

  public void setHttpMinorVersion(int paramInt)
  {
    this.httpMinorVersion = paramInt;
  }

  public void setOwner(Object paramObject)
  {
    if (isSpdy())
      return;
    synchronized (this.pool)
    {
      if (this.owner != null)
        throw new IllegalStateException("Connection already has an owner!");
    }
    this.owner = paramObject;
    monitorexit;
  }

  public void updateReadTimeout(int paramInt)
    throws IOException
  {
    if (!this.connected)
      throw new IllegalStateException("updateReadTimeout - not connected");
    this.socket.setSoTimeout(paramInt);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Connection
 * JD-Core Version:    0.6.0
 */