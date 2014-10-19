package cn.suanya.synl.internal;

class Entry
{
  Class key;
  Entry next;
  Object value;

  public Entry(Class paramClass, Object paramObject)
  {
    this.key = paramClass;
    this.value = paramObject;
  }

  public String toString()
  {
    return "Entry[next=" + this.next + '\n' + ", key=" + this.key + '\n' + ", value=" + this.value + '\n' + ']';
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.internal.Entry
 * JD-Core Version:    0.6.0
 */