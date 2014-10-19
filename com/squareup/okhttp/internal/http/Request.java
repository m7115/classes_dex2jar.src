package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public final class Request
{
  private final Body body;
  private volatile CacheControl cacheControl;
  private final Headers headers;
  private final String method;
  private volatile ParsedHeaders parsedHeaders;
  private final Object tag;
  private volatile URI uri;
  private final URL url;

  private Request(Builder paramBuilder)
  {
    this.url = paramBuilder.url;
    this.method = paramBuilder.method;
    this.headers = paramBuilder.headers.build();
    this.body = paramBuilder.body;
    if (paramBuilder.tag != null);
    for (Object localObject = paramBuilder.tag; ; localObject = this)
    {
      this.tag = localObject;
      return;
    }
  }

  private ParsedHeaders parsedHeaders()
  {
    ParsedHeaders localParsedHeaders1 = this.parsedHeaders;
    if (localParsedHeaders1 != null)
      return localParsedHeaders1;
    ParsedHeaders localParsedHeaders2 = new ParsedHeaders(this.headers);
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

  public Headers getHeaders()
  {
    return this.headers;
  }

  public String getProxyAuthorization()
  {
    return parsedHeaders().proxyAuthorization;
  }

  public String getUserAgent()
  {
    return parsedHeaders().userAgent;
  }

  public String header(String paramString)
  {
    return this.headers.get(paramString);
  }

  public Headers headers()
  {
    return this.headers;
  }

  public List<String> headers(String paramString)
  {
    return this.headers.values(paramString);
  }

  public boolean isHttps()
  {
    return url().getProtocol().equals("https");
  }

  public String method()
  {
    return this.method;
  }

  public Builder newBuilder()
  {
    return new Builder(this, null);
  }

  public Object tag()
  {
    return this.tag;
  }

  public URI uri()
    throws IOException
  {
    try
    {
      URI localURI1 = this.uri;
      if (localURI1 != null)
        return localURI1;
      URI localURI2 = Platform.get().toUriLenient(this.url);
      this.uri = localURI2;
      return localURI2;
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
    throw new IOException(localURISyntaxException.getMessage());
  }

  public URL url()
  {
    return this.url;
  }

  public String urlString()
  {
    return this.url.toString();
  }

  public static abstract class Body
  {
    public static Body create(MediaType paramMediaType, File paramFile)
    {
      if (paramMediaType == null)
        throw new NullPointerException("contentType == null");
      if (paramFile == null)
        throw new NullPointerException("content == null");
      return new Body(paramMediaType, paramFile)
      {
        public long contentLength()
        {
          return this.val$file.length();
        }

        public MediaType contentType()
        {
          return this.val$contentType;
        }

        public void writeTo(BufferedSink paramBufferedSink)
          throws IOException
        {
          long l = contentLength();
          if (l == 0L)
            return;
          try
          {
            localFileInputStream = new FileInputStream(this.val$file);
            try
            {
              byte[] arrayOfByte = new byte[(int)Math.min(8192L, l)];
              while (true)
              {
                int i = localFileInputStream.read(arrayOfByte);
                if (i == -1)
                  break;
                paramBufferedSink.write(arrayOfByte, 0, i);
              }
            }
            finally
            {
            }
            Util.closeQuietly(localFileInputStream);
            throw localObject1;
            Util.closeQuietly(localFileInputStream);
            return;
          }
          finally
          {
            while (true)
              FileInputStream localFileInputStream = null;
          }
        }
      };
    }

    public static Body create(MediaType paramMediaType, String paramString)
    {
      if (paramMediaType.charset() != null);
      try
      {
        while (true)
        {
          Body localBody = create(paramMediaType, paramString.getBytes(paramMediaType.charset().name()));
          return localBody;
          paramMediaType = MediaType.parse(paramMediaType + "; charset=utf-8");
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
      throw new AssertionError();
    }

    public static Body create(MediaType paramMediaType, byte[] paramArrayOfByte)
    {
      if (paramMediaType == null)
        throw new NullPointerException("contentType == null");
      if (paramArrayOfByte == null)
        throw new NullPointerException("content == null");
      return new Body(paramMediaType, paramArrayOfByte)
      {
        public long contentLength()
        {
          return this.val$content.length;
        }

        public MediaType contentType()
        {
          return this.val$contentType;
        }

        public void writeTo(BufferedSink paramBufferedSink)
          throws IOException
        {
          paramBufferedSink.write(this.val$content);
        }
      };
    }

    public long contentLength()
    {
      return -1L;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink paramBufferedSink)
      throws IOException;
  }

  public static class Builder
  {
    private Request.Body body;
    private Headers.Builder headers;
    private String method;
    private Object tag;
    private URL url;

    public Builder()
    {
      this.method = "GET";
      this.headers = new Headers.Builder();
    }

    private Builder(Request paramRequest)
    {
      this.url = paramRequest.url;
      this.method = paramRequest.method;
      this.body = paramRequest.body;
      this.tag = paramRequest.tag;
      this.headers = paramRequest.headers.newBuilder();
    }

    public Builder addHeader(String paramString1, String paramString2)
    {
      this.headers.add(paramString1, paramString2);
      return this;
    }

    public Request build()
    {
      if (this.url == null)
        throw new IllegalStateException("url == null");
      return new Request(this, null);
    }

    public Builder get()
    {
      return method("GET", null);
    }

    public Builder head()
    {
      return method("HEAD", null);
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

    public Builder method(String paramString, Request.Body paramBody)
    {
      if ((paramString == null) || (paramString.length() == 0))
        throw new IllegalArgumentException("method == null || method.length() == 0");
      this.method = paramString;
      this.body = paramBody;
      return this;
    }

    public Builder post(Request.Body paramBody)
    {
      return method("POST", paramBody);
    }

    public Builder put(Request.Body paramBody)
    {
      return method("PUT", paramBody);
    }

    public Builder removeHeader(String paramString)
    {
      this.headers.removeAll(paramString);
      return this;
    }

    public Builder setUserAgent(String paramString)
    {
      return header("User-Agent", paramString);
    }

    public Builder tag(Object paramObject)
    {
      this.tag = paramObject;
      return this;
    }

    public Builder url(String paramString)
    {
      try
      {
        Builder localBuilder = url(new URL(paramString));
        return localBuilder;
      }
      catch (MalformedURLException localMalformedURLException)
      {
      }
      throw new IllegalArgumentException("Malformed URL: " + paramString);
    }

    public Builder url(URL paramURL)
    {
      if (paramURL == null)
        throw new IllegalArgumentException("url == null");
      this.url = paramURL;
      return this;
    }
  }

  private static class ParsedHeaders
  {
    private String proxyAuthorization;
    private String userAgent;

    public ParsedHeaders(Headers paramHeaders)
    {
      int i = 0;
      if (i < paramHeaders.size())
      {
        String str1 = paramHeaders.name(i);
        String str2 = paramHeaders.value(i);
        if ("User-Agent".equalsIgnoreCase(str1))
          this.userAgent = str2;
        while (true)
        {
          i++;
          break;
          if (!"Proxy-Authorization".equalsIgnoreCase(str1))
            continue;
          this.proxyAuthorization = str2;
        }
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.Request
 * JD-Core Version:    0.6.0
 */