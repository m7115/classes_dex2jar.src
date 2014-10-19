package com.squareup.okhttp.internal.okio;

import java.io.UnsupportedEncodingException;

final class Base64
{
  private static final byte[] MAP = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };

  public static byte[] decode(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte1;
    int j;
    int k;
    int m;
    int n;
    label78: int i6;
    int i10;
    label111: int i7;
    int i8;
    int i9;
    if (i > 0)
    {
      int i13 = paramString.charAt(i - 1);
      if ((i13 == 61) || (i13 == 10) || (i13 == 13) || (i13 == 32) || (i13 == 9));
    }
    else
    {
      arrayOfByte1 = new byte[(int)(6L * i / 8L)];
      j = 0;
      k = 0;
      m = 0;
      n = 0;
      if (j >= i)
        break label324;
      i6 = paramString.charAt(j);
      if ((i6 < 65) || (i6 > 90))
        break label204;
      i10 = i6 - 65;
      i7 = k << 6 | (byte)i10;
      i8 = m + 1;
      if (i8 % 4 != 0)
        break label447;
      int i11 = n + 1;
      arrayOfByte1[n] = (byte)(i7 >> 16);
      int i12 = i11 + 1;
      arrayOfByte1[i11] = (byte)(i7 >> 8);
      i9 = i12 + 1;
      arrayOfByte1[i12] = (byte)i7;
    }
    while (true)
    {
      j++;
      n = i9;
      m = i8;
      k = i7;
      break label78;
      i--;
      break;
      label204: if ((i6 >= 97) && (i6 <= 122))
      {
        i10 = i6 - 71;
        break label111;
      }
      if ((i6 >= 48) && (i6 <= 57))
      {
        i10 = i6 + 4;
        break label111;
      }
      if (i6 == 43)
      {
        i10 = 62;
        break label111;
      }
      if (i6 == 47)
      {
        i10 = 63;
        break label111;
      }
      if ((i6 != 10) && (i6 != 13) && (i6 != 32))
      {
        if (i6 == 9)
        {
          i7 = k;
          i8 = m;
          i9 = n;
          continue;
        }
        return null;
        label324: int i1 = m % 4;
        if (i1 == 1)
          return null;
        if (i1 == 2)
        {
          int i4 = k << 12;
          int i5 = n + 1;
          arrayOfByte1[n] = (byte)(i4 >> 16);
          n = i5;
        }
        while (n == arrayOfByte1.length)
        {
          return arrayOfByte1;
          if (i1 != 3)
            continue;
          int i2 = k << 6;
          int i3 = n + 1;
          arrayOfByte1[n] = (byte)(i2 >> 16);
          n = i3 + 1;
          arrayOfByte1[i3] = (byte)(i2 >> 8);
        }
        byte[] arrayOfByte2 = new byte[n];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, n);
        return arrayOfByte2;
        label447: i9 = n;
        continue;
      }
      i7 = k;
      i8 = m;
      i9 = n;
    }
  }

  public static String encode(byte[] paramArrayOfByte)
  {
    int i = 0;
    byte[] arrayOfByte = new byte[4 * (2 + paramArrayOfByte.length) / 3];
    int j = paramArrayOfByte.length - paramArrayOfByte.length % 3;
    int k = 0;
    while (k < j)
    {
      int i5 = i + 1;
      arrayOfByte[i] = MAP[((0xFF & paramArrayOfByte[k]) >> 2)];
      int i6 = i5 + 1;
      arrayOfByte[i5] = MAP[((0x3 & paramArrayOfByte[k]) << 4 | (0xFF & paramArrayOfByte[(k + 1)]) >> 4)];
      int i7 = i6 + 1;
      arrayOfByte[i6] = MAP[((0xF & paramArrayOfByte[(k + 1)]) << 2 | (0xFF & paramArrayOfByte[(k + 2)]) >> 6)];
      int i8 = i7 + 1;
      arrayOfByte[i7] = MAP[(0x3F & paramArrayOfByte[(k + 2)])];
      k += 3;
      i = i8;
    }
    switch (paramArrayOfByte.length % 3)
    {
    default:
    case 1:
    case 2:
    }
    try
    {
      while (true)
      {
        String str = new String(arrayOfByte, 0, i, "US-ASCII");
        return str;
        int i2 = i + 1;
        arrayOfByte[i] = MAP[((0xFF & paramArrayOfByte[j]) >> 2)];
        int i3 = i2 + 1;
        arrayOfByte[i2] = MAP[((0x3 & paramArrayOfByte[j]) << 4)];
        int i4 = i3 + 1;
        arrayOfByte[i3] = 61;
        i = i4 + 1;
        arrayOfByte[i4] = 61;
        continue;
        int m = i + 1;
        arrayOfByte[i] = MAP[((0xFF & paramArrayOfByte[j]) >> 2)];
        int n = m + 1;
        arrayOfByte[m] = MAP[((0x3 & paramArrayOfByte[j]) << 4 | (0xFF & paramArrayOfByte[(j + 1)]) >> 4)];
        int i1 = n + 1;
        arrayOfByte[n] = MAP[((0xF & paramArrayOfByte[(j + 1)]) << 2)];
        i = i1 + 1;
        arrayOfByte[i1] = 61;
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError(localUnsupportedEncodingException);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.Base64
 * JD-Core Version:    0.6.0
 */