package com.yipiao.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({"NewApi"})
public class BaseWebViewClient extends WebViewClient
{
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    paramSslErrorHandler.proceed();
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (paramString.startsWith("alipayqr:"))
    {
      Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      localIntent1.setFlags(268435456);
      paramWebView.getContext().startActivity(localIntent1);
    }
    while (true)
    {
      return true;
      if (paramString.startsWith("tel:"))
      {
        Intent localIntent2 = new Intent("android.intent.action.DIAL", Uri.parse(paramString));
        localIntent2.setFlags(268435456);
        paramWebView.getContext().startActivity(localIntent2);
        continue;
      }
      if (paramString.startsWith("sms:"))
      {
        Intent localIntent3 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        localIntent3.setFlags(268435456);
        paramWebView.getContext().startActivity(localIntent3);
        continue;
      }
      if (paramString.startsWith("mailto:"))
      {
        try
        {
          Intent localIntent4 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
          localIntent4.setFlags(268435456);
          paramWebView.getContext().startActivity(localIntent4);
        }
        catch (Exception localException)
        {
        }
        continue;
      }
      paramWebView.loadUrl(paramString);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.BaseWebViewClient
 * JD-Core Version:    0.6.0
 */