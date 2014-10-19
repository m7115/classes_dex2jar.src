package com.baidu.mapapi.map;

public class Symbol
{
  int a = 0;
  Color b = null;
  int c = 0;

  public void setLineSymbol(Color paramColor, int paramInt)
  {
    this.b = paramColor;
    this.a = paramInt;
  }

  public void setPointSymbol(Color paramColor)
  {
    this.b = paramColor;
    this.c = 1;
  }

  public void setSurface(Color paramColor, int paramInt1, int paramInt2)
  {
    this.b = paramColor;
    this.a = paramInt2;
    this.c = paramInt1;
  }

  public class Color
  {
    public int alpha;
    public int blue;
    public int green;
    public int red;

    public Color()
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Symbol
 * JD-Core Version:    0.6.0
 */