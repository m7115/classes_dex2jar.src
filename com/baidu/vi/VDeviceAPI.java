package com.baidu.vi;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.StatFs;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class VDeviceAPI
{
  private static PowerManager.WakeLock a = null;
  private static BroadcastReceiver b = null;

  public static String getAppVersion()
  {
    String str1 = c.a().getApplicationInfo().packageName;
    try
    {
      String str2 = c.a().getPackageManager().getPackageInfo(str1, 0).versionName;
      return str2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return null;
  }

  public static long getAvailableMemory()
  {
    ActivityManager localActivityManager = (ActivityManager)c.a().getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1024L;
  }

  public static String getCachePath()
  {
    return Environment.getDataDirectory().getAbsolutePath();
  }

  public static String getCellId()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)c.a().getSystemService("phone");
    if (localTelephonyManager == null)
      return null;
    CellLocation localCellLocation = localTelephonyManager.getCellLocation();
    if ((localCellLocation instanceof GsmCellLocation))
      return " " + ((GsmCellLocation)localCellLocation).getCid();
    return " ";
  }

  public static int getCurrentNetworkType()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)c.a().getSystemService("connectivity");
    if (localConnectivityManager.getActiveNetworkInfo() == null)
      return 0;
    switch (localConnectivityManager.getActiveNetworkInfo().getType())
    {
    default:
      return 1;
    case 1:
      return 2;
    case 0:
    }
    return 3;
  }

  public static long getFreeSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getRootDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getAvailableBlocks() / 1024L;
  }

  public static String getImei()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)c.a().getSystemService("phone");
    if (localTelephonyManager != null)
      return localTelephonyManager.getDeviceId();
    return null;
  }

  public static String getImsi()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)c.a().getSystemService("phone");
    if (localTelephonyManager != null)
      return localTelephonyManager.getSubscriberId();
    return null;
  }

  public static String getLac()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)c.a().getSystemService("phone");
    if (localTelephonyManager == null)
      return null;
    CellLocation localCellLocation = localTelephonyManager.getCellLocation();
    if ((localCellLocation instanceof GsmCellLocation))
      return "" + ((GsmCellLocation)localCellLocation).getLac();
    return "";
  }

  public static String getModuleFileName()
  {
    return c.a().getFilesDir().getAbsolutePath();
  }

  public static d getNetworkInfo(int paramInt)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)c.a().getSystemService("connectivity");
    NetworkInfo localNetworkInfo;
    switch (paramInt)
    {
    default:
      localNetworkInfo = null;
    case 2:
    case 3:
    }
    while (localNetworkInfo != null)
    {
      return new d(localNetworkInfo);
      localNetworkInfo = localConnectivityManager.getNetworkInfo(1);
      continue;
      localNetworkInfo = localConnectivityManager.getNetworkInfo(0);
    }
    return null;
  }

  public static String getOsVersion()
  {
    return "android";
  }

  public static int getScreenBrightness()
  {
    ContentResolver localContentResolver = c.a().getContentResolver();
    try
    {
      int k = Settings.System.getInt(localContentResolver, "screen_brightness_mode");
      i = k;
      if (i == 1)
        return -1;
      try
      {
        int j = Settings.System.getInt(localContentResolver, "screen_brightness");
        return j;
      }
      catch (Settings.SettingNotFoundException localSettingNotFoundException2)
      {
        return -1;
      }
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException1)
    {
      while (true)
        int i = 0;
    }
  }

  public static float getScreenDensity()
  {
    if (b.a() == null)
      return 0.0F;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    b.a().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.density;
  }

  public static int getScreenDensityDpi()
  {
    if (b.a() == null)
      return 0;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    b.a().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.densityDpi;
  }

  public static long getSdcardFreeSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getAvailableBlocks() / 1024L;
  }

  public static String getSdcardPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }

  public static long getSdcardTotalSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount() / 1024L;
  }

  public static float getSystemMetricsX()
  {
    if (b.a() == null)
      return 0.0F;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    b.a().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }

  public static float getSystemMetricsY()
  {
    if (b.a() == null)
      return 0.0F;
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    b.a().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }

  public static long getTotalMemory()
  {
    long l = 0L;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      String str = localBufferedReader.readLine();
      if (str != null)
        l = Integer.valueOf(str.split("\\s+")[1]).intValue();
      localBufferedReader.close();
      return l;
    }
    catch (IOException localIOException)
    {
    }
    return l;
  }

  public static long getTotalSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getRootDirectory().getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount() / 1024L;
  }

  public static ScanResult[] getWifiHotpot()
  {
    List localList = ((WifiManager)c.a().getSystemService("wifi")).getScanResults();
    return (ScanResult[])(ScanResult[])localList.toArray(new ScanResult[localList.size()]);
  }

  public static boolean isWifiConnected()
  {
    return ((ConnectivityManager)c.a().getSystemService("connectivity")).getNetworkInfo(1).isConnected();
  }

  public static void makeCall(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + paramString));
    c.a().startActivity(localIntent);
  }

  public static native void onNetworkStateChanged();

  public static void openUrl(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    c.a().startActivity(localIntent);
  }

  public static int sendMMS(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (!PhoneNumberUtils.isWellFormedSmsAddress(paramString1))
      return 1;
    try
    {
      String str1 = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(paramString4)).toString());
      String str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str1);
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.putExtra("address", paramString1);
      localIntent.putExtra("subject", paramString2);
      localIntent.putExtra("sms_body", paramString3);
      localIntent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + paramString4));
      localIntent.setType(str2);
      c.a().startActivity(localIntent);
      return 0;
    }
    catch (Exception localException)
    {
    }
    return 2;
  }

  public static void sendSMS(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString1));
    localIntent.putExtra("sms_body", paramString2);
    c.a().startActivity(localIntent);
  }

  public static void setNetworkChangedCallback()
  {
    unsetNetworkChangedCallback();
    b = new a();
    IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    c.a().registerReceiver(b, localIntentFilter);
  }

  public static void setScreenAlwaysOn(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (a == null)
        a = ((PowerManager)c.a().getSystemService("power")).newWakeLock(10, "VDeviceAPI");
      a.acquire();
    }
    do
      return;
    while ((a == null) || (!a.isHeld()));
    a.release();
    a = null;
  }

  public static void setupSoftware(String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    c.a().startActivity(localIntent);
  }

  public static void unsetNetworkChangedCallback()
  {
    if (b != null)
    {
      c.a().unregisterReceiver(b);
      b = null;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.vi.VDeviceAPI
 * JD-Core Version:    0.6.0
 */