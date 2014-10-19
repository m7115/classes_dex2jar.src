package cn.suanya.hotel.util;

import cn.suanya.hotel.domain.Location;
import com.baidu.mapapi.utils.CoordinateConvert;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class MapUtil
{
  public static GeoPoint MapBar2Baidu(GeoPoint paramGeoPoint)
  {
    return CoordinateConvert.fromWgs84ToBaidu(mapBar2WGS84(paramGeoPoint));
  }

  public static GeoPoint baidu2Gcj(GeoPoint paramGeoPoint)
  {
    GeoPoint localGeoPoint1 = baiduOffset(paramGeoPoint);
    GeoPoint localGeoPoint2 = new GeoPoint(0, 0);
    localGeoPoint2.setLatitudeE6(paramGeoPoint.getLatitudeE6() + localGeoPoint1.getLatitudeE6());
    localGeoPoint2.setLongitudeE6(paramGeoPoint.getLongitudeE6() + localGeoPoint1.getLongitudeE6());
    return localGeoPoint2;
  }

  public static GeoPoint baidu2MapBar(GeoPoint paramGeoPoint)
  {
    GeoPoint localGeoPoint1 = mapBarOffset(paramGeoPoint);
    GeoPoint localGeoPoint2 = new GeoPoint(0, 0);
    localGeoPoint2.setLatitudeE6(paramGeoPoint.getLatitudeE6() + localGeoPoint1.getLatitudeE6());
    localGeoPoint2.setLongitudeE6(paramGeoPoint.getLongitudeE6() + localGeoPoint1.getLongitudeE6());
    return localGeoPoint2;
  }

  private static GeoPoint baiduOffset(GeoPoint paramGeoPoint)
  {
    GeoPoint localGeoPoint = CoordinateConvert.fromGcjToBaidu(paramGeoPoint);
    return new GeoPoint(paramGeoPoint.getLatitudeE6() - localGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6() - localGeoPoint.getLongitudeE6());
  }

  public static double distance(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d1 = Math.abs(paramDouble1 - paramDouble3);
    double d2 = Math.abs(paramDouble2 - paramDouble4);
    return 40075000.0D * Math.pow(Math.pow(d1, 2.0D) + Math.pow(d2, 2.0D), 0.5D) / 360.0D;
  }

  public static double distance(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2)
  {
    int i = paramGeoPoint1.getLatitudeE6() - paramGeoPoint2.getLatitudeE6();
    int j = paramGeoPoint1.getLongitudeE6() - paramGeoPoint2.getLongitudeE6();
    return Math.pow(i * i + j * j, 0.5D);
  }

  public static Location geo2Loc(GeoPoint paramGeoPoint)
  {
    return new Location(paramGeoPoint.getLatitudeE6() / 1000000.0D, paramGeoPoint.getLongitudeE6() / 1000000.0D);
  }

  public static GeoPoint loc2geo(Location paramLocation)
  {
    return new GeoPoint((int)(1000000.0D * paramLocation.getLatitude()), (int)(1000000.0D * paramLocation.getLongitude()));
  }

  private static GeoPoint mapBar2WGS84(GeoPoint paramGeoPoint)
  {
    int i = 1;
    int j = paramGeoPoint.getLatitudeE6() / 10 % 36000000;
    int k = paramGeoPoint.getLongitudeE6() / 10 % 36000000;
    int m = k + (int)(-(Math.cos(j / 100000) * (k / 18000) + Math.sin(k / 100000) * (j / 9000)));
    int n = j + (int)(-(Math.sin(j / 100000) * (k / 18000) + Math.cos(k / 100000) * (j / 9000)));
    int i1 = k + (int)(-(Math.cos(n / 100000) * (m / 18000) + Math.sin(m / 100000) * (n / 9000)));
    int i2;
    int i3;
    int i4;
    if (k > 0)
    {
      i2 = i;
      i3 = i2 + i1;
      i4 = j + (int)(-(Math.sin(n / 100000) * (m / 18000) + Math.cos(m / 100000) * (n / 9000)));
      if (j <= 0)
        break label224;
    }
    while (true)
    {
      return new GeoPoint(10 * (i + i4), i3 * 10);
      i2 = -1;
      break;
      label224: i = -1;
    }
  }

  private static GeoPoint mapBarOffset(GeoPoint paramGeoPoint)
  {
    GeoPoint localGeoPoint = MapBar2Baidu(paramGeoPoint);
    return new GeoPoint(paramGeoPoint.getLatitudeE6() - localGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6() - localGeoPoint.getLongitudeE6());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.util.MapUtil
 * JD-Core Version:    0.6.0
 */