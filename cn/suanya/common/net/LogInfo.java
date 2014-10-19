package cn.suanya.common.net;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

public class LogInfo
{
  private String content;
  private int level = 0;
  private String method;
  private Date runTime;

  public LogInfo(String paramString, Exception paramException)
  {
    this.method = paramString;
    this.runTime = null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramException.printStackTrace(new PrintStream(localByteArrayOutputStream));
    this.content = localByteArrayOutputStream.toString();
  }

  public LogInfo(String paramString1, String paramString2)
  {
    this.method = paramString1;
    this.runTime = null;
    this.content = paramString2;
  }

  public String getContent()
  {
    return this.content;
  }

  public int getLevel()
  {
    return this.level;
  }

  public String getMethod()
  {
    return this.method;
  }

  public Date getRunTime()
  {
    return this.runTime;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  public void setMethod(String paramString)
  {
    this.method = paramString;
  }

  public void setRunTime(Date paramDate)
  {
    this.runTime = paramDate;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.LogInfo
 * JD-Core Version:    0.6.0
 */