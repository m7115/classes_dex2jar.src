package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Address;
import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkResponseCache;
import com.squareup.okhttp.ResponseSource;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.RouteDatabase;
import com.squareup.okhttp.TunnelRequest;
import com.squareup.okhttp.internal.Dns;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.GzipSource;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheRequest;
import java.net.CookieHandler;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocketFactory;

public class HttpEngine
{
  public final boolean bufferRequestBody;
  private BufferedSink bufferedRequestBody;
  private CacheRequest cacheRequest;
  final OkHttpClient client;
  private Connection connection;
  private Request originalRequest;
  private Request request;
  private Sink requestBodyOut;
  private Response response;
  private Source responseBody;
  private InputStream responseBodyBytes;
  private ResponseSource responseSource;
  private Source responseTransferSource;
  private Route route;
  private RouteSelector routeSelector;
  long sentRequestMillis = -1L;
  private boolean transparentGzip;
  private Transport transport;
  private Response validatingResponse;

  public HttpEngine(OkHttpClient paramOkHttpClient, Request paramRequest, boolean paramBoolean, Connection paramConnection, RouteSelector paramRouteSelector, RetryableSink paramRetryableSink)
  {
    this.client = paramOkHttpClient;
    this.originalRequest = paramRequest;
    this.request = paramRequest;
    this.bufferRequestBody = paramBoolean;
    this.connection = paramConnection;
    this.routeSelector = paramRouteSelector;
    this.requestBodyOut = paramRetryableSink;
    if (paramConnection != null)
    {
      paramConnection.setOwner(this);
      this.route = paramConnection.getRoute();
      return;
    }
    this.route = null;
  }

  private Response cacheableResponse()
  {
    return this.response.newBuilder().body(null).build();
  }

  private static Response combine(Response paramResponse1, Response paramResponse2)
    throws IOException
  {
    int i = 0;
    Headers.Builder localBuilder = new Headers.Builder();
    Headers localHeaders1 = paramResponse1.headers();
    int j = 0;
    if (j < localHeaders1.size())
    {
      String str2 = localHeaders1.name(j);
      String str3 = localHeaders1.value(j);
      if (("Warning".equals(str2)) && (str3.startsWith("1")));
      while (true)
      {
        j++;
        break;
        if ((isEndToEnd(str2)) && (paramResponse2.header(str2) != null))
          continue;
        localBuilder.add(str2, str3);
      }
    }
    Headers localHeaders2 = paramResponse2.headers();
    while (i < localHeaders2.size())
    {
      String str1 = localHeaders2.name(i);
      if (isEndToEnd(str1))
        localBuilder.add(str1, localHeaders2.value(i));
      i++;
    }
    return paramResponse1.newBuilder().headers(localBuilder.build()).build();
  }

  private void connect()
    throws IOException
  {
    if (this.connection != null)
      throw new IllegalStateException();
    String str;
    SSLSocketFactory localSSLSocketFactory;
    if (this.routeSelector == null)
    {
      str = this.request.url().getHost();
      if ((str == null) || (str.length() == 0))
        throw new UnknownHostException(this.request.url().toString());
      if (!this.request.isHttps())
        break label312;
      localSSLSocketFactory = this.client.getSslSocketFactory();
    }
    for (HostnameVerifier localHostnameVerifier = this.client.getHostnameVerifier(); ; localHostnameVerifier = null)
    {
      this.routeSelector = new RouteSelector(new Address(str, Util.getEffectivePort(this.request.url()), localSSLSocketFactory, localHostnameVerifier, this.client.getAuthenticator(), this.client.getProxy(), this.client.getProtocols()), this.request.uri(), this.client.getProxySelector(), this.client.getConnectionPool(), Dns.DEFAULT, this.client.getRoutesDatabase());
      this.connection = this.routeSelector.next(this.request.method());
      this.connection.setOwner(this);
      if (!this.connection.isConnected())
      {
        this.connection.connect(this.client.getConnectTimeout(), this.client.getReadTimeout(), getTunnelConfig());
        if (this.connection.isSpdy())
          this.client.getConnectionPool().share(this.connection);
        this.client.getRoutesDatabase().connected(this.connection.getRoute());
      }
      while (true)
      {
        this.route = this.connection.getRoute();
        return;
        if (this.connection.isSpdy())
          continue;
        this.connection.updateReadTimeout(this.client.getReadTimeout());
      }
      label312: localSSLSocketFactory = null;
    }
  }

  public static String getDefaultUserAgent()
  {
    String str = System.getProperty("http.agent");
    if (str != null)
      return str;
    return "Java" + System.getProperty("java.version");
  }

  private TunnelRequest getTunnelConfig()
  {
    if (!this.request.isHttps())
      return null;
    String str = this.request.getUserAgent();
    if (str == null)
      str = getDefaultUserAgent();
    URL localURL = this.request.url();
    return new TunnelRequest(localURL.getHost(), Util.getEffectivePort(localURL), str, this.request.getProxyAuthorization());
  }

  public static String hostHeader(URL paramURL)
  {
    if (Util.getEffectivePort(paramURL) != Util.getDefaultPort(paramURL.getProtocol()))
      return paramURL.getHost() + ":" + paramURL.getPort();
    return paramURL.getHost();
  }

  private void initContentStream(Source paramSource)
    throws IOException
  {
    this.responseTransferSource = paramSource;
    if ((this.transparentGzip) && ("gzip".equalsIgnoreCase(this.response.header("Content-Encoding"))))
    {
      this.response = this.response.newBuilder().removeHeader("Content-Encoding").removeHeader("Content-Length").build();
      this.responseBody = new GzipSource(paramSource);
      return;
    }
    this.responseBody = paramSource;
  }

  private static boolean isEndToEnd(String paramString)
  {
    return (!"Connection".equalsIgnoreCase(paramString)) && (!"Keep-Alive".equalsIgnoreCase(paramString)) && (!"Proxy-Authenticate".equalsIgnoreCase(paramString)) && (!"Proxy-Authorization".equalsIgnoreCase(paramString)) && (!"TE".equalsIgnoreCase(paramString)) && (!"Trailers".equalsIgnoreCase(paramString)) && (!"Transfer-Encoding".equalsIgnoreCase(paramString)) && (!"Upgrade".equalsIgnoreCase(paramString));
  }

  private boolean isRecoverable(IOException paramIOException)
  {
    if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException)));
    for (int i = 1; ; i = 0)
    {
      boolean bool = paramIOException instanceof ProtocolException;
      if ((i != 0) || (bool))
        break;
      return true;
    }
    return false;
  }

  private void maybeCache()
    throws IOException
  {
    OkResponseCache localOkResponseCache = this.client.getOkResponseCache();
    if (localOkResponseCache == null)
      return;
    if (!CacheStrategy.isCacheable(this.response, this.request))
    {
      localOkResponseCache.maybeRemove(this.request);
      return;
    }
    this.cacheRequest = localOkResponseCache.put(cacheableResponse());
  }

  private void prepareRawRequestHeaders()
    throws IOException
  {
    Request.Builder localBuilder = this.request.newBuilder();
    if (this.request.getUserAgent() == null)
      localBuilder.setUserAgent(getDefaultUserAgent());
    if (this.request.header("Host") == null)
      localBuilder.header("Host", hostHeader(this.request.url()));
    if (((this.connection == null) || (this.connection.getHttpMinorVersion() != 0)) && (this.request.header("Connection") == null))
      localBuilder.header("Connection", "Keep-Alive");
    if (this.request.header("Accept-Encoding") == null)
    {
      this.transparentGzip = true;
      localBuilder.header("Accept-Encoding", "gzip");
    }
    if ((hasRequestBody()) && (this.request.header("Content-Type") == null))
      localBuilder.header("Content-Type", "application/x-www-form-urlencoded");
    CookieHandler localCookieHandler = this.client.getCookieHandler();
    if (localCookieHandler != null)
    {
      Map localMap = OkHeaders.toMultimap(localBuilder.build().headers(), null);
      OkHeaders.addCookies(localBuilder, localCookieHandler.get(this.request.uri(), localMap));
    }
    this.request = localBuilder.build();
  }

  public final Connection close()
  {
    if (this.bufferedRequestBody != null)
      Util.closeQuietly(this.bufferedRequestBody);
    while (this.responseBody == null)
    {
      Util.closeQuietly(this.connection);
      this.connection = null;
      return null;
      if (this.requestBodyOut == null)
        continue;
      Util.closeQuietly(this.requestBodyOut);
    }
    Util.closeQuietly(this.responseBody);
    Util.closeQuietly(this.responseBodyBytes);
    if ((this.transport != null) && (!this.transport.canReuseConnection()))
    {
      Util.closeQuietly(this.connection);
      this.connection = null;
      return null;
    }
    if ((this.connection != null) && (!this.connection.clearOwner()))
      this.connection = null;
    Connection localConnection = this.connection;
    this.connection = null;
    return localConnection;
  }

  public final void disconnect()
    throws IOException
  {
    if (this.transport != null)
      this.transport.disconnect(this);
  }

  public final BufferedSink getBufferedRequestBody()
  {
    BufferedSink localBufferedSink1 = this.bufferedRequestBody;
    if (localBufferedSink1 != null)
      return localBufferedSink1;
    Sink localSink = getRequestBody();
    if (localSink != null)
    {
      BufferedSink localBufferedSink2 = Okio.buffer(localSink);
      this.bufferedRequestBody = localBufferedSink2;
      return localBufferedSink2;
    }
    return null;
  }

  public final Connection getConnection()
  {
    return this.connection;
  }

  public final Request getRequest()
  {
    return this.request;
  }

  public final Sink getRequestBody()
  {
    if (this.responseSource == null)
      throw new IllegalStateException();
    return this.requestBodyOut;
  }

  public final Response getResponse()
  {
    if (this.response == null)
      throw new IllegalStateException();
    return this.response;
  }

  public final Source getResponseBody()
  {
    if (this.response == null)
      throw new IllegalStateException();
    return this.responseBody;
  }

  public final InputStream getResponseBodyBytes()
  {
    InputStream localInputStream1 = this.responseBodyBytes;
    if (localInputStream1 != null)
      return localInputStream1;
    InputStream localInputStream2 = Okio.buffer(getResponseBody()).inputStream();
    this.responseBodyBytes = localInputStream2;
    return localInputStream2;
  }

  public Route getRoute()
  {
    return this.route;
  }

  boolean hasRequestBody()
  {
    return HttpMethod.hasRequestBody(this.request.method());
  }

  public final boolean hasResponse()
  {
    return this.response != null;
  }

  public final boolean hasResponseBody()
  {
    if (this.request.method().equals("HEAD"));
    do
    {
      return false;
      int i = this.response.code();
      if (((i < 100) || (i >= 200)) && (i != 204) && (i != 304))
        return true;
    }
    while ((OkHeaders.contentLength(this.response) == -1L) && (!"chunked".equalsIgnoreCase(this.response.header("Transfer-Encoding"))));
    return true;
  }

  public final void readResponse()
    throws IOException
  {
    if (this.response != null);
    do
    {
      return;
      if (this.responseSource != null)
        continue;
      throw new IllegalStateException("call sendRequest() first!");
    }
    while (!this.responseSource.requiresConnection());
    if ((this.bufferedRequestBody != null) && (this.bufferedRequestBody.buffer().size() > 0L))
      this.bufferedRequestBody.flush();
    if (this.sentRequestMillis == -1L)
    {
      if ((OkHeaders.contentLength(this.request) == -1L) && ((this.requestBodyOut instanceof RetryableSink)))
      {
        long l = ((RetryableSink)this.requestBodyOut).contentLength();
        this.request = this.request.newBuilder().header("Content-Length", Long.toString(l)).build();
      }
      this.transport.writeRequestHeaders(this.request);
    }
    if (this.requestBodyOut != null)
    {
      if (this.bufferedRequestBody == null)
        break label405;
      this.bufferedRequestBody.close();
    }
    while (true)
    {
      if ((this.requestBodyOut instanceof RetryableSink))
        this.transport.writeRequestBody((RetryableSink)this.requestBodyOut);
      this.transport.flushRequest();
      this.response = this.transport.readResponseHeaders().request(this.request).handshake(this.connection.getHandshake()).header(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis)).header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis())).setResponseSource(this.responseSource).build();
      this.connection.setHttpMinorVersion(this.response.httpMinorVersion());
      receiveHeaders(this.response.headers());
      if (this.responseSource != ResponseSource.CONDITIONAL_CACHE)
        break label427;
      if (!this.validatingResponse.validate(this.response))
        break label417;
      this.transport.emptyTransferStream();
      releaseConnection();
      this.response = combine(this.validatingResponse, this.response);
      OkResponseCache localOkResponseCache = this.client.getOkResponseCache();
      localOkResponseCache.trackConditionalCacheHit();
      localOkResponseCache.update(this.validatingResponse, cacheableResponse());
      if (this.validatingResponse.body() == null)
        break;
      initContentStream(this.validatingResponse.body().source());
      return;
      label405: this.requestBodyOut.close();
    }
    label417: Util.closeQuietly(this.validatingResponse.body());
    label427: if (!hasResponseBody())
    {
      this.responseTransferSource = this.transport.getTransferStream(this.cacheRequest);
      this.responseBody = this.responseTransferSource;
      return;
    }
    maybeCache();
    initContentStream(this.transport.getTransferStream(this.cacheRequest));
  }

  public void receiveHeaders(Headers paramHeaders)
    throws IOException
  {
    CookieHandler localCookieHandler = this.client.getCookieHandler();
    if (localCookieHandler != null)
      localCookieHandler.put(this.request.uri(), OkHeaders.toMultimap(paramHeaders, null));
  }

  public HttpEngine recover(IOException paramIOException)
  {
    if ((this.routeSelector != null) && (this.connection != null))
      this.routeSelector.connectFailed(this.connection, paramIOException);
    if ((this.requestBodyOut == null) || ((this.requestBodyOut instanceof RetryableSink)));
    for (int i = 1; ((this.routeSelector == null) && (this.connection == null)) || ((this.routeSelector != null) && (!this.routeSelector.hasNext())) || (!isRecoverable(paramIOException)) || (i == 0); i = 0)
      return null;
    Connection localConnection = close();
    return new HttpEngine(this.client, this.originalRequest, this.bufferRequestBody, localConnection, this.routeSelector, (RetryableSink)this.requestBodyOut);
  }

  public final void releaseConnection()
    throws IOException
  {
    if ((this.transport != null) && (this.connection != null))
      this.transport.releaseConnectionOnIdle();
    this.connection = null;
  }

  public final ResponseSource responseSource()
  {
    return this.responseSource;
  }

  public final void sendRequest()
    throws IOException
  {
    if (this.responseSource != null);
    do
    {
      while (true)
      {
        return;
        if (this.transport != null)
          throw new IllegalStateException();
        prepareRawRequestHeaders();
        OkResponseCache localOkResponseCache = this.client.getOkResponseCache();
        Response localResponse;
        if (localOkResponseCache != null)
          localResponse = localOkResponseCache.get(this.request);
        while (true)
        {
          CacheStrategy localCacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), this.request, localResponse).get();
          this.responseSource = localCacheStrategy.source;
          this.request = localCacheStrategy.request;
          if (localOkResponseCache != null)
            localOkResponseCache.trackResponse(this.responseSource);
          if (this.responseSource != ResponseSource.NETWORK)
            this.validatingResponse = localCacheStrategy.response;
          if ((localResponse != null) && (!this.responseSource.usesCache()))
            Util.closeQuietly(localResponse.body());
          if (this.responseSource.requiresConnection())
          {
            if (this.connection == null)
              connect();
            if ((this.connection.getOwner() != this) && (!this.connection.isSpdy()))
            {
              throw new AssertionError();
              localResponse = null;
              continue;
            }
            this.transport = ((Transport)this.connection.newTransport(this));
            if ((!hasRequestBody()) || (this.requestBodyOut != null))
              break;
            this.requestBodyOut = this.transport.createRequestBody(this.request);
            return;
          }
        }
      }
      if (this.connection != null)
      {
        this.client.getConnectionPool().recycle(this.connection);
        this.connection = null;
      }
      this.response = this.validatingResponse;
    }
    while (this.validatingResponse.body() == null);
    initContentStream(this.validatingResponse.body().source());
  }

  public void writingRequestHeaders()
  {
    if (this.sentRequestMillis != -1L)
      throw new IllegalStateException();
    this.sentRequestMillis = System.currentTimeMillis();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpEngine
 * JD-Core Version:    0.6.0
 */