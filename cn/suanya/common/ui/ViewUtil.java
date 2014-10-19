package cn.suanya.common.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.suanya.a.c;
import cn.suanya.a.d;

public class ViewUtil
{
  public static Toast a(Context paramContext, String paramString)
  {
    Toast localToast = Toast.makeText(paramContext, "", 0);
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(a.d.mytoast_view, null);
    ((TextView)localView.findViewById(a.c.text)).setText(paramString);
    localToast.setGravity(17, 0, 0);
    localToast.setView(localView);
    return localToast;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.ui.ViewUtil
 * JD-Core Version:    0.6.0
 */