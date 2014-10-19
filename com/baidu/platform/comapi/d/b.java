package com.baidu.platform.comapi.d;

import android.os.Environment;

public class b
{
  public static int a()
  {
    String str = Environment.getExternalStorageState();
    if (str.equals("bad_removal"))
      return 2;
    if (str.equals("checking"));
    do
    {
      return 0;
      if (str.equals("mounted"))
        return 0;
      if ((str.equals("mounted_ro")) || (str.equals("nofs")))
        break;
      if (str.equals("removed"))
        return 3;
      if (str.equals("shared"))
        return 3;
      if (str.equals("unmountable"))
        break;
    }
    while (!str.equals("unmounted"));
    return 3;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.d.b
 * JD-Core Version:    0.6.0
 */