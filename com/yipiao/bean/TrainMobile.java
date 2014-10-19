package com.yipiao.bean;

import cn.suanya.hc.service.PathService;
import java.io.Serializable;
import org.json.JSONObject;

public class TrainMobile extends Train
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String endStationCode;
  private String startStationCode;
  private String ypEx;

  public String getEndStationCode()
  {
    return this.endStationCode;
  }

  public String getFrom()
  {
    if ((this.from == null) || (this.from.equals("")))
    {
      StationNode localStationNode = PathService.getInstance().getStationInfoByCode(this.fromCode);
      if (localStationNode != null)
        this.from = localStationNode.getName();
    }
    return this.from;
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

  public String getStartPayStr()
  {
    String str = getData().optString("message");
    if ((str != null) && (str.indexOf("起售") > 0))
      return str;
    return null;
  }

  public String getStartStationCode()
  {
    return this.startStationCode;
  }

  public String getTo()
  {
    if ((this.to == null) || (this.to.equals("")))
    {
      StationNode localStationNode = PathService.getInstance().getStationInfoByCode(this.toCode);
      if (localStationNode != null)
        this.to = localStationNode.getName();
    }
    return this.to;
  }

  public String getYpEx()
  {
    return this.ypEx;
  }

  public void setEndStationCode(String paramString)
  {
    this.endStationCode = paramString;
  }

  public void setStartStationCode(String paramString)
  {
    this.startStationCode = paramString;
  }

  public void setYpEx(String paramString)
  {
    this.ypEx = paramString;
  }

  public void setYpInfo(String paramString)
  {
    super.setYpInfo(paramString);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.TrainMobile
 * JD-Core Version:    0.6.0
 */