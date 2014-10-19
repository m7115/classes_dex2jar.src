package com.yipiao.bean;

import com.yipiao.Config;

public class Ticket
{
  private int leftNum;
  private String leftStr;
  private String price;
  private String rate;
  private String seatCode;
  private String seatName;

  public Ticket()
  {
  }

  public Ticket(String paramString1, String paramString2)
  {
    this.seatCode = paramString1;
    Note localNote = Config.getInstance().seatTypesAll.getByCode(paramString1);
    if (localNote == null);
    for (this.seatName = paramString1; ; this.seatName = localNote.getName())
    {
      this.leftStr = paramString2;
      this.leftNum = -1;
      this.leftNum = intParse(paramString2, -1);
      if ((paramString2 == null) || ("-".equals(paramString2)) || ("--".equals(paramString2)))
        this.leftNum = -1;
      if ("无".equals(paramString2))
        this.leftNum = 0;
      if ("有".equals(paramString2))
        this.leftNum = 99;
      return;
    }
  }

  public Ticket(String paramString1, String paramString2, String paramString3)
  {
    this.price = paramString2;
    this.seatName = paramString1;
    this.leftStr = paramString3;
    this.leftNum = -1;
    this.leftNum = intParse(paramString3, -1);
    if ((paramString3 == null) || ("-".equals(paramString3)) || ("--".equals(paramString3)))
      this.leftNum = -1;
    if ("无".equals(paramString3))
      this.leftNum = 0;
    if ("有".equals(paramString3))
      this.leftNum = 99;
  }

  public Ticket(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this(paramString1, paramString3);
    this.price = paramString2;
    this.rate = paramString4;
  }

  public Ticket(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.seatCode = paramString1;
    this.seatName = paramString2;
    this.price = paramString3;
    this.rate = paramString5;
    this.leftStr = paramString4;
    this.leftNum = -1;
    this.leftNum = intParse(paramString4, -1);
    if ((paramString4 == null) || ("-".equals(paramString4)) || ("--".equals(paramString4)))
      this.leftNum = -1;
    if ("无".equals(paramString4))
      this.leftNum = 0;
    if ("有".equals(paramString4))
      this.leftNum = 99;
  }

  private int intParse(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt;
  }

  public int getLeftNum()
  {
    return this.leftNum;
  }

  public String getLeftStr()
  {
    return this.leftStr;
  }

  public String getPrice()
  {
    return this.price;
  }

  public String getRate()
  {
    return this.rate;
  }

  public String getSeatCode()
  {
    return this.seatCode;
  }

  public String getSeatName()
  {
    return this.seatName;
  }

  public boolean hasTicket()
  {
    return (!this.leftStr.contains("无")) && (!this.leftStr.equals("0"));
  }

  public void setLeftNum(int paramInt)
  {
    this.leftNum = paramInt;
  }

  public void setLeftStr(String paramString)
  {
    this.leftStr = paramString;
  }

  public void setPrice(String paramString)
  {
    this.price = paramString;
  }

  public void setRate(String paramString)
  {
    this.rate = paramString;
  }

  public void setSeatCode(String paramString)
  {
    this.seatCode = paramString;
  }

  public void setSeatName(String paramString)
  {
    this.seatName = paramString;
  }

  public String toString()
  {
    String str = this.price;
    if (!hasTicket())
      return "<font color='#999999'>" + this.seatName + "¥" + str + "" + "</font>";
    return "<font color='#444444'>" + this.seatName + "¥" + str + "" + "</font>";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.Ticket
 * JD-Core Version:    0.6.0
 */