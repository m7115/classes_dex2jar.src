package com.yipiao.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

public class OrderItemNew extends OrderItem
{
  static SimpleDateFormat mmdd;
  private static final long serialVersionUID = -5772886098203539845L;
  static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
  private JSONObject data;

  static
  {
    mmdd = new SimpleDateFormat("MM月dd日");
  }

  public JSONObject getData()
  {
    return this.data;
  }

  public void setData(JSONObject paramJSONObject)
  {
    this.data = paramJSONObject;
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("stationTrainDTO");
    if (localJSONObject1 == null)
      localJSONObject1 = new JSONObject();
    JSONObject localJSONObject2 = paramJSONObject.optJSONObject("passengerDTO");
    if (localJSONObject2 == null)
      localJSONObject2 = new JSONObject();
    setOrderNo(paramJSONObject.optString("ticket_no"));
    setSequenceNo(paramJSONObject.optString("sequence_no"));
    try
    {
      setOrderDate(mmdd.format(ymd.parse(paramJSONObject.optString("train_date"))));
      setTrainNo(localJSONObject1.optString("station_train_code"));
      setFrom(localJSONObject1.optString("from_station_name"));
      setTo(localJSONObject1.optString("to_station_name"));
      setLeaveTime(localJSONObject1.optString("start_time").substring(11, 16));
      setCarOfTrain(paramJSONObject.optString("coach_no") + "车");
      setSeatNo(paramJSONObject.optString("seat_name"));
      setSeatNoCode(paramJSONObject.optString("seat_no"));
      setSeatType(paramJSONObject.optString("seat_type_name"));
      setTicketType(paramJSONObject.optString("ticket_type_name"));
      setPrice(paramJSONObject.optString("str_ticket_price_page"));
      setName(localJSONObject2.optString("passenger_name"));
      setIdType(localJSONObject2.optString("passenger_id_type_name"));
      setStatus(paramJSONObject.optString("ticket_status_name"));
      setSeatTypeCode(paramJSONObject.optString("seat_type_code"));
      setTickTypeCode(paramJSONObject.optString("ticket_type_code"));
      setIdTypeCode(localJSONObject2.optString("passenger_id_type_code"));
      setIdNo(localJSONObject2.optString("passenger_id_no"));
      setBatchNo(paramJSONObject.optString("batch_no"));
      setCoachNo(paramJSONObject.optString("coach_no"));
      setStartTrainDatePage(paramJSONObject.optString("start_train_date_page"));
      setCanResign("Y".equals(paramJSONObject.optString("resign_flag")));
      setCanCancel("Y".equals(paramJSONObject.optString("return_flag")));
      return;
    }
    catch (ParseException localParseException)
    {
      while (true)
        setOrderDate(paramJSONObject.optString("train_date").substring(0, 10));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.OrderItemNew
 * JD-Core Version:    0.6.0
 */