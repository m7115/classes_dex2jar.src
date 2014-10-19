package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.FloatMath;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comjni.tools.JNITools;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class o
  implements View.OnKeyListener
{
  private static int A;
  private static int B;
  private static boolean C;
  private static boolean D;
  private static a E;
  private static int F;
  private static int G;
  private static boolean H;
  private static boolean I;
  private static boolean J;
  private static VelocityTracker K;
  private static long L;
  private static long M;
  private static long N;
  private static long O;
  private static int P;
  private static float Q;
  private static float R;
  private static boolean S;
  private static long T;
  private static long U;
  private static long W;
  private static long X;
  private static long Y;
  public static boolean i;
  private static final int p = 3 * ViewConfiguration.getMinimumFlingVelocity();
  private static int y;
  private static long z;
  private boolean V = false;
  private boolean Z = true;
  public int a = 0;
  private boolean aa = true;
  private GeoPoint ab;
  private boolean ac;
  private int ad;
  private int ae;
  private boolean af = false;
  private boolean ag = false;
  public int b = 0;
  public int c = 0;
  public int d = 0;
  public Map<String, Integer> e = new HashMap();
  public Map<String, Integer> f = new HashMap();
  public Map<String, Integer> g = new HashMap();
  public Map<String, Integer> h = new HashMap();
  private com.baidu.platform.comjni.map.basemap.a j = null;
  private Context k = null;
  private q l = null;
  private int m = 0;
  private Bundle n = new Bundle();
  private Handler o = null;
  private boolean q = true;
  private boolean r = false;
  private boolean s = true;
  private boolean t = true;
  private boolean u = true;
  private int v;
  private int w;
  private int x = 20;

  static
  {
    E = new a();
    P = 0;
    W = 400L;
    X = 500L;
    Y = 120L;
    i = true;
  }

  @SuppressLint({"HandlerLeak"})
  public o(Context paramContext, q paramq)
  {
    this.l = paramq;
    this.l.setOnKeyListener(this);
    Display localDisplay = ((Activity)paramContext).getWindowManager().getDefaultDisplay();
    this.v = localDisplay.getWidth();
    this.w = localDisplay.getHeight();
    this.o = new p(this);
    p();
  }

  public static int a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return com.baidu.platform.comjni.map.basemap.a.b(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public static void f()
  {
    y = 0;
    z = 0L;
    A = 0;
    B = 0;
    C = false;
    D = false;
    E.j = false;
    E.m = 0.0D;
    G = 0;
    F = 0;
    H = false;
    I = false;
    J = false;
  }

  private void f(MotionEvent paramMotionEvent)
  {
    if (E.j)
      return;
    U = paramMotionEvent.getDownTime();
    if (U - T < W)
      if ((Math.abs(paramMotionEvent.getX() - Q) < (float)Y) && (Math.abs(paramMotionEvent.getY() - R) < (float)Y))
      {
        e(paramMotionEvent);
        T = 0L;
      }
    while (true)
    {
      Q = paramMotionEvent.getX();
      R = paramMotionEvent.getY();
      int i1 = (int)paramMotionEvent.getX();
      int i2 = (int)paramMotionEvent.getY();
      a(4, 0, i1 | i2 << 16);
      g(i1, i2);
      S = true;
      return;
      T = U;
      continue;
      T = U;
    }
  }

  private void n()
  {
    if (this.ac)
    {
      Point localPoint = new Point();
      this.l.d().toPixels(this.ab, localPoint);
      f(localPoint.x - this.ad, localPoint.y - this.ae);
      this.ac = false;
    }
    this.af = false;
    this.ad = 0;
    this.ae = 0;
    this.ab = null;
  }

  private void o()
  {
    if (this.ac)
    {
      Point localPoint = new Point();
      this.l.d().toPixels(this.ab, localPoint);
      f(localPoint.x - this.ad, localPoint.y - this.ae);
      this.ac = false;
    }
    this.ag = false;
    this.ad = 0;
    this.ae = 0;
    this.ab = null;
  }

  private void p()
  {
    com.baidu.platform.comjni.engine.a.a(4000, this.o);
    com.baidu.platform.comjni.engine.a.a(39, this.o);
    com.baidu.platform.comjni.engine.a.a(512, this.o);
  }

  private void q()
  {
    com.baidu.platform.comjni.engine.a.b(4000, this.o);
    com.baidu.platform.comjni.engine.a.b(39, this.o);
    com.baidu.platform.comjni.engine.a.b(512, this.o);
  }

  int a()
  {
    return this.m;
  }

  public int a(int paramInt1, int paramInt2, int paramInt3)
  {
    return a(this.m, paramInt1, paramInt2, paramInt3);
  }

  void a(int paramInt1, int paramInt2)
  {
    this.j.c(paramInt1, paramInt2);
  }

  public void a(Bundle paramBundle, w paramw)
  {
    if (paramBundle == null)
      throw new IllegalArgumentException("IllegalArgument");
    if (this.j == null)
    {
      this.j = new com.baidu.platform.comjni.map.basemap.a();
      this.j.a();
      this.m = this.j.c();
    }
    if (paramw != null)
      this.j.a(paramw);
    int i1 = com.baidu.platform.comapi.d.c.n();
    int i2 = 0;
    if (i1 >= 180)
      i2 = 1;
    if (com.baidu.platform.comapi.d.c.n() < 160)
      this.x = 18;
    while (true)
    {
      String str1 = paramBundle.getString("modulePath");
      String str2 = paramBundle.getString("appSdcardPath");
      String str3 = paramBundle.getString("appCachePath");
      String str4 = paramBundle.getString("appSecondCachePath");
      int i3 = paramBundle.getInt("mapTmpMax");
      int i4 = paramBundle.getInt("domTmpMax");
      int i5 = paramBundle.getInt("itsTmpMax");
      String str5 = str1 + "/cfg/h/";
      String str6 = str1 + "/cfg/h/";
      String str7 = str2 + "/vmp/h/";
      String str8 = str2 + "/vmp/h/";
      String str9 = str3 + "/tmp/";
      String str10 = str4 + "/tmp/";
      if (i2 == 0)
      {
        str5 = str1 + "/cfg/l/";
        str6 = str1 + "/cfg/l/";
        str7 = str2 + "/vmp/l/";
        str8 = str2 + "/vmp/l/";
      }
      this.j.a(str5, str7, str9, str10, str8, str6, this.v, this.w, com.baidu.platform.comapi.d.c.n(), i3, i4, i5);
      this.j.e();
      return;
      if (com.baidu.platform.comapi.d.c.n() < 240)
      {
        this.x = 25;
        continue;
      }
      if (com.baidu.platform.comapi.d.c.n() < 320)
      {
        this.x = 37;
        continue;
      }
      this.x = 50;
    }
  }

  public void a(GeoPoint paramGeoPoint)
  {
    this.l.a(paramGeoPoint, null, null);
  }

  public void a(GeoPoint paramGeoPoint, Message paramMessage)
  {
    this.l.a(paramGeoPoint, paramMessage, null);
  }

  public void a(q paramq)
  {
    this.l = paramq;
    this.l.setOnKeyListener(this);
  }

  public void a(t paramt)
  {
    if (this.j == null)
      return;
    this.n.clear();
    this.n.putDouble("level", paramt.a);
    this.n.putDouble("rotation", paramt.b);
    this.n.putDouble("overlooking", paramt.c);
    this.n.putDouble("centerptx", paramt.d);
    this.n.putDouble("centerpty", paramt.e);
    this.n.putInt("left", paramt.f.a);
    this.n.putInt("right", paramt.f.b);
    this.n.putInt("top", paramt.f.c);
    this.n.putInt("bottom", paramt.f.d);
    this.n.putInt("lbx", paramt.g.e.a);
    this.n.putInt("lby", paramt.g.e.b);
    this.n.putInt("ltx", paramt.g.f.a);
    this.n.putInt("lty", paramt.g.f.b);
    this.n.putInt("rtx", paramt.g.g.a);
    this.n.putInt("rty", paramt.g.g.b);
    this.n.putInt("rbx", paramt.g.h.a);
    this.n.putInt("rby", paramt.g.h.b);
    this.n.putLong("yoffset", paramt.i);
    this.n.putLong("xoffset", paramt.h);
    this.n.putInt("animation", 0);
    this.n.putInt("animatime", 0);
    Bundle localBundle = this.n;
    boolean bool = paramt.j;
    int i1 = 0;
    if (bool)
      i1 = 1;
    localBundle.putInt("bfpp", i1);
    this.j.a(this.n);
  }

  public void a(t paramt, int paramInt)
  {
    int i1 = 1;
    if (this.j == null)
      return;
    this.n.clear();
    this.n.putDouble("level", paramt.a);
    this.n.putDouble("rotation", paramt.b);
    this.n.putDouble("overlooking", paramt.c);
    this.n.putDouble("centerptx", paramt.d);
    this.n.putDouble("centerpty", paramt.e);
    this.n.putInt("left", paramt.f.a);
    this.n.putInt("right", paramt.f.b);
    this.n.putInt("top", paramt.f.c);
    this.n.putInt("bottom", paramt.f.d);
    this.n.putInt("lbx", paramt.g.e.a);
    this.n.putInt("lby", paramt.g.e.b);
    this.n.putInt("ltx", paramt.g.f.a);
    this.n.putInt("lty", paramt.g.f.b);
    this.n.putInt("rtx", paramt.g.g.a);
    this.n.putInt("rty", paramt.g.g.b);
    this.n.putInt("rbx", paramt.g.h.a);
    this.n.putInt("rby", paramt.g.h.b);
    this.n.putLong("xoffset", paramt.h);
    this.n.putLong("yoffset", paramt.i);
    this.n.putInt("animation", i1);
    this.n.putInt("animatime", paramInt);
    Bundle localBundle = this.n;
    if (paramt.j);
    while (true)
    {
      localBundle.putInt("bfpp", i1);
      this.j.a(this.n);
      return;
      i1 = 0;
    }
  }

  public void a(String paramString)
  {
    this.l.requestRender();
    if ((paramString == null) || (paramString.equals("")))
      throw new IllegalArgumentException("the path is invalid!");
    this.j.a(paramString);
  }

  public void a(boolean paramBoolean)
  {
    this.s = paramBoolean;
  }

  @SuppressLint({"NewApi", "NewApi", "NewApi", "NewApi"})
  public boolean a(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getPointerCount();
    E.getClass();
    if (i1 == 2)
    {
      float f1 = j() - paramMotionEvent.getY(0);
      float f2 = j() - paramMotionEvent.getY(1);
      float f3 = paramMotionEvent.getX(0);
      float f4 = paramMotionEvent.getX(1);
      float f5;
      float f7;
      int i7;
      switch (paramMotionEvent.getAction())
      {
      default:
        if (K == null)
          K = VelocityTracker.obtain();
        K.addMovement(paramMotionEvent);
        int i2 = ViewConfiguration.getMinimumFlingVelocity();
        int i3 = ViewConfiguration.getMaximumFlingVelocity();
        K.computeCurrentVelocity(1000, i3);
        f5 = K.getXVelocity(1);
        float f6 = K.getYVelocity(1);
        f7 = K.getXVelocity(2);
        float f8 = K.getYVelocity(2);
        if ((Math.abs(f5) <= i2) && (Math.abs(f6) <= i2) && (Math.abs(f7) <= i2) && (Math.abs(f8) <= i2))
          break label1483;
        if (E.j != true)
          break label637;
        if (y != 0)
          break label566;
        if (((E.h - f1 > 0.0F) && (E.i - f2 > 0.0F)) || ((E.h - f1 < 0.0F) && (E.i - f2 < 0.0F) && (this.aa == true)))
        {
          double d6 = Math.atan2(f2 - f1, f4 - f3) - Math.atan2(E.i - E.h, E.g - E.f);
          double d7 = FloatMath.sqrt((f4 - f3) * (f4 - f3) + (f2 - f1) * (f2 - f1)) / E.m;
          int i6 = (int)(10000.0D * (Math.log(d7) / Math.log(2.0D)));
          i7 = (int)(d6 * 180.0D / 3.1416D);
          if (((d7 <= 0.0D) || ((i6 <= 3000) && (i6 >= -3000))) && (Math.abs(i7) < 10))
            break;
          y = 2;
        }
      case 5:
      case 261:
      case 6:
      case 262:
      }
      while (true)
      {
        if (y != 0)
          break label566;
        return true;
        M = paramMotionEvent.getEventTime();
        P = -1 + P;
        break;
        L = paramMotionEvent.getEventTime();
        P = -1 + P;
        break;
        O = paramMotionEvent.getEventTime();
        P = 1 + P;
        break;
        N = paramMotionEvent.getEventTime();
        P = 1 + P;
        break;
        if (Math.abs(i7) >= 1)
          continue;
        y = 1;
        continue;
        y = 2;
      }
      label566: if ((y == 1) && (this.q))
      {
        if (!C)
          C = true;
        if (!H)
          H = true;
        if ((E.h - f1 > 0.0F) && (E.i - f2 > 0.0F))
        {
          a(1, 83, 0);
          label637: if ((y != 1) || (P != 0))
            break label1439;
          if (this.r)
            break label1417;
          com.baidu.platform.comapi.c.a.a().a("mapview_gesture_3d_enter");
        }
      }
      label1417: label1439: label1483: 
      do
        while (true)
        {
          if (2 != y)
          {
            E.h = f1;
            E.i = f2;
            E.f = f3;
            E.g = f4;
          }
          if (!E.j)
          {
            E.k = (i() / 2);
            E.l = (j() / 2);
            E.b = f3;
            E.c = f1;
            E.d = f4;
            E.e = f2;
            E.j = true;
            if (0.0D == E.m)
            {
              double d1 = FloatMath.sqrt((E.g - E.f) * (E.g - E.f) + (E.i - E.h) * (E.i - E.h));
              E.m = d1;
            }
          }
          return true;
          if ((E.h - f1 >= 0.0F) || (E.i - f2 >= 0.0F))
            break;
          a(1, 87, 0);
          break;
          if ((y != 2) && (y != 4) && (y != 3))
            break;
          if (!D)
            D = true;
          double d2 = Math.atan2(f2 - f1, f4 - f3) - Math.atan2(E.i - E.h, E.g - E.f);
          double d3 = FloatMath.sqrt((f4 - f3) * (f4 - f3) + (f2 - f1) * (f2 - f1)) / E.m;
          int i4 = (int)(10000.0D * (Math.log(d3) / Math.log(2.0D)));
          double d4 = Math.atan2(E.l - E.h, E.k - E.f);
          double d5 = FloatMath.sqrt((E.k - E.f) * (E.k - E.f) + (E.l - E.h) * (E.l - E.h));
          float f9 = (float)(d3 * (d5 * Math.cos(d4 + d2)) + f3);
          float f10 = (float)(d3 * (d5 * Math.sin(d4 + d2)) + f1);
          int i5 = (int)(d2 * 180.0D / 3.1416D);
          if (this.aa == true)
            if ((d3 > 0.0D) && ((3 == y) || ((Math.abs(i4) > 2000) && (2 == y))))
            {
              y = 3;
              if (!J)
                J = true;
              if (this.t)
                a(8193, 3, i4);
            }
          while (true)
          {
            this.l.setRenderMode(1);
            E.k = f9;
            E.l = f10;
            break;
            if ((this.aa != true) || (i5 == 0) || ((4 != y) && ((Math.abs(i5) <= 10) || (2 != y))))
              continue;
            y = 4;
            if (!I)
              I = true;
            if (!this.u)
              continue;
            a(8193, 1, i5);
            continue;
            if ((Math.abs(f5) <= p) && (Math.abs(f7) <= p))
              continue;
            y = 3;
            if (!J)
              J = true;
            if (!this.t)
              continue;
            a(8193, 3, i4);
          }
          if (k().c != 0)
            continue;
          com.baidu.platform.comapi.c.a.a().a("mapview_gesture_3d_exit");
          continue;
          if ((y != 4) || (P != 0))
            continue;
          if (!this.r)
          {
            com.baidu.platform.comapi.c.a.a().a("mapview_gesture_2d_rotate");
            continue;
          }
          com.baidu.platform.comapi.c.a.a().a("mapview_gesture_3d_rotate");
        }
      while ((y != 0) || (P != 0));
      long l1;
      label1510: long l2;
      if (N > O)
      {
        l1 = N;
        N = l1;
        if (L >= M)
          break label1577;
        l2 = M;
      }
      while (true)
      {
        L = l2;
        if ((N - L >= 200L) || (!this.t))
          break;
        a(8193, 4, 0);
        break;
        l1 = O;
        break label1510;
        label1577: l2 = L;
      }
    }
    if (k().c != 0);
    for (this.r = true; ; this.r = false)
      switch (paramMotionEvent.getAction())
      {
      default:
        return false;
      case 0:
      case 1:
      case 2:
      }
    f(paramMotionEvent);
    while (true)
    {
      return true;
      return c(paramMotionEvent);
      b(paramMotionEvent);
    }
  }

  @SuppressLint({"FloatMath"})
  public boolean a(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    float f1 = (float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
    if (f1 <= 500.0F)
      return false;
    a(34, (int)(f1 * 0.6F), (int)paramMotionEvent2.getY() << 16 | (int)paramMotionEvent2.getX());
    f();
    return true;
  }

  public com.baidu.platform.comjni.map.basemap.a b()
  {
    return this.j;
  }

  public void b(boolean paramBoolean)
  {
    this.t = paramBoolean;
  }

  public boolean b(int paramInt1, int paramInt2)
  {
    int i1 = (int)(this.x * c());
    String str1 = this.j.a(-1, paramInt1, paramInt2, i1);
    if (str1 != null);
    ArrayList localArrayList1;
    JSONObject localJSONObject2;
    int i2;
    ArrayList localArrayList2;
    ArrayList localArrayList3;
    ArrayList localArrayList4;
    ArrayList localArrayList5;
    ArrayList localArrayList6;
    int i3;
    label154: label178: label202: r localr;
    while (true)
    {
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str1);
        localArrayList1 = new ArrayList();
        JSONArray localJSONArray = localJSONObject1.getJSONArray("dataset");
        localJSONObject2 = (JSONObject)localJSONArray.get(0);
        i2 = localJSONObject2.getInt("ty");
        if (i2 != 22)
          break label833;
        localArrayList2 = new ArrayList();
        localArrayList3 = null;
        localArrayList4 = null;
        localArrayList5 = null;
        localArrayList6 = null;
        break label821;
        if (i3 >= localJSONArray.length())
          break label977;
        JSONObject localJSONObject3 = (JSONObject)localJSONArray.get(i3);
        int i8 = localJSONObject3.getInt("ty");
        if (i8 != 25)
          continue;
        break label827;
        localArrayList5 = new ArrayList();
        localArrayList3 = null;
        localArrayList4 = null;
        localArrayList6 = null;
        localArrayList2 = null;
        break label821;
        localArrayList4 = new ArrayList();
        localArrayList3 = null;
        localArrayList5 = null;
        localArrayList6 = null;
        localArrayList2 = null;
        break label821;
        if (i2 != 6)
          continue;
        localArrayList3 = new ArrayList();
        localArrayList4 = null;
        localArrayList5 = null;
        localArrayList6 = null;
        localArrayList2 = null;
        break label821;
        if (i2 != 24)
          break;
        localArrayList6 = new ArrayList();
        localArrayList3 = null;
        localArrayList4 = null;
        localArrayList5 = null;
        localArrayList2 = null;
        break label821;
        localr = new r();
        if (!localJSONObject3.has("ud"))
          break label524;
        localr.a = localJSONObject3.getString("ud");
        localr.c = localJSONObject3.optString("tx");
        if (!localJSONObject3.has("in"))
          break label535;
        localr.b = localJSONObject3.getInt("in");
        if (!localJSONObject3.has("geo"))
          continue;
        String str2 = localJSONObject3.getString("geo");
        Bundle localBundle = new Bundle();
        localBundle.putString("strkey", str2);
        JNITools.TransNodeStr2Pt(localBundle);
        localr.d = new GeoPoint((int)localBundle.getDouble("pty"), (int)localBundle.getDouble("ptx"));
        localr.e = i8;
        if (!localJSONObject3.has("of"))
          continue;
        localr.f = localJSONObject3.getInt("of");
        if (i2 != 22)
          break label905;
        f localf = new f();
        localf.a = localr;
        localf.b = localJSONObject3.getLong("iest");
        localf.c = localJSONObject3.getLong("ieend");
        localf.d = localJSONObject3.getString("iedetail");
        localArrayList2.add(localf);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return false;
      label524: localr.a = "";
      continue;
      label535: localr.b = 0;
    }
    label544: localArrayList5.add(localr);
    label905: 
    while (true)
    {
      label557: localArrayList4.add(localr);
      label821: label827: label833: 
      do
      {
        if (i2 == 6)
        {
          localArrayList3.add(localr);
        }
        else if (i2 == 24)
        {
          localArrayList6.add(localr);
        }
        else
        {
          localArrayList1.add(localr);
          break label827;
          int i7 = localJSONObject2.getInt("layerid");
          this.l.e.c(localArrayList1, i7);
          this.l.e.c(localArrayList1, 0);
          break label1104;
          int i6 = localJSONObject2.getInt("layerid");
          this.l.e.b(localArrayList5, i6);
          break label1104;
          this.l.e.a(localArrayList2);
          break label1104;
          int i5 = localJSONObject2.getInt("layerid");
          this.l.e.c(localArrayList1, i5);
          break label1104;
          int i4 = localJSONObject2.getInt("layerid");
          this.l.e.a(localArrayList4, i4);
          break label1104;
          this.l.e.c(localArrayList3, 0);
          break label1104;
          this.l.e.b(localArrayList6);
          break label1104;
          localArrayList2 = null;
          localArrayList6 = null;
          localArrayList5 = null;
          localArrayList4 = null;
          localArrayList3 = null;
          i3 = 0;
          break;
        }
        i3++;
        break;
        if ((i2 == 3) || (i2 == 13) || (i2 == 14) || (i2 == 16) || (i2 == 15) || (i2 == 4) || (i2 == 28))
          break label154;
        if ((i2 == 8) || (i2 == 1))
          break label178;
        if (i2 != 2)
          break label202;
        break label178;
        if ((i2 == 3) || (i2 == 13) || (i2 == 14) || (i2 == 16) || (i2 == 15) || (i2 == 4) || (i2 == 28))
          break label544;
        if ((i2 == 8) || (i2 == 1))
          break label557;
      }
      while (i2 != 2);
    }
    label977: switch (i2)
    {
    case 18:
    case 17:
    case 19:
    case 3:
    case 4:
    case 13:
    case 14:
    case 15:
    case 16:
    case 28:
    case 22:
    case 23:
    case 1:
    case 2:
    case 8:
    case 6:
    case 24:
    case 5:
    case 7:
    case 9:
    case 10:
    case 11:
    case 12:
    case 20:
    case 21:
    case 25:
    case 26:
    case 27:
    }
    label1104: return true;
  }

  public boolean b(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      int i4 = this.l.c().size();
      i5 = i4 - 1;
      i1 = 0;
      if (i5 < 0);
    }
    catch (JSONException localJSONException1)
    {
      while (true)
      {
        int i5;
        int i6;
        try
        {
          v localv = (v)this.l.c().get(i5);
          if (localv.mType == 27)
            continue;
          i6 = i1;
          break label206;
          i1 = localv.mLayerID;
          String str = this.j.a(i1, paramInt2, paramInt3, paramInt3);
          if ((str == null) || (str.equals("")))
            continue;
          int i7 = ((JSONObject)new JSONObject(str).getJSONArray("dataset").get(0)).getInt("itemindex");
          i2 = i7;
          i3 = 1;
          if (paramInt1 != 1)
            continue;
          this.l.e.a(i2, new GeoPoint(paramInt2, paramInt3), i1);
          return i3;
          localJSONException1 = localJSONException1;
          i1 = 0;
          i2 = -1;
          i3 = 0;
          continue;
        }
        catch (JSONException localJSONException2)
        {
          continue;
          i6 = i1;
        }
        int i2 = -1;
        int i3 = 0;
        continue;
        label206: i5--;
        int i1 = i6;
      }
    }
  }

  public boolean b(MotionEvent paramMotionEvent)
  {
    int i3;
    if (E.j)
      i3 = 1;
    int i1;
    int i2;
    boolean bool;
    do
    {
      return i3;
      float f1 = Math.abs(paramMotionEvent.getX() - Q);
      float f2 = Math.abs(paramMotionEvent.getY() - R);
      double d1;
      if (com.baidu.platform.comapi.d.c.C > 1.5D)
        d1 = 1.5D * com.baidu.platform.comapi.d.c.C;
      while (true)
      {
        float f3 = (float)d1;
        if ((!S) || (f1 / f3 > 3.0F) || (f2 / f3 > 3.0F))
          break;
        return true;
        d1 = com.baidu.platform.comapi.d.c.C;
      }
      S = false;
      i1 = (int)paramMotionEvent.getX();
      i2 = (int)paramMotionEvent.getY();
      if (i1 < 0)
        i1 = 0;
      if (i2 < 0)
        i2 = 0;
      bool = this.s;
      i3 = 0;
    }
    while (!bool);
    a(3, 0, i1 | i2 << 16);
    return false;
  }

  public double c()
  {
    return this.l.g();
  }

  public void c(boolean paramBoolean)
  {
    this.u = paramBoolean;
  }

  public boolean c(int paramInt1, int paramInt2)
  {
    if ((this.af) || (this.ag))
      return false;
    this.af = true;
    this.ad = paramInt1;
    this.ae = paramInt2;
    this.ab = this.l.d().fromPixels(paramInt1, paramInt2);
    if (this.ab == null)
    {
      this.af = false;
      return false;
    }
    this.ac = g();
    if (!this.ac)
      this.af = false;
    return this.ac;
  }

  public boolean c(MotionEvent paramMotionEvent)
  {
    if ((!E.j) && (paramMotionEvent.getEventTime() - U < W) && (Math.abs(paramMotionEvent.getX() - Q) < 10.0F) && (Math.abs(paramMotionEvent.getY() - R) < 10.0F));
    for (int i1 = 1; ; i1 = 0)
    {
      f();
      int i2 = (int)paramMotionEvent.getX();
      int i3 = (int)paramMotionEvent.getY();
      if (i1 == 0)
      {
        if (i2 < 0)
          i2 = 0;
        if (i3 < 0);
        for (int i4 = 0; ; i4 = i3)
        {
          a(5, 0, i2 | i4 << 16);
          return true;
        }
      }
      return false;
    }
  }

  public void d(boolean paramBoolean)
  {
    this.q = paramBoolean;
  }

  public boolean d()
  {
    return this.s;
  }

  public boolean d(int paramInt1, int paramInt2)
  {
    if ((this.af) || (this.ag))
      return false;
    this.ag = true;
    this.ad = paramInt1;
    this.ae = paramInt2;
    this.ab = this.l.d().fromPixels(paramInt1, paramInt2);
    if (this.ab == null)
    {
      this.ag = false;
      return false;
    }
    this.ac = h();
    if (!this.ac)
      this.ag = false;
    return this.ac;
  }

  public boolean d(MotionEvent paramMotionEvent)
  {
    int i1 = (int)paramMotionEvent.getX();
    int i2 = (int)paramMotionEvent.getY();
    if (g(i1, i2));
    do
      return true;
    while ((b(1, i1, i2)) || ((this.V) && (b(i1, i2))));
    return false;
  }

  public void e(int paramInt1, int paramInt2)
  {
    this.v = paramInt1;
    this.w = paramInt2;
  }

  public void e(MotionEvent paramMotionEvent)
  {
    if (this.Z)
      a(8195, (int)paramMotionEvent.getY() << 16 | (int)paramMotionEvent.getX(), this.w / 2 << 16 | this.v / 2);
  }

  public void e(boolean paramBoolean)
  {
    this.Z = paramBoolean;
  }

  public boolean e()
  {
    return this.Z;
  }

  public void f(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == 0))
      return;
    this.l.a(paramInt1, paramInt2);
  }

  public void f(boolean paramBoolean)
  {
    this.V = paramBoolean;
  }

  public boolean g()
  {
    if (this.l.i() >= 19.0F);
    do
      return false;
    while (a(4096, 0, 0) != 1);
    return true;
  }

  public boolean g(int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.e.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int i1 = ((Integer)this.e.get(str)).intValue();
      int i2 = (int)(this.x * c());
      if (this.j.a(i1, paramInt1, paramInt2, i2) != null)
        return true;
    }
    return false;
  }

  public boolean h()
  {
    if (this.l.i() <= 3.0F);
    do
      return false;
    while (a(4097, 0, 0) != 1);
    return true;
  }

  public int i()
  {
    return this.v;
  }

  public int j()
  {
    return this.w;
  }

  public t k()
  {
    int i1 = 1;
    if (this.j == null)
      return null;
    Bundle localBundle = this.j.g();
    t localt = new t();
    localt.a = (float)localBundle.getDouble("level");
    localt.b = (int)localBundle.getDouble("rotation");
    localt.c = (int)localBundle.getDouble("overlooking");
    localt.d = (int)localBundle.getDouble("centerptx");
    localt.e = (int)localBundle.getDouble("centerpty");
    localt.f.a = localBundle.getInt("left");
    localt.f.b = localBundle.getInt("right");
    localt.f.c = localBundle.getInt("top");
    localt.f.d = localBundle.getInt("bottom");
    localt.g.a = localBundle.getLong("gleft");
    localt.g.b = localBundle.getLong("gright");
    localt.g.c = localBundle.getLong("gtop");
    localt.g.d = localBundle.getLong("gbottom");
    localt.g.e.a = localBundle.getInt("lbx");
    localt.g.e.b = localBundle.getInt("lby");
    localt.g.f.a = localBundle.getInt("ltx");
    localt.g.f.b = localBundle.getInt("lty");
    localt.g.g.a = localBundle.getInt("rtx");
    localt.g.g.b = localBundle.getInt("rty");
    localt.g.h.a = localBundle.getInt("rbx");
    localt.g.h.b = localBundle.getInt("rby");
    localt.h = localBundle.getLong("xoffset");
    localt.i = localBundle.getLong("yoffset");
    if (localBundle.getInt("bfpp") == i1);
    while (true)
    {
      localt.j = i1;
      if (localt.g.a <= -20037508L)
        localt.g.a = -20037508L;
      if (localt.g.b >= 20037508L)
        localt.g.b = 20037508L;
      if (localt.g.c >= 20037508L)
        localt.g.c = 20037508L;
      if (localt.g.d <= -20037508L)
        localt.g.d = -20037508L;
      return localt;
      i1 = 0;
    }
  }

  public float l()
  {
    if (this.j == null)
      return 3.0F;
    return (float)this.j.g().getDouble("level");
  }

  public void m()
  {
    q();
    this.o = null;
    if (this.j != null)
    {
      this.j.b();
      this.j = null;
    }
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((this.l != paramView) || (paramKeyEvent.getAction() != 0))
      return false;
    switch (paramInt)
    {
    default:
      return false;
    case 19:
      f(0, -50);
    case 21:
    case 22:
    case 20:
    }
    while (true)
    {
      return true;
      f(-50, 0);
      continue;
      f(50, 0);
      continue;
      f(0, 50);
    }
  }

  static class a
  {
    final int a = 2;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    boolean j;
    float k;
    float l;
    double m;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.o
 * JD-Core Version:    0.6.0
 */