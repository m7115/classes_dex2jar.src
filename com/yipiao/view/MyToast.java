package com.yipiao.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast extends Toast
{
  private static MyToast mToast;

  public MyToast(Context paramContext)
  {
    super(paramContext);
  }

  public static void cancelToast()
  {
    if (mToast != null)
      mToast.cancel();
  }

  public static MyToast makeText(Context paramContext, int paramInt1, int paramInt2)
  {
    if (mToast == null)
      mToast = new MyToast(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(2130903186, null);
    ((TextView)localView.findViewById(2131296256)).setText(paramContext.getResources().getText(paramInt1));
    mToast.setView(localView);
    mToast.setDuration(paramInt2);
    return mToast;
  }

  public static MyToast makeText(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    if (mToast == null)
      mToast = new MyToast(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(2130903186, null);
    ((TextView)localView.findViewById(2131296256)).setText(paramCharSequence);
    mToast.setView(localView);
    mToast.setDuration(paramInt);
    return mToast;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.MyToast
 * JD-Core Version:    0.6.0
 */