package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.Request;
import com.squareup.okhttp.internal.http.Response;
import java.io.IOException;
import java.net.CacheRequest;

public abstract interface OkResponseCache
{
  public abstract Response get(Request paramRequest)
    throws IOException;

  public abstract boolean maybeRemove(Request paramRequest)
    throws IOException;

  public abstract CacheRequest put(Response paramResponse)
    throws IOException;

  public abstract void trackConditionalCacheHit();

  public abstract void trackResponse(ResponseSource paramResponseSource);

  public abstract void update(Response paramResponse1, Response paramResponse2)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.OkResponseCache
 * JD-Core Version:    0.6.0
 */