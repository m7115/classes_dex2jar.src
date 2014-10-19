package com.tencent.mm.sdk.channel;

import com.tencent.mm.a.a;

public class b
{
  static byte[] a(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramString1 != null)
      localStringBuffer.append(paramString1);
    localStringBuffer.append(553844737);
    localStringBuffer.append(paramString2);
    localStringBuffer.append("mMcShCsTr");
    return a.a(localStringBuffer.toString().substring(1, 9).getBytes()).getBytes();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.channel.b
 * JD-Core Version:    0.6.0
 */