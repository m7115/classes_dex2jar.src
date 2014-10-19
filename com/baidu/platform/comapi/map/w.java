package com.baidu.platform.comapi.map;

import android.os.Bundle;
import android.util.SparseArray;
import com.baidu.platform.comjni.map.basemap.BaseMapCallback;
import com.baidu.platform.comjni.map.basemap.a;

public class w extends BaseMapCallback
{
  SparseArray<d> a = new SparseArray();
  o b = null;

  public w(o paramo)
  {
    this.b = paramo;
  }

  public int ReqLayerData(Bundle paramBundle, int paramInt1, int paramInt2)
  {
    d locald = (d)this.a.get(paramInt1);
    if (locald != null)
    {
      paramBundle.putString("jsondata", locald.b());
      Bundle localBundle = locald.c();
      if (localBundle != null)
        paramBundle.putBundle("param", localBundle);
      return locald.e();
    }
    return 0;
  }

  public void a(int paramInt)
  {
    if (this.b != null)
      this.b.b().c(paramInt);
    this.a.remove(paramInt);
  }

  public void a(int paramInt, d paramd)
  {
    this.a.put(paramInt, paramd);
    paramd.a(paramInt, this.b);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.w
 * JD-Core Version:    0.6.0
 */