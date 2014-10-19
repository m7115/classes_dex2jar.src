package com.yipiao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.yipiao.view.MyAlertDialogItem;

public class MyAlertDialogAdapter extends BaseAdapter
{
  private Context context;
  private CharSequence[] mListItem;
  private CharSequence[] mListItem2;
  private boolean singleChoiceDialog;

  public MyAlertDialogAdapter(Context paramContext, CharSequence[] paramArrayOfCharSequence1, CharSequence[] paramArrayOfCharSequence2)
  {
    this.context = paramContext;
    this.mListItem = paramArrayOfCharSequence1;
    this.mListItem2 = paramArrayOfCharSequence2;
  }

  public MyAlertDialogAdapter(Context paramContext, CharSequence[] paramArrayOfCharSequence1, CharSequence[] paramArrayOfCharSequence2, boolean paramBoolean)
  {
    this.context = paramContext;
    this.mListItem = paramArrayOfCharSequence1;
    this.mListItem2 = paramArrayOfCharSequence2;
    this.singleChoiceDialog = paramBoolean;
  }

  public int getCount()
  {
    if ((this.mListItem == null) || (this.mListItem.length <= 0))
      return 0;
    return this.mListItem.length;
  }

  public CharSequence getItem(int paramInt)
  {
    return this.mListItem[paramInt];
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    MyAlertDialogItem localMyAlertDialogItem = new MyAlertDialogItem(this.context, this.singleChoiceDialog);
    localMyAlertDialogItem.setTitle1(this.mListItem[paramInt]);
    if ((this.mListItem2 == null) || (this.mListItem2.length <= 0) || (this.mListItem2[paramInt].equals("")))
    {
      localMyAlertDialogItem.setTitle2Visibility(8);
      return localMyAlertDialogItem;
    }
    localMyAlertDialogItem.setTitle2(this.mListItem2[paramInt]);
    localMyAlertDialogItem.setTitle2Visibility(0);
    return localMyAlertDialogItem;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.MyAlertDialogAdapter
 * JD-Core Version:    0.6.0
 */