package cn.suanya.common.a;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.suanya.common.ui.SYApplication;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class v
{
  static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
  static SimpleDateFormat b = new SimpleDateFormat("yyyyMMdd");
  static SimpleDateFormat c = new SimpleDateFormat("MM月dd日");
  static String[] d = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

  public static int a()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)SYApplication.app.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return 0;
    if (localNetworkInfo.getType() == 1)
      return 1;
    if (localNetworkInfo.getType() == 0)
      return 2;
    return -1;
  }

  public static String a(long paramLong)
  {
    long l1 = paramLong / 1000L;
    long l2 = l1 % 60L;
    long l3 = l1 / 60L;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(l3);
    arrayOfObject[1] = Long.valueOf(l2);
    return MessageFormat.format("{0}分{1}秒", arrayOfObject);
  }

  public static String a(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return paramObject.toString();
  }

  public static String a(String paramString)
  {
    try
    {
      Date localDate = a.parse(paramString);
      paramString = c.format(localDate);
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(localDate);
      String str = paramString + " " + d[(-1 + localCalendar.get(7))];
      return str;
    }
    catch (ParseException localParseException)
    {
    }
    return paramString;
  }

  public static boolean b(String paramString)
  {
    if ((paramString == null) || (paramString.trim().length() == 0))
      return false;
    return Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*").matcher(paramString).matches();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.v
 * JD-Core Version:    0.6.0
 */