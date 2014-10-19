package com.tencent.mm.sdk.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import java.util.HashMap;
import java.util.Map;

public class MMPluginOAuth
{
  private final a a;
  private String b;
  private String c;

  public static class Receiver extends BroadcastReceiver
  {
    private static final Map<String, MMPluginOAuth> a = new HashMap();
    private final MMPluginOAuth b;

    public Receiver()
    {
      this(null);
    }

    public Receiver(MMPluginOAuth paramMMPluginOAuth)
    {
      this.b = paramMMPluginOAuth;
    }

    public static void a(String paramString)
    {
      a.remove(paramString);
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      com.tencent.mm.sdk.platformtools.a.c("MicroMsg.SDK.MMPluginOAuth", "receive oauth result");
      String str1 = paramIntent.getStringExtra("com.tencent.mm.sdk.plugin.Intent.REQUEST_TOKEN");
      String str2 = paramIntent.getStringExtra("com.tencent.mm.sdk.plugin.Intent.ACCESS_TOKEN");
      MMPluginOAuth localMMPluginOAuth;
      if (this.b != null)
        localMMPluginOAuth = this.b;
      while (true)
      {
        new Handler().post(new a(this, localMMPluginOAuth, str2));
        return;
        localMMPluginOAuth = (MMPluginOAuth)a.get(str1);
        if (localMMPluginOAuth == null)
        {
          com.tencent.mm.sdk.platformtools.a.a("MicroMsg.SDK.MMPluginOAuth", "oauth unregistered, request token = " + str1);
          return;
        }
        a(MMPluginOAuth.a(localMMPluginOAuth));
      }
    }
  }

  public static abstract interface a
  {
    public abstract void a(MMPluginOAuth paramMMPluginOAuth);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.plugin.MMPluginOAuth
 * JD-Core Version:    0.6.0
 */