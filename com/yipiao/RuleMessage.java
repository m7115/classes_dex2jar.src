package com.yipiao;

public class RuleMessage
{
  public static Integer result_cancle;
  public static Integer result_confirm;
  public static String result_key;
  public static int type_confirm;
  public static int type_process;
  public static int type_show;
  public static int type_toast = 0;
  private String cancle;
  private String confirm;
  private String message;
  private String title;
  private int type;

  static
  {
    type_show = 1;
    type_confirm = 2;
    type_process = 3;
    result_confirm = Integer.valueOf(1);
    result_cancle = Integer.valueOf(0);
    result_key = "result_message";
  }

  public RuleMessage(int paramInt, String paramString)
  {
    this(paramInt, "", paramString);
  }

  public RuleMessage(int paramInt, String paramString1, String paramString2)
  {
    this(paramInt, paramString1, paramString2, "取消", "确定");
  }

  public RuleMessage(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.type = paramInt;
    this.title = paramString1;
    this.message = paramString2;
    this.cancle = paramString3;
    this.confirm = paramString4;
  }

  public String getCancle()
  {
    return this.cancle;
  }

  public String getConfirm()
  {
    return this.confirm;
  }

  public String getMessage()
  {
    return this.message;
  }

  public String getTitle()
  {
    return this.title;
  }

  public int getType()
  {
    return this.type;
  }

  public void setCancle(String paramString)
  {
    this.cancle = paramString;
  }

  public void setConfirm(String paramString)
  {
    this.confirm = paramString;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.RuleMessage
 * JD-Core Version:    0.6.0
 */