package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.common.a.v;
import cn.suanya.hc.service.PathService;
import com.example.pathview.ResultActivity;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.AdvanceSeatListAdapter;
import com.yipiao.adapter.SeatListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.Train;
import com.yipiao.service.AdvancedQueryService;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdvancedQueryActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  protected SeatListAdapter adapter;
  private String broadcastIntentAction = "com.yipiao.AdvancedQuery";
  private Button btnStop;
  protected ChainQuery cq;

  @SuppressLint({"HandlerLeak"})
  protected Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      ((NotificationManager)AdvancedQueryActivity.this.getSystemService("notification")).cancel(AdvancedQueryService.notifyId);
      String str = paramMessage.getData().getString("status");
      AdvancedQueryActivity.this.statusLabel.setText(str);
      if (AdvancedQueryService.running)
        AdvancedQueryActivity.this.btnStop.setVisibility(0);
      while (true)
      {
        List localList = (List)AdvancedQueryActivity.this.app.getParms("advancedResult");
        if (localList != null)
        {
          AdvancedQueryActivity.this.seatList.clear();
          AdvancedQueryActivity.this.seatList.addAll(localList);
        }
        AdvancedQueryActivity.this.adapter.notifyDataSetChanged();
        return;
        AdvancedQueryActivity.this.btnStop.setVisibility(4);
      }
    }
  };
  protected ListView mListView;
  private Train mTrain;
  private ResultReceiver receiver;
  protected List<Train> seatList;
  private TextView statusLabel;

  private void goToTrainDetail(Train paramTrain)
  {
    Intent localIntent = new Intent(this, TrainDetailActivity.class);
    this.app.putParms("order", null);
    this.app.putParms("train", paramTrain);
    this.app.putParms("cq", this.cq);
    startActivity(localIntent);
  }

  private void goToTrainTime(Train paramTrain)
  {
    Intent localIntent = new Intent(this, ResultActivity.class);
    localIntent.putExtra("code", paramTrain.getCode());
    startActivity(localIntent);
  }

  private void stopService()
  {
    AdvancedQueryService.running = false;
  }

  public void bookTrains(Train paramTrain, List<Train> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Train localTrain = (Train)localIterator.next();
      if (!paramTrain.equals(localTrain))
        continue;
      goToBookSimple(this.cq, localTrain);
    }
  }

  protected int getMainLayout()
  {
    return 2130903063;
  }

  public void goToBook(ChainQuery paramChainQuery, Train paramTrain)
  {
    stopService();
    this.mTrain = paramTrain;
    checkForLogin(2130903181, "请先登陆！");
  }

  public void goToBookSimple(ChainQuery paramChainQuery, Train paramTrain)
  {
    this.app.putParms("bookPassengers", PassengerService.getInstance().getCurrUsers());
    this.app.putParms("isNormalBook", Boolean.valueOf(true));
    this.app.putParms("train", paramTrain);
    StationNode localStationNode1 = PathService.getInstance().getStationInfoByCode(paramTrain.getFromCode());
    StationNode localStationNode2 = PathService.getInstance().getStationInfoByCode(paramTrain.getToCode());
    if (localStationNode1 != null)
      paramChainQuery.setOrg(localStationNode1);
    if (localStationNode2 != null)
      paramChainQuery.setArr(localStationNode2);
    this.app.putParms("chainQuery", paramChainQuery);
    this.app.putParms("passengers", this.passengerService.getCurrUsers());
    startActivity(new Intent(this, BookActivity.class));
  }

  public void init()
  {
    if (checkNeedLaunch())
      return;
    ((NotificationManager)getSystemService("notification")).cancel(AdvancedQueryService.notifyId);
    this.receiver = new ResultReceiver();
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction(this.broadcastIntentAction);
    registerReceiver(this.receiver, localIntentFilter);
    this.cq = ((ChainQuery)this.app.getParms("advanceCq"));
    this.seatList = new ArrayList();
    List localList = (List)this.app.getParms("advancedResult");
    if (localList != null)
      this.seatList.addAll(localList);
    this.adapter = new AdvanceSeatListAdapter(this, this.seatList, 2130903162, this.cq);
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    this.btnStop = ((Button)findViewById(2131296259));
    this.btnStop.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AdvancedQueryActivity.this.stopService();
        AdvancedQueryActivity.this.showToast("智能查询已停止");
      }
    });
    if (AdvancedQueryService.running)
      this.btnStop.setVisibility(0);
    while (true)
    {
      super.init();
      this.statusLabel = setTv(2131296689, "共查到" + this.seatList.size() + "个车次");
      setTv(2131296691, v.a(this.cq.getLeaveDate()));
      return;
      this.btnStop.setVisibility(4);
    }
  }

  protected void onDestroy()
  {
    if (this.receiver != null)
      unregisterReceiver(this.receiver);
    super.onDestroy();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    goToTrainDetail((Train)paramView.getTag());
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2130903181)
    {
      if (this.mTrain.isLogined())
        break label30;
      queryForBook(this.mTrain);
    }
    while (true)
    {
      super.onLoginSuccess(paramInt);
      return;
      label30: goToBookSimple(this.cq, this.mTrain);
    }
  }

  public void queryForBook(Train paramTrain)
  {
    new MyAsyncTask(this, paramTrain)
    {
      protected Object myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        AdvancedQueryActivity.this.monitorWaitForQuery();
        ChainQuery localChainQuery = new ChainQuery();
        localChainQuery.setOrg(PathService.getInstance().getStationInfoByCode(this.val$train.getFromCode()));
        localChainQuery.setArr(PathService.getInstance().getStationInfoByCode(this.val$train.getToCode()));
        localChainQuery.setStudent(AdvancedQueryActivity.this.cq.getStudent());
        localChainQuery.setLeaveDate(AdvancedQueryActivity.this.cq.getLeaveDate());
        localChainQuery.setStudent(AdvancedQueryActivity.this.cq.getStudent());
        return AdvancedQueryActivity.this.getHc().queryPiaoCommon(AdvancedQueryActivity.this.cq);
      }

      protected void myPostExecute(Object paramObject)
      {
        if ((paramObject instanceof String))
          AdvancedQueryActivity.this.showToast((String)paramObject);
        do
        {
          return;
          AdvancedQueryActivity.this.seatList = ((List)paramObject);
        }
        while (this.val$train == null);
        AdvancedQueryActivity.this.bookTrains(this.val$train, AdvancedQueryActivity.this.seatList);
      }
    }
    .execute(new String[0]);
  }

  class ResultReceiver extends BroadcastReceiver
  {
    ResultReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Message localMessage = new Message();
      Bundle localBundle = new Bundle();
      String str = paramIntent.getStringExtra("status");
      if (str != null)
        localBundle.putString("status", str);
      localMessage.setData(localBundle);
      AdvancedQueryActivity.this.mHandler.sendMessage(localMessage);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.AdvancedQueryActivity
 * JD-Core Version:    0.6.0
 */