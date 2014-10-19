package cn.suanya.hotel.domain;

import cn.suanya.common.a.r;
import java.io.Serializable;

public class District extends r
  implements Serializable
{
  private static final long serialVersionUID = -9000611944501881954L;
  private double latitude;
  private double longitude;

  public District()
  {
  }

  public District(String paramString1, String paramString2, double paramDouble1, double paramDouble2)
  {
    super(paramString1, paramString2);
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public boolean isNothing()
  {
    return (this.latitude == 0.0D) && (this.longitude == 0.0D);
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
 * Qualified Name:     cn.suanya.hotel.domain.District
 * JD-Core Version:    0.6.0
 */