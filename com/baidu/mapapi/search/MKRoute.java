package com.baidu.mapapi.search;

import com.baidu.mapapi.utils.DistanceUtil;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.Iterator;

public class MKRoute
{
  public static final int ROUTE_TYPE_BUS_LINE = 3;
  public static final int ROUTE_TYPE_DRIVING = 1;
  public static final int ROUTE_TYPE_UNKNOW = 0;
  public static final int ROUTE_TYPE_WALKING = 2;
  ArrayList<ArrayList<GeoPoint>> a;
  private int b;
  private int c;
  private int d;
  private int e;
  private GeoPoint f;
  private GeoPoint g;
  private ArrayList<ArrayList<GeoPoint>> h;
  private ArrayList<MKStep> i;
  private String j;

  void a(int paramInt)
  {
    this.c = paramInt;
  }

  void a(GeoPoint paramGeoPoint)
  {
    this.f = paramGeoPoint;
  }

  void a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    char[] arrayOfChar = paramString.toCharArray();
    int k = 0;
    int m = 0;
    if (k < arrayOfChar.length)
    {
      if (arrayOfChar[k] == '<')
        m = 1;
      while (true)
      {
        k++;
        break;
        if (arrayOfChar[k] == '>')
        {
          m = 0;
          continue;
        }
        if (m != 0)
          continue;
        localStringBuilder.append(arrayOfChar[k]);
      }
    }
    this.j = localStringBuilder.toString();
  }

  void a(ArrayList<MKStep> paramArrayList)
  {
    this.i = paramArrayList;
  }

  void b(int paramInt)
  {
    this.b = paramInt;
  }

  void b(GeoPoint paramGeoPoint)
  {
    this.g = paramGeoPoint;
  }

  void b(ArrayList<ArrayList<GeoPoint>> paramArrayList)
  {
    this.h = paramArrayList;
  }

  void c(int paramInt)
  {
    this.e = paramInt;
  }

  public void customizeRoute(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2, GeoPoint[] paramArrayOfGeoPoint)
  {
    if ((paramGeoPoint1 == null) || (paramGeoPoint2 == null) || (paramArrayOfGeoPoint == null))
      return;
    ((GeoPoint[][])null);
    customizeRoute(paramGeoPoint1, paramGeoPoint2, new GeoPoint[][] { paramArrayOfGeoPoint });
  }

  public void customizeRoute(GeoPoint paramGeoPoint1, GeoPoint paramGeoPoint2, GeoPoint[][] paramArrayOfGeoPoint)
  {
    if ((paramGeoPoint1 == null) || (paramGeoPoint2 == null) || (paramArrayOfGeoPoint == null));
    double d1;
    Object localObject1;
    do
    {
      return;
      if (paramGeoPoint1 != null)
        this.f = paramGeoPoint1;
      if (paramGeoPoint2 != null)
        this.g = paramGeoPoint2;
      this.e = 3;
      d1 = 0.0D;
      localObject1 = null;
    }
    while ((paramArrayOfGeoPoint == null) || (paramArrayOfGeoPoint.length <= 0));
    this.h = new ArrayList();
    int k = 0;
    if (k < paramArrayOfGeoPoint.length)
    {
      GeoPoint[] arrayOfGeoPoint = paramArrayOfGeoPoint[k];
      if (arrayOfGeoPoint == null);
      while (true)
      {
        k++;
        break;
        ArrayList localArrayList3 = new ArrayList();
        int i1 = 0;
        if (i1 < arrayOfGeoPoint.length)
        {
          if (arrayOfGeoPoint[i1] == null);
          while (true)
          {
            i1++;
            break;
            localArrayList3.add(arrayOfGeoPoint[i1]);
          }
        }
        this.h.add(localArrayList3);
      }
    }
    this.a = new ArrayList();
    this.i = new ArrayList();
    int m = 0;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    MKStep localMKStep1;
    int n;
    double d2;
    Object localObject3;
    label234: double d3;
    if (m < this.h.size())
    {
      localArrayList1 = (ArrayList)this.h.get(m);
      localArrayList2 = new ArrayList();
      localMKStep1 = new MKStep();
      Object localObject2 = localObject1;
      n = 0;
      d2 = d1;
      localObject3 = localObject2;
      if (n < localArrayList1.size())
      {
        if ((m == 0) && (n == 0) && (localArrayList1.size() > 1))
        {
          MKStep localMKStep2 = new MKStep();
          localMKStep2.a((GeoPoint)localArrayList1.get(n));
          localMKStep2.a(String.valueOf(this.i.size()));
          this.i.add(localMKStep2);
        }
        localArrayList2.add(e.b((GeoPoint)localArrayList1.get(n)));
        if (localObject3 == null)
          break label466;
        d3 = d2 + DistanceUtil.getDistance((GeoPoint)localArrayList1.get(n), localObject3);
      }
    }
    while (true)
    {
      if (n == -1 + localArrayList1.size())
      {
        localMKStep1.a((GeoPoint)localArrayList1.get(n));
        localMKStep1.a(String.valueOf(this.i.size()));
      }
      GeoPoint localGeoPoint = (GeoPoint)localArrayList1.get(n);
      n++;
      d2 = d3;
      localObject3 = localGeoPoint;
      break label234;
      this.a.add(localArrayList2);
      this.i.add(localMKStep1);
      m++;
      localObject1 = localObject3;
      d1 = d2;
      break;
      this.c = (int)d1;
      return;
      label466: d3 = d2;
    }
  }

  void d(int paramInt)
  {
    this.d = paramInt;
  }

  public ArrayList<ArrayList<GeoPoint>> getArrayPoints()
  {
    if ((this.h.size() == 0) && (this.e == 1))
    {
      Iterator localIterator = this.i.iterator();
      while (localIterator.hasNext())
        c.a(((MKStep)localIterator.next()).b(), this.h, this.a);
    }
    return this.h;
  }

  public int getDistance()
  {
    return this.c;
  }

  public GeoPoint getEnd()
  {
    return this.g;
  }

  public int getIndex()
  {
    return this.b;
  }

  public int getNumSteps()
  {
    if (this.i != null)
      return this.i.size();
    return 0;
  }

  public int getRouteType()
  {
    return this.e;
  }

  public GeoPoint getStart()
  {
    return this.f;
  }

  public MKStep getStep(int paramInt)
  {
    if ((this.i == null) || (paramInt < 0) || (paramInt > -1 + this.i.size()))
      return null;
    return (MKStep)this.i.get(paramInt);
  }

  public int getTime()
  {
    return this.d;
  }

  public String getTip()
  {
    return this.j;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKRoute
 * JD-Core Version:    0.6.0
 */