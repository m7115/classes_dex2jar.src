package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.baidu.platform.comapi.map.d;
import com.baidu.platform.comapi.map.n;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyLocationOverlay extends Overlay
{
  boolean a = true;
  n b = null;
  private LocationData c = null;
  private MapView d = null;
  private boolean e = false;
  private String f = null;
  private Drawable g;
  private List<Drawable> h;

  public MyLocationOverlay(MapView paramMapView)
  {
    this.d = paramMapView;
    this.mType = 7;
    this.h = new ArrayList();
  }

  void a()
  {
    this.b = new n(this.mType);
    this.mLayerID = this.d.a("location");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not create poi layer.");
    this.d.a(this.mLayerID, this.b);
  }

  d b()
  {
    return this.b;
  }

  Bundle c()
  {
    if (this.g == null)
      return null;
    this.h.clear();
    this.h.add(this.g);
    Bundle localBundle1 = new Bundle();
    localBundle1.clear();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.h.iterator();
    while (localIterator.hasNext())
    {
      Drawable localDrawable = (Drawable)localIterator.next();
      ParcelItem localParcelItem = new ParcelItem();
      Bundle localBundle2 = new Bundle();
      Bitmap localBitmap = ((BitmapDrawable)localDrawable).getBitmap();
      ByteBuffer localByteBuffer = ByteBuffer.allocate(4 * (localBitmap.getWidth() * localBitmap.getHeight()));
      localBitmap.copyPixelsToBuffer(localByteBuffer);
      localBundle2.putByteArray("imgdata", localByteBuffer.array());
      localBundle2.putInt("imgindex", localDrawable.hashCode());
      localBundle2.putInt("imgH", localBitmap.getHeight());
      localBundle2.putInt("imgW", localBitmap.getWidth());
      localParcelItem.setBundle(localBundle2);
      localArrayList.add(localParcelItem);
    }
    if (localArrayList.size() > 0)
    {
      ParcelItem[] arrayOfParcelItem = new ParcelItem[localArrayList.size()];
      for (int i = 0; i < localArrayList.size(); i++)
        arrayOfParcelItem[i] = ((ParcelItem)localArrayList.get(i));
      localBundle1.putParcelableArray("icondata", arrayOfParcelItem);
    }
    this.a = false;
    return localBundle1;
  }

  String d()
  {
    return this.f;
  }

  public void disableCompass()
  {
    this.e = false;
    setData(this.c);
  }

  protected boolean dispatchTap()
  {
    return false;
  }

  public boolean enableCompass()
  {
    this.e = true;
    setData(this.c);
    return true;
  }

  public LocationData getMyLocation()
  {
    return this.c;
  }

  public boolean isCompassEnable()
  {
    return this.e;
  }

  public void setData(LocationData paramLocationData)
  {
    if (paramLocationData != null)
    {
      if (!this.e)
        paramLocationData.direction = -1.0F;
      this.c = paramLocationData;
      this.f = paramLocationData.a();
    }
  }

  public void setMarker(Drawable paramDrawable)
  {
    this.g = paramDrawable;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MyLocationOverlay
 * JD-Core Version:    0.6.0
 */