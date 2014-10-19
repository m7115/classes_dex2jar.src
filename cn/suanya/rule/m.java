package cn.suanya.rule;

import cn.suanya.common.a.v;
import cn.suanya.common.bean.NameValue;
import cn.suanya.rule.bean.Http;
import cn.suanya.rule.bean.SyContext;
import java.util.Iterator;
import java.util.List;

public class m extends a
{
  public String c;
  public String d;
  public boolean e;

  public m(s params)
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
    String str1;
    if (this.e)
      str1 = this.c;
    while (true)
    {
      Object localObject1 = this.b.a(this.d, paramSyContext);
      if ((localObject1 instanceof List))
      {
        Iterator localIterator = ((List)localObject1).iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = localIterator.next();
          if (!(localObject2 instanceof NameValue))
            continue;
          paramSyContext.getR().addParm((NameValue)localObject2);
        }
        str1 = v.a(this.b.a(this.c, paramSyContext));
        continue;
      }
      String str2 = v.a(localObject1);
      paramSyContext.getR().addParm(new NameValue(str1, str2));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.m
 * JD-Core Version:    0.6.0
 */