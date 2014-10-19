package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Route;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Sink;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketPermission;
import java.net.URL;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpURLConnectionImpl extends HttpURLConnection
{
  public static final int MAX_REDIRECTS = 20;
  final OkHttpClient client;
  private long fixedContentLength = -1L;
  Handshake handshake;
  protected HttpEngine httpEngine;
  protected IOException httpEngineFailure;
  private int redirectionCount;
  private Headers.Builder requestHeaders = new Headers.Builder();
  private Route route;

  public HttpURLConnectionImpl(URL paramURL, OkHttpClient paramOkHttpClient)
  {
    super(paramURL);
    this.client = paramOkHttpClient;
  }

  private boolean execute(boolean paramBoolean)
    throws IOException
  {
    try
    {
      this.httpEngine.sendRequest();
      this.route = this.httpEngine.getRoute();
      if (this.httpEngine.getConnection() != null);
      for (Handshake localHandshake = this.httpEngine.getConnection().getHandshake(); ; localHandshake = null)
      {
        this.handshake = localHandshake;
        if (paramBoolean)
          this.httpEngine.readResponse();
        return true;
      }
    }
    catch (IOException localIOException)
    {
      HttpEngine localHttpEngine = this.httpEngine.recover(localIOException);
      if (localHttpEngine != null)
      {
        this.httpEngine = localHttpEngine;
        return false;
      }
      this.httpEngineFailure = localIOException;
    }
    throw localIOException;
  }

  private HttpEngine getResponse()
    throws IOException
  {
    initHttpEngine();
    if (this.httpEngine.hasResponse())
      return this.httpEngine;
    while (true)
      if (execute(true))
      {
        Retry localRetry = processResponseHeaders();
        if (localRetry == Retry.NONE)
        {
          this.httpEngine.releaseConnection();
          return this.httpEngine;
        }
        String str = this.method;
        Sink localSink = this.httpEngine.getRequestBody();
        int i = this.httpEngine.getResponse().code();
        if ((i == 300) || (i == 301) || (i == 302) || (i == 303))
        {
          str = "GET";
          this.requestHeaders.removeAll("Content-Length");
          localSink = null;
        }
        if ((localSink != null) && (!(localSink instanceof RetryableSink)))
          throw new HttpRetryException("Cannot retry streamed HTTP body", i);
        if (localRetry == Retry.DIFFERENT_CONNECTION)
          this.httpEngine.releaseConnection();
        this.httpEngine = newHttpEngine(str, this.httpEngine.close(), (RetryableSink)localSink);
        continue;
      }
  }

  private void initHttpEngine()
    throws IOException
  {
    if (this.httpEngineFailure != null)
      throw this.httpEngineFailure;
    if (this.httpEngine != null)
      return;
    this.connected = true;
    do
      try
      {
        if (this.doOutput)
        {
          if (!this.method.equals("GET"))
            continue;
          this.method = "POST";
        }
        this.httpEngine = newHttpEngine(this.method, null, null);
        return;
      }
      catch (IOException localIOException)
      {
        this.httpEngineFailure = localIOException;
        throw localIOException;
      }
    while (HttpMethod.hasRequestBody(this.method));
    throw new ProtocolException(this.method + " does not support writing");
  }

  private HttpEngine newHttpEngine(String paramString, Connection paramConnection, RetryableSink paramRetryableSink)
  {
    Request.Builder localBuilder = new Request.Builder().url(getURL()).method(paramString, null);
    Headers localHeaders = this.requestHeaders.build();
    for (int i = 0; i < localHeaders.size(); i++)
      localBuilder.addHeader(localHeaders.name(i), localHeaders.value(i));
    boolean bool;
    if (HttpMethod.hasRequestBody(paramString))
      if (this.fixedContentLength != -1L)
      {
        localBuilder.header("Content-Length", Long.toString(this.fixedContentLength));
        bool = false;
      }
    while (true)
    {
      Request localRequest = localBuilder.build();
      OkHttpClient localOkHttpClient = this.client;
      if ((localOkHttpClient.getOkResponseCache() != null) && (!getUseCaches()))
        localOkHttpClient = this.client.clone().setOkResponseCache(null);
      return new HttpEngine(localOkHttpClient, localRequest, bool, paramConnection, null, paramRetryableSink);
      if (this.chunkLength > 0)
      {
        localBuilder.header("Transfer-Encoding", "chunked");
        bool = false;
        continue;
      }
      bool = true;
      continue;
      bool = false;
    }
  }

  private Retry processResponseHeaders()
    throws IOException
  {
    Connection localConnection = this.httpEngine.getConnection();
    if (localConnection != null);
    int i;
    for (Proxy localProxy = localConnection.getRoute().getProxy(); ; localProxy = this.client.getProxy())
    {
      i = getResponseCode();
      switch (i)
      {
      default:
        return Retry.NONE;
      case 407:
      case 401:
      case 300:
      case 301:
      case 302:
      case 303:
      case 307:
      }
    }
    if (localProxy.type() != Proxy.Type.HTTP)
      throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
    Request localRequest = HttpAuthenticator.processAuthHeader(this.client.getAuthenticator(), this.httpEngine.getResponse(), localProxy);
    if (localRequest == null)
      return Retry.NONE;
    this.requestHeaders = localRequest.getHeaders().newBuilder();
    return Retry.SAME_CONNECTION;
    if (!getInstanceFollowRedirects())
      return Retry.NONE;
    int j = 1 + this.redirectionCount;
    this.redirectionCount = j;
    if (j > 20)
      throw new ProtocolException("Too many redirects: " + this.redirectionCount);
    if ((i == 307) && (!this.method.equals("GET")) && (!this.method.equals("HEAD")))
      return Retry.NONE;
    String str = getHeaderField("Location");
    if (str == null)
      return Retry.NONE;
    URL localURL = this.url;
    this.url = new URL(localURL, str);
    if ((!this.url.getProtocol().equals("https")) && (!this.url.getProtocol().equals("http")))
      return Retry.NONE;
    boolean bool1 = localURL.getProtocol().equals(this.url.getProtocol());
    if ((!bool1) && (!this.client.getFollowProtocolRedirects()))
      return Retry.NONE;
    boolean bool2 = localURL.getHost().equals(this.url.getHost());
    if (Util.getEffectivePort(localURL) == Util.getEffectivePort(this.url));
    for (int k = 1; (bool2) && (k != 0) && (bool1); k = 0)
      return Retry.SAME_CONNECTION;
    return Retry.DIFFERENT_CONNECTION;
  }

  private void setProtocols(String paramString, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramBoolean)
      localArrayList.addAll(this.client.getProtocols());
    String[] arrayOfString = paramString.split(",", -1);
    int i = arrayOfString.length;
    int j = 0;
    while (j < i)
    {
      String str = arrayOfString[j];
      try
      {
        localArrayList.add(Util.getProtocol(ByteString.encodeUtf8(str)));
        j++;
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }
    this.client.setProtocols(localArrayList);
  }

  public final void addRequestProperty(String paramString1, String paramString2)
  {
    if (this.connected)
      throw new IllegalStateException("Cannot add request property after connection is made");
    if (paramString1 == null)
      throw new NullPointerException("field == null");
    if (paramString2 == null)
    {
      Platform.get().logW("Ignoring header " + paramString1 + " because its value was null.");
      return;
    }
    if (("X-Android-Transports".equals(paramString1)) || ("X-Android-Protocols".equals(paramString1)))
    {
      setProtocols(paramString2, true);
      return;
    }
    this.requestHeaders.add(paramString1, paramString2);
  }

  public final void connect()
    throws IOException
  {
    initHttpEngine();
    while (!execute(false));
  }

  public final void disconnect()
  {
    if (this.httpEngine == null)
      return;
    try
    {
      this.httpEngine.disconnect();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public int getConnectTimeout()
  {
    return this.client.getConnectTimeout();
  }

  public final InputStream getErrorStream()
  {
    try
    {
      HttpEngine localHttpEngine = getResponse();
      boolean bool = localHttpEngine.hasResponseBody();
      Object localObject = null;
      if (bool)
      {
        int i = localHttpEngine.getResponse().code();
        localObject = null;
        if (i >= 400)
        {
          InputStream localInputStream = localHttpEngine.getResponseBodyBytes();
          localObject = localInputStream;
        }
      }
      return localObject;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public final String getHeaderField(int paramInt)
  {
    try
    {
      String str = getResponse().getResponse().headers().value(paramInt);
      return str;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public final String getHeaderField(String paramString)
  {
    try
    {
      Response localResponse = getResponse().getResponse();
      if (paramString == null)
        return localResponse.statusLine();
      String str = localResponse.headers().get(paramString);
      return str;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public final String getHeaderFieldKey(int paramInt)
  {
    try
    {
      String str = getResponse().getResponse().headers().name(paramInt);
      return str;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public final Map<String, List<String>> getHeaderFields()
  {
    try
    {
      Response localResponse = getResponse().getResponse();
      Map localMap = OkHeaders.toMultimap(localResponse.headers(), localResponse.statusLine());
      return localMap;
    }
    catch (IOException localIOException)
    {
    }
    return Collections.emptyMap();
  }

  public final InputStream getInputStream()
    throws IOException
  {
    if (!this.doInput)
      throw new ProtocolException("This protocol does not support input");
    HttpEngine localHttpEngine = getResponse();
    if (getResponseCode() >= 400)
      throw new FileNotFoundException(this.url.toString());
    InputStream localInputStream = localHttpEngine.getResponseBodyBytes();
    if (localInputStream == null)
      throw new ProtocolException("No response body exists; responseCode=" + getResponseCode());
    return localInputStream;
  }

  public final OutputStream getOutputStream()
    throws IOException
  {
    connect();
    BufferedSink localBufferedSink = this.httpEngine.getBufferedRequestBody();
    if (localBufferedSink == null)
      throw new ProtocolException("method does not support a request body: " + this.method);
    if (this.httpEngine.hasResponse())
      throw new ProtocolException("cannot write request body after response has been read");
    return localBufferedSink.outputStream();
  }

  public final Permission getPermission()
    throws IOException
  {
    String str = getURL().getHost();
    int i = Util.getEffectivePort(getURL());
    if (usingProxy())
    {
      InetSocketAddress localInetSocketAddress = (InetSocketAddress)this.client.getProxy().address();
      str = localInetSocketAddress.getHostName();
      i = localInetSocketAddress.getPort();
    }
    return new SocketPermission(str + ":" + i, "connect, resolve");
  }

  public int getReadTimeout()
  {
    return this.client.getReadTimeout();
  }

  public final Map<String, List<String>> getRequestProperties()
  {
    if (this.connected)
      throw new IllegalStateException("Cannot access request header fields after connection is set");
    return OkHeaders.toMultimap(this.requestHeaders.build(), null);
  }

  public final String getRequestProperty(String paramString)
  {
    if (paramString == null)
      return null;
    return this.requestHeaders.get(paramString);
  }

  public final int getResponseCode()
    throws IOException
  {
    return getResponse().getResponse().code();
  }

  public String getResponseMessage()
    throws IOException
  {
    return getResponse().getResponse().statusMessage();
  }

  public void setConnectTimeout(int paramInt)
  {
    this.client.setConnectTimeout(paramInt, TimeUnit.MILLISECONDS);
  }

  public void setFixedLengthStreamingMode(int paramInt)
  {
    setFixedLengthStreamingMode(paramInt);
  }

  public void setFixedLengthStreamingMode(long paramLong)
  {
    if (this.connected)
      throw new IllegalStateException("Already connected");
    if (this.chunkLength > 0)
      throw new IllegalStateException("Already in chunked mode");
    if (paramLong < 0L)
      throw new IllegalArgumentException("contentLength < 0");
    this.fixedContentLength = paramLong;
    this.fixedContentLength = (int)Math.min(paramLong, 2147483647L);
  }

  public void setIfModifiedSince(long paramLong)
  {
    super.setIfModifiedSince(paramLong);
    if (this.ifModifiedSince != 0L)
    {
      this.requestHeaders.set("If-Modified-Since", HttpDate.format(new Date(this.ifModifiedSince)));
      return;
    }
    this.requestHeaders.removeAll("If-Modified-Since");
  }

  public void setReadTimeout(int paramInt)
  {
    this.client.setReadTimeout(paramInt, TimeUnit.MILLISECONDS);
  }

  public void setRequestMethod(String paramString)
    throws ProtocolException
  {
    if (!HttpMethod.METHODS.contains(paramString))
      throw new ProtocolException("Expected one of " + HttpMethod.METHODS + " but was " + paramString);
    this.method = paramString;
  }

  public final void setRequestProperty(String paramString1, String paramString2)
  {
    if (this.connected)
      throw new IllegalStateException("Cannot set request property after connection is made");
    if (paramString1 == null)
      throw new NullPointerException("field == null");
    if (paramString2 == null)
    {
      Platform.get().logW("Ignoring header " + paramString1 + " because its value was null.");
      return;
    }
    if (("X-Android-Transports".equals(paramString1)) || ("X-Android-Protocols".equals(paramString1)))
    {
      setProtocols(paramString2, false);
      return;
    }
    this.requestHeaders.set(paramString1, paramString2);
  }

  public final boolean usingProxy()
  {
    if (this.route != null);
    for (Proxy localProxy = this.route.getProxy(); (localProxy != null) && (localProxy.type() != Proxy.Type.DIRECT); localProxy = this.client.getProxy())
      return true;
    return false;
  }

  static enum Retry
  {
    static
    {
      DIFFERENT_CONNECTION = new Retry("DIFFERENT_CONNECTION", 2);
      Retry[] arrayOfRetry = new Retry[3];
      arrayOfRetry[0] = NONE;
      arrayOfRetry[1] = SAME_CONNECTION;
      arrayOfRetry[2] = DIFFERENT_CONNECTION;
      $VALUES = arrayOfRetry;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpURLConnectionImpl
 * JD-Core Version:    0.6.0
 */