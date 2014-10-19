package cn.suanya.rule;

import cn.suanya.common.a.n;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.SyContext;
import java.util.ArrayList;
import java.util.Map;

public class c extends a
{
  public String c;
  public boolean d;
  public String[] e;

  public c(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("name");
    this.d = this.m.a("thread", false);
    String str = this.m.a("parms", null);
    if ((str != null) && (str.length() > 0))
    {
      this.e = str.split(",");
      return;
    }
    this.e = null;
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    if ((this.e != null) && (this.e.length > 0))
    {
      ArrayList localArrayList = new ArrayList(this.e.length);
      paramSyContext.getL().put("parms", localArrayList);
      for (int i = 0; i < this.e.length; i++)
        localArrayList.add(this.b.a(this.e[i], paramSyContext));
    }
    a locala = (a)this.a.c.get(this.c);
    if (locala != null)
    {
      if (!this.d)
        break label165;
      new Thread(new a(new SyContext(paramSyContext.getG(), paramSyContext.getL(), paramSyContext.getF(), paramSyContext.engine, paramSyContext.httpClient), locala), "ruleCall").start();
    }
    while (true)
    {
      super.b(paramSyContext);
      return;
      label165: locala.a(paramSyContext);
    }
  }

  class a
    implements Runnable
  {
    SyContext a;
    a b;

    public a(SyContext parama, a arg3)
    {
      this.a = parama;
      Object localObject;
      this.b = localObject;
    }

    public void run()
    {
      try
      {
        this.b.a(this.a);
        return;
      }
      catch (Exception localException)
      {
        n.a(localException);
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.c
 * JD-Core Version:    0.6.0
 */