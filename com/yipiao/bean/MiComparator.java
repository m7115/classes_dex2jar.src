package com.yipiao.bean;

import cn.suanya.common.net.LogInfo;
import com.yipiao.service.YipiaoService;
import java.util.Comparator;
import java.util.List;

public class MiComparator
  implements Comparator<Train>
{
  private MonitorInfo mi;

  public MiComparator(MonitorInfo paramMonitorInfo)
  {
    this.mi = paramMonitorInfo;
  }

  public int compare(Train paramTrain1, Train paramTrain2)
  {
    int i = 1;
    boolean bool1;
    boolean bool2;
    label183: 
    do
    {
      while (true)
      {
        int k;
        try
        {
          if ((this.mi.getTrainList() == null) || (this.mi.getTrainList().isEmpty()))
            continue;
          bool1 = this.mi.getTrainList().contains(paramTrain1.getCode());
          bool2 = this.mi.getTrainList().contains(paramTrain2.getCode());
          if ((!bool1) || (bool2))
            break label183;
          return -1;
          int j = paramTrain1.getSeatNum(this.mi.getSeatTypes());
          k = paramTrain2.getSeatNum(this.mi.getSeatTypes()) - j;
          if (k != 0)
            continue;
          k = paramTrain2.getSeatTypeNum() - paramTrain1.getSeatTypeNum();
          if (k != 0)
            continue;
          int m = paramTrain1.fromTime.compareTo(paramTrain2.fromTime);
          k = m;
          if (k > 0)
          {
            if (i < 0)
              break;
            return i;
          }
        }
        catch (Exception localException)
        {
          YipiaoService.getInstance().log(new LogInfo("sortErrorMi", localException));
          return 0;
        }
        i = k;
      }
      return -1;
    }
    while ((bool1) || (!bool2));
    return i;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.MiComparator
 * JD-Core Version:    0.6.0
 */