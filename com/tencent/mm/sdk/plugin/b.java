package com.tencent.mm.sdk.plugin;

import android.content.ContentValues;
import android.net.Uri;
import android.provider.BaseColumns;
import com.tencent.mm.sdk.platformtools.a;

public class b
{
  public static final class a
  {
    public static int a(Object paramObject)
    {
      if (paramObject == null)
      {
        a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, null value");
        return 0;
      }
      if ((paramObject instanceof Integer))
        return 1;
      if ((paramObject instanceof Long))
        return 2;
      if ((paramObject instanceof String))
        return 3;
      if ((paramObject instanceof Boolean))
        return 4;
      if ((paramObject instanceof Float))
        return 5;
      if ((paramObject instanceof Double))
        return 6;
      a.a("MicroMsg.SDK.PluginProvider.Resolver", "unresolve failed, unknown type=" + paramObject.getClass().toString());
      return 0;
    }

    public static Object a(int paramInt, String paramString)
    {
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 4:
      case 5:
      case 6:
        try
        {
          a.a("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
          break label82;
          return Integer.valueOf(paramString);
          return Long.valueOf(paramString);
          return Boolean.valueOf(paramString);
          return Float.valueOf(paramString);
          Double localDouble = Double.valueOf(paramString);
          return localDouble;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          label82: paramString = null;
        }
      case 3:
      }
      return paramString;
    }

    public static boolean a(ContentValues paramContentValues, Object paramObject)
    {
      int i = a(paramObject);
      if (i == 0)
        return false;
      paramContentValues.put("type", Integer.valueOf(i));
      paramContentValues.put("value", paramObject.toString());
      return true;
    }
  }

  public static final class b
    implements BaseColumns
  {
    public static final Uri a = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.plugin.b
 * JD-Core Version:    0.6.0
 */