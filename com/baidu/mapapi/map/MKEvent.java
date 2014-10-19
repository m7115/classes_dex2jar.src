package com.baidu.mapapi.map;

public class MKEvent
{
  public static final int ERROR_NETWORK_CONNECT = 2;
  public static final int ERROR_NETWORK_DATA = 3;
  public static final int ERROR_PERMISSION_DENIED = 300;
  public static final int ERROR_RESULT_NOT_FOUND = 100;
  public static final int ERROR_ROUTE_ADDR = 4;
  public static final int MKEVENT_BUS_DETAIL = 15;
  public static final int MKEVENT_MAP_MOVE_FINISH = 14;
  public static final int MKEVENT_POIDETAILSHAREURL = 18;
  public static final int MKEVENT_POIRGCSHAREURL = 17;
  public static final int MKEVENT_SUGGESTION = 16;
  int a = 0;
  int b = 0;
  int c = 0;

  MKEvent(int paramInt1, int paramInt2, int paramInt3)
  {
    this.a = paramInt1;
    this.b = paramInt2;
    this.c = paramInt3;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MKEvent
 * JD-Core Version:    0.6.0
 */