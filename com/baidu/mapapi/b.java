package com.baidu.mapapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.platform.comapi.d.a;
import com.baidu.platform.comapi.d.c;

public class b extends BroadcastReceiver
{
  public static final String a = b.class.getSimpleName();

  private void a(Context paramContext)
  {
    String str = a.c(paramContext);
    if (!c.e().equals(str))
      c.a(str);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a(paramContext);
    a.a(paramContext);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.b
 * JD-Core Version:    0.6.0
 */