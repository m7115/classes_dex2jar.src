package com.baidu.mapapi.search;

import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import java.util.Hashtable;

final class f extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    }
    j localj1;
    ImageView localImageView;
    do
    {
      do
      {
        j localj2;
        do
        {
          return;
          localj2 = (j)paramMessage.obj;
        }
        while (PlaceCaterActivity.c == null);
        PlaceCaterActivity.c.setImageBitmap(localj2.a());
        return;
        localj1 = (j)paramMessage.obj;
      }
      while (PlaceCaterActivity.q == null);
      localImageView = (ImageView)PlaceCaterActivity.q.get(Integer.valueOf(paramMessage.arg1));
    }
    while (localImageView == null);
    localImageView.setImageBitmap(localj1.a());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.f
 * JD-Core Version:    0.6.0
 */