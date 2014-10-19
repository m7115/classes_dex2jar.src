package com.squareup.okhttp.internal;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract interface Dns
{
  public static final Dns DEFAULT = new Dns()
  {
    public InetAddress[] getAllByName(String paramString)
      throws UnknownHostException
    {
      if (paramString == null)
        throw new UnknownHostException("host == null");
      return InetAddress.getAllByName(paramString);
    }
  };

  public abstract InetAddress[] getAllByName(String paramString)
    throws UnknownHostException;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.Dns
 * JD-Core Version:    0.6.0
 */