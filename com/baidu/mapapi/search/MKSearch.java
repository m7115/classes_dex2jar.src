package com.baidu.mapapi.search;

import android.content.Context;
import android.content.Intent;
import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.b.g;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

public class MKSearch
{
  public static final int EBUS_NO_SUBWAY = 6;
  public static final int EBUS_TIME_FIRST = 3;
  public static final int EBUS_TRANSFER_FIRST = 4;
  public static final int EBUS_WALK_FIRST = 5;
  public static final int ECAR_AVOID_JAM = -1;
  public static final int ECAR_DIS_FIRST = 1;
  public static final int ECAR_FEE_FIRST = 2;
  public static final int ECAR_TIME_FIRST = 0;
  public static final int POI_DETAIL_SEARCH = 52;
  public static final int TYPE_AREA_MULTI_POI_LIST = 45;
  public static final int TYPE_AREA_POI_LIST = 21;
  public static final int TYPE_CITY_LIST = 7;
  public static final int TYPE_POI_LIST = 11;
  MKSearchListener a;
  private com.baidu.platform.comapi.basestruct.b b = new com.baidu.platform.comapi.basestruct.b();
  private String c;
  private String[] d;
  private String e;
  private int f;
  private int g = 3;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private a k = new a(null);
  private BMapManager l;
  private int m = 0;

  private static com.baidu.platform.comapi.basestruct.c a(GeoPoint paramGeoPoint)
  {
    return com.baidu.platform.comapi.a.a.a().a((float)(paramGeoPoint.getLongitudeE6() / 1000000.0D), (float)(paramGeoPoint.getLatitudeE6() / 1000000.0D), "bd09ll");
  }

  public static int getPoiPageCapacity()
  {
    return com.baidu.platform.comapi.b.e.a().c();
  }

  public static void setPoiPageCapacity(int paramInt)
  {
    com.baidu.platform.comapi.b.e.a().a(paramInt);
  }

  public int busLineSearch(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() <= 0) || (paramString2 == null) || (paramString2.length() <= 0));
    do
    {
      do
        return -1;
      while (paramString1.length() > 31);
      this.i = this.j;
      this.j = 13;
    }
    while (!com.baidu.platform.comapi.b.e.a().a(paramString1, paramString2));
    return 0;
  }

  public int drivingSearch(String paramString1, MKPlanNode paramMKPlanNode1, String paramString2, MKPlanNode paramMKPlanNode2)
  {
    if ((paramMKPlanNode1 == null) || (paramMKPlanNode2 == null))
      return -1;
    com.baidu.platform.comapi.b.b localb1 = new com.baidu.platform.comapi.b.b();
    if (paramMKPlanNode1.name != null)
      localb1.c = paramMKPlanNode1.name;
    String str1;
    if (paramMKPlanNode1.pt != null)
    {
      localb1.b = a(paramMKPlanNode1.pt);
      localb1.a = 1;
      str1 = paramString1;
    }
    while (true)
    {
      com.baidu.platform.comapi.b.b localb2 = new com.baidu.platform.comapi.b.b();
      if (paramMKPlanNode2.name != null)
        localb2.c = paramMKPlanNode2.name;
      String str2;
      if (paramMKPlanNode2.pt != null)
      {
        localb2.b = a(paramMKPlanNode2.pt);
        localb2.a = 1;
        str2 = paramString2;
      }
      while (true)
      {
        this.i = this.j;
        this.j = 2;
        if (com.baidu.platform.comapi.b.e.a().a(localb1, localb2, null, str1, str2, null, 12, this.h, 0, null, null))
        {
          return 0;
          if ((paramString1 != null) && (!"".equals(paramString1)))
            break label212;
          str1 = "1";
          break;
          if ((paramString2 == null) || ("".equals(paramString2)))
          {
            str2 = "1";
            continue;
          }
        }
        else
        {
          return -1;
        }
        str2 = paramString2;
      }
      label212: str1 = paramString1;
    }
  }

  public int drivingSearch(String paramString1, MKPlanNode paramMKPlanNode1, String paramString2, MKPlanNode paramMKPlanNode2, List<MKWpNode> paramList)
  {
    if ((paramMKPlanNode1 == null) || (paramMKPlanNode2 == null))
      return -1;
    com.baidu.platform.comapi.b.b localb1 = new com.baidu.platform.comapi.b.b();
    if (paramMKPlanNode1.name != null)
      localb1.c = paramMKPlanNode1.name;
    String str1;
    if (paramMKPlanNode1.pt != null)
    {
      localb1.b = a(paramMKPlanNode1.pt);
      localb1.a = 1;
      str1 = paramString1;
    }
    while (true)
    {
      com.baidu.platform.comapi.b.b localb2 = new com.baidu.platform.comapi.b.b();
      if (paramMKPlanNode2.name != null)
        localb2.c = paramMKPlanNode2.name;
      String str2;
      if (paramMKPlanNode2.pt != null)
      {
        localb2.b = a(paramMKPlanNode2.pt);
        localb2.a = 1;
        str2 = paramString2;
      }
      while (true)
      {
        label121: this.i = this.j;
        this.j = 2;
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = paramList.iterator();
        label152: 
        while (localIterator.hasNext())
        {
          MKWpNode localMKWpNode = (MKWpNode)localIterator.next();
          if ((localMKWpNode.pt == null) && ((localMKWpNode.name == null) || (localMKWpNode.city == null) || (localMKWpNode.name.length() == 0) || (localMKWpNode.city.length() == 0)))
            continue;
          g localg = new g();
          if (localMKWpNode.name != null)
            localg.b = localMKWpNode.name;
          if (localMKWpNode.pt != null)
            localg.a = a(localMKWpNode.pt);
          if (localMKWpNode.city == null);
          for (localg.c = ""; ; localg.c = localMKWpNode.city)
          {
            localArrayList.add(localg);
            break label152;
            if ((paramString1 != null) && (!"".equals(paramString1)))
              break label386;
            str1 = "1";
            break;
            if ((paramString2 != null) && (!"".equals(paramString2)))
              break label380;
            str2 = "1";
            break label121;
          }
        }
        if (com.baidu.platform.comapi.b.e.a().a(localb1, localb2, null, str1, str2, null, 12, this.h, 0, localArrayList, null))
          return 0;
        return -1;
        label380: str2 = paramString2;
      }
      label386: str1 = paramString1;
    }
  }

  public int geocode(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() == 0) || (paramString1.length() > 99));
    do
    {
      return -1;
      this.i = this.j;
      this.j = 4;
    }
    while (!com.baidu.platform.comapi.b.e.a().b(paramString1, paramString2));
    return 0;
  }

  public int goToPoiPage(int paramInt)
  {
    switch (this.j)
    {
    default:
      return 0;
    case 8:
      com.baidu.platform.comapi.b.e locale4 = com.baidu.platform.comapi.b.e.a();
      String str4 = this.c;
      this.m = paramInt;
      if (locale4.a(str4, 1, paramInt, this.b, 12, null, null))
        return 0;
      return -1;
    case 7:
      com.baidu.platform.comapi.b.e locale3 = com.baidu.platform.comapi.b.e.a();
      String str2 = this.c;
      String str3 = this.e;
      this.m = paramInt;
      if (locale3.a(str2, str3, paramInt, null, 12, null))
        return 0;
      return -1;
    case 9:
      HashMap localHashMap = new HashMap();
      localHashMap.put("distance", "" + this.f);
      com.baidu.platform.comapi.b.e locale2 = com.baidu.platform.comapi.b.e.a();
      String str1 = this.c;
      this.m = paramInt;
      if (locale2.a(str1, 1, paramInt, 12, this.b, this.b, localHashMap))
        return 0;
      return -1;
    case 10:
    case 11:
    }
    com.baidu.platform.comapi.b.e locale1 = com.baidu.platform.comapi.b.e.a();
    String[] arrayOfString = this.d;
    this.m = paramInt;
    if (locale1.a(arrayOfString, 1, paramInt, 12, 0, this.b, this.b, null))
      return 0;
    return -1;
  }

  public boolean init(BMapManager paramBMapManager, MKSearchListener paramMKSearchListener)
  {
    if (paramBMapManager == null)
      return false;
    this.l = paramBMapManager;
    if (paramMKSearchListener != null)
      this.a = paramMKSearchListener;
    com.baidu.platform.comapi.b.e.a().a(this.k);
    return true;
  }

  public int poiDetailSearch(String paramString)
  {
    if (paramString == null);
    do
    {
      do
        return -1;
      while (this.j == 12);
      this.i = this.j;
      this.j = 12;
    }
    while (!com.baidu.platform.comapi.b.e.a().a(paramString));
    return 0;
  }

  public boolean poiDetailShareURLSearch(String paramString)
  {
    if (paramString == null)
      return false;
    this.i = this.j;
    this.j = 15;
    return com.baidu.platform.comapi.b.e.a().b(paramString);
  }

  public int poiMultiSearchInbounds(String[] paramArrayOfString, GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2)
  {
    int n;
    if ((paramArrayOfString == null) || (paramGeoPoint1 == null) || (paramGeoPoint2 == null))
      n = -1;
    boolean bool;
    do
    {
      return n;
      if ((paramArrayOfString.length < 2) || (paramArrayOfString.length > 10))
        return -1;
      com.baidu.platform.comapi.basestruct.c localc1 = com.baidu.platform.comapi.a.a.a().a((float)(paramGeoPoint1.getLongitudeE6() / 1000000.0D), (float)(paramGeoPoint1.getLatitudeE6() / 1000000.0D), "bd09ll");
      com.baidu.platform.comapi.basestruct.c localc2 = com.baidu.platform.comapi.a.a.a().a((float)(paramGeoPoint2.getLongitudeE6() / 1000000.0D), (float)(paramGeoPoint2.getLatitudeE6() / 1000000.0D), "bd09ll");
      this.b.a(localc1);
      this.b.b(localc2);
      this.i = this.j;
      this.j = 10;
      this.d = paramArrayOfString;
      com.baidu.platform.comapi.b.e locale = com.baidu.platform.comapi.b.e.a();
      String[] arrayOfString = this.d;
      this.m = 0;
      bool = locale.a(arrayOfString, 1, 0, 12, 0, this.b, this.b, null);
      n = 0;
    }
    while (bool);
    return -1;
  }

  public int poiMultiSearchNearBy(String[] paramArrayOfString, GeoPoint paramGeoPoint, int paramInt)
  {
    int n;
    if ((paramGeoPoint == null) || (paramArrayOfString == null))
      n = -1;
    boolean bool;
    do
    {
      return n;
      if (paramInt <= 0)
        return -1;
      if ((paramArrayOfString.length < 2) || (paramArrayOfString.length > 10))
        return -1;
      com.baidu.platform.comapi.basestruct.c localc1 = com.baidu.platform.comapi.a.a.a().a((float)(paramGeoPoint.getLongitudeE6() / 1000000.0D), (float)(paramGeoPoint.getLatitudeE6() / 1000000.0D), "bd09ll");
      com.baidu.platform.comapi.basestruct.c localc2 = new com.baidu.platform.comapi.basestruct.c(localc1.a - paramInt, localc1.b - paramInt);
      com.baidu.platform.comapi.basestruct.c localc3 = new com.baidu.platform.comapi.basestruct.c(paramInt + localc1.a, paramInt + localc1.b);
      this.b.a(localc2);
      this.b.b(localc3);
      this.i = this.j;
      this.j = 11;
      this.d = paramArrayOfString;
      com.baidu.platform.comapi.b.e locale = com.baidu.platform.comapi.b.e.a();
      String[] arrayOfString = this.d;
      this.m = 0;
      bool = locale.a(arrayOfString, 1, 0, 12, 0, this.b, this.b, null);
      n = 0;
    }
    while (bool);
    return -1;
  }

  public boolean poiRGCShareURLSearch(GeoPoint paramGeoPoint, String paramString1, String paramString2)
  {
    if (paramGeoPoint == null)
      return false;
    GeoPoint localGeoPoint = com.baidu.mapapi.utils.e.b(paramGeoPoint);
    com.baidu.platform.comapi.basestruct.c localc = new com.baidu.platform.comapi.basestruct.c(localGeoPoint.getLongitudeE6(), localGeoPoint.getLatitudeE6());
    this.i = this.j;
    this.j = 14;
    return com.baidu.platform.comapi.b.e.a().a(localc, paramString1, paramString2);
  }

  public int poiSearchInCity(String paramString1, String paramString2)
  {
    int n;
    if (paramString2 == null)
      n = -1;
    boolean bool;
    do
    {
      return n;
      if (paramString1 == null)
        paramString1 = "";
      String str1 = paramString1.trim();
      String str2 = paramString2.trim();
      if (str1.length() > 16)
        return -1;
      if ((str2.length() == 0) || (str2.length() > 99))
        return -1;
      this.c = str2;
      this.e = str1;
      this.i = this.j;
      this.j = 7;
      com.baidu.platform.comapi.b.e locale = com.baidu.platform.comapi.b.e.a();
      String str3 = this.c;
      String str4 = this.e;
      this.m = 0;
      bool = locale.a(str3, str4, 0, null, 12, null);
      n = 0;
    }
    while (bool);
    return -1;
  }

  public int poiSearchInbounds(String paramString, GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2)
  {
    if ((paramString == null) || (paramGeoPoint1 == null) || (paramGeoPoint2 == null))
      return -1;
    String str1 = paramString.trim();
    if ((str1.length() == 0) || (str1.length() > 99))
      return -1;
    com.baidu.platform.comapi.basestruct.c localc1 = com.baidu.platform.comapi.a.a.a().a((float)(paramGeoPoint1.getLongitudeE6() / 1000000.0D), (float)(paramGeoPoint1.getLatitudeE6() / 1000000.0D), "bd09ll");
    com.baidu.platform.comapi.basestruct.c localc2 = com.baidu.platform.comapi.a.a.a().a((float)(paramGeoPoint2.getLongitudeE6() / 1000000.0D), (float)(paramGeoPoint2.getLatitudeE6() / 1000000.0D), "bd09ll");
    this.b.a(localc1);
    this.b.b(localc2);
    this.i = this.j;
    this.j = 8;
    this.c = str1;
    com.baidu.platform.comapi.b.e locale = com.baidu.platform.comapi.b.e.a();
    String str2 = this.c;
    this.m = 0;
    if (locale.a(str2, 1, 0, this.b, 12, null, null))
      return 0;
    return -1;
  }

  public int poiSearchNearBy(String paramString, GeoPoint paramGeoPoint, int paramInt)
  {
    int n;
    if ((paramGeoPoint == null) || (paramString == null))
      n = -1;
    boolean bool;
    do
    {
      return n;
      if (paramInt <= 0)
        return -1;
      String str1 = paramString.trim();
      if ((str1.length() == 0) || (str1.length() > 99))
        return -1;
      GeoPoint localGeoPoint = com.baidu.mapapi.utils.e.b(paramGeoPoint);
      com.baidu.platform.comapi.basestruct.c localc1 = new com.baidu.platform.comapi.basestruct.c(localGeoPoint.getLongitudeE6() - paramInt, localGeoPoint.getLatitudeE6() - paramInt);
      com.baidu.platform.comapi.basestruct.c localc2 = new com.baidu.platform.comapi.basestruct.c(paramInt + localGeoPoint.getLongitudeE6(), paramInt + localGeoPoint.getLatitudeE6());
      this.b.a(localc1);
      this.b.b(localc2);
      this.c = str1;
      this.i = this.j;
      this.j = 9;
      this.f = paramInt;
      HashMap localHashMap = new HashMap();
      localHashMap.put("distance", "" + this.f);
      com.baidu.platform.comapi.b.e locale = com.baidu.platform.comapi.b.e.a();
      String str2 = this.c;
      this.m = 0;
      bool = locale.a(str2, 1, 0, 12, this.b, this.b, localHashMap);
      n = 0;
    }
    while (bool);
    return -1;
  }

  public int reverseGeocode(GeoPoint paramGeoPoint)
  {
    if (paramGeoPoint == null);
    com.baidu.platform.comapi.basestruct.c localc;
    do
    {
      return -1;
      localc = a(paramGeoPoint);
      this.i = this.j;
      this.j = 5;
    }
    while (!com.baidu.platform.comapi.b.e.a().a(localc));
    return 0;
  }

  public int setDrivingPolicy(int paramInt)
  {
    int n = -1;
    if ((paramInt <= 2) && (paramInt >= n))
    {
      this.h = paramInt;
      n = 0;
    }
    return n;
  }

  public int setTransitPolicy(int paramInt)
  {
    if ((paramInt > 6) || (paramInt < 3))
      return -1;
    this.g = paramInt;
    return 0;
  }

  public int suggestionSearch(String paramString1, String paramString2)
  {
    int n;
    if (paramString1 == null)
    {
      n = -1;
      return n;
    }
    if ((paramString2 == null) || (paramString2.length() == 0));
    for (String str1 = "1"; ; str1 = paramString2)
    {
      String str2 = paramString1.trim();
      if ((str2.length() == 0) || (str2.length() > 99))
        return -1;
      this.i = this.j;
      this.j = 6;
      boolean bool = com.baidu.platform.comapi.b.e.a().a(str2, 0, str1, null, 12, null);
      n = 0;
      if (bool)
        break;
      return -1;
    }
  }

  public int transitSearch(String paramString, MKPlanNode paramMKPlanNode1, MKPlanNode paramMKPlanNode2)
  {
    if ((paramString == null) || (paramMKPlanNode1 == null) || (paramMKPlanNode2 == null))
      return -1;
    if (paramString.length() > 31)
      return -1;
    com.baidu.platform.comapi.b.b localb1 = new com.baidu.platform.comapi.b.b();
    if (paramMKPlanNode1.name != null)
      localb1.c = paramMKPlanNode1.name;
    if (paramMKPlanNode1.pt != null)
    {
      localb1.b = a(paramMKPlanNode1.pt);
      localb1.a = 1;
    }
    com.baidu.platform.comapi.b.b localb2 = new com.baidu.platform.comapi.b.b();
    if (paramMKPlanNode2.name != null)
      localb2.c = paramMKPlanNode2.name;
    if (paramMKPlanNode2.pt != null)
    {
      localb2.b = a(paramMKPlanNode2.pt);
      localb2.a = 1;
    }
    this.i = this.j;
    this.j = 3;
    if (com.baidu.platform.comapi.b.e.a().a(localb1, localb2, paramString, null, 12, this.g, null))
      return 0;
    return -1;
  }

  public int walkingSearch(String paramString1, MKPlanNode paramMKPlanNode1, String paramString2, MKPlanNode paramMKPlanNode2)
  {
    if ((paramMKPlanNode1 == null) || (paramMKPlanNode2 == null))
      return -1;
    com.baidu.platform.comapi.b.b localb1 = new com.baidu.platform.comapi.b.b();
    if (paramMKPlanNode1.name != null)
      localb1.c = paramMKPlanNode1.name;
    String str1;
    if (paramMKPlanNode1.pt != null)
    {
      localb1.b = a(paramMKPlanNode1.pt);
      localb1.a = 1;
      str1 = paramString1;
    }
    while (true)
    {
      com.baidu.platform.comapi.b.b localb2 = new com.baidu.platform.comapi.b.b();
      if (paramMKPlanNode2.name != null)
        localb2.c = paramMKPlanNode2.name;
      String str2;
      if (paramMKPlanNode2.pt != null)
      {
        localb2.b = a(paramMKPlanNode2.pt);
        localb2.a = 1;
        str2 = paramString2;
      }
      while (true)
      {
        this.i = this.j;
        this.j = 1;
        if (com.baidu.platform.comapi.b.e.a().a(localb1, localb2, null, str1, str2, null, 12, null))
        {
          return 0;
          if ((paramString1 != null) && (!"".equals(paramString1)))
            break label206;
          str1 = "1";
          break;
          if ((paramString2 == null) || ("".equals(paramString2)))
          {
            str2 = "1";
            continue;
          }
        }
        else
        {
          return -1;
        }
        str2 = paramString2;
      }
      label206: str1 = paramString1;
    }
  }

  private class a
    implements com.baidu.platform.comapi.b.c
  {
    private a()
    {
    }

    public void a(int paramInt)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      switch (paramInt)
      {
      default:
      case 5:
      case 8:
      case 404:
      case 11:
      case 12:
      case 13:
      case 14:
      }
      while (true)
        switch (MKSearch.a(MKSearch.this))
        {
        case 5:
        case 10:
        case 11:
        case 12:
        default:
          return;
        case 1:
          MKSearch.this.a.onGetWalkingRouteResult(null, paramInt);
          return;
          paramInt = 2;
          continue;
          paramInt = 100;
        case 2:
        case 3:
        case 13:
        case 4:
        case 7:
        case 8:
        case 9:
        case 6:
        }
      MKSearch.this.a.onGetTransitRouteResult(null, paramInt);
      return;
      MKSearch.this.a.onGetTransitRouteResult(null, paramInt);
      return;
      MKSearch.this.a.onGetBusDetailResult(null, paramInt);
      return;
      MKSearch.this.a.onGetAddrResult(null, paramInt);
      MKSearch.this.a.onGetAddrResult(null, paramInt);
      return;
      MKSearch.this.a.onGetPoiResult(null, 1, paramInt);
      return;
      MKSearch.this.a.onGetSuggestionResult(null, paramInt);
    }

    public void a(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      switch (MKSearch.a(MKSearch.this))
      {
      case 5:
      case 6:
      default:
        return;
      case 4:
        MKAddrInfo localMKAddrInfo = new MKAddrInfo();
        if (c.b(paramString, localMKAddrInfo))
        {
          MKSearch.this.a.onGetAddrResult(localMKAddrInfo, 0);
          return;
        }
        MKSearch.this.a.onGetAddrResult(localMKAddrInfo, 100);
        return;
      case 7:
        MKPoiResult localMKPoiResult3 = new MKPoiResult();
        c.a(paramString, localMKPoiResult3, MKSearch.b(MKSearch.this));
        if (MKSearch.this.a != null)
        {
          MKSearch.this.a.onGetPoiResult(localMKPoiResult3, 11, 0);
          return;
        }
        MKSearch.this.a.onGetPoiResult(localMKPoiResult3, 11, 100);
        return;
      case 8:
      case 9:
        MKPoiResult localMKPoiResult2 = new MKPoiResult();
        c.a(paramString, localMKPoiResult2, MKSearch.b(MKSearch.this));
        if (MKSearch.this.a != null)
        {
          MKSearch.this.a.onGetPoiResult(localMKPoiResult2, 21, 0);
          return;
        }
        MKSearch.this.a.onGetPoiResult(localMKPoiResult2, 21, 100);
        return;
      case 10:
      case 11:
      }
      MKPoiResult localMKPoiResult1 = new MKPoiResult();
      if (c.b(paramString, localMKPoiResult1, MKSearch.b(MKSearch.this)))
      {
        MKSearch.this.a.onGetPoiResult(localMKPoiResult1, 45, 0);
        return;
      }
      MKSearch.this.a.onGetPoiResult(localMKPoiResult1, 45, 100);
    }

    public void b(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      switch (MKSearch.a(MKSearch.this))
      {
      default:
        return;
      case 7:
      case 8:
      case 9:
        MKPoiResult localMKPoiResult2 = new MKPoiResult();
        c.a(paramString, localMKPoiResult2);
        if (MKSearch.this.a != null)
        {
          MKSearch.this.a.onGetPoiResult(localMKPoiResult2, 7, 4);
          return;
        }
        MKSearch.this.a.onGetPoiResult(localMKPoiResult2, 7, 100);
        return;
      case 10:
      case 11:
      }
      MKPoiResult localMKPoiResult1 = new MKPoiResult();
      if (c.b(paramString, localMKPoiResult1, MKSearch.b(MKSearch.this)))
      {
        MKSearch.this.a.onGetPoiResult(localMKPoiResult1, 45, 4);
        return;
      }
      MKSearch.this.a.onGetPoiResult(localMKPoiResult1, 45, 100);
    }

    public void c(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      MKRouteAddrResult localMKRouteAddrResult = new MKRouteAddrResult();
      switch (MKSearch.a(MKSearch.this))
      {
      default:
        return;
      case 1:
        MKWalkingRouteResult localMKWalkingRouteResult = new MKWalkingRouteResult();
        if (c.a(paramString, localMKRouteAddrResult))
        {
          localMKWalkingRouteResult.a(localMKRouteAddrResult);
          MKSearch.this.a.onGetWalkingRouteResult(localMKWalkingRouteResult, 4);
          return;
        }
        MKSearch.this.a.onGetWalkingRouteResult(localMKWalkingRouteResult, 100);
        return;
      case 2:
        MKDrivingRouteResult localMKDrivingRouteResult = new MKDrivingRouteResult();
        if (c.a(paramString, localMKRouteAddrResult))
        {
          localMKDrivingRouteResult.a(localMKRouteAddrResult);
          MKSearch.this.a.onGetDrivingRouteResult(localMKDrivingRouteResult, 4);
          return;
        }
        MKSearch.this.a.onGetDrivingRouteResult(localMKDrivingRouteResult, 100);
        return;
      case 3:
      }
      MKTransitRouteResult localMKTransitRouteResult = new MKTransitRouteResult();
      if (c.a(paramString, localMKRouteAddrResult))
      {
        localMKTransitRouteResult.a(localMKRouteAddrResult);
        MKSearch.this.a.onGetTransitRouteResult(localMKTransitRouteResult, 4);
        return;
      }
      MKSearch.this.a.onGetTransitRouteResult(localMKTransitRouteResult, 100);
    }

    public void d(String paramString)
    {
    }

    public void e(String paramString)
    {
    }

    public void f(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      switch (MKSearch.a(MKSearch.this))
      {
      default:
        return;
      case 12:
      }
      if (!PlaceCaterActivity.isShow())
      {
        if (!c.a(paramString, new e()))
          break label145;
        Intent localIntent = new Intent(MKSearch.c(MKSearch.this).getContext(), PlaceCaterActivity.class);
        localIntent.putExtra("result", paramString);
        localIntent.addFlags(268435456);
        MKSearch.c(MKSearch.this).getContext().startActivity(localIntent);
        MKSearch.this.a.onGetPoiDetailSearchResult(52, 0);
      }
      while (true)
      {
        MKSearch.a(MKSearch.this, MKSearch.d(MKSearch.this));
        return;
        label145: MKSearch.this.a.onGetPoiDetailSearchResult(52, 100);
      }
    }

    public void g(String paramString)
    {
      if ((paramString == null) || (MKSearch.this.a == null))
        return;
      MKShareUrlResult localMKShareUrlResult = new MKShareUrlResult();
      if (c.a(paramString, localMKShareUrlResult))
      {
        switch (MKSearch.a(MKSearch.this))
        {
        default:
        case 15:
        case 14:
        }
        while (true)
        {
          MKSearch.this.a.onGetShareUrlResult(localMKShareUrlResult, localMKShareUrlResult.type, 0);
          return;
          localMKShareUrlResult.type = 18;
          continue;
          localMKShareUrlResult.type = 17;
        }
      }
      MKSearch.this.a.onGetShareUrlResult(localMKShareUrlResult, -1, -1);
    }

    public void h(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      MKDrivingRouteResult localMKDrivingRouteResult = new MKDrivingRouteResult();
      try
      {
        if (c.a(paramString, localMKDrivingRouteResult))
        {
          MKSearch.this.a.onGetDrivingRouteResult(localMKDrivingRouteResult, 0);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        MKSearch.this.a.onGetDrivingRouteResult(localMKDrivingRouteResult, 100);
      }
    }

    public void i(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      MKWalkingRouteResult localMKWalkingRouteResult = new MKWalkingRouteResult();
      try
      {
        if (c.a(paramString, localMKWalkingRouteResult))
        {
          MKSearch.this.a.onGetWalkingRouteResult(localMKWalkingRouteResult, 0);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        MKSearch.this.a.onGetWalkingRouteResult(localMKWalkingRouteResult, 100);
      }
    }

    public void j(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      MKTransitRouteResult localMKTransitRouteResult = new MKTransitRouteResult();
      try
      {
        if (c.a(paramString, localMKTransitRouteResult))
        {
          MKSearch.this.a.onGetTransitRouteResult(localMKTransitRouteResult, 0);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        MKSearch.this.a.onGetTransitRouteResult(localMKTransitRouteResult, 100);
      }
    }

    public void k(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      MKAddrInfo localMKAddrInfo2;
      switch (MKSearch.a(MKSearch.this))
      {
      default:
        return;
      case 4:
        localMKAddrInfo2 = new MKAddrInfo();
        if (!c.b(paramString, localMKAddrInfo2))
          break;
        MKSearch.this.a.onGetAddrResult(localMKAddrInfo2, 0);
        return;
      case 5:
        MKAddrInfo localMKAddrInfo1 = new MKAddrInfo();
        if (c.a(paramString, localMKAddrInfo1))
        {
          MKSearch.this.a.onGetAddrResult(localMKAddrInfo1, 0);
          return;
        }
        MKSearch.this.a.onGetAddrResult(localMKAddrInfo1, 100);
        return;
      }
      MKSearch.this.a.onGetAddrResult(localMKAddrInfo2, 100);
    }

    public void l(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      MKBusLineResult localMKBusLineResult = new MKBusLineResult();
      if (c.a(paramString, localMKBusLineResult))
      {
        MKSearch.this.a.onGetBusDetailResult(localMKBusLineResult, 0);
        return;
      }
      MKSearch.this.a.onGetBusDetailResult(localMKBusLineResult, 100);
    }

    public void m(String paramString)
    {
      if ((MKSearch.this.a == null) || (!com.baidu.platform.comapi.a.a))
        return;
      switch (MKSearch.a(MKSearch.this))
      {
      default:
        return;
      case 6:
      }
      MKSuggestionResult localMKSuggestionResult = new MKSuggestionResult();
      if (c.a(paramString, localMKSuggestionResult))
      {
        MKSearch.this.a.onGetSuggestionResult(localMKSuggestionResult, 0);
        return;
      }
      MKSearch.this.a.onGetSuggestionResult(localMKSuggestionResult, 100);
    }

    public void n(String paramString)
    {
    }

    public void o(String paramString)
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKSearch
 * JD-Core Version:    0.6.0
 */