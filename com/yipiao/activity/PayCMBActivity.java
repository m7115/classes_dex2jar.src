package com.yipiao.activity;

import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
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

public class PayCMBActivity extends BaseActivity
{
  private RadioGroup cardGroup;
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
    if (!this.mobileNo.matches("1\\d{10}"))
      throw new m("手机号格式错误！请重新输入。");
    if (!this.cardNo.matches("\\d{4}"))
      throw new m("请输入卡号后4位。");
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
        if (PayCMBActivity.this.cardGroup.getCheckedRadioButtonId() == 2131297012)
          return PayCMBActivity.this.getHc().cmbCreditCardPay(PayCMBActivity.this.mobileNo, PayCMBActivity.this.cardNo, PayCMBActivity.this.sign);
        return PayCMBActivity.this.getHc().cmbNetPay(PayCMBActivity.this.mobileNo, PayCMBActivity.this.cardNo, PayCMBActivity.this.sign);
      }

      protected void myPostExecute(Object paramObject)
      {
        PayCMBActivity.this.showMessage("招行提示", Html.fromHtml(paramObject.toString()));
        PayCMBActivity.this.logToServer("payCMB", paramObject.toString());
      }

      protected void onException(Exception paramException)
      {
        PayCMBActivity.this.signView.refreshSign();
        super.onException(paramException);
      }
    }
    .execute(new Map[0]);
  }

  protected int getMainLayout()
  {
    return 2130903139;
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
          PayCMBActivity.this.checkInput();
          PayCMBActivity.this.paySubmit();
          return;
        }
        catch (m localm)
        {
          PayCMBActivity.this.showToast(localm.getMessage(), 1);
        }
      }
    });
    this.cardGroup = ((RadioGroup)findViewById(2131297011));
    this.signView = ((SYSignView)findViewById(2131296771));
    this.signView.init(2130903166, new SYSignView.SignListenerBase()
    {
      public SYSignView.MulImage load()
        throws Exception
      {
        return PayCMBActivity.this.getHc().cmbPayPre(PayCMBActivity.this.order);
      }
    }
    , "　　附加码：", "");
    this.signView.refreshSign();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayCMBActivity
 * JD-Core Version:    0.6.0
 */