package com.squareup.okhttp.internal.http;

import java.io.IOException;
import java.net.ProtocolException;

public final class StatusLine
{
  public static final int HTTP_CONTINUE = 100;
  public static final int HTTP_TEMP_REDIRECT = 307;
  private final int httpMinorVersion;
  private final int responseCode;
  private final String responseMessage;
  private final String statusLine;

  public StatusLine(String paramString)
    throws IOException
  {
    int j;
    if (paramString.startsWith("HTTP/1."))
    {
      if ((paramString.length() < i) || (paramString.charAt(8) != ' '))
        throw new ProtocolException("Unexpected status line: " + paramString);
      j = '￐' + paramString.charAt(7);
      if ((j < 0) || (j > i))
        throw new ProtocolException("Unexpected status line: " + paramString);
    }
    else
    {
      if (!paramString.startsWith("ICY "))
        break label158;
      j = 0;
      i = 4;
    }
    if (paramString.length() < i + 3)
    {
      throw new ProtocolException("Unexpected status line: " + paramString);
      label158: throw new ProtocolException("Unexpected status line: " + paramString);
    }
    int k = i + 3;
    int m;
    try
    {
      m = Integer.parseInt(paramString.substring(i, k));
      if (paramString.length() > i + 3)
        if (paramString.charAt(i + 3) != ' ')
          throw new ProtocolException("Unexpected status line: " + paramString);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new ProtocolException("Unexpected status line: " + paramString);
    }
    for (String str = paramString.substring(i + 4); ; str = "")
    {
      this.responseMessage = str;
      this.responseCode = m;
      this.statusLine = paramString;
      this.httpMinorVersion = j;
      return;
    }
  }

  public int code()
  {
    return this.responseCode;
  }

  public String getStatusLine()
  {
    return this.statusLine;
  }

  public int httpMinorVersion()
  {
    if (this.httpMinorVersion != -1)
      return this.httpMinorVersion;
    return 1;
  }

  public String message()
  {
    return this.responseMessage;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.StatusLine
 * JD-Core Version:    0.6.0
 */