package com.baidu.location;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

public final class f extends Service
{
  static final int B = 92;
  static final int D = 57;
  static final int G = 52;
  static final int I = 26;
  static final int K = 64;
  static final int L = 27;
  static final int M = 62;
  static final int S = 1000;
  static final int U = 54;
  static final int V = 81;
  static final int W = 25;
  private static String a;
  static final int aa = 21;
  static String ac = Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata";
  private static final int af = 200;
  static final int ag = 43;
  static final int ah = 14;
  static final int ai = 3000;
  static final int ak = 56;
  static final int ao = 101;
  static final float ap = 3.3F;
  static final int aq = 61;
  static final int ar = 53;
  private static final int at = 800;
  static final int b = 63;
  private static final int jdField_byte = 24;
  static final int c = 12;
  static final int jdField_case = 42;
  static final int jdField_do = 28;
  static final int e = 65;
  static final int jdField_else = 2000;
  static final int jdField_for = 22;
  static final int g = 15;
  static final int i = 55;
  static final int jdField_int = 31;
  private static File j;
  private static File k;
  static final int l = 11;
  static final int jdField_long = 13;
  static final int p = 41;
  static final int s = 23;
  static final int t = 91;
  public static final String v = "baidu_location_service";
  static final int jdField_void = 71;
  static final int w = 24;
  static final int x = 3000;
  static final int z = 51;
  private String A = null;
  private e.c C = null;
  private long E = 0L;
  private e F = null;
  private String H = null;
  private boolean J = true;
  private boolean N = true;
  private boolean O = false;
  private long P = 0L;
  private boolean Q = false;
  final Handler R = new d();
  private SQLiteDatabase T = null;
  private String X = null;
  private boolean Y = true;
  private int Z = 0;
  private b ab = null;
  private boolean ad = false;
  private e.c ae = null;
  private boolean aj = false;
  private c.a al = null;
  private boolean am = false;
  final Messenger an = new Messenger(this.R);
  private String as = null;
  private a au = null;
  private e.c jdField_char = null;
  private long d = 0L;
  private Location f = null;
  private boolean jdField_goto = false;
  private String h = null;
  private String jdField_if = "bdcltb09";
  private String m = ac + "/vm.dat";
  private double n = 0.0D;
  private String jdField_new = null;
  private double o = 0.0D;
  private double q = 0.0D;
  private c r = null;
  private c.a jdField_try = null;
  private c.a u = null;
  private c y = null;

  static
  {
    a = ac + "/glb.dat";
    j = null;
    k = null;
  }

  private String a(String paramString)
  {
    j.jdField_if("baidu_location_service", "generate locdata ...");
    if (((this.jdField_try == null) || (!this.jdField_try.jdField_do())) && (this.r != null))
      this.jdField_try = this.r.a();
    this.A = this.jdField_try.a();
    label127: String str1;
    if (this.jdField_try != null)
    {
      j.a("baidu_location_service", this.jdField_try.jdField_if());
      if (((this.C == null) || (!this.C.jdField_for())) && (this.F != null))
        this.C = this.F.jdField_byte();
      if (this.C == null)
        break label302;
      j.a("baidu_location_service", this.C.jdField_else());
      if ((this.ab == null) || (!this.ab.jdField_new()))
        break label313;
      this.f = this.ab.jdField_try();
      label155: a locala = this.au;
      str1 = null;
      if (locala != null)
        str1 = this.au.jdField_byte();
      if (3 != g.jdField_do(this))
        break label321;
    }
    label302: label313: label321: Object[] arrayOfObject;
    for (String str2 = "&cn=32"; ; str2 = String.format("&cn=%d", arrayOfObject))
    {
      if (this.Y)
      {
        String str4 = k.jdField_if();
        if (str4 != null)
          str2 = str2 + str4;
      }
      String str3 = str2 + str1;
      if (paramString != null)
        str3 = paramString + str3;
      return j.a(this.jdField_try, this.C, this.f, str3, 0);
      j.a("baidu_location_service", "cellInfo null...");
      break;
      j.a("baidu_location_service", "wifi list null");
      break label127;
      this.f = null;
      break label155;
      arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.r.jdField_new());
    }
  }

  private String a(boolean paramBoolean)
  {
    if (((this.jdField_try == null) || (!this.jdField_try.jdField_do())) && (this.r != null))
      this.jdField_try = this.r.a();
    jdField_do(this.jdField_try.a());
    return jdField_if(paramBoolean);
  }

  private void a(int paramInt)
  {
    j.jdField_if("baidu_location_service", "on network exception");
    j.a("baidu_location_service", "on network exception");
    this.jdField_new = null;
    this.jdField_char = null;
    if (this.au != null)
      this.au.a(a(false), paramInt);
    if (paramInt == 21)
      jdField_case();
  }

  private void a(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      j.jdField_if("baidu_location_service", "Gps updateloation is null");
    Location localLocation;
    do
    {
      do
      {
        return;
        localLocation = (Location)paramMessage.obj;
      }
      while (localLocation == null);
      j.jdField_if("baidu_location_service", "on update gps...");
    }
    while ((!k.a(localLocation, true)) || (this.r == null) || (this.F == null) || (this.au == null));
  }

  private void a(Message paramMessage, int paramInt)
  {
    j.jdField_if("baidu_location_service", "on network success");
    j.a("baidu_location_service", "on network success");
    String str = (String)paramMessage.obj;
    j.jdField_if("baidu_location_service", "network:" + str);
    if (this.au != null)
      this.au.a(str, paramInt);
    int i1;
    if (j.jdField_do(str))
      if (paramInt == 21)
      {
        this.jdField_new = str;
        i1 = j.jdField_if(str, "ssid\":\"", "\"");
        if ((i1 == -2147483648) || (this.jdField_char == null))
          break label360;
      }
    label360: for (this.h = this.jdField_char.jdField_if(i1); ; this.h = null)
    {
      jdField_if(str);
      double d1 = j.jdField_do(str, "a\":\"", "\"");
      if (d1 != 4.9E-324D)
        k.a(d1, j.jdField_do(str, "b\":\"", "\""), j.jdField_do(str, "c\":\"", "\""), j.jdField_do(str, "b\":\"", "\""));
      int i2 = j.jdField_if(str, "rWifiN\":\"", "\"");
      if (i2 > 15)
        j.Y = i2;
      int i3 = j.jdField_if(str, "rWifiT\":\"", "\"");
      if (i3 > 500)
        j.S = i3;
      float f1 = j.a(str, "hSpeedDis\":\"", "\"");
      if (f1 > 5.0F)
        j.Q = f1;
      float f2 = j.a(str, "mSpeedDis\":\"", "\"");
      if (f2 > 5.0F)
        j.ai = f2;
      float f3 = j.a(str, "mWifiR\":\"", "\"");
      if ((f3 < 1.0F) && (f3 > 0.2D))
        j.jdField_byte = f3;
      if (paramInt == 21)
        jdField_case();
      return;
      this.H = str;
      break;
      if (paramInt == 21)
      {
        this.jdField_new = null;
        break;
      }
      this.H = null;
      break;
    }
  }

  private boolean a(c.a parama)
  {
    int i1 = 1;
    if (this.r == null);
    do
    {
      return false;
      this.jdField_try = this.r.a();
    }
    while (this.jdField_try == parama);
    if ((this.jdField_try == null) || (parama == null))
      return i1;
    if (!parama.a(this.jdField_try));
    while (true)
    {
      return i1;
      i1 = 0;
    }
  }

  private boolean a(e.c paramc)
  {
    int i1 = 1;
    if (this.F == null);
    do
    {
      return false;
      this.C = this.F.jdField_byte();
    }
    while (paramc == this.C);
    if ((this.C == null) || (paramc == null))
      return i1;
    if (!paramc.a(this.C));
    while (true)
    {
      return i1;
      i1 = 0;
    }
  }

  private void b()
  {
    j.jdField_if("baidu_location_service", "on switch gps ...");
    if (this.au == null);
    do
    {
      return;
      if (!this.au.jdField_for())
        continue;
      if (this.ab == null)
        this.ab = new b(this, this.R);
      this.ab.k();
      return;
    }
    while (this.ab == null);
    this.ab.l();
    this.ab = null;
  }

  private void jdField_byte()
  {
    if (this.O)
      return;
    if (System.currentTimeMillis() - this.P < 1000L)
    {
      j.jdField_if("baidu_location_service", "request too frequency ...");
      if (this.jdField_new != null)
      {
        this.au.a(this.jdField_new);
        jdField_case();
        return;
      }
    }
    j.jdField_if("baidu_location_service", "start network locating ...");
    j.a("baidu_location_service", "start network locating ...");
    this.O = true;
    this.J = a(this.al);
    if ((!a(this.jdField_char)) && (!this.J) && (this.jdField_new != null))
    {
      this.au.a(this.jdField_new);
      jdField_case();
      return;
    }
    String str1 = a(null);
    if (str1 == null)
    {
      String str2 = "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"62\"}}";
      this.au.a(str2);
      jdField_case();
      return;
    }
    if (this.h != null)
    {
      str1 = str1 + this.h;
      this.h = null;
    }
    if (g.a(str1, this.R))
    {
      this.al = this.jdField_try;
      this.jdField_char = this.C;
    }
    while (true)
    {
      if (this.Y == true)
        this.Y = false;
      this.P = System.currentTimeMillis();
      return;
      j.jdField_if("baidu_location_service", "request error ..");
    }
  }

  private void c()
  {
    if (this.au != null)
      this.au.jdField_new();
  }

  private void jdField_case()
  {
    this.O = false;
    jdField_void();
  }

  private void jdField_char()
  {
    try
    {
      if ((j.n) && (j.G))
        this.y = new c(this);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void d()
  {
    File localFile1 = new File(ac);
    File localFile2 = new File(ac + "/ls.db");
    if (!localFile1.exists())
      localFile1.mkdirs();
    if (!localFile2.exists());
    try
    {
      localFile2.createNewFile();
      label65: if (localFile2.exists())
      {
        this.T = SQLiteDatabase.openOrCreateDatabase(localFile2, null);
        this.T.execSQL("CREATE TABLE IF NOT EXISTS " + this.jdField_if + "(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
      }
      return;
    }
    catch (Exception localException)
    {
      break label65;
    }
  }

  private void jdField_do()
  {
    j.jdField_if("baidu_location_service", "on new wifi ...");
    if (this.aj)
    {
      jdField_byte();
      this.aj = false;
    }
  }

  private void jdField_do(Message paramMessage)
  {
    if (System.currentTimeMillis() - this.d < 3000L)
    {
      j.jdField_if("baidu_location_service", "request too frequency ...");
      if (this.H != null)
        this.au.a(this.H, 26);
    }
    do
      return;
    while (this.au == null);
    String str = a(this.au.a(paramMessage));
    if (this.h != null)
    {
      str = str + this.h;
      this.h = null;
    }
    g.jdField_do(this);
    if (g.jdField_if(str, this.R))
    {
      this.u = this.jdField_try;
      this.ae = this.C;
    }
    while (true)
    {
      this.d = System.currentTimeMillis();
      return;
      j.jdField_if("baidu_location_service", "request poi error ..");
    }
  }

  private void jdField_do(String paramString)
  {
    if ((this.T == null) || (paramString == null))
    {
      j.jdField_if("baidu_location_service", "db is null...");
      this.Q = false;
    }
    while (true)
    {
      return;
      j.jdField_if("baidu_location_service", "LOCATING...");
      if ((System.currentTimeMillis() - this.E < 1500L) || (paramString.equals(this.as)))
        continue;
      this.Q = false;
      try
      {
        Cursor localCursor = this.T.rawQuery("select * from " + this.jdField_if + " where id = \"" + paramString + "\";", null);
        this.as = paramString;
        this.E = System.currentTimeMillis();
        if (localCursor == null)
          continue;
        if (localCursor.moveToFirst())
        {
          j.jdField_if("baidu_location_service", "lookup DB success:" + this.as);
          this.o = (localCursor.getDouble(1) - 1235.4322999999999D);
          this.q = (localCursor.getDouble(2) - 4326.0D);
          this.n = (localCursor.getDouble(3) - 2367.3217D);
          this.Q = true;
          j.jdField_if("baidu_location_service", "lookup DB success:x" + this.o + "y" + this.n + "r" + this.q);
        }
        localCursor.close();
        return;
      }
      catch (Exception localException)
      {
        this.E = System.currentTimeMillis();
      }
    }
  }

  private void jdField_for(Message paramMessage)
  {
    if (this.au == null)
      return;
    this.au.a(a(true), paramMessage);
  }

  private void jdField_goto()
  {
    j.jdField_if("baidu_location_service", "on new cell ...");
  }

  private String jdField_if(boolean paramBoolean)
  {
    if (this.Q)
    {
      if (paramBoolean)
      {
        String str2 = "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"66\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}";
        Object[] arrayOfObject2 = new Object[4];
        arrayOfObject2[0] = Double.valueOf(this.o);
        arrayOfObject2[1] = Double.valueOf(this.n);
        arrayOfObject2[2] = Double.valueOf(this.q);
        arrayOfObject2[3] = Boolean.valueOf(true);
        return String.format(str2, arrayOfObject2);
      }
      String str1 = "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"68\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%f\",\"isCellChanged\":\"%b\"}}";
      Object[] arrayOfObject1 = new Object[4];
      arrayOfObject1[0] = Double.valueOf(this.o);
      arrayOfObject1[1] = Double.valueOf(this.n);
      arrayOfObject1[2] = Double.valueOf(this.q);
      arrayOfObject1[3] = Boolean.valueOf(this.J);
      return String.format(str1, arrayOfObject1);
    }
    if (paramBoolean)
      return "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"67\"}}";
    return "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"63\"}}";
  }

  private void jdField_if()
  {
    if (this.ab == null);
    do
    {
      return;
      j.jdField_if("baidu_location_service", "on new gps...");
      Location localLocation = this.ab.jdField_try();
      if ((!this.ab.jdField_new()) || (!k.a(localLocation, true)) || (this.r == null) || (this.F == null) || (this.au == null))
        continue;
      if (this.F != null)
        this.F.a();
      k.a(this.r.a(), this.F.jdField_int(), localLocation, this.au.jdField_byte());
    }
    while ((this.au == null) || (!this.ab.jdField_new()));
    this.au.jdField_if(this.ab.jdField_int());
  }

  private void jdField_if(Message paramMessage)
  {
    if (this.au != null)
      this.au.jdField_int(paramMessage);
    if (this.F != null)
      this.F.jdField_case();
    if (this.N)
    {
      g.jdField_for(this.R);
      this.N = false;
    }
  }

  private void jdField_if(String paramString)
  {
    int i1 = 0;
    if ((this.T == null) || (!this.J))
      return;
    while (true)
    {
      try
      {
        int i2;
        String str;
        while (true)
        {
          j.jdField_if("baidu_location_service", "DB:" + paramString);
          JSONObject localJSONObject1 = new JSONObject(paramString);
          i2 = Integer.parseInt(localJSONObject1.getJSONObject("result").getString("error"));
          if (i2 != 161)
            break label419;
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("content");
          if (!localJSONObject2.has("clf"))
            break label480;
          str = localJSONObject2.getString("clf");
          if (str.equals("0"))
          {
            JSONObject localJSONObject3 = localJSONObject2.getJSONObject("point");
            d1 = Double.parseDouble(localJSONObject3.getString("x"));
            d2 = Double.parseDouble(localJSONObject3.getString("y"));
            f1 = Float.parseFloat(localJSONObject2.getString("radius"));
            j.jdField_if("baidu_location_service", "DB PARSE:x" + d1 + "y" + d2 + "R" + f1);
            if (i1 != 0)
              break;
            double d3 = d1 + 1235.4322999999999D;
            double d4 = d2 + 2367.3217D;
            float f2 = 4326.0F + f1;
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("time", Double.valueOf(d3));
            localContentValues.put("tag", Float.valueOf(f2));
            localContentValues.put("type", Double.valueOf(d4));
            try
            {
              if (this.T.update(this.jdField_if, localContentValues, "id = \"" + this.A + "\"", null) > 0)
                break;
              localContentValues.put("id", this.A);
              this.T.insert(this.jdField_if, null, localContentValues);
              j.jdField_if("baidu_location_service", "insert DB success!");
              return;
            }
            catch (Exception localException2)
            {
              return;
            }
          }
        }
        String[] arrayOfString = str.split("\\|");
        d1 = Double.parseDouble(arrayOfString[0]);
        d2 = Double.parseDouble(arrayOfString[1]);
        f1 = Float.parseFloat(arrayOfString[2]);
        continue;
        label419: if (i2 == 167)
        {
          this.T.delete(this.jdField_if, "id = \"" + this.A + "\"", null);
          return;
        }
      }
      catch (Exception localException1)
      {
        j.jdField_if("baidu_location_service", "DB PARSE:exp!");
        return;
      }
      label480: i1 = 1;
      double d1 = 0.0D;
      double d2 = 0.0D;
      float f1 = 0.0F;
    }
  }

  private void jdField_int()
  {
    if (g.a(this))
      g.f();
  }

  private void jdField_int(Message paramMessage)
  {
    j.jdField_if("baidu_location_service", "on request location ...");
    j.a("baidu_location_service", "on request location ...");
    if (this.au == null);
    do
    {
      return;
      if ((this.au.jdField_do(paramMessage) == 1) && (this.ab != null) && (this.ab.jdField_new()))
      {
        j.jdField_if("baidu_location_service", "send gps location to client ...");
        this.au.a(this.ab.jdField_int(), paramMessage);
        return;
      }
      if (!this.Y)
        continue;
      jdField_byte();
      return;
    }
    while (this.O);
    if ((this.F != null) && (this.F.jdField_new()))
    {
      this.aj = true;
      this.R.postDelayed(new b(null), 2000L);
      return;
    }
    jdField_byte();
  }

  public static void jdField_long()
  {
    try
    {
      if (a != null)
      {
        k = new File(a);
        if (!k.exists())
        {
          File localFile = new File(ac);
          if (!localFile.exists())
            localFile.mkdirs();
          k.createNewFile();
          RandomAccessFile localRandomAccessFile = new RandomAccessFile(k, "rw");
          localRandomAccessFile.seek(0L);
          localRandomAccessFile.writeInt(-1);
          localRandomAccessFile.writeInt(-1);
          localRandomAccessFile.writeInt(0);
          localRandomAccessFile.writeLong(0L);
          localRandomAccessFile.writeInt(0);
          localRandomAccessFile.writeInt(0);
          localRandomAccessFile.close();
          return;
        }
      }
      else
      {
        k = null;
        return;
      }
    }
    catch (Exception localException)
    {
      k = null;
    }
  }

  public static String jdField_new()
  {
    j.jdField_if("baidu_location_service", "read trace log1..");
    return null;
  }

  private void jdField_new(Message paramMessage)
  {
    if (this.au != null)
      this.au.jdField_if(paramMessage);
  }

  private void jdField_try()
  {
  }

  private void jdField_try(Message paramMessage)
  {
    if ((this.au != null) && (this.au.jdField_for(paramMessage)) && (this.F != null))
      this.F.jdField_for();
    this.jdField_new = null;
  }

  private void jdField_void()
  {
    if ((this.jdField_new != null) && (g.a(this)))
      g.f();
  }

  public boolean jdField_else()
  {
    return ((KeyguardManager)getSystemService("keyguard")).inKeyguardRestrictedInputMode();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.an.getBinder();
  }

  public void onCreate()
  {
    Thread.setDefaultUncaughtExceptionHandler(new a(this));
    this.r = new c(this, this.R);
    this.F = new e(this, this.R);
    this.au = new a(this.R);
    this.r.jdField_do();
    this.F.jdField_try();
    this.ad = true;
    this.O = false;
    this.aj = false;
    try
    {
      d();
      label92: j.jdField_if("baidu_location_service", "OnCreate");
      Log.d("baidu_location_service", "baidu location service start1 ..." + Process.myPid());
      return;
    }
    catch (Exception localException)
    {
      break label92;
    }
  }

  public void onDestroy()
  {
    if (this.r != null)
      this.r.jdField_byte();
    if (this.F != null)
      this.F.jdField_else();
    if (this.ab != null)
      this.ab.l();
    k.a();
    this.O = false;
    this.aj = false;
    this.ad = false;
    if (this.y != null)
      this.y.jdField_try();
    if (this.T != null)
      this.T.close();
    j.jdField_if("baidu_location_service", "onDestroy");
    Log.d("baidu_location_service", "baidu location service stop ...");
    Process.killProcess(Process.myPid());
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    j.jdField_if("baidu_location_service", "onStratCommandNotSticky");
    return 2;
  }

  public class a
    implements Thread.UncaughtExceptionHandler
  {
    private Context jdField_if;

    a(Context arg2)
    {
      Object localObject;
      this.jdField_if = localObject;
      a();
    }

    private String a(Throwable paramThrowable)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
      paramThrowable.printStackTrace(localPrintWriter);
      localPrintWriter.close();
      return localStringWriter.toString();
    }

    public void a()
    {
      while (true)
      {
        try
        {
          String str1 = Environment.getExternalStorageDirectory().getPath() + "/traces";
          File localFile = new File(str1 + "/error_fs.dat");
          if (!localFile.exists())
            continue;
          RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
          localRandomAccessFile.seek(280L);
          if (1326 != localRandomAccessFile.readInt())
            continue;
          localRandomAccessFile.seek(308L);
          int i = localRandomAccessFile.readInt();
          if ((i > 0) && (i < 2048))
          {
            j.jdField_if("baidu_location_service", "A" + i);
            byte[] arrayOfByte2 = new byte[i];
            localRandomAccessFile.read(arrayOfByte2, 0, i);
            str2 = new String(arrayOfByte2, 0, i);
            localRandomAccessFile.seek(600L);
            int j = localRandomAccessFile.readInt();
            String str3 = null;
            if (j <= 0)
              continue;
            str3 = null;
            if (j >= 2048)
              continue;
            j.jdField_if("baidu_location_service", "A" + j);
            byte[] arrayOfByte1 = new byte[j];
            localRandomAccessFile.read(arrayOfByte1, 0, j);
            str3 = new String(arrayOfByte1, 0, j);
            j.jdField_if("baidu_location_service", str2 + str3);
            if (!a(str2, str3))
              continue;
            localRandomAccessFile.seek(280L);
            localRandomAccessFile.writeInt(12346);
            localRandomAccessFile.close();
            return;
          }
        }
        catch (Exception localException)
        {
          return;
        }
        String str2 = null;
      }
    }

    public void a(File paramFile, String paramString1, String paramString2)
    {
      try
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramFile, "rw");
        localRandomAccessFile.seek(280L);
        localRandomAccessFile.writeInt(12346);
        localRandomAccessFile.seek(300L);
        localRandomAccessFile.writeLong(System.currentTimeMillis());
        byte[] arrayOfByte1 = paramString1.getBytes();
        localRandomAccessFile.writeInt(arrayOfByte1.length);
        localRandomAccessFile.write(arrayOfByte1, 0, arrayOfByte1.length);
        localRandomAccessFile.seek(600L);
        byte[] arrayOfByte2 = paramString2.getBytes();
        localRandomAccessFile.writeInt(arrayOfByte2.length);
        localRandomAccessFile.write(arrayOfByte2, 0, arrayOfByte2.length);
        if (!a(paramString1, paramString2))
        {
          localRandomAccessFile.seek(280L);
          localRandomAccessFile.writeInt(1326);
        }
        localRandomAccessFile.close();
        return;
      }
      catch (Exception localException)
      {
      }
    }

    boolean a(String paramString1, String paramString2)
    {
      if (!g.a(this.jdField_if));
      while (true)
      {
        return false;
        try
        {
          HttpPost localHttpPost = new HttpPost(j.N);
          ArrayList localArrayList = new ArrayList();
          localArrayList.add(new BasicNameValuePair("e0", paramString1));
          localArrayList.add(new BasicNameValuePair("e1", paramString2));
          localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
          DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
          localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(12000));
          localDefaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(12000));
          j.jdField_if("baidu_location_service", "send begin ...");
          if (localDefaultHttpClient.execute(localHttpPost).getStatusLine().getStatusCode() != 200)
            continue;
          j.jdField_if("baidu_location_service", "send ok....");
          return true;
        }
        catch (Exception localException)
        {
        }
      }
      return false;
    }

    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      if (!j.x)
      {
        Process.killProcess(Process.myPid());
        return;
      }
      try
      {
        String str3 = a(paramThrowable);
        localObject2 = str3;
      }
      catch (Exception localException1)
      {
        try
        {
          j.jdField_if("baidu_location_service", (String)localObject2);
          f.jdMethod_long(f.this);
          String str4 = c.a(false);
          if (f.jdMethod_new(f.this) != null)
            str4 = str4 + f.jdMethod_new(f.this).jdMethod_byte();
          if (str4 != null)
          {
            String str5 = Jni.jdField_if(str4);
            str6 = str5;
            String str1 = str6;
            try
            {
              String str2 = Environment.getExternalStorageDirectory().getPath() + "/traces";
              localFile1 = new File(str2 + "/error_fs.dat");
              if (!localFile1.exists())
              {
                File localFile2 = new File(str2);
                if (!localFile2.exists())
                  localFile2.mkdirs();
                boolean bool = localFile1.createNewFile();
                localFile3 = null;
                if (!bool)
                  a(localFile3, str1, (String)localObject2);
              }
              else
              {
                while (true)
                {
                  label218: Process.killProcess(Process.myPid());
                  return;
                  localException1 = localException1;
                  localObject1 = null;
                  localObject2 = localObject1;
                  str1 = null;
                  break;
                  RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile1, "rw");
                  localRandomAccessFile.seek(300L);
                  long l = localRandomAccessFile.readLong();
                  if (System.currentTimeMillis() - l <= 604800000L)
                    continue;
                  a(localFile1, str1, (String)localObject2);
                }
              }
            }
            catch (Exception localException2)
            {
              break label218;
            }
          }
        }
        catch (Exception localException3)
        {
          while (true)
          {
            Object localObject2;
            File localFile1;
            Object localObject1 = localObject2;
            continue;
            File localFile3 = localFile1;
            continue;
            String str6 = null;
          }
        }
      }
    }
  }

  private class b
    implements Runnable
  {
    private b()
    {
    }

    public void run()
    {
      if (f.jdMethod_int(f.this) == true)
      {
        f.a(f.this, false);
        f.jdMethod_else(f.this);
      }
    }
  }

  public class c
  {
    public static final String jdField_for = "com.baidu.locTest.LocationServer";
    private long[] a = new long[20];
    private c.a b = null;
    private int jdField_byte = 1;
    private String c = null;
    private a jdField_case = null;
    private final int jdField_char = 200;
    private PendingIntent d = null;
    private boolean jdField_do = false;
    private boolean jdField_else = false;
    private Context jdField_goto = null;
    private boolean jdField_if = false;
    private int jdField_int = 0;
    private String jdField_long = null;
    private final long jdField_new = 86100000L;
    private AlarmManager jdField_try = null;
    private long jdField_void = 0L;

    public c(Context arg2)
    {
      Context localContext;
      this.jdField_goto = localContext;
      this.jdField_void = System.currentTimeMillis();
      this.jdField_try = ((AlarmManager)localContext.getSystemService("alarm"));
      this.jdField_case = new a();
      localContext.registerReceiver(this.jdField_case, new IntentFilter("com.baidu.locTest.LocationServer"));
      this.d = PendingIntent.getBroadcast(localContext, 0, new Intent("com.baidu.locTest.LocationServer"), 134217728);
      this.jdField_try.setRepeating(2, j.M, j.M, this.d);
      f.this.registerReceiver(this.jdField_case, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public void a()
    {
      int i = 0;
      jdField_if();
      if (f.jdField_for() == null)
        return;
      try
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(f.jdField_for(), "rw");
        if (localRandomAccessFile.length() < 1L)
        {
          localRandomAccessFile.close();
          return;
        }
        localRandomAccessFile.seek(0L);
        int j = localRandomAccessFile.readInt();
        int k = 4 + j * 200;
        int m = j + 1;
        localRandomAccessFile.seek(0L);
        localRandomAccessFile.writeInt(m);
        localRandomAccessFile.seek(k);
        localRandomAccessFile.writeLong(System.currentTimeMillis());
        localRandomAccessFile.writeInt(this.jdField_byte);
        localRandomAccessFile.writeInt(0);
        localRandomAccessFile.writeInt(this.jdField_int);
        localRandomAccessFile.writeInt(this.b.jdField_do);
        localRandomAccessFile.writeInt(this.b.jdField_if);
        localRandomAccessFile.writeInt(this.b.jdField_for);
        localRandomAccessFile.writeInt(this.b.jdField_try);
        byte[] arrayOfByte = new byte[' '];
        while (i < this.jdField_int)
        {
          arrayOfByte[(7 + i * 8)] = (byte)(int)this.a[i];
          arrayOfByte[(6 + i * 8)] = (byte)(int)(this.a[i] >> 8);
          arrayOfByte[(5 + i * 8)] = (byte)(int)(this.a[i] >> 16);
          arrayOfByte[(4 + i * 8)] = (byte)(int)(this.a[i] >> 24);
          arrayOfByte[(3 + i * 8)] = (byte)(int)(this.a[i] >> 32);
          arrayOfByte[(2 + i * 8)] = (byte)(int)(this.a[i] >> 40);
          arrayOfByte[(1 + i * 8)] = (byte)(int)(this.a[i] >> 48);
          arrayOfByte[(0 + i * 8)] = (byte)(int)(this.a[i] >> 56);
          i++;
        }
        if (this.jdField_int > 0)
          localRandomAccessFile.write(arrayOfByte, 0, 8 * this.jdField_int);
        localRandomAccessFile.writeInt(this.jdField_int);
        localRandomAccessFile.close();
        return;
      }
      catch (Exception localException)
      {
      }
    }

    public void jdField_byte()
    {
      String str;
      if (this.jdField_do)
      {
        this.jdField_byte = 1;
        j.M = 60L * (1000L * j.ac);
        j.al = j.M >> 2;
        Calendar localCalendar = Calendar.getInstance();
        int i = localCalendar.get(5);
        int j = localCalendar.get(1);
        int k = 0;
        if (j > 2000)
          k = j - 2000;
        int m = 1 + localCalendar.get(2);
        int n = localCalendar.get(11);
        int i1 = localCalendar.get(12);
        str = k + "," + m + "," + i + "," + n + "," + i1 + "," + j.ac;
        if (!this.jdField_if)
          break label304;
        this.jdField_long = ("&tr=" + j.jdField_do + "," + str);
      }
      while (true)
      {
        j.jdField_if("baidu_location_service", "trace begin:" + this.jdField_long);
        try
        {
          RandomAccessFile localRandomAccessFile1 = new RandomAccessFile(f.a(), "rw");
          localRandomAccessFile1.seek(12L);
          localRandomAccessFile1.writeLong(System.currentTimeMillis());
          localRandomAccessFile1.writeInt(this.jdField_byte);
          localRandomAccessFile1.close();
          RandomAccessFile localRandomAccessFile2 = new RandomAccessFile(f.jdField_for(), "rw");
          localRandomAccessFile2.seek(0L);
          localRandomAccessFile2.writeInt(0);
          localRandomAccessFile2.close();
          return;
          label304: this.jdField_long = (this.jdField_long + "|T" + str);
        }
        catch (Exception localException)
        {
        }
      }
    }

    public void jdField_case()
    {
      int i = 0;
      f.jdField_long();
      if (f.a() == null)
        return;
      while (true)
      {
        int k;
        try
        {
          RandomAccessFile localRandomAccessFile = new RandomAccessFile(f.a(), "rw");
          if (localRandomAccessFile.length() >= 1L)
            continue;
          localRandomAccessFile.close();
          return;
          localRandomAccessFile.seek(0L);
          int j = localRandomAccessFile.readInt();
          k = localRandomAccessFile.readInt();
          int m = localRandomAccessFile.readInt();
          if ((this.jdField_do) && (this.jdField_if))
          {
            j.jdField_if("baidu_location_service", "trace new info:" + j + ":" + k + ":" + m);
            int i2 = (k + 1) % 200;
            localRandomAccessFile.seek(4L);
            localRandomAccessFile.writeInt(i2);
            j++;
            if (j < 200)
              continue;
            j = 199;
            if ((i2 != m) || (j <= 0))
              continue;
            m = (m + 1) % 200;
            localRandomAccessFile.writeInt(m);
            j.jdField_if("baidu_location_service", "trace new info:" + j + ":" + k + ":" + m);
            int n = 24 + i2 * 800;
            localRandomAccessFile.seek(n + 4);
            byte[] arrayOfByte = this.jdField_long.getBytes();
            if (i >= arrayOfByte.length)
              continue;
            arrayOfByte[i] = (byte)(0x5A ^ arrayOfByte[i]);
            i++;
            continue;
            localRandomAccessFile.write(arrayOfByte, 0, arrayOfByte.length);
            localRandomAccessFile.writeInt(arrayOfByte.length);
            localRandomAccessFile.seek(n);
            localRandomAccessFile.writeInt(arrayOfByte.length);
            if ((!this.jdField_do) || (!this.jdField_if))
              continue;
            localRandomAccessFile.seek(0L);
            localRandomAccessFile.writeInt(j);
            localRandomAccessFile.close();
            return;
          }
        }
        catch (Exception localException)
        {
          return;
        }
        int i1 = 24 + k * 800;
      }
    }

    // ERROR //
    public void jdField_do()
    {
      // Byte code:
      //   0: ldc 225
      //   2: ldc 252
      //   4: invokestatic 230	com/baidu/location/j:jdField_if	(Ljava/lang/String;Ljava/lang/String;)V
      //   7: aload_0
      //   8: invokevirtual 254	com/baidu/location/f$c:jdField_new	()V
      //   11: aload_0
      //   12: getfield 74	com/baidu/location/f$c:jdField_else	Z
      //   15: ifeq +9 -> 24
      //   18: aload_0
      //   19: iconst_0
      //   20: putfield 74	com/baidu/location/f$c:jdField_else	Z
      //   23: return
      //   24: aload_0
      //   25: invokevirtual 256	com/baidu/location/f$c:jdField_byte	()V
      //   28: aload_0
      //   29: iconst_0
      //   30: putfield 62	com/baidu/location/f$c:jdField_int	I
      //   33: aload_0
      //   34: aconst_null
      //   35: putfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   38: aload_0
      //   39: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   42: invokestatic 259	com/baidu/location/f:jdField_goto	(Lcom/baidu/location/f;)Lcom/baidu/location/e;
      //   45: ifnull +14 -> 59
      //   48: aload_0
      //   49: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   52: invokestatic 259	com/baidu/location/f:jdField_goto	(Lcom/baidu/location/f;)Lcom/baidu/location/e;
      //   55: invokevirtual 264	com/baidu/location/e:jdField_new	()Z
      //   58: pop
      //   59: aload_0
      //   60: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   63: invokestatic 259	com/baidu/location/f:jdField_goto	(Lcom/baidu/location/f;)Lcom/baidu/location/e;
      //   66: ifnull +120 -> 186
      //   69: aload_0
      //   70: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   73: invokestatic 259	com/baidu/location/f:jdField_goto	(Lcom/baidu/location/f;)Lcom/baidu/location/e;
      //   76: invokevirtual 267	com/baidu/location/e:jdField_byte	()Lcom/baidu/location/e$c;
      //   79: astore_2
      //   80: aload_2
      //   81: ifnull +105 -> 186
      //   84: aload_2
      //   85: getfield 272	com/baidu/location/e$c:jdField_for	Ljava/util/List;
      //   88: ifnull +98 -> 186
      //   91: aload_2
      //   92: getfield 272	com/baidu/location/e$c:jdField_for	Ljava/util/List;
      //   95: invokeinterface 277 1 0
      //   100: istore_3
      //   101: iload_3
      //   102: bipush 20
      //   104: if_icmple +135 -> 239
      //   107: bipush 20
      //   109: istore_3
      //   110: goto +129 -> 239
      //   113: iload 5
      //   115: iload_3
      //   116: if_icmpge +64 -> 180
      //   119: aload_2
      //   120: getfield 272	com/baidu/location/e$c:jdField_for	Ljava/util/List;
      //   123: iload 5
      //   125: invokeinterface 280 2 0
      //   130: checkcast 282	android/net/wifi/ScanResult
      //   133: getfield 285	android/net/wifi/ScanResult:BSSID	Ljava/lang/String;
      //   136: ldc 242
      //   138: ldc_w 287
      //   141: invokevirtual 291	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   144: astore 6
      //   146: aload_0
      //   147: getfield 60	com/baidu/location/f$c:a	[J
      //   150: astore 9
      //   152: iload 4
      //   154: iconst_1
      //   155: iadd
      //   156: istore 8
      //   158: aload 9
      //   160: iload 4
      //   162: aload 6
      //   164: bipush 16
      //   166: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;I)J
      //   169: lastore
      //   170: iinc 5 1
      //   173: iload 8
      //   175: istore 4
      //   177: goto -64 -> 113
      //   180: aload_0
      //   181: iload 4
      //   183: putfield 62	com/baidu/location/f$c:jdField_int	I
      //   186: aload_0
      //   187: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   190: invokestatic 300	com/baidu/location/f:jdField_long	(Lcom/baidu/location/f;)Lcom/baidu/location/c;
      //   193: ifnull +17 -> 210
      //   196: aload_0
      //   197: aload_0
      //   198: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   201: invokestatic 300	com/baidu/location/f:jdField_long	(Lcom/baidu/location/f;)Lcom/baidu/location/c;
      //   204: invokevirtual 305	com/baidu/location/c:a	()Lcom/baidu/location/c$a;
      //   207: putfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   210: aload_0
      //   211: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   214: ifnull +24 -> 238
      //   217: aload_0
      //   218: invokevirtual 307	com/baidu/location/f$c:jdField_for	()V
      //   221: return
      //   222: astore_1
      //   223: return
      //   224: astore 7
      //   226: iload 4
      //   228: istore 8
      //   230: goto -60 -> 170
      //   233: astore 10
      //   235: goto -65 -> 170
      //   238: return
      //   239: iconst_0
      //   240: istore 4
      //   242: iconst_0
      //   243: istore 5
      //   245: goto -132 -> 113
      //
      // Exception table:
      //   from	to	target	type
      //   0	23	222	java/lang/Exception
      //   24	59	222	java/lang/Exception
      //   59	80	222	java/lang/Exception
      //   84	101	222	java/lang/Exception
      //   119	146	222	java/lang/Exception
      //   180	186	222	java/lang/Exception
      //   186	210	222	java/lang/Exception
      //   210	221	222	java/lang/Exception
      //   146	152	224	java/lang/Exception
      //   158	170	233	java/lang/Exception
    }

    // ERROR //
    public void jdField_for()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 135	com/baidu/location/f$c:jdField_if	()V
      //   4: ldc 225
      //   6: new 202	java/lang/StringBuilder
      //   9: dup
      //   10: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   13: ldc_w 309
      //   16: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   19: aload_0
      //   20: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   23: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   26: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   29: invokestatic 230	com/baidu/location/j:jdField_if	(Ljava/lang/String;Ljava/lang/String;)V
      //   32: aload_0
      //   33: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   36: invokevirtual 311	com/baidu/location/f:jdField_else	()Z
      //   39: ifeq +44 -> 83
      //   42: ldc_w 313
      //   45: astore_2
      //   46: aload_0
      //   47: getfield 70	com/baidu/location/f$c:jdField_do	Z
      //   50: ifne +1341 -> 1391
      //   53: new 140	java/io/RandomAccessFile
      //   56: dup
      //   57: invokestatic 138	com/baidu/location/f:jdField_for	()Ljava/io/File;
      //   60: ldc 142
      //   62: invokespecial 145	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   65: astore 14
      //   67: aload 14
      //   69: invokevirtual 148	java/io/RandomAccessFile:length	()J
      //   72: lconst_1
      //   73: lcmp
      //   74: ifge +24 -> 98
      //   77: aload 14
      //   79: invokevirtual 151	java/io/RandomAccessFile:close	()V
      //   82: return
      //   83: ldc_w 315
      //   86: astore_2
      //   87: goto -41 -> 46
      //   90: astore_1
      //   91: ldc_w 317
      //   94: astore_2
      //   95: goto -49 -> 46
      //   98: aload 14
      //   100: invokevirtual 159	java/io/RandomAccessFile:readInt	()I
      //   103: istore 16
      //   105: iconst_0
      //   106: istore 17
      //   108: iload 17
      //   110: iload 16
      //   112: if_icmpge +1279 -> 1391
      //   115: aload 14
      //   117: iconst_4
      //   118: iload 17
      //   120: sipush 200
      //   123: imul
      //   124: iadd
      //   125: i2l
      //   126: invokevirtual 155	java/io/RandomAccessFile:seek	(J)V
      //   129: aload 14
      //   131: invokevirtual 320	java/io/RandomAccessFile:readLong	()J
      //   134: pop2
      //   135: aload 14
      //   137: invokevirtual 159	java/io/RandomAccessFile:readInt	()I
      //   140: istore 20
      //   142: aload 14
      //   144: invokevirtual 159	java/io/RandomAccessFile:readInt	()I
      //   147: istore 21
      //   149: aload 14
      //   151: invokevirtual 159	java/io/RandomAccessFile:readInt	()I
      //   154: istore 22
      //   156: sipush 200
      //   159: newarray byte
      //   161: astore 23
      //   163: aload 14
      //   165: aload 23
      //   167: iconst_0
      //   168: bipush 16
      //   170: iload 22
      //   172: bipush 8
      //   174: imul
      //   175: iadd
      //   176: invokevirtual 324	java/io/RandomAccessFile:read	([BII)I
      //   179: pop
      //   180: sipush 255
      //   183: aload 23
      //   185: iconst_3
      //   186: baload
      //   187: iand
      //   188: ldc_w 325
      //   191: aload 23
      //   193: iconst_2
      //   194: baload
      //   195: bipush 8
      //   197: ishl
      //   198: iand
      //   199: ior
      //   200: ldc_w 326
      //   203: aload 23
      //   205: iconst_1
      //   206: baload
      //   207: bipush 16
      //   209: ishl
      //   210: iand
      //   211: ior
      //   212: ldc_w 327
      //   215: aload 23
      //   217: iconst_0
      //   218: baload
      //   219: bipush 24
      //   221: ishl
      //   222: iand
      //   223: ior
      //   224: istore 25
      //   226: sipush 255
      //   229: aload 23
      //   231: bipush 7
      //   233: baload
      //   234: iand
      //   235: ldc_w 325
      //   238: aload 23
      //   240: bipush 6
      //   242: baload
      //   243: bipush 8
      //   245: ishl
      //   246: iand
      //   247: ior
      //   248: ldc_w 326
      //   251: aload 23
      //   253: iconst_5
      //   254: baload
      //   255: bipush 16
      //   257: ishl
      //   258: iand
      //   259: ior
      //   260: ldc_w 327
      //   263: aload 23
      //   265: iconst_4
      //   266: baload
      //   267: bipush 24
      //   269: ishl
      //   270: iand
      //   271: ior
      //   272: istore 26
      //   274: sipush 255
      //   277: aload 23
      //   279: bipush 11
      //   281: baload
      //   282: iand
      //   283: ldc_w 325
      //   286: aload 23
      //   288: bipush 10
      //   290: baload
      //   291: bipush 8
      //   293: ishl
      //   294: iand
      //   295: ior
      //   296: ldc_w 326
      //   299: aload 23
      //   301: bipush 9
      //   303: baload
      //   304: bipush 16
      //   306: ishl
      //   307: iand
      //   308: ior
      //   309: ldc_w 327
      //   312: aload 23
      //   314: bipush 8
      //   316: baload
      //   317: bipush 24
      //   319: ishl
      //   320: iand
      //   321: ior
      //   322: istore 27
      //   324: sipush 255
      //   327: aload 23
      //   329: bipush 15
      //   331: baload
      //   332: iand
      //   333: ldc_w 325
      //   336: aload 23
      //   338: bipush 14
      //   340: baload
      //   341: bipush 8
      //   343: ishl
      //   344: iand
      //   345: ior
      //   346: ldc_w 326
      //   349: aload 23
      //   351: bipush 13
      //   353: baload
      //   354: bipush 16
      //   356: ishl
      //   357: iand
      //   358: ior
      //   359: ldc_w 327
      //   362: aload 23
      //   364: bipush 12
      //   366: baload
      //   367: bipush 24
      //   369: ishl
      //   370: iand
      //   371: ior
      //   372: istore 28
      //   374: aload_0
      //   375: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   378: getfield 170	com/baidu/location/c$a:jdField_do	I
      //   381: iload 25
      //   383: if_icmpne +815 -> 1198
      //   386: aload_0
      //   387: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   390: getfield 172	com/baidu/location/c$a:jdField_if	I
      //   393: iload 26
      //   395: if_icmpne +803 -> 1198
      //   398: aload_0
      //   399: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   402: getfield 174	com/baidu/location/c$a:jdField_for	I
      //   405: iload 27
      //   407: if_icmpne +791 -> 1198
      //   410: aload_0
      //   411: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   414: getfield 176	com/baidu/location/c$a:jdField_try	I
      //   417: iload 28
      //   419: if_icmpne +779 -> 1198
      //   422: iload 22
      //   424: newarray long
      //   426: astore 29
      //   428: iconst_0
      //   429: istore 30
      //   431: iload 30
      //   433: iload 22
      //   435: if_icmpge +961 -> 1396
      //   438: aload 29
      //   440: iload 30
      //   442: ldc2_w 328
      //   445: aload 23
      //   447: bipush 16
      //   449: iload 30
      //   451: bipush 8
      //   453: imul
      //   454: iadd
      //   455: baload
      //   456: i2l
      //   457: land
      //   458: bipush 56
      //   460: lshl
      //   461: ldc2_w 328
      //   464: aload 23
      //   466: iconst_1
      //   467: bipush 16
      //   469: iload 30
      //   471: bipush 8
      //   473: imul
      //   474: iadd
      //   475: iadd
      //   476: baload
      //   477: i2l
      //   478: land
      //   479: bipush 48
      //   481: lshl
      //   482: lor
      //   483: ldc2_w 328
      //   486: aload 23
      //   488: iconst_2
      //   489: bipush 16
      //   491: iload 30
      //   493: bipush 8
      //   495: imul
      //   496: iadd
      //   497: iadd
      //   498: baload
      //   499: i2l
      //   500: land
      //   501: bipush 40
      //   503: lshl
      //   504: lor
      //   505: ldc2_w 328
      //   508: aload 23
      //   510: iconst_3
      //   511: bipush 16
      //   513: iload 30
      //   515: bipush 8
      //   517: imul
      //   518: iadd
      //   519: iadd
      //   520: baload
      //   521: i2l
      //   522: land
      //   523: bipush 32
      //   525: lshl
      //   526: lor
      //   527: ldc2_w 328
      //   530: aload 23
      //   532: iconst_4
      //   533: bipush 16
      //   535: iload 30
      //   537: bipush 8
      //   539: imul
      //   540: iadd
      //   541: iadd
      //   542: baload
      //   543: i2l
      //   544: land
      //   545: bipush 24
      //   547: lshl
      //   548: lor
      //   549: ldc2_w 328
      //   552: aload 23
      //   554: iconst_5
      //   555: bipush 16
      //   557: iload 30
      //   559: bipush 8
      //   561: imul
      //   562: iadd
      //   563: iadd
      //   564: baload
      //   565: i2l
      //   566: land
      //   567: bipush 16
      //   569: lshl
      //   570: lor
      //   571: ldc2_w 328
      //   574: aload 23
      //   576: bipush 6
      //   578: bipush 16
      //   580: iload 30
      //   582: bipush 8
      //   584: imul
      //   585: iadd
      //   586: iadd
      //   587: baload
      //   588: i2l
      //   589: land
      //   590: bipush 8
      //   592: lshl
      //   593: lor
      //   594: ldc2_w 328
      //   597: aload 23
      //   599: bipush 7
      //   601: bipush 16
      //   603: iload 30
      //   605: bipush 8
      //   607: imul
      //   608: iadd
      //   609: iadd
      //   610: baload
      //   611: i2l
      //   612: land
      //   613: lor
      //   614: lastore
      //   615: iinc 30 1
      //   618: goto -187 -> 431
      //   621: iload 32
      //   623: aload_0
      //   624: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   627: if_icmpge +39 -> 666
      //   630: iload 31
      //   632: istore 33
      //   634: iconst_0
      //   635: istore 34
      //   637: iload 34
      //   639: iload 22
      //   641: if_icmpge +770 -> 1411
      //   644: aload_0
      //   645: getfield 60	com/baidu/location/f$c:a	[J
      //   648: iload 32
      //   650: laload
      //   651: aload 29
      //   653: iload 34
      //   655: laload
      //   656: lcmp
      //   657: ifne +748 -> 1405
      //   660: iinc 33 1
      //   663: goto +742 -> 1405
      //   666: iload 31
      //   668: iconst_5
      //   669: if_icmpgt +100 -> 769
      //   672: iload 31
      //   674: bipush 8
      //   676: imul
      //   677: iload 22
      //   679: aload_0
      //   680: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   683: iadd
      //   684: if_icmpgt +85 -> 769
      //   687: iload 22
      //   689: ifne +10 -> 699
      //   692: aload_0
      //   693: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   696: ifeq +73 -> 769
      //   699: iload 22
      //   701: iconst_1
      //   702: if_icmpne +25 -> 727
      //   705: aload_0
      //   706: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   709: iconst_1
      //   710: if_icmpne +17 -> 727
      //   713: aload_0
      //   714: getfield 60	com/baidu/location/f$c:a	[J
      //   717: iconst_0
      //   718: laload
      //   719: aload 29
      //   721: iconst_0
      //   722: laload
      //   723: lcmp
      //   724: ifeq +45 -> 769
      //   727: iload 22
      //   729: iconst_1
      //   730: if_icmple +468 -> 1198
      //   733: aload_0
      //   734: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   737: iconst_1
      //   738: if_icmple +460 -> 1198
      //   741: aload_0
      //   742: getfield 60	com/baidu/location/f$c:a	[J
      //   745: iconst_0
      //   746: laload
      //   747: aload 29
      //   749: iconst_0
      //   750: laload
      //   751: lcmp
      //   752: ifne +446 -> 1198
      //   755: aload_0
      //   756: getfield 60	com/baidu/location/f$c:a	[J
      //   759: iconst_1
      //   760: laload
      //   761: aload 29
      //   763: iconst_1
      //   764: laload
      //   765: lcmp
      //   766: ifne +432 -> 1198
      //   769: iconst_1
      //   770: istore_3
      //   771: iload 21
      //   773: iconst_1
      //   774: iadd
      //   775: istore 35
      //   777: aload 14
      //   779: bipush 16
      //   781: iload 17
      //   783: sipush 200
      //   786: imul
      //   787: iadd
      //   788: i2l
      //   789: invokevirtual 155	java/io/RandomAccessFile:seek	(J)V
      //   792: aload 14
      //   794: iload 35
      //   796: invokevirtual 163	java/io/RandomAccessFile:writeInt	(I)V
      //   799: aload_0
      //   800: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   803: ifnull +74 -> 877
      //   806: aload_0
      //   807: new 202	java/lang/StringBuilder
      //   810: dup
      //   811: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   814: aload_0
      //   815: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   818: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   821: ldc_w 331
      //   824: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   827: iload 20
      //   829: invokevirtual 207	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   832: aload_2
      //   833: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   836: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   839: putfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   842: aload_0
      //   843: getfield 76	com/baidu/location/f$c:c	Ljava/lang/String;
      //   846: ifnull +31 -> 877
      //   849: aload_0
      //   850: new 202	java/lang/StringBuilder
      //   853: dup
      //   854: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   857: aload_0
      //   858: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   861: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   864: aload_0
      //   865: getfield 76	com/baidu/location/f$c:c	Ljava/lang/String;
      //   868: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   871: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   874: putfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   877: ldc 225
      //   879: ldc_w 333
      //   882: invokestatic 230	com/baidu/location/j:jdField_if	(Ljava/lang/String;Ljava/lang/String;)V
      //   885: iload_3
      //   886: ifne +274 -> 1160
      //   889: aload_0
      //   890: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   893: getfield 170	com/baidu/location/c$a:jdField_do	I
      //   896: sipush 460
      //   899: if_icmpne +305 -> 1204
      //   902: ldc_w 335
      //   905: astore 4
      //   907: new 202	java/lang/StringBuilder
      //   910: dup
      //   911: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   914: aload 4
      //   916: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   919: aload_0
      //   920: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   923: getfield 172	com/baidu/location/c$a:jdField_if	I
      //   926: invokevirtual 207	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   929: ldc 209
      //   931: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   934: aload_0
      //   935: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   938: getfield 174	com/baidu/location/c$a:jdField_for	I
      //   941: invokevirtual 207	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   944: ldc 209
      //   946: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   949: aload_0
      //   950: getfield 64	com/baidu/location/f$c:b	Lcom/baidu/location/c$a;
      //   953: getfield 176	com/baidu/location/c$a:jdField_try	I
      //   956: invokevirtual 207	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   959: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   962: astore 5
      //   964: lconst_0
      //   965: lstore 6
      //   967: aload_0
      //   968: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   971: invokestatic 259	com/baidu/location/f:jdField_goto	(Lcom/baidu/location/f;)Lcom/baidu/location/e;
      //   974: ifnull +33 -> 1007
      //   977: aload_0
      //   978: getfield 39	com/baidu/location/f$c:e	Lcom/baidu/location/f;
      //   981: invokestatic 259	com/baidu/location/f:jdField_goto	(Lcom/baidu/location/f;)Lcom/baidu/location/e;
      //   984: invokevirtual 337	com/baidu/location/e:jdField_char	()Ljava/lang/String;
      //   987: astore 10
      //   989: aload 10
      //   991: ifnull +16 -> 1007
      //   994: aload 10
      //   996: bipush 16
      //   998: invokestatic 297	java/lang/Long:parseLong	(Ljava/lang/String;I)J
      //   1001: lstore 12
      //   1003: lload 12
      //   1005: lstore 6
      //   1007: aload_0
      //   1008: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   1011: iconst_1
      //   1012: if_icmpne +200 -> 1212
      //   1015: new 202	java/lang/StringBuilder
      //   1018: dup
      //   1019: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1022: aload 5
      //   1024: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1027: ldc_w 339
      //   1030: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1033: aload_0
      //   1034: getfield 60	com/baidu/location/f$c:a	[J
      //   1037: iconst_0
      //   1038: laload
      //   1039: invokestatic 343	java/lang/Long:toHexString	(J)Ljava/lang/String;
      //   1042: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1045: ldc_w 345
      //   1048: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1051: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1054: astore 5
      //   1056: aload_0
      //   1057: getfield 60	com/baidu/location/f$c:a	[J
      //   1060: iconst_0
      //   1061: laload
      //   1062: lload 6
      //   1064: lcmp
      //   1065: ifne +319 -> 1384
      //   1068: new 202	java/lang/StringBuilder
      //   1071: dup
      //   1072: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1075: aload 5
      //   1077: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1080: ldc_w 345
      //   1083: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1086: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1089: astore 8
      //   1091: aload_0
      //   1092: new 202	java/lang/StringBuilder
      //   1095: dup
      //   1096: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1099: aload_0
      //   1100: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   1103: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1106: aload 8
      //   1108: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1111: aload_2
      //   1112: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1115: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1118: putfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   1121: aload_0
      //   1122: getfield 76	com/baidu/location/f$c:c	Ljava/lang/String;
      //   1125: ifnull +31 -> 1156
      //   1128: aload_0
      //   1129: new 202	java/lang/StringBuilder
      //   1132: dup
      //   1133: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1136: aload_0
      //   1137: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   1140: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1143: aload_0
      //   1144: getfield 76	com/baidu/location/f$c:c	Ljava/lang/String;
      //   1147: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1150: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1153: putfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   1156: aload_0
      //   1157: invokevirtual 347	com/baidu/location/f$c:a	()V
      //   1160: ldc 225
      //   1162: new 202	java/lang/StringBuilder
      //   1165: dup
      //   1166: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1169: ldc_w 349
      //   1172: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1175: aload_0
      //   1176: getfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   1179: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1182: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1185: invokestatic 230	com/baidu/location/j:jdField_if	(Ljava/lang/String;Ljava/lang/String;)V
      //   1188: aload_0
      //   1189: invokevirtual 351	com/baidu/location/f$c:jdField_case	()V
      //   1192: aload_0
      //   1193: aconst_null
      //   1194: putfield 66	com/baidu/location/f$c:jdField_long	Ljava/lang/String;
      //   1197: return
      //   1198: iinc 17 1
      //   1201: goto -1093 -> 108
      //   1204: ldc_w 353
      //   1207: astore 4
      //   1209: goto -302 -> 907
      //   1212: aload_0
      //   1213: getfield 62	com/baidu/location/f$c:jdField_int	I
      //   1216: iconst_1
      //   1217: if_icmple +167 -> 1384
      //   1220: new 202	java/lang/StringBuilder
      //   1223: dup
      //   1224: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1227: aload 5
      //   1229: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1232: ldc_w 339
      //   1235: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1238: aload_0
      //   1239: getfield 60	com/baidu/location/f$c:a	[J
      //   1242: iconst_0
      //   1243: laload
      //   1244: invokestatic 343	java/lang/Long:toHexString	(J)Ljava/lang/String;
      //   1247: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1250: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1253: astore 9
      //   1255: aload_0
      //   1256: getfield 60	com/baidu/location/f$c:a	[J
      //   1259: iconst_0
      //   1260: laload
      //   1261: lload 6
      //   1263: lcmp
      //   1264: ifne +29 -> 1293
      //   1267: new 202	java/lang/StringBuilder
      //   1270: dup
      //   1271: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1274: aload 9
      //   1276: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1279: ldc_w 345
      //   1282: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1285: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1288: astore 9
      //   1290: lconst_0
      //   1291: lstore 6
      //   1293: lload 6
      //   1295: lconst_0
      //   1296: lcmp
      //   1297: ifle +42 -> 1339
      //   1300: new 202	java/lang/StringBuilder
      //   1303: dup
      //   1304: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1307: aload 9
      //   1309: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1312: ldc 209
      //   1314: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1317: lload 6
      //   1319: invokestatic 343	java/lang/Long:toHexString	(J)Ljava/lang/String;
      //   1322: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1325: ldc_w 345
      //   1328: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1331: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1334: astore 8
      //   1336: goto -245 -> 1091
      //   1339: new 202	java/lang/StringBuilder
      //   1342: dup
      //   1343: invokespecial 203	java/lang/StringBuilder:<init>	()V
      //   1346: aload 9
      //   1348: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1351: ldc 209
      //   1353: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1356: aload_0
      //   1357: getfield 60	com/baidu/location/f$c:a	[J
      //   1360: iconst_1
      //   1361: laload
      //   1362: invokestatic 343	java/lang/Long:toHexString	(J)Ljava/lang/String;
      //   1365: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1368: invokevirtual 219	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1371: astore 8
      //   1373: goto -282 -> 1091
      //   1376: astore 11
      //   1378: goto -371 -> 1007
      //   1381: astore 15
      //   1383: return
      //   1384: aload 5
      //   1386: astore 8
      //   1388: goto -297 -> 1091
      //   1391: iconst_0
      //   1392: istore_3
      //   1393: goto -508 -> 885
      //   1396: iconst_0
      //   1397: istore 31
      //   1399: iconst_0
      //   1400: istore 32
      //   1402: goto -781 -> 621
      //   1405: iinc 34 1
      //   1408: goto -771 -> 637
      //   1411: iinc 32 1
      //   1414: iload 33
      //   1416: istore 31
      //   1418: goto -797 -> 621
      //
      // Exception table:
      //   from	to	target	type
      //   32	42	90	java/lang/Exception
      //   994	1003	1376	java/lang/Exception
      //   53	82	1381	java/lang/Exception
      //   98	105	1381	java/lang/Exception
      //   115	428	1381	java/lang/Exception
      //   438	615	1381	java/lang/Exception
      //   621	630	1381	java/lang/Exception
      //   644	660	1381	java/lang/Exception
      //   672	687	1381	java/lang/Exception
      //   692	699	1381	java/lang/Exception
      //   705	727	1381	java/lang/Exception
      //   733	769	1381	java/lang/Exception
      //   777	877	1381	java/lang/Exception
      //   877	885	1381	java/lang/Exception
    }

    public void jdField_if()
    {
      try
      {
        if (f.jdField_byte(f.this) != null)
        {
          f.a(new File(f.jdField_byte(f.this)));
          if (!f.jdField_for().exists())
          {
            File localFile = new File(f.ac);
            if (!localFile.exists())
              localFile.mkdirs();
            f.jdField_for().createNewFile();
            RandomAccessFile localRandomAccessFile = new RandomAccessFile(f.jdField_for(), "rw");
            localRandomAccessFile.seek(0L);
            localRandomAccessFile.writeInt(0);
            localRandomAccessFile.close();
            return;
          }
        }
        else
        {
          f.a(null);
          return;
        }
      }
      catch (Exception localException)
      {
        f.a(null);
      }
    }

    public void jdField_int()
    {
    }

    public void jdField_new()
    {
      this.jdField_do = false;
      this.jdField_if = false;
      jdField_if();
      f.jdField_long();
      RandomAccessFile localRandomAccessFile1;
      long l1;
      int k;
      int m;
      try
      {
        localRandomAccessFile1 = new RandomAccessFile(f.a(), "rw");
        localRandomAccessFile1.seek(0L);
        int i = localRandomAccessFile1.readInt();
        int j = localRandomAccessFile1.readInt();
        localRandomAccessFile1.readInt();
        l1 = localRandomAccessFile1.readLong();
        k = localRandomAccessFile1.readInt();
        if (i < 0)
        {
          this.jdField_do = true;
          this.jdField_if = true;
          localRandomAccessFile1.close();
          return;
        }
        localRandomAccessFile1.seek(24 + j * 800);
        m = localRandomAccessFile1.readInt();
        if (m > 680)
        {
          this.jdField_do = true;
          this.jdField_if = true;
          localRandomAccessFile1.close();
          return;
        }
      }
      catch (Exception localException)
      {
        j.jdField_if("baidu_location_service", "exception!!!");
        this.jdField_do = true;
        this.jdField_if = true;
        return;
      }
      byte[] arrayOfByte = new byte[800];
      localRandomAccessFile1.read(arrayOfByte, 0, m);
      int n = localRandomAccessFile1.readInt();
      int i1 = 0;
      if (m != n)
      {
        j.jdField_if("baidu_location_service", "trace true check fail");
        this.jdField_do = true;
        this.jdField_if = true;
        localRandomAccessFile1.close();
        return;
      }
      while (i1 < arrayOfByte.length)
      {
        arrayOfByte[i1] = (byte)(0x5A ^ arrayOfByte[i1]);
        i1++;
      }
      this.jdField_long = new String(arrayOfByte, 0, m);
      if (!this.jdField_long.contains("&tr="))
      {
        this.jdField_do = true;
        this.jdField_if = true;
        localRandomAccessFile1.close();
        return;
      }
      long l2 = System.currentTimeMillis();
      long l3 = l2 - l1;
      if (l3 > 3L * j.M - j.al)
        this.jdField_do = true;
      RandomAccessFile localRandomAccessFile2;
      while (true)
      {
        localRandomAccessFile1.seek(12L);
        localRandomAccessFile1.writeLong(l2);
        localRandomAccessFile1.writeInt(this.jdField_byte);
        localRandomAccessFile1.close();
        localRandomAccessFile2 = new RandomAccessFile(f.jdField_for(), "rw");
        localRandomAccessFile2.seek(0L);
        if (localRandomAccessFile2.readInt() != 0)
          break;
        this.jdField_do = true;
        localRandomAccessFile2.close();
        j.jdField_if("baidu_location_service", "Day file number 0");
        return;
        if (l3 > 2L * j.M - j.al)
        {
          this.jdField_long = (this.jdField_long + "|" + k);
          this.jdField_byte = (k + 2);
          continue;
        }
        if (l3 > j.M - j.al)
        {
          this.jdField_byte = (k + 1);
          continue;
        }
        this.jdField_else = true;
        localRandomAccessFile1.close();
        return;
      }
      localRandomAccessFile2.close();
    }

    public void jdField_try()
    {
      this.jdField_goto.unregisterReceiver(this.jdField_case);
      this.jdField_try.cancel(this.d);
      f.a(null);
    }

    public class a extends BroadcastReceiver
    {
      public a()
      {
      }

      public void onReceive(Context paramContext, Intent paramIntent)
      {
        String str = paramIntent.getAction();
        if (str.equals("com.baidu.locTest.LocationServer"))
          f.this.R.obtainMessage(101).sendToTarget();
        int j;
        while (true)
        {
          return;
          try
          {
            if (!str.equals("android.intent.action.BATTERY_CHANGED"))
              continue;
            int i = paramIntent.getIntExtra("status", 0);
            j = paramIntent.getIntExtra("plugged", 0);
            switch (i)
            {
            default:
              f.c.a(f.c.this, null);
              break label160;
              f.c.a(f.c.this, "6");
              return;
            case 2:
            case 3:
            case 4:
            }
          }
          catch (Exception localException)
          {
            f.c.a(f.c.this, null);
            return;
          }
        }
        f.c.a(f.c.this, "4");
        break label160;
        f.c.a(f.c.this, "3");
        break label160;
        f.c.a(f.c.this, "5");
        return;
        label160: switch (j)
        {
        case 1:
        case 2:
        }
      }
    }
  }

  public class d extends Handler
  {
    public d()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      if (f.jdMethod_if(f.this) == true)
        switch (paramMessage.what)
        {
        default:
        case 11:
        case 12:
        case 15:
        case 21:
        case 26:
        case 31:
        case 51:
        case 57:
        case 52:
        case 22:
        case 28:
        case 25:
        case 41:
        case 81:
        case 91:
        case 53:
        case 62:
        case 63:
        case 64:
        case 65:
        case 101:
        case 92:
        }
      while (true)
      {
        super.handleMessage(paramMessage);
        return;
        f.jdMethod_do(f.this, paramMessage);
        continue;
        f.a(f.this, paramMessage);
        continue;
        f.jdMethod_new(f.this, paramMessage);
        continue;
        f.a(f.this, paramMessage, 21);
        continue;
        f.a(f.this, paramMessage, 26);
        continue;
        f.jdMethod_for(f.this);
        continue;
        f.jdMethod_char(f.this);
        continue;
        f.jdMethod_int(f.this, paramMessage);
        continue;
        f.c(f.this);
        continue;
        f.jdMethod_for(f.this, paramMessage);
        continue;
        f.jdMethod_if(f.this, paramMessage);
        continue;
        f.jdMethod_try(f.this, paramMessage);
        continue;
        f.jdMethod_case(f.this);
        continue;
        f.a(f.this);
        continue;
        f.jdMethod_try(f.this);
        continue;
        f.b(f.this);
        continue;
        f.a(f.this, 21);
        continue;
        f.a(f.this, 26);
        continue;
        if (f.jdMethod_do(f.this) == null)
          continue;
        f.jdMethod_do(f.this).jdMethod_do();
        continue;
        f.jdMethod_void(f.this);
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.f
 * JD-Core Version:    0.6.0
 */