package com.yipiao.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.ResignSeatListAdapter;
import com.yipiao.bean.ChainComparator;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.MiComparator;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.Train;

public class MonitorSeatListActivity extends SeatListActivity
{
  private MonitorInfo mi;
  private NotificationManager nmgr;

  protected ChainComparator<Train> comparatorInit()
  {
    ChainComparator localChainComparator = new ChainComparator();
    localChainComparator.add(new MiComparator(this.mi));
    return localChainComparator;
  }

  protected void createAdapter()
  {
    if (this.mi.isResign())
    {
      this.adapter = new ResignSeatListAdapter(this, this.seatList, 2130903162, this.cq, this.mi.getOrders(), true);
      return;
    }
    super.createAdapter();
  }

  protected int getMainLayout()
  {
    return 2130903108;
  }

  public void goToBookSimple(Train paramTrain)
  {
    this.app.putParms("passengers", this.mi.getPassengers());
    this.app.putParms("isNormalBook", Boolean.valueOf(false));
    this.app.putParms("train", paramTrain);
    this.app.putParms("chainQuery", this.cq);
    startActivity(new Intent(this, BookActivity.class));
  }

  public void init()
  {
    this.nmgr = ((NotificationManager)getSystemService("notification"));
    this.nmgr.cancel(0);
    if (checkNeedLaunch())
      return;
    this.mi = ((MonitorInfo)this.app.getParms("mi"));
    this.app.setTempApiVersion(this.mi.getHcApi());
    this.cq = this.mi.getCq();
    try
    {
      this.cq = this.cq.clone();
      label81: this.seatList = this.cq.findResults();
      if (this.seatList == null)
        query();
      super.init();
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      break label81;
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.app.putParms("order", this.mi.getOrders());
    super.onItemClick(paramAdapterView, paramView, paramInt, paramLong);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (getParent() == null)
      {
        MainTab.currentTab = 2131296873;
        startActivity(new Intent(this, MainTab.class));
      }
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.MonitorSeatListActivity
 * JD-Core Version:    0.6.0
 */