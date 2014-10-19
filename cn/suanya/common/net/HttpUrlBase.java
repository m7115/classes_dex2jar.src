package cn.suanya.common.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class HttpUrlBase extends HttpBase
{
  protected HttpContext httpContext = new HttpContext();

  private InputStream filterStream(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    String str = paramHttpURLConnection.getContentEncoding();
    if ((str != null) && (str.toLowerCase().equals("gzip")))
      return new GZIPInputStream(paramHttpURLConnection.getInputStream());
    return paramHttpURLConnection.getInputStream();
  }

  public void addCookie(String paramString, Cookie paramCookie)
  {
    this.httpContext.addCookie(paramString, paramCookie);
  }

  public void clearCookie()
  {
    this.httpContext.clearCookie();
  }

  protected void cookieAdd(String paramString, List<String> paramList)
  {
    if (paramList == null);
    while (true)
    {
      return;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Cookie localCookie = new Cookie((String)localIterator.next());
        if ((localCookie.getmDomain() == null) || (localCookie.getmDomain().length() == 0))
          localCookie.setmDomain(paramString);
        this.httpContext.addCookie(paramString, localCookie);
      }
    }
  }

  protected InputStream filterStreamError(HttpURLConnection paramHttpURLConnection, int paramInt)
    throws IOException
  {
    if (paramInt >= 400);
    for (int i = 1; i != 0; i = 0)
      return paramHttpURLConnection.getErrorStream();
    return filterStream(paramHttpURLConnection);
  }

  public List<Cookie> getCookies(String paramString)
  {
    return this.httpContext.getCookies(paramString);
  }

  public HttpContext getHttpContext()
  {
    return this.httpContext;
  }

  protected int getResponseCode(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    try
    {
      int i = paramHttpURLConnection.getResponseCode();
      return i;
    }
    catch (IOException localIOException)
    {
    }
    return paramHttpURLConnection.getResponseCode();
  }

  protected List<String> getSetCookie(Map<String, List<String>> paramMap)
  {
    if (paramMap == null)
      return null;
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((str != null) && ("set-cookie".equals(str.toLowerCase())))
        return (List)paramMap.get(str);
    }
    return null;
  }

  public void setHttpContext(HttpContext paramHttpContext)
  {
    this.httpContext = paramHttpContext;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpUrlBase
 * JD-Core Version:    0.6.0
 */