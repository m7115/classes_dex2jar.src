package com.secneo.apkwrapper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class ReceiverWrapper extends BroadcastReceiver
{
  private static String PACKAGE_NAME = "com.MobileTicket";

  static
  {
    if (Build.CPU_ABI.equals("x86"))
      System.loadLibrary("DexHelper-x86");
    while (true)
    {
      Helper.installApplication(PACKAGE_NAME);
      return;
      System.loadLibrary("DexHelper");
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.secneo.apkwrapper.ReceiverWrapper
 * JD-Core Version:    0.6.0
 */