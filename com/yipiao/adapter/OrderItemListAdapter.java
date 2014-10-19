package com.yipiao.adapter;

import android.view.View;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.OrderItem;
import java.util.List;

public class OrderItemListAdapter extends BaseViewAdapter<OrderItem>
{
  public OrderItemListAdapter(BaseActivity paramBaseActivity, List<OrderItem> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(OrderItem paramOrderItem, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131296943);
    TextView localTextView2 = (TextView)paramView.findViewById(2131296940);
    TextView localTextView3 = (TextView)paramView.findViewById(2131296961);
    localTextView2.setText(paramOrderItem.getName());
    localTextView3.setText(paramOrderItem.getPrice() + "元");
    localTextView1.setText(paramOrderItem.seatInfo());
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.OrderItemListAdapter
 * JD-Core Version:    0.6.0
 */