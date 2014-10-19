package com.example.pathview.util;

import android.annotation.SuppressLint;
import cn.suanya.common.a.c;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil extends c
{
  public static String addMins(String paramString, int paramInt)
  {
    return minute2HHmm(paramInt + getMinsByStr(paramString));
  }

  public static int getCurrentMins()
  {
    Calendar localCalendar = Calendar.getInstance();
    return localCalendar.get(12) + 60 * localCalendar.get(11);
  }

  @SuppressLint({"SimpleDateFormat"})
  public static String getCurrentTime()
  {
    return new SimpleDateFormat("HH:mm").format(new Date());
  }

  public static int getMinsByStr(String paramString)
  {
    if (paramString == null);
    String[] arrayOfString;
    do
    {
      return 0;
      arrayOfString = paramString.split(":");
    }
    while (arrayOfString.length != 2);
    try
    {
      int i = 60 * Integer.parseInt(arrayOfString[0]);
      int j = Integer.parseInt(arrayOfString[1]);
      return j + i;
    }
    catch (Exception localException)
    {
    }
    return 0;
  }

  public static int getTimeDef(int paramInt1, int paramInt2)
  {
    int i = paramInt2 - paramInt1;
    if (i < 0)
      i += 1440;
    return i;
  }

  public static boolean isBeweenTime(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == paramInt3);
    do
      return false;
    while (getTimeDef(paramInt2, paramInt3) != getTimeDef(paramInt2, paramInt1) + getTimeDef(paramInt1, paramInt3));
    return true;
  }

  public static boolean isBeweenTime(String paramString1, String paramString2, String paramString3)
  {
    return isBeweenTime(getMinsByStr(paramString1), getMinsByStr(paramString2), getMinsByStr(paramString3));
  }

  public static String minute2HHmm(int paramInt)
  {
    if (paramInt < 0)
      paramInt += 1440;
    if (paramInt < 0)
      return "";
    int i = paramInt % 60;
    int j = paramInt / 60 % 24;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(j);
    arrayOfObject[1] = Integer.valueOf(i);
    return MessageFormat.format("{0,number,00}:{1,number,00}", arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.util.TimeUtil
 * JD-Core Version:    0.6.0
 */