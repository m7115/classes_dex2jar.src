package com.baidu.platform.comapi.map;

import android.opengl.GLSurfaceView.Renderer;
import com.baidu.platform.comapi.d.c;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MapRenderer
  implements GLSurfaceView.Renderer
{
  public static int a;
  public static int b;
  public static int c = 0;
  public static int d = 2048;
  public static int e = 14336;
  public static int f = 8192;
  private int g = 0;

  public MapRenderer(int paramInt)
  {
    this.g = paramInt;
  }

  private static native void nativeInit();

  private static native void nativeRender(int paramInt);

  private static native void nativeResize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);

  public void onDrawFrame(GL10 paramGL10)
  {
    if (c <= 1)
    {
      nativeResize(a, b, d, e, f);
      c = 1 + c;
    }
    nativeRender(this.g);
  }

  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    nativeResize(paramInt1, paramInt2, d, e, f);
  }

  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    nativeInit();
    String str1 = paramGL10.glGetString(7938);
    if ((str1.equalsIgnoreCase("OpenGL ES-CM 1.0")) || (str1.equalsIgnoreCase("OpenGL ES 1.0-CM")))
    {
      d = 256;
      e = 1152;
      f = 640;
    }
    String str2 = paramGL10.glGetString(7937);
    if ((c.t() != null) && (c.t().equals(str1)) && (c.s() != null) && (c.s().equals(str2)))
      return;
    c.a(str1, str2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.MapRenderer
 * JD-Core Version:    0.6.0
 */