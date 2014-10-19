package cn.suanya.hc.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.example.pathview.bean.AlarmStation;

public class AlarmStationHelper extends SQLiteOpenHelper
{
  public static final String DB_NAME = "alarm.db";
  public static final String TABLE_ALARM_CREATE = "create table if not exists AlarmTable (_id integer primary key autoincrement,code text not null,number integer not null,name text not null,arrivetime text not null,alarmtime text not null,voice integer not null,vibrate integer not null)";
  public static final String TABLE_NAME_ALARM = "AlarmTable";
  public static final int VERSION = 1;

  public AlarmStationHelper(Context paramContext)
  {
    super(paramContext, "alarm.db", null, 1);
  }

  public AlarmStationHelper(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }

  public void addAlarm(SQLiteDatabase paramSQLiteDatabase, AlarmStation paramAlarmStation)
  {
    long l1 = 1L;
    SQLiteStatement localSQLiteStatement = paramSQLiteDatabase.compileStatement("insert into AlarmTable (code,number,name,arrivetime,alarmtime,voice,vibrate) values(?,?,?,?,?,?,?)");
    paramSQLiteDatabase.beginTransaction();
    localSQLiteStatement.bindString(1, paramAlarmStation.getCode());
    localSQLiteStatement.bindLong(2, paramAlarmStation.getIndex());
    localSQLiteStatement.bindString(3, paramAlarmStation.getName());
    localSQLiteStatement.bindString(4, paramAlarmStation.getArriveTime());
    localSQLiteStatement.bindString(5, paramAlarmStation.getAlarmTime());
    long l2;
    if (paramAlarmStation.isVoice())
    {
      l2 = l1;
      localSQLiteStatement.bindLong(6, l2);
      if (!paramAlarmStation.isVibrate())
        break label120;
    }
    while (true)
    {
      localSQLiteStatement.bindLong(7, l1);
      localSQLiteStatement.executeInsert();
      paramSQLiteDatabase.setTransactionSuccessful();
      paramSQLiteDatabase.endTransaction();
      return;
      l2 = 0L;
      break;
      label120: l1 = 0L;
    }
  }

  public void deleteAlarm(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt)
  {
    paramSQLiteDatabase.execSQL("delete from AlarmTable where code='" + paramString + "' and number=" + paramInt);
  }

  public AlarmStation getAlarmStation(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt)
  {
    int i = 1;
    Cursor localCursor = paramSQLiteDatabase.rawQuery("select * from AlarmTable where code='" + paramString + "' and number=" + paramInt, null);
    if ((localCursor == null) || (localCursor.getCount() <= 0))
      return null;
    localCursor.moveToFirst();
    String str1 = localCursor.getString(localCursor.getColumnIndex("name"));
    String str2 = localCursor.getString(localCursor.getColumnIndex("arrivetime"));
    String str3 = localCursor.getString(localCursor.getColumnIndex("alarmtime"));
    int j = localCursor.getInt(localCursor.getColumnIndex("voice"));
    int k = localCursor.getInt(localCursor.getColumnIndex("vibrate"));
    int m;
    if (j == i)
    {
      m = i;
      if (k != i)
        break label197;
    }
    while (true)
    {
      return new AlarmStation(paramString, paramInt, str1, str2, str3, m, i);
      int n = 0;
      break;
      label197: i = 0;
    }
  }

  public boolean isAlarm(SQLiteDatabase paramSQLiteDatabase, String paramString, int paramInt)
  {
    Cursor localCursor = paramSQLiteDatabase.rawQuery("select * from AlarmTable where code='" + paramString + "' and number=" + paramInt, null);
    return (localCursor != null) && (localCursor.getCount() > 0);
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table if not exists AlarmTable (_id integer primary key autoincrement,code text not null,number integer not null,name text not null,arrivetime text not null,alarmtime text not null,voice integer not null,vibrate integer not null)");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("drop table if exists AlarmTable");
    onCreate(paramSQLiteDatabase);
  }

  public void updateAlarm(SQLiteDatabase paramSQLiteDatabase, AlarmStation paramAlarmStation)
  {
    long l1 = 1L;
    SQLiteStatement localSQLiteStatement = paramSQLiteDatabase.compileStatement("update AlarmTable set arrivetime=?,alarmtime=?,voice=?,vibrate=? where code=? and number=?");
    paramSQLiteDatabase.beginTransaction();
    localSQLiteStatement.bindString(1, paramAlarmStation.getArriveTime());
    localSQLiteStatement.bindString(2, paramAlarmStation.getAlarmTime());
    long l2;
    if (paramAlarmStation.isVoice())
    {
      l2 = l1;
      localSQLiteStatement.bindLong(3, l2);
      if (!paramAlarmStation.isVibrate())
        break label109;
    }
    while (true)
    {
      localSQLiteStatement.bindLong(4, l1);
      localSQLiteStatement.bindString(5, paramAlarmStation.getCode());
      localSQLiteStatement.bindLong(6, paramAlarmStation.getIndex());
      localSQLiteStatement.executeInsert();
      paramSQLiteDatabase.setTransactionSuccessful();
      paramSQLiteDatabase.endTransaction();
      return;
      l2 = 0L;
      break;
      label109: l1 = 0L;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hc.helper.AlarmStationHelper
 * JD-Core Version:    0.6.0
 */