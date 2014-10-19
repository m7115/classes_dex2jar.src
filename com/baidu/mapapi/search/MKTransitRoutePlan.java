package com.baidu.mapapi.search;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;

public class MKTransitRoutePlan
{
  private int a;
  private int b;
  private String c;
  private ArrayList<MKRoute> d;
  private ArrayList<MKLine> e;
  private GeoPoint f;
  private GeoPoint g;

  void a(int paramInt)
  {
    this.a = paramInt;
  }

  void a(GeoPoint paramGeoPoint)
  {
    this.f = paramGeoPoint;
  }

  void a(String paramString)
  {
    this.c = paramString;
  }

  void a(ArrayList<MKRoute> paramArrayList)
  {
    this.d = paramArrayList;
  }

  void b(int paramInt)
  {
    this.b = paramInt;
  }

  void b(GeoPoint paramGeoPoint)
  {
    this.g = paramGeoPoint;
  }

  public String getContent()
  {
    return this.c;
  }

  public int getDistance()
  {
    return this.a;
  }

  public GeoPoint getEnd()
  {
    return this.g;
  }

  public MKLine getLine(int paramInt)
  {
    if ((this.e == null) || (paramInt < 0) || (paramInt > -1 + this.e.size()))
      return null;
    return (MKLine)this.e.get(paramInt);
  }

  public int getNumLines()
  {
    if (this.e != null)
      return this.e.size();
    return 0;
  }

  public int getNumRoute()
  {
    if (this.d != null)
      return this.d.size();
    return 0;
  }

  public MKRoute getRoute(int paramInt)
  {
    if (this.d != null)
      return (MKRoute)this.d.get(paramInt);
    return null;
  }

  public GeoPoint getStart()
  {
    return this.f;
  }

  public int getTime()
  {
    return this.b;
  }

  public void setLine(ArrayList<MKLine> paramArrayList)
  {
    this.e = paramArrayList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKTransitRoutePlan
 * JD-Core Version:    0.6.0
 */