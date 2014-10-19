package com.yipiao;

import cn.suanya.common.a.l;

public class Constants extends l
{
  protected static final String IMAGE_URL = null;
  public static int api_version;
  public static String client_launch;
  public static String conf_downLoad_address;
  public static String conf_update_flag;
  public static String conf_version;
  public static String emei;
  public static boolean isDebug;
  public static String last_version;
  public static String ruleVersionAssest1;
  public static String ruleVersionAssest2;
  public static String ruleVersionAssest3;
  public static String rule_version;
  public static int serviceUrlIndex;
  public static String[] serviceUrls;
  public static int station_db_version;
  public static String url12306 = "dynamic.12306.cn";
  public static String url12306New = "kyfw.12306.cn";
  public static String webViewFlag;

  static
  {
    emei = "emei";
    client_launch = "client_launch";
    conf_update_flag = "configUpdateFlag";
    conf_version = "configVersion";
    rule_version = "ruleVersion";
    last_version = "lastVersion";
    conf_downLoad_address = "downLoadAddress";
    webViewFlag = "webViewFlag";
    station_db_version = 0;
    ruleVersionAssest1 = "rb.sy|rb-pay.sy|ro.sy|rh.sy";
    ruleVersionAssest2 = "rb.sy|rb-pay.sy|rn.sy|rn-resign.sy|rn-register.sy|rn-price.sy";
    ruleVersionAssest3 = "rb.sy|rn-register.sy|rm-ios.sy|rm2-ios.sy|rm-resign-ios.sy";
    api_version = 3;
    isDebug = false;
    serviceUrlIndex = 0;
    serviceUrls = new String[] { "http://suanya.cn/zx", "http://223.4.122.119/zx" };
  }

  public static String getServiceUrl()
  {
    return serviceUrls[serviceUrlIndex];
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.Constants
 * JD-Core Version:    0.6.0
 */