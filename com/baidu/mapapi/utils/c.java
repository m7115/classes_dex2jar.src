package com.baidu.mapapi.utils;

import java.io.File;

public class c
{
  public static boolean a(String paramString)
  {
    File localFile = new File(paramString + "/BaiduMap/vmp/l");
    if (com.baidu.platform.comapi.d.c.n() > 180)
      localFile = new File(paramString + "/BaiduMap/vmp/h");
    if (!localFile.isDirectory())
      return false;
    String[] arrayOfString = localFile.list(new d());
    return (arrayOfString != null) && (arrayOfString.length > 0);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.c
 * JD-Core Version:    0.6.0
 */