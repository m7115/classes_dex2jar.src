package com.yipiao.bean;

import com.yipiao.service.PassengerService;
import java.util.Comparator;
import java.util.List;

public class TrainSort
{
  public static Comparator<Train> defaut;
  public static Comparator<Train> fromTime = new Comparator()
  {
    public int compare(Train paramTrain1, Train paramTrain2)
    {
      int i = paramTrain1.fromTime.compareTo(paramTrain2.fromTime);
      if (i > 0)
        i = 1;
      if (i < 0)
        i = -1;
      return i;
    }
  };
  public static Comparator<Train> seatNum;
  public static Comparator<Train> speed;
  public static Comparator<Train> toTime = new Comparator()
  {
    public int compare(Train paramTrain1, Train paramTrain2)
    {
      int i = paramTrain1.toTime.compareTo(paramTrain2.toTime);
      if (i > 0)
        i = 1;
      if (i < 0)
        i = -1;
      return i;
    }
  };
  public static Comparator<Train> wopu;
  public static Comparator<Train> yingzuo;

  static
  {
    speed = new Comparator()
    {
      public int compare(Train paramTrain1, Train paramTrain2)
      {
        int i = paramTrain1.timeLong.compareTo(paramTrain2.timeLong);
        if (i > 0)
          i = 1;
        if (i < 0)
          i = -1;
        return i;
      }
    };
    seatNum = new Comparator()
    {
      public int compare(Train paramTrain1, Train paramTrain2)
      {
        int i = paramTrain2.getSeatNum() - paramTrain1.getSeatNum();
        if (i > 0)
          i = 1;
        if (i < 0)
          i = -1;
        return i;
      }
    };
    wopu = new Comparator()
    {
      public int compare(Train paramTrain1, Train paramTrain2)
      {
        int i = paramTrain2.getSeatNum("3") - paramTrain1.getSeatNum("3");
        if (i > 0)
          i = 1;
        if (i < 0)
          i = -1;
        return i;
      }
    };
    yingzuo = new Comparator()
    {
      public int compare(Train paramTrain1, Train paramTrain2)
      {
        int i = paramTrain2.getSeatNum("1") - paramTrain1.getSeatNum("1");
        if (i == 0)
          i = paramTrain2.getSeatNum("0") - paramTrain1.getSeatNum("0");
        if (i == 0)
          i = paramTrain2.getSeatTypeNum() - paramTrain1.getSeatTypeNum();
        if (i > 0)
          i = 1;
        if (i < 0)
          i = -1;
        return i;
      }
    };
    defaut = new Comparator()
    {
      public int compare(Train paramTrain1, Train paramTrain2)
      {
        List localList = PassengerService.getInstance().getCurrUsers();
        String[] arrayOfString = { "1", "3", "O" };
        if ((localList != null) && (!localList.isEmpty()))
          arrayOfString = ((UserInfo)localList.get(0)).getLikeSeatTypes();
        int i = paramTrain2.getSeatNum(arrayOfString[0]) - paramTrain1.getSeatNum(arrayOfString[0]);
        if (i == 0)
          i = paramTrain2.getSeatNum() - paramTrain1.getSeatNum();
        if (i == 0)
          i = paramTrain1.fromTime.compareTo(paramTrain2.fromTime);
        if (i > 0)
          i = 1;
        if (i < 0)
          i = -1;
        return i;
      }
    };
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.TrainSort
 * JD-Core Version:    0.6.0
 */