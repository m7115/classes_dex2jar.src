package com.yipiao.activity;

import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import cn.suanya.common.a.n;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.RecentUserAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;
import com.yipiao.service.MonitorBusiness;
import java.util.Iterator;
import java.util.List;

public class UserSetActivity extends LoginActivity
{
  private RecentUserAdapter adapter;
  private List<LoginUser> listRecent;
  private ListView listView;
  private LoginUser oldUser;
  private EditText password;
  private String sign;
  private SYSignView signView;
  private EditText userName;

  public void deleteRecentUser(LoginUser paramLoginUser)
  {
    if (paramLoginUser != null)
    {
      if (paramLoginUser.equals(this.app.getUser()))
      {
        this.userName.setText("");
        this.password.setText("");
        this.app.setUser(new LoginUser(null, null, null));
      }
      this.app.deleteRecentUser(paramLoginUser);
      this.listRecent.remove(paramLoginUser);
      this.adapter.notifyDataSetChanged();
      if (this.listRecent.size() <= 0)
        setVisibility(2131297118, 8);
    }
  }

  public void doLogin(String paramString1, String paramString2)
  {
    this.sign = this.signView.getSign();
    new MyAsyncTask(this, "", "正在登陆...")
    {
      protected LoginUser myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return UserSetActivity.this.login(paramArrayOfString[0], paramArrayOfString[1], UserSetActivity.this.sign);
      }

      protected void myPostExecute(LoginUser paramLoginUser)
      {
        OrderTabActivity.orderQueryStatus(2131296962, true, true);
        UserSetActivity.this.app.setUser(paramLoginUser);
        UserSetActivity.this.showToast(paramLoginUser.getUserLabel() + "登录成功！");
        UserSetActivity.this.app.insertRecentUser(paramLoginUser);
        ((InputMethodManager)UserSetActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(UserSetActivity.this.password.getWindowToken(), 0);
        Iterator localIterator = UserSetActivity.this.app.getMonitorPool().iterator();
        while (localIterator.hasNext())
          ((MonitorInfo)localIterator.next()).getCq().setResults(null);
        UserSetActivity.this.setResult(-1);
        UserSetActivity.this.onAfterLogin();
        UserSetActivity.this.finish();
      }

      protected void onException(Exception paramException)
      {
        if (!UserSetActivity.this.isMobileApi())
          UserSetActivity.this.signView.refreshSign();
        super.onException(paramException);
      }
    }
    .execute(new String[] { paramString1, paramString2 });
  }

  protected int getMainLayout()
  {
    return 2130903181;
  }

  public void init()
  {
    super.init();
    this.signView = ((SYSignView)findViewById(2131296771));
    if (isMobileApi())
      this.signView.setVisibility(8);
    while (true)
    {
      this.userName = ((EditText)findViewById(2131296690));
      this.password = ((EditText)findViewById(2131296692));
      this.oldUser = this.app.getUser();
      if (!this.app.isVisitor())
      {
        this.userName.setText(this.app.getUser().getUserName());
        this.password.setText(this.app.getUser().getPassword());
      }
      setClick(2131297064, this);
      setClick(2131296930, this);
      setClick(2131297117, this);
      initRecentUserList();
      return;
      this.signView.setVisibility(0);
      this.signView.init(2130903166, new SYSignView.SignListenerBase()
      {
        public SYSignView.MulImage load()
          throws Exception
        {
          return UserSetActivity.this.getHc().loginSign();
        }
      });
    }
  }

  protected void initRecentUserList()
  {
    this.listView = ((ListView)findViewById(2131297119));
    this.listRecent = this.app.getRecentUserList();
    if (this.listRecent.size() <= 0)
      setVisibility(2131297118, 8);
    this.adapter = new RecentUserAdapter(this, this.listRecent, 2130903095);
    this.listView.setAdapter(this.adapter);
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        LoginUser localLoginUser = (LoginUser)UserSetActivity.this.adapter.getItem(paramInt);
        UserSetActivity.this.userName.setText(localLoginUser.getUserName());
        UserSetActivity.this.password.setText(localLoginUser.getPassword());
      }
    });
    this.listView.setOnCreateContextMenuListener(this);
  }

  public void onAfterLogin()
  {
    MonitorBusiness.instance().onChangeUser(this.app.getUser());
    new Thread()
    {
      public void run()
      {
        try
        {
          if (UserSetActivity.this.isNewApi())
            HuocheMobile.getInstance().autoLogin();
          if ((UserSetActivity.this.isMobileApi()) && (!UserSetActivity.this.app.getUser().equals(UserSetActivity.this.oldUser)))
            HuocheNew.getInstance().loginOut();
          return;
        }
        catch (Exception localException)
        {
          n.a(localException);
        }
      }
    }
    .start();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296930:
      String str1 = this.userName.getText().toString().trim();
      String str2 = this.password.getText().toString();
      if ((str1.length() < 1) || (str2.length() < 1))
      {
        showToast("请输入帐号和密码！");
        return;
      }
      doLogin(str1, str2);
      return;
    case 2131297064:
      startActivity(new Intent(this, RegisterActivity.class));
      finish();
      return;
    case 2131297117:
    }
    goWEB12306Activity("https://kyfw.12306.cn/otn/forgetPassword/initforgetMyPassword");
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 0)
    {
      int i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
      deleteRecentUser((LoginUser)this.listRecent.get(i));
    }
    return super.onContextItemSelected(paramMenuItem);
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    paramContextMenu.add(0, 0, 0, "删除");
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      MainTab.currentTab = 2131296873;
      startActivity(new Intent(this, MainTab.class));
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onResume()
  {
    if (!isMobileApi())
      this.signView.refreshSign();
    super.onResume();
  }

  protected void onRuleMessage(int paramInt, String paramString)
  {
    if (paramInt == 101)
    {
      String str = this.signView.getSign();
      if ((str == null) || (str.length() == 0))
        this.signView.setSign(paramString);
    }
    super.onRuleMessage(paramInt, paramString);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.UserSetActivity
 * JD-Core Version:    0.6.0
 */