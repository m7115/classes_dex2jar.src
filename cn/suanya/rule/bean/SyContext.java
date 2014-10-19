package cn.suanya.rule.bean;

import cn.suanya.common.net.IHttpClient;
import cn.suanya.rule.a.a;
import java.util.ArrayList;
import java.util.List;

public class SyContext
{
  private List<Each> eachPool;
  public a engine;
  private SyFunction f;
  private Context g;
  public IHttpClient httpClient;
  private Context l;
  private Http r;

  public SyContext(Context paramContext1, Context paramContext2)
  {
    this.g = paramContext1;
    this.l = paramContext2;
  }

  public SyContext(Context paramContext1, Context paramContext2, SyFunction paramSyFunction, a parama, IHttpClient paramIHttpClient)
  {
    this.g = paramContext1;
    this.l = paramContext2;
    this.f = paramSyFunction;
    this.engine = parama;
    this.httpClient = paramIHttpClient;
  }

  public void endEach()
  {
    this.eachPool.remove(0);
  }

  public Each getEach()
  {
    return (Each)this.eachPool.get(0);
  }

  public SyFunction getF()
  {
    return this.f;
  }

  public Context getG()
  {
    if (this.g == null)
      this.g = new Context();
    return this.g;
  }

  public Context getL()
  {
    if (this.l == null)
      this.l = new Context();
    return this.l;
  }

  public Http getR()
  {
    if (this.r == null)
      this.r = new Http();
    return this.r;
  }

  public void setF(SyFunction paramSyFunction)
  {
    this.f = paramSyFunction;
  }

  public void setG(Context paramContext)
  {
    this.g = paramContext;
  }

  public void setL(Context paramContext)
  {
    this.l = paramContext;
  }

  public void setR(Http paramHttp)
  {
    this.r = paramHttp;
  }

  public Each startEach(Object paramObject)
  {
    if (this.eachPool == null)
      this.eachPool = new ArrayList();
    Each localEach = new Each(paramObject);
    this.eachPool.add(0, localEach);
    return localEach;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.bean.SyContext
 * JD-Core Version:    0.6.0
 */