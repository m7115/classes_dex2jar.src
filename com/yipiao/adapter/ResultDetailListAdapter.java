package com.yipiao.adapter;

import android.view.View;
import android.widget.ImageView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.TrainStationInfo;
import java.util.List;

public class ResultDetailListAdapter extends BaseViewAdapter<TrainStationInfo>
{
  public ResultDetailListAdapter(BaseActivity paramBaseActivity, List<TrainStationInfo> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(TrainStationInfo paramTrainStationInfo, View paramView)
  {
    ImageView localImageView1 = (ImageView)paramView.findViewById(2131296834);
    ImageView localImageView2 = (ImageView)paramView.findViewById(2131296836);
    ((ImageView)paramView.findViewById(2131296835));
    int i = paramTrainStationInfo.getStopMins();
    if (this.mlist.indexOf(paramTrainStationInfo) == -1 + this.mlist.size())
    {
      localImageView2.setVisibility(4);
      setTv(2131296833, "终点站");
      setTv(2131296838, paramTrainStationInfo.getArrTime());
    }
    while (true)
    {
      setTv(2131296837, paramTrainStationInfo.getStation().getName());
      paramView.setTag(paramTrainStationInfo);
      return paramView;
      if (this.mlist.indexOf(paramTrainStationInfo) == 0)
      {
        setTv(2131296833, "始发");
        setTv(2131296838, paramTrainStationInfo.getLeaveTime());
        localImageView1.setVisibility(4);
        continue;
      }
      setTv(2131296838, paramTrainStationInfo.getArrTime());
      setTv(2131296833, i + " '");
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.ResultDetailListAdapter
 * JD-Core Version:    0.6.0
 */