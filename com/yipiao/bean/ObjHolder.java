package com.yipiao.bean;

public class ObjHolder
{
  private Object obj;
  private long time;

  public ObjHolder(Object paramObject, long paramLong)
  {
    this.obj = paramObject;
    this.time = (paramLong + System.currentTimeMillis());
  }

  public Object getObj()
  {
    return this.obj;
  }

  public long getTime()
  {
    return this.time;
  }

  public boolean hasOutTime()
  {
    return System.currentTimeMillis() > this.time;
  }

  public void setObj(Object paramObject)
  {
    this.obj = paramObject;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.ObjHolder
 * JD-Core Version:    0.6.0
 */