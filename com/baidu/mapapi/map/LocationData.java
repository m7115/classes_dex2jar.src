package com.baidu.mapapi.map;

import com.baidu.platform.comapi.a.a;
import com.baidu.platform.comapi.basestruct.c;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationData
{
  public float accuracy;
  public float direction;
  public double latitude;
  public double longitude;
  public int satellitesNum;
  public float speed;

  String a()
  {
    JSONObject localJSONObject1 = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    JSONObject localJSONObject2 = new JSONObject();
    c localc = a.a().a((float)this.longitude, (float)this.latitude, "bd09ll");
    if (localc != null);
    try
    {
      localJSONObject1.put("type", 0);
      localJSONObject2.put("ptx", localc.a);
      localJSONObject2.put("pty", localc.b);
      localJSONObject2.put("radius", this.accuracy);
      localJSONObject2.put("direction", this.direction);
      localJSONObject2.put("iconarrownor", "NormalLocArrow");
      localJSONObject2.put("iconarrownorid", 28);
      localJSONObject2.put("iconarrowfoc", "FocusLocArrow");
      localJSONObject2.put("iconarrowfocid", 29);
      localJSONArray.put(localJSONObject2);
      localJSONObject1.put("data", localJSONArray);
      return localJSONObject1.toString();
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.LocationData
 * JD-Core Version:    0.6.0
 */