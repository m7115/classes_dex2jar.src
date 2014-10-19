package com.baidu.mapapi.utils;

import com.baidu.mapapi.map.MKOLSearchRecord;
import com.baidu.mapapi.map.MKOLUpdateElement;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.basestruct.c;
import com.baidu.platform.comapi.map.g;
import com.baidu.platform.comapi.map.j;
import java.util.ArrayList;
import java.util.Iterator;

public class h
{
  public static MKOLSearchRecord a(g paramg)
  {
    if (paramg == null)
      return null;
    MKOLSearchRecord localMKOLSearchRecord = new MKOLSearchRecord();
    localMKOLSearchRecord.cityID = paramg.a;
    localMKOLSearchRecord.cityName = paramg.b;
    localMKOLSearchRecord.cityType = paramg.d;
    if (paramg.a() != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramg.a().iterator();
      int j;
      for (i = 0; localIterator.hasNext(); i = j)
      {
        g localg = (g)localIterator.next();
        localArrayList.add(a(localg));
        j = i + localg.c;
        localMKOLSearchRecord.childCities = localArrayList;
      }
    }
    int i = 0;
    if (localMKOLSearchRecord.cityType == 1);
    for (localMKOLSearchRecord.size = i; ; localMKOLSearchRecord.size = paramg.c)
      return localMKOLSearchRecord;
  }

  public static MKOLUpdateElement a(j paramj)
  {
    if (paramj == null)
      return null;
    MKOLUpdateElement localMKOLUpdateElement = new MKOLUpdateElement();
    localMKOLUpdateElement.cityID = paramj.a;
    localMKOLUpdateElement.cityName = paramj.b;
    if (paramj.g != null)
      localMKOLUpdateElement.geoPt = e.a(new GeoPoint(paramj.g.b(), paramj.g.a()));
    localMKOLUpdateElement.level = paramj.e;
    localMKOLUpdateElement.ratio = paramj.i;
    localMKOLUpdateElement.serversize = paramj.h;
    if (paramj.i == 100);
    for (localMKOLUpdateElement.size = paramj.h; ; localMKOLUpdateElement.size = (paramj.h * paramj.i / 100))
    {
      localMKOLUpdateElement.status = paramj.l;
      localMKOLUpdateElement.update = paramj.j;
      return localMKOLUpdateElement;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.h
 * JD-Core Version:    0.6.0
 */