package com.baidu.mapapi.map;

import android.graphics.drawable.Drawable;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class OverlayItem
{
  public static final int ALIGN_BOTTON = 2;
  public static final int ALIGN_TOP = 3;
  public static final int ALING_CENTER = 1;
  private int a;
  private Drawable b;
  private String c;
  private float d;
  private float e;
  protected GeoPoint mPoint;
  protected String mSnippet;
  protected String mTitle;

  public OverlayItem(GeoPoint paramGeoPoint, String paramString1, String paramString2)
  {
    this.mPoint = paramGeoPoint;
    this.mTitle = paramString1;
    this.mSnippet = paramString2;
    this.b = null;
    this.a = 2;
    this.c = "";
    this.d = 0.5F;
    this.e = 1.0F;
  }

  int a()
  {
    return this.a;
  }

  void a(int paramInt)
  {
    this.a = paramInt;
  }

  void a(String paramString)
  {
    this.c = paramString;
  }

  int b()
  {
    if (getMarker() == null)
      return -1;
    return getMarker().hashCode();
  }

  String c()
  {
    return this.c;
  }

  public float getAnchorX()
  {
    return this.d;
  }

  public float getAnchorY()
  {
    return this.e;
  }

  public final Drawable getMarker()
  {
    return this.b;
  }

  public GeoPoint getPoint()
  {
    return this.mPoint;
  }

  public String getSnippet()
  {
    return this.mSnippet;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public void setAnchor(float paramFloat1, float paramFloat2)
  {
    if ((paramFloat1 > 1.0D) || (paramFloat1 < 0.0D) || (paramFloat2 > 1.0D) || (paramFloat2 < 0.0D))
      return;
    this.d = paramFloat1;
    this.e = paramFloat2;
  }

  public void setAnchor(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 1:
      setAnchor(0.5F, 0.5F);
      return;
    case 3:
      setAnchor(0.5F, 0.0F);
      return;
    case 2:
    }
    setAnchor(0.5F, 1.0F);
  }

  public void setGeoPoint(GeoPoint paramGeoPoint)
  {
    this.mPoint = paramGeoPoint;
  }

  public void setMarker(Drawable paramDrawable)
  {
    this.b = paramDrawable;
  }

  public void setSnippet(String paramString)
  {
    this.mSnippet = paramString;
  }

  public void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.OverlayItem
 * JD-Core Version:    0.6.0
 */