package com.yipiao.service;

import android.content.res.AssetManager;
import cn.suanya.common.a.i;
import cn.suanya.common.a.m;
import cn.suanya.common.net.ClientInfo;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.domain.Request;
import cn.suanya.service.SYService;
import com.example.pathview.bean.RecentTrain;
import com.example.pathview.bean.TrainInfo;
import com.google.gson.reflect.TypeToken;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.RecommendItem;
import com.yipiao.bean.StationNode;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YipiaoService extends SYService
{
  static NoteList all12306City;
  static NoteList allUniversity;
  private static YipiaoService yipiaoService;
  private JSONObject defautLaunchConfig = null;

  public static YipiaoService getInstance()
  {
    if (yipiaoService == null)
      yipiaoService = new YipiaoService();
    return yipiaoService;
  }

  // ERROR //
  @android.annotation.SuppressLint({"DefaultLocale"})
  public NoteList all12306City()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 30	com/yipiao/service/YipiaoService:all12306City	Lcom/yipiao/bean/NoteList;
    //   5: ifnull +13 -> 18
    //   8: getstatic 30	com/yipiao/service/YipiaoService:all12306City	Lcom/yipiao/bean/NoteList;
    //   11: astore 10
    //   13: aload_0
    //   14: monitorexit
    //   15: aload 10
    //   17: areturn
    //   18: new 32	com/yipiao/bean/NoteList
    //   21: dup
    //   22: invokespecial 33	com/yipiao/bean/NoteList:<init>	()V
    //   25: astore_2
    //   26: aconst_null
    //   27: astore_3
    //   28: aload_0
    //   29: getfield 37	com/yipiao/service/YipiaoService:app	Lcn/suanya/common/ui/SYApplication;
    //   32: invokevirtual 43	cn/suanya/common/ui/SYApplication:getAssets	()Landroid/content/res/AssetManager;
    //   35: ldc 45
    //   37: invokevirtual 51	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   40: astore_3
    //   41: new 53	org/json/JSONArray
    //   44: dup
    //   45: aload_3
    //   46: ldc 55
    //   48: invokestatic 61	org/apache/commons/io/IOUtils:toString	(Ljava/io/InputStream;Ljava/lang/String;)Ljava/lang/String;
    //   51: invokespecial 64	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   54: astore 6
    //   56: iconst_0
    //   57: istore 7
    //   59: iload 7
    //   61: aload 6
    //   63: invokevirtual 68	org/json/JSONArray:length	()I
    //   66: if_icmpge +54 -> 120
    //   69: aload 6
    //   71: iload 7
    //   73: invokevirtual 72	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   76: astore 8
    //   78: aload_2
    //   79: new 74	com/yipiao/bean/Note
    //   82: dup
    //   83: aload 8
    //   85: ldc 76
    //   87: invokevirtual 82	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   90: aload 8
    //   92: ldc 84
    //   94: invokevirtual 82	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   97: aload 8
    //   99: ldc 86
    //   101: invokevirtual 82	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   104: invokevirtual 92	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   107: invokespecial 95	com/yipiao/bean/Note:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   110: invokevirtual 99	com/yipiao/bean/NoteList:add	(Ljava/lang/Object;)Z
    //   113: pop
    //   114: iinc 7 1
    //   117: goto -58 -> 59
    //   120: aload_3
    //   121: invokestatic 103	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   124: aload_2
    //   125: putstatic 30	com/yipiao/service/YipiaoService:all12306City	Lcom/yipiao/bean/NoteList;
    //   128: aload_0
    //   129: monitorexit
    //   130: aload_2
    //   131: areturn
    //   132: astore_1
    //   133: aload_0
    //   134: monitorexit
    //   135: aload_1
    //   136: athrow
    //   137: astore 5
    //   139: aload_3
    //   140: invokestatic 103	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   143: aload_0
    //   144: monitorexit
    //   145: aload_2
    //   146: areturn
    //   147: astore 4
    //   149: aload_3
    //   150: invokestatic 103	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   153: aload 4
    //   155: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	15	132	finally
    //   18	26	132	finally
    //   120	130	132	finally
    //   133	135	132	finally
    //   139	145	132	finally
    //   149	156	132	finally
    //   28	56	137	java/lang/Exception
    //   59	114	137	java/lang/Exception
    //   28	56	147	finally
    //   59	114	147	finally
  }

  // ERROR //
  @android.annotation.SuppressLint({"DefaultLocale"})
  public NoteList all12306University()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 106	com/yipiao/service/YipiaoService:allUniversity	Lcom/yipiao/bean/NoteList;
    //   5: ifnull +13 -> 18
    //   8: getstatic 106	com/yipiao/service/YipiaoService:allUniversity	Lcom/yipiao/bean/NoteList;
    //   11: astore 10
    //   13: aload_0
    //   14: monitorexit
    //   15: aload 10
    //   17: areturn
    //   18: new 32	com/yipiao/bean/NoteList
    //   21: dup
    //   22: invokespecial 33	com/yipiao/bean/NoteList:<init>	()V
    //   25: astore_2
    //   26: aconst_null
    //   27: astore_3
    //   28: aload_0
    //   29: getfield 37	com/yipiao/service/YipiaoService:app	Lcn/suanya/common/ui/SYApplication;
    //   32: invokevirtual 43	cn/suanya/common/ui/SYApplication:getAssets	()Landroid/content/res/AssetManager;
    //   35: ldc 108
    //   37: invokevirtual 51	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   40: astore_3
    //   41: new 53	org/json/JSONArray
    //   44: dup
    //   45: aload_3
    //   46: ldc 55
    //   48: invokestatic 61	org/apache/commons/io/IOUtils:toString	(Ljava/io/InputStream;Ljava/lang/String;)Ljava/lang/String;
    //   51: invokespecial 64	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   54: astore 6
    //   56: iconst_0
    //   57: istore 7
    //   59: iload 7
    //   61: aload 6
    //   63: invokevirtual 68	org/json/JSONArray:length	()I
    //   66: if_icmpge +51 -> 117
    //   69: aload 6
    //   71: iload 7
    //   73: invokevirtual 72	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   76: astore 8
    //   78: aload_2
    //   79: new 74	com/yipiao/bean/Note
    //   82: dup
    //   83: aload 8
    //   85: ldc 110
    //   87: invokevirtual 82	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   90: aload 8
    //   92: ldc 112
    //   94: invokevirtual 82	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   97: aload 8
    //   99: ldc 114
    //   101: invokevirtual 82	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   104: invokespecial 95	com/yipiao/bean/Note:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   107: invokevirtual 99	com/yipiao/bean/NoteList:add	(Ljava/lang/Object;)Z
    //   110: pop
    //   111: iinc 7 1
    //   114: goto -55 -> 59
    //   117: aload_3
    //   118: invokestatic 103	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   121: aload_2
    //   122: putstatic 106	com/yipiao/service/YipiaoService:allUniversity	Lcom/yipiao/bean/NoteList;
    //   125: aload_0
    //   126: monitorexit
    //   127: aload_2
    //   128: areturn
    //   129: astore_1
    //   130: aload_0
    //   131: monitorexit
    //   132: aload_1
    //   133: athrow
    //   134: astore 5
    //   136: aload_3
    //   137: invokestatic 103	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   140: aload_0
    //   141: monitorexit
    //   142: aload_2
    //   143: areturn
    //   144: astore 4
    //   146: aload_3
    //   147: invokestatic 103	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   150: aload 4
    //   152: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	15	129	finally
    //   18	26	129	finally
    //   117	127	129	finally
    //   130	132	129	finally
    //   136	142	129	finally
    //   146	153	129	finally
    //   28	56	134	java/lang/Exception
    //   59	111	134	java/lang/Exception
    //   28	56	144	finally
    //   59	111	144	finally
  }

  protected void checkResp(JSONObject paramJSONObject)
    throws m, JSONException
  {
    if (paramJSONObject.optInt("status") != 0)
      throw new m("0", paramJSONObject.optString("message"));
  }

  public Note get12306CityByCode(String paramString)
  {
    Note localNote = all12306City().getByCode(paramString);
    if (localNote == null)
      localNote = new Note(paramString, paramString);
    return localNote;
  }

  public JSONArray getApiSettingList()
  {
    String str = this.app.launchInfo.optString("advanced_setting_al", "[{id:'0',title:'自动设置',subtitle:'系统根据运行情况，自动为您选择引擎',enable:'true'},{id:'2',title:'12306新版',subtitle:'使用12306新版网站订票',enable:'true'},{id:'3',title:'12306手机版',subtitle:'抢票快，支持自动提交订单，支持支付宝，银联等支付方式',enable:'true'}]");
    try
    {
      JSONArray localJSONArray = new JSONArray(str);
      return localJSONArray;
    }
    catch (JSONException localJSONException)
    {
    }
    return new JSONArray();
  }

  public JSONObject getDefautLaunchConfig()
  {
    if (this.defautLaunchConfig != null)
      return this.defautLaunchConfig;
    try
    {
      InputStream localInputStream = this.app.getAssets().open("defaut_launch_config.json");
      byte[] arrayOfByte = new byte[localInputStream.available()];
      localInputStream.read(arrayOfByte);
      this.defautLaunchConfig = new JSONObject(EncodingUtils.getString(arrayOfByte, "UTF-8"));
      JSONObject localJSONObject = this.defautLaunchConfig;
      return localJSONObject;
    }
    catch (Exception localException)
    {
    }
    return new JSONObject();
  }

  protected <T> Request<T> getReQ(T paramT)
  {
    Request localRequest = super.getReQ(paramT);
    localRequest.setRuleVersion(String.valueOf(((YipiaoApplication)this.app).getApiVersion()));
    return localRequest;
  }

  public RecommendItem getRecommend(String paramString)
  {
    Iterator localIterator = getRecommendList().iterator();
    while (localIterator.hasNext())
    {
      RecommendItem localRecommendItem = (RecommendItem)localIterator.next();
      if ((paramString != null) && (paramString.equals(localRecommendItem.getPid())))
        return localRecommendItem;
    }
    return null;
  }

  public List<RecommendItem> getRecommendList()
  {
    return (List)i.a(this.app.launchInfo.optString("app_rl", getInstance().getDefautLaunchConfig().optString("app_rl")), new TypeToken()
    {
    }
    .getType());
  }

  public List<StationNode> getStationDBUpdate(String paramString, int paramInt)
    throws Exception
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("clientVersion", paramString);
    localHashMap.put("stationDbVer", Integer.valueOf(paramInt));
    String str = executeOnservice("stationDbPatch", localHashMap).optString("data");
    if ((str == null) || (str.equals("")))
      return null;
    return (List)i.a(str, new TypeToken()
    {
    }
    .getType());
  }

  public List<RecentTrain> getTrainByfromTo(String paramString1, String paramString2)
    throws Exception
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("from", paramString1);
    localHashMap.put("to", paramString2);
    JSONObject localJSONObject = executeOnservice("getTrainByfromTo", localHashMap);
    checkResp(localJSONObject);
    JSONArray localJSONArray = localJSONObject.optJSONArray("data");
    if (localJSONArray == null)
      throw new m("0", "没有对应的车次！");
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(new RecentTrain(localJSONArray.getJSONObject(i)));
    return localArrayList;
  }

  public TrainInfo getTrainInfoByCode(String paramString)
    throws Exception
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("code", paramString);
    JSONObject localJSONObject1 = executeOnservice("getTrainInfoByCode", localHashMap);
    checkResp(localJSONObject1);
    JSONObject localJSONObject2 = localJSONObject1.optJSONObject("data");
    if (localJSONObject2 == null)
      throw new m("0", "没有" + paramString + "车次的数据！");
    return new TrainInfo(localJSONObject2);
  }

  public JSONObject launch(ClientInfo paramClientInfo)
    throws Exception
  {
    return new JSONObject(executeOnservice("launch", i.a(getReQ(paramClientInfo), new TypeToken()
    {
    }
    .getType())));
  }

  public NoteList provinceUniversity(String paramString)
  {
    NoteList localNoteList1 = all12306University();
    NoteList localNoteList2 = new NoteList();
    Iterator localIterator = localNoteList1.iterator();
    while (localIterator.hasNext())
    {
      Note localNote = (Note)localIterator.next();
      if (!paramString.equals(localNote.getFilter()))
        continue;
      localNoteList2.add(localNote);
    }
    return localNoteList2;
  }

  public Note universityByCode(String paramString)
  {
    Note localNote = all12306University().getByCode(paramString);
    if (localNote == null)
      localNote = new Note(paramString, paramString);
    return localNote;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.YipiaoService
 * JD-Core Version:    0.6.0
 */