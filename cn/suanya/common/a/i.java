package cn.suanya.common.a;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map<Ljava.lang.String;Ljava.lang.Object;>;
import org.json.JSONObject;

public class i
{
  private static GsonBuilder a = new GsonBuilder();

  static
  {
    a.setDateFormat("yy-MM-dd hh:mm:ss");
  }

  public static Object a(String paramString, Type paramType)
  {
    try
    {
      Object localObject = a.create().fromJson(paramString, paramType);
      return localObject;
    }
    catch (Exception localException)
    {
      Log.e("ls", "json2Obj", localException);
    }
    return null;
  }

  public static String a(Object paramObject)
  {
    try
    {
      String str = a.create().toJson(paramObject);
      return str;
    }
    catch (Exception localException)
    {
      Log.e("ls", "obj2json", localException);
    }
    return null;
  }

  public static String a(Object paramObject, Type paramType)
  {
    try
    {
      String str = a.create().toJson(paramObject, paramType);
      return str;
    }
    catch (Exception localException)
    {
      Log.e("ls", "obj2json", localException);
    }
    return null;
  }

  public static Map<String, Object> a(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramJSONObject.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject = paramJSONObject.opt(str);
      if ((localObject instanceof JSONObject))
        localObject = a((JSONObject)localObject);
      localHashMap.put(str, localObject);
    }
    return (Map<String, Object>)localHashMap;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.i
 * JD-Core Version:    0.6.0
 */