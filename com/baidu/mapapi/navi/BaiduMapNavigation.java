package com.baidu.mapapi.navi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.net.URISyntaxException;

public class BaiduMapNavigation
{
  public static void GetLatestBaiduMapApp(Activity paramActivity)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://mo.baidu.com/map/"));
    paramActivity.startActivity(localIntent);
  }

  private static int a(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 0).versionName;
      if (str != null)
      {
        if (str.length() <= 0)
          return 0;
        int i = Integer.valueOf(str.trim().replace(".", "").trim()).intValue();
        return i;
      }
    }
    catch (Exception localException)
    {
    }
    return 0;
  }

  private static String a(NaviPara paramNaviPara)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("intent://map/direction?");
    localStringBuffer.append("origin=");
    if (paramNaviPara.startName != null)
      localStringBuffer.append("name:" + paramNaviPara.startName + "|");
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Double.valueOf(1.0E-006D * paramNaviPara.startPoint.getLatitudeE6());
    arrayOfObject1[1] = Double.valueOf(1.0E-006D * paramNaviPara.startPoint.getLongitudeE6());
    localStringBuffer.append(String.format("latlng:%f,%f", arrayOfObject1));
    localStringBuffer.append("&destination=");
    if (paramNaviPara.endName != null)
      localStringBuffer.append("name:" + paramNaviPara.endName + "|");
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = Double.valueOf(1.0E-006D * paramNaviPara.endPoint.getLatitudeE6());
    arrayOfObject2[1] = Double.valueOf(1.0E-006D * paramNaviPara.endPoint.getLongitudeE6());
    localStringBuffer.append(String.format("latlng:%f,%f", arrayOfObject2));
    localStringBuffer.append("&coord_type=bd09ll");
    localStringBuffer.append("&mode=navigation");
    localStringBuffer.append("#Intent;scheme=bdapp;");
    localStringBuffer.append("package=com.baidu.BaiduMap;");
    localStringBuffer.append("end");
    return localStringBuffer.toString();
  }

  public static void openBaiduMapNavi(NaviPara paramNaviPara, Activity paramActivity)
  {
    if ((paramNaviPara.endPoint == null) || (paramNaviPara.startPoint == null))
      throw new IllegalNaviArgumentException("start point or end point can not be null.");
    int i = a(paramActivity);
    if (i == 0)
      throw new BaiduMapAppNotSupportNaviException("BaiduMap app is not installed");
    if (i < 500)
      throw new BaiduMapAppNotSupportNaviException("current baidumap app version not support navigation.");
    try
    {
      paramActivity.startActivity(Intent.parseUri(a(paramNaviPara), 0));
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      localURISyntaxException.printStackTrace();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.navi.BaiduMapNavigation
 * JD-Core Version:    0.6.0
 */