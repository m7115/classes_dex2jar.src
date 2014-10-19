package cn.suanya.common.a;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class c
{
  public static SimpleDateFormat MMdd;
  public static final String[] dateDisplay;
  public static final String[] weekDisplay;
  public static SimpleDateFormat yyMMdd;
  public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

  static
  {
    yyMMdd = new SimpleDateFormat("yy-MM-dd", Locale.CHINA);
    MMdd = new SimpleDateFormat("MM月dd日", Locale.CHINA);
    weekDisplay = new String[] { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    dateDisplay = new String[] { "今天", "明天", "后天" };
  }

  public static Date addDays(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(5, paramInt);
    return localCalendar.getTime();
  }

  public static int daysBetween(Date paramDate1, Date paramDate2)
  {
    return (int)((roundDate(paramDate2).getTime() - roundDate(paramDate1).getTime()) / 86400000L);
  }

  public static String formart(Date paramDate, String paramString)
  {
    return new SimpleDateFormat(paramString, Locale.CHINA).format(paramDate);
  }

  public static String formartDate(Date paramDate)
  {
    return yyyyMMdd.format(paramDate);
  }

  public static String formartMMdd(Date paramDate)
  {
    return MMdd.format(paramDate);
  }

  public static String formartMMddW(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    int i = localCalendar.get(7);
    return MMdd.format(paramDate) + " " + weekDisplay[(i - 1)];
  }

  public static String getWeek(Date paramDate)
  {
    int i = daysBetween(new Date(), paramDate);
    if ((i >= 0) && (i <= 2))
      return dateDisplay[i];
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    int j = localCalendar.get(7);
    return weekDisplay[(j - 1)];
  }

  public static boolean isAfterTomorrow(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    localCalendar.setTime(paramDate);
    localCalendar.add(5, -2);
    return (i == localCalendar.get(1)) && (j == localCalendar.get(2)) && (k == localCalendar.get(5));
  }

  public static boolean isToday(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    localCalendar.setTime(paramDate);
    return (i == localCalendar.get(1)) && (j == localCalendar.get(2)) && (k == localCalendar.get(5));
  }

  public static boolean isTomorrow(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    localCalendar.setTime(paramDate);
    localCalendar.add(5, -1);
    return (i == localCalendar.get(1)) && (j == localCalendar.get(2)) && (k == localCalendar.get(5));
  }

  public static Date parseDate(String paramString)
  {
    try
    {
      Date localDate = yyyyMMdd.parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
      n.b(localParseException);
    }
    return new Date();
  }

  public static Date roundDate(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    return localCalendar.getTime();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.c
 * JD-Core Version:    0.6.0
 */