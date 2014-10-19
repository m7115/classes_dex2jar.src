package com.baidu.location;

import android.location.Location;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

class k
{
  private static Location a;
  private static int b = 0;
  private static int jdField_byte = 0;
  private static int c = 0;
  private static int jdField_case = 0;
  private static int jdField_char = 0;
  private static final int d = 1040;
  private static String jdField_do = "baidu_location_service";
  private static Location e;
  private static File jdField_else;
  private static final int f = 32;
  private static final int jdField_for = 2048;
  private static e.c g;
  private static final int jdField_goto = 128;
  private static ArrayList h = new ArrayList();
  private static int i = 0;
  private static final int jdField_if = 2048;
  private static double jdField_int = 0.0D;
  private static ArrayList j;
  private static final String k;
  private static int l = 0;
  private static ArrayList jdField_long = new ArrayList();
  private static ArrayList m = new ArrayList();
  private static double n = 0.0D;
  private static double jdField_new = 0.0D;
  private static int o = 0;
  private static final String p;
  private static Location q;
  private static ArrayList r;
  private static final int s = 2048;
  private static final String t;
  private static int jdField_try;
  private static final String u;
  private static int v;
  private static double jdField_void;
  private static int w;
  private static ArrayList x = new ArrayList();
  private static String y;

  static
  {
    j = new ArrayList();
    r = new ArrayList();
    y = f.ac + "/yo.dat";
    u = f.ac + "/yoh.dat";
    t = f.ac + "/yom.dat";
    k = f.ac + "/yol.dat";
    p = f.ac + "/yor.dat";
    jdField_else = null;
    jdField_case = 1024;
    w = 512;
    jdField_byte = 8;
    i = 5;
    b = 8;
    jdField_try = 16;
    o = 1024;
    l = 256;
    jdField_new = 0.0D;
    n = 0.1D;
    jdField_void = 30.0D;
    jdField_int = 100.0D;
    c = 0;
    jdField_char = 64;
    v = 128;
    e = null;
    q = null;
    a = null;
    g = null;
  }

  private static int a(List paramList, int paramInt)
  {
    if ((paramList == null) || (paramInt > 256) || (paramInt < 0))
      return -1;
    while (true)
    {
      int i6;
      int i7;
      try
      {
        if (jdField_else != null)
          continue;
        jdField_else = new File(y);
        if (jdField_else.exists())
          continue;
        j.jdField_if(jdField_do, "upload man readfile does not exist...");
        jdField_else = null;
        return -2;
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(jdField_else, "rw");
        if (localRandomAccessFile.length() >= 1L)
          continue;
        localRandomAccessFile.close();
        return -3;
        localRandomAccessFile.seek(paramInt);
        int i1 = localRandomAccessFile.readInt();
        int i2 = localRandomAccessFile.readInt();
        int i3 = localRandomAccessFile.readInt();
        int i4 = localRandomAccessFile.readInt();
        long l1 = localRandomAccessFile.readLong();
        if ((a(i1, i2, i3, i4, l1)) && (i2 >= 1))
          continue;
        localRandomAccessFile.close();
        return -4;
        byte[] arrayOfByte = new byte[o];
        int i5 = jdField_byte;
        i6 = i2;
        i7 = i5;
        if ((i7 <= 0) || (i6 <= 0))
          continue;
        localRandomAccessFile.seek(l1 + i4 * ((-1 + (i1 + i6)) % i3));
        int i9 = localRandomAccessFile.readInt();
        if ((i9 > 0) && (i9 < i4))
        {
          localRandomAccessFile.read(arrayOfByte, 0, i9);
          if (arrayOfByte[(i9 - 1)] == 0)
          {
            paramList.add(new String(arrayOfByte, 0, i9 - 1));
            break label322;
            localRandomAccessFile.seek(paramInt);
            localRandomAccessFile.writeInt(i1);
            localRandomAccessFile.writeInt(i6);
            localRandomAccessFile.writeInt(i3);
            localRandomAccessFile.writeInt(i4);
            localRandomAccessFile.writeLong(l1);
            localRandomAccessFile.close();
            int i8 = jdField_byte;
            return i8 - i7;
          }
        }
      }
      catch (Exception localException)
      {
        return -5;
      }
      label322: i7--;
      i6--;
    }
  }

  public static String a(int paramInt)
  {
    String str1;
    ArrayList localArrayList;
    if (paramInt == 1)
    {
      str1 = u;
      localArrayList = x;
      if (localArrayList != null)
        break label67;
    }
    label67: 
    do
    {
      do
      {
        return null;
        if (paramInt == 2)
        {
          str1 = t;
          localArrayList = j;
          break;
        }
        if (paramInt != 3)
          continue;
        str1 = k;
        localArrayList = r;
        break;
      }
      while (paramInt != 4);
      str1 = p;
      localArrayList = r;
      break;
      if (localArrayList.size() >= 1)
        continue;
      j.jdField_if(jdField_do, str1 + ":get data from sd file...");
      a(str1, localArrayList);
    }
    while (localArrayList.size() <= 0);
    String str2 = (String)localArrayList.get(0);
    localArrayList.remove(0);
    return str2;
  }

  public static void a()
  {
    b = 0;
    j.jdField_if(jdField_do, "flush data...");
    a(1, false);
    a(2, false);
    a(3, false);
    b = 8;
  }

  public static void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    if (paramDouble1 > 0.0D)
    {
      jdField_new = paramDouble1;
      n = paramDouble2;
      if (paramDouble3 <= 20.0D)
        break label41;
    }
    while (true)
    {
      jdField_void = paramDouble3;
      jdField_int = paramDouble4;
      return;
      paramDouble1 = jdField_new;
      break;
      label41: paramDouble3 = jdField_void;
    }
  }

  public static void a(int paramInt1, int paramInt2)
  {
  }

  public static void a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
  }

  public static void a(int paramInt, boolean paramBoolean)
  {
    Object localObject1;
    Object localObject2;
    label28: File localFile;
    if (paramInt == 1)
    {
      String str4 = u;
      if (paramBoolean)
        return;
      ArrayList localArrayList6 = x;
      localObject1 = str4;
      localObject2 = localArrayList6;
      localFile = new File((String)localObject1);
      if (!localFile.exists())
        a((String)localObject1);
    }
    while (true)
    {
      int i3;
      try
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
        localRandomAccessFile.seek(4L);
        int i1 = localRandomAccessFile.readInt();
        int i2 = localRandomAccessFile.readInt();
        i3 = localRandomAccessFile.readInt();
        int i4 = localRandomAccessFile.readInt();
        int i5 = localRandomAccessFile.readInt();
        int i6 = ((List)localObject2).size();
        i7 = i5;
        if (i6 <= b)
          break label550;
        j.jdField_if(jdField_do, "write new data...");
        if (paramBoolean)
        {
          i9 = i7 + 1;
          if (i3 >= i1)
            continue;
          localRandomAccessFile.seek(128 + i2 * i3);
          byte[] arrayOfByte1 = ((String)((List)localObject2).get(0) + '\000').getBytes();
          localRandomAccessFile.writeInt(arrayOfByte1.length);
          localRandomAccessFile.write(arrayOfByte1, 0, arrayOfByte1.length);
          ((List)localObject2).remove(0);
          int i10 = i3 + 1;
          int i11 = i4;
          i12 = i10;
          int i13 = i11;
          i6--;
          i3 = i12;
          i4 = i13;
          i7 = i9;
          continue;
          if (paramInt != 2)
            continue;
          String str3 = t;
          if (!paramBoolean)
            continue;
          ArrayList localArrayList5 = x;
          localObject1 = str3;
          localObject2 = localArrayList5;
          break label28;
          ArrayList localArrayList4 = j;
          localObject1 = str3;
          localObject2 = localArrayList4;
          break label28;
          if (paramInt != 3)
            continue;
          String str2 = k;
          if (!paramBoolean)
            continue;
          ArrayList localArrayList3 = j;
          localObject1 = str2;
          localObject2 = localArrayList3;
          break label28;
          ArrayList localArrayList2 = r;
          localObject1 = str2;
          localObject2 = localArrayList2;
          break label28;
          if (paramInt != 4)
            break;
          String str1 = p;
          if (!paramBoolean)
            break;
          ArrayList localArrayList1 = r;
          localObject1 = str1;
          localObject2 = localArrayList1;
          break label28;
          if (!paramBoolean)
            break label563;
          long l1 = 128 + i4 * i2;
          localRandomAccessFile.seek(l1);
          byte[] arrayOfByte2 = ((String)((List)localObject2).get(0) + '\000').getBytes();
          localRandomAccessFile.writeInt(arrayOfByte2.length);
          localRandomAccessFile.write(arrayOfByte2, 0, arrayOfByte2.length);
          ((List)localObject2).remove(0);
          i13 = i4 + 1;
          if (i13 <= i3)
            break label556;
          i13 = 0;
          break label556;
          localRandomAccessFile.seek(12L);
          localRandomAccessFile.writeInt(i3);
          localRandomAccessFile.writeInt(i4);
          localRandomAccessFile.writeInt(i7);
          localRandomAccessFile.close();
          if ((i8 == 0) || (paramInt >= 4))
            break;
          a(paramInt + 1, true);
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      int i9 = i7;
      continue;
      label550: int i8 = 0;
      continue;
      label556: int i12 = i3;
      continue;
      label563: i8 = 1;
      int i7 = i9;
    }
  }

  public static void a(c.a parama, e.c paramc, Location paramLocation, String paramString)
  {
    if (!j.O);
    while (true)
    {
      break label6;
      break label6;
      break label6;
      break label6;
      label6: 
      do
        return;
      while ((j.I == 3) && (!a(paramLocation, paramc)) && (!a(paramLocation, false)));
      if ((parama == null) || (!parama.jdField_do()))
        break;
      if (!a(paramLocation, paramc))
        paramc = null;
      String str4 = j.a(parama, paramc, paramLocation, paramString, 1);
      if (str4 == null)
        continue;
      jdField_for(Jni.jdField_if(str4));
      q = paramLocation;
      e = paramLocation;
      if (paramc == null)
        continue;
      g = paramc;
      return;
    }
    c.a locala;
    if ((paramc != null) && (paramc.jdField_if()) && (a(paramLocation, paramc)))
    {
      boolean bool2 = a(paramLocation);
      locala = null;
      if (bool2)
        break label277;
    }
    while (true)
    {
      String str2 = j.a(locala, paramc, paramLocation, paramString, 2);
      if (str2 == null)
        break;
      String str3 = Jni.jdField_if(str2);
      j.jdField_if(jdField_do, "upload size:" + str3.length());
      jdField_do(str3);
      a = paramLocation;
      e = paramLocation;
      if (paramc == null)
        break;
      g = paramc;
      return;
      if (!a(paramLocation))
        parama = null;
      boolean bool1 = a(paramLocation, paramc);
      e.c localc = null;
      if (!bool1);
      while (true)
      {
        if ((parama == null) && (localc == null))
          break label275;
        String str1 = j.a(parama, localc, paramLocation, paramString, 3);
        if (str1 == null)
          break;
        jdField_int(Jni.jdField_if(str1));
        e = paramLocation;
        if (localc == null)
          break;
        g = localc;
        return;
        localc = paramc;
      }
      label275: break label6;
      label277: locala = parama;
    }
  }

  public static void a(String paramString)
  {
    try
    {
      File localFile1 = new File(paramString);
      if (!localFile1.exists())
      {
        File localFile2 = new File(f.ac);
        if (!localFile2.exists())
          localFile2.mkdirs();
        if (!localFile1.createNewFile())
          localFile1 = null;
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile1, "rw");
        localRandomAccessFile.seek(0L);
        localRandomAccessFile.writeInt(32);
        localRandomAccessFile.writeInt(2048);
        localRandomAccessFile.writeInt(1040);
        localRandomAccessFile.writeInt(0);
        localRandomAccessFile.writeInt(0);
        localRandomAccessFile.writeInt(0);
        localRandomAccessFile.close();
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static void a(String paramString, int paramInt)
  {
  }

  public static void a(String paramString, int paramInt, boolean paramBoolean)
  {
  }

  private static boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong)
  {
    if ((paramInt1 < 0) || (paramInt1 >= paramInt3));
    do
      return false;
    while ((paramInt2 < 0) || (paramInt2 > paramInt3) || (paramInt3 < 0) || (paramInt3 > 1024) || (paramInt4 < 128) || (paramInt4 > 1024));
    return true;
  }

  private static boolean a(Location paramLocation)
  {
    int i1 = 1;
    if (paramLocation == null)
      i1 = 0;
    double d2;
    do
    {
      return i1;
      if ((q == null) || (e == null))
      {
        q = paramLocation;
        return i1;
      }
      double d1 = paramLocation.distanceTo(q);
      d2 = d1 * (d1 * j.u) + d1 * j.s + j.r;
    }
    while (paramLocation.distanceTo(e) > d2);
    return false;
  }

  private static boolean a(Location paramLocation, e.c paramc)
  {
    if ((paramLocation == null) || (paramc == null) || (paramc.jdField_for == null) || (paramc.jdField_for.isEmpty()));
    do
      return false;
    while (paramc.jdField_do(g));
    if (a == null)
    {
      a = paramLocation;
      return true;
    }
    return true;
  }

  public static boolean a(Location paramLocation, boolean paramBoolean)
  {
    return b.a(e, paramLocation, paramBoolean);
  }

  public static boolean a(String paramString, List paramList)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      return false;
    while (true)
    {
      int i6;
      int i7;
      try
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
        localRandomAccessFile.seek(8L);
        int i1 = localRandomAccessFile.readInt();
        int i2 = localRandomAccessFile.readInt();
        int i3 = localRandomAccessFile.readInt();
        byte[] arrayOfByte = new byte[o];
        int i4 = b;
        int i5 = i4 + 1;
        i6 = i2;
        i7 = i5;
        int i8 = 0;
        if ((i7 <= 0) || (i6 <= 0))
          continue;
        if (i6 >= i3)
          continue;
        i3 = 0;
        long l1 = 128 + i1 * (i6 - 1);
        try
        {
          localRandomAccessFile.seek(l1);
          int i9 = localRandomAccessFile.readInt();
          if ((i9 > 0) && (i9 < i1))
          {
            localRandomAccessFile.read(arrayOfByte, 0, i9);
            if (arrayOfByte[(i9 - 1)] == 0)
            {
              paramList.add(new String(arrayOfByte, 0, i9 - 1));
              i8 = 1;
              break label223;
              localRandomAccessFile.seek(12L);
              localRandomAccessFile.writeInt(i6);
              localRandomAccessFile.writeInt(i3);
              localRandomAccessFile.close();
              return i8;
            }
          }
        }
        catch (Exception localException2)
        {
          return i8;
        }
      }
      catch (Exception localException1)
      {
        return false;
      }
      label223: i7--;
      i6--;
    }
  }

  public static String jdField_do()
  {
    return jdField_int();
  }

  private static void jdField_do(String paramString)
  {
    jdField_if(paramString);
  }

  public static void jdField_for()
  {
  }

  private static void jdField_for(String paramString)
  {
    jdField_if(paramString);
  }

  public static String jdField_if()
  {
    File localFile1 = new File(t);
    boolean bool = localFile1.exists();
    String str = null;
    if (bool);
    while (true)
    {
      try
      {
        RandomAccessFile localRandomAccessFile3 = new RandomAccessFile(localFile1, "rw");
        localRandomAccessFile3.seek(20L);
        int i3 = localRandomAccessFile3.readInt();
        str = null;
        if (i3 > 128)
        {
          str = "&p1=" + i3;
          localRandomAccessFile3.seek(20L);
          localRandomAccessFile3.writeInt(0);
          localRandomAccessFile3.close();
          return str;
        }
      }
      catch (Exception localException3)
      {
      }
      File localFile2 = new File(k);
      if (localFile2.exists())
        try
        {
          RandomAccessFile localRandomAccessFile2 = new RandomAccessFile(localFile2, "rw");
          localRandomAccessFile2.seek(20L);
          int i2 = localRandomAccessFile2.readInt();
          if (i2 > 256)
          {
            str = "&p2=" + i2;
            localRandomAccessFile2.seek(20L);
            localRandomAccessFile2.writeInt(0);
            localRandomAccessFile2.close();
            return str;
          }
        }
        catch (Exception localException2)
        {
        }
      File localFile3 = new File(p);
      if (!localFile3.exists())
        continue;
      try
      {
        RandomAccessFile localRandomAccessFile1 = new RandomAccessFile(localFile3, "rw");
        localRandomAccessFile1.seek(20L);
        int i1 = localRandomAccessFile1.readInt();
        if (i1 <= 512)
          continue;
        str = "&p3=" + i1;
        localRandomAccessFile1.seek(20L);
        localRandomAccessFile1.writeInt(0);
        localRandomAccessFile1.close();
        return str;
      }
      catch (Exception localException1)
      {
      }
    }
    return str;
  }

  public static void jdField_if(String paramString)
  {
    int i1 = j.l;
    ArrayList localArrayList;
    if (i1 == 1)
    {
      localArrayList = x;
      if (localArrayList != null)
        break label42;
    }
    while (true)
    {
      return;
      if (i1 == 2)
      {
        localArrayList = j;
        break;
      }
      if (i1 != 3)
        continue;
      localArrayList = r;
      break;
      label42: j.jdField_if(jdField_do, "insert2HighPriorityQueue...");
      if (localArrayList.size() <= jdField_try)
        localArrayList.add(paramString);
      if (localArrayList.size() >= jdField_try)
        a(i1, false);
      while (localArrayList.size() > jdField_try)
        localArrayList.remove(0);
    }
  }

  public static String jdField_int()
  {
    String str1 = null;
    int i1 = 1;
    if (i1 < 5)
    {
      str1 = a(i1);
      if (str1 == null);
    }
    do
    {
      do
      {
        do
        {
          return str1;
          i1++;
          break;
          j.jdField_if(jdField_do, "read the old file data...");
          a(r, jdField_char);
          if (r.size() <= 0)
            continue;
          str1 = (String)r.get(0);
          r.remove(0);
        }
        while (str1 != null);
        a(r, c);
        if (r.size() <= 0)
          continue;
        str1 = (String)r.get(0);
        r.remove(0);
      }
      while (str1 != null);
      a(r, v);
    }
    while (r.size() <= 0);
    String str2 = (String)r.get(0);
    r.remove(0);
    return str2;
  }

  private static void jdField_int(String paramString)
  {
    jdField_if(paramString);
  }

  public static void jdField_new()
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.k
 * JD-Core Version:    0.6.0
 */