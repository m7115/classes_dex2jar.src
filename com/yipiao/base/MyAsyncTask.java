package com.yipiao.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.text.Html;
import cn.suanya.common.a.d;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.net.LogInfo;
import com.yipiao.YipiaoApplication;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.MyProgressDialog;
import com.yipiao.view.MyToast;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class MyAsyncTask<Params, Result> extends AsyncTask<Params, Integer, Object>
{
  private YipiaoApplication app;
  protected Context context;
  private boolean hasCancel = false;
  private ProgressDialog mProgressDialog;
  boolean showProgress;

  public MyAsyncTask(Context paramContext, String paramString, CharSequence paramCharSequence, boolean paramBoolean)
  {
    this.context = paramContext;
    this.showProgress = true;
    this.app = YipiaoApplication.getApp();
    this.hasCancel = false;
    1 local1 = new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramDialogInterface)
      {
        MyAsyncTask.access$002(MyAsyncTask.this, true);
      }
    };
    if (paramBoolean)
    {
      this.mProgressDialog = MyProgressDialog.show(getTopActivity(paramContext), paramString, paramCharSequence, true, paramBoolean, local1);
      this.app.mProgressDialog = this.mProgressDialog;
    }
  }

  public MyAsyncTask(Context paramContext, boolean paramBoolean)
  {
    this(paramContext, "", "请稍候...", paramBoolean);
  }

  public MyAsyncTask(BaseActivity paramBaseActivity)
  {
    this(paramBaseActivity, true);
  }

  public MyAsyncTask(BaseActivity paramBaseActivity, String paramString, CharSequence paramCharSequence)
  {
    this(paramBaseActivity, paramString, paramCharSequence, true);
  }

  private void logToServer(LogInfo paramLogInfo)
  {
    YipiaoService.getInstance().asyncLog(paramLogInfo);
  }

  private void logToServer(Exception paramException)
  {
    if ((paramException instanceof m))
    {
      logToServer(new LogInfo("Exception", paramException.getMessage()));
      return;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramException.printStackTrace(new PrintStream(localByteArrayOutputStream));
    logToServer(new LogInfo("Exception", localByteArrayOutputStream.toString()));
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
      n.a(localException);
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
    this.app.successLevel = 1;
    if ((paramException instanceof m))
    {
      m localm = (m)paramException;
      if (d.d.a().equals(localm.a()))
        return;
    }
    String str = paramException.getMessage();
    if ((str == null) || (str.length() == 0))
      str = "出错了";
    if (str.length() > 100)
      str = str.substring(0, 100);
    MyToast.makeText(getTopActivity(this.context), Html.fromHtml(str), 1).show();
  }

  protected void onPostExecute(Object paramObject)
  {
    if (this.hasCancel)
      return;
    if (this.showProgress)
      dismissProgressDialog();
    if ((paramObject instanceof Exception))
    {
      Exception localException = (Exception)paramObject;
      logToServer(localException);
      onException(localException);
      return;
    }
    myPostExecute(paramObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.MyAsyncTask
 * JD-Core Version:    0.6.0
 */