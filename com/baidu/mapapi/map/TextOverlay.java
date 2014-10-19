package com.baidu.mapapi.map;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.utils.e;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.o;
import com.baidu.platform.comjni.map.basemap.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import vi.com.gdi.bgl.android.java.EnvDrawText;

public class TextOverlay extends Overlay
{
  private ArrayList<TextItem> a;
  private MapView b;

  public TextOverlay(MapView paramMapView)
  {
    this.b = paramMapView;
    this.mType = 30;
    this.a = new ArrayList();
  }

  void a()
  {
    this.mLayerID = this.b.a("text");
    if (this.mLayerID == 0)
      throw new RuntimeException("can not add text layer");
  }

  public void addText(TextItem paramTextItem)
  {
    if (paramTextItem == null);
    do
      return;
    while ((paramTextItem.pt == null) || (paramTextItem.text == null) || (paramTextItem.fontSize == 0) || (paramTextItem.fontColor == null));
    if (this.mLayerID == 0)
    {
      this.a.add(paramTextItem);
      return;
    }
    Bundle localBundle = new Bundle();
    GeoPoint localGeoPoint = e.b(paramTextItem.pt);
    int i = localGeoPoint.getLongitudeE6();
    int j = localGeoPoint.getLatitudeE6();
    localBundle.putInt("x", i);
    localBundle.putInt("y", j);
    localBundle.putInt("fsize", paramTextItem.fontSize);
    int k;
    if (paramTextItem.bgColor == null)
    {
      k = Color.argb(0, 0, 0, 0);
      localBundle.putInt("bgcolor", k);
      localBundle.putInt("fcolor", Color.argb(paramTextItem.fontColor.alpha, paramTextItem.fontColor.blue, paramTextItem.fontColor.green, paramTextItem.fontColor.red));
      localBundle.putString("str", paramTextItem.text);
      long l = System.currentTimeMillis();
      paramTextItem.a(l + "_" + size());
      localBundle.putString("id", paramTextItem.a());
      localBundle.putInt("align", paramTextItem.align);
      localBundle.putInt("textaddr", this.mLayerID);
      if (paramTextItem.typeface == null)
        break label339;
      EnvDrawText.registFontCache(paramTextItem.typeface.hashCode(), paramTextItem.typeface);
      localBundle.putInt("fstyle", paramTextItem.typeface.hashCode());
    }
    while (true)
    {
      this.a.add(paramTextItem);
      this.b.getController().a.b().h(localBundle);
      return;
      k = Color.argb(paramTextItem.bgColor.alpha, paramTextItem.bgColor.blue, paramTextItem.bgColor.green, paramTextItem.bgColor.red);
      break;
      label339: localBundle.putInt("fstyle", 0);
    }
  }

  void b()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.a);
    removeAll();
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
      addText((TextItem)localIterator.next());
  }

  public List<TextItem> getAllText()
  {
    return this.a;
  }

  public TextItem getText(int paramInt)
  {
    if ((paramInt < size()) && (paramInt >= 0))
      return (TextItem)this.a.get(paramInt);
    return null;
  }

  public boolean removeAll()
  {
    this.b.getController().a.b().c(this.mLayerID);
    this.b.getController().a.b().a(this.mLayerID, false);
    this.b.getController().a.b().a(this.mLayerID);
    this.a.clear();
    return true;
  }

  public boolean removeText(TextItem paramTextItem)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("textaddr", this.mLayerID);
    if (paramTextItem.a().equals(""));
    do
    {
      return false;
      localBundle.putString("id", paramTextItem.a());
    }
    while (!this.b.getController().a.b().i(localBundle));
    if (paramTextItem.typeface != null)
      EnvDrawText.removeFontCache(paramTextItem.typeface.hashCode());
    this.a.remove(paramTextItem);
    return true;
  }

  public int size()
  {
    return this.a.size();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.TextOverlay
 * JD-Core Version:    0.6.0
 */