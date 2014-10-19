package com.yipiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.NoteListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.NoteList;
import java.io.Serializable;

public class NoteSimpleActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  private NoteListAdapter adapter;
  private ListView mListView;
  protected NoteList noteList;

  protected int getMainLayout()
  {
    return 2130903117;
  }

  public void onClick(View paramView)
  {
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (checkNeedLaunch())
      return;
    this.noteList = ((NoteList)getLocalApp().getParms("noteList"));
    this.adapter = new NoteListAdapter(this, this.noteList, 2130903071);
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setTextFilterEnabled(true);
    this.mListView.setChoiceMode(1);
    this.mListView.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    setResult(-1, new Intent().putExtra("DATA", (Serializable)paramView.getTag()));
    finish();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.NoteSimpleActivity
 * JD-Core Version:    0.6.0
 */