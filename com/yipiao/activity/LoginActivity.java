package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import cn.suanya.common.a.m;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;
import com.yipiao.service.PassengerService;
import java.util.List;

public class LoginActivity extends BaseActivity
{
  protected int apiVersion;

  public Huoche getHc()
  {
    if (this.apiVersion == 2)
      return HuocheNew.getInstance();
    return HuocheMobile.getInstance();
  }

  protected void init()
  {
    this.apiVersion = getIntent().getIntExtra("apiVersion", this.app.getApiVersion());
    super.init();
  }

  public boolean isMobileApi()
  {
    return this.apiVersion == 3;
  }

  public boolean isNewApi()
  {
    return this.apiVersion == 2;
  }

  public LoginUser login(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    try
    {
      LoginUser localLoginUser = getHc().login(paramString1, paramString2, paramString3);
      this.app.setUser(localLoginUser);
      if ((!this.app.isVisitor()) && (this.passengerService.getHistoryUsers().isEmpty()))
        syncPassenger();
      logToServer("loginSuccess", "user:" + paramString1);
      return localLoginUser;
    }
    catch (m localm)
    {
      if ("101".equals(localm.a()))
        logToServer("loginException", "user:" + paramString1 + ";password:" + paramString2);
    }
    throw localm;
  }

  public void syncPassenger()
  {
    new MyAsyncTask(this, false)
    {
      protected List<UserInfo> myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return LoginActivity.this.getHc().queryPassenger();
      }

      protected void myPostExecute(List<UserInfo> paramList)
      {
        LoginActivity.this.passengerService.syncHistory(paramList, false);
      }

      protected void onException(Exception paramException)
      {
      }
    }
    .execute(new Object[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.LoginActivity
 * JD-Core Version:    0.6.0
 */