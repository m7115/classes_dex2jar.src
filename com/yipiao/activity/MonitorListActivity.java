package com.yipiao.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.MonitorListAdapter;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.MonitorInfo;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MonitorListActivity extends LoginActivity
  implements View.OnCreateContextMenuListener, AdapterView.OnItemClickListener, Observer
{
  private MonitorListAdapter adapter;
  protected Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      MonitorListActivity.this.onHandleMessage(paramMessage);
    }
  };
  private ListView mListView;
  private List<MonitorInfo> mp;
  private NotificationManager nmgr;

  private MonitorInfo MonitorForAdd()
  {
    if ((this.mp != null) && (!this.mp.isEmpty()))
    {
      MonitorInfo localMonitorInfo1 = (MonitorInfo)this.mp.get(0);
      try
      {
        MonitorInfo localMonitorInfo2 = initStatus(localMonitorInfo1.clone());
        return localMonitorInfo2;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException2)
      {
      }
    }
    Object localObject = (ChainQuery)this.app.getParms("cq");
    if (localObject == null)
      localObject = new ChainQuery();
    while (true)
    {
      return initStatus(new MonitorInfo((ChainQuery)localObject));
      try
      {
        ChainQuery localChainQuery = ((ChainQuery)localObject).clone();
        localObject = localChainQuery;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException1)
      {
      }
    }
  }

  private void initShareTip()
  {
    setClick(2131296755, new View.OnClickListener(findViewById(2131296755))
    {
      public void onClick(View paramView)
      {
        AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.5F);
        localAlphaAnimation.setDuration(200L);
        localAlphaAnimation.setFillAfter(false);
        this.val$rivWxShare.startAnimation(localAlphaAnimation);
        MonitorListActivity.this.dialogShareToWX();
      }
    });
  }

  private MonitorInfo initStatus(MonitorInfo paramMonitorInfo)
  {
    if (paramMonitorInfo != null)
      paramMonitorInfo.setStatus(1);
    return paramMonitorInfo;
  }

  private void onHandleMessage(Message paramMessage)
  {
    if ((this.mp == null) || (this.mp.isEmpty()))
      setTv(2131296257, "目前没有监控，您可以通过查询设置监控。");
    while (true)
    {
      this.adapter.notifyDataSetChanged();
      return;
      setTv(2131296257, "已进行" + this.app.getMonitorQueryTimes() + "次扫描");
    }
  }

  protected int getMainLayout()
  {
    return 2130903106;
  }

  public void goBook(View paramView)
  {
    MonitorInfo localMonitorInfo = (MonitorInfo)paramView.getTag();
    Intent localIntent = new Intent(this, MonitorSeatListActivity.class);
    this.app.putParms("mi", localMonitorInfo);
    if (localMonitorInfo != null)
      startActivity(localIntent);
  }

  public void goOrder(View paramView)
  {
    checkForLogin(2131296749);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131296259:
    }
    while (true)
    {
      super.onClick(paramView);
      return;
      Intent localIntent = new Intent(this, MonitorSetActivity.class);
      this.app.putParms("mi", MonitorForAdd());
      startActivity(localIntent);
    }
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 0)
    {
      int i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
      this.app.getMonitorPool().remove(this.mp.get(i));
      this.app.saveMonitorPool();
      this.adapter.notifyDataSetChanged();
      if ((this.mp == null) || (this.mp.isEmpty()))
      {
        setTv(2131296257, "");
        setVisibility(2131296257, 8);
      }
    }
    return super.onContextItemSelected(paramMenuItem);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.nmgr = ((NotificationManager)getSystemService("notification"));
    this.nmgr.cancel(1);
    this.mp = this.app.getMonitorPool();
    this.adapter = new MonitorListAdapter(this, this.mp, 2130903107);
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setEmptyView(findViewById(2131296883));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    this.mListView.setOnCreateContextMenuListener(this);
    setClick(2131296930, this);
    setClick(2131296259, this);
    initShareTip();
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    paramContextMenu.add(0, 0, 0, "删除");
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Intent localIntent = new Intent(this, MonitorSetActivity.class);
    this.app.putParms("mi", this.mp.get(paramInt));
    startActivity(localIntent);
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2131296749)
      startActivity(new Intent(this, OrderQueryActivity.class));
    super.onLoginSuccess(paramInt);
  }

  protected void onPause()
  {
    this.app.observable.deleteObserver(this);
    super.onPause();
  }

  protected void onResume()
  {
    if ((this.mp == null) || (this.mp.isEmpty()))
    {
      setTv(2131296257, "");
      setVisibility(2131296257, 8);
    }
    while (true)
    {
      this.adapter.notifyDataSetChanged();
      this.app.observable.addObserver(this);
      super.onResume();
      return;
      setTv(2131296257, "已进行" + this.app.getMonitorQueryTimes() + "次扫描");
    }
  }

  public void update(Observable paramObservable, Object paramObject)
  {
    this.mHandler.sendEmptyMessage(0);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.MonitorListActivity
 * JD-Core Version:    0.6.0
 */