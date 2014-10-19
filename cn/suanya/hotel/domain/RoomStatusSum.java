package cn.suanya.hotel.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomStatusSum
{
  private double lowestPrice;
  private String roomName;
  private List<RoomStatus> roomStatus = new ArrayList();

  public void addByPrice(RoomStatus paramRoomStatus)
  {
    Iterator localIterator = this.roomStatus.iterator();
    for (int i = 0; ; i++)
    {
      if ((localIterator.hasNext()) && (((RoomStatus)localIterator.next()).getPriceNow() <= paramRoomStatus.getPriceNow()))
        continue;
      this.roomStatus.add(i, paramRoomStatus);
      return;
    }
  }

  public double getLowestPrice()
  {
    return this.lowestPrice;
  }

  public String getRoomName()
  {
    return this.roomName;
  }

  public List<RoomStatus> getRoomStatus()
  {
    return this.roomStatus;
  }

  public boolean isCanBook()
  {
    Iterator localIterator = this.roomStatus.iterator();
    while (localIterator.hasNext())
      if (((RoomStatus)localIterator.next()).isCanBook())
        return true;
    return false;
  }

  public void setLowestPrice(double paramDouble)
  {
    this.lowestPrice = paramDouble;
  }

  public void setRoomName(String paramString)
  {
    this.roomName = paramString;
  }

  public void setRoomStatus(List<RoomStatus> paramList)
  {
    this.roomStatus = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.RoomStatusSum
 * JD-Core Version:    0.6.0
 */