package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConnectionPool
{
  private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000L;
  private static final int MAX_CONNECTIONS_TO_CLEANUP = 2;
  private static final ConnectionPool systemDefault;
  private final LinkedList<Connection> connections = new LinkedList();
  private final Runnable connectionsCleanupRunnable = new Runnable()
  {
    public void run()
    {
      ArrayList localArrayList = new ArrayList(2);
      int k;
      for (int i = 0; ; i = k)
        while (true)
        {
          synchronized (ConnectionPool.this)
          {
            ListIterator localListIterator1 = ConnectionPool.this.connections.listIterator(ConnectionPool.this.connections.size());
            if (!localListIterator1.hasPrevious())
              continue;
            Connection localConnection2 = (Connection)localListIterator1.previous();
            if ((localConnection2.isAlive()) && (!localConnection2.isExpired(ConnectionPool.this.keepAliveDurationNs)))
              continue;
            localListIterator1.remove();
            localArrayList.add(localConnection2);
            if (localArrayList.size() != 2)
              break label264;
            ListIterator localListIterator2 = ConnectionPool.this.connections.listIterator(ConnectionPool.this.connections.size());
            if ((!localListIterator2.hasPrevious()) || (i <= ConnectionPool.this.maxIdleConnections))
              continue;
            Connection localConnection1 = (Connection)localListIterator2.previous();
            if (!localConnection1.isIdle())
              break label258;
            localArrayList.add(localConnection1);
            localListIterator2.remove();
            j = i - 1;
            break label270;
            if (!localConnection2.isIdle())
              break label264;
            k = i + 1;
            break;
            Iterator localIterator = localArrayList.iterator();
            if (localIterator.hasNext())
              Util.closeQuietly((Connection)localIterator.next());
          }
          return;
          label258: int j = i;
          break label270;
          label264: k = i;
          break;
          label270: i = j;
        }
    }
  };
  private final ExecutorService executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
  private final long keepAliveDurationNs;
  private final int maxIdleConnections;

  static
  {
    String str1 = System.getProperty("http.keepAlive");
    String str2 = System.getProperty("http.keepAliveDuration");
    String str3 = System.getProperty("http.maxConnections");
    long l;
    if (str2 != null)
      l = Long.parseLong(str2);
    while ((str1 != null) && (!Boolean.parseBoolean(str1)))
    {
      systemDefault = new ConnectionPool(0, l);
      return;
      l = 300000L;
    }
    if (str3 != null)
    {
      systemDefault = new ConnectionPool(Integer.parseInt(str3), l);
      return;
    }
    systemDefault = new ConnectionPool(5, l);
  }

  public ConnectionPool(int paramInt, long paramLong)
  {
    this.maxIdleConnections = paramInt;
    this.keepAliveDurationNs = (1000L * (paramLong * 1000L));
  }

  public static ConnectionPool getDefault()
  {
    return systemDefault;
  }

  private void waitForCleanupCallableToRun()
  {
    try
    {
      this.executorService.submit(new Runnable()
      {
        public void run()
        {
        }
      }).get();
      return;
    }
    catch (Exception localException)
    {
    }
    throw new AssertionError();
  }

  public void evictAll()
  {
    monitorenter;
    try
    {
      ArrayList localArrayList = new ArrayList(this.connections);
      this.connections.clear();
      monitorexit;
      int i = localArrayList.size();
      for (int j = 0; j < i; j++)
        Util.closeQuietly((Closeable)localArrayList.get(j));
    }
    finally
    {
      monitorexit;
    }
  }

  public Connection get(Address paramAddress)
  {
    monitorenter;
    while (true)
    {
      try
      {
        ListIterator localListIterator = this.connections.listIterator(this.connections.size());
        if (localListIterator.hasPrevious())
        {
          localConnection = (Connection)localListIterator.previous();
          if ((!localConnection.getRoute().getAddress().equals(paramAddress)) || (!localConnection.isAlive()) || (System.nanoTime() - localConnection.getIdleStartTimeNs() >= this.keepAliveDurationNs))
            continue;
          localListIterator.remove();
          boolean bool = localConnection.isSpdy();
          if (bool)
            continue;
          try
          {
            Platform.get().tagSocket(localConnection.getSocket());
            if ((localConnection == null) || (!localConnection.isSpdy()))
              continue;
            this.connections.addFirst(localConnection);
            this.executorService.execute(this.connectionsCleanupRunnable);
            monitorexit;
            return localConnection;
          }
          catch (SocketException localSocketException)
          {
            Util.closeQuietly(localConnection);
            Platform.get().logW("Unable to tagSocket(): " + localSocketException);
          }
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Connection localConnection = null;
    }
  }

  public int getConnectionCount()
  {
    monitorenter;
    try
    {
      int i = this.connections.size();
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  List<Connection> getConnections()
  {
    waitForCleanupCallableToRun();
    monitorenter;
    try
    {
      ArrayList localArrayList = new ArrayList(this.connections);
      return localArrayList;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int getHttpConnectionCount()
  {
    monitorenter;
    int i = 0;
    while (true)
    {
      try
      {
        Iterator localIterator = this.connections.iterator();
        if (!localIterator.hasNext())
          continue;
        boolean bool = ((Connection)localIterator.next()).isSpdy();
        if (!bool)
        {
          j = i + 1;
          i = j;
          continue;
          return i;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = i;
    }
  }

  public int getSpdyConnectionCount()
  {
    monitorenter;
    int i = 0;
    while (true)
    {
      try
      {
        Iterator localIterator = this.connections.iterator();
        if (!localIterator.hasNext())
          continue;
        boolean bool = ((Connection)localIterator.next()).isSpdy();
        if (bool)
        {
          j = i + 1;
          i = j;
          continue;
          return i;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = i;
    }
  }

  // ERROR //
  public void recycle(Connection paramConnection)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 210	com/squareup/okhttp/Connection:isSpdy	()Z
    //   4: ifeq +4 -> 8
    //   7: return
    //   8: aload_1
    //   9: invokevirtual 275	com/squareup/okhttp/Connection:clearOwner	()Z
    //   12: ifeq -5 -> 7
    //   15: aload_1
    //   16: invokevirtual 197	com/squareup/okhttp/Connection:isAlive	()Z
    //   19: ifne +8 -> 27
    //   22: aload_1
    //   23: invokestatic 159	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   26: return
    //   27: invokestatic 215	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   30: aload_1
    //   31: invokevirtual 219	com/squareup/okhttp/Connection:getSocket	()Ljava/net/Socket;
    //   34: invokevirtual 278	com/squareup/okhttp/internal/Platform:untagSocket	(Ljava/net/Socket;)V
    //   37: aload_0
    //   38: monitorenter
    //   39: aload_0
    //   40: getfield 67	com/squareup/okhttp/ConnectionPool:connections	Ljava/util/LinkedList;
    //   43: aload_1
    //   44: invokevirtual 227	java/util/LinkedList:addFirst	(Ljava/lang/Object;)V
    //   47: aload_1
    //   48: invokevirtual 281	com/squareup/okhttp/Connection:incrementRecycleCount	()V
    //   51: aload_1
    //   52: invokevirtual 284	com/squareup/okhttp/Connection:resetIdleStartTime	()V
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_0
    //   58: getfield 93	com/squareup/okhttp/ConnectionPool:executorService	Ljava/util/concurrent/ExecutorService;
    //   61: aload_0
    //   62: getfield 100	com/squareup/okhttp/ConnectionPool:connectionsCleanupRunnable	Ljava/lang/Runnable;
    //   65: invokeinterface 231 2 0
    //   70: return
    //   71: astore_2
    //   72: invokestatic 215	com/squareup/okhttp/internal/Platform:get	()Lcom/squareup/okhttp/internal/Platform;
    //   75: new 233	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 234	java/lang/StringBuilder:<init>	()V
    //   82: ldc_w 286
    //   85: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_2
    //   89: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: invokevirtual 251	com/squareup/okhttp/internal/Platform:logW	(Ljava/lang/String;)V
    //   98: aload_1
    //   99: invokestatic 159	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   102: return
    //   103: astore_3
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_3
    //   107: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   27	37	71	java/net/SocketException
    //   39	57	103	finally
    //   104	106	103	finally
  }

  public void share(Connection paramConnection)
  {
    if (!paramConnection.isSpdy())
      throw new IllegalArgumentException();
    this.executorService.execute(this.connectionsCleanupRunnable);
    if (paramConnection.isAlive())
    {
      monitorenter;
      try
      {
        this.connections.addFirst(paramConnection);
        return;
      }
      finally
      {
        monitorexit;
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.ConnectionPool
 * JD-Core Version:    0.6.0
 */