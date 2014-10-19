package com.yipiao.bean;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrainPrice
{
  static DecimalFormat df_price;
  static Map<String, String> qlrSeatNameMap = new HashMap();
  Map<String, Map<String, Double>> trains = new HashMap();

  static
  {
    qlrSeatNameMap.put("软卧下", "软卧");
    qlrSeatNameMap.put("硬卧下", "硬卧");
    qlrSeatNameMap.put("硬座", "硬座");
    qlrSeatNameMap.put("二等软座", "二等座");
    qlrSeatNameMap.put("一等软座", "一等座");
    df_price = new DecimalFormat("0.#");
  }

  public Double getPrice(String paramString1, String paramString2)
  {
    Map localMap = (Map)this.trains.get(paramString1);
    Double localDouble;
    if (localMap == null)
      localDouble = null;
    do
    {
      do
      {
        return localDouble;
        localDouble = (Double)localMap.get(paramString2);
      }
      while ((localDouble != null) || (!"无座".equals(paramString2)));
      localDouble = (Double)localMap.get("硬座");
    }
    while (localDouble != null);
    return (Double)localMap.get("二等座");
  }

  public String getPriceStr(String paramString1, String paramString2)
  {
    Double localDouble = getPrice(paramString1, paramString2);
    if (localDouble == null)
      return "无票价";
    return df_price.format(localDouble);
  }

  public Map<String, Double> getTrainPrice(String paramString)
  {
    return (Map)this.trains.get(paramString);
  }

  public void initWithNew(Train paramTrain, JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    String str1 = paramTrain.getSeatTypeString();
    int i = 0;
    if (i < str1.length())
    {
      char c = str1.charAt(i);
      String str2;
      if (Character.isDigit(c))
        if (c == '0')
          str2 = "WZ";
      while (true)
      {
        String str3 = com.yipiao.Config.getInstance().seatName[((java.lang.Integer)com.yipiao.Config.getInstance().seatCodeIndex.get(String.valueOf(c))).intValue()];
        String str4 = paramJSONObject.optString(str2);
        if ((str4 != null) && (!str4.equalsIgnoreCase("")))
          localHashMap.put(str3, Double.valueOf(str4.substring(1)));
        i++;
        break;
        str2 = "A" + c;
        continue;
        str2 = "" + c;
      }
    }
    this.trains.put(paramTrain.getCode(), localHashMap);
  }

  public void initWithQlr(JSONObject paramJSONObject)
    throws JSONException
  {
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      JSONArray localJSONArray = paramJSONObject.optJSONArray(str1);
      HashMap localHashMap = new HashMap();
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        String str2 = localJSONObject.optString("type");
        Double localDouble = Double.valueOf(localJSONObject.optDouble("pr"));
        String str3 = (String)qlrSeatNameMap.get(str2);
        if (str3 == null)
          continue;
        localHashMap.put(str3, localDouble);
      }
      String[] arrayOfString = str1.split("/");
      for (int j = 0; j < arrayOfString.length; j++)
        this.trains.put(arrayOfString[j], localHashMap);
    }
  }

  public void initWithQmy(List<Map<String, Object>> paramList)
  {
    if (paramList == null);
    while (true)
    {
      return;
      Iterator localIterator1 = paramList.iterator();
      while (localIterator1.hasNext())
      {
        Map localMap1 = (Map)localIterator1.next();
        String str = (String)localMap1.get("code");
        HashMap localHashMap = new HashMap();
        Iterator localIterator2 = ((List)localMap1.get("prices")).iterator();
        while (localIterator2.hasNext())
        {
          Map localMap2 = (Map)localIterator2.next();
          localHashMap.put((String)localMap2.get("type"), Double.valueOf((String)localMap2.get("price")));
        }
        this.trains.put(str, localHashMap);
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.TrainPrice
 * JD-Core Version:    0.6.0
 */