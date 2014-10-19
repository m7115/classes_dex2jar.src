package cn.suanya.hotel.util;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CtripUnPack
{
  public static boolean needPack(Object paramObject)
  {
    boolean bool1 = paramObject instanceof JSONArray;
    int i = 0;
    if (bool1)
    {
      Object localObject = ((JSONArray)paramObject).opt(0);
      i = 0;
      if (localObject != null)
      {
        boolean bool2 = localObject instanceof JSONArray;
        i = 0;
        if (bool2)
          i = 1;
      }
    }
    return i;
  }

  public static Object unPack(Object paramObject)
    throws JSONException
  {
    if ((paramObject instanceof JSONObject))
    {
      localObject1 = (JSONObject)paramObject;
      Iterator localIterator = ((JSONObject)localObject1).keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject2 = ((JSONObject)localObject1).opt(str);
        if (!needPack(localObject2))
          continue;
        ((JSONObject)localObject1).put(str, unPackArray(localObject2));
      }
    }
    Object localObject1 = unPackArray(paramObject);
    return localObject1;
  }

  private static Object unPackArray(Object paramObject)
  {
    if (!needPack(paramObject))
      return paramObject;
    JSONArray localJSONArray1 = (JSONArray)paramObject;
    JSONArray localJSONArray2 = new JSONArray();
    JSONArray localJSONArray3 = (JSONArray)localJSONArray1.opt(0);
    int i = 1;
    while (true)
    {
      JSONObject localJSONObject;
      JSONArray localJSONArray4;
      int j;
      if (i < localJSONArray1.length())
      {
        localJSONObject = new JSONObject();
        localJSONArray4 = localJSONArray1.optJSONArray(i);
        j = 0;
        label63: if (j >= localJSONArray3.length());
      }
      try
      {
        localJSONObject.put(localJSONArray3.optString(j), unPackArray(localJSONArray4.opt(j)));
        label94: j++;
        break label63;
        localJSONArray2.put(localJSONObject);
        i++;
        continue;
        return localJSONArray2;
      }
      catch (JSONException localJSONException)
      {
        break label94;
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.util.CtripUnPack
 * JD-Core Version:    0.6.0
 */