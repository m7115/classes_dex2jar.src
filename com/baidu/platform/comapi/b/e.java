package com.baidu.platform.comapi.b;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class e
{
  private static e b = null;
  private com.baidu.platform.comjni.map.search.a a = null;
  private d c = null;
  private Handler d = null;
  private int e = 10;
  private Bundle f = null;

  private Bundle a(b paramb)
  {
    if (paramb == null)
      return null;
    Bundle localBundle = new Bundle();
    localBundle.putInt("type", paramb.a);
    localBundle.putString("uid", paramb.d);
    if (paramb.b != null)
    {
      localBundle.putInt("x", paramb.b.a());
      localBundle.putInt("y", paramb.b.b());
    }
    localBundle.putString("keyword", paramb.c);
    return localBundle;
  }

  public static e a()
  {
    if (b == null)
    {
      b = new e();
      if (!b.d())
      {
        b = null;
        return null;
      }
    }
    return b;
  }

  public static void b()
  {
    if (b != null)
    {
      if (b.a != null)
      {
        if (b.d != null)
        {
          com.baidu.platform.comjni.engine.a.b(2000, b.d);
          b.d = null;
        }
        b.a.c();
        b.a = null;
        b.f = null;
        b.c.a();
        b.c = null;
      }
      b = null;
    }
  }

  @SuppressLint({"HandlerLeak"})
  private boolean d()
  {
    int i = 1;
    if (this.a == null)
    {
      this.a = new com.baidu.platform.comjni.map.search.a();
      if (this.a.a() == 0)
      {
        this.a = null;
        i = 0;
      }
    }
    else
    {
      return i;
    }
    this.c = new d();
    this.d = new f(this);
    com.baidu.platform.comjni.engine.a.a(2000, this.d);
    this.c.a(this);
    return i;
  }

  private Bundle e()
  {
    if (this.f == null)
      this.f = new Bundle();
    while (true)
    {
      return this.f;
      this.f.clear();
    }
  }

  public void a(int paramInt)
  {
    if ((paramInt > 0) && (paramInt <= 50))
      this.e = paramInt;
  }

  public void a(c paramc)
  {
    this.c.a(paramc);
  }

  public boolean a(b paramb1, b paramb2, String paramString, com.baidu.platform.comapi.basestruct.b paramb, int paramInt1, int paramInt2, Map<String, Object> paramMap)
  {
    if ((paramString == null) || (paramString.equals("")));
    Bundle localBundle1;
    do
    {
      Bundle localBundle2;
      Bundle localBundle3;
      do
      {
        return false;
        localBundle1 = e();
        localBundle2 = a(paramb1);
        localBundle3 = a(paramb2);
      }
      while ((localBundle2 == null) || (localBundle3 == null));
      localBundle1.putBundle("start", localBundle2);
      localBundle1.putBundle("end", localBundle3);
    }
    while ((paramInt2 < 3) || (paramInt2 > 6));
    localBundle1.putInt("strategy", paramInt2);
    localBundle1.putString("cityid", paramString);
    if ((paramb != null) && (paramb.a != null) && (paramb.b != null))
    {
      Bundle localBundle5 = new Bundle();
      localBundle5.putInt("level", paramInt1);
      localBundle5.putInt("ll_x", paramb.a.a());
      localBundle5.putInt("ll_y", paramb.a.b());
      localBundle5.putInt("ru_x", paramb.b.a());
      localBundle5.putInt("ru_y", paramb.b.b());
      localBundle1.putBundle("mapbound", localBundle5);
    }
    if (paramMap != null)
    {
      Bundle localBundle4 = new Bundle();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = paramMap.get(str);
        localBundle4.putString(str.toString(), localObject.toString());
      }
      localBundle1.putBundle("extparams", localBundle4);
    }
    return this.a.d(localBundle1);
  }

  // ERROR //
  public boolean a(b paramb1, b paramb2, String paramString1, String paramString2, String paramString3, com.baidu.platform.comapi.basestruct.b paramb, int paramInt1, int paramInt2, int paramInt3, java.util.ArrayList<g> paramArrayList, Map<String, Object> paramMap)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +7 -> 8
    //   4: aload_2
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: aload_1
    //   11: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   14: ifnonnull +20 -> 34
    //   17: aload 4
    //   19: ifnull +13 -> 32
    //   22: aload 4
    //   24: ldc 119
    //   26: invokevirtual 125	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   29: ifeq +5 -> 34
    //   32: iconst_0
    //   33: ireturn
    //   34: aload_2
    //   35: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   38: ifnonnull +20 -> 58
    //   41: aload 5
    //   43: ifnull +13 -> 56
    //   46: aload 5
    //   48: ldc 119
    //   50: invokevirtual 125	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   53: ifeq +5 -> 58
    //   56: iconst_0
    //   57: ireturn
    //   58: aload_0
    //   59: invokespecial 127	com/baidu/platform/comapi/b/e:e	()Landroid/os/Bundle;
    //   62: astore 12
    //   64: aload 12
    //   66: ldc 197
    //   68: aload_1
    //   69: getfield 43	com/baidu/platform/comapi/b/b:a	I
    //   72: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   75: aload_1
    //   76: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   79: ifnull +31 -> 110
    //   82: aload 12
    //   84: ldc 199
    //   86: aload_1
    //   87: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   90: invokevirtual 66	com/baidu/platform/comapi/basestruct/c:a	()I
    //   93: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   96: aload 12
    //   98: ldc 201
    //   100: aload_1
    //   101: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   104: invokevirtual 70	com/baidu/platform/comapi/basestruct/c:b	()I
    //   107: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   110: aload 12
    //   112: ldc 203
    //   114: aload_1
    //   115: getfield 74	com/baidu/platform/comapi/b/b:c	Ljava/lang/String;
    //   118: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   121: aload 12
    //   123: ldc 205
    //   125: aload_1
    //   126: getfield 52	com/baidu/platform/comapi/b/b:d	Ljava/lang/String;
    //   129: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   132: aload 12
    //   134: ldc 207
    //   136: aload_2
    //   137: getfield 43	com/baidu/platform/comapi/b/b:a	I
    //   140: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   143: aload_2
    //   144: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   147: ifnull +31 -> 178
    //   150: aload 12
    //   152: ldc 209
    //   154: aload_2
    //   155: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   158: invokevirtual 66	com/baidu/platform/comapi/basestruct/c:a	()I
    //   161: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   164: aload 12
    //   166: ldc 211
    //   168: aload_2
    //   169: getfield 59	com/baidu/platform/comapi/b/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   172: invokevirtual 70	com/baidu/platform/comapi/basestruct/c:b	()I
    //   175: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   178: aload 12
    //   180: ldc 213
    //   182: aload_2
    //   183: getfield 74	com/baidu/platform/comapi/b/b:c	Ljava/lang/String;
    //   186: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   189: aload 12
    //   191: ldc 215
    //   193: aload_2
    //   194: getfield 52	com/baidu/platform/comapi/b/b:d	Ljava/lang/String;
    //   197: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   200: aload 12
    //   202: ldc 148
    //   204: iload 7
    //   206: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   209: aload 6
    //   211: ifnull +79 -> 290
    //   214: aload 6
    //   216: getfield 145	com/baidu/platform/comapi/basestruct/b:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   219: ifnull +71 -> 290
    //   222: aload 6
    //   224: getfield 146	com/baidu/platform/comapi/basestruct/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   227: ifnull +63 -> 290
    //   230: aload 12
    //   232: ldc 150
    //   234: aload 6
    //   236: getfield 145	com/baidu/platform/comapi/basestruct/b:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   239: invokevirtual 66	com/baidu/platform/comapi/basestruct/c:a	()I
    //   242: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   245: aload 12
    //   247: ldc 152
    //   249: aload 6
    //   251: getfield 145	com/baidu/platform/comapi/basestruct/b:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   254: invokevirtual 70	com/baidu/platform/comapi/basestruct/c:b	()I
    //   257: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   260: aload 12
    //   262: ldc 154
    //   264: aload 6
    //   266: getfield 146	com/baidu/platform/comapi/basestruct/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   269: invokevirtual 66	com/baidu/platform/comapi/basestruct/c:a	()I
    //   272: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   275: aload 12
    //   277: ldc 156
    //   279: aload 6
    //   281: getfield 146	com/baidu/platform/comapi/basestruct/b:b	Lcom/baidu/platform/comapi/basestruct/c;
    //   284: invokevirtual 70	com/baidu/platform/comapi/basestruct/c:b	()I
    //   287: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   290: aload 12
    //   292: ldc 139
    //   294: iload 8
    //   296: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   299: aload 12
    //   301: ldc 141
    //   303: aload_3
    //   304: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   307: aload 12
    //   309: ldc 217
    //   311: aload 4
    //   313: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   316: aload 12
    //   318: ldc 219
    //   320: aload 5
    //   322: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: aload 12
    //   327: ldc 221
    //   329: iload 9
    //   331: invokevirtual 47	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   334: aload 10
    //   336: ifnull +357 -> 693
    //   339: aload 10
    //   341: invokevirtual 226	java/util/ArrayList:size	()I
    //   344: istore 17
    //   346: iconst_0
    //   347: istore 18
    //   349: ldc 119
    //   351: astore 19
    //   353: ldc 119
    //   355: astore 20
    //   357: iconst_0
    //   358: istore 21
    //   360: iload 21
    //   362: iload 17
    //   364: if_icmpge +309 -> 673
    //   367: new 228	org/json/JSONObject
    //   370: dup
    //   371: invokespecial 229	org/json/JSONObject:<init>	()V
    //   374: astore 22
    //   376: aload 10
    //   378: iload 21
    //   380: invokevirtual 232	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   383: checkcast 234	com/baidu/platform/comapi/b/g
    //   386: getfield 235	com/baidu/platform/comapi/b/g:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   389: ifnull +259 -> 648
    //   392: aload 22
    //   394: ldc 39
    //   396: iconst_1
    //   397: invokevirtual 239	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   400: pop
    //   401: aload 22
    //   403: ldc 72
    //   405: aload 10
    //   407: iload 21
    //   409: invokevirtual 232	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   412: checkcast 234	com/baidu/platform/comapi/b/g
    //   415: getfield 241	com/baidu/platform/comapi/b/g:b	Ljava/lang/String;
    //   418: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   421: pop
    //   422: aload 10
    //   424: iload 21
    //   426: invokevirtual 232	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   429: checkcast 234	com/baidu/platform/comapi/b/g
    //   432: getfield 235	com/baidu/platform/comapi/b/g:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   435: ifnull +71 -> 506
    //   438: aload 22
    //   440: ldc 246
    //   442: new 248	java/lang/StringBuilder
    //   445: dup
    //   446: invokespecial 249	java/lang/StringBuilder:<init>	()V
    //   449: aload 10
    //   451: iload 21
    //   453: invokevirtual 232	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   456: checkcast 234	com/baidu/platform/comapi/b/g
    //   459: getfield 235	com/baidu/platform/comapi/b/g:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   462: getfield 250	com/baidu/platform/comapi/basestruct/c:a	I
    //   465: invokestatic 254	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   468: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: ldc_w 260
    //   474: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: aload 10
    //   479: iload 21
    //   481: invokevirtual 232	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   484: checkcast 234	com/baidu/platform/comapi/b/g
    //   487: getfield 235	com/baidu/platform/comapi/b/g:a	Lcom/baidu/platform/comapi/basestruct/c;
    //   490: getfield 262	com/baidu/platform/comapi/basestruct/c:b	I
    //   493: invokestatic 254	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   496: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   502: invokevirtual 244	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   505: pop
    //   506: new 248	java/lang/StringBuilder
    //   509: dup
    //   510: invokespecial 249	java/lang/StringBuilder:<init>	()V
    //   513: aload 20
    //   515: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   518: aload 10
    //   520: iload 21
    //   522: invokevirtual 232	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   525: checkcast 234	com/baidu/platform/comapi/b/g
    //   528: getfield 264	com/baidu/platform/comapi/b/g:c	Ljava/lang/String;
    //   531: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   537: astore 28
    //   539: aload 28
    //   541: astore 24
    //   543: new 248	java/lang/StringBuilder
    //   546: dup
    //   547: invokespecial 249	java/lang/StringBuilder:<init>	()V
    //   550: aload 19
    //   552: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: aload 22
    //   557: invokevirtual 265	org/json/JSONObject:toString	()Ljava/lang/String;
    //   560: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   566: astore 30
    //   568: aload 30
    //   570: astore 25
    //   572: iload 18
    //   574: iload 17
    //   576: iconst_1
    //   577: isub
    //   578: if_icmpeq +53 -> 631
    //   581: new 248	java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial 249	java/lang/StringBuilder:<init>	()V
    //   588: aload 25
    //   590: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   593: ldc_w 267
    //   596: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   602: astore 25
    //   604: new 248	java/lang/StringBuilder
    //   607: dup
    //   608: invokespecial 249	java/lang/StringBuilder:<init>	()V
    //   611: aload 24
    //   613: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: ldc_w 267
    //   619: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: invokevirtual 263	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   625: astore 32
    //   627: aload 32
    //   629: astore 24
    //   631: iinc 18 1
    //   634: iinc 21 1
    //   637: aload 25
    //   639: astore 19
    //   641: aload 24
    //   643: astore 20
    //   645: goto -285 -> 360
    //   648: aload 22
    //   650: ldc 39
    //   652: iconst_2
    //   653: invokevirtual 239	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   656: pop
    //   657: goto -256 -> 401
    //   660: astore 23
    //   662: aload 20
    //   664: astore 24
    //   666: aload 19
    //   668: astore 25
    //   670: goto -36 -> 634
    //   673: aload 12
    //   675: ldc_w 269
    //   678: aload 19
    //   680: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   683: aload 12
    //   685: ldc_w 271
    //   688: aload 20
    //   690: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   693: aload 11
    //   695: ifnull +86 -> 781
    //   698: new 36	android/os/Bundle
    //   701: dup
    //   702: invokespecial 37	android/os/Bundle:<init>	()V
    //   705: astore 13
    //   707: aload 11
    //   709: invokeinterface 164 1 0
    //   714: invokeinterface 170 1 0
    //   719: astore 14
    //   721: aload 14
    //   723: invokeinterface 175 1 0
    //   728: ifeq +44 -> 772
    //   731: aload 14
    //   733: invokeinterface 179 1 0
    //   738: checkcast 121	java/lang/String
    //   741: astore 15
    //   743: aload 11
    //   745: aload 15
    //   747: invokeinterface 183 2 0
    //   752: astore 16
    //   754: aload 13
    //   756: aload 15
    //   758: invokevirtual 187	java/lang/Object:toString	()Ljava/lang/String;
    //   761: aload 16
    //   763: invokevirtual 187	java/lang/Object:toString	()Ljava/lang/String;
    //   766: invokevirtual 56	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   769: goto -48 -> 721
    //   772: aload 12
    //   774: ldc 189
    //   776: aload 13
    //   778: invokevirtual 135	android/os/Bundle:putBundle	(Ljava/lang/String;Landroid/os/Bundle;)V
    //   781: aload_0
    //   782: getfield 25	com/baidu/platform/comapi/b/e:a	Lcom/baidu/platform/comjni/map/search/a;
    //   785: aload 12
    //   787: invokevirtual 273	com/baidu/platform/comjni/map/search/a:e	(Landroid/os/Bundle;)Z
    //   790: ireturn
    //   791: astore 31
    //   793: goto -159 -> 634
    //   796: astore 29
    //   798: aload 19
    //   800: astore 25
    //   802: goto -168 -> 634
    //
    // Exception table:
    //   from	to	target	type
    //   367	401	660	org/json/JSONException
    //   401	506	660	org/json/JSONException
    //   506	539	660	org/json/JSONException
    //   648	657	660	org/json/JSONException
    //   581	627	791	org/json/JSONException
    //   543	568	796	org/json/JSONException
  }

  public boolean a(b paramb1, b paramb2, String paramString1, String paramString2, String paramString3, com.baidu.platform.comapi.basestruct.b paramb, int paramInt, Map<String, Object> paramMap)
  {
    if ((paramb1 == null) || (paramb2 == null));
    do
      return false;
    while (((paramb1.b == null) && ((paramString2 == null) || (paramString2.equals("")))) || ((paramb2.b == null) && ((paramString3 == null) || (paramString3.equals("")))));
    Bundle localBundle1 = e();
    localBundle1.putInt("starttype", paramb1.a);
    if (paramb1.b != null)
    {
      localBundle1.putInt("startptx", paramb1.b.a());
      localBundle1.putInt("startpty", paramb1.b.b());
    }
    localBundle1.putString("startkeyword", paramb1.c);
    localBundle1.putString("startuid", paramb1.d);
    localBundle1.putInt("endtype", paramb2.a);
    if (paramb2.b != null)
    {
      localBundle1.putInt("endptx", paramb2.b.a());
      localBundle1.putInt("endpty", paramb2.b.b());
    }
    localBundle1.putString("endkeyword", paramb2.c);
    localBundle1.putString("enduid", paramb2.d);
    localBundle1.putInt("level", paramInt);
    if ((paramb != null) && (paramb.a != null) && (paramb.b != null))
    {
      localBundle1.putInt("ll_x", paramb.a.a());
      localBundle1.putInt("ll_y", paramb.a.b());
      localBundle1.putInt("ru_x", paramb.b.a());
      localBundle1.putInt("ru_y", paramb.b.b());
    }
    localBundle1.putString("cityid", paramString1);
    localBundle1.putString("st_cityid", paramString2);
    localBundle1.putString("en_cityid", paramString3);
    if (paramMap != null)
    {
      Bundle localBundle2 = new Bundle();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = paramMap.get(str);
        localBundle2.putString(str.toString(), localObject.toString());
      }
      localBundle1.putBundle("extparams", localBundle2);
    }
    return this.a.f(localBundle1);
  }

  public boolean a(com.baidu.platform.comapi.basestruct.c paramc)
  {
    if (paramc == null)
      return false;
    int i = paramc.b();
    int j = paramc.a();
    return this.a.a(j, i);
  }

  public boolean a(com.baidu.platform.comapi.basestruct.c paramc, String paramString1, String paramString2)
  {
    if ((paramc == null) || (paramString1 == null) || (paramString2 == null))
      return false;
    return this.a.a(paramc.a(), paramc.b(), paramString1, paramString2);
  }

  public boolean a(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return false;
      str = paramString.trim();
    }
    while ((str.length() == 0) || (str.length() > 99));
    return this.a.a(str);
  }

  public boolean a(String paramString, int paramInt1, int paramInt2, int paramInt3, com.baidu.platform.comapi.basestruct.b paramb1, com.baidu.platform.comapi.basestruct.b paramb2, Map<String, Object> paramMap)
  {
    if (paramString == null);
    String str1;
    do
    {
      return false;
      str1 = paramString.trim();
    }
    while ((str1.length() == 0) || (str1.length() > 99));
    Bundle localBundle1 = e();
    localBundle1.putString("keyword", str1);
    localBundle1.putInt("pagenum", paramInt2);
    localBundle1.putInt("count", this.e);
    localBundle1.putInt("cityid", paramInt1);
    localBundle1.putInt("level", paramInt3);
    if (paramb2 != null)
    {
      Bundle localBundle2 = new Bundle();
      localBundle2.putInt("ll_x", paramb2.a.a());
      localBundle2.putInt("ll_y", paramb2.a.b());
      localBundle2.putInt("ru_x", paramb2.b.a());
      localBundle2.putInt("ru_y", paramb2.b.b());
      localBundle1.putBundle("mapbound", localBundle2);
    }
    if (paramb1 != null)
    {
      localBundle1.putInt("ll_x", paramb1.a.a());
      localBundle1.putInt("ll_y", paramb1.a.b());
      localBundle1.putInt("ru_x", paramb1.b.a());
      localBundle1.putInt("ru_y", paramb1.b.b());
      localBundle1.putInt("loc_x", (paramb1.a.a() + paramb1.b.a()) / 2);
      localBundle1.putInt("loc_y", (paramb1.a.b() + paramb1.b.b()) / 2);
    }
    if (paramMap != null)
    {
      Bundle localBundle3 = new Bundle();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        Object localObject = paramMap.get(str2);
        localBundle3.putString(str2.toString(), localObject.toString());
      }
      localBundle1.putBundle("extparams", localBundle3);
    }
    return this.a.b(localBundle1);
  }

  public boolean a(String paramString, int paramInt1, int paramInt2, com.baidu.platform.comapi.basestruct.b paramb, int paramInt3, com.baidu.platform.comapi.basestruct.c paramc, Map<String, Object> paramMap)
  {
    if (paramb == null);
    String str1;
    do
    {
      do
        return false;
      while (paramString == null);
      str1 = paramString.trim();
    }
    while ((str1.length() == 0) || (str1.length() > 99));
    Bundle localBundle1 = e();
    localBundle1.putString("keyword", str1);
    localBundle1.putInt("pagenum", paramInt2);
    localBundle1.putInt("count", this.e);
    localBundle1.putString("cityid", String.valueOf(paramInt1));
    localBundle1.putInt("level", paramInt3);
    if (paramb != null)
    {
      localBundle1.putInt("ll_x", paramb.a.a());
      localBundle1.putInt("ll_y", paramb.a.b());
      localBundle1.putInt("ru_x", paramb.b.a());
      localBundle1.putInt("ru_y", paramb.b.b());
    }
    if (paramc != null)
    {
      localBundle1.putInt("loc_x", paramc.a);
      localBundle1.putInt("loc_y", paramc.b);
    }
    if (paramMap != null)
    {
      Bundle localBundle2 = new Bundle();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        Object localObject = paramMap.get(str2);
        localBundle2.putString(str2.toString(), localObject.toString());
      }
      localBundle1.putBundle("extparams", localBundle2);
    }
    return this.a.h(localBundle1);
  }

  public boolean a(String paramString1, int paramInt1, String paramString2, com.baidu.platform.comapi.basestruct.b paramb, int paramInt2, com.baidu.platform.comapi.basestruct.c paramc)
  {
    if (paramString1 == null);
    String str;
    do
    {
      do
        return false;
      while ((paramInt1 != 0) && (paramInt1 != 2));
      str = paramString1.trim();
    }
    while ((str.length() == 0) || (str.length() > 99));
    Bundle localBundle1 = e();
    localBundle1.putString("keyword", paramString1);
    localBundle1.putInt("type", paramInt1);
    localBundle1.putString("cityid", paramString2);
    Bundle localBundle2 = new Bundle();
    localBundle2.putInt("level", paramInt2);
    localBundle1.putBundle("mapbound", localBundle2);
    if (paramc != null)
    {
      localBundle1.putInt("loc_x", paramc.a);
      localBundle1.putInt("loc_y", paramc.b);
    }
    return this.a.g(localBundle1);
  }

  public boolean a(String paramString1, String paramString2)
  {
    if (paramString2 == null);
    String str;
    do
    {
      do
        return false;
      while ((paramString1 == null) || (paramString1.equals("")));
      str = paramString2.trim();
    }
    while ((str.length() == 0) || (str.length() > 99));
    return this.a.a(paramString1, str);
  }

  public boolean a(String paramString1, String paramString2, int paramInt1, com.baidu.platform.comapi.basestruct.b paramb, int paramInt2, Map<String, Object> paramMap)
  {
    if (paramString1 == null);
    String str1;
    do
    {
      return false;
      str1 = paramString1.trim();
    }
    while ((str1.length() == 0) || (str1.length() > 99));
    Bundle localBundle1 = e();
    localBundle1.putString("keyword", str1);
    localBundle1.putInt("pagenum", paramInt1);
    localBundle1.putInt("count", this.e);
    localBundle1.putString("cityid", paramString2);
    localBundle1.putInt("level", paramInt2);
    if (paramb != null)
    {
      localBundle1.putInt("ll_x", paramb.a.a());
      localBundle1.putInt("ll_y", paramb.a.b());
      localBundle1.putInt("ru_x", paramb.b.a());
      localBundle1.putInt("ru_y", paramb.b.b());
    }
    if (paramMap != null)
    {
      Bundle localBundle2 = new Bundle();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        Object localObject = paramMap.get(str2);
        localBundle2.putString(str2.toString(), localObject.toString());
      }
      localBundle1.putBundle("extparams", localBundle2);
    }
    return this.a.a(localBundle1);
  }

  public boolean a(String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, com.baidu.platform.comapi.basestruct.b paramb1, com.baidu.platform.comapi.basestruct.b paramb2, Map<String, Object> paramMap)
  {
    if (paramArrayOfString == null);
    StringBuilder localStringBuilder;
    label117: 
    do
    {
      do
        return false;
      while ((paramArrayOfString.length < 2) || (paramArrayOfString.length > 10));
      localStringBuilder = new StringBuilder();
      for (int i = 0; ; i++)
      {
        if (i >= paramArrayOfString.length)
          break label117;
        if (paramArrayOfString[i] == null)
          break;
        String str2 = paramArrayOfString[i].trim();
        if ((str2.length() == 0) || (str2.length() > 99) || (paramArrayOfString[i].contains("$$")))
          break;
        localStringBuilder.append(str2);
        if (i == -1 + paramArrayOfString.length)
          continue;
        localStringBuilder.append("$$");
      }
    }
    while (localStringBuilder.toString().length() > 99);
    Bundle localBundle1 = e();
    localBundle1.putString("keyword", localStringBuilder.toString());
    localBundle1.putInt("pagenum", paramInt2);
    localBundle1.putInt("count", this.e);
    localBundle1.putInt("cityid", paramInt1);
    localBundle1.putInt("level", paramInt3);
    localBundle1.putInt("radius", paramInt4);
    if (paramb2 != null)
    {
      Bundle localBundle2 = new Bundle();
      localBundle2.putInt("ll_x", paramb2.a.a());
      localBundle2.putInt("ll_y", paramb2.a.b());
      localBundle2.putInt("ru_x", paramb2.b.a());
      localBundle2.putInt("ru_y", paramb2.b.b());
      localBundle1.putBundle("mapbound", localBundle2);
    }
    if (paramb1 != null)
    {
      localBundle1.putInt("ll_x", paramb1.a.a());
      localBundle1.putInt("ll_y", paramb1.a.b());
      localBundle1.putInt("ru_x", paramb1.b.a());
      localBundle1.putInt("ru_y", paramb1.b.b());
      localBundle1.putInt("loc_x", (paramb1.a.a() + paramb1.b.a()) / 2);
      localBundle1.putInt("loc_y", (paramb1.a.b() + paramb1.b.b()) / 2);
    }
    if (paramMap != null)
    {
      Bundle localBundle3 = new Bundle();
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        Object localObject = paramMap.get(str1);
        localBundle3.putString(str1.toString(), localObject.toString());
      }
      localBundle1.putBundle("extparams", localBundle3);
    }
    return this.a.c(localBundle1);
  }

  String b(int paramInt)
  {
    String str = this.a.a(paramInt);
    if ((str != null) && (str.trim().length() <= 0))
      str = null;
    return str;
  }

  public boolean b(String paramString)
  {
    if (paramString == null)
      return false;
    return this.a.b(paramString);
  }

  public boolean b(String paramString1, String paramString2)
  {
    return this.a.b(paramString1, paramString2);
  }

  public int c()
  {
    return this.e;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.b.e
 * JD-Core Version:    0.6.0
 */