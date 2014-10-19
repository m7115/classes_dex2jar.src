package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import cn.suanya.common.a.m;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.OrderResult;
import com.yipiao.service.Huoche;
import java.util.Map;

public class PayABCActivity extends BaseActivity
{
  private String cardNo;
  private String mobileNo;
  private OrderResult order;
  private String sign;
  private SYSignView signView;

  private void checkInput()
    throws m
  {
    this.mobileNo = getVString(2131297004).toString();
    this.cardNo = getVString(2131297003).toString();
    this.sign = this.signView.getSign();
    if (!this.mobileNo.matches("\\d{4}"))
      throw new m("请输入农行预留手机号后4位。");
    if (!this.cardNo.matches("\\d{16,20}"))
      throw new m("请输入卡号正确的农行卡号。");
    if (!this.sign.matches("\\w{3,8}"))
      throw new m("附加码错误，点击图片可刷新验证码！");
  }

  private void paySubmit()
  {
    new MyAsyncTask(this)
    {
      protected Object myInBackground(Map<String, String>[] paramArrayOfMap)
        throws Exception
      {
        return PayABCActivity.this.getHc().abcNetPay1(PayABCActivity.this.mobileNo, PayABCActivity.this.cardNo, PayABCActivity.this.sign);
      }

      protected void myPostExecute(Object paramObject)
      {
        Intent localIntent = new Intent(PayABCActivity.this, PayABC2Activity.class);
        PayABCActivity.this.startActivityForResult(localIntent, 2130903136);
        PayABCActivity.this.showToast("验证码已经发到您的手机上！");
      }

      protected void onException(Exception paramException)
      {
        PayABCActivity.this.signView.refreshSign();
        super.onException(paramException);
      }
    }
    .execute(new Map[0]);
  }

  protected String getDefautRemark()
  {
    return "<font color=\"#999999\">&nbsp;&nbsp;目前只支持已经开通K码支付的农行卡，如何开通K码支付请咨询农行</font><a href=\"tel:95599\">95599</a>";
  }

  protected int getMainLayout()
  {
    return 2130903135;
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
          PayABCActivity.this.checkInput();
          PayABCActivity.this.paySubmit();
          return;
        }
        catch (m localm)
        {
          PayABCActivity.this.showToast(localm.getMessage(), 1);
        }
      }
    });
    this.signView = ((SYSignView)findViewById(2131296771));
    this.signView.init(2130903166, new SYSignView.SignListenerBase()
    {
      public SYSignView.MulImage load()
        throws Exception
      {
        return PayABCActivity.this.getHc().abcSign(PayABCActivity.this.order);
      }
    }
    , "　　验证码：", "");
    this.signView.refreshSign();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2130903136)
      this.signView.refreshSign();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayABCActivity
 * JD-Core Version:    0.6.0
 */