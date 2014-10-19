package com.yipiao.wxapi;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.suanya.common.a.b;
import cn.suanya.common.a.n;
import cn.suanya.common.ui.SYApplication;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;
import com.tencent.mm.sdk.openapi.d;
import com.tencent.mm.sdk.openapi.g.a;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.ShareAppListAdapter;
import com.yipiao.bean.AppInfo;
import com.yipiao.view.MyAlertDialog;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.view.MyToast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class DisplayHelper
{
  public static Bitmap ConvertGrayImg(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int[] arrayOfInt = new int[i * j];
    paramBitmap.getPixels(arrayOfInt, 0, i, 0, 0, i, j);
    for (int k = 0; k < j; k++)
      for (int m = 0; m < i; m++)
      {
        int n = arrayOfInt[(m + i * k)];
        int i1 = (0xFF0000 & n) >> 16;
        int i2 = (0xFF00 & n) >> 8;
        int i3 = ((n & 0xFF) + (i1 + i2)) / 3;
        int i4 = i3 | (0xFF000000 | i3 << 16 | i3 << 8);
        arrayOfInt[(m + i * k)] = i4;
      }
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
    localBitmap.setPixels(arrayOfInt, 0, i, 0, 0, i, j);
    return localBitmap;
  }

  private static List<AppInfo> castResolveInfo(PackageManager paramPackageManager, List<ResolveInfo> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        try
        {
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.setAppPkgName(localResolveInfo.activityInfo.packageName);
          localAppInfo.setAppClassName(localResolveInfo.activityInfo.name);
          localAppInfo.setAppName(localResolveInfo.loadLabel(paramPackageManager).toString());
          localAppInfo.setAppIcon(localResolveInfo.loadIcon(paramPackageManager));
          localArrayList.add(localAppInfo);
        }
        catch (Exception localException)
        {
        }
      }
    }
    return localArrayList;
  }

  public static void createShareLink(Context paramContext, LinearLayout paramLinearLayout, d paramd, String paramString)
  {
    String str = YipiaoApplication.app.launchInfo.optString("share_filter", "com.qzone,com.kaixin001.activity,com.renren.mobile.android,com.tencent.WBlog,com.sina.weibo");
    n.b("share_filter=" + str);
    createShareLink(paramContext, paramLinearLayout, getShareAppList((Activity)paramContext, str), timeline_supported(paramd), paramd, paramString);
  }

  public static void createShareLink(Context paramContext, LinearLayout paramLinearLayout, List<AppInfo> paramList, boolean paramBoolean, d paramd, String paramString)
  {
    YipiaoApplication localYipiaoApplication = YipiaoApplication.getApp();
    Activity localActivity = (Activity)paramContext;
    try
    {
      paramLinearLayout.removeAllViewsInLayout();
      label15: if (((paramList == null) || (paramList.isEmpty())) && (!paramBoolean))
        paramLinearLayout.setVisibility(8);
      do
      {
        return;
        paramLinearLayout.setVisibility(0);
        if (paramList == null)
          continue;
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          AppInfo localAppInfo = (AppInfo)localIterator.next();
          ImageView localImageView2 = new ImageView(localActivity);
          localImageView2.setImageDrawable(localAppInfo.getAppIcon());
          localImageView2.setTag(localAppInfo);
          float f2 = ((Activity)paramContext).getResources().getDisplayMetrics().density;
          LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams((int)(36.0F * f2), (int)(f2 * 36.0F));
          localLayoutParams2.setMargins(5, 0, 5, 5);
          localImageView2.setLayoutParams(localLayoutParams2);
          localImageView2.setAdjustViewBounds(true);
          localImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
          localImageView2.setOnClickListener(new View.OnClickListener(localYipiaoApplication, paramString, paramContext)
          {
            public void onClick(View paramView)
            {
              AppInfo localAppInfo = (AppInfo)paramView.getTag();
              Intent localIntent = new Intent("android.intent.action.SEND");
              localIntent.setPackage(localAppInfo.getAppPkgName());
              localIntent.setType("text/plain");
              String str = this.val$app.launchInfo.optString("patarn.share.order", "{input}");
              localIntent.putExtra("android.intent.extra.TEXT", this.val$app.launchInfo.optString(localAppInfo.getAppPkgName() + ".order", str).replace("{input}", this.val$shareString));
              localIntent.setFlags(268435456);
              this.val$context.startActivity(localIntent);
            }
          });
          paramLinearLayout.addView(localImageView2);
        }
      }
      while (!paramBoolean);
      ImageView localImageView1 = new ImageView(localActivity);
      localImageView1.setImageDrawable(paramContext.getResources().getDrawable(2130837822));
      localImageView1.setTag("");
      float f1 = ((Activity)paramContext).getResources().getDisplayMetrics().density;
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams((int)(36.0F * f1), (int)(f1 * 36.0F));
      localLayoutParams1.setMargins(5, 0, 5, 5);
      localImageView1.setLayoutParams(localLayoutParams1);
      localImageView1.setAdjustViewBounds(true);
      localImageView1.setScaleType(ImageView.ScaleType.FIT_XY);
      localImageView1.setOnClickListener(new View.OnClickListener(localYipiaoApplication, paramString, paramContext, paramd)
      {
        public void onClick(View paramView)
        {
          String str1 = this.val$app.launchInfo.optString("wx.share.description", "把智行火车票分享到微信朋友圈。");
          String str2 = this.val$app.launchInfo.optString("wx.share.title", "我用智行火车票成功秒杀{input}...").replace("{input}", this.val$shareString);
          DisplayHelper.shareAppToWX(this.val$context, this.val$api, str2, str1);
        }
      });
      paramLinearLayout.addView(localImageView1);
      return;
    }
    catch (Exception localException)
    {
      break label15;
    }
  }

  private static List<AppInfo> getMarketAppList(Activity paramActivity)
  {
    PackageManager localPackageManager = paramActivity.getPackageManager();
    new ArrayList();
    List localList = castResolveInfo(localPackageManager, localPackageManager.queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.yipiao")), 0));
    ArrayList localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        AppInfo localAppInfo = (AppInfo)localIterator.next();
        String str = localAppInfo.getAppPkgName();
        if ("com.eshore.ezone,com.netease.apper,com.eoemobile.netmarket,com.suning.market".contains(str))
          continue;
        if ("com.tencent.android.qqdownloader,com.hiapk.marketpho,com.xiaomi.market,com.qihoo.appstore,com.wandoujia.phoenix2,com.baidu.appsearch".contains(str))
        {
          localArrayList.add(0, localAppInfo);
          continue;
        }
        localArrayList.add(localAppInfo);
      }
    }
    catch (Exception localException)
    {
    }
    return localArrayList;
  }

  private static List<AppInfo> getShareAppList(Activity paramActivity)
  {
    return castResolveInfo(paramActivity.getPackageManager(), getShareApps(paramActivity));
  }

  private static List<AppInfo> getShareAppList(Activity paramActivity, String paramString)
  {
    List localList = getShareAppList(paramActivity);
    ArrayList localArrayList = new ArrayList();
    if (localList == null)
      return localArrayList;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      AppInfo localAppInfo = (AppInfo)localIterator.next();
      if (!paramString.contains(localAppInfo.getAppPkgName()))
        continue;
      localArrayList.add(localAppInfo);
    }
    return localArrayList;
  }

  private static List<ResolveInfo> getShareApps(Activity paramActivity)
  {
    new ArrayList();
    Intent localIntent = new Intent("android.intent.action.SEND", null);
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setType("text/plain");
    return paramActivity.getPackageManager().queryIntentActivities(localIntent, 0);
  }

  public static void shareAppToWX(Context paramContext, d paramd, String paramString1, String paramString2)
  {
    if (!timeline_supported(paramd))
    {
      MyToast.makeText(paramContext, "您未安装微信或出现异常错误", 1).show();
      return;
    }
    YipiaoApplication localYipiaoApplication = YipiaoApplication.getApp();
    Activity localActivity = (Activity)paramContext;
    WXWebpageObject localWXWebpageObject = new WXWebpageObject();
    localWXWebpageObject.webpageUrl = localYipiaoApplication.launchInfo.optString("wx.share.webpageUrl", "http://suanya.cn/hc");
    WXMediaMessage localWXMediaMessage = new WXMediaMessage(localWXWebpageObject);
    localWXMediaMessage.description = paramString2;
    localWXMediaMessage.title = paramString1;
    Bitmap localBitmap1 = BitmapFactory.decodeResource(localActivity.getResources(), 2130837696);
    Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, 150, 150, true);
    localBitmap1.recycle();
    localWXMediaMessage.thumbData = b.a(localBitmap2, true);
    g.a locala = new g.a();
    locala.a = b.c("webpage");
    locala.b = localWXMediaMessage;
    locala.c = 1;
    paramd.a(locala);
  }

  public static void showMarketDialog(Context paramContext)
  {
    List localList = getMarketAppList((Activity)paramContext);
    if ((localList == null) || (localList.size() == 0))
      throw new ActivityNotFoundException();
    if (localList.size() == 1)
    {
      AppInfo localAppInfo = (AppInfo)localList.get(0);
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.yipiao"));
      localIntent.setPackage(localAppInfo.getAppPkgName());
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    View localView = LayoutInflater.from(paramContext).inflate(2130903077, null);
    GridView localGridView = (GridView)localView.findViewById(2131296824);
    localView.findViewById(2131296822).setVisibility(8);
    ShareAppListAdapter localShareAppListAdapter = new ShareAppListAdapter(paramContext, localList, 2130903078);
    localGridView.setAdapter(localShareAppListAdapter);
    ShareAppListAdapter.setListViewHeightBasedOnChildren(localGridView);
    MyAlertDialog localMyAlertDialog = new MyAlertDialog.Builder(paramContext).showByCustomView(localView);
    localGridView.setTag(localMyAlertDialog);
    localGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(localShareAppListAdapter, paramContext, localMyAlertDialog)
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        AppInfo localAppInfo = (AppInfo)this.val$adapter.getItem(paramInt);
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.yipiao"));
        localIntent.setPackage(localAppInfo.getAppPkgName());
        localIntent.setFlags(268435456);
        this.val$context.startActivity(localIntent);
        this.val$dialog.dismiss();
      }
    });
  }

  public static void showShareDialog(Context paramContext)
  {
    showShareDialog(paramContext, YipiaoApplication.getApp().launchInfo.optString("recommend", paramContext.getResources().getString(2131361815)), true);
  }

  private static void showShareDialog(Context paramContext, String paramString, boolean paramBoolean)
  {
    YipiaoApplication localYipiaoApplication = YipiaoApplication.getApp();
    String str = localYipiaoApplication.launchInfo.optString("more_share_filter", "com.android.mms,com.android.email,com.qzone,com.kaixin001.activity,com.renren.mobile.android,com.tencent.WBlog,com.sina.weibo,com.tencent.mobileqq,com.tencent.mm,com.tencent.minihd.qq,com.tencent.hd.qq,com.sina.weibotab");
    n.b("share_filter=" + str);
    List localList = getShareAppList((Activity)paramContext, str);
    if (timeline_supported(localYipiaoApplication.api))
    {
      AppInfo localAppInfo = new AppInfo();
      localAppInfo.setAppName("微信朋友圈");
      localAppInfo.setAppPkgName("com.tencent.mm.friend");
      localAppInfo.setAppClassName("");
      localAppInfo.setAppIcon(paramContext.getResources().getDrawable(2130837957));
      localList.add(0, localAppInfo);
    }
    View localView1 = LayoutInflater.from(paramContext).inflate(2130903077, null);
    GridView localGridView = (GridView)localView1.findViewById(2131296824);
    View localView2 = localView1.findViewById(2131296825);
    if ((localView2 != null) && (paramBoolean))
      localView2.setVisibility(0);
    ShareAppListAdapter localShareAppListAdapter = new ShareAppListAdapter(paramContext, localList, 2130903078);
    localGridView.setAdapter(localShareAppListAdapter);
    ShareAppListAdapter.setListViewHeightBasedOnChildren(localGridView);
    MyAlertDialog localMyAlertDialog = new MyAlertDialog.Builder(paramContext).showByCustomView(localView1);
    localGridView.setTag(localMyAlertDialog);
    localGridView.setOnItemClickListener(new AdapterView.OnItemClickListener(localYipiaoApplication, localShareAppListAdapter, paramContext, paramString, localMyAlertDialog)
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        String str = this.val$app.launchInfo.optString("wx.share.description", "分享智行火车票,体验超高速监控功能，增加最大监控条数到8条。每次分享有效期为48小时。");
        AppInfo localAppInfo = (AppInfo)this.val$adapter.getItem(paramInt);
        if ("com.tencent.mm.friend".equalsIgnoreCase(localAppInfo.getAppPkgName()))
        {
          DisplayHelper.shareAppToWX(this.val$context, this.val$app.api, this.val$shareStr, str);
          return;
        }
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setPackage(localAppInfo.getAppPkgName());
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", this.val$shareStr);
        localIntent.setFlags(268435456);
        this.val$context.startActivity(localIntent);
        this.val$dialog.dismiss();
      }
    });
    View localView3 = localView1.findViewById(2131296823);
    if (localView3 != null)
      localView3.setOnClickListener(new View.OnClickListener(localMyAlertDialog)
      {
        public void onClick(View paramView)
        {
          this.val$dialog.dismiss();
        }
      });
  }

  public static void showShareDialogForOrder(Context paramContext, String paramString, boolean paramBoolean)
  {
    showShareDialog(paramContext, YipiaoApplication.getApp().launchInfo.optString("order.share.text", "我用智行火车票成功秒杀{input}...").replace("{input}", paramString), paramBoolean);
  }

  protected static boolean timeline_supported(d paramd)
  {
    return paramd.a() >= 553779201;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.wxapi.DisplayHelper
 * JD-Core Version:    0.6.0
 */