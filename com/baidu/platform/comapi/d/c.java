package com.baidu.platform.comapi.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.baidu.mapapi.VersionInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

public class c
{
  public static Context A;
  public static final int B;
  public static float C;
  private static boolean D;
  private static int E;
  private static int F;
  static com.baidu.platform.comjni.map.commonmemcache.a a = new com.baidu.platform.comjni.map.commonmemcache.a();
  static String b = "02";
  static String c;
  static String d;
  static String e;
  static String f;
  static int g;
  static int h;
  static int i;
  static int j;
  static int k;
  static int l;
  static String m;
  static String n;
  static String o;
  static String p;
  static String q;
  static String r;
  static String s = "baidu";
  static String t = "baidu";
  static String u = "";
  static String v = "";
  static String w = "";
  static String x;
  static String y = "-1";
  static String z = "-1";

  static
  {
    B = Integer.parseInt(Build.VERSION.SDK);
    C = 1.0F;
    D = true;
    E = 0;
    F = 0;
  }

  public static String a()
  {
    Random localRandom = new Random();
    StringBuffer localStringBuffer = new StringBuffer(10);
    for (int i1 = 0; i1 < 10; i1++)
      localStringBuffer.append(localRandom.nextInt(10));
    return localStringBuffer.toString();
  }

  // ERROR //
  public static String a(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 91	com/baidu/platform/comapi/d/c:D	Z
    //   3: ifne +55 -> 58
    //   6: invokestatic 121	com/baidu/platform/comapi/d/c:a	()Ljava/lang/String;
    //   9: astore 10
    //   11: aload 10
    //   13: astore_2
    //   14: aload_0
    //   15: ldc 123
    //   17: ldc 124
    //   19: invokevirtual 130	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   22: astore 11
    //   24: aload 11
    //   26: new 132	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   33: ldc 135
    //   35: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_2
    //   39: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokevirtual 145	java/lang/String:getBytes	()[B
    //   48: invokevirtual 151	java/io/FileOutputStream:write	([B)V
    //   51: aload 11
    //   53: invokevirtual 154	java/io/FileOutputStream:close	()V
    //   56: aload_2
    //   57: areturn
    //   58: aload_0
    //   59: ldc 123
    //   61: invokevirtual 158	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   64: astore 4
    //   66: aload 4
    //   68: invokevirtual 164	java/io/FileInputStream:available	()I
    //   71: newarray byte
    //   73: astore 5
    //   75: aload 4
    //   77: aload 5
    //   79: invokevirtual 168	java/io/FileInputStream:read	([B)I
    //   82: pop
    //   83: aload 4
    //   85: invokevirtual 169	java/io/FileInputStream:close	()V
    //   88: new 141	java/lang/String
    //   91: dup
    //   92: aload 5
    //   94: invokespecial 171	java/lang/String:<init>	([B)V
    //   97: astore 7
    //   99: aload 7
    //   101: iconst_1
    //   102: aload 7
    //   104: bipush 124
    //   106: invokevirtual 174	java/lang/String:indexOf	(I)I
    //   109: iadd
    //   110: invokevirtual 178	java/lang/String:substring	(I)Ljava/lang/String;
    //   113: astore 9
    //   115: aload 9
    //   117: areturn
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_2
    //   121: aload_1
    //   122: astore_3
    //   123: aload_3
    //   124: invokevirtual 181	java/lang/Exception:printStackTrace	()V
    //   127: aload_2
    //   128: areturn
    //   129: astore_3
    //   130: goto -7 -> 123
    //   133: astore 8
    //   135: aload 8
    //   137: astore_3
    //   138: aload 7
    //   140: astore_2
    //   141: goto -18 -> 123
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	118	java/lang/Exception
    //   58	99	118	java/lang/Exception
    //   14	56	129	java/lang/Exception
    //   99	115	133	java/lang/Exception
  }

  public static void a(String paramString)
  {
    p = paramString;
    f();
  }

  public static void a(String paramString1, String paramString2)
  {
    c(paramString1);
    b(paramString2);
    f();
  }

  public static void b()
  {
    d();
  }

  private static void b(String paramString)
  {
    v = paramString;
  }

  public static void b(String paramString1, String paramString2)
  {
    y = paramString2;
    z = paramString1;
    f();
  }

  public static byte[] b(Context paramContext)
  {
    try
    {
      byte[] arrayOfByte = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures[0].toByteArray();
      return arrayOfByte;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return null;
  }

  public static Bundle c()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("cpu", u);
    localBundle.putString("resid", b);
    localBundle.putString("channel", s);
    localBundle.putString("glr", v);
    localBundle.putString("glv", w);
    localBundle.putString("mb", g());
    localBundle.putString("sv", i());
    localBundle.putString("os", k());
    localBundle.putInt("dpi_x", n());
    localBundle.putInt("dpi_y", n());
    localBundle.putString("net", p);
    localBundle.putString("im", j(A));
    localBundle.putString("imrand", a(A));
    localBundle.putByteArray("signature", b(A));
    localBundle.putString("pcn", A.getPackageName());
    localBundle.putInt("screen_x", h());
    localBundle.putInt("screen_y", j());
    return localBundle;
  }

  public static void c(Context paramContext)
  {
    A = paramContext;
    x = paramContext.getFilesDir().getAbsolutePath();
    d = Build.MODEL;
    e = "Android" + Build.VERSION.SDK;
    c = paramContext.getPackageName();
    e(paramContext);
    f(paramContext);
    g(paramContext);
    h(paramContext);
    i(paramContext);
    l(paramContext);
    u();
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      int i1;
      if (localLocationManager.isProviderEnabled("gps"))
      {
        i1 = 1;
        E = i1;
        if (!localLocationManager.isProviderEnabled("network"))
          break label130;
      }
      label130: for (int i2 = 1; ; i2 = 0)
      {
        F = i2;
        return;
        i1 = 0;
        break;
      }
    }
    catch (Exception localException)
    {
    }
  }

  private static void c(String paramString)
  {
    w = paramString;
  }

  public static void d()
  {
    if (a != null)
      a.b();
  }

  public static void d(Context paramContext)
  {
    int i1 = 1;
    int i2 = 0;
    while (true)
    {
      File localFile4;
      try
      {
        File localFile1 = new File(x);
        if (localFile1.exists())
          continue;
        localFile1.mkdirs();
        File localFile2 = new File(x + "/ver.dat");
        byte[] arrayOfByte1 = { 2, 1, 2, 0, 0, 0 };
        if (!localFile2.exists())
          continue;
        FileInputStream localFileInputStream1 = new FileInputStream(localFile2);
        byte[] arrayOfByte2 = new byte[localFileInputStream1.available()];
        localFileInputStream1.read(arrayOfByte2);
        localFileInputStream1.close();
        if (!Arrays.equals(arrayOfByte2, arrayOfByte1))
          continue;
        i1 = 0;
        AssetManager localAssetManager = paramContext.getAssets();
        File localFile3 = new File(x + "/channel");
        if (localFile3.exists())
          continue;
        InputStream localInputStream3 = localAssetManager.open("channel");
        byte[] arrayOfByte8 = new byte[localInputStream3.available()];
        localInputStream3.read(arrayOfByte8);
        s = new String(arrayOfByte8).trim();
        byte[] arrayOfByte9 = new String(arrayOfByte8).trim().getBytes();
        localInputStream3.close();
        localFile3.createNewFile();
        FileOutputStream localFileOutputStream4 = new FileOutputStream(localFile3);
        localFileOutputStream4.write(arrayOfByte9);
        localFileOutputStream4.close();
        localFile4 = new File(x + "/oem");
        if (localFile4.exists())
          break label971;
        InputStream localInputStream2 = localAssetManager.open("oem");
        byte[] arrayOfByte6 = new byte[localInputStream2.available()];
        localInputStream2.read(arrayOfByte6);
        t = new String(arrayOfByte6).trim();
        byte[] arrayOfByte7 = new String(arrayOfByte6).trim().getBytes();
        localInputStream2.close();
        localFile4.createNewFile();
        FileOutputStream localFileOutputStream3 = new FileOutputStream(localFile4);
        localFileOutputStream3.write(arrayOfByte7);
        localFileOutputStream3.close();
        if (i1 != 0)
        {
          if (!localFile2.exists())
            continue;
          localFile2.delete();
          localFile2.createNewFile();
          FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile2);
          localFileOutputStream1.write(arrayOfByte1);
          localFileOutputStream1.close();
          File localFile5 = new File(x + "/cfg/h");
          if (localFile5.exists())
            continue;
          localFile5.mkdirs();
          File localFile6 = new File(x + "/cfg/l");
          if (localFile6.exists())
            continue;
          localFile6.mkdirs();
          String[] arrayOfString1 = { "CMRequire.dat", "VerDatset.dat", "cfg/h/ResPack.cfg", "cfg/l/ResPack.cfg", "cfg/h/DVHotcity.cfg", "cfg/l/DVHotcity.cfg", "cfg/l/mapstyle.sty", "cfg/l/satellitestyle.sty", "cfg/l/trafficstyle.sty", "cfg/l/DVDirectory.cfg", "cfg/l/DVVersion.cfg", "cfg/h/mapstyle.sty", "cfg/h/satellitestyle.sty", "cfg/h/trafficstyle.sty", "cfg/h/DVDirectory.cfg", "cfg/h/DVVersion.cfg" };
          String[] arrayOfString2 = { "CMRequire.dat", "VerDatset.dat", "cfg/h/ResPack.rs", "cfg/l/ResPack.rs", "cfg/h/DVHotcity.cfg", "cfg/l/DVHotcity.cfg", "cfg/l/mapstyle.sty", "cfg/l/satellitestyle.sty", "cfg/l/trafficstyle.sty", "cfg/l/DVDirectory.cfg", "cfg/l/DVVersion.cfg", "cfg/h/mapstyle.sty", "cfg/h/satellitestyle.sty", "cfg/h/trafficstyle.sty", "cfg/h/DVDirectory.cfg", "cfg/h/DVVersion.cfg" };
          if (i2 < arrayOfString2.length)
          {
            InputStream localInputStream1 = localAssetManager.open(arrayOfString1[i2]);
            byte[] arrayOfByte5 = new byte[localInputStream1.available()];
            localInputStream1.read(arrayOfByte5);
            localInputStream1.close();
            File localFile7 = new File(x + "/" + arrayOfString2[i2]);
            if (!localFile7.exists())
              continue;
            localFile7.delete();
            localFile7.createNewFile();
            FileOutputStream localFileOutputStream2 = new FileOutputStream(localFile7);
            localFileOutputStream2.write(arrayOfByte5);
            localFileOutputStream2.close();
            i2++;
            continue;
            FileInputStream localFileInputStream2 = new FileInputStream(localFile3);
            byte[] arrayOfByte3 = new byte[localFileInputStream2.available()];
            localFileInputStream2.read(arrayOfByte3);
            s = new String(arrayOfByte3).trim();
            localFileInputStream2.close();
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return;
      label971: FileInputStream localFileInputStream3 = new FileInputStream(localFile4);
      byte[] arrayOfByte4 = new byte[localFileInputStream3.available()];
      localFileInputStream3.read(arrayOfByte4);
      t = new String(arrayOfByte4).trim();
      localFileInputStream3.close();
    }
  }

  public static String e()
  {
    return p;
  }

  private static void e(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
      f = VersionInfo.getApiVersion();
      g = localPackageInfo.versionCode;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      f = "1.0.0";
      g = 1;
    }
  }

  public static void f()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("cpu", u);
    localBundle.putString("resid", b);
    localBundle.putString("channel", s);
    localBundle.putString("glr", v);
    localBundle.putString("glv", w);
    localBundle.putString("mb", g());
    localBundle.putString("sv", i());
    localBundle.putString("os", k());
    localBundle.putInt("dpi_x", n());
    localBundle.putInt("dpi_y", n());
    localBundle.putString("net", p);
    localBundle.putString("im", j(A));
    localBundle.putString("imrand", a(A));
    localBundle.putString("pcn", A.getPackageName());
    localBundle.putInt("screen_x", h());
    localBundle.putInt("screen_y", j());
    localBundle.putString("appid", y);
    localBundle.putString("uid", z);
    if (a != null)
      a.a(localBundle);
  }

  private static void f(Context paramContext)
  {
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    if (localWindowManager != null);
    for (Display localDisplay = localWindowManager.getDefaultDisplay(); ; localDisplay = null)
    {
      if (localDisplay != null)
      {
        h = localDisplay.getWidth();
        i = localDisplay.getHeight();
        localDisplay.getMetrics(localDisplayMetrics);
      }
      C = localDisplayMetrics.density;
      j = (int)localDisplayMetrics.xdpi;
      k = (int)localDisplayMetrics.ydpi;
      if (B > 3)
        l = localDisplayMetrics.densityDpi;
      while (true)
      {
        if (l == 0)
          l = 160;
        return;
        l = 160;
      }
    }
  }

  public static String g()
  {
    return d;
  }

  private static void g(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if (localTelephonyManager != null)
    {
      m = j(paramContext);
      n = localTelephonyManager.getSubscriberId();
      o = a(paramContext);
    }
  }

  public static int h()
  {
    return h;
  }

  private static void h(Context paramContext)
  {
    q = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }

  public static String i()
  {
    return f;
  }

  private static void i(Context paramContext)
  {
    if ((TelephonyManager)paramContext.getSystemService("phone") != null)
      r = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }

  public static int j()
  {
    return i;
  }

  // ERROR //
  private static String j(Context paramContext)
  {
    // Byte code:
    //   0: new 297	java/io/File
    //   3: dup
    //   4: new 132	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 133	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: invokevirtual 295	android/content/Context:getFilesDir	()Ljava/io/File;
    //   15: invokevirtual 300	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   18: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: ldc_w 443
    //   24: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc 123
    //   29: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 349	java/io/File:<init>	(Ljava/lang/String;)V
    //   38: astore_1
    //   39: aload_1
    //   40: invokevirtual 353	java/io/File:exists	()Z
    //   43: ifne +42 -> 85
    //   46: iconst_0
    //   47: putstatic 91	com/baidu/platform/comapi/d/c:D	Z
    //   50: aload_0
    //   51: invokestatic 553	com/baidu/platform/comapi/d/c:k	(Landroid/content/Context;)Ljava/lang/String;
    //   54: astore 11
    //   56: aload 11
    //   58: astore_3
    //   59: aload_0
    //   60: ldc 123
    //   62: ldc 124
    //   64: invokevirtual 130	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   67: astore 12
    //   69: aload 12
    //   71: aload_3
    //   72: invokevirtual 145	java/lang/String:getBytes	()[B
    //   75: invokevirtual 151	java/io/FileOutputStream:write	([B)V
    //   78: aload 12
    //   80: invokevirtual 154	java/io/FileOutputStream:close	()V
    //   83: aload_3
    //   84: areturn
    //   85: iconst_1
    //   86: putstatic 91	com/baidu/platform/comapi/d/c:D	Z
    //   89: aload_0
    //   90: ldc 123
    //   92: invokevirtual 158	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   95: astore 5
    //   97: aload 5
    //   99: invokevirtual 164	java/io/FileInputStream:available	()I
    //   102: newarray byte
    //   104: astore 6
    //   106: aload 5
    //   108: aload 6
    //   110: invokevirtual 168	java/io/FileInputStream:read	([B)I
    //   113: pop
    //   114: aload 5
    //   116: invokevirtual 169	java/io/FileInputStream:close	()V
    //   119: new 141	java/lang/String
    //   122: dup
    //   123: aload 6
    //   125: invokespecial 171	java/lang/String:<init>	([B)V
    //   128: astore 8
    //   130: aload 8
    //   132: iconst_0
    //   133: aload 8
    //   135: bipush 124
    //   137: invokevirtual 174	java/lang/String:indexOf	(I)I
    //   140: invokevirtual 556	java/lang/String:substring	(II)Ljava/lang/String;
    //   143: astore 10
    //   145: aload 10
    //   147: areturn
    //   148: astore_2
    //   149: aconst_null
    //   150: astore_3
    //   151: aload_2
    //   152: astore 4
    //   154: aload 4
    //   156: invokevirtual 181	java/lang/Exception:printStackTrace	()V
    //   159: aload_3
    //   160: areturn
    //   161: astore 4
    //   163: goto -9 -> 154
    //   166: astore 9
    //   168: aload 9
    //   170: astore 4
    //   172: aload 8
    //   174: astore_3
    //   175: goto -21 -> 154
    //
    // Exception table:
    //   from	to	target	type
    //   39	56	148	java/lang/Exception
    //   85	130	148	java/lang/Exception
    //   59	83	161	java/lang/Exception
    //   130	145	166	java/lang/Exception
  }

  public static String k()
  {
    return e;
  }

  private static String k(Context paramContext)
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        String str2 = localTelephonyManager.getDeviceId();
        str1 = str2;
        if (TextUtils.isEmpty(str1))
          str1 = "000000000000000";
        return str1;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        String str1 = null;
      }
    }
  }

  public static int l()
  {
    return j;
  }

  private static void l(Context paramContext)
  {
    p = a.c(paramContext);
  }

  public static int m()
  {
    return k;
  }

  public static int n()
  {
    return l;
  }

  public static String o()
  {
    return m;
  }

  public static String p()
  {
    return s;
  }

  public static String q()
  {
    return c;
  }

  public static String r()
  {
    return x;
  }

  public static String s()
  {
    return v;
  }

  public static String t()
  {
    return w;
  }

  private static void u()
  {
    if (a != null)
      a.a();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.d.c
 * JD-Core Version:    0.6.0
 */