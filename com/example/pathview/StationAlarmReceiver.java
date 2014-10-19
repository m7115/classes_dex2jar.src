package com.example.pathview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class StationAlarmReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equalsIgnoreCase("com.yipiao.action.setalarm"))
    {
      Log.i("path - > 提醒广播", "startActivity");
      Intent localIntent = new Intent(paramContext, AlarmDialogActivity.class);
      localIntent.addFlags(268435456);
      localIntent.putExtras(paramIntent.getExtras());
      paramContext.startActivity(localIntent);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.StationAlarmReceiver
 * JD-Core Version:    0.6.0
 */