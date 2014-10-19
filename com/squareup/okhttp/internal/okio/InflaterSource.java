package com.squareup.okhttp.internal.okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class InflaterSource
  implements Source
{
  private int bufferBytesHeldByInflater;
  private boolean closed;
  private final Inflater inflater;
  private final BufferedSource source;

  InflaterSource(BufferedSource paramBufferedSource, Inflater paramInflater)
  {
    if (paramBufferedSource == null)
      throw new IllegalArgumentException("source == null");
    if (paramInflater == null)
      throw new IllegalArgumentException("inflater == null");
    this.source = paramBufferedSource;
    this.inflater = paramInflater;
  }

  public InflaterSource(Source paramSource, Inflater paramInflater)
  {
    this(Okio.buffer(paramSource), paramInflater);
  }

  private void releaseInflatedBytes()
    throws IOException
  {
    if (this.bufferBytesHeldByInflater == 0)
      return;
    int i = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
    this.bufferBytesHeldByInflater -= i;
    this.source.skip(i);
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
    this.inflater.end();
    this.closed = true;
    this.source.close();
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
    if (paramLong == 0L)
      return 0L;
    IOException localIOException;
    while (true)
    {
      boolean bool = refill();
      try
      {
        Segment localSegment = paramOkBuffer.writableSegment(1);
        int i = this.inflater.inflate(localSegment.data, localSegment.limit, 2048 - localSegment.limit);
        if (i > 0)
        {
          localSegment.limit = (i + localSegment.limit);
          paramOkBuffer.size += i;
          return i;
        }
        if ((this.inflater.finished()) || (this.inflater.needsDictionary()))
        {
          releaseInflatedBytes();
          return -1L;
        }
        if (!bool)
          continue;
        throw new EOFException("source exhausted prematurely");
      }
      catch (DataFormatException localDataFormatException)
      {
        localIOException = new IOException();
        localIOException.initCause(localDataFormatException);
      }
    }
    throw localIOException;
  }

  public boolean refill()
    throws IOException
  {
    if (!this.inflater.needsInput())
      return false;
    releaseInflatedBytes();
    if (this.inflater.getRemaining() != 0)
      throw new IllegalStateException("?");
    if (this.source.exhausted())
      return true;
    Segment localSegment = this.source.buffer().head;
    this.bufferBytesHeldByInflater = (localSegment.limit - localSegment.pos);
    this.inflater.setInput(localSegment.data, localSegment.pos, this.bufferBytesHeldByInflater);
    return false;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.InflaterSource
 * JD-Core Version:    0.6.0
 */