package com.tencent.mm.a;

public final class b
{
  public static byte[] a(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    for (int i = 0; i < 4; i++)
      arrayOfByte[i] = (byte)(0xFF & paramInt >> i * 8);
    return arrayOfByte;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.a.b
 * JD-Core Version:    0.6.0
 */