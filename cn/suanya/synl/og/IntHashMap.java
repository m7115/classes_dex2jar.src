package cn.suanya.synl.og;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class IntHashMap
  implements Map
{
  private int count;
  private float loadFactor;
  private Entry[] table;
  private int threshold;

  public IntHashMap()
  {
    this(101, 0.75F);
  }

  public IntHashMap(int paramInt)
  {
    this(paramInt, 0.75F);
  }

  public IntHashMap(int paramInt, float paramFloat)
  {
    if ((paramInt <= 0) || (paramFloat <= 0.0D))
      throw new IllegalArgumentException();
    this.loadFactor = paramFloat;
    this.table = new Entry[paramInt];
    this.threshold = (int)(paramFloat * paramInt);
  }

  public void clear()
  {
    Entry[] arrayOfEntry = this.table;
    int i = arrayOfEntry.length;
    while (true)
    {
      i--;
      if (i < 0)
        break;
      arrayOfEntry[i] = null;
    }
    this.count = 0;
  }

  public final boolean containsKey(int paramInt)
  {
    int i = (0x7FFFFFFF & paramInt) % this.table.length;
    for (Entry localEntry = this.table[i]; localEntry != null; localEntry = localEntry.next)
      if ((localEntry.hash == paramInt) && (localEntry.key == paramInt))
        return true;
    return false;
  }

  public boolean containsKey(Object paramObject)
  {
    if (!(paramObject instanceof Number))
      throw new InternalError("key is not an Number subclass");
    return containsKey(((Number)paramObject).intValue());
  }

  public boolean containsValue(Object paramObject)
  {
    Entry[] arrayOfEntry = this.table;
    if (paramObject == null)
      throw new IllegalArgumentException();
    int j;
    for (int i = arrayOfEntry.length; ; i = j)
    {
      j = i - 1;
      if (i <= 0)
        break;
      for (Entry localEntry = arrayOfEntry[j]; localEntry != null; localEntry = localEntry.next)
        if (localEntry.value.equals(paramObject))
          return true;
    }
    return false;
  }

  public Set entrySet()
  {
    throw new UnsupportedOperationException("entrySet");
  }

  public final Object get(int paramInt)
  {
    int i = (0x7FFFFFFF & paramInt) % this.table.length;
    for (Entry localEntry = this.table[i]; localEntry != null; localEntry = localEntry.next)
      if ((localEntry.hash == paramInt) && (localEntry.key == paramInt))
        return localEntry.value;
    return null;
  }

  public Object get(Object paramObject)
  {
    if (!(paramObject instanceof Number))
      throw new IllegalArgumentException("key is not an Number subclass");
    return get(((Number)paramObject).intValue());
  }

  public boolean isEmpty()
  {
    return this.count == 0;
  }

  public Set keySet()
  {
    HashSet localHashSet = new HashSet();
    IntHashMapIterator localIntHashMapIterator = new IntHashMapIterator(this.table, true);
    while (localIntHashMapIterator.hasNext())
      localHashSet.add(localIntHashMapIterator.next());
    return localHashSet;
  }

  public final Object put(int paramInt, Object paramObject)
  {
    int i = (0x7FFFFFFF & paramInt) % this.table.length;
    if (paramObject == null)
      throw new IllegalArgumentException();
    for (Entry localEntry1 = this.table[i]; localEntry1 != null; localEntry1 = localEntry1.next)
    {
      if ((localEntry1.hash != paramInt) || (localEntry1.key != paramInt))
        continue;
      Object localObject = localEntry1.value;
      localEntry1.value = paramObject;
      return localObject;
    }
    if (this.count >= this.threshold)
    {
      rehash();
      return put(paramInt, paramObject);
    }
    Entry localEntry2 = new Entry();
    localEntry2.hash = paramInt;
    localEntry2.key = paramInt;
    localEntry2.value = paramObject;
    localEntry2.next = this.table[i];
    this.table[i] = localEntry2;
    this.count = (1 + this.count);
    return null;
  }

  public Object put(Object paramObject1, Object paramObject2)
  {
    if (!(paramObject1 instanceof Number))
      throw new IllegalArgumentException("key cannot be null");
    return put(((Number)paramObject1).intValue(), paramObject2);
  }

  public void putAll(Map paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      put(localObject, paramMap.get(localObject));
    }
  }

  protected void rehash()
  {
    int i = this.table.length;
    Entry[] arrayOfEntry1 = this.table;
    int j = 1 + i * 2;
    Entry[] arrayOfEntry2 = new Entry[j];
    this.threshold = (int)(j * this.loadFactor);
    this.table = arrayOfEntry2;
    while (true)
    {
      int k = i - 1;
      if (i <= 0)
        break;
      Entry localEntry;
      for (Object localObject = arrayOfEntry1[k]; localObject != null; localObject = localEntry)
      {
        int m = (0x7FFFFFFF & ((Entry)localObject).hash) % j;
        localEntry = ((Entry)localObject).next;
        ((Entry)localObject).next = arrayOfEntry2[m];
        arrayOfEntry2[m] = localObject;
      }
      i = k;
    }
  }

  public final Object remove(int paramInt)
  {
    int i = (0x7FFFFFFF & paramInt) % this.table.length;
    Object localObject1 = this.table[i];
    label77: label90: Object localObject4;
    for (Object localObject2 = null; ; localObject2 = localObject4)
    {
      Object localObject3 = null;
      if (localObject1 != null)
      {
        if ((((Entry)localObject1).hash != paramInt) || (((Entry)localObject1).key != paramInt))
          break label90;
        if (localObject2 == null)
          break label77;
        localObject2.next = ((Entry)localObject1).next;
      }
      while (true)
      {
        this.count = (-1 + this.count);
        localObject3 = ((Entry)localObject1).value;
        return localObject3;
        this.table[i] = ((Entry)localObject1).next;
      }
      Entry localEntry = ((Entry)localObject1).next;
      localObject4 = localObject1;
      localObject1 = localEntry;
    }
  }

  public Object remove(Object paramObject)
  {
    if (!(paramObject instanceof Number))
      throw new IllegalArgumentException("key cannot be null");
    return remove(((Number)paramObject).intValue());
  }

  public int size()
  {
    return this.count;
  }

  public Collection values()
  {
    ArrayList localArrayList = new ArrayList();
    IntHashMapIterator localIntHashMapIterator = new IntHashMapIterator(this.table, false);
    while (localIntHashMapIterator.hasNext())
      localArrayList.add(localIntHashMapIterator.next());
    return localArrayList;
  }

  public static class Entry
  {
    int hash;
    int key;
    Entry next;
    Object value;
  }

  private static class IntHashMapIterator
    implements Iterator
  {
    IntHashMap.Entry entry;
    int index;
    boolean keys;
    IntHashMap.Entry[] table;

    IntHashMapIterator(IntHashMap.Entry[] paramArrayOfEntry, boolean paramBoolean)
    {
      this.table = paramArrayOfEntry;
      this.keys = paramBoolean;
      this.index = paramArrayOfEntry.length;
    }

    public boolean hasNext()
    {
      if (this.entry != null)
        return true;
      while (true)
      {
        int i = this.index;
        this.index = (i - 1);
        if (i <= 0)
          break;
        IntHashMap.Entry localEntry = this.table[this.index];
        this.entry = localEntry;
        if (localEntry != null)
          return true;
      }
      return false;
    }

    public Object next()
    {
      if (this.entry == null)
      {
        IntHashMap.Entry localEntry2;
        do
        {
          int i = this.index;
          this.index = (i - 1);
          if (i <= 0)
            break;
          localEntry2 = this.table[this.index];
          this.entry = localEntry2;
        }
        while (localEntry2 == null);
      }
      if (this.entry != null)
      {
        IntHashMap.Entry localEntry1 = this.entry;
        this.entry = localEntry1.next;
        if (this.keys)
          return new Integer(localEntry1.key);
        return localEntry1.value;
      }
      throw new NoSuchElementException("IntHashMapIterator");
    }

    public void remove()
    {
      throw new UnsupportedOperationException("remove");
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.IntHashMap
 * JD-Core Version:    0.6.0
 */