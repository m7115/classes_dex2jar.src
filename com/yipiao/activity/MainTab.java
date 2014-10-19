package com.yipiao.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import cn.suanya.common.a.n;
import cn.suanya.hc.service.PathService;
import com.example.pathview.MainActivity;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.InstallManager;
import com.yipiao.service.LaunchService;
import org.json.JSONObject;

public class MainTab extends TabActivity
  implements View.OnClickListener
{
  public static int currentTab = 2131296873;
  private YipiaoApplication app;
  private RadioButton monitor;
  private RadioButton order;
  private RadioButton passenger;
  private RadioButton query;
  private TabHost tabHost;
  private RadioButton trainTime;

  private void execUpdateDb()
  {
    new Thread()
    {
      public void run()
      {
        PathService.getInstance().updateStationDb();
      }
    }
    .start();
  }

  protected void checkUpgrade()
  {
    int i = this.app.launchInfo.optInt("upgrade", 0);
    if ((i > 0) && (i < 3))
    {
      AlertDialog localAlertDialog = new AlertDialog.Builder(this).setMessage(Html.fromHtml(this.app.launchInfo.optString("upgradeRemark", ""))).setNeutralButton("以后再说", new DialogInterface.OnClickListener(i)
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          paramDialogInterface.dismiss();
          if (this.val$upgrade == 2)
            MainTab.this.finish();
        }
      }).setNegativeButton("立即升级", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          paramDialogInterface.dismiss();
          new InstallManager(MainTab.this, MainTab.this.app.launchInfo.optString(Constants.conf_downLoad_address, "")).start();
        }
      }).create();
      if (i > 1)
        localAlertDialog.setCancelable(false);
      localAlertDialog.setCanceledOnTouchOutside(false);
      localAlertDialog.show();
    }
  }

  public void initReddot()
  {
    if (this.app.hasNewVersion())
      setReddot(4, true, "new");
  }

  public void onClick(View paramView)
  {
    this.query.setChecked(false);
    this.monitor.setChecked(false);
    this.trainTime.setChecked(false);
    this.order.setChecked(false);
    this.passenger.setChecked(false);
    ((RadioButton)paramView).setChecked(true);
    currentTab = paramView.getId();
    switch (paramView.getId())
    {
    case 2131296874:
    case 2131296876:
    case 2131296878:
    case 2131296880:
    default:
      return;
    case 2131296873:
      this.tabHost.setCurrentTab(0);
      return;
    case 2131296875:
      this.tabHost.setCurrentTab(1);
      return;
    case 2131296877:
      this.tabHost.setCurrentTab(2);
      return;
    case 2131296879:
      this.tabHost.setCurrentTab(3);
      return;
    case 2131296881:
    }
    this.tabHost.setCurrentTab(4);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.app = ((YipiaoApplication)getApplication());
    requestWindowFeature(1);
    setContentView(2130903105);
    this.query = ((RadioButton)findViewById(2131296873));
    this.query.setOnClickListener(this);
    this.monitor = ((RadioButton)findViewById(2131296875));
    this.monitor.setOnClickListener(this);
    this.trainTime = ((RadioButton)findViewById(2131296877));
    this.trainTime.setOnClickListener(this);
    this.order = ((RadioButton)findViewById(2131296879));
    this.order.setOnClickListener(this);
    this.passenger = ((RadioButton)findViewById(2131296881));
    this.passenger.setOnClickListener(this);
    this.tabHost = getTabHost();
    TabHost.TabSpec localTabSpec1 = this.tabHost.newTabSpec("query").setIndicator("query").setContent(new Intent(this, QueryActivity.class));
    TabHost.TabSpec localTabSpec2 = this.tabHost.newTabSpec("monitor").setIndicator("monitor").setContent(new Intent(this, MonitorListActivity.class));
    TabHost.TabSpec localTabSpec3 = this.tabHost.newTabSpec("time").setIndicator("time").setContent(new Intent(this, MainActivity.class));
    TabHost.TabSpec localTabSpec4 = this.tabHost.newTabSpec("order").setIndicator("order").setContent(new Intent(this, OrderActivity.class));
    TabHost.TabSpec localTabSpec5 = this.tabHost.newTabSpec("more").setIndicator("more", getResources().getDrawable(17170445)).setContent(new Intent(this, MoreActivity.class));
    this.tabHost.addTab(localTabSpec1);
    this.tabHost.addTab(localTabSpec2);
    this.tabHost.addTab(localTabSpec3);
    this.tabHost.addTab(localTabSpec4);
    this.tabHost.addTab(localTabSpec5);
    currentTab = 2131296873;
    checkUpgrade();
    initReddot();
    execUpdateDb();
  }

  protected void onDestroy()
  {
    n.b("MainTab-onDestroy");
    super.onDestroy();
  }

  protected void onResume()
  {
    LaunchService.launch(this, this.app.launchInfo.optInt("launchInterval", 1200000));
    onClick(findViewById(currentTab));
    this.app.setTempApiVersion(0);
    super.onResume();
  }

  public void setReddot(int paramInt, boolean paramBoolean)
  {
    setReddot(paramInt, paramBoolean, "");
  }

  public void setReddot(int paramInt, boolean paramBoolean, String paramString)
  {
    int i = 8;
    if (paramBoolean)
      i = 0;
    int j = 0;
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      TextView localTextView = (TextView)findViewById(j);
      if (localTextView != null)
      {
        localTextView.setVisibility(i);
        localTextView.setText(paramString);
      }
      return;
      j = 2131296874;
      continue;
      j = 2131296876;
      continue;
      j = 2131296878;
      continue;
      j = 2131296880;
      continue;
      j = 2131296882;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.MainTab
 * JD-Core Version:    0.6.0
 */