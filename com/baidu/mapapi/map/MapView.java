package com.baidu.mapapi.map;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ZoomControls;
import com.baidu.mapapi.BMapManager;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.A;
import com.baidu.platform.comapi.map.Projection;
import com.baidu.platform.comapi.map.n;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comapi.map.q;
import com.baidu.platform.comapi.map.q.a;
import com.baidu.platform.comapi.map.r;
import com.baidu.platform.comapi.map.t;
import com.baidu.platform.comapi.map.u;
import com.baidu.platform.comapi.map.x;
import com.baidu.platform.comapi.map.y;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapView extends ViewGroup
{
  q a = null;
  c b = null;
  com.baidu.platform.comapi.map.a c = null;
  private MapController d = null;
  private u e = null;
  private int f = 0;
  private int g = 0;
  private ZoomControls h = null;
  private float i = -1.0F;
  private Projection j = null;
  private int k = 0;
  private int l = 0;
  private q.a m = null;
  private Context n = null;
  private MKMapViewListener o = null;
  private MKMapTouchListener p = null;
  private boolean q = false;
  private t r;
  private boolean s = false;
  private boolean t = false;
  private boolean u = false;
  private List<Overlay> v;

  public MapView(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
    addView(this.a);
    addView(this.b);
  }

  public MapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
    addView(this.a);
    addView(this.b);
  }

  public MapView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
    addView(this.a);
    addView(this.b);
  }

  private void a(int paramInt1, GeoPoint paramGeoPoint, int paramInt2)
  {
    List localList = this.a.c();
    if (localList != null)
      for (int i1 = 0; i1 < localList.size(); i1++)
      {
        if ((((Overlay)localList.get(i1)).mType != 27) || (paramGeoPoint == null))
          continue;
        GeoPoint localGeoPoint = getProjection().fromPixels(paramGeoPoint.getLatitudeE6(), paramGeoPoint.getLongitudeE6());
        if ((((ItemizedOverlay)localList.get(i1)).onTap(localGeoPoint, this)) || (paramInt1 == -1) || (paramInt2 != ((Overlay)localList.get(i1)).mLayerID))
          continue;
        ((ItemizedOverlay)localList.get(i1)).onTap(paramInt1);
      }
  }

  private void a(Context paramContext)
  {
    this.n = paramContext;
    if (this.a == null)
    {
      this.a = new q(paramContext);
      Bundle localBundle = new Bundle();
      localBundle.remove("overlooking");
      localBundle.remove("rotation");
      localBundle.putDouble("centerptx", 12958162.0D);
      localBundle.putDouble("centerpty", 4825907.0D);
      localBundle.putString("modulePath", com.baidu.platform.comapi.d.c.r());
      localBundle.putString("appSdcardPath", com.baidu.mapapi.utils.b.a());
      localBundle.putString("appCachePath", com.baidu.mapapi.utils.b.b());
      localBundle.putString("appSecondCachePath", com.baidu.mapapi.utils.b.c());
      localBundle.putInt("mapTmpMax", com.baidu.mapapi.utils.b.d());
      localBundle.putInt("domTmpMax", com.baidu.mapapi.utils.b.e());
      localBundle.putInt("itsTmpMax", com.baidu.mapapi.utils.b.f());
      this.m = new b(null);
      this.a.a(this.m);
      this.a.a(localBundle, paramContext);
      this.d = new MapController(this);
      this.d.a = this.a.b();
      f();
      this.k = (int)(36.0F * com.baidu.platform.comapi.d.c.C);
      this.l = (int)(40.0F * com.baidu.platform.comapi.d.c.C);
      e();
      d();
      refresh();
      this.a.layout(1 + this.a.getLeft(), 1 + this.a.getTop(), 1 + this.a.getRight(), 1 + this.a.getBottom());
      this.a.setVisibility(0);
      this.a.setFocusable(true);
      this.h = new ZoomControls(paramContext);
      if (this.h.getParent() == null)
      {
        this.h.setOnZoomOutClickListener(new b(this));
        this.h.setOnZoomInClickListener(new c(this));
        this.h.setFocusable(true);
        this.h.setVisibility(0);
        this.h.measure(0, 0);
      }
      this.b = new c(paramContext);
      this.b.setBackgroundColor(0);
      this.b.layout(1 + this.a.getLeft(), 1 + this.a.getTop(), 1 + this.a.getRight(), 1 + this.a.getBottom());
    }
  }

  private void a(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    paramView.measure(this.f, this.g);
    int i1 = paramLayoutParams.width;
    int i2 = paramLayoutParams.height;
    int i3;
    if (i1 == -1)
      i3 = this.f;
    while (true)
    {
      label46: int i4;
      int i5;
      if (i2 == -1)
      {
        i2 = this.g;
        if (!checkLayoutParams(paramLayoutParams))
          break label354;
        LayoutParams localLayoutParams = (LayoutParams)paramLayoutParams;
        i4 = localLayoutParams.x;
        i5 = localLayoutParams.y;
        if ((localLayoutParams.mode == 0) && (localLayoutParams.point != null))
        {
          Point localPoint = getProjection().toPixels(localLayoutParams.point, null);
          i4 = localPoint.x + localLayoutParams.x;
          i5 = localPoint.y + localLayoutParams.y;
        }
        switch (localLayoutParams.alignment)
        {
        case 3:
        case 48:
        case 51:
        default:
        case 80:
        case 5:
        case 81:
        case 17:
        case 1:
        case 16:
        }
      }
      while (true)
      {
        paramView.layout(i4, i5, i3 + i4, i2 + i5);
        return;
        if (i1 != -2)
          break label365;
        i3 = paramView.getMeasuredWidth();
        break;
        if (i2 != -2)
          break label46;
        i2 = paramView.getMeasuredHeight();
        break label46;
        i5 -= i2;
        continue;
        i4 -= i3;
        continue;
        i4 -= i3 / 2;
        i5 -= i2;
        continue;
        i4 -= i3 / 2;
        i5 -= i2 / 2;
        continue;
        i4 -= i3 / 2;
        continue;
        i5 -= i2 / 2;
      }
      label354: paramView.layout(0, 0, i3, i2);
      return;
      label365: i3 = i1;
    }
  }

  private void a(Overlay paramOverlay)
  {
    if (paramOverlay.mType == 21);
    while (true)
    {
      return;
      if (paramOverlay.mLayerID != 0)
        throw new RuntimeException("cat not add overlay,overlay already exists in mapview");
      if (paramOverlay.mType == 27)
      {
        ItemizedOverlay localItemizedOverlay = (ItemizedOverlay)paramOverlay;
        localItemizedOverlay.a();
        localItemizedOverlay.b();
      }
      while (paramOverlay.mLayerID != 0)
      {
        this.d.a.b().a(paramOverlay.mLayerID, true);
        this.d.a.b().a(paramOverlay.mLayerID);
        return;
        if (paramOverlay.mType == 12)
        {
          ((RouteOverlay)paramOverlay).a();
          continue;
        }
        if (paramOverlay.mType == 28)
        {
          ((TransitOverlay)paramOverlay).a();
          continue;
        }
        if (paramOverlay.mType == 14)
        {
          ((PoiOverlay)paramOverlay).a();
          continue;
        }
        if (paramOverlay.mType == 29)
        {
          GraphicsOverlay localGraphicsOverlay = (GraphicsOverlay)paramOverlay;
          localGraphicsOverlay.a();
          localGraphicsOverlay.c();
          continue;
        }
        if (paramOverlay.mType == 7)
        {
          ((MyLocationOverlay)paramOverlay).a();
          continue;
        }
        if (paramOverlay.mType != 30)
          continue;
        TextOverlay localTextOverlay = (TextOverlay)paramOverlay;
        localTextOverlay.a();
        localTextOverlay.b();
      }
    }
  }

  private void a(r paramr, int paramInt)
  {
    List localList = this.a.c();
    if (localList != null)
      for (int i1 = 0; i1 < localList.size(); i1++)
      {
        if (((Overlay)localList.get(i1)).mLayerID != paramInt)
          continue;
        if (((Overlay)localList.get(i1)).mType == 12)
          ((RouteOverlay)localList.get(i1)).onTap(paramr.b);
        if (((Overlay)localList.get(i1)).mType != 28)
          continue;
        ((TransitOverlay)localList.get(i1)).onTap(paramr.b);
      }
  }

  private void b(int paramInt)
  {
    List localList = this.a.c();
    if (localList != null)
      for (int i1 = 0; i1 < localList.size(); i1++)
      {
        if ((((Overlay)localList.get(i1)).mType != 7) || (((Overlay)localList.get(i1)).mLayerID != paramInt))
          continue;
        ((MyLocationOverlay)localList.get(i1)).dispatchTap();
      }
  }

  private void b(Overlay paramOverlay)
  {
    if (paramOverlay.mLayerID == 0)
      return;
    if (paramOverlay.mType == 21)
    {
      this.d.a.b().c(this.d.a.a);
      this.d.a.b().a(this.d.a.a, false);
      return;
    }
    a(paramOverlay.mLayerID);
    this.d.a.b().c(paramOverlay.mLayerID);
    this.d.a.b().a(paramOverlay.mLayerID, false);
    this.d.a.b().a(paramOverlay.mLayerID);
    this.d.a.b().b(paramOverlay.mLayerID);
    paramOverlay.mLayerID = 0;
  }

  private void c(int paramInt)
  {
    List localList = this.a.c();
    if (localList != null)
      for (int i1 = 0; i1 < localList.size(); i1++)
      {
        if ((((Overlay)localList.get(i1)).mType != 21) || (((PopupOverlay)localList.get(i1)).a == null))
          continue;
        ((PopupOverlay)localList.get(i1)).a.onClickedPopup(paramInt);
      }
  }

  private void c(int paramInt1, int paramInt2)
  {
    List localList = this.a.c();
    if (localList != null)
      for (int i1 = 0; i1 < localList.size(); i1++)
      {
        if ((((Overlay)localList.get(i1)).mType != 14) || (((Overlay)localList.get(i1)).mLayerID != paramInt2))
          continue;
        ((PoiOverlay)localList.get(i1)).onTap(paramInt1);
      }
  }

  private void d()
  {
    InputStream localInputStream;
    do
      try
      {
        AssetManager localAssetManager = this.n.getAssets();
        if (com.baidu.platform.comapi.d.c.n() >= 180)
        {
          localInputStream = localAssetManager.open("logo_h.png");
        }
        else
        {
          localInputStream = localAssetManager.open("logo_l.png");
          continue;
          Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
          localInputStream.close();
          Bundle localBundle = new Bundle();
          localBundle.putInt("logoaddr", ((Integer)this.d.a.g.get("logo")).intValue());
          localBundle.putInt("bshow", 1);
          localBundle.putInt("imgW", localBitmap.getWidth());
          localBundle.putInt("imgH", localBitmap.getHeight());
          localBundle.putInt("showLR", 1);
          localBundle.putInt("iconwidth", 0);
          localBundle.putInt("iconlayer", 1);
          localBundle.putInt("bound", 0);
          localBundle.putInt("popname", -1288857267);
          ByteBuffer localByteBuffer = ByteBuffer.allocate(4 * (localBitmap.getWidth() * localBitmap.getHeight()));
          localBitmap.copyPixelsToBuffer(localByteBuffer);
          localBundle.putByteArray("imgdata", localByteBuffer.array());
          this.d.a.b().e(localBundle);
          this.d.a.b().a(this.d.a.c, true);
          this.d.a.b().a(this.d.a.c);
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
    while (localInputStream != null);
  }

  private void e()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.k);
    arrayOfObject[1] = Integer.valueOf(this.l);
    String str = String.format("{\"dataset\":[{\"x\":%d,\"y\":%d,\"hidetime\":1000}]}", arrayOfObject);
    com.baidu.platform.comapi.map.b.a().a(str);
    com.baidu.platform.comapi.map.b.a().d();
  }

  private void f()
  {
    this.e = new d(this);
    this.a.a(this.e);
    this.c = new e(this);
    this.a.a(this.c);
  }

  int a(String paramString)
  {
    return this.a.a(paramString);
  }

  void a()
  {
    int i1 = getChildCount();
    int i2 = 0;
    if (i2 < i1)
    {
      View localView = super.getChildAt(i2);
      if ((localView == this.b) || (localView == this.h) || (localView == this.a));
      while (true)
      {
        i2++;
        break;
        ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
        if ((!(localLayoutParams instanceof LayoutParams)) || (((LayoutParams)localLayoutParams).mode != 0))
          continue;
        a(localView, localLayoutParams);
      }
    }
  }

  void a(int paramInt)
  {
    this.a.a(paramInt);
  }

  void a(int paramInt1, int paramInt2)
  {
    t localt = this.a.l();
    float f1 = localt.a;
    GeoPoint localGeoPoint1 = getMapCenter();
    GeoPoint localGeoPoint2 = new GeoPoint(localGeoPoint1.getLatitudeE6() - paramInt1 / 2, localGeoPoint1.getLongitudeE6() + paramInt2 / 2);
    GeoPoint localGeoPoint3 = new GeoPoint(localGeoPoint1.getLatitudeE6() + paramInt1 / 2, localGeoPoint1.getLongitudeE6() - paramInt2 / 2);
    GeoPoint localGeoPoint4 = com.baidu.mapapi.utils.e.b(localGeoPoint2);
    GeoPoint localGeoPoint5 = com.baidu.mapapi.utils.e.b(localGeoPoint3);
    com.baidu.platform.comapi.basestruct.c localc1 = new com.baidu.platform.comapi.basestruct.c();
    localc1.a(localGeoPoint4.getLongitudeE6());
    localc1.b(localGeoPoint4.getLatitudeE6());
    com.baidu.platform.comapi.basestruct.c localc2 = new com.baidu.platform.comapi.basestruct.c();
    localc2.a(localGeoPoint5.getLongitudeE6());
    localc2.b(localGeoPoint5.getLatitudeE6());
    com.baidu.platform.comapi.basestruct.b localb = new com.baidu.platform.comapi.basestruct.b();
    localb.a(localc1);
    localb.b(localc2);
    if (localb != null)
      f1 = this.a.a(localb);
    localt.a = f1;
    this.d.a.a(localt);
  }

  void a(int paramInt, com.baidu.platform.comapi.map.d paramd)
  {
    this.a.a(paramInt, paramd);
  }

  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.h.setIsZoomOutEnabled(paramBoolean2);
    this.h.setIsZoomInEnabled(paramBoolean1);
  }

  void b(int paramInt1, int paramInt2)
  {
    this.k = paramInt1;
    this.l = paramInt2;
    e();
  }

  boolean b()
  {
    return this.d.zoomIn();
  }

  boolean c()
  {
    return this.d.zoomOut();
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }

  public void destroy()
  {
    if (this.a != null)
    {
      this.a.a();
      this.a = null;
    }
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
      return onKeyDown(paramKeyEvent.getKeyCode(), paramKeyEvent);
    if (paramKeyEvent.getAction() == 1)
      return onKeyUp(paramKeyEvent.getKeyCode(), paramKeyEvent);
    return false;
  }

  @Deprecated
  public void displayZoomControls(boolean paramBoolean)
  {
    if ((!this.q) || (this.h.getParent() == null))
    {
      removeView(this.h);
      addView(this.h);
      this.q = true;
    }
  }

  protected void finalize()
    throws Throwable
  {
    destroy();
    super.finalize();
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return super.generateDefaultLayoutParams();
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }

  public MapController getController()
  {
    return this.d;
  }

  public boolean getCurrentMap()
  {
    this.d.a.a(com.baidu.mapapi.utils.b.h() + "/BaiduMapSDK/capture.png");
    return true;
  }

  public int getLatitudeSpan()
  {
    Projection localProjection = getProjection();
    GeoPoint localGeoPoint1 = localProjection.fromPixels(0, 0);
    GeoPoint localGeoPoint2 = localProjection.fromPixels(-1 + this.f, -1 + this.g);
    return Math.abs(localGeoPoint1.getLatitudeE6() - localGeoPoint2.getLatitudeE6());
  }

  public int getLongitudeSpan()
  {
    Projection localProjection = getProjection();
    GeoPoint localGeoPoint1 = localProjection.fromPixels(0, 0);
    GeoPoint localGeoPoint2 = localProjection.fromPixels(-1 + this.f, -1 + this.g);
    return Math.abs(localGeoPoint1.getLongitudeE6() - localGeoPoint2.getLongitudeE6());
  }

  public GeoPoint getMapCenter()
  {
    GeoPoint localGeoPoint = this.a.h();
    if (localGeoPoint == null)
      return null;
    return com.baidu.mapapi.utils.e.a(localGeoPoint);
  }

  public int getMapOverlooking()
  {
    return this.a.k();
  }

  public int getMapRotation()
  {
    return this.a.j();
  }

  public int getMaxZoomLevel()
  {
    return 19;
  }

  public int getMinZoomLevel()
  {
    return 3;
  }

  public List<Overlay> getOverlays()
  {
    if (this.a != null)
      return this.a.c();
    return null;
  }

  public Projection getProjection()
  {
    if (this.j == null)
      this.j = new a();
    return this.j;
  }

  @Deprecated
  public View getZoomControls()
  {
    return this.h;
  }

  public float getZoomLevel()
  {
    return this.a.i();
  }

  public boolean isDoubleClickZooming()
  {
    return this.d.a.e();
  }

  public boolean isSatellite()
  {
    return this.a.e();
  }

  public boolean isTraffic()
  {
    return this.a.f();
  }

  protected void onAttachedToWindow()
  {
    if ((this.a != null) && (indexOfChild(this.a) == -1))
      addView(this.a);
    if (this.q)
      setBuiltInZoomControls(true);
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    if ((this.q) && (this.h.getParent() != null))
      removeView(this.h);
    removeView(this.a);
    super.onDetachedFromWindow();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.a.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.a.onKeyUp(paramInt, paramKeyEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i1 = 0;
    this.f = (paramInt3 - paramInt1);
    this.g = (paramInt4 - paramInt2);
    ViewGroup.LayoutParams localLayoutParams1 = this.b.getLayoutParams();
    localLayoutParams1.width = this.f;
    localLayoutParams1.height = this.g;
    this.b.setVisibility(0);
    this.b.layout(0, 0, this.f, this.g);
    ViewGroup.LayoutParams localLayoutParams2 = this.a.getLayoutParams();
    localLayoutParams2.width = this.f;
    localLayoutParams2.height = this.g;
    this.a.setVisibility(0);
    this.a.layout(0, 0, this.f, this.g);
    if ((this.q) && (this.h != null))
    {
      ViewGroup.LayoutParams localLayoutParams3 = this.h.getLayoutParams();
      if (localLayoutParams3 != null)
      {
        localLayoutParams3.height = -2;
        localLayoutParams3.width = -2;
      }
      this.h.setVisibility(0);
      this.h.measure(paramInt3 - paramInt1, paramInt4 - paramInt2);
      int i3 = this.h.getMeasuredWidth();
      int i4 = this.h.getMeasuredHeight();
      this.h.layout(paramInt3 - 10 - i3, paramInt4 - 5 - i4 - paramInt2, paramInt3 - 10, paramInt4 - 5 - paramInt2);
    }
    int i2 = getChildCount();
    if (i1 < i2)
    {
      View localView = super.getChildAt(i1);
      if ((localView == this.b) || (localView == this.h) || (localView == this.a));
      while (true)
      {
        i1++;
        break;
        a(localView, localView.getLayoutParams());
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onPause()
  {
    if (this.a != null)
    {
      this.r = this.a.l();
      this.s = this.a.e();
      this.t = this.a.f();
      this.v = new ArrayList(getOverlays());
      getOverlays().clear();
      this.a.onPause();
    }
  }

  public void onRestoreInstanceState(Bundle paramBundle)
  {
    int i1 = paramBundle.getInt("lat");
    int i2 = paramBundle.getInt("lon");
    if ((i1 != 0) && (i2 != 0))
      this.d.setCenter(new GeoPoint(i1, i2));
    float f1 = paramBundle.getFloat("zoom");
    if (f1 != 0.0F)
      this.d.setZoom(f1);
    setTraffic(paramBundle.getBoolean("traffic"));
  }

  public void onResume()
  {
    if (this.a != null)
    {
      this.a.onResume();
      if (this.r != null)
      {
        t localt = this.a.l();
        this.r.f = localt.f;
        this.a.a(this.r);
      }
      this.a.a(this.s);
      this.a.b(this.t);
      if (this.v != null)
      {
        getOverlays().clear();
        getOverlays().addAll(this.v);
      }
      refresh();
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    GeoPoint localGeoPoint = getMapCenter();
    paramBundle.putInt("lat", localGeoPoint.getLatitudeE6());
    paramBundle.putInt("lon", localGeoPoint.getLongitudeE6());
    paramBundle.putFloat("zoom", getZoomLevel());
    paramBundle.putBoolean("traffic", isTraffic());
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.f = paramInt1;
    this.g = paramInt2;
    this.d.a.e(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.a.a(paramMotionEvent);
  }

  public void preLoad()
  {
  }

  public void refresh()
  {
    List localList = getOverlays();
    if (localList != null)
    {
      int i1 = 0;
      if (i1 < localList.size())
      {
        ItemizedOverlay localItemizedOverlay;
        if (((Overlay)localList.get(i1)).mType == 27)
        {
          localItemizedOverlay = (ItemizedOverlay)localList.get(i1);
          if (localItemizedOverlay.c())
          {
            if (localItemizedOverlay.getAllItem().size() > 0)
              break label691;
            this.d.a.b().c(localItemizedOverlay.d());
            this.d.a.b().a(localItemizedOverlay.d(), false);
            this.d.a.b().a(localItemizedOverlay.d());
            label125: localItemizedOverlay.a(false);
          }
        }
        if (((Overlay)localList.get(i1)).mType == 14)
        {
          PoiOverlay localPoiOverlay = (PoiOverlay)localList.get(i1);
          if ((localPoiOverlay.b() != null) && (!localPoiOverlay.b().equals("")))
          {
            x localx = (x)localPoiOverlay.getInnerOverlay();
            localx.a(localPoiOverlay.b());
            localx.a(true);
            localx.d();
          }
        }
        if (((Overlay)localList.get(i1)).mType == 12)
        {
          RouteOverlay localRouteOverlay = (RouteOverlay)localList.get(i1);
          if ((localRouteOverlay.e() != null) && (!localRouteOverlay.e().equals("")))
          {
            y localy = localRouteOverlay.getInnerOverlay();
            localy.a(localRouteOverlay.f());
            localy.a(localRouteOverlay.e());
            localy.a(true);
            localy.d();
          }
        }
        if (((Overlay)localList.get(i1)).mType == 28)
        {
          TransitOverlay localTransitOverlay = (TransitOverlay)localList.get(i1);
          if ((localTransitOverlay.e() != null) && (!localTransitOverlay.e().equals("")))
          {
            A localA = localTransitOverlay.getInnerOverlay();
            localA.a(localTransitOverlay.f());
            localA.a(localTransitOverlay.e());
            localA.a(true);
            localA.d();
          }
        }
        if (((Overlay)localList.get(i1)).mType == 7)
        {
          MyLocationOverlay localMyLocationOverlay = (MyLocationOverlay)localList.get(i1);
          if ((localMyLocationOverlay.d() != null) && (!localMyLocationOverlay.d().equals("")))
          {
            n localn = (n)localMyLocationOverlay.b();
            localn.a(localMyLocationOverlay.c());
            localn.a(localMyLocationOverlay.d());
            localn.a(true);
            localn.d();
          }
        }
        GraphicsOverlay localGraphicsOverlay;
        if (((Overlay)localList.get(i1)).mType == 29)
        {
          localGraphicsOverlay = (GraphicsOverlay)localList.get(i1);
          if (localGraphicsOverlay.d())
          {
            if (localGraphicsOverlay.getAllGraphics().size() != 0)
              break label731;
            this.d.a.b().c(localGraphicsOverlay.b());
            this.d.a.b().a(localGraphicsOverlay.b(), false);
            this.d.a.b().a(localGraphicsOverlay.b());
            label591: localGraphicsOverlay.a(false);
          }
        }
        TextOverlay localTextOverlay;
        if (((Overlay)localList.get(i1)).mType == 30)
        {
          localTextOverlay = (TextOverlay)localList.get(i1);
          if (localTextOverlay.size() != 0)
            break label771;
          this.d.a.b().c(localTextOverlay.mLayerID);
          this.d.a.b().a(localTextOverlay.mLayerID, false);
          this.d.a.b().a(localTextOverlay.mLayerID);
        }
        while (true)
        {
          i1++;
          break;
          label691: this.d.a.b().a(localItemizedOverlay.d(), true);
          this.d.a.b().a(localItemizedOverlay.d());
          break label125;
          label731: this.d.a.b().a(localGraphicsOverlay.b(), true);
          this.d.a.b().a(localGraphicsOverlay.b());
          break label591;
          label771: this.d.a.b().a(localTextOverlay.mLayerID, true);
          this.d.a.b().a(localTextOverlay.mLayerID);
        }
      }
    }
  }

  public void regMapTouchListner(MKMapTouchListener paramMKMapTouchListener)
  {
    this.p = paramMKMapTouchListener;
  }

  public void regMapViewListener(BMapManager paramBMapManager, MKMapViewListener paramMKMapViewListener)
  {
    if (paramBMapManager == null)
      return;
    this.o = paramMKMapViewListener;
  }

  public void setBuiltInZoomControls(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if ((this.q) || (this.h.getParent() != null))
        removeView(this.h);
      addView(this.h);
      this.q = true;
      return;
    }
    this.q = false;
    removeView(this.h);
  }

  public void setDoubleClickZooming(boolean paramBoolean)
  {
    this.d.a.e(paramBoolean);
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.a.setOnClickListener(paramOnClickListener);
  }

  public void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    this.a.setOnLongClickListener(paramOnLongClickListener);
  }

  public void setSatellite(boolean paramBoolean)
  {
    this.s = paramBoolean;
    this.a.a(paramBoolean);
  }

  public void setTraffic(boolean paramBoolean)
  {
    this.t = paramBoolean;
    this.a.b(paramBoolean);
  }

  public void setVisibility(int paramInt)
  {
    if (this.a != null)
      this.a.setVisibility(paramInt);
    super.setVisibility(paramInt);
  }

  public static class LayoutParams extends ViewGroup.LayoutParams
  {
    public static final int BOTTOM = 80;
    public static final int BOTTOM_CENTER = 81;
    public static final int CENTER = 17;
    public static final int CENTER_HORIZONTAL = 1;
    public static final int CENTER_VERTICAL = 16;
    public static final int LEFT = 3;
    public static final int MODE_MAP = 0;
    public static final int MODE_VIEW = 1;
    public static final int RIGHT = 5;
    public static final int TOP = 48;
    public static final int TOP_LEFT = 51;
    public int alignment = 51;
    public int mode = 1;
    public GeoPoint point = null;
    public int x = 0;
    public int y = 0;

    public LayoutParams(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      super(paramInt2);
      this.mode = 1;
      this.x = paramInt3;
      this.y = paramInt4;
      this.alignment = paramInt5;
    }

    public LayoutParams(int paramInt1, int paramInt2, GeoPoint paramGeoPoint, int paramInt3)
    {
      this(paramInt1, paramInt2, paramGeoPoint, 0, 0, paramInt3);
    }

    public LayoutParams(int paramInt1, int paramInt2, GeoPoint paramGeoPoint, int paramInt3, int paramInt4, int paramInt5)
    {
      super(paramInt2);
      this.mode = 0;
      this.point = paramGeoPoint;
      this.x = paramInt3;
      this.y = paramInt4;
      this.alignment = paramInt5;
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }

  class a
    implements Projection
  {
    a()
    {
    }

    public GeoPoint fromPixels(int paramInt1, int paramInt2)
    {
      GeoPoint localGeoPoint = MapView.this.a.d().fromPixels(paramInt1, paramInt2);
      if (localGeoPoint == null)
        return null;
      return com.baidu.mapapi.utils.e.a(localGeoPoint);
    }

    public float metersToEquatorPixels(float paramFloat)
    {
      return MapView.this.a.d().metersToEquatorPixels(paramFloat);
    }

    public Point toPixels(GeoPoint paramGeoPoint, Point paramPoint)
    {
      GeoPoint localGeoPoint = com.baidu.mapapi.utils.e.b(paramGeoPoint);
      return MapView.this.a.d().toPixels(localGeoPoint, paramPoint);
    }
  }

  private class b
    implements q.a
  {
    private b()
    {
    }

    public void a(Object paramObject)
    {
      MapView.a(MapView.this, (Overlay)paramObject);
    }

    public void b(Object paramObject)
    {
      MapView.b(MapView.this, (Overlay)paramObject);
    }
  }

  private class c extends View
  {
    public c(Context arg2)
    {
      super();
    }

    protected void onDraw(Canvas paramCanvas)
    {
      MapView.this.a();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MapView
 * JD-Core Version:    0.6.0
 */