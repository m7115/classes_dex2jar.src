package com.yipiao.service;

import cn.suanya.common.a.n;
import cn.suanya.common.a.u;
import cn.suanya.common.net.HttpUrlAndOkImpl;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.Http;
import cn.suanya.rule.bean.SyContext;
import cn.suanya.rule.s;
import com.example.pathview.util.TimeUtil;
import com.yipiao.Config;
import com.yipiao.HcFunction;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.bean.BookResult;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.FligthPrice;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.ObjHolder;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainPrice;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.bean.UserInfo;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.yipiao.bean.Train;>;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class HuocheBase
  implements Huoche
{
  protected YipiaoApplication app = (YipiaoApplication)YipiaoApplication.app;
  protected Map<String, ObjHolder> bookHolder = new HashMap();
  protected Context glob = null;
  public IHttpClient httpClient;
  public int logined;
  private ChainQuery perCq = null;
  private List<FligthPrice> perFligthPrices = null;
  public s rule;

  private boolean checkTrainType(Train paramTrain, NoteList paramNoteList)
  {
    String str1 = paramTrain.getCode().substring(0, 1).toUpperCase(Locale.getDefault());
    String str2 = paramNoteList.linkCode(",");
    String str3 = Config.getInstance().trainTypeSimples.linkCode(",");
    if (str2.indexOf(str1) >= 0);
    do
    {
      return true;
      if (str3.indexOf(str1) >= 0)
        return false;
    }
    while (str2.indexOf("QT") >= 0);
    return false;
  }

  private s getRule()
  {
    return this.rule;
  }

  public Context abcNetPay1(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    return null;
  }

  public Context abcNetPay2(String paramString)
    throws Exception
  {
    return null;
  }

  public SYSignView.MulImage abcSign(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public Context abcWebPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public void addPassenger(UserInfo paramUserInfo)
    throws Exception
  {
  }

  public JSONArray advanceQueryFirst(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("advanceQueryFirst", getG(), localContext);
    JSONArray localJSONArray = (JSONArray)localContext.get("result");
    if (localJSONArray == null)
      localJSONArray = new JSONArray();
    return localJSONArray;
  }

  public List<TrainStationInfo> arrStationInfo(Train paramTrain)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("train", paramTrain);
    execRule("arrStationInfo", getG(), localContext);
    return (List)localContext.get("stationList");
  }

  public String autoBook(MonitorInfo paramMonitorInfo, List<Train> paramList, List<UserInfo> paramList1)
    throws Exception
  {
    throw new u("该引擎不支持自动抢票");
  }

  public boolean autoLogin()
    throws Exception
  {
    return false;
  }

  public BookResult book(Train paramTrain, ChainQuery paramChainQuery)
    throws Exception
  {
    return null;
  }

  public void cancelOrder(String paramString1, String paramString2, OrderResult paramOrderResult)
    throws Exception
  {
  }

  public Context ccbWebPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public void changePwd(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
  }

  public String cmbCreditCardPay(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    return null;
  }

  public String cmbNetPay(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    return null;
  }

  public SYSignView.MulImage cmbPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public Context cmbPayPreHtml5(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public String confirmRefundTicket(OrderItem paramOrderItem)
    throws Exception
  {
    return null;
  }

  public void confirmResign(String paramString)
    throws Exception
  {
  }

  protected IHttpClient createHttpClient()
    throws Exception
  {
    return new HttpUrlAndOkImpl(30000, 30000);
  }

  protected SYSignView.MulImage createSign(InputStream paramInputStream, Context paramContext)
    throws Exception
  {
    if (paramInputStream == null)
      return null;
    return new SYSignView.MulImage(paramInputStream, paramContext.getStr("imageType"));
  }

  protected SYSignView.MulImage createSign(String paramString)
    throws Exception
  {
    SyContext localSyContext = execRule(paramString, getG());
    return createSign(localSyContext.getR().getStream(), localSyContext.getL());
  }

  public void deletePassenger(UserInfo paramUserInfo)
    throws Exception
  {
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

  protected List<Train> filterPiao(ChainQuery paramChainQuery, List<Train> paramList)
  {
    String str1 = paramChainQuery.getTimeRangBegin();
    String str2 = paramChainQuery.getTimeRangEnd();
    NoteList localNoteList = paramChainQuery.getTrainTypes();
    int i = 0;
    if (i < paramList.size())
    {
      Train localTrain = (Train)paramList.get(i);
      if (!TimeUtil.isBeweenTime(localTrain.getFromTime(), str1, str2))
      {
        paramList.remove(localTrain);
        i--;
      }
      while (true)
      {
        i++;
        break;
        if (!checkTrainType(localTrain, localNoteList))
        {
          paramList.remove(localTrain);
          i--;
          continue;
        }
        if ((paramChainQuery.getTrainNo() == null) || (paramChainQuery.getTrainNo().length() <= 0) || (paramChainQuery.getTrainNo().equals(localTrain.getCode())))
          continue;
        paramList.remove(localTrain);
        i--;
      }
    }
    return paramList;
  }

  protected List<UserInfo> findUserFromOrder(List<OrderItem> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem = (OrderItem)localIterator.next();
      UserInfo localUserInfo = new UserInfo();
      localUserInfo.setSeatType(localOrderItem.getSeatTypeCode());
      localUserInfo.setTickType(localOrderItem.getTickTypeCode());
      localUserInfo.setName(localOrderItem.getName());
      localUserInfo.setCardType(localOrderItem.getIdTypeCode());
      localUserInfo.setCardId(localOrderItem.getIdNo());
      localArrayList.add(localUserInfo);
    }
    return localArrayList;
  }

  protected IHttpClient getC()
  {
    if (this.httpClient == null);
    try
    {
      this.httpClient = createHttpClient();
      loadCookie();
      label19: return this.httpClient;
    }
    catch (Exception localException)
    {
      break label19;
    }
  }

  public SysUserInfo getCurrentUserDetail()
    throws Exception
  {
    return null;
  }

  public List<FligthPrice> getFlightPrice(ChainQuery paramChainQuery)
    throws Exception
  {
    if ((this.perCq != null) && (paramChainQuery.getArr().equals(this.perCq.getArr())) && (paramChainQuery.getOrg().equals(this.perCq.getOrg())) && (paramChainQuery.getLeaveDate().equals(this.perCq.getLeaveDate())))
      return this.perFligthPrices;
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("flightPrice", getG(), localContext);
    List localList = (List)localContext.get("flightPrices");
    Collections.sort(localList);
    this.perCq = paramChainQuery.clone();
    this.perFligthPrices = localList;
    return localList;
  }

  protected Context getG()
  {
    if (this.glob != null)
      return this.glob;
    return this.app.glob;
  }

  public UserInfo getPassengerDetail(UserInfo paramUserInfo)
    throws Exception
  {
    return null;
  }

  public NoteList getSchool(String paramString)
    throws Exception
  {
    return YipiaoService.getInstance().provinceUniversity(paramString);
  }

  public TrainPrice getTicketPrice(ChainQuery paramChainQuery, List<Train> paramList)
    throws Exception
  {
    return null;
  }

  public boolean init(int paramInt)
    throws Exception
  {
    return false;
  }

  public String initRefundTicket(OrderItem paramOrderItem)
    throws Exception
  {
    return null;
  }

  public boolean isLogined()
    throws Exception
  {
    return false;
  }

  public long laterEpay(OrderResult paramOrderResult)
    throws Exception
  {
    return 0L;
  }

  protected void loadCookie()
  {
  }

  public LoginUser login(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    return null;
  }

  public void loginOut()
    throws Exception
  {
    this.logined = 2;
  }

  public SYSignView.MulImage loginSign()
    throws Exception
  {
    return null;
  }

  public List<OrderResult> myHistoryOrder()
    throws Exception
  {
    return null;
  }

  public SYSignView.MulImage orderSign()
    throws Exception
  {
    return null;
  }

  public SYSignView.MulImage orderSignForQianPiao()
    throws Exception
  {
    return null;
  }

  public NoteList queryCity(String paramString)
    throws Exception
  {
    return null;
  }

  public List<Train> queryForAdvanced(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryTicketForAdvanced", getG(), localContext);
    Object localObject = (List)localContext.get("trainList");
    if (localObject == null)
      localObject = new ArrayList();
    filterPiao(paramChainQuery, (List)localObject);
    return (List<Train>)localObject;
  }

  public List<UserInfo> queryPassenger()
    throws Exception
  {
    return null;
  }

  public List<Train> queryPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryTicket", getG(), localContext);
    Object localObject = (List)localContext.get("trainList");
    if (localObject == null)
      localObject = new ArrayList();
    return (List<Train>)localObject;
  }

  public List<Train> queryPiaoCommon(ChainQuery paramChainQuery)
    throws Exception
  {
    return queryPiao(paramChainQuery);
  }

  public List<Train> queryPiaoForMonitor(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryTicketForMonitor", getG(), localContext);
    Object localObject = (List)localContext.get("trainList");
    if (localObject == null)
      localObject = new ArrayList();
    return (List<Train>)localObject;
  }

  public List<Train> queryPiaoNoLogin(ChainQuery paramChainQuery)
    throws Exception
  {
    return queryPiao(paramChainQuery);
  }

  public void reSendEmail()
    throws Exception
  {
  }

  public Train refreshTrain(ChainQuery paramChainQuery, Train paramTrain)
    throws Exception
  {
    long l = this.app.launchInfo.optLong("validQueryTime", 290000L);
    if (System.currentTimeMillis() - paramTrain.getQueryTime() > l)
    {
      Iterator localIterator = queryPiao(paramChainQuery).iterator();
      while (localIterator.hasNext())
      {
        Train localTrain = (Train)localIterator.next();
        if (!paramTrain.equals(localTrain))
          continue;
        paramTrain.setYpInfo(localTrain.getYpInfo());
        paramTrain.setQueryTime(localTrain.getQueryTime());
        paramTrain.setSeats(localTrain.getSeats());
        paramTrain.setData(localTrain.getData());
        return localTrain;
      }
    }
    return paramTrain;
  }

  public boolean registerCheckName(String paramString)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("user_name", paramString);
    execRule("registerCheckName", getG(), localContext);
    return ((Boolean)localContext.get("result")).booleanValue();
  }

  public SYSignView.MulImage registerSign()
    throws Exception
  {
    return createSign("registerSign");
  }

  public BookResult resignBook(Train paramTrain, ChainQuery paramChainQuery, ArrayList<OrderItem> paramArrayList)
    throws Exception
  {
    return null;
  }

  public void resignOrderN(OrderResult paramOrderResult)
    throws Exception
  {
  }

  public SYSignView.MulImage resignOrderSign()
    throws Exception
  {
    return null;
  }

  public void resignOrderT(OrderResult paramOrderResult)
    throws Exception
  {
  }

  public List<Train> resignQueryPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    return null;
  }

  public void resignReady(ArrayList<OrderItem> paramArrayList)
    throws Exception
  {
  }

  public String resignSubmitOrder(String paramString, List<UserInfo> paramList, Train paramTrain, ArrayList<OrderItem> paramArrayList, ChainQuery paramChainQuery)
    throws Exception
  {
    return null;
  }

  public void ruleInit()
  {
    this.rule.a(new HcFunction());
    runRule("init");
  }

  public SyContext runRule(String paramString)
  {
    try
    {
      SyContext localSyContext = execRule(paramString, getG());
      return localSyContext;
    }
    catch (Exception localException)
    {
      n.b(localException.getMessage());
    }
    return null;
  }

  public void saveLoginCookie()
  {
  }

  public void saveLoginCookieLater()
  {
  }

  public void savePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception
  {
  }

  public String submitOrder(ChainQuery paramChainQuery, String paramString, List<UserInfo> paramList, Train paramTrain)
    throws Exception
  {
    return null;
  }

  public void submitRegister(Map<String, String> paramMap)
    throws Exception
  {
    getG().put("regInfo", paramMap);
    execRule("runRegister", getG());
  }

  public OrderResult uncompleteOrder()
    throws Exception
  {
    return null;
  }

  public Context unionPay(OrderResult paramOrderResult, String paramString)
    throws Exception
  {
    return null;
  }

  public void updateCurrentUser(SysUserInfo paramSysUserInfo1, SysUserInfo paramSysUserInfo2, String paramString)
    throws Exception
  {
  }

  public void updatePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception
  {
  }

  public void uploadUser(UserInfo paramUserInfo)
    throws Exception
  {
  }

  public SYSignView.MulImage userinfoUpdateSign()
    throws Exception
  {
    return null;
  }

  public String waitOrder()
    throws Exception
  {
    return null;
  }

  public String waitOrderTimeResign()
    throws Exception
  {
    return waitOrder();
  }

  public Context webPay(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public String zfbClientPayUrl(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("zfbClientPayUrl", getG(), localContext);
    return localContext.getStr("clientUrl");
  }

  public Context zfbWebPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    return null;
  }

  public Context zwdQuery(String paramString1, TrainStationInfo paramTrainStationInfo, String paramString2, boolean paramBoolean)
    throws Exception
  {
    Context localContext = new Context();
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      localContext.put("type", Integer.valueOf(i));
      localContext.put("trainCode", paramString1);
      localContext.put("stationName", paramTrainStationInfo.getName());
      localContext.put("randCode", paramString2);
      localContext.put("stationEn", URLEncoder.encode(paramTrainStationInfo.getName(), "UTF8").replace("%", "-"));
      execRule("zwdQuery", getG(), localContext);
      return localContext;
    }
  }

  public SYSignView.MulImage zwdQuerySign()
    throws Exception
  {
    return createSign("zwdQuerySign");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.HuocheBase
 * JD-Core Version:    0.6.0
 */