package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.suanya.common.a.n;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseWebChromeClient;
import com.yipiao.base.BaseWebViewClient;
import java.util.Map;
import org.json.JSONObject;

public class PayWEBActivity extends BaseActivity
{
  private String cookies;
  private Map<String, String> heads;
  private View llTitle;
  protected byte[] postPar;
  private ProgressBar progressBar;
  private TextView tvTitle;
  protected String url;
  protected WebView webView;

  private void initFinishBackBtn()
  {
    View localView = findViewById(2131296259);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          PayWEBActivity.this.finish();
          PayWEBActivity.this.startActivity(new Intent(PayWEBActivity.this, OrderHistoryActivity.class));
        }
      });
  }

  protected int getMainLayout()
  {
    return 2130903141;
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  protected void init()
  {
    super.init();
    if (checkNeedLaunch())
      return;
    this.url = getIntent().getStringExtra("url");
    this.cookies = getIntent().getStringExtra("cookies");
    this.postPar = getIntent().getByteArrayExtra("postPar");
    this.heads = ((Map)getIntent().getSerializableExtra("heads"));
    this.progressBar = ((ProgressBar)findViewById(2131296700));
    this.webView = ((WebView)findViewById(2131296698));
    this.webView.getSettings().setUseWideViewPort(true);
    this.webView.getSettings().setLoadWithOverviewMode(true);
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.getSettings().setSupportMultipleWindows(true);
    this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    this.webView.getSettings().setBuiltInZoomControls(true);
    this.webView.getSettings().setSupportZoom(true);
    this.webView.setDownloadListener(new MyWebViewDownLoadListener());
    this.webView.setWebViewClient(new BaseWebViewClient()
    {
      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        n.b(paramString);
        if ((paramString != null) && ((paramString.startsWith("http://dynamic.12306.cn/otspay//order/eNotifyAction.do")) || (paramString.startsWith("https://kyfw.12306.cn/opn/enotify/epayStatus"))))
        {
          PayWEBActivity.this.showToast("支付完成,请确认已支付订单。", 1);
          MainTab.currentTab = 2131296879;
          OrderTabActivity.currentTab = 2131296963;
          Intent localIntent = new Intent(PayWEBActivity.this, MainTab.class);
          PayWEBActivity.this.startActivity(localIntent);
        }
        super.onPageStarted(paramWebView, paramString, paramBitmap);
      }
    });
    this.webView.setWebChromeClient(new BaseWebChromeClient(this.progressBar));
    Js localJs = new Js();
    this.webView.addJavascriptInterface(localJs, "Merchants");
    webViewInit();
    initTitleView();
    initFinishBackBtn();
    loadWeb();
  }

  public void initTitleView()
  {
    this.llTitle = findViewById(2131296697);
    this.tvTitle = ((TextView)findViewById(2131296258));
    String str = getIntent().getStringExtra("title");
    if ((str != null) && (!str.equals("")))
    {
      this.tvTitle.setText(str);
      this.llTitle.setVisibility(0);
      return;
    }
    this.llTitle.setVisibility(8);
  }

  public void loadWeb()
  {
    if (this.cookies != null)
    {
      CookieSyncManager.createInstance(this).sync();
      CookieManager localCookieManager = CookieManager.getInstance();
      String[] arrayOfString1 = this.cookies.split(";");
      for (int i = 0; i < arrayOfString1.length; i++)
      {
        String[] arrayOfString2 = arrayOfString1[i].split("\\|");
        if (arrayOfString2.length <= 1)
          continue;
        localCookieManager.setCookie(arrayOfString2[0], arrayOfString2[1]);
      }
      CookieSyncManager.getInstance().sync();
    }
    if (this.postPar != null)
    {
      this.webView.postUrl(this.url, this.postPar);
      return;
    }
    if ((this.heads == null) || (this.heads.isEmpty()))
    {
      this.webView.loadUrl(this.url);
      return;
    }
    this.webView.loadUrl(this.url, this.heads);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  protected void webViewInit()
  {
  }

  class Js
  {
    private Handler mHandler = new Handler();

    Js()
    {
    }

    @JavascriptInterface
    public int isInstallCashier(String paramString)
    {
      try
      {
        String str = PayWEBActivity.this.app.launchInfo.optString("unionpay.package", "com.unionpay.mobilepay.mpos.Activity");
        PackageInfo localPackageInfo2 = PayWEBActivity.this.getPackageManager().getPackageInfo(str, 0);
        localPackageInfo1 = localPackageInfo2;
        if (localPackageInfo1 == null)
        {
          i = 0;
          if (i == 1)
            this.mHandler.post(new Runnable(paramString)
            {
              public void run()
              {
                PayWEBActivity localPayWEBActivity;
                try
                {
                  Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.val$url));
                  localIntent.setFlags(268435456);
                  PayWEBActivity.this.startActivity(localIntent);
                  return;
                }
                catch (Exception localException)
                {
                  localPayWEBActivity = PayWEBActivity.this;
                  if (localException == null);
                }
                for (String str = localException.getMessage(); ; str = "")
                {
                  localPayWEBActivity.logToServer("toUnionpayError", str);
                  return;
                }
              }
            });
          return i;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        while (true)
        {
          localNameNotFoundException.printStackTrace();
          PayWEBActivity.this.logToServer("unionpayPackageError", "");
          PackageInfo localPackageInfo1 = null;
          continue;
          int i = 1;
        }
      }
    }
  }

  public class MyWebViewDownLoadListener
    implements DownloadListener
  {
    public MyWebViewDownLoadListener()
    {
    }

    public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString1));
      PayWEBActivity.this.startActivity(localIntent);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayWEBActivity
 * JD-Core Version:    0.6.0
 */