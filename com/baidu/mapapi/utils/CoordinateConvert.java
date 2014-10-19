package com.baidu.mapapi.utils;

import com.baidu.platform.comapi.a.a;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.c;

public class CoordinateConvert
{
  private static GeoPoint a(GeoPoint paramGeoPoint, String paramString)
  {
    if (paramGeoPoint == null);
    c localc;
    do
    {
      return null;
      float f1 = (float)(1.0E-006D * paramGeoPoint.getLongitudeE6());
      float f2 = (float)(1.0E-006D * paramGeoPoint.getLatitudeE6());
      localc = a.a().a(f1, f2, paramString);
    }
    while (localc == null);
    return e.a(new GeoPoint(localc.b(), localc.a()));
  }

  public static GeoPoint fromGcjToBaidu(GeoPoint paramGeoPoint)
  {
    return a(paramGeoPoint, "gcj02");
  }

  public static GeoPoint fromWgs84ToBaidu(GeoPoint paramGeoPoint)
  {
    return a(paramGeoPoint, "wgs84");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.CoordinateConvert
 * JD-Core Version:    0.6.0
 */