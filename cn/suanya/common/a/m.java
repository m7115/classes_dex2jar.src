package cn.suanya.common.a;

public class m extends Exception
{
  private static final long serialVersionUID = -7057773943105155241L;
  private String a;
  private String b;

  public m(String paramString)
  {
    super(paramString);
    a(d.e.a());
  }

  public m(String paramString1, String paramString2)
  {
    super(paramString2);
    a(paramString1);
  }

  public m(String paramString1, String paramString2, Throwable paramThrowable)
  {
    super(paramString2, paramThrowable);
    a(paramString1);
  }

  public static boolean a(String paramString, Exception paramException)
  {
    if (!(paramException instanceof m));
    m localm;
    do
    {
      return false;
      localm = (m)paramException;
    }
    while (paramString == null);
    return paramString.equals(localm.a());
  }

  public String a()
  {
    return this.a;
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public void b(String paramString)
  {
    this.b = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.m
 * JD-Core Version:    0.6.0
 */