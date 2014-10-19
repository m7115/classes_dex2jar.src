package com.baidu.mapapi.search;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

public class PlaceCaterActivity extends Activity
  implements a.a
{
  static ImageView c;
  static boolean d;
  static DisplayMetrics o;
  static Hashtable<Integer, View> q;
  static Handler r;
  private static int s = -2;
  private static int t = -1;
  private static int u = 10;
  private static int v = 5;
  private static int w = 1;
  private static int x = -7566196;
  private static int y = -12487463;
  private static int z = -1710619;
  TextView a;
  TextView b;
  LinearLayout e;
  TextView f;
  TextView g;
  TextView h;
  TextView i;
  TextView j;
  TextView k;
  TextView l;
  TextView m;
  LinearLayout n;
  e p = new e();

  static
  {
    q = new Hashtable();
    r = new f();
  }

  private Bitmap a(String paramString)
  {
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(getAssets().open(paramString));
      return localBitmap;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  private void a(LinearLayout paramLinearLayout, List<d> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      this.n.removeAllViews();
      q.clear();
      int i1 = paramList.size();
      int i2 = i1 / 2 + i1 % 2;
      for (int i3 = 0; i3 < i2; i3++)
      {
        LinearLayout localLinearLayout1 = new LinearLayout(this);
        localLinearLayout1.setLayoutParams(new ViewGroup.LayoutParams(t, s));
        paramLinearLayout.addView(localLinearLayout1);
        LinearLayout localLinearLayout2 = new LinearLayout(this);
        localLinearLayout2.setOrientation(0);
        localLinearLayout2.setLayoutParams(new ViewGroup.LayoutParams(t, s));
        localLinearLayout2.setPadding(20, 5, 5, 5);
        localLinearLayout1.addView(localLinearLayout2);
        ((LinearLayout.LayoutParams)localLinearLayout2.getLayoutParams()).weight = 1.0F;
        ImageView localImageView1 = new ImageView(this);
        localImageView1.setLayoutParams(new ViewGroup.LayoutParams((int)(22.0F * o.density), (int)(22.0F * o.density)));
        localImageView1.setTag(Integer.valueOf(i3 * 2));
        String str = d.a.replaceAll("#replace#", ((d)paramList.get(i3 * 2)).d);
        a.a(paramLinearLayout.hashCode(), 1 + i3 * 2, str, this);
        q.put(Integer.valueOf(1 + i3 * 2), localImageView1);
        localLinearLayout2.addView(localImageView1);
        ((LinearLayout.LayoutParams)localImageView1.getLayoutParams()).gravity = 17;
        TextView localTextView1 = new TextView(this);
        localTextView1.setTag(paramList.get(i3 * 2));
        localTextView1.setPadding(u, u, u, u);
        localTextView1.setLayoutParams(new ViewGroup.LayoutParams(s, s));
        localTextView1.setClickable(true);
        localTextView1.setText(((d)paramList.get(i3 * 2)).b);
        localTextView1.setTextColor(y);
        localTextView1.setOnClickListener(new h(this));
        localLinearLayout2.addView(localTextView1);
        ((LinearLayout.LayoutParams)localTextView1.getLayoutParams()).gravity = 17;
        if (1 + i3 * 2 >= i1)
          continue;
        LinearLayout localLinearLayout3 = new LinearLayout(this);
        localLinearLayout3.setPadding(20, 5, 5, 5);
        localLinearLayout3.setLayoutParams(new ViewGroup.LayoutParams(t, s));
        localLinearLayout1.addView(localLinearLayout3);
        LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)localLinearLayout3.getLayoutParams();
        localLayoutParams.weight = 1.0F;
        localLayoutParams.gravity = 17;
        ImageView localImageView2 = new ImageView(this);
        localImageView2.setLayoutParams(new ViewGroup.LayoutParams((int)(22.0F * o.density), (int)(22.0F * o.density)));
        paramList.get(1 + i3 * 2);
        a.a(paramLinearLayout.hashCode(), 1 + (1 + i3 * 2), d.a.replaceAll("#replace#", ((d)paramList.get(1 + i3 * 2)).d), this);
        q.put(Integer.valueOf(1 + (1 + i3 * 2)), localImageView2);
        localLinearLayout3.addView(localImageView2);
        ((LinearLayout.LayoutParams)localImageView2.getLayoutParams()).gravity = 16;
        TextView localTextView2 = new TextView(this);
        localTextView2.setTag(paramList.get(1 + i3 * 2));
        localTextView2.setPadding(u, u, u, u);
        localTextView2.setClickable(true);
        localTextView2.setTextColor(y);
        localTextView2.setText(((d)paramList.get(1 + i3 * 2)).b);
        localTextView2.setOnClickListener(new i(this));
        localLinearLayout3.addView(localTextView2);
        ((LinearLayout.LayoutParams)localTextView2.getLayoutParams()).gravity = 17;
      }
    }
  }

  public static boolean isShow()
  {
    return d;
  }

  void a(float paramFloat)
  {
    if (this.e == null)
      return;
    this.e.removeAllViews();
    int i1 = (int)paramFloat;
    int i2 = 0;
    if (i2 < 5)
    {
      if (i2 < i1)
      {
        ImageView localImageView1 = new ImageView(this);
        localImageView1.setImageBitmap(a("place/star_light.png"));
        localImageView1.setLayoutParams(new ViewGroup.LayoutParams((int)(20.0F * o.density), (int)(20.0F * o.density)));
        localImageView1.setPadding(1, 1, 1, 1);
        this.e.addView(localImageView1);
      }
      while (true)
      {
        i2++;
        break;
        ImageView localImageView2 = new ImageView(this);
        localImageView2.setImageBitmap(a("place/star_gray.png"));
        localImageView2.setLayoutParams(new ViewGroup.LayoutParams((int)(20.0F * o.density), (int)(20.0F * o.density)));
        localImageView2.setPadding(1, 1, 1, 1);
        this.e.addView(localImageView2);
      }
    }
    TextView localTextView = new TextView(this);
    localTextView.setLayoutParams(new ViewGroup.LayoutParams(s, s));
    localTextView.setText(Float.toString(paramFloat));
    localTextView.setPadding(10, 0, 10, 0);
    localTextView.setTextColor(-16777216);
    this.e.addView(localTextView);
  }

  void a(DisplayMetrics paramDisplayMetrics)
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this);
    localLinearLayout1.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setBackgroundColor(-3355444);
    localLinearLayout1.setPadding(1, 1, 1, 1);
    LinearLayout localLinearLayout2 = new LinearLayout(this);
    localLinearLayout2.setPadding(1, 1, 1, 1);
    localLinearLayout2.setBackgroundColor(-1);
    localLinearLayout2.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout2.setOrientation(1);
    localLinearLayout1.addView(localLinearLayout2);
    LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)localLinearLayout2.getLayoutParams();
    localLayoutParams1.rightMargin = w;
    localLayoutParams1.bottomMargin = w;
    localLayoutParams1.topMargin = w;
    localLayoutParams1.leftMargin = w;
    this.a = new TextView(this);
    this.a.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    this.a.setTextSize(18.0F);
    this.a.setText("");
    this.a.setPadding(v, v, v, v);
    this.a.setTextColor(-16777216);
    this.a.setTypeface(Typeface.DEFAULT, 1);
    localLinearLayout2.addView(this.a);
    ((LinearLayout.LayoutParams)this.a.getLayoutParams()).leftMargin = 1;
    this.b = new TextView(this);
    this.b.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    this.b.setTextSize(16.0F);
    this.b.setPadding(u, u, u, u);
    this.b.setTextColor(x);
    localLinearLayout2.addView(this.b);
    LinearLayout localLinearLayout3 = new LinearLayout(this);
    localLinearLayout3.setBackgroundColor(-1);
    localLinearLayout3.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    localLinearLayout1.addView(localLinearLayout3);
    LinearLayout.LayoutParams localLayoutParams2 = (LinearLayout.LayoutParams)localLinearLayout3.getLayoutParams();
    localLayoutParams2.topMargin = w;
    localLayoutParams2.rightMargin = w;
    localLayoutParams2.bottomMargin = w;
    localLayoutParams2.leftMargin = w;
    c = new ImageView(this);
    c.setPadding(5, 5, 5, 5);
    c.setLayoutParams(new ViewGroup.LayoutParams((int)(120.0F * paramDisplayMetrics.density), (int)(90.0F * paramDisplayMetrics.density)));
    localLinearLayout3.addView(c);
    LinearLayout localLinearLayout4 = new LinearLayout(this);
    localLinearLayout4.setOrientation(1);
    localLinearLayout4.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout4.setPadding(u, u, u, u);
    localLinearLayout3.addView(localLinearLayout4);
    ((LinearLayout.LayoutParams)localLinearLayout4.getLayoutParams()).gravity = 16;
    this.e = new LinearLayout(this);
    this.e.setPadding(2, 2, 2, 2);
    this.e.setOrientation(0);
    localLinearLayout4.addView(this.e);
    LinearLayout localLinearLayout5 = new LinearLayout(this);
    localLinearLayout5.setPadding(2, 2, 2, 2);
    localLinearLayout5.setLayoutParams(new ViewGroup.LayoutParams(s, s));
    localLinearLayout4.addView(localLinearLayout5);
    TextView localTextView1 = new TextView(this);
    localTextView1.setTextColor(x);
    localTextView1.setTextSize(16.0F);
    localTextView1.setText("参考价：");
    localLinearLayout5.addView(localTextView1);
    this.f = new TextView(this);
    this.f.setTextColor(-4712681);
    this.f.setTextSize(16.0F);
    localLinearLayout5.addView(this.f);
    LinearLayout localLinearLayout6 = new LinearLayout(this);
    localLinearLayout6.setPadding(2, 2, 2, 2);
    localLinearLayout4.addView(localLinearLayout6);
    this.g = new TextView(this);
    this.g.setPadding(0, 0, 5, 0);
    this.g.setText("口味:3.0");
    this.g.setTextColor(x);
    this.g.setTextSize(12.0F);
    localLinearLayout6.addView(this.g);
    this.h = new TextView(this);
    this.h.setPadding(0, 0, 5, 0);
    this.h.setText("服务:3.0");
    this.h.setTextColor(x);
    this.h.setTextSize(12.0F);
    localLinearLayout6.addView(this.h);
    this.i = new TextView(this);
    this.i.setPadding(0, 0, 5, 0);
    this.i.setText("环境:3.0");
    this.i.setTextColor(x);
    this.i.setTextSize(12.0F);
    localLinearLayout6.addView(this.i);
    LinearLayout localLinearLayout7 = new LinearLayout(this);
    localLinearLayout7.setBackgroundColor(-1);
    localLinearLayout7.setPadding(5, 5, 5, 5);
    localLinearLayout7.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    localLinearLayout7.setOrientation(0);
    localLinearLayout1.addView(localLinearLayout7);
    LinearLayout.LayoutParams localLayoutParams3 = (LinearLayout.LayoutParams)localLinearLayout7.getLayoutParams();
    localLayoutParams3.topMargin = w;
    localLayoutParams3.rightMargin = w;
    localLayoutParams3.bottomMargin = w;
    localLayoutParams3.leftMargin = w;
    localLinearLayout7.setOnClickListener(new g(this));
    ImageView localImageView1 = new ImageView(this);
    localImageView1.setPadding(5, 5, 5, 5);
    localImageView1.setLayoutParams(new ViewGroup.LayoutParams((int)(35.0F * paramDisplayMetrics.density), (int)(35.0F * paramDisplayMetrics.density)));
    localImageView1.setImageBitmap(a("place/iconphone.png"));
    localLinearLayout7.addView(localImageView1);
    ((LinearLayout.LayoutParams)localImageView1.getLayoutParams()).gravity = 16;
    this.j = new TextView(this);
    this.j.setTextColor(-16777216);
    this.j.setText("(010)4343243");
    this.j.setPadding(5, 5, 5, 5);
    this.j.setTextSize(16.0F);
    this.j.setLayoutParams(new ViewGroup.LayoutParams(s, s));
    localLinearLayout7.addView(this.j);
    LinearLayout.LayoutParams localLayoutParams4 = (LinearLayout.LayoutParams)this.j.getLayoutParams();
    localLayoutParams4.weight = 1.0F;
    localLayoutParams4.gravity = 16;
    ImageView localImageView2 = new ImageView(this);
    localImageView2.setLayoutParams(new ViewGroup.LayoutParams(s, s));
    localImageView2.setImageBitmap(a("place/arrow.png"));
    localImageView2.setPadding(5, 5, 5, 10);
    localLinearLayout7.addView(localImageView2);
    ((LinearLayout.LayoutParams)localImageView2.getLayoutParams()).gravity = 16;
    LinearLayout localLinearLayout8 = new LinearLayout(this);
    localLinearLayout8.setBackgroundColor(z);
    localLinearLayout8.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout8.setOrientation(1);
    localLinearLayout1.addView(localLinearLayout8);
    LinearLayout.LayoutParams localLayoutParams5 = (LinearLayout.LayoutParams)localLinearLayout8.getLayoutParams();
    localLayoutParams5.topMargin = w;
    localLayoutParams5.rightMargin = w;
    localLayoutParams5.bottomMargin = w;
    localLayoutParams5.leftMargin = w;
    TextView localTextView2 = new TextView(this);
    localTextView2.setTextSize(18.0F);
    localTextView2.setText("商户简介");
    localTextView2.setPadding(v, v, v, v);
    localTextView2.setTextColor(-16777216);
    localTextView2.setLayoutParams(new ViewGroup.LayoutParams(s, s));
    localLinearLayout8.addView(localTextView2);
    this.k = new TextView(this);
    this.k.setBackgroundColor(-1);
    this.k.setTextColor(x);
    this.k.setPadding(u, u, u, u);
    this.k.setTextSize(16.0F);
    this.k.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout8.addView(this.k);
    this.l = new TextView(this);
    this.l.setBackgroundColor(-1);
    this.l.setTextColor(x);
    this.l.setPadding(u, u, u, u);
    this.l.setTextSize(16.0F);
    this.l.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout8.addView(this.l);
    LinearLayout localLinearLayout9 = new LinearLayout(this);
    localLinearLayout9.setBackgroundColor(z);
    localLinearLayout9.setOrientation(1);
    localLinearLayout9.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout1.addView(localLinearLayout9);
    LinearLayout.LayoutParams localLayoutParams6 = (LinearLayout.LayoutParams)localLinearLayout9.getLayoutParams();
    localLayoutParams6.topMargin = w;
    localLayoutParams6.rightMargin = w;
    localLayoutParams6.bottomMargin = w;
    localLayoutParams6.leftMargin = w;
    TextView localTextView3 = new TextView(this);
    localTextView3.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localTextView3.setText("评论信息");
    localTextView3.setPadding(v, v, v, v);
    localTextView3.setTextColor(-16777216);
    localTextView3.setTextSize(18.0F);
    localLinearLayout9.addView(localTextView3);
    this.m = new TextView(this);
    this.m.setPadding(u, u, u, u);
    this.m.setBackgroundColor(-1);
    this.m.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    this.m.setTextSize(16.0F);
    this.m.setTextColor(x);
    localLinearLayout9.addView(this.m);
    LinearLayout localLinearLayout10 = new LinearLayout(this);
    localLinearLayout10.setBackgroundColor(z);
    localLinearLayout10.setOrientation(1);
    localLinearLayout10.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout1.addView(localLinearLayout10);
    LinearLayout.LayoutParams localLayoutParams7 = (LinearLayout.LayoutParams)localLinearLayout10.getLayoutParams();
    localLayoutParams7.topMargin = w;
    localLayoutParams7.rightMargin = w;
    localLayoutParams7.bottomMargin = w;
    localLayoutParams7.leftMargin = w;
    TextView localTextView4 = new TextView(this);
    localTextView4.setLayoutParams(new ViewGroup.LayoutParams(s, s));
    localTextView4.setTextSize(18.0F);
    localTextView4.setPadding(v, v, v, v);
    localTextView4.setTextColor(-16777216);
    localTextView4.setText("查看更多");
    localLinearLayout10.addView(localTextView4);
    this.n = new LinearLayout(this);
    this.n.setOrientation(1);
    this.n.setBackgroundColor(-1);
    this.n.setLayoutParams(new ViewGroup.LayoutParams(t, s));
    localLinearLayout10.addView(this.n);
    ScrollView localScrollView = new ScrollView(this);
    localScrollView.setPadding(5, 5, 0, 5);
    localScrollView.setLayoutParams(new ViewGroup.LayoutParams(t, t));
    localScrollView.setBackgroundColor(-526345);
    localScrollView.addView(localLinearLayout1);
    ((FrameLayout.LayoutParams)localLinearLayout1.getLayoutParams()).rightMargin = 5;
    setContentView(localScrollView);
  }

  void a(e parame)
  {
    this.a.setText(parame.a);
    this.b.setText("地址：" + parame.b);
    this.f.setText("￥" + parame.g);
    this.g.setText("口味:" + parame.h);
    this.h.setText("服务:" + parame.j);
    this.i.setText("环境:" + parame.i);
    this.j.setText(parame.c);
    if ((parame.l != null) && (!"".equals(parame.l)))
    {
      this.k.setVisibility(0);
      this.k.setText("推荐菜：" + parame.l);
    }
    while (true)
    {
      if ((parame.k != null) && (!"".equals(parame.k)))
      {
        this.l.setVisibility(0);
        this.l.setText("商户描述：" + parame.k);
        label288: if ((parame.m == null) || ("".equals(parame.m)))
          break label406;
        this.m.setVisibility(0);
        this.m.setText(parame.m);
        if (parame.e != null)
          a.a(c.hashCode(), 0, parame.e, this);
      }
      try
      {
        float f2 = Float.valueOf(parame.f).floatValue();
        f1 = f2;
        a(f1);
        a(this.n, parame.o);
        return;
        this.k.setVisibility(8);
        continue;
        this.l.setVisibility(8);
        break label288;
        label406: this.m.setVisibility(8);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        while (true)
        {
          localNumberFormatException.printStackTrace();
          float f1 = 0.0F;
        }
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    d = true;
    Bundle localBundle = getIntent().getExtras();
    if (!c.a(localBundle.getString("result"), this.p))
    {
      this.p.a = localBundle.getString("name");
      this.p.b = localBundle.getString("addr");
      this.p.c = localBundle.getString("tel");
      this.p.d = localBundle.getString("uid");
      this.p.e = localBundle.getString("image");
      this.p.f = localBundle.getString("overall_rating");
      this.p.g = localBundle.getString("price");
      this.p.h = localBundle.getString("taste_rating");
      this.p.i = localBundle.getString("enviroment_raing");
      this.p.j = localBundle.getString("service_rating");
      this.p.k = localBundle.getString("description");
      this.p.l = localBundle.getString("recommendation");
      this.p.m = localBundle.getString("review");
      this.p.n = localBundle.getString("user_logo");
      String[] arrayOfString1 = localBundle.getStringArray("aryMoreLinkName");
      String[] arrayOfString2 = localBundle.getStringArray("aryMoreLinkUrl");
      String[] arrayOfString3 = localBundle.getStringArray("aryMoreLinkCnName");
      if ((arrayOfString1 != null) && (arrayOfString2 != null))
        for (int i1 = 0; i1 < arrayOfString2.length; i1++)
        {
          if ("dianping".equals(arrayOfString1[i1]))
            continue;
          d locald = new d();
          locald.d = arrayOfString1[i1];
          locald.c = arrayOfString2[i1];
          locald.b = arrayOfString3[i1];
          this.p.o.add(locald);
        }
    }
    com.baidu.platform.comapi.c.a.a().c();
    o = getResources().getDisplayMetrics();
    a(o);
    a(this.p);
  }

  protected void onDestroy()
  {
    if (q != null)
      q.clear();
    o = null;
    c = null;
    d = false;
    com.baidu.platform.comapi.c.a.b();
    super.onDestroy();
  }

  public void onError(int paramInt1, int paramInt2, String paramString, Object paramObject)
  {
  }

  public void onOk(int paramInt1, int paramInt2, String paramString, Object paramObject)
  {
    if (paramInt1 == c.hashCode())
    {
      Message localMessage2 = r.obtainMessage(1);
      localMessage2.obj = paramObject;
      localMessage2.sendToTarget();
    }
    do
      return;
    while (paramInt1 != this.n.hashCode());
    Message localMessage1 = r.obtainMessage(2);
    localMessage1.obj = paramObject;
    localMessage1.arg1 = paramInt2;
    localMessage1.sendToTarget();
  }

  protected void onPause()
  {
    com.baidu.platform.comapi.c.a.a().d();
    super.onPause();
  }

  protected void onResume()
  {
    if ((this.p.c != null) && (!"".equals(this.p.c)))
    {
      com.baidu.platform.comapi.c.a.a().a("pkgname", com.baidu.platform.comapi.d.c.q());
      com.baidu.platform.comapi.c.a.a().a("place_tel_show");
    }
    while (true)
    {
      super.onResume();
      return;
      com.baidu.platform.comapi.c.a.a().a("pkgname", com.baidu.platform.comapi.d.c.q());
      com.baidu.platform.comapi.c.a.a().a("place_notel_show");
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.PlaceCaterActivity
 * JD-Core Version:    0.6.0
 */