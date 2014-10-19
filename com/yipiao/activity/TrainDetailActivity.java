package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.common.a.c;
import com.example.pathview.bean.TrainInfo;
import com.yipiao.Config;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.ResultTicketListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheBase;
import com.yipiao.service.PassengerService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TrainDetailActivity extends BaseActivity
{
  private ChainQuery cq;
  private View footView;
  private boolean hasFootView = true;
  private ResultTicketListAdapter mAdapter;
  private LinearLayout mLinearLayout;
  private boolean mNoQueryOrder;
  private TextView mTV_LeaveDate;
  private TrainInfo mTrainInfo;
  private Train mtrain;
  private String oldLeaveDate;
  private ArrayList<OrderItem> order;
  private List<Train> seatList;
  private String seatType;
  private int startStation = -1;
  private HorizontalScrollView sv;
  private ListView ticketListView;

  private void addListFoot()
  {
    this.footView = LayoutInflater.from(this).inflate(2130903178, null);
    this.footView.setOnClickListener(this);
    this.ticketListView.addFooterView(this.footView);
  }

  private void buttonPreEnable()
  {
    if (this.cq.getLeavedate2().getTime() > System.currentTimeMillis())
    {
      findViewById(2131297114).setVisibility(0);
      return;
    }
    findViewById(2131297114).setVisibility(4);
  }

  private int dip2px(int paramInt)
  {
    return (int)(0.5F + getResources().getDisplayMetrics().density * paramInt);
  }

  private View generateTrainStationsList(List<TrainStationInfo> paramList)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    LinearLayout localLinearLayout = new LinearLayout(this);
    Iterator localIterator = paramList.iterator();
    int i = 0;
    TrainStationInfo localTrainStationInfo;
    View localView;
    TextView localTextView1;
    TextView localTextView2;
    if (localIterator.hasNext())
    {
      localTrainStationInfo = (TrainStationInfo)localIterator.next();
      localView = localLayoutInflater.inflate(2130903080, null);
      localTextView1 = (TextView)localView.findViewById(2131296833);
      localTextView2 = (TextView)localView.findViewById(2131296838);
      TextView localTextView3 = (TextView)localView.findViewById(2131296837);
      if (paramList.indexOf(localTrainStationInfo) == -1 + paramList.size())
      {
        localView.findViewById(2131296836).setVisibility(4);
        localTextView1.setText("终点");
        localTextView2.setText(localTrainStationInfo.getArrTime());
        label144: localTextView3.setText(localTrainStationInfo.getName());
        localLinearLayout.addView(localView);
        if (i != 0)
          break label289;
        this.startStation = (1 + this.startStation);
        if (!localTrainStationInfo.getName().equals(this.mtrain.getFrom()))
          break label289;
      }
    }
    label289: for (int j = 1; ; j = i)
    {
      i = j;
      break;
      if (paramList.indexOf(localTrainStationInfo) == 0)
      {
        localView.findViewById(2131296834).setVisibility(4);
        localTextView1.setText("始发");
        localTextView2.setText(localTrainStationInfo.getLeaveTime());
        break label144;
      }
      localTextView1.setText(localTrainStationInfo.getStopMins() + "  '");
      localTextView2.setText(localTrainStationInfo.getArrTime());
      break label144;
      return localLinearLayout;
    }
  }

  private void query()
  {
    if (this.sv.getChildCount() == 0)
      queryTrainInfo();
    new MyAsyncTask(this)
    {
      protected Object myInBackground(Void[] paramArrayOfVoid)
        throws Exception
      {
        return TrainDetailActivity.this.getHc().queryPiaoCommon(TrainDetailActivity.this.cq);
      }

      protected void myPostExecute(Object paramObject)
      {
        if ((paramObject instanceof String))
        {
          TrainDetailActivity.this.showToast((String)paramObject);
          return;
        }
        TrainDetailActivity.access$1102(TrainDetailActivity.this, (List)paramObject);
        if (TrainDetailActivity.this.seatList.size() == 0)
        {
          TrainDetailActivity.this.mAdapter.setDisplay(false);
          if (TrainDetailActivity.this.hasFootView)
            TrainDetailActivity.this.footView.setVisibility(8);
        }
        while (true)
        {
          TrainDetailActivity.this.mAdapter.notifyDataSetChanged();
          return;
          TrainDetailActivity.this.mAdapter.setCQ(TrainDetailActivity.this.cq, (Train)TrainDetailActivity.this.seatList.get(0));
          TrainDetailActivity.this.mAdapter.setDisplay(true);
          if ((!TrainDetailActivity.this.hasFootView) || (TrainDetailActivity.this.footView.getVisibility() != 8))
            continue;
          TrainDetailActivity.this.footView.setVisibility(0);
        }
      }

      protected void onException(Exception paramException)
      {
        TrainDetailActivity.this.cq.setLeaveDate(TrainDetailActivity.this.oldLeaveDate);
        TrainDetailActivity.this.setTextViewLeaveDate();
        super.onException(paramException);
      }
    }
    .execute(new Void[0]);
  }

  private void setTextViewLeaveDate()
  {
    this.mTV_LeaveDate.setText(c.formartMMdd(this.cq.getLeavedate2()));
    buttonPreEnable();
  }

  public void addMonitor(String paramString)
  {
    Intent localIntent = new Intent(this, MonitorSetActivity.class);
    try
    {
      ChainQuery localChainQuery2 = this.cq.clone();
      localChainQuery1 = localChainQuery2;
      localChainQuery1.setTrainNo(null);
      MonitorInfo localMonitorInfo = new MonitorInfo(localChainQuery1);
      NoteList localNoteList = new NoteList();
      localNoteList.add(this.cfg.seatTypesAll.getByCode(paramString));
      localMonitorInfo.setSeatTypes(localNoteList);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(this.mtrain.getCode());
      localMonitorInfo.setTrainList(localArrayList);
      this.app.putParms("mi", localMonitorInfo);
      startActivity(localIntent);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        ChainQuery localChainQuery1 = this.cq;
    }
  }

  protected int getMainLayout()
  {
    return 2130903180;
  }

  public void goToBook(ChainQuery paramChainQuery, Train paramTrain, String paramString)
  {
    this.mtrain = paramTrain;
    this.seatType = paramString;
    checkForLogin(paramTrain.getApiVersion(), 2130903181, "请先登陆！");
  }

  public void goToBookSimple(Train paramTrain)
  {
    this.app.putParms("train", paramTrain);
    this.app.putParms("chainQuery", this.cq);
    this.app.putParms("passengers", this.passengerService.getCurrUsers());
    Intent localIntent = new Intent(this, BookActivity.class);
    localIntent.putExtra("seatType", this.seatType);
    startActivity(localIntent);
  }

  public void goToResign(ChainQuery paramChainQuery, Train paramTrain, String paramString, boolean paramBoolean)
  {
    this.mtrain = paramTrain;
    this.mNoQueryOrder = paramBoolean;
    this.seatType = paramString;
    checkForLogin(paramTrain.getApiVersion(), 2130903158, "请先登陆！");
  }

  public void goToResignSimple(Train paramTrain)
  {
    this.app.putParms("train", paramTrain);
    this.app.putParms("chainQuery", this.cq);
    this.app.putParms("order", this.order);
    this.app.putParms("noQueryOrder", Boolean.valueOf(this.mNoQueryOrder));
    Intent localIntent = new Intent(this, ResignBookActivity.class);
    localIntent.putExtra("seatType", this.seatType);
    startActivity(localIntent);
  }

  public void init()
  {
    super.init();
    this.sv = ((HorizontalScrollView)findViewById(2131297113));
    this.ticketListView = ((ListView)findViewById(2131297116));
    this.mTV_LeaveDate = ((TextView)findViewById(2131296669));
    setClick(2131297115, this);
    setClick(2131297114, this);
    setClick(2131296669, this);
    setClick(2131297112, StationActivity.class);
    setClick(2131296259, this);
    setClick(2131297113, this);
    if (getIntent().getBooleanExtra("halfway", false))
    {
      this.hasFootView = false;
      this.cq = ((ChainQuery)this.app.getParms("cq_halfway"));
      if (this.cq == null)
        this.cq = new ChainQuery();
      this.oldLeaveDate = this.cq.getLeaveDate();
      this.mtrain = ((Train)this.app.getParms("train_halfway"));
      this.app.parms.remove("cq_halfway");
      this.app.parms.remove("train_halfway");
    }
    while (true)
    {
      queryTrainInfo();
      setTv(2131296679, this.mtrain.getCode());
      setTv(2131297111, this.mtrain.getFrom() + " 至 " + this.mtrain.getTo());
      setTextViewLeaveDate();
      this.mAdapter = new ResultTicketListAdapter(this, this.mtrain.getSeats(), 2130903097, this.cq, this.mtrain, this.order, false);
      this.ticketListView.setAdapter(this.mAdapter);
      return;
      try
      {
        ChainQuery localChainQuery = (ChainQuery)this.app.getParms("cq");
        if (localChainQuery == null)
          localChainQuery = new ChainQuery();
        this.cq = localChainQuery.clone();
        this.oldLeaveDate = this.cq.getLeaveDate();
        label378: this.mtrain = ((Train)this.app.getParms("train"));
        if (this.mtrain == null)
          this.mtrain = new Train();
        this.cq.setTrainNo(this.mtrain.getCode());
        this.order = ((ArrayList)this.app.getParms("order"));
        if (this.order == null)
        {
          this.hasFootView = true;
          addListFoot();
          continue;
        }
        this.hasFootView = false;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        break label378;
      }
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 2131296669) && (paramInt2 != 0))
    {
      this.oldLeaveDate = this.cq.getLeaveDate();
      Date localDate = (Date)paramIntent.getSerializableExtra("date");
      this.cq.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").format(localDate));
      setTextViewLeaveDate();
      query();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131297115:
    case 2131297114:
    case 2131296669:
    case 2131296259:
    case 2131297104:
    }
    do
    {
      return;
      this.oldLeaveDate = this.cq.getLeaveDate();
      this.cq.addDate(1);
      setTextViewLeaveDate();
      query();
      return;
      this.oldLeaveDate = this.cq.getLeaveDate();
      this.cq.addDate(-1);
      setTextViewLeaveDate();
      query();
      return;
      goDatePickActivity(c.roundDate(new Date()), this.cq.getLeavedate2(), 2, 2131296669);
      return;
      query();
      return;
    }
    while (this.mTrainInfo == null);
    Intent localIntent = new Intent(this, HalfwayTicketActivity.class);
    this.app.putParms("cq", this.cq);
    this.app.putParms("train", this.mtrain);
    localIntent.putExtra("trainInfo", this.mTrainInfo);
    startActivity(localIntent);
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2130903181)
      goToBookSimple(this.mtrain);
    if (paramInt == 2130903158)
      goToResignSimple(this.mtrain);
    super.onLoginSuccess(paramInt);
  }

  protected void queryTrainInfo()
  {
    new MyAsyncTask(this, false)
    {
      protected TrainInfo myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        List localList = TrainDetailActivity.this.app.getHC().arrStationInfo(TrainDetailActivity.this.mtrain);
        return new TrainInfo(TrainDetailActivity.this.mtrain.getCode(), localList);
      }

      protected void myPostExecute(TrainInfo paramTrainInfo)
      {
        TrainDetailActivity.access$202(TrainDetailActivity.this, paramTrainInfo);
        if (paramTrainInfo.getTrainStations() != null)
        {
          TrainDetailActivity.this.sv.addView(TrainDetailActivity.this.generateTrainStationsList(paramTrainInfo.getTrainStations()));
          TrainDetailActivity.this.sv.setVisibility(4);
          TrainDetailActivity.this.sv.postDelayed(new Runnable()
          {
            public void run()
            {
              TrainDetailActivity.this.sv.scrollBy(TrainDetailActivity.this.startStation * TrainDetailActivity.this.dip2px(90) + TrainDetailActivity.this.dip2px(20), 0);
              TrainDetailActivity.this.sv.setVisibility(0);
            }
          }
          , 10L);
          return;
        }
        TrainDetailActivity.this.sv.setVisibility(8);
        TrainDetailActivity.access$702(TrainDetailActivity.this, false);
        TrainDetailActivity.this.ticketListView.removeFooterView(TrainDetailActivity.this.footView);
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
        TrainDetailActivity.this.finish();
      }
    }
    .execute(new String[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.TrainDetailActivity
 * JD-Core Version:    0.6.0
 */