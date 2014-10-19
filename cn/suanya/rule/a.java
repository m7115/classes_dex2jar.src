package cn.suanya.rule;

import cn.suanya.rule.b.c;
import cn.suanya.rule.bean.SyContext;
import java.util.Iterator;
import java.util.List;

public class a extends c
  implements r
{
  protected s a;
  protected cn.suanya.rule.a.a b;

  public a(s params)
  {
    this.a = params;
  }

  public void a(SyContext paramSyContext)
    throws Exception
  {
    this.b = paramSyContext.engine;
    b(paramSyContext);
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    if (this.p == null);
    while (true)
    {
      return;
      Iterator localIterator = this.p.iterator();
      while (localIterator.hasNext())
        ((r)localIterator.next()).a(paramSyContext);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.a
 * JD-Core Version:    0.6.0
 */