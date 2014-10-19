package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.InputStream;

public abstract interface BufferedSource extends Source
{
  public abstract OkBuffer buffer();

  public abstract boolean exhausted()
    throws IOException;

  public abstract InputStream inputStream();

  public abstract byte readByte()
    throws IOException;

  public abstract ByteString readByteString(long paramLong)
    throws IOException;

  public abstract int readInt()
    throws IOException;

  public abstract int readIntLe()
    throws IOException;

  public abstract short readShort()
    throws IOException;

  public abstract int readShortLe()
    throws IOException;

  public abstract String readUtf8(long paramLong)
    throws IOException;

  public abstract String readUtf8Line(boolean paramBoolean)
    throws IOException;

  public abstract void require(long paramLong)
    throws IOException;

  public abstract long seek(byte paramByte)
    throws IOException;

  public abstract void skip(long paramLong)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.BufferedSource
 * JD-Core Version:    0.6.0
 */