package com.squareup.timessquare;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.suanya.a.a;
import cn.suanya.a.e;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressLint({"SimpleDateFormat"})
public class CalendarPickerView extends ListView
{
  private final MonthAdapter adapter = new MonthAdapter(null);
  final List<List<List<MonthCellDescriptor>>> cells = new ArrayList();
  private OnDateSelectedListener dateListener;
  private final DateFormat fullDateFormat;
  private final MonthView.Listener listener = new CellClickedListener(null);
  private final Calendar maxCal = Calendar.getInstance();
  private final Calendar minCal = Calendar.getInstance();
  private final Calendar monthCounter = Calendar.getInstance();
  private final DateFormat monthNameFormat;
  final List<MonthDescriptor> months = new ArrayList();
  private final Calendar selectedCal = Calendar.getInstance();
  private MonthCellDescriptor selectedCell;
  final Calendar today = Calendar.getInstance();
  private final DateFormat weekdayNameFormat;

  public CalendarPickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setDivider(null);
    setDividerHeight(0);
    setAdapter(this.adapter);
    int i = getResources().getColor(a.a.calendar_bg);
    setBackgroundColor(i);
    setCacheColorHint(i);
    this.monthNameFormat = new SimpleDateFormat(paramContext.getString(a.e.month_name_format));
    this.weekdayNameFormat = new SimpleDateFormat(paramContext.getString(a.e.day_name_format));
    this.fullDateFormat = DateFormat.getDateInstance(2);
  }

  private static boolean betweenDates(Calendar paramCalendar1, Calendar paramCalendar2, Calendar paramCalendar3)
  {
    return betweenDates(paramCalendar1.getTime(), paramCalendar2, paramCalendar3);
  }

  static boolean betweenDates(Date paramDate, Calendar paramCalendar1, Calendar paramCalendar2)
  {
    Date localDate = paramCalendar1.getTime();
    return ((paramDate.equals(localDate)) || (paramDate.after(localDate))) && (paramDate.before(paramCalendar2.getTime()));
  }

  private static String dbg(Date paramDate1, Date paramDate2, Date paramDate3)
  {
    return "startDate: " + paramDate1 + "\nminDate: " + paramDate2 + "\nmaxDate: " + paramDate3;
  }

  private static boolean sameDate(Calendar paramCalendar1, Calendar paramCalendar2)
  {
    return (paramCalendar1.get(2) == paramCalendar2.get(2)) && (paramCalendar1.get(1) == paramCalendar2.get(1)) && (paramCalendar1.get(5) == paramCalendar2.get(5));
  }

  private void scrollToSelectedMonth(int paramInt)
  {
    post(new Runnable(paramInt)
    {
      @TargetApi(8)
      public void run()
      {
        try
        {
          CalendarPickerView.this.smoothScrollToPosition(this.val$selectedIndex);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    });
  }

  private static void setMidnight(Calendar paramCalendar)
  {
    paramCalendar.set(11, 0);
    paramCalendar.set(12, 0);
    paramCalendar.set(13, 0);
    paramCalendar.set(14, 0);
  }

  List<List<MonthCellDescriptor>> getMonthCells(MonthDescriptor paramMonthDescriptor, Calendar paramCalendar1, Calendar paramCalendar2)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramCalendar1.getTime());
    ArrayList localArrayList1 = new ArrayList();
    localCalendar.set(5, 1);
    localCalendar.add(5, 1 - localCalendar.get(7));
    if (((localCalendar.get(2) < 1 + paramMonthDescriptor.getMonth()) || (localCalendar.get(1) < paramMonthDescriptor.getYear())) && (localCalendar.get(1) <= paramMonthDescriptor.getYear()))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localCalendar.getTime();
      Logr.d("Building week row starting at %s", arrayOfObject);
      ArrayList localArrayList2 = new ArrayList();
      localArrayList1.add(localArrayList2);
      int i = 0;
      label130: Date localDate;
      boolean bool1;
      label160: boolean bool2;
      if (i < 7)
      {
        localDate = localCalendar.getTime();
        if (localCalendar.get(2) != paramMonthDescriptor.getMonth())
          break label267;
        bool1 = true;
        if ((!bool1) || (!sameDate(localCalendar, paramCalendar2)))
          break label273;
        bool2 = true;
        label177: if ((!bool1) || (!betweenDates(localCalendar, this.minCal, this.maxCal)))
          break label279;
      }
      label267: label273: label279: for (boolean bool3 = true; ; bool3 = false)
      {
        MonthCellDescriptor localMonthCellDescriptor = new MonthCellDescriptor(localDate, bool1, bool3, bool2, sameDate(localCalendar, this.today), localCalendar.get(5));
        if (bool2)
          this.selectedCell = localMonthCellDescriptor;
        localArrayList2.add(localMonthCellDescriptor);
        localCalendar.add(5, 1);
        i++;
        break label130;
        break;
        bool1 = false;
        break label160;
        bool2 = false;
        break label177;
      }
    }
    return localArrayList1;
  }

  public Date getSelectedDate()
  {
    return this.selectedCal.getTime();
  }

  public void init(Date paramDate1, Date paramDate2, Date paramDate3)
  {
    if ((paramDate1 == null) || (paramDate2 == null) || (paramDate3 == null))
      throw new IllegalArgumentException("All dates must be non-null.  " + dbg(paramDate1, paramDate2, paramDate3));
    if ((paramDate1.getTime() == 0L) || (paramDate2.getTime() == 0L) || (paramDate3.getTime() == 0L))
      throw new IllegalArgumentException("All dates must be non-zero.  " + dbg(paramDate1, paramDate2, paramDate3));
    if (paramDate2.after(paramDate3))
      throw new IllegalArgumentException("Min date must be before max date.  " + dbg(paramDate1, paramDate2, paramDate3));
    if ((paramDate1.before(paramDate2)) || (paramDate1.after(paramDate3)))
      paramDate1 = paramDate2;
    this.cells.clear();
    this.months.clear();
    this.selectedCal.setTime(paramDate1);
    this.minCal.setTime(paramDate2);
    this.maxCal.setTime(paramDate3);
    setMidnight(this.selectedCal);
    setMidnight(this.minCal);
    setMidnight(this.maxCal);
    this.maxCal.add(12, -1);
    this.monthCounter.setTime(this.minCal.getTime());
    int i = this.maxCal.get(2);
    int j = this.maxCal.get(1);
    int k = this.selectedCal.get(1);
    int m = this.selectedCal.get(2);
    int n = 0;
    while (((this.monthCounter.get(2) <= i) || (this.monthCounter.get(1) < j)) && (this.monthCounter.get(1) < j + 1))
    {
      MonthDescriptor localMonthDescriptor = new MonthDescriptor(this.monthCounter.get(2), this.monthCounter.get(1), this.monthNameFormat.format(this.monthCounter.getTime()));
      this.cells.add(getMonthCells(localMonthDescriptor, this.monthCounter, this.selectedCal));
      Logr.d("Adding month %s", new Object[] { localMonthDescriptor });
      if ((m == localMonthDescriptor.getMonth()) && (k == localMonthDescriptor.getYear()))
        n = this.months.size();
      this.months.add(localMonthDescriptor);
      this.monthCounter.add(2, 1);
    }
    this.adapter.notifyDataSetChanged();
    if (n != 0)
      scrollToSelectedMonth(n);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.months.isEmpty())
      throw new IllegalStateException("Must have at least one month to display.  Did you forget to call init()?");
    super.onMeasure(paramInt1, paramInt2);
  }

  public void setOnDateSelectedListener(OnDateSelectedListener paramOnDateSelectedListener)
  {
    this.dateListener = paramOnDateSelectedListener;
  }

  private class CellClickedListener
    implements MonthView.Listener
  {
    private CellClickedListener()
    {
    }

    public void handleClick(MonthCellDescriptor paramMonthCellDescriptor)
    {
      Date localDate = paramMonthCellDescriptor.getDate();
      if (!CalendarPickerView.betweenDates(paramMonthCellDescriptor.getDate(), CalendarPickerView.this.minCal, CalendarPickerView.this.maxCal))
      {
        Resources localResources = CalendarPickerView.this.getResources();
        int i = a.e.invalid_date;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = CalendarPickerView.access$400(CalendarPickerView.this).format(CalendarPickerView.access$200(CalendarPickerView.this).getTime());
        arrayOfObject[1] = CalendarPickerView.access$400(CalendarPickerView.this).format(CalendarPickerView.access$300(CalendarPickerView.this).getTime());
        String str = localResources.getString(i, arrayOfObject);
        Toast.makeText(CalendarPickerView.this.getContext(), str, 0).show();
      }
      do
      {
        return;
        CalendarPickerView.this.selectedCell.setSelected(false);
        CalendarPickerView.access$502(CalendarPickerView.this, paramMonthCellDescriptor);
        CalendarPickerView.this.selectedCell.setSelected(true);
        CalendarPickerView.this.selectedCal.setTime(paramMonthCellDescriptor.getDate());
        CalendarPickerView.this.adapter.notifyDataSetChanged();
      }
      while (CalendarPickerView.this.dateListener == null);
      CalendarPickerView.this.dateListener.onDateSelected(localDate);
    }
  }

  private class MonthAdapter extends BaseAdapter
  {
    private final LayoutInflater inflater = LayoutInflater.from(CalendarPickerView.this.getContext());

    private MonthAdapter()
    {
    }

    public int getCount()
    {
      return CalendarPickerView.this.months.size();
    }

    public Object getItem(int paramInt)
    {
      return CalendarPickerView.this.months.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MonthView localMonthView = (MonthView)paramView;
      if (localMonthView == null)
        localMonthView = MonthView.create(paramViewGroup, this.inflater, CalendarPickerView.this.weekdayNameFormat, CalendarPickerView.this.listener, CalendarPickerView.this.today);
      localMonthView.init((MonthDescriptor)CalendarPickerView.this.months.get(paramInt), (List)CalendarPickerView.this.cells.get(paramInt));
      return localMonthView;
    }

    public boolean isEnabled(int paramInt)
    {
      return false;
    }
  }

  public static abstract interface OnDateSelectedListener
  {
    public abstract void onDateSelected(Date paramDate);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.timessquare.CalendarPickerView
 * JD-Core Version:    0.6.0
 */