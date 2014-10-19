package cn.suanya.domain;

import java.io.Serializable;
import org.json.JSONObject;

public class Response<T>
  implements Serializable
{
  private static final long serialVersionUID = 5957345870809286856L;
  private T data;
  private String message;
  private int status;

  public Response()
  {
  }

  public Response(int paramInt, String paramString)
  {
    this.status = paramInt;
    this.message = paramString;
  }

  public Response(T paramT)
  {
    this.data = paramT;
  }

  public Response(JSONObject paramJSONObject)
  {
    this(paramJSONObject.optInt("status", 0), paramJSONObject.optString("message"));
  }

  public T getData()
  {
    return this.data;
  }

  public String getMessage()
  {
    return this.message;
  }

  public int getStatus()
  {
    return this.status;
  }

  public void setData(T paramT)
  {
    this.data = paramT;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setStatus(int paramInt)
  {
    this.status = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.domain.Response
 * JD-Core Version:    0.6.0
 */