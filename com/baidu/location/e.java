package com.baidu.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

class e
{
  private static final int jdField_byte = 15;
  private static String jdField_try = "baidu_location_service";
  private final long a = 5000L;
  private long b = 0L;
  private a c = null;
  private c jdField_case = null;
  private b jdField_char = null;
  private boolean d = false;
  private boolean jdField_do = false;
  private Method e = null;
  private boolean jdField_else = false;
  private final long f = 3000L;
  private boolean jdField_for = true;
  private Object g = null;
  private Context jdField_goto;
  private Handler jdField_if = null;
  private boolean jdField_int = false;
  private long jdField_long = 0L;
  private final long jdField_new = 3000L;
  private WifiManager jdField_void = null;

  public e(Context paramContext, Handler paramHandler)
  {
    this.jdField_goto = paramContext;
    this.jdField_if = paramHandler;
  }

  private void jdField_goto()
  {
    NetworkInfo.State localState1 = NetworkInfo.State.UNKNOWN;
    try
    {
      NetworkInfo.State localState3 = ((ConnectivityManager)this.jdField_goto.getSystemService("connectivity")).getNetworkInfo(1).getState();
      localState2 = localState3;
      if (NetworkInfo.State.CONNECTED == localState2)
        if (this.d)
          return;
    }
    catch (Exception localException)
    {
      while (true)
        NetworkInfo.State localState2 = localState1;
      this.d = true;
      this.jdField_if.postDelayed(new d(null), j.S);
      this.jdField_do = true;
      return;
    }
    this.d = false;
  }

  private void jdField_if()
  {
    if (this.jdField_void == null);
    while (true)
    {
      return;
      try
      {
        List localList = this.jdField_void.getScanResults();
        c localc = new c(localList, this.b);
        this.b = 0L;
        if ((this.jdField_case != null) && (localc.jdField_if(this.jdField_case)))
          continue;
        this.jdField_case = localc;
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }

  public boolean a()
  {
    long l = System.currentTimeMillis();
    if (l - this.jdField_long <= 10000L)
      return false;
    this.jdField_long = l;
    return jdField_new();
  }

  public c jdField_byte()
  {
    if (((this.jdField_case == null) || (!this.jdField_case.jdField_new())) && (this.jdField_void != null))
      try
      {
        c localc = new c(this.jdField_void.getScanResults(), 0L);
        return localc;
      }
      catch (Exception localException)
      {
        return new c(null, 0L);
      }
    return this.jdField_case;
  }

  public void jdField_case()
  {
    if (this.c == null)
      this.c = new a(null);
  }

  public String jdField_char()
  {
    WifiInfo localWifiInfo = this.jdField_void.getConnectionInfo();
    if (localWifiInfo == null);
    while (true)
    {
      return null;
      try
      {
        String str1 = localWifiInfo.getBSSID();
        if (str1 == null)
          continue;
        String str2 = str1.replace(":", "");
        return str2;
      }
      catch (Exception localException)
      {
      }
    }
    return null;
  }

  public void jdField_else()
  {
    if (!this.jdField_int)
      return;
    try
    {
      this.jdField_goto.unregisterReceiver(this.jdField_char);
      label19: this.jdField_char = null;
      this.jdField_void = null;
      this.c = null;
      this.jdField_int = false;
      j.jdField_if(jdField_try, "wifimanager stop ...");
      return;
    }
    catch (Exception localException)
    {
      break label19;
    }
  }

  public void jdField_for()
  {
    if (this.jdField_do);
  }

  public c jdField_int()
  {
    if (((this.jdField_case == null) || (!this.jdField_case.jdField_for())) && (this.jdField_void != null))
      try
      {
        c localc = new c(this.jdField_void.getScanResults(), 0L);
        return localc;
      }
      catch (Exception localException)
      {
        return new c(null, 0L);
      }
    return this.jdField_case;
  }

  public boolean jdField_new()
  {
    if (this.jdField_void == null);
    long l;
    do
    {
      return false;
      l = System.currentTimeMillis();
    }
    while (l - this.b <= 3000L);
    try
    {
      if (this.jdField_void.isWifiEnabled())
      {
        if (this.e != null)
        {
          Object localObject1 = this.g;
          if (localObject1 == null);
        }
        while (true)
        {
          try
          {
            Method localMethod = this.e;
            Object localObject2 = this.g;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Boolean.valueOf(this.jdField_for);
            localMethod.invoke(localObject2, arrayOfObject);
            this.b = l;
            j.jdField_if(jdField_try, "wifimanager start scan ...");
            return true;
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
            this.jdField_void.startScan();
            continue;
          }
          this.jdField_void.startScan();
        }
      }
      this.b = 0L;
      return false;
    }
    catch (Exception localException1)
    {
    }
    return false;
  }

  public void jdField_try()
  {
    if (this.jdField_int == true)
      return;
    this.jdField_void = ((WifiManager)this.jdField_goto.getSystemService("wifi"));
    this.jdField_char = new b(null);
    try
    {
      this.jdField_goto.registerReceiver(this.jdField_char, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
      this.c = new a(null);
      label72: this.jdField_int = true;
      j.jdField_if(jdField_try, "wifimanager start ...");
      Field localField;
      try
      {
        localField = Class.forName("android.net.wifi.WifiManager").getDeclaredField("mService");
        if (localField == null)
        {
          j.jdField_if(jdField_try, "android.net.wifi.WifiManager.mService  NOT  found ...");
          return;
        }
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        return;
      }
      localField.setAccessible(true);
      this.g = localField.get(this.jdField_void);
      Class localClass = this.g.getClass();
      j.jdField_if(jdField_try, "mserviceClass : " + localClass.getName());
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      this.e = localClass.getDeclaredMethod("startScan", arrayOfClass);
      if (this.e == null)
      {
        j.jdField_if(jdField_try, "mService.startScan NOT  found ...");
        return;
      }
      this.e.setAccessible(true);
      return;
    }
    catch (Exception localException1)
    {
      break label72;
    }
  }

  private class a extends BroadcastReceiver
  {
    private a()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramContext == null) || (e.jdMethod_if(e.this) == null))
        return;
      e.a(e.this);
    }
  }

  private class b extends BroadcastReceiver
  {
    private b()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramContext == null) || (e.jdMethod_if(e.this) == null))
        return;
      e.jdMethod_for(e.this);
      e.jdMethod_if(e.this).obtainMessage(41).sendToTarget();
      j.jdMethod_if(e.jdMethod_do(), "wifi manager receive new wifi...");
    }
  }

  protected class c
  {
    private boolean jdField_do = false;
    public List jdField_for = null;
    private long jdField_if = 0L;
    private long jdField_int = 0L;

    public c(List paramLong, long arg3)
    {
      Object localObject;
      this.jdField_if = localObject;
      this.jdField_for = paramLong;
      this.jdField_int = System.currentTimeMillis();
      a();
      j.a(e.jdField_do(), jdField_int());
    }

    private void a()
    {
      if (jdMethod_try() < 1)
        return;
      int i = -1 + this.jdField_for.size();
      int j = 1;
      label23: int k;
      if ((i >= 1) && (j != 0))
      {
        k = 0;
        j = 0;
        label36: if (k < i)
        {
          if (((ScanResult)this.jdField_for.get(k)).level >= ((ScanResult)this.jdField_for.get(k + 1)).level)
            break label149;
          ScanResult localScanResult = (ScanResult)this.jdField_for.get(k + 1);
          this.jdField_for.set(k + 1, this.jdField_for.get(k));
          this.jdField_for.set(k, localScanResult);
        }
      }
      label149: for (int m = 1; ; m = j)
      {
        k++;
        j = m;
        break label36;
        i--;
        break label23;
        break;
      }
    }

    public String a(int paramInt)
    {
      if (jdMethod_try() < 1)
        return null;
      StringBuffer localStringBuffer = new StringBuffer(512);
      String str1 = e.this.jdMethod_char();
      int i = this.jdField_for.size();
      if (i > paramInt);
      while (true)
      {
        int j = 0;
        int k = 1;
        int m = 0;
        int n = 0;
        int i5;
        int i4;
        while (true)
          if (j < paramInt)
          {
            if (((ScanResult)this.jdField_for.get(j)).level == 0)
            {
              i5 = k;
              i4 = n;
              j++;
              n = i4;
              k = i5;
              continue;
            }
            if (k == 0)
              break;
            localStringBuffer.append("&wf=");
            String str3 = ((ScanResult)this.jdField_for.get(j)).BSSID.replace(":", "");
            localStringBuffer.append(str3);
            int i7 = ((ScanResult)this.jdField_for.get(j)).level;
            if (i7 < 0)
              i7 = -i7;
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Integer.valueOf(i7);
            localStringBuffer.append(String.format(";%d;", arrayOfObject2));
            i4 = n + 1;
            if ((str1 == null) || (!str1.equals(str3)))
              break label493;
          }
        label493: for (int i8 = i4; ; i8 = m)
        {
          m = i8;
          i5 = 0;
          break;
          localStringBuffer.append("|");
          String str2 = ((ScanResult)this.jdField_for.get(j)).BSSID.replace(":", "");
          localStringBuffer.append(str2);
          int i1 = ((ScanResult)this.jdField_for.get(j)).level;
          if (i1 < 0)
            i1 = -i1;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(i1);
          localStringBuffer.append(String.format(";%d;", arrayOfObject1));
          int i2 = n + 1;
          if ((str1 != null) && (str1.equals(str2)))
          {
            m = i2;
            int i6 = k;
            i4 = i2;
            i5 = i6;
            break;
            if (k == 0)
            {
              j.jdField_if(e.jdField_do(), str1 + m);
              localStringBuffer.append("&wf_n=" + m);
              localStringBuffer.append("&wf_st=");
              localStringBuffer.append(this.jdField_if);
              localStringBuffer.append("&wf_et=");
              localStringBuffer.append(this.jdField_int);
              if (m > 0)
                this.jdField_do = true;
              return localStringBuffer.toString();
            }
            return null;
          }
          int i3 = k;
          i4 = i2;
          i5 = i3;
          break;
        }
        paramInt = i;
      }
    }

    public boolean a(c paramc)
    {
      return a(paramc, this, j.a);
    }

    public boolean a(c paramc1, c paramc2, float paramFloat)
    {
      if ((paramc1 == null) || (paramc2 == null))
        return false;
      List localList1 = paramc1.jdField_for;
      List localList2 = paramc2.jdField_for;
      if (localList1 == localList2)
        return true;
      if ((localList1 == null) || (localList2 == null))
        return false;
      int i = localList1.size();
      int j = localList2.size();
      float f = i + j;
      if ((i == 0) && (j == 0))
        return true;
      if ((i == 0) || (j == 0))
        return false;
      int k = 0;
      int m = 0;
      String str2;
      int i1;
      if (k < i)
      {
        str2 = ((ScanResult)localList1.get(k)).BSSID;
        if (str2 == null)
          i1 = m;
      }
      while (true)
      {
        k++;
        m = i1;
        break;
        for (int n = 0; ; n++)
        {
          if (n >= j)
            break label256;
          if (!str2.equals(((ScanResult)localList2.get(n)).BSSID))
            continue;
          i1 = m + 1;
          break;
        }
        String str1 = e.jdField_do();
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = Integer.valueOf(m);
        arrayOfObject[1] = Float.valueOf(f);
        arrayOfObject[2] = Float.valueOf(paramFloat);
        j.jdField_if(str1, String.format("same %d,total %f,rate %f...", arrayOfObject));
        return m * 2 >= f * paramFloat;
        label256: i1 = m;
      }
    }

    public String jdMethod_byte()
    {
      try
      {
        String str = a(15);
        return str;
      }
      catch (Exception localException)
      {
      }
      return null;
    }

    public boolean jdMethod_case()
    {
      return this.jdField_do;
    }

    public String jdMethod_char()
    {
      try
      {
        String str = a(j.Y);
        return str;
      }
      catch (Exception localException)
      {
      }
      return null;
    }

    public int jdField_do()
    {
      for (int i = 0; ; i++)
      {
        int j = jdMethod_try();
        int k = 0;
        if (i < j)
        {
          int m = -((ScanResult)this.jdField_for.get(i)).level;
          if (m <= 0)
            continue;
          k = m;
        }
        return k;
      }
    }

    public boolean jdField_do(c paramc)
    {
      if ((this.jdField_for != null) && (paramc != null) && (paramc.jdField_for != null))
      {
        int i;
        if (this.jdField_for.size() < paramc.jdField_for.size())
          i = this.jdField_for.size();
        for (int j = 0; ; j++)
        {
          if (j >= i)
            break label166;
          String str1 = ((ScanResult)this.jdField_for.get(j)).BSSID;
          int k = ((ScanResult)this.jdField_for.get(j)).level;
          String str2 = ((ScanResult)paramc.jdField_for.get(j)).BSSID;
          int m = ((ScanResult)paramc.jdField_for.get(j)).level;
          if ((!str1.equals(str2)) || (k != m))
          {
            return false;
            i = paramc.jdField_for.size();
            break;
          }
        }
        label166: return true;
      }
      return false;
    }

    public String jdMethod_else()
    {
      StringBuffer localStringBuffer = new StringBuffer(512);
      localStringBuffer.append("wifi info:");
      if (jdMethod_try() < 1)
        return localStringBuffer.toString();
      int i = this.jdField_for.size();
      if (i > 10)
        i = 10;
      int j = 0;
      int k = 1;
      if (j < i)
      {
        int n;
        if (((ScanResult)this.jdField_for.get(j)).level == 0)
          n = k;
        while (true)
        {
          j++;
          k = n;
          break;
          if (k != 0)
          {
            localStringBuffer.append("wifi=");
            localStringBuffer.append(((ScanResult)this.jdField_for.get(j)).BSSID.replace(":", ""));
            int i1 = ((ScanResult)this.jdField_for.get(j)).level;
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Integer.valueOf(i1);
            localStringBuffer.append(String.format(";%d;", arrayOfObject2));
            n = 0;
            continue;
          }
          localStringBuffer.append(";");
          localStringBuffer.append(((ScanResult)this.jdField_for.get(j)).BSSID.replace(":", ""));
          int m = ((ScanResult)this.jdField_for.get(j)).level;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Integer.valueOf(m);
          localStringBuffer.append(String.format(",%d;", arrayOfObject1));
          n = k;
        }
      }
      return localStringBuffer.toString();
    }

    public boolean jdField_for()
    {
      return System.currentTimeMillis() - this.jdField_int < 3000L;
    }

    public String jdField_if(int paramInt)
    {
      if ((paramInt == 0) || (jdMethod_try() < 1))
        return null;
      StringBuffer localStringBuffer = new StringBuffer(256);
      int i = 0;
      int j = 1;
      int k = 0;
      if (k < j.Y)
      {
        if ((j & paramInt) != 0)
        {
          if (i != 0)
            break label125;
          localStringBuffer.append("&ssid=");
        }
        while (true)
        {
          localStringBuffer.append(((ScanResult)this.jdField_for.get(k)).BSSID);
          localStringBuffer.append(";");
          localStringBuffer.append(((ScanResult)this.jdField_for.get(k)).SSID);
          i++;
          j <<= 1;
          k++;
          break;
          label125: localStringBuffer.append("|");
        }
      }
      return localStringBuffer.toString();
    }

    public boolean jdField_if()
    {
      return System.currentTimeMillis() - this.jdField_if < 3000L;
    }

    public boolean jdField_if(c paramc)
    {
      if ((this.jdField_for != null) && (paramc != null) && (paramc.jdField_for != null))
      {
        int i;
        if (this.jdField_for.size() < paramc.jdField_for.size())
          i = this.jdField_for.size();
        for (int j = 0; ; j++)
        {
          if (j >= i)
            break label115;
          if (!((ScanResult)this.jdField_for.get(j)).BSSID.equals(((ScanResult)paramc.jdField_for.get(j)).BSSID))
          {
            return false;
            i = paramc.jdField_for.size();
            break;
          }
        }
        label115: return true;
      }
      return false;
    }

    public String jdField_int()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("wifi=");
      if (this.jdField_for == null)
        return localStringBuilder.toString();
      for (int i = 0; i < this.jdField_for.size(); i++)
      {
        int j = ((ScanResult)this.jdField_for.get(i)).level;
        localStringBuilder.append(((ScanResult)this.jdField_for.get(i)).BSSID.replace(":", ""));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(j);
        localStringBuilder.append(String.format(",%d;", arrayOfObject));
      }
      return localStringBuilder.toString();
    }

    public boolean jdMethod_new()
    {
      return System.currentTimeMillis() - this.jdField_int < 5000L;
    }

    public int jdMethod_try()
    {
      if (this.jdField_for == null)
        return 0;
      return this.jdField_for.size();
    }
  }

  private class d
    implements Runnable
  {
    private d()
    {
    }

    public void run()
    {
      if ((e.jdMethod_do(e.this)) && (j.R))
      {
        e.jdMethod_if(e.this).obtainMessage(91).sendToTarget();
        e.jdMethod_if(e.this).postDelayed(this, j.S);
        e.a(e.this, true);
        return;
      }
      e.a(e.this, false);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.e
 * JD-Core Version:    0.6.0
 */