package com.baidu.mapapi.utils;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public class e
{
  static double[] a = { 12890594.859999999D, 8362377.8700000001D, 5591021.0D, 3481989.8300000001D, 1678043.1200000001D, 0.0D };
  static double[] b = { 75000000.0D, 60000000.0D, 45000000.0D, 30000000.0D, 15000000.0D, 0.0D };
  static double[][] c = { { 1.410526172116255E-008D, 8.98305509648872E-006D, -1.9939833816331D, 200.98243831067961D, -187.2403703815547D, 91.608751666984304D, -23.38765649603339D, 2.57121317296198D, -0.03801003308653D, 17337981.199999999D }, { -7.435856389565537E-009D, 8.983055097726239E-006D, -0.78625201886289D, 96.326875997598464D, -1.85204757529826D, -59.369359054858769D, 47.400335492967372D, -16.50741931063887D, 2.28786674699375D, 10260144.859999999D }, { -3.030883460898826E-008D, 8.98305509983578E-006D, 0.30071316287616D, 59.742936184422767D, 7.357984074871D, -25.383710026647449D, 13.45380521110908D, -3.29883767235584D, 0.32710905363475D, 6856817.3700000001D }, { -1.981981304930552E-008D, 8.983055099779535E-006D, 0.03278182852591D, 40.316785277057441D, 0.65659298677277D, -4.44255534477492D, 0.85341911805263D, 0.12923347998204D, -0.04625736007561D, 4482777.0599999996D }, { 3.09191371068437E-009D, 8.983055096812155E-006D, 6.995724061999999E-005D, 23.109343041449009D, -0.00023663490511D, -0.6321817810242D, -0.00663494467273D, 0.03430082397953D, -0.00466043876332D, 2555164.3999999999D }, { 2.890871144776878E-009D, 8.983055095805407E-006D, -3.068298E-008D, 7.47137025468032D, -3.53937994E-006D, -0.02145144861037D, -1.234426596E-005D, 0.00010322952773D, -3.23890364E-006D, 826088.5D } };
  static double[][] d = { { -0.0015702102444D, 111320.7020616939D, 1704480524535203.0D, -10338987376042340.0D, 26112667856603880.0D, -35149669176653700.0D, 26595700718403920.0D, -10725012454188240.0D, 1800819912950474.0D, 82.5D }, { 0.000827782451617253D, 111320.70204635779D, 647795574.66716075D, -4082003173.6413159D, 10774905663.511419D, -15171875531.515591D, 12053065338.62167D, -5124939663.5774717D, 913311935.95120323D, 67.5D }, { 0.00337398766765D, 111320.70202021619D, 4481351.0458903648D, -23393751.199316621D, 79682215.471864551D, -115964993.2797253D, 97236711.156021446D, -43661946.337528206D, 8477230.5011352338D, 52.5D }, { 0.00220636496208D, 111320.70202091279D, 51751.861128411307D, 3796837.7494702451D, 992013.73977910134D, -1221952.21711287D, 1340652.697009075D, -620943.69909843116D, 144416.92938062409D, 37.5D }, { -0.0003441963504368392D, 111320.7020576856D, 278.23539807727519D, 2485758.6900353939D, 6070.7509632433776D, 54821.183453521182D, 9540.6066333042363D, -2710.5532674664501D, 1405.483844121726D, 22.5D }, { -0.0003218135878613132D, 111320.7020701615D, 0.00369383431289D, 823725.64027957176D, 0.46104986909093D, 2351.3431413312919D, 1.58060784298199D, 8.77738589078284D, 0.37238884252424D, 7.45D } };

  public static int a(GeoPoint paramGeoPoint, int paramInt)
  {
    GeoPoint localGeoPoint1 = new GeoPoint(paramGeoPoint.getLatitudeE6() + (int)(1000000.0D * (paramInt / 111000.0D)), paramGeoPoint.getLongitudeE6());
    GeoPoint localGeoPoint2 = b(paramGeoPoint);
    GeoPoint localGeoPoint3 = b(localGeoPoint1);
    return (int)Math.sqrt(Math.pow(localGeoPoint2.getLatitudeE6() - localGeoPoint3.getLatitudeE6(), 2.0D) + Math.pow(localGeoPoint2.getLongitudeE6() - localGeoPoint3.getLongitudeE6(), 2.0D));
  }

  static a a(a parama, double[] paramArrayOfDouble)
  {
    int i = -1;
    a locala = new a();
    locala.a = (paramArrayOfDouble[0] + paramArrayOfDouble[1] * Math.abs(parama.a));
    double d1 = Math.abs(parama.b) / paramArrayOfDouble[9];
    locala.b = (paramArrayOfDouble[2] + d1 * paramArrayOfDouble[3] + d1 * (d1 * paramArrayOfDouble[4]) + d1 * (d1 * (d1 * paramArrayOfDouble[5])) + d1 * (d1 * (d1 * (d1 * paramArrayOfDouble[6]))) + d1 * (d1 * (d1 * (d1 * (d1 * paramArrayOfDouble[7])))) + d1 * (d1 * (d1 * (d1 * (d1 * (d1 * paramArrayOfDouble[8]))))));
    double d2 = locala.a;
    int j;
    double d3;
    if (parama.a < 0.0D)
    {
      j = i;
      locala.a = (d2 * j);
      d3 = locala.b;
      if (parama.b >= 0.0D)
        break label200;
    }
    while (true)
    {
      locala.b = (d3 * i);
      return locala;
      j = 1;
      break;
      label200: i = 1;
    }
  }

  public static GeoPoint a(GeoPoint paramGeoPoint)
  {
    a locala1 = new a();
    locala1.a = paramGeoPoint.getLongitudeE6();
    locala1.b = paramGeoPoint.getLatitudeE6();
    a locala2 = new a();
    locala2.a = locala1.a;
    if (locala2.a > 20037508.342D)
    {
      locala2.a = 20037508.342D;
      locala2.b = locala1.b;
      if ((locala2.b >= 1.0E-006D) || (locala2.b < 0.0D))
        break label186;
      locala2.b = 1.0E-006D;
    }
    label95: for (int i = 0; ; i++)
    {
      double[] arrayOfDouble = null;
      if (i < 6)
      {
        if (Math.abs(locala2.b) <= a[i])
          continue;
        arrayOfDouble = c[i];
      }
      a locala3 = a(locala2, arrayOfDouble);
      return new GeoPoint((int)(1000000.0D * locala3.b), (int)(1000000.0D * locala3.a));
      if (locala2.a >= -20037508.342D)
        break;
      locala2.a = -20037508.342D;
      break;
      if ((locala2.b < 0.0D) && (locala2.b > -1.0E-006D))
      {
        locala2.b = -1.0E-006D;
        break label95;
      }
      if (locala2.b > 20037508.342D)
      {
        locala2.b = 20037508.342D;
        break label95;
      }
      if (locala2.b >= -20037508.342D)
        break label95;
      locala2.b = -20037508.342D;
      break label95;
    }
  }

  public static GeoPoint b(GeoPoint paramGeoPoint)
  {
    a locala1 = new a();
    locala1.b = Math.abs(paramGeoPoint.getLatitudeE6());
    if (locala1.b < 0.1D)
      locala1.b = 0.1D;
    int i = 0;
    if (i < b.length)
      if (locala1.b <= b[i]);
    for (double[] arrayOfDouble = d[i]; ; arrayOfDouble = null)
    {
      locala1.a = (paramGeoPoint.getLongitudeE6() / 1000000.0D);
      locala1.b = (paramGeoPoint.getLatitudeE6() / 1000000.0D);
      a locala2 = a(locala1, arrayOfDouble);
      return new GeoPoint((int)locala2.b, (int)locala2.a);
      i++;
      break;
    }
  }

  static class a
  {
    double a;
    double b;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.e
 * JD-Core Version:    0.6.0
 */