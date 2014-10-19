package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.LoginUser;
import com.yipiao.service.HuocheBase;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;

public class ChangePwdActivity extends BaseActivity
{
  protected void changePwd(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (isEmpty(paramString2))
    {
      showToast("请输入原密码");
      return;
    }
    if (!paramString3.equals(paramString4))
    {
      showToast("新密码输入不一致");
      return;
    }
    if ((isEmpty(paramString3)) || (paramString3.length() < 6) || (!paramString3.matches(".*\\d+.*")) || (!paramString3.matches(".*[a-zA-Z]+.*")))
    {
      showToast("新密码不能少于6位,并同时包含数字和字母");
      return;
    }
    new MyAsyncTask(this, paramString1, paramString2, paramString3)
    {
      protected Void myInBackground(Void[] paramArrayOfVoid)
        throws Exception
      {
        ChangePwdActivity.this.app.getHC().changePwd(this.val$userName, this.val$oldPwd, this.val$newPwd);
        return null;
      }

      protected void myPostExecute(Void paramVoid)
      {
        ChangePwdActivity.this.showToast("密码修改成功，请重新登录");
        ChangePwdActivity.this.loginOut();
      }
    }
    .execute(new Void[0]);
  }

  protected int getMainLayout()
  {
    return 2130903045;
  }

  protected void init()
  {
    super.init();
    setClick(2131296695, this);
    setTv(2131296419, this.app.getUser().getUserName());
  }

  public void loginOut()
  {
    new MyAsyncTask(this)
    {
      protected Object myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        HuocheNew.getInstance().loginOut();
        HuocheMobile.getInstance().loginOut();
        return null;
      }

      protected void myPostExecute(Object paramObject)
      {
        ChangePwdActivity.this.finish();
        Intent localIntent = new Intent(ChangePwdActivity.this, UserSetActivity.class);
        ChangePwdActivity.this.startActivity(localIntent);
      }
    }
    .execute(new String[0]);
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131296695)
      changePwd(this.app.getUser().getUserName(), getVString(2131296690), getVString(2131296692), getVString(2131296694));
    super.onClick(paramView);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.ChangePwdActivity
 * JD-Core Version:    0.6.0
 */