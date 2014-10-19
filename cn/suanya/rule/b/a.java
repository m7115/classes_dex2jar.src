package cn.suanya.rule.b;

import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;

public class a
{
  Map<String, String> a;

  public a(Attributes paramAttributes)
  {
    this.a = new HashMap(paramAttributes.getLength());
    for (int i = 0; i < paramAttributes.getLength(); i++)
    {
      String str1 = paramAttributes.getLocalName(i);
      String str2 = paramAttributes.getValue(i);
      this.a.put(str1, str2);
    }
  }

  public int a(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt((String)this.a.get(paramString));
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt;
  }

  public String a(String paramString)
  {
    return (String)this.a.get(paramString);
  }

  public String a(String paramString1, String paramString2)
  {
    String str = (String)this.a.get(paramString1);
    if (str == null)
      return paramString2;
    return str;
  }

  public boolean a(String paramString, boolean paramBoolean)
  {
    String str = (String)this.a.get(paramString);
    if (str == null)
      return paramBoolean;
    return "TRUE".equalsIgnoreCase(str);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.b.a
 * JD-Core Version:    0.6.0
 */