package com.yipiao.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.suanya.common.a.n;
import cn.suanya.common.widget.RemoteImageView;
import java.net.URISyntaxException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RandomLayout extends LinearLayout
  implements View.OnClickListener
{
  private Context context;
  private JSONObject currentImage;
  private JSONArray imageDataSource;
  private RemoteImageView imageView;
  private View layer;
  private LayoutInflater mInflater;
  private LinearLayout mView;

  public RandomLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.mView = ((LinearLayout)this.mInflater.inflate(2130903152, null));
    this.imageView = ((RemoteImageView)this.mView.findViewById(2131297046));
    this.layer = this.mView.findViewById(2131297047);
    this.layer.setOnClickListener(this);
    this.imageView.setOnClickListener(this);
    ViewGroup.LayoutParams localLayoutParams = this.imageView.getLayoutParams();
    localLayoutParams.height = (getResources().getDisplayMetrics().widthPixels / 6);
    this.imageView.setLayoutParams(localLayoutParams);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
    addView(this.mView, localLayoutParams1);
  }

  public void init(JSONArray paramJSONArray)
  {
    this.imageDataSource = paramJSONArray;
    setVisibility(8);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131297046:
    default:
    case 2131297047:
    }
    do
      return;
    while (this.currentImage == null);
    String str1 = this.currentImage.optString("intent");
    if ((str1 != null) && (str1.length() > 0))
      try
      {
        Intent localIntent2 = Intent.parseUri(str1, 0);
        if (this.context.getPackageManager().queryIntentActivities(localIntent2, 1).size() > 0)
        {
          this.context.startActivity(localIntent2);
          return;
        }
      }
      catch (URISyntaxException localURISyntaxException)
      {
      }
    String str2 = this.currentImage.optString("uri");
    Intent localIntent1 = new Intent("SY.WEB.VIEW.LOCATION", Uri.parse(str2));
    localIntent1.putExtra("url", str2);
    localIntent1.putExtra("history", true);
    localIntent1.putExtra("title", this.currentImage.optString("title"));
    localIntent1.putExtra("cookies", this.currentImage.optString("cookies"));
    String str3 = this.currentImage.optString("name");
    if (str3 != null)
      localIntent1.putExtra("urlName", str3);
    this.context.startActivity(localIntent1);
  }

  public void random()
  {
    double d1 = 0.0D;
    setVisibility(8);
    this.currentImage = null;
    double d2;
    int i;
    if ((this.imageDataSource != null) && (this.imageDataSource.length() > 0))
    {
      d2 = Math.random();
      i = 0;
    }
    while (true)
    {
      if (i < this.imageDataSource.length());
      try
      {
        JSONObject localJSONObject = this.imageDataSource.getJSONObject(i);
        double d3 = localJSONObject.optDouble("rate", 0.0D);
        if (d3 + d1 > d2)
        {
          this.currentImage = localJSONObject;
          if (this.currentImage != null)
          {
            this.imageView.setImageUrl(this.currentImage.optString("image"));
            setVisibility(0);
          }
          return;
        }
        d1 += d3;
        i++;
      }
      catch (JSONException localJSONException)
      {
        while (true)
          n.b(localJSONException);
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.RandomLayout
 * JD-Core Version:    0.6.0
 */