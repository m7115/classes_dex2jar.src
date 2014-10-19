package com.baidu.platform.comapi;

import android.content.Context;
import com.baidu.platform.comjni.engine.AppEngine;
import com.baidu.vi.VMsg;

public class a
{
  public static boolean a = false;

  public boolean a()
  {
    if (a)
      return true;
    a = true;
    return true;
  }

  public boolean a(Context paramContext)
  {
    a = false;
    com.baidu.platform.comapi.d.c.c(paramContext);
    com.baidu.platform.comapi.d.c.d(paramContext);
    com.baidu.vi.c.a(paramContext);
    VMsg.init();
    boolean bool = AppEngine.InitEngine(com.baidu.platform.comapi.d.c.c());
    com.baidu.platform.comapi.d.c.f();
    if (!bool)
      return false;
    if (!AppEngine.StartSocketProc())
    {
      AppEngine.UnInitEngine();
      return false;
    }
    return true;
  }

  public boolean b()
  {
    a = false;
    return true;
  }

  public void c()
  {
    if (a)
      b();
    a = false;
    VMsg.destroy();
    com.baidu.platform.comjni.engine.a.a();
    com.baidu.platform.comapi.d.c.b();
    AppEngine.UnInitEngine();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.a
 * JD-Core Version:    0.6.0
 */