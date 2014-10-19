package com.baidu.mapapi.search;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.HashMap;

public class a
{
  static Context a;
  static HashMap<String, SoftReference<j>> b = new HashMap();
  public static boolean c = false;
  public static int d = 4;
  public static boolean e = false;
  public static byte f = 0;
  public static String g = "10.0.0.200";
  public static int h = 80;

  public static HttpURLConnection a(String paramString)
    throws IOException
  {
    if (!c)
    {
      b();
      if (!c)
        return null;
    }
    if (e)
    {
      int i = paramString.indexOf('/', 7);
      String str1;
      if (i < 0)
        str1 = paramString.substring(7);
      for (String str2 = ""; f == 1; str2 = paramString.substring(i))
      {
        java.net.Proxy localProxy = new java.net.Proxy(Proxy.Type.HTTP, new InetSocketAddress(g, 80));
        return (HttpURLConnection)new URL(paramString).openConnection(localProxy);
        str1 = paramString.substring(7, i);
      }
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL("http://" + g + str2).openConnection();
      localHttpURLConnection.setRequestProperty("X-Online-Host", str1);
      return localHttpURLConnection;
    }
    return (HttpURLConnection)new URL(paramString).openConnection();
  }

  public static void a()
  {
    a = null;
    b.clear();
  }

  public static void a(int paramInt1, int paramInt2, String paramString, a parama)
  {
    if ((paramString == null) || (!paramString.startsWith("http://")))
      return;
    new b(paramString, parama, paramInt1, paramInt2).start();
  }

  public static void a(Context paramContext)
  {
    a = paramContext;
  }

  public static void a(NetworkInfo paramNetworkInfo, boolean paramBoolean)
  {
    c = paramBoolean;
    String str1;
    try
    {
      if (paramNetworkInfo.getType() == 1)
      {
        d = 4;
        e = false;
        return;
      }
      str1 = paramNetworkInfo.getExtraInfo();
      if (str1 != null)
        break label107;
      d = 0;
      g = android.net.Proxy.getDefaultHost();
      h = android.net.Proxy.getDefaultPort();
      if ((g == null) || ("".equals(g)))
        break label98;
      d = 2;
      e = true;
      if ("10.0.0.200".equals(g))
      {
        f = 1;
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    f = 0;
    return;
    label98: d = 1;
    e = false;
    return;
    label107: String str2 = str1.toLowerCase().trim();
    if ((str2.startsWith("cmwap")) || (str2.startsWith("uniwap")) || (str2.startsWith("3gwap")))
    {
      d = 2;
      e = true;
      f = 0;
      g = "10.0.0.172";
      return;
    }
    if (str2.startsWith("ctwap"))
    {
      d = 2;
      e = true;
      f = 1;
      g = "10.0.0.200";
      return;
    }
    if ((str2.startsWith("cmnet")) || (str2.startsWith("uninet")) || (str2.startsWith("ctnet")) || (str2.startsWith("3gnet")))
    {
      d = 1;
      e = false;
    }
  }

  public static void b()
  {
    Context localContext = a;
    ConnectivityManager localConnectivityManager = null;
    if (localContext != null)
      localConnectivityManager = (ConnectivityManager)a.getSystemService("connectivity");
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        a(localNetworkInfo, localNetworkInfo.isConnected());
        return;
      }
      c = false;
      return;
    }
    c = false;
  }

  public static abstract interface a
  {
    public abstract void onError(int paramInt1, int paramInt2, String paramString, Object paramObject);

    public abstract void onOk(int paramInt1, int paramInt2, String paramString, Object paramObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.a
 * JD-Core Version:    0.6.0
 */