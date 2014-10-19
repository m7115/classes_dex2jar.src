package com.squareup.okhttp.internal.okio;

final class Util
{
  public static final String UTF_8 = "UTF-8";

  public static void checkOffsetAndCount(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) < 0L) || (paramLong2 > paramLong1) || (paramLong1 - paramLong2 < paramLong3))
      throw new ArrayIndexOutOfBoundsException();
  }

  public static int reverseBytesInt(int paramInt)
  {
    return (0xFF000000 & paramInt) >>> 24 | (0xFF0000 & paramInt) >>> 8 | (0xFF00 & paramInt) << 8 | (paramInt & 0xFF) << 24;
  }

  public static int reverseBytesShort(short paramShort)
  {
    int i = 0xFFFF & paramShort;
    return (0xFF00 & i) >>> 8 | (i & 0xFF) << 8;
  }

  public static void sneakyRethrow(Throwable paramThrowable)
  {
    sneakyThrow2(paramThrowable);
  }

  private static <T extends Throwable> void sneakyThrow2(Throwable paramThrowable)
    throws Throwable
  {
    throw paramThrowable;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.okio.Util
 * JD-Core Version:    0.6.0
 */