package com.yipiao.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import cn.suanya.common.net.ClientInfo;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.service.BaseLaunchService;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.LoginUser;
import org.json.JSONObject;

public class LaunchService extends BaseLaunchService
{
  static long launchTimes = 0L;

  public static void launch(Context paramContext, int paramInt)
  {
    if (System.currentTimeMillis() - launchTimes > paramInt)
    {
      launchTimes = System.currentTimeMillis();
      paramContext.startService(new Intent(paramContext, LaunchService.class));
    }
  }

  public JSONObject launchToServer()
    throws Exception
  {
    ClientInfo localClientInfo = new ClientInfo();
    localClientInfo.setClientId(this.app.launchInfo.optLong("clientId", 0L));
    localClientInfo.setClientVersion(this.app.getVersionName());
    localClientInfo.setEmei(((TelephonyManager)getSystemService("phone")).getDeviceId());
    localClientInfo.setSid(Integer.valueOf(Constants.sid));
    localClientInfo.setUserName(((YipiaoApplication)this.app).getUser().getUserName());
    localClientInfo.setModel(Build.MODEL);
    localClientInfo.setSysVersion(Build.VERSION.RELEASE);
    localClientInfo.setConfigVersion(this.app.getConfigVersion());
    localClientInfo.setRuleVersion("");
    localClientInfo.setMarkets(packages("market://details?id=com.yipiao"));
    return YipiaoService.getInstance().launch(localClientInfo);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.LaunchService
 * JD-Core Version:    0.6.0
 */