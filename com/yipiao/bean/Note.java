package com.yipiao.bean;

import java.io.Serializable;

public class Note
  implements Serializable
{
  private static final long serialVersionUID = 2532461049782819537L;
  private String code;
  private String filter;
  private String name;

  public Note()
  {
  }

  public Note(String paramString1, String paramString2)
  {
    this.code = paramString1;
    this.name = paramString2;
    this.filter = paramString2;
  }

  public Note(String paramString1, String paramString2, String paramString3)
  {
    this.code = paramString1;
    this.name = paramString2;
    this.filter = paramString3;
  }

  public boolean equals(Object paramObject)
  {
    Note localNote;
    if ((paramObject instanceof Note))
    {
      localNote = (Note)paramObject;
      if (this.name.equals(localNote.getName()))
        break label28;
    }
    label28: 
    do
      return false;
    while (!this.code.equals(localNote.getCode()));
    return true;
  }

  public boolean filter(String paramString)
  {
    return ((getName() != null) && (getName().startsWith(paramString))) || ((getFilter() != null) && (getFilter().indexOf(paramString) >= 0));
  }

  public String getCode()
  {
    return this.code;
  }

  public String getFilter()
  {
    return this.filter;
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

  public void setFilter(String paramString)
  {
    this.filter = paramString;
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
 * Qualified Name:     com.yipiao.bean.Note
 * JD-Core Version:    0.6.0
 */