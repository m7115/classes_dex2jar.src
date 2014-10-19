package cn.suanya.hc.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import cn.suanya.hc.helper.AlarmStationHelper;
import cn.suanya.hc.helper.RecentTrainHelper;
import cn.suanya.hc.helper.StationHelper;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.pathview.bean.AlarmStation;
import com.example.pathview.bean.Point;
import com.example.pathview.bean.RecentTrain;
import com.example.pathview.bean.TrainInfo;
import com.example.pathview.bean.TrainPosition;
import com.example.pathview.util.MathUtil;
import com.example.pathview.util.TimeUtil;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.Note;
import com.yipiao.bean.StationNode;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.service.YipiaoService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class PathService
{
  private static final int BAIDU_GPS_DELAY = 10000;
  private static PathService mInstance;
  protected SYApplication app = SYApplication.app;
  private SQLiteDatabase mAlarmStationDb;
  private AlarmStationHelper mAlarmStationHelper;
  private LocationClient mLocationClient = null;
  private Point mPoint;
  private SQLiteDatabase mRecentTrainDb;
  private RecentTrainHelper mRecentTrainHelper;
  private StationHelper mStationHelper = StationHelper.getInstance();
  private TrainInfo mTrainInfo;
  private BDLocationListener myListener = null;
  protected YipiaoService service = YipiaoService.getInstance();

  private void GpsNotifyObserver(Point paramPoint, TrainInfo paramTrainInfo)
  {
    YipiaoApplication.getApp().pathObservable.setChanged();
    YipiaoApplication.getApp().pathObservable.notifyObservers(getTrainPosition(paramPoint, paramTrainInfo));
  }

  public static PathService getInstance()
  {
    if (mInstance == null)
      mInstance = new PathService();
    return mInstance;
  }

  private void initAlarmStationHelper()
  {
    if (this.mAlarmStationDb == null)
    {
      this.mAlarmStationHelper = new AlarmStationHelper(YipiaoApplication.app);
      this.mAlarmStationDb = this.mAlarmStationHelper.getWritableDatabase();
    }
  }

  private void initRecentTrainHelper()
  {
    if (this.mRecentTrainDb == null)
    {
      this.mRecentTrainHelper = new RecentTrainHelper(YipiaoApplication.getApp());
      this.mRecentTrainDb = this.mRecentTrainHelper.getWritableDatabase();
    }
  }

  private double scale2nextByTime(TrainStationInfo paramTrainStationInfo1, TrainStationInfo paramTrainStationInfo2)
  {
    int i = TimeUtil.getCurrentMins();
    int j = TimeUtil.getMinsByStr(paramTrainStationInfo1.getRealLeaveTime());
    int k = TimeUtil.getMinsByStr(paramTrainStationInfo2.getRealArrTime());
    int m = TimeUtil.getMinsByStr(paramTrainStationInfo2.getRealLeaveTime());
    if (TimeUtil.getTimeDef(j, k) > 720);
    do
    {
      return -1.0D;
      if (TimeUtil.isBeweenTime(i, k, m))
        return 0.0D;
    }
    while (!TimeUtil.isBeweenTime(i, j, k));
    int n = TimeUtil.getTimeDef(j, k);
    return TimeUtil.getTimeDef(i, k) / n;
  }

  private TrainPosition userInTrainLine(Point paramPoint, List<TrainStationInfo> paramList)
  {
    if ((paramPoint == null) || (paramList == null))
      return null;
    double d1 = -1.0D;
    int i = 1;
    double d2 = 1.7976931348623157E+308D;
    int j = paramList.size();
    int k = 1;
    int m;
    double d5;
    double d4;
    if (k < j)
    {
      TrainStationInfo localTrainStationInfo1 = (TrainStationInfo)paramList.get(k - 1);
      TrainStationInfo localTrainStationInfo2 = (TrainStationInfo)paramList.get(k);
      if ((localTrainStationInfo1 != null) && (localTrainStationInfo2 != null))
      {
        Point localPoint1 = new Point(localTrainStationInfo1.getStation().getLng(), localTrainStationInfo1.getStation().getLat());
        Point localPoint2 = new Point(localTrainStationInfo2.getStation().getLng(), localTrainStationInfo2.getStation().getLat());
        double d3 = MathUtil.pointToLine(paramPoint, localPoint1, localPoint2);
        if (d3 >= d2)
          break label285;
        double d6 = MathUtil.distance(paramPoint, localPoint2) / MathUtil.distance(localPoint1, localPoint2);
        if (d6 > 1.0D)
          d6 = 1.0D;
        m = k;
        d5 = d6;
        d4 = d3;
      }
    }
    while (true)
    {
      k++;
      i = m;
      d1 = d5;
      d2 = d4;
      break;
      return null;
      Log.i("最近下一站点：", i + "," + d1);
      Log.i("用户位置映射到2个站点的距离是：", String.valueOf(d2) + "米");
      if (d2 > 100000.0D)
        return null;
      return new TrainPosition(i, (float)d1);
      label285: d4 = d2;
      m = i;
      d5 = d1;
    }
  }

  public void addAlarmStation(AlarmStation paramAlarmStation)
  {
    initAlarmStationHelper();
    this.mAlarmStationHelper.addAlarm(this.mAlarmStationDb, paramAlarmStation);
  }

  public void addRecentStation(String paramString)
  {
    this.mStationHelper.addRecentStation(paramString);
  }

  public void addRecentTrain(RecentTrain paramRecentTrain)
  {
    initRecentTrainHelper();
    this.mRecentTrainHelper.addRecentTrain(this.mRecentTrainDb, paramRecentTrain);
  }

  public void deleteAlarmStation(String paramString, int paramInt)
  {
    initAlarmStationHelper();
    this.mAlarmStationHelper.deleteAlarm(this.mAlarmStationDb, paramString, paramInt);
  }

  public void deleteRecentTrain(String paramString)
  {
    initRecentTrainHelper();
    this.mRecentTrainHelper.deleteRecentTrain(this.mRecentTrainDb, paramString);
  }

  public AlarmStation getAlarmStation(String paramString, int paramInt)
  {
    initAlarmStationHelper();
    return this.mAlarmStationHelper.getAlarmStation(this.mAlarmStationDb, paramString, paramInt);
  }

  public List<RecentTrain> getAllRecentStation()
  {
    initRecentTrainHelper();
    return this.mRecentTrainHelper.getAllRecentTrain(this.mRecentTrainDb);
  }

  public List<Note> getHotStations(int paramInt)
  {
    return this.mStationHelper.getStations(1, paramInt);
  }

  public List<Note> getRecentStations(int paramInt)
  {
    return this.mStationHelper.getStations(0, paramInt);
  }

  public StationNode getStationInfoByCode(String paramString)
  {
    return this.mStationHelper.getByCode(paramString);
  }

  public StationNode getStationInfoByName(String paramString)
  {
    return this.mStationHelper.getByName(paramString);
  }

  public List<Note> getStationsByKey(String paramString)
  {
    return this.mStationHelper.getStationsByKey(paramString);
  }

  public List<RecentTrain> getTrainByfromTo(String paramString1, String paramString2)
    throws Exception
  {
    return YipiaoService.getInstance().getTrainByfromTo(paramString1, paramString2);
  }

  public Cursor getTrainCursorByCode(String paramString)
  {
    return null;
  }

  public TrainInfo getTrainInfoByCode(String paramString)
    throws Exception
  {
    return YipiaoService.getInstance().getTrainInfoByCode(paramString);
  }

  public List<TrainPosition> getTrainPosition(Point paramPoint, TrainInfo paramTrainInfo)
  {
    if ((paramPoint == null) || (paramTrainInfo == null));
    TrainPosition localTrainPosition;
    do
    {
      return null;
      localTrainPosition = userInTrainLine(paramPoint, paramTrainInfo.getTrainStations());
    }
    while (localTrainPosition == null);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(localTrainPosition);
    return localArrayList;
  }

  public List<TrainPosition> getTrainPosition(TrainInfo paramTrainInfo)
  {
    if (paramTrainInfo == null)
      return null;
    this.mTrainInfo = paramTrainInfo;
    ArrayList localArrayList = new ArrayList();
    List localList = paramTrainInfo.getTrainStations();
    int i = localList.size();
    for (int j = 1; j < i; j++)
    {
      double d = scale2nextByTime((TrainStationInfo)localList.get(j - 1), (TrainStationInfo)localList.get(j));
      if (d == -1.0D)
        continue;
      localArrayList.add(new TrainPosition(j, (float)d));
    }
    return localArrayList;
  }

  public void init()
  {
    this.mStationHelper.openDatabase();
  }

  public boolean isAlarmStation(String paramString, int paramInt)
  {
    initAlarmStationHelper();
    return this.mAlarmStationHelper.isAlarm(this.mAlarmStationDb, paramString, paramInt);
  }

  public boolean startLocation(LocationClient paramLocationClient, TrainInfo paramTrainInfo)
  {
    this.mTrainInfo = paramTrainInfo;
    this.mLocationClient = paramLocationClient;
    this.myListener = new MyLocationListener();
    this.mLocationClient.registerLocationListener(this.myListener);
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setOpenGps(false);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setScanSpan(10000);
    localLocationClientOption.disableCache(true);
    localLocationClientOption.setPoiNumber(0);
    localLocationClientOption.setPoiDistance(0.0F);
    localLocationClientOption.setPoiExtraInfo(false);
    this.mLocationClient.setLocOption(localLocationClientOption);
    this.mLocationClient.start();
    if (this.mLocationClient != null)
    {
      int i = this.mLocationClient.requestLocation();
      Log.i("baidu gps-->", "requestLocation()=" + i);
      return true;
    }
    Log.i("LocSDK3", "locClient is null or not started");
    return false;
  }

  public void stopLocation()
  {
    if (this.mLocationClient != null)
    {
      this.mLocationClient.stop();
      this.mLocationClient = null;
      this.myListener = null;
    }
  }

  public void updateAlarmStation(AlarmStation paramAlarmStation)
  {
    initAlarmStationHelper();
    this.mAlarmStationHelper.updateAlarm(this.mAlarmStationDb, paramAlarmStation);
  }

  public void updateStationDb()
  {
    int i = this.app.launchInfo.optInt("stationDbLastVer", Constants.station_db_version);
    int j = this.app.sp.getInt("station_db_ver", Constants.station_db_version);
    if (j < Constants.station_db_version)
      j = Constants.station_db_version;
    if (i <= j)
      return;
    try
    {
      List localList = this.service.getStationDBUpdate(this.app.getVersionName(), j);
      if ((localList != null) && (localList.size() > 0))
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          StationNode localStationNode = (StationNode)localIterator.next();
          this.mStationHelper.updateStation(localStationNode);
        }
      }
      this.app.sp.edit().putInt("station_db_ver", i).commit();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public class MyLocationListener
    implements BDLocationListener
  {
    public MyLocationListener()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      if (paramBDLocation == null)
        return;
      StringBuffer localStringBuffer = new StringBuffer(256);
      localStringBuffer.append("time : ");
      localStringBuffer.append(paramBDLocation.getTime());
      localStringBuffer.append("\nerror code : ");
      localStringBuffer.append(paramBDLocation.getLocType());
      localStringBuffer.append("\nlatitude : ");
      localStringBuffer.append(paramBDLocation.getLatitude());
      localStringBuffer.append("\nlontitude : ");
      localStringBuffer.append(paramBDLocation.getLongitude());
      localStringBuffer.append("\nradius : ");
      localStringBuffer.append(paramBDLocation.getRadius());
      if (paramBDLocation.getLocType() == 61)
      {
        localStringBuffer.append("\nspeed : ");
        localStringBuffer.append(paramBDLocation.getSpeed());
        localStringBuffer.append("\nsatellite : ");
        localStringBuffer.append(paramBDLocation.getSatelliteNumber());
        Log.i("baidu gps-->", localStringBuffer.toString());
        if (paramBDLocation.getLocType() != 166)
          break label235;
        YipiaoService.getInstance().asyncDebug("baidu_gps", "166");
        PathService.access$002(PathService.this, new Point(0.0D, 0.0D));
      }
      while (true)
      {
        PathService.this.GpsNotifyObserver(PathService.this.mPoint, PathService.this.mTrainInfo);
        return;
        if (paramBDLocation.getLocType() != 161)
          break;
        localStringBuffer.append("\naddr : ");
        localStringBuffer.append(paramBDLocation.getAddrStr());
        break;
        label235: if ((paramBDLocation.getLatitude() == 4.9E-324D) || (paramBDLocation.getLongitude() == 4.9E-324D))
        {
          YipiaoService.getInstance().asyncDebug("baidu_gps", paramBDLocation.getLatitude() + "/" + paramBDLocation.getLongitude());
          PathService.access$002(PathService.this, new Point(0.0D, 0.0D));
          continue;
        }
        PathService.access$002(PathService.this, new Point(paramBDLocation.getLongitude(), paramBDLocation.getLatitude()));
      }
    }

    public void onReceivePoi(BDLocation paramBDLocation)
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hc.service.PathService
 * JD-Core Version:    0.6.0
 */