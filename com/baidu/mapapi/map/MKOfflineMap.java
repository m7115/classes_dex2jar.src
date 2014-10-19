package com.baidu.mapapi.map;

import com.baidu.platform.comapi.map.g;
import com.baidu.platform.comapi.map.j;
import com.baidu.platform.comapi.map.k;
import com.baidu.platform.comapi.map.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MKOfflineMap
{
  public static final int TYPE_DOWNLOAD_UPDATE = 0;
  public static final int TYPE_NEW_OFFLINE = 6;
  public static final int TYPE_VER_UPDATE = 4;
  private com.baidu.platform.comapi.map.h a = null;
  private a b = null;
  private a c = null;

  public void destroy()
  {
    this.a.d(0);
    this.a.b(null);
    com.baidu.platform.comapi.map.h.a();
  }

  public ArrayList<MKOLUpdateElement> getAllUpdateInfo()
  {
    ArrayList localArrayList1 = this.a.d();
    if (localArrayList1 == null)
      return null;
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
      localArrayList2.add(com.baidu.mapapi.utils.h.a(((k)localIterator.next()).a()));
    return localArrayList2;
  }

  public ArrayList<MKOLSearchRecord> getHotCityList()
  {
    ArrayList localArrayList1 = this.a.b();
    if (localArrayList1 == null)
      return null;
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
      localArrayList2.add(com.baidu.mapapi.utils.h.a((g)localIterator.next()));
    return localArrayList2;
  }

  public ArrayList<MKOLSearchRecord> getOfflineCityList()
  {
    ArrayList localArrayList1 = this.a.c();
    if (localArrayList1 == null)
      return null;
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
      localArrayList2.add(com.baidu.mapapi.utils.h.a((g)localIterator.next()));
    return localArrayList2;
  }

  public MKOLUpdateElement getUpdateInfo(int paramInt)
  {
    k localk = this.a.f(paramInt);
    if (localk == null)
      return null;
    return com.baidu.mapapi.utils.h.a(localk.a());
  }

  public boolean init(MapController paramMapController, MKOfflineMapListener paramMKOfflineMapListener)
  {
    if (paramMapController == null);
    do
    {
      return false;
      this.a = com.baidu.platform.comapi.map.h.a(paramMapController.a);
    }
    while (this.a == null);
    this.c = new a();
    this.a.a(this.c);
    this.b = new a(paramMKOfflineMapListener);
    this.c.a(4, 0);
    return true;
  }

  public boolean pause(int paramInt)
  {
    return this.a.c(paramInt);
  }

  public boolean remove(int paramInt)
  {
    return this.a.e(paramInt);
  }

  public int scan()
  {
    return scan(false);
  }

  public int scan(boolean paramBoolean)
  {
    ArrayList localArrayList1 = this.a.d();
    int i;
    if (localArrayList1 != null)
      i = localArrayList1.size();
    for (int j = i; ; j = 0)
    {
      this.a.a(paramBoolean);
      ArrayList localArrayList2 = this.a.d();
      if (localArrayList2 != null)
        i = localArrayList2.size();
      return i - j;
      i = 0;
    }
  }

  public ArrayList<MKOLSearchRecord> searchCity(String paramString)
  {
    ArrayList localArrayList1 = this.a.a(paramString);
    if (localArrayList1 == null)
      return null;
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localArrayList1.iterator();
    while (localIterator.hasNext())
      localArrayList2.add(com.baidu.mapapi.utils.h.a((g)localIterator.next()));
    return localArrayList2;
  }

  public boolean start(int paramInt)
  {
    if (this.a == null)
      return false;
    if (this.a.d() != null)
    {
      Iterator localIterator = this.a.d().iterator();
      while (localIterator.hasNext())
      {
        k localk = (k)localIterator.next();
        if (localk.a.a != paramInt)
          continue;
        if ((localk.a.j) || (localk.a.l == 2) || (localk.a.l == 3))
          return this.a.b(paramInt);
        return false;
      }
    }
    return this.a.a(paramInt);
  }

  class a
    implements l
  {
    a()
    {
    }

    public void a(int paramInt1, int paramInt2)
    {
      switch (paramInt1)
      {
      case 5:
      case 7:
      default:
      case 6:
      case 4:
        ArrayList localArrayList;
        do
        {
          return;
          MKOfflineMap.a(MKOfflineMap.this).a(new MKEvent(6, 0, paramInt2));
          return;
          localArrayList = MKOfflineMap.this.getAllUpdateInfo();
        }
        while (localArrayList == null);
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          MKOLUpdateElement localMKOLUpdateElement = (MKOLUpdateElement)localIterator.next();
          if (!localMKOLUpdateElement.update)
            continue;
          MKOfflineMap.a(MKOfflineMap.this).a(new MKEvent(4, 0, localMKOLUpdateElement.cityID));
        }
      case 8:
      }
      int i = 0xFFFF & paramInt2 >> 16;
      MKOfflineMap.a(MKOfflineMap.this).a(new MKEvent(0, 0, i));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MKOfflineMap
 * JD-Core Version:    0.6.0
 */