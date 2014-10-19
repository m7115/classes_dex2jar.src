package cn.suanya.common.a;

import java.io.Serializable;

public class r
  implements p, Serializable
{
  private static final long serialVersionUID = 2532461049782819537L;
  private String code;
  private String data;
  private String name;

  public r()
  {
  }

  public r(String paramString1, String paramString2)
  {
    this.code = paramString1;
    this.name = paramString2;
    this.data = paramString2;
  }

  public r(String paramString1, String paramString2, String paramString3)
  {
    this.code = paramString1;
    this.name = paramString2;
    this.data = paramString3;
  }

  public boolean equals(Object paramObject)
  {
    p localp;
    if ((paramObject instanceof Serializable))
    {
      localp = (Serializable)paramObject;
      if ((this.code != null) && (this.name != null))
        break label28;
    }
    label28: 
    do
      return false;
    while ((!this.name.equals(localp.getName())) || (!this.code.equals(localp.getCode())));
    return true;
  }

  public boolean filter(String paramString)
  {
    return ((getName() != null) && (getName().startsWith(paramString))) || ((getData() != null) && (getData().indexOf(paramString) >= 0));
  }

  public String getCode()
  {
    return this.code;
  }

  public String getData()
  {
    return this.data;
  }

  public Integer getInt()
  {
    return Integer.valueOf(Integer.parseInt(this.code));
  }

  public String getName()
  {
    return this.name;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setData(String paramString)
  {
    this.data = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public String toString()
  {
    return this.name;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.r
 * JD-Core Version:    0.6.0
 */