package com.baidu.platform.comjni.engine;

import android.os.Bundle;

public class AppEngine
{
  public static boolean GetFlaxLength(Bundle paramBundle)
  {
    return JNIEngine.GetFlaxLength(paramBundle);
  }

  public static boolean InitEngine(Bundle paramBundle)
  {
    JNIEngine.initClass(new Bundle(), 0);
    return JNIEngine.InitEngine(paramBundle);
  }

  public static void SetProxyInfo(String paramString, int paramInt)
  {
    JNIEngine.SetProxyInfo(paramString, paramInt);
  }

  public static boolean StartSocketProc()
  {
    return JNIEngine.StartSocketProc();
  }

  public static boolean UnInitEngine()
  {
    return JNIEngine.UnInitEngine();
  }

  public static void despatchMessage(int paramInt1, int paramInt2, int paramInt3)
  {
    a.a(paramInt1, paramInt2, paramInt3);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.engine.AppEngine
 * JD-Core Version:    0.6.0
 */