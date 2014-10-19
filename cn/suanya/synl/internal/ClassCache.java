package cn.suanya.synl.internal;

import cn.suanya.synl.og.ClassCacheInspector;

public abstract interface ClassCache
{
  public abstract void clear();

  public abstract Object get(Class paramClass);

  public abstract int getSize();

  public abstract Object put(Class paramClass, Object paramObject);

  public abstract void setClassInspector(ClassCacheInspector paramClassCacheInspector);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.internal.ClassCache
 * JD-Core Version:    0.6.0
 */