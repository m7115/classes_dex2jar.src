package cn.suanya.hc.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.suanya.common.ui.SYApplication;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.Note;
import com.yipiao.bean.StationNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StationHelper
{
  public static final String DB_FILE_NAME = "stationoffline2.db";
  private static int mResId = 2131099649;
  private static StationHelper stationHelper;
  private SQLiteDatabase db;
  private String mTableName = "station";

  private ContentValues StationNode2ContentValues(StationNode paramStationNode)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("firstLetter", paramStationNode.getFilter());
    localContentValues.put("name", paramStationNode.getName());
    localContentValues.put("code", paramStationNode.getCode());
    localContentValues.put("pinyin", paramStationNode.getPinYin());
    localContentValues.put("latitude", Double.valueOf(paramStationNode.getLat()));
    localContentValues.put("longitude", Double.valueOf(paramStationNode.getLng()));
    localContentValues.put("city", paramStationNode.getCityName());
    localContentValues.put("hotcity", Integer.valueOf(paramStationNode.getHotCity()));
    localContentValues.put("querytime", Integer.valueOf(0));
    return localContentValues;
  }

  private boolean existDatabaseFile()
  {
    return new File(YipiaoApplication.app.getFilesDir().getAbsolutePath() + "/" + "stationoffline2.db").exists();
  }

  public static StationHelper getInstance()
  {
    if (stationHelper == null)
      stationHelper = new StationHelper();
    return stationHelper;
  }

  private StationNode stationByCursor(Cursor paramCursor)
  {
    return new StationNode(paramCursor.getString(paramCursor.getColumnIndex("code")), paramCursor.getString(paramCursor.getColumnIndex("name")), paramCursor.getString(paramCursor.getColumnIndex("shorthand")), paramCursor.getString(paramCursor.getColumnIndex("pinyin")), paramCursor.getDouble(paramCursor.getColumnIndex("latitude")), paramCursor.getDouble(paramCursor.getColumnIndex("longitude")), paramCursor.getString(paramCursor.getColumnIndex("city")), paramCursor.getInt(paramCursor.getColumnIndex("hotcity")));
  }

  public void addRecentStation(String paramString)
  {
    String str = "update " + this.mTableName + " set querytime=" + System.currentTimeMillis() + " where code=?";
    openDatabase().execSQL(str, new String[] { paramString });
  }

  public int deleteStationByCode(String paramString)
  {
    return openDatabase().delete(this.mTableName, "code=?", new String[] { paramString });
  }

  public StationNode getByCode(String paramString)
  {
    String str = "select * from " + this.mTableName + " where code=?";
    Cursor localCursor = openDatabase().rawQuery(str, new String[] { paramString });
    boolean bool = localCursor.moveToFirst();
    StationNode localStationNode = null;
    if (bool)
      localStationNode = stationByCursor(localCursor);
    localCursor.close();
    return localStationNode;
  }

  public StationNode getByName(String paramString)
  {
    String str = "select * from " + this.mTableName + " where name=?";
    Cursor localCursor = openDatabase().rawQuery(str, new String[] { paramString });
    boolean bool = localCursor.moveToFirst();
    StationNode localStationNode = null;
    if (bool)
      localStationNode = stationByCursor(localCursor);
    localCursor.close();
    return localStationNode;
  }

  public List<Note> getStations(int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer("select * from  " + this.mTableName + " ");
    switch (paramInt1)
    {
    default:
    case 0:
    case 1:
    }
    Cursor localCursor;
    while (true)
    {
      SQLiteDatabase localSQLiteDatabase = openDatabase();
      String str = localStringBuffer.toString();
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramInt2);
      localCursor = localSQLiteDatabase.rawQuery(str, arrayOfString);
      if ((localCursor != null) && (localCursor.getCount() > 0))
        break;
      return null;
      localStringBuffer.append("where querytime<>0 order by querytime desc limit ?");
      continue;
      localStringBuffer.append("where hotcity<>0 order by hotcity asc limit ?");
    }
    ArrayList localArrayList = new ArrayList();
    while (localCursor.moveToNext())
      localArrayList.add(stationByCursor(localCursor));
    localCursor.close();
    return localArrayList;
  }

  public List<Note> getStationsByKey(String paramString)
  {
    String str1 = paramString.trim();
    String str2 = "select * from " + this.mTableName + " where firstLetter like ? " + "or name like ? " + "or pinyin like ? " + "or shorthand like ? " + "order by querytime desc,hotcity desc";
    try
    {
      SQLiteDatabase localSQLiteDatabase = openDatabase();
      String[] arrayOfString = new String[4];
      arrayOfString[0] = (str1 + "%");
      arrayOfString[1] = (str1 + "%");
      arrayOfString[2] = (str1 + "%");
      arrayOfString[3] = (str1 + "%");
      Cursor localCursor = localSQLiteDatabase.rawQuery(str2, arrayOfString);
      if ((localCursor == null) || (localCursor.getCount() <= 0))
        return null;
      ArrayList localArrayList = new ArrayList();
      while (localCursor.moveToNext())
        localArrayList.add(stationByCursor(localCursor));
      localCursor.close();
      return localArrayList;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public long insertStation(StationNode paramStationNode)
  {
    deleteStationByCode(paramStationNode.getCode());
    return openDatabase().insert(this.mTableName, "", StationNode2ContentValues(paramStationNode));
  }

  // ERROR //
  public SQLiteDatabase openDatabase()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 288	cn/suanya/hc/helper/StationHelper:db	Landroid/database/sqlite/SQLiteDatabase;
    //   6: ifnull +14 -> 20
    //   9: aload_0
    //   10: getfield 288	cn/suanya/hc/helper/StationHelper:db	Landroid/database/sqlite/SQLiteDatabase;
    //   13: astore 6
    //   15: aload_0
    //   16: monitorexit
    //   17: aload 6
    //   19: areturn
    //   20: aload_0
    //   21: invokespecial 290	cn/suanya/hc/helper/StationHelper:existDatabaseFile	()Z
    //   24: ifne +37 -> 61
    //   27: getstatic 113	com/yipiao/YipiaoApplication:app	Lcn/suanya/common/ui/SYApplication;
    //   30: invokevirtual 294	cn/suanya/common/ui/SYApplication:getResources	()Landroid/content/res/Resources;
    //   33: getstatic 20	cn/suanya/hc/helper/StationHelper:mResId	I
    //   36: invokevirtual 300	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   39: astore_3
    //   40: getstatic 113	com/yipiao/YipiaoApplication:app	Lcn/suanya/common/ui/SYApplication;
    //   43: invokevirtual 119	cn/suanya/common/ui/SYApplication:getFilesDir	()Ljava/io/File;
    //   46: invokevirtual 122	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   49: astore 4
    //   51: aload_3
    //   52: aload 4
    //   54: invokestatic 306	com/example/pathview/util/FileUtil:unZipTo	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   57: aload_3
    //   58: invokevirtual 309	java/io/InputStream:close	()V
    //   61: aload_0
    //   62: new 106	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   69: getstatic 113	com/yipiao/YipiaoApplication:app	Lcn/suanya/common/ui/SYApplication;
    //   72: invokevirtual 119	cn/suanya/common/ui/SYApplication:getFilesDir	()Ljava/io/File;
    //   75: invokevirtual 122	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   78: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: ldc 128
    //   83: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: ldc 8
    //   88: invokevirtual 126	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: aconst_null
    //   95: bipush 16
    //   97: invokestatic 312	android/database/sqlite/SQLiteDatabase:openDatabase	(Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)Landroid/database/sqlite/SQLiteDatabase;
    //   100: putfield 288	cn/suanya/hc/helper/StationHelper:db	Landroid/database/sqlite/SQLiteDatabase;
    //   103: aload_0
    //   104: getfield 288	cn/suanya/hc/helper/StationHelper:db	Landroid/database/sqlite/SQLiteDatabase;
    //   107: astore_2
    //   108: aload_0
    //   109: monitorexit
    //   110: aload_2
    //   111: areturn
    //   112: astore_1
    //   113: aload_0
    //   114: monitorexit
    //   115: aload_1
    //   116: athrow
    //   117: astore 5
    //   119: aload 5
    //   121: invokestatic 318	cn/suanya/common/a/n:a	(Ljava/lang/Throwable;)V
    //   124: goto -63 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   2	17	112	finally
    //   20	51	112	finally
    //   51	61	112	finally
    //   61	110	112	finally
    //   113	115	112	finally
    //   119	124	112	finally
    //   51	61	117	java/lang/Exception
  }

  public int updateStation(StationNode paramStationNode)
  {
    ContentValues localContentValues = StationNode2ContentValues(paramStationNode);
    localContentValues.remove("querytime");
    SQLiteDatabase localSQLiteDatabase = openDatabase();
    String str = this.mTableName;
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramStationNode.getCode();
    int i = localSQLiteDatabase.update(str, localContentValues, "code=?", arrayOfString);
    if (i <= 0)
      i = (int)insertStation(paramStationNode);
    return i;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hc.helper.StationHelper
 * JD-Core Version:    0.6.0
 */