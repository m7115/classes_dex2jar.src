package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.baidu.platform.comapi.basestruct.c;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h
{
  private static com.baidu.platform.comjni.map.basemap.a a = null;
  private static h b = null;
  private static m c = null;
  private static Handler d = null;

  public static h a(o paramo)
  {
    if (b == null)
      b = new h();
    if ((b != null) && (b.b(paramo)))
      return b;
    return null;
  }

  public static void a()
  {
    if (a != null)
    {
      if (b != null)
      {
        if (d != null)
        {
          com.baidu.platform.comjni.engine.a.b(65289, d);
          d = null;
        }
        if (c != null)
        {
          c.a();
          c = null;
        }
        b = null;
      }
      a = null;
    }
  }

  private boolean b(o paramo)
  {
    if (paramo == null);
    do
    {
      return false;
      if (a != null)
        continue;
      a = paramo.b();
    }
    while (a == null);
    c = new m();
    d = new i(this);
    com.baidu.platform.comjni.engine.a.a(65289, d);
    return true;
  }

  public ArrayList<g> a(String paramString)
  {
    if ((paramString.equals("")) || (a == null));
    ArrayList localArrayList1;
    while (true)
    {
      return null;
      String str = a.b(paramString);
      if ((str == null) || (str.equals("")))
        continue;
      localArrayList1 = new ArrayList();
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        if ((localJSONObject1 == null) || (localJSONObject1.length() == 0))
          continue;
        JSONArray localJSONArray1 = localJSONObject1.optJSONArray("dataset");
        if (localJSONArray1 == null)
          continue;
        for (int i = 0; i < localJSONArray1.length(); i++)
        {
          g localg1 = new g();
          JSONObject localJSONObject2 = localJSONArray1.getJSONObject(i);
          localg1.a = localJSONObject2.optInt("id");
          localg1.b = localJSONObject2.optString("name");
          localg1.c = localJSONObject2.optInt("size");
          localg1.d = localJSONObject2.optInt("cty");
          if (localJSONObject2.has("child"))
          {
            JSONArray localJSONArray2 = localJSONObject2.optJSONArray("child");
            ArrayList localArrayList2 = new ArrayList();
            for (int j = 0; j < localJSONArray2.length(); j++)
            {
              g localg2 = new g();
              JSONObject localJSONObject3 = localJSONArray2.optJSONObject(j);
              localg2.a = localJSONObject3.optInt("id");
              localg2.b = localJSONObject3.optString("name");
              localg2.c = localJSONObject3.optInt("size");
              localg2.d = localJSONObject3.optInt("cty");
              localArrayList2.add(localg2);
            }
            localg1.a(localArrayList2);
          }
          localArrayList1.add(localg1);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return null;
      }
    }
    return localArrayList1;
  }

  public void a(l paraml)
  {
    c.a(paraml);
  }

  public boolean a(int paramInt)
  {
    if ((a == null) || (paramInt < 0))
      return false;
    return a.d(paramInt);
  }

  public boolean a(boolean paramBoolean)
  {
    if (a == null)
      return false;
    return a.c(paramBoolean);
  }

  public ArrayList<g> b()
  {
    if (a == null)
      return null;
    String str = a.i();
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      JSONArray localJSONArray1 = new JSONObject(str).optJSONArray("dataset");
      for (int i = 0; i < localJSONArray1.length(); i++)
      {
        g localg1 = new g();
        JSONObject localJSONObject1 = localJSONArray1.optJSONObject(i);
        localg1.a = localJSONObject1.optInt("id");
        localg1.b = localJSONObject1.optString("name");
        localg1.c = localJSONObject1.optInt("size");
        localg1.d = localJSONObject1.optInt("cty");
        if (localJSONObject1.has("child"))
        {
          JSONArray localJSONArray2 = localJSONObject1.optJSONArray("child");
          ArrayList localArrayList2 = new ArrayList();
          for (int j = 0; j < localJSONArray2.length(); j++)
          {
            g localg2 = new g();
            JSONObject localJSONObject2 = localJSONArray2.optJSONObject(j);
            localg2.a = localJSONObject2.optInt("id");
            localg2.b = localJSONObject2.optString("name");
            localg2.c = localJSONObject2.optInt("size");
            localg2.d = localJSONObject2.optInt("cty");
            localArrayList2.add(localg2);
          }
          localg1.a(localArrayList2);
        }
        localArrayList1.add(localg1);
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return null;
    }
    return localArrayList1;
  }

  public void b(l paraml)
  {
    c.b(paraml);
  }

  public boolean b(int paramInt)
  {
    if ((a == null) || (paramInt < 0))
      return false;
    return a.a(paramInt, false, 0);
  }

  public ArrayList<g> c()
  {
    if (a == null)
      return null;
    String str = a.b("");
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      JSONArray localJSONArray1 = new JSONObject(str).optJSONArray("dataset");
      for (int i = 0; i < localJSONArray1.length(); i++)
      {
        g localg1 = new g();
        JSONObject localJSONObject1 = localJSONArray1.optJSONObject(i);
        localg1.a = localJSONObject1.optInt("id");
        localg1.b = localJSONObject1.optString("name");
        localg1.c = localJSONObject1.optInt("size");
        localg1.d = localJSONObject1.optInt("cty");
        if (localJSONObject1.has("child"))
        {
          JSONArray localJSONArray2 = localJSONObject1.optJSONArray("child");
          ArrayList localArrayList2 = new ArrayList();
          for (int j = 0; j < localJSONArray2.length(); j++)
          {
            g localg2 = new g();
            JSONObject localJSONObject2 = localJSONArray2.optJSONObject(j);
            localg2.a = localJSONObject2.optInt("id");
            localg2.b = localJSONObject2.optString("name");
            localg2.c = localJSONObject2.optInt("size");
            localg2.d = localJSONObject2.optInt("cty");
            localArrayList2.add(localg2);
          }
          localg1.a(localArrayList2);
        }
        localArrayList1.add(localg1);
      }
      return localArrayList1;
    }
    catch (Exception localException)
    {
      return null;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public boolean c(int paramInt)
  {
    if ((a == null) || (paramInt < 0))
      return false;
    return a.b(paramInt, false, 0);
  }

  public ArrayList<k> d()
  {
    int i = 0;
    if (a == null);
    String str;
    do
    {
      return null;
      str = a.h();
    }
    while ((str == null) || (str.equals("")));
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      j localj;
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        if (localJSONObject1.length() == 0)
          break;
        JSONArray localJSONArray = localJSONObject1.optJSONArray("dataset");
        if (i >= localJSONArray.length())
          break label310;
        k localk = new k();
        localj = new j();
        JSONObject localJSONObject2 = localJSONArray.optJSONObject(i);
        localj.a = localJSONObject2.optInt("id");
        localj.b = localJSONObject2.optString("name");
        localj.c = localJSONObject2.optString("pinyin");
        localj.h = localJSONObject2.optInt("size");
        localj.i = localJSONObject2.optInt("ratio");
        localj.l = localJSONObject2.optInt("status");
        localj.g = new c();
        localj.g.a(localJSONObject2.optInt("x"));
        localj.g.b(localJSONObject2.optInt("y"));
        if (localJSONObject2.optInt("up") != 1)
          continue;
        localj.j = true;
        localj.e = localJSONObject2.optInt("lev");
        if (localj.j)
        {
          localj.k = localJSONObject2.optInt("svr_size");
          localk.a(localj);
          localArrayList.add(localk);
          i++;
          continue;
          localj.j = false;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
      localj.k = 0;
    }
    label310: return localArrayList;
  }

  public boolean d(int paramInt)
  {
    if (a == null)
      return false;
    return a.b(0, true, paramInt);
  }

  public boolean e(int paramInt)
  {
    if ((a == null) || (paramInt < 0))
      return false;
    return a.c(paramInt, false);
  }

  public k f(int paramInt)
  {
    if ((a == null) || (paramInt < 0));
    while (true)
    {
      return null;
      String str = a.e(paramInt);
      if ((str == null) || (str.equals("")))
        continue;
      k localk = new k();
      j localj = new j();
      try
      {
        JSONObject localJSONObject = new JSONObject(str);
        if (localJSONObject.length() == 0)
          continue;
        localj.a = localJSONObject.optInt("id");
        localj.b = localJSONObject.optString("name");
        localj.c = localJSONObject.optString("pinyin");
        localj.d = localJSONObject.optString("headchar");
        localj.h = localJSONObject.optInt("size");
        localj.i = localJSONObject.optInt("ratio");
        localj.l = localJSONObject.optInt("status");
        localj.g = new c();
        localj.g.a(localJSONObject.optInt("x"));
        localj.g.b(localJSONObject.optInt("y"));
        if (localJSONObject.optInt("up") == 1);
        for (localj.j = true; ; localj.j = false)
        {
          localj.e = localJSONObject.optInt("lev");
          localj.f = localJSONObject.optInt("ver");
          localk.a(localj);
          return localk;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.h
 * JD-Core Version:    0.6.0
 */