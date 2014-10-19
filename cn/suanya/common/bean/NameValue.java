package cn.suanya.common.bean;

import java.io.Serializable;

public class NameValue
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String name;
  private String value;

  public NameValue()
  {
  }

  public NameValue(String paramString1, String paramString2)
  {
    this.name = paramString1;
    this.value = paramString2;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setValue(String paramString)
  {
    this.value = paramString;
  }

  public String toString()
  {
    return this.name + "=" + this.value;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.bean.NameValue
 * JD-Core Version:    0.6.0
 */