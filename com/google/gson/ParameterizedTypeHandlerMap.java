package com.google.gson;

import com.google.gson.internal..Gson.Types;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ParameterizedTypeHandlerMap<T>
{
  private static final Logger logger = Logger.getLogger(ParameterizedTypeHandlerMap.class.getName());
  private final Map<Type, T> map = new HashMap();
  private boolean modifiable = true;
  private final List<Pair<Class<?>, T>> typeHierarchyList = new ArrayList();

  private T getHandlerForTypeHierarchy(Class<?> paramClass)
  {
    Iterator localIterator = this.typeHierarchyList.iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      if (((Class)localPair.first).isAssignableFrom(paramClass))
        return localPair.second;
    }
    return null;
  }

  private int getIndexOfAnOverriddenHandler(Class<?> paramClass)
  {
    for (int i = -1 + this.typeHierarchyList.size(); i >= 0; i--)
      if (paramClass.isAssignableFrom((Class)((Pair)this.typeHierarchyList.get(i)).first))
        return i;
    return -1;
  }

  private int getIndexOfSpecificHandlerForTypeHierarchy(Class<?> paramClass)
  {
    monitorenter;
    try
    {
      int i = -1 + this.typeHierarchyList.size();
      if (i >= 0)
      {
        boolean bool = paramClass.equals(((Pair)this.typeHierarchyList.get(i)).first);
        if (!bool);
      }
      for (int j = i; ; j = -1)
      {
        return j;
        i--;
        break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private String typeToString(Type paramType)
  {
    return .Gson.Types.getRawType(paramType).getSimpleName();
  }

  public ParameterizedTypeHandlerMap<T> copyOf()
  {
    monitorenter;
    try
    {
      ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
      localParameterizedTypeHandlerMap.map.putAll(this.map);
      localParameterizedTypeHandlerMap.typeHierarchyList.addAll(this.typeHierarchyList);
      monitorexit;
      return localParameterizedTypeHandlerMap;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public T getHandlerFor(Type paramType)
  {
    monitorenter;
    try
    {
      Object localObject2 = this.map.get(paramType);
      if (localObject2 == null)
      {
        Class localClass = .Gson.Types.getRawType(paramType);
        if (localClass != paramType)
          localObject2 = getHandlerFor(localClass);
        if (localObject2 == null)
        {
          Object localObject3 = getHandlerForTypeHierarchy(localClass);
          localObject2 = localObject3;
        }
      }
      return localObject2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public boolean hasSpecificHandlerFor(Type paramType)
  {
    monitorenter;
    try
    {
      boolean bool = this.map.containsKey(paramType);
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void makeUnmodifiable()
  {
    monitorenter;
    try
    {
      this.modifiable = false;
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

  public void register(ParameterizedTypeHandlerMap<T> paramParameterizedTypeHandlerMap)
  {
    monitorenter;
    try
    {
      if (!this.modifiable)
        throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    finally
    {
      monitorexit;
    }
    Iterator localIterator = paramParameterizedTypeHandlerMap.map.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      register((Type)localEntry.getKey(), localEntry.getValue());
    }
    for (int i = -1 + paramParameterizedTypeHandlerMap.typeHierarchyList.size(); i >= 0; i--)
      registerForTypeHierarchy((Pair)paramParameterizedTypeHandlerMap.typeHierarchyList.get(i));
    monitorexit;
  }

  public void register(Type paramType, T paramT)
  {
    monitorenter;
    try
    {
      if (!this.modifiable)
        throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    finally
    {
      monitorexit;
    }
    if (hasSpecificHandlerFor(paramType))
      logger.log(Level.WARNING, "Overriding the existing type handler for {0}", paramType);
    this.map.put(paramType, paramT);
    monitorexit;
  }

  public void registerForTypeHierarchy(Pair<Class<?>, T> paramPair)
  {
    monitorenter;
    try
    {
      if (!this.modifiable)
        throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    finally
    {
      monitorexit;
    }
    int i = getIndexOfSpecificHandlerForTypeHierarchy((Class)paramPair.first);
    if (i >= 0)
    {
      logger.log(Level.WARNING, "Overriding the existing type handler for {0}", paramPair.first);
      this.typeHierarchyList.remove(i);
    }
    int j = getIndexOfAnOverriddenHandler((Class)paramPair.first);
    if (j >= 0)
      throw new IllegalArgumentException("The specified type handler for type " + paramPair.first + " hides the previously registered type hierarchy handler for " + ((Pair)this.typeHierarchyList.get(j)).first + ". Gson does not allow this.");
    this.typeHierarchyList.add(0, paramPair);
    monitorexit;
  }

  public void registerForTypeHierarchy(Class<?> paramClass, T paramT)
  {
    monitorenter;
    try
    {
      registerForTypeHierarchy(new Pair(paramClass, paramT));
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

  public void registerIfAbsent(ParameterizedTypeHandlerMap<T> paramParameterizedTypeHandlerMap)
  {
    monitorenter;
    try
    {
      if (!this.modifiable)
        throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    finally
    {
      monitorexit;
    }
    Iterator localIterator = paramParameterizedTypeHandlerMap.map.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (this.map.containsKey(localEntry.getKey()))
        continue;
      register((Type)localEntry.getKey(), localEntry.getValue());
    }
    for (int i = -1 + paramParameterizedTypeHandlerMap.typeHierarchyList.size(); i >= 0; i--)
    {
      Pair localPair = (Pair)paramParameterizedTypeHandlerMap.typeHierarchyList.get(i);
      if (getIndexOfSpecificHandlerForTypeHierarchy((Class)localPair.first) >= 0)
        continue;
      registerForTypeHierarchy(localPair);
    }
    monitorexit;
  }

  public void registerIfAbsent(Type paramType, T paramT)
  {
    monitorenter;
    try
    {
      if (!this.modifiable)
        throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    finally
    {
      monitorexit;
    }
    if (!this.map.containsKey(paramType))
      register(paramType, paramT);
    monitorexit;
  }

  public String toString()
  {
    int i = 1;
    StringBuilder localStringBuilder = new StringBuilder("{mapForTypeHierarchy:{");
    Iterator localIterator1 = this.typeHierarchyList.iterator();
    int j = i;
    if (localIterator1.hasNext())
    {
      Pair localPair = (Pair)localIterator1.next();
      if (j != 0);
      for (int k = 0; ; k = j)
      {
        localStringBuilder.append(typeToString((Type)localPair.first)).append(':');
        localStringBuilder.append(localPair.second);
        j = k;
        break;
        localStringBuilder.append(',');
      }
    }
    localStringBuilder.append("},map:{");
    Iterator localIterator2 = this.map.entrySet().iterator();
    if (localIterator2.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator2.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(typeToString((Type)localEntry.getKey())).append(':');
        localStringBuilder.append(localEntry.getValue());
        break;
        localStringBuilder.append(',');
      }
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ParameterizedTypeHandlerMap
 * JD-Core Version:    0.6.0
 */