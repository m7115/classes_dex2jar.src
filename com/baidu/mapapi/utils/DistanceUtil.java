package com.baidu.mapapi.utils;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.c;
import com.baidu.platform.comjni.tools.a;

public class DistanceUtil
{
  public static double getDistance(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2)
  {
    GeoPoint localGeoPoint1 = e.b(paramGeoPoint1);
    GeoPoint localGeoPoint2 = e.b(paramGeoPoint2);
    if ((localGeoPoint1 == null) || (localGeoPoint2 == null))
      return 0.0D;
    return a.a(new c(localGeoPoint1.getLongitudeE6(), localGeoPoint1.getLatitudeE6()), new c(localGeoPoint2.getLongitudeE6(), localGeoPoint2.getLatitudeE6()));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.DistanceUtil
 * JD-Core Version:    0.6.0
 */