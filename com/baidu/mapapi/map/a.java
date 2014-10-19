package com.baidu.mapapi.map;

class a
{
  private MKOfflineMapListener a;

  public a(MKOfflineMapListener paramMKOfflineMapListener)
  {
    this.a = paramMKOfflineMapListener;
  }

  public void a(MKEvent paramMKEvent)
  {
    if ((this.a != null) && (com.baidu.platform.comapi.a.a));
    switch (paramMKEvent.a)
    {
    default:
      return;
    case 4:
      this.a.onGetOfflineMapState(paramMKEvent.a, paramMKEvent.c);
      return;
    case 6:
      this.a.onGetOfflineMapState(paramMKEvent.a, paramMKEvent.c);
      return;
    case 0:
    }
    this.a.onGetOfflineMapState(paramMKEvent.a, paramMKEvent.c);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.a
 * JD-Core Version:    0.6.0
 */