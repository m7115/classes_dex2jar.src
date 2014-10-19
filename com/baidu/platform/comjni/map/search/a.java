package com.baidu.platform.comjni.map.search;

import android.os.Bundle;

public class a
{
  private int a = 0;
  private JNISearch b = null;

  public int a()
  {
    this.a = this.b.Create();
    return this.a;
  }

  public String a(int paramInt)
  {
    return this.b.GetSearchResult(this.a, paramInt);
  }

  public boolean a(int paramInt1, int paramInt2)
  {
    return this.b.ReverseGeocodeSearch(this.a, paramInt1, paramInt2);
  }

  public boolean a(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    return this.b.PoiRGCShareUrlSearch(this.a, paramInt1, paramInt2, paramString1, paramString2);
  }

  public boolean a(Bundle paramBundle)
  {
    return this.b.ForceSearchByCityName(this.a, paramBundle);
  }

  public boolean a(String paramString)
  {
    return this.b.POIDetailSearchPlace(this.a, paramString);
  }

  public boolean a(String paramString1, String paramString2)
  {
    return this.b.BusLineDetailSearch(this.a, paramString1, paramString2);
  }

  public int b()
  {
    return this.b.QueryInterface(this.a);
  }

  public boolean b(Bundle paramBundle)
  {
    return this.b.AreaSearch(this.a, paramBundle);
  }

  public boolean b(String paramString)
  {
    return this.b.PoiDetailShareUrlSearch(this.a, paramString);
  }

  public boolean b(String paramString1, String paramString2)
  {
    return this.b.geocode(this.a, paramString1, paramString2);
  }

  public int c()
  {
    return this.b.Release(this.a);
  }

  public boolean c(Bundle paramBundle)
  {
    return this.b.AreaMultiSearch(this.a, paramBundle);
  }

  public boolean d(Bundle paramBundle)
  {
    return this.b.RoutePlanByBus(this.a, paramBundle);
  }

  public boolean e(Bundle paramBundle)
  {
    return this.b.RoutePlanByCar(this.a, paramBundle);
  }

  public boolean f(Bundle paramBundle)
  {
    return this.b.RoutePlanByFoot(this.a, paramBundle);
  }

  public boolean g(Bundle paramBundle)
  {
    return this.b.SuggestionSearch(this.a, paramBundle);
  }

  public boolean h(Bundle paramBundle)
  {
    return this.b.MapBoundSearch(this.a, paramBundle);
  }

  public boolean i(Bundle paramBundle)
  {
    return this.b.GeoSearch(this.a, paramBundle);
  }

  public boolean j(Bundle paramBundle)
  {
    return this.b.GeoDetailSearch(this.a, paramBundle);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.search.a
 * JD-Core Version:    0.6.0
 */