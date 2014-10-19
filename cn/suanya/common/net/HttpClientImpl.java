package cn.suanya.common.net;

import cn.suanya.common.a.n;
import cn.suanya.common.a.s;
import cn.suanya.common.bean.NameValue;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;

public class HttpClientImpl extends HttpBase
  implements IHttpClient
{
  private static HttpClient httpClient;
  private HashMap<String, String> CookieContiner = new HashMap();
  private BasicCookieStore cookieStore;
  private BasicHttpContext localContext;

  public HttpClientImpl()
    throws Exception
  {
    try
    {
      KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      localKeyStore.load(null, null);
      SSLSocketFactoryEx localSSLSocketFactoryEx = new SSLSocketFactoryEx(localKeyStore);
      localSSLSocketFactoryEx.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(localBasicHttpParams, "ISO-8859-1");
      HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, true);
      ConnManagerParams.setTimeout(localBasicHttpParams, this.connectTimeout);
      ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 800);
      ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(300));
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, this.connectTimeout);
      HttpConnectionParams.setSoTimeout(localBasicHttpParams, this.readTimeout);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", localSSLSocketFactoryEx, 443));
      httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      HttpClientParams.setCookiePolicy(httpClient.getParams(), "compatibility");
      this.cookieStore = new BasicCookieStore();
      this.localContext = new BasicHttpContext();
      this.localContext.setAttribute("http.cookie-store", this.cookieStore);
      httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
      httpClient.getParams().setParameter("http.protocol.allow-circular-redirects", Boolean.valueOf(true));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      httpClient = new DefaultHttpClient();
    }
  }

  private Cookie cookie2zxCookie(org.apache.http.cookie.Cookie paramCookie)
  {
    Cookie localCookie = new Cookie();
    localCookie.setmName(paramCookie.getName());
    localCookie.setmValue(paramCookie.getValue());
    localCookie.setmDomain(paramCookie.getDomain());
    localCookie.setmComment(paramCookie.getComment());
    return localCookie;
  }

  private InputStream filterStream(HttpEntity paramHttpEntity)
    throws IOException
  {
    Header localHeader = paramHttpEntity.getContentEncoding();
    if ((localHeader != null) && (localHeader.getValue() != null) && (localHeader.getValue().toLowerCase().equals("gzip")))
      return new GZIPInputStream(paramHttpEntity.getContent());
    return paramHttpEntity.getContent();
  }

  private void saveCookie(Header[] paramArrayOfHeader)
  {
    if (paramArrayOfHeader == null);
    while (true)
    {
      return;
      int i = paramArrayOfHeader.length;
      for (int j = 0; j < i; j++)
      {
        String[] arrayOfString1 = paramArrayOfHeader[j].getValue().split(";");
        int k = 0;
        if (k >= arrayOfString1.length)
          continue;
        String[] arrayOfString2 = arrayOfString1[k].split("=");
        String str1 = arrayOfString2[0].trim();
        if (arrayOfString2.length > 1);
        for (String str2 = arrayOfString2[1].trim(); ; str2 = "")
        {
          this.CookieContiner.put(str1, str2);
          k++;
          break;
        }
      }
    }
  }

  private BasicClientCookie zxCookie2Cookie(Cookie paramCookie)
  {
    BasicClientCookie localBasicClientCookie = new BasicClientCookie(paramCookie.getmName(), paramCookie.getmValue());
    localBasicClientCookie.setDomain(paramCookie.getmDomain());
    localBasicClientCookie.setComment(paramCookie.getmComment());
    return localBasicClientCookie;
  }

  public void addCookie(String paramString, Cookie paramCookie)
  {
    BasicClientCookie localBasicClientCookie = zxCookie2Cookie(paramCookie);
    this.cookieStore.addCookie(localBasicClientCookie);
  }

  public void addCookies(HttpPost paramHttpPost)
  {
    if (this.CookieContiner.size() == 0)
      return;
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.CookieContiner.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str1 = ((String)localEntry.getKey()).toString();
      String str2 = ((String)localEntry.getValue()).toString();
      localStringBuilder.append(str1);
      localStringBuilder.append("=");
      localStringBuilder.append(str2);
      localStringBuilder.append(";");
    }
    paramHttpPost.addHeader("Cookie", localStringBuilder.toString());
  }

  public void clearCookie()
  {
    this.cookieStore.clear();
  }

  public List<Cookie> getCookies(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.cookieStore.getCookies().iterator();
    while (localIterator.hasNext())
    {
      org.apache.http.cookie.Cookie localCookie = (org.apache.http.cookie.Cookie)localIterator.next();
      String str = localCookie.getDomain();
      if ((str == null) || (str.indexOf(paramString) < 0))
        continue;
      localArrayList.add(cookie2zxCookie(localCookie));
    }
    return localArrayList;
  }

  public SYHttpResponse syGet(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    if ((paramList2 != null) && (!paramList2.isEmpty()))
      paramString = paramString + s.a(paramList2, "UTF-8");
    if (paramString.contains("12306.cn"))
      n.b(paramString);
    HttpGet localHttpGet = new HttpGet(paramString);
    if ((paramList1 != null) && (paramList1.size() > 0))
    {
      Iterator localIterator = paramList1.iterator();
      while (localIterator.hasNext())
      {
        NameValue localNameValue = (NameValue)localIterator.next();
        if ("charset".equalsIgnoreCase(localNameValue.getName()))
          continue;
        localHttpGet.addHeader(localNameValue.getName(), localNameValue.getValue());
      }
    }
    HttpResponse localHttpResponse = httpClient.execute(localHttpGet, this.localContext);
    if (localHttpResponse.getStatusLine().getStatusCode() != 200)
      throw new RuntimeException("请求失败");
    InputStream localInputStream = filterStream(localHttpResponse.getEntity());
    try
    {
      SYHttpResponse localSYHttpResponse = new SYHttpResponse(null, HttpUtil.byteArrayFromInputStream(localInputStream));
      if (localInputStream != null);
      try
      {
        localInputStream.close();
        return localSYHttpResponse;
      }
      catch (IOException localIOException1)
      {
        n.b(localIOException1);
        return localSYHttpResponse;
      }
    }
    finally
    {
      if (localInputStream == null);
    }
    try
    {
      localInputStream.close();
      throw localObject;
    }
    catch (IOException localIOException2)
    {
      while (true)
        n.b(localIOException2);
    }
  }

  public SYHttpResponse syPost(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    if (paramString1.contains("12306.cn"))
    {
      n.b(paramString1);
      n.b(paramString2);
    }
    HttpPost localHttpPost = new HttpPost(paramString1);
    if ((paramList != null) && (paramList.size() > 0))
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        NameValue localNameValue = (NameValue)localIterator.next();
        if ("charset".equalsIgnoreCase(localNameValue.getName()))
          continue;
        localHttpPost.addHeader(localNameValue.getName(), localNameValue.getValue());
      }
    }
    localHttpPost.setEntity(new StringEntity(paramString2, HttpUtil.getCharset(paramList)));
    HttpResponse localHttpResponse = httpClient.execute(localHttpPost, this.localContext);
    if ((localHttpResponse.getStatusLine().getStatusCode() != 200) && (localHttpResponse.getStatusLine().getStatusCode() != 401))
      throw new RuntimeException("请求失败" + localHttpResponse.getStatusLine().getStatusCode());
    InputStream localInputStream = filterStream(localHttpResponse.getEntity());
    Header[] arrayOfHeader1 = localHttpResponse.getHeaders("Date");
    HashMap localHashMap = null;
    if (arrayOfHeader1 != null)
    {
      localHashMap = new HashMap();
      ArrayList localArrayList = new ArrayList();
      Header[] arrayOfHeader2 = localHttpResponse.getHeaders("Date");
      if ((arrayOfHeader2 != null) && (arrayOfHeader2.length > 0))
      {
        localArrayList.add(arrayOfHeader2[0].getValue());
        localHashMap.put("Date", localArrayList);
      }
    }
    try
    {
      SYHttpResponse localSYHttpResponse = new SYHttpResponse(localHashMap, HttpUtil.byteArrayFromInputStream(localInputStream));
      if (localInputStream != null);
      try
      {
        localInputStream.close();
        return localSYHttpResponse;
      }
      catch (IOException localIOException1)
      {
        n.b(localIOException1);
        return localSYHttpResponse;
      }
    }
    finally
    {
      if (localInputStream == null);
    }
    try
    {
      localInputStream.close();
      throw localObject;
    }
    catch (IOException localIOException2)
    {
      while (true)
        n.b(localIOException2);
    }
  }

  class SSLSocketFactoryEx extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext sslContext = SSLContext.getInstance("TLS");

    public SSLSocketFactoryEx(KeyStore arg2)
      throws Exception
    {
      super();
      SSLContext localSSLContext = this.sslContext;
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = new MyX509TrustManager();
      localSSLContext.init(null, arrayOfTrustManager, null);
    }

    public Socket createSocket()
      throws IOException
    {
      return this.sslContext.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
      throws IOException, UnknownHostException
    {
      return this.sslContext.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpClientImpl
 * JD-Core Version:    0.6.0
 */