package com.squareup.okhttp.internal.spdy;

import java.io.IOException;

public abstract interface IncomingStreamHandler
{
  public static final IncomingStreamHandler REFUSE_INCOMING_STREAMS = new IncomingStreamHandler()
  {
    public void receive(SpdyStream paramSpdyStream)
      throws IOException
    {
      paramSpdyStream.close(ErrorCode.REFUSED_STREAM);
    }
  };

  public abstract void receive(SpdyStream paramSpdyStream)
    throws IOException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.IncomingStreamHandler
 * JD-Core Version:    0.6.0
 */