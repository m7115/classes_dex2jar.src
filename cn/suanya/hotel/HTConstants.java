package cn.suanya.hotel;

import cn.suanya.common.a.l;
import cn.suanya.common.a.q;
import cn.suanya.common.a.r;

public class HTConstants extends l
{
  public static String client_id;
  public static String client_launch;
  public static String conf_downLoad_address;
  public static String conf_update_flag;
  public static String conf_version;
  public static String emei = "emei";
  public static String kSearchCheckInDate;
  public static String kSearchCheckOutDate;
  public static String kSearchCityId;
  public static String kSearchCityName;
  public static String kSearchDistrict;
  public static String kSearchIndex;
  public static String kSearchKeywords;
  public static String kSearchLongitude;
  public static String kSearchRadius;
  public static String last_version;
  public static String launch_finish;
  public static q priceLevel;
  public static q province;
  public static String ruleVersionAssest;
  public static String rule_version;
  public static String[] serviceUrls;
  public static q star;
  public static q starShow;

  static
  {
    client_id = "clientId";
    client_launch = "client_launch";
    conf_update_flag = "configUpdateFlag";
    conf_version = "configVersion";
    rule_version = "ruleVersion";
    last_version = "lastVersion";
    conf_downLoad_address = "downLoadAddress";
    launch_finish = "launchFinish";
    serviceUrls = new String[] { "http://hotel.suanya.cn/hotelapp", "http://121.199.60.56/hotelapp" };
    kSearchLongitude = "longitude";
    kSearchIndex = "index";
    kSearchRadius = "radius";
    kSearchCheckInDate = "checkInDate";
    kSearchCheckOutDate = "checkOutDate";
    kSearchCityName = "cityName";
    kSearchCityId = "cityId";
    kSearchDistrict = "district";
    kSearchKeywords = "keywords";
    ruleVersionAssest = "rh.sy";
    star = new q();
    star.add(new r("0", "不限"));
    star.add(new r("1", "经济型"));
    star.add(new r("3", "三星"));
    star.add(new r("4", "四星"));
    star.add(new r("5", "五星"));
    starShow = new q();
    starShow.add(new r("1", "经济型"));
    starShow.add(new r("2", "两星级"));
    starShow.add(new r("3", "三星级"));
    starShow.add(new r("4", "四星级"));
    starShow.add(new r("5", "五星级"));
    priceLevel = new q();
    priceLevel.add(new r("<", "不限"));
    priceLevel.add(new r("<200", "200元以下"));
    priceLevel.add(new r("200<300", "200-300元"));
    priceLevel.add(new r("300<500", "300-500元"));
    priceLevel.add(new r("500<800", "500-800元"));
    priceLevel.add(new r("800<", "800元以上"));
    province = new q();
    province.add(new r("11", "北京"));
    province.add(new r("12", "天津"));
    province.add(new r("13", "河北"));
    province.add(new r("14", "山西"));
    province.add(new r("15", "内蒙古"));
    province.add(new r("21", "辽宁"));
    province.add(new r("22", "吉林"));
    province.add(new r("23", "黑龙江"));
    province.add(new r("31", "上海"));
    province.add(new r("32", "江苏"));
    province.add(new r("33", "浙江"));
    province.add(new r("34", "安徽"));
    province.add(new r("35", "福建"));
    province.add(new r("36", "江西"));
    province.add(new r("37", "山东"));
    province.add(new r("41", "河南"));
    province.add(new r("42", "湖北"));
    province.add(new r("43", "湖南"));
    province.add(new r("44", "广东"));
    province.add(new r("45", "广西"));
    province.add(new r("46", "海南"));
    province.add(new r("50", "重庆"));
    province.add(new r("51", "四川"));
    province.add(new r("52", "贵州"));
    province.add(new r("53", "云南"));
    province.add(new r("54", "西藏"));
    province.add(new r("61", "陕西"));
    province.add(new r("62", "甘肃"));
    province.add(new r("63", "青海"));
    province.add(new r("64", "宁夏"));
    province.add(new r("65", "新疆"));
    province.add(new r("71", "台湾"));
    province.add(new r("81", "香港"));
    province.add(new r("82", "澳门"));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.HTConstants
 * JD-Core Version:    0.6.0
 */