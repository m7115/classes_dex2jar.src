package com.example.pathview.bean;

public class Point
{
  private double x;
  private double y;

  public Point(double paramDouble1, double paramDouble2)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
  }

  public double getX()
  {
    return this.x;
  }

  public double getY()
  {
    return this.y;
  }

  public void setX(double paramDouble)
  {
    this.x = paramDouble;
  }

  public void setY(double paramDouble)
  {
    this.y = paramDouble;
  }

  public String toString()
  {
    return "x=" + this.x + ", y=" + this.y;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.bean.Point
 * JD-Core Version:    0.6.0
 */