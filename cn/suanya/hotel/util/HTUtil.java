package cn.suanya.hotel.util;

import java.text.DecimalFormat;

public class HTUtil
{
  static DecimalFormat df = new DecimalFormat("0.#");

  public static String formatDouble(double paramDouble)
  {
    return df.format(paramDouble);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.util.HTUtil
 * JD-Core Version:    0.6.0
 */