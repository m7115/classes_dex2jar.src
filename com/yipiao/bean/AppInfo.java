package com.yipiao.bean;

import android.graphics.drawable.Drawable;

public class AppInfo
{
  private String appClassName;
  private Drawable appIcon;
  private String appName;
  private String appPkgName;

  public String getAppClassName()
  {
    return this.appClassName;
  }

  public Drawable getAppIcon()
  {
    return this.appIcon;
  }

  public String getAppName()
  {
    return this.appName;
  }

  public String getAppPkgName()
  {
    return this.appPkgName;
  }

  public void setAppClassName(String paramString)
  {
    this.appClassName = paramString;
  }

  public void setAppIcon(Drawable paramDrawable)
  {
    this.appIcon = paramDrawable;
  }

  public void setAppName(String paramString)
  {
    this.appName = paramString;
  }

  public void setAppPkgName(String paramString)
  {
    this.appPkgName = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.AppInfo
 * JD-Core Version:    0.6.0
 */