package com.yipiao.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAlertDialogItem extends LinearLayout
  implements Checkable
{
  private CheckedTextView ctv;
  private TextView title1;
  private TextView title2;

  public MyAlertDialogItem(Context paramContext)
  {
    this(paramContext, false);
  }

  public MyAlertDialogItem(Context paramContext, boolean paramBoolean)
  {
    super(paramContext);
    LayoutInflater.from(paramContext).inflate(2130903184, this, true);
    this.title1 = ((TextView)findViewById(2131297130));
    this.title2 = ((TextView)findViewById(2131296933));
    this.ctv = ((CheckedTextView)findViewById(2131297131));
    View localView = findViewById(2131297129);
    if (paramBoolean);
    try
    {
      localView.setBackgroundDrawable(paramContext.getResources().getDrawable(2130837820));
      this.ctv.setCheckMarkDrawable(paramContext.getResources().getDrawable(2130837634));
      return;
      localView.setBackgroundDrawable(null);
      this.ctv.setCheckMarkDrawable(paramContext.getResources().getDrawable(2130837633));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public boolean isChecked()
  {
    return this.ctv.isChecked();
  }

  public void setChecked(boolean paramBoolean)
  {
    this.ctv.setChecked(paramBoolean);
  }

  public void setTitle1(CharSequence paramCharSequence)
  {
    this.title1.setText(paramCharSequence);
  }

  public void setTitle1Visibility(int paramInt)
  {
    this.title1.setVisibility(paramInt);
  }

  public void setTitle2(CharSequence paramCharSequence)
  {
    this.title2.setText(paramCharSequence);
  }

  public void setTitle2Visibility(int paramInt)
  {
    this.title2.setVisibility(paramInt);
  }

  public void toggle()
  {
    this.ctv.toggle();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.MyAlertDialogItem
 * JD-Core Version:    0.6.0
 */