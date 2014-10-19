package com.squareup.okhttp.internal.okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

final class RealBufferedSource
  implements BufferedSource
{
  public final OkBuffer buffer;
  private boolean closed;
  public final Source source;

  public RealBufferedSource(Source paramSource)
  {
    this(paramSource, new OkBuffer());
  }

  public RealBufferedSource(Source paramSource, OkBuffer paramOkBuffer)
  {
    if (paramSource == null)
      throw new IllegalArgumentException("source == null");
    this.buffer = paramOkBuffer;
    this.source = paramSource;
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
    if (this.closed)
      return;
    this.closed = true;
    this.source.close();
    this.buffer.clear();
  }

  public Source deadline(Deadline paramDeadline)
  {
    this.source.deadline(paramDeadline);
    return this;
  }

  public boolean exhausted()
    throws IOException
  {
    checkNotClosed();
    return (this.buffer.exhausted()) && (this.source.read(this.buffer, 2048L) == -1L);
  }

  public InputStream inputStream()
  {
    return new InputStream()
    {
      private void checkNotClosed()
        throws IOException
      {
        if (RealBufferedSource.this.closed)
          throw new IOException("closed");
      }

      public int available()
        throws IOException
      {
        checkNotClosed();
        return (int)Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
      }

      public void close()
        throws IOException
      {
        RealBufferedSource.this.close();
      }

      public int read()
        throws IOException
      {
        checkNotClosed();
        if ((RealBufferedSource.this.buffer.size == 0L) && (RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 2048L) == -1L))
          return -1;
        return 0xFF & RealBufferedSource.this.buffer.readByte();
      }

      public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws IOException
      {
        checkNotClosed();
        Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
        if ((RealBufferedSource.this.buffer.size == 0L) && (RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 2048L) == -1L))
          return -1;
        return RealBufferedSource.this.buffer.read(paramArrayOfByte, paramInt1, paramInt2);
      }

      public String toString()
      {
        return RealBufferedSource.this + ".inputStream()";
      }
    };
  }

  public long read(OkBuffer paramOkBuffer, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("byteCount < 0: " + paramLong);
    checkNotClosed();
    if ((this.buffer.size == 0L) && (this.source.read(this.buffer, 2048L) == -1L))
      return -1L;
    long l = Math.min(paramLong, this.buffer.size);
    return this.buffer.read(paramOkBuffer, l);
  }

  public byte readByte()
    throws IOException
  {
    require(1L);
    return this.buffer.readByte();
  }

  public ByteString readByteString(long paramLong)
    throws IOException
  {
    require(paramLong);
    return this.buffer.readByteString(paramLong);
  }

  public int readInt()
    throws IOException
  {
    require(4L);
    return this.buffer.readInt();
  }

  public int readIntLe()
    throws IOException
  {
    require(4L);
    return this.buffer.readIntLe();
  }

  public short readShort()
    throws IOException
  {
    require(2L);
    return this.buffer.readShort();
  }

  public int readShortLe()
    throws IOException
  {
    require(2L);
    return this.buffer.readShortLe();
  }

  public String readUtf8(long paramLong)
    throws IOException
  {
    require(paramLong);
    return this.buffer.readUtf8(paramLong);
  }

  public String readUtf8Line(boolean paramBoolean)
    throws IOException
  {
    checkNotClosed();
    long l1 = 0L;
    long l2;
    while (true)
    {
      l2 = this.buffer.indexOf(10, l1);
      if (l2 != -1L)
        break;
      l1 = this.buffer.size;
      if (this.source.read(this.buffer, 2048L) != -1L)
        continue;
      if (paramBoolean)
        throw new EOFException();
      if (this.buffer.size != 0L)
        return readUtf8(this.buffer.size);
      return null;
    }
    if ((l2 > 0L) && (this.buffer.getByte(l2 - 1L) == 13))
    {
      String str2 = readUtf8(l2 - 1L);
      skip(2L);
      return str2;
    }
    String str1 = readUtf8(l2);
    skip(1L);
    return str1;
  }

  public void require(long paramLong)
    throws IOException
  {
    checkNotClosed();
    while (this.buffer.size < paramLong)
    {
      if (this.source.read(this.buffer, 2048L) != -1L)
        continue;
      throw new EOFException();
    }
  }

  public long seek(byte paramByte)
    throws IOException
  {
    checkNotClosed();
    long l1 = 0L;
    long l2;
    while (true)
    {
      l2 = this.buffer.indexOf(paramByte, l1);
      if (l2 != -1L)
        break;
      l1 = this.buffer.size;
      if (this.source.read(this.buffer, 2048L) != -1L)
        continue;
      throw new EOFException();
    }
    return l2;
  }

  public void skip(long paramLong)
    throws IOException
  {
    checkNotClosed();
    while (paramLong > 0L)
    {
      if ((this.buffer.size == 0L) && (this.source.read(this.buffer, 2048L) == -1L))
        throw new EOFException();
      long l = Math.min(paramLong, this.buffer.size());
      this.buffer.skip(l);
      paramLong -= l;
    }
  }

  public String toString()
  {
    return "buffer(" + this.source + ")";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.RealBufferedSource
 * JD-Core Version:    0.6.0
 */