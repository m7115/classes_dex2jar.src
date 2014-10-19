package com.tencent.mm.sdk.platformtools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

public class LBSManager extends BroadcastReceiver
{
  private static a e;
  boolean a;
  boolean b;
  boolean c;
  int d;
  private b f;
  private Context g;
  private c h;

  public static void a(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0)
      return;
    a.d("MicroMsg.LBSManager", "setLocationCache [" + paramFloat1 + "," + paramFloat2 + "] acc:" + paramInt1 + " source:" + paramInt2);
    if (e == null)
      e = new a();
    e.a = paramFloat1;
    e.b = paramFloat2;
    e.c = paramInt1;
    e.d = System.currentTimeMillis();
    e.e = paramInt2;
  }

  private void c()
  {
    this.h.a();
    this.a = true;
  }

  public String a()
  {
    return d.b(d.a(this.g));
  }

  public String b()
  {
    WifiManager localWifiManager = (WifiManager)this.g.getSystemService("wifi");
    if (localWifiManager == null)
    {
      a.a("MicroMsg.LBSManager", "no wifi service");
      return "";
    }
    if (localWifiManager.getConnectionInfo() == null)
    {
      a.a("MicroMsg.LBSManager", "WIFILocation wifi info null");
      return "";
    }
    LinkedList localLinkedList = new LinkedList();
    List localList = localWifiManager.getScanResults();
    if (localList != null)
      for (int i = 0; i < localList.size(); i++)
        localLinkedList.add(new d.b(((ScanResult)localList.get(i)).BSSID, ((ScanResult)localList.get(i)).level));
    return d.a(localLinkedList);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Location localLocation = (Location)paramIntent.getExtras().get("location");
    this.d = (1 + this.d);
    boolean bool;
    int i;
    String str1;
    String str2;
    if (localLocation != null)
    {
      bool = "gps".equals(localLocation.getProvider());
      if (((bool) && (localLocation.getAccuracy() <= 200.0F)) || ((!bool) && (localLocation.getAccuracy() <= 1000.0F) && (localLocation.getAccuracy() > 0.0F)))
      {
        if (!bool)
          break label269;
        i = 0;
        a((float)localLocation.getLatitude(), (float)localLocation.getLongitude(), (int)localLocation.getAccuracy(), i);
        if ((this.f != null) && ((!this.a) || (!this.b) || (!this.c)))
        {
          str1 = i.a(b());
          str2 = i.a(a());
          if (this.a)
            break label275;
          c();
          this.a = true;
          a.d("MicroMsg.LBSManager", "location by provider ok:[" + localLocation.getLatitude() + " , " + localLocation.getLongitude() + "]  accuracy:" + localLocation.getAccuracy() + "  retry count:" + this.d + " isGpsProvider:" + bool);
          this.f.a((float)localLocation.getLatitude(), (float)localLocation.getLongitude(), (int)localLocation.getAccuracy(), i, str1, str2, true);
        }
      }
    }
    label269: label275: 
    do
    {
      return;
      i = 1;
      break;
      if ((this.b) || (i != 0))
        continue;
      this.b = true;
      a.d("MicroMsg.LBSManager", "report location by GPS ok:[" + localLocation.getLatitude() + " , " + localLocation.getLongitude() + "]  accuracy:" + localLocation.getAccuracy() + "  retry count:" + this.d + " isGpsProvider:" + bool);
      this.f.a((float)localLocation.getLatitude(), (float)localLocation.getLongitude(), (int)localLocation.getAccuracy(), 3, str1, str2, true);
      return;
    }
    while ((this.c) || (i != 1));
    this.c = true;
    a.d("MicroMsg.LBSManager", "report location by Network ok:[" + localLocation.getLatitude() + " , " + localLocation.getLongitude() + "]  accuracy:" + localLocation.getAccuracy() + "  retry count:" + this.d + " isGpsProvider:" + bool);
    this.f.a((float)localLocation.getLatitude(), (float)localLocation.getLongitude(), (int)localLocation.getAccuracy(), 4, str1, str2, true);
  }

  static class a
  {
    float a = -1000.0F;
    float b = -1000.0F;
    int c = -1000;
    long d;
    int e = 1;
  }

  public static abstract interface b
  {
    public abstract void a(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, String paramString1, String paramString2, boolean paramBoolean);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.LBSManager
 * JD-Core Version:    0.6.0
 */