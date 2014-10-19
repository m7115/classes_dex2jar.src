package com.yipiao.adapter;

import android.view.View;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.Note;
import java.util.List;

public class NoteListAdapter extends BaseViewAdapter<Note>
{
  public NoteListAdapter(BaseActivity paramBaseActivity, List<Note> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(Note paramNote, View paramView)
  {
    ((TextView)paramView.findViewById(2131296796)).setText(paramNote.getName());
    paramView.setTag(paramNote);
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.NoteListAdapter
 * JD-Core Version:    0.6.0
 */