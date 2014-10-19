package com.yipiao.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.yipiao.activity.HalfwayTicketActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.Ticket;
import com.yipiao.bean.Train;
import java.util.Iterator;
import java.util.List;

public class HalfwayTicketListAdapter extends BaseViewAdapter<Train>
{
  private String from;
  private String fromTime;
  private int index;
  private boolean isQuery;
  private boolean isShowPrice;
  private int listSize;
  private int mLastStation;
  private List<Ticket> ticketSeats;
  private String to;
  private String toTime;

  public HalfwayTicketListAdapter(BaseActivity paramBaseActivity, List<Train> paramList, int paramInt1, Train paramTrain, int paramInt2)
  {
    super(paramBaseActivity, paramList, paramInt1);
    this.to = paramTrain.getTo();
    this.from = paramTrain.getFrom();
    this.fromTime = paramTrain.getFromTime();
    this.toTime = paramTrain.getToTime();
    this.index = -1;
    this.isQuery = true;
    this.mLastStation = paramInt2;
    this.isShowPrice = false;
  }

  private String getStationPrice(Train paramTrain)
  {
    this.ticketSeats = paramTrain.getSeats();
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.ticketSeats.iterator();
    while (localIterator.hasNext())
    {
      Ticket localTicket = (Ticket)localIterator.next();
      String str = localTicket.getPrice();
      if ((str == null) || (str.length() < 1))
        str = "无票价";
      if (localTicket.getLeftNum() == 0)
        localStringBuilder.append("<font color='#888888'>").append(localTicket.getSeatName()).append(":￥").append(str).append("</font>");
      if (localTicket.getLeftNum() > 0)
        localStringBuilder.append(localTicket.getSeatName()).append(":<font color='#F07C57'>￥").append(str).append("</font>");
      localStringBuilder.append(" ");
    }
    return localStringBuilder.toString();
  }

  private void goToBook(Train paramTrain)
  {
    ((HalfwayTicketActivity)this.mContext).goToBook(paramTrain);
  }

  private void setDefaultStationInfo(int paramInt, Train paramTrain, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131296853);
    TextView localTextView2 = (TextView)paramView.findViewById(2131296855);
    String str1 = "<font color='#858585'>" + this.to + "</font>";
    String str2 = "<font color='#0099FA'>" + paramTrain.getTo() + "</font>";
    if (paramInt > -1 + this.mLastStation)
    {
      localTextView2.setText(Html.fromHtml(str1));
      localTextView1.setText(Html.fromHtml(str2));
      return;
    }
    localTextView2.setText(Html.fromHtml(str2));
    localTextView1.setText(Html.fromHtml(str1));
  }

  private void setStationInfo(int paramInt, Train paramTrain, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131296855);
    TextView localTextView2 = (TextView)paramView.findViewById(2131296853);
    TextView localTextView3 = (TextView)paramView.findViewById(2131296856);
    TextView localTextView4 = (TextView)paramView.findViewById(2131296854);
    String str1 = "<font color='#858585'>" + this.to + "</font>";
    String str2 = "<font color='#858585'>" + this.toTime + "</font>";
    String str3 = "<font color='#0099FA'>" + paramTrain.getTo() + "</font>";
    String str4 = "<font color='#0099FA'>" + paramTrain.getToTime() + "</font>";
    if (paramInt > -1 + this.mLastStation)
    {
      localTextView1.setText(Html.fromHtml(str1));
      localTextView3.setText(Html.fromHtml(str2));
      localTextView2.setText(Html.fromHtml(str3));
      localTextView4.setText(Html.fromHtml(str4));
      return;
    }
    localTextView2.setText(Html.fromHtml(str1));
    localTextView4.setText(Html.fromHtml(str2));
    localTextView1.setText(Html.fromHtml(str3));
    localTextView3.setText(Html.fromHtml(str4));
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = LayoutInflater.from(this.mContext).inflate(this.mListItemLayoutResID, null);
    this.mConvertView = localView;
    ((TextView)localView.findViewById(2131296851)).setText(this.from);
    ((TextView)localView.findViewById(2131296852)).setText(this.fromTime);
    if (paramInt <= this.index)
      return renderItem(paramInt, (Train)this.mlist.get(paramInt), localView);
    return renderItem(paramInt, localView);
  }

  public boolean isShowPrice()
  {
    return this.isShowPrice;
  }

  public void notifyIndex()
  {
    this.index = (1 + this.index);
  }

  protected View renderItem(int paramInt, View paramView)
  {
    Train localTrain = (Train)this.mlist.get(paramInt);
    ProgressBar localProgressBar = (ProgressBar)paramView.findViewById(2131296858);
    setDefaultStationInfo(paramInt, localTrain, paramView);
    if ((paramInt == 1 + this.index) && (this.isQuery))
      localProgressBar.setVisibility(0);
    return paramView;
  }

  protected View renderItem(int paramInt, Train paramTrain, View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131296859);
    Button localButton = (Button)paramView.findViewById(2131296857);
    if (paramTrain.getSeats() == null)
    {
      localTextView.setText(Html.fromHtml("<font color='#888888'>列车在此站点不售票</font>"));
      setDefaultStationInfo(paramInt, paramTrain, paramView);
      localButton.setVisibility(4);
      return paramView;
    }
    if (paramTrain.hasSeat())
    {
      localButton.setEnabled(true);
      localButton.setTag(paramTrain);
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Train localTrain = (Train)paramView.getTag();
          HalfwayTicketListAdapter.this.goToBook(localTrain);
        }
      });
      setStationInfo(paramInt, paramTrain, paramView);
      if (!this.isShowPrice)
        break label135;
      localTextView.setText(Html.fromHtml(getStationPrice(paramTrain)));
    }
    while (true)
    {
      localButton.setVisibility(0);
      paramView.setTag(paramTrain);
      return paramView;
      localButton.setEnabled(false);
      break;
      label135: localTextView.setText(Html.fromHtml(paramTrain.getSeatHtml()));
    }
  }

  protected View renderItem(Train paramTrain, View paramView)
  {
    return null;
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }

  public void setQuery(boolean paramBoolean)
  {
    this.isQuery = paramBoolean;
  }

  public void setShowPrice(boolean paramBoolean)
  {
    this.isShowPrice = paramBoolean;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.HalfwayTicketListAdapter
 * JD-Core Version:    0.6.0
 */