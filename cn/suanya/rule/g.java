package cn.suanya.rule;

import cn.suanya.rule.bean.SyContext;

public class g extends a
{
  public String c;
  public String d;

  public g(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("expression");
    this.d = this.m.a("src");
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    if ((this.d != null) && (this.d.trim().length() > 0));
    for (Object localObject = this.b.a(this.d, paramSyContext); ; localObject = paramSyContext)
    {
      this.b.a(this.c, localObject);
      super.b(paramSyContext);
      return;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.g
 * JD-Core Version:    0.6.0
 */