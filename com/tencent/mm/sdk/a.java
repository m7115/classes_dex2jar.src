package com.tencent.mm.sdk;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.net.Uri;
import com.tencent.mm.sdk.plugin.b.a;
import com.tencent.mm.sdk.plugin.b.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class a
  implements SharedPreferences
{
  private final ContentResolver a;
  private final String[] b = { "_id", "key", "type", "value" };
  private final HashMap<String, Object> c = new HashMap();
  private a d = null;

  public a(Context paramContext)
  {
    this.a = paramContext.getContentResolver();
  }

  private Object a(String paramString)
  {
    while (true)
    {
      try
      {
        Cursor localCursor = this.a.query(b.b.a, this.b, "key = ?", new String[] { paramString }, null);
        if (localCursor == null)
          return null;
        int i = localCursor.getColumnIndex("type");
        int j = localCursor.getColumnIndex("value");
        if (localCursor.moveToFirst())
        {
          localObject = b.a.a(localCursor.getInt(i), localCursor.getString(j));
          localCursor.close();
          return localObject;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
      Object localObject = null;
    }
  }

  public boolean contains(String paramString)
  {
    return a(paramString) != null;
  }

  public SharedPreferences.Editor edit()
  {
    if (this.d == null)
      this.d = new a(this.a);
    return this.d;
  }

  public Map<String, ?> getAll()
  {
    Cursor localCursor;
    try
    {
      localCursor = this.a.query(b.b.a, this.b, null, null, null);
      if (localCursor == null)
        return null;
      int i = localCursor.getColumnIndex("key");
      int j = localCursor.getColumnIndex("type");
      int k = localCursor.getColumnIndex("value");
      while (localCursor.moveToNext())
      {
        Object localObject = b.a.a(localCursor.getInt(j), localCursor.getString(k));
        this.c.put(localCursor.getString(i), localObject);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return this.c;
    }
    localCursor.close();
    HashMap localHashMap = this.c;
    return localHashMap;
  }

  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    Object localObject = a(paramString);
    if ((localObject != null) && ((localObject instanceof Boolean)))
      paramBoolean = ((Boolean)localObject).booleanValue();
    return paramBoolean;
  }

  public float getFloat(String paramString, float paramFloat)
  {
    Object localObject = a(paramString);
    if ((localObject != null) && ((localObject instanceof Float)))
      paramFloat = ((Float)localObject).floatValue();
    return paramFloat;
  }

  public int getInt(String paramString, int paramInt)
  {
    Object localObject = a(paramString);
    if ((localObject != null) && ((localObject instanceof Integer)))
      paramInt = ((Integer)localObject).intValue();
    return paramInt;
  }

  public long getLong(String paramString, long paramLong)
  {
    Object localObject = a(paramString);
    if ((localObject != null) && ((localObject instanceof Long)))
      paramLong = ((Long)localObject).longValue();
    return paramLong;
  }

  public String getString(String paramString1, String paramString2)
  {
    Object localObject = a(paramString1);
    if ((localObject != null) && ((localObject instanceof String)))
      return (String)localObject;
    return paramString2;
  }

  public Set<String> getStringSet(String paramString, Set<String> paramSet)
  {
    return null;
  }

  public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
  }

  public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
  }

  private static class a
    implements SharedPreferences.Editor
  {
    private Map<String, Object> a = new HashMap();
    private Set<String> b = new HashSet();
    private boolean c = false;
    private ContentResolver d;

    public a(ContentResolver paramContentResolver)
    {
      this.d = paramContentResolver;
    }

    public void apply()
    {
    }

    public SharedPreferences.Editor clear()
    {
      this.c = true;
      return this;
    }

    public boolean commit()
    {
      ContentValues localContentValues = new ContentValues();
      if (this.c)
      {
        this.d.delete(b.b.a, null, null);
        this.c = false;
      }
      Iterator localIterator1 = this.b.iterator();
      while (localIterator1.hasNext())
      {
        String str = (String)localIterator1.next();
        this.d.delete(b.b.a, "key = ?", new String[] { str });
      }
      Iterator localIterator2 = this.a.entrySet().iterator();
      while (localIterator2.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator2.next();
        if (!b.a.a(localContentValues, localEntry.getValue()))
          continue;
        ContentResolver localContentResolver = this.d;
        Uri localUri = b.b.a;
        String[] arrayOfString = new String[1];
        arrayOfString[0] = ((String)localEntry.getKey());
        localContentResolver.update(localUri, localContentValues, "key = ?", arrayOfString);
      }
      return true;
    }

    public SharedPreferences.Editor putBoolean(String paramString, boolean paramBoolean)
    {
      this.a.put(paramString, Boolean.valueOf(paramBoolean));
      this.b.remove(paramString);
      return this;
    }

    public SharedPreferences.Editor putFloat(String paramString, float paramFloat)
    {
      this.a.put(paramString, Float.valueOf(paramFloat));
      this.b.remove(paramString);
      return this;
    }

    public SharedPreferences.Editor putInt(String paramString, int paramInt)
    {
      this.a.put(paramString, Integer.valueOf(paramInt));
      this.b.remove(paramString);
      return this;
    }

    public SharedPreferences.Editor putLong(String paramString, long paramLong)
    {
      this.a.put(paramString, Long.valueOf(paramLong));
      this.b.remove(paramString);
      return this;
    }

    public SharedPreferences.Editor putString(String paramString1, String paramString2)
    {
      this.a.put(paramString1, paramString2);
      this.b.remove(paramString1);
      return this;
    }

    public SharedPreferences.Editor putStringSet(String paramString, Set<String> paramSet)
    {
      return null;
    }

    public SharedPreferences.Editor remove(String paramString)
    {
      this.b.add(paramString);
      return this;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.a
 * JD-Core Version:    0.6.0
 */