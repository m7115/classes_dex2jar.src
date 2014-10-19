package com.yipiao.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class TrainNew extends Train
  implements Serializable
{
  private static final long serialVersionUID = 3228796408967905568L;
  private String fromNo;
  private String toNo;
  private JSONObject trainData;
  private JSONObject trainData2;

  private String getString(String paramString)
  {
    return this.trainData2.optString(paramString, "");
  }

  public void createSeatsA()
  {
    this.seats = new ArrayList();
    this.seats.add(new Ticket("9", getString("swz_num")));
    this.seats.add(new Ticket("P", getString("tz_num")));
    this.seats.add(new Ticket("M", getString("zy_num")));
    this.seats.add(new Ticket("O", getString("ze_num")));
    this.seats.add(new Ticket("6", getString("gr_num")));
    this.seats.add(new Ticket("4", getString("rw_num")));
    this.seats.add(new Ticket("3", getString("yw_num")));
    this.seats.add(new Ticket("2", getString("rz_num")));
    this.seats.add(new Ticket("1", getString("yz_num")));
    this.seats.add(new Ticket("0", getString("wz_num")));
  }

  public JSONObject getData()
  {
    return this.trainData;
  }

  public String getFromNo()
  {
    return this.fromNo;
  }

  public String getSeatHtml()
  {
    String str = getStartPayStr();
    if (str != null)
      return str;
    return super.getSeatHtml();
  }

  public String getSeatHtml(TrainPrice paramTrainPrice)
  {
    String str = getStartPayStr();
    if (str != null)
      return str;
    return super.getSeatHtml(paramTrainPrice);
  }

  public String getSecretStr()
  {
    String str1 = this.trainData.optString("secretStr");
    try
    {
      String str2 = URLDecoder.decode(str1, "utf-8");
      return str2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return str1;
  }

  public String getStartPayStr()
  {
    String str = this.trainData.optString("buttonTextInfo");
    if ((str != null) && (str.indexOf("起售") > 0))
      return str.replaceFirst("<br/>", " ");
    return null;
  }

  public String getToNo()
  {
    return this.toNo;
  }

  public JSONObject getTrainData()
  {
    return this.trainData;
  }

  public void setData(JSONObject paramJSONObject)
  {
    this.trainData = paramJSONObject;
  }

  public void setFromNo(String paramString)
  {
    this.fromNo = paramString;
  }

  public void setToNo(String paramString)
  {
    this.toNo = paramString;
  }

  public void setTrainData(JSONObject paramJSONObject)
  {
    this.trainData = paramJSONObject;
    this.trainData2 = paramJSONObject.optJSONObject("queryLeftNewDTO");
    setYpInfo(this.trainData2.optString("yp_info", ""));
    setLocationCode(this.trainData2.optString("location_code", ""));
    this.code3 = getString("train_no");
    if (getString("start_station_telecode").equals(getString("from_station_telecode")))
    {
      this.firstStation = true;
      if (!getString("end_station_telecode").equals(getString("to_station_telecode")))
        break label232;
    }
    label232: for (this.lastStation = true; ; this.lastStation = false)
    {
      this.fromCode = getString("from_station_telecode");
      this.toCode = getString("to_station_telecode");
      this.leaveDate = getString("start_train_date");
      this.timeLong = getString("lishi");
      this.code = getString("station_train_code");
      this.from = getString("from_station_name");
      this.to = getString("to_station_name");
      this.fromTime = getString("start_time");
      this.toTime = getString("arrive_time");
      this.seats = processYpInfo(getYpInfo());
      this.fromNo = getString("from_station_no");
      this.toNo = getString("to_station_no");
      return;
      this.firstStation = false;
      break;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.TrainNew
 * JD-Core Version:    0.6.0
 */