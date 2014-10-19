package com.yipiao.bean;

public class SysUserInfo extends UserInfo
{
  private static final long serialVersionUID = 1L;
  private String activeUser = "Y";
  private String ivrPasswd = "";
  private String password;
  private String pwdQuestion;

  public String getActiveUser()
  {
    return this.activeUser;
  }

  public String getIvrPasswd()
  {
    return this.ivrPasswd;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getPwdQuestion()
  {
    return this.pwdQuestion;
  }

  public void setActiveUser(String paramString)
  {
    this.activeUser = paramString;
  }

  public void setIvrPasswd(String paramString)
  {
    this.ivrPasswd = paramString;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setPwdQuestion(String paramString)
  {
    this.pwdQuestion = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.SysUserInfo
 * JD-Core Version:    0.6.0
 */