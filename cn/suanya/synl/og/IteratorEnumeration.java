package cn.suanya.synl.og;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorEnumeration
  implements Enumeration
{
  private Iterator it;

  public IteratorEnumeration(Iterator paramIterator)
  {
    this.it = paramIterator;
  }

  public boolean hasMoreElements()
  {
    return this.it.hasNext();
  }

  public Object nextElement()
  {
    return this.it.next();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.IteratorEnumeration
 * JD-Core Version:    0.6.0
 */