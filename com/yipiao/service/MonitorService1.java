package com.yipiao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.suanya.common.a.n;
import com.yipiao.YipiaoApplication;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MonitorService1 extends Service
{
  private YipiaoApplication app;
  private int delayRunTimes;
  private long nextRunTime;
  private int rightRunTimes;
  private ThreadPoolExecutor threadPool;

  private void runStatusManager()
  {
    if (this.nextRunTime == 0L);
    do
    {
      return;
      if (System.currentTimeMillis() - this.nextRunTime > 1000L)
      {
        this.delayRunTimes = (1 + this.delayRunTimes);
        this.rightRunTimes = 0;
      }
      while (MonitorBusiness.instance().isSleepTime())
      {
        n.a("unenableServiceMonitor-sleep");
        MonitorSchedule.getInstance().unenableServiceMonitor();
        return;
        this.rightRunTimes = (1 + this.rightRunTimes);
        this.delayRunTimes = 0;
      }
      if (this.rightRunTimes < 3)
        continue;
      n.a("unenableServiceMonitor");
      MonitorSchedule.getInstance().unenableServiceMonitor();
    }
    while (this.delayRunTimes < 3);
    n.a("enableServiceMonitor");
    MonitorSchedule.getInstance().enableServiceMonitor();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.rightRunTimes = 0;
    this.delayRunTimes = 0;
    this.nextRunTime = 0L;
    this.app = ((YipiaoApplication)getApplication());
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    this.threadPool = new ThreadPoolExecutor(2, 2, 1L, TimeUnit.SECONDS, localLinkedBlockingQueue);
  }

  public void onDestroy()
  {
    n.a("MonitorService-->onDestroy");
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    n.a("MonitorService1-start");
    runStatusManager();
    this.threadPool.execute(new ServiceWorker());
    return 1;
  }

  class ServiceWorker
    implements Runnable
  {
    ServiceWorker()
    {
    }

    public void run()
    {
      long l = 10000L + System.currentTimeMillis();
      try
      {
        if (MonitorService1.this.app.runMonitorNum() > 0)
        {
          Boolean localBoolean = Boolean.valueOf(MonitorBusiness.instance().monitor());
          l = MonitorBusiness.instance().getNextStartTime();
          n.a("Amonitor-after-" + (l - System.currentTimeMillis()) + '-' + localBoolean);
        }
        if (MonitorService1.this.app.runMonitorNum() > 0)
        {
          MonitorService1.access$102(MonitorService1.this, l);
          MonitorSchedule.getInstance().startAlarmMonitor(l);
          return;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          n.b("monitor-ServiceWorker:" + localException.getMessage());
          l = MonitorBusiness.instance().getNextStartTime();
        }
        MonitorService1.this.stopSelf();
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.MonitorService1
 * JD-Core Version:    0.6.0
 */