package com.worklight.androidgap;

import android.content.Intent;
import android.os.IBinder;
import com.secneo.apkwrapper.ServiceWrapper;

public class WLForegroundService extends ServiceWrapper
{
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onDestroy()
  {
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return 0;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.worklight.androidgap.WLForegroundService
 * JD-Core Version:    0.6.0
 */