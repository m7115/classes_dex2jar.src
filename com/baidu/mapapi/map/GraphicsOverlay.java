package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comjni.map.basemap.a;
import java.util.ArrayList;

public class GraphicsOverlay extends Overlay
{
  private Bundle a = null;
  private MapView b = null;
  private ArrayList<Graphic> c = null;
  private boolean d = false;

  public GraphicsOverlay(MapView paramMapView)
  {
    this.mType = 29;
    this.b = paramMapView;
    this.c = new ArrayList();
  }

  void a()
  {
    this.mLayerID = this.b.a("geometry");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not add geometry layer");
  }

  void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  int b()
  {
    return this.mLayerID;
  }

  void c()
  {
    this.b.getController().a.b().c(b());
    int i = 0;
    if (i < this.c.size())
    {
      Graphic localGraphic = (Graphic)this.c.get(i);
      ArrayList localArrayList = localGraphic.getGeometry().b;
      if ((localArrayList == null) || (localArrayList.size() == 0));
      int j;
      do
      {
        i++;
        break;
        if ((localGraphic.getGeometry().a != 2) && (localGraphic.getGeometry().a != 5))
          break label450;
        j = localArrayList.size();
      }
      while (j < 2);
      int[] arrayOfInt1 = new int[j];
      int[] arrayOfInt2 = new int[j];
      int k = 0;
      if (k < j)
      {
        if (localArrayList.get(k) == null);
        while (true)
        {
          k++;
          break;
          GeoPoint localGeoPoint1 = e.b((GeoPoint)localArrayList.get(k));
          arrayOfInt1[k] = localGeoPoint1.getLongitudeE6();
          arrayOfInt2[k] = localGeoPoint1.getLatitudeE6();
        }
      }
      this.a.putIntArray("x", arrayOfInt1);
      this.a.putIntArray("y", arrayOfInt2);
      label200: this.a.putInt("linewidth", localGraphic.getSymbol().a);
      this.a.putFloat("red", localGraphic.getSymbol().b.red / 255.0F);
      this.a.putFloat("green", localGraphic.getSymbol().b.green / 255.0F);
      this.a.putFloat("blue", localGraphic.getSymbol().b.blue / 255.0F);
      this.a.putFloat("alpha", localGraphic.getSymbol().b.alpha / 255.0F);
      if (localGraphic.getGeometry().a == 5)
      {
        this.a.putInt("type", 2);
        label329: if (localGraphic.getGeometry().a != 5)
          break label817;
        this.a.putInt("status", 1);
        label350: if ((localGraphic.getGeometry().a != 4) && (localGraphic.getGeometry().a != 1))
          break label860;
        this.a.putInt("level", localGraphic.getGeometry().c);
      }
      while (true)
      {
        this.a.putInt("geometryaddr", b());
        long l = System.currentTimeMillis();
        this.a.putString("id", String.valueOf(l));
        this.b.getController().a.b().f(this.a);
        localGraphic.a(l);
        break;
        label450: if (localGraphic.getGeometry().a == 3)
        {
          if ((localArrayList.size() < 2) || (localArrayList.get(0) == null) || (localArrayList.get(1) == null))
            break;
          int[] arrayOfInt5 = new int[5];
          int[] arrayOfInt6 = new int[5];
          GeoPoint localGeoPoint3 = e.b((GeoPoint)localArrayList.get(0));
          arrayOfInt5[0] = localGeoPoint3.getLongitudeE6();
          arrayOfInt6[0] = localGeoPoint3.getLatitudeE6();
          GeoPoint localGeoPoint4 = e.b(new GeoPoint(((GeoPoint)localArrayList.get(0)).getLatitudeE6(), ((GeoPoint)localArrayList.get(1)).getLongitudeE6()));
          arrayOfInt5[1] = localGeoPoint4.getLongitudeE6();
          arrayOfInt6[1] = localGeoPoint4.getLatitudeE6();
          GeoPoint localGeoPoint5 = e.b((GeoPoint)localArrayList.get(1));
          arrayOfInt5[2] = localGeoPoint5.getLongitudeE6();
          arrayOfInt6[2] = localGeoPoint5.getLatitudeE6();
          GeoPoint localGeoPoint6 = e.b(new GeoPoint(((GeoPoint)localArrayList.get(1)).getLatitudeE6(), ((GeoPoint)localArrayList.get(0)).getLongitudeE6()));
          arrayOfInt5[3] = localGeoPoint6.getLongitudeE6();
          arrayOfInt6[3] = localGeoPoint6.getLatitudeE6();
          arrayOfInt5[4] = arrayOfInt5[0];
          arrayOfInt6[4] = arrayOfInt6[0];
          this.a.putIntArray("x", arrayOfInt5);
          this.a.putIntArray("y", arrayOfInt6);
          break label200;
        }
        if (((localGraphic.getGeometry().a != 4) && (localGraphic.getGeometry().a != 1)) || (localArrayList.get(0) == null))
          break;
        int[] arrayOfInt3 = new int[1];
        int[] arrayOfInt4 = new int[1];
        GeoPoint localGeoPoint2 = e.b((GeoPoint)localArrayList.get(0));
        arrayOfInt3[0] = localGeoPoint2.getLongitudeE6();
        arrayOfInt4[0] = localGeoPoint2.getLatitudeE6();
        this.a.putIntArray("x", arrayOfInt3);
        this.a.putIntArray("y", arrayOfInt4);
        break label200;
        this.a.putInt("type", localGraphic.getGeometry().a);
        break label329;
        label817: if (localGraphic.getGeometry().a == 2)
        {
          this.a.putInt("status", 0);
          break label350;
        }
        this.a.putInt("status", localGraphic.getSymbol().c);
        break label350;
        label860: this.a.putInt("level", (int)this.b.getController().a.l());
      }
    }
    this.d = true;
  }

  boolean d()
  {
    return this.d;
  }

  public ArrayList<Graphic> getAllGraphics()
  {
    return this.c;
  }

  public void removeAll()
  {
    this.b.getController().a.b().c(b());
    this.c.clear();
    this.d = true;
  }

  public void removeGraphic(long paramLong)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("geometryaddr", b());
    localBundle.putString("id", String.valueOf(paramLong));
    this.b.getController().a.b().g(localBundle);
    for (int i = 0; i < this.c.size(); i++)
    {
      if (paramLong != ((Graphic)this.c.get(i)).getID())
        continue;
      this.c.remove(i);
    }
    this.d = true;
  }

  public long setData(Graphic paramGraphic)
  {
    this.a.clear();
    if ((paramGraphic == null) || (paramGraphic.getGeometry() == null) || (paramGraphic.getSymbol() == null))
      return 0L;
    Geometry localGeometry = new Geometry();
    localGeometry.a = paramGraphic.getGeometry().a;
    localGeometry.b = paramGraphic.getGeometry().b;
    localGeometry.c = paramGraphic.getGeometry().c;
    Symbol localSymbol = new Symbol();
    localSymbol.b = paramGraphic.getSymbol().b;
    localSymbol.a = paramGraphic.getSymbol().a;
    localSymbol.c = paramGraphic.getSymbol().c;
    Graphic localGraphic = new Graphic(localGeometry, localSymbol);
    if (b() == 0)
    {
      this.c.add(localGraphic);
      return 0L;
    }
    ArrayList localArrayList = paramGraphic.getGeometry().b;
    if ((localArrayList == null) || (localArrayList.size() == 0))
      return 0L;
    int i = paramGraphic.getSymbol().a;
    if (paramGraphic.getSymbol().b == null)
      return 0L;
    float f1 = paramGraphic.getSymbol().b.red / 255.0F;
    float f2 = paramGraphic.getSymbol().b.green / 255.0F;
    float f3 = paramGraphic.getSymbol().b.blue / 255.0F;
    float f4 = paramGraphic.getSymbol().b.alpha / 255.0F;
    int j = paramGraphic.getSymbol().c;
    int k = paramGraphic.getGeometry().c;
    if ((f1 < 0.0F) || (f1 > 255.0F) || (f2 < 0.0F) || (f2 > 255.0F) || (f3 < 0.0F) || (f3 > 255.0F) || (f4 < 0.0F) || (f4 > 255.0F))
      return 0L;
    if ((paramGraphic.getGeometry().a == 2) || (paramGraphic.getGeometry().a == 5))
    {
      int m = localArrayList.size();
      if (m < 2)
        return 0L;
      if ((i <= 0) && (paramGraphic.getGeometry().a == 2))
        return 0L;
      int[] arrayOfInt1 = new int[m];
      int[] arrayOfInt2 = new int[m];
      for (int n = 0; n < m; n++)
      {
        if (localArrayList.get(n) == null)
          return 0L;
        GeoPoint localGeoPoint1 = e.b((GeoPoint)localArrayList.get(n));
        arrayOfInt1[n] = localGeoPoint1.getLongitudeE6();
        arrayOfInt2[n] = localGeoPoint1.getLatitudeE6();
      }
      this.a.putIntArray("x", arrayOfInt1);
      this.a.putIntArray("y", arrayOfInt2);
      this.a.putInt("linewidth", i);
      this.a.putFloat("red", f1);
      this.a.putFloat("green", f2);
      this.a.putFloat("blue", f3);
      this.a.putFloat("alpha", f4);
      if (paramGraphic.getGeometry().a != 5)
        break label1099;
      this.a.putInt("type", 2);
      label556: if (paramGraphic.getGeometry().a != 5)
        break label1118;
      this.a.putInt("status", 1);
      label577: if ((paramGraphic.getGeometry().a != 4) && (paramGraphic.getGeometry().a != 1))
        break label1161;
      this.a.putInt("level", k);
    }
    while (true)
    {
      this.a.putInt("geometryaddr", b());
      long l = System.currentTimeMillis();
      this.a.putString("id", String.valueOf(l));
      this.b.getController().a.b().f(this.a);
      paramGraphic.a(l);
      localGraphic.a(l);
      this.c.add(localGraphic);
      this.d = true;
      return l;
      if (paramGraphic.getGeometry().a == 3)
      {
        if (localArrayList.size() < 2)
          return 0L;
        if ((localArrayList.get(0) == null) || (localArrayList.get(1) == null))
          return 0L;
        if ((i <= 0) || ((j != 0) && (j != 1)))
          return 0L;
        int[] arrayOfInt5 = new int[5];
        int[] arrayOfInt6 = new int[5];
        GeoPoint localGeoPoint3 = e.b((GeoPoint)localArrayList.get(0));
        arrayOfInt5[0] = localGeoPoint3.getLongitudeE6();
        arrayOfInt6[0] = localGeoPoint3.getLatitudeE6();
        GeoPoint localGeoPoint4 = e.b(new GeoPoint(((GeoPoint)localArrayList.get(0)).getLatitudeE6(), ((GeoPoint)localArrayList.get(1)).getLongitudeE6()));
        arrayOfInt5[1] = localGeoPoint4.getLongitudeE6();
        arrayOfInt6[1] = localGeoPoint4.getLatitudeE6();
        GeoPoint localGeoPoint5 = e.b((GeoPoint)localArrayList.get(1));
        arrayOfInt5[2] = localGeoPoint5.getLongitudeE6();
        arrayOfInt6[2] = localGeoPoint5.getLatitudeE6();
        GeoPoint localGeoPoint6 = e.b(new GeoPoint(((GeoPoint)localArrayList.get(1)).getLatitudeE6(), ((GeoPoint)localArrayList.get(0)).getLongitudeE6()));
        arrayOfInt5[3] = localGeoPoint6.getLongitudeE6();
        arrayOfInt6[3] = localGeoPoint6.getLatitudeE6();
        arrayOfInt5[4] = arrayOfInt5[0];
        arrayOfInt6[4] = arrayOfInt6[0];
        this.a.putIntArray("x", arrayOfInt5);
        this.a.putIntArray("y", arrayOfInt6);
        break;
      }
      if ((paramGraphic.getGeometry().a == 4) || (paramGraphic.getGeometry().a == 1))
      {
        if (localArrayList.get(0) == null)
          return 0L;
        if ((j != 0) && (j != 1))
          return 0L;
        if (k <= 0)
          return 0L;
        int[] arrayOfInt3 = new int[1];
        int[] arrayOfInt4 = new int[1];
        GeoPoint localGeoPoint2 = e.b((GeoPoint)localArrayList.get(0));
        arrayOfInt3[0] = localGeoPoint2.getLongitudeE6();
        arrayOfInt4[0] = localGeoPoint2.getLatitudeE6();
        this.a.putIntArray("x", arrayOfInt3);
        this.a.putIntArray("y", arrayOfInt4);
        break;
      }
      return 0L;
      label1099: this.a.putInt("type", paramGraphic.getGeometry().a);
      break label556;
      label1118: if (paramGraphic.getGeometry().a == 2)
      {
        this.a.putInt("status", 0);
        break label577;
      }
      this.a.putInt("status", paramGraphic.getSymbol().c);
      break label577;
      label1161: this.a.putInt("level", (int)this.b.getController().a.l());
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.GraphicsOverlay
 * JD-Core Version:    0.6.0
 */