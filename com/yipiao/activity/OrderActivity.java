package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.LoginUser;
import com.yipiao.bean.OrderResult;
import com.yipiao.bean.SysUserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.HuocheBase;
import java.util.List;
import org.json.JSONObject;

public class OrderActivity extends BaseActivity
{
  MyAsyncTask<Object, OrderResult> queryTask;
  private ImageView userHead;
  private TextView userName;
  private Button userSwitch;

  private void doSelector(int paramInt)
  {
    switch (paramInt)
    {
    case 2131296926:
    case 2131296927:
    default:
      return;
    case 2131296925:
      redirectUserInfo();
      return;
    case 2131296928:
    }
    redirectModifyPwd();
  }

  private void initMyInfo()
  {
    this.userHead = ((ImageView)findViewById(2131296923));
    this.userName = ((TextView)findViewById(2131296924));
    this.userSwitch = ((Button)findViewById(2131296926));
    setClick(2131296925, this);
    setClick(2131296927, this);
    setClick(2131296926, this);
    setClick(2131296928, this);
    setBackHeight();
  }

  private boolean islogined()
  {
    if (this.app.isVisitor());
    while (true)
    {
      return false;
      if (this.app.isLogined())
        return true;
      if (this.app.getUser().getUserName() == null)
        continue;
      try
      {
        if (this.app.isMobileApi())
        {
          if (!this.app.getUser().canLogin())
            continue;
          getHc().login(this.app.getUser().getUserName(), this.app.getUser().getPassword(), "");
          return true;
        }
        boolean bool = getHc().isLogined();
        return bool;
      }
      catch (Exception localException)
      {
      }
    }
    return false;
  }

  private void queryUnfinish()
  {
    this.queryTask = new MyAsyncTask(this, false)
    {
      protected OrderResult myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        if (!OrderActivity.this.islogined())
          return null;
        return OrderActivity.this.getHc().uncompleteOrder();
      }

      protected void myPostExecute(OrderResult paramOrderResult)
      {
        if (paramOrderResult != null)
        {
          int i = paramOrderResult.getPayOrder().size();
          if (i > 0)
            OrderActivity.this.setTv(2131296750, String.valueOf(i));
        }
        else
        {
          return;
        }
        OrderActivity.this.setVisibility(2131296750, 4);
      }

      protected void onException(Exception paramException)
      {
      }
    };
    this.queryTask.execute(new Object[0]);
  }

  private void redirectModifyPwd()
  {
    startActivity(new Intent(this, ChangePwdActivity.class));
  }

  private void redirectPassenager()
  {
    startActivity(new Intent(this, PassengerHistoryActivity.class));
  }

  private void redirectUserInfo()
  {
    new MyAsyncTask(this, false)
    {
      protected SysUserInfo myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return OrderActivity.this.app.getHC().getCurrentUserDetail();
      }

      protected void myPostExecute(SysUserInfo paramSysUserInfo)
      {
        OrderActivity.this.app.putParms("passenger", paramSysUserInfo);
        Intent localIntent = new Intent(OrderActivity.this, UserInfoActivity.class);
        localIntent.putExtra("passenger", paramSysUserInfo);
        OrderActivity.this.startActivity(localIntent);
      }
    }
    .execute(new String[0]);
  }

  private void setBackHeight()
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131296922);
    int i = getResources().getDisplayMetrics().widthPixels;
    int j = localLinearLayout.getBackground().getIntrinsicHeight();
    int k = localLinearLayout.getBackground().getIntrinsicWidth();
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(i, j * i / k));
  }

  private void setInfo()
  {
    if (this.app.getUser().getUserName() != null)
    {
      this.userName.setText(this.app.getUser().getUserLabel());
      this.userHead.setImageDrawable(getResources().getDrawable(2130837931));
      this.userSwitch.setText("用户切换");
      return;
    }
    this.userName.setText("我的12306");
    this.userHead.setImageDrawable(getResources().getDrawable(2130837931));
    this.userSwitch.setText("用户登录");
  }

  protected int getMainLayout()
  {
    return 2130903115;
  }

  protected void goSceneryWEBActivity(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(this, AceneryWEBActivity.class);
    localIntent.putExtra("url", paramString1);
    localIntent.putExtra("webTip", this.app.launchInfo.optString("acenery_web_tip", "景点门票数据由同程网独家提供"));
    if (paramString2 != null)
      localIntent.putExtra("cookies", paramString2);
    if (paramArrayOfByte != null)
      localIntent.putExtra("postPar", paramArrayOfByte);
    startActivity(localIntent);
  }

  protected void init()
  {
    setClick(2131296749, this);
    setClick(2131296751, this);
    setClick(2131296753, this);
    setClick(2131296755, this);
    setClick(2131296259, this);
    setClick(2131296752, this);
    setClick(2131296754, this);
    findViewById(2131296755);
    initMyInfo();
    super.init();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      super.onClick(paramView);
      return;
    case 2131296749:
      checkForLogin(2131296749);
      return;
    case 2131296751:
      checkForLogin(2131296751);
      return;
    case 2131296755:
      logToServer("goScenery", "");
      goSceneryWEBActivity(this.app.launchInfo.optString("scenery.17u.cn", "http://m.ly.com/scenery/?refid=19221484"), "http://touch.17u.cn|userDownLoadCloseds=true;http://m.ly.com|userDownLoadCloseds=true", null);
      return;
    case 2131296753:
      goWebActivity(this.app.launchInfo.optString("hotel.ctrip", "http://m.ctrip.com/webapp/myctrip/"), null, null);
      return;
    case 2131296259:
      String str = Constants.getServiceUrl() + "/order_help.html?" + System.currentTimeMillis() + "&&cid=" + this.app.launchInfo.optLong("clientId");
      Intent localIntent = new Intent(this, CommonWebActivity.class);
      localIntent.putExtra("title", "订单帮助");
      localIntent.putExtra("url", str);
      startActivity(localIntent);
      return;
    case 2131296752:
      goWebActivity(this.app.launchInfo.optString("hotel.qnr", "http://touch.qunar.com/h5/flight/flightorderlist?jmp=1&bd_source=zhixing"), "http://touch.qunar.com|QN75=closed", null);
      return;
    case 2131296754:
      goWebActivity(this.app.launchInfo.optString("hotel.tongcheng", "http://touch.17u.cn/hotel/orderlist.html"), null, null);
      return;
    case 2131296925:
      checkForLogin(2131296925);
      return;
    case 2131296927:
      redirectPassenager();
      return;
    case 2131296926:
      loginOut();
      return;
    case 2131296928:
    }
    checkForLogin(2131296928);
  }

  protected void onLoginSuccess(int paramInt)
  {
    if (paramInt == 2131296749)
      startActivity(new Intent(this, OrderQueryActivity.class));
    if (paramInt == 2131296751)
      startActivity(new Intent(this, OrderHistoryActivity.class));
    doSelector(paramInt);
    super.onLoginSuccess(paramInt);
  }

  protected void onPause()
  {
    if (this.queryTask != null)
      this.queryTask.cancel(true);
    super.onPause();
  }

  protected void onResume()
  {
    setInfo();
    queryUnfinish();
    super.onResume();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.OrderActivity
 * JD-Core Version:    0.6.0
 */