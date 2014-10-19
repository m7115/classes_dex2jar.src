package com.example.pathview.bean;

import org.json.JSONObject;

public class RecentTrain
{
  private String code;
  private String stationEnd;
  private String stationStart;
  private long time;
  private String timeEnd;
  private String timeStart;
  private int type;

  public RecentTrain(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong, int paramInt)
  {
    this.code = paramString1;
    this.stationStart = paramString2;
    this.timeStart = paramString3;
    this.stationEnd = paramString4;
    this.timeEnd = paramString5;
    this.time = paramLong;
    this.type = paramInt;
  }

  public RecentTrain(JSONObject paramJSONObject)
  {
    this(paramJSONObject.optString("code"), paramJSONObject.optString("stationStart"), paramJSONObject.optString("timeStart"), paramJSONObject.optString("stationEnd"), paramJSONObject.optString("timeEnd"), System.currentTimeMillis(), 0);
  }

  public String getCode()
  {
    return this.code;
  }

  public String getStationEnd()
  {
    return this.stationEnd;
  }

  public String getStationStart()
  {
    return this.stationStart;
  }

  public long getTime()
  {
    return this.time;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTimeStart()
  {
    return this.timeStart;
  }

  public int getType()
  {
    return this.type;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setStationEnd(String paramString)
  {
    this.stationEnd = paramString;
  }

  public void setStationStart(String paramString)
  {
    this.stationStart = paramString;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setTimeEnd(String paramString)
  {
    this.timeEnd = paramString;
  }

  public void setTimeStart(String paramString)
  {
    this.timeStart = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.bean.RecentTrain
 * JD-Core Version:    0.6.0
 */