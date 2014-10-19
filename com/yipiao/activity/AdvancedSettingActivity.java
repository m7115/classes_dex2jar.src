package com.yipiao.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.CheckSettingItem;
import com.yipiao.wxapi.DisplayHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvancedSettingActivity extends BaseActivity
{
  private List<CheckSettingItem> apiList;
  private LinearLayout llApi;

  private List<CheckSettingItem> getApiItems()
  {
    JSONArray localJSONArray = YipiaoService.getInstance().getApiSettingList();
    try
    {
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      if (i < localJSONArray.length())
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        String str1 = localJSONObject.getString("title");
        String str2 = localJSONObject.getString("subtitle");
        String str3 = localJSONObject.getString("id");
        if (localJSONObject.getString("enable").equalsIgnoreCase("true"));
        for (boolean bool = true; ; bool = false)
        {
          CheckSettingItem localCheckSettingItem = new CheckSettingItem(this, str1, str2, str3, bool);
          if (localCheckSettingItem != null)
            localArrayList.add(localCheckSettingItem);
          i++;
          break;
        }
      }
      return localArrayList;
    }
    catch (JSONException localJSONException)
    {
    }
    return new ArrayList();
  }

  private int getEngineSet()
  {
    return this.app.sp.getInt("engineSet2", 0);
  }

  private void initEngineView()
  {
    this.llApi = ((LinearLayout)findViewById(2131296681));
    this.apiList = getApiItems();
    if ((this.apiList == null) || (this.apiList.size() <= 0))
    {
      this.llApi.setVisibility(8);
      return;
    }
    listToView();
  }

  private void initMonitorView()
  {
    checked(2131296684, this.app.sp.getBoolean("advancedSettingMonitor", false));
  }

  private void listToView()
  {
    this.llApi.setVisibility(0);
    int i = this.apiList.size();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    if (i == 1)
    {
      CheckSettingItem localCheckSettingItem2 = (CheckSettingItem)this.apiList.get(0);
      localCheckSettingItem2.setType(3);
      if (localCheckSettingItem2.getId() == getEngineSet())
        localCheckSettingItem2.setChecked(true);
      this.llApi.addView(localCheckSettingItem2);
      return;
    }
    int j = 0;
    label85: CheckSettingItem localCheckSettingItem1;
    if (j < i)
    {
      localCheckSettingItem1 = (CheckSettingItem)this.apiList.get(j);
      localCheckSettingItem1.setTag(localCheckSettingItem1.getItemId());
      localCheckSettingItem1.setOnClickListener(this);
      if (j != 0)
        break label165;
      localCheckSettingItem1.setType(0);
    }
    while (true)
    {
      if (localCheckSettingItem1.getId() == getEngineSet())
        localCheckSettingItem1.setChecked(true);
      this.llApi.addView(localCheckSettingItem1, localLayoutParams);
      j++;
      break label85;
      break;
      label165: if (j == i - 1)
      {
        localCheckSettingItem1.setType(2);
        continue;
      }
      localCheckSettingItem1.setType(1);
    }
  }

  private void setEngineTask(int paramInt)
  {
    1 local1 = new MyAsyncTask(this, null, "设置中...", false)
    {
      protected Void myInBackground(Integer[] paramArrayOfInteger)
        throws Exception
      {
        AdvancedSettingActivity.this.app.setApiVersion(paramArrayOfInteger[0].intValue());
        AdvancedSettingActivity.this.app.sp.edit().putInt("engineSet2", paramArrayOfInteger[0].intValue()).commit();
        return null;
      }

      protected void myPostExecute(Void paramVoid)
      {
        AdvancedSettingActivity.this.updateEngineView();
      }
    };
    Integer[] arrayOfInteger = new Integer[1];
    arrayOfInteger[0] = Integer.valueOf(paramInt);
    local1.execute(arrayOfInteger);
  }

  private void setMonitor()
  {
    boolean bool1 = true;
    boolean bool2 = this.app.sp.getBoolean("advancedSettingMonitor", false);
    SharedPreferences.Editor localEditor = this.app.sp.edit();
    boolean bool3;
    if (!bool2)
    {
      bool3 = bool1;
      localEditor.putBoolean("advancedSettingMonitor", bool3).commit();
      if (bool2)
        break label72;
    }
    while (true)
    {
      checked(2131296684, bool1);
      return;
      bool3 = false;
      break;
      label72: bool1 = false;
    }
  }

  private void shareWX()
  {
    String str1 = this.app.launchInfo.optString("advanced.setting.share.description", "分享到微信后，可让更多人代你监控火车票");
    String str2 = this.app.launchInfo.optString("advanced.setting.share.title", "推荐智行火车票超级监控功能，春节抢票无压力！");
    DisplayHelper.shareAppToWX(this, this.app.api, str2, str1);
  }

  protected int getMainLayout()
  {
    return 2130903040;
  }

  protected void init()
  {
    initEngineView();
    initMonitorView();
    setClick(2131296682, this);
    setClick(2131296685, this);
    setClick(2131296259, this);
    super.init();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 0:
    case 2:
    case 3:
    case 2131296682:
    case 2131296685:
    case 2131296259:
    }
    while (true)
    {
      super.onClick(paramView);
      return;
      setEngineTask(0);
      continue;
      setEngineTask(2);
      continue;
      setEngineTask(3);
      continue;
      setMonitor();
      continue;
      shareWX();
      continue;
      finish();
    }
  }

  protected void updateEngineView()
  {
    int i = getEngineSet();
    Iterator localIterator = this.apiList.iterator();
    while (localIterator.hasNext())
    {
      CheckSettingItem localCheckSettingItem = (CheckSettingItem)localIterator.next();
      if (localCheckSettingItem.getId() == i)
      {
        localCheckSettingItem.setChecked(true);
        continue;
      }
      localCheckSettingItem.setChecked(false);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.AdvancedSettingActivity
 * JD-Core Version:    0.6.0
 */