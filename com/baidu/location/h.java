package com.baidu.location;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

class h
{
  private static int a;
  private static String jdField_do;
  private static float jdField_for;
  private static String jdField_if = "baidu_location_service";
  private static ArrayList jdField_int;
  private static int jdField_new;
  private static long jdField_try;

  static
  {
    a = 100;
    jdField_try = 64L;
    jdField_new = 64;
    jdField_for = 299.0F;
    jdField_do = f.ac + "/juz.dat";
    jdField_int = null;
  }

  public static String a(int paramInt1, int paramInt2, int paramInt3)
  {
    a locala = jdField_if(paramInt1, paramInt2, paramInt3);
    if (locala != null)
    {
      String str = "{\"result\":{\"time\":\"" + j.jdField_for() + "\",\"error\":\"65\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\"}}";
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Double.valueOf(a.jdField_if(locala));
      arrayOfObject[1] = Double.valueOf(a.jdField_int(locala));
      arrayOfObject[2] = Integer.valueOf((int)a.a(locala));
      return String.format(str, arrayOfObject);
    }
    return null;
  }

  private static void a()
  {
    File localFile = new File(jdField_do);
    try
    {
      if (!localFile.exists())
      {
        j.jdField_if(jdField_if, "locCache file does not exists...");
        return;
      }
      if (jdField_int != null)
      {
        jdField_int.clear();
        jdField_int = null;
      }
      jdField_int = new ArrayList();
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
      localRandomAccessFile.seek(0L);
      int i = localRandomAccessFile.readInt();
      j.jdField_if(jdField_if, "size of loc cache is " + i);
      for (int j = 0; j < i; j++)
      {
        localRandomAccessFile.seek(jdField_try + j * jdField_new);
        float f = localRandomAccessFile.readFloat();
        int k = localRandomAccessFile.readInt();
        double d1 = localRandomAccessFile.readDouble();
        int m = localRandomAccessFile.readInt();
        double d2 = localRandomAccessFile.readDouble();
        a locala = new a(localRandomAccessFile.readInt(), localRandomAccessFile.readInt(), k, m, d1, d2, f);
        jdField_int.add(locala);
      }
      localRandomAccessFile.close();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static void a(c.a parama, double paramDouble1, double paramDouble2, float paramFloat)
  {
    if (parama == null)
      return;
    float f;
    if (paramFloat < jdField_for)
      f = jdField_for;
    a locala1;
    while (true)
    {
      locala1 = jdField_if(parama.jdField_if, parama.jdField_for, parama.jdField_try);
      if (locala1 != null)
        break;
      if (jdField_int == null)
        jdField_int = new ArrayList();
      a locala2 = new a(parama.jdField_for, parama.jdField_try, parama.jdField_do, parama.jdField_if, paramDouble1, paramDouble2, f);
      jdField_int.add(locala2);
      if (jdField_int.size() > a)
        jdField_int.remove(0);
      j.jdField_if(jdField_if, "locCache add new cell info into loc cache ...");
      return;
      f = paramFloat;
    }
    a.a(locala1, paramDouble1);
    a.jdField_if(locala1, paramDouble2);
    a.a(locala1, f);
    j.jdField_if(jdField_if, "locCache update loc cache ...");
  }

  private static void jdField_do()
  {
    if (jdField_int == null)
      return;
    File localFile1 = new File(jdField_do);
    while (true)
    {
      RandomAccessFile localRandomAccessFile;
      int i;
      int j;
      a locala;
      try
      {
        if (localFile1.exists())
          continue;
        File localFile2 = new File(f.ac);
        if (localFile2.exists())
          continue;
        boolean bool = localFile2.mkdirs();
        j.jdField_if(jdField_if, "locCache make dirs " + bool);
        if (!localFile1.createNewFile())
          continue;
        j.jdField_if(jdField_if, "locCache create loc cache file success ...");
        localRandomAccessFile = new RandomAccessFile(localFile1, "rw");
        if (localRandomAccessFile.length() >= 1L)
          continue;
        localRandomAccessFile.writeInt(0);
        i = -1 + jdField_int.size();
        j = 0;
        if (i < 0)
          break label262;
        locala = (a)jdField_int.get(i);
        if (locala == null)
        {
          break label278;
          j.jdField_if(jdField_if, "locCache create loc cache file failure ...");
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      localRandomAccessFile.seek(jdField_try + jdField_new * (i % a));
      localRandomAccessFile.writeFloat(a.a(locala));
      localRandomAccessFile.writeInt(a.jdField_for(locala));
      localRandomAccessFile.writeDouble(a.jdField_if(locala));
      localRandomAccessFile.writeInt(a.jdField_new(locala));
      localRandomAccessFile.writeDouble(a.jdField_int(locala));
      localRandomAccessFile.writeInt(a.jdField_do(locala));
      localRandomAccessFile.writeInt(a.jdField_try(locala));
      j.jdField_if(jdField_if, "add a new cell loc into file ...");
      break label278;
      label262: localRandomAccessFile.seek(0L);
      localRandomAccessFile.writeInt(j);
      localRandomAccessFile.close();
      return;
      label278: i--;
      j++;
    }
  }

  private static a jdField_if(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if ((jdField_int == null) || (jdField_int.size() < 1))
        a();
      if ((jdField_int == null) || (jdField_int.size() < 1))
        break label99;
      for (int i = -1 + jdField_int.size(); i >= 0; i--)
      {
        locala = (a)jdField_int.get(i);
        if (locala == null)
          continue;
        boolean bool = locala.a(paramInt1, paramInt2, paramInt3);
        if (bool)
          break label102;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
    label99: a locala = null;
    label102: return locala;
  }

  public static void jdField_if()
  {
    jdField_do();
  }

  private static class a
  {
    private int a = 0;
    private int jdField_do = 0;
    private float jdField_for = 0.0F;
    private int jdField_if = 0;
    private double jdField_int = 0.0D;
    private double jdField_new = 0.0D;
    private int jdField_try = 0;

    public a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble1, double paramDouble2, float paramFloat)
    {
      this.jdField_do = paramInt1;
      this.jdField_try = paramInt2;
      this.jdField_if = paramInt3;
      this.a = paramInt4;
      this.jdField_new = paramDouble1;
      this.jdField_int = paramDouble2;
      this.jdField_for = paramFloat;
    }

    public boolean a(int paramInt1, int paramInt2, int paramInt3)
    {
      return (this.a == paramInt1) && (this.jdField_do == paramInt2) && (this.jdField_try == paramInt3);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.h
 * JD-Core Version:    0.6.0
 */