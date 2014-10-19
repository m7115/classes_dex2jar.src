package com.tencent.mm.sdk.platformtools;

import java.util.TimeZone;

public final class i
{
  private static final long[] a = { 300L, 200L, 300L, 200L };
  private static final TimeZone b = TimeZone.getTimeZone("GMT");
  private static final char[] c = { 9, 10, 13 };
  private static final char[] d = { 60, 62, 34, 39, 38 };
  private static final String[] e = { "&lt;", "&gt;", "&quot;", "&apos;", "&amp;" };

  public static String a(String paramString)
  {
    if (paramString == null)
      paramString = "";
    return paramString;
  }

  public static boolean a(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte == null) || (paramArrayOfByte.length <= 0);
  }

  public static boolean b(String paramString)
  {
    return (paramString == null) || (paramString.length() <= 0);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.i
 * JD-Core Version:    0.6.0
 */