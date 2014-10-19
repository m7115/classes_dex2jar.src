package com.baidu.mapapi.search;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.c.a;
import com.baidu.platform.comapi.d.c;

class h
  implements View.OnClickListener
{
  h(PlaceCaterActivity paramPlaceCaterActivity)
  {
  }

  public void onClick(View paramView)
  {
    d locald = (d)paramView.getTag();
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(locald.c));
    this.a.startActivity(localIntent);
    a.a().a("pkgname", c.q());
    a.a().a("cat", locald.b);
    a.a().a("place_cater_moreinfo_click");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.h
 * JD-Core Version:    0.6.0
 */