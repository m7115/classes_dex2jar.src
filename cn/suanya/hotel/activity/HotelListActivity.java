package cn.suanya.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.suanya.common.a.k;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.ui.SYActivity;
import cn.suanya.domain.Response;
import cn.suanya.hotel.adapter.HotelListItemAdapter;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.HotelInfo;
import cn.suanya.hotel.service.HotelService;
import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends HTActivity
{
  private HotelListItemAdapter adapter;
  private View footerView;
  private FindHotelReq hotelReq;
  private List<HotelInfo> mHotels;
  private ListView mListView;
  private int mPage = 0;

  private void addHotels(List<HotelInfo> paramList)
  {
    paramList.removeAll(this.mHotels);
    this.mHotels.addAll(paramList);
  }

  private void initView()
  {
    this.mListView = ((ListView)findViewById(R.id.hotelListView));
    setClick(R.id.rightBt, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelListActivity.this.goHotelMapActivity(HotelListActivity.this.hotelReq, null);
      }
    });
    this.footerView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(R.layout.listview_footer_layout, null, false);
    this.footerView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelListActivity.this.loadMoreHotels();
      }
    });
    this.mListView.addFooterView(this.footerView);
    this.adapter = new HotelListItemAdapter(this, this.mHotels, this.hotelReq);
    this.mListView.setAdapter(this.adapter);
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        try
        {
          HotelInfo localHotelInfo = (HotelInfo)HotelListActivity.this.mHotels.get(paramInt);
          HotelListActivity.this.goHotelDetailActivity(HotelListActivity.this.hotelReq, localHotelInfo);
          return;
        }
        catch (Exception localException)
        {
          HotelListActivity.this.hotelService.asyncLog(new LogInfo("HotelListClick", localException.getMessage()));
        }
      }
    });
  }

  private void loadHotels()
  {
    4 local4 = new k(this)
    {
      protected Response<List<HotelInfo>> myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return HotelService.instance().findHotelList((FindHotelReq)paramArrayOfObject[0]);
      }

      protected void myPostExecute(Response<List<HotelInfo>> paramResponse)
      {
        List localList = (List)paramResponse.getData();
        if (localList.isEmpty())
        {
          HotelListActivity.this.mListView.removeFooterView(HotelListActivity.this.footerView);
          return;
        }
        HotelListActivity.access$508(HotelListActivity.this);
        HotelListActivity.this.addHotels(localList);
        HotelListActivity.this.adapter.notifyDataSetChanged();
      }
    };
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.hotelReq;
    local4.execute(arrayOfObject);
  }

  private void loadMoreHotels()
  {
    this.hotelReq.setIndex(this.mHotels.size());
    this.hotelReq.setPage(this.mPage);
    loadHotels();
  }

  private void reLoadHotels()
  {
    this.mPage = 0;
    this.mHotels.clear();
    this.adapter.notifyDataSetChanged();
    loadMoreHotels();
  }

  protected int getContentView()
  {
    return R.layout.activity_hotel_list;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.hotelReq = ((FindHotelReq)getIntent().getSerializableExtra("hotelReq"));
    this.mHotels = ((List)getIntent().getSerializableExtra("hotels"));
    if (this.mHotels == null)
      this.mHotels = new ArrayList();
    this.mPage = 0;
    initView();
    if (this.mHotels.isEmpty())
      reLoadHotels();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelListActivity
 * JD-Core Version:    0.6.0
 */