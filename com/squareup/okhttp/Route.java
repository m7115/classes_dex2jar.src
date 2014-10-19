package com.squareup.okhttp;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class Route
{
  final Address address;
  final InetSocketAddress inetSocketAddress;
  final boolean modernTls;
  final Proxy proxy;

  public Route(Address paramAddress, Proxy paramProxy, InetSocketAddress paramInetSocketAddress, boolean paramBoolean)
  {
    if (paramAddress == null)
      throw new NullPointerException("address == null");
    if (paramProxy == null)
      throw new NullPointerException("proxy == null");
    if (paramInetSocketAddress == null)
      throw new NullPointerException("inetSocketAddress == null");
    this.address = paramAddress;
    this.proxy = paramProxy;
    this.inetSocketAddress = paramInetSocketAddress;
    this.modernTls = paramBoolean;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Route;
    int i = 0;
    if (bool1)
    {
      Route localRoute = (Route)paramObject;
      boolean bool2 = this.address.equals(localRoute.address);
      i = 0;
      if (bool2)
      {
        boolean bool3 = this.proxy.equals(localRoute.proxy);
        i = 0;
        if (bool3)
        {
          boolean bool4 = this.inetSocketAddress.equals(localRoute.inetSocketAddress);
          i = 0;
          if (bool4)
          {
            boolean bool5 = this.modernTls;
            boolean bool6 = localRoute.modernTls;
            i = 0;
            if (bool5 == bool6)
              i = 1;
          }
        }
      }
    }
    return i;
  }

  public Address getAddress()
  {
    return this.address;
  }

  public Proxy getProxy()
  {
    return this.proxy;
  }

  public InetSocketAddress getSocketAddress()
  {
    return this.inetSocketAddress;
  }

  public int hashCode()
  {
    int i = 31 * (31 * (527 + this.address.hashCode()) + this.proxy.hashCode()) + this.inetSocketAddress.hashCode();
    if (this.modernTls);
    for (int j = i * 31; ; j = 0)
      return j + i;
  }

  public boolean isModernTls()
  {
    return this.modernTls;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Route
 * JD-Core Version:    0.6.0
 */