package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Bundle;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comapi.map.q;
import com.baidu.platform.comjni.map.basemap.a;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class PopupOverlay extends Overlay
{
  private static int d = 0;
  PopupClickListener a = null;
  private MapView b = null;
  private MapController c = null;

  public PopupOverlay(MapView paramMapView, PopupClickListener paramPopupClickListener)
  {
    this.b = paramMapView;
    this.c = this.b.getController();
    this.mType = 21;
    this.mLayerID = paramMapView.getController().a.a;
    this.a = paramPopupClickListener;
  }

  private Bitmap a(Bitmap paramBitmap1, Bitmap paramBitmap2, Bitmap paramBitmap3)
  {
    if (paramBitmap1 == null)
      paramBitmap1 = null;
    do
      return paramBitmap1;
    while ((paramBitmap2 == null) && (paramBitmap3 == null));
    int i = paramBitmap1.getWidth();
    int j = paramBitmap1.getHeight();
    int m;
    int k;
    if (paramBitmap2 != null)
    {
      int i4 = paramBitmap2.getWidth();
      int i5 = paramBitmap2.getHeight();
      m = i4;
      k = i5;
    }
    while (true)
    {
      int i2;
      int i1;
      if (paramBitmap3 != null)
      {
        i2 = paramBitmap3.getWidth();
        i1 = paramBitmap3.getHeight();
      }
      while (true)
      {
        int i3 = Math.max(Math.max(j, k), i1);
        Bitmap localBitmap = Bitmap.createBitmap(i2 + (i + m), i3, Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        localCanvas.drawBitmap(paramBitmap1, 0.0F, 0.0F, null);
        if (paramBitmap2 != null)
          localCanvas.drawBitmap(paramBitmap2, i, 0.0F, null);
        if (paramBitmap3 != null)
          localCanvas.drawBitmap(paramBitmap3, i + m, 0.0F, null);
        localCanvas.save(31);
        localCanvas.restore();
        return localBitmap;
        i1 = 0;
        i2 = 0;
      }
      k = 0;
      int n = 0;
    }
  }

  public void hidePop()
  {
    if (d == 0)
      return;
    this.c.a.b().c(this.c.a.a);
    this.c.a.b().a(this.c.a.a, false);
    this.b.a.c().remove(this);
    d = 0;
  }

  public void showPopup(Bitmap paramBitmap, GeoPoint paramGeoPoint, int paramInt)
  {
    if ((paramGeoPoint == null) || (paramBitmap == null))
      return;
    this.c.a.b().c(this.c.a.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("layeraddr", ((Integer)this.c.a.e.get("popup")).intValue());
    localBundle.putInt("bshow", 1);
    GeoPoint localGeoPoint = e.b(paramGeoPoint);
    localBundle.putInt("x", localGeoPoint.getLongitudeE6());
    localBundle.putInt("y", localGeoPoint.getLatitudeE6());
    localBundle.putInt("imgW", paramBitmap.getWidth());
    localBundle.putInt("imgH", paramBitmap.getHeight());
    localBundle.putInt("showLR", 1);
    localBundle.putInt("icon0width", 0);
    localBundle.putInt("icon1width", 0);
    localBundle.putInt("iconlayer", 1);
    localBundle.putInt("offset", paramInt);
    localBundle.putInt("popname", -1288857266);
    ByteBuffer localByteBuffer = ByteBuffer.allocate(4 * (paramBitmap.getWidth() * paramBitmap.getHeight()));
    paramBitmap.copyPixelsToBuffer(localByteBuffer);
    localBundle.putByteArray("imgdata", localByteBuffer.array());
    this.c.a.b().b(localBundle);
    this.c.a.b().a(this.c.a.a, true);
    this.c.a.b().a(this.c.a.a);
    if (d == 0)
      this.b.a.c().add(this);
    d = 1 + d;
  }

  public void showPopup(Bitmap[] paramArrayOfBitmap, GeoPoint paramGeoPoint, int paramInt)
  {
    int i = 0;
    if ((paramGeoPoint == null) || (paramArrayOfBitmap == null) || (paramArrayOfBitmap.length == 0) || (paramInt < 0));
    int m;
    int k;
    Bitmap localBitmap1;
    while (true)
    {
      return;
      switch (paramArrayOfBitmap.length)
      {
      default:
        if (paramArrayOfBitmap.length <= 2)
          break label495;
        if (paramArrayOfBitmap[0] != null)
        {
          m = paramArrayOfBitmap[0].getWidth();
          if (paramArrayOfBitmap[1] != null)
            break label449;
          showPopup(paramArrayOfBitmap[0], paramGeoPoint, paramInt);
          return;
        }
      case 1:
        Bitmap localBitmap2 = a(paramArrayOfBitmap[0], null, null);
        k = 0;
        localBitmap1 = localBitmap2;
        if (localBitmap1 == null)
          continue;
        this.c.a.b().c(this.c.a.a);
        Bundle localBundle = new Bundle();
        localBundle.putInt("layeraddr", ((Integer)this.c.a.e.get("popup")).intValue());
        localBundle.putInt("bshow", 1);
        GeoPoint localGeoPoint = e.b(paramGeoPoint);
        localBundle.putInt("x", localGeoPoint.getLongitudeE6());
        localBundle.putInt("y", localGeoPoint.getLatitudeE6());
        localBundle.putInt("imgW", localBitmap1.getWidth());
        localBundle.putInt("imgH", localBitmap1.getHeight());
        localBundle.putInt("showLR", 1);
        localBundle.putInt("icon0width", k);
        localBundle.putInt("icon1width", i);
        localBundle.putInt("iconlayer", 1);
        localBundle.putInt("offset", paramInt);
        localBundle.putInt("popname", -1288857266);
        ByteBuffer localByteBuffer = ByteBuffer.allocate(4 * (localBitmap1.getWidth() * localBitmap1.getHeight()));
        localBitmap1.copyPixelsToBuffer(localByteBuffer);
        localBundle.putByteArray("imgdata", localByteBuffer.array());
        this.c.a.b().b(localBundle);
        this.c.a.b().a(this.c.a.a, true);
        this.c.a.b().a(this.c.a.a);
        if (d == 0)
          this.b.a.c().add(this);
        d = 1 + d;
        return;
      case 2:
        if (paramArrayOfBitmap[0] == null)
          break label507;
      }
    }
    label449: label495: label507: for (int j = paramArrayOfBitmap[0].getWidth(); ; j = 0)
    {
      localBitmap1 = a(paramArrayOfBitmap[0], paramArrayOfBitmap[1], null);
      k = j;
      i = 0;
      break;
      m = 0;
      if (paramArrayOfBitmap[2] != null);
      for (int n = paramArrayOfBitmap[2].getWidth(); ; n = 0)
      {
        localBitmap1 = a(paramArrayOfBitmap[0], paramArrayOfBitmap[1], paramArrayOfBitmap[2]);
        i = n;
        k = m;
        break;
      }
      i = 0;
      k = 0;
      localBitmap1 = null;
      break;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.PopupOverlay
 * JD-Core Version:    0.6.0
 */