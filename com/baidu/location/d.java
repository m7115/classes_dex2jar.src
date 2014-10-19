package com.baidu.location;

import java.security.MessageDigest;

class d
{
  private static char[] a;
  private static char[] jdField_if = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/.".toCharArray();

  static
  {
    a = new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  }

  public static String a(String paramString)
  {
    while (true)
    {
      int m;
      try
      {
        char[] arrayOfChar1 = a((paramString + "webgis").getBytes("UTF-8"));
        byte[] arrayOfByte1 = paramString.getBytes("UTF-8");
        byte[] arrayOfByte2 = new byte[2 + arrayOfByte1.length];
        int i = 0;
        if (i >= arrayOfByte1.length)
          continue;
        arrayOfByte2[i] = arrayOfByte1[i];
        i++;
        continue;
        arrayOfByte2[arrayOfByte1.length] = (byte)(0xFF & Integer.parseInt(String.copyValueOf(arrayOfChar1, 10, 2), 16));
        arrayOfByte2[(1 + arrayOfByte1.length)] = (byte)(0xFF & Integer.parseInt(String.copyValueOf(arrayOfChar1, 20, 2), 16));
        String str1 = "" + (char)(0xFF & Integer.parseInt(String.copyValueOf(arrayOfChar1, 6, 2), 16));
        String str2 = str1 + (char)(0xFF & Integer.parseInt(String.copyValueOf(arrayOfChar1, 16, 2), 16));
        String str3 = str2 + (char)(0xFF & Integer.parseInt(String.copyValueOf(arrayOfChar1, 26, 2), 16));
        char[] arrayOfChar2 = a((str3 + "webgis").getBytes("iso-8859-1"));
        int j = arrayOfByte2.length;
        int k = str3.length();
        byte[] arrayOfByte3 = new byte[j + k];
        m = 0;
        int n = (j + 31) / 32;
        int i1 = 0;
        if (m >= n)
          continue;
        int i2 = m * 32;
        int i3 = 0;
        if ((i3 < 32) && (i2 + i3 < j))
        {
          arrayOfByte3[(i2 + i3)] = (byte)(0xFF & arrayOfChar2[i3] ^ 0xFF & arrayOfByte2[(i2 + i3)]);
          i3++;
          continue;
          if (i1 >= k)
            continue;
          arrayOfByte3[(j + i1)] = (byte)str3.charAt(i1);
          i1++;
          continue;
          String str4 = new String(jdField_if(arrayOfByte3));
          return str4;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return "UnsupportedEncodingException";
      }
      m++;
    }
  }

  private static char[] a(byte[] paramArrayOfByte)
  {
    int i = 0;
    char[] arrayOfChar = new char[32];
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      byte[] arrayOfByte = localMessageDigest.digest();
      int j = 0;
      while (i < 16)
      {
        int k = arrayOfByte[i];
        int m = j + 1;
        arrayOfChar[j] = a[(0xF & k >>> 4)];
        j = m + 1;
        arrayOfChar[m] = a[(k & 0xF)];
        i++;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return arrayOfChar;
  }

  private static char[] jdField_if(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar1 = new char[4 * ((2 + paramArrayOfByte.length) / 3)];
    int i = 0;
    int j = 0;
    int k;
    if (j < paramArrayOfByte.length)
    {
      k = (0xFF & paramArrayOfByte[j]) << 8;
      if (j + 1 >= paramArrayOfByte.length)
        break label244;
      k |= 0xFF & paramArrayOfByte[(j + 1)];
    }
    label115: label244: for (int m = 1; ; m = 0)
    {
      int n = k << 8;
      if (j + 2 < paramArrayOfByte.length)
        n |= 0xFF & paramArrayOfByte[(j + 2)];
      for (int i1 = 1; ; i1 = 0)
      {
        int i2 = i + 3;
        char[] arrayOfChar2 = jdField_if;
        int i3;
        int i4;
        int i5;
        char[] arrayOfChar3;
        if (i1 != 0)
        {
          i3 = 63 - (n & 0x3F);
          arrayOfChar1[i2] = arrayOfChar2[i3];
          i4 = n >> 6;
          i5 = i + 2;
          arrayOfChar3 = jdField_if;
          if (m == 0)
            break label229;
        }
        for (int i6 = 63 - (i4 & 0x3F); ; i6 = 64)
        {
          arrayOfChar1[i5] = arrayOfChar3[i6];
          int i7 = i4 >> 6;
          arrayOfChar1[(i + 1)] = jdField_if[(63 - (i7 & 0x3F))];
          int i8 = i7 >> 6;
          arrayOfChar1[(i + 0)] = jdField_if[(63 - (i8 & 0x3F))];
          j += 3;
          i += 4;
          break;
          i3 = 64;
          break label115;
        }
        return arrayOfChar1;
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.d
 * JD-Core Version:    0.6.0
 */