package com.squareup.okhttp.internal.http;

import java.net.Proxy.Type;
import java.net.URL;

public final class RequestLine
{
  static String get(Request paramRequest, Proxy.Type paramType, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramRequest.method());
    localStringBuilder.append(" ");
    if (includeAuthorityInRequestLine(paramRequest, paramType))
      localStringBuilder.append(paramRequest.url());
    while (true)
    {
      localStringBuilder.append(" ");
      localStringBuilder.append(version(paramInt));
      return localStringBuilder.toString();
      localStringBuilder.append(requestPath(paramRequest.url()));
    }
  }

  private static boolean includeAuthorityInRequestLine(Request paramRequest, Proxy.Type paramType)
  {
    return (!paramRequest.isHttps()) && (paramType == Proxy.Type.HTTP);
  }

  public static String requestPath(URL paramURL)
  {
    String str = paramURL.getFile();
    if (str == null)
      str = "/";
    do
      return str;
    while (str.startsWith("/"));
    return "/" + str;
  }

  public static String version(int paramInt)
  {
    if (paramInt == 1)
      return "HTTP/1.1";
    return "HTTP/1.0";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.RequestLine
 * JD-Core Version:    0.6.0
 */