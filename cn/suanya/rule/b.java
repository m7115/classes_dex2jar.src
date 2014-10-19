package cn.suanya.rule;

import cn.suanya.common.a.n;
import cn.suanya.rule.b.c;
import cn.suanya.rule.bean.SyContext;
import java.util.Map;

public class b extends a
{
  public b(s params)
  {
    super(params);
  }

  public c a(s params)
  {
    params.c.put(this.m.a("name"), this);
    return super.a(params);
  }

  protected void a()
  {
    super.a();
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    n.b("exec:" + this.m.a("name"));
    super.b(paramSyContext);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.b
 * JD-Core Version:    0.6.0
 */