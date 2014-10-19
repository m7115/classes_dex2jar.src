package com.squareup.timessquare;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.suanya.a.a;
import cn.suanya.a.c;
import cn.suanya.a.d;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class MonthView extends LinearLayout
{
  private static final String[] WEEK_DISPLAY_NAMES = { "日", "一", "二", "三", "四", "五", "六" };
  private CalendarGridView grid;
  private Listener listener;
  private TextView title;

  public MonthView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public static MonthView create(ViewGroup paramViewGroup, LayoutInflater paramLayoutInflater, DateFormat paramDateFormat, Listener paramListener, Calendar paramCalendar)
  {
    MonthView localMonthView = (MonthView)paramLayoutInflater.inflate(a.d.month, paramViewGroup, false);
    int i = paramCalendar.get(7);
    CalendarRowView localCalendarRowView = (CalendarRowView)localMonthView.grid.getChildAt(0);
    for (int j = 1; j <= 7; j++)
    {
      paramCalendar.set(7, j);
      ((TextView)localCalendarRowView.getChildAt(j - 1)).setText(WEEK_DISPLAY_NAMES[(j - 1)]);
    }
    paramCalendar.set(7, i);
    localMonthView.listener = paramListener;
    return localMonthView;
  }

  public void init(MonthDescriptor paramMonthDescriptor, List<List<MonthCellDescriptor>> paramList)
  {
    Logr.d("Initializing MonthView for %s", new Object[] { paramMonthDescriptor });
    long l = System.currentTimeMillis();
    this.title.setText(paramMonthDescriptor.getLabel());
    int i = paramList.size();
    for (int j = 0; j < 6; j++)
    {
      CalendarRowView localCalendarRowView = (CalendarRowView)this.grid.getChildAt(j + 1);
      localCalendarRowView.setListener(this.listener);
      if (j < i)
      {
        localCalendarRowView.setVisibility(0);
        List localList = (List)paramList.get(j);
        int k = 0;
        if (k >= localList.size())
          continue;
        MonthCellDescriptor localMonthCellDescriptor = (MonthCellDescriptor)localList.get(k);
        CheckedTextView localCheckedTextView = (CheckedTextView)localCalendarRowView.getChildAt(k);
        localCheckedTextView.setEnabled(localMonthCellDescriptor.isCurrentMonth());
        localCheckedTextView.setText(Integer.toString(localMonthCellDescriptor.getValue()));
        boolean bool;
        if (!localMonthCellDescriptor.isToday())
        {
          bool = true;
          label172: localCheckedTextView.setChecked(bool);
          localCheckedTextView.setSelected(localMonthCellDescriptor.isSelected());
          if (!localMonthCellDescriptor.isSelectable())
            break label254;
          localCheckedTextView.setTextColor(getResources().getColorStateList(a.a.calendar_text_selector));
        }
        while (true)
        {
          if (localMonthCellDescriptor.isToday())
            localCheckedTextView.setTextColor(getResources().getColorStateList(a.a.calendar_text_selector));
          localCheckedTextView.setTag(localMonthCellDescriptor);
          k++;
          break;
          bool = false;
          break label172;
          label254: localCheckedTextView.setTextColor(getResources().getColor(a.a.calendar_text_unselectable));
        }
      }
      localCalendarRowView.setVisibility(8);
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - l);
    Logr.d("MonthView.init took %d ms", arrayOfObject);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.title = ((TextView)findViewById(a.c.title));
    this.grid = ((CalendarGridView)findViewById(a.c.calendar_grid));
  }

  public static abstract interface Listener
  {
    public abstract void handleClick(MonthCellDescriptor paramMonthCellDescriptor);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.timessquare.MonthView
 * JD-Core Version:    0.6.0
 */