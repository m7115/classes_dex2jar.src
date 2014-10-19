package com.yipiao.activity;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.common.a.v;
import cn.suanya.common.bean.NameValueList;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.OrderItemListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.service.Huoche;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.wxapi.DisplayHelper;
import java.util.List;

public class OrderQueryActivity extends BaseActivity
{
  public static boolean refreshOrder = true;
  private OrderItemListAdapter adapter;
  private Button cancelOrderButton;
  Long ePayCurrent = Long.valueOf(0L);
  Long ePayLater = Long.valueOf(0L);
  boolean handerIsLoop = false;
  private View loginbt;
  protected Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      if (OrderQueryActivity.this.handerIsLoop)
      {
        if (OrderQueryActivity.this.showEpayLater() <= 0L)
          OrderQueryActivity.this.handerIsLoop = false;
        OrderQueryActivity.this.mHandler.sendEmptyMessageDelayed(0, 1000L);
      }
    }
  };
  private ListView mListView;
  private OrderResult order;
  private LinearLayout orderDoll;
  private TextView orderRemark;
  private Button payButton;
  private Button resignNButton;
  private Button resignTButton;
  private View rightBt;
  private LinearLayout sharell;
  private LinearLayout unOrder;

  private void loopHandler()
  {
    if (!this.handerIsLoop)
    {
      this.handerIsLoop = true;
      this.mHandler.sendEmptyMessage(0);
    }
  }

  private void payOrder()
  {
    OrderTabActivity.orderQueryStatus(2131296962, true, true);
    if (this.app.isMobileApi())
    {
      new MyAsyncTask(this, true)
      {
        protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          return OrderQueryActivity.this.getHc().webPay(OrderQueryActivity.this.order);
        }

        protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
        {
          String str = paramContext.getStr("action");
          NameValueList localNameValueList = (NameValueList)paramContext.get("values");
          if (localNameValueList == null)
          {
            OrderQueryActivity.this.showToast("支付失败");
            return;
          }
          OrderQueryActivity.this.app.putParms("order", OrderQueryActivity.this.order);
          OrderQueryActivity.this.goWebActivity("网上支付", str, null, localNameValueList.urlEncodeBytes());
        }
      }
      .execute(new Object[0]);
      return;
    }
    Intent localIntent = new Intent(this, PayListActivity.class);
    this.app.putParms("OrderResult", this.order);
    startActivity(localIntent);
  }

  private String shareString()
  {
    if (this.order == null)
      return "";
    if ((this.order.getOrder() == null) || (this.order.getOrder().size() == 0))
      return "";
    OrderItem localOrderItem = (OrderItem)this.order.getOrder().get(0);
    return localOrderItem.getOrderDate() + localOrderItem.getFrom() + "-" + localOrderItem.getTo() + localOrderItem.getSeatType() + this.order.getOrder().size() + "张";
  }

  private long showEpayLater()
  {
    long l1 = System.currentTimeMillis() - this.ePayCurrent.longValue();
    long l2;
    if (this.ePayLater.longValue() < l1)
      l2 = 0L;
    while (true)
    {
      String str = v.a(l2);
      setTv(2131296257, Html.fromHtml("请在<font color=\"#ff0000\">" + str + "</font>内完成支付,否则系统将取消订单!"));
      return l2;
      l2 = this.ePayLater.longValue() - l1;
    }
  }

  public void cancelOrder()
  {
    if ((this.order == null) || (this.order.getOrderNo() == null))
      return;
    new MyAsyncTask(this, true)
    {
      protected Object myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        OrderQueryActivity.this.getHc().cancelOrder(OrderQueryActivity.this.order.getToken(), OrderQueryActivity.this.order.getOrderNo(), OrderQueryActivity.this.order);
        return null;
      }

      protected void myPostExecute(Object paramObject)
      {
        OrderQueryActivity.this.showToast("订单取消成功");
        OrderQueryActivity.refreshOrder = true;
        OrderQueryActivity.this.queryOrder();
      }
    }
    .execute(new Object[0]);
  }

  protected int getMainLayout()
  {
    return 2130903123;
  }

  public void laterEpay()
  {
    if ((this.order == null) || (this.order.getOrder().isEmpty()))
      return;
    new MyAsyncTask(this, true)
    {
      protected Long myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return Long.valueOf(OrderQueryActivity.this.getHc().laterEpay(OrderQueryActivity.this.order));
      }

      protected void myPostExecute(Long paramLong)
      {
        OrderQueryActivity.this.ePayLater = paramLong;
        OrderQueryActivity.this.ePayCurrent = Long.valueOf(System.currentTimeMillis());
        OrderQueryActivity.this.loopHandler();
        OrderQueryActivity.this.showOrder();
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
      }
    }
    .execute(new Object[0]);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296935:
      OrderTabActivity.orderQueryStatus(2131296962, true, true);
      new MyAlertDialog.Builder(this).setTitle("提示").setMessage("每天只能取消3次订单，确定取消订单吗？").setPositiveButton("是", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          OrderQueryActivity.this.cancelOrder();
        }
      }
      , getResources().getDrawable(2130837598)).setNegativeButton("否", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
        }
      }).show();
      return;
    case 2131296958:
    case 2131296959:
    case 2131296960:
      if (this.order.isResign())
      {
        new MyAlertDialog.Builder(this).setTitle("提示").setMessage("每张票只能改签一次，确定改签吗？").setPositiveButton("是", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            OrderQueryActivity.this.resignOrPay();
          }
        }
        , getResources().getDrawable(2130837598)).setNegativeButton("否", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
          }
        }).show();
        return;
      }
      resignOrPay();
      return;
    case 2131296259:
    }
    refreshOrder = true;
    queryOrder();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ((NotificationManager)getSystemService("notification")).cancel(0);
    setClick(2131296935, this);
    this.orderRemark = ((TextView)findViewById(2131296956));
    this.payButton = ((Button)setClick(2131296958, this));
    this.resignNButton = ((Button)setClick(2131296959, this));
    this.resignTButton = ((Button)setClick(2131296960, this));
    this.cancelOrderButton = ((Button)setClick(2131296935, this));
    this.rightBt = setClick(2131296259, this);
    this.adapter = new OrderItemListAdapter(this, null, 2130903124);
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    this.unOrder = ((LinearLayout)findViewById(2131296952));
    this.loginbt = setClick(2131296930, UserSetActivity.class);
    this.sharell = ((LinearLayout)findViewById(2131296938));
    this.orderDoll = ((LinearLayout)findViewById(2131296957));
    refreshOrder = true;
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (refreshOrder)
    {
      refreshOrder = false;
      new MyAsyncTask(this, true)
      {
        protected OrderResult myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          return OrderQueryActivity.this.getHc().uncompleteOrder();
        }

        protected void myPostExecute(OrderResult paramOrderResult)
        {
          OrderQueryActivity.access$002(OrderQueryActivity.this, paramOrderResult);
          OrderQueryActivity.this.showOrder();
          if (OrderQueryActivity.this.order.isvalid())
            OrderQueryActivity.this.laterEpay();
        }

        protected void onException(Exception paramException)
        {
          super.onException(paramException);
        }
      }
      .execute(new Object[0]);
      super.onLoginSuccess(paramInt);
      return;
    }
    showOrder();
  }

  protected void onPause()
  {
    this.handerIsLoop = false;
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    queryOrder();
  }

  public void queryOrder()
  {
    checkForLogin(2130903123);
  }

  public void resignOrPay()
  {
    if (this.order.isEpay())
      payOrder();
    do
    {
      return;
      if (!this.order.isResignN())
        continue;
      resignOrder('N');
      return;
    }
    while (!this.order.isResignT());
    resignOrder('T');
  }

  public void resignOrder(char paramChar)
  {
    if ((this.order == null) || (this.order.getOrderNo() == null))
      return;
    new MyAsyncTask(this, true, paramChar)
    {
      protected Object myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        if (this.val$resignType == 'T')
          OrderQueryActivity.this.getHc().resignOrderT(OrderQueryActivity.this.order);
        if (this.val$resignType == 'N')
          OrderQueryActivity.this.getHc().resignOrderN(OrderQueryActivity.this.order);
        return null;
      }

      protected void myPostExecute(Object paramObject)
      {
        OrderQueryActivity.this.showToast("订单改签成功");
        OrderQueryActivity.refreshOrder = true;
        Intent localIntent = new Intent(OrderQueryActivity.this, OrderHistoryActivity.class);
        localIntent.setFlags(67108864);
        OrderQueryActivity.this.startActivity(localIntent);
        OrderQueryActivity.this.finish();
      }
    }
    .execute(new Object[0]);
  }

  public void showOrder()
  {
    this.loginbt.setVisibility(8);
    this.rightBt.setVisibility(0);
    setVisibility(2131296951, 8);
    if (this.order != null)
    {
      setTv(2131296953, this.order.getFrom() + '-' + this.order.getTo() + ' ' + this.order.getLeaveDate());
      setTv(2131296954, this.order.getStatus());
      setTv(2131296955, this.order.getTrainNo() + '次' + this.order.getLeaveTime() + '开');
      this.adapter.setMlist(this.order.getPayOrder());
      this.adapter.notifyDataSetChanged();
      if (this.order.getOrder().isEmpty())
      {
        setTv(2131296257, "您没有未完成订单");
        this.handerIsLoop = false;
        this.ePayLater = Long.valueOf(0L);
        this.unOrder.setVisibility(8);
      }
    }
    else
    {
      return;
    }
    this.unOrder.setVisibility(0);
    if (this.order.isvalid())
    {
      loopHandler();
      Button localButton5 = this.payButton;
      int n;
      int i1;
      label293: int i2;
      label319: int i3;
      if (this.order.isEpay())
      {
        n = 0;
        localButton5.setVisibility(n);
        Button localButton6 = this.resignNButton;
        if (!this.order.isResignN())
          break label469;
        i1 = 0;
        localButton6.setVisibility(i1);
        Button localButton7 = this.resignTButton;
        if (!this.order.isResignT())
          break label476;
        i2 = 0;
        localButton7.setVisibility(i2);
        Button localButton8 = this.cancelOrderButton;
        if (!this.order.isCancelAble())
          break label483;
        i3 = 0;
        label345: localButton8.setVisibility(i3);
        if ((this.order.getPayRemark() == null) || (this.order.getPayRemark().length() <= 0))
          break label490;
        this.orderRemark.setText(Html.fromHtml(this.order.getPayRemark()));
        this.orderRemark.setVisibility(0);
      }
      while (true)
      {
        setTv(2131296951, "订单号：" + this.order.getOrderNo());
        this.orderDoll.setVisibility(0);
        DisplayHelper.createShareLink(this, this.sharell, this.app.api, shareString());
        return;
        n = 8;
        break;
        label469: i1 = 8;
        break label293;
        label476: i2 = 8;
        break label319;
        label483: i3 = 8;
        break label345;
        label490: this.orderRemark.setVisibility(8);
      }
    }
    setTv(2131296257, "您有一条未完成订单");
    Button localButton1 = this.payButton;
    int i;
    int j;
    label557: int k;
    label583: Button localButton4;
    int m;
    if (this.order.isEpay())
    {
      i = 0;
      localButton1.setVisibility(i);
      Button localButton2 = this.resignNButton;
      if (!this.order.isResignN())
        break label628;
      j = 0;
      localButton2.setVisibility(j);
      Button localButton3 = this.resignTButton;
      if (!this.order.isResignT())
        break label635;
      k = 0;
      localButton3.setVisibility(k);
      localButton4 = this.cancelOrderButton;
      boolean bool = this.order.isCancelAble();
      m = 0;
      if (!bool)
        break label642;
    }
    while (true)
    {
      localButton4.setVisibility(m);
      return;
      i = 8;
      break;
      label628: j = 8;
      break label557;
      label635: k = 8;
      break label583;
      label642: m = 8;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.OrderQueryActivity
 * JD-Core Version:    0.6.0
 */