package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseSource;
import com.squareup.okhttp.internal.Util;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class CacheStrategy
{
  private static final Response.Body EMPTY_BODY = new Response.Body()
  {
    public InputStream byteStream()
    {
      return Util.EMPTY_INPUT_STREAM;
    }

    public long contentLength()
    {
      return 0L;
    }

    public MediaType contentType()
    {
      return null;
    }

    public boolean ready()
      throws IOException
    {
      return true;
    }
  };
  private static final StatusLine GATEWAY_TIMEOUT_STATUS_LINE;
  public final Request request;
  public final Response response;
  public final ResponseSource source;

  static
  {
    try
    {
      GATEWAY_TIMEOUT_STATUS_LINE = new StatusLine("HTTP/1.1 504 Gateway Timeout");
      return;
    }
    catch (IOException localIOException)
    {
    }
    throw new AssertionError();
  }

  private CacheStrategy(Request paramRequest, Response paramResponse, ResponseSource paramResponseSource)
  {
    this.request = paramRequest;
    this.response = paramResponse;
    this.source = paramResponseSource;
  }

  public static boolean isCacheable(Response paramResponse, Request paramRequest)
  {
    int i = paramResponse.code();
    if ((i != 200) && (i != 203) && (i != 300) && (i != 301) && (i != 410));
    CacheControl localCacheControl;
    do
    {
      return false;
      localCacheControl = paramResponse.cacheControl();
    }
    while (((paramRequest.header("Authorization") != null) && (!localCacheControl.isPublic()) && (!localCacheControl.mustRevalidate()) && (localCacheControl.sMaxAgeSeconds() == -1)) || (localCacheControl.noStore()));
    return true;
  }

  public static class Factory
  {
    private int ageSeconds = -1;
    final Response cacheResponse;
    private String etag;
    private Date expires;
    private Date lastModified;
    private String lastModifiedString;
    final long nowMillis;
    private long receivedResponseMillis;
    final Request request;
    private long sentRequestMillis;
    private Date servedDate;
    private String servedDateString;

    public Factory(long paramLong, Request paramRequest, Response paramResponse)
    {
      this.nowMillis = paramLong;
      this.request = paramRequest;
      this.cacheResponse = paramResponse;
      if (paramResponse != null)
      {
        int i = 0;
        if (i < paramResponse.headers().size())
        {
          String str1 = paramResponse.headers().name(i);
          String str2 = paramResponse.headers().value(i);
          if ("Date".equalsIgnoreCase(str1))
          {
            this.servedDate = HttpDate.parse(str2);
            this.servedDateString = str2;
          }
          while (true)
          {
            i++;
            break;
            if ("Expires".equalsIgnoreCase(str1))
            {
              this.expires = HttpDate.parse(str2);
              continue;
            }
            if ("Last-Modified".equalsIgnoreCase(str1))
            {
              this.lastModified = HttpDate.parse(str2);
              this.lastModifiedString = str2;
              continue;
            }
            if ("ETag".equalsIgnoreCase(str1))
            {
              this.etag = str2;
              continue;
            }
            if ("Age".equalsIgnoreCase(str1))
            {
              this.ageSeconds = HeaderParser.parseSeconds(str2);
              continue;
            }
            if (OkHeaders.SENT_MILLIS.equalsIgnoreCase(str1))
            {
              this.sentRequestMillis = Long.parseLong(str2);
              continue;
            }
            if (!OkHeaders.RECEIVED_MILLIS.equalsIgnoreCase(str1))
              continue;
            this.receivedResponseMillis = Long.parseLong(str2);
          }
        }
      }
    }

    private long cacheResponseAge()
    {
      long l1 = 0L;
      if (this.servedDate != null)
        l1 = Math.max(l1, this.receivedResponseMillis - this.servedDate.getTime());
      if (this.ageSeconds != -1)
        l1 = Math.max(l1, TimeUnit.SECONDS.toMillis(this.ageSeconds));
      long l2 = this.receivedResponseMillis - this.sentRequestMillis;
      return this.nowMillis - this.receivedResponseMillis + (l1 + l2);
    }

    private long computeFreshnessLifetime()
    {
      long l1 = 0L;
      CacheControl localCacheControl = this.cacheResponse.cacheControl();
      if (localCacheControl.maxAgeSeconds() != -1)
        l1 = TimeUnit.SECONDS.toMillis(localCacheControl.maxAgeSeconds());
      label86: 
      do
      {
        return l1;
        if (this.expires == null)
          continue;
        long l4;
        long l5;
        if (this.servedDate != null)
        {
          l4 = this.servedDate.getTime();
          l5 = this.expires.getTime() - l4;
          if (l5 <= l1)
            break label86;
        }
        while (true)
        {
          return l5;
          l4 = this.receivedResponseMillis;
          break;
          l5 = l1;
        }
      }
      while ((this.lastModified == null) || (this.cacheResponse.request().url().getQuery() != null));
      long l2;
      if (this.servedDate != null)
        l2 = this.servedDate.getTime();
      while (true)
      {
        long l3 = l2 - this.lastModified.getTime();
        if (l3 <= l1)
          break;
        return l3 / 10L;
        l2 = this.sentRequestMillis;
      }
    }

    private CacheStrategy getCandidate()
    {
      long l1 = 0L;
      if (this.cacheResponse == null)
        return new CacheStrategy(this.request, this.cacheResponse, ResponseSource.NETWORK, null);
      if ((this.request.isHttps()) && (this.cacheResponse.handshake() == null))
        return new CacheStrategy(this.request, this.cacheResponse, ResponseSource.NETWORK, null);
      if (!CacheStrategy.isCacheable(this.cacheResponse, this.request))
        return new CacheStrategy(this.request, this.cacheResponse, ResponseSource.NETWORK, null);
      CacheControl localCacheControl1 = this.request.cacheControl();
      if ((localCacheControl1.noCache()) || (hasConditions(this.request)))
        return new CacheStrategy(this.request, this.cacheResponse, ResponseSource.NETWORK, null);
      long l2 = cacheResponseAge();
      long l3 = computeFreshnessLifetime();
      if (localCacheControl1.maxAgeSeconds() != -1)
        l3 = Math.min(l3, TimeUnit.SECONDS.toMillis(localCacheControl1.maxAgeSeconds()));
      long l4;
      if (localCacheControl1.minFreshSeconds() != -1)
        l4 = TimeUnit.SECONDS.toMillis(localCacheControl1.minFreshSeconds());
      while (true)
      {
        CacheControl localCacheControl2 = this.cacheResponse.cacheControl();
        if ((!localCacheControl2.mustRevalidate()) && (localCacheControl1.maxStaleSeconds() != -1))
          l1 = TimeUnit.SECONDS.toMillis(localCacheControl1.maxStaleSeconds());
        if ((!localCacheControl2.noCache()) && (l2 + l4 < l1 + l3))
        {
          Response.Builder localBuilder1 = this.cacheResponse.newBuilder().setResponseSource(ResponseSource.CACHE);
          if (l4 + l2 >= l3)
            localBuilder1.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
          if ((l2 > 86400000L) && (isFreshnessLifetimeHeuristic()))
            localBuilder1.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
          return new CacheStrategy(this.request, localBuilder1.build(), ResponseSource.CACHE, null);
        }
        Request.Builder localBuilder = this.request.newBuilder();
        Request localRequest;
        if (this.lastModified != null)
        {
          localBuilder.header("If-Modified-Since", this.lastModifiedString);
          if (this.etag != null)
            localBuilder.header("If-None-Match", this.etag);
          localRequest = localBuilder.build();
          if (!hasConditions(localRequest))
            break label455;
        }
        label455: for (ResponseSource localResponseSource = ResponseSource.CONDITIONAL_CACHE; ; localResponseSource = ResponseSource.NETWORK)
        {
          return new CacheStrategy(localRequest, this.cacheResponse, localResponseSource, null);
          if (this.servedDate == null)
            break;
          localBuilder.header("If-Modified-Since", this.servedDateString);
          break;
        }
        l4 = l1;
      }
    }

    private static boolean hasConditions(Request paramRequest)
    {
      return (paramRequest.header("If-Modified-Since") != null) || (paramRequest.header("If-None-Match") != null);
    }

    private boolean isFreshnessLifetimeHeuristic()
    {
      return (this.cacheResponse.cacheControl().maxAgeSeconds() == -1) && (this.expires == null);
    }

    public CacheStrategy get()
    {
      CacheStrategy localCacheStrategy = getCandidate();
      if ((localCacheStrategy.source != ResponseSource.CACHE) && (this.request.cacheControl().onlyIfCached()))
      {
        Response localResponse = new Response.Builder().request(localCacheStrategy.request).statusLine(CacheStrategy.GATEWAY_TIMEOUT_STATUS_LINE).setResponseSource(ResponseSource.NONE).body(CacheStrategy.EMPTY_BODY).build();
        return new CacheStrategy(localCacheStrategy.request, localResponse, ResponseSource.NONE, null);
      }
      return localCacheStrategy;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.CacheStrategy
 * JD-Core Version:    0.6.0
 */