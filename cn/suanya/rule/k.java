package cn.suanya.rule;

import cn.suanya.rule.b.c;
import cn.suanya.rule.bean.SyContext;
import java.util.Iterator;
import java.util.List;

public class k extends a
{
  public String c;
  public a d;
  public a e;

  public k(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("condition");
    this.d = new a(this.a);
    this.e = new a(this.a);
    a locala1 = this.d;
    Iterator localIterator = this.p.iterator();
    a locala2 = locala1;
    while (localIterator.hasNext())
    {
      c localc = (c)localIterator.next();
      if ((localc instanceof f))
        locala2 = this.e;
      locala2.a(localc);
    }
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    if (((Boolean)this.b.a(this.c, paramSyContext)).booleanValue())
    {
      this.d.a(paramSyContext);
      return;
    }
    this.e.a(paramSyContext);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.k
 * JD-Core Version:    0.6.0
 */