package com.baidu.location;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class a
{
  private static String jdField_if = "baidu_location_service";
  private boolean a = false;
  private ArrayList jdField_do = null;
  private boolean jdField_for = false;
  private Handler jdField_int = null;

  public a(Handler paramHandler)
  {
    this.jdField_int = paramHandler;
    this.jdField_do = new ArrayList();
  }

  private a a(Messenger paramMessenger)
  {
    if (this.jdField_do == null)
      return null;
    Iterator localIterator = this.jdField_do.iterator();
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.jdField_for.equals(paramMessenger))
        return locala;
    }
    return null;
  }

  private void a()
  {
    Iterator localIterator = this.jdField_do.iterator();
    boolean bool1 = false;
    if (localIterator.hasNext())
      if (!((a)localIterator.next()).jdField_do.jdField_new.equals("kuikedefancaiburudashahaochi"))
        break label75;
    label75: for (boolean bool2 = true; ; bool2 = bool1)
    {
      bool1 = bool2;
      break;
      if (this.a != bool1)
      {
        this.a = bool1;
        this.jdField_int.obtainMessage(81).sendToTarget();
      }
      return;
    }
  }

  private void a(a parama)
  {
    if (parama == null)
      return;
    if (a(parama.jdField_for) != null)
    {
      a.a(parama, 14);
      return;
    }
    this.jdField_do.add(parama);
    j.jdField_if(jdField_if, parama.jdField_int + " registered ");
    a.a(parama, 13);
  }

  private void jdField_do()
  {
    jdField_int();
    a();
    jdMethod_new();
  }

  private void jdField_int()
  {
    Iterator localIterator = this.jdField_do.iterator();
    boolean bool1 = false;
    boolean bool2 = false;
    if (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.jdField_do.jdField_case)
        bool2 = true;
      if (!locala.jdField_do.jdField_void)
        break label95;
    }
    label95: for (boolean bool3 = true; ; bool3 = bool1)
    {
      bool1 = bool3;
      break;
      j.H = bool1;
      if (this.jdField_for != bool2)
      {
        this.jdField_for = bool2;
        this.jdField_int.obtainMessage(52).sendToTarget();
      }
      return;
    }
  }

  public String a(Message paramMessage)
  {
    String str = null;
    if ((paramMessage == null) || (paramMessage.replyTo == null))
      j.jdField_if(jdField_if, "invalid Poirequest");
    a locala;
    do
    {
      do
      {
        return str;
        locala = a(paramMessage.replyTo);
        str = null;
      }
      while (locala == null);
      locala.jdField_do.a = paramMessage.getData().getInt("num", locala.jdField_do.a);
      locala.jdField_do.jdField_do = paramMessage.getData().getFloat("distance", locala.jdField_do.jdField_do);
      locala.jdField_do.jdField_if = paramMessage.getData().getBoolean("extraInfo", locala.jdField_do.jdField_if);
      locala.jdField_do.jdField_else = true;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Float.valueOf(locala.jdField_do.jdField_do);
      arrayOfObject[1] = Integer.valueOf(locala.jdField_do.a);
      str = String.format("&poi=%.1f|%d", arrayOfObject);
    }
    while (!locala.jdField_do.jdField_if);
    return str + "|1";
  }

  public void a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.jdField_do.iterator();
    while (localIterator1.hasNext())
    {
      a locala2 = (a)localIterator1.next();
      locala2.jdField_if(paramString);
      if (locala2.jdField_if <= 4)
        continue;
      localArrayList.add(locala2);
    }
    if ((localArrayList != null) && (localArrayList.size() > 0))
    {
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
      {
        a locala1 = (a)localIterator2.next();
        j.jdField_if(jdField_if, "remove dead object...");
        this.jdField_do.remove(locala1);
      }
    }
  }

  public void a(String paramString, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = this.jdField_do.iterator();
    while (localIterator1.hasNext())
    {
      a locala2 = (a)localIterator1.next();
      locala2.a(paramString, paramInt);
      if (locala2.jdField_if <= 4)
        continue;
      localArrayList.add(locala2);
    }
    if ((localArrayList != null) && (localArrayList.size() > 0))
    {
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
      {
        a locala1 = (a)localIterator2.next();
        j.jdField_if(jdField_if, "remove dead object...");
        this.jdField_do.remove(locala1);
      }
    }
  }

  public void a(String paramString, Message paramMessage)
  {
    if ((paramString == null) || (paramMessage == null));
    while (true)
    {
      return;
      a locala = a(paramMessage.replyTo);
      if (locala == null)
        break;
      locala.jdField_if(paramString);
      if (locala.jdField_if <= 4)
        continue;
      this.jdField_do.remove(locala);
      return;
    }
    j.jdField_if(jdField_if, "not found the client messener...");
  }

  public String jdMethod_byte()
  {
    StringBuffer localStringBuffer = new StringBuffer(256);
    a locala = (a)this.jdField_do.get(0);
    if (locala.jdField_do.jdField_new != null)
      localStringBuffer.append(locala.jdField_do.jdField_new);
    if (locala.jdField_int != null)
    {
      localStringBuffer.append(":");
      localStringBuffer.append(locala.jdField_int);
      localStringBuffer.append("|");
    }
    String str = localStringBuffer.toString();
    if ((str != null) && (!str.equals("")))
      return "&prod=" + str;
    return null;
  }

  public int jdField_do(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.replyTo == null));
    a locala;
    do
    {
      return 1;
      locala = a(paramMessage.replyTo);
    }
    while ((locala == null) || (locala.jdField_do == null));
    return locala.jdField_do.jdField_goto;
  }

  public boolean jdField_for()
  {
    return this.jdField_for;
  }

  public boolean jdField_for(Message paramMessage)
  {
    boolean bool = true;
    a locala = a(paramMessage.replyTo);
    if (locala == null)
      return false;
    int i = locala.jdField_do.jdField_int;
    locala.jdField_do.jdField_int = paramMessage.getData().getInt("scanSpan", locala.jdField_do.jdField_int);
    if (locala.jdField_do.jdField_int < 1000)
    {
      j.R = false;
      if ((locala.jdField_do.jdField_int <= 999) || (i >= 1000))
        break label307;
    }
    while (true)
    {
      locala.jdField_do.jdField_case = paramMessage.getData().getBoolean("openGPS", locala.jdField_do.jdField_case);
      String str1 = paramMessage.getData().getString("coorType");
      LocationClientOption localLocationClientOption1 = locala.jdField_do;
      label142: String str2;
      LocationClientOption localLocationClientOption2;
      if ((str1 != null) && (!str1.equals("")))
      {
        localLocationClientOption1.jdField_try = str1;
        str2 = paramMessage.getData().getString("addrType");
        localLocationClientOption2 = locala.jdField_do;
        if ((str2 == null) || (str2.equals("")))
          break label295;
      }
      while (true)
      {
        localLocationClientOption2.jdField_char = str2;
        j.A = locala.jdField_do.jdField_char;
        locala.jdField_do.jdField_long = paramMessage.getData().getInt("timeOut", locala.jdField_do.jdField_long);
        locala.jdField_do.jdField_void = paramMessage.getData().getBoolean("location_change_notify", locala.jdField_do.jdField_void);
        locala.jdField_do.jdField_goto = paramMessage.getData().getInt("priority", locala.jdField_do.jdField_goto);
        jdField_do();
        return bool;
        j.R = bool;
        break;
        str1 = locala.jdField_do.jdField_try;
        break label142;
        label295: str2 = locala.jdField_do.jdField_char;
      }
      label307: bool = false;
    }
  }

  public void jdField_if()
  {
    Iterator localIterator = this.jdField_do.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).a();
  }

  public void jdField_if(Message paramMessage)
  {
    a locala = a(paramMessage.replyTo);
    if (locala != null)
    {
      j.jdField_if(jdField_if, locala.jdField_int + " unregistered");
      this.jdField_do.remove(locala);
    }
    jdField_do();
  }

  public void jdField_if(String paramString)
  {
    Iterator localIterator = this.jdField_do.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).a(paramString);
  }

  public void jdField_int(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.replyTo == null))
    {
      j.jdField_if(jdField_if, "invalid regist client");
      return;
    }
    a(new a(paramMessage));
    jdField_do();
  }

  public void jdMethod_new()
  {
    Iterator localIterator = this.jdField_do.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).jdField_if();
  }

  private class a
  {
    public LocationClientOption jdField_do = new LocationClientOption();
    public Messenger jdField_for = null;
    public int jdField_if = 0;
    public String jdField_int = null;

    public a(Message arg2)
    {
      Object localObject;
      this.jdField_for = localObject.replyTo;
      this.jdField_int = localObject.getData().getString("packName");
      this.jdField_do.jdField_new = localObject.getData().getString("prodName");
      j.ak = this.jdField_int;
      j.b = this.jdField_do.jdField_new;
      this.jdField_do.jdField_try = localObject.getData().getString("coorType");
      this.jdField_do.jdField_char = localObject.getData().getString("addrType");
      j.A = this.jdField_do.jdField_char;
      this.jdField_do.jdField_case = localObject.getData().getBoolean("openGPS");
      this.jdField_do.jdField_int = localObject.getData().getInt("scanSpan");
      this.jdField_do.jdField_long = localObject.getData().getInt("timeOut");
      this.jdField_do.jdField_goto = localObject.getData().getInt("priority");
      this.jdField_do.jdField_void = localObject.getData().getBoolean("location_change_notify");
    }

    private void a(int paramInt)
    {
      Message localMessage = Message.obtain(null, paramInt);
      try
      {
        if (this.jdField_for != null)
          this.jdField_for.send(localMessage);
        this.jdField_if = 0;
        return;
      }
      catch (Exception localException)
      {
        while (!(localException instanceof DeadObjectException));
        this.jdField_if = (1 + this.jdField_if);
      }
    }

    private void a(int paramInt, String paramString1, String paramString2)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString(paramString1, paramString2);
      Message localMessage = Message.obtain(null, paramInt);
      localMessage.setData(localBundle);
      try
      {
        if (this.jdField_for != null)
          this.jdField_for.send(localMessage);
        this.jdField_if = 0;
        return;
      }
      catch (Exception localException)
      {
        while (!(localException instanceof DeadObjectException));
        this.jdField_if = (1 + this.jdField_if);
      }
    }

    public void a()
    {
      a(23);
    }

    public void a(String paramString)
    {
      if (this.jdField_do.jdField_void == true)
        jdField_if(paramString);
    }

    public void a(String paramString, int paramInt)
    {
      int i = 0;
      j.jdField_if(a.jdMethod_try(), "decode...");
      if (paramString == null)
        return;
      if (paramInt == 21)
        a(27, "locStr", paramString);
      if ((this.jdField_do.jdField_try != null) && (!this.jdField_do.jdField_try.equals("gcj02")))
      {
        double d1 = j.jdField_do(paramString, "x\":\"", "\"");
        double d2 = j.jdField_do(paramString, "y\":\"", "\"");
        j.jdField_if(a.jdMethod_try(), "decode..." + d1 + ":" + d2);
        if ((d1 != 4.9E-324D) && (d2 != 4.9E-324D))
        {
          double[] arrayOfDouble2 = Jni.jdField_if(d1, d2, this.jdField_do.jdField_try);
          paramString = j.a(j.a(paramString, "x\":\"", "\"", arrayOfDouble2[0]), "y\":\"", "\"", arrayOfDouble2[1]);
          j.jdField_if(a.jdMethod_try(), "decode2 ..." + arrayOfDouble2[0] + ":" + arrayOfDouble2[1]);
          j.jdField_if(a.jdMethod_try(), "decode3 ..." + paramString);
        }
        if (!this.jdField_do.jdField_else);
      }
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("result");
        JSONObject localJSONObject3 = localJSONObject1.getJSONObject("content");
        if (Integer.parseInt(localJSONObject2.getString("error")) == 161)
        {
          JSONObject localJSONObject4 = localJSONObject3.getJSONObject("poi");
          JSONArray localJSONArray = localJSONObject4.getJSONArray("p");
          while (i < localJSONArray.length())
          {
            JSONObject localJSONObject5 = localJSONArray.getJSONObject(i);
            double d3 = Double.parseDouble(localJSONObject5.getString("x"));
            double d4 = Double.parseDouble(localJSONObject5.getString("y"));
            if ((d3 == 4.9E-324D) || (d4 == 4.9E-324D))
              continue;
            double[] arrayOfDouble1 = Jni.jdField_if(d3, d4, this.jdField_do.jdField_try);
            localJSONObject5.put("x", String.valueOf(arrayOfDouble1[0]));
            localJSONObject5.put("y", String.valueOf(arrayOfDouble1[1]));
            localJSONArray.put(i, localJSONObject5);
            i++;
          }
          localJSONObject4.put("p", localJSONArray);
          localJSONObject3.put("poi", localJSONObject4);
          localJSONObject1.put("content", localJSONObject3);
          String str = localJSONObject1.toString();
          paramString = str;
        }
        a(paramInt, "locStr", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
    }

    public void jdField_if()
    {
      if (this.jdField_do.jdField_void == true)
      {
        if (j.ab)
          a(54);
      }
      else
        return;
      a(55);
    }

    public void jdField_if(String paramString)
    {
      if (paramString == null)
        return;
      a(27, "locStr", paramString);
      if ((this.jdField_do.jdField_try != null) && (!this.jdField_do.jdField_try.equals("gcj02")))
      {
        double d1 = j.jdField_do(paramString, "x\":\"", "\"");
        double d2 = j.jdField_do(paramString, "y\":\"", "\"");
        if ((d1 != 4.9E-324D) && (d2 != 4.9E-324D))
        {
          double[] arrayOfDouble = Jni.jdField_if(d1, d2, this.jdField_do.jdField_try);
          if ((arrayOfDouble[0] > 0.0D) || (arrayOfDouble[1] > 0.0D))
            paramString = j.a(j.a(paramString, "x\":\"", "\"", arrayOfDouble[0]), "y\":\"", "\"", arrayOfDouble[1]);
        }
      }
      a(21, "locStr", paramString);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.a
 * JD-Core Version:    0.6.0
 */