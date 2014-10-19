package com.example.pathview;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import cn.suanya.hc.service.PathService;
import com.example.pathview.bean.AlarmStation;
import com.example.pathview.bean.TrainInfo;
import com.example.pathview.bean.TrainPosition;
import com.example.pathview.util.TimeUtil;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheBase;
import com.yipiao.view.MyAlertDialog;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.view.MyToast;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@SuppressLint({"DrawAllocation"})
public class DemoView extends View
{
  private Canvas mCanvas;
  private float mDensity = getResources().getDisplayMetrics().density;
  private GestureDetector mGestureDetector = new GestureDetector(getContext(), new MyGestureListener(null));
  private float mLineHeight = 120.0F;
  private float mLineLength;
  private float mLineWidth = 12.0F;
  private List<TrainPosition> mPositionList;
  private float mStationHeight;
  private Bitmap mStationIcon = BitmapFactory.decodeResource(getResources(), 2130837657);
  private float mStationWidth;
  private Bitmap mTimeIconArr = BitmapFactory.decodeResource(getResources(), 2130837653);
  private Bitmap mTimeIconOrg = BitmapFactory.decodeResource(getResources(), 2130837656);
  private float mTimeIconWidth;
  private TrainInfo mTrainInfo;
  private Bitmap mUserIconLeft = BitmapFactory.decodeResource(getResources(), 2130837659);
  private Bitmap mUserIconRight = BitmapFactory.decodeResource(getResources(), 2130837660);
  private float mXLeft = 60.0F;
  private float mXRight;
  private float mYTop = 120.0F;
  private Paint mpaint;
  private SYSignView signView;
  private int windowWidth;

  public DemoView(android.content.Context paramContext)
  {
    super(paramContext);
  }

  public DemoView(android.content.Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void addStationAlarm(AlarmStation paramAlarmStation)
  {
    AlarmManager localAlarmManager = (AlarmManager)getContext().getSystemService("alarm");
    Intent localIntent = new Intent();
    localIntent.setAction("com.yipiao.action.setalarm");
    Bundle localBundle = new Bundle();
    localBundle.putString("name", paramAlarmStation.getName());
    localBundle.putString("code", paramAlarmStation.getCode());
    localBundle.putInt("index", paramAlarmStation.getIndex());
    localBundle.putString("alarmTime", paramAlarmStation.getAlarmTime());
    localBundle.putBoolean("voice", paramAlarmStation.isVoice());
    localBundle.putBoolean("vibrate", paramAlarmStation.isVibrate());
    localIntent.putExtras(localBundle);
    int i = Integer.valueOf(paramAlarmStation.getCode().hashCode() + "" + paramAlarmStation.getIndex()).intValue();
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(getContext(), i, localIntent, 134217728);
    localAlarmManager.set(0, TimeUtil.getMinsByStr(paramAlarmStation.getArriveTime()) - TimeUtil.getMinsByStr(paramAlarmStation.getAlarmTime()), localPendingIntent);
    Toast.makeText(getContext(), "设置成功", 0).show();
  }

  private void deleteStationAlarm(AlarmStation paramAlarmStation)
  {
    AlarmManager localAlarmManager = (AlarmManager)getContext().getSystemService("alarm");
    Intent localIntent = new Intent(getContext(), StationAlarmReceiver.class);
    localIntent.setAction("com.yipiao.action.setalarm");
    int i = Integer.valueOf(paramAlarmStation.getCode().hashCode() + "" + paramAlarmStation.getIndex()).intValue();
    localAlarmManager.cancel(PendingIntent.getBroadcast(getContext(), i, localIntent, 134217728));
  }

  private void drawDesc(int paramInt)
  {
    TrainStationInfo localTrainStationInfo = (TrainStationInfo)this.mTrainInfo.getTrainStations().get(paramInt);
    float[] arrayOfFloat1 = getStationPoint(paramInt);
    int i = getTopTrainNum(paramInt);
    int j = stationWithTrain(paramInt);
    float f1;
    float f2;
    if (i % 2 == 0)
      switch (j)
      {
      default:
        f1 = 4.0F + this.mStationWidth / 2.0F;
        f2 = -this.mStationWidth / 2.0F;
      case 1:
      case 2:
      }
    float[] arrayOfFloat2;
    while (true)
    {
      arrayOfFloat2 = new float[2];
      arrayOfFloat1[0] += f1 * this.mDensity;
      arrayOfFloat1[1] += f2 * this.mDensity;
      this.mpaint.setColor(-7829368);
      this.mpaint.setAntiAlias(true);
      this.mpaint.setTextSize(14.0F * this.mDensity);
      this.mCanvas.drawText(localTrainStationInfo.getName(), arrayOfFloat2[0], arrayOfFloat2[1] + 14.0F * this.mDensity, this.mpaint);
      this.mpaint.setTextSize(12.0F * this.mDensity);
      arrayOfFloat2[1] += 18.0F * this.mDensity;
      if (paramInt > 0)
      {
        this.mCanvas.drawBitmap(this.mTimeIconArr, arrayOfFloat2[0], arrayOfFloat2[1], this.mpaint);
        this.mCanvas.drawText(localTrainStationInfo.getArrTime(), arrayOfFloat2[0] + this.mTimeIconWidth * this.mDensity, arrayOfFloat2[1] + 9.0F * this.mDensity, this.mpaint);
        arrayOfFloat2[1] += 14.0F * this.mDensity;
      }
      if (paramInt < -1 + this.mTrainInfo.getTrainStations().size())
      {
        this.mCanvas.drawBitmap(this.mTimeIconOrg, arrayOfFloat2[0], arrayOfFloat2[1], this.mpaint);
        this.mCanvas.drawText(localTrainStationInfo.getLeaveTime(), arrayOfFloat2[0] + this.mTimeIconWidth * this.mDensity, arrayOfFloat2[1] + 9.0F * this.mDensity, this.mpaint);
      }
      if ((localTrainStationInfo.getZwd() != null) && (localTrainStationInfo.getZwd().length() > 0))
      {
        this.mpaint.setColor(Color.parseColor("#CC3300"));
        arrayOfFloat2[1] += 14.0F * this.mDensity;
        if (paramInt != 0)
          break;
        Canvas localCanvas2 = this.mCanvas;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localTrainStationInfo.getZwd();
        localCanvas2.drawText(MessageFormat.format("预计{0}出发", arrayOfObject2), arrayOfFloat2[0], arrayOfFloat2[1] + 9.0F * this.mDensity, this.mpaint);
      }
      return;
      f1 = -this.mStationWidth / 2.0F;
      f2 = 4.0F + this.mStationHeight / 2.0F;
      continue;
      f1 = -this.mStationWidth / 2.0F;
      f2 = -this.mStationHeight / 2.0F - 52.0F;
      continue;
      switch (j)
      {
      default:
        f1 = -this.mStationWidth / 2.0F - 52.0F;
        f2 = -this.mStationHeight / 2.0F;
        break;
      case 1:
        f1 = -this.mStationWidth / 2.0F;
        f2 = 4.0F + this.mStationHeight / 2.0F;
        break;
      case 2:
        f1 = -this.mStationWidth / 2.0F;
        f2 = -this.mStationHeight / 2.0F - 52.0F;
      }
    }
    Canvas localCanvas1 = this.mCanvas;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = localTrainStationInfo.getZwd();
    localCanvas1.drawText(MessageFormat.format("预计{0}到达", arrayOfObject1), arrayOfFloat2[0], arrayOfFloat2[1] + 9.0F * this.mDensity, this.mpaint);
  }

  private void drawDescs()
  {
    this.mpaint.setStrokeWidth(0.0F);
    for (int i = 0; i < this.mTrainInfo.getTrainStations().size(); i++)
      drawDesc(i);
  }

  private void drawLines()
  {
    this.mpaint.setColor(Color.rgb(164, 205, 221));
    this.mpaint.setStrokeWidth(this.mLineWidth * this.mDensity);
    float[] arrayOfFloat1 = getStationPoint(0);
    int i = 1;
    float[] arrayOfFloat2;
    for (Object localObject = arrayOfFloat1; i < this.mTrainInfo.getTrainStations().size(); localObject = arrayOfFloat2)
    {
      arrayOfFloat2 = getStationPoint(i);
      this.mCanvas.drawLine(localObject[0], localObject[1], arrayOfFloat2[0], arrayOfFloat2[1], this.mpaint);
      i++;
    }
  }

  private void drawStations()
  {
    for (int i = 0; i < this.mTrainInfo.getTrainStations().size(); i++)
    {
      float[] arrayOfFloat = getStationPoint(i);
      this.mCanvas.drawBitmap(this.mStationIcon, arrayOfFloat[0] - this.mStationWidth * this.mDensity / 2.0F, arrayOfFloat[1] - this.mStationHeight * this.mDensity / 2.0F, this.mpaint);
    }
  }

  private void drawTrains()
  {
    if (this.mPositionList == null)
      return;
    int i = 0;
    label10: TrainPosition localTrainPosition;
    float f1;
    float f2;
    if (i < this.mPositionList.size())
    {
      localTrainPosition = (TrainPosition)this.mPositionList.get(i);
      int j = localTrainPosition.getStationIndex();
      int k = getTopTrainNum(j);
      f1 = this.mLineHeight * (j - k) * this.mDensity + this.mYTop * this.mDensity;
      if (k % 2 != 1)
        break label157;
      f2 = (this.mXLeft + (1.0F - localTrainPosition.getScale()) * this.mLineLength) * this.mDensity;
    }
    for (Bitmap localBitmap = this.mUserIconRight; ; localBitmap = this.mUserIconLeft)
    {
      this.mCanvas.drawBitmap(localBitmap, f2 - localBitmap.getWidth() / 2, f1 - localBitmap.getHeight(), this.mpaint);
      i++;
      break label10;
      break;
      label157: f2 = (this.mXLeft + localTrainPosition.getScale() * this.mLineLength) * this.mDensity;
    }
  }

  private int getLateTime(double paramDouble, int paramInt)
  {
    ((TrainStationInfo)this.mTrainInfo.getTrainStations().get(paramInt - 1)).getLeaveTime();
    ((TrainStationInfo)this.mTrainInfo.getTrainStations().get(paramInt)).getArrTime();
    return 0;
  }

  private float[] getStationPoint(int paramInt)
  {
    int i = getTopTrainNum(paramInt);
    float f1 = this.mXLeft * this.mDensity;
    float f2 = this.mLineHeight * (paramInt - i) * this.mDensity + this.mYTop * this.mDensity;
    if (i % 2 == 1)
      f1 = (this.mXLeft + this.mLineLength) * this.mDensity;
    return new float[] { f1, f2 };
  }

  private int getTopTrainNum(int paramInt)
  {
    if (this.mPositionList == null)
      return 0;
    Iterator localIterator = this.mPositionList.iterator();
    int i = 0;
    if (localIterator.hasNext())
      if (((TrainPosition)localIterator.next()).getStationIndex() > paramInt)
        break label59;
    label59: for (int j = i + 1; ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  private void initSize()
  {
    this.windowWidth = getRootView().getWidth();
    this.mDensity = getResources().getDisplayMetrics().density;
    this.mStationWidth = (this.mStationIcon.getWidth() / this.mDensity);
    this.mStationHeight = (this.mStationIcon.getHeight() / this.mDensity);
    this.mXLeft = 60.0F;
    this.mYTop = 120.0F;
    this.mLineWidth = 12.0F;
    this.mLineHeight = 120.0F;
    this.mLineLength = (this.windowWidth / this.mDensity - 2.0F * this.mXLeft);
    this.mTimeIconWidth = (this.mTimeIconArr.getWidth() / this.mDensity);
    this.mXRight = (this.mXLeft + this.mLineLength + this.mStationWidth);
  }

  private void onClick(float paramFloat1, float paramFloat2)
  {
    for (int i = 0; ; i++)
    {
      if (i < this.mTrainInfo.getTrainStations().size())
      {
        float[] arrayOfFloat = getStationPoint(i);
        if ((Math.abs((arrayOfFloat[0] - paramFloat1) / this.mDensity) >= 40.0F) || (Math.abs((arrayOfFloat[1] - paramFloat2) / this.mDensity) >= 40.0F))
          continue;
        if (!YipiaoApplication.getApp().launchInfo.optBoolean("zwdSign", true))
          showZwdMessage(i, "");
      }
      else
      {
        return;
      }
      showZwdDialog(i);
      return;
    }
  }

  private int scollToIndex()
  {
    if ((this.mPositionList == null) || (this.mPositionList.isEmpty()))
      return 0;
    return ((TrainPosition)this.mPositionList.get(0)).getStationIndex();
  }

  private void showMessage(int paramInt, boolean paramBoolean)
  {
    android.content.Context localContext = getContext();
    TrainStationInfo localTrainStationInfo = (TrainStationInfo)this.mTrainInfo.getTrainStations().get(paramInt);
    if ((localContext instanceof ResultActivity))
    {
      int i = ((ResultActivity)localContext).getScrollTop();
      View localView = LayoutInflater.from(localContext).inflate(2130903144, null);
      TextView localTextView = (TextView)localView.findViewById(2131297023);
      Button localButton = (Button)localView.findViewById(2131297024);
      PopupWindow localPopupWindow = new PopupWindow(localView, 280, 250);
      localPopupWindow.setFocusable(false);
      localPopupWindow.setOutsideTouchable(true);
      localPopupWindow.setBackgroundDrawable(new BitmapDrawable());
      int j = localPopupWindow.getWidth();
      int k = localPopupWindow.getHeight();
      int m;
      if (paramBoolean)
      {
        localView.setBackgroundResource(2130837654);
        m = (int)((this.mXLeft + this.mStationWidth / 2.0F) * this.mDensity);
      }
      for (int n = (int)(this.mLineHeight * (paramInt + 1) * this.mDensity) + k / 2; ; n = (int)(this.mLineHeight * paramInt * this.mDensity + k / 2))
      {
        localPopupWindow.showAtLocation(this, 51, m, n - i);
        localView.setOnClickListener(new View.OnClickListener(localPopupWindow)
        {
          public void onClick(View paramView)
          {
            if (this.val$pw.isShowing())
              this.val$pw.dismiss();
          }
        });
        return;
        String str = TimeUtil.minute2HHmm(0 + TimeUtil.getMinsByStr(localTrainStationInfo.getArrTime()));
        localTextView.setText("火车晚点" + TimeUtil.minute2HHmm(0) + " 预计" + str + "到达此站");
        localView.setBackgroundResource(2130837655);
        localButton.setOnClickListener(new View.OnClickListener(paramInt, localTrainStationInfo, str)
        {
          public void onClick(View paramView)
          {
            View localView = LayoutInflater.from(DemoView.this.getContext()).inflate(2130903075, null);
            TimePicker localTimePicker = (TimePicker)localView.findViewById(2131296806);
            localTimePicker.setIs24HourView(Boolean.valueOf(true));
            CheckBox localCheckBox1 = (CheckBox)localView.findViewById(2131296807);
            CheckBox localCheckBox2 = (CheckBox)localView.findViewById(2131296808);
            boolean bool = PathService.getInstance().isAlarmStation(DemoView.this.mTrainInfo.getCode(), this.val$index);
            if (bool)
            {
              AlarmStation localAlarmStation = PathService.getInstance().getAlarmStation(DemoView.this.mTrainInfo.getCode(), this.val$index);
              String str = localAlarmStation.getAlarmTime();
              localTimePicker.setCurrentHour(Integer.valueOf(str.split(":")[0]));
              localTimePicker.setCurrentMinute(Integer.valueOf(str.split(":")[1]));
              localCheckBox1.setChecked(localAlarmStation.isVoice());
              localCheckBox2.setChecked(localAlarmStation.isVibrate());
            }
            while (true)
            {
              AlertDialog.Builder localBuilder = new AlertDialog.Builder(DemoView.this.getContext());
              localBuilder.setView(localView);
              localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener(localTimePicker, localCheckBox1, localCheckBox2, bool)
              {
                public void onClick(DialogInterface paramDialogInterface, int paramInt)
                {
                  AlarmStation localAlarmStation = new AlarmStation(DemoView.this.mTrainInfo.getCode(), DemoView.5.this.val$index, DemoView.5.this.val$station.getStation().getName(), DemoView.5.this.val$beArriveTime, this.val$timePicker.getCurrentHour() + ":" + this.val$timePicker.getCurrentMinute(), this.val$cbVoice.isChecked(), this.val$cbVibrate.isChecked());
                  if (this.val$isAlarmStation)
                  {
                    PathService.getInstance().updateAlarmStation(localAlarmStation);
                    DemoView.this.deleteStationAlarm(localAlarmStation);
                  }
                  while (true)
                  {
                    DemoView.this.addStationAlarm(localAlarmStation);
                    return;
                    PathService.getInstance().addAlarmStation(localAlarmStation);
                    DemoView.this.invalidate();
                  }
                }
              });
              localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramDialogInterface, int paramInt)
                {
                  paramDialogInterface.dismiss();
                }
              });
              if (bool)
                localBuilder.setNeutralButton("不提醒", new DialogInterface.OnClickListener(localTimePicker, localCheckBox1, localCheckBox2)
                {
                  public void onClick(DialogInterface paramDialogInterface, int paramInt)
                  {
                    AlarmStation localAlarmStation = new AlarmStation(DemoView.this.mTrainInfo.getCode(), DemoView.5.this.val$index, DemoView.5.this.val$station.getStation().getName(), DemoView.5.this.val$beArriveTime, this.val$timePicker.getCurrentHour() + ":" + this.val$timePicker.getCurrentMinute(), this.val$cbVoice.isChecked(), this.val$cbVibrate.isChecked());
                    PathService.getInstance().deleteAlarmStation(DemoView.this.mTrainInfo.getCode(), DemoView.5.this.val$index);
                    DemoView.this.deleteStationAlarm(localAlarmStation);
                    Toast.makeText(DemoView.this.getContext(), "设置成功", 0).show();
                    DemoView.this.invalidate();
                  }
                });
              localBuilder.create().show();
              return;
              localTimePicker.setCurrentHour(Integer.valueOf(0));
              localTimePicker.setCurrentMinute(Integer.valueOf(15));
              localCheckBox1.setChecked(true);
              localCheckBox2.setChecked(true);
            }
          }
        });
        m = (int)(this.mXRight * this.mDensity) - j - (int)(this.mStationWidth / 2.0F * this.mDensity);
      }
    }
    Toast.makeText(localContext, "出错啦！请稍候重试！", 1).show();
  }

  private void showZwdDialog(int paramInt)
  {
    HuocheBase localHuocheBase = YipiaoApplication.getApp().getHC();
    TrainStationInfo localTrainStationInfo = (TrainStationInfo)this.mTrainInfo.getTrainStations().get(paramInt);
    View localView = LayoutInflater.from(getContext()).inflate(2130903079, null);
    MyAlertDialog localMyAlertDialog = new MyAlertDialog.Builder(getContext()).showByCustomView(localView);
    this.signView = ((SYSignView)localView.findViewById(2131296771));
    this.signView.init(2130903166, new SYSignView.SignListenerBase(localHuocheBase)
    {
      public SYSignView.MulImage load()
        throws Exception
      {
        return this.val$hc.zwdQuerySign();
      }
    }
    , false);
    this.signView.refreshSign();
    InputMethodManager localInputMethodManager = (InputMethodManager)getContext().getSystemService("input_method");
    ((TextView)localView.findViewById(2131296828)).setText("正在查询 " + this.mTrainInfo.getCode() + " 次列车到达 " + localTrainStationInfo.getName() + " 的正晚点信息。");
    ((Button)localView.findViewById(2131296830)).setOnClickListener(new View.OnClickListener(localInputMethodManager, paramInt, localMyAlertDialog)
    {
      public void onClick(View paramView)
      {
        if (DemoView.this.signView.getSign().equals(""))
        {
          MyToast.makeText(DemoView.this.getContext(), "请输入验证码", 0).show();
          return;
        }
        this.val$imm.hideSoftInputFromWindow(DemoView.this.signView.mText.getWindowToken(), 0);
        DemoView.this.showZwdMessage(this.val$i, DemoView.this.signView.getSign());
        this.val$dialog.dismiss();
      }
    });
    ((Button)localView.findViewById(2131296832)).setOnClickListener(new View.OnClickListener(localMyAlertDialog)
    {
      public void onClick(View paramView)
      {
        this.val$dialog.dismiss();
      }
    });
  }

  private void showZwdMessage(int paramInt, String paramString)
  {
    HuocheBase localHuocheBase = YipiaoApplication.getApp().getHC();
    TrainStationInfo localTrainStationInfo = (TrainStationInfo)this.mTrainInfo.getTrainStations().get(paramInt);
    new MyAsyncTask(getContext(), true, localHuocheBase, localTrainStationInfo, paramString, paramInt)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Void[] paramArrayOfVoid)
        throws Exception
      {
        Huoche localHuoche = this.val$hc;
        String str1 = DemoView.this.mTrainInfo.getCode();
        TrainStationInfo localTrainStationInfo = this.val$station;
        String str2 = this.val$randCode;
        if (this.val$i == 0);
        for (boolean bool = true; ; bool = false)
          return localHuoche.zwdQuery(str1, localTrainStationInfo, str2, bool);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        this.val$station.setZwd(paramContext.getStr("time"));
        DemoView.this.invalidate();
        String str = paramContext.getStr("desc");
        new MyAlertDialog.Builder(DemoView.this.getContext()).setTitle("正晚点查询结果").setMessage(Html.fromHtml(str)).setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            paramDialogInterface.dismiss();
          }
        }).show();
      }
    }
    .execute(new Void[0]);
  }

  private int stationWithTrain(int paramInt)
  {
    if (this.mPositionList == null)
      return 0;
    Iterator localIterator = this.mPositionList.iterator();
    while (localIterator.hasNext())
    {
      TrainPosition localTrainPosition = (TrainPosition)localIterator.next();
      if (localTrainPosition.getStationIndex() == paramInt)
        return 2;
      if (localTrainPosition.getStationIndex() - paramInt == 1)
        return 1;
    }
    return 0;
  }

  public float getDensity()
  {
    return this.mDensity;
  }

  public SYSignView getSignView()
  {
    return this.signView;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    initSize();
    this.mCanvas = paramCanvas;
    this.mpaint = new Paint();
    drawLines();
    drawStations();
    drawDescs();
    drawTrains();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(2 * getRootView().getWidth(), (int)((2.0F * this.mYTop + (-1 + this.mTrainInfo.getTrainStations().size()) * this.mLineHeight) * this.mDensity));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mGestureDetector.onTouchEvent(paramMotionEvent);
  }

  public void setTrain(TrainInfo paramTrainInfo)
  {
    this.mTrainInfo = paramTrainInfo;
  }

  public void showPosition(List<TrainPosition> paramList)
  {
    this.mPositionList = paramList;
    invalidate();
  }

  public void showView()
  {
    invalidate();
  }

  private class MyGestureListener
    implements GestureDetector.OnGestureListener
  {
    private MyGestureListener()
    {
    }

    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return true;
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return true;
    }

    public void onLongPress(MotionEvent paramMotionEvent)
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return true;
    }

    public void onShowPress(MotionEvent paramMotionEvent)
    {
    }

    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      DemoView.this.onClick(paramMotionEvent.getX(), paramMotionEvent.getY());
      return true;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.DemoView
 * JD-Core Version:    0.6.0
 */