package com.yipiao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrainOld extends Train
  implements Serializable
{
  private static final long serialVersionUID = 3228796408967905568L;
  String[] seatCodeIndex = { "9", "P", "M", "O", "6", "4", "3", "2", "1", "0" };

  public void setSeat(String[] paramArrayOfString)
  {
    this.seats = new ArrayList();
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      if (i >= this.seatCodeIndex.length)
        continue;
      this.seats.add(new Ticket(this.seatCodeIndex[i], paramArrayOfString[i]));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.TrainOld
 * JD-Core Version:    0.6.0
 */