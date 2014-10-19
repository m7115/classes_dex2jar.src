package com.yipiao.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.TrainStationInfo;
import java.util.List;

public class StationListAdapter extends BaseViewAdapter<TrainStationInfo>
{
  public StationListAdapter(BaseActivity paramBaseActivity, List<TrainStationInfo> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(TrainStationInfo paramTrainStationInfo, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131296689);
    TextView localTextView2 = (TextView)paramView.findViewById(2131296691);
    TextView localTextView3 = (TextView)paramView.findViewById(2131296693);
    TextView localTextView4 = (TextView)paramView.findViewById(2131296884);
    localTextView1.setText(paramTrainStationInfo.getName());
    localTextView2.setText(paramTrainStationInfo.getArrTime());
    localTextView3.setText(paramTrainStationInfo.getLeaveTime());
    localTextView4.setText(paramTrainStationInfo.getStopMins() + "分钟");
    paramView.setTag(paramTrainStationInfo);
    if (this.mlist.indexOf(paramTrainStationInfo) % 2 == 1)
    {
      paramView.setBackgroundColor(Color.parseColor("#F2F2F2"));
      return paramView;
    }
    paramView.setBackgroundColor(Color.parseColor("#FFFFFF"));
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.StationListAdapter
 * JD-Core Version:    0.6.0
 */