package cn.suanya.common.a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class q extends ArrayList<p>
  implements Serializable
{
  private static final long serialVersionUID = -3063014443254001756L;

  public q()
  {
  }

  public q(Collection<? extends p> paramCollection)
  {
    super(paramCollection);
  }

  public p a(String paramString)
  {
    if (paramString == null)
      return null;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      p localp = (p)localIterator.next();
      if (paramString.equals(localp.getCode()))
        return localp;
    }
    return null;
  }

  public String[] a()
  {
    String[] arrayOfString = new String[size()];
    for (int i = 0; i < arrayOfString.length; i++)
      arrayOfString[i] = ((p)get(i)).toString();
    return arrayOfString;
  }

  public int b(String paramString)
  {
    if (paramString == null)
    {
      i = -1;
      return i;
    }
    Iterator localIterator = iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        break label51;
      if (paramString.equals(((p)localIterator.next()).getCode()))
        break;
    }
    label51: return -1;
  }

  public q b()
  {
    return new q(this);
  }

  public p c(String paramString)
  {
    if (paramString == null)
      return null;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      p localp = (p)localIterator.next();
      if (paramString.equals(localp.getName()))
        return localp;
    }
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.q
 * JD-Core Version:    0.6.0
 */