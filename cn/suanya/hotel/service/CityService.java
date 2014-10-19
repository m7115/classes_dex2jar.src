package cn.suanya.hotel.service;

import android.content.res.AssetManager;
import cn.suanya.common.a.n;
import cn.suanya.common.a.p;
import cn.suanya.common.a.q;
import cn.suanya.common.a.s;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.hotel.domain.CityInfo;
import cn.suanya.hotel.domain.District;
import cn.suanya.hotel.domain.ListSection;
import cn.suanya.hotel.domain.ListSectionGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class CityService
{
  private static String cityFile = "city.txt";
  private static CityService cityService;
  private static String districtFile;
  private static String hotCityFile = "hotcity.txt";
  private q allCity;
  private Map<String, String> district;
  private List<CityInfo> hotCity;
  private List<CityInfo> recentCity;
  private ListSectionGroup sectionGroup;

  static
  {
    districtFile = "district.txt";
  }

  private q createDistrictList(String paramString)
  {
    q localq = new q();
    localq.add(new District("", "不限", 0.0D, 0.0D));
    if (paramString != null)
    {
      String[] arrayOfString = paramString.split("\\|");
      for (int i = 1; i < arrayOfString.length; i += 3)
        localq.add(new District(arrayOfString[i], arrayOfString[i], Double.parseDouble(arrayOfString[(i + 1)]), Double.parseDouble(arrayOfString[(i + 2)])));
    }
    return localq;
  }

  public static CityService instance()
  {
    if (cityService == null)
      cityService = new CityService();
    return cityService;
  }

  private CityInfo json2City(JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("cnName");
    String str2 = paramJSONObject.optString("id");
    String str3 = paramJSONObject.optString("pinyinName");
    String str4 = paramJSONObject.optString("firstPinyin").toUpperCase();
    double d = paramJSONObject.optDouble("longitude");
    return new CityInfo(str2, str1, str3, str4, paramJSONObject.optDouble("latitude"), d);
  }

  public q getAllCity()
  {
    monitorenter;
    try
    {
      if (this.allCity != null)
      {
        q localq3 = this.allCity;
        return localq3;
      }
      q localq1 = new q();
      try
      {
        JSONArray localJSONArray = new JSONArray(s.c(SYApplication.app.getAssets().open(cityFile)));
        for (int i = 0; i < localJSONArray.length(); i++)
          localq1.add(json2City(localJSONArray.getJSONObject(i)));
      }
      catch (Exception localException)
      {
        n.b(localException);
        this.allCity = localq1;
        q localq2 = this.allCity;
        monitorexit;
        return localq2;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public ListSectionGroup getCityGroup()
  {
    monitorenter;
    ListSectionGroup localListSectionGroup1;
    try
    {
      if (this.sectionGroup != null)
      {
        ListSectionGroup localListSectionGroup3 = this.sectionGroup;
        return localListSectionGroup3;
      }
      getAllCity();
      getHotCity();
      localListSectionGroup1 = new ListSectionGroup();
      if ((this.hotCity != null) && (!this.hotCity.isEmpty()))
        localListSectionGroup1.getSectionGroup().add(new ListSection("热", "热门城市", this.hotCity));
      Iterator localIterator = this.allCity.iterator();
      Object localObject2 = null;
      Object localObject3 = null;
      while (localIterator.hasNext())
      {
        CityInfo localCityInfo = (CityInfo)(p)localIterator.next();
        if ((localObject3 == null) || (!localObject2.getFirstPinyin().equals(localCityInfo.getFirstPinyin())))
        {
          ListSection localListSection = new ListSection(localCityInfo.getFirstPinyin(), localCityInfo.getFirstPinyin(), null);
          localListSectionGroup1.getSectionGroup().add(localListSection);
          localListSection.getSection().add(localCityInfo);
          localObject3 = localListSection;
          localObject2 = localCityInfo;
          continue;
        }
        localObject3.getSection().add(localCityInfo);
      }
    }
    finally
    {
      monitorexit;
    }
    this.sectionGroup = localListSectionGroup1;
    ListSectionGroup localListSectionGroup2 = this.sectionGroup;
    monitorexit;
    return localListSectionGroup2;
  }

  public q getDistrict(String paramString)
  {
    int i = 0;
    if (this.district == null)
    {
      this.district = new HashMap();
      try
      {
        String[] arrayOfString = s.c(SYApplication.app.getAssets().open(districtFile)).split(";");
        while (i < arrayOfString.length)
        {
          String str2 = arrayOfString[i];
          int j = str2.indexOf('|');
          if (j > 0)
            this.district.put(str2.substring(0, j), str2);
          i++;
        }
      }
      catch (Exception localException)
      {
        n.b(localException);
      }
    }
    if (this.district != null);
    for (String str1 = (String)this.district.get(paramString); ; str1 = null)
      return createDistrictList(str1);
  }

  public List<CityInfo> getHotCity()
  {
    if (this.hotCity != null)
      return this.hotCity;
    ArrayList localArrayList = new ArrayList();
    try
    {
      JSONArray localJSONArray = new JSONArray(s.c(SYApplication.app.getAssets().open(hotCityFile)));
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        json2City(localJSONObject).setFirstPinyin("#");
        localArrayList.add(json2City(localJSONObject));
      }
    }
    catch (Exception localException)
    {
      n.b(localException);
      this.hotCity = localArrayList;
    }
    return this.hotCity;
  }

  public List<CityInfo> getRecentCity()
  {
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.service.CityService
 * JD-Core Version:    0.6.0
 */