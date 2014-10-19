package com.yipiao.adapter;

import android.view.View;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.StationNode;
import java.util.HashMap;
import java.util.List;

public class RecentQueryAdapter extends BaseViewAdapter<HashMap<String, StationNode>>
{
  public RecentQueryAdapter(BaseActivity paramBaseActivity, List<HashMap<String, StationNode>> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(HashMap<String, StationNode> paramHashMap, View paramView)
  {
    ViewHolder localViewHolder1 = (ViewHolder)paramView.getTag();
    if (localViewHolder1 == null);
    for (ViewHolder localViewHolder2 = new ViewHolder(paramView); ; localViewHolder2 = localViewHolder1)
    {
      localViewHolder2.text1.setText(((StationNode)paramHashMap.get("from")).getName());
      localViewHolder2.text2.setText(((StationNode)paramHashMap.get("to")).getName());
      paramView.setTag(localViewHolder2);
      return paramView;
    }
  }

  class ViewHolder
  {
    TextView text1;
    TextView text2;

    public ViewHolder(View arg2)
    {
      Object localObject;
      this.text1 = ((TextView)localObject.findViewById(2131296689));
      this.text2 = ((TextView)localObject.findViewById(2131296691));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.RecentQueryAdapter
 * JD-Core Version:    0.6.0
 */