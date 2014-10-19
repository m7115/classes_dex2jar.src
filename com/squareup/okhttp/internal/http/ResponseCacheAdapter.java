package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkResponseCache;
import com.squareup.okhttp.ResponseSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.ResponseCache;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

public class ResponseCacheAdapter
  implements OkResponseCache
{
  private final ResponseCache delegate;

  public ResponseCacheAdapter(ResponseCache paramResponseCache)
  {
    this.delegate = paramResponseCache;
  }

  private static HttpURLConnection createJavaUrlConnection(Response paramResponse)
  {
    if (paramResponse.request().isHttps())
      return new CacheHttpsURLConnection(new CacheHttpURLConnection(paramResponse));
    return new CacheHttpURLConnection(paramResponse);
  }

  private static Response.Body createOkBody(Headers paramHeaders, InputStream paramInputStream)
  {
    return new Response.Body(paramHeaders, paramInputStream)
    {
      public InputStream byteStream()
      {
        return this.val$body;
      }

      public long contentLength()
      {
        return OkHeaders.contentLength(this.val$okHeaders);
      }

      public MediaType contentType()
      {
        String str = this.val$okHeaders.get("Content-Type");
        if (str == null)
          return null;
        return MediaType.parse(str);
      }

      public boolean ready()
        throws IOException
      {
        return true;
      }
    };
  }

  private static Map<String, List<String>> extractJavaHeaders(Request paramRequest)
  {
    return OkHeaders.toMultimap(paramRequest.headers(), null);
  }

  private static Headers extractOkHeaders(CacheResponse paramCacheResponse)
    throws IOException
  {
    Map localMap = paramCacheResponse.getHeaders();
    Headers.Builder localBuilder = new Headers.Builder();
    Iterator localIterator1 = localMap.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      String str = (String)localEntry.getKey();
      if (str == null)
        continue;
      Iterator localIterator2 = ((List)localEntry.getValue()).iterator();
      while (localIterator2.hasNext())
        localBuilder.add(str, (String)localIterator2.next());
    }
    return localBuilder.build();
  }

  private static String extractStatusLine(CacheResponse paramCacheResponse)
    throws IOException
  {
    List localList = (List)paramCacheResponse.getHeaders().get(null);
    if ((localList == null) || (localList.size() == 0))
      return null;
    return (String)localList.get(0);
  }

  private CacheResponse getJavaCachedResponse(Request paramRequest)
    throws IOException
  {
    Map localMap = extractJavaHeaders(paramRequest);
    return this.delegate.get(paramRequest.uri(), paramRequest.method(), localMap);
  }

  private static RuntimeException throwRequestHeaderAccessException()
  {
    throw new UnsupportedOperationException("ResponseCache cannot access request headers");
  }

  private static RuntimeException throwRequestModificationException()
  {
    throw new UnsupportedOperationException("ResponseCache cannot modify the request.");
  }

  private static RuntimeException throwRequestSslAccessException()
  {
    throw new UnsupportedOperationException("ResponseCache cannot access SSL internals");
  }

  private static RuntimeException throwResponseBodyAccessException()
  {
    throw new UnsupportedOperationException("ResponseCache cannot access the response body.");
  }

  public Response get(Request paramRequest)
    throws IOException
  {
    CacheResponse localCacheResponse = getJavaCachedResponse(paramRequest);
    if (localCacheResponse == null)
      return null;
    Response.Builder localBuilder = new Response.Builder();
    localBuilder.request(paramRequest);
    localBuilder.statusLine(extractStatusLine(localCacheResponse));
    Headers localHeaders = extractOkHeaders(localCacheResponse);
    localBuilder.headers(localHeaders);
    localBuilder.setResponseSource(ResponseSource.CACHE);
    localBuilder.body(createOkBody(localHeaders, localCacheResponse.getBody()));
    SecureCacheResponse localSecureCacheResponse;
    if ((localCacheResponse instanceof SecureCacheResponse))
      localSecureCacheResponse = (SecureCacheResponse)localCacheResponse;
    try
    {
      List localList3 = localSecureCacheResponse.getServerCertificateChain();
      localList1 = localList3;
      List localList2 = localSecureCacheResponse.getLocalCertificateChain();
      if (localList2 == null)
        localList2 = Collections.emptyList();
      localBuilder.handshake(Handshake.get(localSecureCacheResponse.getCipherSuite(), localList1, localList2));
      return localBuilder.build();
    }
    catch (SSLPeerUnverifiedException localSSLPeerUnverifiedException)
    {
      while (true)
        List localList1 = Collections.emptyList();
    }
  }

  public ResponseCache getDelegate()
  {
    return this.delegate;
  }

  public boolean maybeRemove(Request paramRequest)
    throws IOException
  {
    return false;
  }

  public CacheRequest put(Response paramResponse)
    throws IOException
  {
    URI localURI = paramResponse.request().uri();
    HttpURLConnection localHttpURLConnection = createJavaUrlConnection(paramResponse);
    return this.delegate.put(localURI, localHttpURLConnection);
  }

  public void trackConditionalCacheHit()
  {
  }

  public void trackResponse(ResponseSource paramResponseSource)
  {
  }

  public void update(Response paramResponse1, Response paramResponse2)
    throws IOException
  {
  }

  private static final class CacheHttpURLConnection extends HttpURLConnection
  {
    private final Request request;
    private final Response response;

    public CacheHttpURLConnection(Response paramResponse)
    {
      super();
      this.request = paramResponse.request();
      this.response = paramResponse;
      this.connected = bool;
      if (paramResponse.body() == null);
      while (true)
      {
        this.doOutput = bool;
        this.method = this.request.method();
        return;
        bool = false;
      }
    }

    public void addRequestProperty(String paramString1, String paramString2)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void connect()
      throws IOException
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void disconnect()
    {
      throw ResponseCacheAdapter.access$000();
    }

    public boolean getAllowUserInteraction()
    {
      return false;
    }

    public int getConnectTimeout()
    {
      return 0;
    }

    public Object getContent()
      throws IOException
    {
      throw ResponseCacheAdapter.access$200();
    }

    public Object getContent(Class[] paramArrayOfClass)
      throws IOException
    {
      throw ResponseCacheAdapter.access$200();
    }

    public boolean getDefaultUseCaches()
    {
      return super.getDefaultUseCaches();
    }

    public boolean getDoInput()
    {
      return true;
    }

    public boolean getDoOutput()
    {
      return this.request.body() != null;
    }

    public InputStream getErrorStream()
    {
      return null;
    }

    public String getHeaderField(int paramInt)
    {
      if (paramInt < 0)
        throw new IllegalArgumentException("Invalid header index: " + paramInt);
      if (paramInt == 0)
        return this.response.statusLine();
      return this.response.headers().value(paramInt - 1);
    }

    public String getHeaderField(String paramString)
    {
      if (paramString == null)
        return this.response.statusLine();
      return this.response.headers().get(paramString);
    }

    public String getHeaderFieldKey(int paramInt)
    {
      if (paramInt < 0)
        throw new IllegalArgumentException("Invalid header index: " + paramInt);
      if (paramInt == 0)
        return null;
      return this.response.headers().name(paramInt - 1);
    }

    public Map<String, List<String>> getHeaderFields()
    {
      return OkHeaders.toMultimap(this.response.headers(), this.response.statusLine());
    }

    public long getIfModifiedSince()
    {
      return 0L;
    }

    public InputStream getInputStream()
      throws IOException
    {
      throw ResponseCacheAdapter.access$200();
    }

    public boolean getInstanceFollowRedirects()
    {
      return super.getInstanceFollowRedirects();
    }

    public OutputStream getOutputStream()
      throws IOException
    {
      throw ResponseCacheAdapter.access$000();
    }

    public int getReadTimeout()
    {
      return 0;
    }

    public String getRequestMethod()
    {
      return this.request.method();
    }

    public Map<String, List<String>> getRequestProperties()
    {
      throw ResponseCacheAdapter.access$100();
    }

    public String getRequestProperty(String paramString)
    {
      return this.request.header(paramString);
    }

    public int getResponseCode()
      throws IOException
    {
      return this.response.code();
    }

    public String getResponseMessage()
      throws IOException
    {
      return this.response.statusMessage();
    }

    public boolean getUseCaches()
    {
      return super.getUseCaches();
    }

    public void setAllowUserInteraction(boolean paramBoolean)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setChunkedStreamingMode(int paramInt)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setConnectTimeout(int paramInt)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setDefaultUseCaches(boolean paramBoolean)
    {
      super.setDefaultUseCaches(paramBoolean);
    }

    public void setDoInput(boolean paramBoolean)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setDoOutput(boolean paramBoolean)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setFixedLengthStreamingMode(int paramInt)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setFixedLengthStreamingMode(long paramLong)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setIfModifiedSince(long paramLong)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setInstanceFollowRedirects(boolean paramBoolean)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setReadTimeout(int paramInt)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setRequestMethod(String paramString)
      throws ProtocolException
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setRequestProperty(String paramString1, String paramString2)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setUseCaches(boolean paramBoolean)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public boolean usingProxy()
    {
      return false;
    }
  }

  private static final class CacheHttpsURLConnection extends DelegatingHttpsURLConnection
  {
    private final ResponseCacheAdapter.CacheHttpURLConnection delegate;

    public CacheHttpsURLConnection(ResponseCacheAdapter.CacheHttpURLConnection paramCacheHttpURLConnection)
    {
      super();
      this.delegate = paramCacheHttpURLConnection;
    }

    public long getContentLengthLong()
    {
      return this.delegate.getContentLengthLong();
    }

    public long getHeaderFieldLong(String paramString, long paramLong)
    {
      return this.delegate.getHeaderFieldLong(paramString, paramLong);
    }

    public HostnameVerifier getHostnameVerifier()
    {
      throw ResponseCacheAdapter.access$400();
    }

    public SSLSocketFactory getSSLSocketFactory()
    {
      throw ResponseCacheAdapter.access$400();
    }

    protected Handshake handshake()
    {
      return this.delegate.response.handshake();
    }

    public void setFixedLengthStreamingMode(long paramLong)
    {
      this.delegate.setFixedLengthStreamingMode(paramLong);
    }

    public void setHostnameVerifier(HostnameVerifier paramHostnameVerifier)
    {
      throw ResponseCacheAdapter.access$000();
    }

    public void setSSLSocketFactory(SSLSocketFactory paramSSLSocketFactory)
    {
      throw ResponseCacheAdapter.access$000();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.ResponseCacheAdapter
 * JD-Core Version:    0.6.0
 */