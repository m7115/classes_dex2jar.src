package com.squareup.okhttp.internal.okio;

import B;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public final class ByteString
{
  public static final ByteString EMPTY;
  private static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  final byte[] data;
  private transient int hashCode;
  private transient String utf8;

  static
  {
    EMPTY = of(new byte[0]);
  }

  ByteString(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
  }

  public static ByteString concat(ByteString[] paramArrayOfByteString)
  {
    int i = paramArrayOfByteString.length;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      k += paramArrayOfByteString[j].size();
      j++;
    }
    byte[] arrayOfByte = new byte[k];
    int m = paramArrayOfByteString.length;
    int n = 0;
    int i1 = 0;
    while (n < m)
    {
      ByteString localByteString = paramArrayOfByteString[n];
      System.arraycopy(localByteString.data, 0, arrayOfByte, i1, localByteString.size());
      i1 += localByteString.size();
      n++;
    }
    return new ByteString(arrayOfByte);
  }

  public static ByteString decodeBase64(String paramString)
  {
    byte[] arrayOfByte = Base64.decode(paramString);
    if (arrayOfByte != null)
      return new ByteString(arrayOfByte);
    return null;
  }

  public static ByteString decodeHex(String paramString)
  {
    if (paramString.length() % 2 != 0)
      throw new IllegalArgumentException("Unexpected hex string: " + paramString);
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    for (int i = 0; i < arrayOfByte.length; i++)
      arrayOfByte[i] = (byte)((decodeHexDigit(paramString.charAt(i * 2)) << 4) + decodeHexDigit(paramString.charAt(1 + i * 2)));
    return of(arrayOfByte);
  }

  private static int decodeHexDigit(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return 10 + (paramChar - 'a');
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return 10 + (paramChar - 'A');
    throw new IllegalArgumentException("Unexpected hex digit: " + paramChar);
  }

  public static ByteString encodeUtf8(String paramString)
  {
    try
    {
      ByteString localByteString = new ByteString(paramString.getBytes("UTF-8"));
      localByteString.utf8 = paramString;
      return localByteString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError(localUnsupportedEncodingException);
  }

  public static ByteString of(byte[] paramArrayOfByte)
  {
    return new ByteString((byte[])paramArrayOfByte.clone());
  }

  public static ByteString read(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j == -1)
        throw new EOFException();
      i += j;
    }
    return new ByteString(arrayOfByte);
  }

  public String base64()
  {
    return Base64.encode(this.data);
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof ByteString)) && (Arrays.equals(((ByteString)paramObject).data, this.data)));
  }

  public boolean equalsAscii(String paramString)
  {
    if ((paramString == null) || (this.data.length != paramString.length()))
      return false;
    if (paramString == this.utf8)
      return true;
    for (int i = 0; ; i++)
    {
      if (i >= this.data.length)
        break label59;
      if (this.data[i] != paramString.charAt(i))
        break;
    }
    label59: return true;
  }

  public int hashCode()
  {
    int i = this.hashCode;
    if (i != 0)
      return i;
    int j = Arrays.hashCode(this.data);
    this.hashCode = j;
    return j;
  }

  public String hex()
  {
    int i = 0;
    char[] arrayOfChar = new char[2 * this.data.length];
    byte[] arrayOfByte = this.data;
    int j = arrayOfByte.length;
    int k = 0;
    while (i < j)
    {
      int m = arrayOfByte[i];
      int n = k + 1;
      arrayOfChar[k] = HEX_DIGITS[(0xF & m >> 4)];
      k = n + 1;
      arrayOfChar[n] = HEX_DIGITS[(m & 0xF)];
      i++;
    }
    return new String(arrayOfChar);
  }

  public int size()
  {
    return this.data.length;
  }

  public ByteString toAsciiLowercase()
  {
    int i = 0;
    while (i < this.data.length)
    {
      int j = this.data[i];
      if ((j < 65) || (j > 90))
      {
        i++;
        continue;
      }
      byte[] arrayOfByte = (byte[])this.data.clone();
      int k = i + 1;
      arrayOfByte[i] = (byte)(j + 32);
      int m = k;
      if (m < arrayOfByte.length)
      {
        int n = arrayOfByte[m];
        if ((n < 65) || (n > 90));
        while (true)
        {
          m++;
          break;
          arrayOfByte[m] = (byte)(n + 32);
        }
      }
      this = new ByteString(arrayOfByte);
    }
    return this;
  }

  public byte[] toByteArray()
  {
    return (byte[])this.data.clone();
  }

  public String toString()
  {
    if (this.data.length == 0)
      return "ByteString[size=0]";
    if (this.data.length <= 16)
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(this.data.length);
      arrayOfObject2[1] = hex();
      return String.format("ByteString[size=%s data=%s]", arrayOfObject2);
    }
    try
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(this.data.length);
      arrayOfObject1[1] = of(MessageDigest.getInstance("MD5").digest(this.data)).hex();
      String str = String.format("ByteString[size=%s md5=%s]", arrayOfObject1);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new AssertionError();
  }

  public String utf8()
  {
    String str1 = this.utf8;
    if (str1 != null)
      return str1;
    try
    {
      String str2 = new String(this.data, "UTF-8");
      this.utf8 = str2;
      return str2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError(localUnsupportedEncodingException);
  }

  public void write(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.data);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.ByteString
 * JD-Core Version:    0.6.0
 */