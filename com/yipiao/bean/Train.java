package com.yipiao.bean;

import com.example.pathview.util.TimeUtil;
import com.yipiao.Config;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class Train
  implements Serializable
{
  private static final long serialVersionUID = 3228796408967905568L;
  protected String code;
  protected String code3;
  private JSONObject data;
  protected boolean firstStation;
  protected String from;
  protected String fromCode;
  protected String fromTime;
  protected boolean lastStation;
  protected String leaveDate;
  private String locationCode;
  protected boolean logined = true;
  private String[] orderStrArray;
  String orderString;
  String orderStringSrc;
  private long queryTime = System.currentTimeMillis();
  String seatString;
  protected List<Ticket> seats;
  protected String timeLong;
  protected String to;
  protected String toCode;
  protected String toTime;
  private String ypInfo;

  private List<Ticket> sortYpInfo(List<Ticket> paramList)
  {
    int i = paramList.size();
    int j = 0;
    if (j < i - 1);
    while (true)
    {
      int k;
      try
      {
        k = Integer.parseInt(((Ticket)paramList.get(j)).getRate());
        int m = j + 1;
        if (m >= i)
          continue;
        n = Integer.parseInt(((Ticket)paramList.get(m)).getRate());
        if (k <= n)
          break label99;
        swap(paramList, j, m);
        m++;
        k = n;
        continue;
        j++;
      }
      catch (Exception localException)
      {
      }
      return paramList;
      label99: int n = k;
    }
  }

  private int str2Int(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
    }
    return paramInt;
  }

  private void swap(List<Ticket> paramList, int paramInt1, int paramInt2)
  {
    Ticket localTicket = (Ticket)paramList.get(paramInt1);
    paramList.set(paramInt1, paramList.get(paramInt2));
    paramList.set(paramInt2, localTicket);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Train))
    {
      Train localTrain = (Train)paramObject;
      if ((localTrain.getCode() != null) && (localTrain.getCode().equals(this.code)))
        return true;
    }
    return false;
  }

  public int getApiVersion()
  {
    if ((this instanceof TrainNew))
      return 2;
    return 3;
  }

  public String getCode()
  {
    return this.code;
  }

  public String getCode3()
  {
    return this.code3;
  }

  public JSONObject getData()
  {
    return this.data;
  }

  public String getFrom()
  {
    return this.from;
  }

  public String getFromCode()
  {
    return this.fromCode;
  }

  public String getFromTime()
  {
    return this.fromTime;
  }

  public String getKey()
  {
    return this.code + this.leaveDate;
  }

  public String getLeaveDate()
  {
    return this.leaveDate;
  }

  public Date getLeaveDate2()
  {
    return TimeUtil.parseDate(this.leaveDate);
  }

  public String getLocationCode()
  {
    return this.locationCode;
  }

  public String[] getOrderStrArray()
  {
    if (this.orderStrArray == null)
      if (this.orderString == null)
        break label34;
    label34: for (String[] arrayOfString = this.orderString.split("#"); ; arrayOfString = null)
    {
      this.orderStrArray = arrayOfString;
      return this.orderStrArray;
    }
  }

  public String getOrderString()
  {
    return this.orderString;
  }

  public String getOrderStringSrc()
  {
    return this.orderStringSrc;
  }

  public long getQueryTime()
  {
    return this.queryTime;
  }

  public String getSeatHtml()
  {
    String str = "";
    Iterator localIterator = getSeats().iterator();
    while (localIterator.hasNext())
    {
      Ticket localTicket = (Ticket)localIterator.next();
      if (localTicket.getLeftNum() == 0)
        str = str + "<font color='#888888'>" + localTicket.getSeatName() + ":" + localTicket.getLeftStr() + "</font>&nbsp;&nbsp;";
      if (localTicket.getLeftNum() <= 0)
        continue;
      str = str + localTicket.getSeatName() + ":<font color='#F07C57'>" + localTicket.getLeftStr() + "</font>&nbsp;&nbsp;";
    }
    if (str.length() < 1)
      str = "车票已经售完";
    return str;
  }

  public String getSeatHtml(TrainPrice paramTrainPrice)
  {
    String str1;
    if (paramTrainPrice == null)
      str1 = getSeatHtml();
    do
    {
      return str1;
      str1 = "";
      Iterator localIterator = getSeats().iterator();
      while (localIterator.hasNext())
      {
        Ticket localTicket = (Ticket)localIterator.next();
        String str2 = localTicket.getPrice();
        if ((str2 == null) || (str2.length() < 1))
          str2 = paramTrainPrice.getPriceStr(this.code, localTicket.getSeatName());
        if (localTicket.getLeftNum() == 0)
          str1 = str1 + "<font color='#888888'>" + localTicket.getSeatName() + ":￥" + str2 + "</font>&nbsp;&nbsp;";
        if (localTicket.getLeftNum() <= 0)
          continue;
        str1 = str1 + localTicket.getSeatName() + ":<font color='#F07C57'>￥" + str2 + "</font>&nbsp;&nbsp;";
      }
    }
    while (str1.length() >= 1);
    return "车票已经售完";
  }

  public String getSeatMsg(NoteList paramNoteList)
  {
    Object localObject1 = "";
    Iterator localIterator = paramNoteList.iterator();
    Note localNote;
    int i;
    Object localObject2;
    if (localIterator.hasNext())
    {
      localNote = (Note)localIterator.next();
      i = getSeatNum(localNote.getCode());
      if (i <= 0)
        break label117;
      if (i >= 99)
        localObject2 = localNote.getName() + "足量 ";
    }
    while (true)
    {
      localObject1 = localObject2;
      break;
      localObject2 = localNote.getName() + i + "张 ";
      continue;
      return localObject1;
      label117: localObject2 = localObject1;
    }
  }

  public int getSeatNum()
  {
    Iterator localIterator = getSeats().iterator();
    int i = 0;
    while (localIterator.hasNext())
      i += ((Ticket)localIterator.next()).getLeftNum();
    return i;
  }

  public int getSeatNum(NoteList paramNoteList)
  {
    Iterator localIterator = paramNoteList.iterator();
    int i = 0;
    while (localIterator.hasNext())
      i += getSeatNum(((Note)localIterator.next()).getCode());
    return i;
  }

  public int getSeatNum(String paramString)
  {
    Iterator localIterator = getSeats().iterator();
    while (localIterator.hasNext())
    {
      Ticket localTicket = (Ticket)localIterator.next();
      if (paramString.equalsIgnoreCase(localTicket.getSeatCode()))
        return localTicket.getLeftNum();
    }
    return 0;
  }

  public int getSeatNum(String[] paramArrayOfString)
  {
    int i = 0;
    int j = 0;
    while (i < paramArrayOfString.length)
    {
      j += getSeatNum(paramArrayOfString[i]);
      i++;
    }
    return j;
  }

  public String getSeatString()
  {
    return this.seatString;
  }

  public int getSeatTypeNum()
  {
    Iterator localIterator = getSeats().iterator();
    int i = 0;
    if (localIterator.hasNext())
      if (((Ticket)localIterator.next()).getLeftNum() <= 0)
        break label47;
    label47: for (int j = i + 1; ; j = i)
    {
      i = j;
      break;
      return i;
    }
  }

  public String getSeatTypeString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = Config.getInstance().seatCodeIndex.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ((Integer)Config.getInstance().seatCodeIndex.get(str)).intValue();
      if (("".equals("")) || ("-".equals("")) || ("--".equals("")) || ("" == null))
        continue;
      if (str.equals("0"))
        str = "1";
      localStringBuffer.append(str);
    }
    return localStringBuffer.toString();
  }

  public List<Ticket> getSeats()
  {
    return this.seats;
  }

  public String getStartPayStr()
  {
    return null;
  }

  public String getTimeLong()
  {
    return this.timeLong;
  }

  public int getTimeLongMin()
  {
    return TimeUtil.getMinsByStr(this.timeLong);
  }

  public String getTo()
  {
    return this.to;
  }

  public String getToCode()
  {
    return this.toCode;
  }

  public String getToTime()
  {
    return this.toTime;
  }

  public String getYpInfo()
  {
    return this.ypInfo;
  }

  public boolean hasSeat()
  {
    Iterator localIterator = getSeats().iterator();
    while (localIterator.hasNext())
      if (((Ticket)localIterator.next()).getLeftNum() > 0)
        return true;
    return false;
  }

  public boolean isFirstStation()
  {
    return this.firstStation;
  }

  public boolean isLastStation()
  {
    return this.lastStation;
  }

  public boolean isLogined()
  {
    return this.logined;
  }

  public List<Ticket> processYpInfo(String paramString)
  {
    this.seats = new ArrayList();
    int i = paramString.length() / 10;
    int j = 1;
    int k = 0;
    int m = 10;
    int n = 6;
    int i1 = 0;
    int i2 = 50;
    String str1;
    int i3;
    Object localObject;
    String str3;
    int i5;
    String str5;
    int i4;
    if (i1 < i)
    {
      str1 = paramString.substring(k, j);
      i3 = str2Int(paramString.substring(n, m), 0);
      if (i3 >= 3000)
      {
        localObject = "无座";
        str3 = "100";
        i5 = i3 - 3000;
        String.valueOf(i5);
        str5 = "0";
        i4 = i2;
      }
    }
    while (true)
    {
      String str2;
      while (true)
      {
        String str6 = String.valueOf(i5);
        try
        {
          String str8 = String.valueOf(Float.parseFloat(paramString.substring(j, n)) / 10.0F);
          str7 = str8;
          this.seats.add(new Ticket(str5, (String)localObject, str7, str6, str3));
          int i6 = i1 + 1;
          int i7 = n + 10;
          int i8 = m + 10;
          int i9 = k + 10;
          j += 10;
          k = i9;
          m = i8;
          n = i7;
          i1 = i6;
          i2 = i4;
          break;
          Note localNote1 = Config.getInstance().seatTypeMobile.getByCode(str1);
          label245: Note localNote2;
          if (localNote1 != null)
          {
            str2 = localNote1.getName();
            localNote2 = Config.getInstance().seatTypeRateMobile.getByCode(str1);
            if (localNote2 == null)
              break label322;
          }
          label322: for (str3 = localNote2.getName(); ; str3 = "41")
          {
            if ((str3 != null) && (str3 != "undefined"))
              break label349;
            str3 = String.valueOf(i2);
            i4 = i2 + 1;
            String str4 = str2;
            i5 = i3;
            str5 = str1;
            localObject = str4;
            break;
            str2 = "";
            break label245;
          }
        }
        catch (Exception localException)
        {
          while (true)
            String str7 = "无";
        }
      }
      return sortYpInfo(this.seats);
      label349: i4 = i2;
      String str9 = str2;
      i5 = i3;
      str5 = str1;
      localObject = str9;
    }
  }

  public void setCode(String paramString)
  {
    this.code = paramString;
  }

  public void setCode3(String paramString)
  {
    this.code3 = paramString;
  }

  public void setData(JSONObject paramJSONObject)
  {
    this.data = paramJSONObject;
  }

  public void setFirstStation(boolean paramBoolean)
  {
    this.firstStation = paramBoolean;
  }

  public void setFrom(String paramString)
  {
    this.from = paramString;
  }

  public void setFromCode(String paramString)
  {
    this.fromCode = paramString;
  }

  public void setFromTime(String paramString)
  {
    this.fromTime = paramString;
  }

  public void setLastStation(boolean paramBoolean)
  {
    this.lastStation = paramBoolean;
  }

  public void setLeaveDate(String paramString)
  {
    this.leaveDate = paramString;
  }

  public void setLocationCode(String paramString)
  {
    this.locationCode = paramString;
  }

  public void setLogined(boolean paramBoolean)
  {
    this.logined = paramBoolean;
  }

  public void setOrderStrArray(String[] paramArrayOfString)
  {
    this.orderStrArray = paramArrayOfString;
  }

  public void setOrderString(String paramString)
  {
    this.orderString = paramString;
  }

  public void setOrderStringSrc(String paramString)
  {
    this.orderStringSrc = paramString;
  }

  public void setQueryTime(long paramLong)
  {
    this.queryTime = paramLong;
  }

  public void setSeatString(String paramString)
  {
    this.seatString = paramString;
  }

  public void setSeats(List<Ticket> paramList)
  {
    this.seats = paramList;
  }

  public void setTimeLong(String paramString)
  {
    this.timeLong = paramString;
  }

  public void setTo(String paramString)
  {
    this.to = paramString;
  }

  public void setToCode(String paramString)
  {
    this.toCode = paramString;
  }

  public void setToTime(String paramString)
  {
    this.toTime = paramString;
  }

  public void setYpInfo(String paramString)
  {
    this.ypInfo = paramString;
  }

  public String showLeaveDate()
  {
    return TimeUtil.formartMMddW(getLeaveDate2());
  }

  public String showTimeLong()
  {
    int i = getTimeLongMin();
    int j = i % 60;
    int k = i / 60;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(k);
    arrayOfObject[1] = Integer.valueOf(j);
    return MessageFormat.format("{0,number,00}小时{1,number,00}分", arrayOfObject);
  }

  public String toString()
  {
    String str1 = this.code;
    String str2 = str1 + "|" + this.from;
    String str3 = str2 + "-" + this.to;
    String str4 = str3 + "|" + this.fromTime;
    return str4 + "|" + this.toTime;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.Train
 * JD-Core Version:    0.6.0
 */