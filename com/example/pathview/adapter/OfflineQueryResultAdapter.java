package com.example.pathview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.pathview.bean.RecentTrain;
import java.util.List;

public class OfflineQueryResultAdapter extends BaseAdapter
{
  private LayoutInflater inflater;
  private List<RecentTrain> list;

  public OfflineQueryResultAdapter(Context paramContext, List<RecentTrain> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.list = paramList;
  }

  public int getCount()
  {
    if (this.list != null)
      return this.list.size();
    return 0;
  }

  public RecentTrain getItem(int paramInt)
  {
    if (this.list != null)
      return (RecentTrain)this.list.get(paramInt);
    return null;
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = this.inflater.inflate(2130903094, null);
    ViewHolder localViewHolder1 = (ViewHolder)paramView.getTag();
    if (localViewHolder1 == null);
    for (ViewHolder localViewHolder2 = new ViewHolder(paramView); ; localViewHolder2 = localViewHolder1)
    {
      RecentTrain localRecentTrain = (RecentTrain)this.list.get(paramInt);
      localViewHolder2.code.setText(localRecentTrain.getCode());
      localViewHolder2.stationStart.setText(localRecentTrain.getStationStart());
      localViewHolder2.timeStart.setText(localRecentTrain.getTimeStart());
      localViewHolder2.stationEnd.setText(localRecentTrain.getStationEnd());
      localViewHolder2.timeEnd.setText(localRecentTrain.getTimeEnd());
      paramView.setTag(localViewHolder2);
      return paramView;
    }
  }

  public void refresh(List<RecentTrain> paramList)
  {
    this.list = paramList;
    notifyDataSetChanged();
  }

  private class ViewHolder
  {
    TextView code;
    TextView stationEnd;
    TextView stationStart;
    TextView timeEnd;
    TextView timeStart;

    public ViewHolder(View arg2)
    {
      Object localObject;
      this.code = ((TextView)localObject.findViewById(2131296860));
      this.stationStart = ((TextView)localObject.findViewById(2131296863));
      this.timeStart = ((TextView)localObject.findViewById(2131296861));
      this.stationEnd = ((TextView)localObject.findViewById(2131296864));
      this.timeEnd = ((TextView)localObject.findViewById(2131296862));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.adapter.OfflineQueryResultAdapter
 * JD-Core Version:    0.6.0
 */