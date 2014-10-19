package com.baidu.platform.comapi.c;

import android.os.Bundle;
import com.baidu.platform.comapi.d.c;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static a a = null;
  private JSONObject b = null;
  private com.baidu.platform.comjni.base.userdatacollect.a c = null;

  public static a a()
  {
    if (a == null)
    {
      a = new a();
      if (!a.e())
      {
        a = null;
        return null;
      }
    }
    return a;
  }

  public static void b()
  {
    if (a != null)
    {
      if (a.c != null)
      {
        a.c.b();
        a.c = null;
      }
      a = null;
    }
  }

  private boolean e()
  {
    int i = 1;
    if (this.c == null)
    {
      this.c = new com.baidu.platform.comjni.base.userdatacollect.a();
      if (this.c.a() == 0)
      {
        this.c = null;
        i = 0;
      }
    }
    else
    {
      return i;
    }
    this.b = new JSONObject();
    return i;
  }

  private Bundle f()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("pd", "mapsdk");
    localBundle.putString("os", "android");
    localBundle.putString("sv", c.i());
    localBundle.putString("ov", c.k());
    localBundle.putString("im", c.o());
    localBundle.putString("channel", c.p());
    localBundle.putString("mb", c.g());
    localBundle.putString("ver", "2");
    localBundle.putInt("sw", c.h());
    localBundle.putInt("sh", c.j());
    localBundle.putString("resid", "02");
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(c.l());
    arrayOfObject[1] = Integer.valueOf(c.m());
    localBundle.putString("dpi", String.format("(%d,%d)", arrayOfObject));
    return localBundle;
  }

  public void a(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("ActParam", this.b);
      label19: if (this.b.length() <= 0)
        this.c.a(paramString, null);
      while (true)
      {
        this.b = null;
        this.b = new JSONObject();
        return;
        this.c.a(paramString, localJSONObject.toString());
      }
    }
    catch (JSONException localJSONException)
    {
      break label19;
    }
  }

  public void a(String paramString1, String paramString2)
  {
    try
    {
      this.b.put(paramString1, paramString2);
      return;
    }
    catch (JSONException localJSONException)
    {
    }
    throw new RuntimeException(localJSONException);
  }

  public boolean c()
  {
    String str = c.r() + "/udc/";
    Bundle localBundle = f();
    return this.c.a(str, localBundle);
  }

  public void d()
  {
    this.c.c();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.c.a
 * JD-Core Version:    0.6.0
 */