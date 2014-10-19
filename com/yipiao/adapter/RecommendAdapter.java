package com.yipiao.adapter;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import cn.suanya.common.widget.RemoteImageView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.RecommendItem;
import java.util.List;

public class RecommendAdapter extends BaseViewAdapter<RecommendItem>
{
  public RecommendAdapter(BaseActivity paramBaseActivity, List<RecommendItem> paramList)
  {
    super(paramBaseActivity, paramList);
    this.mListItemLayoutResID = 2130903096;
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
      i += 4;
    }
    ViewGroup.LayoutParams localLayoutParams = paramGridView.getLayoutParams();
    localLayoutParams.height = j;
    paramGridView.setLayoutParams(localLayoutParams);
  }

  protected View renderItem(RecommendItem paramRecommendItem, View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131296689);
    RemoteImageView localRemoteImageView = (RemoteImageView)paramView.findViewById(2131296826);
    localTextView.setText(paramRecommendItem.getLabel());
    localRemoteImageView.setImageUrl(paramRecommendItem.getImgUrl());
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.RecommendAdapter
 * JD-Core Version:    0.6.0
 */