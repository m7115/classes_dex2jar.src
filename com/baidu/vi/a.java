package com.baidu.vi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class a extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    VDeviceAPI.onNetworkStateChanged();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.vi.a
 * JD-Core Version:    0.6.0
 */