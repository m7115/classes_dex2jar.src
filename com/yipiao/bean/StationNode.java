package com.yipiao.bean;

import cn.suanya.hc.service.PathService;

public class StationNode extends Note
{
  private static final long serialVersionUID = 1L;
  private String cityName;
  private int hotCity;
  private double lat = 23.0D;
  private double lng = 113.0D;
  private String pinYin;

  public StationNode()
  {
  }

  public StationNode(String paramString1, String paramString2, String paramString3, String paramString4, double paramDouble1, double paramDouble2, String paramString5)
  {
    super(paramString1, paramString2, paramString3);
    setPinYin(paramString4);
    this.lat = paramDouble1;
    this.lng = paramDouble2;
    setCityName(paramString5);
    this.hotCity = 0;
  }

  public StationNode(String paramString1, String paramString2, String paramString3, String paramString4, double paramDouble1, double paramDouble2, String paramString5, int paramInt)
  {
    super(paramString1, paramString2, paramString3);
    setPinYin(paramString4);
    this.lat = paramDouble1;
    this.lng = paramDouble2;
    setCityName(paramString5);
    this.hotCity = paramInt;
  }

  public static StationNode defaut()
  {
    return new StationNode("BJP", "北京", "BJ", "beijing", 39.908000000000001D, 116.434D, "北京");
  }

  public String getCityName()
  {
    if ((this.cityName == null) || (this.cityName.length() == 0))
    {
      StationNode localStationNode = PathService.getInstance().getStationInfoByCode(getCode());
      if ((localStationNode == null) || (localStationNode.orgCityName() == null) || (localStationNode.orgCityName().length() == 0))
        return getName();
      return localStationNode.orgCityName();
    }
    return this.cityName;
  }

  public int getHotCity()
  {
    return this.hotCity;
  }

  public double getLat()
  {
    return this.lat;
  }

  public double getLng()
  {
    return this.lng;
  }

  public String getPinYin()
  {
    if ((this.pinYin == null) || (this.pinYin.length() == 0))
    {
      StationNode localStationNode = PathService.getInstance().getStationInfoByCode(getCode());
      if (localStationNode != null)
        localStationNode.orgPinYin();
    }
    return this.pinYin;
  }

  public String orgCityName()
  {
    return this.cityName;
  }

  public String orgPinYin()
  {
    return this.pinYin;
  }

  public void setCityName(String paramString)
  {
    this.cityName = paramString;
  }

  public void setHotCity(int paramInt)
  {
    this.hotCity = paramInt;
  }

  public void setLat(double paramDouble)
  {
    this.lat = paramDouble;
  }

  public void setLng(double paramDouble)
  {
    this.lng = paramDouble;
  }

  public void setPinYin(String paramString)
  {
    this.pinYin = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.StationNode
 * JD-Core Version:    0.6.0
 */