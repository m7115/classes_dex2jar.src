package cn.suanya.rule;

import cn.suanya.rule.bean.SyContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class n extends a
{
  public String c;
  public String d;
  public String e;
  public Class f;
  public String g;
  public Class h;
  public boolean i = false;
  public boolean j = true;
  public int k = 0;
  public String l = "";

  public n(s params)
  {
    super(params);
  }

  private Class a(String paramString, Class paramClass)
  {
    if ((paramString == null) || (paramString.length() < 1))
      return paramClass;
    try
    {
      Class localClass = getClass().getClassLoader().loadClass(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    return paramClass;
  }

  public void a()
  {
    this.c = this.m.a("src");
    this.d = this.m.a("target");
    this.e = this.m.a("loopClazz");
    this.f = a(this.e, HashMap.class);
    this.g = this.m.a("clazz");
    this.h = a(this.g, HashMap.class);
    this.k = this.m.a("start", 0);
    this.i = this.m.a("loop", false);
    this.j = this.m.a("clearLoop", true);
    this.l = this.n;
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    String str = (String)this.b.a(this.c, paramSyContext);
    Object localObject1;
    if (this.d == null)
    {
      localObject1 = paramSyContext;
      if ((localObject1 == null) && (this.i))
      {
        localObject1 = new ArrayList();
        this.b.a(this.d, paramSyContext, localObject1);
      }
      if (!this.i)
        break label142;
      List localList = o.b(this.b, str, this.l, this.k, this.f);
      if (this.j)
        ((List)localObject1).clear();
      ((List)localObject1).addAll(localList);
    }
    while (true)
    {
      super.b(paramSyContext);
      return;
      localObject1 = this.b.a(this.d, paramSyContext);
      break;
      label142: if (localObject1 == null)
      {
        Object localObject2 = o.a(this.b, str, this.l, this.k, this.h);
        if (localObject2 == null)
          continue;
        this.b.a(this.d, paramSyContext, localObject2);
        continue;
      }
      o.a(this.b, str, this.l, this.k, localObject1);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.n
 * JD-Core Version:    0.6.0
 */