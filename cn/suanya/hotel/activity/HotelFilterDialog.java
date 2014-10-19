package cn.suanya.hotel.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import cn.suanya.common.a.q;
import cn.suanya.common.a.r;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.FindHotelReq;

public class HotelFilterDialog extends Dialog
{
  public static q filterItems = new q();
  public static q priceLevel;
  protected FindHotelReq mHotelReq;
  protected HotelFilter mfilter;

  static
  {
    filterItems.add(new r("1", "经济型"));
    filterItems.add(new r("2", "两星级"));
    filterItems.add(new r("3", "三星级"));
    filterItems.add(new r("4", "四星级"));
    filterItems.add(new r("5", "五星级"));
    filterItems.add(new r("<200", "200元以下"));
    filterItems.add(new r("200<300", "200-300元"));
    filterItems.add(new r("300<500", "300-500元"));
    filterItems.add(new r("500<800", "500-800元"));
    filterItems.add(new r("500<", "500元以上"));
    filterItems.add(new r("800<", "800元以上"));
    priceLevel = new q();
  }

  public HotelFilterDialog(Context paramContext, int paramInt, FindHotelReq paramFindHotelReq, HotelFilter paramHotelFilter)
  {
    super(paramContext, paramInt);
    this.mfilter = paramHotelFilter;
    this.mHotelReq = paramFindHotelReq;
    LinearLayout localLinearLayout = (LinearLayout)((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.dialog_hotel_filter, null);
    RadioGroup localRadioGroup1 = (RadioGroup)localLinearLayout.findViewById(R.id.rg_price);
    RadioGroup localRadioGroup2 = (RadioGroup)localLinearLayout.findViewById(R.id.rg_star);
    localLinearLayout.findViewById(R.id.cc).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelFilterDialog.this.dismiss();
      }
    });
    localRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
      {
        if (paramInt == R.id.rb_price_0)
          HotelFilterDialog.this.mHotelReq.setPriceLevel("<");
        if (paramInt == R.id.rb_price_1)
          HotelFilterDialog.this.mHotelReq.setPriceLevel("<200");
        if (paramInt == R.id.rb_price_2)
          HotelFilterDialog.this.mHotelReq.setPriceLevel("200<300");
        if (paramInt == R.id.rb_price_3)
          HotelFilterDialog.this.mHotelReq.setPriceLevel("300<500");
        if (paramInt == R.id.rb_price_4)
          HotelFilterDialog.this.mHotelReq.setPriceLevel("500<");
        HotelFilterDialog.this.mHotelReq.setStar("0");
        HotelFilterDialog.this.mfilter.onFilter(HotelFilterDialog.this.mHotelReq);
        HotelFilterDialog.this.dismiss();
      }
    });
    localRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramRadioGroup, int paramInt)
      {
        if (paramInt == R.id.rb_star_0)
          HotelFilterDialog.this.mHotelReq.setStar("0");
        if (paramInt == R.id.rb_star_1)
          HotelFilterDialog.this.mHotelReq.setStar("1");
        if (paramInt == R.id.rb_star_2)
          HotelFilterDialog.this.mHotelReq.setStar("3");
        if (paramInt == R.id.rb_star_3)
          HotelFilterDialog.this.mHotelReq.setStar("4");
        if (paramInt == R.id.rb_star_4)
          HotelFilterDialog.this.mHotelReq.setStar("5");
        HotelFilterDialog.this.mHotelReq.setPriceLevel("<");
        HotelFilterDialog.this.mfilter.onFilter(HotelFilterDialog.this.mHotelReq);
        HotelFilterDialog.this.dismiss();
      }
    });
    localLinearLayout.setMinimumWidth(10000);
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.x = 0;
    localLayoutParams.y = -1000;
    localLayoutParams.gravity = 80;
    onWindowAttributesChanged(localLayoutParams);
    setCanceledOnTouchOutside(true);
    setContentView(localLinearLayout);
  }

  public static abstract interface HotelFilter
  {
    public abstract void onFilter(FindHotelReq paramFindHotelReq);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelFilterDialog
 * JD-Core Version:    0.6.0
 */