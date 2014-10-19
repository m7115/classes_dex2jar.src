package cn.suanya.synl.og;

import java.util.Map;

public abstract interface NullHandler
{
  public abstract Object nullMethodResult(Map paramMap, Object paramObject, String paramString, Object[] paramArrayOfObject);

  public abstract Object nullPropertyValue(Map paramMap, Object paramObject1, Object paramObject2);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.NullHandler
 * JD-Core Version:    0.6.0
 */