package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public class g
{
  public static class a extends a
  {
    public WXMediaMessage b;
    public int c;

    public int a()
    {
      return 2;
    }

    public void a(Bundle paramBundle)
    {
      super.a(paramBundle);
      paramBundle.putAll(WXMediaMessage.a.a(this.b));
      paramBundle.putInt("_wxapi_sendmessagetowx_req_scene", this.c);
    }

    public void b(Bundle paramBundle)
    {
      super.b(paramBundle);
      this.b = WXMediaMessage.a.a(paramBundle);
      this.c = paramBundle.getInt("_wxapi_sendmessagetowx_req_scene");
    }

    final boolean b()
    {
      if (this.b == null)
      {
        com.tencent.mm.sdk.platformtools.a.a("MicroMsg.SDK.SendMessageToWX.Req", "checkArgs fail ,message is null");
        return false;
      }
      return this.b.checkArgs();
    }
  }

  public static class b extends b
  {
    public b()
    {
    }

    public b(Bundle paramBundle)
    {
      a(paramBundle);
    }

    public void a(Bundle paramBundle)
    {
      super.a(paramBundle);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.g
 * JD-Core Version:    0.6.0
 */