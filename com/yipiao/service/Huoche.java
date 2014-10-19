package com.yipiao.service;

import cn.suanya.rule.bean.Context;
import cn.suanya.rule.bean.SyContext;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.bean.BookResult;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.FligthPrice;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainPrice;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.bean.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

public abstract interface Huoche
{
  public abstract Context abcNetPay1(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract Context abcNetPay2(String paramString)
    throws Exception;

  public abstract SYSignView.MulImage abcSign(OrderResult paramOrderResult)
    throws Exception;

  public abstract Context abcWebPayPre(OrderResult paramOrderResult)
    throws Exception;

  public abstract void addPassenger(UserInfo paramUserInfo)
    throws Exception;

  public abstract JSONArray advanceQueryFirst(ChainQuery paramChainQuery)
    throws Exception;

  public abstract List<TrainStationInfo> arrStationInfo(Train paramTrain)
    throws Exception;

  public abstract String autoBook(MonitorInfo paramMonitorInfo, List<Train> paramList, List<UserInfo> paramList1)
    throws Exception;

  public abstract boolean autoLogin()
    throws Exception;

  public abstract BookResult book(Train paramTrain, ChainQuery paramChainQuery)
    throws Exception;

  public abstract void cancelOrder(String paramString1, String paramString2, OrderResult paramOrderResult)
    throws Exception;

  public abstract Context ccbWebPayPre(OrderResult paramOrderResult)
    throws Exception;

  public abstract void changePwd(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract String cmbCreditCardPay(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract String cmbNetPay(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract SYSignView.MulImage cmbPayPre(OrderResult paramOrderResult)
    throws Exception;

  public abstract Context cmbPayPreHtml5(OrderResult paramOrderResult)
    throws Exception;

  public abstract String confirmRefundTicket(OrderItem paramOrderItem)
    throws Exception;

  public abstract void confirmResign(String paramString)
    throws Exception;

  public abstract void deletePassenger(UserInfo paramUserInfo)
    throws Exception;

  public abstract SysUserInfo getCurrentUserDetail()
    throws Exception;

  public abstract List<FligthPrice> getFlightPrice(ChainQuery paramChainQuery)
    throws Exception;

  public abstract UserInfo getPassengerDetail(UserInfo paramUserInfo)
    throws Exception;

  public abstract NoteList getSchool(String paramString)
    throws Exception;

  public abstract TrainPrice getTicketPrice(ChainQuery paramChainQuery, List<Train> paramList)
    throws Exception;

  public abstract boolean init(int paramInt)
    throws Exception;

  public abstract String initRefundTicket(OrderItem paramOrderItem)
    throws Exception;

  public abstract boolean isLogined()
    throws Exception;

  public abstract long laterEpay(OrderResult paramOrderResult)
    throws Exception;

  public abstract LoginUser login(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract void loginOut()
    throws Exception;

  public abstract SYSignView.MulImage loginSign()
    throws Exception;

  public abstract List<OrderResult> myHistoryOrder()
    throws Exception;

  public abstract SYSignView.MulImage orderSign()
    throws Exception;

  public abstract SYSignView.MulImage orderSignForQianPiao()
    throws Exception;

  public abstract NoteList queryCity(String paramString)
    throws Exception;

  public abstract List<Train> queryForAdvanced(ChainQuery paramChainQuery)
    throws Exception;

  public abstract List<UserInfo> queryPassenger()
    throws Exception;

  public abstract List<Train> queryPiao(ChainQuery paramChainQuery)
    throws Exception;

  public abstract List<Train> queryPiaoCommon(ChainQuery paramChainQuery)
    throws Exception;

  public abstract List<Train> queryPiaoForMonitor(ChainQuery paramChainQuery)
    throws Exception;

  public abstract List<Train> queryPiaoNoLogin(ChainQuery paramChainQuery)
    throws Exception;

  public abstract void reSendEmail()
    throws Exception;

  public abstract boolean registerCheckName(String paramString)
    throws Exception;

  public abstract SYSignView.MulImage registerSign()
    throws Exception;

  public abstract BookResult resignBook(Train paramTrain, ChainQuery paramChainQuery, ArrayList<OrderItem> paramArrayList)
    throws Exception;

  public abstract void resignOrderN(OrderResult paramOrderResult)
    throws Exception;

  public abstract SYSignView.MulImage resignOrderSign()
    throws Exception;

  public abstract void resignOrderT(OrderResult paramOrderResult)
    throws Exception;

  public abstract List<Train> resignQueryPiao(ChainQuery paramChainQuery)
    throws Exception;

  public abstract void resignReady(ArrayList<OrderItem> paramArrayList)
    throws Exception;

  public abstract String resignSubmitOrder(String paramString, List<UserInfo> paramList, Train paramTrain, ArrayList<OrderItem> paramArrayList, ChainQuery paramChainQuery)
    throws Exception;

  public abstract void ruleInit();

  public abstract SyContext runRule(String paramString);

  public abstract void saveLoginCookie();

  public abstract void saveLoginCookieLater();

  public abstract void savePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception;

  public abstract String submitOrder(ChainQuery paramChainQuery, String paramString, List<UserInfo> paramList, Train paramTrain)
    throws Exception;

  public abstract void submitRegister(Map<String, String> paramMap)
    throws Exception;

  public abstract OrderResult uncompleteOrder()
    throws Exception;

  public abstract Context unionPay(OrderResult paramOrderResult, String paramString)
    throws Exception;

  public abstract void updateCurrentUser(SysUserInfo paramSysUserInfo1, SysUserInfo paramSysUserInfo2, String paramString)
    throws Exception;

  public abstract void updatePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
    throws Exception;

  public abstract void uploadUser(UserInfo paramUserInfo)
    throws Exception;

  public abstract SYSignView.MulImage userinfoUpdateSign()
    throws Exception;

  public abstract String waitOrder()
    throws Exception;

  public abstract String waitOrderTimeResign()
    throws Exception;

  public abstract Context webPay(OrderResult paramOrderResult)
    throws Exception;

  public abstract String zfbClientPayUrl(OrderResult paramOrderResult)
    throws Exception;

  public abstract Context zfbWebPayPre(OrderResult paramOrderResult)
    throws Exception;

  public abstract Context zwdQuery(String paramString1, TrainStationInfo paramTrainStationInfo, String paramString2, boolean paramBoolean)
    throws Exception;

  public abstract SYSignView.MulImage zwdQuerySign()
    throws Exception;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.Huoche
 * JD-Core Version:    0.6.0
 */