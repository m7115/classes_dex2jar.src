package cn.suanya.hotel.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cn.suanya.common.a.p;
import cn.suanya.common.a.q;
import cn.suanya.common.widget.RemoteImageView;
import cn.suanya.hotel.HTConstants;
import cn.suanya.hotel.base.R.drawable;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.HotelInfo;
import cn.suanya.hotel.util.MapUtil;
import java.text.DecimalFormat;
import java.util.List;

public class HotelListItemAdapter extends ArrayAdapter<HotelInfo>
{
  static DecimalFormat df_distance;
  static DecimalFormat df_price;
  static DecimalFormat df_rate = new DecimalFormat("#.0");
  private List<HotelInfo> items;
  private FindHotelReq req;

  static
  {
    df_price = new DecimalFormat("0.#");
    df_distance = new DecimalFormat("0.#");
  }

  public HotelListItemAdapter(Activity paramActivity, List<HotelInfo> paramList, FindHotelReq paramFindHotelReq)
  {
    super(paramActivity, R.layout.hotel_list_item, paramList);
    this.items = paramList;
    this.req = paramFindHotelReq;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = LayoutInflater.from(getContext()).inflate(R.layout.hotel_list_item, paramViewGroup, false);
    HotelInfo localHotelInfo = (HotelInfo)this.items.get(paramInt);
    String str1 = localHotelInfo.getHotelName();
    String str2 = df_rate.format(localHotelInfo.getRating());
    double d1 = localHotelInfo.getLowestPrice();
    String str3 = df_price.format(d1);
    String str4 = localHotelInfo.getHotelAddress();
    p localp1 = HTConstants.starShow.a(Integer.toString(localHotelInfo.getStar()));
    if (localp1 == null);
    for (p localp2 = (p)HTConstants.starShow.get(1); ; localp2 = localp1)
    {
      ((TextView)paramView.findViewById(R.id.name)).setText(str1);
      ((TextView)paramView.findViewById(R.id.price)).setText(str3);
      ((TextView)paramView.findViewById(R.id.rating)).setText(str2);
      ((TextView)paramView.findViewById(R.id.starLevel)).setText(localp2.getName());
      ((TextView)paramView.findViewById(R.id.address_tv)).setText(str4);
      TextView localTextView = (TextView)paramView.findViewById(R.id.distance);
      localTextView.setVisibility(8);
      if (this.req.getBlatitude() != 0.0D)
      {
        double d2 = MapUtil.distance(this.req.getBlatitude(), this.req.getBlongitude(), localHotelInfo.getLatitude(), localHotelInfo.getLongitude()) / 1000.0D;
        if (d2 < 100.0D)
        {
          localTextView.setVisibility(0);
          localTextView.setText(df_distance.format(d2) + "公里");
        }
      }
      RemoteImageView localRemoteImageView = (RemoteImageView)paramView.findViewById(R.id.hotelImg);
      localRemoteImageView.setDefaultImage(R.drawable.unkown_logo);
      String str5 = localHotelInfo.getImg();
      if ((str5 != null) && (str5.length() > 0))
        localRemoteImageView.setImageUrl(str5);
      return paramView;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.adapter.HotelListItemAdapter
 * JD-Core Version:    0.6.0
 */