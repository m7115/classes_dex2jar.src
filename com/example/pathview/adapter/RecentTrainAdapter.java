package com.example.pathview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.pathview.bean.RecentTrain;
import java.util.List;

public class RecentTrainAdapter extends BaseAdapter
{
  private LayoutInflater inflater;
  private List<RecentTrain> trainList;

  public RecentTrainAdapter(Context paramContext, List<RecentTrain> paramList)
  {
    this.trainList = paramList;
    this.inflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    return this.trainList.size();
  }

  public RecentTrain getItem(int paramInt)
  {
    return (RecentTrain)this.trainList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = this.inflater.inflate(2130903102, null);
    ViewHolder localViewHolder1 = (ViewHolder)paramView.getTag();
    if (localViewHolder1 == null);
    for (ViewHolder localViewHolder2 = new ViewHolder(paramView); ; localViewHolder2 = localViewHolder1)
    {
      RecentTrain localRecentTrain = (RecentTrain)this.trainList.get(paramInt);
      localViewHolder2.tvCode.setText(localRecentTrain.getCode());
      localViewHolder2.tvStart.setText(localRecentTrain.getStationStart());
      localViewHolder2.tvEnd.setText(localRecentTrain.getStationEnd());
      paramView.setTag(localViewHolder2);
      return paramView;
    }
  }

  public void refresh(List<RecentTrain> paramList)
  {
    this.trainList = paramList;
    notifyDataSetChanged();
  }

  private class ViewHolder
  {
    TextView tvCode;
    TextView tvEnd;
    TextView tvStart;

    public ViewHolder(View arg2)
    {
      Object localObject;
      this.tvCode = ((TextView)localObject.findViewById(2131296860));
      this.tvStart = ((TextView)localObject.findViewById(2131296863));
      this.tvEnd = ((TextView)localObject.findViewById(2131296864));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.adapter.RecentTrainAdapter
 * JD-Core Version:    0.6.0
 */