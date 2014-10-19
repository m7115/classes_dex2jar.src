package cn.suanya.hotel.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import cn.suanya.hotel.base.R.color;
import cn.suanya.hotel.base.R.drawable;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.RoomStatus;
import cn.suanya.hotel.domain.RoomStatusSum;
import cn.suanya.hotel.util.HTUtil;
import java.util.List;

public class RoomStatusListItemAdapter extends BaseExpandableListAdapter
{
  private ExpandableListView expandableListView;
  private Drawable mBDown;
  private Drawable mBUp;
  private int mChildItemLayoutResID = R.layout.room_status_item;
  private Context mContext;
  private int mGroupItemLayoutResID = R.layout.room_status_sum_item;
  private List<RoomStatusSum> mList;

  public RoomStatusListItemAdapter(Context paramContext, List<RoomStatusSum> paramList, ExpandableListView paramExpandableListView)
  {
    this.mList = paramList;
    this.mContext = paramContext;
    this.mBDown = this.mContext.getResources().getDrawable(R.drawable.bookroom_down);
    this.mBUp = this.mContext.getResources().getDrawable(R.drawable.bookroom_up);
    this.expandableListView = paramExpandableListView;
    this.expandableListView.setGroupIndicator(null);
  }

  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((RoomStatusSum)this.mList.get(paramInt1)).getRoomStatus().get(paramInt2);
  }

  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2 + paramInt1 * 10000;
  }

  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) && (this.mChildItemLayoutResID != 0))
      paramView = LayoutInflater.from(this.mContext).inflate(this.mChildItemLayoutResID, null);
    RoomStatus localRoomStatus = (RoomStatus)((RoomStatusSum)this.mList.get(paramInt1)).getRoomStatus().get(paramInt2);
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.sourceName);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.roomType);
    TextView localTextView3 = (TextView)paramView.findViewById(R.id.priceDesc);
    TextView localTextView4 = (TextView)paramView.findViewById(R.id.salesDesc);
    TextView localTextView5 = (TextView)paramView.findViewById(R.id.rmb_symbol);
    TextView localTextView6 = (TextView)paramView.findViewById(R.id.bookbutton);
    localTextView1.setText(localRoomStatus.getSourceName());
    localTextView2.setText(localRoomStatus.getRoomType());
    localTextView3.setText(localRoomStatus.getPriceDesc());
    localTextView4.setText(localRoomStatus.getSalesDesc());
    if (localRoomStatus.isCanBook())
    {
      localTextView6.setText("预订");
      localTextView6.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.shape_available));
      localTextView5.setTextColor(this.mContext.getResources().getColor(R.color.price_text_color));
      localTextView3.setTextColor(this.mContext.getResources().getColor(R.color.price_text_color));
      return paramView;
    }
    localTextView6.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.shape_full));
    localTextView6.setText("满房");
    localTextView5.setTextColor(this.mContext.getResources().getColor(R.color.qi_text_color));
    localTextView3.setTextColor(this.mContext.getResources().getColor(R.color.qi_text_color));
    return paramView;
  }

  public int getChildrenCount(int paramInt)
  {
    return ((RoomStatusSum)this.mList.get(paramInt)).getRoomStatus().size();
  }

  public Object getGroup(int paramInt)
  {
    return this.mList.get(paramInt);
  }

  public int getGroupCount()
  {
    return this.mList.size();
  }

  public long getGroupId(int paramInt)
  {
    return paramInt * 10000;
  }

  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) && (this.mGroupItemLayoutResID != 0))
      paramView = LayoutInflater.from(this.mContext).inflate(this.mGroupItemLayoutResID, null);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.bookPic);
    RoomStatusSum localRoomStatusSum = (RoomStatusSum)this.mList.get(paramInt);
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.roomName);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.room_price_text);
    TextView localTextView3 = (TextView)paramView.findViewById(R.id.rmb_symbol);
    localTextView1.setText(localRoomStatusSum.getRoomName());
    localTextView2.setText(HTUtil.formatDouble(localRoomStatusSum.getLowestPrice()));
    if (localRoomStatusSum.isCanBook())
    {
      localTextView3.setTextColor(this.mContext.getResources().getColor(R.color.price_text_color));
      localTextView2.setTextColor(this.mContext.getResources().getColor(R.color.price_text_color));
    }
    while (paramBoolean)
    {
      localImageView.setImageDrawable(this.mBUp);
      return paramView;
      localTextView3.setTextColor(this.mContext.getResources().getColor(R.color.qi_text_color));
      localTextView2.setTextColor(this.mContext.getResources().getColor(R.color.qi_text_color));
    }
    localImageView.setImageDrawable(this.mBDown);
    return paramView;
  }

  public boolean hasStableIds()
  {
    return true;
  }

  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.adapter.RoomStatusListItemAdapter
 * JD-Core Version:    0.6.0
 */