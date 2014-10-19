package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.BitArray;
import com.squareup.okhttp.internal.BitArray.FixedCapacity;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class HpackDraft05
{
  private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX;
  private static final int PREFIX_6_BITS = 63;
  private static final int PREFIX_7_BITS = 127;
  private static final Header[] STATIC_HEADER_TABLE;

  static
  {
    Header[] arrayOfHeader = new Header[60];
    arrayOfHeader[0] = new Header(Header.TARGET_AUTHORITY, "");
    arrayOfHeader[1] = new Header(Header.TARGET_METHOD, "GET");
    arrayOfHeader[2] = new Header(Header.TARGET_METHOD, "POST");
    arrayOfHeader[3] = new Header(Header.TARGET_PATH, "/");
    arrayOfHeader[4] = new Header(Header.TARGET_PATH, "/index.html");
    arrayOfHeader[5] = new Header(Header.TARGET_SCHEME, "http");
    arrayOfHeader[6] = new Header(Header.TARGET_SCHEME, "https");
    arrayOfHeader[7] = new Header(Header.RESPONSE_STATUS, "200");
    arrayOfHeader[8] = new Header(Header.RESPONSE_STATUS, "500");
    arrayOfHeader[9] = new Header(Header.RESPONSE_STATUS, "404");
    arrayOfHeader[10] = new Header(Header.RESPONSE_STATUS, "403");
    arrayOfHeader[11] = new Header(Header.RESPONSE_STATUS, "400");
    arrayOfHeader[12] = new Header(Header.RESPONSE_STATUS, "401");
    arrayOfHeader[13] = new Header("accept-charset", "");
    arrayOfHeader[14] = new Header("accept-encoding", "");
    arrayOfHeader[15] = new Header("accept-language", "");
    arrayOfHeader[16] = new Header("accept-ranges", "");
    arrayOfHeader[17] = new Header("accept", "");
    arrayOfHeader[18] = new Header("access-control-allow-origin", "");
    arrayOfHeader[19] = new Header("age", "");
    arrayOfHeader[20] = new Header("allow", "");
    arrayOfHeader[21] = new Header("authorization", "");
    arrayOfHeader[22] = new Header("cache-control", "");
    arrayOfHeader[23] = new Header("content-disposition", "");
    arrayOfHeader[24] = new Header("content-encoding", "");
    arrayOfHeader[25] = new Header("content-language", "");
    arrayOfHeader[26] = new Header("content-length", "");
    arrayOfHeader[27] = new Header("content-location", "");
    arrayOfHeader[28] = new Header("content-range", "");
    arrayOfHeader[29] = new Header("content-type", "");
    arrayOfHeader[30] = new Header("cookie", "");
    arrayOfHeader[31] = new Header("date", "");
    arrayOfHeader[32] = new Header("etag", "");
    arrayOfHeader[33] = new Header("expect", "");
    arrayOfHeader[34] = new Header("expires", "");
    arrayOfHeader[35] = new Header("from", "");
    arrayOfHeader[36] = new Header("host", "");
    arrayOfHeader[37] = new Header("if-match", "");
    arrayOfHeader[38] = new Header("if-modified-since", "");
    arrayOfHeader[39] = new Header("if-none-match", "");
    arrayOfHeader[40] = new Header("if-range", "");
    arrayOfHeader[41] = new Header("if-unmodified-since", "");
    arrayOfHeader[42] = new Header("last-modified", "");
    arrayOfHeader[43] = new Header("link", "");
    arrayOfHeader[44] = new Header("location", "");
    arrayOfHeader[45] = new Header("max-forwards", "");
    arrayOfHeader[46] = new Header("proxy-authenticate", "");
    arrayOfHeader[47] = new Header("proxy-authorization", "");
    arrayOfHeader[48] = new Header("range", "");
    arrayOfHeader[49] = new Header("referer", "");
    arrayOfHeader[50] = new Header("refresh", "");
    arrayOfHeader[51] = new Header("retry-after", "");
    arrayOfHeader[52] = new Header("server", "");
    arrayOfHeader[53] = new Header("set-cookie", "");
    arrayOfHeader[54] = new Header("strict-transport-security", "");
    arrayOfHeader[55] = new Header("transfer-encoding", "");
    arrayOfHeader[56] = new Header("user-agent", "");
    arrayOfHeader[57] = new Header("vary", "");
    arrayOfHeader[58] = new Header("via", "");
    arrayOfHeader[59] = new Header("www-authenticate", "");
    STATIC_HEADER_TABLE = arrayOfHeader;
    NAME_TO_FIRST_INDEX = nameToFirstIndex();
  }

  private static Map<ByteString, Integer> nameToFirstIndex()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
    for (int i = 0; i < STATIC_HEADER_TABLE.length; i++)
    {
      if (localLinkedHashMap.containsKey(STATIC_HEADER_TABLE[i].name))
        continue;
      localLinkedHashMap.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
    }
    return Collections.unmodifiableMap(localLinkedHashMap);
  }

  static final class Reader
  {
    private final List<Header> emittedHeaders = new ArrayList();
    BitArray emittedReferencedHeaders = new BitArray.FixedCapacity();
    int headerCount = 0;
    Header[] headerTable = new Header[8];
    int headerTableByteCount = 0;
    private final Huffman.Codec huffmanCodec;
    private int maxHeaderTableByteCount;
    int nextHeaderIndex = -1 + this.headerTable.length;
    BitArray referencedHeaders = new BitArray.FixedCapacity();
    private final BufferedSource source;

    Reader(boolean paramBoolean, int paramInt, Source paramSource)
    {
      if (paramBoolean);
      for (Huffman.Codec localCodec = Huffman.Codec.RESPONSE; ; localCodec = Huffman.Codec.REQUEST)
      {
        this.huffmanCodec = localCodec;
        this.maxHeaderTableByteCount = paramInt;
        this.source = Okio.buffer(paramSource);
        return;
      }
    }

    private void clearHeaderTable()
    {
      clearReferenceSet();
      Arrays.fill(this.headerTable, null);
      this.nextHeaderIndex = (-1 + this.headerTable.length);
      this.headerCount = 0;
      this.headerTableByteCount = 0;
    }

    private void clearReferenceSet()
    {
      this.referencedHeaders.clear();
      this.emittedReferencedHeaders.clear();
    }

    private int evictToRecoverBytes(int paramInt)
    {
      int i = 0;
      if (paramInt > 0)
      {
        for (int j = -1 + this.headerTable.length; (j >= this.nextHeaderIndex) && (paramInt > 0); j--)
        {
          paramInt -= this.headerTable[j].hpackSize;
          this.headerTableByteCount -= this.headerTable[j].hpackSize;
          this.headerCount = (-1 + this.headerCount);
          i++;
        }
        this.referencedHeaders.shiftLeft(i);
        this.emittedReferencedHeaders.shiftLeft(i);
        System.arraycopy(this.headerTable, 1 + this.nextHeaderIndex, this.headerTable, i + (1 + this.nextHeaderIndex), this.headerCount);
        this.nextHeaderIndex = (i + this.nextHeaderIndex);
      }
      return i;
    }

    private ByteString getName(int paramInt)
    {
      if (isStaticHeader(paramInt))
        return HpackDraft05.STATIC_HEADER_TABLE[(paramInt - this.headerCount)].name;
      return this.headerTable[headerTableIndex(paramInt)].name;
    }

    private int headerTableIndex(int paramInt)
    {
      return paramInt + (1 + this.nextHeaderIndex);
    }

    private void insertIntoHeaderTable(int paramInt, Header paramHeader)
    {
      int i = paramHeader.hpackSize;
      if (paramInt != -1);
      for (int j = i - this.headerTable[headerTableIndex(paramInt)].hpackSize; ; j = i)
      {
        if (j > this.maxHeaderTableByteCount)
        {
          clearHeaderTable();
          this.emittedHeaders.add(paramHeader);
          return;
        }
        int k = evictToRecoverBytes(j + this.headerTableByteCount - this.maxHeaderTableByteCount);
        if (paramInt == -1)
        {
          if (1 + this.headerCount > this.headerTable.length)
          {
            Header[] arrayOfHeader = new Header[2 * this.headerTable.length];
            System.arraycopy(this.headerTable, 0, arrayOfHeader, this.headerTable.length, this.headerTable.length);
            if (arrayOfHeader.length == 64)
            {
              this.referencedHeaders = ((BitArray.FixedCapacity)this.referencedHeaders).toVariableCapacity();
              this.emittedReferencedHeaders = ((BitArray.FixedCapacity)this.emittedReferencedHeaders).toVariableCapacity();
            }
            this.referencedHeaders.shiftLeft(this.headerTable.length);
            this.emittedReferencedHeaders.shiftLeft(this.headerTable.length);
            this.nextHeaderIndex = (-1 + this.headerTable.length);
            this.headerTable = arrayOfHeader;
          }
          int n = this.nextHeaderIndex;
          this.nextHeaderIndex = (n - 1);
          this.referencedHeaders.set(n);
          this.headerTable[n] = paramHeader;
          this.headerCount = (1 + this.headerCount);
        }
        while (true)
        {
          this.headerTableByteCount = (j + this.headerTableByteCount);
          return;
          int m = paramInt + (k + headerTableIndex(paramInt));
          this.referencedHeaders.set(m);
          this.headerTable[m] = paramHeader;
        }
      }
    }

    private boolean isStaticHeader(int paramInt)
    {
      return paramInt >= this.headerCount;
    }

    private int readByte()
      throws IOException
    {
      return 0xFF & this.source.readByte();
    }

    private void readIndexedHeader(int paramInt)
    {
      if (isStaticHeader(paramInt))
      {
        Header localHeader = HpackDraft05.STATIC_HEADER_TABLE[(paramInt - this.headerCount)];
        if (this.maxHeaderTableByteCount == 0)
        {
          this.emittedHeaders.add(localHeader);
          return;
        }
        insertIntoHeaderTable(-1, localHeader);
        return;
      }
      int i = headerTableIndex(paramInt);
      if (!this.referencedHeaders.get(i))
      {
        this.emittedHeaders.add(this.headerTable[i]);
        this.emittedReferencedHeaders.set(i);
      }
      this.referencedHeaders.toggle(i);
    }

    private void readLiteralHeaderWithIncrementalIndexingIndexedName(int paramInt)
      throws IOException
    {
      insertIntoHeaderTable(-1, new Header(getName(paramInt), readByteString(false)));
    }

    private void readLiteralHeaderWithIncrementalIndexingNewName()
      throws IOException
    {
      insertIntoHeaderTable(-1, new Header(readByteString(true), readByteString(false)));
    }

    private void readLiteralHeaderWithoutIndexingIndexedName(int paramInt)
      throws IOException
    {
      ByteString localByteString1 = getName(paramInt);
      ByteString localByteString2 = readByteString(false);
      this.emittedHeaders.add(new Header(localByteString1, localByteString2));
    }

    private void readLiteralHeaderWithoutIndexingNewName()
      throws IOException
    {
      ByteString localByteString1 = readByteString(true);
      ByteString localByteString2 = readByteString(false);
      this.emittedHeaders.add(new Header(localByteString1, localByteString2));
    }

    void emitReferenceSet()
    {
      for (int i = -1 + this.headerTable.length; i != this.nextHeaderIndex; i--)
      {
        if ((!this.referencedHeaders.get(i)) || (this.emittedReferencedHeaders.get(i)))
          continue;
        this.emittedHeaders.add(this.headerTable[i]);
      }
    }

    List<Header> getAndReset()
    {
      ArrayList localArrayList = new ArrayList(this.emittedHeaders);
      this.emittedHeaders.clear();
      this.emittedReferencedHeaders.clear();
      return localArrayList;
    }

    int maxHeaderTableByteCount()
    {
      return this.maxHeaderTableByteCount;
    }

    void maxHeaderTableByteCount(int paramInt)
    {
      this.maxHeaderTableByteCount = paramInt;
      if (this.maxHeaderTableByteCount < this.headerTableByteCount)
      {
        if (this.maxHeaderTableByteCount == 0)
          clearHeaderTable();
      }
      else
        return;
      evictToRecoverBytes(this.headerTableByteCount - this.maxHeaderTableByteCount);
    }

    ByteString readByteString(boolean paramBoolean)
      throws IOException
    {
      int i = readByte();
      int j;
      ByteString localByteString1;
      if ((i & 0x80) == 128)
      {
        j = 1;
        int k = readInt(i, 127);
        localByteString1 = this.source.readByteString(k);
        if (j == 0)
          break label75;
      }
      label75: for (ByteString localByteString2 = this.huffmanCodec.decode(localByteString1); ; localByteString2 = localByteString1)
      {
        if (paramBoolean)
          localByteString2 = localByteString2.toAsciiLowercase();
        return localByteString2;
        j = 0;
        break;
      }
    }

    void readHeaders()
      throws IOException
    {
      while (!this.source.exhausted())
      {
        int i = 0xFF & this.source.readByte();
        if (i == 128)
        {
          clearReferenceSet();
          continue;
        }
        if ((i & 0x80) == 128)
        {
          readIndexedHeader(-1 + readInt(i, 127));
          continue;
        }
        if (i == 64)
        {
          readLiteralHeaderWithoutIndexingNewName();
          continue;
        }
        if ((i & 0x40) == 64)
        {
          readLiteralHeaderWithoutIndexingIndexedName(-1 + readInt(i, 63));
          continue;
        }
        if (i == 0)
        {
          readLiteralHeaderWithIncrementalIndexingNewName();
          continue;
        }
        if ((i & 0xC0) == 0)
        {
          readLiteralHeaderWithIncrementalIndexingIndexedName(-1 + readInt(i, 63));
          continue;
        }
        throw new AssertionError("unhandled byte: " + Integer.toBinaryString(i));
      }
    }

    int readInt(int paramInt1, int paramInt2)
      throws IOException
    {
      int i = paramInt1 & paramInt2;
      if (i < paramInt2)
        return i;
      int k;
      for (int j = 0; ; j += 7)
      {
        k = readByte();
        if ((k & 0x80) == 0)
          break;
        paramInt2 += ((k & 0x7F) << j);
      }
      return paramInt2 + (k << j);
    }
  }

  static final class Writer
  {
    private final OkBuffer out;

    Writer(OkBuffer paramOkBuffer)
    {
      this.out = paramOkBuffer;
    }

    void writeByteString(ByteString paramByteString)
      throws IOException
    {
      writeInt(paramByteString.size(), 127, 0);
      this.out.write(paramByteString);
    }

    void writeHeaders(List<Header> paramList)
      throws IOException
    {
      int i = paramList.size();
      int j = 0;
      if (j < i)
      {
        ByteString localByteString = ((Header)paramList.get(j)).name;
        Integer localInteger = (Integer)HpackDraft05.NAME_TO_FIRST_INDEX.get(localByteString);
        if (localInteger != null)
        {
          writeInt(1 + localInteger.intValue(), 63, 64);
          writeByteString(((Header)paramList.get(j)).value);
        }
        while (true)
        {
          j++;
          break;
          this.out.writeByte(64);
          writeByteString(localByteString);
          writeByteString(((Header)paramList.get(j)).value);
        }
      }
    }

    void writeInt(int paramInt1, int paramInt2, int paramInt3)
      throws IOException
    {
      if (paramInt1 < paramInt2)
      {
        this.out.writeByte(paramInt3 | paramInt1);
        return;
      }
      this.out.writeByte(paramInt3 | paramInt2);
      int i = paramInt1 - paramInt2;
      while (i >= 128)
      {
        int j = i & 0x7F;
        this.out.writeByte(j | 0x80);
        i >>>= 7;
      }
      this.out.writeByte(i);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.HpackDraft05
 * JD-Core Version:    0.6.0
 */