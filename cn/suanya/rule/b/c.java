package cn.suanya.rule.b;

import cn.suanya.rule.r;
import cn.suanya.rule.s;
import java.util.ArrayList;
import java.util.List;

public abstract class c
  implements r
{
  protected a m;
  protected String n = "";
  protected c o;
  protected List<c> p;

  public c a(c paramc, a parama)
  {
    this.m = parama;
    if (paramc != null)
      paramc.a(this);
    return this;
  }

  public c a(s params)
  {
    a();
    return this.o;
  }

  public c a(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    String[] arrayOfString = new String(paramArrayOfChar).substring(paramInt1, paramInt1 + paramInt2).split("\n");
    for (int i = 0; i < arrayOfString.length; i++)
    {
      String str = arrayOfString[i];
      this.n += str.trim();
    }
    return this;
  }

  protected void a()
  {
  }

  public void a(c paramc)
  {
    if (this.p == null)
      this.p = new ArrayList();
    this.p.add(paramc);
    paramc.o = this;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.b.c
 * JD-Core Version:    0.6.0
 */