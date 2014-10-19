package com.yipiao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import cn.suanya.common.a.n;
import cn.suanya.hc.service.PathService;
import com.yipiao.YipiaoApplication;
import com.yipiao.activity.AdvancedQueryActivity;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.Ticket;
import com.yipiao.bean.Train;
import com.yipiao.view.MyToast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdvancedQueryService extends Service
{
  public static int notifyId = 7;
  public static boolean running = false;
  protected YipiaoApplication app = YipiaoApplication.getApp();
  private String broadcastIntentAction = "com.yipiao.AdvancedQuery";
  private Context context;
  protected ChainQuery cq;
  private Intent intent;
  private NotificationManager nm;
  private Notification notification;
  protected List<Train> seatList;
  private Vibrator vibrator;

  private void advanceQuery()
  {
    while (true)
    {
      try
      {
        setPageStatus("开启智能查询");
        setStatus("开启智能查询", "开始查询：" + this.cq.getOrg().getName() + "-" + this.cq.getArr().getName(), false, false);
        JSONArray localJSONArray = this.app.getHC().advanceQueryFirst(this.cq);
        int i = 0;
        if (i >= localJSONArray.length())
          continue;
        boolean bool = running;
        if (!bool)
          return;
        JSONObject localJSONObject = localJSONArray.optJSONObject(i);
        if (localJSONObject != null)
        {
          StationNode localStationNode1 = PathService.getInstance().getStationInfoByName(localJSONObject.optString("from"));
          StationNode localStationNode2 = PathService.getInstance().getStationInfoByName(localJSONObject.optString("to"));
          if ((localStationNode1 != null) && (localStationNode2 != null))
          {
            ChainQuery localChainQuery = this.cq.clone();
            localChainQuery.setOrg(localStationNode1);
            localChainQuery.setArr(localStationNode2);
            setPageStatus("正在查询：" + localChainQuery.getOrg().getName() + "-" + localChainQuery.getArr().getName());
            List localList = this.app.getHC().queryForAdvanced(localChainQuery);
            if (localList != null)
            {
              if (setSeatList(localList) <= 0)
                continue;
              setStatus("查询到" + this.seatList.size() + "个车次！", "查询到" + this.seatList.size() + "个车次！", true, false);
              int j = this.seatList.size();
              if (j >= 20)
                continue;
            }
          }
        }
      }
      catch (Exception localException)
      {
        n.b(localException);
        return;
      }
      finally
      {
        running = false;
        setStatus("查询结束共查到" + this.seatList.size() + "个车次！", "查询结束共查到" + this.seatList.size() + "个车次！", true, true);
        setPageStatus("查询到" + this.seatList.size() + "个车次！");
        stopSelf();
      }
      i++;
    }
  }

  private boolean isInvalidTrain(Train paramTrain)
  {
    Iterator localIterator1 = this.cq.findResults().iterator();
    while (localIterator1.hasNext())
    {
      Train localTrain = (Train)localIterator1.next();
      if (!paramTrain.getCode().equals(localTrain.getCode()))
        continue;
      Iterator localIterator2 = paramTrain.getSeats().iterator();
      while (localIterator2.hasNext())
      {
        Ticket localTicket = (Ticket)localIterator2.next();
        if ((localTicket.getLeftNum() > localTrain.getSeatNum(localTicket.getSeatCode())) && (localTrain.getSeatNum(localTicket.getSeatCode()) < 8))
          return true;
      }
      return false;
    }
    return false;
  }

  private void setPageStatus(String paramString)
  {
    Intent localIntent = new Intent(this.broadcastIntentAction);
    localIntent.putExtra("status", paramString);
    sendBroadcast(localIntent);
  }

  private int setSeatList(List<Train> paramList)
  {
    Iterator localIterator = paramList.iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      Train localTrain = (Train)localIterator.next();
      if (!isInvalidTrain(localTrain))
        break label63;
      this.seatList.add(localTrain);
    }
    label63: for (int j = i + 1; ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  private void setStatus(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    PendingIntent localPendingIntent = PendingIntent.getActivity(this.context, 0, this.intent, 134217728);
    this.notification.tickerText = paramString1;
    this.notification.setLatestEventInfo(this.context, "智能查询", paramString2, localPendingIntent);
    if (paramBoolean1)
      this.nm.cancel(notifyId);
    if (paramBoolean2)
      this.vibrator.vibrate(500L);
    this.nm.notify(notifyId, this.notification);
  }

  public void init()
  {
    running = true;
    this.cq = ((ChainQuery)this.app.getParms("cq"));
    if (this.cq == null)
    {
      stopSelf();
      return;
    }
    try
    {
      this.cq = this.cq.clone();
      label44: this.seatList = new ArrayList();
      this.app.putParms("advanceCq", this.cq);
      this.app.putParms("advancedResult", this.seatList);
      MyToast.makeText(this.context, "智能查询正在后台运行", 0).show();
      this.notification = new Notification();
      this.notification.icon = 2130837694;
      this.notification.when = System.currentTimeMillis();
      this.notification.defaults = 4;
      this.notification.flags = 2;
      this.intent = new Intent(this.context, AdvancedQueryActivity.class);
      new Thread(new Runnable()
      {
        public void run()
        {
          AdvancedQueryService.this.advanceQuery();
        }
      }).start();
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      break label44;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.nm = ((NotificationManager)getSystemService("notification"));
    this.vibrator = ((Vibrator)this.app.getSystemService("vibrator"));
    this.context = this;
  }

  public void onDestroy()
  {
    running = false;
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    init();
    super.onStart(paramIntent, paramInt);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.AdvancedQueryService
 * JD-Core Version:    0.6.0
 */