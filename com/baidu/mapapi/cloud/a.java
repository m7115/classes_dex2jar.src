package com.baidu.mapapi.cloud;

import android.os.Handler;
import android.os.Message;

final class a extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    if (((paramMessage.arg1 == 50) || (paramMessage.arg1 == 51)) && (GeoSearchManager.b != null))
    {
      GeoSearchManager.b.a(paramMessage);
      super.handleMessage(paramMessage);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.cloud.a
 * JD-Core Version:    0.6.0
 */