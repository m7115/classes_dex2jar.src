package com.baidu.mapapi.map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import com.baidu.mapapi.search.MKRoute;
import com.baidu.mapapi.search.MKStep;
import com.baidu.mapapi.search.c;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.y;
import com.baidu.platform.comjni.tools.ParcelItem;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class RouteOverlay extends ItemizedOverlay
{
  private ArrayList<a> a = null;
  private MapView b = null;
  private Context c = null;
  private String d = null;
  private y e = null;
  private Drawable f = null;
  private Drawable g = null;
  public ArrayList<MKRoute> mRoute = null;

  public RouteOverlay(Activity paramActivity, MapView paramMapView)
  {
    super(null, paramMapView);
    this.mType = 12;
    this.c = paramActivity;
    this.b = paramMapView;
    this.a = new ArrayList();
    this.mRoute = new ArrayList();
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
    this.e = new y(this.mType);
    this.mLayerID = this.b.a("default");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not create route layer.");
    this.b.a(this.mLayerID, this.e);
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
    return this.d;
  }

  Bundle f()
  {
    if ((this.f == null) && (this.g == null))
      return null;
    Bundle localBundle1 = new Bundle();
    localBundle1.clear();
    ArrayList localArrayList = new ArrayList();
    if (this.f != null)
    {
      ParcelItem localParcelItem1 = new ParcelItem();
      Bundle localBundle2 = new Bundle();
      Bitmap localBitmap1 = ((BitmapDrawable)this.f).getBitmap();
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

  public y getInnerOverlay()
  {
    return this.e;
  }

  public Drawable getStMarker()
  {
    return this.f;
  }

  protected boolean onTap(int paramInt)
  {
    OverlayItem localOverlayItem = getItem(paramInt);
    this.b.getController().animateTo(localOverlayItem.mPoint);
    if (localOverlayItem.mTitle != null)
      Toast.makeText(this.c, localOverlayItem.mTitle, 1).show();
    super.onTap(paramInt);
    return true;
  }

  public void setData(MKRoute paramMKRoute)
  {
    int i = 3;
    if (paramMKRoute == null);
    do
      return;
    while ((paramMKRoute.getStart() == null) || (paramMKRoute.getEnd() == null));
    this.mRoute.add(paramMKRoute);
    if (paramMKRoute.getRouteType() == 1);
    while (true)
    {
      GeoPoint localGeoPoint1 = paramMKRoute.getStart();
      if (localGeoPoint1 != null)
      {
        a locala1 = new a(null);
        locala1.b = localGeoPoint1;
        locala1.c = 0;
        if (i == 4)
          locala1.a = paramMKRoute.getStep(0).getContent();
        this.a.add(locala1);
      }
      int j = paramMKRoute.getNumSteps();
      int k = 0;
      if (j != 0)
        while (true)
          if (k < j)
          {
            MKStep localMKStep = paramMKRoute.getStep(k);
            a locala3 = new a(null);
            locala3.b = localMKStep.getPoint();
            locala3.a = localMKStep.getContent();
            locala3.c = i;
            this.a.add(locala3);
            k++;
            continue;
            if (paramMKRoute.getRouteType() == 2)
            {
              i = 2;
              break;
            }
            if (paramMKRoute.getRouteType() != i)
              break label270;
            i = 4;
            break;
          }
      GeoPoint localGeoPoint2 = paramMKRoute.getEnd();
      if (localGeoPoint2 != null)
      {
        a locala2 = new a(null);
        locala2.b = localGeoPoint2;
        locala2.c = 1;
        this.a.add(locala2);
      }
      g();
      this.d = c.b(this.mRoute);
      return;
      label270: i = 0;
    }
  }

  public void setEnMarker(Drawable paramDrawable)
  {
    this.g = paramDrawable;
  }

  public void setStMarker(Drawable paramDrawable)
  {
    this.f = paramDrawable;
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
 * Qualified Name:     com.baidu.mapapi.map.RouteOverlay
 * JD-Core Version:    0.6.0
 */