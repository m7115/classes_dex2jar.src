package cn.suanya.hotel.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.suanya.common.a.h;
import cn.suanya.common.ui.SYActivity;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import cn.suanya.hotel.HTConstants;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.HotelInfo;
import cn.suanya.hotel.service.HotelService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

public class HTActivity extends SYActivity
{
  protected SYApplication app;
  protected HotelService hotelService;
  protected Handler mLaunchHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      HTActivity.this.onLaunch(paramMessage);
    }
  };
  private TextView mRemark;
  private Observer observer = new Observer()
  {
    public void update(Observable paramObservable, Object paramObject)
    {
      HTActivity.this.mLaunchHandler.sendEmptyMessage(0);
    }
  };

  private void showRemark()
  {
    if (this.mRemark == null)
      return;
    String str1 = getClass().getName();
    if (str1.lastIndexOf('.') > 0)
      str1 = str1.substring(1 + str1.lastIndexOf('.'));
    String str2 = str1.substring(0, 1).toLowerCase() + str1.substring(1);
    if (str2.endsWith("Activity"))
      str2 = str2.substring(0, -8 + str2.length());
    String str3 = this.app.launchInfo.optString(str2, getDefautRemark());
    this.mRemark.setText(Html.fromHtml(str3));
    this.mRemark.setMovementMethod(LinkMovementMethod.getInstance());
  }

  protected void checkUpgrade()
  {
    int i = this.app.launchInfo.optInt("upgrade", 0);
    if (i > 0)
    {
      AlertDialog localAlertDialog = new AlertDialog.Builder(this).setMessage(Html.fromHtml(this.app.launchInfo.optString("upgradeRemark", ""))).setNeutralButton("以后再说", new DialogInterface.OnClickListener(i)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          paramDialogInterface.dismiss();
          if (this.val$upgrade == 2)
            HTActivity.this.finish();
        }
      }).setNegativeButton("立即升级", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          paramDialogInterface.dismiss();
          new h(HTActivity.this, HTActivity.this.app.launchInfo.optString(HTConstants.conf_downLoad_address, "")).a();
        }
      }).create();
      if (i > 1)
        localAlertDialog.setCancelable(false);
      localAlertDialog.show();
    }
  }

  protected int getContentView()
  {
    return 0;
  }

  protected String getDefautRemark()
  {
    return "";
  }

  protected void goCityListActivity(int paramInt)
  {
    startActivityForResult(new Intent(this, HotelCityListActivity.class), paramInt);
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

  protected void goHotelDetailActivity(FindHotelReq paramFindHotelReq, HotelInfo paramHotelInfo)
  {
    Intent localIntent = new Intent(this, HotelDetailActivity.class);
    localIntent.putExtra("hotel", paramHotelInfo);
    localIntent.putExtra("hotelReq", paramFindHotelReq);
    startActivity(localIntent);
  }

  protected void goHotelListActivity(FindHotelReq paramFindHotelReq, List<HotelInfo> paramList)
  {
    Intent localIntent = new Intent(this, HotelListActivity.class);
    localIntent.putExtra("hotelReq", paramFindHotelReq);
    localIntent.putExtra("hotels", (Serializable)paramList);
    startActivity(localIntent);
  }

  protected void goHotelMapActivity(FindHotelReq paramFindHotelReq, ArrayList<HotelInfo> paramArrayList)
  {
    Intent localIntent = new Intent(this, HotelMapActivity.class);
    localIntent.addFlags(67108864);
    localIntent.addFlags(268435456);
    localIntent.putExtra("hotelReq", paramFindHotelReq);
    localIntent.putExtra("hotels", paramArrayList);
    startActivity(localIntent);
  }

  protected void goHotelOrderActivity()
  {
    startActivity(new Intent(this, HotelOrderActivity.class));
  }

  protected void goHotelWEBActivity(String paramString)
  {
    goHotelWEBActivity(paramString, null, null);
  }

  protected void goHotelWEBActivity(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(this, HotelWEBActivity.class);
    localIntent.putExtra("url", paramString1);
    if (paramString2 != null)
      localIntent.putExtra("cookies", paramString2);
    if (paramArrayOfByte != null)
      localIntent.putExtra("postPar", paramArrayOfByte);
    startActivity(localIntent);
  }

  protected void goSearchActivity(FindHotelReq paramFindHotelReq)
  {
    Intent localIntent = new Intent(this, HotelSearchActivity.class);
    localIntent.putExtra("hotelReq", paramFindHotelReq);
    startActivity(localIntent);
  }

  protected void goWebActivity(String paramString)
  {
    goWebActivity(paramString, null, null);
  }

  protected void init()
  {
    View localView = findViewById(R.id.leftBackBt);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          HTActivity.this.finish();
        }
      });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    if (getContentView() != 0)
      setContentView(getContentView());
    this.app = ((SYApplication)getApplication());
    this.hotelService = HotelService.instance();
    this.mRemark = ((TextView)findViewById(R.id.remark));
    showRemark();
    init();
    this.app.launcObserv.addObserver(this.observer);
  }

  protected void onDestroy()
  {
    this.app.launcObserv.deleteObserver(this.observer);
    super.onDestroy();
  }

  protected void onLaunch(Message paramMessage)
  {
    showRemark();
    checkUpgrade();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HTActivity
 * JD-Core Version:    0.6.0
 */