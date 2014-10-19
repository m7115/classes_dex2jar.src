package com.baidu.mapapi.map;

import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comapi.map.t;

public class MapController
{
  o a = null;
  Message b = null;
  private MapView c = null;
  private boolean d = true;
  private boolean e = true;
  private boolean f = true;
  private boolean g = true;

  public MapController(MapView paramMapView)
  {
    this.c = paramMapView;
  }

  public void animateTo(GeoPoint paramGeoPoint)
  {
    if (paramGeoPoint == null)
      return;
    GeoPoint localGeoPoint = e.b(paramGeoPoint);
    this.a.a(localGeoPoint);
  }

  public void animateTo(GeoPoint paramGeoPoint, Message paramMessage)
  {
    if (paramGeoPoint == null)
      return;
    GeoPoint localGeoPoint = e.b(paramGeoPoint);
    this.b = paramMessage;
    this.a.a(localGeoPoint, paramMessage);
  }

  public void enableClick(boolean paramBoolean)
  {
    this.a.f(paramBoolean);
  }

  public boolean handleFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return this.a.a(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
  }

  public boolean isOverlookingGesturesEnabled()
  {
    return this.g;
  }

  public boolean isRotationGesturesEnabled()
  {
    return this.f;
  }

  public boolean isScrollGesturesEnabled()
  {
    return this.d;
  }

  public boolean isZoomGesturesEnabled()
  {
    return this.e;
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    return this.a.onKey(paramView, paramInt, paramKeyEvent);
  }

  public void scrollBy(int paramInt1, int paramInt2)
  {
    this.a.f(paramInt1, paramInt2);
  }

  public void setCenter(GeoPoint paramGeoPoint)
  {
    if (paramGeoPoint == null);
    t localt;
    do
    {
      return;
      localt = this.a.k();
    }
    while (localt == null);
    GeoPoint localGeoPoint = e.b(paramGeoPoint);
    localt.d = localGeoPoint.getLongitudeE6();
    localt.e = localGeoPoint.getLatitudeE6();
    this.a.a(localt);
  }

  public void setCompassMargin(int paramInt1, int paramInt2)
  {
    this.c.b(paramInt1, paramInt2);
  }

  public void setOverlooking(int paramInt)
  {
    if ((paramInt > 0) || (paramInt < -45));
    t localt;
    do
    {
      return;
      localt = this.a.k();
    }
    while (localt == null);
    localt.c = paramInt;
    this.a.a(localt, 300);
  }

  public void setOverlookingGesturesEnabled(boolean paramBoolean)
  {
    this.g = paramBoolean;
    this.a.d(paramBoolean);
  }

  public void setRotation(int paramInt)
  {
    t localt = this.a.k();
    if (localt == null)
      return;
    localt.b = paramInt;
    this.a.a(localt, 300);
  }

  public void setRotationGesturesEnabled(boolean paramBoolean)
  {
    this.f = paramBoolean;
    this.a.c(paramBoolean);
  }

  public void setScrollGesturesEnabled(boolean paramBoolean)
  {
    this.d = paramBoolean;
    this.a.a(paramBoolean);
  }

  public float setZoom(float paramFloat)
  {
    t localt = this.a.k();
    if (localt == null)
      return -1.0F;
    if (paramFloat < 3.0F)
      paramFloat = 3.0F;
    while (true)
    {
      localt.a = paramFloat;
      this.a.a(localt);
      if (paramFloat != 3.0F)
        break;
      this.c.a(true, false);
      return paramFloat;
      if (paramFloat <= 19.0F)
        continue;
      paramFloat = 19.0F;
    }
    if (paramFloat == 19.0F)
    {
      this.c.a(false, true);
      return paramFloat;
    }
    this.c.a(true, true);
    return paramFloat;
  }

  public void setZoomGesturesEnabled(boolean paramBoolean)
  {
    this.e = paramBoolean;
    this.a.b(paramBoolean);
  }

  public boolean zoomIn()
  {
    boolean bool = this.a.g();
    int i = (int)this.a.l();
    if (i <= 3)
    {
      this.c.a(true, false);
      return bool;
    }
    if (i >= 19)
    {
      this.c.a(false, true);
      return bool;
    }
    this.c.a(true, true);
    return bool;
  }

  public boolean zoomInFixing(int paramInt1, int paramInt2)
  {
    boolean bool = this.a.c(paramInt1, paramInt2);
    int i = (int)this.a.l();
    if (i <= 3)
    {
      this.c.a(true, false);
      return bool;
    }
    if (i >= 19)
    {
      this.c.a(false, true);
      return bool;
    }
    this.c.a(true, true);
    return bool;
  }

  public boolean zoomOut()
  {
    boolean bool = this.a.h();
    int i = (int)this.a.l();
    if (i <= 3)
    {
      this.c.a(true, false);
      return bool;
    }
    if (i >= 19)
    {
      this.c.a(false, true);
      return bool;
    }
    this.c.a(true, true);
    return bool;
  }

  public boolean zoomOutFixing(int paramInt1, int paramInt2)
  {
    boolean bool = this.a.d(paramInt1, paramInt2);
    int i = (int)this.a.l();
    if (i <= 3)
    {
      this.c.a(true, false);
      return bool;
    }
    if (i >= 19)
    {
      this.c.a(false, true);
      return bool;
    }
    this.c.a(true, true);
    return bool;
  }

  public void zoomToSpan(int paramInt1, int paramInt2)
  {
    this.c.a(paramInt1, paramInt2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MapController
 * JD-Core Version:    0.6.0
 */