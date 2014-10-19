package com.tencent.mm.sdk.platformtools;

import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import java.io.PrintStream;

public class a
{
  private static int a = 0;
  private static PrintStream b;
  private static byte[] c = null;
  private static final String d;

  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("VERSION.RELEASE:[" + Build.VERSION.RELEASE);
    localStringBuilder.append("] VERSION.CODENAME:[" + Build.VERSION.CODENAME);
    localStringBuilder.append("] VERSION.INCREMENTAL:[" + Build.VERSION.INCREMENTAL);
    localStringBuilder.append("] BOARD:[" + Build.BOARD);
    localStringBuilder.append("] DEVICE:[" + Build.DEVICE);
    localStringBuilder.append("] DISPLAY:[" + Build.DISPLAY);
    localStringBuilder.append("] FINGERPRINT:[" + Build.FINGERPRINT);
    localStringBuilder.append("] HOST:[" + Build.HOST);
    localStringBuilder.append("] MANUFACTURER:[" + Build.MANUFACTURER);
    localStringBuilder.append("] MODEL:[" + Build.MODEL);
    localStringBuilder.append("] PRODUCT:[" + Build.PRODUCT);
    localStringBuilder.append("] TAGS:[" + Build.TAGS);
    localStringBuilder.append("] TYPE:[" + Build.TYPE);
    localStringBuilder.append("] USER:[" + Build.USER + "]");
    d = localStringBuilder.toString();
  }

  public static void a(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, null);
  }

  public static void a(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (a <= 4)
      if (paramArrayOfObject != null)
        break label44;
    while (true)
    {
      Log.e(paramString1, paramString2);
      b.a(b, c, "E/" + paramString1, paramString2);
      return;
      label44: paramString2 = String.format(paramString2, paramArrayOfObject);
    }
  }

  public static void b(String paramString1, String paramString2)
  {
    b(paramString1, paramString2, null);
  }

  public static void b(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (a <= 2)
      if (paramArrayOfObject != null)
        break label44;
    while (true)
    {
      Log.i(paramString1, paramString2);
      b.a(b, c, "I/" + paramString1, paramString2);
      return;
      label44: paramString2 = String.format(paramString2, paramArrayOfObject);
    }
  }

  public static void c(String paramString1, String paramString2)
  {
    c(paramString1, paramString2, null);
  }

  public static void c(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (a <= 1)
      if (paramArrayOfObject != null)
        break label44;
    while (true)
    {
      Log.d(paramString1, paramString2);
      b.a(b, c, "D/" + paramString1, paramString2);
      return;
      label44: paramString2 = String.format(paramString2, paramArrayOfObject);
    }
  }

  public static void d(String paramString1, String paramString2)
  {
    d(paramString1, paramString2, null);
  }

  public static void d(String paramString1, String paramString2, Object[] paramArrayOfObject)
  {
    if (a <= 0)
      if (paramArrayOfObject != null)
        break label43;
    while (true)
    {
      Log.v(paramString1, paramString2);
      b.a(b, c, "V/" + paramString1, paramString2);
      return;
      label43: paramString2 = String.format(paramString2, paramArrayOfObject);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.a
 * JD-Core Version:    0.6.0
 */