package com.yipiao.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class RadioGroupListener
  implements View.OnClickListener
{
  Activity mContext;
  Integer[] radioIds;

  public RadioGroupListener(Activity paramActivity, Integer[] paramArrayOfInteger)
  {
    this.radioIds = paramArrayOfInteger;
    this.mContext = paramActivity;
  }

  public void onClick(View paramView)
  {
    for (int i = 0; i < this.radioIds.length; i++);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.RadioGroupListener
 * JD-Core Version:    0.6.0
 */