package com.baidu.platform.comjni.base.userdatacollect;

import android.os.Bundle;

public class a
{
  private int a = 0;
  private JNIUserdataCollect b = null;

  public int a()
  {
    this.a = this.b.Create();
    return this.a;
  }

  public void a(String paramString1, String paramString2)
  {
    this.b.AppendRecord(this.a, paramString1, paramString2);
  }

  public boolean a(String paramString, Bundle paramBundle)
  {
    return this.b.CreateUDC(this.a, paramString, paramBundle);
  }

  public int b()
  {
    return this.b.Release(this.a);
  }

  public void c()
  {
    this.b.Save(this.a);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.base.userdatacollect.a
 * JD-Core Version:    0.6.0
 */