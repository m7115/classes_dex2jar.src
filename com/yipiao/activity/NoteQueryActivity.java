package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.NoteListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.NoteList;
import java.io.Serializable;

@SuppressLint({"DefaultLocale"})
public class NoteQueryActivity extends BaseActivity
  implements TextWatcher, AdapterView.OnItemClickListener
{
  private NoteListAdapter adapter;
  private EditText editText;
  private ListView mListView;
  protected NoteList orgNoteList;

  public void afterTextChanged(Editable paramEditable)
  {
    if (!this.editText.getText().toString().trim().equals(""));
    queryList(this.editText.getText().toString());
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  protected int getMainLayout()
  {
    return 2130903118;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (checkNeedLaunch())
      return;
    this.orgNoteList = ((NoteList)this.app.getParms("orgNoteList"));
    this.adapter = new NoteListAdapter(this, this.orgNoteList, 2130903071);
    this.adapter.setMlist(this.orgNoteList);
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    this.mListView.setTextFilterEnabled(true);
    this.mListView.setChoiceMode(1);
    this.mListView.setOnItemClickListener(this);
    this.editText = ((EditText)findViewById(2131296737));
    this.editText.addTextChangedListener(this);
    this.mListView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        NoteQueryActivity.this.mListView.setFocusable(true);
        NoteQueryActivity.this.mListView.requestFocus();
        ((InputMethodManager)NoteQueryActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(NoteQueryActivity.this.mListView.getWindowToken(), 0);
        return false;
      }
    });
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    setResult(-1, new Intent().putExtra("DATA", (Serializable)paramView.getTag()));
    finish();
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  protected void queryList(String paramString)
  {
    queryResult(this.orgNoteList.filter(paramString.toLowerCase()));
  }

  protected void queryResult(NoteList paramNoteList)
  {
    this.adapter.setMlist(paramNoteList);
    this.adapter.notifyDataSetChanged();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.NoteQueryActivity
 * JD-Core Version:    0.6.0
 */