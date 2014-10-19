package com.yipiao.base;

import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class DialogBaseActivity extends BaseActivity
{
  protected void init()
  {
    super.init();
    initWindow();
  }

  protected void initWindow()
  {
    View localView = findViewById(2131296702);
    WindowManager localWindowManager = (WindowManager)getSystemService("window");
    int i = localWindowManager.getDefaultDisplay().getWidth();
    int j = localWindowManager.getDefaultDisplay().getHeight();
    localView.setMinimumWidth((int)(0.8D * i));
    localView.setMinimumHeight((int)(0.2D * j));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.DialogBaseActivity
 * JD-Core Version:    0.6.0
 */