package com.baidu.mapapi.search;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;

public class MKAddrInfo
{
  public static final int MK_GEOCODE = 0;
  public static final int MK_REVERSEGEOCODE = 1;
  public MKGeocoderAddressComponent addressComponents;
  public GeoPoint geoPt;
  public ArrayList<MKPoiInfo> poiList;
  public String strAddr;
  public String strBusiness;
  public int type;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKAddrInfo
 * JD-Core Version:    0.6.0
 */