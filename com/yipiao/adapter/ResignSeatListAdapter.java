package com.yipiao.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.yipiao.activity.SeatListActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.Train;
import java.util.ArrayList;
import java.util.List;

public class ResignSeatListAdapter extends SeatListAdapter
{
  private ArrayList<OrderItem> _order;
  private boolean noQueryOrder = false;

  public ResignSeatListAdapter(BaseActivity paramBaseActivity, List<Train> paramList, int paramInt, ChainQuery paramChainQuery, ArrayList<OrderItem> paramArrayList)
  {
    super(paramBaseActivity, paramList, paramInt, paramChainQuery);
    this._order = paramArrayList;
  }

  public ResignSeatListAdapter(BaseActivity paramBaseActivity, List<Train> paramList, int paramInt, ChainQuery paramChainQuery, ArrayList<OrderItem> paramArrayList, boolean paramBoolean)
  {
    super(paramBaseActivity, paramList, paramInt, paramChainQuery);
    this._order = paramArrayList;
    this.noQueryOrder = paramBoolean;
  }

  protected void btRender(Train paramTrain, View paramView)
  {
    Button localButton = (Button)paramView.findViewById(2131296885);
    localButton.setText("改签");
    localButton.setTag(paramTrain);
    localButton.setEnabled(paramTrain.hasSeat());
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Train localTrain = (Train)paramView.getTag();
        ResignSeatListAdapter.this.goToBook(ResignSeatListAdapter.this.cq, localTrain);
      }
    });
  }

  protected void goToBook(ChainQuery paramChainQuery, Train paramTrain)
  {
    ((SeatListActivity)this.mContext).goToResign(paramChainQuery, paramTrain, this._order, this.noQueryOrder);
  }

  protected View renderItem(Train paramTrain, View paramView)
  {
    return super.renderItem(paramTrain, paramView);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.ResignSeatListAdapter
 * JD-Core Version:    0.6.0
 */