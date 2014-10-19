package com.baidu.platform.comapi.map;

import android.graphics.Point;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public abstract interface Projection
{
  public abstract GeoPoint fromPixels(int paramInt1, int paramInt2);

  public abstract float metersToEquatorPixels(float paramFloat);

  public abstract Point toPixels(GeoPoint paramGeoPoint, Point paramPoint);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.Projection
 * JD-Core Version:    0.6.0
 */