package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.suanya.ui.tableView.SYTableView;
import cn.suanya.ui.tableView.TableItem;
import cn.suanya.ui.tableView.TableItemDelegate;
import cn.suanya.ui.tableView.TableItemGroup;
import cn.suanya.ui.tableView.TableItemGroups;
import cn.suanya.ui.tableView.TableItemLink;
import cn.suanya.ui.tableView.TableItemSimple;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.service.HuocheBase;
import com.yipiao.service.PassengerService;
import com.yipiao.service.YipiaoService;
import com.yipiao.wxapi.DisplayHelper;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MoreActivity extends BaseActivity
  implements TableItemDelegate
{
  private static int upgradeMethodIndex = 0;
  TableItemSimple about;
  TableItemSimple app_recomment;
  TableItemSimple changePwd;
  TableItemSimple engineSet;
  TableItemSimple evaluate;
  LayoutInflater localinflater;
  TableItemSimple log;
  TableItemSimple my12306;
  TableItemSimple passengerHistory;
  TableItemSimple passengerSet;
  TableItemSimple phoneBook;
  TableItemSimple problem;
  TableItemSimple register;
  TableItemSimple shareZhixing;
  TableItemSimple suggest;
  private SYTableView tableView;
  private LinearLayout tip_list_footer;
  private LinearLayout tip_list_top;
  TableItemSimple upgrade;
  TableItemSimple userinfo_edit;

  private TableItemGroups createList()
  {
    this.engineSet = new TableItemSimple(this, 2130903173, 0, "购票引擎", "自动设置", true);
    this.passengerSet = new TableItemSimple(this, 2130903173, 0, "当前购票人设置", "让您购票 快一步", true);
    this.passengerHistory = new TableItemSimple(this, 2130903173, 0, "购票人管理", "", true);
    this.changePwd = new TableItemSimple(this, 2130903173, 0, "修改密码", "", true);
    this.register = new TableItemSimple(this, 2130903173, 0, "注册12306账号", "", true);
    this.my12306 = new TableItemSimple(this, 2130903173, 0, "我的12306", "", true);
    if ((this.app.getUser() != null) && (this.app.getUser().getUserLabel() != null))
      this.my12306.getmTitle().setText("我的12306(" + this.app.getUser().getUserLabel() + ")");
    this.userinfo_edit = new TableItemSimple(this, 2130903173, 0, "个人信息", "", true);
    this.suggest = new TableItemSimple(this, 2130903173, 0, "投诉与建议", "", true);
    this.evaluate = new TableItemSimple(this, 2130903173, 0, "给个五星好评", "", true);
    this.problem = new TableItemSimple(this, 2130903173, 0, "常见问题", "", true);
    this.about = new TableItemSimple(this, 2130903173, 0, "关于我们", "", true);
    this.app_recomment = new TableItemSimple(this, 2130903173, 0, "精品应用", "", true);
    this.shareZhixing = new TableItemSimple(this, 2130903173, 0, "推荐智行给好友", "", true);
    HashMap localHashMap = new HashMap();
    localHashMap.put("engineSet", this.engineSet);
    localHashMap.put("passengerSet", this.passengerSet);
    localHashMap.put("passengerHistory", this.passengerHistory);
    localHashMap.put("changePwd", this.changePwd);
    localHashMap.put("register", this.register);
    localHashMap.put("my12306", this.my12306);
    localHashMap.put("userinfo_edit", this.userinfo_edit);
    localHashMap.put("suggest", this.suggest);
    localHashMap.put("evaluate", this.evaluate);
    localHashMap.put("problem", this.problem);
    localHashMap.put("about", this.about);
    localHashMap.put("app_recomment", this.app_recomment);
    localHashMap.put("shareZhixing", this.shareZhixing);
    TableItemGroups localTableItemGroups = new TableItemGroups(new TableItemGroup[0]);
    JSONArray localJSONArray1 = optJsonArrayFromLaunchInfo("more_active_table");
    int j;
    if ((localJSONArray1 != null) && (localJSONArray1.length() > 0))
      j = 0;
    while (true)
    {
      TableItemGroup localTableItemGroup;
      int k;
      JSONObject localJSONObject;
      try
      {
        if (j < localJSONArray1.length())
        {
          JSONArray localJSONArray2 = localJSONArray1.getJSONArray(j);
          localTableItemGroup = new TableItemGroup(new TableItem[0]);
          k = 0;
          if (k >= localJSONArray2.length())
            break label899;
          localJSONObject = localJSONArray2.getJSONObject(k);
          String str2 = localJSONObject.optString("key");
          if (("app_recomment".equalsIgnoreCase(str2)) && (106 == Constants.sid))
            break label928;
          TableItemSimple localTableItemSimple = (TableItemSimple)localHashMap.get(str2);
          if (localTableItemSimple == null)
            break label855;
          localTableItemGroup.add(localTableItemSimple);
        }
      }
      catch (JSONException localJSONException)
      {
      }
      String str1 = this.app.launchInfo.optString(Constants.last_version);
      if (this.app.hasNewVersion())
      {
        this.upgrade = new TableItemSimple(this, 2130903173, 0, "升级软件", Html.fromHtml("最新版本<font color='#ff0000'>V" + str1 + "</font>"), true);
        TableItem[] arrayOfTableItem2 = new TableItem[1];
        arrayOfTableItem2[0] = this.upgrade;
        localTableItemGroups.add(new TableItemGroup(arrayOfTableItem2));
      }
      while (true)
      {
        if (this.app.isDebug())
        {
          int i = this.app.sp.getInt("station_db_ver", -100);
          this.log = new TableItemSimple(this, 2130903173, 0, "查看日志" + i, "", true);
          TableItem[] arrayOfTableItem1 = new TableItem[1];
          arrayOfTableItem1[0] = this.log;
          localTableItemGroups.add(new TableItemGroup(arrayOfTableItem1));
        }
        return localTableItemGroups;
        label855: localTableItemGroup.add(new TableItemLink(this, 2130903173, 0, localJSONObject.optString("title"), localJSONObject.optString("subTitle"), localJSONObject.optString("uri")));
        break label928;
        label899: localTableItemGroups.add(localTableItemGroup);
        j++;
        break;
        ((MainTab)getParent()).setReddot(4, false);
      }
      label928: k++;
    }
  }

  private void execUpgrade()
  {
    JSONArray localJSONArray = this.app.launchInfo.optJSONArray("upgradeMethods");
    int i = localJSONArray.length();
    int j = upgradeMethodIndex % i;
    try
    {
      execUpgrade(localJSONArray.getJSONObject(j));
      upgradeMethodIndex = 1 + upgradeMethodIndex;
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        logToServer("upgradeError", localJSONException.getMessage());
    }
  }

  private void execUpgrade(String paramString)
  {
    downloadApk(this, 136, paramString, "智能火车票");
  }

  private void execUpgrade(JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("uri", "market://details?id=com.yipiao");
    String str2 = paramJSONObject.optString("package", null);
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str1));
    if ((str2 != null) && (str2.length() > 5))
    {
      localIntent.setPackage(str2);
      startActivity(localIntent);
    }
    do
      return;
    while (!str1.startsWith("http"));
    execUpgrade(str1);
  }

  private String getCurrentEngineSetValue()
  {
    int i = 0;
    int j = this.app.sp.getInt("engineSet2", 0);
    JSONArray localJSONArray = YipiaoService.getInstance().getApiSettingList();
    while (i < localJSONArray.length())
      try
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        if (localJSONObject.optInt("id", 0) == j)
        {
          String str = localJSONObject.optString("title", "");
          return str;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        i++;
      }
    return "";
  }

  private void initTip(LinearLayout paramLinearLayout, String paramString)
  {
    JSONArray localJSONArray = optJsonArrayFromLaunchInfo(paramString);
    if ((localJSONArray != null) && (localJSONArray.length() > 0))
    {
      paramLinearLayout.setVisibility(0);
      try
      {
        paramLinearLayout.removeAllViews();
        for (int i = 0; i < localJSONArray.length(); i++)
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          String str1 = localJSONObject.optString("uri");
          String str2 = localJSONObject.optString("intent");
          String str3 = localJSONObject.optString("title");
          String str4 = localJSONObject.optString("name");
          String str5 = localJSONObject.optString("cookies");
          View localView1 = this.localinflater.inflate(2130903113, null);
          if (i + 1 == localJSONArray.length())
          {
            View localView2 = localView1.findViewById(2131296921);
            if (localView2 != null)
              localView2.setVisibility(8);
          }
          localView1.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0F));
          ((TextView)localView1.findViewById(2131296920)).setText(str3);
          localView1.setOnClickListener(new View.OnClickListener(str2, str1, str3, str5, str4)
          {
            public void onClick(View paramView)
            {
              if ((this.val$intentStr != null) && (this.val$intentStr.length() > 0))
                try
                {
                  Intent localIntent2 = Intent.parseUri(this.val$intentStr, 0);
                  if (MoreActivity.this.getPackageManager().queryIntentActivities(localIntent2, 1).size() > 0)
                  {
                    MoreActivity.this.startActivity(localIntent2);
                    return;
                  }
                }
                catch (URISyntaxException localURISyntaxException)
                {
                }
              Intent localIntent1 = new Intent("SY.WEB.VIEW.LOCATION", Uri.parse(this.val$url));
              localIntent1.putExtra("title", this.val$title);
              localIntent1.putExtra("url", this.val$url);
              localIntent1.putExtra("history", true);
              localIntent1.putExtra("cookies", this.val$cookies);
              if (this.val$urlName != null)
                localIntent1.putExtra("urlName", this.val$urlName);
              MoreActivity.this.startActivity(localIntent1);
            }
          });
          paramLinearLayout.addView(localView1);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return;
    }
    paramLinearLayout.setVisibility(8);
  }

  private void initTipList()
  {
    initTip(this.tip_list_top, "more_active_tip");
  }

  protected int getMainLayout()
  {
    return 2130903111;
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296259:
    }
    showShareDialog();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.tip_list_top = ((LinearLayout)findViewById(2131296915));
    this.tip_list_footer = ((LinearLayout)findViewById(2131296919));
    this.localinflater = ((LayoutInflater)getSystemService("layout_inflater"));
    this.tableView = ((SYTableView)findViewById(2131296733));
    1 local1 = new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MoreActivity.this.loginOut();
      }
    };
    setClick(2131296916, local1);
    setClick(2131296917, local1);
    setTv(2131296918, "当前版本V" + getVersionName());
    setClick(2131296259, this);
    initTipList();
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2130903181)
      startActivity(new Intent(this, My12306Activity.class));
    if (paramInt == 2130903045)
      startActivity(new Intent(this, ChangePwdActivity.class));
    if (paramInt == 2130903182)
      new MyAsyncTask(this, false)
      {
        protected SysUserInfo myInBackground(String[] paramArrayOfString)
          throws Exception
        {
          return MoreActivity.this.app.getHC().getCurrentUserDetail();
        }

        protected void myPostExecute(SysUserInfo paramSysUserInfo)
        {
          MoreActivity.this.app.putParms("passenger", paramSysUserInfo);
          Intent localIntent = new Intent(MoreActivity.this, UserInfoActivity.class);
          localIntent.putExtra("passenger", paramSysUserInfo);
          MoreActivity.this.startActivity(localIntent);
        }
      }
      .execute(new String[0]);
    super.onLoginSuccess(paramInt);
  }

  protected void onResume()
  {
    this.tableView.setUpItems(createList(), 2130903088, 2130837879, 2130837871, 2130837862, 2130837862);
    if ((this.app.isLogined()) && (!this.app.isVisitor()))
    {
      findViewById(2131296916).setVisibility(0);
      setTv(2131296916, "退出登陆(" + this.app.getUser().getUserLabel() + ")");
      findViewById(2131296917).setVisibility(8);
    }
    while (true)
    {
      this.engineSet.setLabel(getCurrentEngineSetValue());
      super.onResume();
      return;
      findViewById(2131296916).setVisibility(8);
      findViewById(2131296917).setVisibility(0);
    }
  }

  public void onTableItemChanged(TableItem paramTableItem)
  {
  }

  public void onTableItemClick(TableItem paramTableItem)
  {
    if (this.shareZhixing == paramTableItem)
    {
      showShareDialog();
      if (this.userinfo_edit != paramTableItem)
        break label240;
      checkForLogin(2130903182);
    }
    label240: 
    do
    {
      return;
      if (this.passengerSet == paramTableItem)
      {
        this.app.putParms("passengers", this.passengerService.getCurrUsers());
        startActivity(new Intent(this, PassengerSetActivity.class));
        break;
      }
      if (this.engineSet == paramTableItem)
      {
        startActivity(new Intent(this, AdvancedSettingActivity.class));
        break;
      }
      if (this.app_recomment == paramTableItem)
      {
        startActivity(new Intent(this, AppRecommentActivity.class));
        break;
      }
      if (this.passengerHistory == paramTableItem)
      {
        startActivity(new Intent(this, PassengerHistoryActivity.class));
        break;
      }
      if (this.changePwd == paramTableItem)
      {
        checkForLogin(2130903045);
        break;
      }
      if (this.register == paramTableItem)
      {
        startActivity(new Intent(this, RegisterActivity.class));
        break;
      }
      if (this.suggest == paramTableItem)
      {
        startActivity(new Intent(this, SuggestActivity.class));
        break;
      }
      if (this.my12306 != paramTableItem)
        break;
      checkForLogin(2130903181);
      break;
      if (this.phoneBook == paramTableItem)
      {
        startActivity(new Intent(this, PhoneNoRepeatActivity.class));
        return;
      }
      if (this.evaluate == paramTableItem)
        try
        {
          DisplayHelper.showMarketDialog(this);
          return;
        }
        catch (Exception localException)
        {
          showToast("你没有安装任何软件市场，无法评价！");
          return;
        }
      if (this.upgrade == paramTableItem)
      {
        execUpgrade();
        ((MainTab)getParent()).setReddot(4, false);
        return;
      }
      if (this.about == paramTableItem)
      {
        String str2 = Constants.getServiceUrl() + "/about.html?" + System.currentTimeMillis() + "&cid=" + this.app.launchInfo.optLong("clientId");
        Intent localIntent2 = new Intent(this, CommonWebActivity.class);
        localIntent2.putExtra("title", "关于我们");
        localIntent2.putExtra("url", str2);
        startActivity(localIntent2);
        return;
      }
      if (this.problem != paramTableItem)
        continue;
      String str1 = Constants.getServiceUrl() + "/faq.html?" + System.currentTimeMillis() + "&&cid=" + this.app.launchInfo.optLong("clientId");
      Intent localIntent1 = new Intent(this, CommonWebActivity.class);
      localIntent1.putExtra("title", "常见问题");
      localIntent1.putExtra("url", str1);
      startActivity(localIntent1);
      return;
    }
    while (this.log != paramTableItem);
    startActivity(new Intent(this, LogActivity.class));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.MoreActivity
 * JD-Core Version:    0.6.0
 */