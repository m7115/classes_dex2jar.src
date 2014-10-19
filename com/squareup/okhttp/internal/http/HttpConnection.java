package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Connection;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.Deadline;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import com.squareup.okhttp.internal.okio.Sink;
import com.squareup.okhttp.internal.okio.Source;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
import java.net.ProtocolException;
import java.net.Socket;

public final class HttpConnection
{
  private static final String CRLF = "\r\n";
  private static final byte[] FINAL_CHUNK;
  private static final byte[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final int ON_IDLE_CLOSE = 2;
  private static final int ON_IDLE_HOLD = 0;
  private static final int ON_IDLE_POOL = 1;
  private static final int STATE_CLOSED = 6;
  private static final int STATE_IDLE = 0;
  private static final int STATE_OPEN_REQUEST_BODY = 1;
  private static final int STATE_OPEN_RESPONSE_BODY = 4;
  private static final int STATE_READING_RESPONSE_BODY = 5;
  private static final int STATE_READ_RESPONSE_HEADERS = 3;
  private static final int STATE_WRITING_REQUEST_BODY = 2;
  private final Connection connection;
  private int onIdle = 0;
  private final ConnectionPool pool;
  private final BufferedSink sink;
  private final BufferedSource source;
  private int state = 0;

  static
  {
    FINAL_CHUNK = new byte[] { 48, 13, 10, 13, 10 };
  }

  public HttpConnection(ConnectionPool paramConnectionPool, Connection paramConnection, BufferedSource paramBufferedSource, BufferedSink paramBufferedSink)
  {
    this.pool = paramConnectionPool;
    this.connection = paramConnection;
    this.source = paramBufferedSource;
    this.sink = paramBufferedSink;
  }

  public void closeIfOwnedBy(Object paramObject)
    throws IOException
  {
    this.connection.closeIfOwnedBy(paramObject);
  }

  public void closeOnIdle()
    throws IOException
  {
    this.onIdle = 2;
    if (this.state == 0)
    {
      this.state = 6;
      this.connection.close();
    }
  }

  public boolean discard(Source paramSource, int paramInt)
  {
    Socket localSocket = this.connection.getSocket();
    try
    {
      int i = localSocket.getSoTimeout();
      localSocket.setSoTimeout(paramInt);
      try
      {
        boolean bool = Util.skipAll(paramSource, paramInt);
        return bool;
      }
      finally
      {
        localSocket.setSoTimeout(i);
      }
    }
    catch (IOException localIOException)
    {
    }
    return false;
  }

  public void emptyResponseBody()
    throws IOException
  {
    newFixedLengthSource(null, 0L);
  }

  public void flush()
    throws IOException
  {
    this.sink.flush();
  }

  public boolean isClosed()
  {
    return this.state == 6;
  }

  public Sink newChunkedSink()
  {
    if (this.state != 1)
      throw new IllegalStateException("state: " + this.state);
    this.state = 2;
    return new ChunkedSink(null);
  }

  public Source newChunkedSource(CacheRequest paramCacheRequest, HttpEngine paramHttpEngine)
    throws IOException
  {
    if (this.state != 4)
      throw new IllegalStateException("state: " + this.state);
    this.state = 5;
    return new ChunkedSource(paramCacheRequest, paramHttpEngine);
  }

  public Sink newFixedLengthSink(long paramLong)
  {
    if (this.state != 1)
      throw new IllegalStateException("state: " + this.state);
    this.state = 2;
    return new FixedLengthSink(paramLong, null);
  }

  public Source newFixedLengthSource(CacheRequest paramCacheRequest, long paramLong)
    throws IOException
  {
    if (this.state != 4)
      throw new IllegalStateException("state: " + this.state);
    this.state = 5;
    return new FixedLengthSource(paramCacheRequest, paramLong);
  }

  public Source newUnknownLengthSource(CacheRequest paramCacheRequest)
    throws IOException
  {
    if (this.state != 4)
      throw new IllegalStateException("state: " + this.state);
    this.state = 5;
    return new UnknownLengthSource(paramCacheRequest);
  }

  public void poolOnIdle()
  {
    this.onIdle = 1;
    if (this.state == 0)
    {
      this.onIdle = 0;
      this.pool.recycle(this.connection);
    }
  }

  public void readHeaders(Headers.Builder paramBuilder)
    throws IOException
  {
    while (true)
    {
      String str = this.source.readUtf8Line(true);
      if (str.length() == 0)
        break;
      paramBuilder.addLine(str);
    }
  }

  public Response.Builder readResponse()
    throws IOException
  {
    if ((this.state != 1) && (this.state != 3))
      throw new IllegalStateException("state: " + this.state);
    StatusLine localStatusLine;
    Response.Builder localBuilder;
    do
    {
      localStatusLine = new StatusLine(this.source.readUtf8Line(true));
      localBuilder = new Response.Builder().statusLine(localStatusLine).header(OkHeaders.SELECTED_PROTOCOL, Protocol.HTTP_11.name.utf8());
      Headers.Builder localBuilder1 = new Headers.Builder();
      readHeaders(localBuilder1);
      localBuilder.headers(localBuilder1.build());
    }
    while (localStatusLine.code() == 100);
    this.state = 4;
    return localBuilder;
  }

  public void writeRequest(Headers paramHeaders, String paramString)
    throws IOException
  {
    if (this.state != 0)
      throw new IllegalStateException("state: " + this.state);
    this.sink.writeUtf8(paramString).writeUtf8("\r\n");
    for (int i = 0; i < paramHeaders.size(); i++)
      this.sink.writeUtf8(paramHeaders.name(i)).writeUtf8(": ").writeUtf8(paramHeaders.value(i)).writeUtf8("\r\n");
    this.sink.writeUtf8("\r\n");
    this.state = 1;
  }

  public void writeRequestBody(RetryableSink paramRetryableSink)
    throws IOException
  {
    if (this.state != 1)
      throw new IllegalStateException("state: " + this.state);
    this.state = 3;
    paramRetryableSink.writeToSocket(this.sink);
  }

  private class AbstractSource
  {
    protected final OutputStream cacheBody;
    private final CacheRequest cacheRequest;
    protected boolean closed;

    AbstractSource(CacheRequest arg2)
      throws IOException
    {
      Object localObject;
      if (localObject != null);
      for (OutputStream localOutputStream = localObject.getBody(); ; localOutputStream = null)
      {
        if (localOutputStream == null)
          localObject = null;
        this.cacheBody = localOutputStream;
        this.cacheRequest = localObject;
        return;
      }
    }

    protected final void cacheWrite(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (this.cacheBody != null)
        Okio.copy(paramOkBuffer, paramOkBuffer.size() - paramLong, paramLong, this.cacheBody);
    }

    protected final void endOfInput(boolean paramBoolean)
      throws IOException
    {
      if (HttpConnection.this.state != 5)
        throw new IllegalStateException("state: " + HttpConnection.this.state);
      if (this.cacheRequest != null)
        this.cacheBody.close();
      HttpConnection.access$302(HttpConnection.this, 0);
      if ((paramBoolean) && (HttpConnection.this.onIdle == 1))
      {
        HttpConnection.access$602(HttpConnection.this, 0);
        HttpConnection.this.pool.recycle(HttpConnection.this.connection);
      }
      do
        return;
      while (HttpConnection.this.onIdle != 2);
      HttpConnection.access$302(HttpConnection.this, 6);
      HttpConnection.this.connection.close();
    }

    protected final void unexpectedEndOfInput()
    {
      if (this.cacheRequest != null)
        this.cacheRequest.abort();
      Util.closeQuietly(HttpConnection.this.connection);
      HttpConnection.access$302(HttpConnection.this, 6);
    }
  }

  private final class ChunkedSink
    implements Sink
  {
    private boolean closed;
    private final byte[] hex = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 10 };

    private ChunkedSink()
    {
    }

    private void writeHex(long paramLong)
      throws IOException
    {
      int i = 16;
      do
      {
        byte[] arrayOfByte = this.hex;
        i--;
        arrayOfByte[i] = HttpConnection.access$500()[(int)(0xF & paramLong)];
        paramLong >>>= 4;
      }
      while (paramLong != 0L);
      HttpConnection.this.sink.write(this.hex, i, this.hex.length - i);
    }

    public void close()
      throws IOException
    {
      monitorenter;
      try
      {
        boolean bool = this.closed;
        if (bool);
        while (true)
        {
          return;
          this.closed = true;
          HttpConnection.this.sink.write(HttpConnection.FINAL_CHUNK);
          HttpConnection.access$302(HttpConnection.this, 3);
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public Sink deadline(Deadline paramDeadline)
    {
      return this;
    }

    public void flush()
      throws IOException
    {
      monitorenter;
      try
      {
        boolean bool = this.closed;
        if (bool);
        while (true)
        {
          return;
          HttpConnection.this.sink.flush();
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }

    public void write(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (this.closed)
        throw new IllegalStateException("closed");
      if (paramLong == 0L)
        return;
      writeHex(paramLong);
      HttpConnection.this.sink.write(paramOkBuffer, paramLong);
      HttpConnection.this.sink.writeUtf8("\r\n");
    }
  }

  private class ChunkedSource extends HttpConnection.AbstractSource
    implements Source
  {
    private static final int NO_CHUNK_YET = -1;
    private int bytesRemainingInChunk = -1;
    private boolean hasMoreChunks = true;
    private final HttpEngine httpEngine;

    ChunkedSource(CacheRequest paramHttpEngine, HttpEngine arg3)
      throws IOException
    {
      super(paramHttpEngine);
      Object localObject;
      this.httpEngine = localObject;
    }

    private void readChunkSize()
      throws IOException
    {
      if (this.bytesRemainingInChunk != -1)
        HttpConnection.this.source.readUtf8Line(true);
      String str = HttpConnection.this.source.readUtf8Line(true);
      int i = str.indexOf(";");
      if (i != -1)
        str = str.substring(0, i);
      try
      {
        this.bytesRemainingInChunk = Integer.parseInt(str.trim(), 16);
        if (this.bytesRemainingInChunk == 0)
        {
          this.hasMoreChunks = false;
          Headers.Builder localBuilder = new Headers.Builder();
          HttpConnection.this.readHeaders(localBuilder);
          this.httpEngine.receiveHeaders(localBuilder.build());
          endOfInput(true);
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
      throw new ProtocolException("Expected a hex chunk size but was " + str);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      if ((this.hasMoreChunks) && (!HttpConnection.this.discard(this, 100)))
        unexpectedEndOfInput();
      this.closed = true;
    }

    public Source deadline(Deadline paramDeadline)
    {
      HttpConnection.this.source.deadline(paramDeadline);
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      if (this.closed)
        throw new IllegalStateException("closed");
      if (!this.hasMoreChunks);
      do
      {
        return -1L;
        if ((this.bytesRemainingInChunk != 0) && (this.bytesRemainingInChunk != -1))
          break;
        readChunkSize();
      }
      while (!this.hasMoreChunks);
      long l = HttpConnection.this.source.read(paramOkBuffer, Math.min(paramLong, this.bytesRemainingInChunk));
      if (l == -1L)
      {
        unexpectedEndOfInput();
        throw new IOException("unexpected end of stream");
      }
      this.bytesRemainingInChunk = (int)(this.bytesRemainingInChunk - l);
      cacheWrite(paramOkBuffer, l);
      return l;
    }
  }

  private final class FixedLengthSink
    implements Sink
  {
    private long bytesRemaining;
    private boolean closed;

    private FixedLengthSink(long arg2)
    {
      Object localObject;
      this.bytesRemaining = localObject;
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      this.closed = true;
      if (this.bytesRemaining > 0L)
        throw new ProtocolException("unexpected end of stream");
      HttpConnection.access$302(HttpConnection.this, 3);
    }

    public Sink deadline(Deadline paramDeadline)
    {
      return this;
    }

    public void flush()
      throws IOException
    {
      if (this.closed)
        return;
      HttpConnection.this.sink.flush();
    }

    public void write(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (this.closed)
        throw new IllegalStateException("closed");
      Util.checkOffsetAndCount(paramOkBuffer.size(), 0L, paramLong);
      if (paramLong > this.bytesRemaining)
        throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + paramLong);
      HttpConnection.this.sink.write(paramOkBuffer, paramLong);
      this.bytesRemaining -= paramLong;
    }
  }

  private class FixedLengthSource extends HttpConnection.AbstractSource
    implements Source
  {
    private long bytesRemaining;

    public FixedLengthSource(CacheRequest paramLong, long arg3)
      throws IOException
    {
      super(paramLong);
      Object localObject;
      this.bytesRemaining = localObject;
      if (this.bytesRemaining == 0L)
        endOfInput(true);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      if ((this.bytesRemaining != 0L) && (!HttpConnection.this.discard(this, 100)))
        unexpectedEndOfInput();
      this.closed = true;
    }

    public Source deadline(Deadline paramDeadline)
    {
      HttpConnection.this.source.deadline(paramDeadline);
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      if (this.closed)
        throw new IllegalStateException("closed");
      if (this.bytesRemaining == 0L)
        return -1L;
      long l = HttpConnection.this.source.read(paramOkBuffer, Math.min(this.bytesRemaining, paramLong));
      if (l == -1L)
      {
        unexpectedEndOfInput();
        throw new ProtocolException("unexpected end of stream");
      }
      this.bytesRemaining -= l;
      cacheWrite(paramOkBuffer, l);
      if (this.bytesRemaining == 0L)
        endOfInput(true);
      return l;
    }
  }

  class UnknownLengthSource extends HttpConnection.AbstractSource
    implements Source
  {
    private boolean inputExhausted;

    UnknownLengthSource(CacheRequest arg2)
      throws IOException
    {
      super(localCacheRequest);
    }

    public void close()
      throws IOException
    {
      if (this.closed)
        return;
      if (!this.inputExhausted)
        unexpectedEndOfInput();
      this.closed = true;
    }

    public Source deadline(Deadline paramDeadline)
    {
      HttpConnection.this.source.deadline(paramDeadline);
      return this;
    }

    public long read(OkBuffer paramOkBuffer, long paramLong)
      throws IOException
    {
      if (paramLong < 0L)
        throw new IllegalArgumentException("byteCount < 0: " + paramLong);
      if (this.closed)
        throw new IllegalStateException("closed");
      if (this.inputExhausted)
        return -1L;
      long l = HttpConnection.this.source.read(paramOkBuffer, paramLong);
      if (l == -1L)
      {
        this.inputExhausted = true;
        endOfInput(false);
        return -1L;
      }
      cacheWrite(paramOkBuffer, l);
      return l;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpConnection
 * JD-Core Version:    0.6.0
 */