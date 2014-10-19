package cn.suanya.hotel.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import cn.suanya.common.a.c;
import cn.suanya.common.a.k;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.widget.RemoteImageView;
import cn.suanya.hotel.adapter.RoomStatusListItemAdapter;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.HotelDetail;
import cn.suanya.hotel.domain.HotelInfo;
import cn.suanya.hotel.domain.RoomStatus;
import cn.suanya.hotel.domain.RoomStatusSum;
import cn.suanya.hotel.service.HotelService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HotelDetailActivity extends HTActivity
{
  private TextView addrArrowTv;
  private HotelInfo hotel;
  private TextView hotelAddressTv;
  private RemoteImageView hotelDetailImgTv;
  private TextView hotelNameTv;
  private FindHotelReq hotelReq;
  private ExpandableListView hotelStatusList;
  private TextView phoneTv;
  private List<RoomStatusSum> roomStatusList = new ArrayList();
  private RoomStatusListItemAdapter statusAdapter;
  private TextView telArrowTv;

  private void fillDateInfo()
  {
    setTv(R.id.checkInTV, c.formartDate(this.hotelReq.getCheckInDate()));
    setTv(R.id.checkOutTV, c.formartDate(this.hotelReq.getCheckOutDate()));
    setTv(R.id.lightNumTV, this.hotelReq.getDateNum() + "晚");
  }

  private void fillHotelView()
  {
    if (this.hotel != null)
    {
      String str1 = this.hotel.getHotelName();
      String str2 = this.hotel.getPhone();
      String str3 = this.hotel.getHotelAddress();
      String str4 = this.hotel.getImg();
      if (!TextUtils.isEmpty(str4))
        this.hotelDetailImgTv.setImageUrl(str4);
      if (!TextUtils.isEmpty(str1))
        this.hotelNameTv.setText(str1);
      if (!TextUtils.isEmpty(str2))
      {
        this.phoneTv.setText(str2);
        this.telArrowTv.setVisibility(0);
      }
      if (!TextUtils.isEmpty(str3))
      {
        this.hotelAddressTv.setText(str3);
        this.addrArrowTv.setVisibility(0);
      }
    }
  }

  private void goHotelHtml5(RoomStatus paramRoomStatus)
  {
    if ("携程旅行网".equals(paramRoomStatus.getSourceName()))
    {
      Map localMap3 = (Map)this.hotel.getHotelId().get("ctrip");
      goHotelWEBActivity("http://m.ctrip.com/html5/Hotel/HotelDetail/" + localMap3.get("id") + ".html?atime=" + c.formart(this.hotelReq.getCheckInDate(), "yyyyMMdd") + "&day=" + this.hotelReq.getDateNum() + "&allianceid=7434&sid=172555&ouid=");
    }
    if ("艺龙旅行网".equals(paramRoomStatus.getSourceName()))
    {
      Map localMap2 = (Map)this.hotel.getHotelId().get("elong");
      goHotelWEBActivity("http://m.elong.com/hotel/Detail?HotelId=" + localMap2.get("id") + "&checkindate=" + c.formart(this.hotelReq.getCheckInDate(), "yyyyMMdd") + "&checkoutdate=" + c.formart(this.hotelReq.getCheckOutDate(), "yyyyMMdd") + "&ref=suanyaAp4000394");
    }
    if ("同程网".equals(paramRoomStatus.getSourceName()))
    {
      Map localMap1 = (Map)this.hotel.getHotelId().get("tongcheng");
      goHotelWEBActivity("http://m.ly.com/hotel/jiudian_" + localMap1.get("id") + ".html?refid=19221484", "http://m.ly.com/hotel|comedate=" + c.formart(this.hotelReq.getCheckInDate(), "yyyy-MM-dd") + ";http://m.ly.com/hotel|leavedate=" + c.formart(this.hotelReq.getCheckOutDate(), "yyyy-MM-dd") + ";" + "http://touch.17u.cn|userDownLoadCloseds=true;http://m.ly.com|userDownLoadCloseds=true", null);
    }
  }

  private void initView()
  {
    this.addrArrowTv = ((TextView)findViewById(R.id.adrArrow));
    this.hotelAddressTv = ((TextView)findViewById(R.id.hotelAddress_tv));
    this.telArrowTv = ((TextView)findViewById(R.id.telArrow));
    this.phoneTv = ((TextView)findViewById(R.id.hotelPhone_tv));
    this.hotelDetailImgTv = ((RemoteImageView)findViewById(R.id.hotelDetailImg));
    this.hotelNameTv = ((TextView)findViewById(R.id.mainTitle));
    this.hotelAddressTv.setText("");
    this.phoneTv.setText("");
    this.hotelStatusList = ((ExpandableListView)findViewById(R.id.roomStatusList));
    this.statusAdapter = new RoomStatusListItemAdapter(this, this.roomStatusList, this.hotelStatusList);
    this.hotelStatusList.setAdapter(this.statusAdapter);
    this.hotelStatusList.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
    {
      public boolean onChildClick(ExpandableListView paramExpandableListView, View paramView, int paramInt1, int paramInt2, long paramLong)
      {
        RoomStatus localRoomStatus = (RoomStatus)((RoomStatusSum)HotelDetailActivity.this.roomStatusList.get(paramInt1)).getRoomStatus().get(paramInt2);
        HotelDetailActivity.this.goHotelHtml5(localRoomStatus);
        return false;
      }
    });
    this.hotelStatusList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
    {
      public void onGroupExpand(int paramInt)
      {
        for (int i = 0; i < HotelDetailActivity.this.statusAdapter.getGroupCount(); i++)
        {
          if (paramInt == i)
            continue;
          HotelDetailActivity.this.hotelStatusList.collapseGroup(i);
        }
      }
    });
    this.hotelStatusList.setAdapter(this.statusAdapter);
  }

  private void loadHotelDetail()
  {
    this.roomStatusList.clear();
    this.statusAdapter.notifyDataSetChanged();
    if (this.hotel.getHotelId().get("elong") != null)
      new k(this, "", "请求艺龙数据", true, false)
      {
        protected HotelDetail myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          return HotelDetailActivity.this.hotelService.elongHotelDetail(HotelDetailActivity.this.hotelReq, HotelDetailActivity.this.hotel);
        }

        protected void myPostExecute(HotelDetail paramHotelDetail)
        {
          HotelDetailActivity.this.mergeRooms(paramHotelDetail);
          HotelDetailActivity.this.statusAdapter.notifyDataSetChanged();
        }

        protected void onException(Exception paramException)
        {
          HotelDetailActivity.this.hotelService.asyncLog(new LogInfo("elongDataError", HotelDetailActivity.this.hotel.getHotelId().toString()));
        }
      }
      .execute(new Object[0]);
    if (this.hotel.getHotelId().get("ctrip") != null)
      new k(this, "", "请求携程数据", true, false)
      {
        protected HotelDetail myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          return HotelDetailActivity.this.hotelService.ctripHotelDetail(HotelDetailActivity.this.hotelReq, HotelDetailActivity.this.hotel);
        }

        protected void myPostExecute(HotelDetail paramHotelDetail)
        {
          HotelDetailActivity.this.mergeRooms(paramHotelDetail);
          HotelDetailActivity.this.statusAdapter.notifyDataSetChanged();
        }

        protected void onException(Exception paramException)
        {
          HotelDetailActivity.this.hotelService.asyncLog(new LogInfo("ctripDataError", HotelDetailActivity.this.hotel.getHotelId().toString()));
        }
      }
      .execute(new Object[0]);
    if (this.hotel.getHotelId().get("tongcheng") != null)
      new k(this, "", "请求同程数据", true, false)
      {
        protected HotelDetail myInBackground(Object[] paramArrayOfObject)
          throws Exception
        {
          return HotelDetailActivity.this.hotelService.tongchengHotelDetail(HotelDetailActivity.this.hotelReq, HotelDetailActivity.this.hotel);
        }

        protected void myPostExecute(HotelDetail paramHotelDetail)
        {
          HotelDetailActivity.this.mergeRooms(paramHotelDetail);
          HotelDetailActivity.this.statusAdapter.notifyDataSetChanged();
        }

        protected void onException(Exception paramException)
        {
          HotelDetailActivity.this.hotelService.asyncLog(new LogInfo("tongchengDataError", HotelDetailActivity.this.hotel.getHotelId().toString()));
        }
      }
      .execute(new Object[0]);
  }

  private void mergeRoom(RoomStatus paramRoomStatus)
  {
    Iterator localIterator = this.roomStatusList.iterator();
    while (localIterator.hasNext())
    {
      RoomStatusSum localRoomStatusSum2 = (RoomStatusSum)localIterator.next();
      if (!localRoomStatusSum2.getRoomName().equals(paramRoomStatus.getRoomName()))
        continue;
      localRoomStatusSum2.addByPrice(paramRoomStatus);
      if (localRoomStatusSum2.getLowestPrice() > paramRoomStatus.getPriceNow())
        localRoomStatusSum2.setLowestPrice(paramRoomStatus.getPriceNow());
      return;
    }
    RoomStatusSum localRoomStatusSum1 = new RoomStatusSum();
    localRoomStatusSum1.setRoomName(paramRoomStatus.getRoomName());
    localRoomStatusSum1.setLowestPrice(paramRoomStatus.getPriceNow());
    localRoomStatusSum1.addByPrice(paramRoomStatus);
    this.roomStatusList.add(localRoomStatusSum1);
  }

  private void mergeRooms(HotelDetail paramHotelDetail)
  {
    Iterator localIterator = paramHotelDetail.getRoomStatus().iterator();
    while (localIterator.hasNext())
      mergeRoom((RoomStatus)localIterator.next());
    Collections.sort(this.roomStatusList, new Comparator()
    {
      public int compare(RoomStatusSum paramRoomStatusSum1, RoomStatusSum paramRoomStatusSum2)
      {
        double d = paramRoomStatusSum1.getLowestPrice() - paramRoomStatusSum2.getLowestPrice();
        if (d > 0.0D)
          return 1;
        if (d < 0.0D)
          return -1;
        return 0;
      }
    });
  }

  private void setCheckinDate(Date paramDate)
  {
    this.hotelReq.setCheckInDate(paramDate);
    fillDateInfo();
    loadHotelDetail();
  }

  private void setCheckoutDate(Date paramDate)
  {
    this.hotelReq.setCheckOutDate(paramDate);
    fillDateInfo();
    loadHotelDetail();
  }

  protected int getContentView()
  {
    return R.layout.activity_hotel_detail;
  }

  public void goCityListActivity(int paramInt)
  {
    startActivityForResult(new Intent(this, HotelCityListActivity.class), paramInt);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Date localDate;
    if (-1 == paramInt2)
    {
      localDate = (Date)paramIntent.getSerializableExtra("date");
      if (paramInt1 != R.id.checkInLL)
        break label31;
      setCheckinDate(localDate);
    }
    label31: 
    do
      return;
    while (paramInt1 != R.id.checkOutLL);
    setCheckoutDate(localDate);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.hotelReq = ((FindHotelReq)getIntent().getSerializableExtra("hotelReq"));
    this.hotel = ((HotelInfo)getIntent().getSerializableExtra("hotel"));
    initView();
    fillHotelView();
    fillDateInfo();
    loadHotelDetail();
    setClick(R.id.telArrow, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + HotelDetailActivity.this.hotel.getPhone()));
        localIntent.setFlags(268435456);
        HotelDetailActivity.this.startActivity(localIntent);
      }
    });
    setClick(R.id.adrArrow, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelDetailActivity.this.hotelReq.setBlatitude(HotelDetailActivity.this.hotel.getLatitude());
        HotelDetailActivity.this.hotelReq.setBlongitude(HotelDetailActivity.this.hotel.getLongitude());
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(HotelDetailActivity.this.hotel);
        HotelDetailActivity.this.goHotelMapActivity(HotelDetailActivity.this.hotelReq, localArrayList);
      }
    });
    setClick(R.id.checkInLL, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelDetailActivity.this.goDatePickActivity(c.roundDate(new Date()), HotelDetailActivity.this.hotelReq.getCheckInDate(), 3, R.id.checkInLL);
      }
    });
    setClick(R.id.checkOutLL, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelDetailActivity.this.goDatePickActivity(HotelDetailActivity.this.hotelReq.getCheckInDate(), HotelDetailActivity.this.hotelReq.getCheckOutDate(), 3, R.id.checkOutLL);
      }
    });
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelDetailActivity
 * JD-Core Version:    0.6.0
 */