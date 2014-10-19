package com.tencent.mm.sdk.platformtools;

final class b
{
  private static final byte[] a = { 4, 0, 0, 0, -1, -1, -1, 0 };

  // ERROR //
  public static void a(java.io.PrintStream paramPrintStream, byte[] paramArrayOfByte, java.lang.String paramString1, java.lang.String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +24 -> 25
    //   4: aload_1
    //   5: invokestatic 21	com/tencent/mm/sdk/platformtools/i:a	([B)Z
    //   8: ifne +17 -> 25
    //   11: aload_2
    //   12: invokestatic 25	com/tencent/mm/sdk/platformtools/i:b	(Ljava/lang/String;)Z
    //   15: ifne +10 -> 25
    //   18: aload_3
    //   19: invokestatic 25	com/tencent/mm/sdk/platformtools/i:b	(Ljava/lang/String;)Z
    //   22: ifeq +4 -> 26
    //   25: return
    //   26: aload_0
    //   27: monitorenter
    //   28: new 27	java/lang/StringBuffer
    //   31: dup
    //   32: invokespecial 30	java/lang/StringBuffer:<init>	()V
    //   35: astore 4
    //   37: aload 4
    //   39: ldc 32
    //   41: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   44: invokestatic 44	android/text/format/DateFormat:format	(Ljava/lang/CharSequence;J)Ljava/lang/CharSequence;
    //   47: invokevirtual 48	java/lang/StringBuffer:append	(Ljava/lang/CharSequence;)Ljava/lang/StringBuffer;
    //   50: pop
    //   51: aload 4
    //   53: ldc 50
    //   55: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   58: aload_2
    //   59: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   62: ldc 50
    //   64: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   67: aload_3
    //   68: invokevirtual 53	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   71: pop
    //   72: aload 4
    //   74: invokevirtual 57	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   77: astore 8
    //   79: new 59	javax/crypto/spec/DESKeySpec
    //   82: dup
    //   83: aload_1
    //   84: invokespecial 62	javax/crypto/spec/DESKeySpec:<init>	([B)V
    //   87: astore 9
    //   89: ldc 64
    //   91: invokestatic 70	javax/crypto/SecretKeyFactory:getInstance	(Ljava/lang/String;)Ljavax/crypto/SecretKeyFactory;
    //   94: aload 9
    //   96: invokevirtual 74	javax/crypto/SecretKeyFactory:generateSecret	(Ljava/security/spec/KeySpec;)Ljavax/crypto/SecretKey;
    //   99: astore 11
    //   101: ldc 64
    //   103: invokestatic 79	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   106: astore 12
    //   108: aload 12
    //   110: iconst_1
    //   111: aload 11
    //   113: invokevirtual 83	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   116: aload 12
    //   118: aload 8
    //   120: invokevirtual 89	java/lang/String:getBytes	()[B
    //   123: invokevirtual 93	javax/crypto/Cipher:doFinal	([B)[B
    //   126: astore 13
    //   128: aload_0
    //   129: aload 13
    //   131: arraylength
    //   132: invokestatic 98	com/tencent/mm/a/b:a	(I)[B
    //   135: invokevirtual 103	java/io/PrintStream:write	([B)V
    //   138: aload_0
    //   139: aload 13
    //   141: invokevirtual 103	java/io/PrintStream:write	([B)V
    //   144: aload_0
    //   145: getstatic 13	com/tencent/mm/sdk/platformtools/b:a	[B
    //   148: invokevirtual 103	java/io/PrintStream:write	([B)V
    //   151: aload_0
    //   152: invokevirtual 106	java/io/PrintStream:flush	()V
    //   155: aload_0
    //   156: monitorexit
    //   157: return
    //   158: astore 5
    //   160: aload_0
    //   161: monitorexit
    //   162: aload 5
    //   164: athrow
    //   165: astore 10
    //   167: aload 10
    //   169: invokevirtual 109	java/lang/Exception:printStackTrace	()V
    //   172: goto -21 -> 151
    //
    // Exception table:
    //   from	to	target	type
    //   28	79	158	finally
    //   79	151	158	finally
    //   151	157	158	finally
    //   167	172	158	finally
    //   79	151	165	java/lang/Exception
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.b
 * JD-Core Version:    0.6.0
 */