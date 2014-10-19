package com.yipiao.activity;

import android.webkit.WebView;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseWebViewClient;
import org.json.JSONObject;

public class PayCCBWEBActivity extends PayWEBActivity
{
  protected void webViewInit()
  {
    this.webView.setWebViewClient(new BaseWebViewClient()
    {
      public void onPageFinished(WebView paramWebView, String paramString)
      {
        if ((!PayCCBWEBActivity.this.app.launchInfo.optBoolean("closeCCBCharset", false)) && (paramString.equals("https://ibsbjstar.ccb.com.cn/app/B2CMainPlatVM?CCB_IBSVersion=V5&PT_STYLE=2")))
        {
          if (PayCCBWEBActivity.this.app.getWebViewFlag() != 2)
            break label55;
          paramWebView.loadUrl("javascript:$('#jhform').attr('accept-charset', 'utf-8');");
        }
        while (true)
        {
          super.onPageFinished(paramWebView, paramString);
          return;
          label55: paramWebView.loadUrl("javascript:$('#jhform').attr('accept-charset', 'gbk');");
        }
      }
    });
    super.webViewInit();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayCCBWEBActivity
 * JD-Core Version:    0.6.0
 */