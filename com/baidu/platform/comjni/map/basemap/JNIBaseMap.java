package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;

public class JNIBaseMap
{
  public static native int MapProc(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public native int AddGeometryData(int paramInt, Bundle paramBundle);

  public native void AddItemData(int paramInt, Bundle paramBundle);

  public native int AddLayer(int paramInt1, int paramInt2, int paramInt3, String paramString);

  public native void AddLogoData(int paramInt, Bundle paramBundle);

  public native void AddPopupData(int paramInt, Bundle paramBundle);

  public native int AddTextData(int paramInt, Bundle paramBundle);

  public native void ClearLayer(int paramInt1, int paramInt2);

  public native int Create();

  public native String GeoPtToScrPoint(int paramInt1, int paramInt2, int paramInt3);

  public native Bundle GetMapStatus(int paramInt);

  public native String GetNearlyObjID(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);

  public native boolean Init(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);

  public native void MoveToScrPoint(int paramInt1, int paramInt2, int paramInt3);

  public native String OnHotcityGet(int paramInt);

  public native void OnPause(int paramInt);

  public native boolean OnRecordAdd(int paramInt1, int paramInt2);

  public native String OnRecordGetAll(int paramInt);

  public native String OnRecordGetAt(int paramInt1, int paramInt2);

  public native boolean OnRecordImport(int paramInt, boolean paramBoolean);

  public native boolean OnRecordRemove(int paramInt1, int paramInt2, boolean paramBoolean);

  public native boolean OnRecordStart(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3);

  public native boolean OnRecordSuspend(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3);

  public native void OnResume(int paramInt);

  public native String OnSchcityGet(int paramInt, String paramString);

  public native void PostStatInfo(int paramInt);

  public native int Release(int paramInt);

  public native boolean RemoveGeometryData(int paramInt, Bundle paramBundle);

  public native boolean RemoveItemData(int paramInt, Bundle paramBundle);

  public native int RemoveLayer(int paramInt1, int paramInt2);

  public native boolean RemoveTextData(int paramInt, Bundle paramBundle);

  public native void ResetImageRes(int paramInt);

  public native void SaveScreenToLocal(int paramInt, String paramString);

  public native String ScrPtToGeoPoint(int paramInt1, int paramInt2, int paramInt3);

  public native int SetCallback(int paramInt, BaseMapCallback paramBaseMapCallback);

  public native void SetLayersClickable(int paramInt1, int paramInt2, boolean paramBoolean);

  public native void SetMapStatus(int paramInt, Bundle paramBundle);

  public native void ShowLayers(int paramInt1, int paramInt2, boolean paramBoolean);

  public native void ShowSatelliteMap(int paramInt, boolean paramBoolean);

  public native void ShowTrafficMap(int paramInt, boolean paramBoolean);

  public native void UpdateLayers(int paramInt1, int paramInt2);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.basemap.JNIBaseMap
 * JD-Core Version:    0.6.0
 */