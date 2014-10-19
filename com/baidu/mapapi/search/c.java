package com.baidu.mapapi.search;

import android.os.Bundle;
import com.baidu.mapapi.cloud.CustomPoiInfo;
import com.baidu.mapapi.cloud.DetailResult;
import com.baidu.mapapi.cloud.GeoSearchResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comjni.tools.JNITools;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  private static Bundle a = new Bundle();

  public static String a(ArrayList<MKTransitRoutePlan> paramArrayList)
  {
    if (paramArrayList == null)
      return null;
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("result_type", 14);
      JSONArray localJSONArray1 = new JSONArray();
      for (int i = 0; i < paramArrayList.size(); i++)
      {
        MKTransitRoutePlan localMKTransitRoutePlan = (MKTransitRoutePlan)paramArrayList.get(i);
        JSONObject localJSONObject3 = new JSONObject();
        localJSONObject3.put("uid", "");
        JSONObject localJSONObject4 = new JSONObject();
        if (localMKTransitRoutePlan.getStart() != null)
        {
          GeoPoint localGeoPoint7 = com.baidu.mapapi.utils.e.b(localMKTransitRoutePlan.getStart());
          localJSONObject4.put("x", localGeoPoint7.getLongitudeE6());
          localJSONObject4.put("y", localGeoPoint7.getLatitudeE6());
          localJSONObject3.put("geopt", localJSONObject4);
        }
        localJSONObject1.put("start_point", localJSONObject3);
        JSONObject localJSONObject5 = new JSONObject();
        localJSONObject5.put("uid", "");
        JSONObject localJSONObject6 = new JSONObject();
        if (localMKTransitRoutePlan.getEnd() != null)
        {
          GeoPoint localGeoPoint6 = com.baidu.mapapi.utils.e.b(localMKTransitRoutePlan.getEnd());
          localJSONObject6.put("x", localGeoPoint6.getLongitudeE6());
          localJSONObject6.put("y", localGeoPoint6.getLatitudeE6());
          localJSONObject5.put("geopt", localJSONObject6);
        }
        localJSONObject1.put("end_point", localJSONObject5);
        JSONArray localJSONArray3 = new JSONArray();
        int j = localMKTransitRoutePlan.getNumLines();
        for (int k = 0; k < j; k++)
        {
          JSONObject localJSONObject7 = new JSONObject();
          JSONArray localJSONArray4 = new JSONArray();
          MKLine localMKLine = localMKTransitRoutePlan.getLine(k);
          JSONObject localJSONObject8 = new JSONObject();
          localJSONObject8.put("type", 3);
          JSONObject localJSONObject9 = new JSONObject();
          localJSONObject9.put("type", localMKLine.getType());
          localJSONObject9.put("start_uid", localMKLine.getGetOnStop().uid);
          localJSONObject9.put("end_uid", localMKLine.getGetOffStop().uid);
          localJSONObject8.put("vehicle", localJSONObject9);
          localJSONObject8.put("instructions", localMKLine.getTip());
          JSONObject localJSONObject10 = new JSONObject();
          if (localMKLine.getGetOnStop().pt != null)
          {
            GeoPoint localGeoPoint3 = com.baidu.mapapi.utils.e.b(localMKLine.getGetOnStop().pt);
            localJSONObject10.put("x", localGeoPoint3.getLongitudeE6());
            localJSONObject10.put("y", localGeoPoint3.getLatitudeE6());
            localJSONObject8.put("start_location_pt", localJSONObject10);
          }
          JSONObject localJSONObject11 = new JSONObject();
          if (localMKLine.getGetOffStop().pt != null)
          {
            GeoPoint localGeoPoint2 = com.baidu.mapapi.utils.e.b(localMKLine.getGetOffStop().pt);
            localJSONObject11.put("x", localGeoPoint2.getLongitudeE6());
            localJSONObject11.put("y", localGeoPoint2.getLatitudeE6());
            localJSONObject8.put("end_location_pt", localJSONObject11);
          }
          ArrayList localArrayList1 = localMKLine.a;
          JSONArray localJSONArray5 = new JSONArray();
          m = 0;
          if (m < localArrayList1.size())
          {
            JSONObject localJSONObject12 = new JSONObject();
            GeoPoint localGeoPoint1 = (GeoPoint)localArrayList1.get(m);
            if (localGeoPoint1 == null)
              break label1071;
            localJSONObject12.put("x", localGeoPoint1.getLongitudeE6());
            localJSONObject12.put("y", localGeoPoint1.getLatitudeE6());
            localJSONArray5.put(localJSONObject12);
            break label1071;
          }
          localJSONObject8.put("distance", localMKLine.getDistance());
          localJSONObject8.put("path_geo_pt", localJSONArray5);
          localJSONArray4.put(localJSONObject8);
          localJSONObject7.put("busline", localJSONArray4);
          localJSONArray3.put(localJSONObject7);
        }
        int n = localMKTransitRoutePlan.getNumRoute();
        for (int i1 = 0; i1 < n; i1++)
        {
          JSONObject localJSONObject13 = new JSONObject();
          JSONArray localJSONArray6 = new JSONArray();
          MKRoute localMKRoute = localMKTransitRoutePlan.getRoute(i1);
          JSONObject localJSONObject14 = new JSONObject();
          localJSONObject14.put("type", 5);
          new JSONObject();
          localJSONObject14.put("instructions", localMKRoute.getTip());
          GeoPoint localGeoPoint4 = com.baidu.mapapi.utils.e.b(localMKRoute.getStart());
          JSONObject localJSONObject15 = new JSONObject();
          localJSONObject15.put("x", localGeoPoint4.getLongitudeE6());
          localJSONObject15.put("y", localGeoPoint4.getLatitudeE6());
          localJSONObject14.put("end_location", localJSONObject15);
          ArrayList localArrayList2 = localMKRoute.a;
          JSONArray localJSONArray7 = new JSONArray();
          i2 = 0;
          if (i2 < localArrayList2.size())
          {
            i3 = 0;
            if (i3 >= ((ArrayList)localArrayList2.get(i2)).size())
              break label1083;
            JSONObject localJSONObject16 = new JSONObject();
            GeoPoint localGeoPoint5 = (GeoPoint)((ArrayList)localArrayList2.get(i2)).get(i3);
            if (localGeoPoint5 == null)
              break label1077;
            localJSONObject16.put("x", localGeoPoint5.getLongitudeE6());
            localJSONObject16.put("y", localGeoPoint5.getLatitudeE6());
            localJSONArray7.put(localJSONObject16);
            break label1077;
          }
          localJSONObject14.put("distance", localMKRoute.getDistance());
          localJSONObject14.put("path_geo_pt", localJSONArray7);
          localJSONArray6.put(localJSONObject14);
          localJSONObject13.put("busline", localJSONArray6);
          localJSONArray3.put(localJSONObject13);
        }
        JSONObject localJSONObject17 = new JSONObject();
        localJSONObject17.put("steps", localJSONArray3);
        localJSONArray1.put(localJSONObject17);
      }
      JSONArray localJSONArray2 = new JSONArray();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("legs", localJSONArray1);
      localJSONArray2.put(localJSONObject2);
      localJSONObject1.put("routes", localJSONArray2);
      return localJSONObject1.toString();
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        int m;
        int i2;
        int i3;
        continue;
        label1071: m++;
        continue;
        label1077: i3++;
        continue;
        label1083: i2++;
      }
    }
  }

  public static ArrayList<MKCityListInfo> a(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString == null) || (paramString.equals("")));
    JSONArray localJSONArray;
    do
    {
      return null;
      localJSONArray = paramJSONObject.optJSONArray(paramString);
    }
    while ((localJSONArray == null) || (localJSONArray.length() <= 0));
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localJSONArray.length(); i++)
    {
      JSONObject localJSONObject = (JSONObject)localJSONArray.opt(i);
      MKCityListInfo localMKCityListInfo = new MKCityListInfo();
      localMKCityListInfo.num = localJSONObject.optInt("num");
      localMKCityListInfo.city = localJSONObject.optString("name");
      localArrayList.add(localMKCityListInfo);
    }
    localArrayList.trimToSize();
    return localArrayList;
  }

  private static ArrayList<MKPoiInfo> a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if ((paramJSONObject == null) || (paramString1 == null) || ("".equals(paramString1)));
    JSONArray localJSONArray;
    do
    {
      return null;
      localJSONArray = paramJSONObject.optJSONArray(paramString1);
    }
    while (localJSONArray == null);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localJSONArray.length(); i++)
    {
      JSONObject localJSONObject = (JSONObject)localJSONArray.opt(i);
      MKPoiInfo localMKPoiInfo = new MKPoiInfo();
      localMKPoiInfo.address = localJSONObject.optString("addr");
      localMKPoiInfo.uid = localJSONObject.optString("uid");
      localMKPoiInfo.name = localJSONObject.optString("name");
      localMKPoiInfo.pt = f(localJSONObject, "geo");
      localMKPoiInfo.city = paramString2;
      localArrayList.add(localMKPoiInfo);
    }
    return localArrayList;
  }

  static void a(String paramString, ArrayList<ArrayList<GeoPoint>> paramArrayList1, ArrayList<ArrayList<GeoPoint>> paramArrayList2)
  {
    com.baidu.platform.comapi.basestruct.a locala = com.baidu.platform.comjni.tools.a.a(paramString);
    if ((locala != null) && (locala.d != null))
    {
      ArrayList localArrayList1 = locala.d;
      for (int i = 0; i < localArrayList1.size(); i++)
      {
        ArrayList localArrayList2 = (ArrayList)localArrayList1.get(i);
        ArrayList localArrayList3 = new ArrayList(localArrayList2.size());
        paramArrayList1.add(localArrayList3);
        ArrayList localArrayList4 = new ArrayList(localArrayList2.size());
        paramArrayList2.add(localArrayList4);
        for (int j = 0; j < localArrayList2.size(); j++)
        {
          com.baidu.platform.comapi.basestruct.c localc = (com.baidu.platform.comapi.basestruct.c)localArrayList2.get(j);
          localArrayList4.add(new GeoPoint(localc.b / 100, localc.a / 100));
          localArrayList3.add(com.baidu.mapapi.utils.e.a(new GeoPoint(localc.b / 100, localc.a / 100)));
        }
        localArrayList3.trimToSize();
        localArrayList4.trimToSize();
      }
      paramArrayList1.trimToSize();
      paramArrayList2.trimToSize();
    }
  }

  public static boolean a(String paramString, DetailResult paramDetailResult)
  {
    if ((paramString == null) || (paramDetailResult == null))
      return false;
    while (true)
    {
      int i;
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        paramDetailResult.status = localJSONObject1.optInt("status");
        paramDetailResult.message = localJSONObject1.optString("message");
        JSONObject localJSONObject2 = localJSONObject1.optJSONObject("content");
        if (localJSONObject2 == null)
          break;
        CustomPoiInfo localCustomPoiInfo = new CustomPoiInfo();
        paramDetailResult.content = localCustomPoiInfo;
        localCustomPoiInfo.uid = localJSONObject2.optInt("uid");
        localCustomPoiInfo.name = localJSONObject2.optString("name");
        localCustomPoiInfo.address = localJSONObject2.optString("addr");
        localCustomPoiInfo.telephone = localJSONObject2.optString("tel");
        localCustomPoiInfo.postCode = localJSONObject2.optString("zip");
        localCustomPoiInfo.provinceId = localJSONObject2.optInt("province_id");
        localCustomPoiInfo.cityId = localJSONObject2.optInt("city_id");
        localCustomPoiInfo.districtId = localJSONObject2.optInt("district_id");
        localCustomPoiInfo.provinceName = localJSONObject2.optString("province");
        localCustomPoiInfo.cityName = localJSONObject2.optString("city");
        localCustomPoiInfo.districtName = localJSONObject2.optString("district");
        localCustomPoiInfo.latitude = (float)localJSONObject2.optDouble("latitude");
        localCustomPoiInfo.longitude = (float)localJSONObject2.optDouble("longitude");
        localCustomPoiInfo.databoxId = localJSONObject2.optInt("databox_id");
        localCustomPoiInfo.tag = localJSONObject2.optString("tag");
        JSONObject localJSONObject3 = localJSONObject2.optJSONObject("ext");
        if (localJSONObject3 == null)
          break;
        i = 0;
        if (i >= localJSONObject3.length())
          break;
        localCustomPoiInfo.poiExtend = new HashMap();
        Iterator localIterator = localJSONObject3.keys();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = localJSONObject3.opt(str);
          localCustomPoiInfo.poiExtend.put(str, localObject);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return false;
      }
      i++;
    }
  }

  public static boolean a(String paramString, GeoSearchResult paramGeoSearchResult)
  {
    if ((paramString == null) || (paramGeoSearchResult == null))
      return false;
    while (true)
    {
      int i;
      int j;
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        paramGeoSearchResult.status = localJSONObject1.optInt("status");
        paramGeoSearchResult.total = localJSONObject1.optInt("total");
        paramGeoSearchResult.message = localJSONObject1.optString("message");
        paramGeoSearchResult.size = localJSONObject1.optInt("size");
        paramGeoSearchResult.poiList = new ArrayList();
        JSONArray localJSONArray = localJSONObject1.optJSONArray("content");
        if ((localJSONArray == null) || (localJSONArray.length() <= 0))
          break;
        i = 0;
        if (i >= localJSONArray.length())
          break;
        JSONObject localJSONObject2 = (JSONObject)localJSONArray.opt(i);
        CustomPoiInfo localCustomPoiInfo = new CustomPoiInfo();
        paramGeoSearchResult.poiList.add(localCustomPoiInfo);
        localCustomPoiInfo.uid = localJSONObject2.optInt("uid");
        localCustomPoiInfo.name = localJSONObject2.optString("name");
        localCustomPoiInfo.address = localJSONObject2.optString("addr");
        localCustomPoiInfo.telephone = localJSONObject2.optString("tel");
        localCustomPoiInfo.postCode = localJSONObject2.optString("zip");
        localCustomPoiInfo.provinceId = localJSONObject2.optInt("province_id");
        localCustomPoiInfo.cityId = localJSONObject2.optInt("city_id");
        localCustomPoiInfo.districtId = localJSONObject2.optInt("district_id");
        localCustomPoiInfo.provinceName = localJSONObject2.optString("province");
        localCustomPoiInfo.cityName = localJSONObject2.optString("city");
        localCustomPoiInfo.districtName = localJSONObject2.optString("district");
        localCustomPoiInfo.latitude = (float)localJSONObject2.optDouble("latitude");
        localCustomPoiInfo.longitude = (float)localJSONObject2.optDouble("longitude");
        localCustomPoiInfo.databoxId = localJSONObject2.optInt("databox_id");
        localCustomPoiInfo.tag = localJSONObject2.optString("tag");
        JSONObject localJSONObject3 = localJSONObject2.optJSONObject("ext");
        if (localJSONObject3 == null)
          break label445;
        j = 0;
        if (j >= localJSONObject3.length())
          break label445;
        localCustomPoiInfo.poiExtend = new HashMap();
        Iterator localIterator = localJSONObject3.keys();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Object localObject = localJSONObject3.opt(str);
          localCustomPoiInfo.poiExtend.put(str, localObject);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return false;
      }
      j++;
      continue;
      label445: i++;
    }
    return true;
  }

  public static boolean a(String paramString, MKAddrInfo paramMKAddrInfo)
  {
    if ((paramString == null) || ("".equals(paramString)) || (paramMKAddrInfo == null))
      return false;
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      paramMKAddrInfo.strAddr = localJSONObject.optString("address");
      paramMKAddrInfo.strBusiness = localJSONObject.optString("business");
      paramMKAddrInfo.addressComponents = c(localJSONObject, "addr_detail");
      paramMKAddrInfo.type = 1;
      paramMKAddrInfo.geoPt = g(localJSONObject, "point");
      paramMKAddrInfo.poiList = b(localJSONObject, "surround_poi");
      return true;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return false;
  }

  public static boolean a(String paramString, MKBusLineResult paramMKBusLineResult)
  {
    int i = 1;
    if (paramString != null);
    while (true)
    {
      int k;
      int n;
      try
      {
        if ((paramString.equals("")) || (paramMKBusLineResult == null))
          break label397;
        JSONObject localJSONObject1 = new JSONObject(paramString);
        int j = localJSONObject1.optInt("count");
        JSONArray localJSONArray1 = localJSONObject1.optJSONArray("details");
        if ((localJSONArray1 == null) || (j <= 0))
          break label399;
        k = 0;
        if (k >= localJSONArray1.length())
          break label399;
        JSONObject localJSONObject2 = localJSONArray1.optJSONObject(k);
        paramMKBusLineResult.a(localJSONObject2.optString("starttime"));
        paramMKBusLineResult.b(localJSONObject2.optString("endtime"));
        String str = localJSONObject2.optString("name");
        if (!localJSONObject2.optBoolean("ismonticket"))
          break label407;
        m = i;
        paramMKBusLineResult.a(null, str, m);
        paramMKBusLineResult.getBusRoute().b(new ArrayList());
        paramMKBusLineResult.getBusRoute().a = new ArrayList();
        a(localJSONObject2.optString("geo"), paramMKBusLineResult.getBusRoute().getArrayPoints(), paramMKBusLineResult.getBusRoute().a);
        paramMKBusLineResult.getBusRoute().c(3);
        JSONArray localJSONArray2 = localJSONObject2.optJSONArray("stations");
        if (localJSONArray2 != null)
        {
          MKRoute localMKRoute = paramMKBusLineResult.getBusRoute();
          ArrayList localArrayList = new ArrayList();
          localMKRoute.a(localArrayList);
          n = 0;
          if (n < localJSONArray2.length())
          {
            JSONObject localJSONObject3 = localJSONArray2.optJSONObject(n);
            if (localJSONObject3 == null)
              break label401;
            MKStep localMKStep = new MKStep();
            localMKStep.a(localJSONObject3.optString("name"));
            localMKStep.a(f(localJSONObject3, "geo"));
            localArrayList.add(localMKStep);
            if (n != 0)
              continue;
            localMKRoute.a(new GeoPoint(localMKStep.getPoint().getLatitudeE6(), localMKStep.getPoint().getLongitudeE6()));
            break label401;
            if (n != -1 + localJSONArray2.length())
              break label401;
            localMKRoute.b(new GeoPoint(localMKStep.getPoint().getLatitudeE6(), localMKStep.getPoint().getLongitudeE6()));
          }
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return false;
      }
      k++;
      continue;
      label397: i = 0;
      label399: return i;
      label401: n++;
      continue;
      label407: int m = 0;
    }
  }

  public static boolean a(String paramString, MKDrivingRouteResult paramMKDrivingRouteResult)
    throws JSONException
  {
    int i = 0;
    if ((paramString == null) || ("".equals(paramString)) || (paramMKDrivingRouteResult == null))
      return false;
    JSONObject localJSONObject1 = new JSONObject(paramString);
    JSONObject localJSONObject2 = localJSONObject1.optJSONObject("taxi");
    if (localJSONObject2 != null)
      paramMKDrivingRouteResult.a(localJSONObject2.optInt("total_price"));
    boolean bool;
    if (localJSONObject1.optInt("avoid_jam") != 0)
    {
      bool = true;
      paramMKDrivingRouteResult.a(bool);
      paramMKDrivingRouteResult.a(d(localJSONObject1, "start_point"));
      paramMKDrivingRouteResult.b(d(localJSONObject1, "end_point"));
      if (localJSONObject1.optBoolean("have_way_points_endcity"))
        paramMKDrivingRouteResult.a(e(localJSONObject1, "way_points_endcity"));
      if (paramMKDrivingRouteResult.getStart() == null)
        break label671;
    }
    label671: for (GeoPoint localGeoPoint1 = paramMKDrivingRouteResult.getStart().pt; ; localGeoPoint1 = null)
    {
      if (paramMKDrivingRouteResult.getEnd() != null);
      for (GeoPoint localGeoPoint2 = paramMKDrivingRouteResult.getEnd().pt; ; localGeoPoint2 = null)
      {
        String str1 = localJSONObject1.optJSONObject("start_point").optString("name");
        String str2 = localJSONObject1.optJSONObject("end_point").optString("name");
        String str3 = "驾车方案：" + str1 + "_" + str2;
        JSONObject localJSONObject3 = localJSONObject1.optJSONObject("routes");
        if (localJSONObject3 != null)
        {
          JSONObject localJSONObject4 = localJSONObject3.optJSONObject("legs");
          if (localJSONObject4 != null)
          {
            ArrayList localArrayList1 = new ArrayList();
            paramMKDrivingRouteResult.a(localArrayList1);
            MKRoutePlan localMKRoutePlan = new MKRoutePlan();
            localArrayList1.add(localMKRoutePlan);
            int j = localJSONObject4.optInt("distance");
            localMKRoutePlan.a(j);
            int k = localJSONObject4.optInt("duration");
            localMKRoutePlan.b(k);
            ArrayList localArrayList2 = new ArrayList();
            localMKRoutePlan.a(localArrayList2);
            MKRoute localMKRoute = new MKRoute();
            localArrayList2.add(localMKRoute);
            localMKRoute.d(k);
            localMKRoute.a(str3);
            localMKRoute.b(0);
            localMKRoute.a(j);
            localMKRoute.b(new ArrayList());
            localMKRoute.a = new ArrayList();
            if (localGeoPoint1 != null)
              localMKRoute.a(new GeoPoint(localGeoPoint1.getLatitudeE6(), localGeoPoint1.getLongitudeE6()));
            if (localGeoPoint2 != null)
              localMKRoute.b(new GeoPoint(localGeoPoint2.getLatitudeE6(), localGeoPoint2.getLongitudeE6()));
            localMKRoute.c(1);
            JSONArray localJSONArray = localJSONObject4.optJSONArray("steps");
            if (localJSONArray != null)
            {
              ArrayList localArrayList3 = new ArrayList();
              localMKRoute.a(localArrayList3);
              JSONObject localJSONObject5 = localJSONArray.optJSONObject(0);
              MKStep localMKStep1 = new MKStep();
              localMKStep1.a(localJSONObject5.optString("start_desc"));
              localMKStep1.a(localJSONObject5.optInt("direction"));
              localMKStep1.b(localJSONObject5.optString("start_loc"));
              localMKStep1.a(f(localJSONObject5, "start_loc"));
              localArrayList3.add(localMKStep1);
              while (true)
                if (i < localJSONArray.length())
                {
                  MKStep localMKStep2 = new MKStep();
                  JSONObject localJSONObject6 = localJSONArray.optJSONObject(i);
                  if (localJSONObject6 != null)
                  {
                    localMKStep2.a(localJSONObject6.optString("end_desc"));
                    localMKStep2.a(localJSONObject6.optInt("direction"));
                    System.currentTimeMillis();
                    localMKStep2.a(f(localJSONObject6, "end_loc"));
                    localArrayList3.add(localMKStep2);
                    localMKStep2.b(localJSONObject6.optString("path"));
                  }
                  i++;
                  continue;
                  bool = false;
                  break;
                }
            }
          }
        }
        paramMKDrivingRouteResult.a(null);
        return true;
      }
    }
  }

  public static boolean a(String paramString, MKPoiResult paramMKPoiResult)
  {
    if ((paramString == null) || ("".equals(paramString)))
      return false;
    try
    {
      localJSONObject1 = new JSONObject(paramString);
      JSONArray localJSONArray = localJSONObject1.optJSONArray("citys");
      if ((localJSONArray != null) && (localJSONArray.length() > 0))
      {
        localArrayList = new ArrayList();
        if (paramMKPoiResult != null)
          paramMKPoiResult.c(localArrayList);
        for (int i = 0; i < localJSONArray.length(); i++)
        {
          JSONObject localJSONObject2 = (JSONObject)localJSONArray.opt(i);
          MKCityListInfo localMKCityListInfo = new MKCityListInfo();
          localMKCityListInfo.num = localJSONObject2.optInt("num");
          localMKCityListInfo.city = localJSONObject2.optString("name");
          localArrayList.add(localMKCityListInfo);
        }
      }
    }
    catch (JSONException localJSONException)
    {
      ArrayList localArrayList;
      while (true)
      {
        localJSONException.printStackTrace();
        JSONObject localJSONObject1 = null;
      }
      localArrayList.trimToSize();
    }
    return true;
  }

  public static boolean a(String paramString, MKPoiResult paramMKPoiResult, int paramInt)
  {
    int i = 1;
    if (paramString != null)
      while (true)
      {
        try
        {
          if ((paramString.equals("")) || (paramMKPoiResult == null))
            break;
          JSONObject localJSONObject1 = new JSONObject(paramString);
          int j = localJSONObject1.optInt("total");
          int k = localJSONObject1.optInt("count");
          paramMKPoiResult.b(j);
          paramMKPoiResult.a(k);
          paramMKPoiResult.d(paramInt);
          int m = j / k;
          if (j % k <= 0)
            continue;
          int n = i;
          paramMKPoiResult.c(n + m);
          JSONObject localJSONObject2 = localJSONObject1.optJSONObject("current_city");
          if (localJSONObject2 != null)
          {
            str = localJSONObject2.optString("name");
            JSONArray localJSONArray = localJSONObject1.optJSONArray("pois");
            if (localJSONArray == null)
              break label360;
            ArrayList localArrayList = new ArrayList();
            paramMKPoiResult.a(localArrayList);
            int i1 = 0;
            if (i1 >= localJSONArray.length())
              break label360;
            JSONObject localJSONObject3 = localJSONArray.optJSONObject(i1);
            MKPoiInfo localMKPoiInfo = new MKPoiInfo();
            localMKPoiInfo.name = localJSONObject3.optString("name");
            localMKPoiInfo.address = localJSONObject3.optString("addr");
            localMKPoiInfo.uid = localJSONObject3.optString("uid");
            localMKPoiInfo.phoneNum = localJSONObject3.optString("tel");
            localMKPoiInfo.ePoiType = localJSONObject3.optInt("type");
            if ((localMKPoiInfo.ePoiType == 2) || (localMKPoiInfo.ePoiType == 4))
              continue;
            localMKPoiInfo.pt = f(localJSONObject3, "geo");
            localMKPoiInfo.city = str;
            JSONObject localJSONObject4 = localJSONObject3.optJSONObject("place");
            if ((localJSONObject4 == null) || (!"cater".equals(localJSONObject4.optString("src_name"))) || (!localJSONObject3.optBoolean("detail")))
              continue;
            localMKPoiInfo.hasCaterDetails = true;
            localArrayList.add(localMKPoiInfo);
            i1++;
            continue;
            n = 0;
            continue;
          }
        }
        catch (Exception localException)
        {
          return false;
        }
        String str = null;
      }
    i = 0;
    label360: return i;
  }

  public static boolean a(String paramString, MKRouteAddrResult paramMKRouteAddrResult)
  {
    if ((paramString == null) || ("".equals(paramString)) || (paramMKRouteAddrResult == null))
      return false;
    JSONArray localJSONArray;
    while (true)
    {
      JSONObject localJSONObject1;
      String str2;
      try
      {
        localJSONObject1 = new JSONObject(paramString);
        JSONObject localJSONObject2 = localJSONObject1.optJSONObject("address_info");
        if (localJSONObject2 == null)
          break;
        String str1 = localJSONObject2.optString("st_cityname");
        str2 = localJSONObject2.optString("en_cityname");
        if (!localJSONObject2.optBoolean("have_stcitylist"))
          continue;
        paramMKRouteAddrResult.mStartCityList = a(localJSONObject1, "startcitys");
        if (localJSONObject2.optBoolean("have_encitylist"))
        {
          paramMKRouteAddrResult.mEndCityList = a(localJSONObject1, "endcitys");
          localJSONArray = localJSONObject1.optJSONArray("way_points_citylist");
          if (localJSONArray == null)
            break label299;
          if (localJSONArray.length() > 0)
            break label170;
          break label299;
          paramMKRouteAddrResult.mStartPoiList = a(localJSONObject1, "startpoints", str1);
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return false;
      }
      paramMKRouteAddrResult.mEndPoiList = a(localJSONObject1, "endpoints", str2);
    }
    label170: paramMKRouteAddrResult.mWpCityList = new ArrayList();
    paramMKRouteAddrResult.mWpPoiList = new ArrayList();
    label299: label301: for (int i = 0; ; i++)
      if (i < localJSONArray.length())
      {
        JSONObject localJSONObject3 = localJSONArray.getJSONObject(i);
        if (localJSONObject3.optBoolean("have_citylist"))
          paramMKRouteAddrResult.mWpCityList.add(a(localJSONObject3, "way_points_item"));
        while (localJSONObject3.optBoolean("have_poilist"))
        {
          paramMKRouteAddrResult.mWpPoiList.add(a(localJSONObject3, "way_points_poilist", ""));
          break label301;
          paramMKRouteAddrResult.mWpCityList.add(null);
        }
        paramMKRouteAddrResult.mWpPoiList.add(null);
      }
      else
      {
        return true;
        return true;
      }
  }

  public static boolean a(String paramString, MKShareUrlResult paramMKShareUrlResult)
  {
    if ((paramString == null) || (paramMKShareUrlResult == null));
    while (true)
    {
      return false;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        if (localJSONObject == null)
          continue;
        paramMKShareUrlResult.url = localJSONObject.optString("url");
        paramMKShareUrlResult.type = localJSONObject.optInt("type");
        return true;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return false;
  }

  public static boolean a(String paramString, MKSuggestionResult paramMKSuggestionResult)
  {
    if (paramString != null)
      try
      {
        if (!paramString.equals(""))
        {
          if (paramMKSuggestionResult == null)
            return false;
          JSONObject localJSONObject = new JSONObject(paramString);
          JSONArray localJSONArray1 = localJSONObject.optJSONArray("cityname");
          JSONArray localJSONArray2 = localJSONObject.optJSONArray("poiname");
          JSONArray localJSONArray3 = localJSONObject.optJSONArray("districtname");
          if ((localJSONArray2 != null) && (localJSONArray2.length() > 0))
          {
            ArrayList localArrayList = new ArrayList();
            paramMKSuggestionResult.a(localArrayList);
            int i = localJSONArray2.length();
            for (int j = 0; j < i; j++)
            {
              MKSuggestionInfo localMKSuggestionInfo = new MKSuggestionInfo();
              localMKSuggestionInfo.city = localJSONArray1.optString(j);
              localMKSuggestionInfo.key = localJSONArray2.optString(j);
              localMKSuggestionInfo.district = localJSONArray3.optString(j);
              localArrayList.add(localMKSuggestionInfo);
            }
          }
          return true;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    return false;
  }

  public static boolean a(String paramString, MKTransitRouteResult paramMKTransitRouteResult)
    throws JSONException
  {
    if ((paramString == null) || ("".equals(paramString)) || (paramMKTransitRouteResult == null))
      return false;
    JSONObject localJSONObject1 = new JSONObject(paramString);
    JSONObject localJSONObject2 = localJSONObject1.optJSONObject("taxi");
    if (localJSONObject2 != null)
      paramMKTransitRouteResult.a(localJSONObject2.optInt("total_price"));
    paramMKTransitRouteResult.a(d(localJSONObject1, "start_point"));
    paramMKTransitRouteResult.b(d(localJSONObject1, "end_point"));
    JSONArray localJSONArray1 = localJSONObject1.optJSONArray("routes");
    if ((localJSONArray1 != null) && (localJSONArray1.length() > 0))
    {
      ArrayList localArrayList1 = new ArrayList();
      paramMKTransitRouteResult.a(localArrayList1);
      for (int i = 0; i < localJSONArray1.length(); i++)
      {
        JSONObject localJSONObject3 = ((JSONObject)localJSONArray1.opt(i)).optJSONObject("legs");
        if (localJSONObject3 == null)
          continue;
        MKTransitRoutePlan localMKTransitRoutePlan = new MKTransitRoutePlan();
        localArrayList1.add(localMKTransitRoutePlan);
        localMKTransitRoutePlan.a(localJSONObject3.optInt("distance"));
        localMKTransitRoutePlan.a(f(localJSONObject3, "start_geo"));
        localMKTransitRoutePlan.b(f(localJSONObject3, "end_geo"));
        localMKTransitRoutePlan.b(localJSONObject3.optInt("time"));
        JSONArray localJSONArray2 = localJSONObject3.optJSONArray("steps");
        if ((localJSONArray2 == null) || (localJSONArray2.length() <= 0))
          continue;
        ArrayList localArrayList2 = new ArrayList();
        ArrayList localArrayList3 = new ArrayList();
        localMKTransitRoutePlan.a(localArrayList2);
        localMKTransitRoutePlan.setLine(localArrayList3);
        String str1 = "";
        int j = 0;
        if (j < localJSONArray2.length())
        {
          JSONArray localJSONArray3 = localJSONArray2.optJSONObject(j).optJSONArray("busline");
          JSONObject localJSONObject4;
          int k;
          label335: GeoPoint localGeoPoint1;
          GeoPoint localGeoPoint2;
          String str2;
          if ((localJSONArray3 != null) && (localJSONArray3.length() > 0))
          {
            localJSONObject4 = localJSONArray3.optJSONObject(0);
            if (localJSONObject4 != null)
            {
              if (localJSONObject4.optInt("type") != 5)
                break label509;
              k = 2;
              localGeoPoint1 = f(localJSONObject4, "start_location");
              localGeoPoint2 = f(localJSONObject4, "end_location");
              str2 = null;
              if (0 == 0)
                str2 = localJSONObject4.optString("instructions");
              if (k != 2)
                break label515;
              MKRoute localMKRoute = new MKRoute();
              localMKRoute.c(k);
              localMKRoute.a(localJSONObject4.optInt("distance"));
              localMKRoute.d(localJSONObject4.optInt("duration"));
              localMKRoute.a(localGeoPoint1);
              localMKRoute.b(localGeoPoint2);
              localMKRoute.b(-1 + localArrayList3.size());
              localMKRoute.a(str2);
              localMKRoute.b(new ArrayList());
              localMKRoute.a = new ArrayList();
              a(localJSONObject4.optString("path_geo"), localMKRoute.getArrayPoints(), localMKRoute.a);
              localArrayList2.add(localMKRoute);
            }
          }
          while (true)
          {
            j++;
            break;
            label509: k = 3;
            break label335;
            label515: MKLine localMKLine = new MKLine();
            localMKLine.b(localJSONObject4.optInt("distance"));
            localMKLine.c(localJSONObject4.optInt("duration"));
            MKPoiInfo localMKPoiInfo1 = new MKPoiInfo();
            localMKPoiInfo1.pt = localGeoPoint1;
            localMKLine.a(localMKPoiInfo1);
            MKPoiInfo localMKPoiInfo2 = new MKPoiInfo();
            localMKPoiInfo2.pt = localGeoPoint2;
            localMKLine.b(localMKPoiInfo2);
            localMKLine.a(new ArrayList());
            localMKLine.a = new ArrayList();
            b(localJSONObject4.optString("path_geo"), localMKLine.getPoints(), localMKLine.a);
            JSONObject localJSONObject5 = localJSONObject4.optJSONObject("vehicle");
            if (localJSONObject5 != null)
            {
              localMKLine.d(localJSONObject5.optInt("type"));
              localMKPoiInfo1.name = localJSONObject5.optString("start_name");
              localMKPoiInfo1.uid = localJSONObject5.optString("start_uid");
              localMKPoiInfo2.name = localJSONObject5.optString("end_name");
              localMKPoiInfo2.uid = localJSONObject5.optString("end_uid");
              localMKLine.a(localJSONObject5.optInt("stop_num"));
              localMKLine.d(localJSONObject5.optInt("type"));
              localMKLine.b(localJSONObject5.optString("name"));
              if (!str1.equals(""))
                str1 = str1 + "_";
              str1 = str1 + localMKLine.getTitle();
            }
            localMKLine.a(str2);
            localArrayList3.add(localMKLine);
          }
        }
        localMKTransitRoutePlan.a(str1);
      }
    }
    paramMKTransitRouteResult.a(null);
    return true;
  }

  public static boolean a(String paramString, MKWalkingRouteResult paramMKWalkingRouteResult)
    throws JSONException
  {
    if ((paramString == null) || ("".equals(paramString)) || (paramMKWalkingRouteResult == null))
      return false;
    JSONObject localJSONObject1 = new JSONObject(paramString);
    JSONObject localJSONObject2 = localJSONObject1.optJSONObject("taxi");
    if (localJSONObject2 != null)
      paramMKWalkingRouteResult.a(localJSONObject2.optInt("total_price"));
    paramMKWalkingRouteResult.a(d(localJSONObject1, "start_point"));
    paramMKWalkingRouteResult.b(d(localJSONObject1, "end_point"));
    if (paramMKWalkingRouteResult.getStart() != null);
    for (GeoPoint localGeoPoint1 = paramMKWalkingRouteResult.getStart().pt; ; localGeoPoint1 = null)
    {
      if (paramMKWalkingRouteResult.getEnd() != null);
      for (GeoPoint localGeoPoint2 = paramMKWalkingRouteResult.getEnd().pt; ; localGeoPoint2 = null)
      {
        String str1 = localJSONObject1.optJSONObject("start_point").optString("name");
        String str2 = localJSONObject1.optJSONObject("end_point").optString("name");
        String str3 = "步行方案：" + str1 + "_" + str2;
        JSONObject localJSONObject3 = localJSONObject1.optJSONObject("routes");
        if (localJSONObject3 != null)
        {
          JSONObject localJSONObject4 = localJSONObject3.optJSONObject("legs");
          if (localJSONObject4 != null)
          {
            ArrayList localArrayList1 = new ArrayList();
            paramMKWalkingRouteResult.a(localArrayList1);
            MKRoutePlan localMKRoutePlan = new MKRoutePlan();
            localArrayList1.add(localMKRoutePlan);
            int i = localJSONObject4.optInt("distance");
            localMKRoutePlan.a(i);
            int j = localJSONObject4.optInt("duration");
            localMKRoutePlan.b(j);
            ArrayList localArrayList2 = new ArrayList();
            localMKRoutePlan.a(localArrayList2);
            MKRoute localMKRoute = new MKRoute();
            localArrayList2.add(localMKRoute);
            localMKRoute.d(j);
            localMKRoute.a(str3);
            localMKRoute.b(0);
            localMKRoute.a(i);
            if (localGeoPoint1 != null)
              localMKRoute.a(new GeoPoint(localGeoPoint1.getLatitudeE6(), localGeoPoint1.getLongitudeE6()));
            if (localGeoPoint2 != null)
              localMKRoute.b(new GeoPoint(localGeoPoint2.getLatitudeE6(), localGeoPoint2.getLongitudeE6()));
            localMKRoute.c(2);
            localMKRoute.b(new ArrayList());
            localMKRoute.a = new ArrayList();
            JSONArray localJSONArray = localJSONObject4.optJSONArray("steps");
            if (localJSONArray != null)
            {
              ArrayList localArrayList3 = new ArrayList();
              localMKRoute.a(localArrayList3);
              JSONObject localJSONObject5 = localJSONArray.optJSONObject(0);
              MKStep localMKStep1 = new MKStep();
              localMKStep1.a(localJSONObject5.optString("start_desc"));
              localMKStep1.a(localJSONObject5.optInt("direction"));
              localMKStep1.a(f(localJSONObject5, "start_loc"));
              localArrayList3.add(localMKStep1);
              for (int k = 0; k < localJSONArray.length(); k++)
              {
                MKStep localMKStep2 = new MKStep();
                JSONObject localJSONObject6 = localJSONArray.optJSONObject(k);
                if (localJSONObject6 == null)
                  continue;
                localMKStep2.a(localJSONObject6.optString("end_desc"));
                localMKStep2.a(localJSONObject6.optInt("direction"));
                localMKStep2.a(f(localJSONObject6, "end_loc"));
                localArrayList3.add(localMKStep2);
                a(localJSONObject6.optString("path"), localMKRoute.getArrayPoints(), localMKRoute.a);
              }
            }
          }
        }
        paramMKWalkingRouteResult.a(null);
        return true;
      }
    }
  }

  public static boolean a(String paramString, e parame)
  {
    if ((paramString == null) || (parame == null))
      return false;
    while (true)
    {
      int k;
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        if (localJSONObject1 != null)
        {
          JSONObject localJSONObject2 = localJSONObject1.optJSONObject("content");
          if (localJSONObject2 == null)
            return false;
          parame.d = localJSONObject2.optString("uid");
          parame.b = localJSONObject2.optString("addr");
          parame.a = localJSONObject2.optString("name");
          JSONObject localJSONObject3 = localJSONObject2.optJSONObject("ext");
          if (localJSONObject3 == null)
            continue;
          JSONObject localJSONObject4 = localJSONObject3.optJSONObject("detail_info");
          if (localJSONObject4 == null)
            continue;
          parame.i = localJSONObject4.optString("environment_rating");
          parame.e = localJSONObject4.optString("image");
          JSONArray localJSONArray1 = localJSONObject4.optJSONArray("link");
          if ((localJSONArray1 == null) || (localJSONArray1.length() <= 0))
            continue;
          parame.o = new ArrayList();
          k = 0;
          if (k >= localJSONArray1.length())
            continue;
          JSONObject localJSONObject8 = (JSONObject)localJSONArray1.opt(k);
          if ("dianping".equals(localJSONObject8.optString("name")))
            break label510;
          d locald = new d();
          locald.b = localJSONObject8.optString("cn_name");
          locald.c = localJSONObject8.optString("url");
          locald.d = localJSONObject8.optString("name");
          parame.o.add(locald);
          break label510;
          parame.f = localJSONObject4.optString("overall_rating");
          parame.c = localJSONObject4.optString("phone");
          parame.g = localJSONObject4.optString("price");
          parame.j = localJSONObject4.optString("service_rating");
          parame.h = localJSONObject4.optString("taste_rating");
          JSONObject localJSONObject5 = localJSONObject3.optJSONObject("rich_info");
          if (localJSONObject5 == null)
            continue;
          parame.k = localJSONObject5.optString("description");
          parame.l = localJSONObject5.optString("recommendation");
          JSONArray localJSONArray2 = localJSONObject3.optJSONArray("review");
          if ((localJSONArray2 == null) || (localJSONArray2.length() <= 0))
            continue;
          int i = 0;
          if (i >= localJSONArray2.length())
            continue;
          JSONObject localJSONObject6 = (JSONObject)localJSONArray2.opt(i);
          if ("dianping.com".equals(localJSONObject6.optString("from")))
            continue;
          JSONArray localJSONArray3 = localJSONObject6.optJSONArray("info");
          if ((localJSONArray3 == null) || (localJSONArray3.length() <= 0))
            continue;
          int j = 0;
          if (j >= localJSONArray3.length())
            continue;
          JSONObject localJSONObject7 = localJSONArray3.optJSONObject(j);
          if (localJSONObject7 == null)
            continue;
          parame.m = localJSONObject7.optString("content");
          return true;
          j++;
          continue;
          i++;
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return false;
      label510: k++;
    }
  }

  public static String b(ArrayList<MKRoute> paramArrayList)
  {
    if (paramArrayList == null)
      return null;
    JSONObject localJSONObject1 = new JSONObject();
    JSONArray localJSONArray1;
    int i;
    JSONObject localJSONObject3;
    int j;
    ArrayList localArrayList1;
    JSONArray localJSONArray2;
    int k;
    JSONObject localJSONObject8;
    int n;
    label452: label584: label589: 
    do
    {
      try
      {
        localJSONObject1.put("result_type", 20);
        localJSONArray1 = new JSONArray();
        i = 0;
        if (i >= paramArrayList.size())
          break label733;
        MKRoute localMKRoute = (MKRoute)paramArrayList.get(i);
        if (localMKRoute.getRouteType() == 3)
          localJSONObject1.put("result_buslinedetail", true);
        localJSONObject3 = new JSONObject();
        JSONObject localJSONObject4 = new JSONObject();
        localJSONObject4.put("uid", "");
        JSONObject localJSONObject5 = new JSONObject();
        if (localMKRoute.getStart() != null)
        {
          GeoPoint localGeoPoint5 = com.baidu.mapapi.utils.e.b(localMKRoute.getStart());
          localJSONObject5.put("x", localGeoPoint5.getLongitudeE6());
          localJSONObject5.put("y", localGeoPoint5.getLatitudeE6());
          localJSONObject4.put("geopt", localJSONObject5);
        }
        localJSONObject3.put("start_point", localJSONObject4);
        JSONObject localJSONObject6 = new JSONObject();
        localJSONObject6.put("uid", "");
        JSONObject localJSONObject7 = new JSONObject();
        if (localMKRoute.getEnd() != null)
        {
          GeoPoint localGeoPoint4 = com.baidu.mapapi.utils.e.b(localMKRoute.getEnd());
          localJSONObject7.put("x", localGeoPoint4.getLongitudeE6());
          localJSONObject7.put("y", localGeoPoint4.getLatitudeE6());
          localJSONObject6.put("geopt", localJSONObject7);
        }
        localJSONObject3.put("end_point", localJSONObject6);
        j = localMKRoute.getNumSteps();
        localArrayList1 = localMKRoute.a;
        localJSONArray2 = new JSONArray();
        k = 0;
        if (k >= j)
          break;
        localJSONObject8 = new JSONObject();
        MKStep localMKStep = localMKRoute.getStep(k);
        localJSONObject8.put("direction", localMKStep.a());
        if (localMKStep.getPoint() != null)
        {
          GeoPoint localGeoPoint3 = com.baidu.mapapi.utils.e.b(localMKStep.getPoint());
          JSONObject localJSONObject11 = new JSONObject();
          localJSONObject11.put("x", localGeoPoint3.getLongitudeE6());
          localJSONObject11.put("y", localGeoPoint3.getLatitudeE6());
          localJSONObject8.put("end_loc_pt", localJSONObject11);
        }
        localJSONObject8.put("end_desc", localMKStep.getContent());
        if (localMKRoute.getRouteType() == 3)
          if (localArrayList1.size() > k)
          {
            ArrayList localArrayList3 = (ArrayList)localArrayList1.get(k);
            JSONArray localJSONArray4 = new JSONArray();
            n = 0;
            if (n < localArrayList3.size())
            {
              JSONObject localJSONObject10 = new JSONObject();
              GeoPoint localGeoPoint2 = (GeoPoint)localArrayList3.get(n);
              if (localGeoPoint2 == null)
                break label764;
              localJSONObject10.put("x", localGeoPoint2.getLongitudeE6());
              localJSONObject10.put("y", localGeoPoint2.getLatitudeE6());
              localJSONArray4.put(localJSONObject10);
              break label764;
            }
            localJSONObject8.put("pathPt", localJSONArray4);
          }
        while (true)
        {
          localJSONArray2.put(localJSONObject8);
          k++;
          break;
          if (localMKRoute.getRouteType() != 1)
            break label589;
          if (k >= j)
            continue;
          localJSONObject8.put("path", localMKStep.b());
        }
      }
      catch (JSONException localJSONException)
      {
      }
      return localJSONObject1.toString();
    }
    while (k >= j - 1);
    label536: ArrayList localArrayList2 = (ArrayList)localArrayList1.get(k);
    JSONArray localJSONArray3 = new JSONArray();
    for (int m = 0; ; m++)
      if (m < localArrayList2.size())
      {
        JSONObject localJSONObject9 = new JSONObject();
        GeoPoint localGeoPoint1 = (GeoPoint)localArrayList2.get(m);
        if (localGeoPoint1 == null)
          continue;
        localJSONObject9.put("x", localGeoPoint1.getLongitudeE6());
        localJSONObject9.put("y", localGeoPoint1.getLatitudeE6());
        localJSONArray3.put(localJSONObject9);
      }
      else
      {
        localJSONObject8.put("pathPt", localJSONArray3);
        break label536;
        localJSONObject3.put("steps", localJSONArray2);
        localJSONArray1.put(localJSONObject3);
        i++;
        break;
        label733: JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("legs", localJSONArray1);
        localJSONObject1.put("routes", localJSONObject2);
        break label584;
        label764: n++;
        break label452;
      }
  }

  private static ArrayList<MKPoiInfo> b(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString == null) || ("".equals(paramString)));
    JSONArray localJSONArray;
    do
    {
      return null;
      localJSONArray = paramJSONObject.optJSONArray(paramString);
    }
    while (localJSONArray == null);
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localJSONArray.length(); i++)
    {
      JSONObject localJSONObject = localJSONArray.optJSONObject(i);
      MKPoiInfo localMKPoiInfo = new MKPoiInfo();
      localMKPoiInfo.address = localJSONObject.optString("addr");
      localMKPoiInfo.phoneNum = localJSONObject.optString("tel");
      localMKPoiInfo.uid = localJSONObject.optString("uid");
      localMKPoiInfo.postCode = localJSONObject.optString("zip");
      localMKPoiInfo.name = localJSONObject.optString("name");
      localMKPoiInfo.pt = g(localJSONObject, "point");
      localArrayList.add(localMKPoiInfo);
    }
    return localArrayList;
  }

  private static void b(String paramString, ArrayList<GeoPoint> paramArrayList1, ArrayList<GeoPoint> paramArrayList2)
  {
    com.baidu.platform.comapi.basestruct.a locala = com.baidu.platform.comjni.tools.a.a(paramString);
    if ((locala != null) && (locala.d != null))
    {
      ArrayList localArrayList1 = locala.d;
      if (localArrayList1.size() > 0)
      {
        ArrayList localArrayList2 = (ArrayList)localArrayList1.get(0);
        for (int i = 0; i < localArrayList2.size(); i++)
        {
          com.baidu.platform.comapi.basestruct.c localc = (com.baidu.platform.comapi.basestruct.c)localArrayList2.get(i);
          paramArrayList2.add(new GeoPoint(localc.b / 100, localc.a / 100));
          paramArrayList1.add(com.baidu.mapapi.utils.e.a(new GeoPoint(localc.b / 100, localc.a / 100)));
        }
      }
      paramArrayList1.trimToSize();
      paramArrayList2.trimToSize();
    }
  }

  public static boolean b(String paramString, MKAddrInfo paramMKAddrInfo)
  {
    if ((paramString == null) || ("".equals(paramString)) || (paramMKAddrInfo == null));
    while (true)
    {
      return false;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        if ((localJSONObject == null) || (localJSONObject.optInt("error") != 0))
          continue;
        paramMKAddrInfo.type = 0;
        paramMKAddrInfo.geoPt = com.baidu.mapapi.utils.e.a(new GeoPoint(localJSONObject.optInt("y"), localJSONObject.optInt("x")));
        paramMKAddrInfo.strAddr = localJSONObject.optString("addr");
        return true;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return false;
  }

  public static boolean b(String paramString, MKPoiResult paramMKPoiResult, int paramInt)
  {
    int i = 1;
    ArrayList localArrayList1 = new ArrayList();
    paramMKPoiResult.b(localArrayList1);
    try
    {
      JSONObject localJSONObject1 = new JSONObject(paramString);
      JSONArray localJSONArray1 = localJSONObject1.optJSONArray("content");
      JSONArray localJSONArray2 = localJSONObject1.optJSONArray("result");
      if ((localJSONArray1 != null) && (localJSONArray1.length() > 0) && (localJSONArray2 != null) && (localJSONArray2.length() > 0))
      {
        int j = 0;
        if (j < localJSONArray1.length())
        {
          MKPoiResult localMKPoiResult = new MKPoiResult();
          localArrayList1.add(localMKPoiResult);
          JSONArray localJSONArray3 = localJSONArray1.optJSONObject(j).optJSONArray("cont");
          if ((localJSONArray3 != null) && (localJSONArray3.length() > 0))
          {
            ArrayList localArrayList2 = new ArrayList();
            for (int k = 0; k < localJSONArray3.length(); k++)
            {
              JSONObject localJSONObject3 = localJSONArray3.optJSONObject(k);
              MKPoiInfo localMKPoiInfo = new MKPoiInfo();
              localMKPoiInfo.name = localJSONObject3.optString("name");
              localMKPoiInfo.address = localJSONObject3.optString("addr");
              localMKPoiInfo.pt = f(localJSONObject3, "geo");
              localMKPoiInfo.uid = localJSONObject3.optString("uid");
              localMKPoiInfo.phoneNum = localJSONObject3.optString("tel");
              localMKPoiInfo.ePoiType = localJSONObject3.optInt("type");
              localArrayList2.add(localMKPoiInfo);
            }
            localMKPoiResult.a(localArrayList2);
          }
          JSONObject localJSONObject2 = localJSONArray2.optJSONObject(j);
          int m = localJSONObject2.optInt("total");
          localMKPoiResult.b(m);
          localMKPoiResult.d(localJSONObject2.optInt("page_num"));
          int n = localJSONObject2.optInt("count");
          localMKPoiResult.a(n);
          localMKPoiResult.d(paramInt);
          int i1 = m / n;
          if (m % n > 0);
          for (int i2 = i; ; i2 = 0)
          {
            localMKPoiResult.c(i2 + i1);
            j++;
            break;
          }
        }
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      i = 0;
    }
    return i;
  }

  private static MKGeocoderAddressComponent c(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString == null) || ("".equals(paramString)))
      return null;
    JSONObject localJSONObject = paramJSONObject.optJSONObject(paramString);
    MKGeocoderAddressComponent localMKGeocoderAddressComponent = new MKGeocoderAddressComponent();
    localMKGeocoderAddressComponent.city = localJSONObject.optString("city");
    localMKGeocoderAddressComponent.district = localJSONObject.optString("district");
    localMKGeocoderAddressComponent.province = localJSONObject.optString("province");
    localMKGeocoderAddressComponent.street = localJSONObject.optString("street");
    localMKGeocoderAddressComponent.streetNumber = localJSONObject.optString("street_number");
    return localMKGeocoderAddressComponent;
  }

  public static String c(ArrayList<MKPoiInfo> paramArrayList)
  {
    if (paramArrayList == null)
      return null;
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("result_type", 11);
      JSONArray localJSONArray = new JSONArray();
      for (int i = 0; i < paramArrayList.size(); i++)
      {
        MKPoiInfo localMKPoiInfo = (MKPoiInfo)paramArrayList.get(i);
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("uid", localMKPoiInfo.uid);
        localJSONObject2.put("type", localMKPoiInfo.ePoiType);
        localJSONObject2.put("name", localMKPoiInfo.name);
        JSONObject localJSONObject3 = new JSONObject();
        if (localMKPoiInfo.pt != null)
        {
          GeoPoint localGeoPoint = com.baidu.mapapi.utils.e.b(localMKPoiInfo.pt);
          localJSONObject3.put("x", localGeoPoint.getLongitudeE6());
          localJSONObject3.put("y", localGeoPoint.getLatitudeE6());
          localJSONObject2.put("geopt", localJSONObject3);
        }
        localJSONArray.put(localJSONObject2);
      }
      if (paramArrayList.size() > 0)
        localJSONObject1.put("pois", localJSONArray);
      label197: return localJSONObject1.toString();
    }
    catch (JSONException localJSONException)
    {
      break label197;
    }
  }

  private static MKPlanNode d(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString == null) || ("".equals(paramString)))
      return null;
    JSONObject localJSONObject = paramJSONObject.optJSONObject(paramString);
    MKPlanNode localMKPlanNode = new MKPlanNode();
    localMKPlanNode.name = localJSONObject.optString("name");
    localMKPlanNode.pt = f(localJSONObject, "geo");
    return localMKPlanNode;
  }

  private static List<MKWpNode> e(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString == null) || ("".equals(paramString)));
    JSONArray localJSONArray;
    do
    {
      return null;
      localJSONArray = paramJSONObject.optJSONArray(paramString);
    }
    while (localJSONArray == null);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      MKWpNode localMKWpNode;
      if (i < localJSONArray.length())
        localMKWpNode = new MKWpNode();
      try
      {
        localMKWpNode.name = localJSONArray.getJSONObject(i).optString("name");
        if (localJSONArray.getJSONObject(i).has("bus_stop"))
          localMKWpNode.a = localJSONArray.getJSONObject(i).optBoolean("bus_stop");
        localMKWpNode.pt = f(localJSONArray.getJSONObject(i), "geo");
        localArrayList.add(localMKWpNode);
        label129: i++;
        continue;
        return localArrayList;
      }
      catch (JSONException localJSONException)
      {
        break label129;
      }
    }
  }

  private static GeoPoint f(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramJSONObject == null) || (paramString == null))
      return null;
    String str = paramJSONObject.optString(paramString);
    a.clear();
    a.putString("strkey", str);
    JNITools.TransGeoStr2Pt(a);
    GeoPoint localGeoPoint = new GeoPoint(0, 0);
    localGeoPoint.setLongitudeE6(a.getInt("ptx"));
    localGeoPoint.setLatitudeE6(a.getInt("pty"));
    return com.baidu.mapapi.utils.e.a(localGeoPoint);
  }

  private static GeoPoint g(JSONObject paramJSONObject, String paramString)
  {
    if ((paramJSONObject == null) || (paramString == null) || ("".equals(paramString)));
    JSONObject localJSONObject;
    do
    {
      return null;
      localJSONObject = paramJSONObject.optJSONObject(paramString);
    }
    while (localJSONObject == null);
    int i = localJSONObject.optInt("x");
    return com.baidu.mapapi.utils.e.a(new GeoPoint(localJSONObject.optInt("y"), i));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.c
 * JD-Core Version:    0.6.0
 */