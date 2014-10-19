package com.baidu.platform.comapi.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class s<E> extends ArrayList<E>
{
  private q.a a = null;

  public void a(q.a parama)
  {
    this.a = parama;
  }

  public boolean add(E paramE)
  {
    if (paramE == null)
      return false;
    if (this.a != null)
      this.a.a(paramE);
    return super.add(paramE);
  }

  public boolean addAll(Collection<? extends E> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (this.a == null)
        continue;
      this.a.a(localObject);
    }
    return super.addAll(paramCollection);
  }

  public void clear()
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (this.a == null)
        continue;
      this.a.b(localObject);
    }
    super.clear();
  }

  public E remove(int paramInt)
  {
    Object localObject = super.remove(paramInt);
    if ((this.a != null) && (localObject != null))
      this.a.b(localObject);
    return localObject;
  }

  public boolean remove(Object paramObject)
  {
    if (paramObject == null)
      return false;
    if (this.a != null)
      this.a.b(paramObject);
    return super.remove(paramObject);
  }

  public boolean removeAll(Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (this.a == null)
        continue;
      this.a.b(localObject);
    }
    return super.removeAll(paramCollection);
  }

  public boolean retainAll(Collection<?> paramCollection)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if ((this.a == null) || (paramCollection.contains(localObject)))
        continue;
      this.a.b(localObject);
    }
    return super.retainAll(paramCollection);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.s
 * JD-Core Version:    0.6.0
 */