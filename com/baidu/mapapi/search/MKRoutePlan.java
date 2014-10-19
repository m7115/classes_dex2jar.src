package com.baidu.mapapi.search;

import java.util.ArrayList;

public class MKRoutePlan
{
  private int a;
  private int b;
  private ArrayList<MKRoute> c;

  void a(int paramInt)
  {
    this.a = paramInt;
  }

  void a(ArrayList<MKRoute> paramArrayList)
  {
    this.c = paramArrayList;
  }

  void b(int paramInt)
  {
    this.b = paramInt;
  }

  public int getDistance()
  {
    return this.a;
  }

  public int getNumRoutes()
  {
    if (this.c != null)
      return this.c.size();
    return 0;
  }

  public MKRoute getRoute(int paramInt)
  {
    if ((this.c == null) || (paramInt < 0) || (paramInt > -1 + this.c.size()))
      return null;
    return (MKRoute)this.c.get(paramInt);
  }

  public int getTime()
  {
    return this.b;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKRoutePlan
 * JD-Core Version:    0.6.0
 */