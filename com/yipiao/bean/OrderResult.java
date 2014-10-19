package com.yipiao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class OrderResult
{
  protected boolean cancelAble = true;
  protected boolean epay = false;
  protected String from;
  protected String leaveTime;
  protected List<OrderItem> order = new ArrayList();
  protected String orderDate;
  protected String orderNo;
  protected String orderStr;
  protected String payRemark;
  protected long payTimeLong = 0L;
  protected boolean resignN = false;
  protected boolean resignT = false;
  protected String status;
  protected String to;
  protected String token;
  protected String trainNo;

  public OrderResult()
  {
  }

  public OrderResult(String paramString, Vector<String[]> paramVector)
  {
    this();
    this.token = paramString;
    if (paramVector == null);
    do
    {
      do
      {
        return;
        Iterator localIterator = paramVector.iterator();
        while (localIterator.hasNext())
        {
          String[] arrayOfString = (String[])localIterator.next();
          this.order.add(new OrderItem(arrayOfString));
        }
        setOrderNo("");
        this.status = "";
      }
      while (this.order.isEmpty());
      if (((OrderItem)this.order.get(0)).getOrderNo().length() > 10)
        setOrderNo(((OrderItem)this.order.get(0)).getOrderNo().substring(0, 10));
      this.status = ((OrderItem)this.order.get(0)).getStatus();
    }
    while (this.status.indexOf("showTime_dc") <= 0);
    this.status = "排队中";
  }

  public List<OrderItem> canCancelOrder()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.order.iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem = (OrderItem)localIterator.next();
      if (!localOrderItem.isCanCancel())
        continue;
      localArrayList.add(localOrderItem);
    }
    return localArrayList;
  }

  public ArrayList<OrderItem> canResignOrder()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.order.iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem = (OrderItem)localIterator.next();
      if (!localOrderItem.isCanResign())
        continue;
      localArrayList.add(localOrderItem);
    }
    return localArrayList;
  }

  public int getCanCancel()
  {
    Iterator localIterator = this.order.iterator();
    int i = 0;
    if (localIterator.hasNext())
      if (!((OrderItem)localIterator.next()).isCanCancel())
        break label47;
    label47: for (int j = i + 1; ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  public int getCanResign()
  {
    Iterator localIterator = this.order.iterator();
    int i = 0;
    if (localIterator.hasNext())
      if (!((OrderItem)localIterator.next()).isCanResign())
        break label47;
    label47: for (int j = i + 1; ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  public OrderItem getFirstItem()
  {
    if (!getPayOrder().isEmpty())
      return (OrderItem)getPayOrder().get(0);
    if (this.order.isEmpty())
      return new OrderItem();
    return (OrderItem)this.order.get(0);
  }

  public String getFrom()
  {
    if (this.from == null)
      this.from = getFirstItem().getFrom();
    return this.from;
  }

  public String getLeaveDate()
  {
    return getFirstItem().getOrderDate();
  }

  public String getLeaveTime()
  {
    if (this.leaveTime == null)
      this.leaveTime = getFirstItem().getLeaveTime();
    return this.leaveTime;
  }

  public List<OrderItem> getOrder()
  {
    return this.order;
  }

  public String getOrderDate()
  {
    if (this.orderDate == null)
      this.orderDate = getFirstItem().getOrderDate();
    return this.orderDate;
  }

  public Date getOrderDate2()
  {
    return getFirstItem().getOrderDate2();
  }

  public String getOrderNo()
  {
    if ((this.orderNo != null) && (this.orderNo.length() > 0))
      return this.orderNo;
    if ((!this.order.isEmpty()) && (this.order.get(0) != null) && (((OrderItem)this.order.get(0)).getOrderNo() != null) && (((OrderItem)this.order.get(0)).getOrderNo().length() > 10))
      return ((OrderItem)this.order.get(0)).getOrderNo().substring(0, 10);
    return "";
  }

  public String getOrderStr()
  {
    return this.orderStr;
  }

  public List<OrderItem> getPayOrder()
  {
    return this.order;
  }

  public String getPayRemark()
  {
    return this.payRemark;
  }

  public long getPayTimeLong()
  {
    return this.payTimeLong;
  }

  public double getPrice()
  {
    Iterator localIterator = this.order.iterator();
    double d1 = 0.0D;
    while (true)
      if (localIterator.hasNext())
      {
        OrderItem localOrderItem = (OrderItem)localIterator.next();
        try
        {
          double d3 = Double.parseDouble(localOrderItem.getPrice());
          d2 = d1 + d3;
          d1 = d2;
        }
        catch (Exception localException)
        {
          while (true)
            double d2 = d1;
        }
      }
    return d1;
  }

  public String getSeatType()
  {
    return getFirstItem().getSeatType();
  }

  public String getStatus()
  {
    String str;
    if (this.status != null)
      str = this.status;
    while (true)
    {
      return str;
      if (this.order.isEmpty())
        break;
      str = ((OrderItem)this.order.get(0)).getStatus();
      if (str.indexOf("showTime_dc") > 0)
        return "排队中";
    }
    return "";
  }

  public String getTo()
  {
    if (this.to == null)
      this.to = getFirstItem().getTo();
    return this.to;
  }

  public String getToken()
  {
    return this.token;
  }

  public String getTrainNo()
  {
    if (this.trainNo == null)
      this.trainNo = getFirstItem().getTrainNo();
    return this.trainNo;
  }

  public boolean isCancelAble()
  {
    return this.cancelAble;
  }

  public boolean isEpay()
  {
    return this.epay;
  }

  public boolean isResign()
  {
    return (this.resignN) || (this.resignT) || ((this.payRemark != null) && (this.payRemark.length() > 0));
  }

  public boolean isResignN()
  {
    return this.resignN;
  }

  public boolean isResignT()
  {
    return this.resignT;
  }

  public boolean isvalid()
  {
    return (getOrderNo() != null) && (getOrderNo().length() > 1);
  }

  public void setCancelAble(boolean paramBoolean)
  {
    this.cancelAble = paramBoolean;
  }

  public void setEpay(boolean paramBoolean)
  {
    this.epay = paramBoolean;
  }

  public void setFrom(String paramString)
  {
    this.from = paramString;
  }

  public void setLeaveTime(String paramString)
  {
    this.leaveTime = paramString;
  }

  public void setOrder(List<OrderItem> paramList)
  {
    this.order = paramList;
  }

  public void setOrderDate(String paramString)
  {
    this.orderDate = paramString;
  }

  public void setOrderNo(String paramString)
  {
    this.orderNo = paramString;
  }

  public void setOrderStr(String paramString)
  {
    this.orderStr = paramString;
  }

  public void setPayRemark(String paramString)
  {
    this.payRemark = paramString;
  }

  public void setPayTimeLong(long paramLong)
  {
    this.payTimeLong = paramLong;
  }

  public void setResignN(boolean paramBoolean)
  {
    this.resignN = paramBoolean;
  }

  public void setResignT(boolean paramBoolean)
  {
    this.resignT = paramBoolean;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setTo(String paramString)
  {
    this.to = paramString;
  }

  public void setToken(String paramString)
  {
    this.token = paramString;
  }

  public void setTrainNo(String paramString)
  {
    this.trainNo = paramString;
  }

  public String showString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.order.isEmpty())
      return "目前没有订单！";
    OrderItem localOrderItem = (OrderItem)this.order.get(0);
    localStringBuilder.append("<b><big><font color=\"#0099FA\">").append(localOrderItem.getTrainNo()).append("</font></big>").append(" ").append(localOrderItem.getFrom() + "-" + localOrderItem.getTo()).append(" ").append(localOrderItem.getOrderDate()).append("</b>").append("<br>").append("订单状态：").append(getStatus());
    return localStringBuilder.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderResult
 * JD-Core Version:    0.6.0
 */