package com.baidu.mapapi.map;

import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;

public class Geometry
{
  int a;
  ArrayList<GeoPoint> b = null;
  int c = 0;

  public void setCircle(GeoPoint paramGeoPoint, int paramInt)
  {
    this.b.clear();
    this.a = 4;
    this.b.add(paramGeoPoint);
    this.c = e.a(paramGeoPoint, paramInt);
  }

  public void setEnvelope(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2)
  {
    this.b.clear();
    this.a = 3;
    this.b.add(paramGeoPoint1);
    this.b.add(paramGeoPoint2);
  }

  public void setPoint(GeoPoint paramGeoPoint, int paramInt)
  {
    this.b.clear();
    this.a = 1;
    this.c = paramInt;
    this.b.add(paramGeoPoint);
  }

  public void setPolyLine(GeoPoint[] paramArrayOfGeoPoint)
  {
    this.a = 2;
    if (paramArrayOfGeoPoint == null);
    while (true)
    {
      return;
      this.b.clear();
      for (int i = 0; i < paramArrayOfGeoPoint.length; i++)
        this.b.add(paramArrayOfGeoPoint[i]);
    }
  }

  public void setPolygon(GeoPoint[] paramArrayOfGeoPoint)
  {
    this.a = 5;
    if (paramArrayOfGeoPoint == null);
    while (true)
    {
      return;
      this.b.clear();
      for (int i = 0; i < paramArrayOfGeoPoint.length; i++)
        this.b.add(paramArrayOfGeoPoint[i]);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Geometry
 * JD-Core Version:    0.6.0
 */