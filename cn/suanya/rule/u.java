package cn.suanya.rule;

import cn.suanya.rule.bean.SyContext;

public class u extends a
{
  public String c;
  public b d;

  public u(s params)
  {
    super(params);
    this.d = new b(params);
  }

  public void a()
  {
    this.c = this.m.a("condition");
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    while (((Boolean)this.b.a(this.c, paramSyContext)).booleanValue())
      super.b(paramSyContext);
    super.b(paramSyContext);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.u
 * JD-Core Version:    0.6.0
 */