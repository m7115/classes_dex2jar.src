package com.baidu.mapapi.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.mapapi.search.c;

public class GeoSearchManager
{
  public static final int GEO_SEARCH = 50;
  public static final int GEO_SEARCH_DETAILS = 51;
  static a b;
  static Handler c = new a();
  private static GeoSearchManager e;
  com.baidu.platform.comjni.map.search.a a;
  private Bundle d = null;

  public static GeoSearchManager getInstance()
  {
    if (e == null)
      e = new GeoSearchManager();
    return e;
  }

  String a(int paramInt)
  {
    String str = this.a.a(paramInt);
    if ((str != null) && (str.trim().length() <= 0))
      str = null;
    return str;
  }

  public void destory()
  {
    if (b != null)
    {
      b.a(null);
      b.a = null;
    }
    b = null;
    if (c != null)
      com.baidu.platform.comjni.engine.a.b(2000, c);
    if (this.a != null)
    {
      this.a.c();
      this.a = null;
    }
  }

  public boolean init(GeoSearchListener paramGeoSearchListener)
  {
    if (this.a == null)
    {
      this.a = new com.baidu.platform.comjni.map.search.a();
      if (this.a.a() == 0)
      {
        this.a = null;
        return false;
      }
      if (b == null)
        b = new a(paramGeoSearchListener);
      while (true)
      {
        b.a(this);
        com.baidu.platform.comjni.engine.a.a(2000, c);
        return true;
        b.a = paramGeoSearchListener;
      }
    }
    this.a.b();
    if (b == null)
      b = new a(paramGeoSearchListener);
    while (true)
    {
      b.a(this);
      com.baidu.platform.comjni.engine.a.a(2000, c);
      break;
      b.a = paramGeoSearchListener;
    }
  }

  public boolean searchBounds(BoundsSearchInfo paramBoundsSearchInfo)
  {
    if (paramBoundsSearchInfo.ak == null)
      return false;
    if (this.d == null)
      this.d = new Bundle();
    while (true)
    {
      this.d.putString("url", "http://api.map.baidu.com/geosearch/poi" + paramBoundsSearchInfo.a());
      return this.a.i(this.d);
      this.d.clear();
    }
  }

  public boolean searchDetail(DetailSearchInfo paramDetailSearchInfo)
  {
    if (paramDetailSearchInfo.ak == null)
      return false;
    if (this.d == null)
      this.d = new Bundle();
    while (true)
    {
      this.d.putString("url", "http://api.map.baidu.com/geosearch/detail" + paramDetailSearchInfo.a());
      return this.a.j(this.d);
      this.d.clear();
    }
  }

  public boolean searchNearby(NearbySearchInfo paramNearbySearchInfo)
  {
    if (paramNearbySearchInfo.ak == null)
      return false;
    if (this.d == null)
      this.d = new Bundle();
    while (true)
    {
      this.d.putString("url", "http://api.map.baidu.com/geosearch/poi" + paramNearbySearchInfo.a());
      return this.a.i(this.d);
      this.d.clear();
    }
  }

  public boolean searchRegion(RegionSearchInfo paramRegionSearchInfo)
  {
    if (paramRegionSearchInfo.ak == null)
      return false;
    if (this.d == null)
      this.d = new Bundle();
    while (true)
    {
      this.d.putString("url", "http://api.map.baidu.com/geosearch/poi" + paramRegionSearchInfo.a());
      return this.a.i(this.d);
      this.d.clear();
    }
  }

  private class a
  {
    GeoSearchListener a;
    GeoSearchManager b;

    public a(GeoSearchListener arg2)
    {
      Object localObject;
      this.a = localObject;
    }

    public void a(Message paramMessage)
    {
      if ((paramMessage.what != 2000) || (this.a == null))
        return;
      switch (paramMessage.arg1)
      {
      default:
        return;
      case 50:
        String str2 = this.b.a(50);
        GeoSearchResult localGeoSearchResult = new GeoSearchResult();
        c.a(str2, localGeoSearchResult);
        this.a.onGetGeoResult(localGeoSearchResult, paramMessage.arg1, paramMessage.arg2);
        return;
      case 51:
      }
      String str1 = this.b.a(51);
      DetailResult localDetailResult = new DetailResult();
      c.a(str1, localDetailResult);
      this.a.onGetGeoDetailsResult(localDetailResult, paramMessage.arg1, paramMessage.arg2);
    }

    void a(GeoSearchManager paramGeoSearchManager)
    {
      this.b = paramGeoSearchManager;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.cloud.GeoSearchManager
 * JD-Core Version:    0.6.0
 */