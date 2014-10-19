package com.yipiao.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import cn.suanya.common.net.LogInfo;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.yipiao.service.YipiaoService;

public class LocationWEBActivity extends CommonWebActivity
{
  private static BDLocation mLocation = null;
  private LocationClient locationClient = null;

  private String formatUrl(String paramString, BDLocation paramBDLocation)
  {
    double d1 = paramBDLocation.getLatitude();
    double d2 = paramBDLocation.getLongitude();
    return paramString.replace("{lat}", "" + d1).replace("{lng}", "" + d2);
  }

  private void initBDLocation()
  {
    this.locationClient = new LocationClient(this);
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setOpenGps(false);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setPriority(2);
    localLocationClientOption.setProdName("LocationDemo");
    localLocationClientOption.setScanSpan(0);
    localLocationClientOption.setPoiNumber(5);
    localLocationClientOption.setPoiDistance(1000.0F);
    localLocationClientOption.setPoiExtraInfo(true);
    localLocationClientOption.setAddrType("all");
    localLocationClientOption.disableCache(false);
    this.locationClient.setLocOption(localLocationClientOption);
    this.locationClient.registerLocationListener(new BDLocationListener()
    {
      public void onReceiveLocation(BDLocation paramBDLocation)
      {
        if (paramBDLocation == null)
          return;
        LocationWEBActivity.access$002(paramBDLocation);
        LocationWEBActivity.this.setUrl(LocationWEBActivity.this.formatUrl(LocationWEBActivity.this.getUrl(), paramBDLocation));
        LocationWEBActivity.this.loadWebSimple();
      }

      public void onReceivePoi(BDLocation paramBDLocation)
      {
      }
    });
  }

  public void initLeftBackBtn()
  {
    View localView = findViewById(2131296261);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Object localObject;
          if ((LocationWEBActivity.this.webView.canGoBack()) && (LocationWEBActivity.this.isHistory()))
            localObject = "";
          try
          {
            WebBackForwardList localWebBackForwardList = LocationWEBActivity.this.webView.copyBackForwardList();
            String str = localWebBackForwardList.getItemAtIndex(localWebBackForwardList.getCurrentIndex()).getUrl();
            localObject = str;
            label56: if (LocationWEBActivity.this.useHistoryBack((String)localObject))
            {
              LocationWEBActivity.this.webView.goBack();
              return;
            }
            LocationWEBActivity.this.finish();
            return;
            LocationWEBActivity.this.finish();
            return;
          }
          catch (Exception localException)
          {
            break label56;
          }
        }
      });
  }

  public void initRightBtn()
  {
    View localView = findViewById(2131296259);
    if (localView != null)
    {
      localView.setVisibility(0);
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          LocationWEBActivity.this.finish();
        }
      });
    }
  }

  public void loadWeb()
  {
    String str1 = getUrlName();
    YipiaoService localYipiaoService = YipiaoService.getInstance();
    if (str1 != null);
    while (true)
    {
      localYipiaoService.asyncLog(new LogInfo(str1, super.getUrl()));
      String str2 = super.getUrl();
      if ((str2 == null) || (str2.indexOf("{lat}") <= -1))
        break;
      if (mLocation != null)
      {
        setUrl(formatUrl(str2, mLocation));
        loadWebSimple();
        return;
        str1 = "LocationWEB";
        continue;
      }
      initBDLocation();
      if (!this.locationClient.isStarted())
        this.locationClient.start();
      this.locationClient.requestLocation();
      return;
    }
    loadWebSimple();
  }

  public void loadWebSimple()
  {
    super.loadWeb();
  }

  protected void onDestroy()
  {
    if (this.locationClient != null)
      this.locationClient.stop();
    super.onDestroy();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.LocationWEBActivity
 * JD-Core Version:    0.6.0
 */