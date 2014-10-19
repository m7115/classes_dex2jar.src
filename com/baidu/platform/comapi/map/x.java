package com.baidu.platform.comapi.map;

import android.os.Bundle;

public class x extends d
{
  static x b = null;
  String a = null;
  int f = 0;
  int g = 0;
  int h = 0;
  int i = 0;

  public x(int paramInt)
  {
    this.d = paramInt;
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
    Bundle localBundle = new Bundle();
    localBundle.putInt("accFlag", this.f);
    localBundle.putInt("centerFlag", this.g);
    if (this.g == 1)
    {
      localBundle.putInt("centerX", this.h);
      localBundle.putInt("centerY", this.i);
    }
    return localBundle;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.x
 * JD-Core Version:    0.6.0
 */