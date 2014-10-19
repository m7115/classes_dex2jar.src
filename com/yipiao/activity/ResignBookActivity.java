package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.OrderPassengerListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.BookResult;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainMobile;
import com.yipiao.bean.TrainNew;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheNew;
import com.yipiao.service.PassengerService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResignBookActivity extends PassengerSetActivity
{
  private BookResult br;
  private ChainQuery cq;
  private boolean noQueryOrder = false;
  private ArrayList<OrderItem> order;
  private SYSignView signView;
  private Train train;
  private List<UserInfo> userList;

  private void iniUserSeatType(UserInfo paramUserInfo)
  {
    if ((this.seatType != null) && (this.seatType.length() > 0))
    {
      paramUserInfo.setSeatType(this.seatType);
      return;
    }
    ((OrderPassengerListAdapter)this.adapter).iniUserSeatType(paramUserInfo);
    this.seatType = paramUserInfo.getSeatType();
  }

  public void book()
  {
    new MyAsyncTask(this, true)
    {
      protected BookResult myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        synchronized (ResignBookActivity.this)
        {
          if ((ResignBookActivity.this.noQueryOrder) && ((ResignBookActivity.this.getHc() instanceof HuocheNew)))
          {
            ResignBookActivity.this.getHc().myHistoryOrder();
            ResignBookActivity.this.getHc().resignReady(ResignBookActivity.this.order);
            ResignBookActivity.access$602(ResignBookActivity.this, false);
          }
          BookResult localBookResult = ResignBookActivity.this.getHc().resignBook(ResignBookActivity.this.train, ResignBookActivity.this.cq, ResignBookActivity.this.order);
          return localBookResult;
        }
      }

      protected void myPostExecute(BookResult paramBookResult)
      {
        ResignBookActivity.this.signView.refreshSign();
        ResignBookActivity.access$702(ResignBookActivity.this, paramBookResult);
        ResignBookActivity.this.setTv(2131296693, Html.fromHtml(ResignBookActivity.this.br.ticketString()));
        ResignBookActivity.this.userList.clear();
        if (ResignBookActivity.this.br.getUserList() != null)
          ResignBookActivity.this.userList.addAll(ResignBookActivity.this.br.getUserList());
        ((OrderPassengerListAdapter)ResignBookActivity.this.adapter).setTickets(ResignBookActivity.this.br.getTickets());
        ResignBookActivity.this.iniUserSeatType();
        ResignBookActivity.this.adapter.notifyDataSetChanged();
      }

      protected void onException(Exception paramException)
      {
        ResignBookActivity.this.finish();
        super.onException(paramException);
      }
    }
    .execute(new Object[0]);
  }

  protected BaseAdapter createAdapter()
  {
    return new OrderPassengerListAdapter(this, this.userList, 2130903157);
  }

  public Huoche getHc()
  {
    if ((this.train instanceof TrainNew))
      return getHc(2);
    if ((this.train instanceof TrainMobile))
      return getHc(3);
    return super.getHc();
  }

  protected int getMainLayout()
  {
    return 2130903156;
  }

  protected void iniUserSeatType()
  {
    Iterator localIterator = this.userList.iterator();
    while (localIterator.hasNext())
      iniUserSeatType((UserInfo)localIterator.next());
  }

  public void onClick(View paramView)
  {
    super.onClick(paramView);
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296772:
    }
    submitOrder();
  }

  public void onCreate(Bundle paramBundle)
  {
    this.userList = new ArrayList();
    super.onCreate(paramBundle);
    if (checkNeedLaunch());
    do
    {
      return;
      this.seatType = null;
      this.seatType = getIntent().getStringExtra("seatType");
      this.train = ((Train)this.app.getParms("train"));
      this.cq = ((ChainQuery)this.app.getParms("chainQuery"));
      this.noQueryOrder = ((Boolean)this.app.getParms("noQueryOrder")).booleanValue();
      this.order = ((ArrayList)this.app.getParms("order"));
      setTv(2131296689, this.train.getCode());
      setTv(2131296767, this.train.getFromTime());
      setTv(2131296768, this.train.getToTime());
      setTv(2131296769, " - " + this.train.getFrom());
      setTv(2131296770, " - " + this.train.getTo());
      setTv(2131296765, this.train.showLeaveDate().split(" ")[0]);
      setTv(2131296766, this.train.showLeaveDate().split(" ")[1]);
      setClick(2131296772, this);
      this.signView = ((SYSignView)findViewById(2131296771));
      this.signView.init(2130903166, new SYSignView.SignListenerBase()
      {
        public SYSignView.MulImage load()
          throws Exception
        {
          return ResignBookActivity.this.getHc().resignOrderSign();
        }
      });
    }
    while (!this.app.isLogined());
    book();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
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

  public void submitOrder()
  {
    2 local2 = new MyAsyncTask(this)
    {
      protected String myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        synchronized (ResignBookActivity.this)
        {
          String str = ResignBookActivity.this.getHc().resignSubmitOrder(ResignBookActivity.this.signView.getSign(), ResignBookActivity.this.userList, ResignBookActivity.this.train, ResignBookActivity.this.order, ResignBookActivity.this.cq);
          if ((str == null) || (str.length() == 0))
            return "订单请求已经提交了，请稍后查看我的订单！";
          return str;
        }
      }

      protected void myPostExecute(String paramString)
      {
        ResignBookActivity.this.logToServer("submitOrderResign", ResignBookActivity.this.train.toString());
        if (paramString.length() < 19)
          ResignBookActivity.this.showToast(Html.fromHtml("订单提交成功，订单号为" + paramString), 1);
        while (true)
        {
          OrderQueryActivity.refreshOrder = true;
          Intent localIntent = new Intent(ResignBookActivity.this, OrderQueryActivity.class);
          ResignBookActivity.this.startActivity(localIntent);
          ((InputMethodManager)ResignBookActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(ResignBookActivity.this.signView.mText.getWindowToken(), 0);
          ResignBookActivity.this.finish();
          return;
          ResignBookActivity.this.showToast(paramString);
        }
      }

      protected void onException(Exception paramException)
      {
        try
        {
          ResignBookActivity.this.logToServer("submitOrderResignError", paramException.toString() + "/" + ResignBookActivity.this.train.toString() + "/" + ((UserInfo)ResignBookActivity.this.passengerService.getCurrUsers().get(0)).toString());
          super.onException(paramException);
          ResignBookActivity.this.book();
          return;
        }
        catch (Exception localException)
        {
          while (true)
            ResignBookActivity.this.logToServer("submitOrderResignError", paramException.toString());
        }
      }
    };
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.train;
    arrayOfObject[1] = this.cq;
    local2.execute(arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.ResignBookActivity
 * JD-Core Version:    0.6.0
 */