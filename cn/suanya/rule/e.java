package cn.suanya.rule;

import cn.suanya.rule.bean.Each;
import cn.suanya.rule.bean.SyContext;

public class e extends a
{
  public String c;

  public e(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("src");
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    Object localObject = this.b.a(this.c, paramSyContext);
    if (localObject == null)
      return;
    Each localEach = paramSyContext.startEach(localObject);
    while (localEach.next() != null)
      super.b(paramSyContext);
    paramSyContext.endEach();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.e
 * JD-Core Version:    0.6.0
 */