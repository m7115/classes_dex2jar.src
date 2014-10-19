package cn.suanya.common.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.widget.TextView;
import cn.suanya.a.c;
import cn.suanya.a.d;

public class BaseProgressDialog extends ProgressDialog
{
  private TextView textView;

  public BaseProgressDialog(Context paramContext)
  {
    super(paramContext);
    show();
    setContentView(a.d.view_progressdialog);
    this.textView = ((TextView)findViewById(a.c.textView1));
  }

  public BaseProgressDialog(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2, DialogInterface.OnCancelListener paramOnCancelListener)
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

  public static BaseProgressDialog show(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, boolean paramBoolean1, boolean paramBoolean2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return new BaseProgressDialog(paramContext, paramCharSequence1, paramCharSequence2, paramBoolean1, paramBoolean2, paramOnCancelListener);
  }

  public void setMessage(CharSequence paramCharSequence)
  {
    this.textView.setText(paramCharSequence);
    super.setMessage(paramCharSequence);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.ui.BaseProgressDialog
 * JD-Core Version:    0.6.0
 */