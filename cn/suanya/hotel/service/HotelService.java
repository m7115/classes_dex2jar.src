package cn.suanya.hotel.service;

import cn.suanya.common.a.i;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.a.o;
import cn.suanya.common.bean.NameValue;
import cn.suanya.common.net.ClientInfo;
import cn.suanya.common.net.HttpUrlImpl;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.domain.Request;
import cn.suanya.domain.Response;
import cn.suanya.hotel.HTConstants;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.HotelDetail;
import cn.suanya.hotel.domain.HotelInfo;
import cn.suanya.hotel.domain.RoomStatus;
import cn.suanya.hotel.util.CtripUnPack;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.SyContext;
import cn.suanya.rule.s;
import cn.suanya.service.RuleService;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HotelService
{
  private static HotelService hotelService;
  private static List<NameValue> postHeader;
  private static int serviceUrlIndex = 0;
  private SYApplication app;
  private IHttpClient c;
  private s rule;

  static
  {
    postHeader = new ArrayList();
    postHeader.add(new NameValue("Content-Type", "application/json; charset=UTF-8"));
  }

  private HotelService()
  {
    try
    {
      this.c = new HttpUrlImpl(20000, 20000);
      this.app = SYApplication.app;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private JSONObject executeOnservice(String paramString1, String paramString2)
    throws Exception
  {
    int i = serviceUrlIndex;
    int j = 0;
    while (j < HTConstants.serviceUrls.length)
    {
      if (i >= HTConstants.serviceUrls.length)
        i = 0;
      String str1 = HTConstants.serviceUrls[i] + "/service/" + paramString1;
      try
      {
        String str2 = this.c.postStr(str1, postHeader, paramString2);
        serviceUrlIndex = i;
        JSONObject localJSONObject = new JSONObject(str2);
        return localJSONObject;
      }
      catch (Exception localException)
      {
        i++;
        n.b("executeOnservice-" + paramString1, localException);
        if (j == -1 + HTConstants.serviceUrls.length)
          throw localException;
        j++;
      }
    }
    return null;
  }

  private JSONObject executeOnservice(String paramString, Map<String, Object> paramMap)
    throws Exception
  {
    return executeOnservice(paramString, getReq(new JSONObject(paramMap)).toString());
  }

  private Context getG()
  {
    return this.app.glob;
  }

  private <T> Request<T> getReQ(T paramT)
  {
    SYApplication localSYApplication = SYApplication.app;
    Request localRequest = new Request(paramT);
    localRequest.setClientId(localSYApplication.launchInfo.optLong("clientId", 0L));
    localRequest.setEmei(localSYApplication.getEmei());
    localRequest.setConfigVersion(localSYApplication.launchInfo.optInt(HTConstants.conf_version, 0));
    localRequest.setClientVersion(localSYApplication.getVersionName());
    return localRequest;
  }

  private JSONObject getReq(JSONObject paramJSONObject)
    throws JSONException
  {
    SYApplication localSYApplication = SYApplication.app;
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("emei", "emei");
    localJSONObject.put("clientId", localSYApplication.launchInfo.optLong("clientId", 0L));
    localJSONObject.put("data", paramJSONObject);
    return localJSONObject;
  }

  private s getRule()
  {
    return this.rule;
  }

  public static HotelService instance()
  {
    if (hotelService == null)
      hotelService = new HotelService();
    return hotelService;
  }

  public void asyncLog(LogInfo paramLogInfo)
  {
    new Thread(new Runnable(paramLogInfo)
    {
      public void run()
      {
        try
        {
          HotelService.this.log(this.val$logInfo);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    }).start();
  }

  public HotelDetail ctripHotelDetail(FindHotelReq paramFindHotelReq, HotelInfo paramHotelInfo)
    throws Exception
  {
    HotelDetail localHotelDetail = new HotelDetail(paramHotelInfo);
    Context localContext = new Context();
    localContext.put("hotelReq", paramFindHotelReq);
    localContext.put("hotel", paramHotelInfo);
    SyContext localSyContext = execRule("ctripHotelDetail", getG(), localContext);
    while (true)
    {
      try
      {
        JSONArray localJSONArray = ((JSONObject)CtripUnPack.unPack((JSONObject)localSyContext.getL().get("hotelDetail"))).getJSONArray("Data").getJSONObject(0).getJSONArray("RoomDetailList");
        if (localJSONArray != null)
          break label303;
        return localHotelDetail;
        if (i < localJSONArray.length())
        {
          RoomStatus localRoomStatus = new RoomStatus();
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          localRoomStatus.setSourceName("携程旅行网");
          localRoomStatus.setRoomType(localJSONObject.optString("RoomName"));
          String[] arrayOfString = localJSONObject.optString("RoomUrl").split(",");
          if (arrayOfString.length <= 0)
            continue;
          localRoomStatus.setHeadImg(arrayOfString[0]);
          localRoomStatus.setPriceDesc(localJSONObject.optString("AvgPrice"));
          localRoomStatus.setPriceNow(localJSONObject.optDouble("AvgPrice"));
          if (localJSONObject.optInt("IsFan") != 1)
            continue;
          localRoomStatus.setSalesDesc("返" + localJSONObject.optString("TicketCalcValue"));
          if (localJSONObject.optInt("IsCanBook") != 1)
            continue;
          boolean bool = true;
          localRoomStatus.setCanBook(bool);
          localHotelDetail.getRoomStatus().add(localRoomStatus);
          i++;
          continue;
          bool = false;
          continue;
        }
      }
      catch (Exception localException)
      {
        throw new m("请求携程数据失败！");
      }
      return localHotelDetail;
      label303: int i = 0;
    }
  }

  public HotelDetail elongHotelDetail(FindHotelReq paramFindHotelReq, HotelInfo paramHotelInfo)
    throws Exception
  {
    HotelDetail localHotelDetail = new HotelDetail(paramHotelInfo);
    Context localContext = new Context();
    localContext.put("hotelReq", paramFindHotelReq);
    localContext.put("hotel", paramHotelInfo);
    List localList = (List)execRule("elongHotelDetail", getG(), localContext).getL().get("roomStatus");
    if (localList == null)
      return localHotelDetail;
    Iterator localIterator = localList.iterator();
    while (true)
    {
      o localo;
      RoomStatus localRoomStatus;
      String str1;
      if (localIterator.hasNext())
      {
        localo = (o)localIterator.next();
        localRoomStatus = new RoomStatus();
        localRoomStatus.setSourceName("艺龙旅行网");
        localRoomStatus.setRoomType(localo.b("name"));
        localRoomStatus.setHeadImg("head");
        str1 = localo.b("price");
        localRoomStatus.setPriceDesc(str1);
      }
      try
      {
        localRoomStatus.setPriceNow(Double.parseDouble(str1));
        label166: String str2 = localo.b("rPrice");
        if ((str2 != null) && (str2.length() > 0))
          localRoomStatus.setSalesDesc("返" + localo.b("rPrice"));
        while (true)
        {
          localRoomStatus.setCanBook(localo.a("status"));
          localHotelDetail.getRoomStatus().add(localRoomStatus);
          break;
          localRoomStatus.setSalesDesc("");
        }
        return localHotelDetail;
      }
      catch (Exception localException)
      {
        break label166;
      }
    }
  }

  protected SyContext execRule(String paramString, Context paramContext)
    throws Exception
  {
    return getRule().a(paramString, paramContext, null, getC());
  }

  protected SyContext execRule(String paramString, Context paramContext1, Context paramContext2)
    throws Exception
  {
    return getRule().a(paramString, paramContext1, paramContext2, getC());
  }

  protected SyContext execRule(String paramString, SyContext paramSyContext)
    throws Exception
  {
    return getRule().a(paramString, paramSyContext.getG(), paramSyContext.getL(), getC());
  }

  public Response<List<HotelInfo>> findHotelList(FindHotelReq paramFindHotelReq)
    throws Exception
  {
    JSONObject localJSONObject = executeOnservice("findHotel", paramFindHotelReq.toMap());
    Response localResponse = new Response(localJSONObject);
    localResponse.setStatus(localJSONObject.optInt("status"));
    localResponse.setMessage(localJSONObject.optString("message"));
    if (localResponse.getStatus() != 0)
      throw new m(localResponse.getMessage());
    JSONArray localJSONArray = localJSONObject.optJSONArray("data");
    if (localJSONArray == null)
      throw new m("没有找到合适的酒店！");
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(new HotelInfo(localJSONArray.getJSONObject(i)));
    localResponse.setData(localArrayList);
    return localResponse;
  }

  protected IHttpClient getC()
  {
    if (this.c == null);
    try
    {
      this.c = new HttpUrlImpl(30000, 30000);
      label24: return this.c;
    }
    catch (Exception localException)
    {
      break label24;
    }
  }

  public JSONObject launch(ClientInfo paramClientInfo)
    throws Exception
  {
    return executeOnservice("launch", i.a(getReQ(paramClientInfo), new TypeToken()
    {
    }
    .getType()));
  }

  public Response<Long> log(LogInfo paramLogInfo)
  {
    try
    {
      JSONObject localJSONObject = executeOnservice("log", i.a(getReQ(paramLogInfo), new TypeToken()
      {
      }
      .getType()));
      if (localJSONObject == null);
      return null;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public void ruleInit()
  {
    String str = this.app.launchInfo.optString("ruleVersionHotel", HTConstants.ruleVersionAssest);
    this.rule = RuleService.getInstance().loadRule(str, HTConstants.ruleVersionAssest);
  }

  public HotelDetail tongchengHotelDetail(FindHotelReq paramFindHotelReq, HotelInfo paramHotelInfo)
    throws Exception
  {
    HotelDetail localHotelDetail = new HotelDetail(paramHotelInfo);
    Context localContext = new Context();
    localContext.put("hotelReq", paramFindHotelReq);
    localContext.put("hotel", paramHotelInfo);
    execRule("tongchengHotelDetail", getG(), localContext);
    Object localObject = (List)localContext.get("roomStatus");
    if (localObject == null)
      localObject = new ArrayList();
    localHotelDetail.setRoomStatus((List)localObject);
    return (HotelDetail)localHotelDetail;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.service.HotelService
 * JD-Core Version:    0.6.0
 */