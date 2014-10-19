package cn.suanya.hotel.activity;

import android.os.Bundle;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.hotel.base.R.drawable;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.ui.tableView.SYTableView;
import cn.suanya.ui.tableView.TableItem;
import cn.suanya.ui.tableView.TableItemDelegate;
import cn.suanya.ui.tableView.TableItemGroup;
import cn.suanya.ui.tableView.TableItemGroups;
import cn.suanya.ui.tableView.TableItemSimple;
import org.json.JSONObject;

public class HotelOrderActivity extends HTActivity
  implements TableItemDelegate
{
  protected TableItem ctripItem;
  protected TableItem elongInItem;
  protected TableItem qnrItem;
  private SYTableView tableView;
  protected TableItem tongchengItem;

  private TableItemGroups createList()
  {
    this.ctripItem = new TableItemSimple(this, "携程订单", "");
    this.elongInItem = new TableItemSimple(this, "艺龙订单", "");
    this.tongchengItem = new TableItemSimple(this, "同程订单", "");
    this.qnrItem = new TableItemSimple(this, "去哪儿订单", "");
    TableItem[] arrayOfTableItem = new TableItem[4];
    arrayOfTableItem[0] = this.ctripItem;
    arrayOfTableItem[1] = this.elongInItem;
    arrayOfTableItem[2] = this.tongchengItem;
    arrayOfTableItem[3] = this.qnrItem;
    return new TableItemGroups(new TableItemGroup[] { new TableItemGroup(arrayOfTableItem) });
  }

  protected int getContentView()
  {
    return R.layout.activity_other;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.tableView = ((SYTableView)findViewById(R.id.tableView));
    this.tableView.setUpItems(createList(), R.layout.list_container_no_border2, R.drawable.table_top_single_bg, R.drawable.table_top_bg, R.drawable.table_bottom_bg, R.drawable.table_bottom_bg);
  }

  public void onTableItemChanged(TableItem paramTableItem)
  {
  }

  public void onTableItemClick(TableItem paramTableItem)
  {
    if (this.ctripItem.equals(paramTableItem))
      goHotelWEBActivity(this.app.launchInfo.optString("hotel.ctrip", "http://m.ctrip.com/webapp/myctrip/"));
    if (this.elongInItem.equals(paramTableItem))
      goHotelWEBActivity(this.app.launchInfo.optString("hotel.elong", "http://m.elong.com/Order/HotelOrderList"));
    if (this.tongchengItem.equals(paramTableItem))
      goHotelWEBActivity(this.app.launchInfo.optString("hotel.tongcheng", "http://m.ly.com/hotel/orderlist.html"));
    if (this.qnrItem.equals(paramTableItem))
      goHotelWEBActivity(this.app.launchInfo.optString("hotel.qnr", "http://touch.qunar.com/h5/flight/flightorderlist?jmp=1&bd_source=zhixing"));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelOrderActivity
 * JD-Core Version:    0.6.0
 */