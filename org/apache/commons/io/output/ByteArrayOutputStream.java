package org.apache.commons.io.output;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.input.ClosedInputStream;

public class ByteArrayOutputStream extends OutputStream
{
  private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  private final List<byte[]> buffers = new ArrayList();
  private int count;
  private byte[] currentBuffer;
  private int currentBufferIndex;
  private int filledBufferSum;

  public ByteArrayOutputStream()
  {
    this(1024);
  }

  public ByteArrayOutputStream(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Negative initial size: " + paramInt);
    monitorenter;
    try
    {
      needNewBuffer(paramInt);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void needNewBuffer(int paramInt)
  {
    if (this.currentBufferIndex < -1 + this.buffers.size())
    {
      this.filledBufferSum += this.currentBuffer.length;
      this.currentBufferIndex = (1 + this.currentBufferIndex);
      this.currentBuffer = ((byte[])this.buffers.get(this.currentBufferIndex));
      return;
    }
    if (this.currentBuffer == null)
      this.filledBufferSum = 0;
    while (true)
    {
      this.currentBufferIndex = (1 + this.currentBufferIndex);
      this.currentBuffer = new byte[paramInt];
      this.buffers.add(this.currentBuffer);
      return;
      paramInt = Math.max(this.currentBuffer.length << 1, paramInt - this.filledBufferSum);
      this.filledBufferSum += this.currentBuffer.length;
    }
  }

  private InputStream toBufferedInputStream()
  {
    int i = this.count;
    if (i == 0)
      return new ClosedInputStream();
    ArrayList localArrayList = new ArrayList(this.buffers.size());
    Iterator localIterator = this.buffers.iterator();
    int m;
    for (int j = i; ; j = m)
      if (localIterator.hasNext())
      {
        byte[] arrayOfByte = (byte[])localIterator.next();
        int k = Math.min(arrayOfByte.length, j);
        localArrayList.add(new ByteArrayInputStream(arrayOfByte, 0, k));
        m = j - k;
        if (m != 0)
          continue;
      }
      else
      {
        return new SequenceInputStream(Collections.enumeration(localArrayList));
      }
  }

  public static InputStream toBufferedInputStream(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localByteArrayOutputStream.write(paramInputStream);
    return localByteArrayOutputStream.toBufferedInputStream();
  }

  public void close()
    throws IOException
  {
  }

  public void reset()
  {
    monitorenter;
    try
    {
      this.count = 0;
      this.filledBufferSum = 0;
      this.currentBufferIndex = 0;
      this.currentBuffer = ((byte[])this.buffers.get(this.currentBufferIndex));
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int size()
  {
    monitorenter;
    try
    {
      int i = this.count;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public byte[] toByteArray()
  {
    monitorenter;
    try
    {
      int i = this.count;
      Object localObject2;
      if (i == 0)
      {
        localObject2 = EMPTY_BYTE_ARRAY;
        return localObject2;
      }
      byte[] arrayOfByte1 = new byte[i];
      Iterator localIterator = this.buffers.iterator();
      int j = i;
      int n;
      for (int k = 0; ; k = n)
      {
        int i1;
        if (localIterator.hasNext())
        {
          byte[] arrayOfByte2 = (byte[])localIterator.next();
          int m = Math.min(arrayOfByte2.length, j);
          System.arraycopy(arrayOfByte2, 0, arrayOfByte1, k, m);
          n = k + m;
          i1 = j - m;
          if (i1 != 0);
        }
        else
        {
          localObject2 = arrayOfByte1;
          break;
        }
        j = i1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public String toString()
  {
    return new String(toByteArray());
  }

  public String toString(String paramString)
    throws UnsupportedEncodingException
  {
    return new String(toByteArray(), paramString);
  }

  public int write(InputStream paramInputStream)
    throws IOException
  {
    monitorenter;
    try
    {
      int i = this.count - this.filledBufferSum;
      int j = paramInputStream.read(this.currentBuffer, i, this.currentBuffer.length - i);
      int k = 0;
      int m = j;
      int n = i;
      while (m != -1)
      {
        k += m;
        n += m;
        this.count = (m + this.count);
        if (n == this.currentBuffer.length)
        {
          needNewBuffer(this.currentBuffer.length);
          n = 0;
        }
        int i1 = paramInputStream.read(this.currentBuffer, n, this.currentBuffer.length - n);
        m = i1;
      }
      return k;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void write(int paramInt)
  {
    monitorenter;
    try
    {
      int i = this.count - this.filledBufferSum;
      if (i == this.currentBuffer.length)
      {
        needNewBuffer(1 + this.count);
        i = 0;
      }
      this.currentBuffer[i] = (byte)paramInt;
      this.count = (1 + this.count);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramArrayOfByte.length) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length) || (paramInt1 + paramInt2 < 0))
      throw new IndexOutOfBoundsException();
    if (paramInt2 == 0)
      return;
    monitorenter;
    try
    {
      int i = paramInt2 + this.count;
      int j = this.count - this.filledBufferSum;
      int k = paramInt2;
      while (k > 0)
      {
        int m = Math.min(k, this.currentBuffer.length - j);
        System.arraycopy(paramArrayOfByte, paramInt1 + paramInt2 - k, this.currentBuffer, j, m);
        k -= m;
        if (k <= 0)
          continue;
        needNewBuffer(i);
        j = 0;
      }
      this.count = i;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    monitorenter;
    try
    {
      int i = this.count;
      Iterator localIterator = this.buffers.iterator();
      int m;
      for (int j = i; ; j = m)
        if (localIterator.hasNext())
        {
          byte[] arrayOfByte = (byte[])localIterator.next();
          int k = Math.min(arrayOfByte.length, j);
          paramOutputStream.write(arrayOfByte, 0, k);
          m = j - k;
          if (m != 0)
            continue;
        }
        else
        {
          return;
        }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.output.ByteArrayOutputStream
 * JD-Core Version:    0.6.0
 */