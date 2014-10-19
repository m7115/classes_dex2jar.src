package com.baidu.mapapi.search;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.baidu.platform.comapi.c.a;
import com.baidu.platform.comapi.d.c;

class g
  implements View.OnClickListener
{
  g(PlaceCaterActivity paramPlaceCaterActivity)
  {
  }

  public void onClick(View paramView)
  {
    try
    {
      String str = this.a.j.getText().toString();
      Intent localIntent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str.trim()));
      this.a.startActivity(localIntent);
      a.a().a("pkgname", c.q());
      a.a().a("place_telbutton_click");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.g
 * JD-Core Version:    0.6.0
 */