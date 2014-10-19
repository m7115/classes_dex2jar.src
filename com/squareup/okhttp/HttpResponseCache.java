package com.squareup.okhttp;

import com.squareup.okhttp.internal.DiskLruCache;
import com.squareup.okhttp.internal.DiskLruCache.Editor;
import com.squareup.okhttp.internal.DiskLruCache.Snapshot;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.Headers;
import com.squareup.okhttp.internal.http.Headers.Builder;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.internal.http.Request;
import com.squareup.okhttp.internal.http.Response;
import com.squareup.okhttp.internal.http.Response.Body;
import com.squareup.okhttp.internal.http.Response.Builder;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Okio;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.ResponseCache;
import java.net.URI;
import java.net.URLConnection;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class HttpResponseCache extends ResponseCache
  implements OkResponseCache
{
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final int VERSION = 201105;
  private final DiskLruCache cache;
  private int hitCount;
  private int networkCount;
  private int requestCount;
  private int writeAbortCount;
  private int writeSuccessCount;

  public HttpResponseCache(File paramFile, long paramLong)
    throws IOException
  {
    this.cache = DiskLruCache.open(paramFile, 201105, 2, paramLong);
  }

  private void abortQuietly(DiskLruCache.Editor paramEditor)
  {
    if (paramEditor != null);
    try
    {
      paramEditor.abort();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  private static int readInt(BufferedSource paramBufferedSource)
    throws IOException
  {
    String str = paramBufferedSource.readUtf8Line(true);
    try
    {
      int i = Integer.parseInt(str);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    throw new IOException("Expected an integer but was \"" + str + "\"");
  }

  private static String urlToKey(Request paramRequest)
  {
    return Util.hash(paramRequest.urlString());
  }

  public void close()
    throws IOException
  {
    this.cache.close();
  }

  public void delete()
    throws IOException
  {
    this.cache.delete();
  }

  public void flush()
    throws IOException
  {
    this.cache.flush();
  }

  public Response get(Request paramRequest)
  {
    String str = urlToKey(paramRequest);
    try
    {
      DiskLruCache.Snapshot localSnapshot = this.cache.get(str);
      if (localSnapshot == null)
        return null;
      Entry localEntry = new Entry(localSnapshot.getInputStream(0));
      Response localResponse = localEntry.response(paramRequest, localSnapshot);
      if (!localEntry.matches(paramRequest, localResponse))
      {
        Util.closeQuietly(localResponse.body());
        return null;
      }
      return localResponse;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public CacheResponse get(URI paramURI, String paramString, Map<String, List<String>> paramMap)
    throws IOException
  {
    throw new UnsupportedOperationException("This is not a general purpose response cache.");
  }

  public File getDirectory()
  {
    return this.cache.getDirectory();
  }

  public int getHitCount()
  {
    monitorenter;
    try
    {
      int i = this.hitCount;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public long getMaxSize()
  {
    return this.cache.getMaxSize();
  }

  public int getNetworkCount()
  {
    monitorenter;
    try
    {
      int i = this.networkCount;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getRequestCount()
  {
    monitorenter;
    try
    {
      int i = this.requestCount;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public long getSize()
  {
    return this.cache.size();
  }

  public int getWriteAbortCount()
  {
    monitorenter;
    try
    {
      int i = this.writeAbortCount;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getWriteSuccessCount()
  {
    monitorenter;
    try
    {
      int i = this.writeSuccessCount;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isClosed()
  {
    return this.cache.isClosed();
  }

  public boolean maybeRemove(Request paramRequest)
  {
    if (HttpMethod.invalidatesCache(paramRequest.method()));
    try
    {
      this.cache.remove(urlToKey(paramRequest));
      label22: return true;
      return false;
    }
    catch (IOException localIOException)
    {
      break label22;
    }
  }

  // ERROR //
  public CacheRequest put(Response paramResponse)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 200	com/squareup/okhttp/internal/http/Response:request	()Lcom/squareup/okhttp/internal/http/Request;
    //   4: invokevirtual 185	com/squareup/okhttp/internal/http/Request:method	()Ljava/lang/String;
    //   7: astore_2
    //   8: aload_0
    //   9: aload_1
    //   10: invokevirtual 200	com/squareup/okhttp/internal/http/Response:request	()Lcom/squareup/okhttp/internal/http/Request;
    //   13: invokevirtual 202	com/squareup/okhttp/HttpResponseCache:maybeRemove	(Lcom/squareup/okhttp/internal/http/Request;)Z
    //   16: ifeq +5 -> 21
    //   19: aconst_null
    //   20: areturn
    //   21: aload_2
    //   22: ldc 204
    //   24: invokevirtual 210	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   27: ifeq -8 -> 19
    //   30: aload_1
    //   31: invokevirtual 213	com/squareup/okhttp/internal/http/Response:hasVaryAll	()Z
    //   34: ifne -15 -> 19
    //   37: new 119	com/squareup/okhttp/HttpResponseCache$Entry
    //   40: dup
    //   41: aload_1
    //   42: invokespecial 216	com/squareup/okhttp/HttpResponseCache$Entry:<init>	(Lcom/squareup/okhttp/internal/http/Response;)V
    //   45: astore_3
    //   46: aload_0
    //   47: getfield 37	com/squareup/okhttp/HttpResponseCache:cache	Lcom/squareup/okhttp/internal/DiskLruCache;
    //   50: aload_1
    //   51: invokevirtual 200	com/squareup/okhttp/internal/http/Response:request	()Lcom/squareup/okhttp/internal/http/Request;
    //   54: invokestatic 114	com/squareup/okhttp/HttpResponseCache:urlToKey	(Lcom/squareup/okhttp/internal/http/Request;)Ljava/lang/String;
    //   57: invokevirtual 220	com/squareup/okhttp/internal/DiskLruCache:edit	(Ljava/lang/String;)Lcom/squareup/okhttp/internal/DiskLruCache$Editor;
    //   60: astore 6
    //   62: aload 6
    //   64: ifnull -45 -> 19
    //   67: aload_3
    //   68: aload 6
    //   70: invokevirtual 223	com/squareup/okhttp/HttpResponseCache$Entry:writeTo	(Lcom/squareup/okhttp/internal/DiskLruCache$Editor;)V
    //   73: new 225	com/squareup/okhttp/HttpResponseCache$CacheRequestImpl
    //   76: dup
    //   77: aload_0
    //   78: aload 6
    //   80: invokespecial 228	com/squareup/okhttp/HttpResponseCache$CacheRequestImpl:<init>	(Lcom/squareup/okhttp/HttpResponseCache;Lcom/squareup/okhttp/internal/DiskLruCache$Editor;)V
    //   83: astore 8
    //   85: aload 8
    //   87: areturn
    //   88: astore 4
    //   90: aconst_null
    //   91: astore 5
    //   93: aload_0
    //   94: aload 5
    //   96: invokespecial 230	com/squareup/okhttp/HttpResponseCache:abortQuietly	(Lcom/squareup/okhttp/internal/DiskLruCache$Editor;)V
    //   99: aconst_null
    //   100: areturn
    //   101: astore 7
    //   103: aload 6
    //   105: astore 5
    //   107: goto -14 -> 93
    //
    // Exception table:
    //   from	to	target	type
    //   46	62	88	java/io/IOException
    //   67	85	101	java/io/IOException
  }

  public CacheRequest put(URI paramURI, URLConnection paramURLConnection)
    throws IOException
  {
    throw new UnsupportedOperationException("This is not a general purpose response cache.");
  }

  public void trackConditionalCacheHit()
  {
    monitorenter;
    try
    {
      this.hitCount = (1 + this.hitCount);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void trackResponse(ResponseSource paramResponseSource)
  {
    monitorenter;
    while (true)
    {
      try
      {
        this.requestCount = (1 + this.requestCount);
        int i = 1.$SwitchMap$com$squareup$okhttp$ResponseSource[paramResponseSource.ordinal()];
        switch (i)
        {
        default:
          return;
        case 1:
          this.hitCount = (1 + this.hitCount);
          continue;
        case 2:
        case 3:
        }
      }
      finally
      {
        monitorexit;
      }
      this.networkCount = (1 + this.networkCount);
    }
  }

  public void update(Response paramResponse1, Response paramResponse2)
  {
    Entry localEntry = new Entry(paramResponse2);
    DiskLruCache.Snapshot localSnapshot = ((CacheResponseBody)paramResponse1.body()).snapshot;
    DiskLruCache.Editor localEditor = null;
    try
    {
      localEditor = localSnapshot.edit();
      if (localEditor != null)
      {
        localEntry.writeTo(localEditor);
        localEditor.commit();
      }
      return;
    }
    catch (IOException localIOException)
    {
      abortQuietly(localEditor);
    }
  }

  private final class CacheRequestImpl extends CacheRequest
  {
    private OutputStream body;
    private OutputStream cacheOut;
    private boolean done;
    private final DiskLruCache.Editor editor;

    public CacheRequestImpl(DiskLruCache.Editor arg2)
      throws IOException
    {
      DiskLruCache.Editor localEditor;
      this.editor = localEditor;
      this.cacheOut = localEditor.newOutputStream(1);
      this.body = new FilterOutputStream(this.cacheOut, HttpResponseCache.this, localEditor)
      {
        public void close()
          throws IOException
        {
          synchronized (HttpResponseCache.this)
          {
            if (HttpResponseCache.CacheRequestImpl.this.done)
              return;
            HttpResponseCache.CacheRequestImpl.access$102(HttpResponseCache.CacheRequestImpl.this, true);
            HttpResponseCache.access$208(HttpResponseCache.this);
            super.close();
            this.val$editor.commit();
            return;
          }
        }

        public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
          throws IOException
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
        }
      };
    }

    public void abort()
    {
      synchronized (HttpResponseCache.this)
      {
        if (this.done)
          return;
        this.done = true;
        HttpResponseCache.access$308(HttpResponseCache.this);
        Util.closeQuietly(this.cacheOut);
        try
        {
          this.editor.abort();
          return;
        }
        catch (IOException localIOException)
        {
          return;
        }
      }
    }

    public OutputStream getBody()
      throws IOException
    {
      return this.body;
    }
  }

  private static class CacheResponseBody extends Response.Body
  {
    private final InputStream bodyIn;
    private final String contentLength;
    private final String contentType;
    private final DiskLruCache.Snapshot snapshot;

    public CacheResponseBody(DiskLruCache.Snapshot paramSnapshot, String paramString1, String paramString2)
    {
      this.snapshot = paramSnapshot;
      this.contentType = paramString1;
      this.contentLength = paramString2;
      this.bodyIn = new FilterInputStream(paramSnapshot.getInputStream(1), paramSnapshot)
      {
        public void close()
          throws IOException
        {
          this.val$snapshot.close();
          super.close();
        }
      };
    }

    public InputStream byteStream()
    {
      return this.bodyIn;
    }

    public long contentLength()
    {
      long l1 = -1L;
      try
      {
        if (this.contentLength != null)
        {
          long l2 = Long.parseLong(this.contentLength);
          l1 = l2;
        }
        return l1;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
      return l1;
    }

    public MediaType contentType()
    {
      if (this.contentType != null)
        return MediaType.parse(this.contentType);
      return null;
    }

    public boolean ready()
      throws IOException
    {
      return true;
    }
  }

  private static final class Entry
  {
    private final Handshake handshake;
    private final String requestMethod;
    private final Headers responseHeaders;
    private final String statusLine;
    private final String url;
    private final Headers varyHeaders;

    public Entry(Response paramResponse)
    {
      this.url = paramResponse.request().urlString();
      this.varyHeaders = paramResponse.request().headers().getAll(paramResponse.getVaryFields());
      this.requestMethod = paramResponse.request().method();
      this.statusLine = paramResponse.statusLine();
      this.responseHeaders = paramResponse.headers();
      this.handshake = paramResponse.handshake();
    }

    public Entry(InputStream paramInputStream)
      throws IOException
    {
      BufferedSource localBufferedSource;
      try
      {
        localBufferedSource = Okio.buffer(Okio.source(paramInputStream));
        this.url = localBufferedSource.readUtf8Line(true);
        this.requestMethod = localBufferedSource.readUtf8Line(true);
        Headers.Builder localBuilder1 = new Headers.Builder();
        int j = HttpResponseCache.access$400(localBufferedSource);
        for (int k = 0; k < j; k++)
          localBuilder1.addLine(localBufferedSource.readUtf8Line(true));
        this.varyHeaders = localBuilder1.build();
        this.statusLine = localBufferedSource.readUtf8Line(true);
        Headers.Builder localBuilder2 = new Headers.Builder();
        int m = HttpResponseCache.access$400(localBufferedSource);
        while (i < m)
        {
          localBuilder2.addLine(localBufferedSource.readUtf8Line(true));
          i++;
        }
        this.responseHeaders = localBuilder2.build();
        if (isHttps())
        {
          String str = localBufferedSource.readUtf8Line(true);
          if (str.length() > 0)
            throw new IOException("expected \"\" but was \"" + str + "\"");
        }
      }
      finally
      {
        paramInputStream.close();
      }
      for (this.handshake = Handshake.get(localBufferedSource.readUtf8Line(true), readCertificateList(localBufferedSource), readCertificateList(localBufferedSource)); ; this.handshake = null)
      {
        paramInputStream.close();
        return;
      }
    }

    private boolean isHttps()
    {
      return this.url.startsWith("https://");
    }

    private List<Certificate> readCertificateList(BufferedSource paramBufferedSource)
      throws IOException
    {
      int i = HttpResponseCache.access$400(paramBufferedSource);
      Object localObject;
      if (i == -1)
        localObject = Collections.emptyList();
      while (true)
      {
        return localObject;
        try
        {
          CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
          localObject = new ArrayList(i);
          for (int j = 0; j < i; j++)
            ((List)localObject).add(localCertificateFactory.generateCertificate(new ByteArrayInputStream(ByteString.decodeBase64(paramBufferedSource.readUtf8Line(true)).toByteArray())));
        }
        catch (CertificateException localCertificateException)
        {
        }
      }
      throw new IOException(localCertificateException.getMessage());
    }

    private void writeCertArray(Writer paramWriter, List<Certificate> paramList)
      throws IOException
    {
      try
      {
        paramWriter.write(Integer.toString(paramList.size()) + '\n');
        int i = paramList.size();
        for (int j = 0; j < i; j++)
        {
          String str = ByteString.of(((Certificate)paramList.get(j)).getEncoded()).base64();
          paramWriter.write(str + '\n');
        }
      }
      catch (CertificateEncodingException localCertificateEncodingException)
      {
        throw new IOException(localCertificateEncodingException.getMessage());
      }
    }

    public boolean matches(Request paramRequest, Response paramResponse)
    {
      return (this.url.equals(paramRequest.urlString())) && (this.requestMethod.equals(paramRequest.method())) && (paramResponse.varyMatches(this.varyHeaders, paramRequest));
    }

    public Response response(Request paramRequest, DiskLruCache.Snapshot paramSnapshot)
    {
      String str1 = this.responseHeaders.get("Content-Type");
      String str2 = this.responseHeaders.get("Content-Length");
      return new Response.Builder().request(paramRequest).statusLine(this.statusLine).headers(this.responseHeaders).body(new HttpResponseCache.CacheResponseBody(paramSnapshot, str1, str2)).handshake(this.handshake).build();
    }

    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      int i = 0;
      BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(paramEditor.newOutputStream(0), Util.UTF_8));
      localBufferedWriter.write(this.url + '\n');
      localBufferedWriter.write(this.requestMethod + '\n');
      localBufferedWriter.write(Integer.toString(this.varyHeaders.size()) + '\n');
      for (int j = 0; j < this.varyHeaders.size(); j++)
        localBufferedWriter.write(this.varyHeaders.name(j) + ": " + this.varyHeaders.value(j) + '\n');
      localBufferedWriter.write(this.statusLine + '\n');
      localBufferedWriter.write(Integer.toString(this.responseHeaders.size()) + '\n');
      while (i < this.responseHeaders.size())
      {
        localBufferedWriter.write(this.responseHeaders.name(i) + ": " + this.responseHeaders.value(i) + '\n');
        i++;
      }
      if (isHttps())
      {
        localBufferedWriter.write(10);
        localBufferedWriter.write(this.handshake.cipherSuite() + '\n');
        writeCertArray(localBufferedWriter, this.handshake.peerCertificates());
        writeCertArray(localBufferedWriter, this.handshake.localCertificates());
      }
      localBufferedWriter.close();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.HttpResponseCache
 * JD-Core Version:    0.6.0
 */