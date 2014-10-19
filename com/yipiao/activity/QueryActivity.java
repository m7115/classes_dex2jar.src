package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import cn.suanya.common.a.c;
import cn.suanya.common.a.i;
import com.google.gson.reflect.TypeToken;
import com.yipiao.Config;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.RecentQueryAdapter;
import com.yipiao.base.HCUtil;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.StationNode;
import com.yipiao.view.RandomLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class QueryActivity extends LoginActivity
{
  static int upgradeMethodIndex = 0;
  private RandomLayout _randomImageView;
  protected ChainQuery cq;
  private TextView edit_arrivestation;
  private TextView edit_startstation;
  private RadioGroup engineGroup;
  private ListView listView;
  View list_header;
  private RecentQueryAdapter recentAdapter;
  private List<HashMap<String, StationNode>> recentList;
  private View topBlank;
  private TextView tv6;
  private TextView tv62;
  private TextView tv7;

  private void initRecent()
  {
    this.recentList = ((List)i.a(this.app.sp.getString("RecentQuery", ""), new TypeToken()
    {
    }
    .getType()));
    if (this.recentList == null)
      this.recentList = new ArrayList();
    this.listView = ((ListView)findViewById(2131296739));
    View localView = LayoutInflater.from(this).inflate(2130903153, null);
    this.listView.addHeaderView(localView);
    showListHader();
    this.recentAdapter = new RecentQueryAdapter(this, this.recentList, 2130903147);
    this.listView.setAdapter(this.recentAdapter);
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if ((QueryActivity.this.listView.getHeaderViewsCount() > 0) && (paramInt == 0))
          return;
        HashMap localHashMap = (HashMap)QueryActivity.this.recentAdapter.getItem(paramInt - 1);
        QueryActivity.this.cq.setArr((StationNode)localHashMap.get("to"));
        QueryActivity.this.edit_arrivestation.setText(((StationNode)localHashMap.get("to")).getName());
        QueryActivity.this.cq.setOrg((StationNode)localHashMap.get("from"));
        QueryActivity.this.edit_startstation.setText(((StationNode)localHashMap.get("from")).getName());
      }
    });
    this.listView.setOnCreateContextMenuListener(this);
  }

  private void insertRecent(StationNode paramStationNode1, StationNode paramStationNode2)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("from", paramStationNode1);
    localHashMap1.put("to", paramStationNode2);
    Iterator localIterator = this.recentList.iterator();
    while (localIterator.hasNext())
    {
      HashMap localHashMap2 = (HashMap)localIterator.next();
      if ((!((StationNode)localHashMap2.get("from")).equals(paramStationNode1)) || (!((StationNode)localHashMap2.get("to")).equals(paramStationNode2)))
        continue;
      this.recentList.remove(localHashMap1);
    }
    this.recentList.add(0, localHashMap1);
    if (this.recentList.size() > 6)
      this.recentList.remove(6);
    this.app.sp.edit().putString("RecentQuery", i.a(this.recentList)).commit();
  }

  private void randomImage()
  {
    if (this._randomImageView != null)
    {
      this._randomImageView.random();
      if (this.topBlank != null)
      {
        if (this._randomImageView.getVisibility() != 8)
          break label42;
        this.topBlank.setVisibility(0);
      }
    }
    label42: 
    do
      return;
    while (this._randomImageView.getVisibility() != 0);
    this.topBlank.setVisibility(8);
  }

  private void showListHader()
  {
    if (this.list_header != null)
    {
      if (this.recentList.size() > 0)
        this.list_header.setVisibility(0);
    }
    else
      return;
    this.list_header.setVisibility(8);
  }

  private void showTrainType()
  {
    if (this.cq.getTrainTypes().containsAll(this.cfg.trainTypeSimples))
    {
      setTv(2131296765, "所有车型");
      return;
    }
    if (this.cq.getTrainTypes().isEmpty())
    {
      setTv(2131296765, "请选择");
      return;
    }
    setTv(2131296765, this.cq.getTrainTypes().linkName(","));
  }

  private void updateDisplay()
  {
    this.tv6.setText(" " + c.formartMMdd(this.cq.getLeavedate2()) + " ");
    int i;
    if ((this.cq.getTimeRangBegin() == null) || ("".equals(this.cq.getTimeRangBegin())) || (this.cq.getTimeRangEnd() == null) || ("".equals(this.cq.getTimeRangEnd())) || (("00:00".equals(this.cq.getTimeRangBegin())) && ("24:00".equals(this.cq.getTimeRangEnd()))))
    {
      i = 1;
      if (i == 0)
        break label162;
      this.tv62.setVisibility(8);
    }
    while (true)
    {
      this.tv7.setText(c.getWeek(this.cq.getLeavedate2()));
      return;
      i = 0;
      break;
      label162: this.tv62.setText(this.cq.getTimeRangBegin() + "--" + this.cq.getTimeRangEnd());
      this.tv62.setVisibility(0);
    }
  }

  public void chainQuery()
  {
    Intent localIntent = new Intent(this, QuerySeatListActivity.class);
    this.cq.setResults(null);
    try
    {
      this.app.putParms("cq", this.cq.clone());
      label37: startActivity(localIntent);
      return;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      break label37;
    }
  }

  protected int getMainLayout()
  {
    return 2130903146;
  }

  protected void goHotelWebActivity()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://touch.qunar.com/h5/hotel/?bd_source=zhixing").append("&city=").append(HCUtil.getCityName(this.cq.getArr().getName()));
    String str = localStringBuilder.toString();
    Intent localIntent = new Intent(this, FlightWEBActivity.class);
    localIntent.putExtra("url", str);
    localIntent.putExtra("webTip", this.app.launchInfo.optString("hotel_web_tip", "酒店数据由去哪儿网独家提供"));
    if ("http://touch.qunar.com|QN75=closed" != null)
      localIntent.putExtra("cookies", "http://touch.qunar.com|QN75=closed");
    if (0 != 0)
      localIntent.putExtra("postPar", null);
    startActivity(localIntent);
  }

  public void init()
  {
    super.init();
    initRecent();
    this.list_header = findViewById(2131297048);
    this.cq = this.app.getCq();
    if (this.cq == null)
      this.cq = new ChainQuery();
    setClick(2131296892, this);
    setClick(2131296894, this);
    setClick(2131296899, this);
    setClick(2131296900, this);
    setClick(2131296763, this);
    setClick(2131297032, this);
    setClick(2131297033, this);
    setClick(2131297034, this);
    setClick(2131296801, this);
    setClick(2131296259, AdvancedSettingActivity.class);
    this.tv6 = ((TextView)findViewById(2131296768));
    this.tv62 = ((TextView)findViewById(2131297031));
    this.tv7 = ((TextView)findViewById(2131296769));
    this.edit_startstation = ((TextView)findViewById(2131296893));
    this.edit_startstation.setText(this.cq.getOrg().getName());
    this.edit_arrivestation = ((TextView)findViewById(2131296895));
    this.edit_arrivestation.setText(this.cq.getArr().getName());
    setTv(2131296770, this.cq.getTimeRang());
    showTrainType();
    setClick(2131296885, this);
    this.engineGroup = ((RadioGroup)findViewById(2131297025));
    updateDisplay();
    this.topBlank = findViewById(2131297029);
    this._randomImageView = ((RandomLayout)findViewById(2131297028));
    if (this._randomImageView != null)
    {
      this._randomImageView.setVisibility(8);
      JSONArray localJSONArray = optJsonArrayFromLaunchInfo("query_top");
      this._randomImageView.init(localJSONArray);
      randomImage();
    }
  }

  @SuppressLint({"SimpleDateFormat"})
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 2131296892) && (paramInt2 != 0))
      setCqArr((StationNode)paramIntent.getExtras().get("DATA"));
    if ((paramInt1 == 2131296894) && (paramInt2 != 0))
      setCqOrg((StationNode)paramIntent.getExtras().get("DATA"));
    if ((paramInt1 == 2131296899) && (paramInt2 != 0))
    {
      Date localDate = (Date)paramIntent.getSerializableExtra("currentDate");
      String str1 = (String)paramIntent.getSerializableExtra("begin");
      String str2 = (String)paramIntent.getSerializableExtra("end");
      this.cq.setLeaveDate(new SimpleDateFormat("yyyy-MM-dd").format(localDate));
      this.cq.setTimeRangBegin(str1);
      this.cq.setTimeRangEnd(str2);
      updateDisplay();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296801:
      StationNode localStationNode3 = this.cq.getArr();
      StationNode localStationNode4 = this.cq.getOrg();
      this.cq.setArr(localStationNode4);
      this.cq.setOrg(localStationNode3);
      this.edit_arrivestation.setText(this.cq.getArr().getName());
      this.edit_startstation.setText(this.cq.getOrg().getName());
      return;
    case 2131296892:
      noteFilterDialog(2131296892);
      return;
    case 2131296894:
      noteFilterDialog(2131296894);
      return;
    case 2131296899:
      goDateTimePickActivity(c.roundDate(new Date()), this.cq.getLeavedate2(), this.cq.getTimeRangBegin(), this.cq.getTimeRangEnd(), 2, 2131296899);
      return;
    case 2131296900:
      showDialog(103);
      return;
    case 2131296763:
      showDialog(102);
      return;
    case 2131297032:
      this.cq.setStudent("0X00");
      chainQuery();
      this.app.saveCq();
      insertRecent(this.cq.getOrg(), this.cq.getArr());
      return;
    case 2131296885:
      this.cq.setStudent("00");
      chainQuery();
      this.app.saveCq();
      insertRecent(this.cq.getOrg(), this.cq.getArr());
      return;
    case 2131297033:
      goHotelWebActivity();
      return;
    case 2131297034:
    }
    logToServer("goPlane", this.cq.getArr().getName());
    StationNode localStationNode1 = this.cq.getOrg();
    StationNode localStationNode2 = this.cq.getArr();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://touch.qunar.com/h5/flight/flightlist?bd_source=zhixing").append("&startCity=").append(localStationNode1.getCityName()).append("&destCity=").append(localStationNode2.getCityName()).append("&startDate=").append(this.cq.getLeaveDate()).append("&backDate=&flightType=oneWay");
    goFlightWEBActivity(localStringBuilder.toString(), "http://touch.qunar.com|QN75=closed", null);
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    int i;
    if (paramMenuItem.getItemId() == 0)
    {
      i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
      if ((this.listView.getHeaderViewsCount() <= 0) || (i != 0))
        break label42;
    }
    while (true)
    {
      return super.onContextItemSelected(paramMenuItem);
      label42: this.recentList.remove(i - 1);
      this.recentAdapter.notifyDataSetChanged();
      setListViewHeightBasedOnChildren(this.listView);
      this.app.sp.edit().putString("RecentQuery", i.a(this.recentList)).commit();
      showListHader();
    }
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    if ((paramView == this.listView) && (((AdapterView.AdapterContextMenuInfo)paramContextMenuInfo).position > 0))
      paramContextMenu.add(0, 0, 0, "删除");
  }

  protected void onResume()
  {
    if (this.app.isNewApi())
      this.engineGroup.check(2131297026);
    while (true)
    {
      this.recentAdapter.notifyDataSetChanged();
      setListViewHeightBasedOnChildren(this.listView);
      showListHader();
      randomImage();
      super.onResume();
      return;
      this.engineGroup.check(2131297027);
    }
  }

  protected void setCqArr(StationNode paramStationNode)
  {
    if (paramStationNode.equals(this.cq.getArr()))
    {
      this.cq.setArr(this.cq.getOrg());
      this.edit_arrivestation.setText(this.cq.getOrg().getName());
    }
    this.cq.setOrg(paramStationNode);
    this.edit_startstation.setText(paramStationNode.getName());
  }

  protected void setCqOrg(StationNode paramStationNode)
  {
    if (paramStationNode.equals(this.cq.getOrg()))
    {
      this.cq.setOrg(this.cq.getArr());
      this.edit_startstation.setText(this.cq.getArr().getName());
    }
    this.cq.setArr(paramStationNode);
    this.edit_arrivestation.setText(paramStationNode.getName());
  }

  public void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    int j = 0;
    while (i < localListAdapter.getCount())
    {
      View localView = localListAdapter.getView(i, null, paramListView);
      localView.measure(0, 0);
      j += localView.getMeasuredHeight();
      i++;
    }
    ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
    localLayoutParams.height = (j + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
    paramListView.setLayoutParams(localLayoutParams);
  }

  protected void timeRangSelect(String paramString)
  {
    this.cq.setTimeRang(paramString);
    setTv(2131296770, paramString);
  }

  protected void trainTypeSelectMul(NoteList paramNoteList)
  {
    this.cq.setTrainTypes(paramNoteList);
    showTrainType();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.QueryActivity
 * JD-Core Version:    0.6.0
 */