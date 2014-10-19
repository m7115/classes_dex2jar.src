package com.yipiao.bean;

import java.io.Serializable;

public class UserInfo
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 1L;
  private String ID;
  private String bornDate = "";
  private String cardType = "1";
  private String email = "";
  private String idMode = "N";
  private String[] likeSeatTypes = { "1", "3", "O" };
  private String name;
  private String phone = "";
  private String preferenceCardNo = "";
  private String preferenceFromNo = null;
  private String preferenceToNo = null;
  private String seatDetail = "0";
  private String seatDetailSelect = "0";
  private String seatType = "1";
  private String sex = "M";
  private String stuDepartment = "";
  private String stuEnterYear = "2014";
  private String stuNo = "";
  private String stuProvinceCode = "11";
  private String stuSchoolClass = "";
  private String stuSchoolCode = "10001";
  private String stuSchoolSystem = "4";
  private String tickType = "1";
  private String userId = "";
  private String userStatus = "0";

  private boolean isBlank(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public UserInfo clone()
  {
    try
    {
      UserInfo localUserInfo = (UserInfo)super.clone();
      return localUserInfo;
    }
    catch (Exception localException)
    {
    }
    return this;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof UserInfo))
    {
      UserInfo localUserInfo = (UserInfo)paramObject;
      if ((this.name != null) && (this.name.equals(localUserInfo.getName())) && (this.ID != null) && (this.ID.equalsIgnoreCase(localUserInfo.getCardId())))
        return true;
    }
    return false;
  }

  public String getBornDate()
  {
    return this.bornDate;
  }

  public String getCardId()
  {
    return this.ID;
  }

  public String getCardType()
  {
    return this.cardType;
  }

  public String getEmail()
  {
    return this.email;
  }

  public String getID()
  {
    return this.ID;
  }

  public String getIdMode()
  {
    return this.idMode;
  }

  public String[] getLikeSeatTypes()
  {
    return this.likeSeatTypes;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public String getPreferenceCardNo()
  {
    return this.preferenceCardNo;
  }

  public String getPreferenceFromNo()
  {
    return this.preferenceFromNo;
  }

  public String getPreferenceToNo()
  {
    return this.preferenceToNo;
  }

  public String getSeatDetail()
  {
    return this.seatDetail;
  }

  public String getSeatDetailSelect()
  {
    return this.seatDetailSelect;
  }

  public String getSeatType()
  {
    return this.seatType;
  }

  public String getSex()
  {
    return this.sex;
  }

  public String getSimpleText()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.name).append(",").append(this.cardType).append(",").append(this.ID).append(",").append(this.phone);
    return localStringBuilder.toString();
  }

  public String getStuDepartment()
  {
    return this.stuDepartment;
  }

  public String getStuEnterYear()
  {
    return this.stuEnterYear;
  }

  public String getStuNo()
  {
    return this.stuNo;
  }

  public String getStuProvinceCode()
  {
    return this.stuProvinceCode;
  }

  public String getStuSchoolClass()
  {
    return this.stuSchoolClass;
  }

  public String getStuSchoolCode()
  {
    return this.stuSchoolCode;
  }

  public String getStuSchoolSystem()
  {
    return this.stuSchoolSystem;
  }

  public String getText()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.seatType).append(",").append(this.seatDetail).append(",").append(this.tickType).append(",").append(getSimpleText()).append(",N");
    return localStringBuilder.toString();
  }

  public String getTickType()
  {
    return this.tickType;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public String getUserStatus()
  {
    return this.userStatus;
  }

  public void iniWithOther(UserInfo paramUserInfo)
  {
    this.ID = paramUserInfo.ID;
    this.name = paramUserInfo.name;
    this.phone = paramUserInfo.phone;
    this.sex = paramUserInfo.sex;
    this.likeSeatTypes = paramUserInfo.likeSeatTypes;
    this.tickType = paramUserInfo.tickType;
    this.cardType = paramUserInfo.cardType;
    this.idMode = paramUserInfo.idMode;
    this.userId = paramUserInfo.userId;
    this.stuProvinceCode = paramUserInfo.stuProvinceCode;
    this.stuSchoolCode = paramUserInfo.stuSchoolCode;
    this.stuNo = paramUserInfo.stuNo;
    this.stuSchoolSystem = paramUserInfo.stuSchoolSystem;
    this.stuEnterYear = paramUserInfo.stuEnterYear;
    this.email = paramUserInfo.email;
    this.stuDepartment = paramUserInfo.stuDepartment;
    this.stuSchoolClass = paramUserInfo.stuSchoolClass;
    this.bornDate = paramUserInfo.bornDate;
    this.preferenceCardNo = paramUserInfo.preferenceCardNo;
    this.preferenceFromNo = paramUserInfo.preferenceFromNo;
    this.preferenceToNo = paramUserInfo.preferenceToNo;
    this.userStatus = paramUserInfo.userStatus;
  }

  public void setBornDate(String paramString)
  {
    this.bornDate = paramString;
  }

  public void setCardId(String paramString)
  {
    this.ID = paramString;
  }

  public void setCardType(String paramString)
  {
    this.cardType = paramString;
  }

  public void setEmail(String paramString)
  {
    this.email = paramString;
  }

  public void setID(String paramString)
  {
    this.ID = paramString;
  }

  public void setIdMode(String paramString)
  {
    this.idMode = paramString;
  }

  public void setLikeSeatTypes(String[] paramArrayOfString)
  {
    this.likeSeatTypes = paramArrayOfString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPhone(String paramString)
  {
    this.phone = paramString;
  }

  public void setPreferenceCardNo(String paramString)
  {
    this.preferenceCardNo = paramString;
  }

  public void setPreferenceFromNo(String paramString)
  {
    this.preferenceFromNo = paramString;
  }

  public void setPreferenceToNo(String paramString)
  {
    this.preferenceToNo = paramString;
  }

  public void setSeatDetail(String paramString)
  {
    this.seatDetail = paramString;
  }

  public void setSeatDetailSelect(String paramString)
  {
    this.seatDetailSelect = paramString;
  }

  public void setSeatType(String paramString)
  {
    this.seatType = paramString;
  }

  public void setSex(String paramString)
  {
    this.sex = paramString;
  }

  public void setStuDepartment(String paramString)
  {
    this.stuDepartment = paramString;
  }

  public void setStuEnterYear(String paramString)
  {
    this.stuEnterYear = paramString;
  }

  public void setStuNo(String paramString)
  {
    this.stuNo = paramString;
  }

  public void setStuProvinceCode(String paramString)
  {
    this.stuProvinceCode = paramString;
  }

  public void setStuSchoolClass(String paramString)
  {
    this.stuSchoolClass = paramString;
  }

  public void setStuSchoolCode(String paramString)
  {
    this.stuSchoolCode = paramString;
  }

  public void setStuSchoolSystem(String paramString)
  {
    this.stuSchoolSystem = paramString;
  }

  public void setTickType(String paramString)
  {
    this.tickType = paramString;
  }

  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }

  public void setUserStatus(String paramString)
  {
    this.userStatus = paramString;
  }

  public int stuInfoLevel()
  {
    boolean bool = "3".equals(this.tickType);
    int i = 0;
    if (bool)
    {
      i = 1;
      if (!isBlank(this.stuProvinceCode))
      {
        i = 2;
        if (!isBlank(this.stuSchoolCode))
        {
          i = 3;
          if (!isBlank(this.stuNo))
          {
            i = 4;
            if (!isBlank(this.preferenceFromNo))
            {
              i = 5;
              if (!isBlank(this.preferenceToNo))
              {
                i = 6;
                if (!isBlank(this.stuSchoolSystem))
                {
                  i = 7;
                  if (!isBlank(this.stuEnterYear))
                  {
                    i = 8;
                    if (!isBlank(this.preferenceCardNo))
                      i = 9;
                  }
                }
              }
            }
          }
        }
      }
    }
    return i;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UserInfo [ID=").append(this.ID).append(", name=").append(this.name).append(", phone=").append(this.phone).append(", rangDate=").append(", startDate=").append(", seatType=").append(this.seatType).append(", tickType=").append(this.tickType).append(", cardType=").append(this.cardType).append(", idMode=").append(this.idMode).append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.UserInfo
 * JD-Core Version:    0.6.0
 */