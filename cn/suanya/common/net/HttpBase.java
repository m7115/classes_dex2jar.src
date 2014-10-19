package cn.suanya.common.net;

import cn.suanya.common.a.s;
import cn.suanya.common.bean.NameValue;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpBase
  implements IHttpClient
{
  protected Map<String, String> _header = new HashMap();
  protected int connectTimeout = 30000;
  protected int readTimeout = 30000;

  public HttpBase()
  {
    this._header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:23.0) Gecko/20100101 Firefox/23.0");
    this._header.put("Accept", "application/json, text/javascript, */*");
    this._header.put("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
    this._header.put("Connection", "keep-alive");
    this._header.put("Accept-Encoding", "gzip, deflate");
  }

  public void addCookie(String paramString, Cookie paramCookie)
  {
  }

  public void clearCookie()
  {
  }

  public InputStream get(String paramString)
    throws Exception
  {
    return syGet(paramString, null, null).getInputStream();
  }

  public List<Cookie> getCookies(String paramString)
  {
    return null;
  }

  public String getStr(String paramString)
    throws Exception
  {
    return syGet(paramString, null, null).getString();
  }

  public String getStr(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    return syGet(paramString, paramList1, paramList2).getString();
  }

  public SYHttpResponse httpDo(String paramString1, String paramString2, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    if ("post".equalsIgnoreCase(paramString1))
      return syPost(paramString2, paramList1, paramList2);
    return syGet(paramString2, paramList1, paramList2);
  }

  public String httpDoStr(String paramString1, String paramString2, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    return httpDo(paramString1, paramString2, paramList1, paramList2).getString();
  }

  public InputStream post(String paramString, List<NameValue> paramList)
    throws Exception
  {
    return syPost(paramString, null, paramList).getInputStream();
  }

  public InputStream post(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    return syPost(paramString1, paramList, paramString2).getInputStream();
  }

  public String postStr(String paramString, List<NameValue> paramList)
    throws Exception
  {
    return syPost(paramString, null, paramList).getString();
  }

  public String postStr(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    return syPost(paramString1, paramList, paramString2).getString();
  }

  public String postStr(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    return syPost(paramString, paramList1, paramList2).getString();
  }

  public SYHttpResponse syGet(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    return null;
  }

  public SYHttpResponse syPost(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    return null;
  }

  public SYHttpResponse syPost(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    return syPost(paramString, paramList1, s.a(paramList2, HttpUtil.getCharset(paramList1)));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpBase
 * JD-Core Version:    0.6.0
 */