package com.yipiao.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderItem
  implements Serializable
{
  private static final long serialVersionUID = -3271298432304414808L;
  private String batchNo;
  private String buttonStr;
  private boolean canCancel = false;
  private boolean canResign = false;
  private String carOfTrain;
  private String checkBoxStr;
  private String coachNo;
  private String from;
  private String idNo;
  private String idType;
  private String idTypeCode;
  private String leaveTime;
  private String name;
  private String orderDate;
  private String orderNo;
  private String price;
  private String seatNo;
  private String seatNoCode;
  private String seatType;
  private String seatTypeCode;
  private String sequenceNo;
  private String startTrainDatePage;
  private String status;
  private String tickTypeCode;
  private String ticketType;
  private String to;
  private String trainNo;

  public OrderItem()
  {
  }

  public OrderItem(String[] paramArrayOfString)
  {
    this.orderNo = paramArrayOfString[0];
    this.orderDate = paramArrayOfString[1];
    this.trainNo = paramArrayOfString[2];
    this.from = paramArrayOfString[3];
    this.to = paramArrayOfString[4];
    this.carOfTrain = paramArrayOfString[5];
    this.seatNo = paramArrayOfString[6];
    this.seatType = paramArrayOfString[7];
    this.ticketType = paramArrayOfString[8];
    this.price = paramArrayOfString[9];
    this.name = paramArrayOfString[10];
    this.idType = paramArrayOfString[11];
    this.status = paramArrayOfString[12];
  }

  public String getBatchNo()
  {
    return this.batchNo;
  }

  public String getButtonStr()
  {
    return this.buttonStr;
  }

  public String getCarOfTrain()
  {
    return this.carOfTrain;
  }

  public String getCheckBoxStr()
  {
    return this.checkBoxStr;
  }

  public String getCoachNo()
  {
    return this.coachNo;
  }

  public String getFrom()
  {
    return this.from;
  }

  public String getIdNo()
  {
    return this.idNo;
  }

  public String getIdType()
  {
    return this.idType;
  }

  public String getIdTypeCode()
  {
    return this.idTypeCode;
  }

  public String getLeaveTime()
  {
    return this.leaveTime;
  }

  public String getName()
  {
    return this.name;
  }

  public String getOrderDate()
  {
    return this.orderDate;
  }

  public Date getOrderDate2()
  {
    Date localDate1 = new Date();
    try
    {
      localDate1 = new SimpleDateFormat("MM月dd日").parse(this.orderDate);
      if ((localDate1.getMonth() <= 1) && (new Date().getMonth() >= 11))
        localDate1.setYear(1 + new Date().getYear());
    }
    catch (ParseException localParseException1)
    {
      try
      {
        while (true)
        {
          Date localDate2 = new SimpleDateFormat("yyyy年MM月dd日").parse(this.orderDate);
          return localDate2;
          localDate1.setYear(new Date().getYear());
          continue;
          localParseException1 = localParseException1;
        }
      }
      catch (ParseException localParseException2)
      {
      }
    }
    return localDate1;
  }

  public String getOrderNo()
  {
    return this.orderNo;
  }

  public String getPrice()
  {
    return this.price;
  }

  public double getPriceDouble()
  {
    try
    {
      double d = Double.parseDouble(this.price);
      return d;
    }
    catch (Exception localException)
    {
    }
    return 0.0D;
  }

  public String getSeatNo()
  {
    return this.seatNo;
  }

  public String getSeatNoCode()
  {
    return this.seatNoCode;
  }

  public String getSeatType()
  {
    return this.seatType;
  }

  public String getSeatTypeCode()
  {
    return this.seatTypeCode;
  }

  public String getSequenceNo()
  {
    return this.sequenceNo;
  }

  public String getStartTrainDatePage()
  {
    return this.startTrainDatePage;
  }

  public String getStatus()
  {
    return this.status;
  }

  public String getTickTypeCode()
  {
    return this.tickTypeCode;
  }

  public String getTicketType()
  {
    return this.ticketType;
  }

  public String getTo()
  {
    return this.to;
  }

  public String getTrainNo()
  {
    return this.trainNo;
  }

  public boolean isCanCancel()
  {
    return this.canCancel;
  }

  public boolean isCanResign()
  {
    return this.canResign;
  }

  public boolean isEnabel()
  {
    return !"已改签".equals(this.status);
  }

  public String passengerInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name).append(" ");
    localStringBuilder.append(this.ticketType);
    return localStringBuilder.toString();
  }

  public String seatInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.carOfTrain).append("");
    if ("无座".equals(this.seatNo))
      localStringBuilder.append(this.seatNo).append(" ");
    while (true)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(this.seatType).append("").append(this.seatNo).append("");
    }
  }

  public void setBatchNo(String paramString)
  {
    this.batchNo = paramString;
  }

  public void setButtonStr(String paramString)
  {
    this.buttonStr = paramString;
  }

  public void setCanCancel(boolean paramBoolean)
  {
    this.canCancel = paramBoolean;
  }

  public void setCanResign(boolean paramBoolean)
  {
    this.canResign = paramBoolean;
  }

  public void setCarOfTrain(String paramString)
  {
    this.carOfTrain = paramString;
  }

  public void setCheckBoxStr(String paramString)
  {
    this.checkBoxStr = paramString;
  }

  public void setCoachNo(String paramString)
  {
    this.coachNo = paramString;
  }

  public void setFrom(String paramString)
  {
    this.from = paramString;
  }

  public void setIdNo(String paramString)
  {
    this.idNo = paramString;
  }

  public void setIdType(String paramString)
  {
    this.idType = paramString;
  }

  public void setIdTypeCode(String paramString)
  {
    this.idTypeCode = paramString;
  }

  public void setLeaveTime(String paramString)
  {
    this.leaveTime = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setOrderDate(String paramString)
  {
    this.orderDate = paramString;
  }

  public void setOrderNo(String paramString)
  {
    this.orderNo = paramString;
  }

  public void setPrice(String paramString)
  {
    this.price = paramString;
  }

  public void setSeatNo(String paramString)
  {
    this.seatNo = paramString;
  }

  public void setSeatNoCode(String paramString)
  {
    this.seatNoCode = paramString;
  }

  public void setSeatType(String paramString)
  {
    this.seatType = paramString;
  }

  public void setSeatTypeCode(String paramString)
  {
    this.seatTypeCode = paramString;
  }

  public void setSequenceNo(String paramString)
  {
    this.sequenceNo = paramString;
  }

  public void setStartTrainDatePage(String paramString)
  {
    this.startTrainDatePage = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setTickTypeCode(String paramString)
  {
    this.tickTypeCode = paramString;
  }

  public void setTicketType(String paramString)
  {
    this.ticketType = paramString;
  }

  public void setTo(String paramString)
  {
    this.to = paramString;
  }

  public void setTrainNo(String paramString)
  {
    this.trainNo = paramString;
  }

  public String showString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name).append(" ");
    localStringBuilder.append(this.carOfTrain).append(" ");
    if ("无座".equals(this.seatNo))
      localStringBuilder.append(this.seatNo).append(" ");
    while (true)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(this.seatType).append(",").append(this.seatNo).append(" ");
    }
  }

  public String trainInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.trainNo).append(" ");
    localStringBuilder.append(this.from).append("-");
    localStringBuilder.append(this.to).append(" ");
    localStringBuilder.append(this.orderDate).append(" ");
    localStringBuilder.append(this.leaveTime);
    return localStringBuilder.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderItem
 * JD-Core Version:    0.6.0
 */