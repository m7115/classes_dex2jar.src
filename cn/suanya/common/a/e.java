package cn.suanya.common.a;

import java.util.ArrayList;
import java.util.List;

public class e
{
  static String d = "(?'";
  static int e = 3;
  static String f = "'";
  public String a;
  public String b;
  public List<String> c = new ArrayList();

  public e(String paramString)
  {
    this.a = paramString;
    for (int i = paramString.indexOf(d); i >= 0; i = paramString.indexOf(d))
    {
      int j = paramString.indexOf(f, i + e);
      String str = paramString.substring(i + e, j);
      this.c.add(str);
      paramString = paramString.substring(0, i + 1) + paramString.substring(j + 1);
    }
    this.b = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.e
 * JD-Core Version:    0.6.0
 */