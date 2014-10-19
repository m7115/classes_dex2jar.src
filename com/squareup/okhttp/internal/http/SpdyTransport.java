package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Deadline;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import com.squareup.okhttp.internal.spdy.ErrorCode;
import com.squareup.okhttp.internal.spdy.Header;
import com.squareup.okhttp.internal.spdy.SpdyConnection;
import com.squareup.okhttp.internal.spdy.SpdyStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class SpdyTransport
  implements Transport
{
  private final HttpEngine httpEngine;
  private final SpdyConnection spdyConnection;
  private SpdyStream stream;

  public SpdyTransport(HttpEngine paramHttpEngine, SpdyConnection paramSpdyConnection)
  {
    this.httpEngine = paramHttpEngine;
    this.spdyConnection = paramSpdyConnection;
  }

  private static boolean isProhibitedHeader(Protocol paramProtocol, ByteString paramByteString)
  {
    int i;
    if (paramProtocol == Protocol.SPDY_3)
      if ((!paramByteString.equalsAscii("connection")) && (!paramByteString.equalsAscii("host")) && (!paramByteString.equalsAscii("keep-alive")) && (!paramByteString.equalsAscii("proxy-connection")))
      {
        boolean bool2 = paramByteString.equalsAscii("transfer-encoding");
        i = 0;
        if (!bool2);
      }
      else
      {
        i = 1;
      }
    while (true)
    {
      return i;
      if (paramProtocol != Protocol.HTTP_2)
        break;
      if ((!paramByteString.equalsAscii("connection")) && (!paramByteString.equalsAscii("host")) && (!paramByteString.equalsAscii("keep-alive")) && (!paramByteString.equalsAscii("proxy-connection")) && (!paramByteString.equalsAscii("te")) && (!paramByteString.equalsAscii("transfer-encoding")) && (!paramByteString.equalsAscii("encoding")))
      {
        boolean bool1 = paramByteString.equalsAscii("upgrade");
        i = 0;
        if (!bool1)
          continue;
      }
      return true;
    }
    throw new AssertionError(paramProtocol);
  }

  private static String joinOnNull(String paramString1, String paramString2)
  {
    return paramString1 + '\000' + paramString2;
  }

  public static Response.Builder readNameValueBlock(List<Header> paramList, Protocol paramProtocol)
    throws IOException
  {
    Object localObject1 = null;
    Object localObject2 = "HTTP/1.1";
    Headers.Builder localBuilder = new Headers.Builder();
    localBuilder.set(OkHeaders.SELECTED_PROTOCOL, paramProtocol.name.utf8());
    int i = 0;
    while (i < paramList.size())
    {
      ByteString localByteString = ((Header)paramList.get(i)).name;
      String str = ((Header)paramList.get(i)).value.utf8();
      Object localObject3 = localObject2;
      int j = 0;
      if (j < str.length())
      {
        int k = str.indexOf(0, j);
        if (k == -1)
          k = str.length();
        Object localObject4 = str.substring(j, k);
        if (localByteString.equals(Header.RESPONSE_STATUS));
        while (true)
        {
          int m = k + 1;
          localObject1 = localObject4;
          j = m;
          break;
          if (localByteString.equals(Header.VERSION))
          {
            localObject3 = localObject4;
            localObject4 = localObject1;
            continue;
          }
          if (!isProhibitedHeader(paramProtocol, localByteString))
            localBuilder.add(localByteString.utf8(), (String)localObject4);
          localObject4 = localObject1;
        }
      }
      i++;
      localObject2 = localObject3;
    }
    if (localObject1 == null)
      throw new ProtocolException("Expected ':status' header not present");
    if (localObject2 == null)
      throw new ProtocolException("Expected ':version' header not present");
    return (Response.Builder)(Response.Builder)new Response.Builder().statusLine(new StatusLine((String)localObject2 + " " + localObject1)).headers(localBuilder.build());
  }

  public static List<Header> writeNameValueBlock(Request paramRequest, Protocol paramProtocol, String paramString)
  {
    Headers localHeaders = paramRequest.headers();
    ArrayList localArrayList = new ArrayList(10 + localHeaders.size());
    localArrayList.add(new Header(Header.TARGET_METHOD, paramRequest.method()));
    localArrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(paramRequest.url())));
    String str1 = HttpEngine.hostHeader(paramRequest.url());
    LinkedHashSet localLinkedHashSet;
    int i;
    label160: ByteString localByteString;
    String str2;
    if (Protocol.SPDY_3 == paramProtocol)
    {
      localArrayList.add(new Header(Header.VERSION, paramString));
      localArrayList.add(new Header(Header.TARGET_HOST, str1));
      localArrayList.add(new Header(Header.TARGET_SCHEME, paramRequest.url().getProtocol()));
      localLinkedHashSet = new LinkedHashSet();
      i = 0;
      if (i >= localHeaders.size())
        break label436;
      localByteString = ByteString.encodeUtf8(localHeaders.name(i).toLowerCase(Locale.US));
      str2 = localHeaders.value(i);
      if (!isProhibitedHeader(paramProtocol, localByteString))
        break label247;
    }
    label434: 
    while (true)
    {
      i++;
      break label160;
      if (Protocol.HTTP_2 == paramProtocol)
      {
        localArrayList.add(new Header(Header.TARGET_AUTHORITY, str1));
        break;
      }
      throw new AssertionError();
      label247: if ((localByteString.equals(Header.TARGET_METHOD)) || (localByteString.equals(Header.TARGET_PATH)) || (localByteString.equals(Header.TARGET_SCHEME)) || (localByteString.equals(Header.TARGET_AUTHORITY)) || (localByteString.equals(Header.TARGET_HOST)) || (localByteString.equals(Header.VERSION)))
        continue;
      if (localLinkedHashSet.add(localByteString))
      {
        localArrayList.add(new Header(localByteString, str2));
        continue;
      }
      for (int j = 0; ; j++)
      {
        if (j >= localArrayList.size())
          break label434;
        if (!((Header)localArrayList.get(j)).name.equals(localByteString))
          continue;
        localArrayList.set(j, new Header(localByteString, joinOnNull(((Header)localArrayList.get(j)).value.utf8(), str2)));
        break;
      }
    }
    label436: return localArrayList;
  }

  public boolean canReuseConnection()
  {
    return true;
  }

  public Sink createRequestBody(Request paramRequest)
    throws IOException
  {
    writeRequestHeaders(paramRequest);
    return this.stream.getSink();
  }

  public void disconnect(HttpEngine paramHttpEngine)
    throws IOException
  {
    this.stream.close(ErrorCode.CANCEL);
  }

  public void emptyTransferStream()
  {
  }

  public void flushRequest()
    throws IOException
  {
    this.stream.getSink().close();
  }

  public Source getTransferStream(CacheRequest paramCacheRequest)
    throws IOException
  {
    return new SpdySource(this.stream, paramCacheRequest);
  }

  public Response.Builder readResponseHeaders()
    throws IOException
  {
    return readNameValueBlock(this.stream.getResponseHeaders(), this.spdyConnection.getProtocol());
  }

  public void releaseConnectionOnIdle()
  {
  }

  public void writeRequestBody(RetryableSink paramRetryableSink)
    throws IOException
  {
    throw new UnsupportedOperationException();
  }

  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    if (this.stream != null)
      return;
    this.httpEngine.writingRequestHeaders();
    boolean bool = this.httpEngine.hasRequestBody();
    String str = RequestLine.version(this.httpEngine.getConnection().getHttpMinorVersion());
    this.stream = this.spdyConnection.newStream(writeNameValueBlock(paramRequest, this.spdyConnection.getProtocol(), str), bool, true);
    this.stream.setReadTimeout(this.httpEngine.client.getReadTimeout());
  }

  private static class SpdySource
    implements Source
  {
    private final OutputStream cacheBody;
    private final CacheRequest cacheRequest;
    private boolean closed;
    private boolean inputExhausted;
    private final Source source;
    private final SpdyStream stream;

    SpdySource(SpdyStream paramSpdyStream, CacheRequest paramCacheRequest)
      throws IOException
    {
      this.stream = paramSpdyStream;
      this.source = paramSpdyStream.getSource();
      if (paramCacheRequest != null);
      for (OutputStream localOutputStream = paramCacheRequest.getBody(); ; localOutputStream = null)
      {
        if (localOutputStream == null)
          paramCacheRequest = null;
        this.cacheBody = localOutputStream;
        this.cacheRequest = paramCacheRequest;
        return;
      }
    }

    private boolean discardStream()
    {
      try
      {
        long l = this.stream.getReadTimeoutMillis();
        this.stream.setReadTimeout(l);
        this.stream.setReadTimeout(100L);
        try
        {
          Util.skipAll(this, 100);
          return true;
        }
        finally
        {
          this.stream.setReadTimeout(l);
        }
      }
      catch (IOException localIOException)
      {
      }
      return false;
    }

    public void close()
      throws IOException
    {
      if (this.closed);
      do
      {
        do
        {
          return;
          if ((!this.inputExhausted) && (this.cacheBody != null))
            discardStream();
          this.closed = true;
        }
        while (this.inputExhausted);
        this.stream.closeLater(ErrorCode.CANCEL);
      }
      while (this.cacheRequest == null);
      this.cacheRequest.abort();
    }

    public Source deadline(Deadline paramDeadline)
    {
      this.source.deadline(paramDeadline);
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      if (this.closed)
        throw new IllegalStateException("closed");
      long l;
      if (this.inputExhausted)
        l = -1L;
      do
      {
        return l;
        l = this.source.read(paramOkBuffer, paramLong);
        if (l != -1L)
          continue;
        this.inputExhausted = true;
        if (this.cacheRequest != null)
          this.cacheBody.close();
        return -1L;
      }
      while (this.cacheBody == null);
      Okio.copy(paramOkBuffer, paramOkBuffer.size() - l, l, this.cacheBody);
      return l;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.SpdyTransport
 * JD-Core Version:    0.6.0
 */