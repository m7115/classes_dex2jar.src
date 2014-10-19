package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.Deadline;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Sink;
import java.io.IOException;
import java.net.ProtocolException;

final class RetryableSink
  implements Sink
{
  private boolean closed;
  private final OkBuffer content = new OkBuffer();
  private final int limit;

  public RetryableSink()
  {
    this(-1);
  }

  public RetryableSink(int paramInt)
  {
    this.limit = paramInt;
  }

  public void close()
    throws IOException
  {
    if (this.closed);
    do
    {
      return;
      this.closed = true;
    }
    while (this.content.size() >= this.limit);
    throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
  }

  public long contentLength()
    throws IOException
  {
    return this.content.size();
  }

  public Sink deadline(Deadline paramDeadline)
  {
    return this;
  }

  public void flush()
    throws IOException
  {
  }

  public void write(OkBuffer paramOkBuffer, long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    Util.checkOffsetAndCount(paramOkBuffer.size(), 0L, paramLong);
    if ((this.limit != -1) && (this.content.size() > this.limit - paramLong))
      throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
    this.content.write(paramOkBuffer, paramLong);
  }

  public void writeToSocket(BufferedSink paramBufferedSink)
    throws IOException
  {
    paramBufferedSink.write(this.content.clone(), this.content.size());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.RetryableSink
 * JD-Core Version:    0.6.0
 */