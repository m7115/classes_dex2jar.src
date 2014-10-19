package cn.suanya.rule;

import cn.suanya.common.a.e;
import cn.suanya.rule.a.a;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class o
{
  public static Object a(a parama, String paramString1, String paramString2, int paramInt, Class paramClass)
    throws Exception
  {
    return a(parama, paramString1, paramString2, paramInt, paramClass.newInstance());
  }

  public static Object a(a parama, String paramString1, String paramString2, int paramInt, Object paramObject)
    throws Exception
  {
    if (paramString1 == null)
      paramObject = null;
    while (true)
    {
      return paramObject;
      e locale = new e(paramString2);
      Matcher localMatcher = Pattern.compile(locale.b, 32).matcher(paramString1);
      if (!localMatcher.find(paramInt))
        break;
      for (int i = 0; i < localMatcher.groupCount(); i++)
      {
        if (localMatcher.group(i + 1) == null)
          continue;
        parama.a((String)locale.c.get(i), paramObject, localMatcher.group(i + 1));
      }
    }
    return null;
  }

  public static <T> List<T> b(a parama, String paramString1, String paramString2, int paramInt, Class<T> paramClass)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString1 == null)
      return localArrayList;
    e locale = new e(paramString2);
    Matcher localMatcher = Pattern.compile(locale.b, 32).matcher(paramString1);
    while (localMatcher.find())
    {
      Object localObject = paramClass.newInstance();
      for (int i = 0; i < localMatcher.groupCount(); i++)
      {
        if (localMatcher.group(i + 1) == null)
          continue;
        parama.a((String)locale.c.get(i), localObject, localMatcher.group(i + 1));
      }
      localArrayList.add(localObject);
    }
    return localArrayList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.o
 * JD-Core Version:    0.6.0
 */