package com.yipiao.bean;

import com.google.gson.annotations.Expose;
import com.yipiao.Config;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChainQuery
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = -7809662905985113254L;
  private StationNode arr = new StationNode("GZQ", "广州", "gz", "guangzhou", 23.155000000000001D, 113.264D, "广州");
  private String leaveDate;
  private StationNode org = new StationNode("BJP", "北京", "BJ", "beijing", 39.908000000000001D, 116.434D, "北京");

  @Expose(deserialize=false, serialize=false)
  private transient List<Train> results;
  private String student;
  private String timeRang = Config.getInstance().timeRang[0];
  private String timeRangBegin;
  private String timeRangEnd;
  private String trainNo = "";
  private NoteList trainTypes = Config.getInstance().trainTypeSimples.clone();

  public ChainQuery()
  {
    Calendar localCalendar = Calendar.getInstance();
    this.timeRangBegin = "00:00";
    this.timeRangEnd = "24:00";
    this.leaveDate = new SimpleDateFormat("yyyy-MM-dd").format(localCalendar.getTime());
    this.student = "00";
  }

  public Date addDate(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(getLeavedate2());
    localCalendar.add(5, paramInt);
    this.leaveDate = new SimpleDateFormat("yyyy-MM-dd").format(localCalendar.getTime());
    return localCalendar.getTime();
  }

  public ChainQuery clone()
    throws CloneNotSupportedException
  {
    return (ChainQuery)super.clone();
  }

  public List<Train> findResults()
  {
    return this.results;
  }

  public StationNode getArr()
  {
    return this.arr;
  }

  public String getLeaveDate()
  {
    return this.leaveDate;
  }

  public Date getLeavedate2()
  {
    try
    {
      Date localDate = new SimpleDateFormat("yyyy-MM-dd").parse(this.leaveDate);
      return localDate;
    }
    catch (ParseException localParseException)
    {
    }
    return new Date();
  }

  public StationNode getOrg()
  {
    return this.org;
  }

  public String getPassengerType()
  {
    if ("0X00".equals(this.student))
      return "0X00";
    return "ADULT";
  }

  public String getStudent()
  {
    return this.student;
  }

  public String getTimeRang()
  {
    return this.timeRang;
  }

  public String getTimeRangBegin()
  {
    return this.timeRangBegin;
  }

  public String getTimeRangEnd()
  {
    return this.timeRangEnd;
  }

  public String getTrainNo()
  {
    return this.trainNo;
  }

  public NoteList getTrainTypes()
  {
    return this.trainTypes;
  }

  public void setArr(StationNode paramStationNode)
  {
    this.results = null;
    this.arr = paramStationNode;
  }

  public void setLeaveDate(String paramString)
  {
    this.results = null;
    this.leaveDate = paramString;
  }

  public void setOrg(StationNode paramStationNode)
  {
    this.results = null;
    this.org = paramStationNode;
  }

  public void setResults(List<Train> paramList)
  {
    this.results = paramList;
  }

  public void setStudent(String paramString)
  {
    this.student = paramString;
  }

  public void setTimeRang(String paramString)
  {
    this.timeRang = paramString;
  }

  public void setTimeRangBegin(String paramString)
  {
    this.timeRangBegin = paramString;
  }

  public void setTimeRangEnd(String paramString)
  {
    this.timeRangEnd = paramString;
  }

  public void setTrainNo(String paramString)
  {
    this.trainNo = paramString;
  }

  public void setTrainTypes(NoteList paramNoteList)
  {
    this.trainTypes = paramNoteList;
  }

  public String toString()
  {
    return this.org.getName() + "-" + this.arr.getName() + "(" + this.leaveDate + ")";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.ChainQuery
 * JD-Core Version:    0.6.0
 */