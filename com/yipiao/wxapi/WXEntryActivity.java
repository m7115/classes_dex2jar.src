package com.yipiao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;
import cn.suanya.common.net.LogInfo;
import com.tencent.mm.sdk.openapi.a;
import com.tencent.mm.sdk.openapi.b;
import com.tencent.mm.sdk.openapi.d;
import com.tencent.mm.sdk.openapi.e;
import com.tencent.mm.sdk.openapi.i;
import com.yipiao.YipiaoApplication;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.MyToast;
import org.json.JSONObject;

public class WXEntryActivity extends Activity
  implements e
{
  private d api;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.api == null)
      this.api = YipiaoApplication.getApp().api;
    if (this.api == null)
      this.api = i.a(this, "wx3e388e8f02f38759", false);
    this.api.a(getIntent(), this);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    this.api.a(paramIntent, this);
  }

  public void onReq(a parama)
  {
    switch (parama.a())
    {
    default:
    case 3:
    case 4:
    }
    while (true)
    {
      finish();
      return;
      Toast.makeText(this, "COMMAND_GETMESSAGE_FROM_WX", 1).show();
      continue;
      Toast.makeText(this, "COMMAND_SHOWMESSAGE_FROM_WX", 1).show();
    }
  }

  public void onResp(b paramb)
  {
    switch (paramb.a)
    {
    default:
    case 0:
    }
    while (true)
    {
      YipiaoService.getInstance().asyncLog(new LogInfo("weixinShare", "" + paramb.a + "|" + paramb.b));
      finish();
      return;
      String str = YipiaoApplication.getApp().launchInfo.optString("WxShareSuccess", "分享成功，48小时内您可体验高级监控功能");
      SharedPreferences.Editor localEditor = YipiaoApplication.getApp().sp.edit();
      localEditor.putLong("shareTime", System.currentTimeMillis());
      localEditor.commit();
      MyToast.makeText(this, str, 0).show();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.wxapi.WXEntryActivity
 * JD-Core Version:    0.6.0
 */