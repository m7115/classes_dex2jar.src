package cn.suanya.hotel.domain;

import cn.suanya.common.a.r;
import java.io.Serializable;

public class CityInfo extends r
  implements Serializable
{
  private static final long serialVersionUID = -658871628306441862L;
  private String firstPinyin;
  private double latitude;
  private double longitude;
  private String pinyinName;

  public CityInfo()
  {
  }

  public CityInfo(String paramString1, String paramString2, String paramString3, String paramString4, double paramDouble1, double paramDouble2)
  {
    super(paramString1, paramString2, paramString3);
    this.pinyinName = paramString3;
    this.firstPinyin = paramString4;
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
  }

  public String getFirstPinyin()
  {
    return this.firstPinyin;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public String getPinyinName()
  {
    return this.pinyinName;
  }

  public void setFirstPinyin(String paramString)
  {
    this.firstPinyin = paramString;
  }

  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }

  public void setPinyinName(String paramString)
  {
    this.pinyinName = paramString;
  }

  public String toString()
  {
    return "CityInfo [cnName=" + getName() + ", pinyinName=" + this.pinyinName + ", firstPinyin=" + this.firstPinyin + ", latitude=" + this.latitude + ", longitude=" + this.longitude + "]";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.CityInfo
 * JD-Core Version:    0.6.0
 */