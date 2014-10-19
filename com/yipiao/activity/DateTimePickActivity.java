package com.yipiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import cn.suanya.common.ui.SYActivity;
import com.efor18.rangeseekbar.RangeSeekBar;
import com.efor18.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.example.pathview.util.TimeUtil;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.yipiao.YipiaoApplication;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimePickActivity extends SYActivity
{
  private YipiaoApplication app;
  private String begin;
  private Date currentDate;
  private String end;
  private Date maxDate;
  private Date minDate;
  private Button rightBt;
  private Date selectedDate;

  private void getData()
  {
    Intent localIntent = getIntent();
    this.minDate = ((Date)localIntent.getSerializableExtra("minDate"));
    this.maxDate = ((Date)localIntent.getSerializableExtra("maxDate"));
    this.selectedDate = ((Date)localIntent.getSerializableExtra("selectedDate"));
    this.begin = ((String)localIntent.getSerializableExtra("begin"));
    this.end = ((String)localIntent.getSerializableExtra("end"));
    this.currentDate = this.selectedDate;
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
        {
          DateTimePickActivity.access$302(DateTimePickActivity.this, paramDate);
          DateTimePickActivity.this.setDateResult(paramDate);
        }
      }
    });
  }

  private Date roundDate(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.set(11, 0);
    localCalendar.set(12, paramInt);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    return localCalendar.getTime();
  }

  private void setDateResult(Date paramDate)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("currentDate", this.currentDate);
    localIntent.putExtra("begin", this.begin);
    localIntent.putExtra("end", this.end);
    setResult(-1, localIntent);
    finish();
  }

  protected int getMainLayout()
  {
    return 2130903048;
  }

  protected void initLeftBtn()
  {
    View localView = findViewById(2131296261);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          DateTimePickActivity.this.finish();
        }
      });
  }

  protected void initRightBtn()
  {
    this.rightBt = ((Button)findViewById(2131296259));
    this.rightBt.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        DateTimePickActivity.this.setDateResult(DateTimePickActivity.this.currentDate);
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
    initRightBtn();
    TextView localTextView = (TextView)findViewById(2131296670);
    RangeSeekBar localRangeSeekBar = new RangeSeekBar(Integer.valueOf(0), Integer.valueOf(48), this);
    double d1 = TimeUtil.getMinsByStr(this.begin) / 1440.0D;
    double d2 = TimeUtil.getMinsByStr(this.end) / 1440.0D;
    localRangeSeekBar.setNormalizedMinValue(d1);
    localRangeSeekBar.setNormalizedMaxValue(d2);
    localTextView.setText(this.begin + "--" + this.end);
    localRangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener(localTextView)
    {
      public void onRangeSeekBarValuesChanged(RangeSeekBar<?> paramRangeSeekBar, Integer paramInteger1, Integer paramInteger2)
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
        DateTimePickActivity.access$002(DateTimePickActivity.this, localSimpleDateFormat.format(DateTimePickActivity.this.roundDate(new Date(), 30 * paramInteger1.intValue())));
        DateTimePickActivity localDateTimePickActivity = DateTimePickActivity.this;
        if (paramInteger2.intValue() == 48);
        for (String str = "24:00"; ; str = localSimpleDateFormat.format(DateTimePickActivity.this.roundDate(new Date(), 30 * paramInteger2.intValue())))
        {
          DateTimePickActivity.access$202(localDateTimePickActivity, str);
          this.val$tv.setText(DateTimePickActivity.this.begin + "--" + DateTimePickActivity.this.end);
          return;
        }
      }
    });
    ((ViewGroup)findViewById(2131296667)).addView(localRangeSeekBar);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.DateTimePickActivity
 * JD-Core Version:    0.6.0
 */