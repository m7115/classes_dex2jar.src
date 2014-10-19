package com.baidu.platform.comapi.basestruct;

import java.io.Serializable;

public class c
  implements Serializable
{
  public int a;
  public int b;

  public c()
  {
  }

  public c(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }

  public int a()
  {
    return this.a;
  }

  public void a(int paramInt)
  {
    this.a = paramInt;
  }

  public int b()
  {
    return this.b;
  }

  public void b(int paramInt)
  {
    this.b = paramInt;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    c localc;
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      localc = (c)paramObject;
      if (this.a != localc.a)
        return false;
    }
    while (this.b == localc.b);
    return false;
  }

  public int hashCode()
  {
    return 31 * (31 + this.a) + this.b;
  }

  public String toString()
  {
    return "Point [x=" + this.a + ", y=" + this.b + "]";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.basestruct.c
 * JD-Core Version:    0.6.0
 */