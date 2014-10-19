package com.yipiao.adapter;

import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.yipiao.activity.SeatListActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainPrice;
import java.util.List;

public class SeatListAdapter extends BaseViewAdapter<Train>
{
  protected ChainQuery cq;
  private TrainPrice prices = null;
  private boolean showPrice = false;

  public SeatListAdapter(BaseActivity paramBaseActivity, List<Train> paramList, int paramInt, ChainQuery paramChainQuery)
  {
    super(paramBaseActivity, paramList, paramInt);
    this.cq = paramChainQuery;
  }

  protected void btRender(Train paramTrain, View paramView)
  {
    Button localButton = (Button)paramView.findViewById(2131296885);
    localButton.setTag(paramTrain);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Train localTrain = (Train)paramView.getTag();
        SeatListAdapter.this.goToBook(SeatListAdapter.this.cq, localTrain);
      }
    });
    localButton.setEnabled(paramTrain.hasSeat());
    if (paramTrain.getStartPayStr() != null)
    {
      localButton.setText("抢票");
      localButton.setEnabled(true);
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Train localTrain = (Train)paramView.getTag();
          SeatListAdapter.this.goToQianPiao(SeatListAdapter.this.cq, localTrain);
        }
      });
      return;
    }
    localButton.setText("预订");
  }

  public TrainPrice getPrices()
  {
    return this.prices;
  }

  protected void goToBook(ChainQuery paramChainQuery, Train paramTrain)
  {
    ((SeatListActivity)this.mContext).goToBook(paramChainQuery, paramTrain);
  }

  protected void goToQianPiao(ChainQuery paramChainQuery, Train paramTrain)
  {
    ((SeatListActivity)this.mContext).goToQianPiao(paramChainQuery, paramTrain);
  }

  public boolean isShowPrice()
  {
    return this.showPrice;
  }

  protected View renderItem(Train paramTrain, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131296689);
    TextView localTextView2 = (TextView)paramView.findViewById(2131296691);
    TextView localTextView3 = (TextView)paramView.findViewById(2131296693);
    TextView localTextView4 = (TextView)paramView.findViewById(2131296884);
    TextView localTextView5 = (TextView)paramView.findViewById(2131296767);
    TextView localTextView6 = (TextView)paramView.findViewById(2131296768);
    TextView localTextView7 = (TextView)paramView.findViewById(2131296769);
    TextView localTextView8 = (TextView)paramView.findViewById(2131296770);
    TextView localTextView9 = (TextView)paramView.findViewById(2131297084);
    btRender(paramTrain, paramView);
    localTextView1.setText(paramTrain.getCode());
    localTextView2.setText(paramTrain.showTimeLong());
    localTextView3.setText(paramTrain.getFromTime());
    if (paramTrain.isFirstStation())
    {
      localTextView4.setText("始");
      localTextView4.setBackgroundResource(2130837724);
      localTextView5.setText(paramTrain.getFrom());
      localTextView6.setText(paramTrain.getToTime());
      if (!paramTrain.isLastStation())
        break label246;
      localTextView7.setText("终");
      localTextView7.setBackgroundResource(2130837725);
      label190: localTextView8.setText(paramTrain.getTo());
      if (!this.showPrice)
        break label263;
      localTextView9.setText(Html.fromHtml(paramTrain.getSeatHtml(this.prices)));
    }
    while (true)
    {
      paramView.setTag(paramTrain);
      return paramView;
      localTextView4.setText("过");
      localTextView4.setBackgroundResource(2130837723);
      break;
      label246: localTextView7.setText("过");
      localTextView7.setBackgroundResource(2130837723);
      break label190;
      label263: localTextView9.setText(Html.fromHtml(paramTrain.getSeatHtml()));
    }
  }

  public void setPrices(TrainPrice paramTrainPrice)
  {
    this.prices = paramTrainPrice;
  }

  public void setShowPrice(boolean paramBoolean)
  {
    this.showPrice = paramBoolean;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.SeatListAdapter
 * JD-Core Version:    0.6.0
 */