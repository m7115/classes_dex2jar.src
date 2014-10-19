package com.example.pathview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import cn.suanya.hc.service.PathService;
import com.baidu.location.LocationClient;
import com.example.pathview.bean.RecentTrain;
import com.example.pathview.bean.TrainInfo;
import com.example.pathview.bean.TrainPosition;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.view.MyToast;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class ResultActivity extends BaseActivity
  implements View.OnClickListener, Observer
{
  private View llTitle;
  protected int mGpsOrTime = 0;
  private int mGpsWrongCount = 0;
  private View mLayoutGpsStart;
  private View mLayoutGpsStop;
  private View mLayoutLate;
  private View mLayoutProgress;
  private PathService mPathService;
  private DemoView mPathView;
  private Handler mPositionHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      ResultActivity.this.updatePosition(ResultActivity.this.mTrainPositions);
    }
  };
  private ScrollView mScrollView;
  private boolean mScrollViewFlag = true;
  private Timer mTimer;
  protected String mTrainCode;
  protected TrainInfo mTrainInfo;
  private List<TrainPosition> mTrainPositions;
  private TextView tvTitle;

  private void gpsStart()
  {
    this.mGpsOrTime = 1;
    this.mScrollViewFlag = true;
    this.mLayoutGpsStop.setVisibility(0);
    this.mLayoutGpsStart.setVisibility(8);
    this.mLayoutProgress.setVisibility(0);
    if (this.mTrainInfo != null)
    {
      this.mPathService.startLocation(new LocationClient(this), this.mTrainInfo);
      return;
    }
    Toast.makeText(this, "启动出错，请重新查询", 0).show();
    finish();
  }

  private void gpsStop()
  {
    this.mScrollViewFlag = true;
    this.mLayoutGpsStart.setVisibility(0);
    this.mLayoutGpsStop.setVisibility(8);
    this.mLayoutProgress.setVisibility(8);
    this.mPathService.stopLocation();
  }

  private void timerStop()
  {
    if (this.mTimer != null)
    {
      this.mTimer.cancel();
      this.mTimer = null;
    }
  }

  private void updatePosition(List<TrainPosition> paramList)
  {
    if (paramList == null);
    do
    {
      return;
      this.mPathView.showPosition(paramList);
    }
    while ((paramList.isEmpty()) || (!this.mScrollViewFlag));
    this.mScrollViewFlag = false;
    new CountDownTimer(500L, 1L, (int)(this.mPathView.getDensity() * (120 + 120 * ((TrainPosition)paramList.get(0)).getStationIndex() - 120.0F * ((TrainPosition)paramList.get(0)).getScale())) - getWindowManager().getDefaultDisplay().getHeight() / 2)
    {
      public void onFinish()
      {
        ResultActivity.this.mScrollView.scrollTo(0, this.val$scrollY);
      }

      public void onTick(long paramLong)
      {
        ResultActivity.this.mScrollView.scrollTo(0, (int)(this.val$scrollY - paramLong));
      }
    }
    .start();
  }

  protected int getMainLayout()
  {
    return 2130903062;
  }

  public int getScrollTop()
  {
    return this.mScrollView.getScrollY();
  }

  public void initTitleView(String paramString)
  {
    this.llTitle = findViewById(2131296697);
    this.tvTitle = ((TextView)findViewById(2131296258));
    if ((paramString != null) && (!paramString.equals("")))
    {
      this.tvTitle.setText(paramString);
      this.llTitle.setVisibility(0);
    }
  }

  protected void loadResult(TrainInfo paramTrainInfo)
  {
    if (paramTrainInfo == null)
    {
      Toast.makeText(this, "没有此车次，请重新查询", 0).show();
      finish();
      return;
    }
    List localList = paramTrainInfo.getTrainStations();
    RecentTrain localRecentTrain = new RecentTrain(paramTrainInfo.getCode(), ((TrainStationInfo)localList.get(0)).getName(), ((TrainStationInfo)localList.get(0)).getLeaveTime(), ((TrainStationInfo)localList.get(-1 + localList.size())).getName(), ((TrainStationInfo)localList.get(-1 + localList.size())).getArrTime(), System.currentTimeMillis(), 0);
    new Handler().post(new Runnable(localRecentTrain)
    {
      public void run()
      {
        try
        {
          ResultActivity.this.mPathService.addRecentTrain(this.val$recentTrain);
          return;
        }
        catch (Exception localException)
        {
        }
      }
    });
    this.mTrainInfo = paramTrainInfo;
    this.mPathView = new DemoView(this);
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    this.mPathView.setLayoutParams(localLayoutParams);
    this.mPathView.setTrain(this.mTrainInfo);
    this.mPathView.showView();
    this.mScrollView.addView(this.mPathView);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296759:
      showToast("请点击站点图中的站点图标");
      return;
    case 2131296757:
      if (this.app.sp.getBoolean("GpsStartTip", true))
      {
        String str = this.app.launchInfo.optString("GpsStartTip", "如果您正在乘坐该趟列车，可以通过开启定位功能来获得火车运行情况。");
        new MyAlertDialog.Builder(this).setTitle("提示").setMessage(Html.fromHtml(str)).setCheckBoxText("不再提示").setCheckBoxListener(new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
          {
            if (paramBoolean)
            {
              ResultActivity.this.app.sp.edit().putBoolean("GpsStartTip", false).commit();
              return;
            }
            ResultActivity.this.app.sp.edit().putBoolean("GpsStartTip", true).commit();
          }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            ResultActivity.this.gpsStart();
            ResultActivity.this.timerStop();
          }
        }).show();
        return;
      }
      gpsStart();
      timerStop();
      return;
    case 2131296758:
    }
    gpsStop();
    timerStart();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mScrollView = ((ScrollView)findViewById(2131296732));
    this.mLayoutLate = findViewById(2131296759);
    this.mLayoutLate.setOnClickListener(this);
    this.mLayoutGpsStart = findViewById(2131296757);
    this.mLayoutGpsStart.setOnClickListener(this);
    this.mLayoutGpsStop = findViewById(2131296758);
    this.mLayoutGpsStop.setOnClickListener(this);
    this.mLayoutProgress = findViewById(2131296760);
    this.mPathService = PathService.getInstance();
    this.mGpsOrTime = 0;
    this.mTrainCode = getIntent().getStringExtra("code");
    queryTrainInfo();
  }

  protected void onPause()
  {
    this.app.pathObservable.deleteObserver(this);
    if (this.mGpsOrTime == 0)
      timerStop();
    while (true)
    {
      super.onPause();
      return;
      gpsStop();
    }
  }

  protected void onResume()
  {
    this.app.pathObservable.addObserver(this);
    if (this.mGpsOrTime == 0)
      timerStart();
    while (true)
    {
      super.onResume();
      return;
      gpsStart();
    }
  }

  protected void onRuleMessage(int paramInt, String paramString)
  {
    if ((paramInt == 101) && (this.mPathView != null) && (this.mPathView.getSignView() != null))
    {
      String str = this.mPathView.getSignView().getSign();
      if ((str == null) || (str.length() == 0))
        this.mPathView.getSignView().setSign(paramString);
    }
    super.onRuleMessage(paramInt, paramString);
  }

  protected void queryTrainInfo()
  {
    3 local3 = new MyAsyncTask(this)
    {
      protected TrainInfo myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return ResultActivity.this.mPathService.getTrainInfoByCode(paramArrayOfString[0]);
      }

      protected void myPostExecute(TrainInfo paramTrainInfo)
      {
        ResultActivity.this.loadResult(paramTrainInfo);
        if (ResultActivity.this.mGpsOrTime == 0)
          ResultActivity.this.timerStart();
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
        ResultActivity.this.finish();
      }
    };
    this.mTrainCode = getIntent().getStringExtra("code");
    if ((this.mTrainCode == null) || (this.mTrainCode.equals("")))
    {
      Toast.makeText(this, "车次有误", 1).show();
      finish();
      return;
    }
    setTitle(this.mTrainCode);
    initTitleView(this.mTrainCode);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = this.mTrainCode;
    local3.execute(arrayOfString);
  }

  protected void timerStart()
  {
    this.mGpsOrTime = 0;
    if (this.mTimer != null)
      this.mTimer.cancel();
    this.mTimer = new Timer();
    this.mTimer.schedule(new TimerTask()
    {
      public void run()
      {
        ResultActivity.access$202(ResultActivity.this, ResultActivity.this.mPathService.getTrainPosition(ResultActivity.this.mTrainInfo));
        ResultActivity.this.mPositionHandler.sendEmptyMessage(0);
      }
    }
    , 0L, 60000L);
  }

  public void update(Observable paramObservable, Object paramObject)
  {
    if (paramObject == null)
    {
      this.mGpsWrongCount = (1 + this.mGpsWrongCount);
      if (this.mGpsWrongCount >= 2)
      {
        gpsStop();
        timerStart();
        MyToast.makeText(this, "定位出错，检测到你不在此列车上", 1).show();
        this.mGpsWrongCount = 0;
      }
      return;
    }
    this.mGpsWrongCount = 0;
    this.mLayoutProgress.setVisibility(8);
    updatePosition((List)paramObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.ResultActivity
 * JD-Core Version:    0.6.0
 */