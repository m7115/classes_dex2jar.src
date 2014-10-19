package com.baidu.mapapi.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class i
{
  public static String[] a;
  public static String[] b;
  public static String[] c;
  public static int d;
  private static String e = "";
  private static ArrayList<String> f = new ArrayList();
  private static ArrayList<String> g = new ArrayList();

  static
  {
    d = 0;
  }

  private static String a(String paramString)
  {
    StatFs localStatFs = new StatFs(paramString);
    long l = localStatFs.getBlockSize() * localStatFs.getAvailableBlocks();
    DecimalFormat localDecimalFormat = new DecimalFormat();
    if (l < 1024L)
      return l + "B";
    if (l < 1048576L)
    {
      localDecimalFormat.applyPattern("0");
      double d3 = l / 1024.0D;
      return localDecimalFormat.format(d3) + "K";
    }
    if (l < 1073741824L)
    {
      localDecimalFormat.applyPattern("0.0");
      double d2 = l / 1048576.0D;
      return localDecimalFormat.format(d2) + "M";
    }
    localDecimalFormat.applyPattern("0.0");
    double d1 = l / 1073741824.0D;
    return localDecimalFormat.format(d1) + "G";
  }

  private static void a()
  {
    f.add(e);
    try
    {
      Scanner localScanner = new Scanner(new File("/proc/mounts"));
      while (localScanner.hasNext())
      {
        String str1 = localScanner.nextLine();
        if (!str1.startsWith("/dev/block/vold/"))
          continue;
        String str2 = str1.replace('\t', ' ').split(" ")[1];
        if (str2.equals(e))
          continue;
        f.add(str2);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void a(Context paramContext)
  {
    e = Environment.getExternalStorageDirectory().getAbsolutePath();
    int i = Build.VERSION.SDK_INT;
    boolean bool = false;
    if (i >= 14)
      bool = c(paramContext);
    if (!bool)
    {
      a();
      b();
      c();
      d();
      b(paramContext);
    }
  }

  private static void b()
  {
    g.add(e);
    File localFile = new File("/system/etc/vold.fstab");
    if (!localFile.exists());
    while (true)
    {
      return;
      try
      {
        Scanner localScanner = new Scanner(localFile);
        while (localScanner.hasNext())
        {
          String str1 = localScanner.nextLine();
          if (!str1.startsWith("dev_mount"))
            continue;
          String str2 = str1.replace('\t', ' ').split(" ")[2];
          if (str2.contains(":"))
            str2 = str2.substring(0, str2.indexOf(":"));
          if (str2.equals(e))
            continue;
          g.add(str2);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  private static void b(Context paramContext)
  {
    int i = 1;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (f.size() > 0)
    {
      if (Build.VERSION.SDK_INT < 9)
        localArrayList1.add("Auto");
      while (true)
      {
        localArrayList2.add(a((String)f.get(0)));
        if (f.size() <= i)
          break;
        while (i < f.size())
        {
          localArrayList1.add("外置存储卡");
          localArrayList2.add(a((String)f.get(i)));
          i++;
        }
        if (!Environment.isExternalStorageRemovable())
        {
          localArrayList1.add("内置存储卡");
          continue;
        }
        localArrayList1.add("外置存储卡");
      }
    }
    for (int j = 0; j < f.size(); j++)
    {
      if (c.a((String)f.get(j)))
        continue;
      localArrayList1.remove(j);
      localArrayList2.remove(j);
      ArrayList localArrayList3 = f;
      int k = j - 1;
      localArrayList3.remove(j);
      j = k;
    }
    a = new String[localArrayList1.size()];
    localArrayList1.toArray(a);
    b = new String[f.size()];
    f.toArray(b);
    c = new String[f.size()];
    localArrayList2.toArray(c);
    d = Math.min(a.length, b.length);
    f.clear();
  }

  private static void c()
  {
    for (int i = 0; i < f.size(); i++)
    {
      String str = (String)f.get(i);
      if (g.contains(str))
        continue;
      ArrayList localArrayList = f;
      int j = i - 1;
      localArrayList.remove(i);
      i = j;
    }
    g.clear();
  }

  private static boolean c(Context paramContext)
  {
    StorageManager localStorageManager = (StorageManager)paramContext.getSystemService("storage");
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    ArrayList localArrayList3;
    ArrayList localArrayList4;
    if (localStorageManager != null)
    {
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      localArrayList3 = new ArrayList();
      localArrayList4 = new ArrayList();
    }
    while (true)
    {
      int i;
      try
      {
        Class localClass = Class.forName("android.os.storage.StorageVolume");
        Method localMethod1 = localStorageManager.getClass().getMethod("getVolumeList", new Class[0]);
        Method localMethod2 = localStorageManager.getClass().getMethod("getVolumeState", new Class[] { String.class });
        Method localMethod3 = localClass.getMethod("isRemovable", new Class[0]);
        Method localMethod4 = localClass.getMethod("getPath", new Class[0]);
        Object[] arrayOfObject = (Object[])(Object[])localMethod1.invoke(localStorageManager, new Object[0]);
        i = 0;
        if (i >= arrayOfObject.length)
          continue;
        String str1 = (String)localMethod4.invoke(arrayOfObject[i], new Object[0]);
        boolean bool = ((Boolean)localMethod3.invoke(arrayOfObject[i], new Object[0])).booleanValue();
        if ((str1 != null) && (!str1.equals(e)))
        {
          String str2 = (String)localMethod2.invoke(localStorageManager, new Object[] { str1 });
          if ((str2 != null) && (str2.equals("mounted")))
          {
            if (!bool)
              continue;
            localArrayList2.add(str1);
            break label513;
            localArrayList1.add(str1);
          }
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localClassNotFoundException.printStackTrace();
        return false;
        int j = 0;
        if (j >= localArrayList1.size())
          break label519;
        f.add(localArrayList1.get(j));
        localArrayList3.add("内置存储卡");
        localArrayList4.add(a((String)localArrayList1.get(j)));
        j++;
        continue;
        if (k >= localArrayList2.size())
          continue;
        f.add(localArrayList2.get(k));
        localArrayList3.add("外置存储卡");
        localArrayList4.add(a((String)localArrayList2.get(k)));
        k++;
        continue;
        a = new String[localArrayList3.size()];
        localArrayList3.toArray(a);
        b = new String[f.size()];
        f.toArray(b);
        c = new String[f.size()];
        localArrayList4.toArray(c);
        d = Math.min(a.length, b.length);
        f.clear();
        return true;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException.printStackTrace();
        continue;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        continue;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        continue;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localInvocationTargetException.printStackTrace();
        continue;
      }
      label513: i++;
      continue;
      label519: int k = 0;
    }
  }

  private static void d()
  {
    for (int i = 0; i < f.size(); i++)
    {
      File localFile = new File((String)f.get(i));
      if ((localFile.exists()) && (localFile.isDirectory()) && (localFile.canWrite()))
        continue;
      ArrayList localArrayList = f;
      int j = i - 1;
      localArrayList.remove(i);
      i = j;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.i
 * JD-Core Version:    0.6.0
 */