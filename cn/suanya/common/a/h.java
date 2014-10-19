package cn.suanya.common.a;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import cn.suanya.a.c;
import cn.suanya.a.d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class h
{
  private static String a = "/sdcard/suanya/apk/";
  private Context b;
  private String c;
  private File d;
  private int e;
  private Dialog f;
  private ProgressBar g;
  private Thread h;
  private boolean i = false;
  private Handler j = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        h.b(h.this).setProgress(h.a(h.this));
        return;
      case 2:
      }
      if (h.c(h.this) != null)
        h.c(h.this).dismiss();
      h.d(h.this);
    }
  };
  private Runnable k = new Runnable()
  {
    public void run()
    {
      try
      {
        URL localURL = new URL(h.e(h.this));
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        localHttpURLConnection.connect();
        if (localHttpURLConnection.getResponseCode() == 302)
        {
          localURL = new URL(localHttpURLConnection.getHeaderField("location"));
          localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        }
        int i = localHttpURLConnection.getContentLength();
        InputStream localInputStream = localHttpURLConnection.getInputStream();
        File localFile = new File(h.b());
        if (!localFile.exists())
          localFile.mkdir();
        h.a(h.this, new File(h.b() + localURL.getFile().substring(1 + localURL.getFile().lastIndexOf("/"))));
        FileOutputStream localFileOutputStream = new FileOutputStream(h.f(h.this));
        byte[] arrayOfByte = new byte[1024];
        int j = 0;
        int k = localInputStream.read(arrayOfByte);
        j += k;
        h.a(h.this, (int)(100.0F * (j / i)));
        h.g(h.this).sendEmptyMessage(1);
        if (k < 0)
          h.g(h.this).sendEmptyMessage(2);
        while (true)
        {
          localFileOutputStream.close();
          localInputStream.close();
          return;
          localFileOutputStream.write(arrayOfByte, 0, k);
          boolean bool = h.h(h.this);
          if (!bool)
            break;
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        localMalformedURLException.printStackTrace();
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  };

  public h(Context paramContext, String paramString)
  {
    this.b = paramContext;
    this.c = paramString;
  }

  private void c()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.b);
    localBuilder.setTitle("升级软件");
    View localView = LayoutInflater.from(this.b).inflate(a.d.progress, null);
    this.g = ((ProgressBar)localView.findViewById(a.c.progress));
    localBuilder.setView(localView);
    localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        h.a(h.this, true);
      }
    });
    this.f = localBuilder.create();
    this.f.show();
    d();
  }

  private void d()
  {
    this.h = new Thread(this.k);
    this.h.start();
  }

  private void e()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse("file://" + this.d.toString()), "application/vnd.android.package-archive");
    this.b.startActivity(localIntent);
  }

  public void a()
  {
    c();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.h
 * JD-Core Version:    0.6.0
 */