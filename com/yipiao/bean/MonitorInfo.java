package com.yipiao.bean;

import android.text.Html;
import cn.suanya.common.a.v;
import com.yipiao.Config;
import com.yipiao.service.PassengerService;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MonitorInfo
  implements Serializable, Cloneable
{
  public static final int MonitorStatusBookSuccess = 6;
  public static final int MonitorStatusBooking = 5;
  public static final int MonitorStatusFindTicket = 4;
  public static final int MonitorStatusOutTime = 0;
  public static final int MonitorStatusRuning = 3;
  public static final int MonitorStatusStop = 1;
  public static final int MonitorStatusWait = 2;
  public static int _id = 0;
  static SimpleDateFormat hmFormat = new SimpleDateFormat("HH:mm");
  private static final long serialVersionUID = 8024527540937668148L;
  private boolean autoBook = true;
  private ChainQuery cq;
  private int hcApi;
  private int id;
  private boolean lightNotify = true;
  private List<String> monitorLog;
  private long nextRunTime = 0L;
  public long notifyTime = 0L;
  private ArrayList<OrderItem> orders;
  private List<UserInfo> passengers;
  public int queryTimes = 0;
  private boolean resign = false;
  private NoteList seatTypes = new NoteList();
  private boolean shakeNotify = true;
  private Integer speedMultiple = Integer.valueOf(200);
  private Integer speedMultiple2 = Integer.valueOf(50);
  private int status = 1;
  private List<String> trainList;
  private boolean voiceNotify = true;

  static
  {
    _id = 100;
  }

  public MonitorInfo()
  {
    this.seatTypes.add(Config.getInstance().seatTypes.getByCode("1"));
    this.monitorLog = new ArrayList();
    int i = _id;
    _id = i + 1;
    this.id = i;
    this.notifyTime = 0L;
    this.speedMultiple = Integer.valueOf(200);
    this.speedMultiple2 = Integer.valueOf(50);
    this.queryTimes = 0;
  }

  public MonitorInfo(ChainQuery paramChainQuery)
  {
    this();
    this.cq = paramChainQuery;
  }

  public String[] allTrain()
  {
    if (this.cq.findResults() == null)
      return new String[0];
    String[] arrayOfString = new String[this.cq.findResults().size()];
    Collections.sort(this.cq.findResults(), TrainSort.fromTime);
    Iterator localIterator = this.cq.findResults().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
      arrayOfString[i] = ((Train)localIterator.next()).getCode();
    return arrayOfString;
  }

  public CharSequence[] allTrainLabel()
  {
    if (this.cq.findResults() == null)
      return new String[0];
    CharSequence[] arrayOfCharSequence = new CharSequence[this.cq.findResults().size()];
    Collections.sort(this.cq.findResults(), TrainSort.fromTime);
    Iterator localIterator = this.cq.findResults().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
    {
      Train localTrain = (Train)localIterator.next();
      arrayOfCharSequence[i] = Html.fromHtml(localTrain.getCode() + "<font size='-1' color='#999999'>(" + localTrain.getFromTime() + "-" + localTrain.getToTime() + ")</font>");
    }
    return arrayOfCharSequence;
  }

  public long calNextRunTime(float paramFloat)
  {
    float f1 = 1.0F;
    long l1 = System.currentTimeMillis();
    float f2 = 100.0F * paramFloat / getSpleed();
    if (f2 < f1);
    while (true)
    {
      long l2 = l1 + (int)f1;
      long l3 = 300000L + this.notifyTime;
      if (l3 > l2);
      while (true)
      {
        this.nextRunTime = l3;
        return this.nextRunTime;
        l3 = l2;
      }
      f1 = f2;
    }
  }

  public MonitorInfo clone()
    throws CloneNotSupportedException
  {
    MonitorInfo localMonitorInfo = (MonitorInfo)super.clone();
    localMonitorInfo.cq = localMonitorInfo.cq.clone();
    localMonitorInfo.seatTypes = new NoteList(localMonitorInfo.seatTypes);
    if (this.trainList == null)
    {
      localMonitorInfo.trainList = null;
      if (this.monitorLog != null)
        break label102;
      localMonitorInfo.monitorLog = null;
      label58: if (this.passengers != null)
        break label120;
    }
    label102: label120: for (localMonitorInfo.passengers = null; ; localMonitorInfo.passengers = new ArrayList(this.passengers))
    {
      if (this.orders != null)
        break label138;
      localMonitorInfo.orders = null;
      return localMonitorInfo;
      localMonitorInfo.trainList = new ArrayList(this.trainList);
      break;
      localMonitorInfo.monitorLog = new ArrayList(this.monitorLog);
      break label58;
    }
    label138: localMonitorInfo.orders = new ArrayList(this.orders);
    return localMonitorInfo;
  }

  public ChainQuery getCq()
  {
    return this.cq;
  }

  public int getHcApi()
  {
    return this.hcApi;
  }

  public int getId()
  {
    return this.id;
  }

  public String getLastLog()
  {
    if (!this.monitorLog.isEmpty())
      return (String)this.monitorLog.get(0);
    return "监控未开始！";
  }

  public List<Train> getLastResult()
  {
    return this.cq.findResults();
  }

  public List<String> getMonitorLog()
  {
    return this.monitorLog;
  }

  public long getNextRunTime()
  {
    return this.nextRunTime;
  }

  public long getNotifyTime()
  {
    return this.notifyTime;
  }

  public ArrayList<OrderItem> getOrders()
  {
    return this.orders;
  }

  public List<UserInfo> getPassengers()
  {
    if (this.passengers == null)
    {
      this.passengers = new ArrayList();
      List localList = PassengerService.getInstance().getCurrUsers();
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          UserInfo localUserInfo = (UserInfo)localIterator.next();
          this.passengers.add(localUserInfo.clone());
        }
      }
    }
    return this.passengers;
  }

  public int getQueryTimes()
  {
    return this.queryTimes;
  }

  public Integer getSeatNum()
  {
    if (isResign())
    {
      if ((getOrders() != null) && (!getOrders().isEmpty()))
        return Integer.valueOf(getOrders().size());
    }
    else if ((getPassengers() != null) && (!getPassengers().isEmpty()))
      return Integer.valueOf(getPassengers().size());
    return Integer.valueOf(1);
  }

  public NoteList getSeatTypes()
  {
    return this.seatTypes;
  }

  public Integer getSpeedMultiple()
  {
    return this.speedMultiple;
  }

  public Integer getSpeedMultiple2()
  {
    return this.speedMultiple2;
  }

  public int getSpleed()
  {
    if (v.a() == 1)
      return this.speedMultiple.intValue();
    return this.speedMultiple2.intValue();
  }

  public int getStatus()
  {
    if (isOutTime())
      return 0;
    return this.status;
  }

  public List<String> getTrainList()
  {
    return this.trainList;
  }

  public boolean isAutoBook()
  {
    return this.autoBook;
  }

  public boolean isLightNotify()
  {
    return this.lightNotify;
  }

  public boolean isMonitorTrain(String paramString)
  {
    if (this.trainList == null)
      return true;
    return this.trainList.contains(paramString);
  }

  public boolean isOutTime()
  {
    return System.currentTimeMillis() - this.cq.getLeavedate2().getTime() > 86400000L;
  }

  public boolean isResign()
  {
    return this.resign;
  }

  public boolean isRuning()
  {
    return (this.status != 1) && (this.status != 0) && (this.status != 6) && (!isOutTime());
  }

  public boolean isShakeNotify()
  {
    return this.shakeNotify;
  }

  public boolean isVoiceNotify()
  {
    return this.voiceNotify;
  }

  public void putLog(String paramString)
  {
    String str = hmFormat.format(new Date()) + " " + paramString;
    this.monitorLog.add(0, str);
    if (this.monitorLog.size() > 4)
      this.monitorLog.remove(4);
  }

  public String[] selectTrain()
  {
    if (this.trainList == null)
      return new String[0];
    String[] arrayOfString = new String[this.trainList.size()];
    this.trainList.toArray(arrayOfString);
    return arrayOfString;
  }

  public String selectTrainLink()
  {
    if ((this.trainList == null) || (this.trainList.isEmpty()))
      return "所有列车";
    Iterator localIterator = this.trainList.iterator();
    String str2;
    for (String str1 = ""; localIterator.hasNext(); str1 = str1 + "," + str2)
      str2 = (String)localIterator.next();
    return str1.substring(1);
  }

  public void setAutoBook(boolean paramBoolean)
  {
    this.autoBook = paramBoolean;
  }

  public void setCq(ChainQuery paramChainQuery)
  {
    this.cq = paramChainQuery;
  }

  public void setHcApi(int paramInt)
  {
    this.hcApi = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setLastResult(List<Train> paramList)
  {
    this.cq.setResults(paramList);
  }

  public void setLightNotify(boolean paramBoolean)
  {
    this.lightNotify = paramBoolean;
  }

  public void setMonitorLog(List<String> paramList)
  {
    this.monitorLog = paramList;
  }

  public void setNextRunTime(long paramLong)
  {
    this.nextRunTime = paramLong;
  }

  public void setNotifyTime(long paramLong)
  {
    this.notifyTime = paramLong;
  }

  public void setOrders(ArrayList<OrderItem> paramArrayList)
  {
    this.orders = paramArrayList;
  }

  public void setPassengers(List<UserInfo> paramList)
  {
    this.passengers = paramList;
  }

  public void setQueryTimes(int paramInt)
  {
    this.queryTimes = paramInt;
  }

  public void setResign(boolean paramBoolean)
  {
    this.resign = paramBoolean;
  }

  public void setSeatTypes(NoteList paramNoteList)
  {
    this.seatTypes = paramNoteList;
  }

  public void setShakeNotify(boolean paramBoolean)
  {
    this.shakeNotify = paramBoolean;
  }

  public void setSpeedMultiple(Integer paramInteger)
  {
    this.speedMultiple = paramInteger;
  }

  public void setSpeedMultiple2(Integer paramInteger)
  {
    this.speedMultiple2 = paramInteger;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }

  public void setTrainList(List<String> paramList)
  {
    this.trainList = paramList;
  }

  public void setVoiceNotify(boolean paramBoolean)
  {
    this.voiceNotify = paramBoolean;
  }

  public boolean[] trainSelect()
  {
    String[] arrayOfString = allTrain();
    boolean[] arrayOfBoolean = new boolean[arrayOfString.length];
    for (int i = 0; i < arrayOfString.length; i++)
      arrayOfBoolean[i] = isMonitorTrain(arrayOfString[i]);
    return arrayOfBoolean;
  }

  public void updateTrainSelect(boolean[] paramArrayOfBoolean)
  {
    String[] arrayOfString = allTrain();
    if (arrayOfString.length != paramArrayOfBoolean.length)
      return;
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramArrayOfBoolean.length; i++)
    {
      if (paramArrayOfBoolean[i] == 0)
        continue;
      localArrayList.add(arrayOfString[i]);
    }
    this.trainList = localArrayList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.MonitorInfo
 * JD-Core Version:    0.6.0
 */