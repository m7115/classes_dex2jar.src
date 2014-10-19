package com.example.pathview.util;

import com.example.pathview.bean.Point;

public class MathUtil
{
  private static final double EARTH_RADIUS = 6378137.0D;

  public static double GetDistance(Point paramPoint1, Point paramPoint2)
  {
    double d1 = paramPoint1.getX();
    double d2 = paramPoint1.getY();
    double d3 = paramPoint2.getX();
    double d4 = paramPoint2.getY();
    double d5 = rad(d1);
    double d6 = rad(d3);
    double d7 = d5 - d6;
    double d8 = rad(d2) - rad(d4);
    return Math.round(10000.0D * (6378137.0D * (2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(d7 / 2.0D), 2.0D) + Math.cos(d5) * Math.cos(d6) * Math.pow(Math.sin(d8 / 2.0D), 2.0D)))))) / 10000L;
  }

  public static double distance(Point paramPoint1, Point paramPoint2)
  {
    return Math.hypot(paramPoint1.getX() - paramPoint2.getX(), paramPoint1.getY() - paramPoint2.getY());
  }

  public static double pointToLine(Point paramPoint1, Point paramPoint2, Point paramPoint3)
  {
    double d1 = GetDistance(paramPoint2, paramPoint3);
    double d2 = GetDistance(paramPoint2, paramPoint1);
    double d3 = GetDistance(paramPoint3, paramPoint1);
    if (d3 + d2 == d1)
      d2 = 0.0D;
    do
      return d2;
    while ((d1 <= 1.E-005D) || (d3 * d3 >= d1 * d1 + d2 * d2));
    if (d2 * d2 >= d1 * d1 + d3 * d3)
      return d3;
    double d4 = (d3 + (d1 + d2)) / 2.0D;
    return 2.0D * Math.sqrt(d4 * (d4 - d1) * (d4 - d2) * (d4 - d3)) / d1;
  }

  private static double rad(double paramDouble)
  {
    return 3.141592653589793D * paramDouble / 180.0D;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.util.MathUtil
 * JD-Core Version:    0.6.0
 */