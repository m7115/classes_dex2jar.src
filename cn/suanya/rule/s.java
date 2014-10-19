package cn.suanya.rule;

import cn.suanya.common.a.m;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.rule.a.b;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.SyContext;
import cn.suanya.rule.bean.SyFunction;
import java.util.HashMap;
import java.util.Map;

public class s
{
  public SyFunction a;
  public Map<String, String> b;
  public Map<String, a> c;
  private String d;

  public s(String paramString)
  {
    this.d = paramString;
    this.a = new SyFunction();
    this.b = new HashMap();
    this.c = new HashMap();
  }

  public SyContext a(String paramString, Context paramContext1, Context paramContext2, IHttpClient paramIHttpClient)
    throws Exception
  {
    SyContext localSyContext = new SyContext(paramContext1, paramContext2);
    localSyContext.httpClient = paramIHttpClient;
    return a(paramString, localSyContext);
  }

  public SyContext a(String paramString, SyContext paramSyContext)
    throws Exception
  {
    if (paramSyContext.getF() == null)
      paramSyContext.setF(this.a);
    if (paramSyContext.engine == null)
      paramSyContext.engine = b.a();
    a locala = (a)this.c.get(paramString);
    if (locala != null)
    {
      locala.a(paramSyContext);
      return paramSyContext;
    }
    throw new m("方法名" + paramString + "错误");
  }

  public void a(SyFunction paramSyFunction)
  {
    this.a = paramSyFunction;
  }

  public boolean a(String paramString)
  {
    if (paramString == null)
      return true;
    return paramString.equals(this.d);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.s
 * JD-Core Version:    0.6.0
 */