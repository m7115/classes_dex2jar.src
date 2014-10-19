package com.yipiao.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.widget.TextView;

public class MyProgressDialog extends ProgressDialog
{
  private TextView textView;

  public MyProgressDialog(Context paramContext)
  {
    super(paramContext);
    show();
    setContentView(2130903185);
    this.textView = ((TextView)findViewById(2131296689));
  }

  public MyProgressDialog(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    this(paramContext);
    setIndeterminate(paramBoolean1);
    setCancelable(paramBoolean2);
    setOnCancelListener(paramOnCancelListener);
    if (!paramCharSequence2.equals(""))
    {
      this.textView.setText(paramCharSequence2);
      return;
    }
    this.textView.setText("请稍候…");
  }

  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return show(paramContext, paramCharSequence1, paramCharSequence2, false);
  }

  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean)
  {
    return show(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean, false, null);
  }

  public static ProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2)
  {
    return show(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean1, paramBoolean2, null);
  }

  public static MyProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return new MyProgressDialog(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean1, paramBoolean2, paramOnCancelListener);
  }

  public void setMessage(CharSequence paramCharSequence)
  {
    this.textView.setText(paramCharSequence);
    super.setMessage(paramCharSequence);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.MyProgressDialog
 * JD-Core Version:    0.6.0
 */