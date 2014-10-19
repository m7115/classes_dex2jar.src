package com.squareup.okhttp.internal.okio;

import java.io.Closeable;
import java.io.IOException;

public abstract interface Sink extends Closeable
{
  public abstract void close()
    throws IOException;

  public abstract Sink deadline(Deadline paramDeadline);

  public abstract void flush()
    throws IOException;

  public abstract void write(OkBuffer paramOkBuffer, long paramLong)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.Sink
 * JD-Core Version:    0.6.0
 */