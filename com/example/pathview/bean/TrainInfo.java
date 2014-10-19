package com.example.pathview.bean;

import com.yipiao.bean.TrainStationInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class TrainInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String code;
  private List<TrainStationInfo> trainStations;

  public TrainInfo(String paramString, List<TrainStationInfo> paramList)
  {
    this.code = paramString;
    this.trainStations = paramList;
  }

  public TrainInfo(JSONObject paramJSONObject)
  {
    this.code = paramJSONObject.optString("code");
    this.trainStations = new ArrayList();
    JSONArray localJSONArray = paramJSONObject.optJSONArray("trainStations");
    for (int i = 0; i < localJSONArray.length(); i++)
    {
      JSONObject localJSONObject = localJSONArray.optJSONObject(i);
      this.trainStations.add(new TrainStationInfo(localJSONObject));
    }
  }

  public String getCode()
  {
    return this.code;
  }

  public List<TrainStationInfo> getTrainStations()
  {
    return this.trainStations;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setTrainStations(List<TrainStationInfo> paramList)
  {
    this.trainStations = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.bean.TrainInfo
 * JD-Core Version:    0.6.0
 */