package com.baidu.platform.comapi.basestruct;

import java.io.Serializable;

public class b
  implements Serializable
{
  public c a;
  public c b;

  public b()
  {
    if (this.a == null)
      this.a = new c();
    if (this.b == null)
      this.b = new c();
  }

  public void a(c paramc)
  {
    this.a = paramc;
  }

  public void b(c paramc)
  {
    this.b = paramc;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.basestruct.b
 * JD-Core Version:    0.6.0
 */