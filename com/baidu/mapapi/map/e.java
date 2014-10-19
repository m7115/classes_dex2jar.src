package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.baidu.mapapi.utils.b;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class e
  implements com.baidu.platform.comapi.map.a
{
  e(MapView paramMapView)
  {
  }

  public void a(boolean paramBoolean)
  {
    if (paramBoolean);
    do
      try
      {
        Bitmap localBitmap = BitmapFactory.decodeStream(new FileInputStream(b.h() + "/BaiduMapSDK/capture.png"));
        if ((MapView.a(this.a) != null) && (com.baidu.platform.comapi.a.a))
          MapView.a(this.a).onGetCurrentMap(localBitmap);
        return;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        MapView.a(this.a).onGetCurrentMap(null);
        return;
      }
    while ((MapView.a(this.a) == null) || (!com.baidu.platform.comapi.a.a));
    MapView.a(this.a).onGetCurrentMap(null);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.e
 * JD-Core Version:    0.6.0
 */