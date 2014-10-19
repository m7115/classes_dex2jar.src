package com.baidu.platform.comapi.map;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.baidu.mapapi.map.Overlay;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class q extends GLSurfaceView
  implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener
{
  static w a;
  static e b;
  public static int d;
  private static o n = null;
  private static int o;
  public MapRenderer c;
  u e = null;
  a f = null;
  c g = null;
  z h = null;
  GestureDetector i = new GestureDetector(this);
  List<Overlay> j = null;
  boolean k = false;
  private boolean l = false;
  private boolean m = false;
  private a p = null;
  private int q = 0;
  private int r = 0;

  static
  {
    a = null;
    b = null;
    o = 0;
    d = 0;
  }

  public q(Context paramContext)
  {
    super(paramContext);
  }

  private void m()
  {
    com.baidu.platform.comjni.map.basemap.a locala = n.b();
    if (locala == null);
    int i3;
    do
    {
      return;
      int i1 = locala.a(0, 0, "compass");
      if (i1 > 0)
      {
        d = i1;
        locala.b(i1, true);
        locala.a(i1, true);
        a.a(i1, b.a());
      }
      int i2 = locala.a(0, 0, "logo");
      if (i2 > 0)
      {
        locala.b(i2, true);
        locala.a(i2, false);
        n.c = i2;
        n.g.put("logo", Integer.valueOf(i2));
      }
      i3 = locala.a(0, 0, "popup");
    }
    while (i3 <= 0);
    locala.b(i3, true);
    locala.a(i3, false);
    n.a = i3;
    n.e.put("popup", Integer.valueOf(i3));
  }

  public float a(com.baidu.platform.comapi.basestruct.b paramb)
  {
    float f1 = 3.0F;
    if ((n != null) && (n.b() != null))
    {
      if ((paramb.a.a == paramb.b.a) || (paramb.a.b == paramb.b.b))
        f1 = 18.0F;
    }
    else
      return f1;
    int i1 = Math.abs(paramb.b.a - paramb.a.a);
    int i2 = Math.abs(paramb.a.b - paramb.b.b);
    com.baidu.platform.comapi.d.c.h();
    com.baidu.platform.comapi.d.c.j();
    int i3 = getWidth() / 4;
    int i4 = getHeight() / 4;
    if ((i3 <= 0) || (i4 <= 0))
      return 18.0F;
    while (true)
    {
      if (i5 > i3)
      {
        int i9 = i5 >> 1;
        i6++;
        i5 = i9;
        continue;
      }
      while (true)
      {
        if (i7 > i4)
        {
          i7 >>= 1;
          i8++;
          continue;
        }
        float f2 = 20 - Math.max(i6, i8);
        if (f2 < f1)
          break;
        if (f2 > 19.0F)
          return 19.0F;
        return f2;
        int i7 = i2;
        int i8 = 0;
      }
      int i5 = i1;
      int i6 = 0;
    }
  }

  public int a(String paramString)
  {
    com.baidu.platform.comjni.map.basemap.a locala = n.b();
    if (locala == null)
      return 0;
    if (paramString.equals("location"));
    for (int i1 = locala.a(8, 1000, "location"); ; i1 = locala.a(0, 0, paramString))
    {
      if (i1 > 0)
      {
        locala.b(i1, true);
        locala.a(i1, false);
      }
      return i1;
    }
  }

  public void a()
  {
    o = -1 + o;
    if (o == 0)
    {
      n.m();
      n = null;
      a = null;
      b = null;
    }
    this.c = null;
  }

  public void a(int paramInt)
  {
    a.a(paramInt);
  }

  void a(int paramInt1, int paramInt2)
  {
    int i1 = paramInt1 + this.q / 2;
    int i2 = paramInt2 + this.r / 2;
    n.a(i1, i2);
  }

  public void a(int paramInt, d paramd)
  {
    a.a(paramInt, paramd);
  }

  public void a(Bundle paramBundle, Context paramContext)
  {
    s locals = new s();
    locals.a(this.p);
    this.j = Collections.synchronizedList(locals);
    if ((n == null) && (a == null))
    {
      n = new o(paramContext, this);
      a = new w(n);
      if (n != null)
        n.a(paramBundle, a);
      m();
      b = new e(n);
    }
    o = 1 + o;
    if (n != null)
    {
      n.a(this);
      this.c = new MapRenderer(n.a());
      setRenderer(this.c);
      com.baidu.platform.comjni.map.basemap.a locala = n.b();
      if (locala != null)
        locala.a(paramBundle);
    }
    setLongClickable(false);
    setFocusable(false);
  }

  void a(GeoPoint paramGeoPoint, Message paramMessage, Runnable paramRunnable)
  {
    t localt = n.k();
    localt.d = paramGeoPoint.getLongitudeE6();
    localt.e = paramGeoPoint.getLatitudeE6();
    n.a(localt, 500);
  }

  public void a(a parama)
  {
    this.f = parama;
  }

  public void a(a parama)
  {
    this.p = parama;
  }

  public void a(t paramt)
  {
    n.a(paramt);
  }

  public void a(u paramu)
  {
    this.e = paramu;
  }

  public void a(boolean paramBoolean)
  {
    this.m = paramBoolean;
    com.baidu.platform.comjni.map.basemap.a locala = n.b();
    if (locala != null)
      locala.a(this.m);
  }

  public boolean a(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    o localo = n;
    int i1 = 0;
    if (localo != null)
    {
      boolean bool1 = n.a(paramMotionEvent);
      boolean bool2 = this.i.onTouchEvent(paramMotionEvent);
      if (!bool1)
      {
        i1 = 0;
        if (!bool2);
      }
      else
      {
        i1 = 1;
      }
    }
    return i1;
  }

  public o b()
  {
    return n;
  }

  public void b(boolean paramBoolean)
  {
    this.l = paramBoolean;
    com.baidu.platform.comjni.map.basemap.a locala = n.b();
    if (locala != null)
      locala.b(this.l);
  }

  public List<Overlay> c()
  {
    return this.j;
  }

  public Projection d()
  {
    return b;
  }

  public boolean e()
  {
    return this.m;
  }

  public boolean f()
  {
    return this.l;
  }

  double g()
  {
    return Math.pow(2.0D, 18.0F - i());
  }

  public GeoPoint h()
  {
    t localt = n.k();
    return new GeoPoint(localt.e, localt.d);
  }

  public float i()
  {
    return n.l();
  }

  public int j()
  {
    return n.k().b;
  }

  public int k()
  {
    return n.k().c;
  }

  public t l()
  {
    return n.k();
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }

  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    if (this.e != null)
      this.e.b((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    return false;
  }

  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if ((n != null) && (n.d()))
      return n.a(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
    return false;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    int i2;
    if ((paramInt == 21) || (paramInt == 29))
    {
      int i1 = n.a(1, 18, 0);
      i2 = 0;
      if (i1 == 1)
        i2 = 1;
    }
    int i5;
    do
    {
      do
      {
        while (true)
        {
          return i2;
          if ((paramInt == 19) || (paramInt == 51))
          {
            int i3 = n.a(1, 19, 0);
            i2 = 0;
            if (i3 == 1)
              return true;
          }
          if ((paramInt != 20) && (paramInt != 47))
            break;
          int i4 = n.a(1, 17, 0);
          i2 = 0;
          if (i4 == 1)
            return true;
        }
        if (paramInt == 22)
          break;
        i2 = 0;
      }
      while (paramInt != 32);
      i5 = n.a(1, 16, 0);
      i2 = 0;
    }
    while (i5 != 1);
    return true;
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public void onLongPress(MotionEvent paramMotionEvent)
  {
    if (this.e != null)
      this.e.c((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
  }

  public void onPause()
  {
    if (n != null)
      n.b().d();
    super.onPause();
  }

  public void onResume()
  {
    if (n != null)
    {
      n.a(this);
      n.b().f();
      n.b().e();
      n.b().j();
    }
    super.onResume();
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return false;
  }

  public void onShowPress(MotionEvent paramMotionEvent)
  {
  }

  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    if (n == null);
    do
    {
      return false;
      if (n.d(paramMotionEvent))
        return true;
    }
    while (this.e == null);
    this.e.a((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    return false;
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    MapRenderer.a = paramInt2;
    MapRenderer.b = paramInt3;
    this.q = paramInt2;
    this.r = paramInt3;
    MapRenderer.c = 0;
    super.surfaceChanged(paramSurfaceHolder, paramInt1, paramInt2, paramInt3);
    t localt = l();
    localt.f.a = 0;
    localt.f.c = 0;
    localt.f.d = paramInt3;
    localt.f.b = paramInt2;
    a(localt);
    n.e(this.q, this.r);
  }

  public static abstract interface a
  {
    public abstract void a(Object paramObject);

    public abstract void b(Object paramObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.q
 * JD-Core Version:    0.6.0
 */