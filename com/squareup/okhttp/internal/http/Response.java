package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Handshake;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseSource;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.okio.Source;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class Response
{
  private final Body body;
  private volatile CacheControl cacheControl;
  private final Handshake handshake;
  private final Headers headers;
  private volatile ParsedHeaders parsedHeaders;
  private final Response redirectedBy;
  private final Request request;
  private final StatusLine statusLine;

  private Response(Builder paramBuilder)
  {
    this.request = paramBuilder.request;
    this.statusLine = paramBuilder.statusLine;
    this.handshake = paramBuilder.handshake;
    this.headers = paramBuilder.headers.build();
    this.body = paramBuilder.body;
    this.redirectedBy = paramBuilder.redirectedBy;
  }

  private ParsedHeaders parsedHeaders()
  {
    ParsedHeaders localParsedHeaders1 = this.parsedHeaders;
    if (localParsedHeaders1 != null)
      return localParsedHeaders1;
    ParsedHeaders localParsedHeaders2 = new ParsedHeaders(this.headers, null);
    this.parsedHeaders = localParsedHeaders2;
    return localParsedHeaders2;
  }

  public Body body()
  {
    return this.body;
  }

  public CacheControl cacheControl()
  {
    CacheControl localCacheControl1 = this.cacheControl;
    if (localCacheControl1 != null)
      return localCacheControl1;
    CacheControl localCacheControl2 = CacheControl.parse(this.headers);
    this.cacheControl = localCacheControl2;
    return localCacheControl2;
  }

  public int code()
  {
    return this.statusLine.code();
  }

  public Set<String> getVaryFields()
  {
    return parsedHeaders().varyFields;
  }

  public Handshake handshake()
  {
    return this.handshake;
  }

  public boolean hasVaryAll()
  {
    return parsedHeaders().varyFields.contains("*");
  }

  public String header(String paramString)
  {
    return header(paramString, null);
  }

  public String header(String paramString1, String paramString2)
  {
    String str = this.headers.get(paramString1);
    if (str != null)
      paramString2 = str;
    return paramString2;
  }

  public Headers headers()
  {
    return this.headers;
  }

  public List<String> headers(String paramString)
  {
    return this.headers.values(paramString);
  }

  public int httpMinorVersion()
  {
    return this.statusLine.httpMinorVersion();
  }

  public Builder newBuilder()
  {
    return new Builder(this, null);
  }

  public Response redirectedBy()
  {
    return this.redirectedBy;
  }

  public Request request()
  {
    return this.request;
  }

  public String statusLine()
  {
    return this.statusLine.getStatusLine();
  }

  public String statusMessage()
  {
    return this.statusLine.message();
  }

  public boolean validate(Response paramResponse)
  {
    if (paramResponse.code() == 304);
    ParsedHeaders localParsedHeaders;
    do
    {
      return true;
      localParsedHeaders = paramResponse.parsedHeaders();
    }
    while ((parsedHeaders().lastModified != null) && (localParsedHeaders.lastModified != null) && (localParsedHeaders.lastModified.getTime() < parsedHeaders().lastModified.getTime()));
    return false;
  }

  public boolean varyMatches(Headers paramHeaders, Request paramRequest)
  {
    Iterator localIterator = parsedHeaders().varyFields.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!Util.equal(paramHeaders.values(str), paramRequest.headers(str)))
        return false;
    }
    return true;
  }

  public static abstract class Body
    implements Closeable
  {
    private Reader reader;
    private Source source;

    private Charset charset()
    {
      MediaType localMediaType = contentType();
      if (localMediaType != null)
        return localMediaType.charset(Util.UTF_8);
      return Util.UTF_8;
    }

    public abstract InputStream byteStream();

    public final byte[] bytes()
      throws IOException
    {
      long l = contentLength();
      if (l > 2147483647L)
        throw new IOException("Cannot buffer entire body for content length: " + l);
      byte[] arrayOfByte;
      if (l != -1L)
      {
        arrayOfByte = new byte[(int)l];
        InputStream localInputStream = byteStream();
        Util.readFully(localInputStream, arrayOfByte);
        if (localInputStream.read() != -1)
          throw new IOException("Content-Length and stream length disagree");
      }
      else
      {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        Util.copy(byteStream(), localByteArrayOutputStream);
        arrayOfByte = localByteArrayOutputStream.toByteArray();
      }
      return arrayOfByte;
    }

    public final Reader charStream()
    {
      Reader localReader = this.reader;
      if (localReader != null)
        return localReader;
      InputStreamReader localInputStreamReader = new InputStreamReader(byteStream(), charset());
      this.reader = localInputStreamReader;
      return localInputStreamReader;
    }

    public void close()
      throws IOException
    {
      byteStream().close();
    }

    public abstract long contentLength();

    public abstract MediaType contentType();

    public abstract boolean ready()
      throws IOException;

    public Source source()
    {
      Source localSource1 = this.source;
      if (localSource1 != null)
        return localSource1;
      Source localSource2 = Okio.source(byteStream());
      this.source = localSource2;
      return localSource2;
    }

    public final String string()
      throws IOException
    {
      return new String(bytes(), charset().name());
    }
  }

  public static class Builder
  {
    private Response.Body body;
    private Handshake handshake;
    private Headers.Builder headers;
    private Response redirectedBy;
    private Request request;
    private StatusLine statusLine;

    public Builder()
    {
      this.headers = new Headers.Builder();
    }

    private Builder(Response paramResponse)
    {
      this.request = paramResponse.request;
      this.statusLine = paramResponse.statusLine;
      this.handshake = paramResponse.handshake;
      this.headers = paramResponse.headers.newBuilder();
      this.body = paramResponse.body;
      this.redirectedBy = paramResponse.redirectedBy;
    }

    public Builder addHeader(String paramString1, String paramString2)
    {
      this.headers.add(paramString1, paramString2);
      return this;
    }

    public Builder body(Response.Body paramBody)
    {
      this.body = paramBody;
      return this;
    }

    public Response build()
    {
      if (this.request == null)
        throw new IllegalStateException("request == null");
      if (this.statusLine == null)
        throw new IllegalStateException("statusLine == null");
      return new Response(this, null);
    }

    public Builder handshake(Handshake paramHandshake)
    {
      this.handshake = paramHandshake;
      return this;
    }

    public Builder header(String paramString1, String paramString2)
    {
      this.headers.set(paramString1, paramString2);
      return this;
    }

    public Builder headers(Headers paramHeaders)
    {
      this.headers = paramHeaders.newBuilder();
      return this;
    }

    public Builder redirectedBy(Response paramResponse)
    {
      this.redirectedBy = paramResponse;
      return this;
    }

    public Builder removeHeader(String paramString)
    {
      this.headers.removeAll(paramString);
      return this;
    }

    public Builder request(Request paramRequest)
    {
      this.request = paramRequest;
      return this;
    }

    public Builder setResponseSource(ResponseSource paramResponseSource)
    {
      return header(OkHeaders.RESPONSE_SOURCE, paramResponseSource + " " + this.statusLine.code());
    }

    public Builder statusLine(StatusLine paramStatusLine)
    {
      if (paramStatusLine == null)
        throw new IllegalArgumentException("statusLine == null");
      this.statusLine = paramStatusLine;
      return this;
    }

    public Builder statusLine(String paramString)
    {
      try
      {
        Builder localBuilder = statusLine(new StatusLine(paramString));
        return localBuilder;
      }
      catch (IOException localIOException)
      {
      }
      throw new IllegalArgumentException(localIOException);
    }
  }

  private static class ParsedHeaders
  {
    Date lastModified;
    private Set<String> varyFields = Collections.emptySet();

    private ParsedHeaders(Headers paramHeaders)
    {
      int i = 0;
      if (i < paramHeaders.size())
      {
        String str1 = paramHeaders.name(i);
        String str2 = paramHeaders.value(i);
        if ("Last-Modified".equalsIgnoreCase(str1))
          this.lastModified = HttpDate.parse(str2);
        while (true)
        {
          i++;
          break;
          if (!"Vary".equalsIgnoreCase(str1))
            continue;
          if (this.varyFields.isEmpty())
            this.varyFields = new TreeSet(String.CASE_INSENSITIVE_ORDER);
          for (String str3 : str2.split(","))
            this.varyFields.add(str3.trim());
        }
      }
    }
  }

  public static abstract interface Receiver
  {
    public abstract void onFailure(Failure paramFailure);

    public abstract boolean onResponse(Response paramResponse)
      throws IOException;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.Response
 * JD-Core Version:    0.6.0
 */