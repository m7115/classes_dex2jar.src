package com.yipiao.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import com.yipiao.base.BaseWebViewClient;

public class AceneryWEBActivity extends HistoryWEBActivity
{
  protected String backUrlsKey()
  {
    return "17uBackUrls";
  }

  protected String getUrls()
  {
    return "http://touch.17u.cn/|http://touch.17u.cn/home/|http://touch.17u.cn/home/index.html|http://touch.17u.cn/scenery/|http://m.ly.com/|http://m.ly.com/home/|http://m.ly.com/home/index.html|http://m.ly.com/scenery/";
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject;
    if ((paramInt == 4) && (this.webView.canGoBack()))
      localObject = "";
    try
    {
      WebBackForwardList localWebBackForwardList = this.webView.copyBackForwardList();
      String str = localWebBackForwardList.getItemAtIndex(localWebBackForwardList.getCurrentIndex()).getUrl();
      localObject = str;
      label45: if (useHistoryBack((String)localObject))
      {
        this.webView.goBack();
        return true;
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    catch (Exception localException)
    {
      break label45;
    }
  }

  protected void webViewInit()
  {
    super.webViewInit();
    this.webView.setWebViewClient(new AceneryWebViewClient());
  }

  class AceneryWebViewClient extends BaseWebViewClient
  {
    AceneryWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      if ((paramString != null) && (paramString.indexOf("http://m.ly.com/scenery/") <= -1) && (paramString.indexOf("http://touch.17u.cn/scenery/") > -1));
      super.onPageFinished(paramWebView, paramString);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.AceneryWEBActivity
 * JD-Core Version:    0.6.0
 */