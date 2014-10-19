package com.baidu.platform.comjni.map.commonmemcache;

import android.os.Bundle;

public class JNICommonMemCache
{
  public native int Create();

  public native void Init(int paramInt, Bundle paramBundle);

  public native int Release(int paramInt);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.commonmemcache.JNICommonMemCache
 * JD-Core Version:    0.6.0
 */