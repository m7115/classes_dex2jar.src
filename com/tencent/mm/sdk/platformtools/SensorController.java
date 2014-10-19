package com.tencent.mm.sdk.platformtools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorController extends BroadcastReceiver
  implements SensorEventListener
{
  private static float a = 4.294967E+009F;
  private static float c = 0.5F;
  private float b;
  private a d;
  private boolean e;

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.HEADSET_PLUG"))
    {
      int i = paramIntent.getIntExtra("state", 0);
      if (i == 1)
        this.e = true;
      if (i == 0)
        this.e = false;
    }
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (this.e)
      return;
    float f = paramSensorEvent.values[0];
    switch (paramSensorEvent.sensor.getType())
    {
    default:
      return;
    case 8:
    }
    if (f < a)
    {
      a = f;
      c = 0.5F + f;
    }
    if ((this.b >= c) && (f < c))
      if (this.d != null)
      {
        a.d("MicroMsg.SensorController", "sensor event false");
        this.d.a(false);
      }
    while (true)
    {
      this.b = f;
      return;
      if ((this.b > c) || (f <= c) || (this.d == null))
        continue;
      a.d("MicroMsg.SensorController", "sensor event true");
      this.d.a(true);
    }
  }

  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.SensorController
 * JD-Core Version:    0.6.0
 */