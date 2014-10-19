package com.google.gson;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

final class LruCache<K, V> extends LinkedHashMap<K, V>
  implements Cache<K, V>
{
  private static final long serialVersionUID = 1L;
  private final int maxCapacity;

  public LruCache(int paramInt)
  {
    super(paramInt, 0.7F, true);
    this.maxCapacity = paramInt;
  }

  public void addElement(K paramK, V paramV)
  {
    monitorenter;
    try
    {
      put(paramK, paramV);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public V getElement(K paramK)
  {
    monitorenter;
    try
    {
      Object localObject2 = get(paramK);
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }

  protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
  {
    return size() > this.maxCapacity;
  }

  public V removeElement(K paramK)
  {
    monitorenter;
    try
    {
      Object localObject2 = remove(paramK);
      monitorexit;
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.LruCache
 * JD-Core Version:    0.6.0
 */