package cn.suanya.hotel.domain;

import java.util.ArrayList;
import java.util.List;

public class HotelDetail extends HotelInfo
{
  private static final long serialVersionUID = -5446283152478333037L;
  private List<RoomStatus> roomStatus = new ArrayList();

  public HotelDetail()
  {
  }

  public HotelDetail(HotelInfo paramHotelInfo)
  {
    this();
    setHotelName(paramHotelInfo.getHotelName());
    setHotelAddress(paramHotelInfo.getHotelAddress());
  }

  public List<RoomStatus> getRoomStatus()
  {
    return this.roomStatus;
  }

  public void setRoomStatus(List<RoomStatus> paramList)
  {
    this.roomStatus = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.HotelDetail
 * JD-Core Version:    0.6.0
 */