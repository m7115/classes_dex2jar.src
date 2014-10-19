package com.yipiao.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.yipiao.YipiaoApplication;
import com.yipiao.activity.DownloadCancelActivity;
import com.yipiao.bean.RecommendItem;
import com.yipiao.view.MyToast;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class DownloadService extends Service
{
  private static DownloadService instance;
  private int cpuNums = Runtime.getRuntime().availableProcessors();
  private Map<Integer, Integer> download = new HashMap();
  private ExecutorService executorService = Executors.newFixedThreadPool(1 + this.cpuNums);
  private MyHandler myHandler;
  private NotificationManager nm;
  private Notification notification;

  private void Instanll(File paramFile, Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }

  private void downFile(String paramString1, int paramInt, String paramString2)
  {
    this.executorService.execute(new Runnable(paramString1, paramString2, paramInt)
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: new 40	java/lang/StringBuilder
        //   3: dup
        //   4: invokespecial 41	java/lang/StringBuilder:<init>	()V
        //   7: invokestatic 47	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
        //   10: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   13: ldc 53
        //   15: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   18: aload_0
        //   19: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   22: invokevirtual 60	com/yipiao/service/DownloadService:getApplicationContext	()Landroid/content/Context;
        //   25: invokevirtual 66	android/content/Context:getPackageName	()Ljava/lang/String;
        //   28: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   31: ldc 68
        //   33: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   36: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   39: astore_1
        //   40: new 73	java/net/URL
        //   43: dup
        //   44: aload_0
        //   45: getfield 24	com/yipiao/service/DownloadService$1:val$apkUrl	Ljava/lang/String;
        //   48: invokespecial 76	java/net/URL:<init>	(Ljava/lang/String;)V
        //   51: invokevirtual 80	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   54: checkcast 82	java/net/HttpURLConnection
        //   57: astore 16
        //   59: aload 16
        //   61: invokevirtual 85	java/net/HttpURLConnection:connect	()V
        //   64: aload 16
        //   66: invokevirtual 89	java/net/HttpURLConnection:getResponseCode	()I
        //   69: sipush 302
        //   72: if_icmpne +28 -> 100
        //   75: new 73	java/net/URL
        //   78: dup
        //   79: aload 16
        //   81: ldc 91
        //   83: invokevirtual 95	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
        //   86: invokespecial 76	java/net/URL:<init>	(Ljava/lang/String;)V
        //   89: invokevirtual 80	java/net/URL:openConnection	()Ljava/net/URLConnection;
        //   92: checkcast 82	java/net/HttpURLConnection
        //   95: astore 16
        //   97: goto -33 -> 64
        //   100: aload 16
        //   102: invokevirtual 98	java/net/HttpURLConnection:getContentLength	()I
        //   105: istore 17
        //   107: aload 16
        //   109: invokevirtual 102	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
        //   112: astore 18
        //   114: aload 18
        //   116: ifnull +737 -> 853
        //   119: new 104	java/io/File
        //   122: dup
        //   123: aload_1
        //   124: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
        //   127: astore 19
        //   129: aload 19
        //   131: invokevirtual 109	java/io/File:exists	()Z
        //   134: ifne +17 -> 151
        //   137: aload 19
        //   139: invokevirtual 112	java/io/File:isDirectory	()Z
        //   142: ifne +9 -> 151
        //   145: aload 19
        //   147: invokevirtual 115	java/io/File:mkdirs	()Z
        //   150: pop
        //   151: aload_0
        //   152: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   155: getstatic 119	java/io/File:separatorChar	C
        //   158: invokestatic 125	java/lang/String:valueOf	(C)Ljava/lang/String;
        //   161: ldc 127
        //   163: invokevirtual 131	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   166: astore 20
        //   168: new 104	java/io/File
        //   171: dup
        //   172: new 40	java/lang/StringBuilder
        //   175: dup
        //   176: invokespecial 41	java/lang/StringBuilder:<init>	()V
        //   179: aload_1
        //   180: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: aload 20
        //   185: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: ldc 133
        //   190: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   196: invokespecial 105	java/io/File:<init>	(Ljava/lang/String;)V
        //   199: astore 8
        //   201: new 135	java/io/FileOutputStream
        //   204: dup
        //   205: aload 8
        //   207: invokespecial 138	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
        //   210: astore 21
        //   212: sipush 1024
        //   215: newarray byte
        //   217: astore 25
        //   219: iconst_0
        //   220: istore 26
        //   222: aload 18
        //   224: aload 25
        //   226: invokevirtual 144	java/io/InputStream:read	([B)I
        //   229: istore 27
        //   231: iload 27
        //   233: iconst_m1
        //   234: if_icmpeq +186 -> 420
        //   237: aload_0
        //   238: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   241: invokestatic 148	com/yipiao/service/DownloadService:access$000	(Lcom/yipiao/service/DownloadService;)Ljava/util/Map;
        //   244: aload_0
        //   245: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   248: invokestatic 153	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   251: invokeinterface 159 2 0
        //   256: ifeq +164 -> 420
        //   259: iload 26
        //   261: iload 27
        //   263: iadd
        //   264: istore 28
        //   266: ldc 160
        //   268: iload 28
        //   270: i2f
        //   271: iload 17
        //   273: i2f
        //   274: fdiv
        //   275: fmul
        //   276: f2i
        //   277: istore 29
        //   279: iload 29
        //   281: aload_0
        //   282: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   285: invokestatic 148	com/yipiao/service/DownloadService:access$000	(Lcom/yipiao/service/DownloadService;)Ljava/util/Map;
        //   288: aload_0
        //   289: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   292: invokestatic 153	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   295: invokeinterface 164 2 0
        //   300: checkcast 150	java/lang/Integer
        //   303: invokevirtual 167	java/lang/Integer:intValue	()I
        //   306: isub
        //   307: iconst_1
        //   308: if_icmplt +95 -> 403
        //   311: aload_0
        //   312: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   315: invokestatic 148	com/yipiao/service/DownloadService:access$000	(Lcom/yipiao/service/DownloadService;)Ljava/util/Map;
        //   318: aload_0
        //   319: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   322: invokestatic 153	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   325: iload 29
        //   327: invokestatic 153	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   330: invokeinterface 171 3 0
        //   335: pop
        //   336: aload_0
        //   337: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   340: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   343: iconst_0
        //   344: iload 29
        //   346: invokestatic 153	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   349: invokevirtual 181	com/yipiao/service/DownloadService$MyHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
        //   352: astore 31
        //   354: new 183	android/os/Bundle
        //   357: dup
        //   358: invokespecial 184	android/os/Bundle:<init>	()V
        //   361: astore 32
        //   363: aload 32
        //   365: ldc 186
        //   367: aload_0
        //   368: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   371: invokevirtual 190	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   374: aload 31
        //   376: aload 32
        //   378: invokevirtual 196	android/os/Message:setData	(Landroid/os/Bundle;)V
        //   381: aload 31
        //   383: aload_0
        //   384: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   387: putfield 199	android/os/Message:arg1	I
        //   390: aload_0
        //   391: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   394: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   397: aload 31
        //   399: invokevirtual 203	com/yipiao/service/DownloadService$MyHandler:sendMessage	(Landroid/os/Message;)Z
        //   402: pop
        //   403: aload 21
        //   405: aload 25
        //   407: iconst_0
        //   408: iload 27
        //   410: invokevirtual 207	java/io/FileOutputStream:write	([BII)V
        //   413: iload 28
        //   415: istore 26
        //   417: goto -195 -> 222
        //   420: aload 21
        //   422: invokevirtual 210	java/io/FileOutputStream:close	()V
        //   425: aload 18
        //   427: invokevirtual 211	java/io/InputStream:close	()V
        //   430: aload_0
        //   431: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   434: invokestatic 148	com/yipiao/service/DownloadService:access$000	(Lcom/yipiao/service/DownloadService;)Ljava/util/Map;
        //   437: aload_0
        //   438: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   441: invokestatic 153	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   444: invokeinterface 159 2 0
        //   449: ifeq +68 -> 517
        //   452: aload_0
        //   453: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   456: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   459: iconst_1
        //   460: aload 8
        //   462: invokevirtual 181	com/yipiao/service/DownloadService$MyHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
        //   465: astore 37
        //   467: aload 37
        //   469: aload_0
        //   470: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   473: putfield 199	android/os/Message:arg1	I
        //   476: new 183	android/os/Bundle
        //   479: dup
        //   480: invokespecial 184	android/os/Bundle:<init>	()V
        //   483: astore 38
        //   485: aload 38
        //   487: ldc 186
        //   489: aload_0
        //   490: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   493: invokevirtual 190	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   496: aload 37
        //   498: aload 38
        //   500: invokevirtual 196	android/os/Message:setData	(Landroid/os/Bundle;)V
        //   503: aload_0
        //   504: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   507: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   510: aload 37
        //   512: invokevirtual 203	com/yipiao/service/DownloadService$MyHandler:sendMessage	(Landroid/os/Message;)Z
        //   515: pop
        //   516: return
        //   517: aload 8
        //   519: ifnull +17 -> 536
        //   522: aload 8
        //   524: invokevirtual 109	java/io/File:exists	()Z
        //   527: ifeq +9 -> 536
        //   530: aload 8
        //   532: invokevirtual 214	java/io/File:delete	()Z
        //   535: pop
        //   536: aload_0
        //   537: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   540: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   543: iconst_2
        //   544: new 40	java/lang/StringBuilder
        //   547: dup
        //   548: invokespecial 41	java/lang/StringBuilder:<init>	()V
        //   551: aload_0
        //   552: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   555: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   558: ldc 216
        //   560: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   563: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   566: invokevirtual 181	com/yipiao/service/DownloadService$MyHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
        //   569: astore 34
        //   571: aload 34
        //   573: aload_0
        //   574: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   577: putfield 199	android/os/Message:arg1	I
        //   580: aload_0
        //   581: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   584: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   587: aload 34
        //   589: invokevirtual 203	com/yipiao/service/DownloadService$MyHandler:sendMessage	(Landroid/os/Message;)Z
        //   592: pop
        //   593: return
        //   594: astore 24
        //   596: aload 8
        //   598: astore_3
        //   599: aload_3
        //   600: ifnull +15 -> 615
        //   603: aload_3
        //   604: invokevirtual 109	java/io/File:exists	()Z
        //   607: ifeq +8 -> 615
        //   610: aload_3
        //   611: invokevirtual 214	java/io/File:delete	()Z
        //   614: pop
        //   615: aload_0
        //   616: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   619: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   622: iconst_2
        //   623: new 40	java/lang/StringBuilder
        //   626: dup
        //   627: invokespecial 41	java/lang/StringBuilder:<init>	()V
        //   630: aload_0
        //   631: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   634: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   637: ldc 218
        //   639: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   642: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   645: invokevirtual 181	com/yipiao/service/DownloadService$MyHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
        //   648: astore 4
        //   650: aload 4
        //   652: aload_0
        //   653: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   656: putfield 199	android/os/Message:arg1	I
        //   659: aload_0
        //   660: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   663: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   666: aload 4
        //   668: invokevirtual 203	com/yipiao/service/DownloadService$MyHandler:sendMessage	(Landroid/os/Message;)Z
        //   671: pop
        //   672: return
        //   673: astore 12
        //   675: aconst_null
        //   676: astore 8
        //   678: aload 8
        //   680: ifnull +17 -> 697
        //   683: aload 8
        //   685: invokevirtual 109	java/io/File:exists	()Z
        //   688: ifeq +9 -> 697
        //   691: aload 8
        //   693: invokevirtual 214	java/io/File:delete	()Z
        //   696: pop
        //   697: aload_0
        //   698: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   701: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   704: iconst_2
        //   705: new 40	java/lang/StringBuilder
        //   708: dup
        //   709: invokespecial 41	java/lang/StringBuilder:<init>	()V
        //   712: aload_0
        //   713: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   716: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   719: ldc 220
        //   721: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   724: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   727: invokevirtual 181	com/yipiao/service/DownloadService$MyHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
        //   730: astore 13
        //   732: aload 13
        //   734: aload_0
        //   735: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   738: putfield 199	android/os/Message:arg1	I
        //   741: aload_0
        //   742: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   745: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   748: aload 13
        //   750: invokevirtual 203	com/yipiao/service/DownloadService$MyHandler:sendMessage	(Landroid/os/Message;)Z
        //   753: pop
        //   754: return
        //   755: astore 7
        //   757: aconst_null
        //   758: astore 8
        //   760: aload 8
        //   762: ifnull +17 -> 779
        //   765: aload 8
        //   767: invokevirtual 109	java/io/File:exists	()Z
        //   770: ifeq +9 -> 779
        //   773: aload 8
        //   775: invokevirtual 214	java/io/File:delete	()Z
        //   778: pop
        //   779: aload_0
        //   780: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   783: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   786: iconst_2
        //   787: new 40	java/lang/StringBuilder
        //   790: dup
        //   791: invokespecial 41	java/lang/StringBuilder:<init>	()V
        //   794: aload_0
        //   795: getfield 26	com/yipiao/service/DownloadService$1:val$name	Ljava/lang/String;
        //   798: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   801: ldc 222
        //   803: invokevirtual 56	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   806: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   809: invokevirtual 181	com/yipiao/service/DownloadService$MyHandler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
        //   812: astore 9
        //   814: aload 9
        //   816: aload_0
        //   817: getfield 28	com/yipiao/service/DownloadService$1:val$notificationId	I
        //   820: putfield 199	android/os/Message:arg1	I
        //   823: aload_0
        //   824: getfield 22	com/yipiao/service/DownloadService$1:this$0	Lcom/yipiao/service/DownloadService;
        //   827: invokestatic 175	com/yipiao/service/DownloadService:access$100	(Lcom/yipiao/service/DownloadService;)Lcom/yipiao/service/DownloadService$MyHandler;
        //   830: aload 9
        //   832: invokevirtual 203	com/yipiao/service/DownloadService$MyHandler:sendMessage	(Landroid/os/Message;)Z
        //   835: pop
        //   836: return
        //   837: astore 23
        //   839: goto -79 -> 760
        //   842: astore 22
        //   844: goto -166 -> 678
        //   847: astore_2
        //   848: aconst_null
        //   849: astore_3
        //   850: goto -251 -> 599
        //   853: return
        //
        // Exception table:
        //   from	to	target	type
        //   201	219	594	org/apache/http/client/ClientProtocolException
        //   222	231	594	org/apache/http/client/ClientProtocolException
        //   237	259	594	org/apache/http/client/ClientProtocolException
        //   266	403	594	org/apache/http/client/ClientProtocolException
        //   403	413	594	org/apache/http/client/ClientProtocolException
        //   420	516	594	org/apache/http/client/ClientProtocolException
        //   522	536	594	org/apache/http/client/ClientProtocolException
        //   536	593	594	org/apache/http/client/ClientProtocolException
        //   40	64	673	java/io/IOException
        //   64	97	673	java/io/IOException
        //   100	114	673	java/io/IOException
        //   119	151	673	java/io/IOException
        //   151	201	673	java/io/IOException
        //   40	64	755	java/lang/Exception
        //   64	97	755	java/lang/Exception
        //   100	114	755	java/lang/Exception
        //   119	151	755	java/lang/Exception
        //   151	201	755	java/lang/Exception
        //   201	219	837	java/lang/Exception
        //   222	231	837	java/lang/Exception
        //   237	259	837	java/lang/Exception
        //   266	403	837	java/lang/Exception
        //   403	413	837	java/lang/Exception
        //   420	516	837	java/lang/Exception
        //   522	536	837	java/lang/Exception
        //   536	593	837	java/lang/Exception
        //   201	219	842	java/io/IOException
        //   222	231	842	java/io/IOException
        //   237	259	842	java/io/IOException
        //   266	403	842	java/io/IOException
        //   403	413	842	java/io/IOException
        //   420	516	842	java/io/IOException
        //   522	536	842	java/io/IOException
        //   536	593	842	java/io/IOException
        //   40	64	847	org/apache/http/client/ClientProtocolException
        //   64	97	847	org/apache/http/client/ClientProtocolException
        //   100	114	847	org/apache/http/client/ClientProtocolException
        //   119	151	847	org/apache/http/client/ClientProtocolException
        //   151	201	847	org/apache/http/client/ClientProtocolException
      }
    });
  }

  public static DownloadService getInstance()
  {
    return instance;
  }

  private void onFinish(int paramInt)
  {
    this.notification.tickerText = "已完成下载";
    if (this.nm != null)
      this.nm.cancel(paramInt);
    if (this.download == null)
      stopSelf();
    do
    {
      return;
      if ((this.download.get(Integer.valueOf(paramInt)) != null) && (((Integer)this.download.get(Integer.valueOf(paramInt))).intValue() >= 100))
      {
        RecommendItem localRecommendItem = YipiaoService.getInstance().getRecommend(String.valueOf(paramInt));
        if ((localRecommendItem != null) && ("2".equals(localRecommendItem.getType())))
        {
          String str = YipiaoApplication.getApp().launchInfo.optString("WxShareSuccess", "下载成功，48小时内您可体验高级监控功能");
          SharedPreferences.Editor localEditor = YipiaoApplication.getApp().sp.edit();
          localEditor.putLong("shareTime", System.currentTimeMillis());
          localEditor.commit();
          MyToast.makeText(this, str, 0).show();
        }
      }
      this.download.remove(Integer.valueOf(paramInt));
    }
    while (!this.download.isEmpty());
    stopSelf();
  }

  public void cancelDownload(int paramInt)
  {
    if ((this.download != null) && (this.download.containsKey(Integer.valueOf(paramInt))))
      this.download.remove(Integer.valueOf(paramInt));
  }

  public void downNewFile(String paramString1, int paramInt, String paramString2)
  {
    this.notification = new Notification();
    this.notification.icon = 17301633;
    this.notification.tickerText = ("开始下载 " + paramString2);
    this.notification.when = System.currentTimeMillis();
    this.notification.defaults = 4;
    this.notification.flags = 34;
    Intent localIntent = new Intent(this, DownloadCancelActivity.class);
    localIntent.putExtra("id", paramInt);
    localIntent.putExtra("name", paramString2);
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, paramInt, localIntent, 0);
    if (this.download.containsKey(Integer.valueOf(paramInt)))
    {
      this.nm.cancel(paramInt);
      this.notification.tickerText = ("正在下载" + paramString2);
      this.notification.setLatestEventInfo(this, paramString2, "正在下载：" + this.download.get(Integer.valueOf(paramInt)) + "%", localPendingIntent);
      this.nm.notify(paramInt, this.notification);
      return;
    }
    this.notification.setLatestEventInfo(this, paramString2, "开始下载：0%", localPendingIntent);
    this.download.put(Integer.valueOf(paramInt), Integer.valueOf(0));
    this.nm.notify(paramInt, this.notification);
    MyToast.makeText(this, "开始下载 " + paramString2, 0).show();
    downFile(paramString1, paramInt, paramString2);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.nm = ((NotificationManager)getSystemService("notification"));
    this.myHandler = new MyHandler(Looper.myLooper(), this);
    instance = this;
  }

  public void onDestroy()
  {
    instance = null;
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    String str1;
    int i;
    String str2;
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      str1 = paramIntent.getStringExtra("url");
      i = paramIntent.getIntExtra("id", paramInt);
      str2 = paramIntent.getStringExtra("name");
      if ((str1.equals("")) || (str2.equals("")))
        MyToast.makeText(this, "出错，请稍候重试", 0).show();
    }
    while (true)
    {
      super.onStart(paramIntent, paramInt);
      return;
      downNewFile(str1, i, str2);
      continue;
      MyToast.makeText(this, "手机不存在SD卡，无法更新", 0).show();
    }
  }

  class MyHandler extends Handler
  {
    private Context context;

    public MyHandler(Looper paramContext, Context arg3)
    {
      super();
      Object localObject;
      this.context = localObject;
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if (paramMessage != null);
      switch (paramMessage.what)
      {
      default:
        return;
      case 0:
        Intent localIntent = new Intent(DownloadService.this, DownloadCancelActivity.class);
        localIntent.putExtra("id", paramMessage.arg1);
        localIntent.putExtra("name", paramMessage.getData().getString("name"));
        PendingIntent localPendingIntent = PendingIntent.getActivity(this.context, paramMessage.arg1, localIntent, 0);
        DownloadService.this.notification.setLatestEventInfo(DownloadService.this, paramMessage.getData().getString("name"), "正在下载：" + DownloadService.this.download.get(Integer.valueOf(paramMessage.arg1)) + "%", localPendingIntent);
        DownloadService.this.nm.notify(paramMessage.arg1, DownloadService.this.notification);
        return;
      case 1:
        DownloadService.this.Instanll((File)paramMessage.obj, this.context);
        DownloadService.this.onFinish(paramMessage.arg1);
        return;
      case 2:
      }
      MyToast.makeText(this.context, paramMessage.obj.toString(), 0).show();
      DownloadService.this.onFinish(paramMessage.arg1);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.DownloadService
 * JD-Core Version:    0.6.0
 */