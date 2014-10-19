package cn.suanya.rule.bean;

import cn.suanya.common.a.a;
import cn.suanya.common.a.n;
import cn.suanya.common.a.s;
import cn.suanya.common.a.t;
import cn.suanya.common.bean.NameValue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class SyFunction
{
  static SyFunction f = new SyFunction();
  static SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static SyFunction getInstance()
  {
    return f;
  }

  public int add(int paramInt1, int paramInt2)
  {
    return paramInt1 + paramInt2;
  }

  public long addlong(long paramLong, int paramInt)
  {
    return paramLong + paramInt;
  }

  public String base64Decoder(String paramString)
  {
    return new String(a.a(paramString));
  }

  public String base64Decoder(String paramString1, String paramString2)
  {
    try
    {
      String str = new String(a.a(paramString1), paramString2);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString1;
  }

  public String base64Encoder(InputStream paramInputStream)
  {
    try
    {
      String str = a.a(s.a(paramInputStream).toByteArray());
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return "";
  }

  public String base64Encoder(String paramString)
  {
    return a.a(paramString.getBytes());
  }

  public String base64Encoder(String paramString1, String paramString2)
  {
    try
    {
      String str = a.a(paramString1.getBytes(paramString2));
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString1;
  }

  public int collectionSize(Object paramObject)
  {
    if (paramObject == null);
    do
    {
      return -1;
      if (paramObject.getClass().isArray())
        return ((Object[])(Object[])paramObject).length;
      if ((paramObject instanceof Collection))
        return ((Collection)paramObject).size();
    }
    while (!(paramObject instanceof JSONArray));
    return ((JSONArray)paramObject).length();
  }

  public int compare(String paramString1, String paramString2)
  {
    return paramString1.compareTo(paramString2);
  }

  public boolean contain(Object paramObject1, Object paramObject2)
  {
    int i = 1;
    if (paramObject1 == null);
    while (true)
    {
      return false;
      if (paramObject2 == null)
        continue;
      if ((paramObject1 instanceof String))
      {
        if (((String)paramObject1).indexOf(paramObject2.toString()) >= 0);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      if ((paramObject1 instanceof Collection))
        return ((Collection)paramObject1).contains(paramObject2);
      if (!paramObject1.getClass().isArray())
        continue;
      Object[] arrayOfObject = (Object[])(Object[])paramObject1;
      for (int j = 0; j < arrayOfObject.length; j++)
        if (paramObject2.equals(arrayOfObject[j]))
          return i;
    }
  }

  public long currentMillis()
  {
    return System.currentTimeMillis();
  }

  public String currentTimeMillis()
  {
    return Long.toString(System.currentTimeMillis());
  }

  public String dFormat(String paramString, Date paramDate)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return paramDate.toString();
    return new SimpleDateFormat(paramString, Locale.CHINA).format(paramDate);
  }

  public String dFormat(String paramString1, Date paramDate, String paramString2, String paramString3)
  {
    if ((paramString3 != null) && (paramString3.length() > 0));
    for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString1, new Locale(paramString2)); ; localSimpleDateFormat = new SimpleDateFormat(paramString1, Locale.getDefault()))
    {
      if ((paramString3 != null) && (paramString3.length() > 0))
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone(paramString3));
      return localSimpleDateFormat.format(paramDate);
    }
  }

  public String dFormatUS(String paramString, Date paramDate)
  {
    return new SimpleDateFormat(paramString, Locale.US).format(paramDate);
  }

  public Date dParse(String paramString1, String paramString2)
    throws ParseException
  {
    return new SimpleDateFormat(paramString1, Locale.CHINA).parse(paramString2);
  }

  public Date dParse(String paramString1, String paramString2, String paramString3, String paramString4)
    throws ParseException
  {
    if ((paramString4 != null) && (paramString4.length() > 0));
    for (SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString1, new Locale(paramString3)); ; localSimpleDateFormat = new SimpleDateFormat(paramString1, Locale.getDefault()))
    {
      if ((paramString4 != null) && (paramString4.length() > 0))
        localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone(paramString4));
      return localSimpleDateFormat.parse(paramString2);
    }
  }

  public Date dateAdd(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    localCalendar.add(5, paramInt);
    return localCalendar.getTime();
  }

  public long dateToMillis(Date paramDate)
  {
    return paramDate.getTime();
  }

  public String decrypt(String paramString1, String paramString2)
    throws Exception
  {
    return t.b(paramString2, paramString1);
  }

  public String deleteRegex(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return "";
    return paramString1.replaceAll(paramString2, "");
  }

  public String deleteStr(String paramString1, String paramString2)
  {
    return paramString1.replace(paramString2, "");
  }

  public String deleteTail(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.length() <= paramInt))
      return "";
    return paramString.substring(0, paramString.length() - paramInt);
  }

  public String encrypt(String paramString1, String paramString2)
    throws Exception
  {
    return t.a(paramString2, paramString1);
  }

  public boolean endsWith(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return false;
    return paramString1.endsWith(paramString2);
  }

  public boolean equal(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null);
    do
      return false;
    while (paramObject2 == null);
    if (paramObject1.equals(paramObject2))
      return true;
    return paramObject1.toString().equals(paramObject2.toString());
  }

  public String format(String paramString, Object[] paramArrayOfObject)
  {
    return MessageFormat.format(paramString, paramArrayOfObject);
  }

  public Date getDate(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date());
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    localCalendar.add(5, paramInt);
    return localCalendar.getTime();
  }

  public Object instance(String paramString)
  {
    try
    {
      Object localObject = getClass().getClassLoader().loadClass(paramString).newInstance();
      return localObject;
    }
    catch (Exception localException)
    {
      n.b(localException);
    }
    return new HashMap();
  }

  public boolean isBlank(Object paramObject)
  {
    if (paramObject == null);
    while (true)
    {
      return true;
      if ((paramObject instanceof String))
      {
        if ((((String)paramObject).trim().length() == 0) || ("null".equals(paramObject)));
        for (int i = 1; ; i = 0)
          return i;
      }
      if ((paramObject instanceof Collection))
        return ((Collection)paramObject).isEmpty();
      if ((paramObject instanceof JSONArray))
        if (((JSONArray)paramObject).length() != 0)
          return false;
      if (!paramObject.getClass().isArray())
        break;
      if (((Object[])(Object[])paramObject).length != 0)
        return false;
    }
    return false;
  }

  public boolean isJsonObject(Object paramObject)
  {
    if (paramObject == null);
    do
      return false;
    while (!(paramObject instanceof JSONObject));
    return true;
  }

  public boolean isNull(Object paramObject)
  {
    return paramObject == null;
  }

  public boolean isObject(Object paramObject)
  {
    if (paramObject == null);
    do
      return false;
    while ((paramObject.getClass().isArray()) || ((paramObject instanceof Collection)) || ((paramObject instanceof JSONArray)));
    return true;
  }

  public int length(Object paramObject)
  {
    if (paramObject == null)
      return -1;
    if ((paramObject instanceof String))
      return ((String)paramObject).length();
    return collectionSize(paramObject);
  }

  public String linkCode(Object paramObject, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramObject instanceof List))
    {
      Iterator localIterator = ((List)paramObject).iterator();
      while (localIterator.hasNext())
        localStringBuilder.append((String)localIterator.next()).append(paramString);
    }
    String[] arrayOfString = (String[])(String[])paramObject;
    for (int i = 0; i < arrayOfString.length; i++)
      localStringBuilder.append(arrayOfString[i]).append(paramString);
    return localStringBuilder.toString();
  }

  public void log(Object paramObject)
  {
    if (paramObject == null)
    {
      n.b("null");
      return;
    }
    n.b(paramObject.toString());
  }

  public boolean matches(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return false;
    return paramString1.matches(paramString2);
  }

  public Date millisToDate(long paramLong)
  {
    return new Date(paramLong);
  }

  public int mod(int paramInt1, int paramInt2)
  {
    return paramInt1 % paramInt2;
  }

  public Object[] newArray(int paramInt)
  {
    return new Object[paramInt];
  }

  public int random(int paramInt)
  {
    return (int)(Math.random() * paramInt);
  }

  public String random()
  {
    return String.valueOf(Math.random());
  }

  public void removeNV(List<NameValue> paramList, String paramString)
  {
    if (paramList == null);
    Iterator localIterator;
    do
    {
      return;
      while (!localIterator.hasNext())
        localIterator = paramList.iterator();
    }
    while (!paramString.equals(((NameValue)localIterator.next()).getName()));
    localIterator.remove();
  }

  public String replaceRegex(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      return "";
    return paramString1.replaceAll(paramString2, paramString3);
  }

  public String replaceStr(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      return "";
    return paramString1.replace(paramString2, paramString3);
  }

  public void sleep(int paramInt)
  {
    if (paramInt <= 0)
      return;
    long l = paramInt;
    try
    {
      Thread.sleep(l);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }

  public String[] split(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return new String[0];
    return paramString1.split(paramString2);
  }

  public List<String> splitToList(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return new ArrayList();
    String[] arrayOfString = paramString1.split(paramString2);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < arrayOfString.length; i++)
      localArrayList.add(arrayOfString[i]);
    return localArrayList;
  }

  public boolean startsWith(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return false;
    return paramString1.startsWith(paramString2);
  }

  public String subStr(String paramString, int paramInt)
  {
    return paramString.substring(paramInt);
  }

  public String subStr(String paramString, int paramInt1, int paramInt2)
  {
    return paramString.substring(paramInt1, paramInt2);
  }

  public int toInt(double paramDouble)
  {
    return (int)paramDouble;
  }

  public String toJsonStr(JSONObject paramJSONObject)
  {
    return paramJSONObject.toString();
  }

  public String todate()
  {
    return ymdFormat.format(new Date());
  }

  public String trim(String paramString)
  {
    if (paramString == null)
      return "";
    return paramString.trim();
  }

  public String urlDecoder(String paramString)
  {
    try
    {
      String str = URLDecoder.decode(paramString, "utf-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString;
  }

  public String urlDecoder(String paramString1, String paramString2)
  {
    try
    {
      String str = URLDecoder.decode(paramString1, paramString2);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString1;
  }

  public String urlEncoder(String paramString)
  {
    return URLEncoder.encode(paramString);
  }

  public String urlEncoder(String paramString1, String paramString2)
  {
    try
    {
      String str = URLEncoder.encode(paramString1, paramString2);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return URLEncoder.encode(paramString1);
  }

  public String valueFromNV(List<NameValue> paramList, String paramString)
  {
    if (paramList == null)
      return "";
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      NameValue localNameValue = (NameValue)localIterator.next();
      if (paramString.equals(localNameValue.getName()))
        return localNameValue.getValue();
    }
    return "";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.bean.SyFunction
 * JD-Core Version:    0.6.0
 */