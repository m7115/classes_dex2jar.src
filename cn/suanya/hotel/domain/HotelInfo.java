package cn.suanya.hotel.domain;

import cn.suanya.common.a.i;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class HotelInfo
  implements Serializable
{
  private static final long serialVersionUID = 2968711209208405504L;
  private double distance;
  private String hotelAddress;
  private Map<String, Object> hotelId;
  private String hotelName;
  private String img;
  private double latitude;
  private int liveableCount;
  private double longitude;
  private double lowestPrice;
  private String phone;
  private double rating;
  private String shortName;
  private int star;

  public HotelInfo()
  {
  }

  public HotelInfo(JSONObject paramJSONObject)
  {
    setHotelId(i.a(paramJSONObject.optJSONObject("hotelId")));
    this.hotelName = paramJSONObject.optString("hotelName");
    this.shortName = paramJSONObject.optString("shortName");
    this.hotelAddress = paramJSONObject.optString("hotelAddress");
    this.img = paramJSONObject.optString("img");
    this.star = paramJSONObject.optInt("star");
    this.liveableCount = paramJSONObject.optInt("liveableCount");
    this.latitude = paramJSONObject.optDouble("latitude");
    this.longitude = paramJSONObject.optDouble("longitude");
    this.lowestPrice = paramJSONObject.optDouble("lowestPrice");
    this.rating = paramJSONObject.optDouble("rating");
    this.phone = paramJSONObject.optString("phone");
    this.distance = -1.0D;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof HotelInfo;
    int i = 0;
    if (bool1)
    {
      HotelInfo localHotelInfo = (HotelInfo)paramObject;
      boolean bool2 = localHotelInfo.getLatitude() < getLatitude();
      i = 0;
      if (!bool2)
      {
        boolean bool3 = localHotelInfo.getLongitude() < getLongitude();
        i = 0;
        if (!bool3)
        {
          boolean bool4 = localHotelInfo.getHotelName().equals(getHotelName());
          i = 0;
          if (bool4)
            i = 1;
        }
      }
    }
    return i;
  }

  public double getDistance()
  {
    return this.distance;
  }

  public String getHotelAddress()
  {
    return this.hotelAddress;
  }

  public Map<String, Object> getHotelId()
  {
    return this.hotelId;
  }

  public String getHotelName()
  {
    return this.hotelName;
  }

  public Map<String, Boolean> getHotelServerMap()
  {
    return new HashMap();
  }

  public String getImg()
  {
    return this.img;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public int getLiveableCount()
  {
    return this.liveableCount;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public double getLowestPrice()
  {
    return this.lowestPrice;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public double getRating()
  {
    return this.rating;
  }

  public String getShortName()
  {
    if ((this.shortName == null) || (this.shortName.length() == 0))
      return this.hotelName.substring(0, 4);
    return this.shortName;
  }

  public int getStar()
  {
    return this.star;
  }

  public void setDistance(double paramDouble)
  {
    this.distance = paramDouble;
  }

  public void setHotelAddress(String paramString)
  {
    this.hotelAddress = paramString;
  }

  public void setHotelId(Map<String, Object> paramMap)
  {
    this.hotelId = paramMap;
  }

  public void setHotelName(String paramString)
  {
    this.hotelName = paramString;
  }

  public void setImg(String paramString)
  {
    this.img = paramString;
  }

  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLiveableCount(int paramInt)
  {
    this.liveableCount = paramInt;
  }

  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }

  public void setLowestPrice(double paramDouble)
  {
    this.lowestPrice = paramDouble;
  }

  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }

  public void setRating(double paramDouble)
  {
    this.rating = paramDouble;
  }

  public void setShortName(String paramString)
  {
    this.shortName = paramString;
  }

  public void setStar(int paramInt)
  {
    this.star = paramInt;
  }

  public String sourceName()
  {
    Iterator localIterator = this.hotelId.keySet().iterator();
    Object localObject = null;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (localObject == null);
      while (true)
      {
        localObject = str;
        break;
        str = str + ',' + localObject;
      }
    }
    return localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.HotelInfo
 * JD-Core Version:    0.6.0
 */