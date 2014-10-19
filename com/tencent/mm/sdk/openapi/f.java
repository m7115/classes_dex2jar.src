package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public final class f
{
  public static class a extends b
  {
    public String d;
    public String e;
    public int f;
    public String g;

    public a()
    {
    }

    public a(Bundle paramBundle)
    {
      a(paramBundle);
    }

    public void a(Bundle paramBundle)
    {
      super.a(paramBundle);
      this.d = paramBundle.getString("_wxapi_sendauth_resp_userName");
      this.e = paramBundle.getString("_wxapi_sendauth_resp_token");
      this.f = paramBundle.getInt("_wxapi_sendauth_resp_expireDate", 0);
      this.g = paramBundle.getString("_wxapi_sendauth_resp_state");
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.f
 * JD-Core Version:    0.6.0
 */