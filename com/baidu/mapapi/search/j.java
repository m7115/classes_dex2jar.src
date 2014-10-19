package com.baidu.mapapi.search;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class j
{
  private Bitmap a;

  public j()
  {
  }

  public j(byte[] paramArrayOfByte)
  {
    try
    {
      this.a = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localOutOfMemoryError.printStackTrace();
    }
  }

  public Bitmap a()
  {
    return this.a;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.j
 * JD-Core Version:    0.6.0
 */