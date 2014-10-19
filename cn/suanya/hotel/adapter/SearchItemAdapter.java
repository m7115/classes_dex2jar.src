package cn.suanya.hotel.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.suanya.common.ui.SYActivity;
import cn.suanya.common.ui.SYViewAdapter;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.domain.NodeInfo;
import java.util.List;

public class SearchItemAdapter extends SYViewAdapter<NodeInfo>
{
  public SearchItemAdapter(SYActivity paramSYActivity, List<NodeInfo> paramList, int paramInt)
  {
    super(paramSYActivity, paramList, paramInt);
  }

  public Object getItem(int paramInt)
  {
    return super.getItem(paramInt);
  }

  protected View renderItem(NodeInfo paramNodeInfo, View paramView)
  {
    int i = 4;
    String str1 = paramNodeInfo.getLable();
    String str2 = paramNodeInfo.getValue();
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.item_lable);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.value_text);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.arrow);
    EditText localEditText = (EditText)paramView.findViewById(R.id.value_edit_text);
    if ("关键字".equals(str1))
    {
      localEditText.setHint(str2);
      localEditText.setVisibility(0);
      localImageView.setVisibility(i);
      localTextView2.setVisibility(8);
      localTextView1.setText(str1);
      return paramView;
    }
    if ("入住天数".equals(str1));
    while (true)
    {
      localImageView.setVisibility(i);
      localEditText.setVisibility(8);
      localTextView2.setVisibility(0);
      localTextView2.setText(str2);
      break;
      i = 0;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.adapter.SearchItemAdapter
 * JD-Core Version:    0.6.0
 */