package com.baidu.mapapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.baidu.mapapi.utils.PermissionCheck;
import com.baidu.platform.comapi.b.e;

public class BMapManager
{
  MKGeneralListener a = null;
  private com.baidu.platform.comapi.a b = null;
  private Context c;
  private Handler d = null;
  private String e = null;
  private String f = null;
  private PermissionCheck g = null;
  private boolean h = false;
  private b i = null;

  static
  {
    System.loadLibrary("BaiduMapVOS_v2_1_3");
    System.loadLibrary("BaiduMapSDK_v2_1_3");
  }

  public BMapManager(Context paramContext)
  {
    this.c = paramContext;
  }

  private void a()
  {
    this.i = new b();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
    if (this.c != null)
      this.c.registerReceiver(this.i, localIntentFilter);
  }

  private void b()
  {
    if ((this.i != null) && (this.c != null))
      this.c.unregisterReceiver(this.i);
  }

  public void destroy()
  {
    if (this.d != null)
    {
      com.baidu.platform.comjni.engine.a.b(2000, this.d);
      com.baidu.platform.comjni.engine.a.b(2010, this.d);
      this.d = null;
    }
    if (this.a != null)
      this.a = null;
    e.b();
    b();
    this.b.c();
    com.baidu.mapapi.search.a.a();
  }

  public Context getContext()
  {
    return this.c;
  }

  public boolean init(String paramString, MKGeneralListener paramMKGeneralListener)
  {
    this.e = paramString;
    try
    {
      this.f = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).applicationInfo.loadLabel(this.c.getPackageManager()).toString();
      if (this.b == null)
        this.b = new com.baidu.platform.comapi.a();
      com.baidu.mapapi.utils.b.a(this.c);
      com.baidu.mapapi.search.a.a(this.c);
      this.a = paramMKGeneralListener;
      this.d = new a(this);
      com.baidu.platform.comjni.engine.a.a(2000, this.d);
      com.baidu.platform.comjni.engine.a.a(2010, this.d);
      if (!this.b.a(this.c))
        return false;
    }
    catch (Exception localException)
    {
      while (true)
        this.f = null;
      a();
      start();
      PermissionCheck.setContext(this.c);
      PermissionCheck.InitParam(paramString, this.f, com.baidu.mapapi.utils.a.a(this.c));
    }
    return PermissionCheck.check();
  }

  public boolean start()
  {
    return this.b.a();
  }

  public boolean stop()
  {
    return this.b.b();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.BMapManager
 * JD-Core Version:    0.6.0
 */