package com.yipiao.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import cn.suanya.common.a.c;
import cn.suanya.common.a.n;
import cn.suanya.common.a.s;
import cn.suanya.common.net.LogInfo;
import com.example.pathview.ResultActivity;
import com.example.pathview.ResultByTrainActivity;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.SeatListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainComparator;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainPrice;
import com.yipiao.bean.TrainSort;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.FilterTrainDialog;
import com.yipiao.view.FilterTrainDialog.FilterDilogListener;
import com.yipiao.view.RandomLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SeatListActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  public static Comparator<Train>[] comparators;
  static String[] sortLabel = { "默认排序", "按始发时间排序", "按到达时间排序", "按列车速度排序", "卧铺优先排序", "硬座优先排序", "按余票数量排序" };
  private RandomLayout _randomImageView;
  protected SeatListAdapter adapter;
  int compartorIndex = 0;
  protected ChainQuery cq;
  private View emptyView;
  private View footerView;
  protected ChainComparator<Train> mComparator;
  protected ListView mListView;
  private boolean mNoQueryOrder;
  private List<OrderItem> mOrders;
  private Train mTrain;
  protected List<Train> seatList;

  static
  {
    comparators = new Comparator[7];
    comparators[0] = TrainSort.fromTime;
    comparators[1] = TrainSort.fromTime;
    comparators[2] = TrainSort.toTime;
    comparators[3] = TrainSort.speed;
    comparators[4] = TrainSort.wopu;
    comparators[5] = TrainSort.yingzuo;
    comparators[6] = TrainSort.seatNum;
  }

  private void goToTrainTime(Train paramTrain)
  {
    Intent localIntent = new Intent(this, ResultActivity.class);
    localIntent.putExtra("code", paramTrain.getCode());
    startActivity(localIntent);
  }

  private void goToTrainTime12306(Train paramTrain)
  {
    Intent localIntent = new Intent(this, ResultByTrainActivity.class);
    this.app.putParms("train", paramTrain);
    startActivity(localIntent);
  }

  protected void addListFooter()
  {
    this.footerView = LayoutInflater.from(this).inflate(2130903150, null);
    this.emptyView = this.footerView.findViewById(2131297043);
    this._randomImageView = ((RandomLayout)this.footerView.findViewById(2131297028));
    JSONArray localJSONArray = optJsonArrayFromLaunchInfo("train_list_footer");
    this._randomImageView.init(localJSONArray);
    this.mListView.addFooterView(this.footerView);
  }

  protected void addListHeader()
  {
  }

  protected void beginQuery()
  {
    if (this.seatList != null)
    {
      this.seatList.clear();
      this.adapter.notifyDataSetChanged();
    }
    if (this._randomImageView != null)
      this._randomImageView.setVisibility(8);
  }

  protected void buttonGroupEnable()
  {
    if (this.cq.getLeavedate2().getTime() > System.currentTimeMillis())
    {
      findViewById(2131296673).setVisibility(0);
      return;
    }
    findViewById(2131296673).setVisibility(4);
  }

  protected ChainComparator<Train> comparatorInit()
  {
    return new ChainComparator();
  }

  protected void createAdapter()
  {
    this.adapter = new SeatListAdapter(this, this.seatList, 2130903162, this.cq);
  }

  public void goToBook(ChainQuery paramChainQuery, Train paramTrain)
  {
    this.mTrain = paramTrain;
    checkForLogin(paramTrain.getApiVersion(), 2130903181, "请先登陆！");
  }

  public void goToBookSimple(Train paramTrain)
  {
    this.app.putParms("train", paramTrain);
    this.app.putParms("chainQuery", this.cq);
    this.app.putParms("passengers", this.passengerService.getCurrUsers());
    startActivity(new Intent(this, BookActivity.class));
  }

  public void goToQianPiao(ChainQuery paramChainQuery, Train paramTrain)
  {
    this.mTrain = paramTrain;
    checkForLogin(2, 2130903068, "请先登陆！");
  }

  public void goToQianPiaoSimple(Train paramTrain)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.seatList.iterator();
    while (localIterator.hasNext())
    {
      Train localTrain = (Train)localIterator.next();
      String str = paramTrain.getStartPayStr();
      if ((str != null) && (!str.equals(localTrain.getStartPayStr())))
        continue;
      localArrayList.add(localTrain);
    }
    this.app.putParms("trainCode", paramTrain.getCode());
    this.app.putParms("trains", localArrayList);
    this.app.putParms("chainQuery", this.cq);
    this.app.putParms("passengers", this.passengerService.getCurrUsers());
    startActivity(new Intent(this, BookQianPiao2Activity.class));
  }

  public void goToResign(ChainQuery paramChainQuery, Train paramTrain, List<OrderItem> paramList, boolean paramBoolean)
  {
    this.mTrain = paramTrain;
    this.mOrders = paramList;
    this.mNoQueryOrder = paramBoolean;
    checkForLogin(paramTrain.getApiVersion(), 2130903158, "请先登陆！");
  }

  public void goToResignSimple(Train paramTrain)
  {
    this.app.putParms("train", paramTrain);
    this.app.putParms("chainQuery", this.cq);
    this.app.putParms("order", this.mOrders);
    this.app.putParms("noQueryOrder", Boolean.valueOf(this.mNoQueryOrder));
    startActivity(new Intent(this, ResignBookActivity.class));
  }

  protected void goToTrainDetail(Train paramTrain)
  {
    Intent localIntent = new Intent(this, TrainDetailActivity.class);
    this.app.putParms("train", paramTrain);
    this.app.putParms("cq", this.cq);
    startActivity(localIntent);
  }

  protected void hiddenEmptyView()
  {
    if (this.emptyView != null)
    {
      this.emptyView.getLayoutParams().height = 0;
      this.emptyView.setVisibility(8);
    }
  }

  public void init()
  {
    this.mComparator = comparatorInit();
    super.init();
    if (this.seatList == null)
      this.seatList = new ArrayList();
    createAdapter();
    this.mListView = ((ListView)findViewById(2131296764));
    addListHeader();
    addListFooter();
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(this);
    setTitle();
    setClick(2131296673, this);
    setClick(2131296672, this);
    setClick(2131296668, this);
    setClick(2131296259, this);
    setClick(2131296891, this);
    setClick(2131296887, this);
    setClick(2131296890, this);
    buttonGroupEnable();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 2131296668) && (paramInt2 != 0))
    {
      Date localDate = (Date)paramIntent.getSerializableExtra("currentDate");
      this.cq.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").format(localDate));
      String str1 = (String)paramIntent.getSerializableExtra("begin");
      String str2 = (String)paramIntent.getSerializableExtra("end");
      this.cq.setTimeRangBegin(str1);
      this.cq.setTimeRangEnd(str2);
      setTitle();
      query();
      buttonGroupEnable();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296259:
      query();
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
    case 2131296887:
      showDialog(2130903163);
      return;
    case 2131296890:
    }
    showPrice();
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 2130903163:
    }
    return new FilterTrainDialog(this, this.compartorIndex, this.cq, new FilterTrainDialog.FilterDilogListener()
    {
      public void submitFilter(int paramInt, ChainQuery paramChainQuery)
      {
        SeatListActivity.this.cq = paramChainQuery;
        SeatListActivity.this.compartorIndex = paramInt;
        SeatListActivity.this.mComparator.add(SeatListActivity.comparators[SeatListActivity.this.compartorIndex]);
        SeatListActivity.this.setTv(2131297044, SeatListActivity.this.cq.getTimeRangBegin() + "--" + SeatListActivity.this.cq.getTimeRangEnd());
        SeatListActivity.this.query();
      }
    }).create();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    goToTrainDetail((Train)paramView.getTag());
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2130903181)
      goToBookSimple(this.mTrain);
    if (paramInt == 2130903068)
      goToQianPiaoSimple(this.mTrain);
    if (paramInt == 2130903158)
      goToResignSimple(this.mTrain);
    super.onLoginSuccess(paramInt);
  }

  public JSONArray optJsonArrayFromLaunchInfo(String paramString)
  {
    try
    {
      JSONArray localJSONArray1 = YipiaoService.getInstance().getDefautLaunchConfig().optJSONArray(paramString);
      JSONArray localJSONArray2 = new JSONArray(this.app.launchInfo.optString(paramString, localJSONArray1.toString()));
      return localJSONArray2;
    }
    catch (Exception localException)
    {
      n.b(localException);
    }
    return null;
  }

  public void query()
  {
    beginQuery();
    new MyAsyncTask(this)
    {
      protected Object myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        SeatListActivity.this.monitorWaitForQuery();
        return SeatListActivity.this.getHc().queryPiaoCommon(SeatListActivity.this.cq);
      }

      protected void myPostExecute(Object paramObject)
      {
        if ((paramObject instanceof String))
        {
          SeatListActivity.this.showToast((String)paramObject);
          return;
        }
        SeatListActivity.this.seatList = ((List)paramObject);
        SeatListActivity.this.cq.setResults(SeatListActivity.this.seatList);
        SeatListActivity.this.sort();
        SeatListActivity.this.showSeatList();
        SeatListActivity.this.app.successLevel = 2;
      }

      protected void onException(Exception paramException)
      {
        SeatListActivity.this.app.successLevel = 0;
        super.onException(paramException);
      }
    }
    .execute(new String[0]);
  }

  public void queryPrice()
  {
    new MyAsyncTask(this)
    {
      protected TrainPrice myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return SeatListActivity.this.getHc().getTicketPrice(SeatListActivity.this.cq, SeatListActivity.this.seatList);
      }

      protected void myPostExecute(TrainPrice paramTrainPrice)
      {
        SeatListActivity.this.setTv(2131296890, "显示余票");
        SeatListActivity.this.adapter.setPrices(paramTrainPrice);
        SeatListActivity.this.adapter.setShowPrice(true);
        SeatListActivity.this.adapter.notifyDataSetChanged();
      }
    }
    .execute(new String[0]);
  }

  protected void setTitle()
  {
    setTv(2131296669, cn.suanya.common.a.v.a(this.cq.getLeaveDate()).split(" ")[0]);
    setTv(2131297044, this.cq.getTimeRangBegin() + "--" + this.cq.getTimeRangEnd());
  }

  protected void showEmptyView()
  {
    if (this.emptyView != null)
    {
      this.emptyView.setVisibility(0);
      this.emptyView.getLayoutParams().height = this.mListView.getHeight();
      this.emptyView.setVisibility(0);
    }
  }

  protected void showPrice()
  {
    Button localButton = (Button)findViewById(2131296890);
    if (localButton.getText().equals("显示价格"))
    {
      queryPrice();
      return;
    }
    this.adapter.setShowPrice(false);
    this.adapter.notifyDataSetChanged();
    localButton.setText("显示价格");
  }

  protected void showSeatList()
  {
    this.adapter.setMlist(this.seatList);
    this.adapter.notifyDataSetChanged();
    if (s.a(this.seatList))
      showEmptyView();
    while (true)
    {
      return;
      hiddenEmptyView();
      int i = this.app.launchInfo.optInt("bannleMinSeatNum", 4);
      if (this._randomImageView == null)
        continue;
      if (i > this.seatList.size())
        break;
      if (this._randomImageView.getVisibility() == 0)
        continue;
      this._randomImageView.random();
      return;
    }
    this._randomImageView.setVisibility(8);
  }

  public void sort()
  {
    try
    {
      Collections.sort(this.seatList, this.mComparator);
      this.mListView.setSelection(0);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        logToServer(new LogInfo("sortErrorQuery", localException));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.SeatListActivity
 * JD-Core Version:    0.6.0
 */