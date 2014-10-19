package com.yipiao.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class OrderTabActivity extends TabActivity
  implements View.OnClickListener
{
  public static int currentTab = 2131296962;
  private RadioButton hotelorder;
  private RadioButton oldOrde;
  private TabHost tabHost;
  private RadioButton unOrder;

  public static void orderQueryStatus(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    currentTab = paramInt;
    OrderQueryActivity.refreshOrder = paramBoolean1;
    OrderHistoryActivity.refreshOrder = paramBoolean2;
  }

  public void onClick(View paramView)
  {
    this.unOrder.setChecked(false);
    this.oldOrde.setChecked(false);
    this.hotelorder.setChecked(false);
    ((RadioButton)paramView).setChecked(true);
    currentTab = paramView.getId();
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296962:
      this.tabHost.setCurrentTab(0);
      return;
    case 2131296963:
      this.tabHost.setCurrentTab(1);
      return;
    case 2131296964:
    }
    this.tabHost.setCurrentTab(2);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903125);
    this.unOrder = ((RadioButton)findViewById(2131296962));
    this.unOrder.setOnClickListener(this);
    this.oldOrde = ((RadioButton)findViewById(2131296963));
    this.oldOrde.setOnClickListener(this);
    this.hotelorder = ((RadioButton)findViewById(2131296964));
    this.hotelorder.setOnClickListener(this);
    this.tabHost = getTabHost();
    TabHost.TabSpec localTabSpec1 = this.tabHost.newTabSpec("unOrder").setIndicator("unOrder").setContent(new Intent(this, OrderQueryActivity.class));
    TabHost.TabSpec localTabSpec2 = this.tabHost.newTabSpec("oldOrde").setIndicator("oldOrde").setContent(new Intent(this, OrderHistoryActivity.class));
    TabHost.TabSpec localTabSpec3 = this.tabHost.newTabSpec("hotelorder").setIndicator("hotelorder").setContent(new Intent(this, OrderQueryActivity.class));
    this.tabHost.addTab(localTabSpec1);
    this.tabHost.addTab(localTabSpec2);
    this.tabHost.addTab(localTabSpec3);
    currentTab = 2131296962;
  }

  protected void onResume()
  {
    onClick(findViewById(currentTab));
    super.onResume();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.OrderTabActivity
 * JD-Core Version:    0.6.0
 */