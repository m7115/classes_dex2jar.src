package cn.suanya.rule;

import cn.suanya.common.a.j;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.rule.bean.SyContext;

public class h extends a
{
  public String c;
  public String d;
  public String e;
  public String f;

  public h(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("condition");
    this.d = this.m.a("message");
    this.e = this.m.a("code", "0");
    this.f = this.m.a("title", "");
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    Boolean localBoolean = Boolean.valueOf(true);
    if (this.c != null)
      localBoolean = (Boolean)this.b.a(this.c, paramSyContext);
    if (localBoolean.booleanValue())
    {
      Object localObject1 = "";
      if (this.d != null);
      try
      {
        Object localObject2 = this.b.a(this.d, paramSyContext);
        if (localObject2 == null);
        String str;
        for (localObject1 = ""; "-10".equals(this.e); localObject1 = str)
        {
          throw new j((String)localObject1);
          str = localObject2.toString();
        }
      }
      catch (Exception localException)
      {
        n.b(this.d);
        throw localException;
      }
      m localm = new m(this.e, (String)localObject1);
      localm.b(this.f);
      throw localm;
    }
    super.b(paramSyContext);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.h
 * JD-Core Version:    0.6.0
 */