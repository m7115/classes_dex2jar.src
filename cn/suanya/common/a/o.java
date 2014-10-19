package cn.suanya.common.a;

import java.util.HashMap;

public class o extends HashMap<String, Object>
{
  private static final long serialVersionUID = -849793937315467589L;

  public String a(String paramString1, String paramString2)
  {
    Object localObject = get(paramString1);
    if (localObject == null)
      return paramString2;
    try
    {
      String str = (String)localObject;
      return str;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return paramString2;
  }

  public boolean a(String paramString)
  {
    return a(paramString, false);
  }

  public boolean a(String paramString, boolean paramBoolean)
  {
    Object localObject = get(paramString);
    if (localObject == null)
      return paramBoolean;
    try
    {
      boolean bool = ((Boolean)localObject).booleanValue();
      return bool;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return paramBoolean;
  }

  public String b(String paramString)
  {
    return a(paramString, null);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.o
 * JD-Core Version:    0.6.0
 */