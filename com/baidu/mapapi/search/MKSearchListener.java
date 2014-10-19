package com.baidu.mapapi.search;

public abstract interface MKSearchListener
{
  public abstract void onGetAddrResult(MKAddrInfo paramMKAddrInfo, int paramInt);

  public abstract void onGetBusDetailResult(MKBusLineResult paramMKBusLineResult, int paramInt);

  public abstract void onGetDrivingRouteResult(MKDrivingRouteResult paramMKDrivingRouteResult, int paramInt);

  public abstract void onGetPoiDetailSearchResult(int paramInt1, int paramInt2);

  public abstract void onGetPoiResult(MKPoiResult paramMKPoiResult, int paramInt1, int paramInt2);

  public abstract void onGetShareUrlResult(MKShareUrlResult paramMKShareUrlResult, int paramInt1, int paramInt2);

  public abstract void onGetSuggestionResult(MKSuggestionResult paramMKSuggestionResult, int paramInt);

  public abstract void onGetTransitRouteResult(MKTransitRouteResult paramMKTransitRouteResult, int paramInt);

  public abstract void onGetWalkingRouteResult(MKWalkingRouteResult paramMKWalkingRouteResult, int paramInt);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKSearchListener
 * JD-Core Version:    0.6.0
 */