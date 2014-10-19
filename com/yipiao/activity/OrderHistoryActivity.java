package com.yipiao.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import cn.suanya.common.net.LogInfo;
import cn.suanya.hc.service.PathService;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.HistoryOrderListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.OrderResultComparator;
import com.yipiao.bean.StationNode;
import com.yipiao.service.Huoche;
import com.yipiao.view.MyAlertDialog;
import com.yipiao.view.MyAlertDialog.Builder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OrderHistoryActivity extends BaseActivity
{
  public static boolean refreshOrder = true;
  private HistoryOrderListAdapter adapter;
  private View loginbt;
  private ListView mListView;
  private List<OrderResult> order;
  private View rightBt;

  private boolean isStudentOrder(ArrayList<OrderItem> paramArrayList)
  {
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
      if (!"3".equals(((OrderItem)localIterator.next()).getTickTypeCode()))
        return false;
    return true;
  }

  public void confirmRefundTicket(OrderItem paramOrderItem)
  {
    new MyAsyncTask(this, true, paramOrderItem)
    {
      protected String myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return OrderHistoryActivity.this.getHc().confirmRefundTicket(this.val$oi);
      }

      protected void myPostExecute(String paramString)
      {
        OrderHistoryActivity.this.showToast(Html.fromHtml(paramString));
        OrderHistoryActivity.refreshOrder = true;
        OrderTabActivity.orderQueryStatus(2131296963, true, true);
        OrderHistoryActivity.this.queryOrder();
      }
    }
    .execute(new Object[0]);
  }

  protected int getMainLayout()
  {
    return 2130903119;
  }

  public void initRefundTicket(OrderItem paramOrderItem)
  {
    new MyAsyncTask(this, true, paramOrderItem)
    {
      protected String myInBackground(OrderItem[] paramArrayOfOrderItem)
        throws Exception
      {
        return OrderHistoryActivity.this.getHc().initRefundTicket(paramArrayOfOrderItem[0]);
      }

      protected void myPostExecute(String paramString)
      {
        new MyAlertDialog.Builder(getTopActivity(OrderHistoryActivity.this)).setMessage(Html.fromHtml(paramString)).setNeutralButton("取消", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            paramDialogInterface.dismiss();
          }
        }).setNegativeButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            paramDialogInterface.dismiss();
            OrderHistoryActivity.this.confirmRefundTicket(OrderHistoryActivity.2.this.val$oi);
          }
        }).create().show();
      }
    }
    .execute(new OrderItem[] { paramOrderItem });
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296259:
    }
    refreshOrder = true;
    queryOrder();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.rightBt = setClick(2131296259, this);
    this.adapter = new HistoryOrderListAdapter(this, null, 2130903120);
    this.mListView = ((ListView)findViewById(2131296929));
    this.mListView.setAdapter(this.adapter);
    this.loginbt = setClick(2131296930, UserSetActivity.class);
    refreshOrder = true;
  }

  protected void onResume()
  {
    super.onResume();
    queryOrder();
  }

  public void queryOrder()
  {
    if (refreshOrder)
    {
      refreshOrder = false;
      new MyAsyncTask(getTopActivity(), true)
      {
        protected List<OrderResult> myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          return OrderHistoryActivity.this.getHc().myHistoryOrder();
        }

        protected void myPostExecute(List<OrderResult> paramList)
        {
          OrderHistoryActivity.access$002(OrderHistoryActivity.this, paramList);
          OrderHistoryActivity.this.showOrder();
        }
      }
      .execute(new Object[0]);
      return;
    }
    showOrder();
  }

  public void resignTicket(ArrayList<OrderItem> paramArrayList)
  {
    refreshOrder = true;
    OrderItem localOrderItem = (OrderItem)paramArrayList.get(0);
    ChainQuery localChainQuery = new ChainQuery();
    if (isStudentOrder(paramArrayList))
      localChainQuery.setStudent("0X00");
    localChainQuery.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").format(localOrderItem.getOrderDate2()));
    StationNode localStationNode1 = PathService.getInstance().getStationInfoByName(localOrderItem.getFrom());
    StationNode localStationNode2 = PathService.getInstance().getStationInfoByName(localOrderItem.getTo());
    localChainQuery.setOrg(localStationNode1);
    localChainQuery.setArr(localStationNode2);
    Intent localIntent = new Intent(this, ResignSeatListActivity.class);
    this.app.putParms("cq", localChainQuery);
    this.app.putParms("order", paramArrayList);
    startActivity(localIntent);
  }

  public void showOrder()
  {
    setVisibility(2131296257, 8);
    this.loginbt.setVisibility(8);
    this.rightBt.setVisibility(0);
    if (this.order != null)
    {
      sort();
      this.adapter.setMlist(this.order);
      this.adapter.notifyDataSetChanged();
      if (this.order.isEmpty())
      {
        setTv(2131296257, "您没有历史订单");
        findViewById(2131296257).setVisibility(0);
        this.mListView.setVisibility(8);
        return;
      }
      setTv(2131296257, "共有" + this.order.size() + "个历史订单");
      findViewById(2131296257).setVisibility(8);
      this.mListView.setVisibility(0);
      return;
    }
    setTv(2131296257, "您没有历史订单");
    findViewById(2131296257).setVisibility(0);
    this.mListView.setVisibility(8);
  }

  public void sort()
  {
    try
    {
      Collections.sort(this.order, new OrderResultComparator());
      this.mListView.setSelection(0);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        logToServer(new LogInfo("sortErrorHistoryOrder", localException));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.OrderHistoryActivity
 * JD-Core Version:    0.6.0
 */