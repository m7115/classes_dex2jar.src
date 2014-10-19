package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.suanya.ui.tableView.SYTableView;
import cn.suanya.ui.tableView.TableItem;
import cn.suanya.ui.tableView.TableItemDelegate;
import cn.suanya.ui.tableView.TableItemGroup;
import cn.suanya.ui.tableView.TableItemGroups;
import cn.suanya.ui.tableView.TableItemSimple;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.service.HuocheBase;

public class My12306Activity extends BaseActivity
  implements TableItemDelegate
{
  TableItemSimple changePwd;
  TableItemSimple passengerHistory;
  private SYTableView tableView;
  TableItemSimple userinfo_edit;

  private TableItemGroups createList()
  {
    this.passengerHistory = new TableItemSimple(this, 2130903173, 0, "购票人管理", "", true);
    this.changePwd = new TableItemSimple(this, 2130903173, 0, "修改密码", "", true);
    this.userinfo_edit = new TableItemSimple(this, 2130903173, 0, "个人信息", "", true);
    TableItem[] arrayOfTableItem = new TableItem[3];
    arrayOfTableItem[0] = this.passengerHistory;
    arrayOfTableItem[1] = this.userinfo_edit;
    arrayOfTableItem[2] = this.changePwd;
    return new TableItemGroups(new TableItemGroup[] { new TableItemGroup(arrayOfTableItem) });
  }

  protected int getMainLayout()
  {
    return 2130903114;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.tableView = ((SYTableView)findViewById(2131296733));
    setClick(2131296916, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        My12306Activity.this.loginOut();
      }
    });
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2130903181)
      goWEB12306Activity("https://kyfw.12306.cn/otn/index/initMy12306", true);
    if (paramInt == 2130903045)
      startActivity(new Intent(this, ChangePwdActivity.class));
    if (paramInt == 2130903182)
      new MyAsyncTask(this, false)
      {
        protected SysUserInfo myInBackground(String[] paramArrayOfString)
          throws Exception
        {
          return My12306Activity.this.app.getHC().getCurrentUserDetail();
        }

        protected void myPostExecute(SysUserInfo paramSysUserInfo)
        {
          My12306Activity.this.app.putParms("passenger", paramSysUserInfo);
          Intent localIntent = new Intent(My12306Activity.this, UserInfoActivity.class);
          localIntent.putExtra("passenger", paramSysUserInfo);
          My12306Activity.this.startActivity(localIntent);
        }
      }
      .execute(new String[0]);
    super.onLoginSuccess(paramInt);
  }

  protected void onResume()
  {
    this.tableView.setUpItems(createList(), 2130903088, 2130837879, 2130837871, 2130837862, 2130837862);
    setTv(2131296916, "退出登陆(" + this.app.getUser().getUserLabel() + ")");
    super.onResume();
  }

  public void onTableItemChanged(TableItem paramTableItem)
  {
  }

  public void onTableItemClick(TableItem paramTableItem)
  {
    if (this.passengerHistory == paramTableItem)
      startActivity(new Intent(this, PassengerHistoryActivity.class));
    while (true)
    {
      if (this.userinfo_edit == paramTableItem)
        checkForLogin(2130903182);
      return;
      if (this.changePwd != paramTableItem)
        continue;
      checkForLogin(2130903045);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.My12306Activity
 * JD-Core Version:    0.6.0
 */