package com.squareup.okhttp.internal.okio;

import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OkBuffer
  implements BufferedSink, BufferedSource, Cloneable
{
  Segment head;
  long size;

  private byte[] readBytes(long paramLong)
  {
    Util.checkOffsetAndCount(this.size, 0L, paramLong);
    if (paramLong > 2147483647L)
      throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + paramLong);
    int i = 0;
    byte[] arrayOfByte = new byte[(int)paramLong];
    while (i < paramLong)
    {
      int j = (int)Math.min(paramLong - i, this.head.limit - this.head.pos);
      System.arraycopy(this.head.data, this.head.pos, arrayOfByte, i, j);
      i += j;
      Segment localSegment1 = this.head;
      localSegment1.pos = (j + localSegment1.pos);
      if (this.head.pos != this.head.limit)
        continue;
      Segment localSegment2 = this.head;
      this.head = localSegment2.pop();
      SegmentPool.INSTANCE.recycle(localSegment2);
    }
    this.size -= paramLong;
    return arrayOfByte;
  }

  public OkBuffer buffer()
  {
    return this;
  }

  public void clear()
  {
    skip(this.size);
  }

  public OkBuffer clone()
  {
    OkBuffer localOkBuffer = new OkBuffer();
    if (size() == 0L)
      return localOkBuffer;
    localOkBuffer.write(this.head.data, this.head.pos, this.head.limit - this.head.pos);
    for (Segment localSegment = this.head.next; localSegment != this.head; localSegment = localSegment.next)
      localOkBuffer.write(localSegment.data, localSegment.pos, localSegment.limit - localSegment.pos);
    return localOkBuffer;
  }

  public void close()
  {
  }

  public long completeSegmentByteCount()
  {
    long l = this.size;
    if (l == 0L)
      l = 0L;
    Segment localSegment;
    do
    {
      return l;
      localSegment = this.head.prev;
    }
    while (localSegment.limit >= 2048);
    return l - (localSegment.limit - localSegment.pos);
  }

  public OkBuffer deadline(Deadline paramDeadline)
  {
    return this;
  }

  public OkBuffer emitCompleteSegments()
  {
    return this;
  }

  public boolean equals(Object paramObject)
  {
    long l1 = 0L;
    if (!(paramObject instanceof OkBuffer))
      return false;
    OkBuffer localOkBuffer = (OkBuffer)paramObject;
    if (this.size != localOkBuffer.size)
      return false;
    if (this.size == l1)
      return true;
    Segment localSegment1 = this.head;
    Segment localSegment2 = localOkBuffer.head;
    int i = localSegment1.pos;
    int j = localSegment2.pos;
    while (l1 < this.size)
    {
      long l2 = Math.min(localSegment1.limit - i, localSegment2.limit - j);
      int k = 0;
      while (k < l2)
      {
        byte[] arrayOfByte1 = localSegment1.data;
        int m = i + 1;
        int n = arrayOfByte1[i];
        byte[] arrayOfByte2 = localSegment2.data;
        int i1 = j + 1;
        if (n != arrayOfByte2[j])
          return false;
        k++;
        j = i1;
        i = m;
      }
      if (i == localSegment1.limit)
      {
        localSegment1 = localSegment1.next;
        i = localSegment1.pos;
      }
      if (j == localSegment2.limit)
      {
        localSegment2 = localSegment2.next;
        j = localSegment2.pos;
      }
      l1 += l2;
    }
    return true;
  }

  public boolean exhausted()
  {
    return this.size == 0L;
  }

  public void flush()
  {
  }

  public byte getByte(long paramLong)
  {
    Util.checkOffsetAndCount(this.size, paramLong, 1L);
    for (Segment localSegment = this.head; ; localSegment = localSegment.next)
    {
      int i = localSegment.limit - localSegment.pos;
      if (paramLong < i)
        return localSegment.data[(localSegment.pos + (int)paramLong)];
      paramLong -= i;
    }
  }

  public int hashCode()
  {
    Segment localSegment = this.head;
    if (localSegment == null)
      return 0;
    int i = 1;
    do
    {
      int j = localSegment.pos;
      int k = localSegment.limit;
      while (j < k)
      {
        int m = i * 31 + localSegment.data[j];
        j++;
        i = m;
      }
      localSegment = localSegment.next;
    }
    while (localSegment != this.head);
    return i;
  }

  public long indexOf(byte paramByte)
  {
    return indexOf(paramByte, 0L);
  }

  public long indexOf(byte paramByte, long paramLong)
  {
    Segment localSegment = this.head;
    if (localSegment == null)
      return -1L;
    long l1 = 0L;
    int i = localSegment.limit - localSegment.pos;
    if (paramLong > i)
      paramLong -= i;
    while (true)
    {
      l1 += i;
      localSegment = localSegment.next;
      if (localSegment != this.head)
        break;
      return -1L;
      byte[] arrayOfByte = localSegment.data;
      long l2 = paramLong + localSegment.pos;
      long l3 = localSegment.limit;
      while (l2 < l3)
      {
        if (arrayOfByte[(int)l2] == paramByte)
          return l1 + l2 - localSegment.pos;
        l2 += 1L;
      }
      paramLong = 0L;
    }
  }

  public InputStream inputStream()
  {
    return new InputStream()
    {
      public int available()
      {
        return (int)Math.min(OkBuffer.this.size, 2147483647L);
      }

      public void close()
      {
      }

      public int read()
      {
        return 0xFF & OkBuffer.this.readByte();
      }

      public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        return OkBuffer.this.read(paramArrayOfByte, paramInt1, paramInt2);
      }

      public String toString()
      {
        return OkBuffer.this + ".inputStream()";
      }
    };
  }

  public OutputStream outputStream()
  {
    return new OutputStream()
    {
      public void close()
      {
      }

      public void flush()
      {
      }

      public String toString()
      {
        return this + ".outputStream()";
      }

      public void write(int paramInt)
      {
        OkBuffer.this.writeByte((byte)paramInt);
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        OkBuffer.this.write(paramArrayOfByte, paramInt1, paramInt2);
      }
    };
  }

  int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Segment localSegment = this.head;
    int i;
    if (localSegment == null)
      i = -1;
    do
    {
      return i;
      i = Math.min(paramInt2, localSegment.limit - localSegment.pos);
      System.arraycopy(localSegment.data, localSegment.pos, paramArrayOfByte, paramInt1, i);
      localSegment.pos = (i + localSegment.pos);
      this.size -= i;
    }
    while (localSegment.pos != localSegment.limit);
    this.head = localSegment.pop();
    SegmentPool.INSTANCE.recycle(localSegment);
    return i;
  }

  public long read(OkBuffer paramOkBuffer, long paramLong)
  {
    if (this.size == 0L)
      return -1L;
    if (paramLong > this.size)
      paramLong = this.size;
    paramOkBuffer.write(this, paramLong);
    return paramLong;
  }

  public byte readByte()
  {
    if (this.size == 0L)
      throw new IllegalStateException("size == 0");
    Segment localSegment = this.head;
    int i = localSegment.pos;
    int j = localSegment.limit;
    byte[] arrayOfByte = localSegment.data;
    int k = i + 1;
    int m = arrayOfByte[i];
    this.size -= 1L;
    if (k == j)
    {
      this.head = localSegment.pop();
      SegmentPool.INSTANCE.recycle(localSegment);
      return m;
    }
    localSegment.pos = k;
    return m;
  }

  public ByteString readByteString(long paramLong)
  {
    return new ByteString(readBytes(paramLong));
  }

  public int readInt()
  {
    if (this.size < 4L)
      throw new IllegalArgumentException("size < 4: " + this.size);
    Segment localSegment = this.head;
    int i = localSegment.pos;
    int j = localSegment.limit;
    if (j - i < 4)
      return (0xFF & readByte()) << 24 | (0xFF & readByte()) << 16 | (0xFF & readByte()) << 8 | 0xFF & readByte();
    byte[] arrayOfByte = localSegment.data;
    int k = i + 1;
    int m = (0xFF & arrayOfByte[i]) << 24;
    int n = k + 1;
    int i1 = m | (0xFF & arrayOfByte[k]) << 16;
    int i2 = n + 1;
    int i3 = i1 | (0xFF & arrayOfByte[n]) << 8;
    int i4 = i2 + 1;
    int i5 = i3 | 0xFF & arrayOfByte[i2];
    this.size -= 4L;
    if (i4 == j)
    {
      this.head = localSegment.pop();
      SegmentPool.INSTANCE.recycle(localSegment);
      return i5;
    }
    localSegment.pos = i4;
    return i5;
  }

  public int readIntLe()
  {
    return Util.reverseBytesInt(readInt());
  }

  public short readShort()
  {
    if (this.size < 2L)
      throw new IllegalArgumentException("size < 2: " + this.size);
    Segment localSegment = this.head;
    int i = localSegment.pos;
    int j = localSegment.limit;
    if (j - i < 2)
      return (short)((0xFF & readByte()) << 8 | 0xFF & readByte());
    byte[] arrayOfByte = localSegment.data;
    int k = i + 1;
    int m = (0xFF & arrayOfByte[i]) << 8;
    int n = k + 1;
    int i1 = m | 0xFF & arrayOfByte[k];
    this.size -= 2L;
    if (n == j)
    {
      this.head = localSegment.pop();
      SegmentPool.INSTANCE.recycle(localSegment);
    }
    while (true)
    {
      return (short)i1;
      localSegment.pos = n;
    }
  }

  public int readShortLe()
  {
    return Util.reverseBytesShort(readShort());
  }

  public String readUtf8(long paramLong)
  {
    Util.checkOffsetAndCount(this.size, 0L, paramLong);
    if (paramLong > 2147483647L)
      throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + paramLong);
    String str1;
    if (paramLong == 0L)
      str1 = "";
    while (true)
    {
      return str1;
      Segment localSegment = this.head;
      if (paramLong + localSegment.pos > localSegment.limit)
        try
        {
          String str2 = new String(readBytes(paramLong), "UTF-8");
          return str2;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          throw new AssertionError(localUnsupportedEncodingException2);
        }
      try
      {
        str1 = new String(localSegment.data, localSegment.pos, (int)paramLong, "UTF-8");
        localSegment.pos = (int)(paramLong + localSegment.pos);
        this.size -= paramLong;
        if (localSegment.pos != localSegment.limit)
          continue;
        this.head = localSegment.pop();
        SegmentPool.INSTANCE.recycle(localSegment);
        return str1;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException1)
      {
      }
    }
    throw new AssertionError(localUnsupportedEncodingException1);
  }

  public String readUtf8Line(boolean paramBoolean)
    throws EOFException
  {
    long l = indexOf(10);
    if (l == -1L)
    {
      if (paramBoolean)
        throw new EOFException();
      if (this.size != 0L)
        return readUtf8(this.size);
      return null;
    }
    if ((l > 0L) && (getByte(l - 1L) == 13))
    {
      String str2 = readUtf8(l - 1L);
      skip(2L);
      return str2;
    }
    String str1 = readUtf8(l);
    skip(1L);
    return str1;
  }

  public void require(long paramLong)
    throws EOFException
  {
    if (this.size < paramLong)
      throw new EOFException();
  }

  public long seek(byte paramByte)
    throws EOFException
  {
    long l = indexOf(paramByte);
    if (l == -1L)
      throw new EOFException();
    return l;
  }

  List<Integer> segmentSizes()
  {
    if (this.head == null)
      return Collections.emptyList();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(this.head.limit - this.head.pos));
    for (Segment localSegment = this.head.next; localSegment != this.head; localSegment = localSegment.next)
      localArrayList.add(Integer.valueOf(localSegment.limit - localSegment.pos));
    return localArrayList;
  }

  public long size()
  {
    return this.size;
  }

  public void skip(long paramLong)
  {
    Util.checkOffsetAndCount(this.size, 0L, paramLong);
    this.size -= paramLong;
    while (paramLong > 0L)
    {
      int i = (int)Math.min(paramLong, this.head.limit - this.head.pos);
      paramLong -= i;
      Segment localSegment1 = this.head;
      localSegment1.pos = (i + localSegment1.pos);
      if (this.head.pos != this.head.limit)
        continue;
      Segment localSegment2 = this.head;
      this.head = localSegment2.pop();
      SegmentPool.INSTANCE.recycle(localSegment2);
    }
  }

  public String toString()
  {
    if (this.size == 0L)
      return "OkBuffer[size=0]";
    if (this.size <= 16L)
    {
      ByteString localByteString = clone().readByteString(this.size);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Long.valueOf(this.size);
      arrayOfObject2[1] = localByteString.hex();
      return String.format("OkBuffer[size=%s data=%s]", arrayOfObject2);
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
      for (Segment localSegment = this.head.next; localSegment != this.head; localSegment = localSegment.next)
        localMessageDigest.update(localSegment.data, localSegment.pos, localSegment.limit - localSegment.pos);
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Long.valueOf(this.size);
      arrayOfObject1[1] = ByteString.of(localMessageDigest.digest()).hex();
      String str = String.format("OkBuffer[size=%s md5=%s]", arrayOfObject1);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new AssertionError();
  }

  Segment writableSegment(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 2048))
      throw new IllegalArgumentException();
    Segment localSegment1;
    if (this.head == null)
    {
      this.head = SegmentPool.INSTANCE.take();
      Segment localSegment2 = this.head;
      Segment localSegment3 = this.head;
      localSegment1 = this.head;
      localSegment3.prev = localSegment1;
      localSegment2.next = localSegment1;
    }
    do
    {
      return localSegment1;
      localSegment1 = this.head.prev;
    }
    while (paramInt + localSegment1.limit <= 2048);
    return localSegment1.push(SegmentPool.INSTANCE.take());
  }

  public OkBuffer write(ByteString paramByteString)
  {
    return write(paramByteString.data, 0, paramByteString.data.length);
  }

  public OkBuffer write(byte[] paramArrayOfByte)
  {
    return write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public OkBuffer write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1 + paramInt2;
    while (paramInt1 < i)
    {
      Segment localSegment = writableSegment(1);
      int j = Math.min(i - paramInt1, 2048 - localSegment.limit);
      System.arraycopy(paramArrayOfByte, paramInt1, localSegment.data, localSegment.limit, j);
      paramInt1 += j;
      localSegment.limit = (j + localSegment.limit);
    }
    this.size += paramInt2;
    return this;
  }

  public void write(OkBuffer paramOkBuffer, long paramLong)
  {
    if (paramOkBuffer == this)
      throw new IllegalArgumentException("source == this");
    Util.checkOffsetAndCount(paramOkBuffer.size, 0L, paramLong);
    Segment localSegment5;
    label68: Segment localSegment1;
    long l;
    if (paramLong > 0L)
      if (paramLong < paramOkBuffer.head.limit - paramOkBuffer.head.pos)
      {
        if (this.head != null)
        {
          localSegment5 = this.head.prev;
          if ((localSegment5 != null) && (paramLong + (localSegment5.limit - localSegment5.pos) <= 2048L))
            break label217;
          paramOkBuffer.head = paramOkBuffer.head.split((int)paramLong);
        }
      }
      else
      {
        localSegment1 = paramOkBuffer.head;
        l = localSegment1.limit - localSegment1.pos;
        paramOkBuffer.head = localSegment1.pop();
        if (this.head != null)
          break label249;
        this.head = localSegment1;
        Segment localSegment2 = this.head;
        Segment localSegment3 = this.head;
        Segment localSegment4 = this.head;
        localSegment3.prev = localSegment4;
        localSegment2.next = localSegment4;
      }
    while (true)
    {
      paramOkBuffer.size -= l;
      this.size = (l + this.size);
      paramLong -= l;
      break;
      localSegment5 = null;
      break label68;
      label217: paramOkBuffer.head.writeTo(localSegment5, (int)paramLong);
      paramOkBuffer.size -= paramLong;
      this.size = (paramLong + this.size);
      return;
      label249: this.head.prev.push(localSegment1).compact();
    }
  }

  public OkBuffer writeByte(int paramInt)
  {
    Segment localSegment = writableSegment(1);
    byte[] arrayOfByte = localSegment.data;
    int i = localSegment.limit;
    localSegment.limit = (i + 1);
    arrayOfByte[i] = (byte)paramInt;
    this.size = (1L + this.size);
    return this;
  }

  public OkBuffer writeInt(int paramInt)
  {
    Segment localSegment = writableSegment(4);
    byte[] arrayOfByte = localSegment.data;
    int i = localSegment.limit;
    int j = i + 1;
    arrayOfByte[i] = (byte)(0xFF & paramInt >> 24);
    int k = j + 1;
    arrayOfByte[j] = (byte)(0xFF & paramInt >> 16);
    int m = k + 1;
    arrayOfByte[k] = (byte)(0xFF & paramInt >> 8);
    int n = m + 1;
    arrayOfByte[m] = (byte)(paramInt & 0xFF);
    localSegment.limit = n;
    this.size = (4L + this.size);
    return this;
  }

  public OkBuffer writeShort(int paramInt)
  {
    Segment localSegment = writableSegment(2);
    byte[] arrayOfByte = localSegment.data;
    int i = localSegment.limit;
    int j = i + 1;
    arrayOfByte[i] = (byte)(0xFF & paramInt >> 8);
    int k = j + 1;
    arrayOfByte[j] = (byte)(paramInt & 0xFF);
    localSegment.limit = k;
    this.size = (2L + this.size);
    return this;
  }

  public OkBuffer writeUtf8(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      OkBuffer localOkBuffer = write(arrayOfByte, 0, arrayOfByte.length);
      return localOkBuffer;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError(localUnsupportedEncodingException);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.OkBuffer
 * JD-Core Version:    0.6.0
 */