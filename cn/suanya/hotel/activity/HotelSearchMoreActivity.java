package cn.suanya.hotel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import cn.suanya.common.persistent.SYPersitentFile;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.RecentSearch;
import cn.suanya.hotel.listener.MyMKSearchListener;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionInfo;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class HotelSearchMoreActivity extends HTActivity
  implements TextWatcher, View.OnCreateContextMenuListener
{
  private static SYPersitentFile reqFile;
  private EditText editText;
  private FindHotelReq hotelReq;
  private ListView listView;
  private MKSearch mkSearch = new MKSearch();
  private ProgressDialog pDialog;
  private ProgressBar progressBar1;
  private ArrayList<RecentSearch> recentResultList;
  private MKSearchListener searchListener = new MySearchListener();

  private void addRecentSearch(RecentSearch paramRecentSearch)
  {
    Iterator localIterator = this.recentResultList.iterator();
    while (localIterator.hasNext())
    {
      RecentSearch localRecentSearch = (RecentSearch)localIterator.next();
      if (!localRecentSearch.toString().equals(paramRecentSearch.toString()))
        continue;
      this.recentResultList.remove(localRecentSearch);
    }
    if (this.recentResultList.size() >= 10)
      this.recentResultList.remove(0);
    this.recentResultList.add(0, paramRecentSearch);
    writeRecentSearchList(this.recentResultList);
  }

  private void initRecentList()
  {
    refreshRecentSearchList();
    this.listView.setOnCreateContextMenuListener(this);
    this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        RecentSearch localRecentSearch = (RecentSearch)HotelSearchMoreActivity.this.recentResultList.get(paramInt);
        HotelSearchMoreActivity.this.mkSearch.geocode(localRecentSearch.toString(), localRecentSearch.getCity());
        HotelSearchMoreActivity.this.pDialog.show();
        HotelSearchMoreActivity.this.addRecentSearch(localRecentSearch);
      }
    });
  }

  private ArrayList<RecentSearch> readRecentSearchList()
  {
    return (ArrayList)reqFile.readObject("recentSearch");
  }

  private void refreshRecentSearchList()
  {
    this.recentResultList = readRecentSearchList();
    if (this.recentResultList == null)
    {
      this.recentResultList = new ArrayList();
      return;
    }
    String[] arrayOfString = new String[this.recentResultList.size()];
    for (int i = 0; i < arrayOfString.length; i++)
      arrayOfString[i] = ((RecentSearch)this.recentResultList.get(i)).getStr();
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this, R.layout.list_item_search_result, arrayOfString);
    this.listView.setAdapter(localArrayAdapter);
  }

  private void writeRecentSearchList(ArrayList<RecentSearch> paramArrayList)
  {
    reqFile.writeObject("recentSearch", paramArrayList);
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if (!this.editText.getText().toString().trim().equals(""))
      this.progressBar1.setVisibility(0);
    while (true)
    {
      this.mkSearch.suggestionSearch(paramEditable.toString(), this.hotelReq.getCityName());
      return;
      this.progressBar1.setVisibility(8);
    }
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  protected int getContentView()
  {
    return R.layout.activity_hotel_search_more;
  }

  protected void init()
  {
    super.init();
    if (reqFile == null)
      reqFile = new SYPersitentFile(getFilesDir().getAbsolutePath() + "/");
    this.hotelReq = ((FindHotelReq)getIntent().getSerializableExtra("hotelReq"));
    this.editText = ((EditText)findViewById(R.id.editText));
    this.listView = ((ListView)findViewById(R.id.listView));
    this.progressBar1 = ((ProgressBar)findViewById(R.id.progressBar1));
    this.pDialog = new ProgressDialog(this);
    this.pDialog.setMessage("请稍候...");
    this.mkSearch.init(HotelMapActivity.mBMapMan, this.searchListener);
    this.editText.addTextChangedListener(this);
    initRecentList();
    this.listView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        HotelSearchMoreActivity.this.listView.setFocusable(true);
        HotelSearchMoreActivity.this.listView.requestFocus();
        ((InputMethodManager)HotelSearchMoreActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(HotelSearchMoreActivity.this.listView.getWindowToken(), 0);
        return false;
      }
    });
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 0)
    {
      int i = ((AdapterView.AdapterContextMenuInfo)paramMenuItem.getMenuInfo()).position;
      this.recentResultList.remove(i);
      writeRecentSearchList(this.recentResultList);
      refreshRecentSearchList();
    }
    return super.onContextItemSelected(paramMenuItem);
  }

  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    paramContextMenu.add(0, 0, 0, "删除");
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  class MySearchListener extends MyMKSearchListener
  {
    MySearchListener()
    {
    }

    public void onGetAddrResult(MKAddrInfo paramMKAddrInfo, int paramInt)
    {
      if (paramInt != 0)
      {
        System.out.println("错误号：" + paramInt);
        HotelSearchMoreActivity.this.finish();
        return;
      }
      HotelSearchMoreActivity.this.hotelReq.setBlatitude(paramMKAddrInfo.geoPt.getLatitudeE6() / 1000000.0D);
      HotelSearchMoreActivity.this.hotelReq.setBlongitude(paramMKAddrInfo.geoPt.getLongitudeE6() / 1000000.0D);
      System.out.println("经纬度：" + HotelSearchMoreActivity.this.hotelReq.getBlatitude() + "  " + HotelSearchMoreActivity.this.hotelReq.getBlongitude());
      HotelSearchMoreActivity.this.goHotelMapActivity(HotelSearchMoreActivity.this.hotelReq, null);
      HotelSearchMoreActivity.this.finish();
    }

    public void onGetSuggestionResult(MKSuggestionResult paramMKSuggestionResult, int paramInt)
    {
      HotelSearchMoreActivity.this.progressBar1.setVisibility(8);
      HotelSearchMoreActivity.this.pDialog.dismiss();
      if ((paramInt != 0) || (paramMKSuggestionResult == null))
        Toast.makeText(HotelSearchMoreActivity.this, "抱歉，未找到结果", 1).show();
      ArrayList localArrayList;
      do
      {
        return;
        localArrayList = new ArrayList();
        int i = paramMKSuggestionResult.getSuggestionNum();
        for (int j = 0; j < i; j++)
        {
          if ((paramMKSuggestionResult.getSuggestion(j) == null) || (paramMKSuggestionResult.getSuggestion(j).city == null) || (!paramMKSuggestionResult.getSuggestion(j).city.equals(HotelSearchMoreActivity.this.hotelReq.getCityName() + "市")))
            continue;
          localArrayList.add(paramMKSuggestionResult.getSuggestion(j));
        }
      }
      while ((localArrayList == null) || (localArrayList.size() <= 0));
      String[] arrayOfString = new String[localArrayList.size()];
      for (int k = 0; k < arrayOfString.length; k++)
        arrayOfString[k] = (((MKSuggestionInfo)localArrayList.get(k)).city + ((MKSuggestionInfo)localArrayList.get(k)).district + ((MKSuggestionInfo)localArrayList.get(k)).key);
      ArrayAdapter localArrayAdapter = new ArrayAdapter(HotelSearchMoreActivity.this, R.layout.list_item_search_result, arrayOfString);
      HotelSearchMoreActivity.this.listView.setAdapter(localArrayAdapter);
      HotelSearchMoreActivity.this.listView.setOnCreateContextMenuListener(null);
      HotelSearchMoreActivity.this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(localArrayList)
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          MKSuggestionInfo localMKSuggestionInfo = (MKSuggestionInfo)this.val$ss.get(paramInt);
          HotelSearchMoreActivity.this.mkSearch.geocode(localMKSuggestionInfo.key + localMKSuggestionInfo.city + localMKSuggestionInfo.district, localMKSuggestionInfo.city);
          HotelSearchMoreActivity.this.pDialog.show();
          HotelSearchMoreActivity.this.addRecentSearch(new RecentSearch(localMKSuggestionInfo.key, localMKSuggestionInfo.city, localMKSuggestionInfo.district));
        }
      });
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelSearchMoreActivity
 * JD-Core Version:    0.6.0
 */