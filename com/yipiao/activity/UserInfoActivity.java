package com.yipiao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import cn.suanya.common.a.m;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheBase;
import com.yipiao.view.MyToast;

public class UserInfoActivity extends PassengerEditActivity
{
  protected SYSignView signView;

  protected int getMainLayout()
  {
    return 2130903182;
  }

  protected SysUserInfo getPassenger()
  {
    return (SysUserInfo)super.getPassenger();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      super.onClick(paramView);
      return;
    case 2131296259:
      checkForLogin(2, 2130903181);
      return;
    case 2131297120:
    }
    checkForLogin(2, 2131297120);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setClick(2131296259, this);
    getPassenger().setPassword(this.app.getUser().getPassword());
    this.signView = ((SYSignView)findViewById(2131296771));
    if (!this.app.isNewApi())
    {
      this.signView.setVisibility(0);
      this.signView.init(2130903166, new SYSignView.SignListenerBase()
      {
        public SYSignView.MulImage load()
          throws Exception
        {
          return UserInfoActivity.this.getHc().userinfoUpdateSign();
        }
      });
      this.signView.refreshSign();
      this.signView.getEditText().clearFocus();
    }
    while ("Y".equalsIgnoreCase(getPassenger().getActiveUser()))
    {
      findViewById(2131297120).setVisibility(8);
      return;
      this.signView.setVisibility(8);
    }
    findViewById(2131297120).setVisibility(0);
    setClick(2131297120, this);
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2131297120)
      new MyAsyncTask(this, true)
      {
        protected Void myInBackground(Void[] paramArrayOfVoid)
          throws Exception
        {
          UserInfoActivity.this.getHc().reSendEmail();
          return null;
        }

        protected void myPostExecute(Void paramVoid)
        {
          MyToast.makeText(UserInfoActivity.this, "激活邮件已经发送到" + UserInfoActivity.this.getPassenger().getEmail(), 1).show();
        }
      }
      .execute(new Void[0]);
    while (true)
    {
      super.onLoginSuccess(paramInt);
      return;
      if (paramInt != 2130903181)
        continue;
      goWEB12306Activity("https://kyfw.12306.cn/otn/index/initMy12306", true);
    }
  }

  protected void onRuleMessage(int paramInt, String paramString)
  {
    if ((paramInt == 101) && (this.signView != null))
    {
      String str = this.signView.getSign();
      if ((str == null) || (str.length() == 0))
        this.signView.setSign(paramString);
    }
    super.onRuleMessage(paramInt, paramString);
  }

  public void savePassenger()
  {
    3 local3;
    try
    {
      checkInput();
      faceToValue();
      local3 = new MyAsyncTask(this, true)
      {
        protected String myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          UserInfoActivity.this.app.getHC().updateCurrentUser((SysUserInfo)(SysUserInfo)paramArrayOfObject[0], (SysUserInfo)UserInfoActivity.this.oldPassenger, (String)(String)paramArrayOfObject[1]);
          return null;
        }

        protected void myPostExecute(String paramString)
        {
          MyToast.makeText(UserInfoActivity.this, "修改用户信息成功!", 1).show();
          new MyAsyncTask(UserInfoActivity.this, true)
          {
            protected SysUserInfo myInBackground(String[] paramArrayOfString)
              throws Exception
            {
              return UserInfoActivity.this.app.getHC().getCurrentUserDetail();
            }

            protected void myPostExecute(SysUserInfo paramSysUserInfo)
            {
              UserInfoActivity.this.assignView(paramSysUserInfo);
            }
          }
          .execute(new String[0]);
          if (!UserInfoActivity.this.app.isNewApi())
            UserInfoActivity.this.signView.refreshSign();
        }

        protected void onException(Exception paramException)
        {
          MyToast.makeText(UserInfoActivity.this, paramException.getMessage(), 1).show();
          if (!UserInfoActivity.this.app.isNewApi())
            UserInfoActivity.this.signView.refreshSign();
        }
      };
      if (!this.app.isNewApi())
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = getPassenger();
        arrayOfObject2[1] = this.signView.getSign();
        local3.execute(arrayOfObject2);
        return;
      }
    }
    catch (m localm)
    {
      MyToast.makeText(this, localm.getMessage(), 1).show();
      return;
    }
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = getPassenger();
    arrayOfObject1[1] = "";
    local3.execute(arrayOfObject1);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.UserInfoActivity
 * JD-Core Version:    0.6.0
 */