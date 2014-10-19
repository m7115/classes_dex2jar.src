package cn.suanya.hotel.domain;

public class Location
{
  private String city;
  private double latitude;
  private double longitude;

  public Location(double paramDouble1, double paramDouble2)
  {
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
  }

  public Location(String paramString, double paramDouble1, double paramDouble2)
  {
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.city = paramString;
  }

  public String getCity()
  {
    return this.city;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public void setCity(String paramString)
  {
    this.city = paramString;
  }

  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.Location
 * JD-Core Version:    0.6.0
 */