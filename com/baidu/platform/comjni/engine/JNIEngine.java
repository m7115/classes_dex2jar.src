package com.baidu.platform.comjni.engine;

import android.os.Bundle;

public class JNIEngine
{
  public static native boolean GetFlaxLength(Bundle paramBundle);

  public static native boolean InitEngine(Bundle paramBundle);

  public static native void SetProxyInfo(String paramString, int paramInt);

  public static native boolean StartSocketProc();

  public static native boolean UnInitEngine();

  public static native int initClass(Object paramObject, int paramInt);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.engine.JNIEngine
 * JD-Core Version:    0.6.0
 */