package cn.suanya.common.net;

import cn.suanya.common.bean.NameValue;
import java.util.List;

public class HttpUrlAndOkImpl extends HttpUrlBase
{
  private HttpUrlBase defautClient;
  private HttpUrlBase okClient;
  private HttpUrlBase urlClient;

  public HttpUrlAndOkImpl()
    throws Exception
  {
    this.urlClient = new HttpUrlImpl();
    this.okClient = new OkHttpImpl();
    this.urlClient.setHttpContext(this.httpContext);
    this.okClient.setHttpContext(this.httpContext);
    this.defautClient = this.urlClient;
  }

  public HttpUrlAndOkImpl(int paramInt1, int paramInt2)
    throws Exception
  {
    this.urlClient = new HttpUrlImpl(paramInt1, paramInt2);
    this.okClient = new OkHttpImpl(paramInt1, paramInt2);
    this.urlClient.setHttpContext(this.httpContext);
    this.okClient.setHttpContext(this.httpContext);
    this.defautClient = this.urlClient;
  }

  private HttpUrlBase getClient(HttpUrlBase paramHttpUrlBase)
  {
    if (paramHttpUrlBase == null)
      return this.defautClient;
    if (paramHttpUrlBase == this.urlClient)
      return this.okClient;
    return this.urlClient;
  }

  public SYHttpResponse syGet(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    long l = System.currentTimeMillis();
    HttpUrlBase localHttpUrlBase1 = getClient(null);
    SYHttpResponse localSYHttpResponse1;
    try
    {
      SYHttpResponse localSYHttpResponse2 = localHttpUrlBase1.syGet(paramString, paramList1, paramList2);
      this.defautClient = localHttpUrlBase1;
      return localSYHttpResponse2;
    }
    catch (Exception localException)
    {
      HttpUrlBase localHttpUrlBase2 = getClient(localHttpUrlBase1);
      if (System.currentTimeMillis() - l > 20000L)
      {
        this.defautClient = localHttpUrlBase2;
        throw localException;
      }
      localSYHttpResponse1 = localHttpUrlBase2.syGet(paramString, paramList1, paramList2);
      this.defautClient = localHttpUrlBase2;
    }
    return localSYHttpResponse1;
  }

  public SYHttpResponse syPost(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    long l = System.currentTimeMillis();
    HttpUrlBase localHttpUrlBase1 = getClient(null);
    SYHttpResponse localSYHttpResponse1;
    try
    {
      SYHttpResponse localSYHttpResponse2 = localHttpUrlBase1.syPost(paramString1, paramList, paramString2);
      this.defautClient = localHttpUrlBase1;
      return localSYHttpResponse2;
    }
    catch (Exception localException)
    {
      HttpUrlBase localHttpUrlBase2 = getClient(localHttpUrlBase1);
      if (System.currentTimeMillis() - l > 20000L)
      {
        this.defautClient = localHttpUrlBase2;
        throw localException;
      }
      localSYHttpResponse1 = localHttpUrlBase2.syPost(paramString1, paramList, paramString2);
      this.defautClient = localHttpUrlBase2;
    }
    return localSYHttpResponse1;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpUrlAndOkImpl
 * JD-Core Version:    0.6.0
 */