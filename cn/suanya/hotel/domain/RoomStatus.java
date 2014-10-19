package cn.suanya.hotel.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomStatus
{
  static String roomNameDel;
  static List<NameMap> roomNameMap = new ArrayList();
  private boolean canBook;
  private String headImg;
  private String priceDesc;
  private double priceNow;
  private String roomName;
  private String roomType;
  private String salesDesc;
  private String sourceName;

  static
  {
    roomNameMap.add(new NameMap("商务房", ".*商务房.*"));
    roomNameMap.add(new NameMap("商务景观大床房", "商务景观大床房.*"));
    roomNameMap.add(new NameMap("豪华景观大床房", "豪华景观大床房.*"));
    roomNameDel = "(\\(.*\\)|（.*）|\\[.*\\]|［.*］)";
  }

  private void calRoomName(String paramString)
  {
    Iterator localIterator = roomNameMap.iterator();
    while (localIterator.hasNext())
    {
      NameMap localNameMap = (NameMap)localIterator.next();
      if (!paramString.matches(localNameMap.value))
        continue;
      this.roomName = localNameMap.name;
      return;
    }
    this.roomName = paramString;
    this.roomName = this.roomName.replaceAll(roomNameDel, "");
  }

  public String getHeadImg()
  {
    return this.headImg;
  }

  public String getPriceDesc()
  {
    return this.priceDesc;
  }

  public double getPriceNow()
  {
    return this.priceNow;
  }

  public String getRoomName()
  {
    return this.roomName;
  }

  public String getRoomType()
  {
    return this.roomType;
  }

  public String getSalesDesc()
  {
    return this.salesDesc;
  }

  public String getSourceName()
  {
    return this.sourceName;
  }

  public boolean isCanBook()
  {
    return this.canBook;
  }

  public void setCanBook(boolean paramBoolean)
  {
    this.canBook = paramBoolean;
  }

  public void setHeadImg(String paramString)
  {
    this.headImg = paramString;
  }

  public void setPriceDesc(String paramString)
  {
    this.priceDesc = paramString;
  }

  public void setPriceNow(double paramDouble)
  {
    this.priceNow = paramDouble;
  }

  public void setRoomType(String paramString)
  {
    this.roomType = paramString;
    calRoomName(paramString);
  }

  public void setSalesDesc(String paramString)
  {
    this.salesDesc = paramString;
  }

  public void setSourceName(String paramString)
  {
    this.sourceName = paramString;
  }

  static class NameMap
  {
    String name;
    String value;

    public NameMap(String paramString1, String paramString2)
    {
      this.name = paramString1;
      this.value = paramString2;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.RoomStatus
 * JD-Core Version:    0.6.0
 */