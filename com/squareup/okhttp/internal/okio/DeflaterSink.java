package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class DeflaterSink
  implements Sink
{
  private boolean closed;
  private final Deflater deflater;
  private final BufferedSink sink;

  public DeflaterSink(Sink paramSink, Deflater paramDeflater)
  {
    this.sink = Okio.buffer(paramSink);
    this.deflater = paramDeflater;
  }

  @IgnoreJRERequirement
  private void deflate(boolean paramBoolean)
    throws IOException
  {
    OkBuffer localOkBuffer = this.sink.buffer();
    label115: 
    do
    {
      Segment localSegment = localOkBuffer.writableSegment(1);
      if (paramBoolean);
      for (int i = this.deflater.deflate(localSegment.data, localSegment.limit, 2048 - localSegment.limit, 2); ; i = this.deflater.deflate(localSegment.data, localSegment.limit, 2048 - localSegment.limit))
      {
        if (i <= 0)
          break label115;
        localSegment.limit = (i + localSegment.limit);
        localOkBuffer.size += i;
        this.sink.emitCompleteSegments();
        break;
      }
    }
    while (!this.deflater.needsInput());
  }

  public void close()
    throws IOException
  {
    if (this.closed);
    while (true)
    {
      return;
      Object localObject1 = null;
      try
      {
        this.deflater.finish();
        deflate(false);
      }
      catch (Throwable localObject3)
      {
        try
        {
          this.deflater.end();
          localObject2 = localObject1;
        }
        catch (Throwable localObject3)
        {
          try
          {
            while (true)
            {
              Object localObject2;
              this.sink.close();
              this.closed = true;
              if (localObject2 == null)
                break;
              Util.sneakyRethrow(localObject2);
              return;
              localThrowable1 = localThrowable1;
              localObject1 = localThrowable1;
              continue;
              localThrowable2 = localThrowable2;
              if (localObject1 == null)
                continue;
              localObject3 = localObject1;
            }
          }
          catch (Throwable localThrowable3)
          {
            while (true)
            {
              if (localObject3 != null)
                continue;
              Object localObject3 = localThrowable3;
            }
          }
        }
      }
    }
  }

  public Sink deadline(Deadline paramDeadline)
  {
    this.sink.deadline(paramDeadline);
    return this;
  }

  public void flush()
    throws IOException
  {
    deflate(true);
    this.sink.flush();
  }

  public String toString()
  {
    return "DeflaterSink(" + this.sink + ")";
  }

  public void write(OkBuffer paramOkBuffer, long paramLong)
    throws IOException
  {
    Util.checkOffsetAndCount(paramOkBuffer.size, 0L, paramLong);
    while (paramLong > 0L)
    {
      Segment localSegment = paramOkBuffer.head;
      int i = (int)Math.min(paramLong, localSegment.limit - localSegment.pos);
      this.deflater.setInput(localSegment.data, localSegment.pos, i);
      deflate(false);
      paramOkBuffer.size -= i;
      localSegment.pos = (i + localSegment.pos);
      if (localSegment.pos == localSegment.limit)
      {
        paramOkBuffer.head = localSegment.pop();
        SegmentPool.INSTANCE.recycle(localSegment);
      }
      paramLong -= i;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.DeflaterSink
 * JD-Core Version:    0.6.0
 */