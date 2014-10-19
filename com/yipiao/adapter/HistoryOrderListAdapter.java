package com.yipiao.adapter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.common.a.c;
import cn.suanya.hc.service.PathService;
import com.yipiao.YipiaoApplication;
import com.yipiao.activity.FlightWEBActivity;
import com.yipiao.activity.OrderHistoryActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.base.HCUtil;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.StationNode;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.wxapi.DisplayHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class HistoryOrderListAdapter extends BaseViewAdapter<OrderResult>
{
  private LayoutInflater inflater = LayoutInflater.from(this.mContext);

  public HistoryOrderListAdapter(BaseActivity paramBaseActivity, List<OrderResult> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  private void cancelOrder(OrderItem paramOrderItem)
  {
    ((OrderHistoryActivity)this.mContext).initRefundTicket(paramOrderItem);
  }

  private CharSequence[] getOrderItemsLabel(List<OrderItem> paramList)
  {
    String[] arrayOfString = new String[paramList.size()];
    for (int i = 0; i < arrayOfString.length; i++)
    {
      OrderItem localOrderItem = (OrderItem)paramList.get(i);
      arrayOfString[i] = (localOrderItem.getName() + "(" + localOrderItem.getSeatType() + ")");
    }
    return arrayOfString;
  }

  private void resign(ArrayList<OrderItem> paramArrayList)
  {
    ((OrderHistoryActivity)this.mContext).resignTicket(paramArrayList);
  }

  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    int j = 0;
    while (i < localListAdapter.getCount())
    {
      View localView = localListAdapter.getView(i, null, paramListView);
      localView.measure(0, 0);
      j += localView.getMeasuredHeight();
      i++;
    }
    ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
    localLayoutParams.height = (j + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
    paramListView.setLayoutParams(localLayoutParams);
  }

  private String shareString(OrderResult paramOrderResult)
  {
    return paramOrderResult.getLeaveDate() + paramOrderResult.getFrom() + "-" + paramOrderResult.getTo() + "的" + paramOrderResult.getSeatType();
  }

  protected void goHotelWebActivity(String paramString, Date paramDate)
  {
    StationNode localStationNode = PathService.getInstance().getStationInfoByName(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://touch.qunar.com/h5/hotel/?bd_source=zhixing").append("&city=").append(HCUtil.getCityName(localStationNode.getName())).append("&checkInDate=" + c.formart(paramDate, "yyyy-MM-dd"));
    String str = localStringBuilder.toString();
    Intent localIntent = new Intent(this.mContext, FlightWEBActivity.class);
    localIntent.putExtra("url", str);
    localIntent.putExtra("webTip", this.mContext.getLocalApp().launchInfo.optString("hotel_web_tip", "酒店数据由去哪儿网独家提供"));
    if ("http://touch.qunar.com|QN75=closed" != null)
      localIntent.putExtra("cookies", "http://touch.qunar.com|QN75=closed");
    if (0 != 0)
      localIntent.putExtra("postPar", null);
    this.mContext.startActivity(localIntent);
  }

  protected View renderItem(OrderResult paramOrderResult, View paramView)
  {
    Holder localHolder1 = (Holder)paramView.getTag();
    if (localHolder1 == null);
    for (Holder localHolder2 = new Holder(paramView, null); ; localHolder2 = localHolder1)
    {
      localHolder2.ticketLayout.removeViews(1, -2 + localHolder2.ticketLayout.getChildCount());
      Iterator localIterator = paramOrderResult.getOrder().iterator();
      while (localIterator.hasNext())
      {
        OrderItem localOrderItem = (OrderItem)localIterator.next();
        View localView = this.inflater.inflate(2130903121, null);
        ItemHolder localItemHolder = (ItemHolder)localView.getTag();
        if (localItemHolder == null)
          localItemHolder = new ItemHolder(localView, null);
        localItemHolder.seatInfo1.setEnabled(localOrderItem.isEnabel());
        localItemHolder.seatInfo2.setEnabled(localOrderItem.isEnabel());
        localItemHolder.leaveDate.setEnabled(localOrderItem.isEnabel());
        localItemHolder.leaveTime.setEnabled(localOrderItem.isEnabel());
        localItemHolder.passengerInfo.setEnabled(localOrderItem.isEnabel());
        localItemHolder.seatStatus.setEnabled(localOrderItem.isEnabel());
        localItemHolder.passengerInfo.setText(localOrderItem.getName());
        localItemHolder.seatInfo1.setText(localOrderItem.getSeatType() + '(' + localOrderItem.getPrice() + "元)");
        localItemHolder.seatInfo2.setText(localOrderItem.getTrainNo() + '次' + localOrderItem.getCarOfTrain() + localOrderItem.getSeatNo());
        localItemHolder.leaveDate.setText(localOrderItem.getOrderDate());
        localItemHolder.leaveTime.setText(localOrderItem.getLeaveTime() + '开');
        localItemHolder.seatStatus.setText(Html.fromHtml(localOrderItem.getStatus()));
        localView.setTag(localItemHolder);
        localHolder2.ticketLayout.addView(localView, -1 + localHolder2.ticketLayout.getChildCount());
      }
      localHolder2.title.setText(paramOrderResult.getFrom() + "-" + paramOrderResult.getTo());
      localHolder2.title2.setText("(订单号：" + paramOrderResult.getOrderNo() + ")");
      if (paramOrderResult.getCanResign() > 0)
      {
        localHolder2.resignOrder.setVisibility(0);
        if (paramOrderResult.getCanCancel() <= 0)
          break label619;
        localHolder2.cancelOrder.setVisibility(0);
      }
      while (true)
      {
        localHolder2.cancelOrder.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            OrderResult localOrderResult = (OrderResult)paramView.getTag();
            List localList = localOrderResult.canCancelOrder();
            if (localOrderResult.getOrder().size() == 1)
              HistoryOrderListAdapter.this.cancelOrder((OrderItem)localList.get(0));
            if (localOrderResult.getOrder().size() > 1)
              HistoryOrderListAdapter.this.showDialogCancelOrder(localList);
          }
        });
        localHolder2.resignOrder.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            OrderResult localOrderResult = (OrderResult)paramView.getTag();
            ArrayList localArrayList = localOrderResult.canResignOrder();
            if (localOrderResult.getOrder().size() == 1)
              HistoryOrderListAdapter.this.resign(localArrayList);
            if (localOrderResult.getOrder().size() > 1)
              HistoryOrderListAdapter.this.showDialogResign(localArrayList);
          }
        });
        localHolder2.goHotel.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            OrderResult localOrderResult = (OrderResult)paramView.getTag();
            HistoryOrderListAdapter.this.goHotelWebActivity(localOrderResult.getTo(), c.addDays(localOrderResult.getOrderDate2(), 1));
          }
        });
        localHolder2.shareOrder.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            OrderResult localOrderResult = (OrderResult)paramView.getTag();
            DisplayHelper.showShareDialogForOrder(HistoryOrderListAdapter.this.mContext, HistoryOrderListAdapter.this.shareString(localOrderResult), false);
          }
        });
        localHolder2.shareOrder.setTag(paramOrderResult);
        localHolder2.cancelOrder.setTag(paramOrderResult);
        localHolder2.resignOrder.setTag(paramOrderResult);
        localHolder2.goHotel.setTag(paramOrderResult);
        paramView.setTag(localHolder2);
        return paramView;
        localHolder2.resignOrder.setVisibility(8);
        break;
        label619: localHolder2.cancelOrder.setVisibility(8);
      }
    }
  }

  protected Dialog showDialogCancelOrder(List<OrderItem> paramList)
  {
    return new MyAlertDialog.Builder(getTopContext()).setSingleChoiceItems(getOrderItemsLabel(paramList), 0, new DialogInterface.OnClickListener(paramList)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        HistoryOrderListAdapter.this.cancelOrder((OrderItem)this.val$orderItems.get(paramInt));
        paramDialogInterface.dismiss();
      }
    }).setTitle("请选择要退的票").show();
  }

  protected Dialog showDialogResign(List<OrderItem> paramList)
  {
    boolean[] arrayOfBoolean = new boolean[paramList.size()];
    for (int i = 0; i < arrayOfBoolean.length; i++)
      arrayOfBoolean[i] = true;
    return new MyAlertDialog.Builder(getTopContext()).setMultiChoiceItems(getOrderItemsLabel(paramList), arrayOfBoolean, new DialogInterface.OnMultiChoiceClickListener(arrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
      {
        this.val$mulSelect[paramInt] = paramBoolean;
      }
    }).setPositiveButton("确定", new DialogInterface.OnClickListener(arrayOfBoolean, paramList)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        ArrayList localArrayList = new ArrayList();
        for (int i = 0; i < this.val$mulSelect.length; i++)
        {
          if (this.val$mulSelect[i] == 0)
            continue;
          localArrayList.add(this.val$orderItems.get(i));
        }
        if (localArrayList.isEmpty())
        {
          HistoryOrderListAdapter.this.mContext.showToast("请选择要改签的票");
          return;
        }
        HistoryOrderListAdapter.this.resign(localArrayList);
      }
    }).setTitle("请选择要改签的票").show();
  }

  private class Holder
  {
    final Button cancelOrder;
    final Button goHotel;
    final Button resignOrder;
    final Button shareOrder;
    final LinearLayout ticketLayout;
    final TextView title;
    final TextView title2;

    private Holder(View arg2)
    {
      Object localObject;
      this.title = ((TextView)localObject.findViewById(2131296459));
      this.title2 = ((TextView)localObject.findViewById(2131296933));
      this.ticketLayout = ((LinearLayout)localObject.findViewById(2131296931));
      this.cancelOrder = ((Button)localObject.findViewById(2131296935));
      this.resignOrder = ((Button)localObject.findViewById(2131296936));
      this.goHotel = ((Button)localObject.findViewById(2131296937));
      this.shareOrder = ((Button)localObject.findViewById(2131296939));
    }
  }

  private class ItemHolder
  {
    final TextView leaveDate;
    final TextView leaveTime;
    final TextView passengerInfo;
    final TextView seatInfo1;
    final TextView seatInfo2;
    final TextView seatStatus;

    private ItemHolder(View arg2)
    {
      Object localObject;
      this.seatInfo1 = ((TextView)localObject.findViewById(2131296943));
      this.seatInfo2 = ((TextView)localObject.findViewById(2131296944));
      this.leaveDate = ((TextView)localObject.findViewById(2131296941));
      this.leaveTime = ((TextView)localObject.findViewById(2131296942));
      this.passengerInfo = ((TextView)localObject.findViewById(2131296940));
      this.seatStatus = ((TextView)localObject.findViewById(2131296945));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.HistoryOrderListAdapter
 * JD-Core Version:    0.6.0
 */