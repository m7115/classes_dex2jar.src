package com.baidu.location;

final class g$4 extends Thread
{
  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: iconst_3
    //   3: istore_2
    //   4: iload_2
    //   5: ifle +213 -> 218
    //   8: new 13	org/apache/http/client/methods/HttpPost
    //   11: dup
    //   12: invokestatic 19	com/baidu/location/j:do	()Ljava/lang/String;
    //   15: invokespecial 22	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   18: astore 9
    //   20: new 24	java/util/ArrayList
    //   23: dup
    //   24: invokespecial 25	java/util/ArrayList:<init>	()V
    //   27: astore 10
    //   29: invokestatic 31	com/baidu/location/g:byte	()Z
    //   32: ifeq +245 -> 277
    //   35: aload 10
    //   37: new 33	org/apache/http/message/BasicNameValuePair
    //   40: dup
    //   41: ldc 35
    //   43: ldc 37
    //   45: invokespecial 40	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: invokeinterface 46 2 0
    //   53: pop
    //   54: aload 10
    //   56: new 33	org/apache/http/message/BasicNameValuePair
    //   59: dup
    //   60: ldc 48
    //   62: invokestatic 49	com/baidu/location/g:do	()Ljava/lang/String;
    //   65: invokespecial 40	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: invokeinterface 46 2 0
    //   73: pop
    //   74: aload 9
    //   76: new 51	org/apache/http/client/entity/UrlEncodedFormEntity
    //   79: dup
    //   80: aload 10
    //   82: ldc 53
    //   84: invokespecial 56	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   87: invokevirtual 60	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   90: new 62	org/apache/http/impl/client/DefaultHttpClient
    //   93: dup
    //   94: invokespecial 63	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   97: astore 14
    //   99: aload 14
    //   101: invokeinterface 69 1 0
    //   106: ldc 71
    //   108: invokestatic 75	com/baidu/location/g:b	()I
    //   111: invokestatic 81	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   114: invokeinterface 87 3 0
    //   119: pop
    //   120: aload 14
    //   122: invokeinterface 69 1 0
    //   127: ldc 89
    //   129: invokestatic 75	com/baidu/location/g:b	()I
    //   132: invokestatic 81	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   135: invokeinterface 87 3 0
    //   140: pop
    //   141: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   144: ldc 94
    //   146: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   149: aload 14
    //   151: aload 9
    //   153: invokeinterface 101 2 0
    //   158: astore 17
    //   160: aload 17
    //   162: invokeinterface 107 1 0
    //   167: invokeinterface 112 1 0
    //   172: sipush 200
    //   175: if_icmpne +672 -> 847
    //   178: invokestatic 31	com/baidu/location/g:byte	()Z
    //   181: ifeq +565 -> 746
    //   184: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   187: ldc 114
    //   189: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   192: aload 17
    //   194: invokeinterface 118 1 0
    //   199: invokestatic 124	org/apache/http/util/EntityUtils:toByteArray	(Lorg/apache/http/HttpEntity;)[B
    //   202: astore 23
    //   204: aload 23
    //   206: ifnonnull +109 -> 315
    //   209: iconst_0
    //   210: istore_1
    //   211: iload_1
    //   212: ifeq +6 -> 218
    //   215: invokestatic 127	com/baidu/location/g:for	()V
    //   218: invokestatic 129	com/baidu/location/j:if	()V
    //   221: getstatic 133	com/baidu/location/j:t	I
    //   224: iconst_m1
    //   225: if_icmpeq +630 -> 855
    //   228: getstatic 133	com/baidu/location/j:t	I
    //   231: istore 7
    //   233: getstatic 133	com/baidu/location/j:t	I
    //   236: invokestatic 136	com/baidu/location/j:if	(I)V
    //   239: iload 7
    //   241: iconst_m1
    //   242: if_icmpeq +8 -> 250
    //   245: iload 7
    //   247: invokestatic 139	com/baidu/location/j:a	(I)V
    //   250: invokestatic 143	com/baidu/location/g:case	()Landroid/os/Handler;
    //   253: bipush 92
    //   255: invokevirtual 149	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   258: invokevirtual 154	android/os/Message:sendToTarget	()V
    //   261: aconst_null
    //   262: invokestatic 157	com/baidu/location/g:do	(Landroid/os/Handler;)Landroid/os/Handler;
    //   265: pop
    //   266: aconst_null
    //   267: invokestatic 160	com/baidu/location/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   270: pop
    //   271: iconst_0
    //   272: invokestatic 163	com/baidu/location/g:for	(Z)Z
    //   275: pop
    //   276: return
    //   277: aload 10
    //   279: new 33	org/apache/http/message/BasicNameValuePair
    //   282: dup
    //   283: ldc 35
    //   285: ldc 165
    //   287: invokespecial 40	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   290: invokeinterface 46 2 0
    //   295: pop
    //   296: goto -242 -> 54
    //   299: astore 11
    //   301: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   304: ldc 167
    //   306: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   309: iinc 2 255
    //   312: goto -308 -> 4
    //   315: aload 23
    //   317: arraylength
    //   318: sipush 640
    //   321: if_icmpge +38 -> 359
    //   324: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   327: ldc 169
    //   329: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   332: iconst_0
    //   333: putstatic 173	com/baidu/location/j:ag	Z
    //   336: ldc2_w 174
    //   339: getstatic 178	com/baidu/location/j:Z	D
    //   342: dadd
    //   343: putstatic 181	com/baidu/location/j:o	D
    //   346: getstatic 184	com/baidu/location/j:J	D
    //   349: ldc2_w 174
    //   352: dsub
    //   353: putstatic 186	com/baidu/location/j:if	D
    //   356: goto -145 -> 211
    //   359: iconst_1
    //   360: putstatic 173	com/baidu/location/j:ag	Z
    //   363: ldc2_w 187
    //   366: aload 23
    //   368: bipush 7
    //   370: baload
    //   371: i2l
    //   372: land
    //   373: bipush 56
    //   375: lshl
    //   376: ldc2_w 187
    //   379: aload 23
    //   381: bipush 6
    //   383: baload
    //   384: i2l
    //   385: land
    //   386: bipush 48
    //   388: lshl
    //   389: lor
    //   390: ldc2_w 187
    //   393: aload 23
    //   395: iconst_5
    //   396: baload
    //   397: i2l
    //   398: land
    //   399: bipush 40
    //   401: lshl
    //   402: lor
    //   403: ldc2_w 187
    //   406: aload 23
    //   408: iconst_4
    //   409: baload
    //   410: i2l
    //   411: land
    //   412: bipush 32
    //   414: lshl
    //   415: lor
    //   416: ldc2_w 187
    //   419: aload 23
    //   421: iconst_3
    //   422: baload
    //   423: i2l
    //   424: land
    //   425: bipush 24
    //   427: lshl
    //   428: lor
    //   429: ldc2_w 187
    //   432: aload 23
    //   434: iconst_2
    //   435: baload
    //   436: i2l
    //   437: land
    //   438: bipush 16
    //   440: lshl
    //   441: lor
    //   442: ldc2_w 187
    //   445: aload 23
    //   447: iconst_1
    //   448: baload
    //   449: i2l
    //   450: land
    //   451: bipush 8
    //   453: lshl
    //   454: lor
    //   455: ldc2_w 187
    //   458: aload 23
    //   460: iconst_0
    //   461: baload
    //   462: i2l
    //   463: land
    //   464: lor
    //   465: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   468: astore 24
    //   470: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   473: ldc 195
    //   475: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   478: aload 24
    //   480: invokevirtual 199	java/lang/Long:longValue	()J
    //   483: invokestatic 205	java/lang/Double:longBitsToDouble	(J)D
    //   486: putstatic 186	com/baidu/location/j:if	D
    //   489: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   492: new 207	java/lang/StringBuilder
    //   495: dup
    //   496: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   499: ldc 210
    //   501: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   504: aload 24
    //   506: invokevirtual 199	java/lang/Long:longValue	()J
    //   509: invokestatic 205	java/lang/Double:longBitsToDouble	(J)D
    //   512: invokevirtual 217	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   515: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   518: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   521: ldc2_w 187
    //   524: aload 23
    //   526: bipush 15
    //   528: baload
    //   529: i2l
    //   530: land
    //   531: bipush 56
    //   533: lshl
    //   534: ldc2_w 187
    //   537: aload 23
    //   539: bipush 14
    //   541: baload
    //   542: i2l
    //   543: land
    //   544: bipush 48
    //   546: lshl
    //   547: lor
    //   548: ldc2_w 187
    //   551: aload 23
    //   553: bipush 13
    //   555: baload
    //   556: i2l
    //   557: land
    //   558: bipush 40
    //   560: lshl
    //   561: lor
    //   562: ldc2_w 187
    //   565: aload 23
    //   567: bipush 12
    //   569: baload
    //   570: i2l
    //   571: land
    //   572: bipush 32
    //   574: lshl
    //   575: lor
    //   576: ldc2_w 187
    //   579: aload 23
    //   581: bipush 11
    //   583: baload
    //   584: i2l
    //   585: land
    //   586: bipush 24
    //   588: lshl
    //   589: lor
    //   590: ldc2_w 187
    //   593: aload 23
    //   595: bipush 10
    //   597: baload
    //   598: i2l
    //   599: land
    //   600: bipush 16
    //   602: lshl
    //   603: lor
    //   604: ldc2_w 187
    //   607: aload 23
    //   609: bipush 9
    //   611: baload
    //   612: i2l
    //   613: land
    //   614: bipush 8
    //   616: lshl
    //   617: lor
    //   618: ldc2_w 187
    //   621: aload 23
    //   623: bipush 8
    //   625: baload
    //   626: i2l
    //   627: land
    //   628: lor
    //   629: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   632: astore 25
    //   634: aload 25
    //   636: invokevirtual 199	java/lang/Long:longValue	()J
    //   639: invokestatic 205	java/lang/Double:longBitsToDouble	(J)D
    //   642: putstatic 181	com/baidu/location/j:o	D
    //   645: sipush 625
    //   648: newarray byte
    //   650: putstatic 224	com/baidu/location/j:j	[B
    //   653: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   656: new 207	java/lang/StringBuilder
    //   659: dup
    //   660: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   663: ldc 210
    //   665: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   668: aload 25
    //   670: invokevirtual 199	java/lang/Long:longValue	()J
    //   673: invokestatic 205	java/lang/Double:longBitsToDouble	(J)D
    //   676: invokevirtual 217	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   679: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   682: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   685: iconst_0
    //   686: istore 26
    //   688: iload 26
    //   690: sipush 625
    //   693: if_icmpge -482 -> 211
    //   696: getstatic 224	com/baidu/location/j:j	[B
    //   699: iload 26
    //   701: aload 23
    //   703: iload 26
    //   705: bipush 16
    //   707: iadd
    //   708: baload
    //   709: bastore
    //   710: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   713: new 207	java/lang/StringBuilder
    //   716: dup
    //   717: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   720: ldc 226
    //   722: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: getstatic 224	com/baidu/location/j:j	[B
    //   728: iload 26
    //   730: baload
    //   731: invokevirtual 229	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   734: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   737: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   740: iinc 26 1
    //   743: goto -55 -> 688
    //   746: aload 17
    //   748: invokeinterface 118 1 0
    //   753: ldc 53
    //   755: invokestatic 232	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   758: astore 18
    //   760: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   763: new 207	java/lang/StringBuilder
    //   766: dup
    //   767: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   770: ldc 226
    //   772: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: aload 18
    //   777: invokevirtual 214	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   780: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   783: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   786: aload 18
    //   788: invokestatic 235	com/baidu/location/g:if	(Ljava/lang/String;)Z
    //   791: ifeq +14 -> 805
    //   794: invokestatic 92	com/baidu/location/g:long	()Ljava/lang/String;
    //   797: ldc 237
    //   799: invokestatic 97	com/baidu/location/j:if	(Ljava/lang/String;Ljava/lang/String;)V
    //   802: invokestatic 240	com/baidu/location/g:c	()V
    //   805: new 242	org/json/JSONObject
    //   808: dup
    //   809: aload 18
    //   811: invokespecial 243	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   814: astore 20
    //   816: aload 20
    //   818: ldc 245
    //   820: invokevirtual 248	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   823: ifeq -605 -> 218
    //   826: aload 20
    //   828: ldc 245
    //   830: invokevirtual 251	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   833: invokestatic 255	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   836: putstatic 133	com/baidu/location/j:t	I
    //   839: goto -621 -> 218
    //   842: astore 21
    //   844: goto -626 -> 218
    //   847: aload 9
    //   849: invokevirtual 258	org/apache/http/client/methods/HttpPost:abort	()V
    //   852: goto -543 -> 309
    //   855: getstatic 261	com/baidu/location/j:new	I
    //   858: iconst_m1
    //   859: if_icmpeq +25 -> 884
    //   862: getstatic 261	com/baidu/location/j:new	I
    //   865: istore 7
    //   867: goto -628 -> 239
    //   870: astore_3
    //   871: aconst_null
    //   872: invokestatic 157	com/baidu/location/g:do	(Landroid/os/Handler;)Landroid/os/Handler;
    //   875: pop
    //   876: goto -610 -> 266
    //   879: astore 19
    //   881: goto -76 -> 805
    //   884: iconst_m1
    //   885: istore 7
    //   887: goto -648 -> 239
    //   890: astore 22
    //   892: goto -674 -> 218
    //
    // Exception table:
    //   from	to	target	type
    //   8	54	299	java/lang/Exception
    //   54	192	299	java/lang/Exception
    //   277	296	299	java/lang/Exception
    //   746	786	299	java/lang/Exception
    //   847	852	299	java/lang/Exception
    //   805	839	842	java/lang/Exception
    //   218	239	870	java/lang/Exception
    //   245	250	870	java/lang/Exception
    //   250	266	870	java/lang/Exception
    //   855	867	870	java/lang/Exception
    //   786	805	879	java/lang/Exception
    //   192	204	890	java/lang/Exception
    //   215	218	890	java/lang/Exception
    //   315	356	890	java/lang/Exception
    //   359	685	890	java/lang/Exception
    //   696	740	890	java/lang/Exception
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.g.4
 * JD-Core Version:    0.6.0
 */