package com.squareup.okhttp.internal.okio;

final class SegmentPool
{
  static final SegmentPool INSTANCE = new SegmentPool();
  static final long MAX_SIZE = 65536L;
  long byteCount;
  private Segment next;

  void recycle(Segment paramSegment)
  {
    if ((paramSegment.next != null) || (paramSegment.prev != null))
      throw new IllegalArgumentException();
    monitorenter;
    try
    {
      if (2048L + this.byteCount > 65536L)
        return;
      this.byteCount = (2048L + this.byteCount);
      paramSegment.next = this.next;
      paramSegment.limit = 0;
      paramSegment.pos = 0;
      this.next = paramSegment;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  Segment take()
  {
    monitorenter;
    try
    {
      if (this.next != null)
      {
        Segment localSegment = this.next;
        this.next = localSegment.next;
        localSegment.next = null;
        this.byteCount -= 2048L;
        return localSegment;
      }
      return new Segment();
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.SegmentPool
 * JD-Core Version:    0.6.0
 */