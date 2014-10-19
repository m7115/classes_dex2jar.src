package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;

public class a
{
  private int a = 0;
  private JNIBaseMap b = null;
  private BaseMapCallback c = null;

  public static int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return JNIBaseMap.MapProc(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public int a(int paramInt1, int paramInt2, String paramString)
  {
    return this.b.AddLayer(this.a, paramInt1, paramInt2, paramString);
  }

  public String a(int paramInt1, int paramInt2)
  {
    return this.b.ScrPtToGeoPoint(this.a, paramInt1, paramInt2);
  }

  public String a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return this.b.GetNearlyObjID(this.a, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void a(int paramInt)
  {
    this.b.UpdateLayers(this.a, paramInt);
  }

  public void a(int paramInt, boolean paramBoolean)
  {
    this.b.ShowLayers(this.a, paramInt, paramBoolean);
  }

  public void a(Bundle paramBundle)
  {
    this.b.SetMapStatus(this.a, paramBundle);
  }

  public void a(String paramString)
  {
    this.b.SaveScreenToLocal(this.a, paramString);
  }

  public void a(boolean paramBoolean)
  {
    this.b.ShowSatelliteMap(this.a, paramBoolean);
  }

  public boolean a()
  {
    this.a = this.b.Create();
    this.b.SetCallback(this.a, this.c);
    return true;
  }

  public boolean a(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return this.b.OnRecordStart(this.a, paramInt1, paramBoolean, paramInt2);
  }

  public boolean a(BaseMapCallback paramBaseMapCallback)
  {
    if (paramBaseMapCallback == null)
      return false;
    return this.c.SetMapCallback(paramBaseMapCallback);
  }

  public boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    return this.b.Init(this.a, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
  }

  public int b(int paramInt)
  {
    return this.b.RemoveLayer(this.a, paramInt);
  }

  public String b(int paramInt1, int paramInt2)
  {
    return this.b.GeoPtToScrPoint(this.a, paramInt1, paramInt2);
  }

  public String b(String paramString)
  {
    return this.b.OnSchcityGet(this.a, paramString);
  }

  public void b(int paramInt, boolean paramBoolean)
  {
    this.b.SetLayersClickable(this.a, paramInt, paramBoolean);
  }

  public void b(Bundle paramBundle)
  {
    this.b.AddPopupData(this.a, paramBundle);
  }

  public void b(boolean paramBoolean)
  {
    this.b.ShowTrafficMap(this.a, paramBoolean);
  }

  public boolean b()
  {
    this.b.Release(this.a);
    return true;
  }

  public boolean b(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return this.b.OnRecordSuspend(this.a, paramInt1, paramBoolean, paramInt2);
  }

  public int c()
  {
    return this.a;
  }

  public void c(int paramInt)
  {
    this.b.ClearLayer(this.a, paramInt);
  }

  public void c(int paramInt1, int paramInt2)
  {
    this.b.MoveToScrPoint(this.a, paramInt1, paramInt2);
  }

  public void c(Bundle paramBundle)
  {
    this.b.AddItemData(this.a, paramBundle);
  }

  public boolean c(int paramInt, boolean paramBoolean)
  {
    return this.b.OnRecordRemove(this.a, paramInt, paramBoolean);
  }

  public boolean c(boolean paramBoolean)
  {
    return this.b.OnRecordImport(this.a, paramBoolean);
  }

  public void d()
  {
    this.b.OnPause(this.a);
  }

  public boolean d(int paramInt)
  {
    return this.b.OnRecordAdd(this.a, paramInt);
  }

  public boolean d(Bundle paramBundle)
  {
    return this.b.RemoveItemData(this.a, paramBundle);
  }

  public String e(int paramInt)
  {
    return this.b.OnRecordGetAt(this.a, paramInt);
  }

  public void e()
  {
    this.b.OnResume(this.a);
  }

  public void e(Bundle paramBundle)
  {
    this.b.AddLogoData(this.a, paramBundle);
  }

  public int f(Bundle paramBundle)
  {
    return this.b.AddGeometryData(this.a, paramBundle);
  }

  public void f()
  {
    this.b.ResetImageRes(this.a);
  }

  public Bundle g()
  {
    return this.b.GetMapStatus(this.a);
  }

  public boolean g(Bundle paramBundle)
  {
    return this.b.RemoveGeometryData(this.a, paramBundle);
  }

  public int h(Bundle paramBundle)
  {
    return this.b.AddTextData(this.a, paramBundle);
  }

  public String h()
  {
    return this.b.OnRecordGetAll(this.a);
  }

  public String i()
  {
    return this.b.OnHotcityGet(this.a);
  }

  public boolean i(Bundle paramBundle)
  {
    return this.b.RemoveTextData(this.a, paramBundle);
  }

  public void j()
  {
    this.b.PostStatInfo(this.a);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.basemap.a
 * JD-Core Version:    0.6.0
 */