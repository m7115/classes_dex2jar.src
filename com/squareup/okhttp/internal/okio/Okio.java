package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Okio
{
  public static BufferedSink buffer(Sink paramSink)
  {
    return new RealBufferedSink(paramSink);
  }

  public static BufferedSource buffer(Source paramSource)
  {
    return new RealBufferedSource(paramSource);
  }

  public static void copy(OkBuffer paramOkBuffer, long paramLong1, long paramLong2, OutputStream paramOutputStream)
    throws IOException
  {
    Util.checkOffsetAndCount(paramOkBuffer.size, paramLong1, paramLong2);
    for (Segment localSegment = paramOkBuffer.head; paramLong1 >= localSegment.limit - localSegment.pos; localSegment = localSegment.next)
      paramLong1 -= localSegment.limit - localSegment.pos;
    while (paramLong2 > 0L)
    {
      int i = (int)(paramLong1 + localSegment.pos);
      int j = (int)Math.min(localSegment.limit - i, paramLong2);
      paramOutputStream.write(localSegment.data, i, j);
      paramLong2 -= j;
      paramLong1 = 0L;
    }
  }

  public static Sink sink(OutputStream paramOutputStream)
  {
    return new Sink(paramOutputStream)
    {
      private Deadline deadline = Deadline.NONE;

      public void close()
        throws IOException
      {
        this.val$out.close();
      }

      public Sink deadline(Deadline paramDeadline)
      {
        if (paramDeadline == null)
          throw new IllegalArgumentException("deadline == null");
        this.deadline = paramDeadline;
        return this;
      }

      public void flush()
        throws IOException
      {
        this.val$out.flush();
      }

      public String toString()
      {
        return "sink(" + this.val$out + ")";
      }

      public void write(OkBuffer paramOkBuffer, long paramLong)
        throws IOException
      {
        Util.checkOffsetAndCount(paramOkBuffer.size, 0L, paramLong);
        while (paramLong > 0L)
        {
          this.deadline.throwIfReached();
          Segment localSegment = paramOkBuffer.head;
          int i = (int)Math.min(paramLong, localSegment.limit - localSegment.pos);
          this.val$out.write(localSegment.data, localSegment.pos, i);
          localSegment.pos = (i + localSegment.pos);
          paramLong -= i;
          paramOkBuffer.size -= i;
          if (localSegment.pos != localSegment.limit)
            continue;
          paramOkBuffer.head = localSegment.pop();
          SegmentPool.INSTANCE.recycle(localSegment);
        }
      }
    };
  }

  public static Source source(InputStream paramInputStream)
  {
    return new Source(paramInputStream)
    {
      private Deadline deadline = Deadline.NONE;

      public void close()
        throws IOException
      {
        this.val$in.close();
      }

      public Source deadline(Deadline paramDeadline)
      {
        if (paramDeadline == null)
          throw new IllegalArgumentException("deadline == null");
        this.deadline = paramDeadline;
        return this;
      }

      public long read(OkBuffer paramOkBuffer, long paramLong)
        throws IOException
      {
        if (paramLong < 0L)
          throw new IllegalArgumentException("byteCount < 0: " + paramLong);
        this.deadline.throwIfReached();
        Segment localSegment = paramOkBuffer.writableSegment(1);
        int i = (int)Math.min(paramLong, 2048 - localSegment.limit);
        int j = this.val$in.read(localSegment.data, localSegment.limit, i);
        if (j == -1)
          return -1L;
        localSegment.limit = (j + localSegment.limit);
        paramOkBuffer.size += j;
        return j;
      }

      public String toString()
      {
        return "source(" + this.val$in + ")";
      }
    };
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.Okio
 * JD-Core Version:    0.6.0
 */