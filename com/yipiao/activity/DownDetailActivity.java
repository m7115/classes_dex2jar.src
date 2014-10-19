package com.yipiao.activity;

import android.content.Intent;
import android.view.View;
import cn.suanya.common.a.n;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.DialogBaseActivity;
import com.yipiao.bean.RecommendItem;

public class DownDetailActivity extends DialogBaseActivity
{
  RecommendItem item;

  private void download()
  {
    try
    {
      int j = Integer.parseInt(this.item.getPid());
      i = j;
      String str1 = getIntent().getScheme() + ":";
      String str2 = this.item.getIntent();
      if ((str1 != null) && (!str1.equals("")))
        str2 = str2.replaceFirst(str1, "");
      n.b("download --> id:" + i + ",name:" + this.item.getLabel() + ", url:" + str2);
      downloadApk(this, i, str2, this.item.getLabel());
      return;
    }
    catch (Exception localException)
    {
      while (true)
        int i = this.item.hashCode();
    }
  }

  private void initView()
  {
    if ((this.app.getParms("RecommendItem") instanceof RecommendItem))
      this.item = ((RecommendItem)this.app.getParms("RecommendItem"));
    if (this.item == null)
    {
      showToast("出错，请稍候重试");
      return;
    }
    setTv(2131296459, this.item.getLabel());
    setTv(2131296703, this.item.getDetail());
  }

  protected int getMainLayout()
  {
    return 2130903050;
  }

  protected void init()
  {
    super.init();
    setClick(2131296706, this);
    n.b(getIntent().toString());
    initView();
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131296706)
    {
      download();
      finish();
    }
    super.onClick(paramView);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.DownDetailActivity
 * JD-Core Version:    0.6.0
 */