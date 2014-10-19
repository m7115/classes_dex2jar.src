package com.baidu.platform.comapi.map;

import android.os.Bundle;
import com.baidu.platform.comjni.map.basemap.a;

public abstract class d
{
  int c = 0;
  int d = 0;
  o e = null;

  public d()
  {
  }

  public d(int paramInt)
  {
    this.d = paramInt;
  }

  public void a(int paramInt, o paramo)
  {
    this.c = paramInt;
    this.e = paramo;
  }

  public abstract void a(String paramString);

  public void a(boolean paramBoolean)
  {
    if ((this.c == 0) || (this.e == null));
    a locala;
    do
    {
      return;
      locala = this.e.b();
    }
    while (locala == null);
    locala.a(this.c, paramBoolean);
  }

  public abstract String b();

  public abstract Bundle c();

  public void d()
  {
    if ((this.c == 0) || (this.e == null));
    a locala;
    do
    {
      return;
      locala = this.e.b();
    }
    while (locala == null);
    locala.a(this.c);
  }

  public int e()
  {
    return this.d;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.d
 * JD-Core Version:    0.6.0
 */