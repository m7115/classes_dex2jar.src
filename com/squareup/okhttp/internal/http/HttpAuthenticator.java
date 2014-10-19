package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.OkAuthenticator;
import com.squareup.okhttp.OkAuthenticator.Challenge;
import com.squareup.okhttp.OkAuthenticator.Credential;
import java.io.IOException;
import java.net.Authenticator;
import java.net.Authenticator.RequestorType;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class HttpAuthenticator
{
  public static final OkAuthenticator SYSTEM_DEFAULT = new OkAuthenticator()
  {
    private InetAddress getConnectToInetAddress(Proxy paramProxy, URL paramURL)
      throws IOException
    {
      if ((paramProxy != null) && (paramProxy.type() != Proxy.Type.DIRECT))
        return ((InetSocketAddress)paramProxy.address()).getAddress();
      return InetAddress.getByName(paramURL.getHost());
    }

    public OkAuthenticator.Credential authenticate(Proxy paramProxy, URL paramURL, List<OkAuthenticator.Challenge> paramList)
      throws IOException
    {
      int i = paramList.size();
      int j = 0;
      if (j < i)
      {
        OkAuthenticator.Challenge localChallenge = (OkAuthenticator.Challenge)paramList.get(j);
        if (!"Basic".equalsIgnoreCase(localChallenge.getScheme()));
        PasswordAuthentication localPasswordAuthentication;
        do
        {
          j++;
          break;
          localPasswordAuthentication = Authenticator.requestPasswordAuthentication(paramURL.getHost(), getConnectToInetAddress(paramProxy, paramURL), paramURL.getPort(), paramURL.getProtocol(), localChallenge.getRealm(), localChallenge.getScheme(), paramURL, Authenticator.RequestorType.SERVER);
        }
        while (localPasswordAuthentication == null);
        return OkAuthenticator.Credential.basic(localPasswordAuthentication.getUserName(), new String(localPasswordAuthentication.getPassword()));
      }
      return null;
    }

    public OkAuthenticator.Credential authenticateProxy(Proxy paramProxy, URL paramURL, List<OkAuthenticator.Challenge> paramList)
      throws IOException
    {
      int i = paramList.size();
      int j = 0;
      if (j < i)
      {
        OkAuthenticator.Challenge localChallenge = (OkAuthenticator.Challenge)paramList.get(j);
        if (!"Basic".equalsIgnoreCase(localChallenge.getScheme()));
        PasswordAuthentication localPasswordAuthentication;
        do
        {
          j++;
          break;
          InetSocketAddress localInetSocketAddress = (InetSocketAddress)paramProxy.address();
          localPasswordAuthentication = Authenticator.requestPasswordAuthentication(localInetSocketAddress.getHostName(), getConnectToInetAddress(paramProxy, paramURL), localInetSocketAddress.getPort(), paramURL.getProtocol(), localChallenge.getRealm(), localChallenge.getScheme(), paramURL, Authenticator.RequestorType.PROXY);
        }
        while (localPasswordAuthentication == null);
        return OkAuthenticator.Credential.basic(localPasswordAuthentication.getUserName(), new String(localPasswordAuthentication.getPassword()));
      }
      return null;
    }
  };

  private static List<OkAuthenticator.Challenge> parseChallenges(Headers paramHeaders, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < paramHeaders.size())
    {
      if (!paramString.equalsIgnoreCase(paramHeaders.name(i)));
      label180: 
      while (true)
      {
        i++;
        break;
        String str1 = paramHeaders.value(i);
        int j = 0;
        while (true)
        {
          if (j >= str1.length())
            break label180;
          int k = HeaderParser.skipUntil(str1, j, " ");
          String str2 = str1.substring(j, k).trim();
          int m = HeaderParser.skipWhitespace(str1, k);
          if (!str1.regionMatches(true, m, "realm=\"", 0, "realm=\"".length()))
            break;
          int n = m + "realm=\"".length();
          int i1 = HeaderParser.skipUntil(str1, n, "\"");
          String str3 = str1.substring(n, i1);
          j = HeaderParser.skipWhitespace(str1, 1 + HeaderParser.skipUntil(str1, i1 + 1, ","));
          localArrayList.add(new OkAuthenticator.Challenge(str2, str3));
        }
      }
    }
    return localArrayList;
  }

  public static Request processAuthHeader(OkAuthenticator paramOkAuthenticator, Response paramResponse, Proxy paramProxy)
    throws IOException
  {
    String str1;
    String str2;
    if (paramResponse.code() == 401)
    {
      str1 = "WWW-Authenticate";
      str2 = "Authorization";
    }
    List localList;
    while (true)
    {
      localList = parseChallenges(paramResponse.headers(), str1);
      if (!localList.isEmpty())
        break;
      return null;
      if (paramResponse.code() == 407)
      {
        str1 = "Proxy-Authenticate";
        str2 = "Proxy-Authorization";
        continue;
      }
      throw new IllegalArgumentException();
    }
    Request localRequest = paramResponse.request();
    if (paramResponse.code() == 407);
    for (OkAuthenticator.Credential localCredential = paramOkAuthenticator.authenticateProxy(paramProxy, localRequest.url(), localList); localCredential == null; localCredential = paramOkAuthenticator.authenticate(paramProxy, localRequest.url(), localList))
      return null;
    return localRequest.newBuilder().header(str2, localCredential.getHeaderValue()).build();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.HttpAuthenticator
 * JD-Core Version:    0.6.0
 */