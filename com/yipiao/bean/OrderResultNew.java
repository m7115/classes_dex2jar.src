package com.yipiao.bean;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderResultNew extends OrderResult
{
  private JSONObject data;
  private double ticket_total_price_page;

  public JSONObject getData()
  {
    return this.data;
  }

  public double getPrice()
  {
    return this.ticket_total_price_page;
  }

  public void setData(JSONObject paramJSONObject)
  {
    int i = 0;
    setOrderNo(paramJSONObject.optString("sequence_no"));
    String str = paramJSONObject.optString("order_date");
    try
    {
      setOrderDate(str.split(" ")[0]);
      this.ticket_total_price_page = paramJSONObject.optDouble("ticket_total_price_page");
      setOrder(new ArrayList());
      JSONArray localJSONArray = paramJSONObject.optJSONArray("tickets");
      while (i < localJSONArray.length())
      {
        OrderItemNew localOrderItemNew = new OrderItemNew();
        localOrderItemNew.setData(localJSONArray.optJSONObject(i));
        getOrder().add(localOrderItemNew);
        i++;
      }
    }
    catch (Exception localException)
    {
      while (true)
        setOrderDate(str);
      this.data = paramJSONObject;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderResultNew
 * JD-Core Version:    0.6.0
 */