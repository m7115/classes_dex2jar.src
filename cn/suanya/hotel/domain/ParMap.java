package cn.suanya.hotel.domain;

import java.util.Date;
import java.util.HashMap;

public class ParMap extends HashMap<String, Object>
{
  private static final long serialVersionUID = 1L;

  public Date getDate(String paramString)
  {
    return (Date)get(paramString);
  }

  public double getDouble(String paramString)
  {
    return ((Double)get(paramString)).doubleValue();
  }

  public int getInt(String paramString)
  {
    return ((Integer)get(paramString)).intValue();
  }

  public String getString(String paramString)
  {
    return (String)get(paramString);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.ParMap
 * JD-Core Version:    0.6.0
 */