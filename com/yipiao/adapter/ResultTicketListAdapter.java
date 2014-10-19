package com.yipiao.adapter;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.yipiao.activity.TrainDetailActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.Ticket;
import com.yipiao.bean.Train;
import java.util.ArrayList;
import java.util.List;

public class ResultTicketListAdapter extends BaseViewAdapter<Ticket>
{
  private ChainQuery cq;
  private boolean isDisplay = true;
  private Train mtrain;
  private boolean noQueryOrder;
  private ArrayList<OrderItem> order;

  public ResultTicketListAdapter(BaseActivity paramBaseActivity, List<Ticket> paramList, int paramInt, ChainQuery paramChainQuery, Train paramTrain, ArrayList<OrderItem> paramArrayList, boolean paramBoolean)
  {
    super(paramBaseActivity, paramList, paramInt);
    this.cq = paramChainQuery;
    this.mtrain = paramTrain;
    this.order = paramArrayList;
    this.noQueryOrder = paramBoolean;
  }

  protected void btRender(Ticket paramTicket, View paramView)
  {
    Button localButton = (Button)paramView.findViewById(2131296868);
    TextView localTextView = (TextView)paramView.findViewById(2131296867);
    if (this.isDisplay)
    {
      String str = paramTicket.getSeatCode();
      boolean bool;
      if (paramTicket.getLeftNum() == 0)
      {
        bool = true;
        localButton.setVisibility(0);
        localTextView.setVisibility(0);
        localButton.setEnabled(true);
        if (this.order != null)
          break label176;
        if (!bool)
          break label124;
        localTextView.setText("无票");
        localButton.setBackgroundDrawable(this.mContext.getResources().getDrawable(2130837573));
        localButton.setText("监控");
      }
      while (true)
      {
        localButton.setOnClickListener(new View.OnClickListener(bool, str)
        {
          public void onClick(View paramView)
          {
            if (this.val$bool)
            {
              ((TrainDetailActivity)ResultTicketListAdapter.this.mContext).addMonitor(this.val$seatType);
              return;
            }
            ResultTicketListAdapter.this.goToBook(ResultTicketListAdapter.this.cq, ResultTicketListAdapter.this.mtrain, this.val$seatType);
          }
        });
        return;
        bool = false;
        break;
        label124: localButton.setBackgroundDrawable(this.mContext.getResources().getDrawable(2130837553));
        localTextView.setText(paramTicket.getLeftStr() + "张");
        localButton.setText("预订");
      }
      label176: if (bool)
      {
        localTextView.setText("无票");
        localButton.setText("改签");
        localButton.setEnabled(false);
      }
      while (true)
      {
        localButton.setOnClickListener(new View.OnClickListener(str)
        {
          public void onClick(View paramView)
          {
            ((TrainDetailActivity)ResultTicketListAdapter.this.mContext).goToResign(ResultTicketListAdapter.this.cq, ResultTicketListAdapter.this.mtrain, this.val$seatType, ResultTicketListAdapter.this.noQueryOrder);
          }
        });
        return;
        localTextView.setText(paramTicket.getLeftStr() + "张");
        localButton.setText("改签");
      }
    }
    localButton.setVisibility(4);
    localTextView.setVisibility(4);
  }

  protected void goToBook(ChainQuery paramChainQuery, Train paramTrain, String paramString)
  {
    ((TrainDetailActivity)this.mContext).goToBook(paramChainQuery, paramTrain, paramString);
  }

  public boolean isDisplay()
  {
    return this.isDisplay;
  }

  protected View renderItem(Ticket paramTicket, View paramView)
  {
    setTv(2131296865, paramTicket.getSeatName());
    setTv(2131296866, "￥" + paramTicket.getPrice());
    btRender(paramTicket, paramView);
    return paramView;
  }

  public void setCQ(ChainQuery paramChainQuery, Train paramTrain)
  {
    this.cq = paramChainQuery;
    this.mtrain = paramTrain;
    setMlist(this.mtrain.getSeats());
  }

  public void setDisplay(boolean paramBoolean)
  {
    this.isDisplay = paramBoolean;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.ResultTicketListAdapter
 * JD-Core Version:    0.6.0
 */