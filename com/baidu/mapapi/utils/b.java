package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import java.io.File;

public class b
{
  public static final String a = System.getProperty("file.separator");
  static String b;
  static String c;
  static String d;
  static String e;
  static int f;
  static int g;
  static int h;
  static String[] i;
  static String[] j;
  static String[] k;
  private static String l;

  public static String a()
  {
    String str1 = b;
    String str2 = str1 + "/BaiduMapSDK";
    if (str2.length() != 0)
    {
      File localFile = new File(str2);
      if (!localFile.exists())
        localFile.mkdirs();
    }
    return str2;
  }

  public static void a(Context paramContext)
  {
    c = paramContext.getFilesDir().getAbsolutePath();
    b(paramContext);
    if (com.baidu.platform.comapi.d.b.a() == 0)
    {
      d = j();
      e = paramContext.getCacheDir().getAbsolutePath();
      f = 20971520;
      g = 52428800;
      h = 5242880;
    }
    while (true)
    {
      c(paramContext);
      return;
      d = paramContext.getCacheDir().getAbsolutePath();
      e = "";
      f = 10485760;
      g = 10485760;
      h = 5242880;
    }
  }

  // ERROR //
  public static boolean a(String paramString)
  {
    // Byte code:
    //   0: new 57	java/io/File
    //   3: dup
    //   4: new 39	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: ldc 113
    //   17: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokespecial 60	java/io/File:<init>	(Ljava/lang/String;)V
    //   26: astore_1
    //   27: aload_1
    //   28: invokevirtual 64	java/io/File:exists	()Z
    //   31: ifeq +8 -> 39
    //   34: aload_1
    //   35: invokevirtual 116	java/io/File:delete	()Z
    //   38: pop
    //   39: aload_1
    //   40: invokevirtual 119	java/io/File:createNewFile	()Z
    //   43: istore 5
    //   45: iload 5
    //   47: istore_3
    //   48: aload_1
    //   49: invokevirtual 64	java/io/File:exists	()Z
    //   52: ifeq +8 -> 60
    //   55: aload_1
    //   56: invokevirtual 116	java/io/File:delete	()Z
    //   59: pop
    //   60: iload_3
    //   61: ireturn
    //   62: astore_2
    //   63: iconst_0
    //   64: istore_3
    //   65: aload_2
    //   66: astore 4
    //   68: aload 4
    //   70: invokevirtual 122	java/io/IOException:printStackTrace	()V
    //   73: iload_3
    //   74: ireturn
    //   75: astore 4
    //   77: goto -9 -> 68
    //
    // Exception table:
    //   from	to	target	type
    //   0	39	62	java/io/IOException
    //   39	45	62	java/io/IOException
    //   48	60	75	java/io/IOException
  }

  public static String b()
  {
    return d;
  }

  private static void b(Context paramContext)
  {
    i.a(paramContext);
    i = i.b;
    j = i.a;
    k = i.c;
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("map_pref", 0);
    if (localSharedPreferences.contains("selected_sdcard"))
    {
      b = localSharedPreferences.getString("selected_sdcard", "/sdcard/");
      if (!a(b));
    }
    while (true)
    {
      return;
      try
      {
        if ((i != null) && (i.length > 0))
          b = i[0];
        while ((b == null) || (b.trim().length() < 1))
        {
          b = Environment.getExternalStorageDirectory().getAbsolutePath();
          return;
          b = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
      }
      catch (Exception localException)
      {
        while (true)
          b = Environment.getExternalStorageDirectory().getAbsolutePath();
      }
    }
  }

  public static String c()
  {
    return e;
  }

  private static void c(Context paramContext)
  {
    l = "";
  }

  public static int d()
  {
    return f;
  }

  public static int e()
  {
    return g;
  }

  public static int f()
  {
    return h;
  }

  public static File g()
  {
    return new File(b);
  }

  public static String h()
  {
    try
    {
      String str = g().getAbsolutePath();
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static boolean i()
  {
    if (com.baidu.platform.comapi.d.b.a() != 0)
      return false;
    return a(h());
  }

  private static String j()
  {
    String str1 = "";
    if (i())
    {
      String str2 = h();
      str1 = str2 + "/BaiduMapSDK/cache";
    }
    if (str1.length() != 0)
    {
      File localFile = new File(str1);
      if (!localFile.exists())
        localFile.mkdirs();
    }
    return str1;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.b
 * JD-Core Version:    0.6.0
 */