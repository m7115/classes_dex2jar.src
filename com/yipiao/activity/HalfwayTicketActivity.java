package com.yipiao.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.pathview.bean.TrainInfo;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.HalfwayTicketListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HalfwayTicketActivity extends BaseActivity
  implements AdapterView.OnItemClickListener
{
  private Handler adapterHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      HalfwayTicketActivity.this.mAdapter.setMlist(HalfwayTicketActivity.this.trainList);
      HalfwayTicketActivity.this.mAdapter.notifyIndex();
      HalfwayTicketActivity.this.mAdapter.notifyDataSetChanged();
    }
  };
  private ChainQuery cq;
  private volatile boolean isQuerying = true;
  private List<TrainStationInfo> list;
  private HalfwayTicketListAdapter mAdapter;
  private Button mButton;
  private int mLastStation;
  private ListView mListView;
  private Train mTrain;
  private TrainInfo mTrainInfo;
  private Button priceButton;
  private List<Train> trainList;

  private void filterStation()
  {
    Iterator localIterator = this.mTrainInfo.getTrainStations().iterator();
    while ((localIterator.hasNext()) && (!((TrainStationInfo)localIterator.next()).getName().equals(this.mTrain.getFrom())));
    while (true)
    {
      TrainStationInfo localTrainStationInfo2;
      if (localIterator.hasNext())
      {
        localTrainStationInfo2 = (TrainStationInfo)localIterator.next();
        if (!localTrainStationInfo2.getName().equals(this.mTrain.getTo()));
      }
      else
      {
        for (this.mLastStation = 0; (localIterator.hasNext()) && (this.mLastStation < 3); this.mLastStation = (1 + this.mLastStation))
        {
          TrainStationInfo localTrainStationInfo1 = (TrainStationInfo)localIterator.next();
          this.list.add(localTrainStationInfo1);
          Train localTrain1 = new Train();
          localTrain1.setTo(localTrainStationInfo1.getName());
          this.trainList.add(localTrain1);
        }
      }
      this.list.add(localTrainStationInfo2);
      Train localTrain2 = new Train();
      localTrain2.setTo(localTrainStationInfo2.getName());
      this.trainList.add(localTrain2);
    }
    if (this.list.size() > 1)
    {
      Collections.reverse(this.list);
      Collections.reverse(this.trainList);
    }
    if (this.list.isEmpty())
    {
      Toast.makeText(this, "中途没有站点", 0).show();
      finish();
    }
  }

  private void initView()
  {
    filterStation();
    this.priceButton = ((Button)findViewById(2131296842));
    this.priceButton.setOnClickListener(this);
    this.mButton = ((Button)findViewById(2131296259));
    this.mButton.setOnClickListener(this);
    this.mListView = ((ListView)findViewById(2131296840));
    this.mAdapter = new HalfwayTicketListAdapter(this, this.trainList, 2130903092, this.mTrain, this.mLastStation);
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnItemClickListener(this);
  }

  private void query()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        new ChainQuery();
        try
        {
          ChainQuery localChainQuery = HalfwayTicketActivity.this.cq.clone();
          Huoche localHuoche = HalfwayTicketActivity.this.getHc();
          Iterator localIterator = HalfwayTicketActivity.this.list.iterator();
          int i = -1;
          while (localIterator.hasNext())
          {
            TrainStationInfo localTrainStationInfo = (TrainStationInfo)localIterator.next();
            if (!HalfwayTicketActivity.this.isQuerying)
              break;
            i++;
            localChainQuery.setArr(localTrainStationInfo.getStation());
            List localList = localHuoche.queryForAdvanced(localChainQuery);
            if ((localList != null) && (localList.size() != 0))
            {
              Train localTrain = (Train)localList.get(0);
              HalfwayTicketActivity.this.trainList.set(i, localTrain);
            }
            HalfwayTicketActivity.this.adapterHandler.obtainMessage().sendToTarget();
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
          return;
        }
        finally
        {
          HalfwayTicketActivity.this.showViewOnThread();
        }
        throw localObject;
      }
    }).start();
  }

  private void showPrice()
  {
    if (this.mAdapter.isShowPrice())
    {
      this.mAdapter.setShowPrice(false);
      this.priceButton.setText("显示价格");
    }
    while (true)
    {
      this.mAdapter.notifyDataSetChanged();
      return;
      this.mAdapter.setShowPrice(true);
      this.priceButton.setText("显示座位");
    }
  }

  private void showViewOnThread()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        HalfwayTicketActivity.this.mButton.setText("刷新");
        HalfwayTicketActivity.access$602(HalfwayTicketActivity.this, false);
        HalfwayTicketActivity.this.mAdapter.setQuery(false);
        HalfwayTicketActivity.this.mAdapter.notifyDataSetChanged();
      }
    });
  }

  protected int getMainLayout()
  {
    return 2130903082;
  }

  public void goToBook(Train paramTrain)
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

  protected void init()
  {
    ChainQuery localChainQuery = (ChainQuery)this.app.getParms("cq");
    if (localChainQuery == null)
      localChainQuery = new ChainQuery();
    try
    {
      this.cq = localChainQuery.clone();
      this.mTrain = ((Train)this.app.getParms("train"));
      this.mTrainInfo = ((TrainInfo)getIntent().getSerializableExtra("trainInfo"));
      this.list = new ArrayList();
      this.trainList = new ArrayList();
      super.init();
      setTv(2131296679, this.cq.getTrainNo() + "到" + this.mTrain.getTo());
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          HalfwayTicketActivity.this.initView();
          HalfwayTicketActivity.this.query();
        }
      }
      , 55L);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      while (true)
        localCloneNotSupportedException.printStackTrace();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296259:
      if (this.isQuerying)
      {
        this.mButton.setText("刷新");
        this.isQuerying = false;
        return;
      }
      this.mButton.setText("停止");
      this.isQuerying = true;
      this.mAdapter.setIndex(-1);
      this.mAdapter.setQuery(true);
      this.mAdapter.notifyDataSetChanged();
      query();
      return;
    case 2131296842:
    }
    showPrice();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    Train localTrain = (Train)paramView.getTag();
    if (localTrain == null)
      return;
    Intent localIntent = new Intent(this, TrainDetailActivity.class);
    this.app.putParms("train_halfway", localTrain);
    this.cq.setArr(((TrainStationInfo)this.list.get(paramInt)).getStation());
    this.app.putParms("cq_halfway", this.cq);
    localIntent.putExtra("halfway", true);
    startActivity(localIntent);
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2130903181)
      goToBookSimple(this.mTrain);
    super.onLoginSuccess(paramInt);
  }

  protected void onPause()
  {
    this.isQuerying = false;
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.HalfwayTicketActivity
 * JD-Core Version:    0.6.0
 */