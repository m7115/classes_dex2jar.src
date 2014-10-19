package com.baidu.platform.comapi.map;

import android.os.Bundle;

public class A extends d
{
  static A f = null;
  String a = null;
  Bundle b = null;

  public A(int paramInt)
  {
    this.d = paramInt;
  }

  public void a(Bundle paramBundle)
  {
    this.b = paramBundle;
  }

  public void a(String paramString)
  {
    if (paramString != null)
      this.a = paramString;
  }

  public String b()
  {
    return this.a;
  }

  public Bundle c()
  {
    return this.b;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.A
 * JD-Core Version:    0.6.0
 */