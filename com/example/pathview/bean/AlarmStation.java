package com.example.pathview.bean;

public class AlarmStation
{
  private String alarmTime;
  private String arriveTime;
  private String code;
  private int index;
  private String name;
  private boolean vibrate;
  private boolean voice;

  public AlarmStation(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.code = paramString1;
    this.index = paramInt;
    this.name = paramString2;
    this.arriveTime = paramString3;
    this.alarmTime = paramString4;
    this.voice = paramBoolean1;
    this.vibrate = paramBoolean2;
  }

  public String getAlarmTime()
  {
    return this.alarmTime;
  }

  public String getArriveTime()
  {
    return this.arriveTime;
  }

  public String getCode()
  {
    return this.code;
  }

  public int getIndex()
  {
    return this.index;
  }

  public String getName()
  {
    return this.name;
  }

  public boolean isVibrate()
  {
    return this.vibrate;
  }

  public boolean isVoice()
  {
    return this.voice;
  }

  public void setAlarmTime(String paramString)
  {
    this.alarmTime = paramString;
  }

  public void setArriveTime(String paramString)
  {
    this.arriveTime = paramString;
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setVibrate(boolean paramBoolean)
  {
    this.vibrate = paramBoolean;
  }

  public void setVoice(boolean paramBoolean)
  {
    this.voice = paramBoolean;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.bean.AlarmStation
 * JD-Core Version:    0.6.0
 */