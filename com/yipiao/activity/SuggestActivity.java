package com.yipiao.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.yipiao.base.BaseActivity;

public class SuggestActivity extends BaseActivity
{
  protected int getMainLayout()
  {
    return 2130903172;
  }

  public void init()
  {
    super.init();
    setClick(2131296695, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        String str = SuggestActivity.this.getVString(2131297103);
        if ((str == null) || (str.length() < 1))
        {
          SuggestActivity.this.showToast("您还没有填写建议！");
          return;
        }
        SuggestActivity.this.logToServer("suggest", str);
        SuggestActivity.this.showToast("建议已经提交，谢谢！");
        SuggestActivity.this.finish();
      }
    });
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.SuggestActivity
 * JD-Core Version:    0.6.0
 */