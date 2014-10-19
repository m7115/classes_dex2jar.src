package com.baidu.location;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Handler;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.json.JSONObject;

class g
{
  private static int a = 0;
  private static boolean b = false;
  private static final int jdField_byte = 1;
  private static Handler c;
  private static final int jdField_case = 2;
  private static boolean jdField_char = false;
  private static int d = 0;
  private static String jdField_do;
  private static String e;
  private static boolean jdField_else = false;
  private static ArrayList f;
  private static String jdField_for;
  private static int g = 0;
  private static int jdField_goto = 0;
  private static boolean h = false;
  private static final int i = 4;
  private static Uri jdField_if;
  private static Handler jdField_int;
  private static boolean j = false;
  private static String k;
  private static String l;
  private static String jdField_long;
  private static Handler m;
  public static final int n = 3;
  private static String jdField_new = "baidu_location_service";
  private static int jdField_try;
  private static int jdField_void;

  static
  {
    d = 2048;
    jdField_void = 3;
    jdField_long = null;
    j = false;
    jdField_else = false;
    b = false;
    jdField_char = false;
    h = false;
    f = null;
    a = 12000;
    jdField_if = null;
    jdField_try = 4;
    k = "10.0.0.172";
    g = 80;
    jdField_goto = 0;
    jdField_int = null;
    m = null;
    c = null;
  }

  private static int a(Context paramContext, NetworkInfo paramNetworkInfo)
  {
    int i1 = 2;
    String str2;
    String str3;
    if ((paramNetworkInfo != null) && (paramNetworkInfo.getExtraInfo() != null))
    {
      str2 = paramNetworkInfo.getExtraInfo().toLowerCase();
      if (str2 != null)
        if ((str2.startsWith("cmwap")) || (str2.startsWith("uniwap")) || (str2.startsWith("3gwap")))
        {
          str3 = Proxy.getDefaultHost();
          if ((str3 != null) && (!str3.equals("")) && (!str3.equals("null")))
          {
            k = str3;
            i1 = 1;
          }
        }
    }
    String str1;
    do
    {
      do
      {
        do
        {
          return i1;
          str3 = "10.0.0.172";
          break;
          if (!str2.startsWith("ctwap"))
            continue;
          String str4 = Proxy.getDefaultHost();
          if ((str4 != null) && (!str4.equals("")) && (!str4.equals("null")));
          while (true)
          {
            k = str4;
            return 1;
            str4 = "10.0.0.200";
          }
        }
        while ((str2.startsWith("cmnet")) || (str2.startsWith("uninet")) || (str2.startsWith("ctnet")) || (str2.startsWith("3gnet")));
        str1 = Proxy.getDefaultHost();
      }
      while ((str1 == null) || (str1.length() <= 0));
      if (!"10.0.0.172".equals(str1.trim()))
        continue;
      k = "10.0.0.172";
      return 1;
    }
    while (!"10.0.0.200".equals(str1.trim()));
    k = "10.0.0.200";
    return 1;
  }

  public static void a(String paramString, boolean paramBoolean)
  {
    if ((jdField_char) || (paramString == null))
      return;
    jdField_long = Jni.jdField_if(paramString);
    h = paramBoolean;
    jdField_char = true;
    new g.4().start();
  }

  public static boolean a(Context paramContext)
  {
    if (paramContext == null);
    do
    {
      return false;
      jdField_do(paramContext);
    }
    while (jdField_try != 3);
    return true;
  }

  public static boolean a(String paramString, Handler paramHandler)
  {
    if ((j) || (paramString == null))
      return false;
    j = true;
    j.jdField_if(jdField_new, "bloc : " + l);
    l = Jni.jdField_if(paramString);
    j.jdField_if(jdField_new, "NUMBER_e : " + l.length());
    j.jdField_if(jdField_new, "content: " + l);
    jdField_int = paramHandler;
    if (jdField_do == null)
      jdField_do = k.jdField_do();
    new g.1().start();
    return true;
  }

  public static void c()
  {
    String str1 = f.ac + "/config.dat";
    if (j.jdField_void);
    for (int i1 = 1; ; i1 = 0)
    {
      if (j.jdField_try);
      for (int i2 = 1; ; i2 = 0)
      {
        Object[] arrayOfObject = new Object[35];
        arrayOfObject[0] = Integer.valueOf(j.g);
        arrayOfObject[1] = Float.valueOf(j.am);
        arrayOfObject[2] = Float.valueOf(j.c);
        arrayOfObject[3] = Float.valueOf(j.F);
        arrayOfObject[4] = Float.valueOf(j.U);
        arrayOfObject[5] = Integer.valueOf(j.p);
        arrayOfObject[6] = Integer.valueOf(j.K);
        arrayOfObject[7] = Integer.valueOf(j.X);
        arrayOfObject[8] = Integer.valueOf(j.jdField_int);
        arrayOfObject[9] = Integer.valueOf(j.jdField_for);
        arrayOfObject[10] = Integer.valueOf(j.ad);
        arrayOfObject[11] = Integer.valueOf(j.jdField_long);
        arrayOfObject[12] = Float.valueOf(j.D);
        arrayOfObject[13] = Float.valueOf(j.C);
        arrayOfObject[14] = Float.valueOf(j.ai);
        arrayOfObject[15] = Float.valueOf(j.Q);
        arrayOfObject[16] = Integer.valueOf(j.Y);
        arrayOfObject[17] = Float.valueOf(j.jdField_byte);
        arrayOfObject[18] = Integer.valueOf(j.S);
        arrayOfObject[19] = Float.valueOf(j.a);
        arrayOfObject[20] = Float.valueOf(j.u);
        arrayOfObject[21] = Float.valueOf(j.s);
        arrayOfObject[22] = Integer.valueOf(j.r);
        arrayOfObject[23] = Integer.valueOf(j.q);
        arrayOfObject[24] = Integer.valueOf(i1);
        arrayOfObject[25] = Integer.valueOf(i2);
        arrayOfObject[26] = Integer.valueOf(j.V);
        arrayOfObject[27] = Integer.valueOf(j.L);
        arrayOfObject[28] = Long.valueOf(j.ac);
        arrayOfObject[29] = Integer.valueOf(j.af);
        arrayOfObject[30] = Float.valueOf(j.w);
        arrayOfObject[31] = Float.valueOf(j.W);
        arrayOfObject[32] = Integer.valueOf(j.v);
        arrayOfObject[33] = Integer.valueOf(j.ae);
        arrayOfObject[34] = Integer.valueOf(j.jdField_goto);
        String str2 = String.format("{\"ver\":\"%d\",\"gps\":\"%.1f|%.1f|%.1f|%.1f|%d|%d|%d|%d|%d|%d|%d\",\"up\":\"%.1f|%.1f|%.1f|%.1f\",\"wf\":\"%d|%.1f|%d|%.1f\",\"ab\":\"%.2f|%.2f|%d|%d\",\"gpc\":\"%d|%d|%d|%d|%d|%d\",\"zxd\":\"%.1f|%.1f|%d|%d|%d\"}", arrayOfObject);
        j.jdField_if(jdField_new, "save2Config : " + str2);
        byte[] arrayOfByte = str2.getBytes();
        try
        {
          File localFile1 = new File(str1);
          if (!localFile1.exists())
          {
            File localFile2 = new File(f.ac);
            if (!localFile2.exists())
              localFile2.mkdirs();
            if (localFile1.createNewFile())
            {
              j.jdField_if(jdField_new, "upload manager create file success");
              RandomAccessFile localRandomAccessFile1 = new RandomAccessFile(localFile1, "rw");
              localRandomAccessFile1.seek(0L);
              localRandomAccessFile1.writeBoolean(false);
              localRandomAccessFile1.writeBoolean(false);
              localRandomAccessFile1.close();
            }
          }
          else
          {
            RandomAccessFile localRandomAccessFile2 = new RandomAccessFile(localFile1, "rw");
            localRandomAccessFile2.seek(0L);
            localRandomAccessFile2.writeBoolean(true);
            localRandomAccessFile2.seek(2L);
            localRandomAccessFile2.writeInt(arrayOfByte.length);
            localRandomAccessFile2.write(arrayOfByte);
            localRandomAccessFile2.close();
          }
          return;
        }
        catch (Exception localException)
        {
          return;
        }
      }
    }
  }

  public static int jdField_do(Context paramContext)
  {
    jdField_try = jdField_if(paramContext);
    return jdField_try;
  }

  public static void f()
  {
    if (b)
      return;
    b = true;
    int i1;
    if (f == null)
    {
      jdField_goto = 0;
      f = new ArrayList();
      i1 = 0;
      if (jdField_goto >= 2)
        break label229;
    }
    label158: label209: label229: for (Object localObject = k.jdField_do(); ; localObject = null)
    {
      if ((localObject == null) && (jdField_goto != 1))
        jdField_goto = 2;
      while (true)
      {
        try
        {
          if (j.af != 0)
            continue;
          localObject = f.jdField_new();
          if (localObject != null)
            continue;
          String str2 = b.j();
          localObject = str2;
          if (localObject != null)
            break label158;
          if ((f != null) && (f.size() >= 1))
            break label209;
          f = null;
          b = false;
          j.jdField_if(jdField_new, "No upload data...");
          return;
          if (j.af != 1)
            continue;
          localObject = b.j();
          if (localObject != null)
            continue;
          String str1 = f.jdField_new();
          localObject = str1;
          continue;
        }
        catch (Exception localException)
        {
          localObject = null;
          continue;
        }
        jdField_goto = 1;
        continue;
        f.add(localObject);
        i1 += ((String)localObject).length();
        j.jdField_if(jdField_new, "upload data size:" + i1);
        if (i1 < d)
          break;
      }
      j.jdField_if(jdField_new, "Beging upload data...");
      new g.3().start();
      return;
    }
  }

  public static void jdField_for()
  {
    String str = f.ac + "/config.dat";
    try
    {
      File localFile1 = new File(str);
      if (!localFile1.exists())
      {
        File localFile2 = new File(f.ac);
        if (!localFile2.exists())
          localFile2.mkdirs();
        if (localFile1.createNewFile())
        {
          j.jdField_if(jdField_new, "upload manager create file success");
          RandomAccessFile localRandomAccessFile1 = new RandomAccessFile(localFile1, "rw");
          localRandomAccessFile1.seek(0L);
          localRandomAccessFile1.writeBoolean(false);
          localRandomAccessFile1.writeBoolean(false);
          localRandomAccessFile1.close();
        }
      }
      else
      {
        RandomAccessFile localRandomAccessFile2 = new RandomAccessFile(localFile1, "rw");
        localRandomAccessFile2.seek(1L);
        localRandomAccessFile2.writeBoolean(true);
        localRandomAccessFile2.seek(1024L);
        localRandomAccessFile2.writeDouble(j.jdField_if);
        localRandomAccessFile2.writeDouble(j.o);
        localRandomAccessFile2.writeBoolean(j.ag);
        if ((j.ag) && (j.j != null))
          localRandomAccessFile2.write(j.j);
        localRandomAccessFile2.close();
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static void jdField_for(Handler paramHandler)
  {
    String str1 = f.ac + "/config.dat";
    try
    {
      File localFile = new File(str1);
      if (localFile.exists())
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
        if (localRandomAccessFile.readBoolean())
        {
          localRandomAccessFile.seek(2L);
          int i1 = localRandomAccessFile.readInt();
          byte[] arrayOfByte = new byte[i1];
          localRandomAccessFile.read(arrayOfByte, 0, i1);
          jdField_if(new String(arrayOfByte));
        }
        localRandomAccessFile.seek(1L);
        if (localRandomAccessFile.readBoolean())
        {
          localRandomAccessFile.seek(1024L);
          j.jdField_if = localRandomAccessFile.readDouble();
          j.o = localRandomAccessFile.readDouble();
          j.ag = localRandomAccessFile.readBoolean();
          if (j.ag)
          {
            j.j = new byte[625];
            localRandomAccessFile.read(j.j, 0, 625);
          }
        }
        localRandomAccessFile.close();
      }
      String str2 = "&ver=" + j.g + "&usr=" + j.jdField_do + "&app=" + j.ak + "&prod=" + j.b;
      j.jdField_if(jdField_new, str2);
      c = paramHandler;
      a(str2, false);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static boolean jdField_for(Context paramContext)
  {
    int i1 = 1;
    if (paramContext == null)
      return false;
    jdField_do(paramContext);
    if (jdField_try == i1);
    while (true)
    {
      return i1;
      i1 = 0;
    }
  }

  // ERROR //
  private static int jdField_if(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 461
    //   4: invokevirtual 467	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   7: checkcast 469	android/net/ConnectivityManager
    //   10: astore 6
    //   12: aload 6
    //   14: ifnonnull +5 -> 19
    //   17: iconst_4
    //   18: ireturn
    //   19: aload 6
    //   21: invokevirtual 473	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   24: astore 7
    //   26: aload 7
    //   28: ifnull +286 -> 314
    //   31: aload 7
    //   33: invokevirtual 476	android/net/NetworkInfo:isAvailable	()Z
    //   36: ifne +6 -> 42
    //   39: goto +275 -> 314
    //   42: aload 7
    //   44: invokevirtual 479	android/net/NetworkInfo:getType	()I
    //   47: iconst_1
    //   48: if_icmpne +5 -> 53
    //   51: iconst_3
    //   52: ireturn
    //   53: ldc_w 481
    //   56: invokestatic 487	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   59: putstatic 70	com/baidu/location/g:jdField_if	Landroid/net/Uri;
    //   62: aload_0
    //   63: invokevirtual 491	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   66: getstatic 70	com/baidu/location/g:jdField_if	Landroid/net/Uri;
    //   69: aconst_null
    //   70: aconst_null
    //   71: aconst_null
    //   72: aconst_null
    //   73: invokevirtual 497	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   76: astore 9
    //   78: aload 9
    //   80: ifnull +179 -> 259
    //   83: aload 9
    //   85: invokeinterface 502 1 0
    //   90: ifeq +169 -> 259
    //   93: aload 9
    //   95: aload 9
    //   97: ldc_w 504
    //   100: invokeinterface 508 2 0
    //   105: invokeinterface 512 2 0
    //   110: astore 10
    //   112: aload 10
    //   114: ifnull +71 -> 185
    //   117: aload 10
    //   119: invokevirtual 101	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   122: ldc 126
    //   124: invokevirtual 516	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   127: ifeq +58 -> 185
    //   130: invokestatic 116	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   133: astore 12
    //   135: aload 12
    //   137: ifnull +181 -> 318
    //   140: aload 12
    //   142: ldc 118
    //   144: invokevirtual 122	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   147: ifne +171 -> 318
    //   150: aload 12
    //   152: ldc 124
    //   154: invokevirtual 122	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   157: ifne +161 -> 318
    //   160: aload 12
    //   162: putstatic 76	com/baidu/location/g:k	Ljava/lang/String;
    //   165: bipush 80
    //   167: putstatic 78	com/baidu/location/g:g	I
    //   170: aload 9
    //   172: ifnull +144 -> 316
    //   175: aload 9
    //   177: invokeinterface 517 1 0
    //   182: goto +134 -> 316
    //   185: aload 10
    //   187: ifnull +72 -> 259
    //   190: aload 10
    //   192: invokevirtual 101	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   195: ldc_w 519
    //   198: invokevirtual 516	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   201: ifeq +58 -> 259
    //   204: invokestatic 116	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   207: astore 11
    //   209: aload 11
    //   211: ifnull +116 -> 327
    //   214: aload 11
    //   216: ldc 118
    //   218: invokevirtual 122	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifne +106 -> 327
    //   224: aload 11
    //   226: ldc 124
    //   228: invokevirtual 122	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   231: ifne +96 -> 327
    //   234: aload 11
    //   236: putstatic 76	com/baidu/location/g:k	Ljava/lang/String;
    //   239: bipush 80
    //   241: putstatic 78	com/baidu/location/g:g	I
    //   244: aload 9
    //   246: ifnull +79 -> 325
    //   249: aload 9
    //   251: invokeinterface 517 1 0
    //   256: goto +69 -> 325
    //   259: aload 9
    //   261: ifnull +10 -> 271
    //   264: aload 9
    //   266: invokeinterface 517 1 0
    //   271: iconst_2
    //   272: ireturn
    //   273: astore_2
    //   274: aconst_null
    //   275: astore_3
    //   276: getstatic 48	com/baidu/location/g:jdField_new	Ljava/lang/String;
    //   279: ldc_w 521
    //   282: invokestatic 183	com/baidu/location/j:jdField_if	(Ljava/lang/String;Ljava/lang/String;)V
    //   285: aload_0
    //   286: aload_3
    //   287: invokestatic 523	com/baidu/location/g:a	(Landroid/content/Context;Landroid/net/NetworkInfo;)I
    //   290: istore 5
    //   292: iload 5
    //   294: ireturn
    //   295: astore 4
    //   297: iconst_4
    //   298: ireturn
    //   299: astore_1
    //   300: aload_1
    //   301: invokevirtual 526	java/lang/Exception:printStackTrace	()V
    //   304: iconst_4
    //   305: ireturn
    //   306: astore 8
    //   308: aload 7
    //   310: astore_3
    //   311: goto -35 -> 276
    //   314: iconst_4
    //   315: ireturn
    //   316: iconst_1
    //   317: ireturn
    //   318: ldc 128
    //   320: astore 12
    //   322: goto -162 -> 160
    //   325: iconst_1
    //   326: ireturn
    //   327: ldc 74
    //   329: astore 11
    //   331: goto -97 -> 234
    //
    // Exception table:
    //   from	to	target	type
    //   0	12	273	java/lang/SecurityException
    //   19	26	273	java/lang/SecurityException
    //   276	292	295	java/lang/Exception
    //   0	12	299	java/lang/Exception
    //   19	26	299	java/lang/Exception
    //   31	39	299	java/lang/Exception
    //   42	51	299	java/lang/Exception
    //   53	78	299	java/lang/Exception
    //   83	112	299	java/lang/Exception
    //   117	135	299	java/lang/Exception
    //   140	160	299	java/lang/Exception
    //   160	170	299	java/lang/Exception
    //   175	182	299	java/lang/Exception
    //   190	209	299	java/lang/Exception
    //   214	234	299	java/lang/Exception
    //   234	244	299	java/lang/Exception
    //   249	256	299	java/lang/Exception
    //   264	271	299	java/lang/Exception
    //   31	39	306	java/lang/SecurityException
    //   42	51	306	java/lang/SecurityException
    //   53	78	306	java/lang/SecurityException
    //   83	112	306	java/lang/SecurityException
    //   117	135	306	java/lang/SecurityException
    //   140	160	306	java/lang/SecurityException
    //   160	170	306	java/lang/SecurityException
    //   175	182	306	java/lang/SecurityException
    //   190	209	306	java/lang/SecurityException
    //   214	234	306	java/lang/SecurityException
    //   234	244	306	java/lang/SecurityException
    //   249	256	306	java/lang/SecurityException
    //   264	271	306	java/lang/SecurityException
  }

  public static boolean jdField_if(String paramString)
  {
    if (paramString != null);
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      int i1 = Integer.parseInt(localJSONObject.getString("ver"));
      if (i1 > j.g)
      {
        j.g = i1;
        if (localJSONObject.has("gps"))
        {
          j.jdField_if(jdField_new, "has gps...");
          String[] arrayOfString6 = localJSONObject.getString("gps").split("\\|");
          if (arrayOfString6.length > 10)
          {
            if ((arrayOfString6[0] != null) && (!arrayOfString6[0].equals("")))
              j.am = Float.parseFloat(arrayOfString6[0]);
            if ((arrayOfString6[1] != null) && (!arrayOfString6[1].equals("")))
              j.c = Float.parseFloat(arrayOfString6[1]);
            if ((arrayOfString6[2] != null) && (!arrayOfString6[2].equals("")))
              j.F = Float.parseFloat(arrayOfString6[2]);
            if ((arrayOfString6[3] != null) && (!arrayOfString6[3].equals("")))
              j.U = Float.parseFloat(arrayOfString6[3]);
            if ((arrayOfString6[4] != null) && (!arrayOfString6[4].equals("")))
              j.p = Integer.parseInt(arrayOfString6[4]);
            if ((arrayOfString6[5] != null) && (!arrayOfString6[5].equals("")))
              j.K = Integer.parseInt(arrayOfString6[5]);
            if ((arrayOfString6[6] != null) && (!arrayOfString6[6].equals("")))
              j.X = Integer.parseInt(arrayOfString6[6]);
            if ((arrayOfString6[7] != null) && (!arrayOfString6[7].equals("")))
              j.jdField_int = Integer.parseInt(arrayOfString6[7]);
            if ((arrayOfString6[8] != null) && (!arrayOfString6[8].equals("")))
              j.jdField_for = Integer.parseInt(arrayOfString6[8]);
            if ((arrayOfString6[9] != null) && (!arrayOfString6[9].equals("")))
              j.ad = Integer.parseInt(arrayOfString6[9]);
            if ((arrayOfString6[10] != null) && (!arrayOfString6[10].equals("")))
              j.jdField_long = Integer.parseInt(arrayOfString6[10]);
          }
        }
        if (localJSONObject.has("up"))
        {
          j.jdField_if(jdField_new, "has up...");
          String[] arrayOfString5 = localJSONObject.getString("up").split("\\|");
          if (arrayOfString5.length > 3)
          {
            if ((arrayOfString5[0] != null) && (!arrayOfString5[0].equals("")))
              j.D = Float.parseFloat(arrayOfString5[0]);
            if ((arrayOfString5[1] != null) && (!arrayOfString5[1].equals("")))
              j.C = Float.parseFloat(arrayOfString5[1]);
            if ((arrayOfString5[2] != null) && (!arrayOfString5[2].equals("")))
              j.ai = Float.parseFloat(arrayOfString5[2]);
            if ((arrayOfString5[3] != null) && (!arrayOfString5[3].equals("")))
              j.Q = Float.parseFloat(arrayOfString5[3]);
          }
        }
        if (localJSONObject.has("wf"))
        {
          j.jdField_if(jdField_new, "has wf...");
          String[] arrayOfString4 = localJSONObject.getString("wf").split("\\|");
          if (arrayOfString4.length > 3)
          {
            if ((arrayOfString4[0] != null) && (!arrayOfString4[0].equals("")))
              j.Y = Integer.parseInt(arrayOfString4[0]);
            if ((arrayOfString4[1] != null) && (!arrayOfString4[1].equals("")))
              j.jdField_byte = Float.parseFloat(arrayOfString4[1]);
            if ((arrayOfString4[2] != null) && (!arrayOfString4[2].equals("")))
              j.S = Integer.parseInt(arrayOfString4[2]);
            if ((arrayOfString4[3] != null) && (!arrayOfString4[3].equals("")))
              j.a = Float.parseFloat(arrayOfString4[3]);
          }
        }
        if (localJSONObject.has("ab"))
        {
          j.jdField_if(jdField_new, "has ab...");
          String[] arrayOfString3 = localJSONObject.getString("ab").split("\\|");
          if (arrayOfString3.length > 3)
          {
            if ((arrayOfString3[0] != null) && (!arrayOfString3[0].equals("")))
              j.u = Float.parseFloat(arrayOfString3[0]);
            if ((arrayOfString3[1] != null) && (!arrayOfString3[1].equals("")))
              j.s = Float.parseFloat(arrayOfString3[1]);
            if ((arrayOfString3[2] != null) && (!arrayOfString3[2].equals("")))
              j.r = Integer.parseInt(arrayOfString3[2]);
            if ((arrayOfString3[3] != null) && (!arrayOfString3[3].equals("")))
              j.q = Integer.parseInt(arrayOfString3[3]);
          }
        }
        if (localJSONObject.has("zxd"))
        {
          String[] arrayOfString2 = localJSONObject.getString("zxd").split("\\|");
          if (arrayOfString2.length > 4)
          {
            if ((arrayOfString2[0] != null) && (!arrayOfString2[0].equals("")))
              j.w = Float.parseFloat(arrayOfString2[0]);
            if ((arrayOfString2[1] != null) && (!arrayOfString2[1].equals("")))
              j.W = Float.parseFloat(arrayOfString2[1]);
            if ((arrayOfString2[2] != null) && (!arrayOfString2[2].equals("")))
              j.v = Integer.parseInt(arrayOfString2[2]);
            if ((arrayOfString2[3] != null) && (!arrayOfString2[3].equals("")))
              j.ae = Integer.parseInt(arrayOfString2[3]);
            if ((arrayOfString2[4] != null) && (!arrayOfString2[4].equals("")))
              j.jdField_goto = Integer.parseInt(arrayOfString2[4]);
          }
        }
        String[] arrayOfString1;
        if (localJSONObject.has("gpc"))
        {
          j.jdField_if(jdField_new, "has gpc...");
          arrayOfString1 = localJSONObject.getString("gpc").split("\\|");
          if (arrayOfString1.length > 5)
            if ((arrayOfString1[0] != null) && (!arrayOfString1[0].equals("")))
            {
              if (Integer.parseInt(arrayOfString1[0]) <= 0)
                break label1325;
              j.jdField_void = true;
            }
        }
        while (true)
        {
          if ((arrayOfString1[1] != null) && (!arrayOfString1[1].equals("")))
          {
            if (Integer.parseInt(arrayOfString1[1]) > 0)
              j.jdField_try = true;
          }
          else
          {
            label1166: if ((arrayOfString1[2] != null) && (!arrayOfString1[2].equals("")))
              j.V = Integer.parseInt(arrayOfString1[2]);
            if ((arrayOfString1[3] != null) && (!arrayOfString1[3].equals("")))
              j.L = Integer.parseInt(arrayOfString1[3]);
            if ((arrayOfString1[4] != null) && (!arrayOfString1[4].equals("")))
            {
              int i2 = Integer.parseInt(arrayOfString1[4]);
              if (i2 <= 0)
                break label1339;
              j.ac = i2;
              j.M = 60L * (1000L * j.ac);
              j.al = j.M >> 2;
            }
            if ((arrayOfString1[5] != null) && (!arrayOfString1[5].equals("")))
              j.af = Integer.parseInt(arrayOfString1[5]);
          }
          try
          {
            j.jdField_if(jdField_new, "config change true...");
            return true;
            label1325: j.jdField_void = false;
            continue;
            j.jdField_try = false;
            break label1166;
            label1339: j.G = false;
          }
          catch (Exception localException2)
          {
            return true;
          }
        }
      }
      return false;
    }
    catch (Exception localException1)
    {
    }
    return false;
  }

  public static boolean jdField_if(String paramString, Handler paramHandler)
  {
    if ((jdField_else) || (paramString == null))
      return false;
    jdField_else = true;
    e = Jni.jdField_if(paramString);
    j.jdField_if(jdField_new, "bloc : " + e);
    m = paramHandler;
    if (jdField_for == null)
      jdField_for = k.jdField_do();
    new g.2().start();
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.g
 * JD-Core Version:    0.6.0
 */