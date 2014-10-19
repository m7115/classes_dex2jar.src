package com.yipiao;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.suanya.common.a.n;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import cn.suanya.hotel.service.HotelService;
import cn.suanya.rule.bean.Context;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.sdk.openapi.d;
import com.yipiao.base.DefaultExceptionHandler;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.service.HuocheBase;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;
import com.yipiao.service.MonitorSchedule;
import com.yipiao.service.PassengerService;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.yipiao.bean.LoginUser;>;
import java.util.Map;
import org.json.JSONObject;

public class YipiaoApplication extends SYApplication
{
  public static final String APP_ID = "wx3e388e8f02f38759";
  public d api;
  private int apiVersion;
  public int autoSelectApi = 0;
  private ChainQuery cq = new ChainQuery();
  public ProgressDialog mProgressDialog;
  private List<MonitorInfo> monitorPool;
  public PendingIntent monitorServiceIntent;
  public SYApplication.ObservMonitor observable = new SYApplication.ObservMonitor(this);
  public SYApplication.ObservMonitor pathObservable = new SYApplication.ObservMonitor(this);
  public SYApplication.ObservMonitor ruleObservable = new SYApplication.ObservMonitor(this);
  public int successLevel = 1;
  private int tempApiVersion = 0;
  private LoginUser user;

  public static YipiaoApplication getApp()
  {
    return (YipiaoApplication)app;
  }

  private void startMonitorService()
  {
    this.monitorPool = new ArrayList();
    readMonitorPool();
    if (runMonitorNum() > 0)
      startFirstMonitorService(100L + System.currentTimeMillis());
  }

  // ERROR //
  public MonitorInfo addMonitor(MonitorInfo paramMonitorInfo)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 68	com/yipiao/YipiaoApplication:monitorPool	Ljava/util/List;
    //   4: invokeinterface 97 1 0
    //   9: astore 5
    //   11: aload 5
    //   13: invokeinterface 103 1 0
    //   18: istore 6
    //   20: aconst_null
    //   21: astore 7
    //   23: iload 6
    //   25: ifeq +25 -> 50
    //   28: aload 5
    //   30: invokeinterface 107 1 0
    //   35: checkcast 109	com/yipiao/bean/MonitorInfo
    //   38: astore 8
    //   40: aload_1
    //   41: aload 8
    //   43: if_acmpne -32 -> 11
    //   46: aload 8
    //   48: astore 7
    //   50: aload 7
    //   52: ifnonnull +21 -> 73
    //   55: aload_1
    //   56: invokevirtual 113	com/yipiao/bean/MonitorInfo:clone	()Lcom/yipiao/bean/MonitorInfo;
    //   59: astore 7
    //   61: aload_0
    //   62: getfield 68	com/yipiao/YipiaoApplication:monitorPool	Ljava/util/List;
    //   65: iconst_0
    //   66: aload 7
    //   68: invokeinterface 117 3 0
    //   73: aload 7
    //   75: astore_3
    //   76: aload_3
    //   77: iconst_2
    //   78: invokevirtual 121	com/yipiao/bean/MonitorInfo:setStatus	(I)V
    //   81: aload_3
    //   82: areturn
    //   83: astore_2
    //   84: aconst_null
    //   85: astore_3
    //   86: aload_2
    //   87: astore 4
    //   89: aload 4
    //   91: invokevirtual 124	java/lang/CloneNotSupportedException:printStackTrace	()V
    //   94: aload 4
    //   96: invokestatic 130	cn/suanya/common/a/n:b	(Ljava/lang/Throwable;)V
    //   99: aload_3
    //   100: areturn
    //   101: astore 9
    //   103: aload 7
    //   105: astore_3
    //   106: aload 9
    //   108: astore 4
    //   110: goto -21 -> 89
    //   113: astore 4
    //   115: goto -26 -> 89
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	83	java/lang/CloneNotSupportedException
    //   11	20	83	java/lang/CloneNotSupportedException
    //   28	40	83	java/lang/CloneNotSupportedException
    //   55	73	101	java/lang/CloneNotSupportedException
    //   76	81	113	java/lang/CloneNotSupportedException
  }

  public void deleteAllRecentUser()
  {
    this.sp.edit().putString("RecentUserList", "").commit();
  }

  public List<LoginUser> deleteRecentUser(LoginUser paramLoginUser)
  {
    List localList = getRecentUserList();
    localList.remove(paramLoginUser);
    setRecentUserList(localList);
    return localList;
  }

  public int getApiVersion()
  {
    int i = this.launchInfo.optInt("autoSelectApi", 3);
    if (this.autoSelectApi != 0)
      i = this.autoSelectApi;
    int j = this.launchInfo.optInt("forceSelectApi", 0);
    if (this.tempApiVersion != 0)
      i = this.tempApiVersion;
    do
    {
      return i;
      if (j != 0)
        return j;
    }
    while (this.apiVersion == 0);
    return this.apiVersion;
  }

  public String getBaiduKey()
  {
    return "8c035f231dd80a911c5eb3d2af07bd3a";
  }

  public ChainQuery getCq()
  {
    return this.cq;
  }

  public HuocheBase getHC()
  {
    if (isNewApi())
      return HuocheNew.getInstance();
    if (isMobileApi())
      return HuocheMobile.getInstance();
    return HuocheMobile.getInstance();
  }

  public List<MonitorInfo> getMonitorPool()
  {
    return this.monitorPool;
  }

  public int getMonitorQueryTimes()
  {
    Iterator localIterator = this.monitorPool.iterator();
    int i = 0;
    while (localIterator.hasNext())
      i += ((MonitorInfo)localIterator.next()).queryTimes;
    return i;
  }

  public Object getParms(String paramString)
  {
    return this.parms.get(paramString);
  }

  public int getParmsCount()
  {
    if (this.parms == null)
      return 0;
    return this.parms.size();
  }

  public List<LoginUser> getRecentUserList()
  {
    Object localObject = (List)cn.suanya.common.a.i.a(this.sp.getString("RecentUserList", ""), new TypeToken()
    {
    }
    .getType());
    if (localObject == null)
      localObject = new ArrayList();
    return (List<LoginUser>)localObject;
  }

  public LoginUser getUser()
  {
    if (this.user == null)
      return new LoginUser(null, null, null);
    return this.user;
  }

  public String getUserShow()
  {
    if (isVisitor())
      return this.launchInfo.optString("unLoginWarn", "欢迎使用智行火车票！");
    return this.user.getUserLabel() + ",您好!";
  }

  public int getWebViewFlag()
  {
    return this.sp.getInt(Constants.webViewFlag, 0);
  }

  public String[] getserviceUrls()
  {
    return Constants.serviceUrls;
  }

  public boolean hasNewVersion()
  {
    String str = this.launchInfo.optString(Constants.last_version);
    return getVersionName().compareTo(str) < 0;
  }

  public void initRule()
  {
    HuocheNew.getInstance().ruleInit();
    HuocheMobile.getInstance().ruleInit();
    HotelService.instance().ruleInit();
    super.initRule();
  }

  public void insertRecentUser(LoginUser paramLoginUser)
  {
    List localList = getRecentUserList();
    localList.remove(paramLoginUser);
    localList.add(0, paramLoginUser);
    if (localList.size() > 5)
      localList.remove(5);
    setRecentUserList(localList);
  }

  public boolean isAutoApiVersion()
  {
    int i = this.launchInfo.optInt("forceSelectApi", 0);
    int j = this.apiVersion;
    int k = 0;
    if (j == 0)
    {
      k = 0;
      if (i == 0)
      {
        int m = getApiVersion();
        k = 0;
        if (m == 3)
          k = 1;
      }
    }
    return k;
  }

  public boolean isDebug()
  {
    return Constants.isDebug;
  }

  public boolean isLogined()
  {
    return getHC().logined == 1;
  }

  public boolean isMobileApi()
  {
    return getApiVersion() == 3;
  }

  public boolean isNewApi()
  {
    return getApiVersion() == 2;
  }

  public boolean isVisitor()
  {
    return (this.user == null) || (this.user.getUserName() == null) || (this.user.getUserName().length() == 0) || (this.user.getUserName().startsWith("zhixinghuoche"));
  }

  public void onCreate()
  {
    n.a("Application-create1");
    getChannelID();
    this.api = com.tencent.mm.sdk.openapi.i.a(this, "wx3e388e8f02f38759", false);
    this.api.a("wx3e388e8f02f38759");
    Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
    System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
    com.yipiao.service.PersistentObjService.localPath = getFilesDir().getAbsolutePath() + "/";
    n.a("Application-create2");
    super.onCreate();
    n.a("Application-create3");
    this.glob.put("cfg", Config.getInstance());
    this.user = new LoginUser(this.sp.getString("userName", null), this.sp.getString("password", null), this.sp.getString("userLabel", null));
    this.apiVersion = this.sp.getInt("engineSet2", 0);
    readCq();
    startMonitorService();
    n.a("Application-create4");
    new Thread()
    {
      public void run()
      {
        try
        {
          HuocheNew localHuocheNew = HuocheNew.getInstance();
          localHuocheNew.loadCookie();
          if (localHuocheNew.isLogined());
          for (localHuocheNew.logined = 1; YipiaoApplication.this.launchInfo.optBoolean("autoLoginForN", false); localHuocheNew.logined = 2)
          {
            localHuocheNew.autoLogin();
            return;
          }
        }
        catch (Exception localException)
        {
          n.a(localException);
        }
      }
    }
    .start();
    new Thread()
    {
      public void run()
      {
        HuocheMobile localHuocheMobile = HuocheMobile.getInstance();
        try
        {
          localHuocheMobile.init(1);
          YipiaoApplication.this.autoSelectApi = 0;
        }
        catch (Exception localException1)
        {
          try
          {
            while (true)
            {
              if (localHuocheMobile.autoLogin())
                PassengerService.getInstance().syncHistory(localHuocheMobile.queryPassenger(), true);
              return;
              localException1 = localException1;
              YipiaoApplication.this.autoSelectApi = 2;
              n.a(localException1);
            }
          }
          catch (Exception localException2)
          {
            n.a(localException2);
          }
        }
      }
    }
    .start();
    n.a("Application-create");
  }

  protected void onNewVersion()
  {
    this.launchInfo = super.getDefautServiceInfo();
    saveServiceInfo(this.launchInfo);
  }

  public void onTerminate()
  {
    n.b("Application--onTerminate");
    saveCq();
    saveMonitorPool();
    super.onTerminate();
  }

  public void putParms(String paramString, Object paramObject)
  {
    this.parms.put(paramString, paramObject);
  }

  public void readCq()
  {
    ChainQuery localChainQuery = new ChainQuery();
    try
    {
      String str = this.sp.getString("queryCondition2", null);
      if ((str != null) && (str.length() > 10))
        this.cq = ((ChainQuery)cn.suanya.common.a.i.a(str, new TypeToken()
        {
        }
        .getType()));
      if (this.cq == null)
        this.cq = localChainQuery;
      if (localChainQuery.getLeaveDate().compareTo(this.cq.getLeaveDate()) > 0)
        this.cq.setLeaveDate(localChainQuery.getLeaveDate());
      this.cq.setTimeRang("00:00--24:00");
      return;
    }
    catch (Exception localException)
    {
      this.cq = localChainQuery;
    }
  }

  public void readMonitorPool()
  {
    String str = this.sp.getString("jsonMonitorPool", null);
    if ((str != null) && (str.length() > 10))
      n.b("readMonitorPool-length-" + str.length());
    this.monitorPool = ((List)cn.suanya.common.a.i.a(str, new TypeToken()
    {
    }
    .getType()));
    if (this.monitorPool == null)
      this.monitorPool = new ArrayList();
    n.b("readMonitorPool-size-" + this.monitorPool.size());
    for (int i = -1 + this.monitorPool.size(); i >= 0; i--)
    {
      MonitorInfo localMonitorInfo = (MonitorInfo)this.monitorPool.get(i);
      int j = MonitorInfo._id;
      MonitorInfo._id = j + 1;
      localMonitorInfo.setId(j);
    }
    n.b("readMonitorPool2-size-" + this.monitorPool.size());
  }

  public void removeMonitor(int paramInt)
  {
    this.monitorPool.remove(paramInt);
  }

  public int runMonitorNum()
  {
    if (this.monitorPool == null)
      return 0;
    Iterator localIterator = this.monitorPool.iterator();
    int i = 0;
    if (localIterator.hasNext())
      if (!((MonitorInfo)localIterator.next()).isRuning())
        break label56;
    label56: for (int j = i + 1; ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  public void saveCq()
  {
    SharedPreferences.Editor localEditor = this.sp.edit();
    List localList = this.cq.findResults();
    this.cq.setResults(null);
    String str = cn.suanya.common.a.i.a(this.cq);
    this.cq.setResults(localList);
    localEditor.putString("queryCondition2", str);
    localEditor.commit();
  }

  public void saveMonitorPool()
  {
    int i = 0;
    if (this.monitorPool == null);
    while (true)
    {
      return;
      Object[] arrayOfObject = new Object[this.monitorPool.size()];
      for (int j = 0; j < arrayOfObject.length; j++)
      {
        MonitorInfo localMonitorInfo = (MonitorInfo)this.monitorPool.get(j);
        arrayOfObject[j] = localMonitorInfo.getCq().findResults();
        localMonitorInfo.getCq().setResults(null);
      }
      String str = cn.suanya.common.a.i.a(this.monitorPool);
      SharedPreferences.Editor localEditor = this.sp.edit();
      localEditor.putString("jsonMonitorPool", str);
      localEditor.commit();
      while (i < arrayOfObject.length)
      {
        ((MonitorInfo)this.monitorPool.get(i)).getCq().setResults((List)arrayOfObject[i]);
        i++;
      }
    }
  }

  public void setApiVersion(int paramInt)
  {
    this.apiVersion = paramInt;
  }

  public void setCq(ChainQuery paramChainQuery)
  {
    this.cq = paramChainQuery;
  }

  public void setRecentUserList(List<LoginUser> paramList)
  {
    if (paramList == null)
      return;
    this.sp.edit().putString("RecentUserList", cn.suanya.common.a.i.a(paramList)).commit();
  }

  public void setTempApiVersion(int paramInt)
  {
    this.tempApiVersion = paramInt;
  }

  public void setUser(LoginUser paramLoginUser)
  {
    this.sp.edit().putString("userLabel", paramLoginUser.getUserLabel()).putString("password", paramLoginUser.getPassword()).putString("userName", paramLoginUser.getUserName()).commit();
    this.user = paramLoginUser;
  }

  public void setWebViewFlag(int paramInt)
  {
    this.sp.edit().putInt(Constants.webViewFlag, paramInt).commit();
  }

  public void startFirstMonitorService(long paramLong)
  {
    MonitorSchedule.getInstance().startMonitor(paramLong);
  }

  public void stopMonitor(MonitorInfo paramMonitorInfo)
  {
    paramMonitorInfo.setStatus(1);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.YipiaoApplication
 * JD-Core Version:    0.6.0
 */