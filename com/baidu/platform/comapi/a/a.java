package com.baidu.platform.comapi.a;

import android.os.Bundle;
import com.baidu.platform.comapi.basestruct.c;

public class a
{
  private static a a = null;
  private com.baidu.platform.comjni.base.location.a b = null;

  public static a a()
  {
    if (a == null)
    {
      a = new a();
      if (!a.b())
      {
        a = null;
        return null;
      }
    }
    return a;
  }

  private boolean b()
  {
    if (this.b == null)
    {
      this.b = new com.baidu.platform.comjni.base.location.a();
      if (this.b.a() == 0)
      {
        this.b = null;
        return false;
      }
    }
    return true;
  }

  public c a(float paramFloat1, float paramFloat2, String paramString)
  {
    if (paramString == null);
    Bundle localBundle;
    do
    {
      do
      {
        return null;
        if (!paramString.equals(""))
          continue;
        paramString = "bd09ll";
      }
      while ((!paramString.equals("bd09ll")) && (!paramString.equals("bd09mc")) && (!paramString.equals("gcj02")) && (!paramString.equals("wgs84")));
      if (paramString.equals("bd09mc"))
        return new c((int)paramFloat1, (int)paramFloat2);
      localBundle = new Bundle();
      this.b.a(paramFloat1, paramFloat2, localBundle, paramString);
    }
    while (localBundle.isEmpty());
    c localc = new c(0, 0);
    localc.a((int)localBundle.getDouble("x"));
    localc.b((int)localBundle.getDouble("y"));
    return localc;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.a.a
 * JD-Core Version:    0.6.0
 */