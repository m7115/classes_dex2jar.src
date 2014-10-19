package com.MobileTicket;

public class CheckCodeUtil
{
  static
  {
    System.loadLibrary("checkcode");
  }

  public static String checkcode(String paramString)
  {
    return checkcode("", paramString);
  }

  public static native String checkcode(String paramString1, String paramString2);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.MobileTicket.CheckCodeUtil
 * JD-Core Version:    0.6.0
 */