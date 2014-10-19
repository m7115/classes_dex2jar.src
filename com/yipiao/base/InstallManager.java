package com.yipiao.base;

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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InstallManager
{
  private static final int DOWN_OVER = 2;
  private static final int DOWN_UPDATE = 1;
  private static String savePath = "/sdcard/zhixing/";
  private File apkFile;
  private String apkUrl;
  private Thread downLoadThread;
  private Dialog downloadDialog;
  private boolean interceptFlag = false;
  private Context mContext;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        InstallManager.this.mProgress.setProgress(InstallManager.this.progress);
        return;
      case 2:
      }
      if (InstallManager.this.downloadDialog != null)
        InstallManager.this.downloadDialog.dismiss();
      InstallManager.this.installApk();
    }
  };
  private ProgressBar mProgress;
  private Runnable mdownApkRunnable = new Runnable()
  {
    public void run()
    {
      try
      {
        URL localURL = new URL(InstallManager.this.apkUrl);
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        localHttpURLConnection.connect();
        if (localHttpURLConnection.getResponseCode() == 302)
        {
          localURL = new URL(localHttpURLConnection.getHeaderField("location"));
          localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        }
        int i = localHttpURLConnection.getContentLength();
        InputStream localInputStream = localHttpURLConnection.getInputStream();
        File localFile = new File(InstallManager.savePath);
        if (!localFile.exists())
          localFile.mkdir();
        InstallManager.access$702(InstallManager.this, new File(InstallManager.savePath + localURL.getFile().substring(1 + localURL.getFile().lastIndexOf("/"))));
        FileOutputStream localFileOutputStream = new FileOutputStream(InstallManager.this.apkFile);
        byte[] arrayOfByte = new byte[1024];
        int j = 0;
        int k = localInputStream.read(arrayOfByte);
        j += k;
        InstallManager.access$002(InstallManager.this, (int)(100.0F * (j / i)));
        InstallManager.this.mHandler.sendEmptyMessage(1);
        if (k < 0)
          InstallManager.this.mHandler.sendEmptyMessage(2);
        while (true)
        {
          localFileOutputStream.close();
          localInputStream.close();
          return;
          localFileOutputStream.write(arrayOfByte, 0, k);
          boolean bool = InstallManager.this.interceptFlag;
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
  private int progress;

  public InstallManager(Context paramContext, String paramString)
  {
    this.mContext = paramContext;
    this.apkUrl = paramString;
  }

  private void downloadApk()
  {
    this.downLoadThread = new Thread(this.mdownApkRunnable);
    this.downLoadThread.start();
  }

  private void initConnHeader(HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
    paramHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.1.1; zh-cn; MI 2S Build/JRO03L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
    paramHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN, en-US");
    paramHttpURLConnection.setRequestProperty("Accept-Charset", "utf-8, iso-8859-1, utf-16, *;q=0.7");
    paramHttpURLConnection.setRequestProperty("x-GETzip", "supported");
    paramHttpURLConnection.setRequestProperty("Cache-Control", "no-cache");
  }

  private void installApk()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse("file://" + this.apkFile.toString()), "application/vnd.android.package-archive");
    this.mContext.startActivity(localIntent);
  }

  private void showDownloadDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
    localBuilder.setTitle("升级软件");
    View localView = LayoutInflater.from(this.mContext).inflate(2130903145, null);
    this.mProgress = ((ProgressBar)localView.findViewById(2131296340));
    localBuilder.setView(localView);
    localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        InstallManager.access$402(InstallManager.this, true);
      }
    });
    this.downloadDialog = localBuilder.create();
    this.downloadDialog.setCanceledOnTouchOutside(false);
    this.downloadDialog.show();
    downloadApk();
  }

  public void start()
  {
    showDownloadDialog();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.InstallManager
 * JD-Core Version:    0.6.0
 */