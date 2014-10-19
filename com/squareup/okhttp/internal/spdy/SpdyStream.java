package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.Deadline;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public final class SpdyStream
{
  long bytesLeftInWriteWindow;
  private final SpdyConnection connection;
  private ErrorCode errorCode = null;
  private final int id;
  private final int priority;
  private long readTimeoutMillis = 0L;
  private final List<Header> requestHeaders;
  private List<Header> responseHeaders;
  final SpdyDataSink sink;
  private final SpdyDataSource source;
  long unacknowledgedBytesRead = 0L;

  static
  {
    if (!SpdyStream.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }

  SpdyStream(int paramInt1, SpdyConnection paramSpdyConnection, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, List<Header> paramList)
  {
    if (paramSpdyConnection == null)
      throw new NullPointerException("connection == null");
    if (paramList == null)
      throw new NullPointerException("requestHeaders == null");
    this.id = paramInt1;
    this.connection = paramSpdyConnection;
    this.bytesLeftInWriteWindow = paramSpdyConnection.peerSettings.getInitialWindowSize(65536);
    this.source = new SpdyDataSource(paramSpdyConnection.okHttpSettings.getInitialWindowSize(65536), null);
    this.sink = new SpdyDataSink();
    SpdyDataSource.access$102(this.source, paramBoolean2);
    SpdyDataSink.access$202(this.sink, paramBoolean1);
    this.priority = paramInt2;
    this.requestHeaders = paramList;
  }

  private void cancelStreamIfNecessary()
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    monitorenter;
    while (true)
    {
      boolean bool;
      try
      {
        if ((this.source.finished) || (!this.source.closed))
          continue;
        if (this.sink.finished)
          break label112;
        if (!this.sink.closed)
          continue;
        break label112;
        bool = isOpen();
        monitorexit;
        if (i != 0)
        {
          close(ErrorCode.CANCEL);
          return;
          i = 0;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (bool)
        continue;
      this.connection.removeStream(this.id);
      return;
      label112: int i = 1;
    }
  }

  private void checkOutNotClosed()
    throws IOException
  {
    if (this.sink.closed)
      throw new IOException("stream closed");
    if (this.sink.finished)
      throw new IOException("stream finished");
    if (this.errorCode != null)
      throw new IOException("stream was reset: " + this.errorCode);
  }

  private boolean closeInternal(ErrorCode paramErrorCode)
  {
    assert (!Thread.holdsLock(this));
    monitorenter;
    try
    {
      if (this.errorCode != null)
        return false;
      if ((this.source.finished) && (this.sink.finished))
        return false;
    }
    finally
    {
      monitorexit;
    }
    this.errorCode = paramErrorCode;
    notifyAll();
    monitorexit;
    this.connection.removeStream(this.id);
    return true;
  }

  void addBytesToWriteWindow(long paramLong)
  {
    this.bytesLeftInWriteWindow = (paramLong + this.bytesLeftInWriteWindow);
    if (paramLong > 0L)
      notifyAll();
  }

  public void close(ErrorCode paramErrorCode)
    throws IOException
  {
    if (!closeInternal(paramErrorCode))
      return;
    this.connection.writeSynReset(this.id, paramErrorCode);
  }

  public void closeLater(ErrorCode paramErrorCode)
  {
    if (!closeInternal(paramErrorCode))
      return;
    this.connection.writeSynResetLater(this.id, paramErrorCode);
  }

  public SpdyConnection getConnection()
  {
    return this.connection;
  }

  public ErrorCode getErrorCode()
  {
    monitorenter;
    try
    {
      ErrorCode localErrorCode = this.errorCode;
      monitorexit;
      return localErrorCode;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public int getId()
  {
    return this.id;
  }

  int getPriority()
  {
    return this.priority;
  }

  public long getReadTimeoutMillis()
  {
    return this.readTimeoutMillis;
  }

  public List<Header> getRequestHeaders()
  {
    return this.requestHeaders;
  }

  public List<Header> getResponseHeaders()
    throws IOException
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.readTimeoutMillis == 0L)
          break label192;
        l1 = System.nanoTime() / 1000000L;
        l2 = this.readTimeoutMillis;
        try
        {
          if ((this.responseHeaders != null) || (this.errorCode != null))
            break label144;
          if (this.readTimeoutMillis == 0L)
          {
            wait();
            continue;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          InterruptedIOException localInterruptedIOException = new InterruptedIOException();
          localInterruptedIOException.initCause(localInterruptedException);
          throw localInterruptedIOException;
        }
      }
      finally
      {
        monitorexit;
      }
      if (l2 > 0L)
      {
        wait(l2);
        l2 = l1 + this.readTimeoutMillis - System.nanoTime() / 1000000L;
        continue;
      }
      throw new SocketTimeoutException("Read response header timeout. readTimeoutMillis: " + this.readTimeoutMillis);
      label144: if (this.responseHeaders != null)
      {
        List localList = this.responseHeaders;
        monitorexit;
        return localList;
      }
      throw new IOException("stream was reset: " + this.errorCode);
      label192: long l1 = 0L;
      long l2 = 0L;
    }
  }

  public Sink getSink()
  {
    monitorenter;
    try
    {
      if ((this.responseHeaders == null) && (!isLocallyInitiated()))
        throw new IllegalStateException("reply before requesting the sink");
    }
    finally
    {
      monitorexit;
    }
    monitorexit;
    return this.sink;
  }

  public Source getSource()
  {
    return this.source;
  }

  public boolean isLocallyInitiated()
  {
    if ((0x1 & this.id) == 1);
    for (int i = 1; this.connection.client == i; i = 0)
      return true;
    return false;
  }

  public boolean isOpen()
  {
    monitorenter;
    try
    {
      ErrorCode localErrorCode = this.errorCode;
      int i = 0;
      if (localErrorCode != null);
      while (true)
      {
        return i;
        if (((this.source.finished) || (this.source.closed)) && ((this.sink.finished) || (this.sink.closed)))
        {
          List localList = this.responseHeaders;
          i = 0;
          if (localList != null)
            continue;
        }
        i = 1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void receiveData(BufferedSource paramBufferedSource, int paramInt)
    throws IOException
  {
    assert (!Thread.holdsLock(this));
    this.source.receive(paramBufferedSource, paramInt);
  }

  void receiveFin()
  {
    assert (!Thread.holdsLock(this));
    monitorenter;
    try
    {
      SpdyDataSource.access$102(this.source, true);
      boolean bool = isOpen();
      notifyAll();
      monitorexit;
      if (!bool)
        this.connection.removeStream(this.id);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void receiveHeaders(List<Header> paramList, HeadersMode paramHeadersMode)
  {
    assert (!Thread.holdsLock(this));
    boolean bool = true;
    monitorenter;
    do
      while (true)
      {
        try
        {
          if (this.responseHeaders == null)
          {
            if (!paramHeadersMode.failIfHeadersAbsent())
              continue;
            localErrorCode = ErrorCode.PROTOCOL_ERROR;
            monitorexit;
            if (localErrorCode == null)
              break;
            closeLater(localErrorCode);
            return;
            this.responseHeaders = paramList;
            bool = isOpen();
            notifyAll();
            localErrorCode = null;
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        if (paramHeadersMode.failIfHeadersPresent())
        {
          localErrorCode = ErrorCode.STREAM_IN_USE;
          continue;
        }
        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(this.responseHeaders);
        localArrayList.addAll(paramList);
        this.responseHeaders = localArrayList;
        ErrorCode localErrorCode = null;
      }
    while (bool);
    this.connection.removeStream(this.id);
  }

  void receiveRstStream(ErrorCode paramErrorCode)
  {
    monitorenter;
    try
    {
      if (this.errorCode == null)
      {
        this.errorCode = paramErrorCode;
        notifyAll();
      }
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

  public void reply(List<Header> paramList, boolean paramBoolean)
    throws IOException
  {
    boolean bool = true;
    assert (!Thread.holdsLock(this));
    monitorenter;
    if (paramList == null)
      try
      {
        throw new NullPointerException("responseHeaders == null");
      }
      finally
      {
        monitorexit;
      }
    if (this.responseHeaders != null)
      throw new IllegalStateException("reply already sent");
    this.responseHeaders = paramList;
    if (!paramBoolean)
      SpdyDataSink.access$202(this.sink, true);
    while (true)
    {
      monitorexit;
      this.connection.writeSynReply(this.id, bool, paramList);
      if (bool)
        this.connection.flush();
      return;
      bool = false;
    }
  }

  public void setReadTimeout(long paramLong)
  {
    this.readTimeoutMillis = paramLong;
  }

  final class SpdyDataSink
    implements Sink
  {
    private boolean closed;
    private boolean finished;

    static
    {
      if (!SpdyStream.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    SpdyDataSink()
    {
    }

    public void close()
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      synchronized (SpdyStream.this)
      {
        if (this.closed)
          return;
        if (!SpdyStream.this.sink.finished)
          SpdyStream.this.connection.writeData(SpdyStream.this.id, true, null, 0L);
      }
      synchronized (SpdyStream.this)
      {
        this.closed = true;
        SpdyStream.this.connection.flush();
        SpdyStream.this.cancelStreamIfNecessary();
        return;
        localObject1 = finally;
        monitorexit;
        throw localObject1;
      }
    }

    public Sink deadline(Deadline paramDeadline)
    {
      return this;
    }

    public void flush()
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      synchronized (SpdyStream.this)
      {
        SpdyStream.this.checkOutNotClosed();
        SpdyStream.this.connection.flush();
        return;
      }
    }

    public void write(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      assert (!Thread.holdsLock(SpdyStream.this));
      try
      {
        while (true)
        {
          SpdyStream.this.checkOutNotClosed();
          long l = Math.min(SpdyStream.this.bytesLeftInWriteWindow, paramLong);
          SpdyStream localSpdyStream2 = SpdyStream.this;
          localSpdyStream2.bytesLeftInWriteWindow -= l;
          monitorexit;
          paramLong -= l;
          SpdyStream.this.connection.writeData(SpdyStream.this.id, false, paramOkBuffer, l);
          if (paramLong > 0L)
          {
            localSpdyStream1 = SpdyStream.this;
            monitorenter;
            try
            {
              while (SpdyStream.this.bytesLeftInWriteWindow <= 0L)
                SpdyStream.this.wait();
            }
            catch (InterruptedException localInterruptedException)
            {
              throw new InterruptedIOException();
            }
          }
        }
      }
      finally
      {
        SpdyStream localSpdyStream1;
        monitorexit;
      }
    }
  }

  private final class SpdyDataSource
    implements Source
  {
    private boolean closed;
    private boolean finished;
    private final long maxByteCount;
    private final OkBuffer readBuffer = new OkBuffer();
    private final OkBuffer receiveBuffer = new OkBuffer();

    static
    {
      if (!SpdyStream.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    private SpdyDataSource(long arg2)
    {
      Object localObject;
      this.maxByteCount = localObject;
    }

    private void checkNotClosed()
      throws IOException
    {
      if (this.closed)
        throw new IOException("stream closed");
      if (SpdyStream.this.errorCode != null)
        throw new IOException("stream was reset: " + SpdyStream.this.errorCode);
    }

    private void waitUntilReadable()
      throws IOException
    {
      long l2;
      long l1;
      if (SpdyStream.this.readTimeoutMillis != 0L)
      {
        l2 = System.nanoTime() / 1000000L;
        l1 = SpdyStream.this.readTimeoutMillis;
      }
      while (true)
      {
        try
        {
          if ((this.readBuffer.size() != 0L) || (this.finished) || (this.closed) || (SpdyStream.this.errorCode != null))
            break label141;
          if (SpdyStream.this.readTimeoutMillis == 0L)
          {
            SpdyStream.this.wait();
            continue;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          throw new InterruptedIOException();
        }
        if (l1 > 0L)
        {
          SpdyStream.this.wait(l1);
          l1 = l2 + SpdyStream.this.readTimeoutMillis - System.nanoTime() / 1000000L;
          continue;
        }
        throw new SocketTimeoutException("Read timed out");
        label141: return;
        l1 = 0L;
        l2 = 0L;
      }
    }

    public void close()
      throws IOException
    {
      synchronized (SpdyStream.this)
      {
        this.closed = true;
        this.readBuffer.clear();
        SpdyStream.this.notifyAll();
        SpdyStream.this.cancelStreamIfNecessary();
        return;
      }
    }

    public Source deadline(Deadline paramDeadline)
    {
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      synchronized (SpdyStream.this)
      {
        waitUntilReadable();
        checkNotClosed();
        if (this.readBuffer.size() == 0L)
          return -1L;
        long l = this.readBuffer.read(paramOkBuffer, Math.min(paramLong, this.readBuffer.size()));
        SpdyStream localSpdyStream2 = SpdyStream.this;
        localSpdyStream2.unacknowledgedBytesRead = (l + localSpdyStream2.unacknowledgedBytesRead);
        if (SpdyStream.this.unacknowledgedBytesRead >= SpdyStream.this.connection.peerSettings.getInitialWindowSize(65536) / 2)
        {
          SpdyStream.this.connection.writeWindowUpdateLater(SpdyStream.this.id, SpdyStream.this.unacknowledgedBytesRead);
          SpdyStream.this.unacknowledgedBytesRead = 0L;
        }
        synchronized (SpdyStream.this.connection)
        {
          SpdyConnection localSpdyConnection2 = SpdyStream.this.connection;
          localSpdyConnection2.unacknowledgedBytesRead = (l + localSpdyConnection2.unacknowledgedBytesRead);
          if (SpdyStream.this.connection.unacknowledgedBytesRead >= SpdyStream.this.connection.peerSettings.getInitialWindowSize(65536) / 2)
          {
            SpdyStream.this.connection.writeWindowUpdateLater(0, SpdyStream.this.connection.unacknowledgedBytesRead);
            SpdyStream.this.connection.unacknowledgedBytesRead = 0L;
          }
          return l;
        }
      }
    }

    void receive(BufferedSource paramBufferedSource, long paramLong)
      throws IOException
    {
      if ((!$assertionsDisabled) && (Thread.holdsLock(SpdyStream.this)))
        throw new AssertionError();
      while (true)
      {
        Object localObject2;
        paramLong -= localObject2;
        synchronized (SpdyStream.this)
        {
          if (this.readBuffer.size() == 0L)
          {
            j = 1;
            this.readBuffer.write(this.receiveBuffer, this.receiveBuffer.size());
            if (j != 0)
              SpdyStream.this.notifyAll();
            if (paramLong > 0L);
            boolean bool;
            synchronized (SpdyStream.this)
            {
              bool = this.finished;
              int i;
              if (paramLong + this.readBuffer.size() > this.maxByteCount)
              {
                i = 1;
                if (i != 0)
                {
                  paramBufferedSource.skip(paramLong);
                  SpdyStream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                  return;
                }
              }
              else
              {
                i = 0;
              }
            }
            if (bool)
            {
              paramBufferedSource.skip(paramLong);
              return;
            }
            long l = paramBufferedSource.read(this.receiveBuffer, paramLong);
            if (l != -1L)
              continue;
            throw new EOFException();
          }
          int j = 0;
        }
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.SpdyStream
 * JD-Core Version:    0.6.0
 */