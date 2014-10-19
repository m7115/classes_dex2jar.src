package com.baidu.mapapi.cloud;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public class Bounds
{
  public GeoPoint leftBottom;
  public GeoPoint rightTop;

  public Bounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.leftBottom = new GeoPoint(paramInt1, paramInt2);
    this.rightTop = new GeoPoint(paramInt3, paramInt4);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.cloud.Bounds
 * JD-Core Version:    0.6.0
 */