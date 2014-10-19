package cn.suanya.common.a;

import android.util.Log;
import cn.suanya.common.persistent.SYPersistent;
import cn.suanya.common.persistent.SYPersitentFile;
import cn.suanya.common.ui.SYApplication;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class n
{
  static String a = "yipiao";
  static List<String> b = null;
  static SYPersistent c = null;
  static SimpleDateFormat d = new SimpleDateFormat("HH:mm:ss");

  public static List<String> a()
  {
    if (!c())
      return new ArrayList();
    if (b == null)
      b = (List)d().readObject("/syLog.log");
    if (b == null)
      b = new ArrayList();
    return b;
  }

  public static void a(String paramString)
  {
    d(paramString);
  }

  public static void a(String paramString, Throwable paramThrowable)
  {
    Log.i("yipiao", paramString, paramThrowable);
  }

  public static void a(Throwable paramThrowable)
  {
    Log.i("yipiao", paramThrowable.getMessage(), paramThrowable);
  }

  public static String b()
  {
    if (!c())
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = a().iterator();
    while (localIterator.hasNext())
      localStringBuilder.append((String)localIterator.next());
    return localStringBuilder.toString();
  }

  public static void b(String paramString)
  {
    Log.i("yipiao", paramString);
  }

  public static void b(String paramString, Throwable paramThrowable)
  {
    Log.e("yipiao", paramString, paramThrowable);
  }

  public static void b(Throwable paramThrowable)
  {
    Log.e("yipiao", paramThrowable.getMessage(), paramThrowable);
  }

  public static void c(String paramString)
  {
    Log.e("yipiao", paramString);
  }

  private static void c(String paramString, Throwable paramThrowable)
  {
    if (!c())
      return;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d.format(new Date()));
    if (paramThrowable != null)
    {
      localStringBuilder.append(paramString + "-->" + paramThrowable.getMessage()).append("\n");
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramThrowable.printStackTrace(new PrintStream(localByteArrayOutputStream));
      a().add(0, localStringBuilder.toString() + "\n" + localByteArrayOutputStream.toString());
    }
    while (true)
    {
      if (a().size() > 100)
        a().remove(-1 + a().size());
      e();
      return;
      localStringBuilder.append(paramString).append("\n");
      a().add(0, localStringBuilder.toString());
    }
  }

  private static boolean c()
  {
    if (SYApplication.app == null)
      return false;
    return SYApplication.app.isDebug();
  }

  private static SYPersistent d()
  {
    if ((c == null) && (c()))
      c = new SYPersitentFile(SYApplication.app.getFilesDir().getAbsolutePath());
    return c;
  }

  private static void d(String paramString)
  {
    c(paramString, null);
  }

  private static void e()
  {
    if (c())
      d().writeObject("/syLog.log", b);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.n
 * JD-Core Version:    0.6.0
 */