package cn.suanya.hotel.domain;

import cn.suanya.common.a.c;
import cn.suanya.common.a.p;
import cn.suanya.common.a.q;
import cn.suanya.hotel.HTConstants;
import cn.suanya.hotel.service.CityService;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindHotelReq
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private double blatitude;
  private double blongitude;
  private Date checkInDate;
  private Date checkOutDate;
  private CityInfo city;
  private String cityId;
  private String cityName;
  private District district;
  private String districtName;
  private double glatitude;
  private double glongitude;
  private Integer highestPrice;
  private int index;
  private String keywords;
  private Integer lowestPrice;
  private boolean needFindCity = false;
  private int page;
  private String priceLevel;
  private Integer radius;
  private String star;
  private double tlatitude;
  private double tlongitude;

  public FindHotelReq()
  {
    Date localDate = c.roundDate(new Date());
    this.checkInDate = localDate;
    this.checkOutDate = c.addDays(localDate, 1);
    this.index = 0;
    this.page = 0;
    this.radius = Integer.valueOf(2500);
    setCity((CityInfo)CityService.instance().getHotCity().get(0));
    setPriceLevel(((p)HTConstants.priceLevel.get(0)).getCode());
    setStar(((p)HTConstants.star.get(0)).getCode());
  }

  public void clearAllPoint()
  {
    setBlatitude(0.0D);
    setBlongitude(0.0D);
    setGlatitude(0.0D);
    setGlongitude(0.0D);
    setTlatitude(0.0D);
    setTlongitude(0.0D);
  }

  public double getBlatitude()
  {
    return this.blatitude;
  }

  public double getBlongitude()
  {
    return this.blongitude;
  }

  public Date getCheckInDate()
  {
    return this.checkInDate;
  }

  public Date getCheckOutDate()
  {
    return this.checkOutDate;
  }

  public CityInfo getCity()
  {
    return this.city;
  }

  public String getCityId()
  {
    return this.cityId;
  }

  public String getCityName()
  {
    return this.cityName;
  }

  public int getDateNum()
  {
    return c.daysBetween(this.checkInDate, this.checkOutDate);
  }

  public District getDistrict()
  {
    return this.district;
  }

  public String getDistrictName()
  {
    return this.districtName;
  }

  public double getGlatitude()
  {
    return this.glatitude;
  }

  public double getGlongitude()
  {
    return this.glongitude;
  }

  public Integer getHighestPrice()
  {
    return this.highestPrice;
  }

  public int getIndex()
  {
    return this.index;
  }

  public String getKeywords()
  {
    return this.keywords;
  }

  public Integer getLowestPrice()
  {
    return this.lowestPrice;
  }

  public int getPage()
  {
    return this.page;
  }

  public String getPriceLevel()
  {
    return this.priceLevel;
  }

  public Integer getRadius()
  {
    return this.radius;
  }

  public String getStar()
  {
    return this.star;
  }

  public double getTlatitude()
  {
    return this.tlatitude;
  }

  public double getTlongitude()
  {
    return this.tlongitude;
  }

  public boolean isNeedFindCity()
  {
    return this.needFindCity;
  }

  public void setBlatitude(double paramDouble)
  {
    this.blatitude = paramDouble;
  }

  public void setBlongitude(double paramDouble)
  {
    this.blongitude = paramDouble;
  }

  public void setCheckInDate(Date paramDate)
  {
    if (c.daysBetween(paramDate, this.checkOutDate) <= 0)
      this.checkOutDate = c.addDays(paramDate, 1);
    this.checkInDate = paramDate;
  }

  public void setCheckOutDate(Date paramDate)
  {
    if (c.daysBetween(this.checkInDate, paramDate) <= 0)
      paramDate = c.addDays(this.checkInDate, 1);
    this.checkOutDate = paramDate;
  }

  public void setCity(CityInfo paramCityInfo)
  {
    if (paramCityInfo == null)
      return;
    if (!paramCityInfo.equals(this.city))
      setDistrict(new District("", "不限", 0.0D, 0.0D));
    this.city = paramCityInfo;
    setCityName(paramCityInfo.getName());
    setCityId(paramCityInfo.getCode());
  }

  public void setCityId(String paramString)
  {
    this.cityId = paramString;
  }

  public void setCityName(String paramString)
  {
    this.cityName = paramString;
  }

  public void setDistrict(District paramDistrict)
  {
    if ((paramDistrict == null) || (paramDistrict.isNothing()));
    for (this.districtName = null; ; this.districtName = paramDistrict.getName())
    {
      this.district = paramDistrict;
      return;
    }
  }

  public void setDistrictName(String paramString)
  {
    this.districtName = paramString;
  }

  public void setGlatitude(double paramDouble)
  {
    this.glatitude = paramDouble;
  }

  public void setGlongitude(double paramDouble)
  {
    this.glongitude = paramDouble;
  }

  public void setHighestPrice(Integer paramInteger)
  {
    this.highestPrice = paramInteger;
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }

  public void setKeywords(String paramString)
  {
    this.keywords = paramString;
  }

  public void setLowestPrice(Integer paramInteger)
  {
    this.lowestPrice = paramInteger;
  }

  public void setNeedFindCity(boolean paramBoolean)
  {
    this.needFindCity = paramBoolean;
  }

  public void setPage(int paramInt)
  {
    this.page = paramInt;
  }

  public void setPriceLevel(String paramString)
  {
    this.priceLevel = paramString;
    String[] arrayOfString = paramString.split("<");
    this.lowestPrice = Integer.valueOf(0);
    this.highestPrice = Integer.valueOf(0);
    try
    {
      if (arrayOfString.length > 0)
        this.lowestPrice = Integer.valueOf(Integer.parseInt(arrayOfString[0]));
      try
      {
        label46: if (arrayOfString.length > 1)
          this.highestPrice = Integer.valueOf(Integer.parseInt(arrayOfString[1]));
        return;
      }
      catch (Exception localException2)
      {
        return;
      }
    }
    catch (Exception localException1)
    {
      break label46;
    }
  }

  public void setRadius(Integer paramInteger)
  {
    this.radius = paramInteger;
  }

  public void setStar(String paramString)
  {
    this.star = paramString;
  }

  public void setTlatitude(double paramDouble)
  {
    this.tlatitude = paramDouble;
  }

  public void setTlongitude(double paramDouble)
  {
    this.tlongitude = paramDouble;
  }

  public Map<String, Object> toMap()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("glongitude", Double.valueOf(this.glongitude));
    localHashMap.put("glatitude", Double.valueOf(this.glatitude));
    localHashMap.put("blongitude", Double.valueOf(this.blongitude));
    localHashMap.put("blatitude", Double.valueOf(this.blatitude));
    localHashMap.put("tlongitude", Double.valueOf(this.tlongitude));
    localHashMap.put("tlatitude", Double.valueOf(this.tlatitude));
    localHashMap.put("index", Integer.valueOf(this.index));
    localHashMap.put("page", Integer.valueOf(this.page));
    localHashMap.put("radius", this.radius);
    localHashMap.put("cityName", this.cityName);
    localHashMap.put("cityId", this.cityId);
    localHashMap.put("keywords", this.keywords);
    localHashMap.put("districtName", this.districtName);
    localHashMap.put("checkInDate", c.formartDate(this.checkInDate));
    localHashMap.put("checkOutDate", c.formartDate(this.checkOutDate));
    localHashMap.put("star", this.star);
    localHashMap.put("lowestPrice", this.lowestPrice);
    localHashMap.put("highestPrice", this.highestPrice);
    return localHashMap;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.FindHotelReq
 * JD-Core Version:    0.6.0
 */