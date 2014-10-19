package com.yipiao.adapter;

import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import java.util.List;

public class SimpleBaseViewAdapter extends BaseViewAdapter<String>
{
  public SimpleBaseViewAdapter(BaseActivity paramBaseActivity, List<String> paramList)
  {
    super(paramBaseActivity, paramList, 2130903168);
  }

  protected View renderItem(String paramString, View paramView)
  {
    ((TextView)paramView.findViewById(2131296689)).setText(Html.fromHtml(paramString));
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.SimpleBaseViewAdapter
 * JD-Core Version:    0.6.0
 */