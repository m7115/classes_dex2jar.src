package com.yipiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.suanya.common.ui.SYActivity;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import java.util.Calendar;
import java.util.Date;

public class DatePickActivity extends SYActivity
{
  private Date maxDate;
  private Date minDate;
  private Date selectedDate;

  private void getData()
  {
    Intent localIntent = getIntent();
    this.minDate = ((Date)localIntent.getSerializableExtra("minDate"));
    this.maxDate = ((Date)localIntent.getSerializableExtra("maxDate"));
    this.selectedDate = ((Date)localIntent.getSerializableExtra("selectedDate"));
  }

  private void initView()
  {
    if (this.minDate == null)
      this.minDate = new Date();
    if (this.maxDate == null)
    {
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.add(2, 6);
      this.maxDate = localCalendar.getTime();
    }
    if (this.selectedDate == null)
      this.selectedDate = this.minDate;
    CalendarPickerView localCalendarPickerView = (CalendarPickerView)findViewById(2131296688);
    localCalendarPickerView.init(this.selectedDate, this.minDate, this.maxDate);
    localCalendarPickerView.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener()
    {
      public void onDateSelected(Date paramDate)
      {
        if (paramDate != null)
          DatePickActivity.this.setDateResult(paramDate);
      }
    });
  }

  private void setDateResult(Date paramDate)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("date", paramDate);
    setResult(-1, localIntent);
    finish();
  }

  protected int getMainLayout()
  {
    return 2130903043;
  }

  protected void initLeftBtn()
  {
    View localView = findViewById(2131296261);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          DatePickActivity.this.finish();
        }
      });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(getMainLayout());
    getData();
    initView();
    initLeftBtn();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.DatePickActivity
 * JD-Core Version:    0.6.0
 */