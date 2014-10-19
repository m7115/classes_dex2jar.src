package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Deadline;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.util.List;

public final class Http20Draft09
  implements Variant
{
  private static final ByteString CONNECTION_HEADER = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
  static final byte FLAG_ACK = 1;
  static final byte FLAG_END_HEADERS = 4;
  static final byte FLAG_END_PUSH_PROMISE = 4;
  static final byte FLAG_END_STREAM = 1;
  static final byte FLAG_NONE = 0;
  static final byte FLAG_PRIORITY = 8;
  static final byte TYPE_CONTINUATION = 10;
  static final byte TYPE_DATA = 0;
  static final byte TYPE_GOAWAY = 7;
  static final byte TYPE_HEADERS = 1;
  static final byte TYPE_PING = 6;
  static final byte TYPE_PRIORITY = 2;
  static final byte TYPE_PUSH_PROMISE = 5;
  static final byte TYPE_RST_STREAM = 3;
  static final byte TYPE_SETTINGS = 4;
  static final byte TYPE_WINDOW_UPDATE = 9;

  private static IllegalArgumentException illegalArgument(String paramString, Object[] paramArrayOfObject)
  {
    throw new IllegalArgumentException(String.format(paramString, paramArrayOfObject));
  }

  private static IOException ioException(String paramString, Object[] paramArrayOfObject)
    throws IOException
  {
    throw new IOException(String.format(paramString, paramArrayOfObject));
  }

  public Protocol getProtocol()
  {
    return Protocol.HTTP_2;
  }

  public int maxFrameSize()
  {
    return 16383;
  }

  public FrameReader newReader(BufferedSource paramBufferedSource, boolean paramBoolean)
  {
    return new Reader(paramBufferedSource, 4096, paramBoolean);
  }

  public FrameWriter newWriter(BufferedSink paramBufferedSink, boolean paramBoolean)
  {
    return new Writer(paramBufferedSink, paramBoolean);
  }

  static final class ContinuationSource
    implements Source
  {
    byte flags;
    int left;
    int length;
    private final BufferedSource source;
    int streamId;

    public ContinuationSource(BufferedSource paramBufferedSource)
    {
      this.source = paramBufferedSource;
    }

    private void readContinuationHeader()
      throws IOException
    {
      int i = this.streamId;
      int j = this.source.readInt();
      int k = this.source.readInt();
      int m = (short)((0x3FFF0000 & j) >> 16);
      this.left = m;
      this.length = m;
      byte b = (byte)((0xFF00 & j) >> 8);
      this.flags = (byte)(j & 0xFF);
      this.streamId = (0x7FFFFFFF & k);
      if (b != 10)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Byte.valueOf(b);
        throw Http20Draft09.access$100("%s != TYPE_CONTINUATION", arrayOfObject);
      }
      if (this.streamId != i)
        throw Http20Draft09.access$100("TYPE_CONTINUATION streamId changed", new Object[0]);
    }

    public void close()
      throws IOException
    {
    }

    public Source deadline(Deadline paramDeadline)
    {
      this.source.deadline(paramDeadline);
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (this.left == 0)
        if ((0x4 & this.flags) == 0);
      long l;
      do
      {
        return -1L;
        readContinuationHeader();
        break;
        l = this.source.read(paramOkBuffer, Math.min(paramLong, this.left));
      }
      while (l == -1L);
      this.left = (int)(this.left - l);
      return l;
    }
  }

  static final class Reader
    implements FrameReader
  {
    private final boolean client;
    private final Http20Draft09.ContinuationSource continuation;
    final HpackDraft05.Reader hpackReader;
    private final BufferedSource source;

    Reader(BufferedSource paramBufferedSource, int paramInt, boolean paramBoolean)
    {
      this.source = paramBufferedSource;
      this.client = paramBoolean;
      this.continuation = new Http20Draft09.ContinuationSource(this.source);
      this.hpackReader = new HpackDraft05.Reader(paramBoolean, paramInt, this.continuation);
    }

    private void readData(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if ((paramByte & 0x1) != 0);
      for (boolean bool = true; ; bool = false)
      {
        paramHandler.data(bool, paramInt, this.source, paramShort);
        return;
      }
    }

    private void readGoAway(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramShort < 8)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Short.valueOf(paramShort);
        throw Http20Draft09.access$100("TYPE_GOAWAY length < 8: %s", arrayOfObject2);
      }
      if (paramInt != 0)
        throw Http20Draft09.access$100("TYPE_GOAWAY streamId != 0", new Object[0]);
      int i = this.source.readInt();
      int j = this.source.readInt();
      int k = paramShort - 8;
      ErrorCode localErrorCode = ErrorCode.fromHttp2(j);
      if (localErrorCode == null)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(j);
        throw Http20Draft09.access$100("TYPE_GOAWAY unexpected error code: %d", arrayOfObject1);
      }
      ByteString localByteString = ByteString.EMPTY;
      if (k > 0)
        localByteString = this.source.readByteString(k);
      paramHandler.goAway(i, localErrorCode, localByteString);
    }

    private List<Header> readHeaderBlock(short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      Http20Draft09.ContinuationSource localContinuationSource = this.continuation;
      this.continuation.left = paramShort;
      localContinuationSource.length = paramShort;
      this.continuation.flags = paramByte;
      this.continuation.streamId = paramInt;
      this.hpackReader.readHeaders();
      this.hpackReader.emitReferenceSet();
      return this.hpackReader.getAndReset();
    }

    private void readHeaders(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramInt == 0)
        throw Http20Draft09.access$100("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
      boolean bool;
      int i;
      if ((paramByte & 0x1) != 0)
      {
        bool = true;
        if ((paramByte & 0x8) == 0)
          break label82;
        i = 0x7FFFFFFF & this.source.readInt();
        paramShort = (short)(paramShort - 4);
      }
      while (true)
      {
        paramHandler.headers(false, bool, paramInt, -1, i, readHeaderBlock(paramShort, paramByte, paramInt), HeadersMode.HTTP_20_HEADERS);
        return;
        bool = false;
        break;
        label82: i = -1;
      }
    }

    private void readPing(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      boolean bool = true;
      if (paramShort != 8)
      {
        Object[] arrayOfObject = new Object[bool];
        arrayOfObject[0] = Short.valueOf(paramShort);
        throw Http20Draft09.access$100("TYPE_PING length != 8: %s", arrayOfObject);
      }
      if (paramInt != 0)
        throw Http20Draft09.access$100("TYPE_PING streamId != 0", new Object[0]);
      int i = this.source.readInt();
      int j = this.source.readInt();
      if ((paramByte & 0x1) != 0);
      while (true)
      {
        paramHandler.ping(bool, i, j);
        return;
        bool = false;
      }
    }

    private void readPriority(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramShort != 4)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Short.valueOf(paramShort);
        throw Http20Draft09.access$100("TYPE_PRIORITY length: %d != 4", arrayOfObject);
      }
      if (paramInt == 0)
        throw Http20Draft09.access$100("TYPE_PRIORITY streamId == 0", new Object[0]);
      paramHandler.priority(paramInt, 0x7FFFFFFF & this.source.readInt());
    }

    private void readPushPromise(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramInt == 0)
        throw Http20Draft09.access$100("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
      paramHandler.pushPromise(paramInt, 0x7FFFFFFF & this.source.readInt(), readHeaderBlock((short)(paramShort - 4), paramByte, paramInt));
    }

    private void readRstStream(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramShort != 4)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Short.valueOf(paramShort);
        throw Http20Draft09.access$100("TYPE_RST_STREAM length: %d != 4", arrayOfObject2);
      }
      if (paramInt == 0)
        throw Http20Draft09.access$100("TYPE_RST_STREAM streamId == 0", new Object[0]);
      int i = this.source.readInt();
      ErrorCode localErrorCode = ErrorCode.fromHttp2(i);
      if (localErrorCode == null)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(i);
        throw Http20Draft09.access$100("TYPE_RST_STREAM unexpected error code: %d", arrayOfObject1);
      }
      paramHandler.rstStream(paramInt, localErrorCode);
    }

    private void readSettings(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramInt != 0)
        throw Http20Draft09.access$100("TYPE_SETTINGS streamId != 0", new Object[0]);
      if ((paramByte & 0x1) != 0)
      {
        if (paramShort != 0)
          throw Http20Draft09.access$100("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
        paramHandler.ackSettings();
      }
      Settings localSettings;
      do
      {
        return;
        if (paramShort % 8 != 0)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Short.valueOf(paramShort);
          throw Http20Draft09.access$100("TYPE_SETTINGS length %% 8 != 0: %s", arrayOfObject);
        }
        localSettings = new Settings();
        for (short s = 0; s < paramShort; s += 8)
        {
          int i = this.source.readInt();
          int j = this.source.readInt();
          localSettings.set(i & 0xFFFFFF, 0, j);
        }
        paramHandler.settings(false, localSettings);
      }
      while (localSettings.getHeaderTableSize() < 0);
      this.hpackReader.maxHeaderTableByteCount(localSettings.getHeaderTableSize());
    }

    private void readWindowUpdate(FrameReader.Handler paramHandler, short paramShort, byte paramByte, int paramInt)
      throws IOException
    {
      if (paramShort != 4)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Short.valueOf(paramShort);
        throw Http20Draft09.access$100("TYPE_WINDOW_UPDATE length !=4: %s", arrayOfObject2);
      }
      long l = 0x7FFFFFFF & this.source.readInt();
      if (l == 0L)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Long.valueOf(l);
        throw Http20Draft09.access$100("windowSizeIncrement was 0", arrayOfObject1);
      }
      paramHandler.windowUpdate(paramInt, l);
    }

    public void close()
      throws IOException
    {
      this.source.close();
    }

    public boolean nextFrame(FrameReader.Handler paramHandler)
      throws IOException
    {
      while (true)
      {
        int k;
        byte b;
        int n;
        try
        {
          int i = this.source.readInt();
          int j = this.source.readInt();
          k = (short)((0x3FFF0000 & i) >> 16);
          int m = (byte)((0xFF00 & i) >> 8);
          b = (byte)(i & 0xFF);
          n = j & 0x7FFFFFFF;
          switch (m)
          {
          case 8:
          default:
            this.source.skip(k);
            return true;
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          case 7:
          case 9:
          }
        }
        catch (IOException localIOException)
        {
          return false;
        }
        readData(paramHandler, k, b, n);
        continue;
        readHeaders(paramHandler, k, b, n);
        continue;
        readPriority(paramHandler, k, b, n);
        continue;
        readRstStream(paramHandler, k, b, n);
        continue;
        readSettings(paramHandler, k, b, n);
        continue;
        readPushPromise(paramHandler, k, b, n);
        continue;
        readPing(paramHandler, k, b, n);
        continue;
        readGoAway(paramHandler, k, b, n);
        continue;
        readWindowUpdate(paramHandler, k, b, n);
      }
    }

    public void readConnectionHeader()
      throws IOException
    {
      if (this.client);
      ByteString localByteString;
      do
      {
        return;
        localByteString = this.source.readByteString(Http20Draft09.CONNECTION_HEADER.size());
      }
      while (Http20Draft09.CONNECTION_HEADER.equals(localByteString));
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localByteString.utf8();
      throw Http20Draft09.access$100("Expected a connection header but was %s", arrayOfObject);
    }
  }

  static final class Writer
    implements FrameWriter
  {
    private final boolean client;
    private boolean closed;
    private final OkBuffer hpackBuffer;
    private final HpackDraft05.Writer hpackWriter;
    private final BufferedSink sink;

    Writer(BufferedSink paramBufferedSink, boolean paramBoolean)
    {
      this.sink = paramBufferedSink;
      this.client = paramBoolean;
      this.hpackBuffer = new OkBuffer();
      this.hpackWriter = new HpackDraft05.Writer(this.hpackBuffer);
    }

    private void headers(boolean paramBoolean, int paramInt1, int paramInt2, List<Header> paramList)
      throws IOException
    {
      if (this.closed)
        throw new IOException("closed");
      if (this.hpackBuffer.size() != 0L)
        throw new IllegalStateException();
      this.hpackWriter.writeHeaders(paramList);
      int i = (int)this.hpackBuffer.size();
      byte b = 4;
      if (paramBoolean)
        b = 5;
      if (paramInt2 != -1)
        b = (byte)(b | 0x8);
      if (paramInt2 != -1)
        i += 4;
      frameHeader(i, 1, b, paramInt1);
      if (paramInt2 != -1)
        this.sink.writeInt(0x7FFFFFFF & paramInt2);
      this.sink.write(this.hpackBuffer, this.hpackBuffer.size());
    }

    public void ackSettings()
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      frameHeader(0, 4, 1, 0);
      this.sink.flush();
      monitorexit;
    }

    public void close()
      throws IOException
    {
      monitorenter;
      try
      {
        this.closed = true;
        this.sink.close();
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

    public void connectionHeader()
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      boolean bool = this.client;
      if (!bool);
      while (true)
      {
        monitorexit;
        return;
        this.sink.write(Http20Draft09.CONNECTION_HEADER.toByteArray());
        this.sink.flush();
      }
    }

    public void data(boolean paramBoolean, int paramInt, OkBuffer paramOkBuffer)
      throws IOException
    {
      monitorenter;
      try
      {
        data(paramBoolean, paramInt, paramOkBuffer, (int)paramOkBuffer.size());
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

    public void data(boolean paramBoolean, int paramInt1, OkBuffer paramOkBuffer, int paramInt2)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      byte b = 0;
      if (paramBoolean)
        b = (byte)1;
      dataFrame(paramInt1, b, paramOkBuffer, paramInt2);
      monitorexit;
    }

    void dataFrame(int paramInt1, byte paramByte, OkBuffer paramOkBuffer, int paramInt2)
      throws IOException
    {
      frameHeader(paramInt2, 0, paramByte, paramInt1);
      if (paramInt2 > 0)
        this.sink.write(paramOkBuffer, paramInt2);
    }

    public void flush()
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      this.sink.flush();
      monitorexit;
    }

    void frameHeader(int paramInt1, byte paramByte1, byte paramByte2, int paramInt2)
      throws IOException
    {
      if (paramInt1 > 16383)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(paramInt1);
        throw Http20Draft09.access$200("FRAME_SIZE_ERROR length > 16383: %s", arrayOfObject2);
      }
      if ((0x80000000 & paramInt2) != 0)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(paramInt2);
        throw Http20Draft09.access$200("reserved bit set: %s", arrayOfObject1);
      }
      this.sink.writeInt((paramInt1 & 0x3FFF) << 16 | (paramByte1 & 0xFF) << 8 | paramByte2 & 0xFF);
      this.sink.writeInt(0x7FFFFFFF & paramInt2);
    }

    public void goAway(int paramInt, ErrorCode paramErrorCode, byte[] paramArrayOfByte)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      if (paramErrorCode.httpCode == -1)
        throw Http20Draft09.access$200("errorCode.httpCode == -1", new Object[0]);
      frameHeader(8 + paramArrayOfByte.length, 7, 0, 0);
      this.sink.writeInt(paramInt);
      this.sink.writeInt(paramErrorCode.httpCode);
      if (paramArrayOfByte.length > 0)
        this.sink.write(paramArrayOfByte);
      this.sink.flush();
      monitorexit;
    }

    public void headers(int paramInt, List<Header> paramList)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      headers(false, paramInt, -1, paramList);
      monitorexit;
    }

    public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      byte b = 0;
      if (paramBoolean)
        b = 1;
      frameHeader(8, 6, b, 0);
      this.sink.writeInt(paramInt1);
      this.sink.writeInt(paramInt2);
      this.sink.flush();
      monitorexit;
    }

    public void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      if (this.hpackBuffer.size() != 0L)
        throw new IllegalStateException();
      this.hpackWriter.writeHeaders(paramList);
      frameHeader((int)(4L + this.hpackBuffer.size()), 5, 4, paramInt1);
      this.sink.writeInt(0x7FFFFFFF & paramInt2);
      this.sink.write(this.hpackBuffer, this.hpackBuffer.size());
      monitorexit;
    }

    public void rstStream(int paramInt, ErrorCode paramErrorCode)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      if (paramErrorCode.spdyRstCode == -1)
        throw new IllegalArgumentException();
      frameHeader(4, 3, 0, paramInt);
      this.sink.writeInt(paramErrorCode.httpCode);
      this.sink.flush();
      monitorexit;
    }

    public void settings(Settings paramSettings)
      throws IOException
    {
      int i = 0;
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      frameHeader(8 * paramSettings.size(), 4, 0, 0);
      while (true)
      {
        if (i < 10)
        {
          if (paramSettings.isSet(i))
          {
            this.sink.writeInt(0xFFFFFF & i);
            this.sink.writeInt(paramSettings.get(i));
          }
        }
        else
        {
          this.sink.flush();
          monitorexit;
          return;
        }
        i++;
      }
    }

    public void synReply(boolean paramBoolean, int paramInt, List<Header> paramList)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      headers(paramBoolean, paramInt, -1, paramList);
      monitorexit;
    }

    public void synStream(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<Header> paramList)
      throws IOException
    {
      monitorenter;
      if (paramBoolean2)
        try
        {
          throw new UnsupportedOperationException();
        }
        finally
        {
          monitorexit;
        }
      if (this.closed)
        throw new IOException("closed");
      headers(paramBoolean1, paramInt1, paramInt3, paramList);
      monitorexit;
    }

    public void windowUpdate(int paramInt, long paramLong)
      throws IOException
    {
      monitorenter;
      try
      {
        if (this.closed)
          throw new IOException("closed");
      }
      finally
      {
        monitorexit;
      }
      if ((paramLong == 0L) || (paramLong > 2147483647L))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Long.valueOf(paramLong);
        throw Http20Draft09.access$200("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", arrayOfObject);
      }
      frameHeader(4, 9, 0, paramInt);
      this.sink.writeInt((int)paramLong);
      this.sink.flush();
      monitorexit;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Http20Draft09
 * JD-Core Version:    0.6.0
 */