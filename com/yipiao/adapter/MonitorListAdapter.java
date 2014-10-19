package com.yipiao.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.StationNode;
import java.util.List;

public class MonitorListAdapter extends BaseViewAdapter<MonitorInfo>
{
  public MonitorListAdapter(BaseActivity paramBaseActivity, List<MonitorInfo> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  private void updateStatus(MonitorInfo paramMonitorInfo, View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131296767);
    Button localButton1 = (Button)paramView.findViewById(2131296885);
    Button localButton2 = (Button)paramView.findViewById(2131296886);
    localTextView.setVisibility(8);
    localButton1.setVisibility(8);
    localButton2.setVisibility(8);
    localButton1.setTag(paramMonitorInfo);
    if (paramMonitorInfo.isRuning())
      localTextView.setBackgroundResource(2130837766);
    while (true)
      switch (paramMonitorInfo.getStatus())
      {
      default:
        return;
        localTextView.setBackgroundResource(2130837765);
      case 0:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 1:
      }
    localTextView.setText("已经过期");
    localTextView.setVisibility(0);
    return;
    localTextView.setText("正在监控");
    localTextView.setVisibility(0);
    return;
    localTextView.setText("执行监控");
    localTextView.setVisibility(0);
    return;
    localTextView.setText("监控成功");
    localButton1.setVisibility(0);
    return;
    localTextView.setText("正在抢票");
    localTextView.setVisibility(0);
    return;
    localTextView.setText("抢票成功");
    localButton2.setVisibility(0);
    return;
    localTextView.setText("已经停止");
    localTextView.setVisibility(0);
  }

  protected View renderItem(MonitorInfo paramMonitorInfo, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131296689);
    TextView localTextView2 = (TextView)paramView.findViewById(2131296691);
    TextView localTextView3 = (TextView)paramView.findViewById(2131296693);
    TextView localTextView4 = (TextView)paramView.findViewById(2131296884);
    TextView localTextView5 = (TextView)paramView.findViewById(2131296768);
    localTextView1.setText(paramMonitorInfo.getCq().getOrg().getName() + "-" + paramMonitorInfo.getCq().getArr().getName());
    localTextView2.setText(paramMonitorInfo.getCq().getLeaveDate());
    localTextView3.setText(paramMonitorInfo.getSeatTypes().linkName("或"));
    localTextView4.setText("");
    localTextView5.setText(paramMonitorInfo.getLastLog());
    updateStatus(paramMonitorInfo, paramView);
    paramView.setTag(paramMonitorInfo);
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.MonitorListAdapter
 * JD-Core Version:    0.6.0
 */