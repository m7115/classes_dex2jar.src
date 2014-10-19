package com.yipiao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Vibrator;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import com.yipiao.YipiaoApplication;
import com.yipiao.activity.BookMonitorActivity;
import com.yipiao.activity.MonitorSeatListActivity;
import com.yipiao.activity.OrderQueryActivity;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MiComparator;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.Train;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class MonitorBusiness
  implements MediaPlayer.OnCompletionListener
{
  static int current;
  static SimpleDateFormat hhmm;
  static MonitorBusiness instance;
  public static long waitTo = 0L;
  private YipiaoApplication app = YipiaoApplication.getApp();
  private HuocheBase autoSubmitHcM;
  private HuocheBase autoSubmitHcN;
  private List<HuocheBase> huocheList;
  private MediaPlayer mediaPlayer = MediaPlayer.create(this.app, 2131099648);
  private long nextSaveTime = 0L;
  private NotificationManager notificationMgr = (NotificationManager)this.app.getSystemService("notification");
  private Vibrator vibrator = (Vibrator)this.app.getSystemService("vibrator");

  static
  {
    hhmm = new SimpleDateFormat("HHmm");
  }

  private MonitorBusiness()
  {
    this.mediaPlayer.setOnCompletionListener(this);
  }

  private void autoBook(HuocheBase paramHuocheBase, Train paramTrain, MonitorInfo paramMonitorInfo)
  {
    monitorNotify(paramMonitorInfo, null, 5);
    try
    {
      if (this.app.isVisitor())
        throw new m("没有设置12306帐号，请先登陆");
    }
    catch (Exception localException)
    {
      monitorNotify(paramMonitorInfo, "抢票失败：" + localException.getMessage(), 4);
      n.a(localException);
      return;
    }
    List localList = paramMonitorInfo.getPassengers();
    String str = getAutoSubmitHuoche(paramHuocheBase).autoBook(paramMonitorInfo, paramMonitorInfo.getLastResult(), localList);
    bookSuccessNotification(paramMonitorInfo, str);
    monitorNotify(paramMonitorInfo, str, 6);
  }

  private void bookPerFinishNotification(Train paramTrain, MonitorInfo paramMonitorInfo, String paramString)
  {
    if (isSleepTime())
      return;
    this.app.putParms("passengers", paramMonitorInfo.getPassengers());
    this.app.putParms("isNormalBook", Boolean.valueOf(false));
    this.app.putParms("train", paramTrain);
    this.app.putParms("chainQuery", paramMonitorInfo.getCq());
    this.app.putParms("mi", paramMonitorInfo);
    Notification localNotification = new Notification(2130837694, paramString, System.currentTimeMillis());
    Intent localIntent = new Intent(this.app, BookMonitorActivity.class);
    PendingIntent localPendingIntent = PendingIntent.getActivity(this.app, 0, localIntent, 134217728);
    localNotification.setLatestEventInfo(this.app, "有票通知", paramString, localPendingIntent);
    localNotification.defaults = 4;
    this.notificationMgr.notify(0, localNotification);
  }

  private void bookSuccessNotification(MonitorInfo paramMonitorInfo, String paramString)
  {
    if (isSleepTime())
      return;
    Notification localNotification = new Notification(2130837694, paramString, System.currentTimeMillis());
    Intent localIntent = new Intent(this.app, OrderQueryActivity.class);
    localNotification.tickerText = "抢票成功！";
    PendingIntent localPendingIntent = PendingIntent.getActivity(this.app, 0, localIntent, 134217728);
    localNotification.setLatestEventInfo(this.app, "下单提醒", paramString, localPendingIntent);
    if (paramMonitorInfo.isShakeNotify())
      this.vibrator.vibrate(2500L);
    localNotification.ledOnMS = 2;
    localNotification.ledOffMS = 1;
    localNotification.ledARGB = -16711936;
    localNotification.flags = (0x1 | localNotification.flags);
    this.notificationMgr.notify(0, localNotification);
  }

  private HuocheBase getAutoSubmitHuoche(HuocheBase paramHuocheBase)
  {
    if ((paramHuocheBase instanceof HuocheNew))
      return this.autoSubmitHcN;
    return this.autoSubmitHcM;
  }

  private int getHcApi(HuocheBase paramHuocheBase)
  {
    if ((paramHuocheBase instanceof HuocheNew))
      return 2;
    return 3;
  }

  private List<HuocheBase> getHuocheList()
  {
    int i;
    if (this.huocheList == null)
    {
      this.huocheList = new ArrayList();
      this.autoSubmitHcN = HuocheNew.getInstance().copyHc();
      this.autoSubmitHcM = HuocheMobile.getInstance().copyHc();
      i = this.app.launchInfo.optInt("monitorApiLimit", 0);
      if (i != 0)
        break label120;
      this.huocheList.add(HuocheNew.getInstance());
      this.huocheList.add(HuocheMobile.getInstance());
      this.huocheList.add(this.autoSubmitHcN);
      this.huocheList.add(this.autoSubmitHcM);
    }
    while (true)
    {
      initCopyHcForBook();
      return this.huocheList;
      label120: if (i == 2)
      {
        this.huocheList.add(HuocheNew.getInstance());
        this.huocheList.add(this.autoSubmitHcN);
        continue;
      }
      if (i != 3)
        continue;
      this.huocheList.add(HuocheMobile.getInstance());
      this.huocheList.add(this.autoSubmitHcM);
    }
  }

  private HuocheBase getMonitorHuoche(int paramInt)
  {
    int i = paramInt % getHuocheList().size();
    return (HuocheBase)this.huocheList.get(i);
  }

  private Train getValidTrain(MonitorInfo paramMonitorInfo, List<Train> paramList)
  {
    Train localTrain;
    if ((paramList == null) || (paramList.isEmpty()))
      localTrain = null;
    int i;
    do
    {
      return localTrain;
      Collections.sort(paramList, new MiComparator(paramMonitorInfo));
      localTrain = (Train)paramList.get(0);
      i = localTrain.getSeatNum(paramMonitorInfo.getSeatTypes());
    }
    while (paramMonitorInfo.getSeatNum().intValue() <= i);
    return null;
  }

  private void hasTicketNotification(MonitorInfo paramMonitorInfo, String paramString)
  {
    if (isSleepTime())
      return;
    Notification localNotification = new Notification(2130837694, paramString, System.currentTimeMillis());
    Intent localIntent = new Intent(this.app, MonitorSeatListActivity.class);
    this.app.putParms("mi", paramMonitorInfo);
    localNotification.tickerText = "来票啦！";
    PendingIntent localPendingIntent = PendingIntent.getActivity(this.app, 0, localIntent, 134217728);
    localNotification.setLatestEventInfo(this.app, "有票通知", paramString, localPendingIntent);
    if (paramMonitorInfo.isShakeNotify())
      this.vibrator.vibrate(2500L);
    if (paramMonitorInfo.isVoiceNotify())
      voiceNotify(this.mediaPlayer);
    localNotification.ledOnMS = 2;
    localNotification.ledOffMS = 1;
    localNotification.ledARGB = -16711936;
    localNotification.flags = (0x1 | localNotification.flags);
    this.notificationMgr.notify(0, localNotification);
  }

  private void initCopyHcForBook()
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          MonitorBusiness.this.autoSubmitHcN.autoLogin();
          MonitorBusiness.this.autoSubmitHcM.autoLogin();
          return;
        }
        catch (Exception localException)
        {
          n.a(localException);
        }
      }
    }
    .start();
  }

  public static MonitorBusiness instance()
  {
    if (instance == null)
      instance = new MonitorBusiness();
    return instance;
  }

  private void monitor(MonitorInfo paramMonitorInfo)
  {
    try
    {
      HuocheBase localHuocheBase = getMonitorHuoche(paramMonitorInfo.getQueryTimes());
      paramMonitorInfo.queryTimes = (1 + paramMonitorInfo.queryTimes);
      monitorNotify(paramMonitorInfo, null, 3);
      List localList = localHuocheBase.queryPiaoForMonitor(paramMonitorInfo.getCq());
      paramMonitorInfo.setHcApi(getHcApi(localHuocheBase));
      paramMonitorInfo.setLastResult(localList);
      Train localTrain = getValidTrain(paramMonitorInfo, localList);
      if (localTrain != null)
      {
        paramMonitorInfo.notifyTime = System.currentTimeMillis();
        String str = localTrain.getCode() + "(" + localTrain.getFrom() + "-" + localTrain.getTo() + ")剩余" + localTrain.getSeatMsg(paramMonitorInfo.getSeatTypes());
        monitorNotify(paramMonitorInfo, str, 4);
        if (!isSleepTime())
        {
          hasTicketNotification(paramMonitorInfo, str);
          if (paramMonitorInfo.isAutoBook())
            autoBook(localHuocheBase, localTrain, paramMonitorInfo);
        }
      }
      while (true)
      {
        paramMonitorInfo.calNextRunTime((float)this.app.launchInfo.optLong("monitorInterval", 30000L));
        return;
        monitorNotify(paramMonitorInfo, "没有找到合适的票!", 2);
      }
    }
    catch (Exception localException)
    {
      n.b("ServiceWorker-->run", localException);
      monitorNotify(paramMonitorInfo, "没有找到合适的票!!", 2);
      paramMonitorInfo.setNextRunTime(System.currentTimeMillis() + this.app.launchInfo.optInt("monitorIntervalAfterException", 60000));
    }
  }

  private void monitorNotify(MonitorInfo paramMonitorInfo, String paramString, int paramInt)
  {
    if (paramString != null)
      paramMonitorInfo.putLog(paramString);
    if (paramMonitorInfo.getStatus() != 1)
      paramMonitorInfo.setStatus(paramInt);
    this.app.observable.setChanged();
    this.app.observable.notifyObservers(null);
  }

  private void saveMonitorPool()
  {
    if ((this.app.runMonitorNum() == 0) || (System.currentTimeMillis() - this.nextSaveTime > 60000L))
    {
      this.app.saveMonitorPool();
      this.nextSaveTime = System.currentTimeMillis();
    }
  }

  private void voiceNotify(MediaPlayer paramMediaPlayer)
  {
    AudioManager localAudioManager = (AudioManager)this.app.getSystemService("audio");
    int i = localAudioManager.getStreamMaxVolume(3);
    current = localAudioManager.getStreamVolume(3);
    localAudioManager.setStreamVolume(3, i, 8);
    paramMediaPlayer.start();
  }

  public static void waitFor(int paramInt)
  {
    waitTo = System.currentTimeMillis() + paramInt;
  }

  public MonitorInfo firstRunMonitor(List<MonitorInfo> paramList)
  {
    long l1 = System.currentTimeMillis();
    Object localObject1 = null;
    Iterator localIterator = paramList.iterator();
    Object localObject2;
    long l2;
    if (localIterator.hasNext())
    {
      localObject2 = (MonitorInfo)localIterator.next();
      if ((!((MonitorInfo)localObject2).isRuning()) || (l1 < ((MonitorInfo)localObject2).getNextRunTime()))
        break label75;
      l2 = ((MonitorInfo)localObject2).getNextRunTime();
    }
    while (true)
    {
      l1 = l2;
      localObject1 = localObject2;
      break;
      return localObject1;
      label75: localObject2 = localObject1;
      l2 = l1;
    }
  }

  public long getNextStartTime()
  {
    long l1 = System.currentTimeMillis() + this.app.launchInfo.optInt("monitorIntervalOnSleep", 300000);
    if (isSleepTime())
      return l1;
    Iterator localIterator = this.app.getMonitorPool().iterator();
    long l2 = l1;
    long l3;
    while (localIterator.hasNext())
    {
      MonitorInfo localMonitorInfo = (MonitorInfo)localIterator.next();
      if (!localMonitorInfo.isRuning())
        continue;
      if (localMonitorInfo.getNextRunTime() >= l2)
        break label143;
      l3 = localMonitorInfo.getNextRunTime();
    }
    while (true)
    {
      l2 = l3;
      break;
      int i = this.app.launchInfo.optInt("minMonitorInterval", 4500);
      if (l2 < System.currentTimeMillis() + i)
        l2 = System.currentTimeMillis() + i;
      return l2;
      label143: l3 = l2;
    }
  }

  public boolean isSleepTime()
  {
    String str = hhmm.format(new Date());
    String[] arrayOfString1 = this.app.launchInfo.optString("sleepTime", "2305-2400;0000-0650").split(";");
    int i = 0;
    int j = arrayOfString1.length;
    int k = 0;
    if (i < j)
    {
      String[] arrayOfString2 = arrayOfString1[i].split("-");
      if (arrayOfString2.length < 2);
      do
      {
        i++;
        break;
      }
      while ((str.compareTo(arrayOfString2[0]) < 0) || (str.compareTo(arrayOfString2[1]) > 0));
      k = 1;
    }
    return k;
  }

  public boolean monitor()
  {
    if (waitTo > System.currentTimeMillis());
    MonitorInfo localMonitorInfo;
    do
    {
      return false;
      waitFor(1000);
      localMonitorInfo = firstRunMonitor(this.app.getMonitorPool());
    }
    while (localMonitorInfo == null);
    monitor(localMonitorInfo);
    saveMonitorPool();
    return true;
  }

  public void onChangeUser(LoginUser paramLoginUser)
  {
    this.huocheList = null;
  }

  public void onCompletion(MediaPlayer paramMediaPlayer)
  {
    ((AudioManager)this.app.getSystemService("audio")).setStreamVolume(3, current, 8);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.MonitorBusiness
 * JD-Core Version:    0.6.0
 */