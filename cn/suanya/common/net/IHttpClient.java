package cn.suanya.common.net;

import cn.suanya.common.bean.NameValue;
import java.io.InputStream;
import java.util.List;

public abstract interface IHttpClient
{
  public abstract void addCookie(String paramString, Cookie paramCookie);

  public abstract void clearCookie();

  public abstract InputStream get(String paramString)
    throws Exception;

  public abstract List<Cookie> getCookies(String paramString);

  public abstract String getStr(String paramString)
    throws Exception;

  public abstract String getStr(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception;

  public abstract SYHttpResponse httpDo(String paramString1, String paramString2, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception;

  public abstract String httpDoStr(String paramString1, String paramString2, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception;

  public abstract InputStream post(String paramString, List<NameValue> paramList)
    throws Exception;

  public abstract InputStream post(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception;

  public abstract String postStr(String paramString, List<NameValue> paramList)
    throws Exception;

  public abstract String postStr(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception;

  public abstract String postStr(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception;

  public abstract SYHttpResponse syGet(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception;

  public abstract SYHttpResponse syPost(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception;

  public abstract SYHttpResponse syPost(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.IHttpClient
 * JD-Core Version:    0.6.0
 */