package pl.droidsonroids.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.TextView;
import java.io.IOException;

public class GifTextView extends TextView
{
  public GifTextView(Context paramContext)
  {
    super(paramContext);
  }

  public GifTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    parseAttrs(paramAttributeSet);
  }

  public GifTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    parseAttrs(paramAttributeSet);
  }

  private Drawable getGifOrDefaultDrawable(int paramInt)
  {
    if (paramInt == 0)
      return null;
    Resources localResources = getResources();
    if ("drawable".equals(localResources.getResourceTypeName(paramInt)));
    try
    {
      GifDrawable localGifDrawable = new GifDrawable(localResources, paramInt);
      return localGifDrawable;
    }
    catch (IOException localIOException)
    {
      return localResources.getDrawable(paramInt);
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      label38: break label38;
    }
  }

  @TargetApi(17)
  private void parseAttrs(AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      Drawable localDrawable1 = getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableLeft", 0));
      Drawable localDrawable2 = getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableRight", 0));
      Drawable localDrawable3 = getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableTop", 0));
      Drawable localDrawable4 = getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableBottom", 0));
      Drawable localDrawable5 = getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableStart", 0));
      Drawable localDrawable6 = getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableEnd", 0));
      setCompoundDrawablesWithIntrinsicBounds(localDrawable1, localDrawable3, localDrawable2, localDrawable4);
      if (Build.VERSION.SDK_INT >= 17)
        setCompoundDrawablesRelativeWithIntrinsicBounds(localDrawable5, localDrawable3, localDrawable6, localDrawable4);
      setBackgroundInternal(getGifOrDefaultDrawable(paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "background", 0)));
    }
  }

  @TargetApi(16)
  private void setBackgroundInternal(Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      setBackground(paramDrawable);
      return;
    }
    setBackgroundDrawable(paramDrawable);
  }

  public void setBackgroundResource(int paramInt)
  {
    setBackgroundInternal(getGifOrDefaultDrawable(paramInt));
  }

  @TargetApi(17)
  public void setCompoundDrawablesRelativeWithIntrinsicBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setCompoundDrawablesRelativeWithIntrinsicBounds(getGifOrDefaultDrawable(paramInt1), getGifOrDefaultDrawable(paramInt2), getGifOrDefaultDrawable(paramInt3), getGifOrDefaultDrawable(paramInt4));
  }

  public void setCompoundDrawablesWithIntrinsicBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setCompoundDrawablesWithIntrinsicBounds(getGifOrDefaultDrawable(paramInt1), getGifOrDefaultDrawable(paramInt2), getGifOrDefaultDrawable(paramInt3), getGifOrDefaultDrawable(paramInt4));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     pl.droidsonroids.gif.GifTextView
 * JD-Core Version:    0.6.0
 */