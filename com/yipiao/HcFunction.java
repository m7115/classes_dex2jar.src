package com.yipiao;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import cn.suanya.common.a.c;
import cn.suanya.common.a.n;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.common.ui.SYApplication.ObservMonitor;
import cn.suanya.rule.bean.SyFunction;
import com.MobileTicket.CheckCodeUtil;
import com.yipiao.base.HCUtil;
import com.yipiao.bean.Note;
import com.yipiao.bean.Train;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.YipiaoService;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HcFunction extends SyFunction
{
  private static byte[] sign = null;

  private YipiaoApplication getApp()
  {
    return (YipiaoApplication)YipiaoApplication.app;
  }

  private String hexStr(int paramInt)
  {
    String str = "";
    for (int i = 0; i < paramInt; i++)
      str = str + Integer.toHexString(random(16));
    return str;
  }

  private byte[] readsign()
    throws Exception
  {
    if (sign != null)
      return sign;
    InputStream localInputStream = getApp().getResources().getAssets().open("sign");
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte, 0, arrayOfByte.length);
        if (i == -1)
          break;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception localException2)
    {
      n.b(localException2);
      try
      {
        localByteArrayOutputStream.close();
        while (true)
        {
          return sign;
          sign = localByteArrayOutputStream.toByteArray();
          try
          {
            localByteArrayOutputStream.close();
          }
          catch (Exception localException4)
          {
            n.b(localException4);
          }
        }
      }
      catch (Exception localException3)
      {
        while (true)
          n.b(localException3);
      }
    }
    finally
    {
    }
    try
    {
      localByteArrayOutputStream.close();
      throw localObject;
    }
    catch (Exception localException1)
    {
      while (true)
        n.b(localException1);
    }
  }

  public String Md5(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer("");
      for (int i = 0; i < arrayOfByte.length; i++)
      {
        int j = arrayOfByte[i];
        if (j < 0)
          j += 256;
        if (j < 16)
          localStringBuffer.append("0");
        localStringBuffer.append(Integer.toHexString(j));
      }
      String str = localStringBuffer.toString();
      return str;
    }
    catch (Exception localException)
    {
      n.b(localException);
    }
    return null;
  }

  public String Sha(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes());
      String str = new BigInteger(1, localMessageDigest.digest()).toString(16);
      return str;
    }
    catch (Exception localException)
    {
      n.b(localException);
    }
    return paramString;
  }

  public String WlSubstring(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf("+") > 0))
      paramString = paramString.substring(0, paramString.indexOf("+"));
    return paramString;
  }

  public boolean calPassengerSeat(Train paramTrain, ArrayList<UserInfo> paramArrayList)
  {
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      UserInfo localUserInfo = (UserInfo)localIterator.next();
      if (!HCUtil.calPassengerSeat(paramTrain.getSeats(), localUserInfo))
        return false;
    }
    return true;
  }

  public int confirmMessage(String paramString1, String paramString2)
    throws Exception
  {
    return confirmMessage(paramString1, paramString2, "确定", "取消");
  }

  public int confirmMessage(String paramString1, String paramString2, String paramString3, String paramString4)
    throws Exception
  {
    getApp().ruleObservable.setChanged();
    getApp().ruleObservable.notifyObservers(new RuleMessage(RuleMessage.type_confirm, paramString1, paramString2, paramString4, paramString3));
    synchronized (getApp().glob)
    {
      getApp().glob.wait();
      return getApp().glob.getInt(RuleMessage.result_key);
    }
  }

  public String createDeviceNo()
  {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
  }

  public String createDeviceNoIos()
  {
    return (hexStr(8) + "-" + hexStr(4) + "-" + hexStr(4) + "-" + hexStr(4) + "-" + hexStr(12)).toUpperCase(Locale.ENGLISH);
  }

  public String createDeviceUUID()
  {
    android.content.Context localContext = YipiaoApplication.app.getApplicationContext();
    boolean bool = localContext.getPackageManager().hasSystemFeature("android.hardware.wifi");
    String str1 = null;
    if (bool)
      str1 = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    String str2 = Settings.Secure.getString(localContext.getContentResolver(), "android_id");
    if (str1 != null);
    for (String str3 = str2 + str1; ; str3 = str2)
      return UUID.nameUUIDFromBytes(str3.getBytes()).toString();
  }

  public String createWlDeviceCtxSession()
  {
    Double localDouble = Double.valueOf(Math.floor(16777216.0D * Math.random()));
    Long localLong = Long.valueOf(new Date().getTime());
    return localDouble.intValue() + "" + localLong.toString();
  }

  public Note get12306CityByCode(String paramString)
  {
    return YipiaoService.getInstance().get12306CityByCode(paramString);
  }

  public String getCheckCode(String paramString)
  {
    return CheckCodeUtil.checkcode("", paramString);
  }

  public String getConfig(String paramString)
  {
    return YipiaoApplication.app.launchInfo.optString(paramString, "");
  }

  public String getConfig(String paramString1, String paramString2)
  {
    return YipiaoApplication.app.launchInfo.optString(paramString1, paramString2);
  }

  public String getDeviceNo()
  {
    String str = getApp().sp.getString("deviceNo12306", "");
    if (str.length() < 2)
    {
      str = createDeviceNo();
      getApp().sp.edit().putString("deviceNo12306", str).commit();
    }
    return str;
  }

  public String getDeviceNoIos()
  {
    String str = getApp().sp.getString("deviceNo12306Ios", "");
    if (str.length() < 2)
    {
      str = createDeviceNoIos();
      getApp().sp.edit().putString("deviceNo12306Ios", str).commit();
    }
    return str;
  }

  public String getIphoneCheckCode(String paramString)
  {
    return Md5(paramString);
  }

  public String getModel()
  {
    return Build.MODEL;
  }

  public String getRelease()
  {
    return Build.VERSION.RELEASE;
  }

  public long getServertimeOffset(String paramString)
  {
    long l = Date.parse(paramString);
    return Calendar.getInstance().getTimeInMillis() - l;
  }

  public String getSynServerDate(Long paramLong)
  {
    return c.formart(new Date(Calendar.getInstance().getTimeInMillis() - paramLong.longValue()), "yyyyMMddHHmmss");
  }

  public String getWldeviceNoProvisioningRealm(String paramString1, String paramString2)
    throws JSONException
  {
    JSONObject localJSONObject1 = new JSONObject();
    localJSONObject1.put("id", "MobileTicket");
    localJSONObject1.put("version", "1.21");
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("id", paramString1);
    localJSONObject2.put("os", Build.VERSION.RELEASE);
    localJSONObject2.put("model", Build.MODEL);
    localJSONObject2.put("environment", "android");
    new JSONObject().put("jsoncustom", "");
    JSONObject localJSONObject3 = new JSONObject();
    localJSONObject3.put("token", paramString2);
    localJSONObject3.put("app", localJSONObject1);
    localJSONObject3.put("device", localJSONObject2);
    localJSONObject3.put("custom", new JSONObject());
    JSONObject localJSONObject4 = new JSONObject();
    localJSONObject4.put("ID", localJSONObject3);
    JSONObject localJSONObject5 = new JSONObject();
    localJSONObject5.put("wl_deviceNoProvisioningRealm", localJSONObject4);
    return localJSONObject5.toString();
  }

  public void logToServer(String paramString1, String paramString2)
  {
    YipiaoService.getInstance().asyncLog(new LogInfo(paramString1, paramString2));
  }

  public void notifyActivity(int paramInt, String paramString)
  {
    getApp().ruleObservable.setChanged();
    getApp().ruleObservable.notifyObservers(new RuleMessage(paramInt, paramString));
  }

  public String numberSub(String paramString1, String paramString2)
    throws Exception
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#.00");
    Double localDouble = Double.valueOf(Double.parseDouble(paramString1) - Double.parseDouble(paramString2));
    if (localDouble.doubleValue() == 0.0D)
      return "0.00";
    return localDecimalFormat.format(localDouble);
  }

  public void processMessage(String paramString)
  {
    getApp().ruleObservable.setChanged();
    getApp().ruleObservable.notifyObservers(new RuleMessage(RuleMessage.type_process, paramString));
  }

  public String sercord(String paramString1, String paramString2)
  {
    String str1 = base64Decoder(paramString1);
    n.b(str1);
    String[] arrayOfString = str1.split("#");
    arrayOfString[13] = ypInfo(arrayOfString[13], paramString2);
    String str2 = linkCode(arrayOfString, "#");
    String str3 = str2.substring(0, -1 + str2.length());
    n.b(str3);
    return base64Encoder(str3);
  }

  public int showMessage(String paramString1, String paramString2)
    throws InterruptedException
  {
    return showMessage(paramString1, paramString2, "确定");
  }

  public int showMessage(String paramString1, String paramString2, String paramString3)
    throws InterruptedException
  {
    getApp().ruleObservable.setChanged();
    getApp().ruleObservable.notifyObservers(new RuleMessage(RuleMessage.type_show, paramString1, paramString2, "取消", paramString3));
    synchronized (getApp().glob)
    {
      getApp().glob.wait();
      return getApp().glob.getInt(RuleMessage.result_key);
    }
  }

  public void toastMessage(String paramString)
  {
    getApp().ruleObservable.setChanged();
    getApp().ruleObservable.notifyObservers(new RuleMessage(RuleMessage.type_toast, paramString));
  }

  public Note universityByCode(String paramString)
  {
    return YipiaoService.getInstance().universityByCode(paramString);
  }

  public String wlAuthenticityRealm(String paramString)
    throws Exception
  {
    int i = paramString.indexOf("+");
    String str1 = paramString.substring(i + 1);
    String str2 = paramString.substring(0, i);
    JSONArray localJSONArray1 = new JSONArray(Arrays.asList(str1.split("-")));
    JSONArray localJSONArray2 = new JSONArray();
    localJSONArray2.put(0, str2);
    localJSONArray2.put(1, localJSONArray1);
    return SySecurityUtils.hashDataFromJSON(localJSONArray2, readsign());
  }

  public String ypInfo(String paramString1, String paramString2)
  {
    int i = paramString1.length() / 10;
    int j = 0;
    String str1 = "";
    while (j < i)
    {
      String str2 = paramString1.substring(j * 10, 10 + j * 10);
      if (str2.startsWith(paramString2))
        str2 = str2.substring(0, 9) + '5';
      str1 = str1 + str2;
      j++;
    }
    return str1;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.HcFunction
 * JD-Core Version:    0.6.0
 */