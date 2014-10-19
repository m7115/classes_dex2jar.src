package cn.suanya.common.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class SYActivity extends Activity
  implements View.OnClickListener
{
  protected Dialog mProgressDialog;

  protected void dismissProgressDialog()
  {
    try
    {
      if ((this.mProgressDialog != null) && (this.mProgressDialog.isShowing()))
        this.mProgressDialog.dismiss();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public SYApplication getApp()
  {
    return (SYApplication)super.getApplication();
  }

  public Activity getTopActivity()
  {
    while (getParent() != null)
      this = getParent();
    return this;
  }

  public String getVString(int paramInt)
  {
    View localView = findViewById(paramInt);
    if ((localView instanceof TextView))
      return ((TextView)localView).getText().toString();
    return "";
  }

  protected void goWebActivity(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(this, WEBActivity.class);
    localIntent.putExtra("url", paramString1);
    if (paramString2 != null)
      localIntent.putExtra("cookies", paramString2);
    if (paramArrayOfByte != null)
      localIntent.putExtra("postPar", paramArrayOfByte);
    startActivity(localIntent);
  }

  public void onClick(View paramView)
  {
  }

  public View setClick(int paramInt, View.OnClickListener paramOnClickListener)
  {
    View localView = findViewById(paramInt);
    if (localView == null)
      return localView;
    localView.setOnClickListener(paramOnClickListener);
    return localView;
  }

  public View setClick(int paramInt, Class<?> paramClass)
  {
    return setClick(paramInt, new View.OnClickListener(paramClass)
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(SYActivity.this, this.val$clazz);
        SYActivity.this.startActivity(localIntent);
      }
    });
  }

  public EditText setEt(int paramInt, CharSequence paramCharSequence)
  {
    EditText localEditText = (EditText)findViewById(paramInt);
    localEditText.setText(paramCharSequence);
    return localEditText;
  }

  public TextView setTv(int paramInt, CharSequence paramCharSequence)
  {
    TextView localTextView = (TextView)findViewById(paramInt);
    localTextView.setText(paramCharSequence);
    return localTextView;
  }

  protected void showMessage(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    new AlertDialog.Builder(getTopActivity()).setTitle(paramCharSequence1).setMessage(paramCharSequence2).setNeutralButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
      }
    }).create().show();
  }

  protected void showProgressDialog(String paramString, CharSequence paramCharSequence, boolean paramBoolean)
  {
    this.mProgressDialog = ProgressDialog.show(getTopActivity(), paramString, paramCharSequence, true, paramBoolean);
  }

  protected void showProgressDialog(boolean paramBoolean)
  {
    this.mProgressDialog = ProgressDialog.show(getTopActivity(), "", "", true, paramBoolean);
  }

  protected void showToast(int paramInt)
  {
    showToast(paramInt, 0);
  }

  protected void showToast(int paramInt1, int paramInt2)
  {
    Toast.makeText(getTopActivity(), paramInt1, paramInt2).show();
  }

  public void showToast(CharSequence paramCharSequence)
  {
    showToast(paramCharSequence, 0);
  }

  protected void showToast(CharSequence paramCharSequence, int paramInt)
  {
    Toast.makeText(getTopActivity(), paramCharSequence, paramInt).show();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.ui.SYActivity
 * JD-Core Version:    0.6.0
 */