package com.squareup.okhttp;

import com.squareup.okhttp.internal.okio.ByteString;

public enum Protocol
{
  public final ByteString name;
  public final boolean spdyVariant;

  static
  {
    HTTP_11 = new Protocol("HTTP_11", 2, "http/1.1", false);
    Protocol[] arrayOfProtocol = new Protocol[3];
    arrayOfProtocol[0] = HTTP_2;
    arrayOfProtocol[1] = SPDY_3;
    arrayOfProtocol[2] = HTTP_11;
    $VALUES = arrayOfProtocol;
  }

  private Protocol(String paramString, boolean paramBoolean)
  {
    this.name = ByteString.encodeUtf8(paramString);
    this.spdyVariant = paramBoolean;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Protocol
 * JD-Core Version:    0.6.0
 */