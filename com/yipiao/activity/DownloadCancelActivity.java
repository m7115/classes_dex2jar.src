package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import com.yipiao.base.DialogBaseActivity;
import com.yipiao.service.DownloadService;

public class DownloadCancelActivity extends DialogBaseActivity
{
  private int id;
  private String name;

  protected int getMainLayout()
  {
    return 2130903049;
  }

  public void init()
  {
    super.init();
    this.name = getIntent().getStringExtra("name");
    this.id = getIntent().getIntExtra("id", 0);
    if ((this.name == null) || (this.name.equals("")) || (this.id == 0))
    {
      finish();
      return;
    }
    setTv(2131296703, "正在下载 " + this.name);
    setClick(2131296704, this);
    setClick(2131296705, this);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131296704:
    case 2131296705:
    }
    while (true)
    {
      super.onClick(paramView);
      return;
      finish();
      continue;
      DownloadService localDownloadService = DownloadService.getInstance();
      if (localDownloadService != null)
        localDownloadService.cancelDownload(this.id);
      finish();
    }
  }

  protected void onPause()
  {
    super.onPause();
    finish();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.DownloadCancelActivity
 * JD-Core Version:    0.6.0
 */