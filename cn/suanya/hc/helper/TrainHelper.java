package cn.suanya.hc.helper;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.suanya.common.a.n;
import cn.suanya.common.ui.SYApplication;
import com.example.pathview.bean.RecentTrain;
import com.example.pathview.bean.TrainInfo;
import com.example.pathview.util.FileUtil;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.TrainStationInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrainHelper
{
  public static final String DB_FILE_NAME = "trainoffline.db";
  private static int mResId = 0;
  private static TrainHelper trainHelper;
  private SQLiteDatabase db;
  private String mTableName = "train";

  private TrainHelper()
  {
    try
    {
      this.db = openDatabase();
      return;
    }
    catch (Exception localException)
    {
      n.a(localException);
    }
  }

  private boolean existDatabaseFile()
  {
    return new File(YipiaoApplication.app.getFilesDir().toString() + "/" + "trainoffline.db").exists();
  }

  public static TrainHelper getInstance()
  {
    monitorenter;
    try
    {
      if (trainHelper == null)
        trainHelper = new TrainHelper();
      TrainHelper localTrainHelper = trainHelper;
      return localTrainHelper;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private SQLiteDatabase openDatabase()
    throws Exception
  {
    if (this.db != null)
      return this.db;
    if (!existDatabaseFile())
      FileUtil.unZipTo(YipiaoApplication.app.getResources().openRawResource(mResId), YipiaoApplication.app.getFilesDir().getAbsolutePath());
    return SQLiteDatabase.openOrCreateDatabase(YipiaoApplication.app.getFilesDir().getAbsolutePath() + "/" + "trainoffline.db", null);
  }

  public Cursor getCursorByCode(String paramString)
  {
    SQLiteDatabase localSQLiteDatabase = this.db;
    String str = "select train_id as _id,sub_train_number from " + this.mTableName + " where sub_train_number like ? group by sub_train_number limit 10";
    String[] arrayOfString = new String[1];
    arrayOfString[0] = (paramString + "%");
    return localSQLiteDatabase.rawQuery(str, arrayOfString);
  }

  public TrainInfo getTrainByCode(String paramString)
  {
    String str1 = "select train_number from " + this.mTableName + " where sub_train_number=?";
    String str2 = "select * from " + this.mTableName + " where train_number=?";
    Cursor localCursor1 = this.db.rawQuery(str1, new String[] { paramString });
    if (!localCursor1.moveToFirst())
      return null;
    String str3 = localCursor1.getString(localCursor1.getColumnIndex("train_number"));
    Cursor localCursor2 = this.db.rawQuery(str2, new String[] { str3 });
    ArrayList localArrayList = new ArrayList();
    while (localCursor2.moveToNext())
    {
      TrainStationInfo localTrainStationInfo = new TrainStationInfo();
      localTrainStationInfo.setName(localCursor2.getString(localCursor2.getColumnIndex("to_station_name")));
      localTrainStationInfo.setLeaveTime(localCursor2.getString(localCursor2.getColumnIndex("from_time")));
      localTrainStationInfo.setArrTime(localCursor2.getString(localCursor2.getColumnIndex("to_time")));
      localArrayList.add(localTrainStationInfo);
    }
    TrainInfo localTrainInfo = null;
    if (localArrayList != null)
    {
      int i = localArrayList.size();
      localTrainInfo = null;
      if (i > 0)
        localTrainInfo = new TrainInfo(paramString, localArrayList);
    }
    localCursor1.close();
    localCursor2.close();
    return localTrainInfo;
  }

  public List<RecentTrain> getTrainByfromTo(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equals("")) || (paramString2 == null) || (paramString2.equals("")));
    Cursor localCursor;
    do
    {
      return null;
      SQLiteDatabase localSQLiteDatabase = this.db;
      String[] arrayOfString = new String[2];
      arrayOfString[0] = ("%" + paramString1 + "%");
      arrayOfString[1] = ("%" + paramString2 + "%");
      localCursor = localSQLiteDatabase.rawQuery("select t1.[sub_train_number],t1.[to_station_name],t1.[from_time],t2.[to_station_name],t2.[to_time] from train t1,train t2 where t1.to_station_name like ?  and t2.to_station_name like ? and t1.[train_number]=t2.[train_number] and t1.[station_num] < t2.[station_num]", arrayOfString);
    }
    while ((localCursor == null) || (localCursor.getCount() <= 0));
    ArrayList localArrayList = new ArrayList();
    while (localCursor.moveToNext())
    {
      RecentTrain localRecentTrain = new RecentTrain(localCursor.getString(0), localCursor.getString(1), localCursor.getString(2), localCursor.getString(3), localCursor.getString(4), System.currentTimeMillis(), 0);
      if (localRecentTrain == null)
        continue;
      localArrayList.add(localRecentTrain);
    }
    return localArrayList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hc.helper.TrainHelper
 * JD-Core Version:    0.6.0
 */