package com.baidu.platform.comapi.map;

import android.os.Handler;
import android.os.Message;

class p extends Handler
{
  p(o paramo)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    boolean bool;
    if ((paramMessage.what == 4000) && (o.a(this.a) != null) && (o.a(this.a).f != null))
    {
      a locala = o.a(this.a).f;
      if (paramMessage.arg2 == 1)
      {
        bool = true;
        locala.a(bool);
      }
    }
    else if ((paramMessage.what == 39) && (o.a(this.a) != null))
    {
      if (paramMessage.arg1 != 100)
        break label237;
      if (o.a(this.a).e != null)
      {
        o.a(this.a).e.b();
        if (o.b(this.a))
          o.c(this.a);
        if (o.d(this.a))
          o.e(this.a);
      }
    }
    while (true)
    {
      if (o.a(this.a).e != null)
        o.a(this.a).e.a();
      if (paramMessage.what == 512)
      {
        int i = paramMessage.arg1;
        if (o.a(this.a).e != null)
          o.a(this.a).e.a(i);
      }
      return;
      bool = false;
      break;
      label237: if (paramMessage.arg1 == 200)
      {
        if (o.a(this.a).e == null)
          continue;
        o.a(this.a).e.c();
        continue;
      }
      if (paramMessage.arg1 == 0)
      {
        o.a(this.a).setRenderMode(0);
        continue;
      }
      o.a(this.a).setRenderMode(1);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.p
 * JD-Core Version:    0.6.0
 */