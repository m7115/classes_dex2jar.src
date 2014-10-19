package com.yipiao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import cn.suanya.common.a.n;
import com.yipiao.YipiaoApplication;

public class MonitorService2 extends Service
{
  private static PowerManager.WakeLock m_wklk;
  private YipiaoApplication app;
  public boolean isRun;
  private Thread thr;

  public static void wkAcquire()
  {
    if ((m_wklk != null) && (!m_wklk.isHeld()))
      m_wklk.acquire();
  }

  public static void wkRelease()
  {
    if ((m_wklk != null) && (m_wklk.isHeld()))
      m_wklk.release();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.app = ((YipiaoApplication)getApplication());
    m_wklk = ((PowerManager)getSystemService("power")).newWakeLock(1, "cn");
  }

  public void onDestroy()
  {
    n.a("MonitorService2-->onDestroy");
    this.isRun = false;
    wkRelease();
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    n.a("MonitorService2-start");
    this.isRun = true;
    wkAcquire();
    if ((this.thr == null) || (!this.thr.isAlive()))
    {
      this.thr = new Thread(new ServiceWorker());
      this.thr.start();
      return 1;
    }
    synchronized (this.thr)
    {
      this.thr.notifyAll();
      return 1;
    }
  }

  class ServiceWorker
    implements Runnable
  {
    ServiceWorker()
    {
    }

    public void run()
    {
      if (MonitorService2.this.isRun)
      {
        (10000L + System.currentTimeMillis());
        try
        {
          if (MonitorService2.this.app.runMonitorNum() > 0)
          {
            Boolean localBoolean = Boolean.valueOf(MonitorBusiness.instance().monitor());
            l = MonitorBusiness.instance().getNextStartTime() - System.currentTimeMillis();
            n.a("Smonitor-after-" + l + '-' + localBoolean);
          }
        }
        catch (Exception localException)
        {
          try
          {
            while (true)
            {
              synchronized (MonitorService2.this.thr)
              {
                MonitorService2.this.thr.wait(l);
              }
              localException = localException;
              n.b("monitor-ServiceWorker:" + localException.getMessage());
              long l = MonitorBusiness.instance().getNextStartTime() - System.currentTimeMillis();
              try
              {
                Thread.sleep(l);
              }
              catch (InterruptedException localInterruptedException1)
              {
              }
            }
          }
          catch (InterruptedException localInterruptedException2)
          {
            while (true)
              localInterruptedException2.printStackTrace();
          }
        }
      }
      MonitorService2.this.stopSelf();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.MonitorService2
 * JD-Core Version:    0.6.0
 */