package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpAuthenticator;
import com.squareup.okhttp.internal.http.HttpURLConnectionImpl;
import com.squareup.okhttp.internal.http.HttpsURLConnectionImpl;
import com.squareup.okhttp.internal.http.ResponseCacheAdapter;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.tls.OkHostnameVerifier;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public final class OkHttpClient
  implements Cloneable, URLStreamHandlerFactory
{
  private OkAuthenticator authenticator;
  private int connectTimeout;
  private ConnectionPool connectionPool;
  private CookieHandler cookieHandler;
  private boolean followProtocolRedirects = true;
  private HostnameVerifier hostnameVerifier;
  private List<Protocol> protocols;
  private Proxy proxy;
  private ProxySelector proxySelector;
  private int readTimeout;
  private OkResponseCache responseCache;
  private final RouteDatabase routeDatabase = new RouteDatabase();
  private SSLSocketFactory sslSocketFactory;

  private SSLSocketFactory getDefaultSSLSocketFactory()
  {
    monitorenter;
    try
    {
      SSLSocketFactory localSSLSocketFactory1 = this.sslSocketFactory;
      if (localSSLSocketFactory1 == null);
      try
      {
        SSLContext localSSLContext = SSLContext.getInstance("TLS");
        localSSLContext.init(null, null, null);
        this.sslSocketFactory = localSSLContext.getSocketFactory();
        SSLSocketFactory localSSLSocketFactory2 = this.sslSocketFactory;
        monitorexit;
        return localSSLSocketFactory2;
      }
      catch (GeneralSecurityException localGeneralSecurityException)
      {
        throw new AssertionError();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private OkResponseCache toOkResponseCache(ResponseCache paramResponseCache)
  {
    if ((paramResponseCache == null) || ((paramResponseCache instanceof OkResponseCache)))
      return (OkResponseCache)paramResponseCache;
    return new ResponseCacheAdapter(paramResponseCache);
  }

  public OkHttpClient clone()
  {
    try
    {
      OkHttpClient localOkHttpClient = (OkHttpClient)super.clone();
      return localOkHttpClient;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    throw new AssertionError();
  }

  OkHttpClient copyWithDefaults()
  {
    OkHttpClient localOkHttpClient = clone();
    if (localOkHttpClient.proxySelector == null)
      localOkHttpClient.proxySelector = ProxySelector.getDefault();
    if (localOkHttpClient.cookieHandler == null)
      localOkHttpClient.cookieHandler = CookieHandler.getDefault();
    if (localOkHttpClient.responseCache == null)
      localOkHttpClient.responseCache = toOkResponseCache(ResponseCache.getDefault());
    if (localOkHttpClient.sslSocketFactory == null)
      localOkHttpClient.sslSocketFactory = getDefaultSSLSocketFactory();
    if (localOkHttpClient.hostnameVerifier == null)
      localOkHttpClient.hostnameVerifier = OkHostnameVerifier.INSTANCE;
    if (localOkHttpClient.authenticator == null)
      localOkHttpClient.authenticator = HttpAuthenticator.SYSTEM_DEFAULT;
    if (localOkHttpClient.connectionPool == null)
      localOkHttpClient.connectionPool = ConnectionPool.getDefault();
    if (localOkHttpClient.protocols == null)
      localOkHttpClient.protocols = Util.HTTP2_SPDY3_AND_HTTP;
    return localOkHttpClient;
  }

  public URLStreamHandler createURLStreamHandler(String paramString)
  {
    if ((!paramString.equals("http")) && (!paramString.equals("https")))
      return null;
    return new URLStreamHandler(paramString)
    {
      protected int getDefaultPort()
      {
        if (this.val$protocol.equals("http"))
          return 80;
        if (this.val$protocol.equals("https"))
          return 443;
        throw new AssertionError();
      }

      protected URLConnection openConnection(URL paramURL)
      {
        return OkHttpClient.this.open(paramURL);
      }

      protected URLConnection openConnection(URL paramURL, Proxy paramProxy)
      {
        return OkHttpClient.this.open(paramURL, paramProxy);
      }
    };
  }

  public OkAuthenticator getAuthenticator()
  {
    return this.authenticator;
  }

  public int getConnectTimeout()
  {
    return this.connectTimeout;
  }

  public ConnectionPool getConnectionPool()
  {
    return this.connectionPool;
  }

  public CookieHandler getCookieHandler()
  {
    return this.cookieHandler;
  }

  public boolean getFollowProtocolRedirects()
  {
    return this.followProtocolRedirects;
  }

  public HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public OkResponseCache getOkResponseCache()
  {
    return this.responseCache;
  }

  public List<Protocol> getProtocols()
  {
    return this.protocols;
  }

  public Proxy getProxy()
  {
    return this.proxy;
  }

  public ProxySelector getProxySelector()
  {
    return this.proxySelector;
  }

  public int getReadTimeout()
  {
    return this.readTimeout;
  }

  public ResponseCache getResponseCache()
  {
    if ((this.responseCache instanceof ResponseCacheAdapter))
      return ((ResponseCacheAdapter)this.responseCache).getDelegate();
    return null;
  }

  public RouteDatabase getRoutesDatabase()
  {
    return this.routeDatabase;
  }

  public SSLSocketFactory getSslSocketFactory()
  {
    return this.sslSocketFactory;
  }

  @Deprecated
  public List<String> getTransports()
  {
    ArrayList localArrayList = new ArrayList(this.protocols.size());
    int i = this.protocols.size();
    for (int j = 0; j < i; j++)
      localArrayList.add(((Protocol)this.protocols.get(j)).name.utf8());
    return localArrayList;
  }

  public HttpURLConnection open(URL paramURL)
  {
    return open(paramURL, this.proxy);
  }

  HttpURLConnection open(URL paramURL, Proxy paramProxy)
  {
    String str = paramURL.getProtocol();
    OkHttpClient localOkHttpClient = copyWithDefaults();
    localOkHttpClient.proxy = paramProxy;
    if (str.equals("http"))
      return new HttpURLConnectionImpl(paramURL, localOkHttpClient);
    if (str.equals("https"))
      return new HttpsURLConnectionImpl(paramURL, localOkHttpClient);
    throw new IllegalArgumentException("Unexpected protocol: " + str);
  }

  public OkHttpClient setAuthenticator(OkAuthenticator paramOkAuthenticator)
  {
    this.authenticator = paramOkAuthenticator;
    return this;
  }

  public void setConnectTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("timeout < 0");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("unit == null");
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L)
      throw new IllegalArgumentException("Timeout too large.");
    this.connectTimeout = (int)l;
  }

  public OkHttpClient setConnectionPool(ConnectionPool paramConnectionPool)
  {
    this.connectionPool = paramConnectionPool;
    return this;
  }

  public OkHttpClient setCookieHandler(CookieHandler paramCookieHandler)
  {
    this.cookieHandler = paramCookieHandler;
    return this;
  }

  public OkHttpClient setFollowProtocolRedirects(boolean paramBoolean)
  {
    this.followProtocolRedirects = paramBoolean;
    return this;
  }

  public OkHttpClient setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
  {
    this.hostnameVerifier = paramHostnameVerifier;
    return this;
  }

  public OkHttpClient setOkResponseCache(OkResponseCache paramOkResponseCache)
  {
    this.responseCache = paramOkResponseCache;
    return this;
  }

  public OkHttpClient setProtocols(List<Protocol> paramList)
  {
    List localList = Util.immutableList(paramList);
    if (!localList.contains(Protocol.HTTP_11))
      throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + localList);
    if (localList.contains(null))
      throw new IllegalArgumentException("protocols must not contain null");
    this.protocols = Util.immutableList(localList);
    return this;
  }

  public OkHttpClient setProxy(Proxy paramProxy)
  {
    this.proxy = paramProxy;
    return this;
  }

  public OkHttpClient setProxySelector(ProxySelector paramProxySelector)
  {
    this.proxySelector = paramProxySelector;
    return this;
  }

  public void setReadTimeout(long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("timeout < 0");
    if (paramTimeUnit == null)
      throw new IllegalArgumentException("unit == null");
    long l = paramTimeUnit.toMillis(paramLong);
    if (l > 2147483647L)
      throw new IllegalArgumentException("Timeout too large.");
    this.readTimeout = (int)l;
  }

  public OkHttpClient setResponseCache(ResponseCache paramResponseCache)
  {
    return setOkResponseCache(toOkResponseCache(paramResponseCache));
  }

  public OkHttpClient setSslSocketFactory(SSLSocketFactory paramSSLSocketFactory)
  {
    this.sslSocketFactory = paramSSLSocketFactory;
    return this;
  }

  @Deprecated
  public OkHttpClient setTransports(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    int i = paramList.size();
    int j = 0;
    while (j < i)
      try
      {
        localArrayList.add(Util.getProtocol(ByteString.encodeUtf8((String)paramList.get(j))));
        j++;
      }
      catch (IOException localIOException)
      {
        throw new IllegalArgumentException(localIOException);
      }
    return setProtocols(localArrayList);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.OkHttpClient
 * JD-Core Version:    0.6.0
 */