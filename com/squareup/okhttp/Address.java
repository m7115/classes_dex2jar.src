package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public final class Address
{
  final OkAuthenticator authenticator;
  final HostnameVerifier hostnameVerifier;
  final List<Protocol> protocols;
  final Proxy proxy;
  final SSLSocketFactory sslSocketFactory;
  final String uriHost;
  final int uriPort;

  public Address(String paramString, int paramInt, SSLSocketFactory paramSSLSocketFactory, HostnameVerifier paramHostnameVerifier, OkAuthenticator paramOkAuthenticator, Proxy paramProxy, List<Protocol> paramList)
    throws UnknownHostException
  {
    if (paramString == null)
      throw new NullPointerException("uriHost == null");
    if (paramInt <= 0)
      throw new IllegalArgumentException("uriPort <= 0: " + paramInt);
    if (paramOkAuthenticator == null)
      throw new IllegalArgumentException("authenticator == null");
    if (paramList == null)
      throw new IllegalArgumentException("protocols == null");
    this.proxy = paramProxy;
    this.uriHost = paramString;
    this.uriPort = paramInt;
    this.sslSocketFactory = paramSSLSocketFactory;
    this.hostnameVerifier = paramHostnameVerifier;
    this.authenticator = paramOkAuthenticator;
    this.protocols = Util.immutableList(paramList);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Address;
    int i = 0;
    if (bool1)
    {
      Address localAddress = (Address)paramObject;
      boolean bool2 = Util.equal(this.proxy, localAddress.proxy);
      i = 0;
      if (bool2)
      {
        boolean bool3 = this.uriHost.equals(localAddress.uriHost);
        i = 0;
        if (bool3)
        {
          int j = this.uriPort;
          int k = localAddress.uriPort;
          i = 0;
          if (j == k)
          {
            boolean bool4 = Util.equal(this.sslSocketFactory, localAddress.sslSocketFactory);
            i = 0;
            if (bool4)
            {
              boolean bool5 = Util.equal(this.hostnameVerifier, localAddress.hostnameVerifier);
              i = 0;
              if (bool5)
              {
                boolean bool6 = Util.equal(this.authenticator, localAddress.authenticator);
                i = 0;
                if (bool6)
                {
                  boolean bool7 = Util.equal(this.protocols, localAddress.protocols);
                  i = 0;
                  if (bool7)
                    i = 1;
                }
              }
            }
          }
        }
      }
    }
    return i;
  }

  public OkAuthenticator getAuthenticator()
  {
    return this.authenticator;
  }

  public HostnameVerifier getHostnameVerifier()
  {
    return this.hostnameVerifier;
  }

  public List<Protocol> getProtocols()
  {
    return this.protocols;
  }

  public Proxy getProxy()
  {
    return this.proxy;
  }

  public SSLSocketFactory getSslSocketFactory()
  {
    return this.sslSocketFactory;
  }

  public String getUriHost()
  {
    return this.uriHost;
  }

  public int getUriPort()
  {
    return this.uriPort;
  }

  public int hashCode()
  {
    int i = 31 * (31 * (527 + this.uriHost.hashCode()) + this.uriPort);
    int j;
    int m;
    label61: int n;
    if (this.sslSocketFactory != null)
    {
      j = this.sslSocketFactory.hashCode();
      int k = 31 * (j + i);
      if (this.hostnameVerifier == null)
        break label143;
      m = this.hostnameVerifier.hashCode();
      n = 31 * (m + k);
      if (this.authenticator == null)
        break label149;
    }
    label143: label149: for (int i1 = this.authenticator.hashCode(); ; i1 = 0)
    {
      int i2 = 31 * (i1 + n);
      Proxy localProxy = this.proxy;
      int i3 = 0;
      if (localProxy != null)
        i3 = this.proxy.hashCode();
      return 31 * (i2 + i3) + this.protocols.hashCode();
      j = 0;
      break;
      m = 0;
      break label61;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Address
 * JD-Core Version:    0.6.0
 */