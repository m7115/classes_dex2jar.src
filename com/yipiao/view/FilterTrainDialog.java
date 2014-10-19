package com.yipiao.view;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.efor18.rangeseekbar.RangeSeekBar;
import com.efor18.rangeseekbar.RangeSeekBar.OnRangeSeekBarChangeListener;
import com.example.pathview.util.TimeUtil;
import com.yipiao.Config;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.NoteList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class FilterTrainDialog
  implements View.OnClickListener
{
  private String begin;
  private int compartorIndex;
  private ChainQuery cq;
  private String end;
  private FilterDilogListener mClickListener;
  private Context mContext;
  private Dialog mDialog;
  private boolean[] mulTrainType;
  private View view;

  public FilterTrainDialog(Context paramContext, int paramInt, ChainQuery paramChainQuery, FilterDilogListener paramFilterDilogListener)
  {
    this.mDialog = new Dialog(paramContext, 2131427458);
    this.mContext = paramContext;
    this.mClickListener = paramFilterDilogListener;
    if (paramInt > 3)
      paramInt = 0;
    this.compartorIndex = paramInt;
    this.cq = paramChainQuery;
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

  private void setButtonBg(int paramInt, boolean paramBoolean)
  {
    ImageButton localImageButton;
    switch (paramInt)
    {
    default:
      return;
    case 0:
      localImageButton = (ImageButton)this.view.findViewById(2131297091);
    case 1:
    case 2:
    }
    while (paramBoolean)
    {
      localImageButton.setImageDrawable(this.mContext.getResources().getDrawable(2130837610));
      return;
      localImageButton = (ImageButton)this.view.findViewById(2131297093);
      continue;
      localImageButton = (ImageButton)this.view.findViewById(2131297095);
    }
    localImageButton.setImageDrawable(this.mContext.getResources().getDrawable(2130837611));
  }

  private void setMultiChoiceItem(ChainQuery paramChainQuery)
  {
    this.mulTrainType = Config.getInstance().trainTypeSimples.getSelectArray(paramChainQuery.getTrainTypes());
    for (int i = 0; i < this.mulTrainType.length; i++)
      setButtonBg(i, this.mulTrainType[i]);
    RelativeLayout localRelativeLayout1 = (RelativeLayout)this.view.findViewById(2131297090);
    RelativeLayout localRelativeLayout2 = (RelativeLayout)this.view.findViewById(2131297092);
    RelativeLayout localRelativeLayout3 = (RelativeLayout)this.view.findViewById(2131297094);
    localRelativeLayout1.setOnClickListener(this);
    localRelativeLayout2.setOnClickListener(this);
    localRelativeLayout3.setOnClickListener(this);
  }

  private void setPositiveButton()
  {
    ((Button)this.view.findViewById(2131297097)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        NoteList localNoteList = Config.getInstance().trainTypeSimples.getSelectNote(FilterTrainDialog.this.mulTrainType);
        FilterTrainDialog.this.cq.setTrainTypes(localNoteList);
        FilterTrainDialog.this.cq.setTimeRangBegin(FilterTrainDialog.this.begin);
        FilterTrainDialog.this.cq.setTimeRangEnd(FilterTrainDialog.this.end);
        YipiaoApplication.getApp().sp.edit().putInt("compartorIndex", FilterTrainDialog.this.compartorIndex).commit();
        FilterTrainDialog.this.mClickListener.submitFilter(FilterTrainDialog.this.compartorIndex, FilterTrainDialog.this.cq);
        FilterTrainDialog.this.mDialog.dismiss();
      }
    });
  }

  private void setRangeseekBar(ChainQuery paramChainQuery)
  {
    TextView localTextView = (TextView)this.view.findViewById(2131297096);
    this.begin = paramChainQuery.getTimeRangBegin();
    this.end = paramChainQuery.getTimeRangEnd();
    localTextView.setText(this.begin + "--" + this.end);
    ((LinearLayout)this.view.findViewById(2131296670).getParent()).setVisibility(8);
    RangeSeekBar localRangeSeekBar = new RangeSeekBar(Integer.valueOf(0), Integer.valueOf(48), this.mContext);
    double d1 = TimeUtil.getMinsByStr(this.begin) / 1440.0D;
    double d2 = TimeUtil.getMinsByStr(this.end) / 1440.0D;
    localRangeSeekBar.setNormalizedMinValue(d1);
    localRangeSeekBar.setNormalizedMaxValue(d2);
    localRangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener(localTextView)
    {
      public void onRangeSeekBarValuesChanged(RangeSeekBar<?> paramRangeSeekBar, Integer paramInteger1, Integer paramInteger2)
      {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
        FilterTrainDialog.access$202(FilterTrainDialog.this, localSimpleDateFormat.format(FilterTrainDialog.this.roundDate(new Date(), 30 * paramInteger1.intValue())));
        FilterTrainDialog localFilterTrainDialog = FilterTrainDialog.this;
        if (paramInteger2.intValue() == 48);
        for (String str = "24:00"; ; str = localSimpleDateFormat.format(FilterTrainDialog.this.roundDate(new Date(), 30 * paramInteger2.intValue())))
        {
          FilterTrainDialog.access$302(localFilterTrainDialog, str);
          this.val$tv.setText(FilterTrainDialog.this.begin + "--" + FilterTrainDialog.this.end);
          return;
        }
      }
    });
    ViewGroup localViewGroup = (ViewGroup)this.view.findViewById(2131296667);
    ((LinearLayout)localViewGroup.getParent()).setBackgroundColor(this.mContext.getResources().getColor(2131165192));
    localViewGroup.addView(localRangeSeekBar);
  }

  private void setSingleChoiceItems(int paramInt)
  {
    RadioButton localRadioButton;
    switch (paramInt)
    {
    default:
      localRadioButton = (RadioButton)this.view.findViewById(2131297086);
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      localRadioButton.setChecked(true);
      ((RadioGroup)this.view.findViewById(2131297085)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
      {
        public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
        {
          switch (paramInt)
          {
          default:
            FilterTrainDialog.access$402(FilterTrainDialog.this, 0);
            return;
          case 2131297086:
            FilterTrainDialog.access$402(FilterTrainDialog.this, 0);
            return;
          case 2131297087:
            FilterTrainDialog.access$402(FilterTrainDialog.this, 1);
            return;
          case 2131297088:
            FilterTrainDialog.access$402(FilterTrainDialog.this, 2);
            return;
          case 2131297089:
          }
          FilterTrainDialog.access$402(FilterTrainDialog.this, 3);
        }
      });
      return;
      localRadioButton = (RadioButton)this.view.findViewById(2131297086);
      continue;
      localRadioButton = (RadioButton)this.view.findViewById(2131297087);
      continue;
      localRadioButton = (RadioButton)this.view.findViewById(2131297088);
      continue;
      localRadioButton = (RadioButton)this.view.findViewById(2131297089);
    }
  }

  public Dialog create()
  {
    this.view = ((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(2130903163, null);
    this.view.setMinimumWidth(100000);
    this.mDialog.setContentView(this.view, new ViewGroup.LayoutParams(-2, -2));
    this.mDialog.setCanceledOnTouchOutside(true);
    setSingleChoiceItems(this.compartorIndex);
    setMultiChoiceItem(this.cq);
    setRangeseekBar(this.cq);
    setPositiveButton();
    return this.mDialog;
  }

  public void onClick(View paramView)
  {
    int i = 1;
    switch (paramView.getId())
    {
    case 2131297091:
    case 2131297093:
    default:
      return;
    case 2131297090:
      boolean[] arrayOfBoolean3 = this.mulTrainType;
      if (this.mulTrainType[0] == 0);
      while (true)
      {
        arrayOfBoolean3[0] = i;
        setButtonBg(0, this.mulTrainType[0]);
        return;
        i = 0;
      }
    case 2131297092:
      boolean[] arrayOfBoolean2 = this.mulTrainType;
      int j = this.mulTrainType[i];
      int k = 0;
      if (j == 0)
        k = i;
      arrayOfBoolean2[i] = k;
      setButtonBg(i, this.mulTrainType[i]);
      return;
    case 2131297094:
    }
    boolean[] arrayOfBoolean1 = this.mulTrainType;
    if (this.mulTrainType[2] == 0);
    while (true)
    {
      arrayOfBoolean1[2] = i;
      setButtonBg(2, this.mulTrainType[2]);
      return;
      i = 0;
    }
  }

  public static abstract interface FilterDilogListener
  {
    public abstract void submitFilter(int paramInt, ChainQuery paramChainQuery);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.FilterTrainDialog
 * JD-Core Version:    0.6.0
 */