package com.baidu.mapapi.search;

import java.util.ArrayList;

public class MKPoiResult
{
  private int a = 0;
  private int b = 0;
  private int c = 0;
  private int d = 0;
  private ArrayList<MKPoiResult> e;
  private ArrayList<MKPoiInfo> f;
  private ArrayList<MKCityListInfo> g;

  void a(int paramInt)
  {
    this.b = paramInt;
  }

  void a(ArrayList<MKPoiInfo> paramArrayList)
  {
    this.f = paramArrayList;
  }

  void b(int paramInt)
  {
    this.a = paramInt;
  }

  void b(ArrayList<MKPoiResult> paramArrayList)
  {
    this.e = paramArrayList;
  }

  void c(int paramInt)
  {
    this.c = paramInt;
  }

  void c(ArrayList<MKCityListInfo> paramArrayList)
  {
    this.g = paramArrayList;
  }

  void d(int paramInt)
  {
    this.d = paramInt;
  }

  public ArrayList<MKPoiInfo> getAllPoi()
  {
    return this.f;
  }

  public MKCityListInfo getCityListInfo(int paramInt)
  {
    if ((this.g == null) || (paramInt < 0) || (paramInt > -1 + this.g.size()))
      return null;
    return (MKCityListInfo)this.g.get(paramInt);
  }

  public int getCityListNum()
  {
    if (this.g != null)
      return this.g.size();
    return 0;
  }

  public int getCurrentNumPois()
  {
    return this.b;
  }

  public ArrayList<MKPoiResult> getMultiPoiResult()
  {
    return this.e;
  }

  public int getNumPages()
  {
    return this.c;
  }

  public int getNumPois()
  {
    return this.a;
  }

  public int getPageIndex()
  {
    return this.d;
  }

  public MKPoiInfo getPoi(int paramInt)
  {
    if ((this.f == null) || (paramInt < 0) || (paramInt > -1 + this.f.size()))
      return null;
    return (MKPoiInfo)this.f.get(paramInt);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKPoiResult
 * JD-Core Version:    0.6.0
 */