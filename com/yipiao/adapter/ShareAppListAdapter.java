package com.yipiao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.yipiao.bean.AppInfo;
import java.util.ArrayList;
import java.util.List;

public class ShareAppListAdapter extends BaseAdapter
{
  protected Context mContext;
  protected View mConvertView;
  protected int mListItemLayoutResID;
  protected List<AppInfo> mlist;

  public ShareAppListAdapter(Context paramContext, List<AppInfo> paramList, int paramInt)
  {
    this.mContext = paramContext;
    this.mlist = paramList;
    this.mListItemLayoutResID = paramInt;
  }

  public static void setListViewHeightBasedOnChildren(GridView paramGridView)
  {
    ListAdapter localListAdapter = paramGridView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    int j = 0;
    while (i < localListAdapter.getCount())
    {
      View localView = localListAdapter.getView(i, null, paramGridView);
      localView.measure(0, 0);
      j += localView.getMeasuredHeight();
      i += 3;
    }
    paramGridView.setVerticalSpacing(10);
    paramGridView.setMinimumHeight(j + 10 * (-1 + localListAdapter.getCount()));
  }

  public void appendList(List<AppInfo> paramList)
  {
    if (this.mlist == null)
      this.mlist = new ArrayList();
    this.mlist.addAll(paramList);
  }

  public int getCount()
  {
    if (this.mlist == null)
      return 0;
    return this.mlist.size();
  }

  public Object getItem(int paramInt)
  {
    return this.mlist.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) && (this.mListItemLayoutResID != 0))
      paramView = LayoutInflater.from(this.mContext).inflate(this.mListItemLayoutResID, null);
    this.mConvertView = paramView;
    return renderItem((AppInfo)this.mlist.get(paramInt), paramView);
  }

  protected View renderItem(AppInfo paramAppInfo, View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131296689);
    ImageView localImageView = (ImageView)paramView.findViewById(2131296826);
    localTextView.setText(paramAppInfo.getAppName());
    localImageView.setImageDrawable(paramAppInfo.getAppIcon());
    return paramView;
  }

  protected View setClick(int paramInt, View.OnClickListener paramOnClickListener)
  {
    View localView = this.mConvertView.findViewById(paramInt);
    if (localView == null)
      return localView;
    localView.setOnClickListener(paramOnClickListener);
    return localView;
  }

  public void setMlist(List<AppInfo> paramList)
  {
    this.mlist = paramList;
  }

  protected TextView setTv(int paramInt, CharSequence paramCharSequence)
  {
    TextView localTextView = (TextView)this.mConvertView.findViewById(paramInt);
    localTextView.setText(paramCharSequence);
    return localTextView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.ShareAppListAdapter
 * JD-Core Version:    0.6.0
 */