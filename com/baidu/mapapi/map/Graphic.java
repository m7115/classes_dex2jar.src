package com.baidu.mapapi.map;

public class Graphic
{
  private Geometry a = null;
  private Symbol b = null;
  private long c = 0L;

  public Graphic(Geometry paramGeometry, Symbol paramSymbol)
  {
    this.a = paramGeometry;
    this.b = paramSymbol;
  }

  void a(long paramLong)
  {
    this.c = paramLong;
  }

  public Geometry getGeometry()
  {
    return this.a;
  }

  public long getID()
  {
    return this.c;
  }

  public Symbol getSymbol()
  {
    return this.b;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Graphic
 * JD-Core Version:    0.6.0
 */