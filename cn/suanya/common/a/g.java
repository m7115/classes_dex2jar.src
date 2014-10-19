package cn.suanya.common.a;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import cn.suanya.common.ui.SYApplication;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class g
{
  private static g b;
  private ThreadPoolExecutor a;

  protected g()
  {
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    this.a = new ThreadPoolExecutor(5, 10, 1L, TimeUnit.SECONDS, localLinkedBlockingQueue);
  }

  public static g a()
  {
    if (b == null)
      b = new g();
    return b;
  }

  public Bitmap a(String paramString, boolean paramBoolean, ImageView paramImageView)
  {
    return a(paramString, paramBoolean, new b(paramImageView));
  }

  public Bitmap a(String paramString, boolean paramBoolean, a parama)
  {
    String str = Environment.getExternalStorageDirectory() + "/Android/data/" + SYApplication.app.getPackageName() + "/images/" + a.a(paramString.getBytes());
    if ((!paramBoolean) && ("mounted".equals(Environment.getExternalStorageState())))
    {
      Bitmap localBitmap = b.b(str);
      if (localBitmap != null)
      {
        parama.imageLoaded(localBitmap);
        return localBitmap;
      }
    }
    1 local1 = new Handler(parama)
    {
      public void handleMessage(Message paramMessage)
      {
        this.a.imageLoaded((Bitmap)paramMessage.obj);
      }
    };
    this.a.execute(new Runnable(paramString, str, local1)
    {
      public void run()
      {
        Bitmap localBitmap = b.a(this.a);
        b.a(localBitmap, this.b);
        Message localMessage = this.c.obtainMessage(0, localBitmap);
        this.c.sendMessage(localMessage);
      }
    });
    return null;
  }

  public static abstract interface a
  {
    public abstract void imageLoaded(Bitmap paramBitmap);
  }

  public class b
    implements g.a
  {
    private ImageView b;

    public b(ImageView arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public void imageLoaded(Bitmap paramBitmap)
    {
      if (paramBitmap != null)
        this.b.setImageBitmap(paramBitmap);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.g
 * JD-Core Version:    0.6.0
 */