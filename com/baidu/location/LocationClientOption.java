package com.baidu.location;

public final class LocationClientOption
{
  public static final int GpsFirst = 1;
  public static final int MIN_SCAN_SPAN = 1000;
  public static final int NetWorkFirst = 2;
  protected int a = 3;
  protected String jdField_byte = "com.baidu.location.service_v2.9";
  protected boolean jdField_case = false;
  protected String jdField_char = "detail";
  protected float jdField_do = 500.0F;
  protected boolean jdField_else = false;
  protected boolean jdField_for = true;
  protected int jdField_goto = 1;
  protected boolean jdField_if = false;
  protected int jdField_int = 0;
  protected int jdField_long = 12000;
  protected String jdField_new = "SDK2.0";
  protected String jdField_try = "gcj02";
  protected boolean jdField_void = false;

  public LocationClientOption()
  {
  }

  public LocationClientOption(LocationClientOption paramLocationClientOption)
  {
    this.jdField_try = paramLocationClientOption.jdField_try;
    this.jdField_char = paramLocationClientOption.jdField_char;
    this.jdField_case = paramLocationClientOption.jdField_case;
    this.jdField_int = paramLocationClientOption.jdField_int;
    this.jdField_long = paramLocationClientOption.jdField_long;
    this.jdField_new = paramLocationClientOption.jdField_new;
    this.jdField_goto = paramLocationClientOption.jdField_goto;
    this.jdField_void = paramLocationClientOption.jdField_void;
    this.jdField_if = paramLocationClientOption.jdField_if;
    this.jdField_do = paramLocationClientOption.jdField_do;
    this.a = paramLocationClientOption.a;
    this.jdField_byte = paramLocationClientOption.jdField_byte;
    this.jdField_for = paramLocationClientOption.jdField_for;
  }

  public void disableCache(boolean paramBoolean)
  {
    this.jdField_for = paramBoolean;
  }

  public boolean equals(LocationClientOption paramLocationClientOption)
  {
    return (this.jdField_try.equals(paramLocationClientOption.jdField_try)) && (this.jdField_char.equals(paramLocationClientOption.jdField_char)) && (this.jdField_case == paramLocationClientOption.jdField_case) && (this.jdField_int == paramLocationClientOption.jdField_int) && (this.jdField_long == paramLocationClientOption.jdField_long) && (this.jdField_new.equals(paramLocationClientOption.jdField_new)) && (this.jdField_void == paramLocationClientOption.jdField_void) && (this.jdField_goto == paramLocationClientOption.jdField_goto) && (this.a == paramLocationClientOption.a) && (this.jdField_if == paramLocationClientOption.jdField_if) && (this.jdField_do == paramLocationClientOption.jdField_do) && (this.jdField_for == paramLocationClientOption.jdField_for);
  }

  public String getAddrType()
  {
    return this.jdField_char;
  }

  public String getCoorType()
  {
    return this.jdField_try;
  }

  public float getPoiDistance()
  {
    return this.jdField_do;
  }

  public boolean getPoiExtranInfo()
  {
    return this.jdField_if;
  }

  public int getPoiNumber()
  {
    return this.a;
  }

  public int getPriority()
  {
    return this.jdField_goto;
  }

  public String getProdName()
  {
    return this.jdField_new;
  }

  public int getScanSpan()
  {
    return this.jdField_int;
  }

  public String getServiceName()
  {
    return this.jdField_byte;
  }

  public int getTimeOut()
  {
    return this.jdField_long;
  }

  public boolean isDisableCache()
  {
    return this.jdField_for;
  }

  public boolean isLocationNotify()
  {
    return this.jdField_void;
  }

  public boolean isOpenGps()
  {
    return this.jdField_case;
  }

  public void setAddrType(String paramString)
  {
    if (paramString.length() > 32)
      paramString = paramString.substring(0, 32);
    this.jdField_char = paramString;
  }

  public void setCoorType(String paramString)
  {
    String str = paramString.toLowerCase();
    if ((str.equals("gcj02")) || (str.equals("bd09")) || (str.equals("bd09ll")))
      this.jdField_try = str;
  }

  public void setLocationNotify(boolean paramBoolean)
  {
    this.jdField_void = paramBoolean;
  }

  public void setOpenGps(boolean paramBoolean)
  {
    this.jdField_case = paramBoolean;
  }

  public void setPoiDistance(float paramFloat)
  {
    this.jdField_do = paramFloat;
  }

  public void setPoiExtraInfo(boolean paramBoolean)
  {
    this.jdField_if = paramBoolean;
  }

  public void setPoiNumber(int paramInt)
  {
    if (paramInt > 10)
      paramInt = 10;
    this.a = paramInt;
  }

  public void setPriority(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 2))
      this.jdField_goto = paramInt;
  }

  public void setProdName(String paramString)
  {
    if (paramString.length() > 64)
      paramString = paramString.substring(0, 64);
    this.jdField_new = paramString;
  }

  public void setScanSpan(int paramInt)
  {
    this.jdField_int = paramInt;
  }

  public void setServiceName(String paramString)
  {
    this.jdField_byte = paramString;
  }

  public void setTimeOut(int paramInt)
  {
    this.jdField_long = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.LocationClientOption
 * JD-Core Version:    0.6.0
 */