package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;

abstract interface Variant
{
  public abstract Protocol getProtocol();

  public abstract int maxFrameSize();

  public abstract FrameReader newReader(BufferedSource paramBufferedSource, boolean paramBoolean);

  public abstract FrameWriter newWriter(BufferedSink paramBufferedSink, boolean paramBoolean);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Variant
 * JD-Core Version:    0.6.0
 */