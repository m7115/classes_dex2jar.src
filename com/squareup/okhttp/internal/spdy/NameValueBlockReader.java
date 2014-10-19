package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Deadline;
import com.squareup.okhttp.internal.okio.InflaterSource;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

class NameValueBlockReader
{
  private int compressedLimit;
  private final InflaterSource inflaterSource = new InflaterSource(new Source(paramBufferedSource)
  {
    public void close()
      throws IOException
    {
      this.val$source.close();
    }

    public Source deadline(Deadline paramDeadline)
    {
      this.val$source.deadline(paramDeadline);
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (NameValueBlockReader.this.compressedLimit == 0);
      long l;
      do
      {
        return -1L;
        l = this.val$source.read(paramOkBuffer, Math.min(paramLong, NameValueBlockReader.this.compressedLimit));
      }
      while (l == -1L);
      NameValueBlockReader.access$022(NameValueBlockReader.this, l);
      return l;
    }
  }
  , new Inflater()
  {
    public int inflate(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws DataFormatException
    {
      int i = super.inflate(paramArrayOfByte, paramInt1, paramInt2);
      if ((i == 0) && (needsDictionary()))
      {
        setDictionary(Spdy3.DICTIONARY);
        i = super.inflate(paramArrayOfByte, paramInt1, paramInt2);
      }
      return i;
    }
  });
  private final BufferedSource source = Okio.buffer(this.inflaterSource);

  public NameValueBlockReader(BufferedSource paramBufferedSource)
  {
  }

  private void doneReading()
    throws IOException
  {
    if (this.compressedLimit > 0)
    {
      this.inflaterSource.refill();
      if (this.compressedLimit != 0)
        throw new IOException("compressedLimit > 0: " + this.compressedLimit);
    }
  }

  private ByteString readByteString()
    throws IOException
  {
    int i = this.source.readInt();
    return this.source.readByteString(i);
  }

  public void close()
    throws IOException
  {
    this.source.close();
  }

  public List<Header> readNameValueBlock(int paramInt)
    throws IOException
  {
    this.compressedLimit = (paramInt + this.compressedLimit);
    int i = this.source.readInt();
    if (i < 0)
      throw new IOException("numberOfPairs < 0: " + i);
    if (i > 1024)
      throw new IOException("numberOfPairs > 1024: " + i);
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++)
    {
      ByteString localByteString1 = readByteString().toAsciiLowercase();
      ByteString localByteString2 = readByteString();
      if (localByteString1.size() == 0)
        throw new IOException("name.size == 0");
      localArrayList.add(new Header(localByteString1, localByteString2));
    }
    doneReading();
    return localArrayList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.NameValueBlockReader
 * JD-Core Version:    0.6.0
 */