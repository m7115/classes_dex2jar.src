package com.yipiao.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CheckSettingItem extends LinearLayout
{
  public static final int TYPE_FIRST = 0;
  public static final int TYPE_LAST = 2;
  public static final int TYPE_MIDDLE = 1;
  public static final int TYPE_SINGLE = 3;
  private boolean enable;
  private String itemId;
  private ImageView ivCheck;
  private TextView tvSubTitle;
  private TextView tvTitle;
  private int type;

  public CheckSettingItem(Context paramContext)
  {
    super(paramContext);
    LayoutInflater.from(paramContext).inflate(2130903069, this);
    setType(1);
    this.tvTitle = ((TextView)findViewById(2131296788));
    this.tvSubTitle = ((TextView)findViewById(2131296789));
    this.ivCheck = ((ImageView)findViewById(2131296790));
    this.itemId = "0";
    this.enable = false;
  }

  public CheckSettingItem(Context paramContext, int paramInt, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2)
  {
    this(paramContext);
    setType(paramInt);
    setTitle(paramString1);
    setSubTitle(paramString2);
    setChecked(paramBoolean1);
    setItemId(paramString3);
    setEnable(paramBoolean2);
    try
    {
      setId(Integer.parseInt(paramString3));
      return;
    }
    catch (Exception localException)
    {
      setId(0);
    }
  }

  public CheckSettingItem(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this(paramContext, 1, paramString1, paramString2, false, paramString3, paramBoolean);
  }

  public CheckSettingItem(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2)
  {
    this(paramContext, 1, paramString1, paramString2, paramBoolean1, paramString3, paramBoolean2);
  }

  public String getItemId()
  {
    return this.itemId;
  }

  public String getSubTitle()
  {
    return this.tvSubTitle.getText().toString();
  }

  public String getTitle()
  {
    return this.tvTitle.getText().toString();
  }

  public int getType()
  {
    return this.type;
  }

  public boolean isEnable()
  {
    return this.enable;
  }

  public void setChecked(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.ivCheck.setImageResource(2130837804);
      return;
    }
    this.ivCheck.setImageResource(2130837805);
  }

  public void setEnable(boolean paramBoolean)
  {
    this.enable = paramBoolean;
  }

  public void setItemId(String paramString)
  {
    this.itemId = paramString;
  }

  public void setSubTitle(String paramString)
  {
    this.tvSubTitle.setText(paramString);
  }

  public void setTitle(String paramString)
  {
    this.tvTitle.setText(paramString);
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
    switch (paramInt)
    {
    default:
      return;
    case 0:
      setBackgroundResource(2130837875);
      return;
    case 1:
      setBackgroundResource(2130837863);
      return;
    case 2:
      setBackgroundResource(2130837863);
      return;
    case 3:
    }
    setBackgroundResource(2130837880);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.CheckSettingItem
 * JD-Core Version:    0.6.0
 */