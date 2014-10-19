package cn.suanya.rule;

import cn.suanya.common.a.n;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.common.net.SYHttpResponse;
import cn.suanya.rule.bean.Http;
import cn.suanya.rule.bean.SyContext;
import java.util.List;

public class p extends a
{
  public String c;
  public String d;
  public int e;
  public int f;
  public boolean g;
  public String h;

  public p(s params)
  {
    super(params);
  }

  protected void a()
  {
    this.c = this.m.a("url");
    this.d = this.m.a("method", "get");
    this.e = this.m.a("connectTimeout", 10000);
    this.f = this.m.a("readTimeout", 10000);
    this.g = this.m.a("staticUrl", true);
    this.h = this.m.a("encoding", "utf-8");
    super.a();
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    paramSyContext.setR(new Http(this.h));
    super.b(paramSyContext);
    n.b("Request:" + this.c);
    n.b("Parms:" + paramSyContext.getR().getParms());
    String str = this.c;
    if (!this.g)
      str = (String)this.b.a(str, paramSyContext);
    List localList1 = paramSyContext.getR().getHeads();
    List localList2 = paramSyContext.getR().getParms();
    if (this.d.equalsIgnoreCase("get"));
    for (SYHttpResponse localSYHttpResponse = paramSyContext.httpClient.syGet(str, localList1, localList2); ; localSYHttpResponse = paramSyContext.httpClient.syPost(str, localList1, localList2))
    {
      paramSyContext.getR().setResponse(localSYHttpResponse);
      return;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.p
 * JD-Core Version:    0.6.0
 */