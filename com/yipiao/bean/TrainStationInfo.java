package com.yipiao.bean;

import cn.suanya.hc.service.PathService;
import com.example.pathview.util.TimeUtil;
import java.io.Serializable;
import org.json.JSONObject;

public class TrainStationInfo
  implements Serializable
{
  private static final long serialVersionUID = 2L;
  private String arrTime;
  private Integer dayNum;
  private String leaveTime;
  private String name;
  private StationNode station;
  private String zwd;

  public TrainStationInfo()
  {
  }

  public TrainStationInfo(String paramString1, StationNode paramStationNode, String paramString2, String paramString3)
  {
    this(paramString1, paramStationNode, paramString2, paramString3, Integer.valueOf(1));
  }

  public TrainStationInfo(String paramString1, StationNode paramStationNode, String paramString2, String paramString3, Integer paramInteger)
  {
    this.name = paramString1;
    this.station = paramStationNode;
    this.leaveTime = paramString2;
    this.arrTime = paramString3;
    this.dayNum = paramInteger;
  }

  public TrainStationInfo(JSONObject paramJSONObject)
  {
    this(paramJSONObject.optString("name"), null, paramJSONObject.optString("leaveTime"), paramJSONObject.optString("arrTime"), Integer.valueOf(paramJSONObject.optInt("dayNum")));
  }

  public String getArrTime()
  {
    return this.arrTime;
  }

  public Integer getDayNum()
  {
    return this.dayNum;
  }

  public String getLeaveTime()
  {
    return this.leaveTime;
  }

  public String getName()
  {
    return this.name;
  }

  public String getRealArrTime()
  {
    if ((this.zwd == null) || (this.zwd.length() < 1))
      return this.arrTime;
    return this.zwd;
  }

  public String getRealLeaveTime()
  {
    if ((this.zwd == null) || (this.zwd.length() < 1))
      return this.leaveTime;
    return TimeUtil.addMins(this.zwd, getStopMins());
  }

  public StationNode getStation()
  {
    if (this.station == null)
    {
      StationNode localStationNode = PathService.getInstance().getStationInfoByName(this.name);
      this.station = localStationNode;
      if (localStationNode != null)
        return this.station;
      return StationNode.defaut();
    }
    return this.station;
  }

  public int getStopMins()
  {
    int i = TimeUtil.getMinsByStr(this.arrTime);
    int j = TimeUtil.getMinsByStr(this.leaveTime);
    if ((i < 0) || (j < 0))
      return 0;
    return TimeUtil.getTimeDef(i, j);
  }

  public String getZwd()
  {
    return this.zwd;
  }

  public void setArrTime(String paramString)
  {
    this.arrTime = paramString;
  }

  public void setDayNum(Integer paramInteger)
  {
    this.dayNum = paramInteger;
  }

  public void setLeaveTime(String paramString)
  {
    this.leaveTime = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setStation(StationNode paramStationNode)
  {
    this.station = paramStationNode;
  }

  public void setZwd(String paramString)
  {
    this.zwd = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.TrainStationInfo
 * JD-Core Version:    0.6.0
 */