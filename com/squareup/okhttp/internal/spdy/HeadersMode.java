package com.squareup.okhttp.internal.spdy;

 enum HeadersMode
{
  static
  {
    SPDY_REPLY = new HeadersMode("SPDY_REPLY", 1);
    SPDY_HEADERS = new HeadersMode("SPDY_HEADERS", 2);
    HTTP_20_HEADERS = new HeadersMode("HTTP_20_HEADERS", 3);
    HeadersMode[] arrayOfHeadersMode = new HeadersMode[4];
    arrayOfHeadersMode[0] = SPDY_SYN_STREAM;
    arrayOfHeadersMode[1] = SPDY_REPLY;
    arrayOfHeadersMode[2] = SPDY_HEADERS;
    arrayOfHeadersMode[3] = HTTP_20_HEADERS;
    $VALUES = arrayOfHeadersMode;
  }

  public boolean failIfHeadersAbsent()
  {
    return this == SPDY_HEADERS;
  }

  public boolean failIfHeadersPresent()
  {
    return this == SPDY_REPLY;
  }

  public boolean failIfStreamAbsent()
  {
    return (this == SPDY_REPLY) || (this == SPDY_HEADERS);
  }

  public boolean failIfStreamPresent()
  {
    return this == SPDY_SYN_STREAM;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.HeadersMode
 * JD-Core Version:    0.6.0
 */