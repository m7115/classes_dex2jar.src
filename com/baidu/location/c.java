package com.baidu.location;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class c
{
  private static String jdField_byte = null;
  private static int c;
  private static Method jdField_case;
  private static boolean jdField_char;
  private static Class d;
  private static Method jdField_for;
  private static String jdField_goto = null;
  private static Method jdField_long = null;
  private static long jdField_void;
  private a a = new a();
  private boolean b = false;
  private Handler jdField_do = null;
  private final String jdField_else = "baidu_location_service";
  private Context jdField_if = null;
  private b jdField_int = null;
  private List jdField_new = null;
  private TelephonyManager jdField_try = null;

  static
  {
    jdField_case = null;
    jdField_for = null;
    d = null;
    jdField_void = 3000L;
    c = 3;
    jdField_char = false;
  }

  public c(Context paramContext, Handler paramHandler)
  {
    this.jdField_if = paramContext;
    this.jdField_do = paramHandler;
  }

  public static String a(boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer(256);
    localStringBuffer.append("&sdk=");
    localStringBuffer.append(3.3F);
    if ((paramBoolean) && (j.A.equals("all")))
      localStringBuffer.append("&addr=all");
    if (paramBoolean)
      localStringBuffer.append("&coor=gcj02");
    if (jdField_goto == null)
    {
      localStringBuffer.append("&im=");
      localStringBuffer.append(jdField_byte);
    }
    while (true)
    {
      localStringBuffer.append("&mb=");
      localStringBuffer.append(Build.MODEL);
      localStringBuffer.append("&resid=");
      localStringBuffer.append("12");
      localStringBuffer.append("&os=A");
      localStringBuffer.append(Build.VERSION.SDK);
      if (paramBoolean)
      {
        localStringBuffer.append("&sv=");
        String str = Build.VERSION.RELEASE;
        if ((str != null) && (str.length() > 5))
          str = str.substring(0, 5);
        localStringBuffer.append(str);
      }
      return localStringBuffer.toString();
      localStringBuffer.append("&cu=");
      localStringBuffer.append(jdField_goto);
    }
  }

  private void a(CellLocation paramCellLocation)
  {
    if ((paramCellLocation == null) || (this.jdField_try == null))
      return;
    if (!jdField_char)
    {
      jdField_byte = this.jdField_try.getDeviceId();
      jdField_char = jdField_if();
    }
    j.jdField_if("baidu_location_service", "set cell info..");
    a locala1 = new a();
    locala1.jdField_byte = System.currentTimeMillis();
    String str1 = this.jdField_try.getNetworkOperator();
    if ((str1 != null) && (str1.length() > 0));
    while (true)
    {
      int j;
      try
      {
        if (str1.length() < 3)
          continue;
        int n = Integer.valueOf(str1.substring(0, 3)).intValue();
        if (n >= 0)
          continue;
        n = this.a.jdField_do;
        locala1.jdField_do = n;
        String str2 = str1.substring(3);
        if (str2 == null)
          break label676;
        char[] arrayOfChar = str2.toCharArray();
        k = 0;
        if ((k < arrayOfChar.length) && (Character.isDigit(arrayOfChar[k])))
          continue;
        int m = Integer.valueOf(str2.substring(0, k)).intValue();
        if (m >= 0)
          continue;
        m = this.a.jdField_if;
        locala1.jdField_if = m;
        if (!(paramCellLocation instanceof GsmCellLocation))
          continue;
        locala1.jdField_for = ((GsmCellLocation)paramCellLocation).getLac();
        locala1.jdField_try = ((GsmCellLocation)paramCellLocation).getCid();
        locala1.jdField_new = 'g';
        if ((!locala1.jdField_for()) || ((this.a != null) && (this.a.a(locala1))))
          break;
        this.a = locala1;
        this.jdField_do.obtainMessage(31).sendToTarget();
        if (!locala1.jdField_for())
          break label659;
        if (this.jdField_new != null)
          continue;
        this.jdField_new = new LinkedList();
        j = this.jdField_new.size();
        if (j != 0)
          break label638;
        locala2 = null;
        if ((locala2 != null) && (locala2.jdField_try == this.a.jdField_try) && (locala2.jdField_for == this.a.jdField_for))
          break;
        if (locala2 == null)
          continue;
        locala2.jdField_byte = (this.a.jdField_byte - locala2.jdField_byte);
        this.jdField_new.add(this.a);
        if (this.jdField_new.size() <= c)
          break;
        this.jdField_new.remove(0);
        return;
        k++;
        continue;
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
        continue;
        if (!(paramCellLocation instanceof CdmaCellLocation))
          continue;
        locala1.jdField_new = 'c';
      }
      if (Integer.parseInt(Build.VERSION.SDK) < 5)
        break;
      if (d == null);
      try
      {
        d = Class.forName("android.telephony.cdma.CdmaCellLocation");
        jdField_long = d.getMethod("getBaseStationId", new Class[0]);
        jdField_case = d.getMethod("getNetworkId", new Class[0]);
        jdField_for = d.getMethod("getSystemId", new Class[0]);
        if ((d == null) || (!d.isInstance(paramCellLocation)))
          continue;
        try
        {
          int i = ((Integer)jdField_for.invoke(paramCellLocation, new Object[0])).intValue();
          if (i < 0)
            i = this.a.jdField_if;
          locala1.jdField_if = i;
          locala1.jdField_try = ((Integer)jdField_long.invoke(paramCellLocation, new Object[0])).intValue();
          locala1.jdField_for = ((Integer)jdField_case.invoke(paramCellLocation, new Object[0])).intValue();
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
          return;
        }
      }
      catch (Exception localException2)
      {
        d = null;
        localException2.printStackTrace();
        return;
      }
      label638: a locala2 = (a)this.jdField_new.get(j - 1);
      continue;
      label659: if (this.jdField_new == null)
        break;
      this.jdField_new.clear();
      return;
      label676: int k = 0;
    }
  }

  private boolean jdField_if()
  {
    if ((jdField_byte == null) || (jdField_byte.length() < 10));
    while (true)
    {
      return false;
      try
      {
        char[] arrayOfChar = jdField_byte.toCharArray();
        for (int i = 0; ; i++)
        {
          if (i >= 10)
            break label60;
          if (arrayOfChar[i] > '9')
            break;
          int j = arrayOfChar[i];
          if (j < 48)
            break;
        }
        label60: return true;
      }
      catch (Exception localException)
      {
      }
    }
    return false;
  }

  public a a()
  {
    if (((this.a == null) || (!this.a.jdField_do()) || (!this.a.jdField_for())) && (this.jdField_try != null));
    try
    {
      a(this.jdField_try.getCellLocation());
      label45: return this.a;
    }
    catch (Exception localException)
    {
      break label45;
    }
  }

  public void jdField_byte()
  {
    if (!this.b)
      return;
    if ((this.jdField_int != null) && (this.jdField_try != null))
      this.jdField_try.listen(this.jdField_int, 0);
    this.jdField_int = null;
    this.jdField_try = null;
    this.jdField_new.clear();
    this.jdField_new = null;
    j.jdField_if("baidu_location_service", "cell manager stop ...");
    this.b = false;
  }

  public void jdField_do()
  {
    if (this.b == true);
    do
    {
      return;
      this.jdField_try = ((TelephonyManager)this.jdField_if.getSystemService("phone"));
      this.jdField_new = new LinkedList();
      this.jdField_int = new b();
    }
    while ((this.jdField_try == null) || (this.jdField_int == null));
    try
    {
      this.jdField_try.listen(this.jdField_int, 272);
      jdField_byte = this.jdField_try.getDeviceId();
      j.jdField_do = "v3.3" + jdField_byte + "|" + Build.MODEL;
      try
      {
        label124: jdField_goto = j.a.jdField_if(this.jdField_if);
        j.jdField_if("baidu_location_service", "CUID:" + jdField_goto);
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            if (jdField_goto != null)
              j.jdField_do = "v3.3|" + jdField_goto + "|" + Build.MODEL;
            j.jdField_if("baidu_location_service", "CUID:" + j.jdField_do);
            label231: jdField_char = jdField_if();
            j.a("baidu_location_service", "i:" + jdField_byte);
            j.jdField_if("baidu_location_service", "cell manager start...");
            this.b = true;
            return;
            localException2 = localException2;
            jdField_goto = null;
          }
        }
        catch (Exception localException3)
        {
          break label231;
        }
      }
    }
    catch (Exception localException1)
    {
      break label124;
    }
  }

  public String jdField_for()
  {
    if (this.jdField_try == null)
      this.jdField_try = ((TelephonyManager)this.jdField_if.getSystemService("phone"));
    try
    {
      a(this.jdField_try.getCellLocation());
      label35: return this.a.toString();
    }
    catch (Exception localException)
    {
      break label35;
    }
  }

  public String jdField_int()
  {
    return jdField_byte;
  }

  public int jdField_new()
  {
    return this.jdField_try.getNetworkType();
  }

  public class a
  {
    public long jdField_byte = 0L;
    public int jdField_do = -1;
    public int jdField_for = -1;
    public int jdField_if = -1;
    public int jdField_int = -1;
    public char jdField_new = '\000';
    public int jdField_try = -1;

    public a()
    {
      this.jdField_byte = System.currentTimeMillis();
    }

    public a(int paramInt1, int paramInt2, int paramInt3, int paramChar, char arg6)
    {
      this.jdField_for = paramInt1;
      this.jdField_try = paramInt2;
      this.jdField_do = paramInt3;
      this.jdField_if = paramChar;
      char c;
      this.jdField_new = c;
      this.jdField_byte = (System.currentTimeMillis() / 1000L);
    }

    public String a()
    {
      StringBuffer localStringBuffer = new StringBuffer(128);
      localStringBuffer.append(23 + this.jdField_try);
      localStringBuffer.append("H");
      localStringBuffer.append(45 + this.jdField_for);
      localStringBuffer.append("K");
      localStringBuffer.append(54 + this.jdField_if);
      localStringBuffer.append("Q");
      localStringBuffer.append(203 + this.jdField_do);
      return localStringBuffer.toString();
    }

    public boolean a(a parama)
    {
      return (this.jdField_for == parama.jdField_for) && (this.jdField_try == parama.jdField_try) && (this.jdField_if == parama.jdField_if);
    }

    public boolean jdField_do()
    {
      return System.currentTimeMillis() - this.jdField_byte < c.jdField_try();
    }

    public boolean jdField_for()
    {
      return (this.jdField_for > -1) && (this.jdField_try > 0);
    }

    public String jdField_if()
    {
      StringBuffer localStringBuffer = new StringBuffer(64);
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = Integer.valueOf(this.jdField_do);
      arrayOfObject[1] = Integer.valueOf(this.jdField_if);
      arrayOfObject[2] = Integer.valueOf(this.jdField_for);
      arrayOfObject[3] = Integer.valueOf(this.jdField_try);
      arrayOfObject[4] = Integer.valueOf(this.jdField_int);
      localStringBuffer.append(String.format("cell=%d|%d|%d|%d:%d", arrayOfObject));
      return localStringBuffer.toString();
    }

    public String jdField_int()
    {
      while (true)
      {
        int i;
        Object localObject3;
        try
        {
          List localList = c.jdField_if(c.this).getNeighboringCellInfo();
          if ((localList == null) || (localList.isEmpty()))
            break label346;
          localObject2 = "&nc=";
          Iterator localIterator = localList.iterator();
          i = 0;
          if (localIterator.hasNext())
          {
            NeighboringCellInfo localNeighboringCellInfo = (NeighboringCellInfo)localIterator.next();
            if (i != 0)
              continue;
            if (localNeighboringCellInfo.getLac() == this.jdField_for)
              continue;
            localObject3 = (String)localObject2 + localNeighboringCellInfo.getLac() + "|" + localNeighboringCellInfo.getCid() + "|" + localNeighboringCellInfo.getRssi();
            break label351;
            localObject3 = (String)localObject2 + "|" + localNeighboringCellInfo.getCid() + "|" + localNeighboringCellInfo.getRssi();
            break label351;
            if (i < 8)
            {
              if (localNeighboringCellInfo.getLac() == this.jdField_for)
                continue;
              localObject3 = (String)localObject2 + ";" + localNeighboringCellInfo.getLac() + "|" + localNeighboringCellInfo.getCid() + "|" + localNeighboringCellInfo.getRssi();
              break label351;
              String str = (String)localObject2 + ";" + "|" + localNeighboringCellInfo.getCid() + "|" + localNeighboringCellInfo.getRssi();
              localObject3 = str;
            }
          }
        }
        catch (Exception localException)
        {
          localObject1 = null;
          j.jdField_if("baidu_location_service", "Neighbour:" + (String)localObject1);
          return localObject1;
        }
        Object localObject1 = localObject2;
        continue;
        label346: localObject1 = null;
        continue;
        label351: i++;
        Object localObject2 = localObject3;
      }
    }

    public String toString()
    {
      StringBuffer localStringBuffer = new StringBuffer(128);
      localStringBuffer.append("&nw=");
      localStringBuffer.append(c.jdField_for(c.this).jdField_new);
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = Integer.valueOf(this.jdField_do);
      arrayOfObject[1] = Integer.valueOf(this.jdField_if);
      arrayOfObject[2] = Integer.valueOf(this.jdField_for);
      arrayOfObject[3] = Integer.valueOf(this.jdField_try);
      arrayOfObject[4] = Integer.valueOf(this.jdField_int);
      localStringBuffer.append(String.format("&cl=%d|%d|%d|%d&cl_s=%d", arrayOfObject));
      localStringBuffer.append("&cl_t=");
      localStringBuffer.append(this.jdField_byte);
      if ((c.jdField_do(c.this) != null) && (c.jdField_do(c.this).size() > 0))
      {
        int i = c.jdField_do(c.this).size();
        localStringBuffer.append("&clt=");
        int j = 0;
        if (j < i)
        {
          a locala = (a)c.jdField_do(c.this).get(j);
          if (locala.jdField_do != this.jdField_do)
            localStringBuffer.append(locala.jdField_do);
          localStringBuffer.append("|");
          if (locala.jdField_if != this.jdField_if)
            localStringBuffer.append(locala.jdField_if);
          localStringBuffer.append("|");
          if (locala.jdField_for != this.jdField_for)
            localStringBuffer.append(locala.jdField_for);
          localStringBuffer.append("|");
          if (locala.jdField_try != this.jdField_try)
            localStringBuffer.append(locala.jdField_try);
          localStringBuffer.append("|");
          if (j != i - 1)
            localStringBuffer.append(locala.jdField_byte / 1000L);
          while (true)
          {
            localStringBuffer.append(";");
            j++;
            break;
            localStringBuffer.append((System.currentTimeMillis() - locala.jdField_byte) / 1000L);
          }
        }
      }
      return localStringBuffer.toString();
    }
  }

  private class b extends PhoneStateListener
  {
    public b()
    {
    }

    public void onCellLocationChanged(CellLocation paramCellLocation)
    {
      if (paramCellLocation == null)
        return;
      try
      {
        c.a(c.this, c.jdMethod_if(c.this).getCellLocation());
        return;
      }
      catch (Exception localException)
      {
      }
    }

    public void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
    {
      if (c.jdMethod_for(c.this) != null)
      {
        if (c.jdMethod_for(c.this).jdField_new != 'g')
          break label98;
        c.jdMethod_for(c.this).jdField_int = paramSignalStrength.getGsmSignalStrength();
      }
      while (true)
      {
        j.jdMethod_if("cell strength", "===== cell singal strength changed : " + c.jdMethod_for(c.this).jdField_int);
        if (c.a(c.this) != null)
          c.a(c.this).obtainMessage(31).sendToTarget();
        return;
        label98: if (c.jdMethod_for(c.this).jdField_new != 'c')
          continue;
        c.jdMethod_for(c.this).jdField_int = paramSignalStrength.getCdmaDbm();
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.c
 * JD-Core Version:    0.6.0
 */