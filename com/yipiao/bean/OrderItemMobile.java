package com.yipiao.bean;

import cn.suanya.hc.service.PathService;
import com.yipiao.Config;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class OrderItemMobile extends OrderItem
{
  static SimpleDateFormat mmdd;
  static SimpleDateFormat mmss;
  static SimpleDateFormat ms;
  private static final long serialVersionUID = -7304661017987787514L;
  static SimpleDateFormat y_m_d;
  static SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
  private JSONObject data;

  static
  {
    y_m_d = new SimpleDateFormat("yyyy-MM-dd");
    mmdd = new SimpleDateFormat("MM月dd日");
    ms = new SimpleDateFormat("mmss");
    mmss = new SimpleDateFormat("mm:ss");
  }

  public JSONObject getData()
  {
    return this.data;
  }

  public void setData(JSONObject paramJSONObject)
  {
    this.data = paramJSONObject;
    if (paramJSONObject.optJSONObject("passengerDTO") == null)
      new JSONObject();
    setOrderNo(paramJSONObject.optString("ticket_no"));
    setSequenceNo(paramJSONObject.optString("sequence_no"));
    Date localDate = new Date();
    try
    {
      localDate = ymd.parse(paramJSONObject.optString("train_date"));
      setOrderDate(mmdd.format(localDate));
      setTrainNo(paramJSONObject.optString("station_train_code"));
      StationNode localStationNode1 = PathService.getInstance().getStationInfoByCode(paramJSONObject.optString("from_station_telecode"));
      if (localStationNode1 != null)
        setFrom(localStationNode1.getName());
      StationNode localStationNode2 = PathService.getInstance().getStationInfoByCode(paramJSONObject.optString("to_station_telecode"));
      if (localStationNode2 != null)
        setTo(localStationNode2.getName());
      String str1 = paramJSONObject.optString("start_time");
      if ((str1 == null) || (str1.length() < 4))
        str1 = "0000";
      str2 = str1.substring(0, 2);
      str3 = str1.substring(2, 4);
    }
    catch (Exception localException1)
    {
      try
      {
        int i = Integer.parseInt(str2);
        if (i >= 30)
          i -= 30;
        String str7 = Integer.toString(i);
        localObject = str7;
      }
      catch (Exception localException1)
      {
        try
        {
          String str2;
          String str3;
          String str8;
          if (((String)localObject).length() < 2)
            str8 = '0' + (String)localObject;
          for (Object localObject = str8; ; localObject = str2)
          {
            label247: setLeaveTime((String)localObject + ":" + str3);
            setCarOfTrain(paramJSONObject.optString("coach_no") + "车");
            setSeatNo(paramJSONObject.optString("seat_name"));
            setSeatNoCode(paramJSONObject.optString("seat_no"));
            String str4 = paramJSONObject.optString("seat_type_code");
            Note localNote1 = Config.getInstance().seatTypeMobile.getByCode(str4);
            if (localNote1 != null)
              setSeatType(localNote1.getName());
            String str5 = paramJSONObject.optString("ticket_type_code");
            Note localNote2 = Config.getInstance().tickTypes.getByCode(str5);
            if (localNote2 != null)
              setTicketType(localNote2.getName());
            String str6 = paramJSONObject.optString("ticket_price");
            if (str6.startsWith("."))
              str6 = "0" + str6;
            setPrice(str6);
            setName(paramJSONObject.optString("passenger_name"));
            setIdType(paramJSONObject.optString("passenger_id_type_name"));
            setStatus(paramJSONObject.optString("ticket_status_name"));
            setSeatTypeCode(paramJSONObject.optString("seat_type_code"));
            setTickTypeCode(paramJSONObject.optString("ticket_type_code"));
            setIdTypeCode(paramJSONObject.optString("passenger_id_type_code"));
            setIdNo(paramJSONObject.optString("passenger_id_no"));
            setBatchNo(paramJSONObject.optString("batch_no"));
            setCoachNo(paramJSONObject.optString("coach_no"));
            setStartTrainDatePage(y_m_d.format(localDate) + " " + getLeaveTime());
            setCanResign("Y".equals(paramJSONObject.optString("resign_flag")));
            setCanCancel("Y".equals(paramJSONObject.optString("return_flag")));
            return;
            localParseException = localParseException;
            setOrderDate(paramJSONObject.optString("train_date").substring(0, 10));
            break;
            localException1 = localException1;
          }
        }
        catch (Exception localException2)
        {
          break label247;
        }
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderItemMobile
 * JD-Core Version:    0.6.0
 */