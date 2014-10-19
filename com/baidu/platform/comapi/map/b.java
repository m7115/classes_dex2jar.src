package com.baidu.platform.comapi.map;

import android.os.Bundle;

public class b extends d
{
  static b b = null;
  String a = null;

  public b(int paramInt)
  {
    this.d = paramInt;
  }

  public static d a()
  {
    if (b == null)
      b = new b(20);
    return b;
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
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.b
 * JD-Core Version:    0.6.0
 */