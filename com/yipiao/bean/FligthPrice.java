package com.yipiao.bean;

public class FligthPrice
  implements Comparable<FligthPrice>
{
  private String date;
  private String price;
  private String rate;

  public int compareTo(FligthPrice paramFligthPrice)
  {
    String str = getDate();
    if (str == null)
      return 0;
    return str.compareTo(paramFligthPrice.getDate());
  }

  public String getDate()
  {
    return this.date;
  }

  public String getPrice()
  {
    return this.price;
  }

  public String getRate()
  {
    return this.rate;
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setPrice(String paramString)
  {
    this.price = paramString;
  }

  public void setRate(String paramString)
  {
    this.rate = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.FligthPrice
 * JD-Core Version:    0.6.0
 */