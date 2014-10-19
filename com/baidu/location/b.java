package com.baidu.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class b
{
  private static File A;
  private static final int B = 750;
  private static final int G = 5;
  private static int I = 0;
  private static long J = 0L;
  private static long L = 0L;
  private static final int M = 5;
  private static String N;
  private static int b = 0;
  private static long jdField_byte = 0L;
  private static int c = 0;
  private static int jdField_case = 0;
  private static boolean jdField_char = false;
  private static final double jdField_else = 1.E-005D;
  private static final int jdField_goto = 3000;
  private static final int h = 1024;
  private static final int i = 1000;
  private static final String jdField_if = "baidu_location_service";
  private static int j = 0;
  private static int k = 0;
  private static final int l = 12;
  private static double m = 0.0D;
  private static double o = 0.0D;
  private static final int p = 1;
  private static String r = null;
  private static final int s = 3;
  private static final int v = 100;
  private static final int jdField_void = 3600;
  private static StringBuffer y;
  private static final int z = 5;
  private boolean C = false;
  private String D = null;
  private long E = 0L;
  private Location F;
  private Handler H = null;
  private final int K = 400;
  private GpsStatus a;
  private long d = 0L;
  private LocationManager jdField_do = null;
  private boolean e = false;
  private Context f;
  private d jdField_for = null;
  private String g = null;
  private boolean jdField_int = false;
  private long jdField_long = 0L;
  private String n = null;
  private a jdField_new = null;
  private final long q = 1000L;
  private boolean t = false;
  private String jdField_try = null;
  private List u = new ArrayList();
  private boolean w = true;
  private b x = null;

  static
  {
    N = "Temp_in.dat";
    A = new File(f.ac, N);
    y = null;
    jdField_char = true;
    jdField_case = 0;
    c = 0;
    jdField_byte = 0L;
    J = 0L;
    L = 0L;
    o = 0.0D;
    m = 0.0D;
    j = 0;
    I = 0;
    b = 0;
  }

  public b(Context paramContext, Handler paramHandler)
  {
    this.f = paramContext;
    this.H = paramHandler;
    try
    {
      if (j.jdField_do != null)
      {
        this.jdField_for = new d(j.jdField_do);
        return;
      }
      this.jdField_for = new d("NULL");
      return;
    }
    catch (Exception localException)
    {
      this.jdField_for = null;
    }
  }

  private void a(double paramDouble1, double paramDouble2, float paramFloat)
  {
    j.jdField_if("baidu_location_service", "check...gps ...");
    if (!j.z);
    while (true)
    {
      return;
      boolean bool1 = paramDouble1 < 73.146973000000003D;
      int i1 = 0;
      if (!bool1)
      {
        boolean bool2 = paramDouble1 < 135.25268600000001D;
        i1 = 0;
        if (!bool2)
        {
          boolean bool3 = paramDouble2 < 54.258806999999997D;
          i1 = 0;
          if (!bool3)
          {
            boolean bool4 = paramDouble2 < 14.604846999999999D;
            i1 = 0;
            if (!bool4)
            {
              boolean bool5 = paramFloat < 18.0F;
              i1 = 0;
              if (!bool5)
                break label141;
            }
          }
        }
      }
      while (j.I != i1)
      {
        j.I = i1;
        try
        {
          if (j.I != 3)
            break label424;
          this.jdField_do.removeUpdates(this.x);
          this.jdField_do.requestLocationUpdates("gps", 1000L, 1.0F, this.x);
          return;
        }
        catch (Exception localException)
        {
          return;
        }
        label141: j.jdField_if("baidu_location_service", "check...gps2 ...");
        double d1 = paramDouble1 - j.jdField_if;
        double d2 = j.o - paramDouble2;
        int i2 = (int)(d1 * 1000.0D);
        int i3 = (int)(d2 * 1000.0D);
        j.jdField_if("baidu_location_service", "check...gps ..." + i2 + i3);
        if ((i2 > 0) && (i2 < 50) && (i3 > 0) && (i3 < 50))
        {
          j.jdField_if("baidu_location_service", "check...gps ..." + i2 + i3);
          int i4 = i2 + i3 * 50;
          int i5 = i4 >> 2;
          int i6 = i4 & 0x3;
          boolean bool6 = j.ag;
          i1 = 0;
          if (!bool6)
            continue;
          i1 = 0x3 & j.j[i5] >> i6 * 2;
          j.jdField_if("baidu_location_service", "check gps scacity..." + i1);
          continue;
        }
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Double.valueOf(paramDouble1);
        arrayOfObject[1] = Double.valueOf(paramDouble2);
        String str1 = String.format("&ll=%.5f|%.5f", arrayOfObject);
        String str2 = str1 + "&im=" + j.jdField_do;
        j.J = paramDouble1;
        j.Z = paramDouble2;
        g.a(str2, true);
        i1 = 0;
      }
    }
    label424: this.jdField_do.removeUpdates(this.x);
    this.jdField_do.requestLocationUpdates("gps", 1000L, 5.0F, this.x);
  }

  private void a(Location paramLocation)
  {
    j.jdField_if("baidu_location_service", "set new gpsLocation ...");
    this.F = paramLocation;
    if (this.F == null)
      this.D = null;
    while (true)
    {
      if (this.jdField_for != null);
      try
      {
        d.a(this.jdField_for, this.F);
        label44: this.H.obtainMessage(51).sendToTarget();
        return;
        long l1 = System.currentTimeMillis();
        this.F.setTime(l1);
        float f1 = (float)(3.6D * this.F.getSpeed());
        Object[] arrayOfObject = new Object[6];
        arrayOfObject[0] = Double.valueOf(this.F.getLongitude());
        arrayOfObject[1] = Double.valueOf(this.F.getLatitude());
        arrayOfObject[2] = Float.valueOf(f1);
        arrayOfObject[3] = Float.valueOf(this.F.getBearing());
        arrayOfObject[4] = Integer.valueOf(k);
        arrayOfObject[5] = Long.valueOf(l1);
        this.D = String.format("&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", arrayOfObject);
        a(this.F.getLongitude(), this.F.getLatitude(), f1);
      }
      catch (Exception localException)
      {
        break label44;
      }
    }
  }

  private void a(boolean paramBoolean)
  {
    this.C = paramBoolean;
    if ((paramBoolean) && (!jdField_new()));
    do
    {
      do
        return;
      while (j.ab == paramBoolean);
      j.ab = paramBoolean;
    }
    while (!j.H);
    this.H.obtainMessage(53).sendToTarget();
  }

  public static boolean a(Location paramLocation1, Location paramLocation2, boolean paramBoolean)
  {
    int i1 = 1;
    if (paramLocation1 == paramLocation2)
      i1 = 0;
    float f2;
    do
      while (true)
      {
        return i1;
        if ((paramLocation1 == null) || (paramLocation2 == null))
          continue;
        float f1 = paramLocation2.getSpeed();
        if ((paramBoolean) && (j.I == 3) && (f1 < 5.0F))
          continue;
        f2 = paramLocation2.distanceTo(paramLocation1);
        if (f1 > j.C)
          if (f2 <= j.Q)
            return false;
        if (f1 <= j.D)
          break;
        if (f2 <= j.ai)
          return false;
      }
    while (f2 > 5.0F);
    return false;
  }

  public static String jdField_do(Location paramLocation)
  {
    String str = jdField_for(paramLocation);
    if (str != null)
      str = str + "&g_tp=0";
    return str;
  }

  private static void f()
  {
    jdField_char = true;
    y = null;
    jdField_case = 0;
    c = 0;
    jdField_byte = 0L;
    J = 0L;
    L = 0L;
    o = 0.0D;
    m = 0.0D;
    j = 0;
    I = 0;
    b = 0;
  }

  public static String jdField_for(Location paramLocation)
  {
    if (paramLocation == null)
      return null;
    float f1 = (float)(3.6D * paramLocation.getSpeed());
    float f2;
    int i1;
    double d1;
    if (paramLocation.hasAccuracy())
    {
      f2 = paramLocation.getAccuracy();
      i1 = (int)f2;
      if (!paramLocation.hasAltitude())
        break label153;
      d1 = paramLocation.getAltitude();
    }
    while (true)
    {
      Object[] arrayOfObject = new Object[8];
      arrayOfObject[0] = Double.valueOf(paramLocation.getLongitude());
      arrayOfObject[1] = Double.valueOf(paramLocation.getLatitude());
      arrayOfObject[2] = Float.valueOf(f1);
      arrayOfObject[3] = Float.valueOf(paramLocation.getBearing());
      arrayOfObject[4] = Integer.valueOf(i1);
      arrayOfObject[5] = Integer.valueOf(k);
      arrayOfObject[6] = Double.valueOf(d1);
      arrayOfObject[7] = Long.valueOf(paramLocation.getTime() / 1000L);
      return String.format("&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d", arrayOfObject);
      f2 = -1.0F;
      break;
      label153: d1 = 555.0D;
    }
  }

  private static boolean h()
  {
    if (A.exists())
      A.delete();
    if (!A.getParentFile().exists())
      A.getParentFile().mkdirs();
    try
    {
      A.createNewFile();
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(A, "rw");
      localRandomAccessFile.seek(0L);
      localRandomAccessFile.writeInt(0);
      localRandomAccessFile.writeInt(0);
      localRandomAccessFile.writeInt(1);
      localRandomAccessFile.close();
      f();
      return A.exists();
    }
    catch (IOException localIOException)
    {
    }
    return false;
  }

  public static String jdField_if(Location paramLocation)
  {
    String str = jdField_for(paramLocation);
    if (str != null)
      str = str + r;
    return str;
  }

  private static boolean jdField_if(int paramInt1, int paramInt2, int paramInt3)
  {
    int i1 = 1;
    if ((paramInt1 < 0) || (paramInt1 > j.L))
      i1 = 0;
    do
    {
      return i1;
      if ((paramInt2 < 0) || (paramInt2 > paramInt1 + 1))
        return false;
    }
    while ((paramInt3 >= i1) && (paramInt3 <= paramInt1 + 1) && (paramInt3 <= j.L));
    return false;
  }

  public static String j()
  {
    j.jdField_if("baidu_location_service", "GPS readline...");
    if (A == null)
      return null;
    if (!A.exists())
      return null;
    try
    {
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(A, "rw");
      localRandomAccessFile.seek(0L);
      int i1 = localRandomAccessFile.readInt();
      int i2 = localRandomAccessFile.readInt();
      int i3 = localRandomAccessFile.readInt();
      if (!jdField_if(i1, i2, i3))
      {
        localRandomAccessFile.close();
        h();
        return null;
      }
      j.jdField_if("baidu_location_service", "GPS readline1...");
      if ((i2 == 0) || (i2 == i3))
      {
        localRandomAccessFile.close();
        return null;
      }
      j.jdField_if("baidu_location_service", "GPS readline2...");
      long l1 = 0L + (12 + 1024 * (i2 - 1));
      localRandomAccessFile.seek(l1);
      int i4 = localRandomAccessFile.readInt();
      byte[] arrayOfByte = new byte[i4];
      localRandomAccessFile.seek(l1 + 4L);
      for (int i5 = 0; i5 < i4; i5++)
        arrayOfByte[i5] = localRandomAccessFile.readByte();
      String str = new String(arrayOfByte);
      int i6;
      if (i1 < j.L)
        i6 = i2 + 1;
      while (true)
      {
        localRandomAccessFile.seek(4L);
        localRandomAccessFile.writeInt(i6);
        localRandomAccessFile.close();
        return str;
        int i7 = j.L;
        if (i2 == i7)
        {
          i6 = 1;
          continue;
        }
        i6 = i2 + 1;
      }
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  public boolean jdField_case()
  {
    return (this.F != null) && (this.F.getLatitude() != 0.0D) && (this.F.getLongitude() != 0.0D);
  }

  public String jdField_char()
  {
    return this.D;
  }

  public void jdField_goto()
  {
  }

  public String jdField_int()
  {
    if (this.F != null)
    {
      String str1 = "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"}}";
      float f1;
      if (this.F.hasAccuracy())
        f1 = this.F.getAccuracy();
      while (true)
      {
        int i1 = (int)f1;
        float f2 = (float)(3.6D * this.F.getSpeed());
        double[] arrayOfDouble = Jni.jdField_if(this.F.getLongitude(), this.F.getLatitude(), "gps2gcj");
        if ((arrayOfDouble[0] <= 0.0D) && (arrayOfDouble[1] <= 0.0D))
        {
          arrayOfDouble[0] = this.F.getLongitude();
          arrayOfDouble[1] = this.F.getLatitude();
        }
        Object[] arrayOfObject = new Object[6];
        arrayOfObject[0] = Double.valueOf(arrayOfDouble[0]);
        arrayOfObject[1] = Double.valueOf(arrayOfDouble[1]);
        arrayOfObject[2] = Integer.valueOf(i1);
        arrayOfObject[3] = Float.valueOf(this.F.getBearing());
        arrayOfObject[4] = Float.valueOf(f2);
        arrayOfObject[5] = Integer.valueOf(k);
        String str2 = String.format(str1, arrayOfObject);
        j.jdField_if("baidu_location_service", "wgs84: " + this.F.getLongitude() + " " + this.F.getLatitude() + " gcj02: " + arrayOfDouble[0] + " " + arrayOfDouble[1]);
        return str2;
        f1 = 10.0F;
      }
    }
    j.jdField_if("baidu_location_service", "gps man getGpsJson but gpslocation is null");
    return null;
  }

  public void k()
  {
    if (this.t == true)
      return;
    try
    {
      this.jdField_do = ((LocationManager)this.f.getSystemService("location"));
      this.x = new b(null);
      this.jdField_new = new a(null);
      this.jdField_do.requestLocationUpdates("gps", 1000L, 5.0F, this.x);
      this.jdField_do.addGpsStatusListener(this.jdField_new);
      this.jdField_do.addNmeaListener(this.jdField_new);
      this.t = true;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void l()
  {
    if (!this.t)
      return;
    if (this.jdField_do != null);
    try
    {
      if (this.x != null)
        this.jdField_do.removeUpdates(this.x);
      if (this.jdField_new != null)
      {
        this.jdField_do.removeGpsStatusListener(this.jdField_new);
        this.jdField_do.removeNmeaListener(this.jdField_new);
      }
      if (this.jdField_for != null)
        d.a(this.jdField_for);
      label76: this.x = null;
      this.jdField_new = null;
      this.jdField_do = null;
      this.t = false;
      a(false);
      return;
    }
    catch (Exception localException)
    {
      break label76;
    }
  }

  public boolean jdField_new()
  {
    if (!jdField_case());
    long l1;
    do
    {
      do
      {
        return false;
        l1 = System.currentTimeMillis();
      }
      while ((this.jdField_int) && (l1 - this.jdField_long > 3000L));
      if (k >= 3)
        return true;
    }
    while (l1 - this.d >= 3000L);
    return true;
  }

  public Location jdField_try()
  {
    return this.F;
  }

  private class a
    implements GpsStatus.Listener, GpsStatus.NmeaListener
  {
    private a()
    {
    }

    public void onGpsStatusChanged(int paramInt)
    {
      if (b.jdMethod_new(b.this) == null)
        return;
      switch (paramInt)
      {
      case 3:
      default:
        return;
      case 2:
        b.a(b.this, null);
        b.jdMethod_if(b.this, false);
        b.a(0);
        return;
      case 4:
      }
      j.jdMethod_if("baidu_location_service", "gps status change");
      int i;
      if (b.jdMethod_do(b.this) == null)
      {
        b.a(b.this, b.jdMethod_new(b.this).getGpsStatus(null));
        Iterator localIterator = b.jdMethod_do(b.this).getSatellites().iterator();
        i = 0;
        label118: if (!localIterator.hasNext())
          break label176;
        if (!((GpsSatellite)localIterator.next()).usedInFix())
          break label267;
      }
      label267: for (int j = i + 1; ; j = i)
      {
        i = j;
        break label118;
        b.jdMethod_new(b.this).getGpsStatus(b.jdMethod_do(b.this));
        break;
        label176: j.jdMethod_if("baidu_location_service", "gps nunmber in count:" + i);
        if ((b.jdMethod_void() >= 3) && (i < 3))
          b.jdMethod_do(b.this, System.currentTimeMillis());
        if (i < 3)
          b.jdMethod_if(b.this, false);
        if ((b.jdMethod_void() <= 3) && (i > 3))
          b.jdMethod_if(b.this, true);
        b.a(i);
        return;
      }
    }

    public void onNmeaReceived(long paramLong, String paramString)
    {
      if (!j.m)
        j.h = 0;
      do
        return;
      while ((paramString == null) || (paramString.equals("")) || (paramString.length() < 9) || (paramString.length() > 150) || (!b.this.jdMethod_new()));
      long l = System.currentTimeMillis();
      j.jdMethod_if("baidu_location_service", "gps manager onNmeaReceived : " + l + " " + paramString);
      if ((l - b.a(b.this) > 400L) && (b.jdMethod_int(b.this)) && (b.jdMethod_case(b.this).size() > 0));
      try
      {
        b.c localc = new b.c(b.this, b.jdMethod_case(b.this), b.jdMethod_if(b.this), b.jdMethod_for(b.this), b.jdMethod_byte(b.this), null);
        if (b.c.jdMethod_if(localc))
        {
          j.h = b.c.jdMethod_do(localc);
          if (j.h > 0)
          {
            Object[] arrayOfObject = new Object[3];
            arrayOfObject[0] = Double.valueOf(b.c.jdMethod_for(localc));
            arrayOfObject[1] = Double.valueOf(b.c.a(localc));
            arrayOfObject[2] = Integer.valueOf(j.h);
            b.a(String.format("&nmea=%.1f|%.1f&g_tp=%d", arrayOfObject));
          }
        }
        while (true)
        {
          b.jdMethod_case(b.this).clear();
          b.jdMethod_if(b.this, b.jdMethod_do(b.this, b.a(b.this, null)));
          b.jdMethod_do(b.this, false);
          if (!paramString.startsWith("$GPGGA"))
            break;
          b.jdMethod_do(b.this, true);
          b.jdMethod_if(b.this, paramString.trim());
          b.a(b.this, System.currentTimeMillis());
          return;
          j.h = 0;
          j.jdMethod_if("baidu_location_service", "nmea invalid");
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          j.h = 0;
          continue;
          if (paramString.startsWith("$GPGSV"))
          {
            b.jdMethod_case(b.this).add(paramString.trim());
            continue;
          }
          if (!paramString.startsWith("$GPGSA"))
            continue;
          b.a(b.this, paramString.trim());
        }
      }
    }
  }

  private class b
    implements LocationListener
  {
    private b()
    {
    }

    public void onLocationChanged(Location paramLocation)
    {
      b.a(b.this, paramLocation);
      b.a(b.this, false);
      if (b.jdMethod_try(b.this))
        b.jdMethod_if(b.this, true);
    }

    public void onProviderDisabled(String paramString)
    {
      b.a(b.this, null);
      b.jdMethod_if(b.this, false);
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
      switch (paramInt)
      {
      default:
        return;
      case 0:
        b.a(b.this, null);
        b.jdMethod_if(b.this, false);
        return;
      case 1:
        b.jdMethod_if(b.this, System.currentTimeMillis());
        b.a(b.this, true);
        b.jdMethod_if(b.this, false);
        return;
      case 2:
      }
      b.a(b.this, false);
    }
  }

  private class c
  {
    public int a = 0;
    private String b = "";
    private double jdField_byte = 0.0D;
    private char jdField_case = 'N';
    private String jdField_char = "";
    private int d = 0;
    private List jdField_do = null;
    private int e = 0;
    private List jdField_else = null;
    private String f = "";
    private double jdField_for = 0.0D;
    private String g = "";
    private boolean jdField_goto = false;
    private boolean h = false;
    private int jdField_if = 1;
    private String jdField_int = "";
    private int jdField_long = 0;
    private boolean jdField_new = false;
    private boolean jdField_try = false;
    private boolean jdField_void = false;

    private c(List paramString1, String paramString2, String paramString3, String arg5)
    {
      this.jdField_do = paramString1;
      this.jdField_char = paramString2;
      this.f = paramString3;
      Object localObject;
      this.g = localObject;
      this.jdField_else = new ArrayList();
      jdField_try();
    }

    private double a()
    {
      return this.jdField_for;
    }

    private int a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
    {
      if (!this.jdField_goto)
        return 0;
      if ((paramBoolean1) && (this.h))
      {
        this.a = 1;
        if (this.d >= j.K)
          return 1;
        if (this.d <= j.p)
          return 4;
      }
      if ((paramBoolean2) && (this.jdField_void))
      {
        this.a = 2;
        if (this.jdField_for <= j.am)
          return 1;
        if (this.jdField_for >= j.c)
          return 4;
      }
      if ((paramBoolean3) && (this.jdField_void))
      {
        this.a = 3;
        if (this.jdField_byte <= j.F)
          return 1;
        if (this.jdField_byte >= j.U)
          return 4;
      }
      if (!this.jdField_try)
        return 0;
      int i1;
      if (paramBoolean4)
      {
        this.a = 4;
        Iterator localIterator2 = this.jdField_else.iterator();
        i1 = 0;
        if (localIterator2.hasNext())
          if (((a)localIterator2.next()).jdField_do() < j.jdField_for)
            break label781;
      }
      label774: label781: for (int i2 = i1 + 1; ; i2 = i1)
      {
        i1 = i2;
        break;
        if (i1 >= j.jdField_int)
          return 1;
        if (i1 <= j.X)
          return 4;
        ArrayList localArrayList1;
        ArrayList localArrayList2;
        ArrayList localArrayList3;
        int j;
        if (paramBoolean5)
        {
          this.a = 5;
          localArrayList1 = new ArrayList();
          localArrayList2 = new ArrayList();
          localArrayList3 = new ArrayList();
          for (int i = 0; i < 10; i++)
            localArrayList1.add(new ArrayList());
          Iterator localIterator1 = this.jdField_else.iterator();
          j = 0;
          if (localIterator1.hasNext())
          {
            a locala = (a)localIterator1.next();
            if ((locala.jdField_do() < 10) || (locala.jdField_if() < 1))
              break label774;
            ((List)localArrayList1.get((locala.jdField_do() - 10) / 5)).add(locala);
          }
        }
        for (int n = j + 1; ; n = j)
        {
          j = n;
          break;
          if (j >= 4)
          {
            for (int k = 0; k < localArrayList1.size(); k++)
            {
              if (((List)localArrayList1.get(k)).size() == 0)
                continue;
              double[] arrayOfDouble4 = a((List)localArrayList1.get(k));
              if (arrayOfDouble4 == null)
                continue;
              localArrayList2.add(arrayOfDouble4);
              localArrayList3.add(Integer.valueOf(k));
            }
            if ((localArrayList2 != null) && (localArrayList2.size() > 0))
            {
              double[] arrayOfDouble1 = (double[])localArrayList2.get(0);
              arrayOfDouble1[0] *= ((Integer)localArrayList3.get(0)).intValue();
              arrayOfDouble1[1] *= ((Integer)localArrayList3.get(0)).intValue();
              if (localArrayList2.size() > 1)
              {
                for (int m = 1; m < localArrayList2.size(); m++)
                {
                  double[] arrayOfDouble3 = (double[])localArrayList2.get(m);
                  arrayOfDouble3[0] *= ((Integer)localArrayList3.get(m)).intValue();
                  arrayOfDouble3[1] *= ((Integer)localArrayList3.get(m)).intValue();
                  arrayOfDouble1[0] += arrayOfDouble3[0];
                  arrayOfDouble1[1] += arrayOfDouble3[1];
                }
                arrayOfDouble1[0] /= localArrayList2.size();
                arrayOfDouble1[1] /= localArrayList2.size();
              }
              double[] arrayOfDouble2 = a(arrayOfDouble1[0], arrayOfDouble1[1]);
              if (arrayOfDouble2[0] <= j.ad)
                return 1;
              if (arrayOfDouble2[0] >= j.jdField_long)
                return 4;
            }
            else
            {
              return 4;
            }
          }
          else
          {
            return 4;
          }
          this.a = 0;
          return 3;
        }
      }
    }

    private boolean a(String paramString)
    {
      int i = 0;
      if (paramString != null)
      {
        int j = paramString.length();
        i = 0;
        if (j > 8)
        {
          int k = 1;
          int m = 0;
          while (k < -3 + paramString.length())
          {
            m ^= paramString.charAt(k);
            k++;
          }
          boolean bool = Integer.toHexString(m).equalsIgnoreCase(paramString.substring(-2 + paramString.length(), paramString.length()));
          i = 0;
          if (bool)
            i = 1;
        }
      }
      return i;
    }

    private double[] a(double paramDouble1, double paramDouble2)
    {
      double d1 = 0.0D;
      if (paramDouble2 == d1)
        if (paramDouble1 > d1)
          d1 = 90.0D;
      while (true)
      {
        double[] arrayOfDouble = new double[2];
        arrayOfDouble[0] = Math.sqrt(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
        arrayOfDouble[1] = d1;
        return arrayOfDouble;
        if (paramDouble1 >= d1)
          continue;
        d1 = 270.0D;
        continue;
        d1 = Math.toDegrees(Math.atan(paramDouble1 / paramDouble2));
      }
    }

    private double[] a(List paramList)
    {
      if ((paramList != null) && (paramList.size() > 0))
      {
        double[] arrayOfDouble1 = jdField_if(90 - ((a)paramList.get(0)).jdField_if(), ((a)paramList.get(0)).a());
        if (paramList.size() > 1)
        {
          for (int i = 1; i < paramList.size(); i++)
          {
            double[] arrayOfDouble2 = jdField_if(90 - ((a)paramList.get(i)).jdField_if(), ((a)paramList.get(i)).a());
            arrayOfDouble1[0] += arrayOfDouble2[0];
            arrayOfDouble1[1] += arrayOfDouble2[1];
          }
          arrayOfDouble1[0] /= paramList.size();
          arrayOfDouble1[1] /= paramList.size();
        }
        return arrayOfDouble1;
      }
      return null;
    }

    private double jdField_byte()
    {
      return this.jdField_byte;
    }

    private int jdField_case()
    {
      return a(true, true, true, true, true);
    }

    private String jdField_char()
    {
      return this.g;
    }

    private boolean jdField_for()
    {
      return this.h;
    }

    private boolean jdField_goto()
    {
      return this.jdField_void;
    }

    private boolean jdField_if()
    {
      return this.jdField_goto;
    }

    private double[] jdField_if(double paramDouble1, double paramDouble2)
    {
      double[] arrayOfDouble = new double[2];
      arrayOfDouble[0] = (paramDouble1 * Math.sin(Math.toRadians(paramDouble2)));
      arrayOfDouble[1] = (paramDouble1 * Math.cos(Math.toRadians(paramDouble2)));
      return arrayOfDouble;
    }

    private List jdField_int()
    {
      return this.jdField_do;
    }

    private boolean jdField_long()
    {
      return this.jdField_new;
    }

    private boolean jdField_new()
    {
      return this.jdField_try;
    }

    private void jdField_try()
    {
      boolean bool1 = true;
      String[] arrayOfString3;
      if (a(this.jdField_char))
      {
        arrayOfString3 = this.jdField_char.split(",");
        if (arrayOfString3.length == 15);
      }
      while (true)
      {
        return;
        if ((!arrayOfString3[6].equals("")) && (!arrayOfString3[7].equals("")))
        {
          this.jdField_long = Integer.valueOf(arrayOfString3[6]).intValue();
          this.d = Integer.valueOf(arrayOfString3[7]).intValue();
          this.h = bool1;
        }
        if (!a(this.g))
          break;
        String str3 = this.g.substring(0, -3 + this.g.length());
        int i3 = 0;
        int i4 = 0;
        while (i3 < str3.length())
        {
          if (str3.charAt(i3) == ',')
            i4++;
          i3++;
        }
        String[] arrayOfString2 = str3.split(",", i4 + 1);
        if (arrayOfString2.length < 6)
          continue;
        if ((arrayOfString2[2].equals("")) || (arrayOfString2[(-3 + arrayOfString2.length)].equals("")) || (arrayOfString2[(-2 + arrayOfString2.length)].equals("")) || (arrayOfString2[(-1 + arrayOfString2.length)].equals("")))
          break;
        this.jdField_if = Integer.valueOf(arrayOfString2[2]).intValue();
        this.jdField_byte = Double.valueOf(arrayOfString2[(-3 + arrayOfString2.length)]).doubleValue();
        this.jdField_for = Double.valueOf(arrayOfString2[(-2 + arrayOfString2.length)]).doubleValue();
        this.jdField_void = bool1;
      }
      String[] arrayOfString1;
      boolean bool2;
      if ((this.jdField_do != null) && (this.jdField_do.size() > 0))
      {
        Iterator localIterator = this.jdField_do.iterator();
        if (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if (!a(str1))
            break label669;
          String str2 = str1.substring(0, -3 + str1.length());
          int i = 0;
          int j = 0;
          while (i < str2.length())
          {
            if (str2.charAt(i) == ',')
              j++;
            i++;
          }
          arrayOfString1 = str2.split(",", j + 1);
          if ((Integer.valueOf(arrayOfString1[bool1]).intValue() == this.jdField_do.size()) && (arrayOfString1.length > 8))
          {
            bool2 = bool1;
            label455: this.jdField_try = bool2;
            if (this.jdField_try)
              break label494;
          }
        }
        else
        {
          label468: if ((!this.h) || (!this.jdField_void))
            break label685;
        }
      }
      while (true)
      {
        this.jdField_goto = bool1;
        return;
        bool2 = false;
        break label455;
        label494: int k = 4;
        label497: int m;
        int n;
        int i1;
        if (k < arrayOfString1.length)
          if ((!arrayOfString1[k].equals("")) && (!arrayOfString1[(k + 1)].equals("")) && (!arrayOfString1[(k + 2)].equals("")))
          {
            this.e = (1 + this.e);
            m = Integer.valueOf(arrayOfString1[k]).intValue();
            n = Integer.valueOf(arrayOfString1[(k + 1)]).intValue();
            i1 = Integer.valueOf(arrayOfString1[(k + 2)]).intValue();
            if (!arrayOfString1[(k + 3)].equals(""))
              break label651;
          }
        label651: for (int i2 = 0; ; i2 = Integer.valueOf(arrayOfString1[(k + 3)]).intValue())
        {
          this.jdField_else.add(new a(m, i1, n, i2));
          k += 4;
          break label497;
          break;
        }
        label669: this.jdField_try = false;
        break label468;
        this.jdField_try = false;
        break label468;
        label685: bool1 = false;
      }
    }

    private String jdField_void()
    {
      return this.f;
    }

    public String jdField_do()
    {
      return this.jdField_int;
    }

    public int jdField_else()
    {
      return this.jdField_long;
    }

    private class a
    {
      private int a = 0;
      private int jdField_do = 0;
      private int jdField_if = 0;
      private int jdField_int = 0;

      public a(int paramInt1, int paramInt2, int paramInt3, int arg5)
      {
        this.jdField_int = paramInt1;
        this.a = paramInt2;
        this.jdField_if = paramInt3;
        int i;
        this.jdField_do = i;
      }

      public int a()
      {
        return this.a;
      }

      public int jdField_do()
      {
        return this.jdField_do;
      }

      public int jdField_if()
      {
        return this.jdField_if;
      }
    }
  }

  public static class d
  {
    private boolean a = true;
    private String jdField_if = null;

    public d(String paramString)
    {
      if (paramString != null)
        if (paramString.length() <= 100);
      for (paramString = paramString.substring(0, 100); ; paramString = "")
      {
        this.jdField_if = paramString;
        return;
      }
    }

    private String a(int paramInt)
    {
      if (!b.m().exists())
        return null;
      while (true)
      {
        int i;
        try
        {
          RandomAccessFile localRandomAccessFile = new RandomAccessFile(b.m(), "rw");
          localRandomAccessFile.seek(0L);
          i = localRandomAccessFile.readInt();
          if (!b.a(i, localRandomAccessFile.readInt(), localRandomAccessFile.readInt()))
          {
            localRandomAccessFile.close();
            b.jdMethod_long();
            return null;
            localRandomAccessFile.close();
            return null;
            long l = 12L + 0L + 1024 * (paramInt - 1);
            localRandomAccessFile.seek(l);
            int j = localRandomAccessFile.readInt();
            byte[] arrayOfByte = new byte[j];
            localRandomAccessFile.seek(l + 4L);
            int k = 0;
            if (k >= j)
              continue;
            arrayOfByte[k] = localRandomAccessFile.readByte();
            k++;
            continue;
            localRandomAccessFile.close();
            String str = new String(arrayOfByte);
            return str;
          }
        }
        catch (IOException localIOException)
        {
          return null;
        }
        if (paramInt == 0)
          continue;
        if (paramInt != i + 1)
          continue;
      }
    }

    private void a()
    {
      if ((b.c() != null) && (b.c().length() >= 100))
        a(b.c().toString());
      b.n();
    }

    private boolean a(Location paramLocation)
    {
      return a(paramLocation, j.V, j.aa);
    }

    private boolean a(Location paramLocation, int paramInt1, int paramInt2)
    {
      if ((paramLocation == null) || (!j.void) || (!this.a) || (!j.P))
        return false;
      if (j.V < 5)
      {
        j.V = 5;
        if (j.aa >= 5)
          break label241;
        j.aa = 5;
      }
      double d1;
      double d2;
      long l1;
      while (true)
      {
        d1 = paramLocation.getLongitude();
        d2 = paramLocation.getLatitude();
        l1 = paramLocation.getTime() / 1000L;
        if (!b.jdMethod_else())
          break label259;
        b.jdField_if(1);
        b.a(new StringBuffer(""));
        StringBuffer localStringBuffer3 = b.c();
        Object[] arrayOfObject3 = new Object[4];
        arrayOfObject3[0] = this.jdField_if;
        arrayOfObject3[1] = Long.valueOf(l1);
        arrayOfObject3[2] = Double.valueOf(d1);
        arrayOfObject3[3] = Double.valueOf(d2);
        localStringBuffer3.append(String.format("&nr=%s&traj=%d,%.5f,%.5f|", arrayOfObject3));
        b.jdMethod_do(b.c().length());
        b.a(l1);
        b.jdField_if(d1);
        b.a(d2);
        b.jdField_if(()Math.floor(0.5D + d1 * 100000.0D));
        b.jdMethod_do(()Math.floor(0.5D + d2 * 100000.0D));
        b.jdField_if(false);
        return true;
        if (j.V <= 1000)
          break;
        j.V = 1000;
        break;
        label241: if (j.aa <= 3600)
          continue;
        j.aa = 3600;
      }
      label259: float[] arrayOfFloat = new float[1];
      Location.distanceBetween(d2, d1, b.d(), b.a(), arrayOfFloat);
      long l2 = l1 - b.g();
      if ((arrayOfFloat[0] >= j.V) || (l2 >= j.aa))
      {
        if (b.c() == null)
        {
          b.i();
          b.jdMethod_do(0);
          b.a(new StringBuffer(""));
          StringBuffer localStringBuffer2 = b.c();
          Object[] arrayOfObject2 = new Object[4];
          arrayOfObject2[0] = this.jdField_if;
          arrayOfObject2[1] = Long.valueOf(l1);
          arrayOfObject2[2] = Double.valueOf(d1);
          arrayOfObject2[3] = Double.valueOf(d2);
          localStringBuffer2.append(String.format("&nr=%s&traj=%d,%.5f,%.5f|", arrayOfObject2));
          b.jdMethod_do(b.c().length());
          b.a(l1);
          b.jdField_if(d1);
          b.a(d2);
          b.jdField_if(()Math.floor(0.5D + d1 * 100000.0D));
          b.jdMethod_do(()Math.floor(0.5D + d2 * 100000.0D));
        }
        while (true)
        {
          if (15 + b.jdMethod_byte() > 750)
          {
            a(b.c().toString());
            b.a(null);
          }
          if (b.jdMethod_for() >= j.L)
            this.a = false;
          return true;
          b.jdField_if(d1);
          b.a(d2);
          long l3 = ()Math.floor(0.5D + d1 * 100000.0D);
          long l4 = ()Math.floor(0.5D + d2 * 100000.0D);
          b.jdMethod_for((int)(l1 - b.g()));
          b.jdMethod_new((int)(l3 - b.o()));
          b.jdMethod_int((int)(l4 - b.b()));
          StringBuffer localStringBuffer1 = b.c();
          Object[] arrayOfObject1 = new Object[3];
          arrayOfObject1[0] = Integer.valueOf(b.jdField_if());
          arrayOfObject1[1] = Integer.valueOf(b.e());
          arrayOfObject1[2] = Integer.valueOf(b.jdMethod_do());
          localStringBuffer1.append(String.format("%d,%d,%d|", arrayOfObject1));
          b.jdMethod_do(b.c().length());
          b.a(l1);
          b.jdField_if(l3);
          b.jdMethod_do(l4);
        }
      }
      return false;
    }

    private boolean a(String paramString)
    {
      if ((paramString == null) || (!paramString.startsWith("&nr")));
      do
        return false;
      while ((!b.m().exists()) && (!b.jdMethod_long()));
      while (true)
      {
        int k;
        try
        {
          RandomAccessFile localRandomAccessFile = new RandomAccessFile(b.m(), "rw");
          localRandomAccessFile.seek(0L);
          int i = localRandomAccessFile.readInt();
          int j = localRandomAccessFile.readInt();
          k = localRandomAccessFile.readInt();
          if (b.a(i, j, k))
            continue;
          localRandomAccessFile.close();
          b.jdMethod_long();
          return false;
          if (!j.try)
            continue;
          if (i == j.L)
            continue;
          if ((k <= 1) || (!paramString.equals(a(k - 1))))
            continue;
          localRandomAccessFile.close();
          return false;
          if (k == 1)
          {
            i4 = j.L;
            if (!paramString.equals(a(i4)))
              continue;
            localRandomAccessFile.close();
            return false;
            localRandomAccessFile.seek(0L + (12 + 1024 * (k - 1)));
            if (paramString.length() <= 750)
              continue;
            localRandomAccessFile.close();
            return false;
            String str = Jni.jdField_if(paramString);
            int m = str.length();
            if (m <= 1020)
              continue;
            localRandomAccessFile.close();
            return false;
            localRandomAccessFile.writeInt(m);
            localRandomAccessFile.writeBytes(str);
            if (i != 0)
              continue;
            localRandomAccessFile.seek(0L);
            localRandomAccessFile.writeInt(1);
            localRandomAccessFile.writeInt(1);
            localRandomAccessFile.writeInt(2);
            localRandomAccessFile.close();
            return true;
            if (i >= -1 + j.L)
              continue;
            localRandomAccessFile.seek(0L);
            localRandomAccessFile.writeInt(i + 1);
            localRandomAccessFile.seek(8L);
            localRandomAccessFile.writeInt(i + 2);
            continue;
            if (i != -1 + j.L)
              continue;
            localRandomAccessFile.seek(0L);
            localRandomAccessFile.writeInt(j.L);
            if ((j != 0) && (j != 1))
              continue;
            localRandomAccessFile.writeInt(2);
            localRandomAccessFile.seek(8L);
            localRandomAccessFile.writeInt(1);
            continue;
            if (k != j)
              continue;
            if (k != j.L)
              break label496;
            n = 1;
            if (n != j.L)
              break label505;
            i1 = 1;
            localRandomAccessFile.seek(4L);
            localRandomAccessFile.writeInt(i1);
            localRandomAccessFile.writeInt(n);
            continue;
            if (k != j.L)
              continue;
            int i2 = 1;
            if (i2 != j)
              continue;
            if (i2 != j.L)
              continue;
            int i3 = 1;
            localRandomAccessFile.seek(4L);
            localRandomAccessFile.writeInt(i3);
            localRandomAccessFile.seek(8L);
            localRandomAccessFile.writeInt(i2);
            continue;
            i2 = k + 1;
            continue;
            i3 = i2 + 1;
            continue;
          }
        }
        catch (IOException localIOException)
        {
          return false;
        }
        int i4 = k - 1;
        continue;
        label496: int n = k + 1;
        continue;
        label505: int i1 = n + 1;
      }
    }

    private boolean jdField_if()
    {
      if (b.m().exists())
        b.m().delete();
      b.n();
      return !b.m().exists();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.b
 * JD-Core Version:    0.6.0
 */