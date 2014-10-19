package com.yipiao.bean;

public class LoginUser
{
  private String password;
  private String userLabel;
  private String userName;

  public LoginUser(String paramString1, String paramString2, String paramString3)
  {
    this.userName = paramString1;
    this.password = paramString2;
    this.userLabel = paramString3;
  }

  public boolean canLogin()
  {
    int i = 1;
    if ((this.password == null) || (this.password.length() < i))
      i = 0;
    do
      return i;
    while ((this.userName != null) && (this.userName.length() >= i) && (!this.userName.startsWith("zhixinghuoche")));
    return false;
  }

  public boolean equals(Object paramObject)
  {
    if ((!(paramObject instanceof LoginUser)) || (this.userName == null))
      return false;
    return this.userName.equals(((LoginUser)paramObject).getUserName());
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getUserLabel()
  {
    return this.userLabel;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setUserLabel(String paramString)
  {
    this.userLabel = paramString;
  }

  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.LoginUser
 * JD-Core Version:    0.6.0
 */