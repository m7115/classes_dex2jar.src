package com.baidu.mapapi.map;

import android.graphics.Typeface;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class TextItem
{
  public static final int ALIGN_BOTTOM = 2;
  public static final int ALIGN_CENTER = 0;
  public static final int ALIGN_TOP = 1;
  private String a;
  public int align = 0;
  public Symbol.Color bgColor;
  public Symbol.Color fontColor;
  public int fontSize = 12;
  public GeoPoint pt;
  public String text;
  public Typeface typeface;

  String a()
  {
    return this.a;
  }

  void a(String paramString)
  {
    this.a = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.TextItem
 * JD-Core Version:    0.6.0
 */