package com.yipiao.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import cn.suanya.common.a.i;
import cn.suanya.common.a.n;
import com.google.gson.reflect.TypeToken;
import com.yipiao.YipiaoApplication;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.UserInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PassengerService
{
  private static PassengerService passengerService;
  private List<UserInfo> currUsers = new ArrayList();
  private List<UserInfo> historyUsers = new ArrayList();
  private SharedPreferences sp;

  public static PassengerService getInstance()
  {
    return getInstance(YipiaoApplication.getApp().sp);
  }

  public static PassengerService getInstance(SharedPreferences paramSharedPreferences)
  {
    if (passengerService == null)
    {
      passengerService = new PassengerService();
      passengerService.sp = paramSharedPreferences;
      String str1 = paramSharedPreferences.getString("currUsers", null);
      if (str1 != null)
        passengerService.currUsers = ((List)i.a(str1, new TypeToken()
        {
        }
        .getType()));
      String str2 = paramSharedPreferences.getString("historyUsers", null);
      if (str2 != null)
        passengerService.historyUsers = ((List)i.a(str2, new TypeToken()
        {
        }
        .getType()));
    }
    return passengerService;
  }

  private int indexInListForUserWithType(UserInfo paramUserInfo, List<UserInfo> paramList)
  {
    for (int i = 0; i < paramList.size(); i++)
    {
      UserInfo localUserInfo = (UserInfo)paramList.get(i);
      if ((paramUserInfo.equals(localUserInfo)) && (paramUserInfo.getTickType().equals(localUserInfo.getTickType())))
        return i;
    }
    return -1;
  }

  private int syncHistory(UserInfo paramUserInfo, boolean paramBoolean)
  {
    int i = this.historyUsers.indexOf(paramUserInfo);
    if (i < 0)
    {
      if (!paramBoolean)
      {
        this.historyUsers.add(0, paramUserInfo);
        if (this.historyUsers.size() > 160)
          this.historyUsers.remove(-1 + this.historyUsers.size());
        return 1;
      }
    }
    else
    {
      UserInfo localUserInfo = (UserInfo)this.historyUsers.get(i);
      localUserInfo.setUserStatus(paramUserInfo.getUserStatus());
      localUserInfo.setTickType(paramUserInfo.getTickType());
    }
    return 0;
  }

  private void updateCurrUser(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
  {
    if (updateUserInUserList(paramUserInfo1, paramUserInfo2, this.currUsers))
      setCurrUsers(this.currUsers);
  }

  private void updateHistory(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
  {
    int i = indexInListForUserWithType(paramUserInfo2, this.historyUsers);
    if (i < 0)
      i = this.historyUsers.indexOf(paramUserInfo1);
    if (i >= 0)
    {
      this.historyUsers.remove(i);
      this.historyUsers.add(i, paramUserInfo1);
    }
    while (true)
    {
      String str = i.a(this.historyUsers);
      SharedPreferences.Editor localEditor = this.sp.edit();
      localEditor.putString("historyUsers", str);
      localEditor.commit();
      return;
      this.historyUsers.add(0, paramUserInfo1);
    }
  }

  private void updateMonitor(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
  {
    YipiaoApplication localYipiaoApplication = YipiaoApplication.getApp();
    Iterator localIterator = localYipiaoApplication.getMonitorPool().iterator();
    int i = 0;
    if (localIterator.hasNext())
      if (!updateUserInUserList(paramUserInfo1, paramUserInfo2, ((MonitorInfo)localIterator.next()).getPassengers()))
        break label70;
    label70: for (int j = 1; ; j = i)
    {
      i = j;
      break;
      if (i != 0)
        localYipiaoApplication.saveMonitorPool();
      return;
    }
  }

  private boolean updateUserInUserList(UserInfo paramUserInfo1, UserInfo paramUserInfo2, List<UserInfo> paramList)
  {
    if (paramList == null);
    int i;
    do
    {
      return false;
      i = indexInListForUserWithType(paramUserInfo2, paramList);
    }
    while (i < 0);
    ((UserInfo)paramList.get(i)).iniWithOther(paramUserInfo1);
    return true;
  }

  public List<UserInfo> getCurrUsers()
  {
    return this.currUsers;
  }

  public List<UserInfo> getHistoryUsers()
  {
    return this.historyUsers;
  }

  public void removeHistory(UserInfo paramUserInfo)
  {
    int i = this.historyUsers.indexOf(paramUserInfo);
    if (i >= 0)
    {
      this.historyUsers.remove(i);
      syncRemoveUser(paramUserInfo);
      String str = i.a(this.historyUsers);
      SharedPreferences.Editor localEditor = this.sp.edit();
      localEditor.putString("historyUsers", str);
      localEditor.commit();
    }
  }

  public void setCurrUsers(List<UserInfo> paramList)
  {
    this.currUsers = paramList;
    String str = i.a(paramList);
    SharedPreferences.Editor localEditor = this.sp.edit();
    localEditor.putString("currUsers", str);
    localEditor.commit();
  }

  public void setHistoryUsers(List<UserInfo> paramList)
  {
    this.historyUsers = paramList;
  }

  public void setLikedSeatType(UserInfo paramUserInfo, String paramString)
  {
    if ((paramString == null) || ("0".endsWith(paramString)) || (paramString.length() == 0))
      return;
    String[] arrayOfString1 = paramUserInfo.getLikeSeatTypes();
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    for (int i = 0; i < arrayOfString1.length; i++)
    {
      String str = arrayOfString1[i];
      if (paramString.equals(str))
        continue;
      localArrayList.add(str);
    }
    if (localArrayList.size() < 3)
      localArrayList.add(paramString);
    if (localArrayList.size() < 3)
      localArrayList.add(paramString);
    String[] arrayOfString2 = new String[localArrayList.size()];
    localArrayList.toArray(arrayOfString2);
    paramUserInfo.setLikeSeatTypes(arrayOfString2);
    updatePassenger(paramUserInfo, paramUserInfo);
  }

  public String[] showHistoryUsers()
  {
    String[] arrayOfString = new String[this.historyUsers.size()];
    Iterator localIterator = this.historyUsers.iterator();
    for (int i = 0; localIterator.hasNext(); i++)
      arrayOfString[i] = ((UserInfo)localIterator.next()).getName();
    return arrayOfString;
  }

  public int syncHistory(List<UserInfo> paramList, boolean paramBoolean)
  {
    if (paramList == null)
      return 0;
    int i = -1 + paramList.size();
    int j = 0;
    while (i >= 0)
    {
      j += syncHistory((UserInfo)paramList.get(i), paramBoolean);
      i--;
    }
    String str = i.a(this.historyUsers);
    SharedPreferences.Editor localEditor = this.sp.edit();
    localEditor.putString("historyUsers", str);
    localEditor.commit();
    syncUserDetail(paramList);
    return j;
  }

  public void syncRemoveUser(UserInfo paramUserInfo)
  {
    new Thread(paramUserInfo)
    {
      public void run()
      {
        try
        {
          YipiaoApplication.getApp().getHC().deletePassenger(this.val$u);
          return;
        }
        catch (Exception localException)
        {
          n.a(localException);
        }
      }
    }
    .start();
  }

  public void syncUserDetail(UserInfo paramUserInfo)
  {
    int i = this.historyUsers.indexOf(paramUserInfo);
    if (i >= 0)
    {
      UserInfo localUserInfo = (UserInfo)this.historyUsers.get(i);
      if (("3".equals(localUserInfo.getTickType())) && ("3".equals(localUserInfo.getTickType())) && (paramUserInfo.stuInfoLevel() > localUserInfo.stuInfoLevel()))
      {
        localUserInfo.setStuProvinceCode(paramUserInfo.getStuProvinceCode());
        localUserInfo.setStuSchoolCode(paramUserInfo.getStuSchoolCode());
        localUserInfo.setStuDepartment(paramUserInfo.getStuDepartment());
        localUserInfo.setStuSchoolSystem(paramUserInfo.getStuSchoolSystem());
        localUserInfo.setStuSchoolClass(paramUserInfo.getStuSchoolClass());
        localUserInfo.setStuEnterYear(paramUserInfo.getStuEnterYear());
        localUserInfo.setPreferenceCardNo(paramUserInfo.getPreferenceCardNo());
        localUserInfo.setPreferenceFromNo(paramUserInfo.getPreferenceFromNo());
        localUserInfo.setPreferenceToNo(paramUserInfo.getPreferenceToNo());
        localUserInfo.setPreferenceCardNo(paramUserInfo.getPreferenceCardNo());
        localUserInfo.setStuNo(paramUserInfo.getStuNo());
      }
    }
    String str = i.a(this.historyUsers);
    SharedPreferences.Editor localEditor = this.sp.edit();
    localEditor.putString("historyUsers", str);
    localEditor.commit();
  }

  public void syncUserDetail(List<UserInfo> paramList)
  {
    new Thread(paramList)
    {
      public void run()
      {
        try
        {
          HuocheBase localHuocheBase = YipiaoApplication.getApp().getHC();
          Iterator localIterator = this.val$userList.iterator();
          while (localIterator.hasNext())
          {
            UserInfo localUserInfo1 = (UserInfo)localIterator.next();
            if (!"3".equals(localUserInfo1.getTickType()))
              continue;
            int i = PassengerService.this.historyUsers.indexOf(localUserInfo1);
            if ((i < 0) || (((UserInfo)PassengerService.this.historyUsers.get(i)).stuInfoLevel() >= 6))
              continue;
            UserInfo localUserInfo2 = localHuocheBase.getPassengerDetail(localUserInfo1);
            PassengerService.this.syncUserDetail(localUserInfo2);
          }
        }
        catch (Exception localException)
        {
          n.a(localException);
        }
      }
    }
    .start();
  }

  public void updatePassenger(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
  {
    updateHistory(paramUserInfo1, paramUserInfo2);
    updateCurrUser(paramUserInfo1, paramUserInfo2);
    updateMonitor(paramUserInfo1, paramUserInfo2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.PassengerService
 * JD-Core Version:    0.6.0
 */