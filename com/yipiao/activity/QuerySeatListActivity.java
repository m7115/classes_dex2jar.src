package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.common.a.c;
import cn.suanya.common.net.LogInfo;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainComparator;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.FligthPrice;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainMobile;
import com.yipiao.bean.TrainSort;
import com.yipiao.service.AdvancedQueryService;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import com.yipiao.view.MyAlertDialog.Builder;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public class QuerySeatListActivity extends SeatListActivity
{
  static int go_login_forMonitor = 2131296889;
  static ChainComparator<Train> queryComparator;
  private View flight1;
  private View flight2;
  private TextView flightCity;
  private TextView flightDate1;
  private TextView flightDate2;
  private TextView flightPrice1;
  private TextView flightPrice2;
  private View flightView;
  private View flightWrap;
  int noEndEngine = 2;

  @SuppressLint({"HandlerLeak"})
  protected Handler queryExceptionHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.arg1 == QuerySeatListActivity.this.queryTimes)
      {
        QuerySeatListActivity localQuerySeatListActivity = QuerySeatListActivity.this;
        localQuerySeatListActivity.noEndEngine = (-1 + localQuerySeatListActivity.noEndEngine);
        Exception localException = (Exception)paramMessage.obj;
        if (QuerySeatListActivity.this.noEndEngine == 0)
          QuerySeatListActivity.this.showToast(localException.getMessage());
        QuerySeatListActivity.this.dismissProgressDialog();
      }
      super.handleMessage(paramMessage);
    }
  };

  @SuppressLint({"HandlerLeak"})
  protected Handler queryFinishHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      if (paramMessage.arg1 == QuerySeatListActivity.this.queryTimes)
      {
        QuerySeatListActivity localQuerySeatListActivity = QuerySeatListActivity.this;
        localQuerySeatListActivity.noEndEngine = (-1 + localQuerySeatListActivity.noEndEngine);
        List localList = (List)paramMessage.obj;
        QuerySeatListActivity.this.mergeSeatList(localList);
        QuerySeatListActivity.this.showSeatList();
        QuerySeatListActivity.this.dismissProgressDialog();
      }
      super.handleMessage(paramMessage);
    }
  };
  int queryTimes = 0;
  Thread thr2;
  Thread thr3;

  private void doQueryByHc(Huoche paramHuoche, int paramInt)
  {
    try
    {
      List localList = paramHuoche.queryPiaoCommon(this.cq);
      Message localMessage2 = new Message();
      localMessage2.obj = localList;
      localMessage2.arg1 = paramInt;
      this.queryFinishHandler.sendMessage(localMessage2);
      return;
    }
    catch (Exception localException)
    {
      Message localMessage1 = new Message();
      localMessage1.obj = localException;
      localMessage1.arg1 = paramInt;
      this.queryExceptionHandler.sendMessage(localMessage1);
    }
  }

  private int findTrain(Train paramTrain1, Train paramTrain2)
  {
    int i = 0;
    String[] arrayOfString = { "1", "3", "O", "M" };
    while (i < arrayOfString.length)
    {
      int j = paramTrain1.getSeatNum(arrayOfString[i]);
      int k = paramTrain2.getSeatNum(arrayOfString[i]);
      if (j > 5)
        j = 5;
      if (k > 5)
        k = 5;
      if (j > k)
        return 1;
      if (j < k)
        return 2;
      i++;
    }
    if ((paramTrain1 instanceof TrainMobile))
      return 1;
    return 2;
  }

  private void goFlight(ChainQuery paramChainQuery, String paramString)
  {
    logToServer("goPlane2", paramChainQuery.getArr().getName());
    StationNode localStationNode1 = paramChainQuery.getOrg();
    StationNode localStationNode2 = paramChainQuery.getArr();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://touch.qunar.com/h5/flight/flightlist?bd_source=zhixing").append("&startCity=").append(localStationNode1.getCityName()).append("&destCity=").append(localStationNode2.getCityName()).append("&startDate=").append(paramString).append("&backDate=&flightType=oneWay");
    goFlightWEBActivity(localStringBuilder.toString(), "http://touch.qunar.com|QN75=closed", null);
  }

  private void mergeSeatList(List<Train> paramList)
  {
    if ((paramList == null) || (paramList == null))
      return;
    if ((this.seatList == null) || (this.seatList.isEmpty()))
    {
      this.seatList = paramList;
      return;
    }
    paramList.addAll(this.seatList);
    while (true)
    {
      int i;
      try
      {
        Collections.sort(paramList, TrainSort.fromTime);
        i = -2 + paramList.size();
        if (i >= 0)
        {
          Train localTrain1 = (Train)paramList.get(i);
          Train localTrain2 = (Train)paramList.get(i + 1);
          if (!localTrain1.getCode().equals(localTrain2.getCode()))
            break label177;
          if (findTrain(localTrain1, localTrain2) != 1)
            continue;
          paramList.remove(i + 1);
          break label177;
          paramList.remove(i);
        }
      }
      catch (Exception localException)
      {
        logToServer(new LogInfo("sortErrorQuery", localException));
        return;
      }
      this.seatList = paramList;
      sort();
      return;
      label177: i--;
    }
  }

  private void mulEngineQuery()
  {
    beginQuery();
    showProgressDialog("", "请稍候...", true);
    this.noEndEngine = 2;
    this.queryTimes = (1 + this.queryTimes);
    this.thr3 = new Thread()
    {
      public void run()
      {
        QuerySeatListActivity.this.doQueryByHc(QuerySeatListActivity.this.getHc(3), QuerySeatListActivity.this.queryTimes);
      }
    };
    this.thr3.start();
    this.thr2 = new Thread()
    {
      public void run()
      {
        QuerySeatListActivity.this.doQueryByHc(QuerySeatListActivity.this.getHc(2), QuerySeatListActivity.this.queryTimes);
      }
    };
    this.thr2.start();
  }

  private void setFlightOnClick(View paramView, ChainQuery paramChainQuery, String paramString)
  {
    if ((paramView == null) || (paramChainQuery == null))
      return;
    paramView.setOnClickListener(new View.OnClickListener(paramChainQuery, paramString)
    {
      public void onClick(View paramView)
      {
        QuerySeatListActivity.this.goFlight(this.val$cq, this.val$date);
      }
    });
  }

  public void addListHeader()
  {
    this.flightView = LayoutInflater.from(this).inflate(2130903149, null);
    this.flightWrap = this.flightView.findViewById(2131297035);
    this.flightWrap.setVisibility(8);
    this.flightCity = ((TextView)this.flightView.findViewById(2131297036));
    this.flight1 = this.flightView.findViewById(2131297037);
    this.flight2 = this.flightView.findViewById(2131297040);
    this.flightDate1 = ((TextView)this.flightView.findViewById(2131297039));
    this.flightPrice1 = ((TextView)this.flightView.findViewById(2131297038));
    this.flightDate2 = ((TextView)this.flightView.findViewById(2131297042));
    this.flightPrice2 = ((TextView)this.flightView.findViewById(2131297041));
    this.mListView.addHeaderView(this.flightView);
  }

  public void addMonitor(boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, MonitorSetActivity.class);
    this.app.putParms("mi", new MonitorInfo(this.cq));
    startActivity(localIntent);
  }

  protected void advancedQuery()
  {
    if (AdvancedQueryService.running)
    {
      showToast(this.app.launchInfo.optString("AdvanceQuery.running.warn", "智能查询正在运行，请稍后"));
      return;
    }
    new MyAlertDialog.Builder(this).setTitle("提示").setMessage(this.app.launchInfo.optString("advancedQuery.dialogMessage", "智能查询在后台运行时，您可以在通知栏随时查看执行状况。")).setPositiveButton("后台执行", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        Intent localIntent = new Intent(QuerySeatListActivity.this, AdvancedQueryService.class);
        QuerySeatListActivity.this.cq.setResults(QuerySeatListActivity.this.seatList);
        QuerySeatListActivity.this.app.putParms("cq", QuerySeatListActivity.this.cq);
        QuerySeatListActivity.this.startService(localIntent);
      }
    }).setNeutralButton("执行", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        Intent localIntent1 = new Intent(QuerySeatListActivity.this, AdvancedQueryService.class);
        QuerySeatListActivity.this.cq.setResults(QuerySeatListActivity.this.seatList);
        QuerySeatListActivity.this.app.putParms("cq", QuerySeatListActivity.this.cq);
        QuerySeatListActivity.this.startService(localIntent1);
        Intent localIntent2 = new Intent(QuerySeatListActivity.this, AdvancedQueryActivity.class);
        QuerySeatListActivity.this.startActivity(localIntent2);
      }
    }).show();
  }

  protected ChainComparator<Train> comparatorInit()
  {
    this.compartorIndex = this.app.sp.getInt("compartorIndex", 0);
    if (this.compartorIndex >= comparators.length)
      this.compartorIndex = 0;
    if (queryComparator == null)
    {
      queryComparator = new ChainComparator();
      queryComparator.add(comparators[this.compartorIndex]);
    }
    return queryComparator;
  }

  protected String getDefautRemark()
  {
    return "<br/><font color=\"#707070\">没票了，查询太慢，都可以试试加入监控哟！</font>";
  }

  protected int getMainLayout()
  {
    return 2130903148;
  }

  public void goToBookSimple(Train paramTrain)
  {
    this.app.putParms("bookPassengers", PassengerService.getInstance().getCurrUsers());
    this.app.putParms("isNormalBook", Boolean.valueOf(true));
    super.goToBookSimple(paramTrain);
  }

  public void init()
  {
    if (checkNeedLaunch())
      return;
    this.cq = ((ChainQuery)this.app.getParms("cq"));
    this.seatList = this.cq.findResults();
    if (this.seatList == null)
      query();
    super.init();
    setClick(2131296889, this);
    setClick(2131296888, this);
    sort();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      super.onClick(paramView);
      return;
    case 2131296259:
      query();
      return;
    case 2131296889:
      addMonitor(true);
      return;
    case 2131296888:
      advancedQuery();
      return;
    case 2131296673:
      this.cq.addDate(-1);
      buttonGroupEnable();
      setTitle();
      query();
      return;
    case 2131296672:
      this.cq.addDate(1);
      buttonGroupEnable();
      setTitle();
      query();
      return;
    case 2131296668:
      goDateTimePickActivity(c.roundDate(new Date()), this.cq.getLeavedate2(), this.cq.getTimeRangBegin(), this.cq.getTimeRangEnd(), 2, 2131296668);
      return;
    case 2131296891:
      query();
      return;
    case 2131296890:
    }
    showPrice();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if ((this.mListView.getHeaderViewsCount() > 0) && (paramInt == 0))
    {
      goFlight(this.cq, this.cq.getLeaveDate());
      return;
    }
    this.app.putParms("order", null);
    super.onItemClick(paramAdapterView, paramView, paramInt, paramLong);
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == go_login_forMonitor)
      addMonitor(false);
    if (paramInt == 2131296888);
    super.onLoginSuccess(paramInt);
  }

  public void query()
  {
    boolean bool = this.app.launchInfo.optBoolean("mulEngineQuery", true);
    if ((this.app.isAutoApiVersion()) && (bool))
      mulEngineQuery();
    while (true)
    {
      queryFlight();
      return;
      super.query();
    }
  }

  public void queryFlight()
  {
    new MyAsyncTask(this, false)
    {
      protected List<FligthPrice> myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return QuerySeatListActivity.this.getHc().getFlightPrice(QuerySeatListActivity.this.cq);
      }

      protected void myPostExecute(List<FligthPrice> paramList)
      {
        if ((paramList == null) || (paramList.size() < 2))
        {
          if (QuerySeatListActivity.this.flightWrap != null)
            QuerySeatListActivity.this.flightWrap.setVisibility(8);
          return;
        }
        StationNode localStationNode1 = QuerySeatListActivity.this.cq.getOrg();
        StationNode localStationNode2 = QuerySeatListActivity.this.cq.getArr();
        QuerySeatListActivity.this.flightCity.setText(localStationNode1.getCityName() + " - " + localStationNode2.getCityName());
        QuerySeatListActivity.this.flightDate1.setText(cn.suanya.common.a.v.a(((FligthPrice)paramList.get(0)).getDate()).split(" ")[0]);
        QuerySeatListActivity.this.flightPrice1.setText("￥" + ((FligthPrice)paramList.get(0)).getPrice());
        QuerySeatListActivity.this.flightDate2.setText(cn.suanya.common.a.v.a(((FligthPrice)paramList.get(1)).getDate()).split(" ")[0]);
        QuerySeatListActivity.this.flightPrice2.setText("￥" + ((FligthPrice)paramList.get(1)).getPrice());
        QuerySeatListActivity.this.setFlightOnClick(QuerySeatListActivity.this.flight1, QuerySeatListActivity.this.cq, ((FligthPrice)paramList.get(0)).getDate());
        QuerySeatListActivity.this.setFlightOnClick(QuerySeatListActivity.this.flight2, QuerySeatListActivity.this.cq, ((FligthPrice)paramList.get(1)).getDate());
        TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
        localTranslateAnimation.setDuration(200L);
        QuerySeatListActivity.this.flightWrap.startAnimation(localTranslateAnimation);
        QuerySeatListActivity.this.flightWrap.setVisibility(0);
      }

      protected void onException(Exception paramException)
      {
        if (QuerySeatListActivity.this.flightWrap != null)
          QuerySeatListActivity.this.flightWrap.setVisibility(8);
      }
    }
    .execute(new String[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.QuerySeatListActivity
 * JD-Core Version:    0.6.0
 */