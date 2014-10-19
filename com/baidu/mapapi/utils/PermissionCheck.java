package com.baidu.mapapi.utils;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.baidu.platform.comapi.d.a;
import com.baidu.platform.comapi.d.c;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

public class PermissionCheck
{
  public static Context a;
  private static Map<String, String> b;

  public static void InitParam(String paramString1, String paramString2, String paramString3)
  {
    if (b == null)
      b = new HashMap();
    Bundle localBundle = c.c();
    if ((paramString1 == null) || (paramString1.length() == 0))
      paramString1 = "-1";
    Log.i("auth info", "ak:  " + paramString1);
    b.put("ak", paramString1);
    b.put("from", "lbs_androidsdk");
    b.put("mcode", paramString3);
    Log.i("auth info", "mcode:  " + paramString3);
    b.put("mb", localBundle.getString("mb"));
    b.put("os", localBundle.getString("os"));
    b.put("sv", localBundle.getString("sv"));
    b.put("imt", "1");
    b.put("im", localBundle.getString("im"));
    b.put("imrand", localBundle.getString("imrand"));
    b.put("net", localBundle.getString("net"));
    b.put("cpu", localBundle.getString("cpu"));
    b.put("glr", localBundle.getString("glr"));
    b.put("glv", localBundle.getString("glv"));
    b.put("resid", localBundle.getString("resid"));
    b.put("appid", "-1");
    b.put("ver", "1");
    Map localMap1 = b;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(localBundle.getInt("screen_x"));
    arrayOfObject1[1] = Integer.valueOf(localBundle.getInt("screen_y"));
    localMap1.put("screen", String.format("(%d,%d)", arrayOfObject1));
    Map localMap2 = b;
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = Integer.valueOf(localBundle.getInt("dpi_x"));
    arrayOfObject2[1] = Integer.valueOf(localBundle.getInt("dpi_y"));
    localMap2.put("dpi", String.format("(%d,%d)", arrayOfObject2));
    b.put("pcn", localBundle.getString("pcn"));
    b.put("name", paramString2);
  }

  private static HttpClient a()
  {
    try
    {
      KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      localKeyStore.load(null, null);
      f localf = new f(localKeyStore);
      localf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
      a.a(a, localBasicHttpParams);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", localf, 443));
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      return localDefaultHttpClient;
    }
    catch (Exception localException)
    {
    }
    return new DefaultHttpClient();
  }

  public static boolean check()
  {
    return com.baidu.platform.comjni.permissioncheck.PermissionCheck.check();
  }

  // ERROR //
  public static int permissionCheck()
  {
    // Byte code:
    //   0: getstatic 17	com/baidu/mapapi/utils/PermissionCheck:b	Ljava/util/Map;
    //   3: ifnonnull +5 -> 8
    //   6: iconst_1
    //   7: ireturn
    //   8: invokestatic 240	com/baidu/mapapi/utils/PermissionCheck:a	()Lorg/apache/http/client/HttpClient;
    //   11: astore_0
    //   12: new 242	org/apache/http/client/methods/HttpPost
    //   15: dup
    //   16: ldc 244
    //   18: invokespecial 247	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   21: astore_1
    //   22: new 249	java/util/ArrayList
    //   25: dup
    //   26: invokespecial 250	java/util/ArrayList:<init>	()V
    //   29: astore_2
    //   30: getstatic 17	com/baidu/mapapi/utils/PermissionCheck:b	Ljava/util/Map;
    //   33: invokeinterface 254 1 0
    //   38: invokeinterface 260 1 0
    //   43: astore_3
    //   44: aload_3
    //   45: invokeinterface 265 1 0
    //   50: ifeq +51 -> 101
    //   53: aload_3
    //   54: invokeinterface 269 1 0
    //   59: checkcast 271	java/util/Map$Entry
    //   62: astore 12
    //   64: aload_2
    //   65: new 273	org/apache/http/message/BasicNameValuePair
    //   68: dup
    //   69: aload 12
    //   71: invokeinterface 276 1 0
    //   76: checkcast 28	java/lang/String
    //   79: aload 12
    //   81: invokeinterface 279 1 0
    //   86: checkcast 28	java/lang/String
    //   89: invokespecial 282	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: invokeinterface 288 2 0
    //   97: pop
    //   98: goto -54 -> 44
    //   101: aload_1
    //   102: new 290	org/apache/http/client/entity/UrlEncodedFormEntity
    //   105: dup
    //   106: aload_2
    //   107: ldc 185
    //   109: invokespecial 293	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   112: invokevirtual 297	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   115: aload_0
    //   116: aload_1
    //   117: invokeinterface 303 2 0
    //   122: invokeinterface 309 1 0
    //   127: astore 7
    //   129: aload 7
    //   131: ifnull +87 -> 218
    //   134: aload 7
    //   136: invokeinterface 315 1 0
    //   141: pop2
    //   142: aload 7
    //   144: ldc 185
    //   146: invokestatic 320	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   149: astore 10
    //   151: aload 10
    //   153: invokestatic 323	com/baidu/mapapi/utils/PermissionCheck:praseResult	(Ljava/lang/String;)I
    //   156: istore 11
    //   158: aload_0
    //   159: invokeinterface 327 1 0
    //   164: invokeinterface 332 1 0
    //   169: iload 11
    //   171: ireturn
    //   172: astore 4
    //   174: aload 4
    //   176: invokevirtual 335	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   179: goto -64 -> 115
    //   182: astore 6
    //   184: aload 6
    //   186: invokevirtual 336	java/lang/Exception:printStackTrace	()V
    //   189: aload_0
    //   190: invokeinterface 327 1 0
    //   195: invokeinterface 332 1 0
    //   200: iconst_4
    //   201: ireturn
    //   202: astore 5
    //   204: aload_0
    //   205: invokeinterface 327 1 0
    //   210: invokeinterface 332 1 0
    //   215: aload 5
    //   217: athrow
    //   218: ldc_w 338
    //   221: astore 10
    //   223: goto -72 -> 151
    //
    // Exception table:
    //   from	to	target	type
    //   101	115	172	java/io/UnsupportedEncodingException
    //   115	129	182	java/lang/Exception
    //   134	151	182	java/lang/Exception
    //   151	158	182	java/lang/Exception
    //   115	129	202	finally
    //   134	151	202	finally
    //   151	158	202	finally
    //   184	189	202	finally
  }

  public static int praseResult(String paramString)
  {
    int i = -1;
    if ((paramString == null) || (paramString.length() == 0))
      return 3;
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      j = localJSONObject.optInt("status");
      if ((j != 1) && (j != 2) && (j != 4))
        if (j == 0)
        {
          if (localJSONObject.has("uid"))
          {
            k = localJSONObject.optInt("uid");
            if (localJSONObject.has("appid"))
              i = localJSONObject.optInt("appid");
            c.b("" + k, "" + i);
            return j;
          }
        }
        else
        {
          Log.e("baidumapsdk", "Authentication Error,status: " + j + " message: " + localJSONObject.optString("message"));
          return j;
        }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        return 3;
        int k = i;
        continue;
        int j = 100;
      }
    }
    catch (JSONException localJSONException)
    {
    }
    return 3;
  }

  public static void setContext(Context paramContext)
  {
    a = paramContext;
  }

  public boolean check(String paramString)
  {
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.PermissionCheck
 * JD-Core Version:    0.6.0
 */