package com.squareup.okhttp.internal.okio;

import java.io.Closeable;
import java.io.IOException;

public abstract interface Source extends Closeable
{
  public abstract void close()
    throws IOException;

  public abstract Source deadline(Deadline paramDeadline);

  public abstract long read(OkBuffer paramOkBuffer, long paramLong)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.Source
 * JD-Core Version:    0.6.0
 */