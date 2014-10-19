package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import cn.suanya.common.a.n;
import com.yipiao.base.BaseWebViewClient;
import org.json.JSONException;
import org.json.JSONObject;

public class PayZFBWEBActivity extends PayWEBActivity
{
  protected void webViewInit()
  {
    this.webView.setWebViewClient(new BaseWebViewClient()
    {
      public void onLoadResource(WebView paramWebView, String paramString)
      {
        super.onLoadResource(paramWebView, paramString);
      }

      public void onPageFinished(WebView paramWebView, String paramString)
      {
        super.onPageFinished(paramWebView, paramString);
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        if ((paramString != null) && ((paramString.startsWith("http://dynamic.12306.cn/otspay//order/eNotifyAction.do")) || (paramString.startsWith("https://kyfw.12306.cn/opn/enotify/epayStatus"))))
        {
          PayZFBWEBActivity.this.logToServer("zfbPayEnd", "");
          PayZFBWEBActivity.this.showToast("支付完成,请确认已支付订单。", 1);
          MainTab.currentTab = 2131296879;
          OrderTabActivity.currentTab = 2131296963;
          Intent localIntent = new Intent(PayZFBWEBActivity.this, MainTab.class);
          PayZFBWEBActivity.this.startActivity(localIntent);
        }
        super.onPageStarted(paramWebView, paramString, paramBitmap);
      }

      @SuppressLint({"NewApi"})
      public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
      {
        return super.shouldInterceptRequest(paramWebView, paramString);
      }
    });
    super.webViewInit();
  }

  public class JavascriptPostIntercept
  {
    private Handler mHandler = new Handler();

    public JavascriptPostIntercept()
    {
    }

    @JavascriptInterface
    public void customAjax(String paramString)
    {
      this.mHandler.post(new Runnable(paramString)
      {
        public void run()
        {
          n.c("///////////" + this.val$json);
          if (this.val$json.indexOf("token") > -1);
          try
          {
            String str = new JSONObject(this.val$json).optString("qrCode");
            n.a(str);
            Intent localIntent = new Intent(PayZFBWEBActivity.this, PayWEBActivity.class);
            localIntent.putExtra("url", str);
            localIntent.setFlags(268435456);
            PayZFBWEBActivity.this.startActivity(localIntent);
            return;
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      });
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayZFBWEBActivity
 * JD-Core Version:    0.6.0
 */