package cn.suanya.common.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class SYHttpResponse
{
  private ByteArrayOutputStream byteStream;
  private Map<String, List<String>> headers;

  public SYHttpResponse(Map<String, List<String>> paramMap, ByteArrayOutputStream paramByteArrayOutputStream)
  {
    this.headers = paramMap;
    this.byteStream = paramByteArrayOutputStream;
  }

  public Map<String, List<String>> getHeaders()
  {
    return this.headers;
  }

  public InputStream getInputStream()
  {
    return new ByteArrayInputStream(this.byteStream.toByteArray());
  }

  public String getString()
  {
    return getString("UTF-8");
  }

  public String getString(String paramString)
  {
    try
    {
      String str = this.byteStream.toString(paramString);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      localUnsupportedEncodingException.printStackTrace();
    }
    return null;
  }

  public void setHeaders(Map<String, List<String>> paramMap)
  {
    this.headers = paramMap;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.SYHttpResponse
 * JD-Core Version:    0.6.0
 */