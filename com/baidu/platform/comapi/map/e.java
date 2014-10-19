package com.baidu.platform.comapi.map;

import android.graphics.Point;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comjni.map.basemap.a;
import org.json.JSONException;
import org.json.JSONObject;

class e
  implements Projection
{
  private o a = null;

  public e(o paramo)
  {
    this.a = paramo;
  }

  public GeoPoint fromPixels(int paramInt1, int paramInt2)
  {
    String str = this.a.b().a(paramInt1, paramInt2);
    GeoPoint localGeoPoint = new GeoPoint(0, 0);
    if (str != null)
      try
      {
        JSONObject localJSONObject = new JSONObject(str);
        localGeoPoint.setLongitudeE6(localJSONObject.getInt("geox"));
        localGeoPoint.setLatitudeE6(localJSONObject.getInt("geoy"));
        return localGeoPoint;
      }
      catch (JSONException localJSONException)
      {
      }
    return null;
  }

  public float metersToEquatorPixels(float paramFloat)
  {
    return (float)(paramFloat / this.a.c());
  }

  public Point toPixels(GeoPoint paramGeoPoint, Point paramPoint)
  {
    if (paramPoint == null)
      paramPoint = new Point(0, 0);
    a locala = this.a.b();
    if (locala == null);
    String str;
    do
    {
      return paramPoint;
      str = locala.b(paramGeoPoint.getLongitudeE6(), paramGeoPoint.getLatitudeE6());
    }
    while (str == null);
    try
    {
      JSONObject localJSONObject = new JSONObject(str);
      paramPoint.x = localJSONObject.getInt("scrx");
      paramPoint.y = localJSONObject.getInt("scry");
      return paramPoint;
    }
    catch (JSONException localJSONException)
    {
    }
    return paramPoint;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.e
 * JD-Core Version:    0.6.0
 */