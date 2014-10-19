package com.baidu.location;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.UUID;

class j
{
  public static String A;
  public static final boolean B = false;
  public static float C = 0.0F;
  public static float D = 0.0F;
  private static boolean E = false;
  public static float F = 0.0F;
  public static boolean G = false;
  public static boolean H = false;
  public static int I = 0;
  public static double J = 0.0D;
  public static int K = 0;
  public static int L = 0;
  public static long M = 0L;
  public static String N;
  public static boolean O = false;
  public static boolean P = false;
  public static float Q = 0.0F;
  public static boolean R = false;
  public static int S = 0;
  private static final int T = 128;
  public static float U;
  public static int V;
  public static float W;
  public static int X;
  public static int Y;
  public static double Z;
  public static float a;
  public static int aa;
  public static boolean ab = false;
  public static long ac;
  public static int ad;
  public static int ae;
  public static int af;
  public static boolean ag;
  public static String ah;
  public static float ai;
  private static Process aj;
  public static String ak;
  public static long al;
  public static float am;
  public static String b;
  public static float jdField_byte;
  public static float c;
  public static final boolean jdField_case;
  private static String jdField_char;
  public static boolean d = false;
  public static String jdField_do;
  public static int e;
  private static String jdField_else;
  private static String f;
  public static int jdField_for;
  public static int g;
  public static int jdField_goto;
  public static int h = 0;
  private static final String i;
  public static double jdField_if;
  public static int jdField_int;
  public static byte[] j;
  private static boolean k;
  public static int l;
  public static int jdField_long;
  public static boolean m;
  public static boolean n;
  public static int jdField_new;
  public static double o;
  public static int p;
  public static int q;
  public static int r;
  public static float s;
  public static int t;
  public static boolean jdField_try;
  public static float u;
  public static int v;
  public static boolean jdField_void;
  public static float w;
  public static boolean x;
  public static final boolean y;
  public static boolean z;

  static
  {
    f = "baidu_location_service";
    jdField_char = "http://loc.map.baidu.com/sdk.php";
    N = "http://loc.map.baidu.com/sdk_ep.php";
    E = false;
    k = true;
    jdField_else = "[baidu_location_service]";
    aj = null;
    jdField_do = null;
    A = "no";
    ah = "gcj02";
    R = true;
    G = true;
    l = 3;
    J = 0.0D;
    Z = 0.0D;
    jdField_if = 0.0D;
    o = 0.0D;
    I = 0;
    j = null;
    ag = false;
    g = 0;
    am = 1.1F;
    c = 2.2F;
    F = 2.3F;
    U = 3.8F;
    p = 3;
    K = 10;
    X = 2;
    jdField_int = 7;
    jdField_for = 20;
    ad = 70;
    jdField_long = 120;
    D = 2.0F;
    C = 10.0F;
    ai = 50.0F;
    Q = 200.0F;
    Y = 16;
    jdField_byte = 0.9F;
    S = 5000;
    a = 0.5F;
    u = 0.0F;
    s = 0.1F;
    r = 30;
    q = 100;
    jdField_void = true;
    jdField_try = true;
    V = 20;
    aa = 300;
    L = 1000;
    M = 1200000L;
    ac = 20L;
    al = 300000L;
    af = 0;
    O = true;
    P = true;
    n = false;
    x = true;
    z = true;
    m = true;
    w = 6.0F;
    W = 10.0F;
    v = 60;
    ae = 70;
    jdField_goto = 6;
    i = f.ac + "/con.dat";
    ak = null;
    b = null;
    jdField_new = -1;
    t = -1;
    e = 0;
  }

  static float a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.equals("")));
    String str;
    do
    {
      int i2;
      int i3;
      do
      {
        int i1;
        do
        {
          return 1.4E-45F;
          i1 = paramString1.indexOf(paramString2);
        }
        while (i1 == -1);
        i2 = i1 + paramString2.length();
        i3 = paramString1.indexOf(paramString3, i2);
      }
      while (i3 == -1);
      str = paramString1.substring(i2, i3);
    }
    while ((str == null) || (str.equals("")));
    try
    {
      float f1 = Float.parseFloat(str);
      return f1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      jdField_if(f, "util numberFormatException, intStr : " + str);
      localNumberFormatException.printStackTrace();
    }
    return 1.4E-45F;
  }

  static String a()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i1 = localCalendar.get(5);
    int i2 = localCalendar.get(1);
    int i3 = 1 + localCalendar.get(2);
    int i4 = localCalendar.get(11);
    int i5 = localCalendar.get(12);
    int i6 = localCalendar.get(13);
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Integer.valueOf(i2);
    arrayOfObject[1] = Integer.valueOf(i3);
    arrayOfObject[2] = Integer.valueOf(i1);
    arrayOfObject[3] = Integer.valueOf(i4);
    arrayOfObject[4] = Integer.valueOf(i5);
    arrayOfObject[5] = Integer.valueOf(i6);
    return String.format("%d_%d_%d_%d_%d_%d", arrayOfObject);
  }

  public static String a(c.a parama, e.c paramc, Location paramLocation, String paramString, int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (parama != null)
    {
      String str6 = parama.toString();
      if (str6 != null)
        localStringBuffer.append(str6);
    }
    String str5;
    if (paramc != null)
    {
      if (paramInt != 0)
        break label288;
      str5 = paramc.jdField_char();
    }
    label81: String str2;
    while (true)
    {
      if (str5 != null)
        localStringBuffer.append(str5);
      String str4;
      if (paramLocation != null)
      {
        if ((h == 0) || (paramInt == 0))
          break label297;
        str4 = b.jdField_if(paramLocation);
        if (str4 != null)
          localStringBuffer.append(str4);
      }
      boolean bool1 = false;
      if (paramInt == 0)
        bool1 = true;
      String str1 = c.a(bool1);
      if (str1 != null)
        localStringBuffer.append(str1);
      if (paramString != null)
        localStringBuffer.append(paramString);
      if (parama != null)
      {
        String str3 = parama.jdField_int();
        if ((str3 != null) && (str3.length() + localStringBuffer.length() < 750))
          localStringBuffer.append(str3);
      }
      str2 = localStringBuffer.toString();
      jdField_if(f, "util format : " + str2);
      if ((paramLocation == null) || (paramc == null))
        break label371;
      try
      {
        float f1 = paramLocation.getSpeed();
        int i1 = h;
        int i2 = paramc.jdField_do();
        int i3 = paramc.jdField_try();
        boolean bool2 = paramc.jdField_case();
        if ((f1 < w) && ((i1 == 1) || (i1 == 0)) && ((i2 < v) || (bool2 == true)))
        {
          l = 1;
          return str2;
          label288: str5 = paramc.jdField_byte();
          continue;
          label297: str4 = b.jdField_do(paramLocation);
          break label81;
        }
        if ((f1 >= W) || ((i1 != 1) && (i1 != 0) && (i1 != 3)) || ((i2 >= ae) && (i3 <= jdField_goto)))
          break;
        l = 2;
        return str2;
      }
      catch (Exception localException)
      {
        l = 3;
        return str2;
      }
    }
    l = 3;
    return str2;
    label371: l = 3;
    return str2;
  }

  static String a(String paramString1, String paramString2, String paramString3, double paramDouble)
  {
    if ((paramString1 == null) || (paramString1.equals("")));
    int i2;
    int i3;
    do
    {
      int i1;
      do
      {
        return null;
        i1 = paramString1.indexOf(paramString2);
      }
      while (i1 == -1);
      i2 = i1 + paramString2.length();
      i3 = paramString1.indexOf(paramString3, i2);
    }
    while (i3 == -1);
    String str1 = paramString1.substring(0, i2);
    String str2 = paramString1.substring(i3);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Double.valueOf(paramDouble);
    String str3 = String.format("%.7f", arrayOfObject);
    String str4 = str1 + str3 + str2;
    jdField_if(f, "NEW:" + str4);
    return str4;
  }

  public static String a(byte[] paramArrayOfByte, String paramString, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i1 = paramArrayOfByte.length;
    for (int i2 = 0; i2 < i1; i2++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i2]);
      if (paramBoolean)
        str = str.toUpperCase();
      if (str.length() == 1)
        localStringBuilder.append("0");
      localStringBuilder.append(str).append(paramString);
    }
    return localStringBuilder.toString();
  }

  public static String a(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.reset();
      localMessageDigest.update(paramArrayOfByte);
      String str = a(localMessageDigest.digest(), "", paramBoolean);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new RuntimeException(localNoSuchAlgorithmException);
  }

  public static void a(int paramInt)
  {
    int i1 = 1;
    int i2;
    int i3;
    label24: int i4;
    label38: int i5;
    label55: int i6;
    if ((paramInt & 0x1) == i1)
    {
      i2 = i1;
      O = i2;
      if ((paramInt & 0x2) != 2)
        break label158;
      i3 = i1;
      P = i3;
      if ((paramInt & 0x4) != 4)
        break label163;
      i4 = i1;
      n = i4;
      if ((paramInt & 0x8) != 8)
        break label169;
      i5 = i1;
      x = i5;
      if ((paramInt & 0x10000) != 65536)
        break label175;
      i6 = i1;
      label74: z = i6;
      if ((paramInt & 0x20000) != 131072)
        break label181;
    }
    while (true)
    {
      m = i1;
      jdField_if(f, "A1~C3:" + O + P + n + x + z + m);
      return;
      i2 = 0;
      break;
      label158: i3 = 0;
      break label24;
      label163: i4 = 0;
      break label38;
      label169: i5 = 0;
      break label55;
      label175: i6 = 0;
      break label74;
      label181: i1 = 0;
    }
  }

  public static void a(String paramString)
  {
    if (k)
      Log.d(jdField_else, paramString);
  }

  public static void a(String paramString1, String paramString2)
  {
    if (E)
      Log.d(paramString1, paramString2);
  }

  static double jdField_do(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.equals("")));
    String str;
    do
    {
      int i2;
      int i3;
      do
      {
        int i1;
        do
        {
          return 4.9E-324D;
          i1 = paramString1.indexOf(paramString2);
        }
        while (i1 == -1);
        i2 = i1 + paramString2.length();
        i3 = paramString1.indexOf(paramString3, i2);
      }
      while (i3 == -1);
      str = paramString1.substring(i2, i3);
    }
    while ((str == null) || (str.equals("")));
    try
    {
      double d1 = Double.parseDouble(str);
      return d1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      jdField_if(f, "util numberFormatException, doubleStr : " + str);
      localNumberFormatException.printStackTrace();
    }
    return 4.9E-324D;
  }

  public static String jdField_do()
  {
    return jdField_char;
  }

  public static boolean jdField_do(String paramString)
  {
    int i1 = jdField_if(paramString, "error\":\"", "\"");
    return (i1 > 100) && (i1 < 200);
  }

  static String jdField_for()
  {
    Calendar localCalendar = Calendar.getInstance();
    int i1 = localCalendar.get(5);
    int i2 = localCalendar.get(1);
    int i3 = 1 + localCalendar.get(2);
    int i4 = localCalendar.get(11);
    int i5 = localCalendar.get(12);
    int i6 = localCalendar.get(13);
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Integer.valueOf(i2);
    arrayOfObject[1] = Integer.valueOf(i3);
    arrayOfObject[2] = Integer.valueOf(i1);
    arrayOfObject[3] = Integer.valueOf(i4);
    arrayOfObject[4] = Integer.valueOf(i5);
    arrayOfObject[5] = Integer.valueOf(i6);
    return String.format("%d-%d-%d %d:%d:%d", arrayOfObject);
  }

  static int jdField_if(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.equals("")));
    String str;
    do
    {
      int i2;
      int i3;
      do
      {
        int i1;
        do
        {
          return -2147483648;
          i1 = paramString1.indexOf(paramString2);
        }
        while (i1 == -1);
        i2 = i1 + paramString2.length();
        i3 = paramString1.indexOf(paramString3, i2);
      }
      while (i3 == -1);
      str = paramString1.substring(i2, i3);
    }
    while ((str == null) || (str.equals("")));
    try
    {
      int i4 = Integer.parseInt(str);
      return i4;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      jdField_if(f, "util numberFormatException, intStr : " + str);
      localNumberFormatException.printStackTrace();
    }
    return -2147483648;
  }

  public static void jdField_if()
  {
    int i1 = 0;
    try
    {
      File localFile = new File(i);
      RandomAccessFile localRandomAccessFile;
      int i2;
      int i3;
      byte[] arrayOfByte;
      if (localFile.exists())
      {
        localRandomAccessFile = new RandomAccessFile(localFile, "rw");
        localRandomAccessFile.seek(4L);
        i2 = localRandomAccessFile.readInt();
        i3 = localRandomAccessFile.readInt();
        localRandomAccessFile.seek(128L);
        arrayOfByte = new byte[i2];
      }
      while (true)
      {
        if (i1 < i3)
        {
          localRandomAccessFile.seek(128 + i2 * i1);
          int i4 = localRandomAccessFile.readInt();
          if ((i4 > 0) && (i4 < i2))
          {
            localRandomAccessFile.read(arrayOfByte, 0, i4);
            if (arrayOfByte[(i4 - 1)] == 0)
            {
              String str = new String(arrayOfByte, 0, i4 - 1);
              jdField_if(f, "a:" + str);
              if (str.equals(ak))
              {
                jdField_new = localRandomAccessFile.readInt();
                e = i1;
                jdField_if(f, str + jdField_new);
              }
            }
          }
        }
        else
        {
          if (i1 == i3)
            e = i3;
          localRandomAccessFile.close();
          return;
        }
        i1++;
      }
    }
    catch (Exception localException)
    {
    }
  }

  public static void jdField_if(int paramInt)
  {
    File localFile = new File(i);
    if (!localFile.exists())
      jdField_int();
    try
    {
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
      localRandomAccessFile.seek(4L);
      int i1 = localRandomAccessFile.readInt();
      int i2 = localRandomAccessFile.readInt();
      localRandomAccessFile.seek(128 + i1 * e);
      byte[] arrayOfByte = (ak + '\000').getBytes();
      localRandomAccessFile.writeInt(arrayOfByte.length);
      localRandomAccessFile.write(arrayOfByte, 0, arrayOfByte.length);
      localRandomAccessFile.writeInt(paramInt);
      if (i2 == e)
      {
        localRandomAccessFile.seek(8L);
        localRandomAccessFile.writeInt(i2 + 1);
      }
      localRandomAccessFile.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static void jdField_if(String paramString)
  {
    if ((E) && (paramString != null))
      jdField_char = paramString;
  }

  public static void jdField_if(String paramString1, String paramString2)
  {
  }

  public static void jdField_int()
  {
    try
    {
      File localFile1 = new File(i);
      if (!localFile1.exists())
      {
        File localFile2 = new File(f.ac);
        if (!localFile2.exists())
          localFile2.mkdirs();
        if (!localFile1.createNewFile())
          localFile1 = null;
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile1, "rw");
        localRandomAccessFile.seek(0L);
        localRandomAccessFile.writeInt(0);
        localRandomAccessFile.writeInt(128);
        localRandomAccessFile.writeInt(0);
        localRandomAccessFile.close();
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static void jdField_new()
  {
    if (aj != null);
    try
    {
      jdField_if(f, "logcat stop...");
      aj.destroy();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void jdField_try()
  {
    if (!E)
      return;
    while (true)
    {
      File localFile;
      try
      {
        if (aj == null)
          continue;
        aj.destroy();
        aj = null;
        localFile = new File(f.ac);
        if (localFile.exists())
        {
          jdField_if("sdkdemo_applocation", "directory already exists...");
          jdField_if(f, "logcat start ...");
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      localFile.mkdirs();
      jdField_if("sdkdemo_applocation", "directory not exists, make dirs...");
    }
  }

  public static class a
  {
    private static final boolean a;
    private static final String jdField_if = a.class.getSimpleName();

    private static String a(Context paramContext)
    {
      return j.b.a(paramContext);
    }

    public static String jdField_if(Context paramContext)
    {
      String str1 = a(paramContext);
      String str2 = j.b.jdMethod_do(paramContext);
      if (TextUtils.isEmpty(str2))
        str2 = "0";
      String str3 = new StringBuffer(str2).reverse().toString();
      return str1 + "|" + str3;
    }
  }

  public static class b
  {
    private static final String a = "a";
    private static final String jdField_do = "bids";
    private static final String jdField_for = "i";
    private static final String jdField_if = "DeviceId";

    public static String a(Context paramContext)
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("bids", 0);
      String str1 = localSharedPreferences.getString("i", null);
      if (str1 == null)
      {
        str1 = jdField_do(paramContext);
        SharedPreferences.Editor localEditor2 = localSharedPreferences.edit();
        localEditor2.putString("i", str1);
        localEditor2.commit();
      }
      String str2 = localSharedPreferences.getString("a", null);
      if (str2 == null)
      {
        str2 = jdField_if(paramContext);
        SharedPreferences.Editor localEditor1 = localSharedPreferences.edit();
        localEditor1.putString("a", str2);
        localEditor1.commit();
      }
      String str3 = j.a(("com.baidu" + str1 + str2).getBytes(), true);
      String str4 = Settings.System.getString(paramContext.getContentResolver(), str3);
      if (TextUtils.isEmpty(str4))
      {
        String str5 = UUID.randomUUID().toString();
        String str6 = j.a((str1 + str2 + str5).getBytes(), true);
        Settings.System.putString(paramContext.getContentResolver(), str3, str6);
        if (!str6.equals(Settings.System.getString(paramContext.getContentResolver(), str3)))
          str6 = str3;
        return str6;
      }
      return str4;
    }

    public static String jdField_do(Context paramContext)
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        String str = localTelephonyManager.getDeviceId();
        if (TextUtils.isEmpty(str))
          str = "";
        return str;
      }
      return "";
    }

    public static String jdField_if(Context paramContext)
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (TextUtils.isEmpty(str))
        str = "";
      return str;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.j
 * JD-Core Version:    0.6.0
 */