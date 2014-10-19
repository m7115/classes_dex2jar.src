package com.yipiao.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.suanya.common.bean.NameValueList;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.OrderResult;
import com.yipiao.service.Huoche;

public class MyWebActivity extends PayWEBActivity
{
  private OrderResult order;
  private TextView tvTip;

  private void initBottomView()
  {
    this.tvTip = ((TextView)findViewById(2131296701));
    this.tvTip.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        new MyAsyncTask(MyWebActivity.this, true)
        {
          protected cn.suanya.rule.bean.Context myInBackground(Object[] paramArrayOfObject)
            throws Exception
          {
            return MyWebActivity.this.getHc().webPay(MyWebActivity.this.order);
          }

          protected void myPostExecute(cn.suanya.rule.bean.Context paramContext)
          {
            String str = paramContext.getStr("action");
            NameValueList localNameValueList = (NameValueList)paramContext.get("values");
            Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str + '?' + localNameValueList.urlEncode()));
            MyWebActivity.this.startActivity(Intent.createChooser(localIntent, "选择浏览器"));
          }
        }
        .execute(new Object[0]);
      }
    });
  }

  protected int getMainLayout()
  {
    return 2130903058;
  }

  public void init()
  {
    super.init();
    initBottomView();
    this.order = ((OrderResult)this.app.getParms("order"));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.MyWebActivity
 * JD-Core Version:    0.6.0
 */