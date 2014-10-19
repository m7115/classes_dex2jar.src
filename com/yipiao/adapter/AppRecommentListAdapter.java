package com.yipiao.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.suanya.common.a.g.a;
import cn.suanya.common.widget.RemoteImageView;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.RecommendItem;
import java.util.List;
import java.util.Map;

public class AppRecommentListAdapter extends BaseViewAdapter<RecommendItem>
{
  private YipiaoApplication app;
  private Context context;
  private LayoutInflater inflater = LayoutInflater.from(this.mContext);

  public AppRecommentListAdapter(BaseActivity paramBaseActivity, List<RecommendItem> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
    this.app = paramBaseActivity.getLocalApp();
    this.context = paramBaseActivity;
  }

  protected View renderItem(RecommendItem paramRecommendItem, View paramView)
  {
    Holder localHolder = (Holder)paramView.getTag();
    if (localHolder == null)
      localHolder = new Holder(paramView, null);
    localHolder.app_name.setText(paramRecommendItem.getLabel());
    localHolder.localImageView.setTag(paramRecommendItem.getImgUrl());
    localHolder.localImageView.setImageUrl(paramRecommendItem.getImgUrl(), new TheImageDefautCall(localHolder.localImageView, paramRecommendItem.getImgUrl()));
    localHolder.app_description.setText(paramRecommendItem.getDescription());
    localHolder.app_install.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        RecommendItem localRecommendItem = (RecommendItem)paramView.getTag();
        AppRecommentListAdapter.this.app.parms.put("RecommendItem", localRecommendItem);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(localRecommendItem.getIntent()));
        localIntent.setFlags(268435456);
        AppRecommentListAdapter.this.context.startActivity(localIntent);
      }
    });
    localHolder.app_install.setTag(paramRecommendItem);
    paramView.setTag(localHolder);
    return paramView;
  }

  private class Holder
  {
    final TextView app_description;
    final Button app_install;
    final TextView app_name;
    final RemoteImageView localImageView;

    private Holder(View arg2)
    {
      Object localObject;
      this.app_name = ((TextView)localObject.findViewById(2131296656));
      this.localImageView = ((RemoteImageView)localObject.findViewById(2131296659));
      this.app_description = ((TextView)localObject.findViewById(2131296664));
      this.app_install = ((Button)localObject.findViewById(2131296660));
      this.localImageView.setDefaultImage(2130837784);
    }
  }

  class TheImageDefautCall
    implements g.a
  {
    private ImageView mView;
    private String url;

    public TheImageDefautCall(ImageView paramString, String arg3)
    {
      this.mView = paramString;
      Object localObject;
      this.url = localObject;
    }

    public void imageLoaded(Bitmap paramBitmap)
    {
      if ((paramBitmap != null) && (this.mView.getTag() != null) && (this.mView.getTag().equals(this.url)))
        this.mView.setImageBitmap(paramBitmap);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.AppRecommentListAdapter
 * JD-Core Version:    0.6.0
 */