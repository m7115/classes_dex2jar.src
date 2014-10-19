package cn.suanya.common.a;

import android.content.res.AssetManager;
import cn.suanya.common.net.HttpUrlImpl;
import cn.suanya.common.ui.SYApplication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class f
{
  private static f b;
  private ThreadPoolExecutor a;

  protected f()
  {
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    this.a = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, localLinkedBlockingQueue);
  }

  public static f a()
  {
    if (b == null)
      b = new f();
    return b;
  }

  public InputStream a(String paramString1, String paramString2)
    throws Exception
  {
    File localFile = new File(paramString1);
    if ((localFile.exists()) && (localFile.isFile()) && (localFile.canRead()));
    while (true)
    {
      try
      {
        localFileInputStream = new FileInputStream(localFile);
        if (localFileInputStream == null)
          break;
        return localFileInputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        localFileNotFoundException.printStackTrace();
      }
      FileInputStream localFileInputStream = null;
    }
    InputStream localInputStream = new HttpUrlImpl(30000, 30000).get(paramString2);
    if (!localFile.getParentFile().exists())
      localFile.getParentFile().mkdirs();
    FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
    byte[] arrayOfByte = new byte[1024];
    for (int i = localInputStream.read(arrayOfByte); i >= 0; i = localInputStream.read(arrayOfByte))
      localFileOutputStream.write(arrayOfByte, 0, i);
    localFileOutputStream.close();
    return new FileInputStream(localFile);
  }

  public InputStream a(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    try
    {
      InputStream localInputStream2 = b(paramString1, paramString2);
      localInputStream1 = localInputStream2;
      if (localInputStream1 != null)
        return localInputStream1;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        localIOException.printStackTrace();
        InputStream localInputStream1 = null;
      }
    }
    return a(paramString2, paramString3);
  }

  public InputStream b(String paramString1, String paramString2)
    throws IOException
  {
    SYApplication localSYApplication = SYApplication.app;
    if (paramString1 != null)
      try
      {
        InputStream localInputStream2 = localSYApplication.getAssets().open(paramString1);
        localInputStream1 = localInputStream2;
        if (localInputStream1 != null)
          return localInputStream1;
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          localIOException.printStackTrace();
          InputStream localInputStream1 = null;
        }
      }
    return new FileInputStream(new File(paramString2));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.f
 * JD-Core Version:    0.6.0
 */