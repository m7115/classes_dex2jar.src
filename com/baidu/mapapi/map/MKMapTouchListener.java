package com.baidu.mapapi.map;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public abstract interface MKMapTouchListener
{
  public abstract void onMapClick(GeoPoint paramGeoPoint);

  public abstract void onMapDoubleClick(GeoPoint paramGeoPoint);

  public abstract void onMapLongClick(GeoPoint paramGeoPoint);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MKMapTouchListener
 * JD-Core Version:    0.6.0
 */