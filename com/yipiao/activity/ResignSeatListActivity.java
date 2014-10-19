package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.ResignSeatListAdapter;
import com.yipiao.adapter.SeatListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.Train;
import com.yipiao.service.Huoche;
import java.util.ArrayList;
import java.util.List;

public class ResignSeatListActivity extends SeatListActivity
{
  private ArrayList<OrderItem> order;

  public void addMonitor(boolean paramBoolean)
  {
    MonitorInfo localMonitorInfo = new MonitorInfo(this.cq);
    localMonitorInfo.setResign(true);
    localMonitorInfo.setOrders(this.order);
    Intent localIntent = new Intent(this, MonitorSetActivity.class);
    this.app.putParms("mi", localMonitorInfo);
    startActivity(localIntent);
  }

  protected void createAdapter()
  {
    this.adapter = new ResignSeatListAdapter(this, this.seatList, 2130903162, this.cq, this.order);
  }

  protected int getMainLayout()
  {
    return 2130903158;
  }

  protected void goToTrainDetail(Train paramTrain)
  {
    this.app.putParms("order", this.order);
    super.goToTrainDetail(paramTrain);
  }

  public void init()
  {
    if (checkNeedLaunch())
      return;
    this.cq = ((ChainQuery)this.app.getParms("cq"));
    this.order = ((ArrayList)this.app.getParms("order"));
    if (this.seatList == null)
      query(true);
    setClick(2131296889, this);
    super.init();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      super.onClick(paramView);
      return;
    case 2131296889:
    }
    addMonitor(true);
  }

  public void query()
  {
    query(false);
  }

  public void query(boolean paramBoolean)
  {
    new MyAsyncTask(this, paramBoolean)
    {
      protected Object myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        if (this.val$isFirst)
          ResignSeatListActivity.this.getHc().resignReady(ResignSeatListActivity.this.order);
        ResignSeatListActivity.this.monitorWaitForQuery();
        return ResignSeatListActivity.this.getHc().resignQueryPiao(ResignSeatListActivity.this.cq);
      }

      protected void myPostExecute(Object paramObject)
      {
        if ((paramObject instanceof String))
        {
          ResignSeatListActivity.this.showToast((String)paramObject);
          return;
        }
        ResignSeatListActivity.this.seatList = ((List)paramObject);
        ResignSeatListActivity.this.cq.setResults(ResignSeatListActivity.this.seatList);
        ResignSeatListActivity.this.sort();
        ResignSeatListActivity.this.adapter.setMlist(ResignSeatListActivity.this.seatList);
        ResignSeatListActivity.this.adapter.notifyDataSetChanged();
      }

      protected void onException(Exception paramException)
      {
        if (this.val$isFirst)
          ResignSeatListActivity.this.finish();
        super.onException(paramException);
      }
    }
    .execute(new String[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.ResignSeatListActivity
 * JD-Core Version:    0.6.0
 */