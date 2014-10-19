package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comjni.map.basemap.a;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ItemizedOverlay<Item extends OverlayItem> extends Overlay
  implements Comparator<Integer>
{
  private ArrayList<OverlayItem> a;
  private ArrayList<Integer> b;
  private Drawable c;
  private boolean d;
  protected MapView mMapView;

  public ItemizedOverlay(Drawable paramDrawable, MapView paramMapView)
  {
    this.mType = 27;
    this.c = paramDrawable;
    this.a = new ArrayList();
    this.b = new ArrayList();
    this.mMapView = paramMapView;
    this.mLayerID = 0;
  }

  private void a(List<OverlayItem> paramList, boolean paramBoolean)
  {
    int i = 0;
    if (this.mLayerID == 0)
    {
      if (!paramBoolean)
        this.a.addAll(paramList);
      return;
    }
    Bundle localBundle1 = new Bundle();
    localBundle1.clear();
    ArrayList localArrayList = new ArrayList();
    localBundle1.putInt("itemaddr", this.mLayerID);
    localBundle1.putInt("bshow", 1);
    if (paramBoolean)
      localBundle1.putString("extparam", "update");
    int j = 0;
    if (j < paramList.size())
    {
      OverlayItem localOverlayItem = (OverlayItem)paramList.get(j);
      if ((localOverlayItem.getMarker() == null) && (this.c == null));
      ParcelItem localParcelItem;
      Bitmap localBitmap;
      Bundle localBundle2;
      do
      {
        j++;
        break;
        if (localOverlayItem.getMarker() == null)
          localOverlayItem.setMarker(this.c);
        long l = System.currentTimeMillis();
        if (!paramBoolean)
          localOverlayItem.a("" + l + "_" + j);
        localParcelItem = new ParcelItem();
        localBitmap = ((BitmapDrawable)localOverlayItem.getMarker()).getBitmap();
        localBundle2 = new Bundle();
      }
      while (localOverlayItem.getPoint() == null);
      GeoPoint localGeoPoint = e.b(localOverlayItem.getPoint());
      localBundle2.putInt("x", localGeoPoint.getLongitudeE6());
      localBundle2.putInt("y", localGeoPoint.getLatitudeE6());
      localBundle2.putInt("imgW", localBitmap.getWidth());
      localBundle2.putInt("imgH", localBitmap.getHeight());
      localBundle2.putInt("showLR", 1);
      localBundle2.putInt("iconwidth", 0);
      localBundle2.putInt("iconlayer", 1);
      localBundle2.putFloat("ax", localOverlayItem.getAnchorX());
      localBundle2.putFloat("ay", localOverlayItem.getAnchorY());
      localBundle2.putInt("bound", localOverlayItem.a());
      localBundle2.putString("popname", "" + localOverlayItem.c());
      localBundle2.putInt("imgindex", localOverlayItem.b());
      if ((!paramBoolean) && (a(localOverlayItem)))
        localBundle2.putByteArray("imgdata", null);
      while (true)
      {
        localParcelItem.setBundle(localBundle2);
        localArrayList.add(localParcelItem);
        if (paramBoolean)
          break;
        this.a.add(localOverlayItem);
        break;
        ByteBuffer localByteBuffer = ByteBuffer.allocate(4 * (localBitmap.getWidth() * localBitmap.getHeight()));
        localBitmap.copyPixelsToBuffer(localByteBuffer);
        localBundle2.putByteArray("imgdata", localByteBuffer.array());
      }
    }
    if (localArrayList.size() > 0)
    {
      ParcelItem[] arrayOfParcelItem = new ParcelItem[localArrayList.size()];
      while (i < localArrayList.size())
      {
        arrayOfParcelItem[i] = ((ParcelItem)localArrayList.get(i));
        i++;
      }
      localBundle1.putParcelableArray("itemdatas", arrayOfParcelItem);
      this.mMapView.getController().a.b().c(localBundle1);
    }
    this.d = true;
  }

  private boolean a(OverlayItem paramOverlayItem)
  {
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      OverlayItem localOverlayItem = (OverlayItem)localIterator.next();
      if (paramOverlayItem.b() == -1)
        return false;
      if ((localOverlayItem.b() != -1) && (paramOverlayItem.b() == localOverlayItem.b()))
        return true;
    }
    return false;
  }

  private int b(boolean paramBoolean)
  {
    if (this.a == null);
    do
      return 0;
    while (this.a.size() == 0);
    Iterator localIterator = this.a.iterator();
    int i = -2147483648;
    int j = 2147483647;
    if (!localIterator.hasNext())
      return i - j;
    GeoPoint localGeoPoint = ((OverlayItem)localIterator.next()).getPoint();
    int k;
    if (paramBoolean)
    {
      k = localGeoPoint.getLatitudeE6();
      label75: if (k > i)
        i = k;
      if (k >= j)
        break label108;
    }
    while (true)
    {
      j = k;
      break;
      k = localGeoPoint.getLongitudeE6();
      break label75;
      label108: k = j;
    }
  }

  protected static void boundCenter(OverlayItem paramOverlayItem)
  {
    if (paramOverlayItem == null)
      return;
    paramOverlayItem.a(2);
    paramOverlayItem.setAnchor(0.5F, 0.5F);
  }

  protected static void boundCenterBottom(OverlayItem paramOverlayItem)
  {
    if (paramOverlayItem == null)
      return;
    paramOverlayItem.a(1);
    paramOverlayItem.setAnchor(0.5F, 1.0F);
  }

  void a()
  {
    this.mLayerID = this.mMapView.a("item");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not add new layer");
  }

  void a(ArrayList<OverlayItem> paramArrayList)
  {
    int i = paramArrayList.size();
    if (this.b != null)
    {
      this.b.clear();
      this.b = null;
    }
    if (this.a != null)
    {
      this.a.clear();
      this.a = null;
    }
    this.a = new ArrayList(i);
    this.b = new ArrayList(i);
    for (int j = 0; j < i; j++)
    {
      this.b.add(j, Integer.valueOf(j));
      OverlayItem localOverlayItem = (OverlayItem)paramArrayList.get(j);
      if (localOverlayItem.getMarker() == null)
        localOverlayItem.setMarker(this.c);
      this.a.add(j, localOverlayItem);
    }
    Collections.sort(this.b, this);
  }

  void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  public void addItem(OverlayItem paramOverlayItem)
  {
    if ((this.a != null) && (paramOverlayItem != null))
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramOverlayItem);
      addItem(localArrayList);
    }
  }

  public void addItem(List<OverlayItem> paramList)
  {
    if (paramList == null)
      return;
    a(paramList, false);
  }

  void b()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.a);
    removeAll();
    addItem(localArrayList);
  }

  boolean c()
  {
    return this.d;
  }

  public int compare(Integer paramInteger1, Integer paramInteger2)
  {
    int i = 1;
    GeoPoint localGeoPoint1 = ((OverlayItem)this.a.get(paramInteger1.intValue())).getPoint();
    GeoPoint localGeoPoint2 = ((OverlayItem)this.a.get(paramInteger2.intValue())).getPoint();
    if (localGeoPoint1.getLatitudeE6() > localGeoPoint2.getLatitudeE6())
      i = -1;
    do
      return i;
    while (localGeoPoint1.getLatitudeE6() < localGeoPoint2.getLatitudeE6());
    if (localGeoPoint1.getLongitudeE6() < localGeoPoint2.getLongitudeE6())
      return -1;
    if (localGeoPoint1.getLongitudeE6() == localGeoPoint2.getLongitudeE6());
    for (int j = 0; ; j = i)
      return j;
  }

  protected Item createItem(int paramInt)
  {
    return null;
  }

  int d()
  {
    return this.mLayerID;
  }

  public ArrayList<OverlayItem> getAllItem()
  {
    return this.a;
  }

  public GeoPoint getCenter()
  {
    int i = getIndexToDraw(0);
    if (i == -1)
      return null;
    return getItem(i).getPoint();
  }

  protected int getIndexToDraw(int paramInt)
  {
    if ((this.a == null) || (this.a.size() == 0))
      paramInt = -1;
    return paramInt;
  }

  public final OverlayItem getItem(int paramInt)
  {
    if ((this.a != null) && (this.a.size() > paramInt) && (paramInt >= 0))
      return (OverlayItem)this.a.get(paramInt);
    return null;
  }

  public int getLatSpanE6()
  {
    return b(true);
  }

  public int getLonSpanE6()
  {
    return b(false);
  }

  protected boolean hitTest(OverlayItem paramOverlayItem, Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    if (paramDrawable == null)
      return false;
    Bitmap localBitmap = ((BitmapDrawable)paramDrawable).getBitmap();
    int i = localBitmap.getHeight();
    int j = localBitmap.getWidth();
    Point localPoint = this.mMapView.getProjection().toPixels(paramOverlayItem.getPoint(), null);
    Rect localRect = paramDrawable.getBounds();
    localRect.left = (localPoint.x - (int)(paramOverlayItem.getAnchorX() * j));
    localRect.right = (localPoint.x + (int)((1.0F - paramOverlayItem.getAnchorX()) * j));
    localRect.bottom = (localPoint.y + (int)((1.0F - paramOverlayItem.getAnchorY()) * i));
    localRect.top = (localPoint.y - (int)(paramOverlayItem.getAnchorY() * i));
    return localRect.contains(paramInt1, paramInt2);
  }

  protected boolean onTap(int paramInt)
  {
    return false;
  }

  public boolean onTap(GeoPoint paramGeoPoint, MapView paramMapView)
  {
    return false;
  }

  public boolean removeAll()
  {
    this.mMapView.getController().a.b().c(this.mLayerID);
    this.a.clear();
    this.d = true;
    return true;
  }

  public boolean removeItem(OverlayItem paramOverlayItem)
  {
    if (this.mLayerID == 0);
    Bundle localBundle;
    do
    {
      do
      {
        return false;
        localBundle = new Bundle();
        localBundle.putInt("itemaddr", this.mLayerID);
      }
      while (paramOverlayItem.c().equals(""));
      localBundle.putString("id", paramOverlayItem.c());
    }
    while (!this.mMapView.getController().a.b().d(localBundle));
    this.a.remove(paramOverlayItem);
    this.d = true;
    return true;
  }

  public int size()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
  }

  public boolean updateItem(OverlayItem paramOverlayItem)
  {
    if (paramOverlayItem == null)
      return false;
    if (paramOverlayItem.c().equals(""))
      return false;
    Iterator localIterator = this.a.iterator();
    OverlayItem localOverlayItem;
    do
    {
      if (!localIterator.hasNext())
        break;
      localOverlayItem = (OverlayItem)localIterator.next();
    }
    while (!paramOverlayItem.c().equals(localOverlayItem.c()));
    for (int i = 1; ; i = 0)
    {
      if (i == 0)
        return false;
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramOverlayItem);
      a(localArrayList, true);
      return true;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.ItemizedOverlay
 * JD-Core Version:    0.6.0
 */