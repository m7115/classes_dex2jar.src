package com.yipiao.bean;

import cn.suanya.common.a.n;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrderResultMobile extends OrderResult
{
  static SimpleDateFormat MMdd;
  static SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
  static SimpleDateFormat yyyyMMdd;
  private JSONObject data;
  private String orderTimeoutDate;
  private String orderTypeCode = "dc";
  private List<OrderItem> originOrder;
  private List<OrderItem> payOrder;
  private double ticket_total_price_page;

  static
  {
    MMdd = new SimpleDateFormat("MM月dd日");
    yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
  }

  public OrderResultMobile()
  {
    setPayOrder(new ArrayList());
    setOriginOrder(new ArrayList());
  }

  public JSONObject getData()
  {
    return this.data;
  }

  public String getOrderTimeoutDate()
  {
    return this.orderTimeoutDate;
  }

  public String getOrderTypeCode()
  {
    return this.orderTypeCode;
  }

  public List<OrderItem> getOriginOrder()
  {
    return this.originOrder;
  }

  public double getOriginPrice()
  {
    Iterator localIterator = this.originOrder.iterator();
    double d = 0.0D;
    while (localIterator.hasNext())
      d += ((OrderItem)localIterator.next()).getPriceDouble();
    return d;
  }

  public List<OrderItem> getPayOrder()
  {
    return this.payOrder;
  }

  public double getPayPrice()
  {
    Iterator localIterator = this.payOrder.iterator();
    double d = 0.0D;
    while (localIterator.hasNext())
      d += ((OrderItem)localIterator.next()).getPriceDouble();
    return d;
  }

  public double getPrice()
  {
    return this.ticket_total_price_page;
  }

  public void setData(JSONObject paramJSONObject)
  {
    int i = 0;
    setOrderNo(paramJSONObject.optString("sequence_no"));
    String str1 = paramJSONObject.optString("order_date");
    try
    {
      setOrderDate(yyyyMMdd.format(dateformat.parse(str1)));
      this.ticket_total_price_page = paramJSONObject.optDouble("ticket_price_all");
      JSONArray localJSONArray = paramJSONObject.optJSONArray("myTicketList");
      if (i < localJSONArray.length())
      {
        localJSONObject = localJSONArray.optJSONObject(i);
        localOrderItemMobile = new OrderItemMobile();
        localOrderItemMobile.setData(localJSONArray.optJSONObject(i));
        getOrder().add(localOrderItemMobile);
        str2 = localJSONObject.optString("ticket_status_code", "i");
        if (("j".equals(str2)) || ("i".equals(str2)))
        {
          this.payOrder.add(localOrderItemMobile);
          this.orderTimeoutDate = localJSONObject.optString("pay_limit_time");
        }
      }
    }
    catch (Exception localException1)
    {
      while (true)
      {
        OrderItemMobile localOrderItemMobile;
        String str2;
        try
        {
          JSONObject localJSONObject;
          setPayTimeLong(dateformat.parse(localJSONObject.optString("pay_limit_time")).getTime() - System.currentTimeMillis());
          i++;
          continue;
          localException1 = localException1;
          try
          {
            setOrderDate(str1.substring(0, 4) + "-" + str1.substring(4, 6) + "-" + str1.substring(6, 8));
          }
          catch (Exception localException2)
          {
          }
        }
        catch (ParseException localParseException)
        {
          n.b(localParseException);
          continue;
        }
        if (!"e".equals(str2))
          continue;
        this.originOrder.add(localOrderItemMobile);
        this.orderTypeCode = "gc";
      }
      this.data = paramJSONObject;
    }
  }

  public void setOrderTimeoutDate(String paramString)
  {
    this.orderTimeoutDate = paramString;
  }

  public void setOrderTypeCode(String paramString)
  {
    this.orderTypeCode = paramString;
  }

  public void setOriginOrder(List<OrderItem> paramList)
  {
    this.originOrder = paramList;
  }

  public void setPayOrder(List<OrderItem> paramList)
  {
    this.payOrder = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderResultMobile
 * JD-Core Version:    0.6.0
 */