package cn.suanya.rule;

import cn.suanya.common.a.v;
import cn.suanya.common.bean.NameValue;
import cn.suanya.rule.bean.Http;
import cn.suanya.rule.bean.SyContext;

public class i extends a
{
  public String c;
  public String d;
  public boolean e;

  public i(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("name");
    this.d = this.m.a("value");
    this.e = this.m.a("staticName", true);
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    if (this.e);
    for (String str = this.c; ; str = v.a(this.b.a(this.c, paramSyContext)))
    {
      NameValue localNameValue = new NameValue(str, v.a(this.b.a(this.d, paramSyContext)));
      paramSyContext.getR().addHeader(localNameValue);
      return;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.i
 * JD-Core Version:    0.6.0
 */