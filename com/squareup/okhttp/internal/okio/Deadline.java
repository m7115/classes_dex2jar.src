package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class Deadline
{
  public static final Deadline NONE = new Deadline()
  {
    public boolean reached()
    {
      return false;
    }

    public Deadline start(long paramLong, TimeUnit paramTimeUnit)
    {
      throw new UnsupportedOperationException();
    }
  };
  private long deadlineNanos;

  public boolean reached()
  {
    return System.nanoTime() - this.deadlineNanos >= 0L;
  }

  public Deadline start(long paramLong, TimeUnit paramTimeUnit)
  {
    this.deadlineNanos = (System.nanoTime() + paramTimeUnit.toNanos(paramLong));
    return this;
  }

  public final void throwIfReached()
    throws IOException
  {
    if (reached())
      throw new IOException("Deadline reached");
    if (Thread.interrupted())
      throw new InterruptedIOException();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.Deadline
 * JD-Core Version:    0.6.0
 */