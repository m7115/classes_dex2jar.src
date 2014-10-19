package com.baidu.platform.comjni.base.userdatacollect;

import android.os.Bundle;

public class JNIUserdataCollect
{
  public native void AppendRecord(int paramInt, String paramString1, String paramString2);

  public native int Create();

  public native boolean CreateUDC(int paramInt, String paramString, Bundle paramBundle);

  public native int Release(int paramInt);

  public native void Save(int paramInt);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.base.userdatacollect.JNIUserdataCollect
 * JD-Core Version:    0.6.0
 */