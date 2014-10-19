package cn.suanya.common.net;

import cn.suanya.common.a.n;
import cn.suanya.common.bean.NameValue;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HttpUtil
{
  public static ByteArrayOutputStream byteArrayFromInputStream(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream;
    if (paramInputStream == null)
      localByteArrayOutputStream = null;
    while (true)
    {
      return localByteArrayOutputStream;
      byte[] arrayOfByte = new byte[1024];
      localByteArrayOutputStream = new ByteArrayOutputStream();
      try
      {
        while (true)
        {
          int i = paramInputStream.read(arrayOfByte);
          if (i <= -1)
            break;
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
      }
      catch (Exception localException)
      {
        n.b(localException);
      }
    }
    return localByteArrayOutputStream;
  }

  public static String getCharset(List<NameValue> paramList)
  {
    if (paramList == null)
      return "UTF-8";
    for (int i = 0; i < paramList.size(); i++)
    {
      NameValue localNameValue = (NameValue)paramList.get(i);
      if (!"charset".equalsIgnoreCase(localNameValue.getName()))
        continue;
      paramList.remove(i);
      return localNameValue.getValue();
    }
    return "UTF-8";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.HttpUtil
 * JD-Core Version:    0.6.0
 */