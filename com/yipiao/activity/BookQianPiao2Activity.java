package com.yipiao.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import com.yipiao.Config;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.SimpleBaseViewAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.Train;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.HuocheMobile;
import com.yipiao.service.HuocheNew;
import com.yipiao.view.SYSignViewDialog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class BookQianPiao2Activity extends BaseActivity
{
  public static final int KEY_MSG = 10;
  public static final int KEY_SHOW_SIGN = 11;
  private static List<HuocheMobile> mhcs;
  static SimpleDateFormat mmss = new SimpleDateFormat("HH:mm:ss");
  private SimpleBaseViewAdapter adapter;
  private ChainQuery cq;
  private String[] likeSeatTypes = { "3", "O" };
  private List<String> log = new ArrayList();
  private ListView logListView;

  @SuppressLint({"HandlerLeak"})
  protected Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 10:
        String str = paramMessage.getData().getString("msg");
        BookQianPiao2Activity.this.log.add(0, BookQianPiao2Activity.mmss.format(new Date()) + " -- " + str);
        if (BookQianPiao2Activity.this.log.size() > 100)
          BookQianPiao2Activity.this.log.remove(100);
        BookQianPiao2Activity.this.adapter.notifyDataSetChanged();
        return;
      case 11:
      }
      BookQianPiao2Activity.this.signDialog = new SYSignViewDialog(BookQianPiao2Activity.this, new SYSignView.SignListenerBase()
      {
        public SYSignView.MulImage load()
          throws Exception
        {
          return BookQianPiao2Activity.this.nhc.orderSign();
        }

        public void onCancel()
        {
          BookQianPiao2Activity.access$902(BookQianPiao2Activity.this, "");
          BookQianPiao2Activity.this.endSign();
        }

        public void onFinish(String paramString)
        {
          BookQianPiao2Activity.access$902(BookQianPiao2Activity.this, paramString);
          BookQianPiao2Activity.this.endSign();
        }
      });
      BookQianPiao2Activity.this.signDialog.show();
    }
  };
  private String mSignStr;
  private ReturnTrain mTrain;
  protected HuocheMobile mhc;
  private Thread nBookTr;
  private ReturnTrain nTrain;
  protected HuocheNew nhc;
  private List<HuocheNew> nhcs;
  private List<UserInfo> passengers;
  protected boolean qianPiao = false;
  private TextView seatType;
  private TextView seatType2;
  private List<String> selectedTrains;
  public SYSignViewDialog signDialog;
  private List<Train> trains;

  private CharSequence[] allTrainCode()
  {
    CharSequence[] arrayOfCharSequence = new CharSequence[this.trains.size()];
    for (int i = 0; i < arrayOfCharSequence.length; i++)
      arrayOfCharSequence[i] = Html.fromHtml(((Train)this.trains.get(i)).getCode() + "<font size='-1' color='#999999'>(" + ((Train)this.trains.get(i)).getFromTime() + "-" + ((Train)this.trains.get(i)).getToTime() + ")</font>");
    return arrayOfCharSequence;
  }

  private void calSeatType(Train paramTrain, List<UserInfo> paramList)
  {
    String str = findSeatType(paramTrain);
    if (str != null)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        ((UserInfo)localIterator.next()).setSeatType(str);
    }
  }

  private void checkSuccess(String paramString, Exception paramException)
  {
    if (((paramException instanceof m)) && ("103".equals(((m)paramException).a())) && (this.qianPiao))
    {
      this.qianPiao = false;
      showMessage(paramString + "抢票成功");
    }
    try
    {
      Thread.sleep(2000L);
      label63: OrderQueryActivity.refreshOrder = true;
      startActivity(new Intent(this, OrderQueryActivity.class));
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label63;
    }
  }

  private String findSeatType(Train paramTrain)
  {
    for (int i = 0; i < this.likeSeatTypes.length; i++)
    {
      String str = this.likeSeatTypes[i];
      if (paramTrain.getSeatNum(str) >= this.passengers.size())
        return str;
    }
    return null;
  }

  private void showSelectedTrain()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = this.selectedTrains.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localStringBuffer.append(",").append(str);
    }
    localStringBuffer.delete(0, 1);
    setTv(2131296777, localStringBuffer.toString());
  }

  private boolean[] trainSelect()
  {
    int i = this.trains.size();
    boolean[] arrayOfBoolean = new boolean[i];
    for (int j = 0; j < i; j++)
    {
      Iterator localIterator = this.selectedTrains.iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        arrayOfBoolean[j] = false;
        if (!str.equalsIgnoreCase(((Train)this.trains.get(j)).getCode()))
          continue;
        arrayOfBoolean[j] = true;
      }
    }
    return arrayOfBoolean;
  }

  protected boolean checkPassengers()
  {
    if ((this.passengers == null) || (this.passengers.isEmpty()))
    {
      showToast("请添加购票人信息！");
      return false;
    }
    if (this.passengers.size() > 5)
    {
      showToast("一次最多添加5个购票人！");
      return false;
    }
    return true;
  }

  public void endQianPiao()
  {
    setVisibility(2131296773, 8);
    this.qianPiao = false;
    findViewById(2131296785).setVisibility(0);
    findViewById(2131296786).setVisibility(8);
  }

  public void endSign()
  {
    synchronized (this.nhc)
    {
      this.nhc.notify();
      return;
    }
  }

  protected int getMainLayout()
  {
    return 2130903068;
  }

  public void init()
  {
    if (checkNeedLaunch())
      return;
    getWindow().setFlags(128, 128);
    setClick(2131296785, this);
    setClick(2131296786, this);
    setClick(2131296778, this);
    setClick(2131296776, this);
    this.selectedTrains = new ArrayList();
    this.selectedTrains.add((String)this.app.getParms("trainCode"));
    this.trains = ((List)this.app.getParms("trains"));
    this.cq = ((ChainQuery)this.app.getParms("chainQuery"));
    this.passengers = ((List)this.app.getParms("passengers"));
    if ((this.trains != null) && (this.trains.size() > 0) && (this.trains.get(0) != null) && (((Train)this.trains.get(0)).getStartPayStr() != null))
      showMessage(((Train)this.trains.get(0)).getStartPayStr());
    showSelectedTrain();
    showPassenger();
    this.logListView = ((ListView)findViewById(2131296764));
    this.logListView.setEmptyView(findViewById(2131296784));
    this.adapter = new SimpleBaseViewAdapter(this, this.log);
    this.logListView.setAdapter(this.adapter);
    this.seatType = setTv(2131296781, this.cfg.seatTypesAll.getByCode(this.likeSeatTypes[0]).getName());
    this.seatType2 = setTv(2131296783, this.cfg.seatTypesAll.getByCode(this.likeSeatTypes[1]).getName());
    setClick(2131296780, this);
    setClick(2131296782, this);
    this.mhc = HuocheMobile.getInstance();
    this.nhc = HuocheNew.getInstance();
    this.nhcs = new ArrayList();
    showTip();
    int i = this.app.launchInfo.optInt("qianPiao_hcNum_n", 3);
    for (int j = 0; j < i; j++)
    {
      this.nhcs.add(this.nhc.copyHc());
      showMessage("网页引擎" + (j + 1) + "准备就绪");
    }
    if ((mhcs == null) || (mhcs.isEmpty()))
    {
      mhcs = new ArrayList();
      int k = this.app.launchInfo.optInt("qianPiao_hcNum_m", 3);
      int m = 0;
      if (k > 0)
      {
        mhcs.add(this.mhc);
        k--;
      }
      while (m < k)
      {
        mhcs.add(this.mhc.copyHc());
        m++;
      }
    }
    new Thread()
    {
      public void run()
      {
        Iterator localIterator = BookQianPiao2Activity.mhcs.iterator();
        int i = 0;
        while (localIterator.hasNext())
        {
          HuocheMobile localHuocheMobile = (HuocheMobile)localIterator.next();
          i++;
          try
          {
            localHuocheMobile.init(0);
            localHuocheMobile.autoLogin();
            BookQianPiao2Activity.this.showMessage("手机引擎" + i + "准备就绪");
          }
          catch (Exception localException)
          {
            n.b(localException);
            BookQianPiao2Activity.this.showMessage("手机引擎" + i + "初始化失败");
          }
        }
      }
    }
    .start();
    super.init();
  }

  public void mQianPiao()
  {
    this.mTrain = new ReturnTrain();
    Iterator localIterator = mhcs.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      HuocheMobile localHuocheMobile = (HuocheMobile)localIterator.next();
      i++;
      new MobileStread(localHuocheMobile, String.valueOf(i)).start();
    }
  }

  public void nQianPiao()
  {
    this.nTrain = new ReturnTrain();
    Iterator localIterator = this.nhcs.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      HuocheNew localHuocheNew = (HuocheNew)localIterator.next();
      i++;
      new QueryThread(localHuocheNew, String.valueOf(i)).start();
    }
  }

  public void nQianPiaoBook()
  {
    monitorenter;
    try
    {
      if ((this.nBookTr != null) && (this.nBookTr.isAlive()))
        return;
      this.nBookTr = new Thread()
      {
        Train nt;

        // ERROR //
        public void run()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   4: getfield 26	com/yipiao/activity/BookQianPiao2Activity:qianPiao	Z
          //   7: ifeq +324 -> 331
          //   10: aload_0
          //   11: aload_0
          //   12: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   15: invokestatic 30	com/yipiao/activity/BookQianPiao2Activity:access$800	(Lcom/yipiao/activity/BookQianPiao2Activity;)Lcom/yipiao/activity/BookQianPiao2Activity$ReturnTrain;
          //   18: invokevirtual 36	com/yipiao/activity/BookQianPiao2Activity$ReturnTrain:getTrain	()Lcom/yipiao/bean/Train;
          //   21: putfield 38	com/yipiao/activity/BookQianPiao2Activity$2:nt	Lcom/yipiao/bean/Train;
          //   24: aload_0
          //   25: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   28: new 40	java/lang/StringBuilder
          //   31: dup
          //   32: invokespecial 41	java/lang/StringBuilder:<init>	()V
          //   35: ldc 43
          //   37: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   40: aload_0
          //   41: getfield 38	com/yipiao/activity/BookQianPiao2Activity$2:nt	Lcom/yipiao/bean/Train;
          //   44: invokevirtual 53	com/yipiao/bean/Train:getCode	()Ljava/lang/String;
          //   47: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   50: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   53: invokevirtual 60	com/yipiao/activity/BookQianPiao2Activity:showMessage	(Ljava/lang/String;)V
          //   56: aload_0
          //   57: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   60: aload_0
          //   61: getfield 38	com/yipiao/activity/BookQianPiao2Activity$2:nt	Lcom/yipiao/bean/Train;
          //   64: aload_0
          //   65: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   68: invokestatic 64	com/yipiao/activity/BookQianPiao2Activity:access$500	(Lcom/yipiao/activity/BookQianPiao2Activity;)Ljava/util/List;
          //   71: invokestatic 68	com/yipiao/activity/BookQianPiao2Activity:access$600	(Lcom/yipiao/activity/BookQianPiao2Activity;Lcom/yipiao/bean/Train;Ljava/util/List;)V
          //   74: aload_0
          //   75: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   78: getfield 72	com/yipiao/activity/BookQianPiao2Activity:nhc	Lcom/yipiao/service/HuocheNew;
          //   81: aload_0
          //   82: getfield 38	com/yipiao/activity/BookQianPiao2Activity$2:nt	Lcom/yipiao/bean/Train;
          //   85: aload_0
          //   86: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   89: invokestatic 76	com/yipiao/activity/BookQianPiao2Activity:access$200	(Lcom/yipiao/activity/BookQianPiao2Activity;)Lcom/yipiao/bean/ChainQuery;
          //   92: invokevirtual 82	com/yipiao/service/HuocheNew:bookForQianPiao	(Lcom/yipiao/bean/Train;Lcom/yipiao/bean/ChainQuery;)Lcom/yipiao/bean/BookResult;
          //   95: pop
          //   96: aload_0
          //   97: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   100: getfield 26	com/yipiao/activity/BookQianPiao2Activity:qianPiao	Z
          //   103: ifeq -103 -> 0
          //   106: aload_0
          //   107: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   110: invokevirtual 85	com/yipiao/activity/BookQianPiao2Activity:showSign	()V
          //   113: aload_0
          //   114: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   117: getfield 72	com/yipiao/activity/BookQianPiao2Activity:nhc	Lcom/yipiao/service/HuocheNew;
          //   120: astore_3
          //   121: aload_3
          //   122: monitorenter
          //   123: aload_0
          //   124: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   127: getfield 72	com/yipiao/activity/BookQianPiao2Activity:nhc	Lcom/yipiao/service/HuocheNew;
          //   130: invokevirtual 90	java/lang/Object:wait	()V
          //   133: aload_3
          //   134: monitorexit
          //   135: aload_0
          //   136: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   139: invokestatic 94	com/yipiao/activity/BookQianPiao2Activity:access$900	(Lcom/yipiao/activity/BookQianPiao2Activity;)Ljava/lang/String;
          //   142: ifnull -142 -> 0
          //   145: aload_0
          //   146: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   149: invokestatic 94	com/yipiao/activity/BookQianPiao2Activity:access$900	(Lcom/yipiao/activity/BookQianPiao2Activity;)Ljava/lang/String;
          //   152: invokevirtual 100	java/lang/String:length	()I
          //   155: iconst_4
          //   156: if_icmplt -156 -> 0
          //   159: aload_0
          //   160: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   163: new 40	java/lang/StringBuilder
          //   166: dup
          //   167: invokespecial 41	java/lang/StringBuilder:<init>	()V
          //   170: ldc 102
          //   172: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   175: aload_0
          //   176: getfield 38	com/yipiao/activity/BookQianPiao2Activity$2:nt	Lcom/yipiao/bean/Train;
          //   179: invokevirtual 53	com/yipiao/bean/Train:getCode	()Ljava/lang/String;
          //   182: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   185: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   188: invokevirtual 60	com/yipiao/activity/BookQianPiao2Activity:showMessage	(Ljava/lang/String;)V
          //   191: aload_0
          //   192: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   195: getfield 72	com/yipiao/activity/BookQianPiao2Activity:nhc	Lcom/yipiao/service/HuocheNew;
          //   198: aload_0
          //   199: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   202: invokestatic 76	com/yipiao/activity/BookQianPiao2Activity:access$200	(Lcom/yipiao/activity/BookQianPiao2Activity;)Lcom/yipiao/bean/ChainQuery;
          //   205: aload_0
          //   206: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   209: invokestatic 94	com/yipiao/activity/BookQianPiao2Activity:access$900	(Lcom/yipiao/activity/BookQianPiao2Activity;)Ljava/lang/String;
          //   212: aload_0
          //   213: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   216: invokestatic 64	com/yipiao/activity/BookQianPiao2Activity:access$500	(Lcom/yipiao/activity/BookQianPiao2Activity;)Ljava/util/List;
          //   219: aload_0
          //   220: getfield 38	com/yipiao/activity/BookQianPiao2Activity$2:nt	Lcom/yipiao/bean/Train;
          //   223: invokevirtual 106	com/yipiao/service/HuocheNew:submitOrderForQianPiao	(Lcom/yipiao/bean/ChainQuery;Ljava/lang/String;Ljava/util/List;Lcom/yipiao/bean/Train;)V
          //   226: aload_0
          //   227: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   230: getfield 72	com/yipiao/activity/BookQianPiao2Activity:nhc	Lcom/yipiao/service/HuocheNew;
          //   233: invokevirtual 109	com/yipiao/service/HuocheNew:waitOrder	()Ljava/lang/String;
          //   236: astore 5
          //   238: aload 5
          //   240: ifnull +99 -> 339
          //   243: aload 5
          //   245: invokevirtual 100	java/lang/String:length	()I
          //   248: iconst_5
          //   249: if_icmple +90 -> 339
          //   252: new 111	cn/suanya/common/a/m
          //   255: dup
          //   256: ldc 113
          //   258: ldc 115
          //   260: invokespecial 118	cn/suanya/common/a/m:<init>	(Ljava/lang/String;Ljava/lang/String;)V
          //   263: athrow
          //   264: astore_1
          //   265: aload_1
          //   266: invokestatic 124	cn/suanya/common/a/n:b	(Ljava/lang/Throwable;)V
          //   269: aload_0
          //   270: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   273: ldc 126
          //   275: aload_1
          //   276: invokestatic 130	com/yipiao/activity/BookQianPiao2Activity:access$700	(Lcom/yipiao/activity/BookQianPiao2Activity;Ljava/lang/String;Ljava/lang/Exception;)V
          //   279: aload_0
          //   280: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   283: new 40	java/lang/StringBuilder
          //   286: dup
          //   287: invokespecial 41	java/lang/StringBuilder:<init>	()V
          //   290: ldc 132
          //   292: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   295: aload_1
          //   296: invokevirtual 135	java/lang/Exception:getMessage	()Ljava/lang/String;
          //   299: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   302: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   305: invokevirtual 60	com/yipiao/activity/BookQianPiao2Activity:showMessage	(Ljava/lang/String;)V
          //   308: aload_1
          //   309: instanceof 111
          //   312: ifeq -312 -> 0
          //   315: ldc 137
          //   317: aload_1
          //   318: invokestatic 141	cn/suanya/common/a/m:a	(Ljava/lang/String;Ljava/lang/Exception;)Z
          //   321: ifeq -321 -> 0
          //   324: aload_0
          //   325: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   328: invokevirtual 144	com/yipiao/activity/BookQianPiao2Activity:nQianPiao	()V
          //   331: return
          //   332: astore 4
          //   334: aload_3
          //   335: monitorexit
          //   336: aload 4
          //   338: athrow
          //   339: aload_0
          //   340: getfield 17	com/yipiao/activity/BookQianPiao2Activity$2:this$0	Lcom/yipiao/activity/BookQianPiao2Activity;
          //   343: ldc 146
          //   345: invokevirtual 60	com/yipiao/activity/BookQianPiao2Activity:showMessage	(Ljava/lang/String;)V
          //   348: goto -348 -> 0
          //
          // Exception table:
          //   from	to	target	type
          //   10	123	264	java/lang/Exception
          //   135	238	264	java/lang/Exception
          //   243	264	264	java/lang/Exception
          //   336	339	264	java/lang/Exception
          //   339	348	264	java/lang/Exception
          //   123	135	332	finally
          //   334	336	332	finally
        }
      };
      this.nBookTr.start();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2131296778)
      showPassenger();
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131296777:
    case 2131296779:
    case 2131296781:
    case 2131296783:
    case 2131296784:
    default:
    case 2131296785:
    case 2131296786:
    case 2131296778:
    case 2131296776:
    case 2131296780:
    case 2131296782:
    }
    while (true)
    {
      super.onClick(paramView);
      return;
      if (this.qianPiao)
      {
        endQianPiao();
        continue;
      }
      startQianPiao();
      continue;
      Intent localIntent = new Intent(this, PassengerSetActivity.class);
      this.app.putParms("passengers", this.passengers);
      startActivityForResult(localIntent, 2131296778);
      continue;
      showDialogTrainCodeMul(allTrainCode(), trainSelect());
      continue;
      showDialogSeatTypeAll(this.cfg.seatTypesAll.posByName(this.seatType.getText().toString()), 2131296781);
      continue;
      showDialogSeatTypeAll(this.cfg.seatTypesAll.posByName(this.seatType2.getText().toString()), 2131296783);
    }
  }

  protected void onPause()
  {
    endQianPiao();
    super.onPause();
  }

  protected void onResume()
  {
    endQianPiao();
    super.onResume();
  }

  protected void onRuleMessage(int paramInt, String paramString)
  {
    if ((paramInt == 101) && (this.signDialog != null) && (this.signDialog.signView != null))
    {
      String str = this.signDialog.signView.getSign();
      if ((str == null) || (str.length() == 0))
        this.signDialog.signView.setSign(paramString);
    }
    super.onRuleMessage(paramInt, paramString);
  }

  public void qianPiao()
  {
    mQianPiao();
    nQianPiao();
  }

  protected void seatTypeSelect(Note paramNote, int paramInt)
  {
    super.seatTypeSelect(paramNote, paramInt);
    if (paramInt == 2131296781)
      this.likeSeatTypes[0] = paramNote.getCode();
    if (paramInt == 2131296783)
      this.likeSeatTypes[1] = paramNote.getCode();
  }

  public void showMessage(String paramString)
  {
    Message localMessage = new Message();
    localMessage.what = 10;
    Bundle localBundle = new Bundle();
    localBundle.putString("msg", paramString);
    localMessage.setData(localBundle);
    this.mHandler.sendMessage(localMessage);
  }

  protected void showPassenger()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = this.passengers.iterator();
    while (localIterator.hasNext())
    {
      UserInfo localUserInfo = (UserInfo)localIterator.next();
      localStringBuffer.append(",").append(localUserInfo.getName());
    }
    localStringBuffer.delete(0, 1);
    setTv(2131296779, localStringBuffer.toString());
  }

  public void showSign()
  {
    this.mSignStr = "";
    Message localMessage = new Message();
    localMessage.what = 11;
    this.mHandler.sendMessage(localMessage);
  }

  protected void showTip()
  {
    showMessage(this.app.launchInfo.optString("qianPiao_tip", "<font color='#e61984'>抢票前请设置好购票人和首选和备选座位，点击车次可以设置多车次同时抢票。部分验证码需要手工输入。</font>"));
  }

  public void startQianPiao()
  {
    if (!checkPassengers())
    {
      showToast("请添加乘车人");
      return;
    }
    setVisibility(2131296773, 0);
    this.qianPiao = true;
    qianPiao();
    findViewById(2131296785).setVisibility(8);
    findViewById(2131296786).setVisibility(0);
  }

  protected void trainCodeSelectMul(boolean[] paramArrayOfBoolean)
  {
    this.selectedTrains.clear();
    for (int i = 0; i < paramArrayOfBoolean.length; i++)
    {
      if (paramArrayOfBoolean[i] == 0)
        continue;
      this.selectedTrains.add(((Train)this.trains.get(i)).getCode());
    }
    showSelectedTrain();
  }

  class MobileStread extends Thread
  {
    HuocheMobile mhcq;

    public MobileStread(HuocheMobile paramString, String arg3)
    {
      super();
      this.mhcq = paramString;
    }

    public void run()
    {
      if (BookQianPiao2Activity.this.qianPiao);
      while (true)
      {
        while (true)
        {
          try
          {
            if (!BookQianPiao2Activity.this.mTrain.isEmpty())
              continue;
            BookQianPiao2Activity.this.showMessage("手机引擎" + getName() + "开始查询");
            List localList = this.mhcq.queryforQianPiao(BookQianPiao2Activity.this.cq);
            i = 0;
            Iterator localIterator = localList.iterator();
            if (!localIterator.hasNext())
              continue;
            Train localTrain2 = (Train)localIterator.next();
            if ((!BookQianPiao2Activity.this.selectedTrains.contains(localTrain2.getCode())) || (BookQianPiao2Activity.this.findSeatType(localTrain2) == null))
              break label444;
            BookQianPiao2Activity.this.mTrain.putTrain(localTrain2);
            j = i + 1;
            break label448;
            BookQianPiao2Activity.this.showMessage("手机引擎" + getName() + "查询到" + localList.size() + "个车次" + i + "个有效");
            Train localTrain1 = BookQianPiao2Activity.this.mTrain.getTrain();
            if (localTrain1 == null)
              break;
            BookQianPiao2Activity.this.showMessage("手机引擎" + getName() + "提交订单:" + localTrain1.getCode());
            BookQianPiao2Activity.this.calSeatType(localTrain1, BookQianPiao2Activity.this.passengers);
            this.mhcq.qianPiao(BookQianPiao2Activity.this.cq, localTrain1, BookQianPiao2Activity.this.passengers);
          }
          catch (Exception localException1)
          {
            n.b(localException1);
            BookQianPiao2Activity.this.checkSuccess("手机引擎", localException1);
            if (!BookQianPiao2Activity.this.qianPiao)
              continue;
            BookQianPiao2Activity.this.showMessage("手机引擎" + getName() + localException1.getMessage());
            if (((localException1 instanceof m)) && (m.a("104", localException1)))
              BookQianPiao2Activity.this.mTrain.clear();
          }
          if ((!(localException1 instanceof m)) || (!m.a("102", localException1)))
            break;
          try
          {
            this.mhcq.init(2);
          }
          catch (Exception localException2)
          {
            n.b(localException1);
          }
        }
        break;
        return;
        label444: int j = i;
        label448: int i = j;
      }
    }
  }

  class QueryThread extends Thread
  {
    HuocheNew nhcq;

    public QueryThread(HuocheNew paramString, String arg3)
    {
      super();
      this.nhcq = paramString;
    }

    public void run()
    {
      this.nhcq.runRule("leftTicketInit");
      if ((BookQianPiao2Activity.this.qianPiao) && (BookQianPiao2Activity.this.nTrain.isEmpty()));
      while (true)
      {
        try
        {
          BookQianPiao2Activity.this.showMessage("网页引擎" + getName() + "开始查询");
          List localList = this.nhcq.queryforQianPiao(BookQianPiao2Activity.this.cq);
          i = 0;
          Iterator localIterator = localList.iterator();
          if (!localIterator.hasNext())
            continue;
          Train localTrain = (Train)localIterator.next();
          if ((!BookQianPiao2Activity.this.selectedTrains.contains(localTrain.getCode())) || (BookQianPiao2Activity.this.findSeatType(localTrain) == null))
            break label282;
          BookQianPiao2Activity.this.nTrain.putTrain(localTrain);
          BookQianPiao2Activity.this.nQianPiaoBook();
          j = i + 1;
          break label286;
          BookQianPiao2Activity.this.showMessage("网页引擎" + getName() + "查询到" + localList.size() + "个车次" + i + "个有效");
        }
        catch (Exception localException)
        {
          BookQianPiao2Activity.this.showMessage("网页引擎" + getName() + "出错" + localException.getMessage());
        }
        break;
        return;
        label282: int j = i;
        label286: int i = j;
      }
    }
  }

  class ReturnTrain
  {
    private List<Train> returnTrains = Collections.synchronizedList(new ArrayList());
    private int trainPos = 0;

    ReturnTrain()
    {
    }

    public void clear()
    {
      this.returnTrains.clear();
    }

    public Train getTrain()
    {
      if (this.returnTrains.isEmpty())
        return null;
      if (this.returnTrains.size() <= this.trainPos)
        this.trainPos = 0;
      Train localTrain = (Train)this.returnTrains.get(this.trainPos);
      this.trainPos = (1 + this.trainPos);
      return localTrain;
    }

    public boolean isEmpty()
    {
      return this.returnTrains.isEmpty();
    }

    public void putTrain(Train paramTrain)
    {
      this.returnTrains.add(paramTrain);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.BookQianPiao2Activity
 * JD-Core Version:    0.6.0
 */