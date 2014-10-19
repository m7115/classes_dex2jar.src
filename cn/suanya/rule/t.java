package cn.suanya.rule;

import cn.suanya.common.a.m;
import cn.suanya.rule.b.c;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.SyContext;
import java.util.Iterator;
import java.util.List;

public class t extends a
{
  public String c;
  public a d;
  public a e;

  public t(s params)
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
      if ((localc instanceof d))
        locala2 = this.e;
      locala2.a(localc);
    }
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    try
    {
      this.d.a(paramSyContext);
      return;
    }
    catch (m localm)
    {
      paramSyContext.getL().put("error_msg", localm.getMessage());
      paramSyContext.getL().put("error_code", localm.a());
      this.e.a(paramSyContext);
      return;
    }
    catch (Exception localException)
    {
      paramSyContext.getL().put("error_msg", localException.getMessage());
      paramSyContext.getL().put("error_code", "-1");
      this.e.a(paramSyContext);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.t
 * JD-Core Version:    0.6.0
 */