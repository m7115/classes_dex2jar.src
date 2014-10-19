package com.yipiao.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.suanya.common.a.c;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import com.yipiao.Config;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.OrderItem;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.view.MyAlertDialog.Builder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

public class MonitorSetActivity extends BaseActivity
  implements Observer
{
  private TextView edit_arrivestation;
  private TextView edit_startstation;
  protected Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      MonitorSetActivity.this.onHandleMessage(paramMessage);
    }
  };
  private MonitorInfo mi;
  private NoteList monitorSeedsList;
  private TextView seatNumTV;
  private TextView seatTypeTV;
  private Button startBt;
  private Button stopBt;

  private void onHandleMessage(Message paramMessage)
  {
  }

  private void onMonitorModify()
  {
    if (this.mi.isRuning())
      this.app.saveMonitorPool();
  }

  private void setDetailLeaveTv(String paramString1, String paramString2)
  {
    TextView localTextView = (TextView)findViewById(2131296897);
    if (("00:00".equals(paramString1)) && ("24:00".equals(paramString2)));
    for (int i = 1; i != 0; i = 0)
    {
      localTextView.setVisibility(8);
      return;
    }
    localTextView.setText(paramString1 + "--" + paramString2);
    localTextView.setVisibility(0);
  }

  private void setSpeedDialog(int paramInt1, int paramInt2)
  {
    new MyAlertDialog.Builder(this).setSingleChoiceItems(this.monitorSeedsList.getLabels(), paramInt2, new DialogInterface.OnClickListener(paramInt1)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        Note localNote = MonitorSetActivity.this.monitorSeedsList.get(paramInt);
        switch (this.val$id)
        {
        case 2131296902:
        default:
        case 2131296901:
        case 2131296903:
        }
        while (true)
        {
          MonitorSetActivity.this.showSpeed();
          paramDialogInterface.dismiss();
          MonitorSetActivity.this.onMonitorModify();
          return;
          MonitorSetActivity.this.mi.setSpeedMultiple(Integer.valueOf(localNote.getCode()));
          continue;
          MonitorSetActivity.this.mi.setSpeedMultiple2(Integer.valueOf(localNote.getCode()));
        }
      }
    }).show();
  }

  private void showAutoBook()
  {
    checked(2131296908, this.mi.isAutoBook());
  }

  private void showHistoryOrder()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = this.mi.getOrders().iterator();
    while (localIterator.hasNext())
    {
      OrderItem localOrderItem = (OrderItem)localIterator.next();
      localStringBuffer.append("<br/>").append(localOrderItem.getName() + '(' + localOrderItem.getTrainNo() + '/' + localOrderItem.getSeatType() + ')');
    }
    if (localStringBuffer.length() > 1)
      localStringBuffer.delete(0, 5);
    setTv(2131296910, Html.fromHtml(localStringBuffer.toString()));
  }

  private void showPassenger()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = this.mi.getPassengers().iterator();
    while (localIterator.hasNext())
    {
      UserInfo localUserInfo = (UserInfo)localIterator.next();
      localStringBuffer.append(",").append(localUserInfo.getName());
    }
    if (localStringBuffer.length() > 1)
      localStringBuffer.delete(0, 1);
    setTv(2131296779, localStringBuffer.toString());
  }

  private void showSpeed()
  {
    String str1 = this.mi.getSpeedMultiple().toString();
    Note localNote1 = this.monitorSeedsList.getByCode(str1);
    if (localNote1 == null)
      localNote1 = this.monitorSeedsList.get(0);
    setTv(2131296902, localNote1.getName());
    String str2 = this.mi.getSpeedMultiple2().toString();
    Note localNote2 = this.monitorSeedsList.getByCode(str2);
    if (localNote2 == null)
      localNote2 = this.monitorSeedsList.get(0);
    setTv(2131296904, localNote2.getName());
  }

  protected int getMainLayout()
  {
    return 2130903109;
  }

  protected void numSelect(Integer paramInteger)
  {
    this.seatNumTV.setText(paramInteger.toString());
    onMonitorModify();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2131296778)
      showPassenger();
    if ((paramInt1 == 2131296896) && (paramInt2 != 0))
    {
      Date localDate = (Date)paramIntent.getSerializableExtra("currentDate");
      String str1 = (String)paramIntent.getSerializableExtra("begin");
      String str2 = (String)paramIntent.getSerializableExtra("end");
      String str3 = new SimpleDateFormat("yyyy-MM-dd").format(localDate);
      this.mi.getCq().setLeaveDate(str3);
      this.mi.getCq().setTimeRangBegin(str1);
      this.mi.getCq().setTimeRangEnd(str2);
      setTv(2131296691, str3);
      setDetailLeaveTv(str1, str2);
    }
    if ((paramInt1 == 2131296892) && (paramInt2 != 0))
      setCqArr((StationNode)paramIntent.getExtras().get("DATA"));
    if ((paramInt1 == 2131296894) && (paramInt2 != 0))
      setCqOrg((StationNode)paramIntent.getExtras().get("DATA"));
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    int j;
    int k;
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296801:
      StationNode localStationNode1 = this.mi.getCq().getArr();
      StationNode localStationNode2 = this.mi.getCq().getOrg();
      this.mi.getCq().setArr(localStationNode2);
      this.mi.getCq().setOrg(localStationNode1);
      this.edit_arrivestation.setText(this.mi.getCq().getArr().getName());
      this.edit_startstation.setText(this.mi.getCq().getOrg().getName());
      return;
    case 2131296892:
      noteFilterDialog(2131296892);
      return;
    case 2131296894:
      noteFilterDialog(2131296894);
      return;
    case 2131296898:
      new MyAsyncTask(this)
      {
        protected Object myInBackground(String[] paramArrayOfString)
          throws Exception
        {
          if ((MonitorSetActivity.this.mi.getCq().findResults() != null) && (!MonitorSetActivity.this.mi.getCq().findResults().isEmpty()))
            return MonitorSetActivity.this.mi.getCq().findResults();
          return MonitorSetActivity.this.getHc().queryPiaoCommon(MonitorSetActivity.this.mi.getCq());
        }

        protected void myPostExecute(Object paramObject)
        {
          if ((paramObject instanceof List))
            MonitorSetActivity.this.mi.getCq().setResults((List)paramObject);
          MonitorSetActivity.this.showDialogTrainCodeMul(MonitorSetActivity.this.mi.allTrainLabel(), MonitorSetActivity.this.mi.trainSelect());
        }
      }
      .execute(new String[0]);
      return;
    case 2131296899:
      showDialogSeatTypeAllMul(this.mi.getSeatTypes());
      return;
    case 2131296900:
      showDialogNumSelect(-1 + this.mi.getSeatNum().intValue());
      return;
    case 2131296896:
      goDateTimePickActivity(c.roundDate(new Date()), this.mi.getCq().getLeavedate2(), this.mi.getCq().getTimeRangBegin(), this.mi.getCq().getTimeRangEnd(), 2, 2131296896);
      return;
    case 2131296763:
      this.mi.setVoiceNotify(checkClick(2131296826));
      onMonitorModify();
      return;
    case 2131296911:
      this.mi.setShakeNotify(checkClick(2131296912));
      onMonitorModify();
      return;
    case 2131296905:
      onMonitorModify();
      return;
    case 2131296901:
      String str2 = this.mi.getSpeedMultiple().toString();
      j = this.monitorSeedsList.posByCode(str2);
      k = 0;
      if (j >= 0)
        break;
    case 2131296903:
    case 2131296907:
    case 2131296908:
    }
    while (true)
    {
      setSpeedDialog(2131296901, k);
      return;
      String str1 = this.mi.getSpeedMultiple2().toString();
      int i = this.monitorSeedsList.posByCode(str1);
      if (i < 0);
      setSpeedDialog(2131296903, i);
      return;
      MonitorInfo localMonitorInfo = this.mi;
      boolean bool1 = this.mi.isAutoBook();
      boolean bool2 = false;
      if (bool1);
      while (true)
      {
        localMonitorInfo.setAutoBook(bool2);
        showAutoBook();
        return;
        bool2 = true;
      }
      k = j;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (checkNeedLaunch())
      return;
    if (!checkShare())
    {
      this.monitorSeedsList = Config.getInstance().monitorSeeds;
      this.mi = ((MonitorInfo)this.app.getParms("mi"));
      this.app.putParms("miPassenger", this.mi.getPassengers());
      this.edit_startstation = ((TextView)findViewById(2131296893));
      this.edit_startstation.setText(this.mi.getCq().getOrg().getName());
      this.edit_arrivestation = ((TextView)findViewById(2131296895));
      this.edit_arrivestation.setText(this.mi.getCq().getArr().getName());
      setTv(2131296691, this.mi.getCq().getLeaveDate());
      setDetailLeaveTv(this.mi.getCq().getTimeRangBegin(), this.mi.getCq().getTimeRangEnd());
      setTv(2131296884, this.mi.selectTrainLink());
      showAutoBook();
      showSpeed();
      checked(2131296826, this.mi.isVoiceNotify());
      checked(2131296912, this.mi.isShakeNotify());
      this.seatTypeTV = setTv(2131296768, this.mi.getSeatTypes().linkName(","));
      this.seatNumTV = setTv(2131296770, this.mi.getSeatNum().toString());
      setClick(2131296896, this);
      if (!this.mi.isResign())
      {
        setClick(2131296892, this);
        setClick(2131296894, this);
        setClick(2131296801, this);
      }
      setClick(2131296898, this);
      setClick(2131296899, this);
      setClick(2131296900, this);
      setClick(2131296763, this);
      setClick(2131296911, this);
      setClick(2131296905, this);
      setClick(2131296901, this);
      setClick(2131296903, this);
      setClick(2131296778, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent(MonitorSetActivity.this, MonitorPassengerSetActivity.class);
          MonitorSetActivity.this.app.putParms("passengers", MonitorSetActivity.this.mi.getPassengers());
          MonitorSetActivity.this.startActivityForResult(localIntent, 2131296778);
        }
      });
      if (!this.mi.isResign())
        break label570;
      setVisibility(2131296778, 8);
      setVisibility(2131296909, 0);
      showHistoryOrder();
      label436: setClick(2131296907, this);
      setClick(2131296908, this);
      this.startBt = ((Button)setClick(2131296885, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          MonitorSetActivity.this.checkForLogin(2131296885);
        }
      }));
      this.stopBt = ((Button)setClick(2131296886, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          MonitorSetActivity.this.app.stopMonitor(MonitorSetActivity.this.mi);
          MonitorSetActivity.this.mi.putLog("监控已经停止！");
          MonitorSetActivity.this.showToast("监控已经停止！");
          MonitorSetActivity.this.startBt.setVisibility(0);
          MonitorSetActivity.this.stopBt.setVisibility(8);
          MonitorSetActivity.this.app.saveMonitorPool();
          MonitorSetActivity.this.toMonitorList();
        }
      }));
      if (!this.mi.isRuning())
        break label596;
      this.startBt.setVisibility(8);
      this.stopBt.setVisibility(0);
    }
    while (true)
    {
      setClick(2131296913, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          Intent localIntent = new Intent(MonitorSetActivity.this, MonitorSeatListActivity.class);
          MonitorSetActivity.this.app.putParms("mi", MonitorSetActivity.this.mi);
          MonitorSetActivity.this.startActivity(localIntent);
        }
      });
      setClick(2131296259, new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          MonitorSetActivity.this.app.getMonitorPool().remove(MonitorSetActivity.this.mi);
          MonitorSetActivity.this.app.saveMonitorPool();
          MonitorSetActivity.this.finish();
        }
      });
      return;
      this.monitorSeedsList = Config.getInstance().monitorSeeds2;
      break;
      label570: setVisibility(2131296778, 0);
      setVisibility(2131296909, 8);
      showPassenger();
      break label436;
      label596: this.startBt.setVisibility(0);
      this.stopBt.setVisibility(8);
    }
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2131296885)
    {
      if ((this.app.runMonitorNum() >= 4) && (!checkShare()))
      {
        showToast(this.app.launchInfo.optString("monitor.running.number4", "最多只能同时运行4个监控，可分享到微信获取更多监控数量！"));
        return;
      }
      if (this.app.runMonitorNum() >= 8)
      {
        showToast("最多只能同时运行8个监控，请先停止其他监控！");
        return;
      }
      this.mi = this.app.addMonitor(this.mi);
      this.mi.setNextRunTime(0L);
      String str = "开始监控:" + this.mi.getCq().getLeaveDate() + this.mi.getCq().getOrg().getName() + "-" + this.mi.getCq().getArr().getName();
      this.mi.putLog(str);
      showToast(str);
      logToServer("addMonitor", str);
      this.startBt.setVisibility(8);
      this.stopBt.setVisibility(0);
      this.app.saveMonitorPool();
      reStartMonitor();
      toMonitorList();
    }
    super.onLoginSuccess(paramInt);
  }

  protected void onPause()
  {
    this.app.observable.deleteObserver(this);
    super.onPause();
  }

  protected void onResume()
  {
    this.app.observable.addObserver(this);
    super.onResume();
  }

  public void reStartMonitor()
  {
    this.app.startFirstMonitorService(100L + System.currentTimeMillis());
  }

  protected void seatTypeSelectMul(NoteList paramNoteList)
  {
    this.mi.setSeatTypes(paramNoteList);
    this.seatTypeTV.setText(this.mi.getSeatTypes().linkName(","));
    super.seatTypeSelectMul(paramNoteList);
    onMonitorModify();
  }

  protected void setCqArr(StationNode paramStationNode)
  {
    if (paramStationNode.equals(this.mi.getCq().getArr()))
    {
      this.mi.getCq().setArr(this.mi.getCq().getOrg());
      this.edit_arrivestation.setText(this.mi.getCq().getOrg().getName());
    }
    this.mi.getCq().setOrg(paramStationNode);
    this.edit_startstation.setText(paramStationNode.getName());
  }

  protected void setCqOrg(StationNode paramStationNode)
  {
    if (paramStationNode.equals(this.mi.getCq().getOrg()))
    {
      this.mi.getCq().setOrg(this.mi.getCq().getArr());
      this.edit_startstation.setText(this.mi.getCq().getArr().getName());
    }
    this.mi.getCq().setArr(paramStationNode);
    this.edit_arrivestation.setText(paramStationNode.getName());
  }

  public void toMonitorList()
  {
    MainTab.currentTab = 2131296875;
    startActivity(new Intent(this, MainTab.class));
    finish();
  }

  protected void trainCodeSelectMul(boolean[] paramArrayOfBoolean)
  {
    this.mi.updateTrainSelect(paramArrayOfBoolean);
    setTv(2131296884, this.mi.selectTrainLink());
  }

  public void update(Observable paramObservable, Object paramObject)
  {
    this.mHandler.sendEmptyMessage(0);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.MonitorSetActivity
 * JD-Core Version:    0.6.0
 */