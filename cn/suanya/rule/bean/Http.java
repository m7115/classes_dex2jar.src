package cn.suanya.rule.bean;

import cn.suanya.common.bean.NameValue;
import cn.suanya.common.net.SYHttpResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Http
{
  private String body;
  private String charset;
  private List<NameValue> heads;
  private List<NameValue> parms;
  private SYHttpResponse response;

  public Http()
  {
    this("utf8");
  }

  public Http(String paramString)
  {
    this.charset = paramString;
    this.heads = new ArrayList();
    this.parms = new ArrayList();
  }

  public void addHeader(NameValue paramNameValue)
  {
    this.heads.add(paramNameValue);
  }

  public void addParm(NameValue paramNameValue)
  {
    this.parms.add(paramNameValue);
  }

  public String getBody()
    throws IOException
  {
    if (this.body != null)
      return this.body;
    this.body = this.response.getString(this.charset);
    return this.body;
  }

  public String getCharset()
  {
    return this.charset;
  }

  public List<NameValue> getHeads()
  {
    return this.heads;
  }

  public List<NameValue> getParms()
  {
    return this.parms;
  }

  public SYHttpResponse getResponse()
  {
    return this.response;
  }

  public InputStream getStream()
  {
    return this.response.getInputStream();
  }

  public void setCharset(String paramString)
  {
    this.charset = paramString;
  }

  public void setResponse(SYHttpResponse paramSYHttpResponse)
  {
    this.body = null;
    this.response = paramSYHttpResponse;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.bean.Http
 * JD-Core Version:    0.6.0
 */