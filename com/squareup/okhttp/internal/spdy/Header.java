package com.squareup.okhttp.internal.spdy;

import com.squareup.okhttp.internal.okio.ByteString;

public final class Header
{
  public static final ByteString RESPONSE_STATUS = ByteString.encodeUtf8(":status");
  public static final ByteString TARGET_AUTHORITY;
  public static final ByteString TARGET_HOST;
  public static final ByteString TARGET_METHOD = ByteString.encodeUtf8(":method");
  public static final ByteString TARGET_PATH = ByteString.encodeUtf8(":path");
  public static final ByteString TARGET_SCHEME = ByteString.encodeUtf8(":scheme");
  public static final ByteString VERSION;
  final int hpackSize;
  public final ByteString name;
  public final ByteString value;

  static
  {
    TARGET_AUTHORITY = ByteString.encodeUtf8(":authority");
    TARGET_HOST = ByteString.encodeUtf8(":host");
    VERSION = ByteString.encodeUtf8(":version");
  }

  public Header(ByteString paramByteString1, ByteString paramByteString2)
  {
    this.name = paramByteString1;
    this.value = paramByteString2;
    this.hpackSize = (32 + paramByteString1.size() + paramByteString2.size());
  }

  public Header(ByteString paramByteString, String paramString)
  {
    this(paramByteString, ByteString.encodeUtf8(paramString));
  }

  public Header(String paramString1, String paramString2)
  {
    this(ByteString.encodeUtf8(paramString1), ByteString.encodeUtf8(paramString2));
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Header;
    int i = 0;
    if (bool1)
    {
      Header localHeader = (Header)paramObject;
      boolean bool2 = this.name.equals(localHeader.name);
      i = 0;
      if (bool2)
      {
        boolean bool3 = this.value.equals(localHeader.value);
        i = 0;
        if (bool3)
          i = 1;
      }
    }
    return i;
  }

  public int hashCode()
  {
    return 31 * (527 + this.name.hashCode()) + this.value.hashCode();
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.name.utf8();
    arrayOfObject[1] = this.value.utf8();
    return String.format("%s: %s", arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.spdy.Header
 * JD-Core Version:    0.6.0
 */