package com.baidu.mapapi.utils;

import java.io.File;
import java.io.FilenameFilter;

final class d
  implements FilenameFilter
{
  public boolean accept(File paramFile, String paramString)
  {
    return (paramString.endsWith(".dat")) || (paramString.endsWith(".dat_svc")) || (paramString.endsWith(".dat_seg")) || (paramString.equalsIgnoreCase("DVWifilog.cfg")) || (paramString.equalsIgnoreCase("DVUserdat.cfg"));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.d
 * JD-Core Version:    0.6.0
 */