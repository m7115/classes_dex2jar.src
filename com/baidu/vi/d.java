package com.baidu.vi;

import android.net.NetworkInfo;

public class d
{
  public String a;
  public int b;
  public int c;

  public d(NetworkInfo paramNetworkInfo)
  {
    this.a = paramNetworkInfo.getTypeName();
    this.b = paramNetworkInfo.getType();
    switch (1.a[paramNetworkInfo.getState().ordinal()])
    {
    default:
      this.c = 0;
      return;
    case 1:
      this.c = 2;
      return;
    case 2:
    }
    this.c = 1;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.vi.d
 * JD-Core Version:    0.6.0
 */