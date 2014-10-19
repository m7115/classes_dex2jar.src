package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.OrderPassengerListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.BookResult;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainMobile;
import com.yipiao.bean.TrainNew;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class BookActivity extends PassengerSetActivity
{
  protected BookResult br;
  protected ChainQuery cq;
  protected boolean isNormalBook;
  protected SYSignView signView;
  protected Train train;

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

  public void book(Train paramTrain, ChainQuery paramChainQuery)
  {
    new MyAsyncTask(this, true)
    {
      protected BookResult myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        synchronized (BookActivity.this)
        {
          BookResult localBookResult = BookActivity.this.getHc().book((Train)paramArrayOfObject[0], (ChainQuery)paramArrayOfObject[1]);
          return localBookResult;
        }
      }

      protected void myPostExecute(BookResult paramBookResult)
      {
        BookActivity.this.signView.refreshSign();
        BookActivity.this.br = paramBookResult;
        BookActivity.this.setTv(2131296693, Html.fromHtml(BookActivity.this.br.ticketString()));
        ((OrderPassengerListAdapter)BookActivity.this.adapter).setTickets(BookActivity.this.br.getTickets());
        BookActivity.this.iniUserSeatType();
        BookActivity.this.adapter.notifyDataSetChanged();
        BookActivity.this.app.successLevel = 3;
      }

      protected void onException(Exception paramException)
      {
        BookActivity.this.finish();
        BookActivity.this.app.successLevel = 0;
        super.onException(paramException);
      }
    }
    .execute(new Object[] { paramTrain, paramChainQuery });
  }

  protected boolean checkPassengers()
  {
    if ((this.passengers == null) || (this.passengers.isEmpty()))
    {
      showToast("请添加购票人信息！");
      return false;
    }
    if (this.passengers.size() > 5)
    {
      showToast("一次最多添加5个购票人！");
      return false;
    }
    return true;
  }

  protected BaseAdapter createAdapter()
  {
    return new OrderPassengerListAdapter(this, this.passengers, 2130903122);
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
    return 2130903067;
  }

  protected void iniUserSeatType()
  {
    Iterator localIterator = this.passengers.iterator();
    while (localIterator.hasNext())
      iniUserSeatType((UserInfo)localIterator.next());
  }

  public void init()
  {
    super.init();
    if (checkNeedLaunch())
      return;
    this.seatType = null;
    this.seatType = getIntent().getStringExtra("seatType");
    this.train = ((Train)this.app.getParms("train"));
    this.cq = ((ChainQuery)this.app.getParms("chainQuery"));
    if ((this.app.getParms("isNormalBook") instanceof Boolean));
    for (this.isNormalBook = ((Boolean)this.app.getParms("isNormalBook")).booleanValue(); ; this.isNormalBook = true)
    {
      setTv(2131296689, this.train.getCode());
      setTv(2131296767, this.train.getFromTime());
      setTv(2131296768, this.train.getToTime());
      setTv(2131296769, " - " + this.train.getFrom());
      setTv(2131296770, " - " + this.train.getTo());
      setTv(2131296765, this.train.showLeaveDate().split(" ")[0]);
      setTv(2131296766, this.train.showLeaveDate().split(" ")[1]);
      setClick(2131296259, this);
      setClick(2131296772, this);
      this.signView = ((SYSignView)findViewById(2131296771));
      this.signView.init(2130903166, new SYSignView.SignListenerBase()
      {
        public SYSignView.MulImage load()
          throws Exception
        {
          return BookActivity.this.getHc().orderSign();
        }
      });
      initBook();
      return;
    }
  }

  protected void initBook()
  {
    book(this.train, this.cq);
  }

  protected void onAddCurrentPassenger(UserInfo paramUserInfo)
  {
    iniUserSeatType(paramUserInfo);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      super.onClick(paramView);
      return;
    case 2131296772:
      submitOrder();
      return;
    case 2131296259:
    }
    String str = Constants.getServiceUrl() + "/help.html?" + System.currentTimeMillis() + "&&cid=" + this.app.launchInfo.optLong("clientId");
    Intent localIntent = new Intent(this, CommonWebActivity.class);
    localIntent.putExtra("title", "购票帮助");
    localIntent.putExtra("url", str);
    startActivity(localIntent);
  }

  protected void onPassengerSetEnd()
  {
    if (this.isNormalBook)
    {
      super.onPassengerSetEnd();
      return;
    }
    this.app.saveMonitorPool();
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
    if (!checkPassengers())
      return;
    2 local2 = new MyAsyncTask(this)
    {
      protected String myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        synchronized (BookActivity.this)
        {
          String str = BookActivity.this.getHc().submitOrder(BookActivity.this.cq, BookActivity.this.signView.getSign(), BookActivity.this.passengers, BookActivity.this.train);
          if ((str == null) || (str.length() == 0))
            return "订单请求已经提交了，请稍后查看我的订单！";
          return str;
        }
      }

      protected void myPostExecute(String paramString)
      {
        BookActivity.this.logToServer("submitOrder", BookActivity.this.train.toString());
        if (paramString.length() < 19)
          BookActivity.this.showToast(Html.fromHtml("订单提交成功，订单号为" + paramString), 1);
        while (true)
        {
          OrderQueryActivity.refreshOrder = true;
          Intent localIntent = new Intent(BookActivity.this, OrderQueryActivity.class);
          BookActivity.this.startActivity(localIntent);
          ((InputMethodManager)BookActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(BookActivity.this.signView.mText.getWindowToken(), 0);
          BookActivity.this.app.successLevel = 4;
          BookActivity.this.finish();
          return;
          BookActivity.this.showToast(paramString);
        }
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
        BookActivity.this.book(BookActivity.this.train, BookActivity.this.cq);
        BookActivity.this.app.successLevel = 1;
      }
    };
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.train;
    arrayOfObject[1] = this.cq;
    local2.execute(arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.BookActivity
 * JD-Core Version:    0.6.0
 */