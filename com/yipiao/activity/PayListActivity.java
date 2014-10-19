package com.yipiao.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import cn.suanya.common.a.m;
import cn.suanya.common.a.s;
import cn.suanya.common.bean.NameValue;
import cn.suanya.common.bean.NameValueList;
import cn.suanya.common.net.Cookie;
import cn.suanya.common.net.IHttpClient;
import cn.suanya.ui.tableView.SYTableView;
import cn.suanya.ui.tableView.TableItem;
import cn.suanya.ui.tableView.TableItemDelegate;
import cn.suanya.ui.tableView.TableItemGroup;
import cn.suanya.ui.tableView.TableItemGroups;
import cn.suanya.ui.tableView.TableItemSimple;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.OrderResult;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class PayListActivity extends BaseActivity
  implements TableItemDelegate
{
  TableItemSimple beijing;
  TableItemSimple guangda;
  TableItemSimple jianhang;
  TableItemSimple jiaotong;
  TableItemSimple mobilePay;
  TableItemSimple nonghang;
  private OrderResult order;
  TableItemSimple pingan;
  TableItemSimple pufa;
  private SYTableView tableView;
  TableItemSimple web_12306;
  TableItemSimple xingye;
  TableItemSimple zfbClientPay;
  TableItemSimple zfbpay;
  TableItemSimple zhaoshangHtml5;
  TableItemSimple zhaoshangshouji;

  private TableItemGroups createList()
  {
    this.zfbClientPay = new TableItemSimple(this, 2130903173, 2130837506, "支付宝客户端支付", "", true);
    this.zfbpay = new TableItemSimple(this, 2130903173, 2130837506, "支付宝支付", "推荐", true);
    this.zhaoshangshouji = new TableItemSimple(this, 2130903173, 2130837531, "招商银行手机支付", "", true);
    this.zhaoshangHtml5 = new TableItemSimple(this, 2130903173, 2130837531, "招商银行网银支付", "推荐", true);
    this.jianhang = new TableItemSimple(this, 2130903173, 2130837535, "建设银行网银支付", "推荐", true);
    this.nonghang = new TableItemSimple(this, 2130903173, 2130837539, "农业银行K码支付", "", true);
    this.jiaotong = new TableItemSimple(this, 2130903173, 2130837536, "交通银行银联支付", "", true);
    this.guangda = new TableItemSimple(this, 2130903173, 2130837537, "光大银行银联支付", "", true);
    this.xingye = new TableItemSimple(this, 2130903173, 2130837538, "兴业银行银联支付", "", true);
    this.pufa = new TableItemSimple(this, 2130903173, 2130837532, "浦发银行银联支付", "", true);
    this.beijing = new TableItemSimple(this, 2130903173, 2130837533, "北京银行银联支付", "", true);
    this.pingan = new TableItemSimple(this, 2130903173, 2130837534, "平安银行银联支付", "", true);
    this.mobilePay = new TableItemSimple(this, 2130903173, 2130837540, "去手机版支付", "推荐", true);
    this.web_12306 = new TableItemSimple(this, 2130903173, 2130837541, "其它支付方式", "", true);
    HashMap localHashMap = new HashMap();
    localHashMap.put("zfbClientPay", this.zfbClientPay);
    localHashMap.put("zfbpay", this.zfbpay);
    localHashMap.put("zhaoshangshouji", this.zhaoshangshouji);
    localHashMap.put("zhaoshangHtml5", this.zhaoshangHtml5);
    localHashMap.put("jianhang", this.jianhang);
    localHashMap.put("nonghang", this.nonghang);
    localHashMap.put("jiaotong", this.jiaotong);
    localHashMap.put("guangda", this.guangda);
    localHashMap.put("xingye", this.xingye);
    localHashMap.put("pufa", this.pufa);
    localHashMap.put("beijing", this.beijing);
    localHashMap.put("pingan", this.pingan);
    localHashMap.put("mobilePay", this.mobilePay);
    localHashMap.put("web_12306", this.web_12306);
    String str = this.app.launchInfo.optString("payListItems", "zfbClientPay|zfbpay|zhaoshangHtml5|jianhang|nonghang|mobilePay|web_12306");
    TableItemGroup localTableItemGroup = new TableItemGroup(new TableItem[0]);
    String[] arrayOfString = str.split("\\|");
    for (int i = 0; i < arrayOfString.length; i++)
    {
      TableItemSimple localTableItemSimple = (TableItemSimple)localHashMap.get(arrayOfString[i]);
      if (localTableItemSimple == null)
        continue;
      localTableItemGroup.add(localTableItemSimple);
    }
    return new TableItemGroups(new TableItemGroup[] { localTableItemGroup });
  }

  private boolean hasInstallAliPay()
  {
    try
    {
      PackageInfo localPackageInfo2 = getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 0);
      localPackageInfo1 = localPackageInfo2;
      int i = 0;
      if (localPackageInfo1 != null)
        i = 1;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        localNameNotFoundException.printStackTrace();
        PackageInfo localPackageInfo1 = null;
      }
    }
  }

  private void initFinishBackBtn()
  {
    View localView = findViewById(2131296259);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          PayListActivity.this.finish();
          PayListActivity.this.startActivity(new Intent(PayListActivity.this, OrderHistoryActivity.class));
        }
      });
  }

  private void mobilePay()
  {
    new MyAsyncTask(this, true)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        HuocheMobile localHuocheMobile = HuocheMobile.getInstance();
        localHuocheMobile.autoLogin();
        OrderResult localOrderResult = localHuocheMobile.uncompleteOrder();
        if ((localOrderResult.getOrder() == null) || (localOrderResult.getOrder().isEmpty()))
          throw new m("没有待支付订单");
        return localHuocheMobile.webPay(localOrderResult);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        String str = paramContext.getStr("action");
        NameValueList localNameValueList = (NameValueList)paramContext.get("values");
        if (localNameValueList == null)
        {
          PayListActivity.this.showToast("支付失败");
          return;
        }
        PayListActivity.this.app.putParms("order", PayListActivity.this.order);
        PayListActivity.this.goWebActivity("网上支付", str, null, localNameValueList.urlEncodeBytes());
      }
    }
    .execute(new Object[0]);
  }

  private void set12306Cookie(Intent paramIntent)
  {
    Object localObject1 = "";
    List localList = HuocheNew.getInstance().httpClient.getCookies(Constants.url12306);
    if ((localList != null) && (localList.size() > 0))
    {
      Iterator localIterator = localList.iterator();
      Cookie localCookie;
      for (Object localObject2 = localObject1; localIterator.hasNext(); localObject2 = (String)localObject2 + Constants.url12306 + "|" + localCookie.toString() + ";")
        localCookie = (Cookie)localIterator.next();
      localObject1 = localObject2;
    }
    if ((localObject1 != null) && (((String)localObject1).length() > 1))
      paramIntent.putExtra("cookies", (String)localObject1);
  }

  public void abcWebPay()
  {
    new MyAsyncTask(this, true)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().abcWebPayPre(PayListActivity.this.order);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        Intent localIntent = new Intent(PayListActivity.this, PayABCActivity.class);
        PayListActivity.this.startActivity(localIntent);
      }
    }
    .execute(new Object[0]);
  }

  public void ccbWebPay()
  {
    new MyAsyncTask(this, true)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().ccbWebPayPre(PayListActivity.this.order);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        String str = (String)paramContext.get("ccb_action");
        NameValueList localNameValueList = (NameValueList)paramContext.get("ccb_parms");
        HashMap localHashMap = new HashMap();
        localHashMap.put("Referer", "https://epay.12306.cn/pay/webBusiness");
        Intent localIntent = new Intent(PayListActivity.this, PayCCBWEBActivity.class);
        PayListActivity.this.goWebPay(false, "utf8", str, true, localNameValueList, localHashMap, localIntent, "建设银行网银支付");
      }
    }
    .execute(new Object[0]);
  }

  public void cmbWebPay()
  {
    new MyAsyncTask(this, true)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().cmbPayPreHtml5(PayListActivity.this.order);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        String str = (String)paramContext.get("cmb_action");
        NameValueList localNameValueList = (NameValueList)paramContext.get("cmb_parms");
        if (localNameValueList == null)
        {
          PayListActivity.this.showToast("支付失败");
          return;
        }
        PayListActivity.this.goWebPay(false, "utf8", str, false, localNameValueList, null, "招行银行网银支付");
      }
    }
    .execute(new Object[0]);
  }

  protected int getMainLayout()
  {
    return 2130903140;
  }

  public void goWebPay(boolean paramBoolean1, String paramString1, String paramString2, boolean paramBoolean2, List<NameValue> paramList, HashMap<String, String> paramHashMap)
  {
    goWebPay(paramBoolean1, paramString1, paramString2, paramBoolean2, paramList, paramHashMap, new Intent(this, PayWEBActivity.class), null);
  }

  public void goWebPay(boolean paramBoolean1, String paramString1, String paramString2, boolean paramBoolean2, List<NameValue> paramList, HashMap<String, String> paramHashMap, Intent paramIntent, String paramString3)
  {
    Object localObject = "";
    char c = '?';
    if (paramString2.indexOf("?") > 0)
      c = '&';
    try
    {
      String str = s.a(paramList, paramString1);
      localObject = str;
      label34: if (paramBoolean1)
      {
        startActivity(Intent.createChooser(new Intent("android.intent.action.VIEW", Uri.parse(paramString2 + c + (String)localObject)), "选择合适的浏览器"));
        return;
      }
      if (paramBoolean2)
        paramIntent.putExtra("url", Uri.parse(paramString2 + c + (String)localObject).toString());
      while (true)
      {
        paramIntent.putExtra("heads", paramHashMap);
        if (paramString3 != null)
          paramIntent.putExtra("title", paramString3);
        startActivity(paramIntent);
        return;
        paramIntent.putExtra("url", paramString2);
        try
        {
          paramIntent.putExtra("postPar", ((String)localObject).getBytes(paramString1));
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          localUnsupportedEncodingException2.printStackTrace();
        }
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException1)
    {
      break label34;
    }
  }

  public void goWebPay(boolean paramBoolean1, String paramString1, String paramString2, boolean paramBoolean2, List<NameValue> paramList, HashMap<String, String> paramHashMap, String paramString3)
  {
    goWebPay(paramBoolean1, paramString1, paramString2, paramBoolean2, paramList, paramHashMap, new Intent(this, PayWEBActivity.class), paramString3);
  }

  public void init()
  {
    super.init();
    if (checkNeedLaunch())
      return;
    this.tableView = ((SYTableView)findViewById(2131296733));
    this.tableView.setUpItems(createList(), 2130903088, 2130837879, 2130837871, 2130837862, 2130837862);
    this.order = ((OrderResult)this.app.getParms("OrderResult"));
    initFinishBackBtn();
  }

  public void onTableItemChanged(TableItem paramTableItem)
  {
  }

  public void onTableItemClick(TableItem paramTableItem)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Referer", "https://unionpaysecure.com/bank/bank");
    if (this.zhaoshangshouji == paramTableItem)
      startActivity(new Intent(this, PayCMBActivity.class));
    do
    {
      return;
      if (this.mobilePay == paramTableItem)
      {
        mobilePay();
        return;
      }
      if (this.zhaoshangHtml5 == paramTableItem)
      {
        cmbWebPay();
        return;
      }
      if (this.zfbpay == paramTableItem)
      {
        zfbWebPay();
        return;
      }
      if (this.zfbClientPay == paramTableItem)
      {
        zfbClientPay();
        return;
      }
      if (this.jianhang == paramTableItem)
      {
        ccbWebPay();
        return;
      }
      if (this.nonghang == paramTableItem)
      {
        abcWebPay();
        return;
      }
      if (this.web_12306 == paramTableItem)
      {
        webPay();
        return;
      }
      if (this.jiaotong == paramTableItem)
      {
        unionPay("05", false, "utf8", false, localHashMap);
        return;
      }
      if (this.guangda == paramTableItem)
      {
        localHashMap.put("Accept-Language", "zh-CN");
        localHashMap.put("Accept-Charset", "GBK");
        unionPay("10", false, "GBK", true, localHashMap);
        return;
      }
      if (this.xingye == paramTableItem)
      {
        unionPay("17", false, "utf8", true, localHashMap);
        return;
      }
      if (this.pufa == paramTableItem)
      {
        unionPay("12", false, "utf8", true, localHashMap);
        return;
      }
      if (this.beijing != paramTableItem)
        continue;
      unionPay("13", false, "utf8", false, localHashMap);
      return;
    }
    while (this.pingan != paramTableItem);
    unionPay("16", false, "utf8", false, localHashMap);
  }

  public void unionPay(String paramString1, boolean paramBoolean1, String paramString2, boolean paramBoolean2, HashMap<String, String> paramHashMap)
  {
    new MyAsyncTask(this, true, paramString1, paramBoolean1, paramString2, paramBoolean2, paramHashMap)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().unionPay(PayListActivity.this.order, this.val$bankCode);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        List localList = (List)paramContext.get("epay_bank_value");
        String str = paramContext.getStr("epay_bank_action");
        PayListActivity.this.goWebPay(this.val$toBrowser, this.val$charset, str, this.val$isGet, localList, this.val$headers);
      }
    }
    .execute(new Object[0]);
  }

  public void webPay()
  {
    new MyAsyncTask(this, true)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().webPay(PayListActivity.this.order);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        String str = (String)paramContext.get("epay_action");
        NameValueList localNameValueList = (NameValueList)paramContext.get("epay_parms");
        if (localNameValueList == null)
        {
          PayListActivity.this.showToast("支付失败");
          return;
        }
        PayListActivity.this.goWebPay(true, "utf8", str, false, localNameValueList, null);
      }
    }
    .execute(new Object[0]);
  }

  public void zfbClientPay()
  {
    if (!hasInstallAliPay())
    {
      showToast("您还没有安装支付宝客户端！", 1);
      return;
    }
    new MyAsyncTask(this, true)
    {
      protected String myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().zfbClientPayUrl(PayListActivity.this.order);
      }

      protected void myPostExecute(String paramString)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        PayListActivity.this.startActivity(localIntent);
      }
    }
    .execute(new Object[0]);
  }

  public void zfbWebPay()
  {
    new MyAsyncTask(this, true)
    {
      protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        return PayListActivity.this.getHc().zfbWebPayPre(PayListActivity.this.order);
      }

      protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
      {
        String str = paramContext.getStr("zfbAction");
        List localList = (List)paramContext.get("zfbForm_values");
        HashMap localHashMap = new HashMap();
        localHashMap.put("Referer", "https://epay.12306.cn/pay/webBusiness");
        Intent localIntent = new Intent(PayListActivity.this, PayZFBWEBActivity.class);
        PayListActivity.this.set12306Cookie(localIntent);
        PayListActivity.this.goWebPay(false, "utf8", str, false, localList, localHashMap, localIntent, "支付宝支付");
      }
    }
    .execute(new Object[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PayListActivity
 * JD-Core Version:    0.6.0
 */