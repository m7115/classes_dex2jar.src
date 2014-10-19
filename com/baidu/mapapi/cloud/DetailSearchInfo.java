package com.baidu.mapapi.cloud;

public class DetailSearchInfo
{
  public String ak;
  public int id;
  public int scope = 1;
  public String sn;
  public int timeStamp = 0;

  String a()
  {
    if (this.id == 0)
      return null;
    StringBuilder localStringBuilder = new StringBuilder("/");
    localStringBuilder.append(this.id).append('?');
    localStringBuilder.append("scope=").append(this.scope);
    if (this.ak != null)
      localStringBuilder.append("&ak=").append(this.ak);
    if (this.sn != null)
    {
      localStringBuilder.append("&sn=").append(this.sn);
      localStringBuilder.append("&timestamp=").append(this.timeStamp);
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.cloud.DetailSearchInfo
 * JD-Core Version:    0.6.0
 */