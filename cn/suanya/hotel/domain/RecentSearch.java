package cn.suanya.hotel.domain;

import java.io.Serializable;

public class RecentSearch
  implements Serializable
{
  public static final int TYPE_INPUT = 0;
  public static final int TYPE_SUGGEST = 1;
  private static final long serialVersionUID = 1L;
  private String city;
  private String district;
  private String key;
  private int type;

  public RecentSearch(String paramString)
  {
    this.key = paramString;
    this.city = "";
    this.district = "";
    this.type = 0;
  }

  public RecentSearch(String paramString1, String paramString2, String paramString3)
  {
    this.key = paramString1;
    this.city = paramString2;
    this.district = paramString3;
    this.type = 1;
  }

  public String getCity()
  {
    return this.city;
  }

  public String getDistrict()
  {
    return this.district;
  }

  public String getKey()
  {
    return this.key;
  }

  public String getStr()
  {
    return this.city + this.district + this.key;
  }

  public int getType()
  {
    return this.type;
  }

  public void setCity(String paramString)
  {
    this.city = paramString;
  }

  public void setDistrict(String paramString)
  {
    this.district = paramString;
  }

  public void setKey(String paramString)
  {
    this.key = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public String toString()
  {
    return this.key + this.city + this.district;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.RecentSearch
 * JD-Core Version:    0.6.0
 */