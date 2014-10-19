package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;

public class BaseMapCallback
{
  private BaseMapCallback a = null;

  public int ReqLayerData(Bundle paramBundle, int paramInt1, int paramInt2)
  {
    if (this.a == null)
      return 0;
    return this.a.ReqLayerData(paramBundle, paramInt1, paramInt2);
  }

  public boolean SetMapCallback(BaseMapCallback paramBaseMapCallback)
  {
    if (paramBaseMapCallback == null)
      return false;
    this.a = paramBaseMapCallback;
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.basemap.BaseMapCallback
 * JD-Core Version:    0.6.0
 */