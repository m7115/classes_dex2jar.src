package cn.suanya.common.net;

import cn.suanya.common.a.n;
import cn.suanya.common.a.s;
import cn.suanya.common.bean.NameValue;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

public class HttpUrlImpl extends HttpUrlBase
  implements IHttpClient
{
  private static int maxTryNum = 3;
  private int getTryNum = 0;
  private X509HostnameVerifier hostnameVerifier = SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
  private int postTryNum = 0;
  private SSLContext sslContext = null;

  public HttpUrlImpl()
    throws Exception
  {
    TrustManager[] arrayOfTrustManager = { new MyX509TrustManager() };
    Security.getProviders();
    this.sslContext = SSLContext.getInstance("TLS");
    this.sslContext.init(null, arrayOfTrustManager, new SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(this.sslContext.getSocketFactory());
    HttpsURLConnection.setDefaultHostnameVerifier(this.hostnameVerifier);
    HttpsURLConnection.setFollowRedirects(false);
  }

  public HttpUrlImpl(int paramInt1, int paramInt2)
    throws Exception
  {
    this();
    this.connectTimeout = paramInt1;
    this.readTimeout = paramInt2;
  }

  public HttpUrlImpl(int paramInt1, int paramInt2, Map<String, String> paramMap)
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

  private SYHttpResponse syGet2(String paramString, List<NameValue> paramList)
    throws Exception
  {
    InputStream localInputStream = null;
    if (paramString.contains("12306.cn"))
      n.b(paramString);
    URL localURL = new URL(paramString);
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
    String str1;
    try
    {
      localHttpURLConnection.setConnectTimeout(this.connectTimeout);
      localHttpURLConnection.setReadTimeout(this.readTimeout);
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
    if (paramList != null)
    {
      Iterator localIterator2 = paramList.iterator();
      localObject2 = str1;
      boolean bool5 = localIterator2.hasNext();
      localInputStream = null;
      if (bool5)
      {
        NameValue localNameValue = (NameValue)localIterator2.next();
        if ("charset".equalsIgnoreCase(localNameValue.getName()))
          break label672;
        if ("Cookie".equalsIgnoreCase(localNameValue.getName()))
        {
          localInputStream = null;
          if ((localObject2 == null) || (((String)localObject2).length() <= 0))
            break label662;
          localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
          break label662;
        }
        localHttpURLConnection.setRequestProperty(localNameValue.getName(), localNameValue.getValue());
        break label672;
      }
    }
    while (true)
    {
      localInputStream = null;
      if ((localObject2 != null) && (((String)localObject2).length() > 0))
        localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
      Map localMap;
      try
      {
        localHttpURLConnection.connect();
        localMap = localHttpURLConnection.getHeaderFields();
        localInputStream = null;
        if (localMap != null)
          break label512;
        this.getTryNum = (1 + this.getTryNum);
        int k = this.getTryNum;
        int m = maxTryNum;
        localInputStream = null;
        if (k >= m)
          break label494;
        localHttpURLConnection.disconnect();
        SYHttpResponse localSYHttpResponse2 = syGet2(paramString, paramList);
        s.b(null);
        localHttpURLConnection.disconnect();
        return localSYHttpResponse2;
      }
      catch (IOException localIOException)
      {
        boolean bool2 = localIOException instanceof EOFException;
        localInputStream = null;
        if (!bool2)
        {
          boolean bool3 = localIOException instanceof FileNotFoundException;
          localInputStream = null;
          if (!bool3)
          {
            boolean bool4 = localIOException instanceof SSLException;
            localInputStream = null;
            if (!bool4)
            {
              String str3 = localIOException.getMessage();
              localInputStream = null;
              if ((str3 == null) || (localIOException.getMessage().indexOf("-1") <= 0))
                break label491;
            }
          }
        }
        this.getTryNum = (1 + this.getTryNum);
        int i = this.getTryNum;
        int j = maxTryNum;
        localInputStream = null;
        if (i < j)
        {
          localHttpURLConnection.disconnect();
          SYHttpResponse localSYHttpResponse1 = syGet2(paramString, paramList);
          s.b(null);
          localHttpURLConnection.disconnect();
          return localSYHttpResponse1;
        }
        throw localIOException;
      }
      label491: throw localIOException;
      label494: throw new Exception("网络链接失败！", new Exception(paramString));
      label512: int n = this.getTryNum;
      localInputStream = null;
      if (n > 0)
        n.a("GET//" + paramString + "//" + this.getTryNum);
      cookieAdd(localURL.getHost(), getSetCookie(localMap));
      int i1 = localHttpURLConnection.getResponseCode();
      if ((i1 == 302) || (i1 == 301))
      {
        SYHttpResponse localSYHttpResponse3 = syGet(localHttpURLConnection.getHeaderField("location"), paramList, null);
        s.b(null);
        localHttpURLConnection.disconnect();
        return localSYHttpResponse3;
      }
      localInputStream = filterStreamError(localHttpURLConnection, i1);
      SYHttpResponse localSYHttpResponse4 = new SYHttpResponse(localMap, HttpUtil.byteArrayFromInputStream(localInputStream));
      s.b(localInputStream);
      localHttpURLConnection.disconnect();
      return localSYHttpResponse4;
      label662: label672: for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        localObject2 = localObject3;
        break;
      }
      localObject2 = str1;
    }
  }

  private SYHttpResponse syPost2(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    InputStream localInputStream = null;
    if (paramString1.contains("12306.cn"))
    {
      n.b(paramString1);
      n.b(paramString2);
    }
    URL localURL = new URL(paramString1);
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
    String str1;
    try
    {
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setConnectTimeout(this.connectTimeout);
      localHttpURLConnection.setReadTimeout(this.readTimeout);
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
          break label711;
        if ("Cookie".equalsIgnoreCase(localNameValue.getName()))
        {
          localInputStream = null;
          if ((localObject2 == null) || (((String)localObject2).length() <= 0))
            break label701;
          localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
          break label701;
        }
        localHttpURLConnection.setRequestProperty(localNameValue.getName(), localNameValue.getValue());
        break label711;
      }
    }
    while (true)
    {
      localInputStream = null;
      if ((localObject2 != null) && (((String)localObject2).length() > 0))
        localHttpURLConnection.setRequestProperty("Cookie", (String)localObject2);
      int k;
      Map localMap;
      try
      {
        localHttpURLConnection.connect();
        DataOutputStream localDataOutputStream = new DataOutputStream(localHttpURLConnection.getOutputStream());
        localDataOutputStream.write(paramString2.getBytes(str3));
        localDataOutputStream.flush();
        localDataOutputStream.close();
        k = getResponseCode(localHttpURLConnection);
        localMap = localHttpURLConnection.getHeaderFields();
        localInputStream = null;
        if (localMap != null)
          break label603;
        this.postTryNum = (1 + this.postTryNum);
        int m = this.postTryNum;
        int n = maxTryNum;
        localInputStream = null;
        if (m >= n)
          break label585;
        localHttpURLConnection.disconnect();
        SYHttpResponse localSYHttpResponse2 = syPost2(paramString1, paramList, paramString2);
        s.b(null);
        localHttpURLConnection.disconnect();
        return localSYHttpResponse2;
      }
      catch (IOException localIOException)
      {
        boolean bool3 = localIOException instanceof EOFException;
        localInputStream = null;
        if (!bool3)
        {
          boolean bool4 = localIOException instanceof FileNotFoundException;
          localInputStream = null;
          if (!bool4)
          {
            boolean bool5 = localIOException instanceof SSLException;
            localInputStream = null;
            if (!bool5)
            {
              String str4 = localIOException.getMessage();
              localInputStream = null;
              if ((str4 == null) || (localIOException.getMessage().indexOf("-1") <= 0))
                break label582;
            }
          }
        }
        this.postTryNum = (1 + this.postTryNum);
        int i = this.postTryNum;
        int j = maxTryNum;
        localInputStream = null;
        if (i < j)
        {
          localHttpURLConnection.disconnect();
          SYHttpResponse localSYHttpResponse1 = syPost2(paramString1, paramList, paramString2);
          s.b(null);
          localHttpURLConnection.disconnect();
          return localSYHttpResponse1;
        }
        throw localIOException;
      }
      label582: throw localIOException;
      label585: throw new Exception("网络链接失败！", new Exception(paramString1));
      label603: cookieAdd(localURL.getHost(), getSetCookie(localMap));
      if ((k == 302) || (k == 301))
      {
        SYHttpResponse localSYHttpResponse3 = syGet(localHttpURLConnection.getHeaderField("location"), paramList, null);
        s.b(null);
        localHttpURLConnection.disconnect();
        return localSYHttpResponse3;
      }
      localInputStream = filterStreamError(localHttpURLConnection, k);
      SYHttpResponse localSYHttpResponse4 = new SYHttpResponse(localMap, HttpUtil.byteArrayFromInputStream(localInputStream));
      s.b(localInputStream);
      localHttpURLConnection.disconnect();
      return localSYHttpResponse4;
      label701: label711: for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        localObject2 = localObject3;
        break;
      }
      localObject2 = str1;
    }
  }

  public SYHttpResponse syGet(String paramString, List<NameValue> paramList1, List<NameValue> paramList2)
    throws Exception
  {
    this.getTryNum = 0;
    if ((paramList2 != null) && (!paramList2.isEmpty()))
      paramString = paramString + s.a(paramList2, "UTF-8");
    return syGet2(paramString, paramList1);
  }

  public SYHttpResponse syPost(String paramString1, List<NameValue> paramList, String paramString2)
    throws Exception
  {
    this.postTryNum = 0;
    return syPost2(paramString1, paramList, paramString2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpUrlImpl
 * JD-Core Version:    0.6.0
 */