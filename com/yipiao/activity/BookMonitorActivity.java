package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.MonitorInfo;

public class BookMonitorActivity extends BookActivity
{
  private void goMonitorResult()
  {
    Intent localIntent = new Intent(this, MonitorSeatListActivity.class);
    MonitorInfo localMonitorInfo = (MonitorInfo)this.app.getParms("mi");
    if (localMonitorInfo != null)
    {
      localIntent.putExtra("mi", localMonitorInfo);
      localIntent.putExtra("mid", localMonitorInfo.getId());
      startActivity(localIntent);
    }
    finish();
  }

  public void init()
  {
    checkForLogin(2130903067, "请先登陆！");
    super.init();
  }

  protected void initLeftBtn()
  {
    Button localButton = (Button)findViewById(2131296261);
    if (localButton != null)
    {
      localButton.setText("监控结果");
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          BookMonitorActivity.this.goMonitorResult();
        }
      });
    }
  }

  protected void onLoginCanceled(int paramInt)
  {
    if (paramInt == 2130903067)
      finish();
    super.onLoginCanceled(paramInt);
  }

  protected void onLoginSuccess(int paramInt)
  {
    super.onLoginSuccess(paramInt);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.BookMonitorActivity
 * JD-Core Version:    0.6.0
 */