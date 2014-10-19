package com.yipiao.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.webkit.WebView;
import cn.suanya.common.ui.BaseWebViewClient;
import cn.suanya.common.ui.WEBActivity;

public class WEB12306Activity extends WEBActivity
{
  private boolean needLogin;
  private String targetUrl;

  public void finish()
  {
    if (this.webView != null)
      this.webView.stopLoading();
    super.finish();
  }

  public void init()
  {
    super.init();
    this.targetUrl = getIntent().getStringExtra("url");
    this.needLogin = getIntent().getBooleanExtra("needLogin", false);
    setUrl("https://dynamic.12306.cn/otsweb/");
    this.webView.setWebViewClient(new FlightWebViewClient());
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  class FlightWebViewClient extends BaseWebViewClient
  {
    FlightWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.WEB12306Activity
 * JD-Core Version:    0.6.0
 */