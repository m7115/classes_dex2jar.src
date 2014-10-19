package com.squareup.okhttp.internal.okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class GzipSource
  implements Source
{
  private static final byte FCOMMENT = 4;
  private static final byte FEXTRA = 2;
  private static final byte FHCRC = 1;
  private static final byte FNAME = 3;
  private static final byte SECTION_BODY = 1;
  private static final byte SECTION_DONE = 3;
  private static final byte SECTION_HEADER = 0;
  private static final byte SECTION_TRAILER = 2;
  private final CRC32 crc = new CRC32();
  private final Inflater inflater = new Inflater(true);
  private final InflaterSource inflaterSource;
  private int section = 0;
  private final BufferedSource source;

  public GzipSource(Source paramSource)
    throws IOException
  {
    this.source = Okio.buffer(paramSource);
    this.inflaterSource = new InflaterSource(this.source, this.inflater);
  }

  private void checkEqual(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 != paramInt1)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = Integer.valueOf(paramInt2);
      arrayOfObject[2] = Integer.valueOf(paramInt1);
      throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", arrayOfObject));
    }
  }

  private void consumeHeader()
    throws IOException
  {
    this.source.require(10L);
    int i = this.source.buffer().getByte(3L);
    if ((0x1 & i >> 1) == 1);
    for (int j = 1; ; j = 0)
    {
      if (j != 0)
        updateCrc(this.source.buffer(), 0L, 10L);
      checkEqual("ID1ID2", 8075, this.source.readShort());
      this.source.skip(8L);
      if ((0x1 & i >> 2) == 1)
      {
        this.source.require(2L);
        if (j != 0)
          updateCrc(this.source.buffer(), 0L, 2L);
        int k = 0xFFFF & this.source.buffer().readShortLe();
        this.source.require(k);
        if (j != 0)
          updateCrc(this.source.buffer(), 0L, k);
        this.source.skip(k);
      }
      if ((0x1 & i >> 3) == 1)
      {
        long l2 = this.source.seek(0);
        if (j != 0)
          updateCrc(this.source.buffer(), 0L, l2 + 1L);
        this.source.skip(l2 + 1L);
      }
      if ((0x1 & i >> 4) == 1)
      {
        long l1 = this.source.seek(0);
        if (j != 0)
          updateCrc(this.source.buffer(), 0L, l1 + 1L);
        this.source.skip(l1 + 1L);
      }
      if (j != 0)
      {
        checkEqual("FHCRC", this.source.readShortLe(), (short)(int)this.crc.getValue());
        this.crc.reset();
      }
      return;
    }
  }

  private void consumeTrailer()
    throws IOException
  {
    checkEqual("CRC", this.source.readIntLe(), (int)this.crc.getValue());
    checkEqual("ISIZE", this.source.readIntLe(), this.inflater.getTotalOut());
  }

  private void updateCrc(OkBuffer paramOkBuffer, long paramLong1, long paramLong2)
  {
    Segment localSegment = paramOkBuffer.head;
    long l = paramLong2;
    while (l > 0L)
    {
      int i = localSegment.limit - localSegment.pos;
      if (paramLong1 < i)
      {
        int j = (int)Math.min(l, i - paramLong1);
        this.crc.update(localSegment.data, (int)(paramLong1 + localSegment.pos), j);
        l -= j;
      }
      paramLong1 -= i;
      localSegment = localSegment.next;
    }
  }

  public void close()
    throws IOException
  {
    this.inflaterSource.close();
  }

  public Source deadline(Deadline paramDeadline)
  {
    this.source.deadline(paramDeadline);
    return this;
  }

  public long read(OkBuffer paramOkBuffer, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("byteCount < 0: " + paramLong);
    if (paramLong == 0L)
      return 0L;
    if (this.section == 0)
    {
      consumeHeader();
      this.section = 1;
    }
    if (this.section == 1)
    {
      long l1 = paramOkBuffer.size;
      long l2 = this.inflaterSource.read(paramOkBuffer, paramLong);
      if (l2 != -1L)
      {
        updateCrc(paramOkBuffer, l1, l2);
        return l2;
      }
      this.section = 2;
    }
    if (this.section == 2)
    {
      consumeTrailer();
      this.section = 3;
      if (!this.source.exhausted())
        throw new IOException("gzip finished without exhausting source");
    }
    return -1L;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.GzipSource
 * JD-Core Version:    0.6.0
 */