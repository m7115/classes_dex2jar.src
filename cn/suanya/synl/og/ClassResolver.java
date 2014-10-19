package cn.suanya.synl.og;

import java.util.Map;

public abstract interface ClassResolver
{
  public abstract Class classForName(String paramString, Map paramMap)
    throws ClassNotFoundException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.ClassResolver
 * JD-Core Version:    0.6.0
 */