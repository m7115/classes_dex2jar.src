package cn.suanya.common.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.suanya.a.c;
import cn.suanya.a.d;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class WEBActivity extends SYActivity
{
  private String cookies;
  protected Handler handler;
  private Map<String, String> heads;
  private byte[] postPar;
  protected ProgressBar progressBar;
  protected TimerTask tipTask;
  protected TextView tipTextView;
  protected Timer tipTimer;
  private String url;
  protected WebView webView;

  protected int getContentView()
  {
    return a.d.base_web;
  }

  public String getUrl()
  {
    return this.url;
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public void init()
  {
    this.url = getIntent().getStringExtra("url");
    if (this.url == null)
      this.url = getIntent().getDataString();
    this.postPar = getIntent().getByteArrayExtra("postPar");
    this.heads = ((Map)getIntent().getSerializableExtra("heads"));
    this.cookies = getIntent().getStringExtra("cookies");
    this.progressBar = ((ProgressBar)findViewById(a.c.progressWebLoad));
    this.webView = ((WebView)findViewById(a.c.webView1));
    this.webView.getSettings().setDatabaseEnabled(true);
    this.webView.getSettings().setUseWideViewPort(true);
    this.webView.getSettings().setLoadWithOverviewMode(true);
    this.webView.getSettings().setSavePassword(true);
    this.webView.getSettings().setJavaScriptEnabled(true);
    this.webView.getSettings().setSupportMultipleWindows(true);
    this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    this.webView.getSettings().setBuiltInZoomControls(true);
    this.webView.getSettings().setSupportZoom(true);
    this.webView.setWebViewClient(new BaseWebViewClient());
    this.webView.getSettings().setDomStorageEnabled(true);
    this.webView.getSettings().setGeolocationEnabled(true);
    this.webView.setWebChromeClient(new BaseWebChromeClient(this.progressBar));
    this.webView.setDownloadListener(new MyWebViewDownLoadListener());
    this.tipTextView = ((TextView)findViewById(a.c.tvTip));
    this.handler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        if (WEBActivity.this.tipTextView != null)
          WEBActivity.this.tipTextView.setVisibility(8);
        super.handleMessage(paramMessage);
      }
    };
    this.tipTask = new TimerTask()
    {
      public void run()
      {
        WEBActivity.this.handler.sendEmptyMessage(0);
      }
    };
    this.tipTimer = new Timer();
    String str = getIntent().getStringExtra("webTip");
    if ((str != null) && (!str.equals("")))
    {
      this.tipTextView.setVisibility(0);
      this.tipTextView.setText(str);
      this.tipTimer.schedule(this.tipTask, 5000L);
      return;
    }
    this.tipTextView.setVisibility(8);
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

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(getContentView());
    init();
    webViewInit();
    loadWeb();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }

  protected void webViewInit()
  {
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
      WEBActivity.this.startActivity(localIntent);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.ui.WEBActivity
 * JD-Core Version:    0.6.0
 */