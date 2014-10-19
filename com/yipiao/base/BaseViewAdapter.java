package com.yipiao.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.yipiao.YipiaoApplication;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewAdapter<T> extends BaseAdapter
{
  protected BaseActivity mContext;
  protected View mConvertView;
  protected int mListItemLayoutResID;
  protected List<T> mlist;

  public BaseViewAdapter(BaseActivity paramBaseActivity, List<T> paramList)
  {
    this.mContext = paramBaseActivity;
    this.mlist = paramList;
    this.mListItemLayoutResID = 0;
  }

  public BaseViewAdapter(BaseActivity paramBaseActivity, List<T> paramList, int paramInt)
  {
    this.mContext = paramBaseActivity;
    this.mlist = paramList;
    this.mListItemLayoutResID = paramInt;
  }

  public void appendList(List<T> paramList)
  {
    if (this.mlist == null)
      this.mlist = new ArrayList();
    this.mlist.addAll(paramList);
  }

  public YipiaoApplication getApp()
  {
    return (YipiaoApplication)YipiaoApplication.app;
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

  public Context getTopContext()
  {
    Object localObject = this.mContext;
    int i = 3;
    while (true)
    {
      if ((i <= 0) || (((Activity)localObject).getParent() == null))
        return localObject;
      Activity localActivity = this.mContext.getParent();
      i--;
      localObject = localActivity;
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) && (this.mListItemLayoutResID != 0))
      paramView = LayoutInflater.from(this.mContext).inflate(this.mListItemLayoutResID, null);
    this.mConvertView = paramView;
    return renderItem(this.mlist.get(paramInt), paramView);
  }

  protected abstract View renderItem(T paramT, View paramView);

  protected View setClick(int paramInt, View.OnClickListener paramOnClickListener)
  {
    View localView = this.mConvertView.findViewById(paramInt);
    if (localView == null)
      return localView;
    localView.setOnClickListener(paramOnClickListener);
    return localView;
  }

  public void setMlist(List<T> paramList)
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
 * Qualified Name:     com.yipiao.base.BaseViewAdapter
 * JD-Core Version:    0.6.0
 */