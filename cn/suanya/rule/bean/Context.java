package cn.suanya.rule.bean;

import java.util.HashMap;

public class Context extends HashMap<String, Object>
{
  private static final long serialVersionUID = 1L;

  public int getInt(String paramString)
  {
    try
    {
      int i = Integer.parseInt(get(paramString).toString());
      return i;
    }
    catch (Exception localException)
    {
    }
    return 0;
  }

  public String getStr(String paramString)
  {
    Object localObject = get(paramString);
    if (localObject == null)
      return "";
    return localObject.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.bean.Context
 * JD-Core Version:    0.6.0
 */