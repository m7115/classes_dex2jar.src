package com.google.android.gcm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.secneo.apkwrapper.ReceiverWrapper;

public class GCMBroadcastReceiver extends ReceiverWrapper
{
  private static final String TAG = "GCMBroadcastReceiver";

  protected String getGCMIntentServiceClassName(Context paramContext)
  {
    return paramContext.getPackageName() + ".GCMIntentService";
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.v("GCMBroadcastReceiver", "onReceive: " + paramIntent.getAction());
    String str = getGCMIntentServiceClassName(paramContext);
    Log.v("GCMBroadcastReceiver", "GCM IntentService class: " + str);
    GCMBaseIntentService.runIntentInService(paramContext, paramIntent, str);
    setResult(-1, null, null);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.android.gcm.GCMBroadcastReceiver
 * JD-Core Version:    0.6.0
 */