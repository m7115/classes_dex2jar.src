package com.yipiao.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.suanya.common.a.n;
import cn.suanya.common.a.v;
import cn.suanya.common.net.Cookie;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import cn.suanya.common.ui.WEBActivity;
import com.yipiao.Config;
import com.yipiao.Constants;
import com.yipiao.RuleMessage;
import com.yipiao.YipiaoApplication;
import com.yipiao.activity.CityListActivity;
import com.yipiao.activity.DatePickActivity;
import com.yipiao.activity.DateTimePickActivity;
import com.yipiao.activity.FlightWEBActivity;
import com.yipiao.activity.LaunchActivity;
import com.yipiao.activity.MainTab;
import com.yipiao.activity.MyWebActivity;
import com.yipiao.activity.NoteFilterActivity;
import com.yipiao.activity.NoteQueryActivity;
import com.yipiao.activity.NoteSimpleActivity;
import com.yipiao.activity.UserSetActivity;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.service.DownloadService;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;
import com.yipiao.service.MonitorBusiness;
import com.yipiao.service.PassengerService;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.MyAlertDialog;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.view.MyProgressDialog;
import com.yipiao.view.MyToast;
import com.yipiao.wxapi.DisplayHelper;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BaseActivity extends Activity
  implements View.OnClickListener
{
  protected static final int dialog_cardType_select = 105;
  protected static final int dialog_enterYear_select = 10020;
  protected static final int dialog_num_select = 100;
  protected static final int dialog_num_select_par = 200;
  protected static final int dialog_passengerType_select = 108;
  protected static final int dialog_schoolSystem_select = 10010;
  protected static final int dialog_seatTypeH_select = 114;
  protected static final int dialog_seatTypeL_select = 124;
  protected static final int dialog_seatType_mul = 101;
  protected static final int dialog_seatType_mul_par = 201;
  protected static final int dialog_seatType_select = 104;
  protected static final int dialog_sexType_select = 107;
  protected static final int dialog_tickType_select = 106;
  protected static final int dialog_timeRang_select = 103;
  protected static final int dialog_trainCode_mul_par = 207;
  protected static final int dialog_trainType_mul = 102;
  protected static final int dialog_trainType_mul_par = 202;
  static String mEmei = null;
  protected static boolean[] mulSelect;
  private boolean _isChecked = false;
  protected YipiaoApplication app;
  protected Config cfg;
  protected int dialog_select_index = 0;
  private long last_key_back = 0L;
  protected Handler mLaunchHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      BaseActivity.this.onLaunch(paramMessage);
    }
  };
  protected Dialog mProgressDialog;
  private TextView mRemark;
  protected Handler mRuleHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      RuleMessage localRuleMessage = (RuleMessage)paramMessage.obj;
      if (localRuleMessage.getType() > 100)
        BaseActivity.this.onRuleMessage(localRuleMessage.getType(), localRuleMessage.getMessage());
      while (true)
      {
        return;
        if (localRuleMessage.getType() != RuleMessage.type_process)
          break;
        if ((BaseActivity.this.app.mProgressDialog == null) || (!BaseActivity.this.app.mProgressDialog.isShowing()))
          continue;
        BaseActivity.this.app.mProgressDialog.setMessage(localRuleMessage.getMessage());
        return;
      }
      if (localRuleMessage.getType() == RuleMessage.type_toast)
      {
        BaseActivity.this.showToast(localRuleMessage.getMessage());
        return;
      }
      MyAlertDialog.Builder localBuilder = new MyAlertDialog.Builder(BaseActivity.this.getTopActivity());
      localBuilder.setTitle(localRuleMessage.getTitle()).setMessage(localRuleMessage.getMessage());
      localBuilder.setPositiveButton(localRuleMessage.getConfirm(), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.app.glob.put(RuleMessage.result_key, RuleMessage.result_confirm);
          synchronized (BaseActivity.this.app.glob)
          {
            BaseActivity.this.app.glob.notify();
            return;
          }
        }
      });
      if (localRuleMessage.getType() == RuleMessage.type_confirm)
        localBuilder.setNegativeButton(localRuleMessage.getCancle(), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            BaseActivity.this.app.glob.put(RuleMessage.result_key, RuleMessage.result_cancle);
            synchronized (BaseActivity.this.app.glob)
            {
              BaseActivity.this.app.glob.notify();
              return;
            }
          }
        });
      MyAlertDialog localMyAlertDialog = localBuilder.create();
      localMyAlertDialog.setCancelable(false);
      localMyAlertDialog.show();
    }
  };
  protected View mTitle;
  private Observer observer = new Observer()
  {
    public void update(Observable paramObservable, Object paramObject)
    {
      BaseActivity.this.mLaunchHandler.sendEmptyMessage(0);
    }
  };
  public PassengerService passengerService;
  private int req_id_for_login;
  protected Observer ruleObserver = new Observer()
  {
    public void update(Observable paramObservable, Object paramObject)
    {
      Message localMessage = new Message();
      localMessage.obj = paramObject;
      BaseActivity.this.mRuleHandler.sendMessage(localMessage);
    }
  };

  private void sexTypeSelect(Note paramNote)
  {
    setTv(2131296651, paramNote.getName());
  }

  private void showTip()
  {
    String str1 = getAcitveName() + "_tip";
    String str2 = this.app.launchInfo.optString(str1);
    String[] arrayOfString;
    String str3;
    if (str2 != null)
    {
      arrayOfString = str2.split("\\|");
      if (arrayOfString.length >= 4)
      {
        str3 = arrayOfString[1];
        if (this.app.sp.getString(str1, "0").compareTo(str3) < 0)
          break label81;
      }
    }
    return;
    label81: String str4 = arrayOfString[2];
    String str5 = arrayOfString[3];
    new MyAlertDialog.Builder(this).setTitle(str4).setMessage(Html.fromHtml(str5)).setCheckBoxText("不再提示").setCheckBoxListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
      {
        BaseActivity.access$002(BaseActivity.this, paramBoolean);
      }
    }).setPositiveButton("确定", new DialogInterface.OnClickListener(str1, str3)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        if (BaseActivity.this._isChecked)
          BaseActivity.this.app.sp.edit().putString(this.val$name, this.val$tipNo).commit();
      }
    }).show();
  }

  public boolean autoLoginForMobile(int paramInt, String paramString)
  {
    new MyAsyncTask(this, true, paramInt, paramString)
    {
      protected LoginUser myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        HuocheMobile.getInstance().autoLogin();
        return null;
      }

      protected void myPostExecute(LoginUser paramLoginUser)
      {
        BaseActivity.this.onHasLogined(this.val$reqId);
      }

      protected void onException(Exception paramException)
      {
        BaseActivity.this.goLoginActivity(3, this.val$reqId, this.val$message);
        super.onException(paramException);
      }
    }
    .execute(new Object[0]);
    return false;
  }

  protected void cardTypeSelect(Note paramNote)
  {
    setTv(2131296652, paramNote.getName());
  }

  public boolean checkClick(int paramInt)
  {
    ImageView localImageView = (ImageView)findViewById(paramInt);
    Object localObject = localImageView.getTag();
    if (localObject != null);
    for (boolean bool1 = ((Boolean)localObject).booleanValue(); ; bool1 = false)
    {
      boolean bool2;
      if (!bool1)
      {
        bool2 = true;
        if (!bool2)
          break label65;
        localImageView.setImageResource(2130837610);
      }
      while (true)
      {
        localImageView.setTag(Boolean.valueOf(bool2));
        return bool2;
        bool2 = false;
        break;
        label65: localImageView.setImageResource(2130837611);
      }
    }
  }

  public boolean checkForLogin(int paramInt)
  {
    return checkForLogin(this.app.getApiVersion(), paramInt, null);
  }

  public boolean checkForLogin(int paramInt1, int paramInt2)
  {
    return checkForLogin(paramInt1, paramInt2, null);
  }

  public boolean checkForLogin(int paramInt1, int paramInt2, String paramString)
  {
    if (this.app.isVisitor())
    {
      goLoginActivity(paramInt1, paramInt2, paramString);
      return false;
    }
    if (paramInt1 == 3)
      return autoLoginForMobile(paramInt2, paramString);
    return loginForNewApi(paramInt2, paramString);
  }

  public boolean checkForLogin(int paramInt, String paramString)
  {
    return checkForLogin(this.app.getApiVersion(), paramInt, paramString);
  }

  public boolean checkNeedLaunch()
  {
    if (this.app.getParmsCount() == 0)
    {
      n.b("checkNeedLaunch--Need");
      startActivity(new Intent(this, LaunchActivity.class));
      finish();
      return true;
    }
    return false;
  }

  protected boolean checkShare()
  {
    return System.currentTimeMillis() - this.app.sp.getLong("shareTime", 0L) <= 172800000L;
  }

  protected void checkUpgrade()
  {
    int i = this.app.launchInfo.optInt("upgrade", 0);
    if (i > 0)
    {
      MyAlertDialog localMyAlertDialog = new MyAlertDialog.Builder(this).setMessage(Html.fromHtml(this.app.launchInfo.optString("upgradeRemark", ""))).setPositiveButton("以后再说", new DialogInterface.OnClickListener(i)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          paramDialogInterface.dismiss();
          if (this.val$upgrade == 2)
            BaseActivity.this.finish();
        }
      }).setNegativeButton("立即升级", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          paramDialogInterface.dismiss();
          new InstallManager(BaseActivity.this, BaseActivity.this.app.launchInfo.optString(Constants.conf_downLoad_address, "")).start();
        }
      }).create();
      if (i > 1)
        localMyAlertDialog.setCancelable(false);
      localMyAlertDialog.setCanceledOnTouchOutside(false);
      localMyAlertDialog.show();
    }
  }

  public void checked(int paramInt, boolean paramBoolean)
  {
    ImageView localImageView = (ImageView)findViewById(paramInt);
    if (paramBoolean)
      localImageView.setImageResource(2130837610);
    while (true)
    {
      localImageView.setTag(Boolean.valueOf(paramBoolean));
      return;
      localImageView.setImageResource(2130837611);
    }
  }

  public void checkedSingle(int paramInt, boolean paramBoolean)
  {
    ImageView localImageView = (ImageView)findViewById(paramInt);
    if (paramBoolean)
    {
      localImageView.setImageResource(2130837734);
      return;
    }
    localImageView.setImageResource(2130837736);
  }

  public void dialogShareToWX()
  {
    String str1 = this.app.launchInfo.optString("wx.share.description2", "分享智行火车票,体验超高速监控功能，增加最大监控条数到8条。每次分享有效期为48小时。");
    String str2 = this.app.launchInfo.optString("wx.share.title2", "推荐智行火车票超级监控功能，春节抢票无压力！");
    DisplayHelper.shareAppToWX(this, this.app.api, str2, str1);
  }

  protected void dismissProgressDialog()
  {
    try
    {
      if ((this.mProgressDialog != null) && (this.mProgressDialog.isShowing()))
        this.mProgressDialog.dismiss();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void downloadApk(android.content.Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    int i = v.a();
    if (i == 0)
    {
      showToast("无网络！请联网后再进行升级");
      return;
    }
    if (i == 1)
    {
      Intent localIntent = new Intent(paramContext, DownloadService.class);
      localIntent.putExtra("url", paramString1);
      localIntent.putExtra("id", paramInt);
      localIntent.putExtra("name", paramString2);
      startService(localIntent);
      return;
    }
    new MyAlertDialog.Builder(this).setMessage("您当前使用的是2G/3G网络，是否确认下载。").setPositiveButton("确认", new DialogInterface.OnClickListener(paramContext, paramString1, paramInt, paramString2)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        Intent localIntent = new Intent(this.val$context, DownloadService.class);
        localIntent.putExtra("url", this.val$url);
        localIntent.putExtra("id", this.val$id);
        localIntent.putExtra("name", this.val$name);
        BaseActivity.this.startService(localIntent);
      }
    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
      }
    }).show();
  }

  protected void enterYearSelect(Note paramNote)
  {
    setTv(2131296985, paramNote.getName());
  }

  protected String getAcitveName()
  {
    String str1 = getClass().getName();
    if (str1.lastIndexOf('.') > 0)
      str1 = str1.substring(1 + str1.lastIndexOf('.'));
    String str2 = str1.substring(0, 1).toLowerCase() + str1.substring(1);
    if (str2.endsWith("Activity"))
      str2 = str2.substring(0, -8 + str2.length());
    return str2;
  }

  protected String getDefautRemark()
  {
    return "";
  }

  public String getEmei()
  {
    return this.app.getEmei();
  }

  public Huoche getHc()
  {
    return this.app.getHC();
  }

  public Huoche getHc(int paramInt)
  {
    if (paramInt == 2)
      return HuocheNew.getInstance();
    return HuocheMobile.getInstance();
  }

  public YipiaoApplication getLocalApp()
  {
    return (YipiaoApplication)getApplication();
  }

  protected int getMainLayout()
  {
    return 0;
  }

  public Activity getTopActivity()
  {
    while (getParent() != null)
      this = getParent();
    return this;
  }

  public String getVString(int paramInt)
  {
    View localView = findViewById(paramInt);
    if ((localView instanceof TextView))
      return ((TextView)localView).getText().toString();
    return "";
  }

  protected String getVersionName()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return "2.0";
  }

  protected void goDatePickActivity(Date paramDate1, Date paramDate2, int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(this, DatePickActivity.class);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(2, paramInt1);
    Date localDate = localCalendar.getTime();
    localIntent.putExtra("minDate", paramDate1);
    localIntent.putExtra("maxDate", localDate);
    localIntent.putExtra("selectedDate", paramDate2);
    startActivityForResult(localIntent, paramInt2);
  }

  protected void goDateTimePickActivity(Date paramDate1, Date paramDate2, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(this, DateTimePickActivity.class);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(2, paramInt1);
    Date localDate = localCalendar.getTime();
    localIntent.putExtra("minDate", paramDate1);
    localIntent.putExtra("maxDate", localDate);
    localIntent.putExtra("selectedDate", paramDate2);
    localIntent.putExtra("begin", paramString1);
    localIntent.putExtra("end", paramString2);
    startActivityForResult(localIntent, paramInt2);
  }

  protected void goFlightWEBActivity(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(this, FlightWEBActivity.class);
    localIntent.putExtra("url", paramString1);
    localIntent.putExtra("webTip", this.app.launchInfo.optString("flight_web_tip", "机票数据由去哪儿网独家提供"));
    if (paramString2 != null)
      localIntent.putExtra("cookies", paramString2);
    if (paramArrayOfByte != null)
      localIntent.putExtra("postPar", paramArrayOfByte);
    startActivity(localIntent);
  }

  public void goLoginActivity(int paramInt1, int paramInt2, String paramString)
  {
    this.req_id_for_login = paramInt2;
    if (paramString != null)
      showToast(paramString);
    Intent localIntent = new Intent(this, UserSetActivity.class);
    localIntent.putExtra("apiVersion", paramInt1);
    if (paramInt2 == 0)
    {
      startActivity(localIntent);
      return;
    }
    startActivityForResult(localIntent, paramInt2);
  }

  protected void goWEB12306Activity(String paramString)
  {
    goWEB12306Activity(paramString, false);
  }

  protected void goWEB12306Activity(String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent(this, WEBActivity.class);
    localIntent.putExtra("url", paramString);
    localIntent.putExtra("needLogin", paramBoolean);
    Object localObject1 = "";
    List localList = HuocheNew.getInstance().httpClient.getCookies(Constants.url12306New);
    if ((localList != null) && (localList.size() > 0))
    {
      Iterator localIterator = localList.iterator();
      Cookie localCookie;
      for (Object localObject2 = localObject1; localIterator.hasNext(); localObject2 = (String)localObject2 + Constants.url12306New + "|" + localCookie.toString() + ";")
        localCookie = (Cookie)localIterator.next();
      localObject1 = localObject2;
    }
    if ((localObject1 != null) && (((String)localObject1).length() > 1))
      localIntent.putExtra("cookies", (String)localObject1);
    startActivity(localIntent);
  }

  protected void goWebActivity(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(this, MyWebActivity.class);
    localIntent.putExtra("title", paramString1);
    localIntent.putExtra("url", paramString2);
    if (paramString3 != null)
      localIntent.putExtra("cookies", paramString3);
    if (paramArrayOfByte != null)
      localIntent.putExtra("postPar", paramArrayOfByte);
    startActivity(localIntent);
  }

  protected void goWebActivity(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    goWebActivity(paramString1, paramString2, paramArrayOfByte, WEBActivity.class);
  }

  protected void goWebActivity(String paramString1, String paramString2, byte[] paramArrayOfByte, Class paramClass)
  {
    if (paramClass != null);
    while (true)
    {
      Intent localIntent = new Intent(this, paramClass);
      localIntent.putExtra("url", paramString1);
      if (paramString2 != null)
        localIntent.putExtra("cookies", paramString2);
      if (paramArrayOfByte != null)
        localIntent.putExtra("postPar", paramArrayOfByte);
      startActivity(localIntent);
      return;
      paramClass = WEBActivity.class;
    }
  }

  protected void init()
  {
    initLeftBtn();
  }

  protected void initLeftBtn()
  {
    View localView = findViewById(2131296261);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          BaseActivity.this.onBackClick();
        }
      });
  }

  public boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() < 1);
  }

  public boolean isEmptyV(int paramInt)
  {
    View localView = findViewById(paramInt);
    if ((localView instanceof TextView))
      return isEmpty(((TextView)localView).getText().toString());
    return false;
  }

  public void logToServer(LogInfo paramLogInfo)
  {
    YipiaoService.getInstance().asyncLog(paramLogInfo);
  }

  public void logToServer(String paramString1, String paramString2)
  {
    logToServer(new LogInfo(paramString1, paramString2));
  }

  public boolean loginForNewApi(int paramInt, String paramString)
  {
    new MyAsyncTask(this, true, paramInt, paramString)
    {
      protected Boolean myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return Boolean.valueOf(HuocheNew.getInstance().isLogined());
      }

      protected void myPostExecute(Boolean paramBoolean)
      {
        if (paramBoolean.booleanValue())
        {
          BaseActivity.this.onHasLogined(this.val$reqId);
          return;
        }
        BaseActivity.this.goLoginActivity(2, this.val$reqId, this.val$message);
      }
    }
    .execute(new Object[0]);
    return false;
  }

  public void loginOut()
  {
    new MyAsyncTask(this)
    {
      protected Object myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        BaseActivity.this.getHc().loginOut();
        return null;
      }

      protected void myPostExecute(Object paramObject)
      {
        Intent localIntent = new Intent(BaseActivity.this, UserSetActivity.class);
        BaseActivity.this.startActivity(localIntent);
      }
    }
    .execute(new String[0]);
  }

  public void monitorWaitForQuery()
  {
    MonitorBusiness.waitFor(this.app.launchInfo.optInt("monitorWaitForQuery", 10000));
  }

  public void noteFilterDialog(int paramInt)
  {
    startActivityForResult(new Intent(this, CityListActivity.class), paramInt);
  }

  public void noteFilterDialog(int paramInt, NoteList paramNoteList)
  {
    this.app.putParms("orgNoteList", paramNoteList);
    startActivityForResult(new Intent(this, NoteFilterActivity.class), paramInt);
  }

  public void noteQueryDialog(int paramInt, NoteList paramNoteList)
  {
    this.app.putParms("orgNoteList", paramNoteList);
    startActivityForResult(new Intent(this, NoteQueryActivity.class), paramInt);
  }

  public void noteSimpleDialog(int paramInt, NoteList paramNoteList)
  {
    this.app.putParms("noteList", paramNoteList);
    startActivityForResult(new Intent(this, NoteSimpleActivity.class), paramInt);
  }

  protected void numSelect(Integer paramInteger)
  {
    showToast(paramInteger.toString());
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == this.req_id_for_login)
    {
      if (paramInt2 != 0)
        onLoginSuccess(paramInt1);
    }
    else
      return;
    onLoginCanceled(paramInt1);
  }

  protected void onBackClick()
  {
    finish();
  }

  public void onClick(View paramView)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.app = getLocalApp();
    this.cfg = Config.getInstance();
    this.passengerService = PassengerService.getInstance(this.app.sp);
    setContentView(getMainLayout());
    getHc().runRule(getAcitveName() + "_onCreate");
    init();
    this.mRemark = ((TextView)findViewById(2131296265));
    showRemark();
    showTip();
    this.app.launcObserv.addObserver(this.observer);
    double d = this.app.launchInfo.optDouble("onCreateLogRate", 0.01D);
    if (Math.random() < d)
      logToServer(getAcitveName() + "onCreate", "");
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 103:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.timeRang, 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.timeRangSelect(BaseActivity.this.cfg.timeRang[paramInt]);
          paramDialogInterface.dismiss();
        }
      }).create();
    case 100:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }, 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.numSelect(Integer.valueOf(paramInt + 1));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 107:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.sexTypes.getLabels(), 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.sexTypeSelect(BaseActivity.this.cfg.sexTypes.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 104:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.seatTypes.getLabels(), 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.seatTypeSelect(BaseActivity.this.cfg.seatTypes.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 114:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.seatTypesH.getLabels(), 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.seatTypeSelect(BaseActivity.this.cfg.seatTypesH.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 124:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.seatTypesL.getLabels(), 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.seatTypeSelect(BaseActivity.this.cfg.seatTypesL.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 105:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.cardTypes.getLabels(), this.cfg.cardTypes.posByName(getVString(2131296652)), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.cardTypeSelect(BaseActivity.this.cfg.cardTypes.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 106:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.tickTypes.getLabels(), this.cfg.tickTypes.posByName(getVString(2131296973)), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.tickTypeSelect(BaseActivity.this.cfg.tickTypes.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 108:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.passengerTypes.getLabels(), 0, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.passengerTypeSelect(BaseActivity.this.cfg.passengerTypes.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 101:
      mulSelect = this.cfg.seatTypes.getSelectArray(new MonitorInfo().getSeatTypes());
      return new MyAlertDialog.Builder(this).setMultiChoiceItems(this.cfg.seatTypes.getLabels(), mulSelect, new DialogInterface.OnMultiChoiceClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
        {
          BaseActivity.mulSelect[paramInt] = paramBoolean;
        }
      }).setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          NoteList localNoteList = BaseActivity.this.cfg.seatTypes.getSelectNote(BaseActivity.mulSelect);
          BaseActivity.this.seatTypeSelectMul(localNoteList);
        }
      }).create();
    case 102:
      mulSelect = this.cfg.trainTypeSimples.getSelectArray(getLocalApp().getCq().getTrainTypes());
      return new MyAlertDialog.Builder(this).setMultiChoiceItems(this.cfg.trainTypeSimples.getLabels(), mulSelect, new DialogInterface.OnMultiChoiceClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
        {
          BaseActivity.mulSelect[paramInt] = paramBoolean;
        }
      }).setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          NoteList localNoteList = BaseActivity.this.cfg.trainTypeSimples.getSelectNote(BaseActivity.mulSelect);
          BaseActivity.this.trainTypeSelectMul(localNoteList);
        }
      }).create();
    case 10010:
      return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.schoolSystems.getLabels(), this.cfg.schoolSystems.posByName(getVString(2131296983)), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          BaseActivity.this.schoolSystemSelect(BaseActivity.this.cfg.schoolSystems.get(paramInt));
          paramDialogInterface.dismiss();
        }
      }).create();
    case 10020:
    }
    return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.enterYears.getLabels(), this.cfg.enterYears.posByName(getVString(2131296985)), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseActivity.this.enterYearSelect(BaseActivity.this.cfg.enterYears.get(paramInt));
        paramDialogInterface.dismiss();
      }
    }).create();
  }

  protected void onDestroy()
  {
    this.app.launcObserv.deleteObserver(this.observer);
    super.onDestroy();
  }

  protected void onHasLogined(int paramInt)
  {
    onLoginSuccess(paramInt);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && ((getTopActivity() instanceof MainTab)))
    {
      long l = System.currentTimeMillis();
      if (l - this.last_key_back > 3000L)
      {
        this.last_key_back = l;
        showToast("再按一次退出系统");
      }
      while (true)
      {
        return true;
        finish();
        MyToast.cancelToast();
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onLaunch(Message paramMessage)
  {
    showRemark();
    showTip();
    checkUpgrade();
  }

  protected void onLoginCanceled(int paramInt)
  {
  }

  protected void onLoginSuccess(int paramInt)
  {
  }

  protected void onPause()
  {
    super.onPause();
    this.app.ruleObservable.deleteObserver(this.ruleObserver);
  }

  protected void onResume()
  {
    super.onResume();
    this.app.ruleObservable.addObserver(this.ruleObserver);
  }

  protected void onRuleMessage(int paramInt, String paramString)
  {
  }

  protected JSONArray optJsonArrayFromLaunchInfo(String paramString)
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

  protected void passengerTypeSelect(Note paramNote)
  {
    setTv(2131296654, paramNote.getName());
  }

  protected void schoolSystemSelect(Note paramNote)
  {
    setTv(2131296983, paramNote.getName());
  }

  protected void seatTypeSelect(Note paramNote)
  {
    setTv(2131296781, paramNote.getName());
  }

  protected void seatTypeSelect(Note paramNote, int paramInt)
  {
    setTv(paramInt, paramNote.getName());
  }

  protected void seatTypeSelectMul(NoteList paramNoteList)
  {
  }

  protected View setClick(int paramInt1, int paramInt2)
  {
    return setClick(paramInt1, new View.OnClickListener(paramInt2)
    {
      public void onClick(View paramView)
      {
        BaseActivity.this.showDialog(this.val$dialog);
      }
    });
  }

  public View setClick(int paramInt, View.OnClickListener paramOnClickListener)
  {
    View localView = findViewById(paramInt);
    if (localView == null)
      return localView;
    localView.setOnClickListener(paramOnClickListener);
    return localView;
  }

  public View setClick(int paramInt, Class<?> paramClass)
  {
    return setClick(paramInt, new View.OnClickListener(paramClass)
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(BaseActivity.this, this.val$clazz);
        BaseActivity.this.startActivity(localIntent);
      }
    });
  }

  public View setClick(int paramInt1, Class<?> paramClass, int paramInt2)
  {
    return setClick(paramInt1, new View.OnClickListener(paramClass, paramInt2)
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(BaseActivity.this, this.val$clazz);
        BaseActivity.this.startActivityForResult(localIntent, this.val$request);
      }
    });
  }

  public View setClick(int paramInt, String paramString)
  {
    return setClick(paramInt, new View.OnClickListener(paramString)
    {
      public void onClick(View paramView)
      {
        BaseActivity.this.goWebActivity(this.val$url, null, null);
      }
    });
  }

  public void setContentView(int paramInt)
  {
    requestWindowFeature(1);
    if (paramInt == 0)
      return;
    super.setContentView(paramInt);
  }

  public EditText setEt(int paramInt, CharSequence paramCharSequence)
  {
    EditText localEditText = (EditText)findViewById(paramInt);
    localEditText.setText(paramCharSequence);
    return localEditText;
  }

  public View setFocusChanger(int paramInt, View.OnFocusChangeListener paramOnFocusChangeListener)
  {
    View localView = findViewById(paramInt);
    if (localView == null)
      return null;
    localView.setOnFocusChangeListener(paramOnFocusChangeListener);
    return localView;
  }

  public TextView setTv(int paramInt, CharSequence paramCharSequence)
  {
    TextView localTextView = (TextView)findViewById(paramInt);
    if (localTextView == null)
      return null;
    localTextView.setText(paramCharSequence);
    localTextView.setVisibility(0);
    return localTextView;
  }

  public View setVisibility(int paramInt1, int paramInt2)
  {
    View localView = findViewById(paramInt1);
    if (localView == null)
      return null;
    localView.setVisibility(paramInt2);
    return localView;
  }

  protected Dialog showDialogNumSelect(int paramInt)
  {
    return new MyAlertDialog.Builder(this).setSingleChoiceItems(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }, paramInt, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseActivity.this.numSelect(Integer.valueOf(paramInt + 1));
        paramDialogInterface.dismiss();
      }
    }).show();
  }

  protected Dialog showDialogSeatType(int paramInt)
  {
    return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.seatTypes.getLabels(), paramInt, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseActivity.this.seatTypeSelect(BaseActivity.this.cfg.seatTypes.get(paramInt));
        paramDialogInterface.dismiss();
      }
    }).show();
  }

  protected Dialog showDialogSeatType(int paramInt1, int paramInt2)
  {
    return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.seatTypes.getLabels(), paramInt1, new DialogInterface.OnClickListener(paramInt2)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseActivity.this.seatTypeSelect(BaseActivity.this.cfg.seatTypes.get(paramInt), this.val$targetView);
        paramDialogInterface.dismiss();
      }
    }).show();
  }

  protected Dialog showDialogSeatTypeAll(int paramInt1, int paramInt2)
  {
    return new MyAlertDialog.Builder(this).setSingleChoiceItems(this.cfg.seatTypesAll.getLabels(), paramInt1, new DialogInterface.OnClickListener(paramInt2)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseActivity.this.seatTypeSelect(BaseActivity.this.cfg.seatTypesAll.get(paramInt), this.val$targetView);
        paramDialogInterface.dismiss();
      }
    }).show();
  }

  protected Dialog showDialogSeatTypeAllMul(List<Note> paramList)
  {
    boolean[] arrayOfBoolean = this.cfg.seatTypesAll.getSelectArray(paramList);
    return new MyAlertDialog.Builder(this).setMultiChoiceItems(this.cfg.seatTypesAll.getLabels(), arrayOfBoolean, new DialogInterface.OnMultiChoiceClickListener(arrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
      {
        this.val$mulSelect[paramInt] = paramBoolean;
      }
    }).setPositiveButton("确定", new DialogInterface.OnClickListener(arrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        NoteList localNoteList = BaseActivity.this.cfg.seatTypesAll.getSelectNote(this.val$mulSelect);
        BaseActivity.this.seatTypeSelectMul(localNoteList);
      }
    }).show();
  }

  protected Dialog showDialogSeatTypeMul(List<Note> paramList)
  {
    boolean[] arrayOfBoolean = this.cfg.seatTypes.getSelectArray(paramList);
    return new MyAlertDialog.Builder(this).setMultiChoiceItems(this.cfg.seatTypes.getLabels(), arrayOfBoolean, new DialogInterface.OnMultiChoiceClickListener(arrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
      {
        this.val$mulSelect[paramInt] = paramBoolean;
      }
    }).setPositiveButton("确定", new DialogInterface.OnClickListener(arrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        NoteList localNoteList = BaseActivity.this.cfg.seatTypes.getSelectNote(this.val$mulSelect);
        BaseActivity.this.seatTypeSelectMul(localNoteList);
      }
    }).show();
  }

  protected Dialog showDialogTrainCodeMul(CharSequence[] paramArrayOfCharSequence, boolean[] paramArrayOfBoolean)
  {
    return new MyAlertDialog.Builder(this).setMultiChoiceItems(paramArrayOfCharSequence, paramArrayOfBoolean, new DialogInterface.OnMultiChoiceClickListener(paramArrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt, boolean paramBoolean)
      {
        this.val$mulSelect[paramInt] = paramBoolean;
      }
    }).setSelectAllOrNone(true).setPositiveButton("确定", new DialogInterface.OnClickListener(paramArrayOfBoolean)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        BaseActivity.this.trainCodeSelectMul(this.val$mulSelect);
      }
    }).show();
  }

  protected void showMessage(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    new MyAlertDialog.Builder(getTopActivity()).setTitle(paramCharSequence1).setMessage(paramCharSequence2).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
      }
    }).create().show();
  }

  protected void showProgressDialog(String paramString, CharSequence paramCharSequence, boolean paramBoolean)
  {
    this.app.mProgressDialog = MyProgressDialog.show(getTopActivity(), paramString, paramCharSequence, true, paramBoolean);
    this.mProgressDialog = this.app.mProgressDialog;
  }

  protected void showProgressDialog(boolean paramBoolean)
  {
    this.mProgressDialog = MyProgressDialog.show(getTopActivity(), "", "", true, paramBoolean);
  }

  protected void showRemark()
  {
    if (this.mRemark == null)
      return;
    String str1 = getAcitveName();
    String str2 = this.app.launchInfo.optString(str1, getDefautRemark());
    this.mRemark.setText(Html.fromHtml(str2));
    this.mRemark.setVisibility(0);
    this.mRemark.setMovementMethod(LinkMovementMethod.getInstance());
  }

  public void showShareDialog()
  {
    DisplayHelper.showShareDialog(this);
  }

  protected void showToast(int paramInt)
  {
    showToast(paramInt, 0);
  }

  protected void showToast(int paramInt1, int paramInt2)
  {
    MyToast.makeText(getTopActivity(), paramInt1, paramInt2).show();
  }

  public void showToast(CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (paramCharSequence.length() == 0))
      paramCharSequence = "出错了";
    if (paramCharSequence.length() > 100)
      paramCharSequence = paramCharSequence.subSequence(0, 100);
    showToast(paramCharSequence, 0);
  }

  protected void showToast(CharSequence paramCharSequence, int paramInt)
  {
    MyToast.makeText(getTopActivity(), paramCharSequence, paramInt).show();
  }

  protected void tickTypeSelect(Note paramNote)
  {
    setTv(2131296973, paramNote.getName());
  }

  protected void timeRangSelect(String paramString)
  {
  }

  protected void trainCodeSelectMul(boolean[] paramArrayOfBoolean)
  {
  }

  protected void trainTypeSelectMul(NoteList paramNoteList)
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.BaseActivity
 * JD-Core Version:    0.6.0
 */