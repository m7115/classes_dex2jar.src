package com.baidu.platform.comapi.b;

import android.os.Handler;
import android.os.Message;

class f extends Handler
{
  f(e parame)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if ((paramMessage.arg1 != 50) && (paramMessage.arg1 != 51) && (e.a(this.a) != null))
    {
      e.a(this.a).a(paramMessage);
      super.handleMessage(paramMessage);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.b.f
 * JD-Core Version:    0.6.0
 */