package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public abstract interface FrameReader extends Closeable
{
  public abstract boolean nextFrame(Handler paramHandler)
    throws IOException;

  public abstract void readConnectionHeader()
    throws IOException;

  public static abstract interface Handler
  {
    public abstract void ackSettings();

    public abstract void data(boolean paramBoolean, int paramInt1, BufferedSource paramBufferedSource, int paramInt2)
      throws IOException;

    public abstract void goAway(int paramInt, ErrorCode paramErrorCode, ByteString paramByteString);

    public abstract void headers(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, List<Header> paramList, HeadersMode paramHeadersMode);

    public abstract void ping(boolean paramBoolean, int paramInt1, int paramInt2);

    public abstract void priority(int paramInt1, int paramInt2);

    public abstract void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
      throws IOException;

    public abstract void rstStream(int paramInt, ErrorCode paramErrorCode);

    public abstract void settings(boolean paramBoolean, Settings paramSettings);

    public abstract void windowUpdate(int paramInt, long paramLong);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.FrameReader
 * JD-Core Version:    0.6.0
 */