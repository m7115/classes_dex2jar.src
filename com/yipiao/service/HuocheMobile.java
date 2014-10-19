package com.yipiao.service;

import android.util.Base64;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.SyContext;
import cn.suanya.rule.s;
import cn.suanya.service.RuleService;
import com.yipiao.Config;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.HCUtil;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.bean.BookResult;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.OrderResultMobile;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainPrice;
import com.yipiao.bean.UserInfo;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.yipiao.bean.Train;>;
import java.util.Map;
import org.json.JSONObject;

public class HuocheMobile extends HuocheBase
{
  static HuocheMobile hc;
  private long initTime = 0L;

  public HuocheMobile()
  {
    getG().put("mHc", this);
  }

  public HuocheMobile(Context paramContext)
  {
    this.glob = paramContext;
    paramContext.put("app", this.app);
    paramContext.put("cfg", Config.getInstance());
    getG().put("mHc", this);
  }

  private void composite()
    throws Exception
  {
    runRule("composite");
  }

  public static HuocheMobile getInstance()
  {
    if (hc == null)
      hc = new HuocheMobile();
    return hc;
  }

  private boolean init()
    throws Exception
  {
    return init(0);
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
    if (paramList == null)
      throw new m("没有合适的车次");
    Iterator localIterator1 = paramList.iterator();
    Train localTrain;
    int j;
    while (localIterator1.hasNext())
    {
      localTrain = (Train)localIterator1.next();
      if ((paramMonitorInfo.getTrainList() != null) && (!paramMonitorInfo.getTrainList().isEmpty()) && (!paramMonitorInfo.getTrainList().contains(localTrain.getCode())))
        continue;
      int i = localTrain.getSeatNum(paramMonitorInfo.getSeatTypes());
      if (paramMonitorInfo.getSeatNum().intValue() > i)
        continue;
      Iterator localIterator2 = paramList1.iterator();
      while (localIterator2.hasNext())
      {
        UserInfo localUserInfo = (UserInfo)localIterator2.next();
        if (HCUtil.calPassengerSeat(localTrain.getSeats(), localUserInfo, paramMonitorInfo))
          continue;
        j = 0;
        label195: if (j == 0)
          break label334;
      }
    }
    while (true)
    {
      if (localTrain == null)
        throw new m("没有合适的座位");
      autoLogin();
      Context localContext = new Context();
      localContext.put("train", localTrain);
      localContext.put("userList", paramList1);
      localContext.put("query", localChainQuery);
      try
      {
        if (paramMonitorInfo.isResign())
        {
          localContext.put("orders", paramMonitorInfo.getOrders());
          execRule("autoBookResign", getG(), localContext);
          break label342;
        }
        execRule("autoBook", getG(), localContext);
      }
      catch (Exception localException)
      {
        if (m.a("103", localException))
          return localException.getMessage();
        throw localException;
      }
      j = 1;
      break label195;
      label334: break;
      localTrain = null;
    }
    label342: return "订单已经提交，请前往未支付订单查看";
  }

  public boolean autoLogin()
    throws Exception
  {
    monitorenter;
    LoginUser localLoginUser;
    try
    {
      localLoginUser = this.app.getUser();
      if ((this.logined == 1) && (localLoginUser.canLogin()) && (localLoginUser.getUserName().equals(getG().getStr("userName"))))
        return true;
      if (!localLoginUser.canLogin())
        throw new m("没有设置登陆帐号");
    }
    finally
    {
      monitorexit;
    }
    login(localLoginUser.getUserName(), localLoginUser.getPassword(), "");
    monitorexit;
    return true;
  }

  public BookResult book(Train paramTrain, ChainQuery paramChainQuery)
    throws Exception
  {
    init();
    BookResult localBookResult = new BookResult();
    localBookResult.setTickets(paramTrain.getSeats());
    return localBookResult;
  }

  public void cancelOrder(String paramString1, String paramString2, OrderResult paramOrderResult)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("order", paramOrderResult);
    execRule("cancelOrder", getG(), localContext);
    this.initTime = System.currentTimeMillis();
  }

  public void changePwd(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    localContext.put("userName", paramString1);
    localContext.put("oldPwd", paramString2);
    localContext.put("newPwd", paramString3);
    execRule("changePwd", getG(), localContext);
  }

  public String confirmRefundTicket(OrderItem paramOrderItem)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("order", paramOrderItem);
    execRule("confirmRefundTicket", getG(), localContext);
    return localContext.getStr("confirmRefundTicket_msg");
  }

  public void confirmResign(String paramString)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("orderNo", paramString);
    execRule("confirmResignSuc", getG(), localContext);
  }

  public HuocheMobile copyHc()
  {
    HuocheMobile localHuocheMobile = new HuocheMobile(new Context());
    localHuocheMobile.rule = this.rule;
    try
    {
      localHuocheMobile.httpClient = createHttpClient();
      label31: localHuocheMobile.runRule("init");
      return localHuocheMobile;
    }
    catch (Exception localException)
    {
      break label31;
    }
  }

  public void deletePassenger(UserInfo paramUserInfo)
    throws Exception
  {
    autoLogin();
    List localList = queryPassenger();
    if ((localList != null) && (localList.contains(paramUserInfo)))
    {
      Context localContext = new Context();
      localContext.put("passenger", paramUserInfo);
      execRule("deletePassenger", getG(), localContext);
    }
  }

  public SysUserInfo getCurrentUserDetail()
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    execRule("queryMobileUser", getG(), localContext);
    return (SysUserInfo)localContext.get("userinfo");
  }

  public long getInitTime()
  {
    return this.initTime;
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

  public boolean init(int paramInt)
    throws Exception
  {
    int i = 60000 * this.app.launchInfo.optInt("mobile_reinit_min", 10);
    int j = 60000 * this.app.launchInfo.optInt("mobile_reinit_min_0", 30);
    monitorenter;
    while (true)
    {
      long l1;
      try
      {
        l1 = System.currentTimeMillis() - this.initTime;
        long l2 = System.currentTimeMillis();
        if ((paramInt == 2) || (this.initTime == 0L) || ((paramInt == 1) && (l1 > i)))
        {
          getC().clearCookie();
          this.logined = 2;
          execRule("init_", getG());
          n.a("init:" + (System.currentTimeMillis() - l2) + ";initTime:" + l1 + ";initLevel:" + paramInt);
          this.initTime = System.currentTimeMillis();
          return true;
          if (paramInt != 1)
            continue;
          composite();
          n.a("composite:" + (System.currentTimeMillis() - l2));
          return false;
        }
      }
      finally
      {
        monitorexit;
      }
      if ((paramInt != 0) || (l1 <= j))
        continue;
    }
  }

  public String initRefundTicket(OrderItem paramOrderItem)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("order", paramOrderItem);
    execRule("initRefundTicket", getG(), localContext);
    return localContext.getStr("initRefundTicket_msg");
  }

  public boolean isLogined()
    throws Exception
  {
    return this.logined == 1;
  }

  public long laterEpay(OrderResult paramOrderResult)
    throws Exception
  {
    return paramOrderResult.getPayTimeLong();
  }

  public LoginUser login(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    monitorenter;
    try
    {
      init();
      this.logined = 2;
      Context localContext = new Context();
      localContext.put("userName", paramString1);
      localContext.put("password", paramString2);
      while (true)
      {
        try
        {
          execRule("runLogin", getG(), localContext);
          this.initTime = System.currentTimeMillis();
          this.logined = 1;
          LoginUser localLoginUser = new LoginUser(paramString1, paramString2, paramString1);
          monitorexit;
          return localLoginUser;
        }
        catch (m localm)
        {
          if (!"102".equals(localm.a()))
            break;
        }
        init(2);
        execRule("runLogin", getG(), localContext);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localm;
  }

  public void loginOut()
    throws Exception
  {
    if (this.logined == 2)
      return;
    this.logined = 2;
    init();
    execRule("logout", getG());
    this.initTime = System.currentTimeMillis();
  }

  public List<OrderResult> myHistoryOrder()
    throws Exception
  {
    init();
    Context localContext = new Context();
    execRule("myHistoryOrder", getG(), localContext);
    this.initTime = System.currentTimeMillis();
    saveLoginCookieLater();
    return (List)localContext.get("orderResults");
  }

  public SYSignView.MulImage orderSign()
    throws Exception
  {
    init();
    Context localContext = new Context();
    execRule("orderSign", getG(), localContext);
    this.initTime = System.currentTimeMillis();
    return createSign(new ByteArrayInputStream(Base64.decode(localContext.getStr("passCode"), 0)), localContext);
  }

  public void qianPiao(ChainQuery paramChainQuery, Train paramTrain, List<UserInfo> paramList)
    throws Exception
  {
    autoLogin();
    Context localContext1 = new Context();
    localContext1.put("train", paramTrain);
    localContext1.put("userList", paramList);
    localContext1.put("query", paramChainQuery);
    try
    {
      execRule("qianPiao", getG(), localContext1);
      return;
    }
    catch (Exception localException)
    {
      if ((localException.getMessage() != null) && (localException.getMessage().contains("登陆")))
      {
        login(this.app.getUser().getUserName(), this.app.getUser().getPassword(), "");
        Context localContext2 = new Context();
        localContext2.put("train", paramTrain);
        localContext2.put("userList", paramList);
        localContext2.put("query", paramChainQuery);
        execRule("qianPiao", getG(), localContext1);
        return;
      }
    }
    throw localException;
  }

  public void qianPiao2(ChainQuery paramChainQuery, List<String> paramList, List<UserInfo> paramList1)
    throws Exception
  {
    autoLogin();
    Context localContext1 = new Context();
    localContext1.put("trains", paramList);
    localContext1.put("userList", paramList1);
    localContext1.put("query", paramChainQuery);
    try
    {
      execRule("qianPiao", getG(), localContext1);
      return;
    }
    catch (Exception localException)
    {
      if ((localException.getMessage() != null) && (localException.getMessage().contains("登陆")))
      {
        login(this.app.getUser().getUserName(), this.app.getUser().getPassword(), "");
        Context localContext2 = new Context();
        localContext2.put("trains", paramList);
        localContext2.put("userList", paramList1);
        localContext2.put("query", paramChainQuery);
        execRule("qianPiao2", getG(), localContext1);
        return;
      }
    }
    throw localException;
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
    init();
    return super.queryForAdvanced(paramChainQuery);
  }

  public List<UserInfo> queryPassenger()
    throws Exception
  {
    Context localContext = new Context();
    execRule("querymobilePassenger", getG(), localContext);
    return (List)getG().get("passengers");
  }

  public List<Train> queryPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    init();
    long l = System.currentTimeMillis();
    List localList = super.queryPiao(paramChainQuery);
    n.a("queryPiao:" + (System.currentTimeMillis() - l));
    if ((localList != null) && (!localList.isEmpty()))
      this.initTime = System.currentTimeMillis();
    filterPiao(paramChainQuery, localList);
    return localList;
  }

  public List<Train> queryPiaoForMonitor(ChainQuery paramChainQuery)
    throws Exception
  {
    init();
    List localList = super.queryPiaoForMonitor(paramChainQuery);
    if ((localList != null) && (!localList.isEmpty()))
      this.initTime = System.currentTimeMillis();
    filterPiao(paramChainQuery, localList);
    return localList;
  }

  public List<Train> queryforQianPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("query", paramChainQuery);
    execRule("queryForQianPiao", getG(), localContext);
    Object localObject = (List)localContext.get("trainList");
    if (localObject == null)
      localObject = new ArrayList();
    filterPiao(paramChainQuery, (List)localObject);
    return (List<Train>)localObject;
  }

  public void reSendEmail()
    throws Exception
  {
    HuocheNew.getInstance().reSendEmail();
  }

  public BookResult resignBook(Train paramTrain, ChainQuery paramChainQuery, ArrayList<OrderItem> paramArrayList)
    throws Exception
  {
    init();
    BookResult localBookResult = book(paramTrain, paramChainQuery);
    Context localContext = new Context();
    localContext.put("train", paramTrain);
    localContext.put("query", paramChainQuery);
    localContext.put("orders", paramArrayList);
    execRule("resignBook", getG(), localContext);
    localBookResult.setUserList((List)localContext.get("userList"));
    return localBookResult;
  }

  public void resignOrderN(OrderResult paramOrderResult)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("order", paramOrderResult);
    execRule("resignNForm", getG(), localContext);
  }

  public SYSignView.MulImage resignOrderSign()
    throws Exception
  {
    init();
    Context localContext = new Context();
    execRule("resignOrderSign", getG(), localContext);
    return createSign(new ByteArrayInputStream(Base64.decode(localContext.getStr("passCode"), 0)), localContext);
  }

  public void resignOrderT(OrderResult paramOrderResult)
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("order", paramOrderResult);
    execRule("resignTForm", getG(), localContext);
  }

  public List<Train> resignQueryPiao(ChainQuery paramChainQuery)
    throws Exception
  {
    init();
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

  public String resignSubmitOrder(String paramString, List<UserInfo> paramList, Train paramTrain, ArrayList<OrderItem> paramArrayList, ChainQuery paramChainQuery)
    throws Exception
  {
    init();
    Train localTrain = refreshTrain(paramChainQuery, paramTrain);
    this.bookHolder.remove(localTrain.getKey());
    Context localContext = new Context();
    localContext.put("train", localTrain);
    localContext.put("userList", paramList);
    localContext.put("sign", paramString);
    localContext.put("query", paramChainQuery);
    localContext.put("orders", paramArrayList);
    execRule("resignSubmitOrderAndWait", getG(), localContext);
    return localContext.getStr("orderId");
  }

  public void ruleInit()
  {
    String str = this.app.launchInfo.optString("ruleVersion3", Constants.ruleVersionAssest3);
    if ((this.rule == null) || (!this.rule.a(str)))
      this.rule = RuleService.getInstance().loadRule(str, Constants.ruleVersionAssest3);
    super.ruleInit();
  }

  public void savePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception
  {
    autoLogin();
    List localList = queryPassenger();
    if ((localList != null) && (localList.contains(paramUserInfo2)))
    {
      Context localContext2 = new Context();
      localContext2.put("tempaddpassenger", paramUserInfo1);
      localContext2.put("tempoldpassenger", paramUserInfo2);
      execRule("modifyPassenger", getG(), localContext2);
      return;
    }
    Context localContext1 = new Context();
    localContext1.put("tempaddpassenger", paramUserInfo1);
    execRule("addPassenger", getG(), localContext1);
  }

  public void setInitTime(long paramLong)
  {
    this.initTime = paramLong;
  }

  public String submitOrder(ChainQuery paramChainQuery, String paramString, List<UserInfo> paramList, Train paramTrain)
    throws Exception
  {
    init();
    Train localTrain = refreshTrain(paramChainQuery, paramTrain);
    Context localContext = new Context();
    localContext.put("train", localTrain);
    localContext.put("userList", paramList);
    localContext.put("sign", paramString);
    localContext.put("query", paramChainQuery);
    execRule("submitOrderAndWait", getG(), localContext);
    this.initTime = System.currentTimeMillis();
    return localContext.getStr("orderId");
  }

  public OrderResult uncompleteOrder()
    throws Exception
  {
    init();
    Context localContext = new Context();
    localContext.put("orderResult", new OrderResultMobile());
    execRule("uncompleteOrder", getG(), localContext);
    this.initTime = System.currentTimeMillis();
    return (OrderResult)localContext.get("orderResult");
  }

  public void updateCurrentUser(SysUserInfo paramSysUserInfo1, SysUserInfo paramSysUserInfo2, String paramString)
    throws Exception
  {
    autoLogin();
    Context localContext = new Context();
    localContext.put("userinfo", paramSysUserInfo1);
    localContext.put("passcode", paramString);
    execRule("changeMobileUser", getG(), localContext);
  }

  public void updatePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception
  {
    autoLogin();
    List localList = queryPassenger();
    if ((localList != null) && (localList.contains(paramUserInfo2)))
    {
      Context localContext = new Context();
      localContext.put("tempaddpassenger", paramUserInfo1);
      localContext.put("tempoldpassenger", paramUserInfo2);
      execRule("modifyPassenger", getG(), localContext);
    }
  }

  public SYSignView.MulImage userinfoUpdateSign()
    throws Exception
  {
    init();
    Context localContext = new Context();
    execRule("userinfoUpdateSign", getG(), localContext);
    this.initTime = System.currentTimeMillis();
    return createSign(new ByteArrayInputStream(Base64.decode(localContext.getStr("passCode"), 0)), localContext);
  }

  public String waitOrder()
    throws Exception
  {
    init();
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
    init();
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
    init();
    Context localContext = new Context();
    localContext.put("order", paramOrderResult);
    SyContext localSyContext = execRule("payOrder", getG(), localContext);
    this.initTime = System.currentTimeMillis();
    return localSyContext.getL();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.HuocheMobile
 * JD-Core Version:    0.6.0
 */