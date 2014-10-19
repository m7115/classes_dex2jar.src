package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.basestruct.c;

public class t
{
  public float a = -1.0F;
  public int b = -1;
  public int c = -1;
  public int d = -1;
  public int e = -1;
  public b f = new b();
  public a g = new a();
  public long h = 0L;
  public long i = 0L;
  public boolean j = false;

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    t localt;
    do
      while (true)
      {
        return true;
        if (paramObject == null)
          return false;
        localt = (t)paramObject;
        if (this.d != localt.d)
          return false;
        if (this.e != localt.e)
          return false;
        if (this.j != localt.j)
          return false;
        if (this.g == null)
        {
          if (localt.g != null)
            return false;
        }
        else if (!this.g.equals(localt.g))
          return false;
        if (Float.floatToIntBits(this.a) != Float.floatToIntBits(localt.a))
          return false;
        if (this.c != localt.c)
          return false;
        if (this.b != localt.b)
          return false;
        if (this.i != localt.i)
          return false;
        if (this.h != localt.h)
          return false;
        if (this.f != null)
          break;
        if (localt.f != null)
          return false;
      }
    while (this.f.equals(localt.f));
    return false;
  }

  public int hashCode()
  {
    int k = 31 * (31 * (31 + this.d) + this.e);
    int m;
    int i1;
    label45: int i2;
    int i3;
    if (this.j)
    {
      m = 1;
      int n = 31 * (m + k);
      if (this.g != null)
        break label106;
      i1 = 0;
      i2 = 31 * (31 * (31 * (31 * (i1 + n) + Float.floatToIntBits(this.a)) + this.c) + this.b);
      b localb = this.f;
      i3 = 0;
      if (localb != null)
        break label118;
    }
    while (true)
    {
      return i2 + i3;
      m = 0;
      break;
      label106: i1 = this.g.hashCode();
      break label45;
      label118: i3 = this.f.hashCode();
    }
  }

  public class a
  {
    public long a = 0L;
    public long b = 0L;
    public long c = 0L;
    public long d = 0L;
    public c e = new c(0, 0);
    public c f = new c(0, 0);
    public c g = new c(0, 0);
    public c h = new c(0, 0);

    public a()
    {
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject);
      a locala;
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (!(paramObject instanceof a))
          return false;
        locala = (a)paramObject;
        if (this.d != locala.d)
          return false;
        if (this.a != locala.a)
          return false;
        if (this.b != locala.b)
          return false;
      }
      while (this.c == locala.c);
      return false;
    }

    public int hashCode()
    {
      return 31 * (31 * (31 * (31 + (int)(this.d ^ this.d >>> 32)) + (int)(this.a ^ this.a >>> 32)) + (int)(this.b ^ this.b >>> 32)) + (int)(this.c ^ this.c >>> 32);
    }
  }

  public class b
  {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;

    public b()
    {
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject);
      b localb;
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (!(paramObject instanceof b))
          return false;
        localb = (b)paramObject;
        if (this.d != localb.d)
          return false;
        if (this.a != localb.a)
          return false;
        if (this.b != localb.b)
          return false;
      }
      while (this.c == localb.c);
      return false;
    }

    public int hashCode()
    {
      return 31 * (31 * (31 * (31 + this.d) + this.a) + this.b) + this.c;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.t
 * JD-Core Version:    0.6.0
 */