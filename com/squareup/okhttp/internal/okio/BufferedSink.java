package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.OutputStream;

public abstract interface BufferedSink extends Sink
{
  public abstract OkBuffer buffer();

  public abstract BufferedSink emitCompleteSegments()
    throws IOException;

  public abstract OutputStream outputStream();

  public abstract BufferedSink write(ByteString paramByteString)
    throws IOException;

  public abstract BufferedSink write(byte[] paramArrayOfByte)
    throws IOException;

  public abstract BufferedSink write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;

  public abstract BufferedSink writeByte(int paramInt)
    throws IOException;

  public abstract BufferedSink writeInt(int paramInt)
    throws IOException;

  public abstract BufferedSink writeShort(int paramInt)
    throws IOException;

  public abstract BufferedSink writeUtf8(String paramString)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.BufferedSink
 * JD-Core Version:    0.6.0
 */