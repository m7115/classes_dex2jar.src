package com.yipiao.base;

import cn.suanya.common.net.LogInfo;
import com.yipiao.service.YipiaoService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DefaultExceptionHandler
  implements Thread.UncaughtExceptionHandler
{
  private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

  private void sendCrashReport(Throwable paramThrowable)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramThrowable.printStackTrace(new PrintStream(localByteArrayOutputStream));
    LogInfo localLogInfo = new LogInfo("crash", localByteArrayOutputStream.toString());
    YipiaoService.getInstance().log(localLogInfo);
  }

  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    sendCrashReport(paramThrowable);
    if (this.mDefaultHandler != null)
      this.mDefaultHandler.uncaughtException(paramThread, paramThrowable);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.DefaultExceptionHandler
 * JD-Core Version:    0.6.0
 */