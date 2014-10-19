package cn.suanya.rule.b;

import cn.suanya.rule.d;
import cn.suanya.rule.e;
import cn.suanya.rule.f;
import cn.suanya.rule.g;
import cn.suanya.rule.h;
import cn.suanya.rule.i;
import cn.suanya.rule.j;
import cn.suanya.rule.k;
import cn.suanya.rule.l;
import cn.suanya.rule.m;
import cn.suanya.rule.n;
import cn.suanya.rule.p;
import cn.suanya.rule.q;
import cn.suanya.rule.s;
import cn.suanya.rule.t;
import cn.suanya.rule.u;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class b extends DefaultHandler
{
  public s a;
  public c b;

  public b(s params)
  {
    this.a = params;
  }

  private c a(String paramString)
  {
    if ("call".equals(paramString))
      return new cn.suanya.rule.c(this.a);
    if ("if".equals(paramString))
      return new k(this.a);
    if ("else".equals(paramString))
      return new f(this.a);
    if ("while".equals(paramString))
      return new u(this.a);
    if ("eval".equals(paramString))
      return new g(this.a);
    if ("exception".equals(paramString))
      return new h(this.a);
    if ("regex".equals(paramString))
      return new n(this.a);
    if ("each".equals(paramString))
      return new e(this.a);
    if ("head".equals(paramString))
      return new i(this.a);
    if ("parm".equals(paramString))
      return new m(this.a);
    if ("json".equals(paramString))
      return new l(this.a);
    if ("block".equals(paramString))
      return new cn.suanya.rule.b(this.a);
    if ("http".equals(paramString))
      return new j(this.a);
    if ("request".equals(paramString))
      return new p(this.a);
    if ("response".equals(paramString))
      return new q(this.a);
    if ("try".equals(paramString))
      return new t(this.a);
    if ("catch".equals(paramString))
      return new d(this.a);
    return new cn.suanya.rule.a(this.a);
  }

  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    if (this.b != null)
      this.b = this.b.a(paramArrayOfChar, paramInt1, paramInt2);
    super.characters(paramArrayOfChar, paramInt1, paramInt2);
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    this.b = this.b.a(this.a);
    super.endElement(paramString1, paramString2, paramString3);
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    c localc = a(paramString3);
    a locala = new a(paramAttributes);
    this.b = localc.a(this.b, locala);
    super.startElement(paramString1, paramString2, paramString3, paramAttributes);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.b.b
 * JD-Core Version:    0.6.0
 */