package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class SpdyConnection
  implements Closeable
{
  private static final ExecutorService executor;
  long bytesLeftInWriteWindow;
  final boolean client;
  private final Set<Integer> currentPushRequests = new LinkedHashSet();
  final FrameReader frameReader;
  final FrameWriter frameWriter;
  private final IncomingStreamHandler handler;
  private final String hostName;
  private long idleStartTimeNs = System.nanoTime();
  private int lastGoodStreamId;
  final long maxFrameSize;
  private int nextPingId;
  private int nextStreamId;
  final Settings okHttpSettings = new Settings();
  final Settings peerSettings = new Settings();
  private Map<Integer, Ping> pings;
  final Protocol protocol;
  private final PushObserver pushObserver;
  final Reader readerRunnable;
  private boolean receivedInitialPeerSettings = false;
  private boolean shutdown;
  private final Map<Integer, SpdyStream> streams = new HashMap();
  long unacknowledgedBytesRead = 0L;

  static
  {
    if (!SpdyConnection.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      executor = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp SpdyConnection", true));
      return;
    }
  }

  private SpdyConnection(Builder paramBuilder)
  {
    this.protocol = paramBuilder.protocol;
    this.pushObserver = paramBuilder.pushObserver;
    this.client = paramBuilder.client;
    this.handler = paramBuilder.handler;
    int j;
    if (paramBuilder.client)
    {
      j = i;
      this.nextStreamId = j;
      if (!paramBuilder.client)
        break label269;
      label120: this.nextPingId = i;
      if (paramBuilder.client)
        this.okHttpSettings.set(7, 0, 16777216);
      this.hostName = paramBuilder.hostName;
      if (this.protocol != Protocol.HTTP_2)
        break label274;
    }
    for (Object localObject = new Http20Draft09(); ; localObject = new Spdy3())
    {
      this.bytesLeftInWriteWindow = this.peerSettings.getInitialWindowSize(65536);
      this.frameReader = ((Variant)localObject).newReader(paramBuilder.source, this.client);
      this.frameWriter = ((Variant)localObject).newWriter(paramBuilder.sink, this.client);
      this.maxFrameSize = ((Variant)localObject).maxFrameSize();
      this.readerRunnable = new Reader(null);
      new Thread(this.readerRunnable).start();
      return;
      j = 2;
      break;
      label269: i = 2;
      break label120;
      label274: if (this.protocol != Protocol.SPDY_3)
        break label296;
    }
    label296: throw new AssertionError(this.protocol);
  }

  // ERROR //
  private void close(ErrorCode paramErrorCode1, ErrorCode paramErrorCode2)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 57	com/squareup/okhttp/internal/spdy/SpdyConnection:$assertionsDisabled	Z
    //   3: ifne +18 -> 21
    //   6: aload_0
    //   7: invokestatic 314	java/lang/Thread:holdsLock	(Ljava/lang/Object;)Z
    //   10: ifeq +11 -> 21
    //   13: new 223	java/lang/AssertionError
    //   16: dup
    //   17: invokespecial 315	java/lang/AssertionError:<init>	()V
    //   20: athrow
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 318	com/squareup/okhttp/internal/spdy/SpdyConnection:shutdown	(Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
    //   26: aconst_null
    //   27: astore 4
    //   29: aload_0
    //   30: monitorenter
    //   31: aload_0
    //   32: getfield 93	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   35: invokeinterface 323 1 0
    //   40: ifne +270 -> 310
    //   43: aload_0
    //   44: getfield 93	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   47: invokeinterface 327 1 0
    //   52: aload_0
    //   53: getfield 93	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   56: invokeinterface 330 1 0
    //   61: anewarray 332	com/squareup/okhttp/internal/spdy/SpdyStream
    //   64: invokeinterface 338 2 0
    //   69: checkcast 340	[Lcom/squareup/okhttp/internal/spdy/SpdyStream;
    //   72: astore 18
    //   74: aload_0
    //   75: getfield 93	com/squareup/okhttp/internal/spdy/SpdyConnection:streams	Ljava/util/Map;
    //   78: invokeinterface 343 1 0
    //   83: aload_0
    //   84: iconst_0
    //   85: invokespecial 347	com/squareup/okhttp/internal/spdy/SpdyConnection:setIdle	(Z)V
    //   88: aload 18
    //   90: astore 6
    //   92: aload_0
    //   93: getfield 349	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   96: ifnull +208 -> 304
    //   99: aload_0
    //   100: getfield 349	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   103: invokeinterface 327 1 0
    //   108: aload_0
    //   109: getfield 349	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   112: invokeinterface 330 1 0
    //   117: anewarray 351	com/squareup/okhttp/internal/spdy/Ping
    //   120: invokeinterface 338 2 0
    //   125: checkcast 353	[Lcom/squareup/okhttp/internal/spdy/Ping;
    //   128: astore 17
    //   130: aload_0
    //   131: aconst_null
    //   132: putfield 349	com/squareup/okhttp/internal/spdy/SpdyConnection:pings	Ljava/util/Map;
    //   135: aload 17
    //   137: astore 7
    //   139: aload_0
    //   140: monitorexit
    //   141: aload 6
    //   143: ifnull +73 -> 216
    //   146: aload 6
    //   148: arraylength
    //   149: istore 12
    //   151: iconst_0
    //   152: istore 13
    //   154: aload 4
    //   156: astore 14
    //   158: iload 13
    //   160: iload 12
    //   162: if_icmpge +50 -> 212
    //   165: aload 6
    //   167: iload 13
    //   169: aaload
    //   170: astore 15
    //   172: aload 15
    //   174: aload_2
    //   175: invokevirtual 355	com/squareup/okhttp/internal/spdy/SpdyStream:close	(Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
    //   178: iinc 13 1
    //   181: goto -23 -> 158
    //   184: astore_3
    //   185: aload_3
    //   186: astore 4
    //   188: goto -159 -> 29
    //   191: astore 5
    //   193: aload_0
    //   194: monitorexit
    //   195: aload 5
    //   197: athrow
    //   198: astore 16
    //   200: aload 14
    //   202: ifnull -24 -> 178
    //   205: aload 16
    //   207: astore 14
    //   209: goto -31 -> 178
    //   212: aload 14
    //   214: astore 4
    //   216: aload 7
    //   218: ifnull +32 -> 250
    //   221: aload 7
    //   223: arraylength
    //   224: istore 10
    //   226: iconst_0
    //   227: istore 11
    //   229: iload 11
    //   231: iload 10
    //   233: if_icmpge +17 -> 250
    //   236: aload 7
    //   238: iload 11
    //   240: aaload
    //   241: invokevirtual 358	com/squareup/okhttp/internal/spdy/Ping:cancel	()V
    //   244: iinc 11 1
    //   247: goto -18 -> 229
    //   250: aload_0
    //   251: getfield 185	com/squareup/okhttp/internal/spdy/SpdyConnection:frameReader	Lcom/squareup/okhttp/internal/spdy/FrameReader;
    //   254: invokeinterface 362 1 0
    //   259: aload_0
    //   260: getfield 195	com/squareup/okhttp/internal/spdy/SpdyConnection:frameWriter	Lcom/squareup/okhttp/internal/spdy/FrameWriter;
    //   263: invokeinterface 365 1 0
    //   268: aload 4
    //   270: astore 9
    //   272: aload 9
    //   274: ifnull +29 -> 303
    //   277: aload 9
    //   279: athrow
    //   280: astore 8
    //   282: aload 8
    //   284: astore 4
    //   286: goto -27 -> 259
    //   289: astore 9
    //   291: aload 4
    //   293: ifnull -21 -> 272
    //   296: aload 4
    //   298: astore 9
    //   300: goto -28 -> 272
    //   303: return
    //   304: aconst_null
    //   305: astore 7
    //   307: goto -168 -> 139
    //   310: aconst_null
    //   311: astore 6
    //   313: goto -221 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   21	26	184	java/io/IOException
    //   31	88	191	finally
    //   92	135	191	finally
    //   139	141	191	finally
    //   193	195	191	finally
    //   172	178	198	java/io/IOException
    //   250	259	280	java/io/IOException
    //   259	268	289	java/io/IOException
  }

  private SpdyStream newStream(int paramInt, List<Header> paramList, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    boolean bool1;
    if (!paramBoolean1)
    {
      bool1 = true;
      if (paramBoolean2)
        break label65;
    }
    label65: for (boolean bool2 = true; ; bool2 = false)
    {
      synchronized (this.frameWriter)
      {
        monitorenter;
        try
        {
          if (!this.shutdown)
            break label71;
          throw new IOException("shutdown");
        }
        finally
        {
          monitorexit;
        }
      }
      bool1 = false;
      break;
    }
    label71: int i = this.nextStreamId;
    this.nextStreamId = (2 + this.nextStreamId);
    SpdyStream localSpdyStream = new SpdyStream(i, this, bool1, bool2, -1, paramList);
    if (localSpdyStream.isOpen())
    {
      this.streams.put(Integer.valueOf(i), localSpdyStream);
      setIdle(false);
    }
    monitorexit;
    if (paramInt == 0)
      this.frameWriter.synStream(bool1, bool2, i, paramInt, -1, 0, paramList);
    while (true)
    {
      monitorexit;
      if (!paramBoolean1)
        this.frameWriter.flush();
      return localSpdyStream;
      if (this.client)
        throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
      this.frameWriter.pushPromise(paramInt, i, paramList);
    }
  }

  private void pushDataLater(int paramInt1, BufferedSource paramBufferedSource, int paramInt2, boolean paramBoolean)
    throws IOException
  {
    OkBuffer localOkBuffer = new OkBuffer();
    paramBufferedSource.require(paramInt2);
    paramBufferedSource.read(localOkBuffer, paramInt2);
    if (localOkBuffer.size() != paramInt2)
      throw new IOException(localOkBuffer.size() + " != " + paramInt2);
    ExecutorService localExecutorService = executor;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.hostName;
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    localExecutorService.submit(new NamedRunnable("OkHttp %s Push Data[%s]", arrayOfObject, paramInt1, localOkBuffer, paramInt2, paramBoolean)
    {
      public void execute()
      {
        try
        {
          boolean bool = SpdyConnection.this.pushObserver.onData(this.val$streamId, this.val$buffer, this.val$byteCount, this.val$inFinished);
          if (bool)
            SpdyConnection.this.frameWriter.rstStream(this.val$streamId, ErrorCode.CANCEL);
          if ((bool) || (this.val$inFinished))
            synchronized (SpdyConnection.this)
            {
              SpdyConnection.this.currentPushRequests.remove(Integer.valueOf(this.val$streamId));
              return;
            }
        }
        catch (IOException localIOException)
        {
        }
      }
    });
  }

  private void pushHeadersLater(int paramInt, List<Header> paramList, boolean paramBoolean)
  {
    ExecutorService localExecutorService = executor;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.hostName;
    arrayOfObject[1] = Integer.valueOf(paramInt);
    localExecutorService.submit(new NamedRunnable("OkHttp %s Push Headers[%s]", arrayOfObject, paramInt, paramList, paramBoolean)
    {
      public void execute()
      {
        boolean bool = SpdyConnection.this.pushObserver.onHeaders(this.val$streamId, this.val$requestHeaders, this.val$inFinished);
        if (bool);
        try
        {
          SpdyConnection.this.frameWriter.rstStream(this.val$streamId, ErrorCode.CANCEL);
          if ((bool) || (this.val$inFinished))
            synchronized (SpdyConnection.this)
            {
              SpdyConnection.this.currentPushRequests.remove(Integer.valueOf(this.val$streamId));
              return;
            }
        }
        catch (IOException localIOException)
        {
        }
      }
    });
  }

  private void pushRequestLater(int paramInt, List<Header> paramList)
  {
    monitorenter;
    try
    {
      if (this.currentPushRequests.contains(Integer.valueOf(paramInt)))
      {
        writeSynResetLater(paramInt, ErrorCode.PROTOCOL_ERROR);
        return;
      }
      this.currentPushRequests.add(Integer.valueOf(paramInt));
      monitorexit;
      ExecutorService localExecutorService = executor;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.hostName;
      arrayOfObject[1] = Integer.valueOf(paramInt);
      localExecutorService.submit(new NamedRunnable("OkHttp %s Push Request[%s]", arrayOfObject, paramInt, paramList)
      {
        public void execute()
        {
          if (SpdyConnection.this.pushObserver.onRequest(this.val$streamId, this.val$requestHeaders))
            try
            {
              SpdyConnection.this.frameWriter.rstStream(this.val$streamId, ErrorCode.CANCEL);
              synchronized (SpdyConnection.this)
              {
                SpdyConnection.this.currentPushRequests.remove(Integer.valueOf(this.val$streamId));
                return;
              }
            }
            catch (IOException localIOException)
            {
            }
        }
      });
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void pushResetLater(int paramInt, ErrorCode paramErrorCode)
  {
    ExecutorService localExecutorService = executor;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.hostName;
    arrayOfObject[1] = Integer.valueOf(paramInt);
    localExecutorService.submit(new NamedRunnable("OkHttp %s Push Reset[%s]", arrayOfObject, paramInt, paramErrorCode)
    {
      public void execute()
      {
        SpdyConnection.this.pushObserver.onReset(this.val$streamId, this.val$errorCode);
        synchronized (SpdyConnection.this)
        {
          SpdyConnection.this.currentPushRequests.remove(Integer.valueOf(this.val$streamId));
          return;
        }
      }
    });
  }

  private boolean pushedStream(int paramInt)
  {
    return (this.protocol == Protocol.HTTP_2) && (paramInt != 0) && ((paramInt & 0x1) == 0);
  }

  private Ping removePing(int paramInt)
  {
    monitorenter;
    try
    {
      if (this.pings != null)
      {
        localPing = (Ping)this.pings.remove(Integer.valueOf(paramInt));
        return localPing;
      }
      Ping localPing = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private void setIdle(boolean paramBoolean)
  {
    monitorenter;
    if (paramBoolean);
    try
    {
      long l = System.nanoTime();
      while (true)
      {
        this.idleStartTimeNs = l;
        return;
        l = 9223372036854775807L;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void writePing(boolean paramBoolean, int paramInt1, int paramInt2, Ping paramPing)
    throws IOException
  {
    FrameWriter localFrameWriter = this.frameWriter;
    monitorenter;
    if (paramPing != null);
    try
    {
      paramPing.send();
      this.frameWriter.ping(paramBoolean, paramInt1, paramInt2);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void writePingLater(boolean paramBoolean, int paramInt1, int paramInt2, Ping paramPing)
  {
    ExecutorService localExecutorService = executor;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.hostName;
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    localExecutorService.submit(new NamedRunnable("OkHttp %s ping %08x%08x", arrayOfObject, paramBoolean, paramInt1, paramInt2, paramPing)
    {
      public void execute()
      {
        try
        {
          SpdyConnection.this.writePing(this.val$reply, this.val$payload1, this.val$payload2, this.val$ping);
          return;
        }
        catch (IOException localIOException)
        {
        }
      }
    });
  }

  void addBytesToWriteWindow(long paramLong)
  {
    this.bytesLeftInWriteWindow = (paramLong + this.bytesLeftInWriteWindow);
    if (paramLong > 0L)
      notifyAll();
  }

  public void close()
    throws IOException
  {
    close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
  }

  public void flush()
    throws IOException
  {
    this.frameWriter.flush();
  }

  public long getIdleStartTimeNs()
  {
    monitorenter;
    try
    {
      long l = this.idleStartTimeNs;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Protocol getProtocol()
  {
    return this.protocol;
  }

  SpdyStream getStream(int paramInt)
  {
    monitorenter;
    try
    {
      SpdyStream localSpdyStream = (SpdyStream)this.streams.get(Integer.valueOf(paramInt));
      monitorexit;
      return localSpdyStream;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isIdle()
  {
    monitorenter;
    try
    {
      long l = this.idleStartTimeNs;
      if (l != 9223372036854775807L)
      {
        i = 1;
        return i;
      }
      int i = 0;
    }
    finally
    {
      monitorexit;
    }
  }

  public SpdyStream newStream(List<Header> paramList, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    return newStream(0, paramList, paramBoolean1, paramBoolean2);
  }

  public int openStreamCount()
  {
    monitorenter;
    try
    {
      int i = this.streams.size();
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

  public Ping ping()
    throws IOException
  {
    Ping localPing = new Ping();
    monitorenter;
    try
    {
      if (this.shutdown)
        throw new IOException("shutdown");
    }
    finally
    {
      monitorexit;
    }
    int i = this.nextPingId;
    this.nextPingId = (2 + this.nextPingId);
    if (this.pings == null)
      this.pings = new HashMap();
    this.pings.put(Integer.valueOf(i), localPing);
    monitorexit;
    writePing(false, i, 1330343787, localPing);
    return localPing;
  }

  public SpdyStream pushStream(int paramInt, List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    if (this.client)
      throw new IllegalStateException("Client cannot push requests.");
    if (this.protocol != Protocol.HTTP_2)
      throw new IllegalStateException("protocol != HTTP_2");
    return newStream(paramInt, paramList, paramBoolean, false);
  }

  SpdyStream removeStream(int paramInt)
  {
    monitorenter;
    try
    {
      SpdyStream localSpdyStream = (SpdyStream)this.streams.remove(Integer.valueOf(paramInt));
      if ((localSpdyStream != null) && (this.streams.isEmpty()))
        setIdle(true);
      return localSpdyStream;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void sendConnectionHeader()
    throws IOException
  {
    this.frameWriter.connectionHeader();
    this.frameWriter.settings(this.okHttpSettings);
  }

  public void shutdown(ErrorCode paramErrorCode)
    throws IOException
  {
    synchronized (this.frameWriter)
    {
      monitorenter;
    }
    try
    {
      if (this.shutdown)
      {
        monitorexit;
        return;
      }
      this.shutdown = true;
      int i = this.lastGoodStreamId;
      monitorexit;
      this.frameWriter.goAway(i, paramErrorCode, Util.EMPTY_BYTE_ARRAY);
      return;
      localObject1 = finally;
      throw localObject1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject2;
  }

  public void writeData(int paramInt, boolean paramBoolean, OkBuffer paramOkBuffer, long paramLong)
    throws IOException
  {
    if (paramLong == 0L)
    {
      this.frameWriter.data(paramBoolean, paramInt, paramOkBuffer, 0);
      return;
    }
    while (true)
    {
      try
      {
        int i = (int)Math.min(Math.min(paramLong, this.bytesLeftInWriteWindow), this.maxFrameSize);
        this.bytesLeftInWriteWindow -= i;
        monitorexit;
        paramLong -= i;
        FrameWriter localFrameWriter = this.frameWriter;
        if ((paramBoolean) && (paramLong == 0L))
        {
          bool = true;
          localFrameWriter.data(bool, paramInt, paramOkBuffer, i);
          if (paramLong <= 0L)
            break;
          monitorenter;
          try
          {
            if (this.bytesLeftInWriteWindow > 0L)
              continue;
            wait();
            continue;
          }
          catch (InterruptedException localInterruptedException)
          {
            throw new InterruptedIOException();
          }
        }
      }
      finally
      {
        monitorexit;
      }
      boolean bool = false;
    }
  }

  void writeSynReply(int paramInt, boolean paramBoolean, List<Header> paramList)
    throws IOException
  {
    this.frameWriter.synReply(paramBoolean, paramInt, paramList);
  }

  void writeSynReset(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    this.frameWriter.rstStream(paramInt, paramErrorCode);
  }

  void writeSynResetLater(int paramInt, ErrorCode paramErrorCode)
  {
    ExecutorService localExecutorService = executor;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.hostName;
    arrayOfObject[1] = Integer.valueOf(paramInt);
    localExecutorService.submit(new NamedRunnable("OkHttp %s stream %d", arrayOfObject, paramInt, paramErrorCode)
    {
      public void execute()
      {
        try
        {
          SpdyConnection.this.writeSynReset(this.val$streamId, this.val$errorCode);
          return;
        }
        catch (IOException localIOException)
        {
        }
      }
    });
  }

  void writeWindowUpdateLater(int paramInt, long paramLong)
  {
    ExecutorService localExecutorService = executor;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.hostName;
    arrayOfObject[1] = Integer.valueOf(paramInt);
    localExecutorService.submit(new NamedRunnable("OkHttp Window Update %s stream %d", arrayOfObject, paramInt, paramLong)
    {
      public void execute()
      {
        try
        {
          SpdyConnection.this.frameWriter.windowUpdate(this.val$streamId, this.val$unacknowledgedBytesRead);
          return;
        }
        catch (IOException localIOException)
        {
        }
      }
    });
  }

  public static class Builder
  {
    private boolean client;
    private IncomingStreamHandler handler = IncomingStreamHandler.REFUSE_INCOMING_STREAMS;
    private String hostName;
    private Protocol protocol = Protocol.SPDY_3;
    private PushObserver pushObserver = PushObserver.CANCEL;
    private BufferedSink sink;
    private BufferedSource source;

    public Builder(String paramString, boolean paramBoolean, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
    {
      this.hostName = paramString;
      this.client = paramBoolean;
      this.source = paramBufferedSource;
      this.sink = paramBufferedSink;
    }

    public Builder(boolean paramBoolean, Socket paramSocket)
      throws IOException
    {
      this("", paramBoolean, Okio.buffer(Okio.source(paramSocket.getInputStream())), Okio.buffer(Okio.sink(paramSocket.getOutputStream())));
    }

    public SpdyConnection build()
    {
      return new SpdyConnection(this, null);
    }

    public Builder handler(IncomingStreamHandler paramIncomingStreamHandler)
    {
      this.handler = paramIncomingStreamHandler;
      return this;
    }

    public Builder protocol(Protocol paramProtocol)
    {
      this.protocol = paramProtocol;
      return this;
    }

    public Builder pushObserver(PushObserver paramPushObserver)
    {
      this.pushObserver = paramPushObserver;
      return this;
    }
  }

  class Reader extends NamedRunnable
    implements FrameReader.Handler
  {
    private Reader()
    {
      super(arrayOfObject);
    }

    private void ackSettingsLater()
    {
      ExecutorService localExecutorService = SpdyConnection.executor;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = SpdyConnection.access$1000(SpdyConnection.this);
      localExecutorService.submit(new NamedRunnable("OkHttp %s ACK Settings", arrayOfObject)
      {
        public void execute()
        {
          try
          {
            SpdyConnection.this.frameWriter.ackSettings();
            return;
          }
          catch (IOException localIOException)
          {
          }
        }
      });
    }

    public void ackSettings()
    {
    }

    public void data(boolean paramBoolean, int paramInt1, BufferedSource paramBufferedSource, int paramInt2)
      throws IOException
    {
      if (SpdyConnection.this.pushedStream(paramInt1))
        SpdyConnection.this.pushDataLater(paramInt1, paramBufferedSource, paramInt2, paramBoolean);
      SpdyStream localSpdyStream;
      do
      {
        return;
        localSpdyStream = SpdyConnection.this.getStream(paramInt1);
        if (localSpdyStream == null)
        {
          SpdyConnection.this.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
          paramBufferedSource.skip(paramInt2);
          return;
        }
        localSpdyStream.receiveData(paramBufferedSource, paramInt2);
      }
      while (!paramBoolean);
      localSpdyStream.receiveFin();
    }

    // ERROR //
    protected void execute()
    {
      // Byte code:
      //   0: getstatic 93	com/squareup/okhttp/internal/spdy/ErrorCode:INTERNAL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   3: astore_1
      //   4: getstatic 93	com/squareup/okhttp/internal/spdy/ErrorCode:INTERNAL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   7: astore_2
      //   8: aload_0
      //   9: getfield 12	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   12: getfield 97	com/squareup/okhttp/internal/spdy/SpdyConnection:client	Z
      //   15: ifne +15 -> 30
      //   18: aload_0
      //   19: getfield 12	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   22: getfield 101	com/squareup/okhttp/internal/spdy/SpdyConnection:frameReader	Lcom/squareup/okhttp/internal/spdy/FrameReader;
      //   25: invokeinterface 106 1 0
      //   30: aload_0
      //   31: getfield 12	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   34: getfield 101	com/squareup/okhttp/internal/spdy/SpdyConnection:frameReader	Lcom/squareup/okhttp/internal/spdy/FrameReader;
      //   37: aload_0
      //   38: invokeinterface 110 2 0
      //   43: ifne -13 -> 30
      //   46: getstatic 113	com/squareup/okhttp/internal/spdy/ErrorCode:NO_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   49: astore_1
      //   50: getstatic 116	com/squareup/okhttp/internal/spdy/ErrorCode:CANCEL	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   53: astore 10
      //   55: aload_0
      //   56: getfield 12	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   59: aload_1
      //   60: aload 10
      //   62: invokestatic 120	com/squareup/okhttp/internal/spdy/SpdyConnection:access$1100	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;Lcom/squareup/okhttp/internal/spdy/ErrorCode;Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
      //   65: return
      //   66: astore 7
      //   68: getstatic 123	com/squareup/okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   71: astore 4
      //   73: getstatic 123	com/squareup/okhttp/internal/spdy/ErrorCode:PROTOCOL_ERROR	Lcom/squareup/okhttp/internal/spdy/ErrorCode;
      //   76: astore 8
      //   78: aload_0
      //   79: getfield 12	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   82: aload 4
      //   84: aload 8
      //   86: invokestatic 120	com/squareup/okhttp/internal/spdy/SpdyConnection:access$1100	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;Lcom/squareup/okhttp/internal/spdy/ErrorCode;Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
      //   89: return
      //   90: astore 9
      //   92: return
      //   93: astore_3
      //   94: aload_1
      //   95: astore 4
      //   97: aload_3
      //   98: astore 5
      //   100: aload_0
      //   101: getfield 12	com/squareup/okhttp/internal/spdy/SpdyConnection$Reader:this$0	Lcom/squareup/okhttp/internal/spdy/SpdyConnection;
      //   104: aload 4
      //   106: aload_2
      //   107: invokestatic 120	com/squareup/okhttp/internal/spdy/SpdyConnection:access$1100	(Lcom/squareup/okhttp/internal/spdy/SpdyConnection;Lcom/squareup/okhttp/internal/spdy/ErrorCode;Lcom/squareup/okhttp/internal/spdy/ErrorCode;)V
      //   110: aload 5
      //   112: athrow
      //   113: astore 6
      //   115: goto -5 -> 110
      //   118: astore 5
      //   120: goto -20 -> 100
      //   123: astore 11
      //   125: return
      //
      // Exception table:
      //   from	to	target	type
      //   8	30	66	java/io/IOException
      //   30	55	66	java/io/IOException
      //   78	89	90	java/io/IOException
      //   8	30	93	finally
      //   30	55	93	finally
      //   68	73	93	finally
      //   100	110	113	java/io/IOException
      //   73	78	118	finally
      //   55	65	123	java/io/IOException
    }

    public void goAway(int paramInt, ErrorCode paramErrorCode, ByteString paramByteString)
    {
      if (paramByteString.size() > 0);
      synchronized (SpdyConnection.this)
      {
        SpdyConnection.access$1502(SpdyConnection.this, true);
        Iterator localIterator = SpdyConnection.this.streams.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if ((((Integer)localEntry.getKey()).intValue() <= paramInt) || (!((SpdyStream)localEntry.getValue()).isLocallyInitiated()))
            continue;
          ((SpdyStream)localEntry.getValue()).receiveRstStream(ErrorCode.REFUSED_STREAM);
          localIterator.remove();
        }
      }
      monitorexit;
    }

    public void headers(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, List<Header> paramList, HeadersMode paramHeadersMode)
    {
      if (SpdyConnection.this.pushedStream(paramInt1))
        SpdyConnection.this.pushHeadersLater(paramInt1, paramList, paramBoolean2);
      SpdyStream localSpdyStream1;
      do
      {
        return;
        synchronized (SpdyConnection.this)
        {
          if (SpdyConnection.this.shutdown)
            return;
        }
        localSpdyStream1 = SpdyConnection.this.getStream(paramInt1);
        if (localSpdyStream1 == null)
        {
          if (paramHeadersMode.failIfStreamAbsent())
          {
            SpdyConnection.this.writeSynResetLater(paramInt1, ErrorCode.INVALID_STREAM);
            monitorexit;
            return;
          }
          if (paramInt1 <= SpdyConnection.this.lastGoodStreamId)
          {
            monitorexit;
            return;
          }
          if (paramInt1 % 2 == SpdyConnection.this.nextStreamId % 2)
          {
            monitorexit;
            return;
          }
          SpdyStream localSpdyStream2 = new SpdyStream(paramInt1, SpdyConnection.this, paramBoolean1, paramBoolean2, paramInt3, paramList);
          SpdyConnection.access$1602(SpdyConnection.this, paramInt1);
          SpdyConnection.this.streams.put(Integer.valueOf(paramInt1), localSpdyStream2);
          ExecutorService localExecutorService = SpdyConnection.executor;
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = SpdyConnection.access$1000(SpdyConnection.this);
          arrayOfObject[1] = Integer.valueOf(paramInt1);
          localExecutorService.submit(new NamedRunnable("OkHttp %s stream %d", arrayOfObject, localSpdyStream2)
          {
            public void execute()
            {
              try
              {
                SpdyConnection.this.handler.receive(this.val$newStream);
                return;
              }
              catch (IOException localIOException)
              {
              }
              throw new RuntimeException(localIOException);
            }
          });
          monitorexit;
          return;
        }
        monitorexit;
        if (paramHeadersMode.failIfStreamPresent())
        {
          localSpdyStream1.closeLater(ErrorCode.PROTOCOL_ERROR);
          SpdyConnection.this.removeStream(paramInt1);
          return;
        }
        localSpdyStream1.receiveHeaders(paramList, paramHeadersMode);
      }
      while (!paramBoolean2);
      localSpdyStream1.receiveFin();
    }

    public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      if (paramBoolean)
      {
        Ping localPing = SpdyConnection.this.removePing(paramInt1);
        if (localPing != null)
          localPing.receive();
        return;
      }
      SpdyConnection.this.writePingLater(true, paramInt1, paramInt2, null);
    }

    public void priority(int paramInt1, int paramInt2)
    {
    }

    public void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
    {
      SpdyConnection.this.pushRequestLater(paramInt2, paramList);
    }

    public void rstStream(int paramInt, ErrorCode paramErrorCode)
    {
      if (SpdyConnection.this.pushedStream(paramInt))
        SpdyConnection.this.pushResetLater(paramInt, paramErrorCode);
      SpdyStream localSpdyStream;
      do
      {
        return;
        localSpdyStream = SpdyConnection.this.removeStream(paramInt);
      }
      while (localSpdyStream == null);
      localSpdyStream.receiveRstStream(paramErrorCode);
    }

    public void settings(boolean paramBoolean, Settings paramSettings)
    {
      while (true)
      {
        synchronized (SpdyConnection.this)
        {
          int i = SpdyConnection.this.peerSettings.getInitialWindowSize(65536);
          if (!paramBoolean)
            continue;
          SpdyConnection.this.peerSettings.clear();
          SpdyConnection.this.peerSettings.merge(paramSettings);
          if (SpdyConnection.this.getProtocol() != Protocol.HTTP_2)
            continue;
          ackSettingsLater();
          int j = SpdyConnection.this.peerSettings.getInitialWindowSize(65536);
          if ((j == -1) || (j == i))
            break label274;
          l = j - i;
          if (SpdyConnection.this.receivedInitialPeerSettings)
            continue;
          SpdyConnection.this.addBytesToWriteWindow(l);
          SpdyConnection.access$2202(SpdyConnection.this, true);
          boolean bool = SpdyConnection.this.streams.isEmpty();
          arrayOfSpdyStream = null;
          if (bool)
            continue;
          arrayOfSpdyStream = (SpdyStream[])SpdyConnection.this.streams.values().toArray(new SpdyStream[SpdyConnection.this.streams.size()]);
          if ((arrayOfSpdyStream != null) && (l != 0L))
          {
            Iterator localIterator = SpdyConnection.this.streams.values().iterator();
            if (localIterator.hasNext())
              synchronized ((SpdyStream)localIterator.next())
              {
                ???.addBytesToWriteWindow(l);
              }
          }
        }
        return;
        label274: long l = 0L;
        SpdyStream[] arrayOfSpdyStream = null;
      }
    }

    public void windowUpdate(int paramInt, long paramLong)
    {
      if (paramInt == 0)
        synchronized (SpdyConnection.this)
        {
          SpdyConnection localSpdyConnection2 = SpdyConnection.this;
          localSpdyConnection2.bytesLeftInWriteWindow = (paramLong + localSpdyConnection2.bytesLeftInWriteWindow);
          SpdyConnection.this.notifyAll();
          return;
        }
      SpdyStream localSpdyStream = SpdyConnection.this.getStream(paramInt);
      if (localSpdyStream != null)
      {
        monitorenter;
        try
        {
          localSpdyStream.addBytesToWriteWindow(paramLong);
          return;
        }
        finally
        {
          monitorexit;
        }
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyConnection
 * JD-Core Version:    0.6.0
 */