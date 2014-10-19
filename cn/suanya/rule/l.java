package cn.suanya.rule;

import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.rule.bean.SyContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class l extends a
{
  public String c;
  public String d;

  public l(s params)
  {
    super(params);
  }

  public void a()
  {
    this.c = this.m.a("src");
    this.d = this.m.a("target");
  }

  protected void b(SyContext paramSyContext)
    throws Exception
  {
    String str = (String)this.b.a(this.c, paramSyContext);
    if (str == null)
    {
      n.b("Json:" + this.c + " is null!");
      return;
    }
    try
    {
      if (str.startsWith("["))
      {
        JSONArray localJSONArray = new JSONArray(str);
        this.b.a(this.d, paramSyContext, localJSONArray);
      }
      while (true)
      {
        super.b(paramSyContext);
        return;
        JSONObject localJSONObject = new JSONObject(str);
        this.b.a(this.d, paramSyContext, localJSONObject);
      }
    }
    catch (Exception localException)
    {
      n.b(str);
      n.b(localException);
    }
    throw new m("0", "数据格式错误(json)", new m("1", str, localException));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.l
 * JD-Core Version:    0.6.0
 */