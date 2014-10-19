package com.tencent.mm.sdk.channel;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class a
{
  public static boolean a(Context paramContext, String paramString, Bundle paramBundle)
  {
    return a(paramContext, "com.tencent.mm", "com.tencent.mm.plugin.base.stub.WXEntryActivity", paramString, paramBundle);
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString1.length() == 0) || (paramString2 == null) || (paramString2.length() == 0))
    {
      com.tencent.mm.sdk.platformtools.a.a("MicroMsg.SDK.MMessageAct", "send fail, invalid arguments");
      return false;
    }
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString1, paramString2);
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    String str = paramContext.getPackageName();
    localIntent.putExtra("_mmessage_sdkVersion", 553844737);
    localIntent.putExtra("_mmessage_appPackage", str);
    localIntent.putExtra("_mmessage_content", paramString3);
    localIntent.putExtra("_mmessage_checksum", b.a(paramString3, str));
    localIntent.addFlags(268435456).addFlags(134217728);
    try
    {
      paramContext.startActivity(localIntent);
      com.tencent.mm.sdk.platformtools.a.c("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + localIntent);
      return true;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      com.tencent.mm.sdk.platformtools.a.a("MicroMsg.SDK.MMessageAct", "send fail, target ActivityNotFound");
    }
    return false;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.channel.a
 * JD-Core Version:    0.6.0
 */