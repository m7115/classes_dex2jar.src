package cn.suanya.hotel.domain;

import cn.suanya.common.a.p;
import java.util.ArrayList;
import java.util.List;

public class ListSection
{
  private String key;
  private String name;
  private List<p> section;

  public ListSection(String paramString1, String paramString2, List paramList)
  {
    this.key = paramString1;
    this.name = paramString2;
    if (paramList == null)
      paramList = new ArrayList();
    this.section = paramList;
  }

  public String getKey()
  {
    return this.key;
  }

  public String getName()
  {
    return this.name;
  }

  public List<p> getSection()
  {
    return this.section;
  }

  public void setKey(String paramString)
  {
    this.key = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setSection(List<p> paramList)
  {
    this.section = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.ListSection
 * JD-Core Version:    0.6.0
 */