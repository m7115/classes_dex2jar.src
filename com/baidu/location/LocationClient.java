package com.baidu.location;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import java.util.ArrayList;
import java.util.Iterator;

public final class LocationClient
{
  private static final int B = 4;
  private static final int b = 8;
  private static final int f = 9;
  private static final String jdField_for = "baidu_location_Client";
  private static final int jdField_goto = 1000;
  private static final int h = 7;
  private static final int jdField_if = 10;
  private static final int m = 5;
  private static final int n = 12;
  private static final int o = 6;
  private static final int p = 2;
  private static final int s = 6000;
  private static final int jdField_try = 1;
  private static final int jdField_void = 3;
  private static final int y = 11;
  private String A = null;
  private ArrayList C = null;
  private boolean a = false;
  private b jdField_byte = null;
  private BDLocationListener c = null;
  private Boolean jdField_case = Boolean.valueOf(false);
  private long jdField_char = 0L;
  private boolean d = false;
  private a jdField_do = new a(null);
  private String e = "3.3";
  private i jdField_else = null;
  private boolean g = false;
  private final Messenger i = new Messenger(this.jdField_do);
  private boolean jdField_int = false;
  private Context j = null;
  private Messenger k = null;
  private LocationClientOption l = new LocationClientOption();
  private Boolean jdField_long = Boolean.valueOf(false);
  private final Object jdField_new = new Object();
  private boolean q = false;
  private long r = 0L;
  private ServiceConnection t = new LocationClient.1(this);
  private String u = "http://loc.map.baidu.com/sdk.php";
  private boolean v = false;
  private boolean w = false;
  private BDLocation x = null;
  private String z = null;

  public LocationClient(Context paramContext)
  {
    this.j = paramContext;
    this.l = new LocationClientOption();
    this.jdField_else = new i(this.j, this);
  }

  public LocationClient(Context paramContext, LocationClientOption paramLocationClientOption)
  {
    this.j = paramContext;
    this.l = paramLocationClientOption;
    this.jdField_else = new i(this.j, this);
  }

  private Bundle a()
  {
    if (this.l == null)
      return null;
    Bundle localBundle = new Bundle();
    localBundle.putInt("num", this.l.a);
    localBundle.putFloat("distance", this.l.jdField_do);
    localBundle.putBoolean("extraInfo", this.l.jdField_if);
    return localBundle;
  }

  private void a(int paramInt)
  {
    if ((paramInt == 26) && (this.v == true))
    {
      Iterator localIterator2 = this.C.iterator();
      while (localIterator2.hasNext())
        ((BDLocationListener)localIterator2.next()).onReceivePoi(this.x);
      this.v = false;
    }
    if (((this.d != true) && ((this.l.jdField_void != true) || (this.x.getLocType() != 61)) && (this.x.getLocType() != 66) && (this.x.getLocType() != 67)) || ((this.l != null) && (this.l.isDisableCache()) && (this.x.getLocType() == 65)));
    do
    {
      return;
      Iterator localIterator1 = this.C.iterator();
      while (localIterator1.hasNext())
        ((BDLocationListener)localIterator1.next()).onReceiveLocation(this.x);
    }
    while ((this.jdField_int) && ((this.x.getLocType() == 66) || (this.x.getLocType() == 67)));
    this.d = false;
  }

  private void a(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      return;
    BDNotifyListener localBDNotifyListener = (BDNotifyListener)paramMessage.obj;
    this.jdField_else.jdField_if(localBDNotifyListener);
  }

  private void a(Message paramMessage, int paramInt)
  {
    String str = paramMessage.getData().getString("locStr");
    j.jdField_if("baidu_location_Client", "on receive new location : " + str);
    j.a("baidu_location_Client", "on receive new location : " + str);
    this.x = new BDLocation(str);
    a(paramInt);
  }

  private void jdField_byte()
  {
    if (this.k == null)
    {
      j.jdField_if("baidu_location_Client", "server not connected");
      return;
    }
    Message localMessage;
    if ((!this.a) || (!this.l.jdField_void))
      localMessage = Message.obtain(null, 22);
    try
    {
      localMessage.replyTo = this.i;
      this.k.send(localMessage);
      this.r = System.currentTimeMillis();
      this.d = true;
      j.jdField_if("baidu_location_Client", "send request to server...");
      synchronized (this.jdField_new)
      {
        if ((this.l != null) && (this.l.jdField_int >= 1000) && (!this.g))
        {
          if (this.jdField_byte == null)
            this.jdField_byte = new b(null);
          this.jdField_do.postDelayed(this.jdField_byte, this.l.jdField_int);
          this.g = true;
        }
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void jdField_do()
  {
    if (this.k == null)
      return;
    Message localMessage = Message.obtain(null, 25);
    try
    {
      localMessage.replyTo = this.i;
      localMessage.setData(a());
      this.k.send(localMessage);
      this.jdField_char = System.currentTimeMillis();
      this.v = true;
      j.jdField_if("baidu_location_Client", "send poi request to server...");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void jdField_do(Message paramMessage)
  {
    j.jdField_if("baidu_location_Client", "onSetOption...");
    if ((paramMessage == null) || (paramMessage.obj == null))
      j.jdField_if("baidu_location_Client", "setOption, but msg.obj is null");
    LocationClientOption localLocationClientOption;
    do
    {
      return;
      localLocationClientOption = (LocationClientOption)paramMessage.obj;
    }
    while (this.l.equals(localLocationClientOption));
    if (this.l.jdField_int != localLocationClientOption.jdField_int);
    try
    {
      synchronized (this.jdField_new)
      {
        if (this.g == true)
        {
          this.jdField_do.removeCallbacks(this.jdField_byte);
          this.g = false;
        }
        if ((localLocationClientOption.jdField_int >= 1000) && (!this.g))
        {
          if (this.jdField_byte == null)
            this.jdField_byte = new b(null);
          this.jdField_do.postDelayed(this.jdField_byte, localLocationClientOption.jdField_int);
          this.g = true;
        }
        this.l = new LocationClientOption(localLocationClientOption);
        if (this.k == null)
        {
          j.jdField_if("baidu_location_Client", "server not connected");
          return;
        }
      }
    }
    catch (Exception localException2)
    {
      while (true)
        j.jdField_if("baidu_location_Client", "set location excpetion...");
      try
      {
        Message localMessage = Message.obtain(null, 15);
        localMessage.replyTo = this.i;
        localMessage.setData(jdField_if());
        this.k.send(localMessage);
        j.jdField_if("baidu_location_Client", "change option ...");
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
  }

  private void jdField_for()
  {
    if (this.q == true)
      return;
    j.jdField_try();
    this.A = this.j.getPackageName();
    this.z = (this.A + "_bdls_v2.9");
    j.jdField_if("baidu_location_Client", this.z);
    Intent localIntent = new Intent(this.j, f.class);
    if (this.l == null)
      this.l = new LocationClientOption();
    try
    {
      this.j.bindService(localIntent, this.t, 1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.q = false;
    }
  }

  private void jdField_for(Message paramMessage)
  {
    BDLocation localBDLocation = new BDLocation(paramMessage.getData().getString("locStr"));
    if ((this.c == null) || ((this.l != null) && (this.l.isDisableCache()) && (localBDLocation.getLocType() == 65)))
      return;
    this.c.onReceiveLocation(localBDLocation);
  }

  private Bundle jdField_if()
  {
    if (this.l == null)
      return null;
    Bundle localBundle = new Bundle();
    localBundle.putString("packName", this.A);
    localBundle.putString("prodName", this.l.jdField_new);
    localBundle.putString("coorType", this.l.jdField_try);
    localBundle.putString("addrType", this.l.jdField_char);
    localBundle.putString("Url", this.u);
    localBundle.putBoolean("openGPS", this.l.jdField_case);
    localBundle.putBoolean("location_change_notify", this.l.jdField_void);
    localBundle.putInt("scanSpan", this.l.jdField_int);
    localBundle.putInt("timeOut", this.l.jdField_long);
    localBundle.putInt("priority", this.l.jdField_goto);
    localBundle.putBoolean("map", this.jdField_long.booleanValue());
    localBundle.putBoolean("import", this.jdField_case.booleanValue());
    return localBundle;
  }

  private void jdField_if(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null));
    BDLocationListener localBDLocationListener;
    do
    {
      return;
      localBDLocationListener = (BDLocationListener)paramMessage.obj;
    }
    while ((this.C == null) || (!this.C.contains(localBDLocationListener)));
    this.C.remove(localBDLocationListener);
  }

  // ERROR //
  private void jdField_int()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 100	com/baidu/location/LocationClient:q	Z
    //   4: ifeq +10 -> 14
    //   7: aload_0
    //   8: getfield 104	com/baidu/location/LocationClient:k	Landroid/os/Messenger;
    //   11: ifnonnull +4 -> 15
    //   14: return
    //   15: aconst_null
    //   16: bipush 12
    //   18: invokestatic 302	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
    //   21: astore_1
    //   22: aload_1
    //   23: aload_0
    //   24: getfield 118	com/baidu/location/LocationClient:i	Landroid/os/Messenger;
    //   27: putfield 305	android/os/Message:replyTo	Landroid/os/Messenger;
    //   30: aload_0
    //   31: getfield 104	com/baidu/location/LocationClient:k	Landroid/os/Messenger;
    //   34: aload_1
    //   35: invokevirtual 308	android/os/Messenger:send	(Landroid/os/Message;)V
    //   38: aload_0
    //   39: getfield 102	com/baidu/location/LocationClient:j	Landroid/content/Context;
    //   42: aload_0
    //   43: getfield 165	com/baidu/location/LocationClient:t	Landroid/content/ServiceConnection;
    //   46: invokevirtual 446	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   49: aload_0
    //   50: getfield 136	com/baidu/location/LocationClient:jdField_new	Ljava/lang/Object;
    //   53: astore_3
    //   54: aload_3
    //   55: monitorenter
    //   56: aload_0
    //   57: getfield 128	com/baidu/location/LocationClient:g	Z
    //   60: iconst_1
    //   61: if_icmpne +19 -> 80
    //   64: aload_0
    //   65: getfield 111	com/baidu/location/LocationClient:jdField_do	Lcom/baidu/location/LocationClient$a;
    //   68: aload_0
    //   69: getfield 130	com/baidu/location/LocationClient:jdField_byte	Lcom/baidu/location/LocationClient$b;
    //   72: invokevirtual 352	com/baidu/location/LocationClient$a:removeCallbacks	(Ljava/lang/Runnable;)V
    //   75: aload_0
    //   76: iconst_0
    //   77: putfield 128	com/baidu/location/LocationClient:g	Z
    //   80: aload_3
    //   81: monitorexit
    //   82: aload_0
    //   83: getfield 138	com/baidu/location/LocationClient:jdField_else	Lcom/baidu/location/i;
    //   86: invokevirtual 448	com/baidu/location/i:jdField_if	()V
    //   89: aload_0
    //   90: aconst_null
    //   91: putfield 104	com/baidu/location/LocationClient:k	Landroid/os/Messenger;
    //   94: invokestatic 449	com/baidu/location/j:jdField_new	()V
    //   97: aload_0
    //   98: iconst_0
    //   99: putfield 100	com/baidu/location/LocationClient:q	Z
    //   102: return
    //   103: astore_2
    //   104: aload_2
    //   105: invokevirtual 328	java/lang/Exception:printStackTrace	()V
    //   108: goto -70 -> 38
    //   111: astore 5
    //   113: aload_3
    //   114: monitorexit
    //   115: aload 5
    //   117: athrow
    //   118: astore 4
    //   120: goto -40 -> 80
    //
    // Exception table:
    //   from	to	target	type
    //   30	38	103	java/lang/Exception
    //   56	80	111	finally
    //   80	82	111	finally
    //   113	115	111	finally
    //   56	80	118	java/lang/Exception
  }

  private void jdField_int(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      return;
    BDNotifyListener localBDNotifyListener = (BDNotifyListener)paramMessage.obj;
    this.jdField_else.jdField_do(localBDNotifyListener);
  }

  private void jdField_new()
  {
    if (this.k == null)
    {
      j.jdField_if("baidu_location_Client", "server not connected");
      return;
    }
    Message localMessage = Message.obtain(null, 22);
    try
    {
      localMessage.replyTo = this.i;
      this.k.send(localMessage);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void jdField_new(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      return;
    BDLocationListener localBDLocationListener = (BDLocationListener)paramMessage.obj;
    if (this.C == null)
      this.C = new ArrayList();
    this.C.add(localBDLocationListener);
  }

  private void jdField_try()
  {
    Message localMessage = Message.obtain(null, 28);
    try
    {
      localMessage.replyTo = this.i;
      this.k.send(localMessage);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void jdField_try(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      return;
    this.c = ((BDLocationListener)paramMessage.obj);
  }

  public BDLocation getLastKnownLocation()
  {
    return this.x;
  }

  public LocationClientOption getLocOption()
  {
    return this.l;
  }

  public String getVersion()
  {
    return this.e;
  }

  public boolean isStarted()
  {
    return this.q;
  }

  public void registerLocationListener(BDLocationListener paramBDLocationListener)
  {
    Message localMessage = this.jdField_do.obtainMessage(5);
    localMessage.obj = paramBDLocationListener;
    localMessage.sendToTarget();
  }

  public void registerNotify(BDNotifyListener paramBDNotifyListener)
  {
    Message localMessage = this.jdField_do.obtainMessage(9);
    localMessage.obj = paramBDNotifyListener;
    localMessage.sendToTarget();
  }

  public void registerNotifyLocationListener(BDLocationListener paramBDLocationListener)
  {
    Message localMessage = this.jdField_do.obtainMessage(8);
    localMessage.obj = paramBDLocationListener;
    localMessage.sendToTarget();
  }

  public void removeNotifyEvent(BDNotifyListener paramBDNotifyListener)
  {
    Message localMessage = this.jdField_do.obtainMessage(10);
    localMessage.obj = paramBDNotifyListener;
    localMessage.sendToTarget();
  }

  public int requestLocation()
  {
    if ((this.k == null) || (this.i == null))
      return 1;
    if ((this.C == null) || (this.C.size() < 1))
      return 2;
    if (System.currentTimeMillis() - this.r < 1000L)
      return 6;
    j.jdField_if("baidu_location_Client", "request location ...");
    this.jdField_do.obtainMessage(4).sendToTarget();
    return 0;
  }

  public void requestNotifyLocation()
  {
    this.jdField_do.obtainMessage(11).sendToTarget();
  }

  public int requestOfflineLocation()
  {
    if ((this.k == null) || (this.i == null))
      return 1;
    if ((this.C == null) || (this.C.size() < 1))
      return 2;
    this.jdField_do.obtainMessage(12).sendToTarget();
    return 0;
  }

  public int requestPoi()
  {
    int i1 = 7;
    if ((this.k == null) || (this.i == null))
      i1 = 1;
    do
    {
      return i1;
      if ((this.C == null) || (this.C.size() < 1))
        return 2;
      if (System.currentTimeMillis() - this.jdField_char < 6000L)
        return 6;
    }
    while (this.l.a < 1);
    j.jdField_if("baidu_location_Client", "request location ...");
    this.jdField_do.obtainMessage(i1).sendToTarget();
    return 0;
  }

  public void setForBaiduMap(boolean paramBoolean)
  {
    this.jdField_long = Boolean.valueOf(paramBoolean);
  }

  public void setLocOption(LocationClientOption paramLocationClientOption)
  {
    if (paramLocationClientOption == null)
      paramLocationClientOption = new LocationClientOption();
    Message localMessage = this.jdField_do.obtainMessage(3);
    localMessage.obj = paramLocationClientOption;
    localMessage.sendToTarget();
  }

  public void start()
  {
    this.jdField_do.obtainMessage(1).sendToTarget();
  }

  public void stop()
  {
    this.jdField_do.obtainMessage(2).sendToTarget();
  }

  public void unRegisterLocationListener(BDLocationListener paramBDLocationListener)
  {
    Message localMessage = this.jdField_do.obtainMessage(6);
    localMessage.obj = paramBDLocationListener;
    localMessage.sendToTarget();
  }

  public boolean updateLocation(Location paramLocation)
  {
    if ((this.k == null) || (this.i == null) || (paramLocation == null))
      return false;
    try
    {
      Message localMessage = Message.obtain(null, 57);
      localMessage.obj = paramLocation;
      this.k.send(localMessage);
      return true;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private class a extends Handler
  {
    private a()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
      case 3:
      case 8:
      case 5:
      case 6:
      case 9:
      case 10:
      case 1:
      case 2:
      case 11:
      case 4:
      case 12:
      case 7:
      case 54:
      case 55:
        do
        {
          do
          {
            return;
            LocationClient.jdMethod_do(LocationClient.this, paramMessage);
            return;
            LocationClient.jdMethod_if(LocationClient.this, paramMessage);
            return;
            LocationClient.a(LocationClient.this, paramMessage);
            return;
            LocationClient.jdMethod_try(LocationClient.this, paramMessage);
            return;
            LocationClient.jdMethod_new(LocationClient.this, paramMessage);
            return;
            LocationClient.jdMethod_for(LocationClient.this, paramMessage);
            return;
            LocationClient.jdMethod_if(LocationClient.this);
            return;
            LocationClient.jdMethod_new(LocationClient.this);
            return;
            LocationClient.jdMethod_case(LocationClient.this);
            return;
            LocationClient.a(LocationClient.this);
            return;
            LocationClient.jdMethod_for(LocationClient.this);
            return;
            LocationClient.jdMethod_goto(LocationClient.this);
            return;
          }
          while (!LocationClient.jdMethod_byte(LocationClient.this).jdField_void);
          LocationClient.jdMethod_if(LocationClient.this, true);
          return;
        }
        while (!LocationClient.jdMethod_byte(LocationClient.this).jdField_void);
        LocationClient.jdMethod_if(LocationClient.this, false);
        return;
      case 21:
        LocationClient.a(LocationClient.this, paramMessage, 21);
        return;
      case 26:
        LocationClient.a(LocationClient.this, paramMessage, 26);
        return;
      case 27:
      }
      LocationClient.jdMethod_int(LocationClient.this, paramMessage);
    }
  }

  private class b
    implements Runnable
  {
    private b()
    {
    }

    public void run()
    {
      synchronized (LocationClient.jdMethod_do(LocationClient.this))
      {
        LocationClient.jdMethod_for(LocationClient.this, false);
        if ((LocationClient.jdMethod_char(LocationClient.this) == null) || (LocationClient.jdMethod_int(LocationClient.this) == null))
          return;
        if ((LocationClient.jdMethod_else(LocationClient.this) == null) || (LocationClient.jdMethod_else(LocationClient.this).size() < 1))
          return;
      }
      j.jdMethod_if("baidu_location_Client", "request location ...");
      LocationClient.jdMethod_try(LocationClient.this).obtainMessage(4).sendToTarget();
      monitorexit;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.LocationClient
 * JD-Core Version:    0.6.0
 */