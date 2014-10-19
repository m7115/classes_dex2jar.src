package com.yipiao.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import com.yipiao.YipiaoApplication;

public class MonitorSchedule
{
  private static MonitorSchedule monitorSchedule;
  private YipiaoApplication app = YipiaoApplication.getApp();
  private Intent intent2 = new Intent(this.app, MonitorService2.class);
  private PendingIntent monitorServiceIntent;

  public MonitorSchedule()
  {
    Intent localIntent = new Intent(this.app, MonitorService1.class);
    this.monitorServiceIntent = PendingIntent.getService(this.app, 0, localIntent, 0);
  }

  public static MonitorSchedule getInstance()
  {
    if (monitorSchedule == null)
      monitorSchedule = new MonitorSchedule();
    return monitorSchedule;
  }

  public void enableServiceMonitor()
  {
    this.app.startService(this.intent2);
  }

  public void startAlarmMonitor(long paramLong)
  {
    AlarmManager localAlarmManager = (AlarmManager)this.app.getSystemService("alarm");
    localAlarmManager.cancel(this.monitorServiceIntent);
    localAlarmManager.set(0, paramLong, this.monitorServiceIntent);
  }

  public void startMonitor(long paramLong)
  {
    startServiceMonitor(paramLong);
    startAlarmMonitor(paramLong);
  }

  public void startServiceMonitor(long paramLong)
  {
    this.app.startService(this.intent2);
  }

  public void unenableServiceMonitor()
  {
    this.app.stopService(this.intent2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.MonitorSchedule
 * JD-Core Version:    0.6.0
 */