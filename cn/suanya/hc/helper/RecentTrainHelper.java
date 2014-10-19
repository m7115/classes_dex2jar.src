package cn.suanya.hc.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.example.pathview.bean.RecentTrain;
import java.util.ArrayList;
import java.util.List;

public class RecentTrainHelper extends SQLiteOpenHelper
{
  public static final String DATABASE_NAME = "RecentStation.db";
  public static final String TABLE_NAME_RECENT = "recentStation";
  private static final String TALBE_RECENT_CREATE = "create table if not exists recentStation(code text primary key not null,station_start text not null,time_start text,station_end text not null,time_end text,time integer not null,type integer not null)";
  public static final int VERSION = 1;

  public RecentTrainHelper(Context paramContext)
  {
    super(paramContext, "RecentStation.db", null, 1);
  }

  public void addRecentTrain(SQLiteDatabase paramSQLiteDatabase, RecentTrain paramRecentTrain)
  {
    deleteRecentTrain(paramSQLiteDatabase, paramRecentTrain.getCode());
    SQLiteStatement localSQLiteStatement = paramSQLiteDatabase.compileStatement("insert into recentStation (code,station_start,time_start,station_end,time_end,time,type) values(?,?,?,?,?,?,?)");
    paramSQLiteDatabase.beginTransaction();
    localSQLiteStatement.bindString(1, paramRecentTrain.getCode());
    localSQLiteStatement.bindString(2, paramRecentTrain.getStationStart());
    localSQLiteStatement.bindString(3, paramRecentTrain.getTimeStart());
    localSQLiteStatement.bindString(4, paramRecentTrain.getStationEnd());
    localSQLiteStatement.bindString(5, paramRecentTrain.getTimeEnd());
    localSQLiteStatement.bindLong(6, paramRecentTrain.getTime());
    localSQLiteStatement.bindLong(7, paramRecentTrain.getType());
    localSQLiteStatement.executeInsert();
    paramSQLiteDatabase.setTransactionSuccessful();
    paramSQLiteDatabase.endTransaction();
  }

  public void deleteRecentTrain(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    paramSQLiteDatabase.execSQL("delete from recentStation where code=?", new String[] { paramString });
  }

  public List<RecentTrain> getAllRecentTrain(SQLiteDatabase paramSQLiteDatabase)
  {
    Cursor localCursor = paramSQLiteDatabase.rawQuery("select * from recentStation order by time desc", null);
    if ((localCursor == null) || (localCursor.getCount() <= 0))
    {
      localCursor.close();
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    while (localCursor.moveToNext())
    {
      RecentTrain localRecentTrain = new RecentTrain(localCursor.getString(localCursor.getColumnIndex("code")), localCursor.getString(localCursor.getColumnIndex("station_start")), localCursor.getString(localCursor.getColumnIndex("time_start")), localCursor.getString(localCursor.getColumnIndex("station_end")), localCursor.getString(localCursor.getColumnIndex("time_end")), localCursor.getInt(localCursor.getColumnIndex("time")), localCursor.getInt(localCursor.getColumnIndex("type")));
      if (localRecentTrain == null)
        continue;
      localArrayList.add(localRecentTrain);
    }
    localCursor.close();
    return localArrayList;
  }

  public boolean isRecentStation(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    Cursor localCursor = paramSQLiteDatabase.rawQuery("select * from recentStation where code=?", new String[] { paramString });
    if ((localCursor == null) || (localCursor.getCount() <= 0))
    {
      localCursor.close();
      return false;
    }
    localCursor.close();
    return true;
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table if not exists recentStation(code text primary key not null,station_start text not null,time_start text,station_end text not null,time_end text,time integer not null,type integer not null)");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("drop table if exists recentStation");
    paramSQLiteDatabase.execSQL("create table if not exists recentStation(code text primary key not null,station_start text not null,time_start text,station_end text not null,time_end text,time integer not null,type integer not null)");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hc.helper.RecentTrainHelper
 * JD-Core Version:    0.6.0
 */