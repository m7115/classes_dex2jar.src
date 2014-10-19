package cn.suanya.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import cn.suanya.a.f;
import cn.suanya.common.a.g;
import cn.suanya.common.a.g.a;

public class RemoteImageView extends ImageView
{
  public static g imageLoader = g.a();
  private CharSequence mUrl;

  public RemoteImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mUrl = paramContext.obtainStyledAttributes(paramAttributeSet, a.f.SYRemoteImage, 0, 0).getString(0);
    if ((this.mUrl != null) && (this.mUrl.length() > 0))
      setImageUrl(this.mUrl.toString());
  }

  public void setDefaultImage(int paramInt)
  {
    setImageResource(paramInt);
  }

  public void setImageUrl(String paramString)
  {
    imageLoader.a(paramString, false, this);
  }

  public void setImageUrl(String paramString, g.a parama)
  {
    imageLoader.a(paramString, false, parama);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.widget.RemoteImageView
 * JD-Core Version:    0.6.0
 */