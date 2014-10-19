package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.suanya.common.a.n;
import cn.suanya.hc.service.PathService;
import cn.suanya.hotel.service.CityService;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.LaunchService;
import com.yipiao.service.YipiaoService;

public class LaunchActivity extends BaseActivity
{
  private WebView webView;

  @SuppressLint({"SetJavaScriptEnabled"})
  private void checkWebView()
  {
    if (this.app.getWebViewFlag() > 0)
      return;
    this.webView = ((WebView)findViewById(2131296647));
    Js localJs = new Js();
    this.webView.addJavascriptInterface(localJs, "a");
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.loadUrl("http://suanya.cn/zx/shell/browser");
  }

  private void exec()
  {
    new MyAsyncTask(this, false)
    {
      protected String myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        Thread.sleep(500L);
        return null;
      }

      protected void myPostExecute(String paramString)
      {
        MainTab.currentTab = 2131296873;
        Intent localIntent = new Intent(LaunchActivity.this, MainTab.class);
        LaunchActivity.this.startActivity(localIntent);
        LaunchActivity.this.finish();
      }
    }
    .execute(new String[0]);
  }

  private void exec2()
  {
    new Thread()
    {
      public void run()
      {
        PathService.getInstance().init();
        CityService.instance().getAllCity();
        try
        {
          HuocheMobile.getInstance().init(1);
          LaunchActivity.this.app.autoSelectApi = 0;
          YipiaoService.getInstance().all12306City();
          YipiaoService.getInstance().all12306University();
          return;
        }
        catch (Exception localException)
        {
          while (true)
            n.a(localException);
        }
      }
    }
    .start();
  }

  protected int getMainLayout()
  {
    return 2130903085;
  }

  public void onCreate(Bundle paramBundle)
  {
    n.a("Launch-create");
    super.onCreate(paramBundle);
    checkWebView();
    LaunchService.launch(this, 0);
    exec();
    exec2();
    n.a("Launch-create2");
  }

  class Js
  {
    Js()
    {
    }

    @JavascriptInterface
    public void b(String paramString)
    {
      try
      {
        int j = Integer.parseInt(paramString);
        i = j;
        LaunchActivity.this.app.setWebViewFlag(i);
        return;
      }
      catch (Exception localException)
      {
        while (true)
          int i = 0;
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.LaunchActivity
 * JD-Core Version:    0.6.0
 */