package com.baidu.vi;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class VMsg
{
  private static Handler a;
  private static HandlerThread b;

  private static native void OnSDKUserCommand(int paramInt1, int paramInt2, int paramInt3);

  public static void destroy()
  {
    b.quit();
    b = null;
    a.removeCallbacksAndMessages(null);
    a = null;
  }

  public static void init()
  {
    b = new HandlerThread("VIMsgThread");
    b.start();
    a = new a(b.getLooper());
  }

  private static void postMessage(int paramInt1, int paramInt2, int paramInt3)
  {
    if (a == null)
      return;
    a.obtainMessage(paramInt1, paramInt2, paramInt3).sendToTarget();
  }

  static class a extends Handler
  {
    public a(Looper paramLooper)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      VMsg.a(paramMessage.what, paramMessage.arg1, paramMessage.arg2);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.vi.VMsg
 * JD-Core Version:    0.6.0
 */