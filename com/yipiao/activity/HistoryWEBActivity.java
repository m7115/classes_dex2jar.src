package com.yipiao.activity;

import cn.suanya.common.ui.WEBActivity;
import com.yipiao.YipiaoApplication;
import org.json.JSONObject;

public class HistoryWEBActivity extends WEBActivity
{
  protected String backUrlsKey()
  {
    return "qlrBackUrls";
  }

  protected String getLink(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf("?") > -1))
      paramString = paramString.substring(0, paramString.indexOf("?"));
    return paramString;
  }

  protected String getUrls()
  {
    return "";
  }

  protected boolean useHistoryBack(String paramString)
  {
    String[] arrayOfString = YipiaoApplication.getApp().launchInfo.optString(backUrlsKey(), getUrls()).split("\\|");
    if (paramString.indexOf("?") > -1)
      paramString = paramString.substring(0, paramString.indexOf("?"));
    int i = arrayOfString.length;
    for (int j = 0; j < i; j++)
      if (arrayOfString[j].equalsIgnoreCase(paramString))
        return false;
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.HistoryWEBActivity
 * JD-Core Version:    0.6.0
 */