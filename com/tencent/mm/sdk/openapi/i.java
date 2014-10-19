package com.tencent.mm.sdk.openapi;

import android.content.Context;

public class i
{
  private static i a = null;

  public static d a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (a == null)
      a = new i();
    return new j(paramContext, paramString, paramBoolean);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.i
 * JD-Core Version:    0.6.0
 */