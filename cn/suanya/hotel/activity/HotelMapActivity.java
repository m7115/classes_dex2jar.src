package cn.suanya.hotel.activity;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.suanya.common.a.c;
import cn.suanya.common.a.k;
import cn.suanya.common.a.p;
import cn.suanya.common.a.q;
import cn.suanya.common.ui.SYActivity;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.domain.Response;
import cn.suanya.hotel.android.widget.MyMapView;
import cn.suanya.hotel.base.R.drawable;
import cn.suanya.hotel.base.R.id;
import cn.suanya.hotel.base.R.layout;
import cn.suanya.hotel.base.R.style;
import cn.suanya.hotel.domain.CityInfo;
import cn.suanya.hotel.domain.District;
import cn.suanya.hotel.domain.FindHotelReq;
import cn.suanya.hotel.domain.HotelInfo;
import cn.suanya.hotel.domain.Location;
import cn.suanya.hotel.listener.MyMKSearchListener;
import cn.suanya.hotel.service.CityService;
import cn.suanya.hotel.service.HotelService;
import cn.suanya.hotel.util.MapUtil;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MKMapTouchListener;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKGeocoderAddressComponent;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HotelMapActivity extends HTActivity
  implements View.OnClickListener
{
  static DecimalFormat df;
  public static BMapManager mBMapMan;
  static GeoPoint mapViewGeo;
  private static int zoom = 16;
  private TextView cityTitle;
  private Location currLocation;
  private boolean directToLocation = false;
  private FindHotelReq hotelReq;
  private OverlayTest itemOverlay;
  private TextView keywordTv;
  private List<HotelInfo> mHotels;
  public LocationClient mLocationClient = null;
  public MyMapView mMapView = null;
  private Vibrator mVibrator;
  private View mapParentView;
  private MKMapTouchListener mapTouchListener;
  private MKSearch mkSerach = new MKSearch();
  public BDLocationListener myListener = new MyLocationListener();
  private MyLocationOverlay myLocationOverlay;
  private MKSearchListener mySearchListener = new MySearchListener();
  private List<HotelInfo> periHotels;
  private GeoPoint preGeoPoint;

  static
  {
    mBMapMan = null;
    df = new DecimalFormat("0.#");
    mapViewGeo = null;
  }

  private void addHotels(List<HotelInfo> paramList)
  {
    this.mHotels.removeAll(paramList);
    this.mHotels.addAll(0, paramList);
    sortHotels(this.mHotels);
    if (this.mHotels.size() > 40)
      this.mHotels = new ArrayList(this.mHotels.subList(0, 40));
  }

  private void clearOverlays()
  {
    if (this.itemOverlay != null)
    {
      this.itemOverlay.removeAll();
      this.mMapView.refresh();
    }
  }

  private Location getReqLocation(FindHotelReq paramFindHotelReq)
  {
    if (paramFindHotelReq == null);
    do
    {
      return null;
      if ((paramFindHotelReq.getBlatitude() != 0.0D) && (paramFindHotelReq.getBlongitude() != 0.0D))
        return new Location(paramFindHotelReq.getCityName(), paramFindHotelReq.getBlatitude(), paramFindHotelReq.getBlongitude());
    }
    while (paramFindHotelReq.getCity() == null);
    if ((paramFindHotelReq.getDistrict() != null) && (paramFindHotelReq.getDistrict().getLatitude() != 0.0D) && (paramFindHotelReq.getDistrict().getLongitude() != 0.0D))
      return new Location(paramFindHotelReq.getCity().getName(), paramFindHotelReq.getDistrict().getLatitude(), paramFindHotelReq.getDistrict().getLongitude());
    return new Location(paramFindHotelReq.getCity().getName(), paramFindHotelReq.getCity().getLatitude(), paramFindHotelReq.getCity().getLongitude());
  }

  private void initListener()
  {
    1 local1 = new MKMapViewListener()
    {
      public void onClickMapPoi(MapPoi paramMapPoi)
      {
        HotelMapActivity.this.showToast("点击");
      }

      public void onGetCurrentMap(Bitmap paramBitmap)
      {
      }

      public void onMapAnimationFinish()
      {
      }

      public void onMapLoadFinish()
      {
      }

      public void onMapMoveFinish()
      {
        GeoPoint localGeoPoint = HotelMapActivity.this.getCurrGeo();
        HotelMapActivity.mapViewGeo = localGeoPoint;
        if ((HotelMapActivity.this.preGeoPoint == null) || (MapUtil.distance(HotelMapActivity.this.preGeoPoint, localGeoPoint) > 0.005D))
        {
          HotelMapActivity.access$002(HotelMapActivity.this, localGeoPoint);
          HotelMapActivity.this.mkSerach.reverseGeocode(HotelMapActivity.this.preGeoPoint);
        }
      }
    };
    this.mMapView.regMapViewListener(mBMapMan, local1);
    this.mapTouchListener = new MKMapTouchListener()
    {
      public void onMapClick(GeoPoint paramGeoPoint)
      {
      }

      public void onMapDoubleClick(GeoPoint paramGeoPoint)
      {
      }

      public void onMapLongClick(GeoPoint paramGeoPoint)
      {
        HotelMapActivity.this.findViewById(R.id.tip_view).setVisibility(8);
        long[] arrayOfLong = { 100L, 100L };
        HotelMapActivity.this.mVibrator.vibrate(arrayOfLong, -1);
        HotelMapActivity.this.onLongPressLocation(paramGeoPoint);
      }
    };
    this.mMapView.regMapTouchListner(this.mapTouchListener);
  }

  private void onLongPressLocation(GeoPoint paramGeoPoint)
  {
    if (this.mMapView != null)
    {
      this.mMapView.refresh();
      this.mMapView.getController().animateTo(paramGeoPoint);
    }
    searchHotelsBylocation(paramGeoPoint);
  }

  private void setCityByCityName(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.endsWith("市"))
        paramString = paramString.substring(0, -1 + paramString.length());
      p localp = CityService.instance().getAllCity().c(paramString);
      this.cityTitle.setText("-" + paramString);
      if (localp != null)
      {
        this.hotelReq.setCity((CityInfo)localp);
        this.cityTitle.setText("-" + paramString);
      }
    }
    else
    {
      return;
    }
    this.cityTitle.setText("-" + paramString);
  }

  private void setLocationOption()
  {
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setOpenGps(false);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setPoiNumber(5);
    localLocationClientOption.setPoiDistance(1000.0F);
    localLocationClientOption.setPoiExtraInfo(true);
    localLocationClientOption.setAddrType("all");
    localLocationClientOption.disableCache(false);
    localLocationClientOption.setProdName("locSDKDemo2");
    localLocationClientOption.setPriority(2);
    this.mLocationClient.setLocOption(localLocationClientOption);
  }

  private GeoPoint setMyLocationInMssap(Location paramLocation)
  {
    GeoPoint localGeoPoint = new GeoPoint((int)(1000000.0D * paramLocation.getLatitude()), (int)(1000000.0D * paramLocation.getLongitude()));
    clearOverlays();
    LocationData localLocationData = new LocationData();
    localLocationData.latitude = paramLocation.getLatitude();
    localLocationData.longitude = paramLocation.getLongitude();
    String str = paramLocation.getCity();
    localLocationData.direction = 2.0F;
    this.myLocationOverlay.setData(localLocationData);
    if (this.mMapView != null)
    {
      this.mMapView.getOverlays().add(this.myLocationOverlay);
      this.mMapView.refresh();
      this.mMapView.getController().setCenter(localGeoPoint);
      setCityByCityName(str);
    }
    return localGeoPoint;
  }

  private void showDate()
  {
    setTv(R.id.date_tv, c.formartMMdd(this.hotelReq.getCheckInDate()));
  }

  private void showFilter()
  {
    p localp1 = HotelFilterDialog.filterItems.a(this.hotelReq.getStar());
    if (localp1 != null)
    {
      setTv(R.id.filter_tv, localp1.getName());
      return;
    }
    p localp2 = HotelFilterDialog.filterItems.a(this.hotelReq.getPriceLevel());
    if (localp2 != null)
    {
      setTv(R.id.filter_tv, localp2.getName());
      return;
    }
    setTv(R.id.filter_tv, "不限");
  }

  private void sortHotels(List<HotelInfo> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      HotelInfo localHotelInfo = (HotelInfo)localIterator.next();
      localHotelInfo.setDistance(MapUtil.distance(this.hotelReq.getBlatitude(), this.hotelReq.getBlongitude(), localHotelInfo.getLatitude(), localHotelInfo.getLongitude()));
    }
    Collections.sort(paramList, new Comparator()
    {
      public int compare(HotelInfo paramHotelInfo1, HotelInfo paramHotelInfo2)
      {
        return Double.compare(paramHotelInfo1.getDistance(), paramHotelInfo2.getDistance());
      }
    });
  }

  public Bitmap drawTextToBitmap(Context paramContext, int paramInt, String paramString)
  {
    Bitmap localBitmap1 = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
    Bitmap.Config localConfig = localBitmap1.getConfig();
    if (localConfig == null)
      localConfig = Bitmap.Config.ARGB_8888;
    Bitmap localBitmap2 = localBitmap1.copy(localConfig, true);
    Canvas localCanvas = new Canvas(localBitmap2);
    Paint localPaint = new Paint(1);
    localPaint.setColor(-1);
    localPaint.setTextSize(9.0F * getResources().getDisplayMetrics().density);
    localPaint.setAntiAlias(true);
    String[] arrayOfString = paramString.split("\\|");
    String str1 = arrayOfString[0];
    String str2 = "";
    if (arrayOfString.length == 2)
      str2 = arrayOfString[1];
    Rect localRect1 = new Rect();
    localPaint.getTextBounds(str1, 0, str1.length(), localRect1);
    Rect localRect2 = new Rect();
    localPaint.getTextBounds(str2, 0, str2.length(), localRect2);
    int i = (localBitmap2.getWidth() - localRect1.width()) / 2;
    int j = 5 * (localBitmap2.getHeight() - localRect1.height()) / 12;
    int k = (localBitmap2.getWidth() - localRect2.width()) / 2;
    int m = 5 * (localBitmap2.getHeight() - localRect2.height()) / 12;
    localCanvas.drawText(str1, i, j, localPaint);
    localCanvas.drawText(str2, k, m + 1.2F * localRect1.height(), localPaint);
    return localBitmap2;
  }

  public void filterHotel()
  {
    5 local5 = new HotelFilterDialog.HotelFilter()
    {
      public void onFilter(FindHotelReq paramFindHotelReq)
      {
        HotelMapActivity.this.mHotels.clear();
        HotelMapActivity.this.refreshHotel();
        HotelMapActivity.this.showFilter();
      }
    };
    new HotelFilterDialog(this, R.style.MMTheme_DataSheet, this.hotelReq, local5).show();
  }

  protected int getContentView()
  {
    return R.layout.activity_hotel_map;
  }

  public GeoPoint getCurrGeo()
  {
    int i = this.mapParentView.getLeft() + this.mapParentView.getWidth() / 2;
    int j = this.mapParentView.getTop() + this.mapParentView.getHeight() / 2;
    return this.mMapView.getProjection().fromPixels(i, j);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      if (paramInt1 != R.id.hotel_title_rl)
        break label100;
      CityInfo localCityInfo = (CityInfo)paramIntent.getSerializableExtra("city");
      double d1 = localCityInfo.getLatitude();
      double d2 = localCityInfo.getLongitude();
      if ((d1 != 0.0D) && (d2 != 0.0D))
      {
        Location localLocation = new Location(localCityInfo.getName(), d1, d2);
        GeoPoint localGeoPoint = MapUtil.loc2geo(localLocation);
        setCityByCityName(localLocation.getCity());
        searchHotelsBylocation(localGeoPoint);
      }
    }
    label100: 
    do
      return;
    while (paramInt1 != R.id.dateButton);
    Date localDate = (Date)paramIntent.getSerializableExtra("date");
    this.hotelReq.setCheckInDate(localDate);
    showDate();
    refreshHotel();
  }

  public void onClick(View paramView)
  {
    super.onClick(paramView);
    int i = paramView.getId();
    if (i == R.id.locationButton)
      if (this.currLocation != null)
      {
        GeoPoint localGeoPoint = MapUtil.loc2geo(this.currLocation);
        setCityByCityName(this.currLocation.getCity());
        this.mMapView.getController().animateTo(localGeoPoint);
        searchHotelsBylocation(localGeoPoint);
      }
    do
    {
      return;
      this.directToLocation = true;
      this.mLocationClient.start();
      this.mLocationClient.requestLocation();
      return;
      if (i == R.id.listButton)
      {
        this.hotelReq.setRadius(Integer.valueOf(2 * this.hotelReq.getRadius().intValue()));
        goHotelListActivity(this.hotelReq, this.mHotels);
        return;
      }
      if (i == R.id.hotel_title_rl)
      {
        goCityListActivity(R.id.hotel_title_rl);
        return;
      }
      if (i == R.id.filterButton)
      {
        filterHotel();
        return;
      }
      if (i != R.id.dateButton)
        continue;
      goDatePickActivity(c.roundDate(new Date()), this.hotelReq.getCheckInDate(), 3, R.id.dateButton);
      return;
    }
    while (i != R.id.rightBt);
    Intent localIntent = new Intent(this, HotelSearchMoreActivity.class);
    localIntent.putExtra("hotelReq", this.hotelReq);
    startActivity(localIntent);
  }

  public void onCreate(Bundle paramBundle)
  {
    if (mBMapMan == null)
    {
      mBMapMan = new BMapManager(this);
      mBMapMan.init(getApp().getBaiduKey(), new MyGeneralListener());
    }
    this.mLocationClient = new LocationClient(this);
    setLocationOption();
    this.mLocationClient.registerLocationListener(this.myListener);
    this.mLocationClient.start();
    this.mLocationClient.requestLocation();
    this.mkSerach.init(mBMapMan, this.mySearchListener);
    super.onCreate(paramBundle);
    setClick(R.id.filterButton, this);
    setClick(R.id.dateButton, this);
    this.mMapView = ((MyMapView)findViewById(R.id.bmapsView));
    this.myLocationOverlay = new MyLocationOverlay(this.mMapView);
    this.mHotels = ((List)getIntent().getSerializableExtra("hotels"));
    if (this.mHotels == null)
      this.mHotels = new ArrayList();
    this.hotelReq = ((FindHotelReq)getIntent().getSerializableExtra("hotelReq"));
    this.mMapView.setBuiltInZoomControls(false);
    this.mMapView.getController().setZoom(zoom);
    initListener();
    this.mapParentView = findViewById(R.id.hotel_map_view_parent);
    setClick(R.id.locationButton, this);
    setClick(R.id.rightBt, this);
    setClick(R.id.listButton, this);
    setClick(R.id.hotel_title_rl, this);
    this.cityTitle = ((TextView)findViewById(R.id.hotel_city_tv));
    this.cityTitle.setText("-" + this.hotelReq.getCityName());
    this.mVibrator = ((Vibrator)getApplication().getSystemService("vibrator"));
    if (this.hotelReq != null)
    {
      this.hotelReq.setIndex(0);
      this.hotelReq.setPage(0);
      this.hotelReq.setDistrictName(null);
    }
    Location localLocation = getReqLocation(this.hotelReq);
    if (localLocation == null)
    {
      this.directToLocation = true;
      this.mLocationClient.start();
      this.mLocationClient.requestLocation();
    }
    label524: 
    while (true)
    {
      if (this.hotelReq == null)
      {
        this.hotelReq = new FindHotelReq();
        this.hotelReq.setIndex(0);
        this.hotelReq.setPage(0);
      }
      showFilter();
      showDate();
      return;
      GeoPoint localGeoPoint = MapUtil.loc2geo(localLocation);
      setCityByCityName(localLocation.getCity());
      if ((this.mHotels == null) || (this.mHotels.isEmpty()))
        searchHotelsBylocation(localGeoPoint);
      while (true)
      {
        if (!this.hotelReq.isNeedFindCity())
          break label524;
        this.mkSerach.reverseGeocode(MapUtil.loc2geo(localLocation));
        break;
        showHotelsInMap(this.mHotels, null);
      }
    }
  }

  protected void onDestroy()
  {
    try
    {
      if (this.mMapView != null)
        this.mMapView.destroy();
      try
      {
        label14: this.mLocationClient.stop();
        label21: super.onDestroy();
        return;
      }
      catch (Exception localException2)
      {
        break label21;
      }
    }
    catch (Exception localException1)
    {
      break label14;
    }
  }

  protected void onPause()
  {
    this.mMapView.onPause();
    super.onPause();
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    this.mMapView.onRestoreInstanceState(paramBundle);
  }

  protected void onResume()
  {
    this.mMapView.onResume();
    if (mapViewGeo != null)
      this.mMapView.getController().setCenter(mapViewGeo);
    super.onResume();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mMapView.onSaveInstanceState(paramBundle);
  }

  public void refreshHotel()
  {
    3 local3 = new k(this)
    {
      protected Response<List<HotelInfo>> myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return HotelService.instance().findHotelList((FindHotelReq)paramArrayOfObject[0]);
      }

      protected void myPostExecute(Response<List<HotelInfo>> paramResponse)
      {
        HotelMapActivity.access$802(HotelMapActivity.this, (List)paramResponse.getData());
        List localList = HotelMapActivity.this.mHotels;
        GeoPoint localGeoPoint = null;
        if (localList != null)
        {
          int i = HotelMapActivity.this.mHotels.size();
          localGeoPoint = null;
          if (i > 0)
            localGeoPoint = new GeoPoint((int)(1000000.0D * ((HotelInfo)HotelMapActivity.this.mHotels.get(0)).getLatitude()), (int)(1000000.0D * ((HotelInfo)HotelMapActivity.this.mHotels.get(0)).getLongitude()));
        }
        HotelMapActivity.this.showHotelsInMap(HotelMapActivity.this.mHotels, localGeoPoint);
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
        HotelMapActivity.this.clearOverlays();
      }
    };
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.hotelReq;
    local3.execute(arrayOfObject);
  }

  public void searchHotelsBylocation(GeoPoint paramGeoPoint)
  {
    mapViewGeo = paramGeoPoint;
    Location localLocation1 = MapUtil.geo2Loc(paramGeoPoint);
    Location localLocation2 = MapUtil.geo2Loc(MapUtil.baidu2Gcj(paramGeoPoint));
    Location localLocation3 = MapUtil.geo2Loc(MapUtil.baidu2MapBar(paramGeoPoint));
    if ((this.hotelReq.getKeywords() == null) || ((this.hotelReq.getKeywords() != null) && (this.hotelReq.getKeywords().equals(""))))
    {
      this.hotelReq.setGlatitude(localLocation2.getLatitude());
      this.hotelReq.setGlongitude(localLocation2.getLongitude());
      this.hotelReq.setBlatitude(localLocation1.getLatitude());
      this.hotelReq.setBlongitude(localLocation1.getLongitude());
      this.hotelReq.setTlatitude(localLocation3.getLatitude());
      this.hotelReq.setTlongitude(localLocation3.getLongitude());
    }
    refreshHotel();
  }

  public OverlayItem showHotelsInMap(HotelInfo paramHotelInfo, GeoPoint paramGeoPoint, String paramString)
  {
    OverlayItem localOverlayItem = new OverlayItem(paramGeoPoint, paramHotelInfo.getHotelName(), paramHotelInfo.getHotelName());
    int i = paramHotelInfo.getLiveableCount();
    String str = paramString + paramHotelInfo.getShortName() + "|¥" + df.format(paramHotelInfo.getLowestPrice());
    Bitmap localBitmap2;
    if (i <= 0)
      localBitmap2 = drawTextToBitmap(this, R.drawable.hotel_full, str);
    Bitmap localBitmap1;
    for (BitmapDrawable localBitmapDrawable = new BitmapDrawable(getResources(), localBitmap2); ; localBitmapDrawable = new BitmapDrawable(getResources(), localBitmap1))
    {
      localOverlayItem.setMarker(localBitmapDrawable);
      return localOverlayItem;
      localBitmap1 = drawTextToBitmap(this, R.drawable.hotel_idle, str);
    }
  }

  public void showHotelsInMap(List<HotelInfo> paramList, GeoPoint paramGeoPoint)
  {
    clearOverlays();
    int i = R.drawable.navigate_light;
    this.periHotels = paramList;
    Drawable localDrawable = getResources().getDrawable(i);
    this.mMapView.getOverlays().clear();
    this.itemOverlay = new OverlayTest(localDrawable, this.mMapView);
    this.mMapView.getOverlays().add(this.itemOverlay);
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < paramList.size(); j++)
    {
      HotelInfo localHotelInfo = (HotelInfo)paramList.get(j);
      localArrayList.add(showHotelsInMap(localHotelInfo, new GeoPoint((int)(1000000.0D * localHotelInfo.getLatitude()), (int)(1000000.0D * localHotelInfo.getLongitude())), ""));
    }
    this.itemOverlay.addItem(localArrayList);
    this.mMapView.refresh();
    if (paramGeoPoint != null)
      this.mMapView.getController().setCenter(paramGeoPoint);
  }

  class MyGeneralListener
    implements MKGeneralListener
  {
    MyGeneralListener()
    {
    }

    public void onGetNetworkState(int paramInt)
    {
      if (paramInt == 2)
        Toast.makeText(HotelMapActivity.this, "您的网络出错啦！", 1).show();
      do
        return;
      while (paramInt != 3);
      Toast.makeText(HotelMapActivity.this, "输入正确的检索条件！", 1).show();
    }

    public void onGetPermissionState(int paramInt)
    {
      if (paramInt == 300)
        Toast.makeText(HotelMapActivity.this, "百度Key错误！", 1).show();
    }
  }

  class MyLocationListener
    implements BDLocationListener
  {
    MyLocationListener()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      if (paramBDLocation == null);
      do
      {
        return;
        double d1 = paramBDLocation.getLongitude();
        double d2 = paramBDLocation.getLatitude();
        HotelMapActivity.access$502(HotelMapActivity.this, new Location(paramBDLocation.getCity(), d2, d1));
      }
      while (!HotelMapActivity.this.directToLocation);
      GeoPoint localGeoPoint = MapUtil.loc2geo(HotelMapActivity.this.currLocation);
      HotelMapActivity.this.setCityByCityName(HotelMapActivity.this.currLocation.getCity());
      HotelMapActivity.this.mMapView.getController().animateTo(localGeoPoint);
      HotelMapActivity.this.searchHotelsBylocation(localGeoPoint);
    }

    public void onReceivePoi(BDLocation paramBDLocation)
    {
      if (paramBDLocation == null);
    }
  }

  class MySearchListener extends MyMKSearchListener
  {
    MySearchListener()
    {
    }

    public void onGetAddrResult(MKAddrInfo paramMKAddrInfo, int paramInt)
    {
      String str = paramMKAddrInfo.addressComponents.city;
      HotelMapActivity.this.setCityByCityName(str);
    }

    public void onGetPoiResult(MKPoiResult paramMKPoiResult, int paramInt1, int paramInt2)
    {
      if ((paramInt2 != 0) || (paramMKPoiResult == null))
        Toast.makeText(HotelMapActivity.this, "抱歉，未找到结果", 1).show();
      label21: MKPoiInfo localMKPoiInfo;
      do
      {
        Iterator localIterator;
        do
        {
          return;
          break label21;
          continue;
          while (paramMKPoiResult.getCurrentNumPois() <= 0);
          MyPoiOverlay localMyPoiOverlay = new MyPoiOverlay(HotelMapActivity.this, HotelMapActivity.this.mMapView, HotelMapActivity.this.mkSerach);
          localMyPoiOverlay.setData(paramMKPoiResult.getAllPoi());
          HotelMapActivity.this.mMapView.getOverlays().clear();
          HotelMapActivity.this.mMapView.getOverlays().add(localMyPoiOverlay);
          HotelMapActivity.this.mMapView.refresh();
          localIterator = paramMKPoiResult.getAllPoi().iterator();
        }
        while (!localIterator.hasNext());
        localMKPoiInfo = (MKPoiInfo)localIterator.next();
      }
      while (localMKPoiInfo.pt == null);
      HotelMapActivity.this.mMapView.getController().animateTo(localMKPoiInfo.pt);
    }
  }

  class OverlayTest extends ItemizedOverlay<OverlayItem>
  {
    public OverlayTest(Drawable paramMapView, MapView arg3)
    {
      super(localMapView);
    }

    protected boolean onTap(int paramInt)
    {
      if (HotelMapActivity.this.periHotels != null)
      {
        HotelInfo localHotelInfo = (HotelInfo)HotelMapActivity.this.periHotels.get(paramInt);
        HotelMapActivity.this.goHotelDetailActivity(HotelMapActivity.this.hotelReq, localHotelInfo);
      }
      return true;
    }

    public boolean onTap(GeoPoint paramGeoPoint, MapView paramMapView)
    {
      super.onTap(paramGeoPoint, paramMapView);
      return false;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.activity.HotelMapActivity
 * JD-Core Version:    0.6.0
 */