package cn.suanya.service;

import cn.suanya.common.a.i;
import cn.suanya.common.a.l;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.net.HttpUrlImpl;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.domain.Request;
import cn.suanya.domain.Response;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SYService
{
  static int serviceUrlIndex = 0;
  private static SYService syService;
  protected SYApplication app = SYApplication.app;
  protected IHttpClient c;

  public SYService()
  {
    try
    {
      this.c = new HttpUrlImpl(10000, 10000);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static SYService getInstance()
  {
    if (syService == null)
      syService = new SYService();
    return syService;
  }

  public void asyncDebug(LogInfo paramLogInfo)
  {
    if (this.app.isDebug())
      asyncLog(paramLogInfo);
  }

  public void asyncDebug(String paramString, Exception paramException)
  {
    asyncDebug(new LogInfo(paramString, paramException));
  }

  public void asyncDebug(String paramString1, String paramString2)
  {
    asyncDebug(new LogInfo(paramString1, paramString2));
  }

  public void asyncLog(LogInfo paramLogInfo)
  {
    new Thread(new Runnable(paramLogInfo)
    {
      public void run()
      {
        try
        {
          SYService.this.log(this.val$logInfo);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }).start();
  }

  public void debug(LogInfo paramLogInfo)
  {
    if (this.app.isDebug())
      log(paramLogInfo);
  }

  protected String executeOnservice(String paramString1, String paramString2)
    throws Exception
  {
    int i = 0;
    while (i < this.app.getserviceUrls().length)
    {
      if (serviceUrlIndex >= this.app.getserviceUrls().length)
        serviceUrlIndex = 0;
      String str1 = this.app.getserviceUrls()[serviceUrlIndex] + "/json/" + paramString1;
      try
      {
        String str2 = this.c.postStr(str1, null, paramString2);
        return str2;
      }
      catch (Exception localException)
      {
        serviceUrlIndex = 1 + serviceUrlIndex;
        n.b("executeOnservice-" + paramString1, localException);
        if (i == -1 + this.app.getserviceUrls().length)
          throw new m("", "网络链接失败！", localException);
        i++;
      }
    }
    return null;
  }

  protected JSONObject executeOnservice(String paramString, Map<String, Object> paramMap)
    throws Exception
  {
    return new JSONObject(executeOnservice(paramString, getReq(new JSONObject(paramMap)).toString()));
  }

  protected long getClientId()
  {
    if (this.app.launchInfo == null)
      return 0L;
    return this.app.launchInfo.optLong(l.client_id, 0L);
  }

  protected <T> Request<T> getReQ(T paramT)
  {
    Request localRequest = new Request(paramT);
    localRequest.setClientId(getClientId());
    localRequest.setEmei(this.app.getEmei());
    localRequest.setConfigVersion(this.app.getConfigVersion());
    localRequest.setClientVersion(this.app.getVersionName());
    return localRequest;
  }

  protected JSONObject getReq(JSONObject paramJSONObject)
    throws JSONException
  {
    SYApplication localSYApplication = SYApplication.app;
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("emei", localSYApplication.getEmei());
    localJSONObject.put("clientId", localSYApplication.launchInfo.optLong("clientId", 0L));
    localJSONObject.put("data", paramJSONObject);
    return localJSONObject;
  }

  public Response<Long> log(LogInfo paramLogInfo)
  {
    int i = this.app.launchInfo.optInt("logMark", 1);
    if (getClientId() % i != 0L)
      return new Response();
    try
    {
      String str = executeOnservice("log", i.a(getReQ(paramLogInfo), new TypeToken()
      {
      }
      .getType()));
      if (str == null)
        return null;
      Response localResponse = (Response)i.a(str, new TypeToken()
      {
      }
      .getType());
      return localResponse;
    }
    catch (Exception localException)
    {
      n.c("Log-" + paramLogInfo.getMethod() + "|" + paramLogInfo.getContent());
    }
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.service.SYService
 * JD-Core Version:    0.6.0
 */