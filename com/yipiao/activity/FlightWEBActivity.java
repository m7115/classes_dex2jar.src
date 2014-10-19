package com.yipiao.activity;

import android.view.KeyEvent;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import cn.suanya.common.ui.BaseWebViewClient;

public class FlightWEBActivity extends HistoryWEBActivity
{
  protected String getUrls()
  {
    return "http://touch.qunar.com/h5/|http://touch.qunar.com/h5/index|http://user.qunar.com/mobile/login.jsp|http://touch.qunar.com/h5/user/usercenter|http://touch.qunar.com/h5/flight/flightorderqmc|http://touch.qunar.com/h5/hotel/hotelorderqmc|http://touch.qunar.com/h5/group/grouporderqmc|http://touch.qunar.com/h5/flight/flightlist|http://touch.qunar.com/h5/flight/index|http://touch.qunar.com/h5/flight/";
  }

  public void init()
  {
    super.init();
    this.webView.setWebViewClient(new FlightWebViewClient());
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject1;
    Object localObject2;
    if ((paramInt == 4) && (this.webView.canGoBack()))
    {
      localObject1 = "";
      localObject2 = "";
    }
    try
    {
      localWebBackForwardList = this.webView.copyBackForwardList();
      String str3 = localWebBackForwardList.getItemAtIndex(localWebBackForwardList.getCurrentIndex()).getUrl();
      localObject1 = str3;
    }
    catch (Exception localException1)
    {
      try
      {
        WebBackForwardList localWebBackForwardList;
        String str4 = localWebBackForwardList.getItemAtIndex(-1 + localWebBackForwardList.getCurrentIndex()).getUrl();
        localObject2 = str4;
        while (true)
        {
          label70: String str1 = getLink((String)localObject1);
          String str2 = getLink((String)localObject2);
          if ("https://mpkq.qunar.com/touch/touchCashier".equalsIgnoreCase(str1))
          {
            this.webView.goBackOrForward(-2);
            return true;
          }
          if (("http://touch.qunar.com/h5/user/usercenter".equalsIgnoreCase(str1)) && (str2 != null) && (str2.indexOf("http://user.qunar.com/mobile/login.jsp") > -1))
          {
            this.webView.goBackOrForward(-4);
            return true;
          }
          if (useHistoryBack((String)localObject1))
          {
            this.webView.goBack();
            return true;
          }
          return super.onKeyDown(paramInt, paramKeyEvent);
          return super.onKeyDown(paramInt, paramKeyEvent);
          localException1 = localException1;
        }
      }
      catch (Exception localException2)
      {
        break label70;
      }
    }
  }

  class FlightWebViewClient extends BaseWebViewClient
  {
    FlightWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      if ((paramString != null) && (paramString.indexOf("http://touch.qunar.com/h5") > -1))
        paramWebView.loadUrl("javascript:setInterval(function(){ var _ad =document.getElementsByClassName('ad')[0] ; if (_ad!=null){ for (var _index = 0; _index < _ad.children.length; _index ++ ){ var c =_ad.children[_index]; if (c.nodeName!='SCRIPT'){ c.style='display:NONE' };  }; _ad.style='display:NONE';} },200);");
      super.onPageFinished(paramWebView, paramString);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.FlightWEBActivity
 * JD-Core Version:    0.6.0
 */