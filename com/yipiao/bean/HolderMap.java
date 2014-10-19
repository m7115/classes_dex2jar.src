package com.yipiao.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class HolderMap extends HashMap<String, ObjHolder>
{
  private static final long serialVersionUID = 1L;

  public ObjHolder put(String paramString, ObjHolder paramObjHolder)
  {
    Iterator localIterator = entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if ((localEntry.getValue() == null) && (!((ObjHolder)localEntry.getValue()).hasOutTime()))
        continue;
      remove(localEntry.getKey());
    }
    return (ObjHolder)super.put(paramString, paramObjHolder);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.HolderMap
 * JD-Core Version:    0.6.0
 */