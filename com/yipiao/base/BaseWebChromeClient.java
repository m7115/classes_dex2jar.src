package com.yipiao.base;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class BaseWebChromeClient extends WebChromeClient
{
  private ProgressBar progressBar;

  public BaseWebChromeClient(ProgressBar paramProgressBar)
  {
    this.progressBar = paramProgressBar;
  }

  public void onProgressChanged(WebView paramWebView, int paramInt)
  {
    if (paramInt >= 100)
      this.progressBar.setVisibility(8);
    while (true)
    {
      this.progressBar.setProgress(paramInt);
      return;
      this.progressBar.setVisibility(0);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.BaseWebChromeClient
 * JD-Core Version:    0.6.0
 */