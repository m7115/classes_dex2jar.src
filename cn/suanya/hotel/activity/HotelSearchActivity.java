package cn.suanya.hotel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import cn.suanya.common.a.c;
import cn.suanya.common.a.p;
import cn.suanya.common.a.q;
import cn.suanya.common.persistent.SYPersitentFile;
import cn.suanya.hotel.HTConstants;
import cn.suanya.hotel.base.R.drawable;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.CityInfo;
import cn.suanya.hotel.domain.District;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.service.CityService;
import cn.suanya.ui.tableView.SYTableView;
import cn.suanya.ui.tableView.TableItem;
import cn.suanya.ui.tableView.TableItemDelegate;
import cn.suanya.ui.tableView.TableItemGroup;
import cn.suanya.ui.tableView.TableItemGroups;
import cn.suanya.ui.tableView.TableItemSimple;
import cn.suanya.ui.tableView.TableItemSingleChoice;
import java.io.File;
import java.util.Date;

public class HotelSearchActivity extends HTActivity
  implements TableItemDelegate
{
  private static FindHotelReq hotelReq;
  private static SYPersitentFile reqFile;
  private TableItem checkInItem;
  private TableItem checkOutItem;
  private TableItem cityItem;
  private TableItemSingleChoice districtItem;
  private TableItemSingleChoice priceItem;
  private TableItemSingleChoice starItem;
  private SYTableView tableView;

  private TableItemGroups createList()
  {
    this.cityItem = new TableItemSimple(this, "城市", "");
    this.districtItem = new TableItemSingleChoice(this, "行政区", null, null);
    this.checkInItem = new TableItemSimple(this, "入住时间", "");
    this.checkOutItem = new TableItemSimple(this, "离店时间", "");
    this.priceItem = new TableItemSingleChoice(this, "房价", HTConstants.priceLevel, (p)HTConstants.priceLevel.get(0));
    this.starItem = new TableItemSingleChoice(this, "酒店星级", HTConstants.star, (p)HTConstants.star.get(0));
    TableItem[] arrayOfTableItem1 = new TableItem[4];
    arrayOfTableItem1[0] = this.cityItem;
    arrayOfTableItem1[1] = this.districtItem;
    arrayOfTableItem1[2] = this.checkInItem;
    arrayOfTableItem1[3] = this.checkOutItem;
    TableItemGroup localTableItemGroup = new TableItemGroup(arrayOfTableItem1);
    TableItem[] arrayOfTableItem2 = new TableItem[2];
    arrayOfTableItem2[0] = this.priceItem;
    arrayOfTableItem2[1] = this.starItem;
    return new TableItemGroups(new TableItemGroup[] { localTableItemGroup, new TableItemGroup(arrayOfTableItem2) });
  }

  private void refresh()
  {
    this.cityItem.setLabel(hotelReq.getCityName());
    q localq = CityService.instance().getDistrict(hotelReq.getCityId());
    this.districtItem.setOptions(localq);
    Object localObject = hotelReq.getDistrict();
    if ((localObject == null) || (!localq.contains(localObject)))
      localObject = (p)localq.get(0);
    this.districtItem.setValue(localObject);
    this.checkInItem.setLabel(c.formartMMddW(hotelReq.getCheckInDate()));
    this.checkOutItem.setLabel(c.formartMMddW(hotelReq.getCheckOutDate()));
    this.priceItem.setValue(HTConstants.priceLevel.a(hotelReq.getPriceLevel()));
    this.starItem.setValue(HTConstants.star.a(hotelReq.getStar()));
  }

  private void setCheckinDate(Date paramDate)
  {
    hotelReq.setCheckInDate(paramDate);
    this.checkInItem.setLabel(c.formartMMddW(hotelReq.getCheckInDate()));
    this.checkOutItem.setLabel(c.formartMMddW(hotelReq.getCheckOutDate()));
  }

  private void setCheckoutDate(Date paramDate)
  {
    hotelReq.setCheckOutDate(paramDate);
    this.checkInItem.setLabel(c.formartMMddW(hotelReq.getCheckInDate()));
    this.checkOutItem.setLabel(c.formartMMddW(hotelReq.getCheckOutDate()));
  }

  protected int getContentView()
  {
    return R.layout.activity_hotel_search;
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
      if (paramInt1 != this.checkInItem.getId())
        break label36;
      setCheckinDate(localDate);
    }
    label36: CityInfo localCityInfo;
    do
    {
      do
      {
        return;
        if (paramInt1 != this.checkOutItem.getId())
          continue;
        setCheckoutDate(localDate);
        return;
      }
      while (paramInt1 != this.cityItem.getId());
      localCityInfo = (CityInfo)paramIntent.getSerializableExtra("city");
    }
    while (localCityInfo == null);
    hotelReq.setCity(localCityInfo);
    this.cityItem.setLabel(localCityInfo.getName());
    q localq = CityService.instance().getDistrict(hotelReq.getCityId());
    this.districtItem.setOptions(localq);
    this.districtItem.setValue(localq.get(0));
  }

  protected void onCreate(Bundle paramBundle)
  {
    if (reqFile == null)
      reqFile = new SYPersitentFile(getFilesDir().getAbsolutePath() + "/");
    if (hotelReq == null)
      hotelReq = readCq();
    hotelReq.setRadius(Integer.valueOf(2000));
    super.onCreate(paramBundle);
    this.tableView = ((SYTableView)findViewById(R.id.tableView));
    this.tableView.setUpItems(createList(), R.layout.list_container_no_border2, R.drawable.table_top_single_bg, R.drawable.table_top_bg, R.drawable.table_bottom_bg, R.drawable.table_bottom_bg);
    findViewById(R.id.contentView).setVisibility(0);
    refresh();
    setClick(R.id.searchBT, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelSearchActivity.this.saveCq();
        HotelSearchActivity.this.goHotelListActivity(HotelSearchActivity.hotelReq, null);
      }
    });
    setClick(R.id.mapSearchBT, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelSearchActivity.this.saveCq();
        HotelSearchActivity.this.goHotelMapActivity(HotelSearchActivity.hotelReq, null);
      }
    });
    setClick(R.id.rightBt, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        HotelSearchActivity.this.goHotelOrderActivity();
      }
    });
  }

  public void onTableItemChanged(TableItem paramTableItem)
  {
    if (this.starItem.equals(paramTableItem))
      hotelReq.setStar(this.starItem.getValue().getCode());
    do
    {
      return;
      if (!this.priceItem.equals(paramTableItem))
        continue;
      hotelReq.setPriceLevel(this.priceItem.getValue().getCode());
      return;
    }
    while (!this.districtItem.equals(paramTableItem));
    hotelReq.setDistrict((District)this.districtItem.getValue());
  }

  public void onTableItemClick(TableItem paramTableItem)
  {
    if (this.cityItem.equals(paramTableItem))
      goCityListActivity(this.cityItem.getId());
    do
    {
      return;
      if (!this.checkInItem.equals(paramTableItem))
        continue;
      goDatePickActivity(c.roundDate(new Date()), hotelReq.getCheckInDate(), 3, this.checkInItem.getId());
      return;
    }
    while (!this.checkOutItem.equals(paramTableItem));
    goDatePickActivity(hotelReq.getCheckInDate(), hotelReq.getCheckOutDate(), 3, this.checkOutItem.getId());
  }

  public FindHotelReq readCq()
  {
    FindHotelReq localFindHotelReq1 = new FindHotelReq();
    FindHotelReq localFindHotelReq2 = (FindHotelReq)reqFile.readObject("findHotelReq");
    if (localFindHotelReq2 == null)
      localFindHotelReq2 = localFindHotelReq1;
    do
      return localFindHotelReq2;
    while (localFindHotelReq2.getCheckInDate().getTime() >= System.currentTimeMillis());
    localFindHotelReq2.setCheckInDate(localFindHotelReq1.getCheckInDate());
    localFindHotelReq2.setCheckOutDate(localFindHotelReq1.getCheckOutDate());
    return localFindHotelReq2;
  }

  public void saveCq()
  {
    reqFile.writeObject("findHotelReq", hotelReq);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelSearchActivity
 * JD-Core Version:    0.6.0
 */