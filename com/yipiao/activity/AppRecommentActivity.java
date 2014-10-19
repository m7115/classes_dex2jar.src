package com.yipiao.activity;

import android.os.Bundle;
import android.widget.ListView;
import com.yipiao.adapter.AppRecommentListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.service.YipiaoService;

public class AppRecommentActivity extends BaseActivity
{
  private AppRecommentListAdapter adapter;
  private ListView mListView;

  protected int getMainLayout()
  {
    return 2130903042;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.adapter = new AppRecommentListAdapter(this, YipiaoService.getInstance().getRecommendList(), 2130903064);
    this.mListView = ((ListView)findViewById(2131296687));
    this.mListView.setAdapter(this.adapter);
    this.adapter.notifyDataSetChanged();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.AppRecommentActivity
 * JD-Core Version:    0.6.0
 */