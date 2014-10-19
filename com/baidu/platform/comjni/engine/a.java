package com.baidu.platform.comjni.engine;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static SparseArray<List<Handler>> a = new SparseArray();

  public static void a()
  {
    int i = a.size();
    for (int j = 0; j < i; j++)
    {
      List localList = (List)a.get(a.keyAt(j));
      if (localList == null)
        continue;
      localList.clear();
    }
    a.clear();
    a = null;
  }

  public static void a(int paramInt1, int paramInt2, int paramInt3)
  {
    List localList = (List)a.get(paramInt1);
    if ((localList != null) && (!localList.isEmpty()))
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
        Message.obtain((Handler)localIterator.next(), paramInt1, paramInt2, paramInt3, null).sendToTarget();
    }
  }

  public static void a(int paramInt, Handler paramHandler)
  {
    if (a == null)
      a = new SparseArray();
    List localList = (List)a.get(paramInt);
    if (localList != null)
    {
      if (!localList.contains(paramHandler))
        localList.add(paramHandler);
      return;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramHandler);
    a.put(paramInt, localArrayList);
  }

  public static void b(int paramInt, Handler paramHandler)
  {
    paramHandler.removeCallbacksAndMessages(null);
    List localList = (List)a.get(paramInt);
    if (localList != null)
      localList.remove(paramHandler);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.engine.a
 * JD-Core Version:    0.6.0
 */