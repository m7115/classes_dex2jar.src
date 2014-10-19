package com.baidu.location;

import org.json.JSONObject;

public final class BDLocation
{
  public static final int TypeCacheLocation = 65;
  public static final int TypeCriteriaException = 62;
  public static final int TypeGpsLocation = 61;
  public static final int TypeNetWorkException = 63;
  public static final int TypeNetWorkLocation = 161;
  public static final int TypeNone = 0;
  public static final int TypeOffLineLocation = 66;
  public static final int TypeOffLineLocationFail = 67;
  public static final int TypeOffLineLocationNetworkFail = 68;
  public static final int TypeServerError = 167;
  private String a = null;
  private boolean b = false;
  private String jdField_byte = null;
  private boolean c = false;
  private boolean jdField_case = false;
  private float jdField_char = -1.0F;
  private String d = null;
  private int jdField_do = -1;
  private boolean e = false;
  private double jdField_else = 4.9E-324D;
  private double f = 4.9E-324D;
  private double jdField_for = 4.9E-324D;
  private String jdField_goto = null;
  private int jdField_if = 0;
  private boolean jdField_int = false;
  private float jdField_long = 0.0F;
  public a mAddr = new a();
  public String mServerString = null;
  private float jdField_new = 0.0F;
  private boolean jdField_try = false;
  private boolean jdField_void = false;

  public BDLocation()
  {
  }

  public BDLocation(double paramDouble1, double paramDouble2, float paramFloat)
  {
    this.jdField_else = paramDouble2;
    this.jdField_for = paramDouble1;
    this.jdField_long = paramFloat;
    this.d = j.jdField_for();
  }

  public BDLocation(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")));
    JSONObject localJSONObject1;
    int i;
    label760: 
    while (true)
    {
      return;
      try
      {
        localJSONObject1 = new JSONObject(paramString);
        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("result");
        i = Integer.parseInt(localJSONObject2.getString("error"));
        setLocType(i);
        setTime(localJSONObject2.getString("time"));
        if (i == 61)
        {
          JSONObject localJSONObject3 = localJSONObject1.getJSONObject("content");
          JSONObject localJSONObject4 = localJSONObject3.getJSONObject("point");
          setLatitude(Double.parseDouble(localJSONObject4.getString("y")));
          setLongitude(Double.parseDouble(localJSONObject4.getString("x")));
          setRadius(Float.parseFloat(localJSONObject3.getString("radius")));
          setSpeed(Float.parseFloat(localJSONObject3.getString("s")));
          setDerect(Float.parseFloat(localJSONObject3.getString("d")));
          setSatelliteNumber(Integer.parseInt(localJSONObject3.getString("n")));
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        this.jdField_if = 0;
        this.jdField_void = false;
        return;
      }
      if (i != 161)
        break;
      JSONObject localJSONObject7 = localJSONObject1.getJSONObject("content");
      JSONObject localJSONObject8 = localJSONObject7.getJSONObject("point");
      setLatitude(Double.parseDouble(localJSONObject8.getString("y")));
      setLongitude(Double.parseDouble(localJSONObject8.getString("x")));
      setRadius(Float.parseFloat(localJSONObject7.getString("radius")));
      String str2;
      if (localJSONObject7.has("addr"))
      {
        String str1 = localJSONObject7.getString("addr");
        this.mAddr.jdField_try = str1;
        j.jdField_if("baidu_location_service", str1);
        String[] arrayOfString = str1.split(",");
        this.mAddr.jdField_if = arrayOfString[0];
        this.mAddr.jdField_new = arrayOfString[1];
        this.mAddr.jdField_int = arrayOfString[2];
        this.mAddr.jdField_byte = arrayOfString[3];
        this.mAddr.jdField_do = arrayOfString[4];
        this.mAddr.jdField_for = arrayOfString[5];
        if (((this.mAddr.jdField_if.contains("北京")) && (this.mAddr.jdField_new.contains("北京"))) || ((this.mAddr.jdField_if.contains("上海")) && (this.mAddr.jdField_new.contains("上海"))) || ((this.mAddr.jdField_if.contains("天津")) && (this.mAddr.jdField_new.contains("天津"))) || ((this.mAddr.jdField_if.contains("重庆")) && (this.mAddr.jdField_new.contains("重庆"))))
        {
          j.jdField_if("baidu_location_service", "true,beijing");
          str2 = this.mAddr.jdField_if;
          String str3 = str2 + this.mAddr.jdField_int + this.mAddr.jdField_byte + this.mAddr.jdField_do;
          this.mAddr.jdField_try = str3;
          this.jdField_void = true;
        }
      }
      while (true)
      {
        if (!localJSONObject7.has("poi"))
          break label760;
        this.jdField_case = true;
        this.jdField_byte = localJSONObject7.getJSONObject("poi").toString();
        return;
        str2 = this.mAddr.jdField_if + this.mAddr.jdField_new;
        break;
        this.jdField_void = false;
        setAddrStr(null);
      }
    }
    while (true)
    {
      JSONObject localJSONObject5 = localJSONObject1.getJSONObject("content");
      JSONObject localJSONObject6 = localJSONObject5.getJSONObject("point");
      setLatitude(Double.parseDouble(localJSONObject6.getString("y")));
      setLongitude(Double.parseDouble(localJSONObject6.getString("x")));
      setRadius(Float.parseFloat(localJSONObject5.getString("radius")));
      a(Boolean.valueOf(Boolean.parseBoolean(localJSONObject5.getString("isCellChanged"))));
      return;
      if (i == 66)
        continue;
      if (i != 68)
        break;
    }
  }

  public BDLocation(String paramString1, double paramDouble1, double paramDouble2, float paramFloat, String paramString2, String paramString3)
  {
    this.d = paramString1;
    this.jdField_else = paramDouble1;
    this.jdField_for = paramDouble2;
    this.jdField_long = paramFloat;
    this.jdField_goto = paramString2;
    this.a = paramString3;
    this.d = j.jdField_for();
  }

  private void a(Boolean paramBoolean)
  {
    this.e = paramBoolean.booleanValue();
  }

  public String getAddrStr()
  {
    return this.mAddr.jdField_try;
  }

  public double getAltitude()
  {
    return this.f;
  }

  public String getCity()
  {
    return this.mAddr.jdField_new;
  }

  public String getCityCode()
  {
    return this.mAddr.jdField_for;
  }

  public String getCoorType()
  {
    return this.jdField_goto;
  }

  public float getDerect()
  {
    return this.jdField_char;
  }

  public String getDistrict()
  {
    return this.mAddr.jdField_int;
  }

  public double getLatitude()
  {
    return this.jdField_else;
  }

  public int getLocType()
  {
    return this.jdField_if;
  }

  public double getLongitude()
  {
    return this.jdField_for;
  }

  public String getPoi()
  {
    return this.jdField_byte;
  }

  public String getProvince()
  {
    return this.mAddr.jdField_if;
  }

  public float getRadius()
  {
    return this.jdField_long;
  }

  public int getSatelliteNumber()
  {
    this.b = true;
    return this.jdField_do;
  }

  public float getSpeed()
  {
    return this.jdField_new;
  }

  public String getStreet()
  {
    return this.mAddr.jdField_byte;
  }

  public String getStreetNumber()
  {
    return this.mAddr.jdField_do;
  }

  public String getTime()
  {
    return this.d;
  }

  public boolean hasAddr()
  {
    return this.jdField_void;
  }

  public boolean hasAltitude()
  {
    return this.c;
  }

  public boolean hasPoi()
  {
    return this.jdField_case;
  }

  public boolean hasRadius()
  {
    return this.jdField_try;
  }

  public boolean hasSateNumber()
  {
    return this.b;
  }

  public boolean hasSpeed()
  {
    return this.jdField_int;
  }

  public boolean isCellChangeFlag()
  {
    return this.e;
  }

  public void setAddrStr(String paramString)
  {
    this.a = paramString;
    this.jdField_void = true;
  }

  public void setAltitude(double paramDouble)
  {
    this.f = paramDouble;
    this.c = true;
  }

  public void setCoorType(String paramString)
  {
    this.jdField_goto = paramString;
  }

  public void setDerect(float paramFloat)
  {
    this.jdField_char = paramFloat;
  }

  public void setLatitude(double paramDouble)
  {
    this.jdField_else = paramDouble;
  }

  public void setLocType(int paramInt)
  {
    this.jdField_if = paramInt;
  }

  public void setLongitude(double paramDouble)
  {
    this.jdField_for = paramDouble;
  }

  public void setRadius(float paramFloat)
  {
    this.jdField_long = paramFloat;
    this.jdField_try = true;
  }

  public void setSatelliteNumber(int paramInt)
  {
    this.jdField_do = paramInt;
  }

  public void setSpeed(float paramFloat)
  {
    this.jdField_new = paramFloat;
    this.jdField_int = true;
  }

  public void setTime(String paramString)
  {
    this.d = paramString;
  }

  public String toJsonString()
  {
    return null;
  }

  public BDLocation toNewLocation(String paramString)
  {
    return null;
  }

  public class a
  {
    public String jdField_byte = null;
    public String jdField_do = null;
    public String jdField_for = null;
    public String jdField_if = null;
    public String jdField_int = null;
    public String jdField_new = null;
    public String jdField_try = null;

    public a()
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.BDLocation
 * JD-Core Version:    0.6.0
 */