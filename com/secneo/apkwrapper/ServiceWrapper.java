package com.secneo.apkwrapper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceWrapper extends Service
{
  private static String PACKAGE_NAME = "com.MobileTicket";

  static
  {
    if (Helper.getCPUABI().equals("x86"))
      System.loadLibrary("DexHelper-x86");
    while (true)
    {
      Helper.installApplication(PACKAGE_NAME);
      return;
      System.loadLibrary("DexHelper");
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.secneo.apkwrapper.ServiceWrapper
 * JD-Core Version:    0.6.0
 */