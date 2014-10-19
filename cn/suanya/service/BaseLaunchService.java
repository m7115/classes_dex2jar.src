package cn.suanya.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.IBinder;
import cn.suanya.common.a.l;
import cn.suanya.common.a.n;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class BaseLaunchService extends Service
{
  protected SYApplication app;
  private Thread thr;

  private void work()
  {
    JSONObject localJSONObject2;
    try
    {
      JSONObject localJSONObject1 = launchToServer();
      if (localJSONObject1 == null)
        return;
      localJSONObject2 = localJSONObject1.optJSONObject("data");
      if (localJSONObject2 == null)
        return;
      if (this.app.launchInfo == null)
        this.app.launchInfo = localJSONObject2;
      Iterator localIterator = localJSONObject2.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = localJSONObject2.opt(str);
        this.app.launchInfo.put(str, localObject);
      }
    }
    catch (Exception localException)
    {
      n.a("LaunchService:", localException);
      return;
    }
    this.app.saveServiceInfo(this.app.launchInfo);
    this.app.launcObserv.setChanged();
    this.app.launcObserv.notifyObservers(l.launch_finish);
    this.app.setConfigVersion(localJSONObject2.optInt(l.conf_version, 0));
    checkAndLoadRule();
  }

  public void checkAndLoadRule()
    throws Exception
  {
    this.app.initRule();
  }

  public JSONObject launchToServer()
    throws Exception
  {
    return null;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.app = ((SYApplication)getApplication());
    this.thr = new Thread(null, new ServiceWorker(), "BackgroundSercie");
    this.thr.start();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
  }

  public String packages(String paramString)
  {
    new ArrayList();
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    Iterator localIterator = getPackageManager().queryIntentActivities(localIntent, 0).iterator();
    ResolveInfo localResolveInfo;
    for (String str = ""; localIterator.hasNext(); str = str + "|" + localResolveInfo.activityInfo.packageName)
      localResolveInfo = (ResolveInfo)localIterator.next();
    if (str.length() > 0)
      str = str.substring(1);
    return str;
  }

  class ServiceWorker
    implements Runnable
  {
    ServiceWorker()
    {
    }

    public void run()
    {
      BaseLaunchService.this.work();
      BaseLaunchService.this.stopSelf();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.service.BaseLaunchService
 * JD-Core Version:    0.6.0
 */