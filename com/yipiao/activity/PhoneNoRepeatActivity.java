package com.yipiao.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.yipiao.Config;
import com.yipiao.Constants;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.InstallManager;
import com.yipiao.bean.Note;
import org.json.JSONObject;

public class PhoneNoRepeatActivity extends BaseActivity
{
  private Note areaNote;
  private String phone;

  private void showArea()
  {
    setTv(2131297015, this.areaNote.getName() + "(" + this.areaNote.getCode() + "95105105)");
  }

  public void call()
  {
    this.phone = (this.areaNote.getCode() + "95105105");
    try
    {
      Intent localIntent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.phone));
      localIntent.setFlags(268435456);
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      showToast("是否安装拨号程序");
    }
  }

  protected int getMainLayout()
  {
    return 2130903142;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 2131297014) && (paramInt2 != 0))
    {
      this.areaNote = ((Note)paramIntent.getExtras().get("DATA"));
      showArea();
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.areaNote = new Note("", "本市");
    showArea();
    setClick(2131297014, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        PhoneNoRepeatActivity.this.noteFilterDialog(2131297014, PhoneNoRepeatActivity.this.cfg.getAllAreaCodes());
      }
    });
    setClick(2131297018, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        PhoneNoRepeatActivity.this.call();
      }
    });
    setClick(2131297017, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        new InstallManager(PhoneNoRepeatActivity.this, PhoneNoRepeatActivity.this.app.launchInfo.optString(Constants.conf_downLoad_address, "")).start();
      }
    });
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PhoneNoRepeatActivity
 * JD-Core Version:    0.6.0
 */