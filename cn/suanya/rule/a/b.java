package cn.suanya.rule.a;

import cn.suanya.synl.Synl;

public class b
  implements a
{
  private static b b;
  private Synl a = new Synl();

  public static a a()
  {
    if (b == null)
      b = new b();
    return b;
  }

  public Object a(String paramString, Object paramObject)
    throws Exception
  {
    return this.a.getValue(paramString, paramObject);
  }

  public void a(String paramString, Object paramObject1, Object paramObject2)
    throws Exception
  {
    this.a.setValue(paramString, paramObject1, paramObject2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.a.b
 * JD-Core Version:    0.6.0
 */