package cn.suanya.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import java.util.Calendar;
import java.util.Date;

public class DatePickActivity extends HTActivity
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
    setContentView(R.layout.activity_calendar_picker);
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
    CalendarPickerView localCalendarPickerView = (CalendarPickerView)findViewById(R.id.calendar_view);
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

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getData();
    initView();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.DatePickActivity
 * JD-Core Version:    0.6.0
 */