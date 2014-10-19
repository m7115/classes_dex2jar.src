package com.tencent.mm.sdk.platformtools;

import android.os.Handler;
import android.os.Message;

public class c extends Handler
{
  private final int a;
  private final boolean b;
  private long c;
  private final a d;

  public void a()
  {
    removeMessages(this.a);
  }

  protected void finalize()
  {
    a();
    super.finalize();
  }

  public void handleMessage(Message paramMessage)
  {
    if ((paramMessage.what != this.a) || (this.d == null));
    do
      return;
    while ((!this.d.a()) || (!this.b));
    sendEmptyMessageDelayed(this.a, this.c);
  }

  public static abstract interface a
  {
    public abstract boolean a();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.c
 * JD-Core Version:    0.6.0
 */