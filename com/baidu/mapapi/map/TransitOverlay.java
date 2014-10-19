package com.baidu.mapapi.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import com.baidu.mapapi.search.MKLine;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKTransitRoutePlan;
import com.baidu.mapapi.search.c;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.A;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class TransitOverlay extends ItemizedOverlay
{
  private ArrayList<a> a = null;
  private MapView b = null;
  private Context c = null;
  private int d = 1;
  private String e = null;
  private A f = null;
  private Drawable g;
  private Drawable h;
  public ArrayList<MKTransitRoutePlan> mPlan = null;

  public TransitOverlay(Activity paramActivity, MapView paramMapView)
  {
    super(null, paramMapView);
    this.mType = 28;
    this.c = paramActivity;
    this.b = paramMapView;
    this.a = new ArrayList();
    this.mPlan = new ArrayList();
  }

  private void g()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < size(); i++)
      localArrayList.add(createItem(i));
    super.a(localArrayList);
  }

  void a()
  {
    this.f = new A(12);
    this.mLayerID = this.b.a("default");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not create transit layer.");
    this.b.a(this.mLayerID, this.f);
  }

  public void animateTo()
  {
    if (size() > 0)
    {
      OverlayItem localOverlayItem = getItem(0);
      if (localOverlayItem != null)
        this.b.getController().animateTo(localOverlayItem.mPoint);
    }
  }

  protected OverlayItem createItem(int paramInt)
  {
    a locala = (a)this.a.get(paramInt);
    return new OverlayItem(locala.b, locala.a, null);
  }

  String e()
  {
    return this.e;
  }

  Bundle f()
  {
    if ((this.h == null) && (this.g == null))
      return null;
    Bundle localBundle1 = new Bundle();
    localBundle1.clear();
    ArrayList localArrayList = new ArrayList();
    if (this.h != null)
    {
      ParcelItem localParcelItem1 = new ParcelItem();
      Bundle localBundle2 = new Bundle();
      Bitmap localBitmap1 = ((BitmapDrawable)this.h).getBitmap();
      ByteBuffer localByteBuffer1 = ByteBuffer.allocate(4 * (localBitmap1.getWidth() * localBitmap1.getHeight()));
      localBitmap1.copyPixelsToBuffer(localByteBuffer1);
      localBundle2.putByteArray("imgdata", localByteBuffer1.array());
      localBundle2.putInt("type", 1);
      localBundle2.putInt("imgH", localBitmap1.getHeight());
      localBundle2.putInt("imgW", localBitmap1.getWidth());
      localParcelItem1.setBundle(localBundle2);
      localArrayList.add(localParcelItem1);
    }
    if (this.g != null)
    {
      ParcelItem localParcelItem2 = new ParcelItem();
      Bundle localBundle3 = new Bundle();
      Bitmap localBitmap2 = ((BitmapDrawable)this.g).getBitmap();
      ByteBuffer localByteBuffer2 = ByteBuffer.allocate(4 * (localBitmap2.getWidth() * localBitmap2.getHeight()));
      localBitmap2.copyPixelsToBuffer(localByteBuffer2);
      localBundle3.putByteArray("imgdata", localByteBuffer2.array());
      localBundle3.putInt("type", 2);
      localBundle3.putInt("imgH", localBitmap2.getHeight());
      localBundle3.putInt("imgW", localBitmap2.getWidth());
      localParcelItem2.setBundle(localBundle3);
      localArrayList.add(localParcelItem2);
    }
    if (localArrayList.size() > 0)
    {
      ParcelItem[] arrayOfParcelItem = new ParcelItem[localArrayList.size()];
      for (int i = 0; i < localArrayList.size(); i++)
        arrayOfParcelItem[i] = ((ParcelItem)localArrayList.get(i));
      localBundle1.putParcelableArray("icondata", arrayOfParcelItem);
    }
    return localBundle1;
  }

  public Drawable getEnMarker()
  {
    return this.g;
  }

  public A getInnerOverlay()
  {
    return this.f;
  }

  public Drawable getStMarker()
  {
    return this.h;
  }

  protected boolean onTap(int paramInt)
  {
    OverlayItem localOverlayItem = getItem(paramInt);
    this.b.getController().animateTo(localOverlayItem.mPoint);
    if ((localOverlayItem != null) && (localOverlayItem.mTitle != null))
      Toast.makeText(this.c, localOverlayItem.mTitle, 1).show();
    super.onTap(paramInt);
    return true;
  }

  public void setData(MKTransitRoutePlan paramMKTransitRoutePlan)
  {
    if (paramMKTransitRoutePlan == null);
    int i;
    int j;
    do
    {
      return;
      i = paramMKTransitRoutePlan.getNumLines();
      j = paramMKTransitRoutePlan.getNumRoute();
    }
    while ((i == 0) && (j == 0));
    this.mPlan.add(paramMKTransitRoutePlan);
    GeoPoint localGeoPoint1 = paramMKTransitRoutePlan.getStart();
    if (localGeoPoint1 != null)
    {
      a locala1 = new a(null);
      locala1.b = localGeoPoint1;
      locala1.c = 0;
      this.a.add(locala1);
    }
    int k = 0;
    if (k < i)
    {
      MKLine localMKLine1 = paramMKTransitRoutePlan.getLine(k);
      a locala3 = new a(null);
      MKPoiInfo localMKPoiInfo1 = localMKLine1.getGetOnStop();
      locala3.b = localMKPoiInfo1.pt;
      locala3.a = ("在" + localMKPoiInfo1.name + "上车，" + "乘坐" + localMKLine1.getTitle() + "经过" + String.valueOf(localMKLine1.getNumViaStops()) + "站");
      if ((k == 0) && (this.a.size() > 0))
        ((a)this.a.get(-1 + this.a.size())).a = locala3.a;
      label249: a locala4;
      int m;
      if (localMKLine1.getType() == 0)
      {
        locala3.c = 2;
        this.a.add(locala3);
        MKLine localMKLine2 = paramMKTransitRoutePlan.getLine(k);
        locala4 = new a(null);
        MKPoiInfo localMKPoiInfo2 = localMKLine1.getGetOffStop();
        locala4.b = localMKPoiInfo2.pt;
        locala4.a = ("在" + localMKPoiInfo2.name + "下车");
        m = 0;
        label332: if (m < j)
        {
          MKRoute localMKRoute = paramMKTransitRoutePlan.getRoute(m);
          if (localMKRoute.getIndex() != k)
            break label432;
          locala4.a = (locala4.a + "," + localMKRoute.getTip());
        }
        if (localMKLine2.getType() != 0)
          break label438;
      }
      label432: label438: for (locala4.c = 2; ; locala4.c = 4)
      {
        this.a.add(locala4);
        k++;
        break;
        locala3.c = 4;
        break label249;
        m++;
        break label332;
      }
    }
    GeoPoint localGeoPoint2 = paramMKTransitRoutePlan.getEnd();
    if (localGeoPoint2 != null)
    {
      a locala2 = new a(null);
      locala2.b = localGeoPoint2;
      locala2.c = 1;
      this.a.add(locala2);
    }
    g();
    this.e = c.a(this.mPlan);
  }

  public void setEnMarker(Drawable paramDrawable)
  {
    this.g = paramDrawable;
  }

  public void setStMarker(Drawable paramDrawable)
  {
    this.h = paramDrawable;
  }

  public int size()
  {
    if (this.a == null)
      return 0;
    return this.a.size();
  }

  private class a
  {
    public String a;
    public GeoPoint b;
    public int c;

    private a()
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.TransitOverlay
 * JD-Core Version:    0.6.0
 */