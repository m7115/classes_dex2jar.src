package com.baidu.location;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

class LocationClient$1
  implements ServiceConnection
{
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    LocationClient.a(this.a, new Messenger(paramIBinder));
    if (LocationClient.jdMethod_char(this.a) == null)
      j.jdMethod_if("baidu_location_Client", "server not connected");
    while (true)
    {
      return;
      LocationClient.a(this.a, true);
      Log.d("baidu_location_client", "baidu location connected ...");
      try
      {
        Message localMessage = Message.obtain(null, 11);
        localMessage.replyTo = LocationClient.jdMethod_int(this.a);
        localMessage.setData(LocationClient.jdMethod_long(this.a));
        LocationClient.jdMethod_char(this.a).send(localMessage);
        LocationClient.a(this.a, true);
        LocationClient.jdMethod_do(this.a, true);
        j.jdMethod_if("baidu_location_Client", "bindService ...");
        if (LocationClient.jdMethod_byte(this.a) == null)
          continue;
        LocationClient.jdMethod_try(this.a).obtainMessage(4).sendToTarget();
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    LocationClient.a(this.a, null);
    LocationClient.a(this.a, false);
    j.jdMethod_if("baidu_location_Client", "unbindservice...");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.LocationClient.1
 * JD-Core Version:    0.6.0
 */