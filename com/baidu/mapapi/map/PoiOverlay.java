package com.baidu.mapapi.map;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.c;
import com.baidu.platform.comapi.map.d;
import com.baidu.platform.comapi.map.x;
import java.util.ArrayList;

public class PoiOverlay extends Overlay
{
  private MapView a = null;
  private Context b = null;
  private ArrayList<MKPoiInfo> c = null;
  private String d = null;
  private d e;

  public PoiOverlay(Activity paramActivity, MapView paramMapView)
  {
    this.mType = 14;
    this.b = paramActivity;
    this.c = new ArrayList();
    this.a = paramMapView;
  }

  void a()
  {
    this.e = new x(this.mType);
    this.mLayerID = this.a.a("default");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not create poi layer.");
    this.a.a(this.mLayerID, this.e);
  }

  public void animateTo()
  {
    if (size() > 0)
      this.a.getController().animateTo(((MKPoiInfo)this.c.get(0)).pt);
  }

  String b()
  {
    return this.d;
  }

  public d getInnerOverlay()
  {
    return this.e;
  }

  public MKPoiInfo getPoi(int paramInt)
  {
    if (this.c == null);
    do
      return null;
    while ((paramInt < 0) || (paramInt >= this.c.size()));
    return (MKPoiInfo)this.c.get(paramInt);
  }

  protected boolean onTap(int paramInt)
  {
    this.a.getController().animateTo(((MKPoiInfo)this.c.get(paramInt)).pt);
    Toast.makeText(this.b, ((MKPoiInfo)this.c.get(paramInt)).name, 1).show();
    return false;
  }

  public void setData(ArrayList<MKPoiInfo> paramArrayList)
  {
    if (paramArrayList == null);
    do
      return;
    while (paramArrayList.size() <= 0);
    this.c.clear();
    for (int i = 0; i < paramArrayList.size(); i++)
      this.c.add(paramArrayList.get(i));
    this.d = c.c(this.c);
  }

  public int size()
  {
    int i = 10;
    if (this.c == null)
      i = 0;
    do
      return i;
    while (this.c.size() > i);
    return this.c.size();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.PoiOverlay
 * JD-Core Version:    0.6.0
 */