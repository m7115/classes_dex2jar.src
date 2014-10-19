package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public abstract class b
{
  public int a;
  public String b;
  public String c;

  public void a(Bundle paramBundle)
  {
    this.a = paramBundle.getInt("_wxapi_baseresp_errcode");
    this.b = paramBundle.getString("_wxapi_baseresp_errstr");
    this.c = paramBundle.getString("_wxapi_baseresp_transaction");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.b
 * JD-Core Version:    0.6.0
 */