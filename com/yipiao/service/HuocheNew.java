package com.yipiao.service;

import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.net.Cookie;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.Http;
import cn.suanya.rule.bean.SyContext;
import cn.suanya.rule.s;
import cn.suanya.service.RuleService;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.HCUtil;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.bean.BookResult;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.ObjHolder;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.OrderResultNew;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainPrice;
import com.yipiao.bean.UserInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.yipiao.bean.Train;>;
import java.util.Map;
import org.json.JSONObject;

public class HuocheNew extends HuocheBase
{
  static HuocheNew hc;
  private long loginFresh = 0L;

  public HuocheNew()
  {
    getG().put("nHc", this);
  }

  private String autoBookByM(MonitorInfo paramMonitorInfo, List<Train> paramList, List<UserInfo> paramList1)
    throws Exception
  {
    HuocheMobile localHuocheMobile = HuocheMobile.getInstance();
    return localHuocheMobile.autoBook(paramMonitorInfo, localHuocheMobile.queryPiaoForMonitor(paramMonitorInfo.getCq()), paramList1);
  }

  public static HuocheNew getInstance()
  {
    if (hc == null)
      hc = new HuocheNew();
    return hc;
  }

  public Context abcNetPay1(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("mobileNo", paramString1);
    localContext.put("cardNo", paramString2);
    localContext.put("sign", paramString3);
    execRule("ABCCardPayMessageAct", getG(), localContext);
    return localContext;
  }

  public Context abcNetPay2(String paramString)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("verifycode", paramString);
    execRule("ABCConfirmPay", getG(), localContext);
    return localContext;
  }

  public SYSignView.MulImage abcSign(OrderResult paramOrderResult)
    throws Exception
  {
    return createSign("ABCSign");
  }

  public Context abcWebPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("abcWebPayPre", getG(), localContext);
    return localContext;
  }

  public void addPassenger(UserInfo paramUserInfo)
    throws Exception
  {
    savePassenger(paramUserInfo, paramUserInfo);
  }

  public String autoBook(MonitorInfo paramMonitorInfo, List<Train> paramList, List<UserInfo> paramList1)
    throws Exception
  {
    ChainQuery localChainQuery = paramMonitorInfo.getCq();
    if (paramMonitorInfo.isResign())
      paramList1 = findUserFromOrder(paramMonitorInfo.getOrders());
    if ((paramList1 == null) || (paramList1.isEmpty()))
      throw new m("没有设置监控购票人");
    Train localTrain1 = new Train();
    if (paramList == null)
      throw new m("没有合适的车次");
    Iterator localIterator1 = paramList.iterator();
    Train localTrain2;
    int j;
    while (localIterator1.hasNext())
    {
      localTrain2 = (Train)localIterator1.next();
      if ((paramMonitorInfo.getTrainList() != null) && (!paramMonitorInfo.getTrainList().isEmpty()) && (!paramMonitorInfo.getTrainList().contains(localTrain2.getCode())))
        continue;
      int i = localTrain2.getSeatNum(paramMonitorInfo.getSeatTypes());
      if (paramMonitorInfo.getSeatNum().intValue() > i)
        continue;
      Iterator localIterator2 = paramList1.iterator();
      while (localIterator2.hasNext())
      {
        UserInfo localUserInfo = (UserInfo)localIterator2.next();
        if (HCUtil.calPassengerSeat(localTrain2.getSeats(), localUserInfo, paramMonitorInfo))
          continue;
        j = 0;
        label204: if (j == 0)
          break label387;
      }
    }
    while (true)
    {
      if (localTrain2 == null)
        throw new m("没有合适的座位");
      autoLogin();
      book(localTrain2, localChainQuery);
      Context localContext = new Context();
      localContext.put("train", localTrain2);
      localContext.put("userList", paramList1);
      localContext.put("query", localChainQuery);
      try
      {
        if (paramMonitorInfo.isResign())
        {
          myHistoryOrder();
          resignReady(paramMonitorInfo.getOrders());
          resignBook(localTrain2, localChainQuery, paramMonitorInfo.getOrders());
          localContext.put("orders", paramMonitorInfo.getOrders());
          execRule("autoSubmitOrderResign", getG(), localContext);
          break label396;
        }
        book(localTrain2, localChainQuery);
        execRule("autoSubmitOrder", getG(), localContext);
      }
      catch (Exception localException)
      {
        if (m.a("103", localException))
          return localException.getMessage();
        throw localException;
      }
      j = 1;
      break label204;
      label387: break;
      localTrain2 = localTrain1;
    }
    label396: return "订单已经提交，请前往未支付订单查看";
  }

  public boolean autoLogin()
    throws Exception
  {
    monitorenter;
    LoginUser localLoginUser;
    try
    {
      localLoginUser = this.app.getUser();
      if (isLogined())
        return true;
      if (!localLoginUser.canLogin())
        throw new m("没有设置登陆帐号");
    }
    finally
    {
      monitorexit;
    }
    Context localContext = new Context();
    localContext.put("name", localLoginUser.getUserName());
    localContext.put("password", localLoginUser.getPassword());
    execRule("autoLogin", getG(), localContext);
    this.loginFresh = System.currentTimeMillis();
    this.logined = 1;
    monitorexit;
    return true;
  }

  public BookResult book(Train paramTrain, ChainQuery paramChainQuery)
    throws Exception
  {
    Train localTrain = refreshTrain(paramChainQuery, paramTrain);
    monitorenter;
    try
    {
      ObjHolder localObjHolder = (ObjHolder)this.bookHolder.get(localTrain.getKey());
      if ((localObjHolder != null) && (!localObjHolder.hasOutTime()))
      {
        n.b("book--" + localTrain.getKey());
        BookResult localBookResult2 = (BookResult)localObjHolder.getObj();
        return localBookResult2;
      }
      this.bookHolder.clear();
      Context localContext = new Context();
      localContext.put("train", localTrain);
      localContext.put("query", paramChainQuery);
      execRule("book", getG(), localContext);
      BookResult localBookResult1 = new BookResult();
      localBookResult1.setLeftTicketStr(getG().getStr("book_LeftTicketStr"));
      localBookResult1.setToken(getG().getStr("book_token"));
      localBookResult1.setTickets((List)localContext.get("bookPriceList"));
      this.bookHolder.put(localTrain.getKey(), new ObjHolder(localBookResult1, 200000L));
      saveLoginCookieLater();
      return localBookResult1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public BookResult bookForQianPiao(Train paramTrain, ChainQuery paramChainQuery)
    throws Exception
  {
    monitorenter;
    try
    {
      Context localContext = new Context();
      localContext.put("train", paramTrain);
      localContext.put("query", paramChainQuery);
      execRule("bookForQianPiao", getG(), localContext);
      BookResult localBookResult = new BookResult();
      localBookResult.setLeftTicketStr(getG().getStr("book_LeftTicketStr"));
      localBookResult.setToken(getG().getStr("book_token"));
      localBookResult.setTickets((List)localContext.get("bookPriceList"));
      return localBookResult;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void cancelOrder(String paramString1, String paramString2, OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("token", paramString1);
    localContext.put("sequenceNo", paramString2);
    localContext.put("orderResult", paramOrderResult);
    execRule("cancelOrder", getG(), localContext);
  }

  public Context ccbWebPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("ccbWebPayPre", getG(), localContext);
    return localContext;
  }

  public void changePwd(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    localContext.put("oldPwd", paramString2);
    localContext.put("newPwd", paramString3);
    execRule("changePwd", getG(), localContext);
  }

  public String cmbCreditCardPay(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("epay_cmb_MobileNo", paramString1);
    localContext.put("epay_cmb_CardNo", paramString2);
    localContext.put("epay_cmb_DynaCode", paramString3);
    execRule("cmbMobilePayProc_CreditCard", getG(), localContext);
    return (String)localContext.get("cmb_result_message");
  }

  public String cmbNetPay(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("epay_cmb_MobileNo", paramString1);
    localContext.put("epay_cmb_CardNo", paramString2);
    localContext.put("epay_cmb_DynaCode", paramString3);
    execRule("cmbMobilePayProc_NetPay", getG(), localContext);
    return (String)localContext.get("cmb_result_message");
  }

  public SYSignView.MulImage cmbPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    return createSign(execRule("cmbPayPre", getG(), localContext).getR().getStream(), localContext);
  }

  public Context cmbPayPreHtml5(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("cmbPayPreHtml5", getG(), localContext);
    return localContext;
  }

  public String confirmRefundTicket(OrderItem paramOrderItem)
    throws Exception
  {
    execRule("confirmRefundTicket", getG());
    return getG().getStr("confirmRefundTicket_msg");
  }

  public HuocheNew copyHc()
  {
    HuocheNew localHuocheNew = new HuocheNew();
    localHuocheNew.rule = this.rule;
    try
    {
      localHuocheNew.httpClient = createHttpClient();
      return localHuocheNew;
    }
    catch (Exception localException)
    {
    }
    return localHuocheNew;
  }

  public void deletePassenger(UserInfo paramUserInfo)
    throws Exception
  {
    autoLogin();
    List localList = queryPassenger();
    if ((localList != null) && (localList.contains(paramUserInfo)))
    {
      Context localContext = new Context();
      localContext.put("userinfo", paramUserInfo);
      execRule("deletePassenger", getG(), localContext);
    }
  }

  public SysUserInfo getCurrentUserDetail()
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    execRule("queryWebUser", getG(), localContext);
    return (SysUserInfo)localContext.get("userinfo");
  }

  public UserInfo getPassengerDetail(UserInfo paramUserInfo)
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    localContext.put("userinfo", paramUserInfo);
    execRule("querySinglePassenger", getG(), localContext);
    return (UserInfo)localContext.get("userinfo");
  }

  public TrainPrice getTicketPrice(ChainQuery paramChainQuery, List<Train> paramList)
    throws Exception
  {
    return new TrainPrice();
  }

  public TrainPrice getTicketPrice12306(ChainQuery paramChainQuery, List<Train> paramList)
    throws Exception
  {
    TrainPrice localTrainPrice = new TrainPrice();
    try
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Train localTrain = (Train)localIterator.next();
        Context localContext = new Context();
        localContext.put("train", localTrain);
        localContext.put("leaveDate", paramChainQuery.getLeaveDate());
        execRule("ticketPrice12306", getG(), localContext);
        localTrainPrice.initWithNew(localTrain, (JSONObject)localContext.get("price_data"));
      }
    }
    catch (Exception localException)
    {
      throw new m("请求票价数据失败");
    }
    return localTrainPrice;
  }

  public String initRefundTicket(OrderItem paramOrderItem)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("order", paramOrderItem);
    execRule("initRefundTicket", getG(), localContext);
    return localContext.getStr("initRefundTicket_msg");
  }

  public boolean isLogined()
    throws Exception
  {
    if (this.logined == 2)
      return false;
    if (loginIsFresh())
      return true;
    execRule("isLogined", getG());
    Boolean localBoolean = (Boolean)getG().get("isLogined");
    if (localBoolean.booleanValue())
    {
      this.logined = 1;
      this.loginFresh = System.currentTimeMillis();
    }
    while (true)
    {
      return localBoolean.booleanValue();
      this.logined = 2;
    }
  }

  public long laterEpay(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("laterEpay", getG(), localContext);
    if (localContext.get("epay_loseTime") == null);
    do
      return 0L;
    while (localContext.get("epay_beginTime") == null);
    return Long.parseLong(localContext.get("epay_loseTime").toString()) - Long.parseLong(localContext.get("epay_beginTime").toString());
  }

  public void loadCookie()
  {
    List localList = (List)PersistentObjService.readObject("cookie12306New.txt");
    if (localList == null);
    while (true)
    {
      return;
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        Cookie localCookie = (Cookie)localIterator.next();
        this.httpClient.addCookie(Constants.url12306New, localCookie);
      }
    }
  }

  public LoginUser login(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("sign", paramString3);
    localContext.put("name", paramString1);
    localContext.put("password", paramString2);
    execRule("runLogin", getG(), localContext);
    this.loginFresh = System.currentTimeMillis();
    saveLoginCookie();
    this.logined = 1;
    return new LoginUser(paramString1, paramString2, paramString1);
  }

  public boolean loginIsFresh()
  {
    return (60000L * this.app.launchInfo.optLong("login_keep_fresh2", 20L) > System.currentTimeMillis() - this.loginFresh) && (this.logined == 1);
  }

  public void loginOut()
    throws Exception
  {
    if (this.logined == 2)
      return;
    this.logined = 2;
    execRule("loginOut", getG());
  }

  public SYSignView.MulImage loginSign()
    throws Exception
  {
    return createSign("loginSign");
  }

  public List<OrderResult> myHistoryOrder()
    throws Exception
  {
    Context localContext = new Context();
    execRule("myHistoryOrder", getG(), localContext);
    saveLoginCookieLater();
    return (List)localContext.get("orderResults");
  }

  public SYSignView.MulImage orderSign()
    throws Exception
  {
    return createSign("orderSign");
  }

  public SYSignView.MulImage orderSignForQianPiao()
    throws Exception
  {
    return createSign("orderSignForQianPiao");
  }

  public NoteList queryCity(String paramString)
    throws Exception
  {
    Context localContext = new Context();
    NoteList localNoteList = new NoteList();
    localContext.put("cityList", localNoteList);
    localContext.put("cityName", paramString);
    execRule("queryCity", getG(), localContext);
    return localNoteList.filter(paramString);
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
    Context localContext = new Context();
    execRule("queryPassenger", getG(), localContext);
    return (List)localContext.get("passenger");
  }

  public List<Train> queryPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    long l = System.currentTimeMillis();
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryTicket", getG(), localContext);
    if (loginIsFresh())
      this.loginFresh = System.currentTimeMillis();
    n.a("queryNew-" + (System.currentTimeMillis() - l));
    saveLoginCookieLater();
    if (localContext.get("trainList") != null);
    for (Object localObject = localContext.get("trainList"); ; localObject = new ArrayList())
    {
      List localList = (List)(List)localObject;
      filterPiao(paramChainQuery, localList);
      return localList;
    }
  }

  public List<Train> queryPiaoCommon(ChainQuery paramChainQuery)
    throws Exception
  {
    try
    {
      List localList = queryPiao(paramChainQuery);
      return localList;
    }
    catch (m localm)
    {
      if ((this.app.getApiVersion() == 2) && ("110".equals(localm.a())))
        return HuocheMobile.getInstance().queryPiaoCommon(paramChainQuery);
    }
    throw localm;
  }

  public List<Train> queryPiaoForMonitor(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryTicketForMonitor", getG(), localContext);
    if (loginIsFresh())
      this.loginFresh = System.currentTimeMillis();
    Object localObject = (List)localContext.get("trainList");
    if (localObject == null)
      localObject = new ArrayList();
    filterPiao(paramChainQuery, (List)localObject);
    return (List<Train>)localObject;
  }

  public List<Train> queryPiaoNoLogin(ChainQuery paramChainQuery)
    throws Exception
  {
    return queryPiao(paramChainQuery);
  }

  public List<Train> queryforQianPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryForQianPiao", getG(), localContext);
    saveLoginCookieLater();
    if (localContext.get("trainList") != null);
    for (Object localObject = localContext.get("trainList"); ; localObject = new ArrayList())
    {
      List localList = (List)(List)localObject;
      filterPiao(paramChainQuery, localList);
      return localList;
    }
  }

  public void reSendEmail()
    throws Exception
  {
    Context localContext = new Context();
    execRule("reSendEmail", getG(), localContext);
  }

  public SYSignView.MulImage registerSign()
    throws Exception
  {
    return createSign("registerSign");
  }

  public BookResult resignBook(Train paramTrain, ChainQuery paramChainQuery, ArrayList<OrderItem> paramArrayList)
    throws Exception
  {
    Train localTrain = refreshTrain(paramChainQuery, paramTrain);
    Context localContext = new Context();
    localContext.put("train", localTrain);
    localContext.put("query", paramChainQuery);
    execRule("resignBook", getG(), localContext);
    BookResult localBookResult = new BookResult();
    localBookResult.setLeftTicketStr(getG().getStr("book_LeftTicketStr"));
    localBookResult.setToken(getG().getStr("book_token"));
    localBookResult.setTickets((List)localContext.get("bookPriceList"));
    localBookResult.setUserList((List)localContext.get("userList"));
    return localBookResult;
  }

  public void resignOrderN(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("order", paramOrderResult);
    execRule("resignNForm", getG(), localContext);
  }

  public SYSignView.MulImage resignOrderSign()
    throws Exception
  {
    return createSign("resignOrderSign");
  }

  public void resignOrderT(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("order", paramOrderResult);
    execRule("resignTForm", getG(), localContext);
  }

  public List<Train> resignQueryPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("resignQueryTicket", getG(), localContext);
    saveLoginCookieLater();
    Object localObject = (List)localContext.get("trainList");
    if (localObject == null)
      localObject = new ArrayList();
    filterPiao(paramChainQuery, (List)localObject);
    return (List<Train>)localObject;
  }

  public void resignReady(ArrayList<OrderItem> paramArrayList)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("order", paramArrayList);
    execRule("resignReady", getG(), localContext);
  }

  public String resignSubmitOrder(String paramString, List<UserInfo> paramList, Train paramTrain, ArrayList<OrderItem> paramArrayList, ChainQuery paramChainQuery)
    throws Exception
  {
    this.bookHolder.remove(paramTrain.getKey());
    Context localContext = new Context();
    localContext.put("train", paramTrain);
    localContext.put("userList", paramList);
    localContext.put("sign", paramString);
    localContext.put("query", paramChainQuery);
    execRule("resignSubmitOrderAndWait", getG(), localContext);
    saveLoginCookieLater();
    return localContext.getStr("orderId");
  }

  public void ruleInit()
  {
    String str = this.app.launchInfo.optString("ruleVersion2", Constants.ruleVersionAssest2);
    if ((this.rule == null) || (!this.rule.a(str)))
      this.rule = RuleService.getInstance().loadRule(str, Constants.ruleVersionAssest2);
    super.ruleInit();
  }

  public void saveLoginCookie()
  {
    List localList = getC().getCookies(Constants.url12306New);
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
      ((Cookie)localIterator.next()).setLastUsed(System.currentTimeMillis());
    PersistentObjService.saveObject(localList, "cookie12306New.txt");
  }

  public void savePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception
  {
    autoLogin();
    List localList = queryPassenger();
    if ((localList != null) && (localList.contains(paramUserInfo2)))
    {
      Context localContext2 = new Context();
      localContext2.put("userinfo", paramUserInfo1);
      localContext2.put("olduserinfo", paramUserInfo2);
      execRule("modifyPassenger", getG(), localContext2);
      return;
    }
    Context localContext1 = new Context();
    localContext1.put("user", paramUserInfo1);
    execRule("addUser", getG(), localContext1);
  }

  public String submitOrder(ChainQuery paramChainQuery, String paramString, List<UserInfo> paramList, Train paramTrain)
    throws Exception
  {
    this.bookHolder.remove(paramTrain.getKey());
    Context localContext = new Context();
    localContext.put("train", paramTrain);
    localContext.put("userList", paramList);
    localContext.put("orderSign", paramString);
    localContext.put("query", paramChainQuery);
    execRule("submitOrderAndWait", getG(), localContext);
    saveLoginCookieLater();
    return localContext.getStr("orderId");
  }

  public void submitOrderForQianPiao(ChainQuery paramChainQuery, String paramString, List<UserInfo> paramList, Train paramTrain)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("train", paramTrain);
    localContext.put("userList", paramList);
    localContext.put("orderSign", paramString);
    localContext.put("query", paramChainQuery);
    execRule("submitOrderForQianPiao", getG(), localContext);
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
    Context localContext = new Context();
    localContext.put("orderResult", new OrderResultNew());
    execRule("uncompleteOrder", getG(), localContext);
    return (OrderResult)localContext.get("orderResult");
  }

  public Context unionPay(OrderResult paramOrderResult, String paramString)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    localContext.put("bank_code", paramString);
    execRule("unionPay", getG(), localContext);
    return localContext;
  }

  public void updateCurrentUser(SysUserInfo paramSysUserInfo1, SysUserInfo paramSysUserInfo2, String paramString)
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    localContext.put("userinfo", paramSysUserInfo1);
    localContext.put("olduserinfo", paramSysUserInfo2);
    localContext.put("myName", this.app.getUser().getUserName());
    localContext.put("myPassword", this.app.getUser().getPassword());
    execRule("changeWebUser", getG(), localContext);
  }

  public void updatePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception
  {
    autoLogin();
    List localList = queryPassenger();
    if ((localList != null) && (localList.contains(paramUserInfo2)))
    {
      Context localContext = new Context();
      localContext.put("userinfo", paramUserInfo1);
      localContext.put("olduserinfo", paramUserInfo2);
      execRule("modifyPassenger", getG(), localContext);
    }
  }

  public void uploadUser(UserInfo paramUserInfo)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("user", paramUserInfo);
    localContext.put("myName", this.app.getUserShow());
    localContext.put("myPassword", this.app.getUser().getPassword());
    execRule("runAddUser", getG(), localContext);
  }

  public SYSignView.MulImage userinfoUpdateSign()
    throws Exception
  {
    return createSign("registerSign");
  }

  public String waitOrder()
    throws Exception
  {
    Context localContext = new Context();
    execRule("queryOrderWaitTime", getG(), localContext);
    String str = ((JSONObject)localContext.get("order_Wait")).optString("orderId");
    if ((str != null) && (str.length() > 5))
      return str;
    return "";
  }

  public String waitOrderTimeResign()
    throws Exception
  {
    Context localContext = new Context();
    execRule("waitOrderTimeResign", getG(), localContext);
    String str = ((JSONObject)localContext.get("order_Wait")).optString("orderId");
    if ((str != null) && (str.length() > 5))
      return str;
    return "";
  }

  public Context webPay(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("webPay", getG(), localContext);
    return localContext;
  }

  public Context zfbWebPayPre(OrderResult paramOrderResult)
    throws Exception
  {
    Context localContext = new Context();
    localContext.put("orderResult", paramOrderResult);
    execRule("zfbWebPayPre", getG(), localContext);
    return localContext;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.HuocheNew
 * JD-Core Version:    0.6.0
 */