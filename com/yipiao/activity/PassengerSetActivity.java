package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.PassengerListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import com.yipiao.view.MyAlertDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PassengerSetActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  protected BaseAdapter adapter;
  private View addChild;
  protected ListView mListView;
  protected List<UserInfo> passengers;
  protected MyAlertDialog passengersDialog;
  public String seatType;

  private void addToPassengers(UserInfo paramUserInfo, int paramInt)
  {
    if (this.passengers.size() >= 5)
    {
      showToast("最多只能添加5个购票人！");
      return;
    }
    this.passengers.add(paramInt, paramUserInfo);
  }

  private UserInfo getPassengerForChild()
  {
    for (int i = this.passengers.size(); i > 0; i--)
    {
      UserInfo localUserInfo = (UserInfo)this.passengers.get(i - 1);
      if ("1".equals(localUserInfo.getTickType()))
        return localUserInfo;
    }
    return null;
  }

  private void showAddChildBt()
  {
    if (this.addChild == null)
      return;
    if (getPassengerForChild() == null)
    {
      this.addChild.setVisibility(8);
      return;
    }
    this.addChild.setVisibility(0);
  }

  public void addChildPassenger()
  {
    UserInfo localUserInfo1 = getPassengerForChild();
    if (localUserInfo1 != null)
    {
      UserInfo localUserInfo2 = localUserInfo1.clone();
      localUserInfo2.setTickType("2");
      addToPassengers(localUserInfo2, 1 + this.passengers.indexOf(localUserInfo1));
      this.adapter.notifyDataSetChanged();
    }
  }

  protected BaseAdapter createAdapter()
  {
    return new PassengerListAdapter(this, this.passengers, 2130903131);
  }

  protected int getMainLayout()
  {
    return 2130903134;
  }

  public void init()
  {
    super.init();
    this.passengers = ((List)this.app.getParms("passengers"));
    if (this.passengers == null)
      this.passengers = new ArrayList();
    this.adapter = createAdapter();
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    setClick(2131296259, this);
    this.addChild = setClick(2131296965, this);
    showAddChildBt();
    setClick(2131296966, this);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2131296966)
    {
      List localList = (List)this.app.getParms("selectList");
      for (int i = -1 + this.passengers.size(); i >= 0; i--)
      {
        if (localList.contains((UserInfo)this.passengers.get(i)))
          continue;
        this.passengers.remove(i);
      }
      localList.removeAll(this.passengers);
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        UserInfo localUserInfo = (UserInfo)localIterator.next();
        this.passengers.add(localUserInfo);
        onAddCurrentPassenger(localUserInfo);
      }
      showAddChildBt();
      this.adapter.notifyDataSetChanged();
    }
    if ((paramInt1 == 2130903131) && (paramInt2 != 0))
      this.adapter.notifyDataSetChanged();
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  protected void onAddCurrentPassenger(UserInfo paramUserInfo)
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296966:
      showPassengerDialog();
      return;
    case 2131296965:
      addChildPassenger();
      return;
    case 2131296259:
    }
    finish();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int i = this.passengers.indexOf(paramView.getTag());
    this.app.putParms("passenger", this.passengers.get(i));
    startActivityForResult(new Intent(this, PassengerEditActivity.class), 2130903131);
  }

  protected void onLoginSuccess(int paramInt)
  {
    1 local1 = new MyAsyncTask(this, true)
    {
      protected List<UserInfo> myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PassengerSetActivity.this.getHc().queryPassenger();
      }

      protected void myPostExecute(List<UserInfo> paramList)
      {
        int i = PassengerSetActivity.this.passengerService.syncHistory(paramList, false);
        PassengerSetActivity.this.adapter.notifyDataSetChanged();
        PassengerSetActivity.this.showToast("同步了" + i + "个购票人信息");
        PassengerSetActivity.this.showPassengerDialog();
      }

      protected void onException(Exception paramException)
      {
      }
    };
    if (paramInt == 2131297065)
      local1.execute(new Object[0]);
    super.onLoginSuccess(paramInt);
  }

  protected void onPassengerSetEnd()
  {
    this.passengerService.setCurrUsers(this.passengers);
  }

  protected void onPause()
  {
    onPassengerSetEnd();
    super.onPause();
  }

  public void removePassenger(UserInfo paramUserInfo)
  {
    for (int i = -1 + this.passengers.size(); ; i--)
    {
      if (i >= 0)
      {
        if (!paramUserInfo.equals(this.passengers.get(i)))
          continue;
        this.passengers.remove(i);
      }
      showAddChildBt();
      this.adapter.notifyDataSetChanged();
      return;
    }
  }

  public void showPassengerDialog()
  {
    Intent localIntent = new Intent(this, PassengerMultipleSelectActivity.class);
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.passengers);
    this.app.putParms("selectList", localArrayList);
    startActivityForResult(localIntent, 2131296966);
  }

  public void syncPassenger()
  {
    checkForLogin(2131297065);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PassengerSetActivity
 * JD-Core Version:    0.6.0
 */