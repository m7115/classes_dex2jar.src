package cn.suanya.common.a;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.widget.Toast;
import cn.suanya.common.ui.BaseProgressDialog;
import cn.suanya.common.ui.SYActivity;
import cn.suanya.domain.Response;

public abstract class k<Params, Result> extends AsyncTask<Params, Integer, Object>
{
  protected Context context;
  private boolean hasCancel = false;
  private ProgressDialog mProgressDialog;
  boolean showProgress;

  public k(Context paramContext, String paramString, CharSequence paramCharSequence, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.context = paramContext;
    this.showProgress = true;
    this.hasCancel = false;
    1 local1 = new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        k.access$002(k.this, true);
      }
    };
    if (paramBoolean1)
      this.mProgressDialog = BaseProgressDialog.show(getTopActivity(paramContext), paramString, paramCharSequence, true, paramBoolean1, local1);
  }

  public k(Context paramContext, boolean paramBoolean)
  {
    this(paramContext, "", "请求数据...", paramBoolean, true);
  }

  public k(SYActivity paramSYActivity)
  {
    this(paramSYActivity, true);
  }

  private void onErrorStatus(Response<Object> paramResponse)
  {
    Toast.makeText(getTopActivity(this.context), paramResponse.getMessage(), 1).show();
  }

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

  protected Object doInBackground(Params[] paramArrayOfParams)
  {
    try
    {
      Object localObject = myInBackground(paramArrayOfParams);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return localException;
  }

  public Context getTopActivity(Context paramContext)
  {
    if ((paramContext instanceof Activity))
    {
      paramContext = (Activity)paramContext;
      if (paramContext.getParent() != null)
        paramContext = paramContext.getParent();
    }
    return paramContext;
  }

  protected abstract Result myInBackground(Params[] paramArrayOfParams)
    throws Exception;

  protected abstract void myPostExecute(Result paramResult);

  protected void onException(Exception paramException)
  {
    n.b("SYAsyncTask", paramException);
    Toast.makeText(getTopActivity(this.context), paramException.getMessage(), 1).show();
  }

  protected void onPostExecute(Object paramObject)
  {
    if (this.hasCancel)
      return;
    if (this.showProgress)
      dismissProgressDialog();
    if ((paramObject instanceof Exception))
    {
      onException((Exception)paramObject);
      return;
    }
    if ((paramObject instanceof Response))
    {
      Response localResponse = (Response)paramObject;
      if (localResponse.getStatus() != 0)
      {
        onErrorStatus(localResponse);
        return;
      }
      myPostExecute(paramObject);
      return;
    }
    myPostExecute(paramObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.k
 * JD-Core Version:    0.6.0
 */