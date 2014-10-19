package com.baidu.mapapi;

import android.os.Handler;
import android.os.Message;
import com.baidu.mapapi.utils.PermissionCheck;

class a extends Handler
{
  a(BMapManager paramBMapManager)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (paramMessage.what == 2010)
      switch (paramMessage.arg2)
      {
      case 3:
      default:
        if (this.a.a == null)
          break;
        this.a.a.onGetPermissionState(300);
      case 0:
      case 2:
      case 1:
      case 4:
      }
    do
    {
      do
      {
        do
        {
          return;
          PermissionCheck.check();
        }
        while ((BMapManager.a(this.a)) || (this.a.a == null));
        BMapManager.a(this.a, true);
        this.a.a.onGetNetworkState(2);
        return;
      }
      while (paramMessage.what == 65289);
      if ((paramMessage.arg2 != 3) || (this.a.a == null))
        continue;
      this.a.a.onGetNetworkState(3);
    }
    while (((paramMessage.arg2 != 2) && (paramMessage.arg2 != 404) && (paramMessage.arg2 != 5)) || (this.a.a == null));
    this.a.a.onGetNetworkState(2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.a
 * JD-Core Version:    0.6.0
 */