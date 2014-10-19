package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.net.CacheRequest;

abstract interface Transport
{
  public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

  public abstract boolean canReuseConnection();

  public abstract Sink createRequestBody(Request paramRequest)
    throws IOException;

  public abstract void disconnect(HttpEngine paramHttpEngine)
    throws IOException;

  public abstract void emptyTransferStream()
    throws IOException;

  public abstract void flushRequest()
    throws IOException;

  public abstract Source getTransferStream(CacheRequest paramCacheRequest)
    throws IOException;

  public abstract Response.Builder readResponseHeaders()
    throws IOException;

  public abstract void releaseConnectionOnIdle()
    throws IOException;

  public abstract void writeRequestBody(RetryableSink paramRetryableSink)
    throws IOException;

  public abstract void writeRequestHeaders(Request paramRequest)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.Transport
 * JD-Core Version:    0.6.0
 */