package cn.suanya.common.net;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager
  implements X509TrustManager
{
  X509TrustManager myJSSEX509TrustManager;

  public MyX509TrustManager()
    throws Exception
  {
    this(null);
  }

  public MyX509TrustManager(InputStream paramInputStream)
    throws Exception
  {
  }

  public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
  }

  public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
  }

  public X509Certificate[] getAcceptedIssuers()
  {
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.MyX509TrustManager
 * JD-Core Version:    0.6.0
 */