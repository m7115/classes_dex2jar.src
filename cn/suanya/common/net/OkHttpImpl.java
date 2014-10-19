package cn.suanya.common.net;

import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.a.s;
import cn.suanya.common.bean.NameValue;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class OkHttpImpl extends HttpUrlBase
  implements IHttpClient
{
  private static OkHttpClient client = null;
  private X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
  private SSLContext sslContext = null;

  public OkHttpImpl()
    throws Exception
  {
    TrustManager[] arrayOfTrustManager = { new MyX509TrustManager() };
    Security.getProviders();
    this.sslContext = SSLContext.getInstance("TLS");
    this.sslContext.init(null, arrayOfTrustManager, new SecureRandom());
    client = new OkHttpClient();
    client.setSslSocketFactory(this.sslContext.getSocketFactory());
    client.setHostnameVerifier(this.hostnameVerifier);
    client.setFollowProtocolRedirects(false);
    ConnectionPool localConnectionPool = new ConnectionPool(20, 300000L);
    client.setConnectionPool(localConnectionPool);
    client.setConnectTimeout(this.connectTimeout, TimeUnit.MILLISECONDS);
    client.setReadTimeout(this.readTimeout, TimeUnit.MILLISECONDS);
  }

  public OkHttpImpl(int paramInt1, int paramInt2)
    throws Exception
  {
    this();
    this.connectTimeout = paramInt1;
    this.readTimeout = paramInt2;
  }

  public OkHttpImpl(int paramInt1, int paramInt2, Map<String, String> paramMap)
    throws Exception
  {
    this();
    this.connectTimeout = paramInt1;
    this.readTimeout = paramInt2;
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this._header.put(localEntry.getKey(), localEntry.getValue());
    }
  }

  public SYHttpResponse syGet(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    InputStream localInputStream = null;
    if ((paramList2 != null) && (!paramList2.isEmpty()))
      paramString = paramString + s.a(paramList2, "UTF-8");
    if (paramString.contains("12306.cn"))
      n.b(paramString);
    URL localURL = new URL(paramString);
    HttpURLConnection localHttpURLConnection = client.open(localURL);
    String str1;
    try
    {
      str1 = this.httpContext.getCookieString(localURL.getHost());
      Iterator localIterator1 = this._header.keySet().iterator();
      while (true)
      {
        boolean bool1 = localIterator1.hasNext();
        localInputStream = null;
        if (!bool1)
          break;
        String str2 = (String)localIterator1.next();
        localHttpURLConnection.setRequestProperty(str2, (String)this._header.get(str2));
      }
    }
    finally
    {
      s.b(localInputStream);
      localHttpURLConnection.disconnect();
    }
    Object localObject2;
    if (paramList1 != null)
    {
      Iterator localIterator2 = paramList1.iterator();
      localObject2 = str1;
      boolean bool2 = localIterator2.hasNext();
      localInputStream = null;
      if (bool2)
      {
        NameValue localNameValue = (NameValue)localIterator2.next();
        if ("charset".equalsIgnoreCase(localNameValue.getName()))
          break label453;
        if ("Cookie".equalsIgnoreCase(localNameValue.getName()))
        {
          localInputStream = null;
          if ((localObject2 == null) || (((String)localObject2).length() <= 0))
            break label443;
          localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
          break label443;
        }
        localHttpURLConnection.setRequestProperty(localNameValue.getName(), localNameValue.getValue());
        break label453;
      }
    }
    while (true)
    {
      localInputStream = null;
      if ((localObject2 != null) && (((String)localObject2).length() > 0))
        localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
      Map localMap = localHttpURLConnection.getHeaderFields();
      localInputStream = null;
      if (localMap == null)
        throw new m("网络链接失败！");
      cookieAdd(localURL.getHost(), getSetCookie(localMap));
      int i = localHttpURLConnection.getResponseCode();
      if ((302 == i) || (301 == i))
      {
        SYHttpResponse localSYHttpResponse1 = syGet(localHttpURLConnection.getHeaderField("location"), paramList1, null);
        s.b(null);
        localHttpURLConnection.disconnect();
        return localSYHttpResponse1;
      }
      localInputStream = filterStreamError(localHttpURLConnection, i);
      SYHttpResponse localSYHttpResponse2 = new SYHttpResponse(localMap, HttpUtil.byteArrayFromInputStream(localInputStream));
      s.b(localInputStream);
      localHttpURLConnection.disconnect();
      return localSYHttpResponse2;
      label443: label453: for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        localObject2 = localObject3;
        break;
      }
      localObject2 = str1;
    }
  }

  public SYHttpResponse syPost(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    InputStream localInputStream = null;
    if (paramString1.contains("12306.cn"))
    {
      n.b(paramString1);
      n.b(paramString2);
    }
    URL localURL = new URL(paramString1);
    HttpURLConnection localHttpURLConnection = client.open(localURL);
    String str1;
    try
    {
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setRequestMethod("POST");
      str1 = this.httpContext.getCookieString(localURL.getHost());
      Iterator localIterator1 = this._header.keySet().iterator();
      while (true)
      {
        boolean bool1 = localIterator1.hasNext();
        localInputStream = null;
        if (!bool1)
          break;
        String str2 = (String)localIterator1.next();
        localHttpURLConnection.setRequestProperty(str2, (String)this._header.get(str2));
      }
    }
    finally
    {
      s.b(localInputStream);
      localHttpURLConnection.disconnect();
    }
    String str3 = HttpUtil.getCharset(paramList);
    localInputStream = null;
    Object localObject2;
    if (paramList != null)
    {
      Iterator localIterator2 = paramList.iterator();
      localObject2 = str1;
      boolean bool2 = localIterator2.hasNext();
      localInputStream = null;
      if (bool2)
      {
        NameValue localNameValue = (NameValue)localIterator2.next();
        if ("charset".equalsIgnoreCase(localNameValue.getName()))
          break label490;
        if ("Cookie".equalsIgnoreCase(localNameValue.getName()))
        {
          localInputStream = null;
          if ((localObject2 == null) || (((String)localObject2).length() <= 0))
            break label480;
          localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
          break label480;
        }
        localHttpURLConnection.setRequestProperty(localNameValue.getName(), localNameValue.getValue());
        break label490;
      }
    }
    while (true)
    {
      localInputStream = null;
      if ((localObject2 != null) && (((String)localObject2).length() > 0))
        localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
      localHttpURLConnection.connect();
      DataOutputStream localDataOutputStream = new DataOutputStream(localHttpURLConnection.getOutputStream());
      localDataOutputStream.write(paramString2.getBytes(str3));
      localDataOutputStream.flush();
      localDataOutputStream.close();
      int i = getResponseCode(localHttpURLConnection);
      Map localMap = localHttpURLConnection.getHeaderFields();
      localInputStream = null;
      if (localMap == null)
        throw new m("网络链接失败！");
      cookieAdd(localURL.getHost(), getSetCookie(localMap));
      if ((i == 302) || (i == 301))
      {
        SYHttpResponse localSYHttpResponse1 = syGet(localHttpURLConnection.getHeaderField("location"), paramList, null);
        s.b(null);
        localHttpURLConnection.disconnect();
        return localSYHttpResponse1;
      }
      localInputStream = filterStreamError(localHttpURLConnection, i);
      SYHttpResponse localSYHttpResponse2 = new SYHttpResponse(localMap, HttpUtil.byteArrayFromInputStream(localInputStream));
      s.b(localInputStream);
      localHttpURLConnection.disconnect();
      return localSYHttpResponse2;
      label480: label490: for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        localObject2 = localObject3;
        break;
      }
      localObject2 = str1;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.OkHttpImpl
 * JD-Core Version:    0.6.0
 */