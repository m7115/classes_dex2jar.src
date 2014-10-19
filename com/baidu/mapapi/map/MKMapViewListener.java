package com.baidu.mapapi.map;

import android.graphics.Bitmap;

public abstract interface MKMapViewListener
{
  public abstract void onClickMapPoi(MapPoi paramMapPoi);

  public abstract void onGetCurrentMap(Bitmap paramBitmap);

  public abstract void onMapAnimationFinish();

  public abstract void onMapLoadFinish();

  public abstract void onMapMoveFinish();
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MKMapViewListener
 * JD-Core Version:    0.6.0
 */