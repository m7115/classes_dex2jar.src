package com.baidu.platform.comapi.basestruct;

public class GeoPoint
{
  private int a;
  private int b;

  public GeoPoint(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.b = paramInt2;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    do
      return false;
    while (paramObject.getClass() != getClass());
    if ((this.a == ((GeoPoint)paramObject).a) && (this.b == ((GeoPoint)paramObject).b));
    for (int i = 1; ; i = 0)
      return i;
  }

  public int getLatitudeE6()
  {
    return this.a;
  }

  public int getLongitudeE6()
  {
    return this.b;
  }

  public int hashCode()
  {
    return toString().hashCode();
  }

  public void setLatitudeE6(int paramInt)
  {
    this.a = paramInt;
  }

  public void setLongitudeE6(int paramInt)
  {
    this.b = paramInt;
  }

  public String toString()
  {
    return "GeoPoint: Latitude: " + this.a + ", Longitude: " + this.b;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.basestruct.GeoPoint
 * JD-Core Version:    0.6.0
 */