package cn.suanya.common.bean;

import cn.suanya.common.a.s;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class NameValueList extends ArrayList<NameValue>
{
  private static final long serialVersionUID = 1L;

  public void add(String paramString, Object paramObject)
  {
    if (paramObject == null)
    {
      add(new NameValue(paramString, ""));
      return;
    }
    add(new NameValue(paramString, paramObject.toString()));
  }

  public NameValue getByName(String paramString)
  {
    if (paramString == null)
      return null;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      NameValue localNameValue = (NameValue)localIterator.next();
      if (paramString.equals(localNameValue.getName()))
        return localNameValue;
    }
    return null;
  }

  public NameValue getByValue(String paramString)
  {
    if (paramString == null)
      return null;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      NameValue localNameValue = (NameValue)localIterator.next();
      if (paramString.equals(localNameValue.getValue()))
        return localNameValue;
    }
    return null;
  }

  public void removeByName(String paramString)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      if (!paramString.equals(((NameValue)localIterator.next()).getName()))
        continue;
      localIterator.remove();
    }
  }

  public void removeByValue(String paramString)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      if (!paramString.equals(((NameValue)localIterator.next()).getValue()))
        continue;
      localIterator.remove();
    }
  }

  public NameValueList subList(String paramString)
  {
    NameValueList localNameValueList = new NameValueList();
    String[] arrayOfString = paramString.split("\\|");
    for (int i = 0; i < arrayOfString.length; i++)
    {
      NameValue localNameValue = getByName(arrayOfString[i]);
      if (localNameValue == null)
        continue;
      localNameValueList.add(localNameValue);
    }
    return localNameValueList;
  }

  public String toJson()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("{");
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      NameValue localNameValue = (NameValue)localIterator.next();
      localStringBuffer.append("\"").append(localNameValue.getName()).append("\":\"").append(localNameValue.getValue()).append("\"").append(",");
    }
    if (localStringBuffer.length() > 1)
      localStringBuffer.delete(-1 + localStringBuffer.length(), localStringBuffer.length());
    localStringBuffer.append("}");
    return localStringBuffer.toString();
  }

  public String urlEncode()
  {
    try
    {
      String str = s.a(this, "utf-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return "";
  }

  public String urlEncode(String paramString)
    throws UnsupportedEncodingException
  {
    return s.a(this, paramString);
  }

  public byte[] urlEncodeBytes()
  {
    try
    {
      byte[] arrayOfByte = s.a(this, "utf-8").getBytes("utf-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.bean.NameValueList
 * JD-Core Version:    0.6.0
 */