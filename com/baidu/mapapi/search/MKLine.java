package com.baidu.mapapi.search;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;

public class MKLine
{
  public static final int LINE_TYPE_BUS = 0;
  public static final int LINE_TYPE_SUBWAY = 1;
  ArrayList<GeoPoint> a;
  private int b;
  private int c;
  private int d;
  private int e;
  private String f;
  private String g;
  private String h;
  private MKPoiInfo i;
  private MKPoiInfo j;
  private ArrayList<GeoPoint> k;

  void a(int paramInt)
  {
    this.b = paramInt;
  }

  void a(MKPoiInfo paramMKPoiInfo)
  {
    this.i = paramMKPoiInfo;
  }

  void a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    char[] arrayOfChar = paramString.toCharArray();
    int m = 0;
    int n = 0;
    if (m < arrayOfChar.length)
    {
      if (arrayOfChar[m] == '<')
        n = 1;
      while (true)
      {
        m++;
        break;
        if (arrayOfChar[m] == '>')
        {
          n = 0;
          continue;
        }
        if (n != 0)
          continue;
        localStringBuilder.append(arrayOfChar[m]);
      }
    }
    this.h = localStringBuilder.toString();
  }

  void a(ArrayList<GeoPoint> paramArrayList)
  {
    this.k = paramArrayList;
  }

  void b(int paramInt)
  {
    this.c = paramInt;
  }

  void b(MKPoiInfo paramMKPoiInfo)
  {
    this.j = paramMKPoiInfo;
  }

  void b(String paramString)
  {
    this.f = paramString;
  }

  void c(int paramInt)
  {
    this.d = paramInt;
  }

  void d(int paramInt)
  {
    this.e = paramInt;
  }

  public int getDistance()
  {
    return this.c;
  }

  public MKPoiInfo getGetOffStop()
  {
    return this.j;
  }

  public MKPoiInfo getGetOnStop()
  {
    return this.i;
  }

  public int getNumViaStops()
  {
    return this.b;
  }

  public ArrayList<GeoPoint> getPoints()
  {
    return this.k;
  }

  public int getTime()
  {
    return this.d;
  }

  public String getTip()
  {
    return this.h;
  }

  public String getTitle()
  {
    return this.f;
  }

  public int getType()
  {
    return this.e;
  }

  public String getUid()
  {
    return this.g;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKLine
 * JD-Core Version:    0.6.0
 */