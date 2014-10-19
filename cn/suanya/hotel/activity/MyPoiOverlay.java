package cn.suanya.hotel.activity;

import android.app.Activity;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKSearch;

public class MyPoiOverlay extends PoiOverlay
{
  MKSearch mSearch;

  public MyPoiOverlay(Activity paramActivity, MapView paramMapView, MKSearch paramMKSearch)
  {
    super(paramActivity, paramMapView);
    this.mSearch = paramMKSearch;
  }

  protected boolean onTap(int paramInt)
  {
    super.onTap(paramInt);
    MKPoiInfo localMKPoiInfo = getPoi(paramInt);
    if (localMKPoiInfo.hasCaterDetails)
      this.mSearch.poiDetailSearch(localMKPoiInfo.uid);
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.MyPoiOverlay
 * JD-Core Version:    0.6.0
 */