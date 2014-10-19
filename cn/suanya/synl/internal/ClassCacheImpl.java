package cn.suanya.synl.internal;

import cn.suanya.synl.og.ClassCacheInspector;
import java.util.Arrays;

public class ClassCacheImpl
  implements ClassCache
{
  private static final int TABLE_SIZE = 512;
  private static final int TABLE_SIZE_MASK = 511;
  private ClassCacheInspector _classInspector;
  private int _size = 0;
  private Entry[] _table = new Entry[512];

  public void clear()
  {
    for (int i = 0; i < this._table.length; i++)
      this._table[i] = null;
    this._size = 0;
  }

  public final Object get(Class paramClass)
  {
    int i = 0x1FF & paramClass.hashCode();
    for (Entry localEntry = this._table[i]; ; localEntry = localEntry.next)
    {
      Object localObject = null;
      if (localEntry != null)
      {
        if (localEntry.key != paramClass)
          continue;
        localObject = localEntry.value;
      }
      return localObject;
    }
  }

  public int getSize()
  {
    return this._size;
  }

  public final Object put(Class paramClass, Object paramObject)
  {
    if ((this._classInspector != null) && (!this._classInspector.shouldCache(paramClass)))
      return paramObject;
    int i = 0x1FF & paramClass.hashCode();
    Entry localEntry = this._table[i];
    Object localObject1;
    if (localEntry == null)
    {
      this._table[i] = new Entry(paramClass, paramObject);
      this._size = (1 + this._size);
      localObject1 = null;
    }
    while (true)
    {
      return localObject1;
      if (localEntry.key == paramClass)
      {
        Object localObject3 = localEntry.value;
        localEntry.value = paramObject;
        localObject1 = localObject3;
        continue;
      }
      do
      {
        localEntry = localEntry.next;
        if (localEntry.key != paramClass)
          continue;
        Object localObject2 = localEntry.value;
        localEntry.value = paramObject;
        localObject1 = localObject2;
        break;
      }
      while (localEntry.next != null);
      localEntry.next = new Entry(paramClass, paramObject);
      localObject1 = null;
    }
  }

  public void setClassInspector(ClassCacheInspector paramClassCacheInspector)
  {
    this._classInspector = paramClassCacheInspector;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("ClassCacheImpl[_table=");
    if (this._table == null);
    for (Object localObject = null; ; localObject = Arrays.asList(this._table))
      return localObject + '\n' + ", _classInspector=" + this._classInspector + '\n' + ", _size=" + this._size + '\n' + ']';
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.internal.ClassCacheImpl
 * JD-Core Version:    0.6.0
 */