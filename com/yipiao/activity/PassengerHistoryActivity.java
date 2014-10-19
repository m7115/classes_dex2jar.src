package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import cn.suanya.common.net.LogInfo;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.PassengerHistoryListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.UserInfo;
import com.yipiao.bean.UserInfoComparator;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import java.util.Collections;
import java.util.List;

public class PassengerHistoryActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  protected BaseAdapter adapter;
  protected ListView mListView;

  public void checkLoginForSyncPassenger()
  {
    checkForLogin(2130903181, "登录后才能同步购票人信息");
  }

  protected BaseAdapter createAdapter()
  {
    return new PassengerHistoryListAdapter(this, this.passengerService.getHistoryUsers(), 2130903129);
  }

  protected String getDefautRemark()
  {
    return "";
  }

  protected int getMainLayout()
  {
    return 2130903128;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 2131296996) && (paramInt2 != 0))
    {
      if ((UserInfo)this.app.getParms("passenger") == null)
        return;
      this.adapter.notifyDataSetChanged();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296996:
      this.app.putParms("passenger", new UserInfo());
      startActivityForResult(new Intent(this, PassengerEditActivity.class), 2131296996);
      return;
    case 2131296994:
      checkLoginForSyncPassenger();
      return;
    case 2131296995:
    }
    this.passengerService.getHistoryUsers().removeAll(this.passengerService.getHistoryUsers());
    this.adapter.notifyDataSetChanged();
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 0)
    {
      int i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
      UserInfo localUserInfo = (UserInfo)this.passengerService.getHistoryUsers().get(i);
      this.passengerService.removeHistory(localUserInfo);
      this.adapter.notifyDataSetChanged();
    }
    return super.onContextItemSelected(paramMenuItem);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.adapter = createAdapter();
    this.mListView = ((ListView)findViewById(2131296993));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    this.mListView.setOnCreateContextMenuListener(this);
    sort();
    setClick(2131296996, this);
    setClick(2131296994, this);
    setClick(2131296995, this);
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    paramContextMenu.add(0, 0, 0, "删除");
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.app.putParms("passenger", paramView.getTag());
    startActivityForResult(new Intent(this, PassengerEditActivity.class), 2131296996);
  }

  protected void onLoginSuccess(int paramInt)
  {
    syncPassenger();
    super.onLoginSuccess(paramInt);
  }

  public void sort()
  {
    try
    {
      Collections.sort(this.passengerService.getHistoryUsers(), new UserInfoComparator());
      this.mListView.setSelection(0);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        logToServer(new LogInfo("sortErrorQuery", localException));
    }
  }

  public void syncPassenger()
  {
    new MyAsyncTask(this, true)
    {
      protected List<UserInfo> myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PassengerHistoryActivity.this.getHc().queryPassenger();
      }

      protected void myPostExecute(List<UserInfo> paramList)
      {
        int i = PassengerHistoryActivity.this.passengerService.syncHistory(paramList, false);
        PassengerHistoryActivity.this.sort();
        PassengerHistoryActivity.this.adapter.notifyDataSetChanged();
        PassengerHistoryActivity.this.showToast("同步了" + i + "个购票人信息");
      }

      protected void onException(Exception paramException)
      {
      }
    }
    .execute(new Object[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PassengerHistoryActivity
 * JD-Core Version:    0.6.0
 */