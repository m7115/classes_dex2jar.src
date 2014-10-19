package com.tencent.mm.sdk.channel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;
import java.util.HashMap;
import java.util.Map;

public class MMessage
{
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    a(paramContext, paramString1, paramString2, paramString3, null);
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    String str1 = paramString1 + ".permission.MM_MESSAGE";
    Intent localIntent = new Intent(paramString2);
    if (paramBundle != null)
      localIntent.putExtras(paramBundle);
    String str2 = paramContext.getPackageName();
    localIntent.putExtra("_mmessage_sdkVersion", 553844737);
    localIntent.putExtra("_mmessage_appPackage", str2);
    localIntent.putExtra("_mmessage_content", paramString3);
    localIntent.putExtra("_mmessage_checksum", b.a(paramString3, str2));
    paramContext.sendBroadcast(localIntent, str1);
    a.c("MicroMsg.SDK.MMessage", "send mm message, intent=" + localIntent + ", perm=" + str1);
    return true;
  }

  public static final class Receiver extends BroadcastReceiver
  {
    public static final Map<String, MMessage.a> a = new HashMap();
    private final MMessage.a b;

    public Receiver()
    {
      this(null);
    }

    public Receiver(MMessage.a parama)
    {
      this.b = parama;
    }

    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      a.c("MicroMsg.SDK.MMessage", "receive intent=" + paramIntent);
      if (this.b != null)
      {
        this.b.a(paramIntent);
        a.c("MicroMsg.SDK.MMessage", "mm message self-handled");
      }
      MMessage.a locala;
      do
      {
        return;
        locala = (MMessage.a)a.get(paramIntent.getAction());
      }
      while (locala == null);
      locala.a(paramIntent);
      a.c("MicroMsg.SDK.MMessage", "mm message handled");
    }
  }

  public static abstract interface a
  {
    public abstract void a(Intent paramIntent);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.channel.MMessage
 * JD-Core Version:    0.6.0
 */