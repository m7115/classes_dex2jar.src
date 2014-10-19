package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.OutputStream;

final class RealBufferedSink
  implements BufferedSink
{
  public final OkBuffer buffer;
  private boolean closed;
  public final Sink sink;

  public RealBufferedSink(Sink paramSink)
  {
    this(paramSink, new OkBuffer());
  }

  public RealBufferedSink(Sink paramSink, OkBuffer paramOkBuffer)
  {
    if (paramSink == null)
      throw new IllegalArgumentException("sink == null");
    this.buffer = paramOkBuffer;
    this.sink = paramSink;
  }

  private void checkNotClosed()
  {
    if (this.closed)
      throw new IllegalStateException("closed");
  }

  public OkBuffer buffer()
  {
    return this.buffer;
  }

  public void close()
    throws IOException
  {
    if (this.closed);
    while (true)
    {
      return;
      try
      {
        boolean bool = this.buffer.size < 0L;
        Object localObject = null;
        if (bool)
          this.sink.write(this.buffer, this.buffer.size);
        try
        {
          label44: this.sink.close();
          this.closed = true;
          if (localObject == null)
            continue;
          Util.sneakyRethrow((Throwable)localObject);
          return;
        }
        catch (Throwable localThrowable2)
        {
          while (true)
          {
            if (localObject != null)
              continue;
            localObject = localThrowable2;
          }
        }
      }
      catch (Throwable localThrowable1)
      {
        break label44;
      }
    }
  }

  public Sink deadline(Deadline paramDeadline)
  {
    this.sink.deadline(paramDeadline);
    return this;
  }

  public BufferedSink emitCompleteSegments()
    throws IOException
  {
    checkNotClosed();
    long l = this.buffer.completeSegmentByteCount();
    if (l > 0L)
      this.sink.write(this.buffer, l);
    return this;
  }

  public void flush()
    throws IOException
  {
    checkNotClosed();
    if (this.buffer.size > 0L)
      this.sink.write(this.buffer, this.buffer.size);
    this.sink.flush();
  }

  public OutputStream outputStream()
  {
    return new OutputStream()
    {
      private void checkNotClosed()
        throws IOException
      {
        if (RealBufferedSink.this.closed)
          throw new IOException("closed");
      }

      public void close()
        throws IOException
      {
        RealBufferedSink.this.close();
      }

      public void flush()
        throws IOException
      {
        if (!RealBufferedSink.this.closed)
          RealBufferedSink.this.flush();
      }

      public String toString()
      {
        return RealBufferedSink.this + ".outputStream()";
      }

      public void write(int paramInt)
        throws IOException
      {
        checkNotClosed();
        RealBufferedSink.this.buffer.writeByte((byte)paramInt);
        RealBufferedSink.this.emitCompleteSegments();
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws IOException
      {
        checkNotClosed();
        RealBufferedSink.this.buffer.write(paramArrayOfByte, paramInt1, paramInt2);
        RealBufferedSink.this.emitCompleteSegments();
      }
    };
  }

  public String toString()
  {
    return "buffer(" + this.sink + ")";
  }

  public BufferedSink write(ByteString paramByteString)
    throws IOException
  {
    checkNotClosed();
    this.buffer.write(paramByteString);
    return emitCompleteSegments();
  }

  public BufferedSink write(byte[] paramArrayOfByte)
    throws IOException
  {
    checkNotClosed();
    this.buffer.write(paramArrayOfByte);
    return emitCompleteSegments();
  }

  public BufferedSink write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    checkNotClosed();
    this.buffer.write(paramArrayOfByte, paramInt1, paramInt2);
    return emitCompleteSegments();
  }

  public void write(OkBuffer paramOkBuffer, long paramLong)
    throws IOException
  {
    checkNotClosed();
    this.buffer.write(paramOkBuffer, paramLong);
    emitCompleteSegments();
  }

  public BufferedSink writeByte(int paramInt)
    throws IOException
  {
    checkNotClosed();
    this.buffer.writeByte(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeInt(int paramInt)
    throws IOException
  {
    checkNotClosed();
    this.buffer.writeInt(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeShort(int paramInt)
    throws IOException
  {
    checkNotClosed();
    this.buffer.writeShort(paramInt);
    return emitCompleteSegments();
  }

  public BufferedSink writeUtf8(String paramString)
    throws IOException
  {
    checkNotClosed();
    this.buffer.writeUtf8(paramString);
    return emitCompleteSegments();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.RealBufferedSink
 * JD-Core Version:    0.6.0
 */