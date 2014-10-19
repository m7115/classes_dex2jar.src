package com.baidu.mapapi.search;

public class MKBusLineResult
{
  private String a;
  private String b;
  private int c;
  private String d;
  private String e;
  private MKRoute f = new MKRoute();

  void a(String paramString)
  {
    this.d = paramString;
  }

  void a(String paramString1, String paramString2, int paramInt)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramInt;
  }

  void b(String paramString)
  {
    this.e = paramString;
  }

  public String getBusCompany()
  {
    return this.a;
  }

  public String getBusName()
  {
    return this.b;
  }

  public MKRoute getBusRoute()
  {
    return this.f;
  }

  public String getEndTime()
  {
    return this.e;
  }

  public String getStartTime()
  {
    return this.d;
  }

  public MKStep getStation(int paramInt)
  {
    if ((this.f == null) || (this.f.getNumSteps() <= 0));
    do
      return null;
    while ((paramInt < 0) || (paramInt > -1 + this.f.getNumSteps()));
    return this.f.getStep(paramInt);
  }

  public int isMonTicket()
  {
    return this.c;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKBusLineResult
 * JD-Core Version:    0.6.0
 */