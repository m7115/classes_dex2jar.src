package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.suanya.common.a.m;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.OrderResult;
import com.yipiao.service.Huoche;
import java.util.Map;

public class PayABC2Activity extends BaseActivity
{
  private OrderResult order;
  private String verifycode;

  private void checkInput()
    throws m
  {
    this.verifycode = getVString(2131297006).toString();
    if (!this.verifycode.matches("\\d{6}"))
      throw new m("请输入6位,手机验证码。");
  }

  private void paySubmit()
  {
    new MyAsyncTask(this)
    {
      protected Object myInBackground(Map<String, String>[] paramArrayOfMap)
        throws Exception
      {
        return PayABC2Activity.this.getHc().abcNetPay2(PayABC2Activity.this.verifycode);
      }

      protected void myPostExecute(Object paramObject)
      {
        PayABC2Activity.this.showToast("支付完成");
        MainTab.currentTab = 2131296879;
        OrderTabActivity.currentTab = 2131296963;
        Intent localIntent = new Intent(PayABC2Activity.this, MainTab.class);
        PayABC2Activity.this.startActivity(localIntent);
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
        PayABC2Activity.this.finish();
      }
    }
    .execute(new Map[0]);
  }

  protected int getMainLayout()
  {
    return 2130903136;
  }

  public void init()
  {
    super.init();
    if (checkNeedLaunch())
      return;
    this.order = ((OrderResult)this.app.getParms("OrderResult"));
    setClick(2131297005, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        try
        {
          PayABC2Activity.this.checkInput();
          PayABC2Activity.this.paySubmit();
          return;
        }
        catch (m localm)
        {
          PayABC2Activity.this.showToast(localm.getMessage(), 1);
        }
      }
    });
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayABC2Activity
 * JD-Core Version:    0.6.0
 */