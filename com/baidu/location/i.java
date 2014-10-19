package com.baidu.location;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import java.util.ArrayList;
import java.util.Iterator;

public class i
{
  public static final String jdField_new = "android.com.baidu.location.TIMER.NOTIFY";
  private int a = 0;
  private long b = 0L;
  private ArrayList jdField_byte = null;
  private boolean c = false;
  private BDLocation jdField_case = null;
  private long jdField_char = 0L;
  private LocationClient d = null;
  private String jdField_do = "baidu_location_service";
  private b jdField_else = null;
  private AlarmManager jdField_for = null;
  private float jdField_goto = 3.4028235E+38F;
  private Context jdField_if = null;
  private a jdField_int = new a();
  private boolean jdField_long = false;
  private PendingIntent jdField_try = null;
  private boolean jdField_void = false;

  public i(Context paramContext, LocationClient paramLocationClient)
  {
    this.jdField_if = paramContext;
    this.d = paramLocationClient;
    this.d.registerNotifyLocationListener(this.jdField_int);
    this.jdField_for = ((AlarmManager)this.jdField_if.getSystemService("alarm"));
    this.jdField_else = new b();
    this.c = false;
  }

  private void a()
  {
    int i = 10000;
    if (!jdField_do())
      return;
    int j;
    if (this.jdField_goto > 5000.0F)
    {
      j = 600000;
      if (!this.jdField_long)
        break label138;
      this.jdField_long = false;
    }
    while (true)
    {
      if (this.a != 0)
      {
        long l = this.jdField_char + this.a - System.currentTimeMillis();
        if (i <= l);
      }
      for (int k = 0; ; k = 1)
      {
        if (k == 0)
          break label136;
        this.a = i;
        this.jdField_char = System.currentTimeMillis();
        a(this.a);
        return;
        if (this.jdField_goto > 1000.0F)
        {
          j = 120000;
          break;
        }
        if (this.jdField_goto > 500.0F)
        {
          j = 60000;
          break;
        }
        j = i;
        break;
      }
      label136: break;
      label138: i = j;
    }
  }

  private void a(long paramLong)
  {
    if (this.jdField_void)
      this.jdField_for.cancel(this.jdField_try);
    this.jdField_try = PendingIntent.getBroadcast(this.jdField_if, 0, new Intent("android.com.baidu.location.TIMER.NOTIFY"), 134217728);
    this.jdField_for.set(0, paramLong + System.currentTimeMillis(), this.jdField_try);
    j.jdField_if(this.jdField_do, "timer start:" + paramLong);
  }

  private void a(BDLocation paramBDLocation)
  {
    j.jdField_if(this.jdField_do, "notify new loation");
    this.jdField_void = false;
    if ((paramBDLocation.getLocType() != 61) && (paramBDLocation.getLocType() != 161) && (paramBDLocation.getLocType() != 65))
      a(120000L);
    do
      return;
    while ((System.currentTimeMillis() - this.b < 5000L) || (this.jdField_byte == null));
    this.jdField_case = paramBDLocation;
    this.b = System.currentTimeMillis();
    float[] arrayOfFloat = new float[1];
    Iterator localIterator = this.jdField_byte.iterator();
    float f1 = 3.4028235E+38F;
    if (localIterator.hasNext())
    {
      BDNotifyListener localBDNotifyListener = (BDNotifyListener)localIterator.next();
      Location.distanceBetween(paramBDLocation.getLatitude(), paramBDLocation.getLongitude(), localBDNotifyListener.mLatitudeC, localBDNotifyListener.mLongitudeC, arrayOfFloat);
      float f2 = arrayOfFloat[0] - localBDNotifyListener.mRadius - paramBDLocation.getRadius();
      j.jdField_if(this.jdField_do, "distance:" + f2);
      if (f2 > 0.0F)
        if (f2 >= f1)
          break label251;
      while (true)
      {
        f1 = f2;
        break;
        if (localBDNotifyListener.Notified < 3)
        {
          localBDNotifyListener.Notified = (1 + localBDNotifyListener.Notified);
          localBDNotifyListener.onNotify(paramBDLocation, arrayOfFloat[0]);
          if (localBDNotifyListener.Notified < 3)
            this.jdField_long = true;
        }
        label251: f2 = f1;
      }
    }
    if (f1 < this.jdField_goto)
      this.jdField_goto = f1;
    this.a = 0;
    a();
  }

  private boolean jdField_do()
  {
    if ((this.jdField_byte == null) || (this.jdField_byte.isEmpty()))
    {
      i = 0;
      return i;
    }
    Iterator localIterator = this.jdField_byte.iterator();
    int i = 0;
    label31: if (localIterator.hasNext())
      if (((BDNotifyListener)localIterator.next()).Notified >= 3)
        break label63;
    label63: for (int j = 1; ; j = i)
    {
      i = j;
      break label31;
      break;
    }
  }

  public void a(BDNotifyListener paramBDNotifyListener)
  {
    j.jdField_if(this.jdField_do, paramBDNotifyListener.mCoorType + "2gcj");
    if (paramBDNotifyListener.mCoorType == null)
      return;
    if (!paramBDNotifyListener.mCoorType.equals("gcj02"))
    {
      double[] arrayOfDouble = Jni.jdField_if(paramBDNotifyListener.mLongitude, paramBDNotifyListener.mLatitude, paramBDNotifyListener.mCoorType + "2gcj");
      paramBDNotifyListener.mLongitudeC = arrayOfDouble[0];
      paramBDNotifyListener.mLatitudeC = arrayOfDouble[1];
      j.jdField_if(this.jdField_do, paramBDNotifyListener.mCoorType + "2gcj");
      j.jdField_if(this.jdField_do, "coor:" + paramBDNotifyListener.mLongitude + "," + paramBDNotifyListener.mLatitude + ":" + paramBDNotifyListener.mLongitudeC + "," + paramBDNotifyListener.mLatitudeC);
    }
    if ((this.jdField_case == null) || (System.currentTimeMillis() - this.b > 300000L))
      this.d.requestNotifyLocation();
    while (true)
    {
      a();
      return;
      float[] arrayOfFloat = new float[1];
      Location.distanceBetween(this.jdField_case.getLatitude(), this.jdField_case.getLongitude(), paramBDNotifyListener.mLatitudeC, paramBDNotifyListener.mLongitudeC, arrayOfFloat);
      float f = arrayOfFloat[0] - paramBDNotifyListener.mRadius - this.jdField_case.getRadius();
      if (f > 0.0F)
      {
        if (f >= this.jdField_goto)
          continue;
        this.jdField_goto = f;
        continue;
      }
      if (paramBDNotifyListener.Notified >= 3)
        continue;
      paramBDNotifyListener.Notified = (1 + paramBDNotifyListener.Notified);
      paramBDNotifyListener.onNotify(this.jdField_case, arrayOfFloat[0]);
      if (paramBDNotifyListener.Notified >= 3)
        continue;
      this.jdField_long = true;
    }
  }

  public int jdField_do(BDNotifyListener paramBDNotifyListener)
  {
    if (this.jdField_byte == null)
      return 0;
    if (this.jdField_byte.contains(paramBDNotifyListener))
      this.jdField_byte.remove(paramBDNotifyListener);
    if ((this.jdField_byte.size() == 0) && (this.jdField_void))
      this.jdField_for.cancel(this.jdField_try);
    return 1;
  }

  public int jdField_if(BDNotifyListener paramBDNotifyListener)
  {
    if (this.jdField_byte == null)
      this.jdField_byte = new ArrayList();
    this.jdField_byte.add(paramBDNotifyListener);
    paramBDNotifyListener.isAdded = true;
    paramBDNotifyListener.mNotifyCache = this;
    if (!this.c)
    {
      this.jdField_if.registerReceiver(this.jdField_else, new IntentFilter("android.com.baidu.location.TIMER.NOTIFY"));
      this.c = true;
    }
    if (paramBDNotifyListener.mCoorType == null)
      return 1;
    if (!paramBDNotifyListener.mCoorType.equals("gcj02"))
    {
      double[] arrayOfDouble = Jni.jdField_if(paramBDNotifyListener.mLongitude, paramBDNotifyListener.mLatitude, paramBDNotifyListener.mCoorType + "2gcj");
      paramBDNotifyListener.mLongitudeC = arrayOfDouble[0];
      paramBDNotifyListener.mLatitudeC = arrayOfDouble[1];
      j.jdField_if(this.jdField_do, paramBDNotifyListener.mCoorType + "2gcj");
      j.jdField_if(this.jdField_do, "coor:" + paramBDNotifyListener.mLongitude + "," + paramBDNotifyListener.mLatitude + ":" + paramBDNotifyListener.mLongitudeC + "," + paramBDNotifyListener.mLatitudeC);
    }
    if ((this.jdField_case == null) || (System.currentTimeMillis() - this.b > 30000L))
      this.d.requestNotifyLocation();
    while (true)
    {
      a();
      return 1;
      float[] arrayOfFloat = new float[1];
      Location.distanceBetween(this.jdField_case.getLatitude(), this.jdField_case.getLongitude(), paramBDNotifyListener.mLatitudeC, paramBDNotifyListener.mLongitudeC, arrayOfFloat);
      float f = arrayOfFloat[0] - paramBDNotifyListener.mRadius - this.jdField_case.getRadius();
      if (f > 0.0F)
      {
        if (f >= this.jdField_goto)
          continue;
        this.jdField_goto = f;
        continue;
      }
      if (paramBDNotifyListener.Notified >= 3)
        continue;
      paramBDNotifyListener.Notified = (1 + paramBDNotifyListener.Notified);
      paramBDNotifyListener.onNotify(this.jdField_case, arrayOfFloat[0]);
      if (paramBDNotifyListener.Notified >= 3)
        continue;
      this.jdField_long = true;
    }
  }

  public void jdField_if()
  {
    if (this.jdField_void)
      this.jdField_for.cancel(this.jdField_try);
    this.jdField_case = null;
    this.b = 0L;
    if (this.c)
    {
      j.jdField_if(this.jdField_do, "unregister...");
      this.jdField_if.unregisterReceiver(this.jdField_else);
    }
    this.c = false;
  }

  public class a
    implements BDLocationListener
  {
    public a()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      i.a(i.this, paramBDLocation);
    }

    public void onReceivePoi(BDLocation paramBDLocation)
    {
    }
  }

  public class b extends BroadcastReceiver
  {
    public b()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      j.jdMethod_if(i.a(i.this), "timer expire,request location...");
      if ((i.jdMethod_do(i.this) == null) || (i.jdMethod_do(i.this).isEmpty()))
        return;
      i.jdMethod_if(i.this).requestNotifyLocation();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.i
 * JD-Core Version:    0.6.0
 */