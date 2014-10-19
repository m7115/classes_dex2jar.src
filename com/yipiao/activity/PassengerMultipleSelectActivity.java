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
import com.yipiao.adapter.PassengerMultipleSelectListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.UserInfo;
import com.yipiao.bean.UserInfoComparator;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import java.util.Collections;
import java.util.List;

public class PassengerMultipleSelectActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  protected BaseAdapter adapter;
  private List<UserInfo> mList;
  protected ListView mListView;
  private List<UserInfo> mSelectList;

  private void sort()
  {
    try
    {
      Collections.sort(this.passengerService.getHistoryUsers(), new UserInfoComparator());
      return;
    }
    catch (Exception localException)
    {
      logToServer(new LogInfo("sortErrorQuery", localException));
    }
  }

  public void checkLoginForSyncPassenger()
  {
    checkForLogin(2130903181, "登录后才能同步购票人信息");
  }

  protected BaseAdapter createAdapter()
  {
    return new PassengerMultipleSelectListAdapter(this, this.mList, this.mSelectList, 2130903133);
  }

  protected int getMainLayout()
  {
    return 2130903132;
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
    case 2131296259:
    }
    finish();
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
    this.mSelectList = ((List)this.app.getParms("selectList"));
    this.mList = this.passengerService.getHistoryUsers();
    sort();
    this.adapter = createAdapter();
    this.mListView = ((ListView)findViewById(2131296993));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    this.mListView.setOnCreateContextMenuListener(this);
    setClick(2131296996, this);
    setClick(2131296994, this);
    setClick(2131296259, this);
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

  public void syncPassenger()
  {
    new MyAsyncTask(this, true)
    {
      protected List<UserInfo> myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PassengerMultipleSelectActivity.this.getHc().queryPassenger();
      }

      protected void myPostExecute(List<UserInfo> paramList)
      {
        int i = PassengerMultipleSelectActivity.this.passengerService.syncHistory(paramList, false);
        PassengerMultipleSelectActivity.this.sort();
        PassengerMultipleSelectActivity.this.adapter.notifyDataSetChanged();
        PassengerMultipleSelectActivity.this.showToast("同步了" + i + "个购票人信息");
      }

      protected void onException(Exception paramException)
      {
      }
    }
    .execute(new Object[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PassengerMultipleSelectActivity
 * JD-Core Version:    0.6.0
 */