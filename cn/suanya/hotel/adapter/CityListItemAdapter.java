package cn.suanya.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import cn.suanya.common.a.p;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.ListSection;
import cn.suanya.hotel.domain.ListSectionGroup;

public class CityListItemAdapter extends BaseAdapter
  implements SectionIndexer
{
  private Context mContext;
  private ListSectionGroup mList;

  public CityListItemAdapter(Context paramContext, ListSectionGroup paramListSectionGroup)
  {
    this.mContext = paramContext;
    this.mList = paramListSectionGroup;
  }

  public int getCount()
  {
    return this.mList.getCount();
  }

  public Object getItem(int paramInt)
  {
    return this.mList.getItem(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getPositionForSection(int paramInt)
  {
    return this.mList.getPositionForSection(paramInt);
  }

  public int getSectionForPosition(int paramInt)
  {
    return this.mList.getSectionIndexForPosition(paramInt);
  }

  public Object[] getSections()
  {
    return this.mList.getSection();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) && (R.layout.citylist_item != 0))
      paramView = LayoutInflater.from(this.mContext).inflate(R.layout.citylist_item, null);
    p localp = this.mList.getItem(paramInt);
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.city);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.head);
    localTextView1.setText(localp.getName());
    localTextView2.setVisibility(8);
    if (this.mList.isFirstItem(paramInt))
    {
      localTextView2.setText(this.mList.getSectionForPosition(paramInt).getName());
      localTextView2.setVisibility(0);
    }
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.adapter.CityListItemAdapter
 * JD-Core Version:    0.6.0
 */