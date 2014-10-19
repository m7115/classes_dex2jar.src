package com.yipiao.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import android.widget.TextView;
import cn.suanya.common.ui.WEBActivity;
import com.yipiao.YipiaoApplication;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class CommonWebActivity extends WEBActivity
{
  private boolean history = false;
  private View llTitle;
  private TextView tvTitle;
  private String urlName = null;
  private boolean viewBottom = false;

  private void initBottom()
  {
    View localView = findViewById(2131296674);
    if (localView == null)
      return;
    if (this.viewBottom);
    for (int i = 0; ; i = 8)
    {
      localView.setVisibility(i);
      setClick(2131296262, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (CommonWebActivity.this.webView.canGoBack())
            CommonWebActivity.this.webView.goBack();
        }
      });
      setClick(2131296263, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (CommonWebActivity.this.webView.canGoForward())
            CommonWebActivity.this.webView.goForward();
        }
      });
      setClick(2131296264, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          CommonWebActivity.this.webView.reload();
        }
      });
      return;
    }
  }

  protected String backUrlsKey()
  {
    return "commonBackUrls";
  }

  protected int getContentView()
  {
    return 2130903047;
  }

  protected String getLink(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf("?") > -1))
      paramString = paramString.substring(0, paramString.indexOf("?"));
    return paramString;
  }

  public String getUrlName()
  {
    return this.urlName;
  }

  protected String getUrls()
  {
    return "http://m\\.58\\.com/([\\s\\S]*)/job\\.shtml|http://m\\.58\\.com/([\\s\\S]*)/house\\.shtml|http://m\\.58\\.com/([\\s\\S]*)/jiazheng\\.shtml";
  }

  public void init()
  {
    super.init();
    initTitleView();
    initLeftBackBtn();
    initRightBtn();
    this.viewBottom = getIntent().getBooleanExtra("viewBottom", false);
    this.history = getIntent().getBooleanExtra("history", false);
    this.urlName = getIntent().getStringExtra("urlName");
    initBottom();
  }

  public void initLeftBackBtn()
  {
    View localView = findViewById(2131296261);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          CommonWebActivity.this.finish();
        }
      });
  }

  public void initRightBtn()
  {
    View localView = findViewById(2131296259);
    if (localView != null)
    {
      localView.setVisibility(4);
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          CommonWebActivity.this.finish();
        }
      });
    }
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

  public boolean isHistory()
  {
    return this.history;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    Object localObject;
    if ((paramInt == 4) && (this.webView.canGoBack()) && (isHistory()))
      localObject = "";
    try
    {
      WebBackForwardList localWebBackForwardList = this.webView.copyBackForwardList();
      String str = localWebBackForwardList.getItemAtIndex(localWebBackForwardList.getCurrentIndex()).getUrl();
      localObject = str;
      label52: if (useHistoryBack((String)localObject))
      {
        this.webView.goBack();
        return true;
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    catch (Exception localException)
    {
      break label52;
    }
  }

  public void setViewBottom(boolean paramBoolean)
  {
    this.viewBottom = paramBoolean;
  }

  protected boolean useHistoryBack(String paramString)
  {
    String[] arrayOfString = YipiaoApplication.getApp().launchInfo.optString(backUrlsKey(), getUrls()).split("\\|");
    if (paramString.indexOf("?") > -1)
      paramString = paramString.substring(0, paramString.indexOf("?"));
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (Pattern.matches(arrayOfString[j], paramString))
        return false;
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.CommonWebActivity
 * JD-Core Version:    0.6.0
 */