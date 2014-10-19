package cn.suanya.hotel.activity;

import android.view.KeyEvent;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import cn.suanya.common.ui.BaseWebViewClient;
import cn.suanya.common.ui.WEBActivity;

public class HotelWEBActivity extends WEBActivity
{
  protected String getLink(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf("?") > -1))
      paramString = paramString.substring(0, paramString.indexOf("?"));
    return paramString;
  }

  protected String getUrls()
  {
    return "http://touch.17u.cn/|http://touch.17u.cn/home/|http://touch.17u.cn/home/index.html|http://touch.17u.cn/scenery/|http://m.ly.com/|http://m.ly.com/home/|http://m.ly.com/hotel/|http://m.ly.com/home/index.html|http://m.ly.com/scenery/|http://m.ctrip.com/html5/|http://m.ctrip.com|http://m.ctrip.com/html5/Hotel/";
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

  protected boolean useHistoryBack(String paramString)
  {
    String[] arrayOfString = getUrls().split("\\|");
    if (paramString.indexOf("?") > -1)
      paramString = paramString.substring(0, paramString.indexOf("?"));
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (arrayOfString[j].equalsIgnoreCase(paramString))
        return false;
    return true;
  }

  protected void webViewInit()
  {
    this.webView.setWebViewClient(new BaseWebViewClient()
    {
      public void onPageFinished(WebView paramWebView, String paramString)
      {
        super.onPageFinished(paramWebView, paramString);
      }
    });
    super.webViewInit();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelWEBActivity
 * JD-Core Version:    0.6.0
 */