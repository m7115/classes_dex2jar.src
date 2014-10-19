package com.baidu.location;

class Jni
{
  private static int a;
  private static int jdField_byte;
  private static int jdField_case = 0;
  private static int jdField_do;
  private static int jdField_for;
  private static int jdField_if;
  private static int jdField_int;
  private static boolean jdField_new;
  private static int jdField_try = 1;

  static
  {
    jdField_byte = 2;
    jdField_for = 11;
    jdField_int = 12;
    jdField_if = 13;
    a = 14;
    jdField_do = 1024;
    jdField_new = false;
    try
    {
      System.loadLibrary("locSDK3");
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      localUnsatisfiedLinkError.printStackTrace();
      jdField_new = true;
    }
  }

  private static native String a(byte[] paramArrayOfByte, int paramInt);

  private static native String b(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

  public static String jdField_if(String paramString)
  {
    int i = 740;
    int j = 0;
    if (jdField_new)
      return "err!";
    byte[] arrayOfByte1 = paramString.getBytes();
    byte[] arrayOfByte2 = new byte[jdField_do];
    int k = arrayOfByte1.length;
    if (k > i);
    while (true)
    {
      int m = 0;
      if (j < i)
      {
        if (arrayOfByte1[j] != 0)
        {
          arrayOfByte2[m] = arrayOfByte1[j];
          m++;
        }
        while (true)
        {
          j++;
          break;
          j.jdField_if("baidu_location_service", "\\0 found in string");
        }
      }
      j.jdField_if("baidu_location_service", "number:" + arrayOfByte1.length);
      return a(arrayOfByte2, 132456) + "|tp=3";
      i = k;
    }
  }

  public static double[] jdField_if(double paramDouble1, double paramDouble2, String paramString)
  {
    double[] arrayOfDouble = { 0.0D, 0.0D };
    if (jdField_new)
      return arrayOfDouble;
    int i = -1;
    if (paramString.equals("bd09"))
      i = jdField_case;
    while (true)
    {
      j.jdField_if("baidu_location_service", "type:" + i);
      try
      {
        String[] arrayOfString = b(paramDouble1, paramDouble2, i, 132456).split(":");
        arrayOfDouble[0] = Double.parseDouble(arrayOfString[0]);
        arrayOfDouble[1] = Double.parseDouble(arrayOfString[1]);
        return arrayOfDouble;
        if (paramString.equals("bd09ll"))
        {
          i = jdField_try;
          continue;
        }
        if (paramString.equals("gcj02"))
        {
          i = jdField_byte;
          continue;
        }
        if (paramString.equals("gps2gcj"))
        {
          i = jdField_for;
          continue;
        }
        if (paramString.equals("bd092gcj"))
        {
          i = jdField_int;
          continue;
        }
        if (!paramString.equals("bd09ll2gcj"))
          continue;
        i = jdField_if;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.Jni
 * JD-Core Version:    0.6.0
 */