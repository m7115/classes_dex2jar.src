package com.squareup.okhttp.internal.http;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class HttpDate
{
  private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS;
  private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
  private static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT = new ThreadLocal()
  {
    protected DateFormat initialValue()
    {
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      return localSimpleDateFormat;
    }
  };

  static
  {
    BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = new String[] { "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z" };
    BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];
  }

  public static String format(Date paramDate)
  {
    return ((DateFormat)STANDARD_DATE_FORMAT.get()).format(paramDate);
  }

  public static Date parse(String paramString)
  {
    try
    {
      Date localDate2 = ((DateFormat)STANDARD_DATE_FORMAT.get()).parse(paramString);
      return localDate2;
    }
    catch (ParseException localParseException1)
    {
      while (true)
      {
        int j;
        Object localObject2;
        synchronized (BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS)
        {
          int i = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length;
          j = 0;
          if (j >= i)
            break;
          localObject2 = BROWSER_COMPATIBLE_DATE_FORMATS[j];
          if (localObject2 == null)
          {
            localObject2 = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[j], Locale.US);
            BROWSER_COMPATIBLE_DATE_FORMATS[j] = localObject2;
          }
        }
        try
        {
          Date localDate1 = ((DateFormat)localObject2).parse(paramString);
          monitorexit;
          return localDate1;
          localObject1 = finally;
          monitorexit;
          throw localObject1;
        }
        catch (ParseException localParseException2)
        {
          j++;
        }
      }
      monitorexit;
    }
    return (Date)null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpDate
 * JD-Core Version:    0.6.0
 */