package com.baidu.mapapi.map;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.a;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;
import com.baidu.platform.comapi.map.f;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comapi.map.q;
import com.baidu.platform.comapi.map.r;
import com.baidu.platform.comapi.map.t;
import com.baidu.platform.comapi.map.u;
import java.util.List;

class d
  implements u
{
  d(MapView paramMapView)
  {
  }

  public void a()
  {
    this.a.a();
    if ((MapView.a(this.a) != null) && (!MapView.b(this.a)) && ((this.a.getHeight() > 0) || (this.a.getWidth() > 0)))
    {
      MapView.a(this.a).onMapLoadFinish();
      MapView.a(this.a, true);
    }
  }

  public void a(int paramInt)
  {
    MapView.a(this.a, paramInt);
  }

  public void a(int paramInt1, int paramInt2)
  {
    if (MapView.e(this.a) != null)
      MapView.e(this.a).onMapClick(this.a.getProjection().fromPixels(paramInt1, paramInt2));
  }

  public void a(int paramInt1, GeoPoint paramGeoPoint, int paramInt2)
  {
    MapView.a(this.a, paramInt1, paramGeoPoint, paramInt2);
  }

  public void a(List<f> paramList)
  {
  }

  public void a(List<r> paramList, int paramInt)
  {
    MapView.a(this.a, (r)paramList.get(0), paramInt);
  }

  public void b()
  {
    float f = this.a.a.i();
    if (f <= 3.0F)
      if (MapView.c(this.a) != f)
      {
        MapView.a(this.a, f);
        Toast.makeText(this.a.getContext(), "已缩小到最小级别", 0).show();
        this.a.a(true, false);
      }
    while (true)
    {
      if ((MapView.d(this.a).b != null) && (MapView.d(this.a).b.getTarget() != null))
      {
        MapView.d(this.a).b.getTarget().sendMessage(MapView.d(this.a).b);
        MapView.d(this.a).b = null;
      }
      if ((MapView.a(this.a) != null) && (a.a))
        MapView.a(this.a).onMapAnimationFinish();
      return;
      if (f >= 19.0F)
      {
        if (MapView.c(this.a) == f)
          continue;
        MapView.a(this.a, f);
        Toast.makeText(this.a.getContext(), "已放大到最大级别", 0).show();
        this.a.a(false, true);
        continue;
      }
      if (MapView.c(this.a) == 0.0F)
        continue;
      MapView.a(this.a, 0.0F);
      this.a.a(true, true);
    }
  }

  public void b(int paramInt1, int paramInt2)
  {
    if (MapView.e(this.a) != null)
      MapView.e(this.a).onMapDoubleClick(this.a.getProjection().fromPixels(paramInt1, paramInt2));
  }

  public void b(List<r> paramList)
  {
  }

  public void b(List<r> paramList, int paramInt)
  {
    MapView.a(this.a, ((r)paramList.get(0)).b, paramInt);
  }

  public void c()
  {
    float f = this.a.a.i();
    if (f <= 3.0F)
      if (MapView.c(this.a) != f)
      {
        MapView.a(this.a, f);
        Toast.makeText(this.a.getContext(), "已缩小到最小级别", 0).show();
        this.a.a(true, false);
      }
    while (true)
    {
      if ((MapView.a(this.a) != null) && (a.a))
        MapView.a(this.a).onMapMoveFinish();
      return;
      if (f >= 19.0F)
      {
        if (MapView.c(this.a) == f)
          continue;
        MapView.a(this.a, f);
        Toast.makeText(this.a.getContext(), "已放大到最大级别", 0).show();
        this.a.a(false, true);
        continue;
      }
      if (MapView.c(this.a) == 0.0F)
        continue;
      MapView.a(this.a, 0.0F);
      this.a.a(true, true);
    }
  }

  public void c(int paramInt1, int paramInt2)
  {
    if (MapView.e(this.a) != null)
      MapView.e(this.a).onMapLongClick(this.a.getProjection().fromPixels(paramInt1, paramInt2));
  }

  public void c(List<r> paramList, int paramInt)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      r localr = (r)paramList.get(0);
      if (localr.e == 17)
      {
        MapPoi localMapPoi = new MapPoi();
        localMapPoi.geoPt = e.a(localr.d);
        localMapPoi.strText = localr.c.replaceAll("\\\\", "");
        localMapPoi.offset = localr.f;
        if ((MapView.a(this.a) != null) && (a.a))
          MapView.a(this.a).onClickMapPoi(localMapPoi);
      }
      if (localr.e == 19)
      {
        t localt = MapView.d(this.a).a.k();
        localt.c = 0;
        localt.b = 0;
        MapView.d(this.a).a.a(localt, 300);
      }
      if (localr.e == 18)
        MapView.b(this.a, paramInt);
      if (localr.e == 23)
        MapView.a(this.a, localr, paramInt);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.d
 * JD-Core Version:    0.6.0
 */