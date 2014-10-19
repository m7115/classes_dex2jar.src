package cn.suanya.common.a;

import cn.suanya.common.bean.NameValue;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class s
{
  public static ByteArrayOutputStream a(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i <= -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return localByteArrayOutputStream;
  }

  public static String a(List<NameValue> paramList, String paramString)
    throws UnsupportedEncodingException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramList == null)
      return "";
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      NameValue localNameValue = (NameValue)localIterator.next();
      String str1 = localNameValue.getValue();
      if (str1 != null);
      for (String str2 = URLEncoder.encode(str1, paramString); ; str2 = "")
      {
        String str3 = localNameValue.getName();
        if ((str3 != null) && (str3.length() != 0))
          break;
        return str1;
      }
      String str4 = URLEncoder.encode(localNameValue.getName(), paramString);
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append(str4);
      if ("N_O_V".equals(str1))
        continue;
      localStringBuilder.append("=");
      localStringBuilder.append(str2);
    }
    return localStringBuilder.toString();
  }

  public static void a(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static boolean a(Collection<?> paramCollection)
  {
    if (paramCollection == null)
      return false;
    return paramCollection.isEmpty();
  }

  public static void b(InputStream paramInputStream)
  {
    a(paramInputStream);
  }

  public static String c(InputStream paramInputStream)
    throws IOException
  {
    return a(paramInputStream).toString("utf-8");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.s
 * JD-Core Version:    0.6.0
 */