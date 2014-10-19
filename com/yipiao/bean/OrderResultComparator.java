package com.yipiao.bean;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class OrderResultComparator
  implements Comparator<OrderResult>
{
  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

  public int compare(OrderResult paramOrderResult1, OrderResult paramOrderResult2)
  {
    try
    {
      long l1 = this.sf.parse(paramOrderResult1.getOrderDate()).getTime();
      long l2 = this.sf.parse(paramOrderResult2.getOrderDate()).getTime();
      int i;
      if (l1 < l2)
        i = 1;
      boolean bool;
      do
      {
        return i;
        bool = l1 < l2;
        i = 0;
      }
      while (!bool);
      return -1;
    }
    catch (Exception localException)
    {
    }
    return 0;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderResultComparator
 * JD-Core Version:    0.6.0
 */