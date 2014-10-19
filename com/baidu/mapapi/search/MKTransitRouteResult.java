package com.baidu.mapapi.search;

import java.util.ArrayList;

public class MKTransitRouteResult
{
  private MKPlanNode a;
  private MKPlanNode b;
  private ArrayList<MKTransitRoutePlan> c;
  private MKRouteAddrResult d;
  private int e;

  void a(int paramInt)
  {
    this.e = paramInt;
  }

  void a(MKPlanNode paramMKPlanNode)
  {
    this.a = paramMKPlanNode;
  }

  void a(MKRouteAddrResult paramMKRouteAddrResult)
  {
    this.d = paramMKRouteAddrResult;
  }

  void a(ArrayList<MKTransitRoutePlan> paramArrayList)
  {
    this.c = paramArrayList;
  }

  void b(MKPlanNode paramMKPlanNode)
  {
    this.b = paramMKPlanNode;
  }

  public MKRouteAddrResult getAddrResult()
  {
    return this.d;
  }

  public MKPlanNode getEnd()
  {
    return this.b;
  }

  public int getNumPlan()
  {
    if (this.c != null)
      return this.c.size();
    return 0;
  }

  public MKTransitRoutePlan getPlan(int paramInt)
  {
    if ((this.c == null) || (paramInt < 0) || (paramInt > -1 + this.c.size()))
      return null;
    return (MKTransitRoutePlan)this.c.get(paramInt);
  }

  public MKPlanNode getStart()
  {
    return this.a;
  }

  public int getTaxiPrice()
  {
    return this.e;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKTransitRouteResult
 * JD-Core Version:    0.6.0
 */