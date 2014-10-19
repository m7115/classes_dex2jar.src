package com.yipiao.base;

import com.yipiao.Config;
import com.yipiao.bean.MonitorInfo;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.Ticket;
import com.yipiao.bean.UserInfo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class HCUtil
{
  public static boolean calPassengerSeat(List<Ticket> paramList, UserInfo paramUserInfo)
  {
    for (int i = 0; i < paramUserInfo.getLikeSeatTypes().length; i++)
    {
      String str = paramUserInfo.getLikeSeatTypes()[i];
      Note localNote2 = Config.getInstance().seatTypesAll.getByCode(str);
      if (hasTicket(localNote2, paramList) < 0)
        continue;
      paramUserInfo.setSeatType(localNote2.getCode());
      return true;
    }
    Iterator localIterator = Config.getInstance().seatTypesAll.iterator();
    while (localIterator.hasNext())
    {
      Note localNote1 = (Note)localIterator.next();
      if (hasTicket(localNote1, paramList) < 0)
        continue;
      paramUserInfo.setSeatType(localNote1.getCode());
      return false;
    }
    paramUserInfo.setSeatType(paramUserInfo.getLikeSeatTypes()[0]);
    return false;
  }

  public static boolean calPassengerSeat(List<Ticket> paramList, UserInfo paramUserInfo, MonitorInfo paramMonitorInfo)
  {
    NoteList localNoteList = paramMonitorInfo.getSeatTypes();
    for (int i = 0; i < paramUserInfo.getLikeSeatTypes().length; i++)
    {
      String str = paramUserInfo.getLikeSeatTypes()[i];
      if ((localNoteList.getByCode(str) == null) || (hasTicket(str, paramList) < 0))
        continue;
      paramUserInfo.setSeatType(str);
      return true;
    }
    Iterator localIterator = localNoteList.iterator();
    while (localIterator.hasNext())
    {
      Note localNote = (Note)localIterator.next();
      if (hasTicket(localNote, paramList) < 0)
        continue;
      paramUserInfo.setSeatType(localNote.getCode());
      return true;
    }
    paramUserInfo.setSeatType(paramUserInfo.getLikeSeatTypes()[0]);
    return false;
  }

  public static boolean calPassengerSeat(List<Ticket> paramList, List<UserInfo> paramList1)
  {
    Iterator localIterator = paramList1.iterator();
    while (localIterator.hasNext())
      if (!calPassengerSeat(paramList, (UserInfo)localIterator.next()))
        return false;
    return true;
  }

  public static String getCityName(String paramString)
  {
    if (paramString == null)
      paramString = null;
    while (true)
    {
      return paramString;
      if (paramString.length() <= 2)
        continue;
      String[] arrayOfString = { "东", "南", "西", "北" };
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
        if (paramString.endsWith(arrayOfString[j]))
          return paramString.substring(0, -1 + paramString.length());
    }
  }

  private static int hasTicket(Note paramNote, List<Ticket> paramList)
  {
    if ((paramNote == null) || (paramList == null))
    {
      i = -1;
      return i;
    }
    Iterator localIterator = paramList.iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        break label70;
      Ticket localTicket = (Ticket)localIterator.next();
      if ((paramNote.getName().equals(localTicket.getSeatName())) && (localTicket.hasTicket()))
        break;
    }
    label70: return -1;
  }

  private static int hasTicket(String paramString, List<Ticket> paramList)
  {
    if ((paramString == null) || (paramList == null))
    {
      i = -1;
      return i;
    }
    Iterator localIterator = paramList.iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        break label67;
      Ticket localTicket = (Ticket)localIterator.next();
      if ((paramString.equals(localTicket.getSeatCode())) && (localTicket.hasTicket()))
        break;
    }
    label67: return -1;
  }

  public static IDResult valideIdCard(String paramString)
  {
    IDResult localIDResult = new IDResult();
    if (paramString == null)
      return localIDResult;
    String str1 = paramString.trim().toUpperCase();
    if (str1.length() != 18)
      return localIDResult;
    if (!Pattern.matches("^[0-9|*]{17}[X|x|0-9]$", str1))
      return localIDResult;
    if (!"11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82,91".contains(str1.substring(0, 2)))
      return localIDResult;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    Date localDate;
    try
    {
      localDate = localSimpleDateFormat.parse(str1.substring(6, 14));
      boolean bool1 = localDate.before(localSimpleDateFormat.parse("19000101"));
      if (bool1)
        return localIDResult;
    }
    catch (ParseException localParseException)
    {
      return localIDResult;
    }
    char[] arrayOfChar = { 49, 48, 88, 57, 56, 55, 54, 53, 52, 51, 50 };
    long l = 0L;
    for (int i = 0; i < 17; i++)
      l = ()(l + Integer.valueOf(str1.substring(i, i + 1)).intValue() * (Math.pow(2.0D, 17 - i) % 11.0D));
    int j = str1.charAt(17);
    int k = arrayOfChar[(int)(l % 11L)];
    boolean bool2 = false;
    if (j == k)
      bool2 = true;
    String str2 = "F";
    if (Integer.parseInt(str1.substring(16, 17)) % 2 != 0)
      str2 = "M";
    localIDResult.setBirthDay(localDate);
    localIDResult.setResult(bool2);
    localIDResult.setGender(str2);
    return localIDResult;
  }

  public static class IDResult
  {
    private Date birthDay;
    private String gender;
    private boolean result = false;

    public Date getBirthDay()
    {
      return this.birthDay;
    }

    public String getGender()
    {
      return this.gender;
    }

    public boolean isResult()
    {
      return this.result;
    }

    public void setBirthDay(Date paramDate)
    {
      this.birthDay = paramDate;
    }

    public void setGender(String paramString)
    {
      this.gender = paramString;
    }

    public void setResult(boolean paramBoolean)
    {
      this.result = paramBoolean;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.HCUtil
 * JD-Core Version:    0.6.0
 */