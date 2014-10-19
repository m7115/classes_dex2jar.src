package cn.suanya.common.a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class b
{
  public static Bitmap a(String paramString)
  {
    try
    {
      URLConnection localURLConnection = new URL(paramString).openConnection();
      localURLConnection.connect();
      InputStream localInputStream = localURLConnection.getInputStream();
      Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
      localInputStream.close();
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static boolean a(Bitmap paramBitmap, String paramString)
  {
    try
    {
      File localFile = new File(paramString).getParentFile();
      if ((!localFile.exists()) && (!localFile.isDirectory()))
        localFile.mkdirs();
      FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      return true;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static byte[] a(Bitmap paramBitmap, boolean paramBoolean)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    if (paramBoolean)
      paramBitmap.recycle();
    byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
    try
    {
      localByteArrayOutputStream.close();
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return arrayOfByte;
  }

  public static Bitmap b(String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      if (!localFile.exists())
        return null;
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      Bitmap localBitmap = BitmapFactory.decodeStream(localFileInputStream);
      localFileInputStream.close();
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static String c(String paramString)
  {
    if (paramString == null)
      return String.valueOf(System.currentTimeMillis());
    return paramString + System.currentTimeMillis();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.b
 * JD-Core Version:    0.6.0
 */