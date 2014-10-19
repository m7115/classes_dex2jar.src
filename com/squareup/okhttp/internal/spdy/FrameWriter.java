package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.OkBuffer;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public abstract interface FrameWriter extends Closeable
{
  public abstract void ackSettings()
    throws IOException;

  public abstract void connectionHeader()
    throws IOException;

  public abstract void data(boolean paramBoolean, int paramInt, OkBuffer paramOkBuffer)
    throws IOException;

  public abstract void data(boolean paramBoolean, int paramInt1, OkBuffer paramOkBuffer, int paramInt2)
    throws IOException;

  public abstract void flush()
    throws IOException;

  public abstract void goAway(int paramInt, ErrorCode paramErrorCode, byte[] paramArrayOfByte)
    throws IOException;

  public abstract void headers(int paramInt, List<Header> paramList)
    throws IOException;

  public abstract void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    throws IOException;

  public abstract void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
    throws IOException;

  public abstract void rstStream(int paramInt, ErrorCode paramErrorCode)
    throws IOException;

  public abstract void settings(Settings paramSettings)
    throws IOException;

  public abstract void synReply(boolean paramBoolean, int paramInt, List<Header> paramList)
    throws IOException;

  public abstract void synStream(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<Header> paramList)
    throws IOException;

  public abstract void windowUpdate(int paramInt, long paramLong)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.FrameWriter
 * JD-Core Version:    0.6.0
 */