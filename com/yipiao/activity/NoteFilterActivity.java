package com.yipiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.NoteListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.NoteList;
import java.io.Serializable;

public class NoteFilterActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  private NoteListAdapter adapter;
  private EditText etFilter;
  private GridView mListView;
  protected NoteList orgNoteList;

  protected int getMainLayout()
  {
    return 2130903072;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (checkNeedLaunch())
      return;
    this.orgNoteList = ((NoteList)this.app.getParms("orgNoteList"));
    this.adapter = new NoteListAdapter(this, this.orgNoteList, 2130903071);
    this.adapter.setMlist(this.orgNoteList);
    this.mListView = ((GridView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setTextFilterEnabled(true);
    this.mListView.setOnItemClickListener(this);
    this.etFilter = ((EditText)findViewById(2131296791));
    this.etFilter.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        String str = paramEditable.toString().toLowerCase();
        if ("".equals(str))
          NoteFilterActivity.this.adapter.setMlist(NoteFilterActivity.this.orgNoteList);
        while (true)
        {
          NoteFilterActivity.this.adapter.notifyDataSetChanged();
          return;
          NoteFilterActivity.this.adapter.setMlist(NoteFilterActivity.this.orgNoteList.filter(str));
        }
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }
    });
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    setResult(-1, new Intent().putExtra("DATA", (Serializable)paramView.getTag()));
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.etFilter.getWindowToken(), 0);
    finish();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.NoteFilterActivity
 * JD-Core Version:    0.6.0
 */