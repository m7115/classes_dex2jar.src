package com.tencent.mm.a;

import java.security.MessageDigest;

public final class a
{
  public static final String a(byte[] paramArrayOfByte)
  {
    int i = 0;
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      int j = arrayOfByte.length;
      char[] arrayOfChar2 = new char[j * 2];
      int k = 0;
      while (i < j)
      {
        int m = arrayOfByte[i];
        int n = k + 1;
        arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
        k = n + 1;
        arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
        i++;
      }
      String str = new String(arrayOfChar2);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.a.a
 * JD-Core Version:    0.6.0
 */