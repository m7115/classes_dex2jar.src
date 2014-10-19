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
import android.widget.TextView;
import cn.suanya.hc.service.PathService;
import com.yipiao.adapter.NoteListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.Note;
import java.io.Serializable;
import java.util.List;

public class CityListActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  private NoteListAdapter adapterHot;
  private NoteListAdapter adapterRecent;
  private EditText etFilter;
  private GridView listViewHot;
  private GridView listViewRecent;
  private PathService mPathService;
  protected List<Note> noteListHot;
  protected List<Note> noteListRecent;
  private TextView tvHot;
  private TextView tvRecent;

  protected int getMainLayout()
  {
    return 2130903070;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.listViewRecent = ((GridView)findViewById(2131296793));
    this.listViewHot = ((GridView)findViewById(2131296795));
    this.etFilter = ((EditText)findViewById(2131296791));
    this.tvRecent = ((TextView)findViewById(2131296792));
    this.tvHot = ((TextView)findViewById(2131296794));
    this.mPathService = PathService.getInstance();
    this.noteListRecent = this.mPathService.getRecentStations(9);
    if ((this.noteListRecent != null) && (this.noteListRecent.size() > 0))
    {
      this.adapterRecent = new NoteListAdapter(this, this.noteListRecent, 2130903071);
      this.adapterRecent.setMlist(this.noteListRecent);
      this.listViewRecent.setAdapter(this.adapterRecent);
      this.listViewRecent.setOnItemClickListener(this);
    }
    while (true)
    {
      this.noteListHot = this.mPathService.getHotStations(30);
      this.adapterHot = new NoteListAdapter(this, this.noteListHot, 2130903071);
      this.adapterHot.setMlist(this.noteListHot);
      this.listViewHot.setAdapter(this.adapterHot);
      this.listViewHot.setOnItemClickListener(this);
      this.etFilter.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramEditable)
        {
          String str = paramEditable.toString().toLowerCase();
          if ("".equals(str))
          {
            if ((CityListActivity.this.adapterRecent != null) && (CityListActivity.this.adapterRecent.getCount() > 0))
            {
              CityListActivity.this.listViewRecent.setVisibility(0);
              CityListActivity.this.tvRecent.setVisibility(0);
            }
            CityListActivity.this.tvHot.setVisibility(0);
            CityListActivity.this.adapterHot.setMlist(CityListActivity.this.noteListHot);
          }
          while (true)
          {
            CityListActivity.this.adapterHot.notifyDataSetChanged();
            return;
            CityListActivity.this.listViewRecent.setVisibility(8);
            CityListActivity.this.tvRecent.setVisibility(8);
            CityListActivity.this.tvHot.setVisibility(8);
            CityListActivity.this.adapterHot.setMlist(CityListActivity.this.mPathService.getStationsByKey(str));
          }
        }

        public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
        }

        public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
        {
        }
      });
      return;
      this.listViewRecent.setVisibility(8);
      this.tvRecent.setVisibility(8);
    }
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.mPathService.addRecentStation(((Note)paramView.getTag()).getCode());
    setResult(-1, new Intent().putExtra("DATA", (Serializable)paramView.getTag()));
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(this.etFilter.getWindowToken(), 0);
    finish();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.CityListActivity
 * JD-Core Version:    0.6.0
 */