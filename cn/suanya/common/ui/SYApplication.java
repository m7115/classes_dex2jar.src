package cn.suanya.common.ui;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.telephony.TelephonyManager;
import cn.suanya.common.a.l;
import cn.suanya.common.persistent.SYPersistent;
import cn.suanya.common.persistent.SYPersitentFile;
import cn.suanya.rule.bean.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import org.json.JSONException;
import org.json.JSONObject;

public class SYApplication extends Application
{
  public static SYApplication app;
  static String mEmei;
  private static String sid = null;
  private int configVersion;
  public Context glob;
  public ObservMonitor launcObserv = new ObservMonitor();
  public JSONObject launchInfo;
  public Map<String, Object> parms = new HashMap();
  public SYPersistent persist = new SYPersitentFile("");
  public SharedPreferences sp;

  static
  {
    mEmei = null;
  }

  public SYApplication()
  {
    app = this;
  }

  public boolean checkVersionChange()
  {
    String str1 = getVersionName();
    String str2 = this.sp.getString("pre_version", null);
    int i;
    if (str2 != null)
    {
      boolean bool = str2.equals(str1);
      i = 0;
      if (bool);
    }
    else
    {
      this.sp.edit().putString("pre_version", str1).commit();
      setConfigVersion(0);
      i = 1;
    }
    return i;
  }

  public String getBaiduKey()
  {
    return "";
  }

  public String getChannelID()
  {
    if (sid == null)
      sid = "0";
    try
    {
      InputStream localInputStream = getAssets().open(l.sidAssest);
      sid = new BufferedReader(new InputStreamReader(localInputStream)).readLine();
      sid = sid.trim();
      l.sid = Integer.parseInt(sid);
      localInputStream.close();
      label65: return sid;
    }
    catch (IOException localIOException)
    {
      break label65;
    }
  }

  public int getConfigVersion()
  {
    return this.configVersion;
  }

  protected JSONObject getDefautServiceInfo()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject("{}");
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public String getEmei()
  {
    if (mEmei == null)
      mEmei = ((TelephonyManager)getSystemService("phone")).getDeviceId();
    return mEmei;
  }

  public String getVersionName()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return "1.0";
  }

  public String[] getserviceUrls()
  {
    return new String[] { "http://suanya.cn" };
  }

  public void initRule()
  {
  }

  public boolean isDebug()
  {
    return false;
  }

  public void onCreate()
  {
    this.sp = getSharedPreferences("mySP", 0);
    readServiceInfo();
    if (checkVersionChange())
      onNewVersion();
    this.configVersion = this.sp.getInt(l.conf_version, 0);
    this.glob = new Context();
    this.glob.put("app", this);
    initRule();
    super.onCreate();
  }

  protected void onNewVersion()
  {
  }

  public void readServiceInfo()
  {
    String str = this.sp.getString("jsonServiceInfo", null);
    if ((str != null) && (str.length() > 10))
      try
      {
        this.launchInfo = new JSONObject(str);
        return;
      }
      catch (JSONException localJSONException)
      {
        this.launchInfo = getDefautServiceInfo();
        return;
      }
    this.launchInfo = getDefautServiceInfo();
  }

  public void saveServiceInfo(JSONObject paramJSONObject)
  {
    this.launchInfo = paramJSONObject;
    SharedPreferences.Editor localEditor = this.sp.edit();
    localEditor.putString("jsonServiceInfo", paramJSONObject.toString());
    localEditor.commit();
  }

  public void setConfigVersion(int paramInt)
  {
    this.sp.edit().putInt(l.conf_version, paramInt).commit();
    this.configVersion = paramInt;
  }

  public class ObservMonitor extends Observable
  {
    public ObservMonitor()
    {
    }

    public void setChanged()
    {
      super.setChanged();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.ui.SYApplication
 * JD-Core Version:    0.6.0
 */