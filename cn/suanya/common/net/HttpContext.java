package cn.suanya.common.net;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HttpContext
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  List<Cookie> mCookies = new ArrayList();

  public void addCookie(String paramString, Cookie paramCookie)
  {
    if (paramString == null);
    do
      return;
    while (paramCookie == null);
    Iterator localIterator = this.mCookies.iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      if ((localCookie == null) || (localCookie.getmDomain() == null) || (localCookie.getmName() == null) || (!localCookie.getmName().equalsIgnoreCase(paramCookie.getmName())) || (!paramString.endsWith(localCookie.getmDomain())))
        continue;
      localIterator.remove();
    }
    this.mCookies.add(paramCookie);
  }

  public void clearCookie()
  {
    this.mCookies = new ArrayList();
  }

  public void clearCookie(String paramString)
  {
    Iterator localIterator = this.mCookies.iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      if ((localCookie.getmDomain() == null) || (!paramString.endsWith(localCookie.getmDomain())))
        continue;
      localIterator.remove();
    }
  }

  public String getCookieString(String paramString)
  {
    Iterator localIterator = getCookies(paramString).iterator();
    Cookie localCookie;
    for (String str = ""; localIterator.hasNext(); str = str + "; " + localCookie.toString())
      localCookie = (Cookie)localIterator.next();
    if (str.length() < 1)
      return str;
    return str.substring(2);
  }

  public List<Cookie> getCookies(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mCookies.iterator();
    while (localIterator.hasNext())
    {
      Cookie localCookie = (Cookie)localIterator.next();
      String str = localCookie.getmDomain();
      if ((str == null) || (!paramString.endsWith(str)))
        continue;
      localArrayList.add(localCookie);
    }
    return localArrayList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpContext
 * JD-Core Version:    0.6.0
 */