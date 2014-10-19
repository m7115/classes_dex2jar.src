package com.tencent.mm.sdk.openapi;

import android.os.Bundle;

public class h
{
  public static class a extends a
  {
    public WXMediaMessage b;

    public a()
    {
    }

    public a(Bundle paramBundle)
    {
      b(paramBundle);
    }

    public int a()
    {
      return 4;
    }

    public void a(Bundle paramBundle)
    {
      Bundle localBundle = WXMediaMessage.a.a(this.b);
      super.a(localBundle);
      paramBundle.putAll(localBundle);
    }

    public void b(Bundle paramBundle)
    {
      super.b(paramBundle);
      this.b = WXMediaMessage.a.a(paramBundle);
    }

    final boolean b()
    {
      if (this.b == null)
        return false;
      return this.b.checkArgs();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.h
 * JD-Core Version:    0.6.0
 */