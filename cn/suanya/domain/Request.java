package cn.suanya.domain;

import java.io.Serializable;

public class Request<T>
  implements Serializable
{
  private static final long serialVersionUID = -8830309415262707035L;
  private long clientId;
  private String clientVersion;
  private int configVersion;
  private T data;
  private String emei;
  private String ruleVersion;

  public Request()
  {
  }

  public Request(T paramT)
  {
    this();
    this.data = paramT;
  }

  public long getClientId()
  {
    return this.clientId;
  }

  public String getClientVersion()
  {
    return this.clientVersion;
  }

  public int getConfigVersion()
  {
    return this.configVersion;
  }

  public T getData()
  {
    return this.data;
  }

  public String getEmei()
  {
    return this.emei;
  }

  public String getRuleVersion()
  {
    return this.ruleVersion;
  }

  public void setClientId(long paramLong)
  {
    this.clientId = paramLong;
  }

  public void setClientVersion(String paramString)
  {
    this.clientVersion = paramString;
  }

  public void setConfigVersion(int paramInt)
  {
    this.configVersion = paramInt;
  }

  public void setData(T paramT)
  {
    this.data = paramT;
  }

  public void setEmei(String paramString)
  {
    this.emei = paramString;
  }

  public void setRuleVersion(String paramString)
  {
    this.ruleVersion = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.domain.Request
 * JD-Core Version:    0.6.0
 */