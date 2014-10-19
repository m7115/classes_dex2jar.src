package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.Request;
import com.squareup.okhttp.internal.http.Request.Builder;
import java.io.IOException;
import java.net.URL;

public final class TunnelRequest
{
  final String host;
  final int port;
  final String proxyAuthorization;
  final String userAgent;

  public TunnelRequest(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    if (paramString1 == null)
      throw new NullPointerException("host == null");
    if (paramString2 == null)
      throw new NullPointerException("userAgent == null");
    this.host = paramString1;
    this.port = paramInt;
    this.userAgent = paramString2;
    this.proxyAuthorization = paramString3;
  }

  Request getRequest()
    throws IOException
  {
    Request.Builder localBuilder = new Request.Builder().url(new URL("https", this.host, this.port, "/"));
    if (this.port == Util.getDefaultPort("https"));
    for (String str = this.host; ; str = this.host + ":" + this.port)
    {
      localBuilder.header("Host", str);
      localBuilder.header("User-Agent", this.userAgent);
      if (this.proxyAuthorization != null)
        localBuilder.header("Proxy-Authorization", this.proxyAuthorization);
      localBuilder.header("Proxy-Connection", "Keep-Alive");
      return localBuilder.build();
    }
  }

  String requestLine()
  {
    return "CONNECT " + this.host + ":" + this.port + " HTTP/1.1";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.TunnelRequest
 * JD-Core Version:    0.6.0
 */