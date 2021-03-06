package pl.droidsonroids.gif;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;

public class GifImageView extends ImageView
{
  static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";

  public GifImageView(Context paramContext)
  {
    super(paramContext);
  }

  public GifImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    trySetGifDrawable(paramAttributeSet, getResources());
  }

  public GifImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    trySetGifDrawable(paramAttributeSet, getResources());
  }

  public void setBackgroundResource(int paramInt)
  {
    setResource(false, paramInt, getResources());
  }

  public void setImageResource(int paramInt)
  {
    setResource(true, paramInt, getResources());
  }

  @TargetApi(16)
  void setResource(boolean paramBoolean, int paramInt, Resources paramResources)
  {
    try
    {
      localGifDrawable = new GifDrawable(paramResources, paramInt);
      if (paramBoolean)
      {
        setImageDrawable(localGifDrawable);
        return;
      }
      if (Build.VERSION.SDK_INT >= 16)
      {
        setBackground(localGifDrawable);
        return;
      }
    }
    catch (IOException localIOException)
    {
      GifDrawable localGifDrawable;
      if (paramBoolean)
      {
        super.setImageResource(paramInt);
        return;
        setBackgroundDrawable(localGifDrawable);
        return;
      }
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      label39: break label39;
      super.setBackgroundResource(paramInt);
    }
  }

  void trySetGifDrawable(AttributeSet paramAttributeSet, Resources paramResources)
  {
    if ((paramAttributeSet != null) && (paramResources != null))
    {
      int i = paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", -1);
      if ((i > 0) && ("drawable".equals(paramResources.getResourceTypeName(i))))
        setResource(true, i, paramResources);
      int j = paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "background", -1);
      if ((j > 0) && ("drawable".equals(paramResources.getResourceTypeName(j))))
        setResource(false, j, paramResources);
    }
  }

  public void updateImageInputStream(InputStream paramInputStream)
  {
    try
    {
      setImageDrawable(new GifDrawable(paramInputStream));
      return;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      return;
    }
    catch (IOException localIOException)
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     pl.droidsonroids.gif.GifImageView
 * JD-Core Version:    0.6.0
 */