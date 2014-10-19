package cn.suanya.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import cn.suanya.hotel.adapter.CityListItemAdapter;
import cn.suanya.hotel.android.widget.IndexableListView;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.CityInfo;
import cn.suanya.hotel.domain.ListSectionGroup;
import cn.suanya.hotel.service.CityService;

public class HotelCityListActivity extends HTActivity
{
  private CityListItemAdapter adapter;
  private IndexableListView mListView;
  private ListSectionGroup sectionGroup;

  private void initView()
  {
    this.mListView = ((IndexableListView)findViewById(R.id.city_listview));
    this.mListView.setFastScrollEnabled(true);
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        CityInfo localCityInfo = (CityInfo)HotelCityListActivity.this.sectionGroup.getItem(paramInt);
        if (localCityInfo != null)
        {
          Intent localIntent = new Intent();
          localIntent.putExtra("city", localCityInfo);
          HotelCityListActivity.this.setResult(-1, localIntent);
          HotelCityListActivity.this.finish();
        }
      }
    });
  }

  protected int getContentView()
  {
    return R.layout.activity_city_list;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initView();
    this.sectionGroup = CityService.instance().getCityGroup();
    this.adapter = new CityListItemAdapter(this, this.sectionGroup);
    this.mListView.setAdapter(this.adapter);
    this.mListView.setFastScrollEnabled(true);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelCityListActivity
 * JD-Core Version:    0.6.0
 */