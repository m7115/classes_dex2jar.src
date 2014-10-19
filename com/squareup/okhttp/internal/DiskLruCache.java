package com.squareup.okhttp.internal;

import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DiskLruCache
  implements Closeable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream()
  {
    public void write(int paramInt)
      throws IOException
    {
    }
  };
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Runnable cleanupRunnable = new Runnable()
  {
    public void run()
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.this.journalWriter == null)
          return;
      }
      try
      {
        DiskLruCache.this.trimToSize();
        if (DiskLruCache.this.journalRebuildRequired())
        {
          DiskLruCache.this.rebuildJournal();
          DiskLruCache.access$402(DiskLruCache.this, 0);
        }
        monitorexit;
        return;
        localObject = finally;
        monitorexit;
        throw localObject;
      }
      catch (IOException localIOException)
      {
      }
      throw new RuntimeException(localIOException);
    }
  };
  private final File directory;
  final ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true));
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  private BufferedSink journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;

  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBackup = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
  }

  private void checkNotClosed()
  {
    if (this.journalWriter == null)
      throw new IllegalStateException("cache is closed");
  }

  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    monitorenter;
    Entry localEntry;
    try
    {
      localEntry = paramEditor.entry;
      if (localEntry.currentEditor != paramEditor)
        throw new IllegalStateException();
    }
    finally
    {
      monitorexit;
    }
    int i = 0;
    if (paramBoolean)
    {
      boolean bool = localEntry.readable;
      i = 0;
      if (!bool)
        for (int j = 0; ; j++)
        {
          int k = this.valueCount;
          i = 0;
          if (j >= k)
            break;
          if (paramEditor.written[j] == 0)
          {
            paramEditor.abort();
            throw new IllegalStateException("Newly created entry didn't create value for index " + j);
          }
          if (localEntry.getDirtyFile(j).exists())
            continue;
          paramEditor.abort();
          monitorexit;
          return;
        }
    }
    while (true)
    {
      if (i < this.valueCount)
      {
        File localFile1 = localEntry.getDirtyFile(i);
        if (paramBoolean)
        {
          if (localFile1.exists())
          {
            File localFile2 = localEntry.getCleanFile(i);
            localFile1.renameTo(localFile2);
            long l2 = localEntry.lengths[i];
            long l3 = localFile2.length();
            localEntry.lengths[i] = l3;
            this.size = (l3 + (this.size - l2));
          }
        }
        else
          deleteIfExists(localFile1);
      }
      else
      {
        this.redundantOpCount = (1 + this.redundantOpCount);
        Entry.access$702(localEntry, null);
        if ((paramBoolean | localEntry.readable))
        {
          Entry.access$602(localEntry, true);
          this.journalWriter.writeUtf8("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
          if (paramBoolean)
          {
            long l1 = this.nextSequenceNumber;
            this.nextSequenceNumber = (1L + l1);
            Entry.access$1202(localEntry, l1);
          }
        }
        while (true)
        {
          this.journalWriter.flush();
          if ((this.size <= this.maxSize) && (!journalRebuildRequired()))
            break;
          this.executorService.execute(this.cleanupRunnable);
          break;
          this.lruEntries.remove(localEntry.key);
          this.journalWriter.writeUtf8("REMOVE " + localEntry.key + '\n');
        }
      }
      i++;
    }
  }

  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((!paramFile.delete()) && (paramFile.exists()))
      throw new IOException("failed to delete " + paramFile);
  }

  private Editor edit(String paramString, long paramLong)
    throws IOException
  {
    monitorenter;
    while (true)
    {
      Entry localEntry1;
      Editor localEditor2;
      try
      {
        checkNotClosed();
        validateKey(paramString);
        localEntry1 = (Entry)this.lruEntries.get(paramString);
        if (paramLong == -1L)
          continue;
        if (localEntry1 == null)
          continue;
        long l = localEntry1.sequenceNumber;
        if (l == paramLong)
          continue;
        localEditor2 = null;
        return localEditor2;
        if (localEntry1 == null)
        {
          Entry localEntry3 = new Entry(paramString, null);
          this.lruEntries.put(paramString, localEntry3);
          localEntry2 = localEntry3;
          localEditor2 = new Editor(localEntry2, null);
          Entry.access$702(localEntry2, localEditor2);
          this.journalWriter.writeUtf8("DIRTY " + paramString + '\n');
          this.journalWriter.flush();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Editor localEditor1 = localEntry1.currentEditor;
      if (localEditor1 != null)
      {
        localEditor2 = null;
        continue;
      }
      Entry localEntry2 = localEntry1;
    }
  }

  private static String inputStreamToString(InputStream paramInputStream)
    throws IOException
  {
    OkBuffer localOkBuffer = Util.readFully(Okio.source(paramInputStream));
    return localOkBuffer.readUtf8(localOkBuffer.size());
  }

  private boolean journalRebuildRequired()
  {
    return (this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size());
  }

  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong)
    throws IOException
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("maxSize <= 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("valueCount <= 0");
    File localFile1 = new File(paramFile, "journal.bkp");
    File localFile2;
    if (localFile1.exists())
    {
      localFile2 = new File(paramFile, "journal");
      if (!localFile2.exists())
        break label139;
      localFile1.delete();
    }
    while (true)
    {
      DiskLruCache localDiskLruCache1 = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
      if (localDiskLruCache1.journalFile.exists())
        try
        {
          localDiskLruCache1.readJournal();
          localDiskLruCache1.processJournal();
          localDiskLruCache1.journalWriter = Okio.buffer(Okio.sink(new FileOutputStream(localDiskLruCache1.journalFile, true)));
          return localDiskLruCache1;
          label139: renameTo(localFile1, localFile2, false);
        }
        catch (IOException localIOException)
        {
          Platform.get().logW("DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
          localDiskLruCache1.delete();
        }
    }
    paramFile.mkdirs();
    DiskLruCache localDiskLruCache2 = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
    localDiskLruCache2.rebuildJournal();
    return localDiskLruCache2;
  }

  private void processJournal()
    throws IOException
  {
    deleteIfExists(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      if (localEntry.currentEditor == null)
      {
        for (int j = 0; j < this.valueCount; j++)
          this.size += localEntry.lengths[j];
        continue;
      }
      Entry.access$702(localEntry, null);
      for (int i = 0; i < this.valueCount; i++)
      {
        deleteIfExists(localEntry.getCleanFile(i));
        deleteIfExists(localEntry.getDirtyFile(i));
      }
      localIterator.remove();
    }
  }

  // ERROR //
  private void readJournal()
    throws IOException
  {
    // Byte code:
    //   0: new 442	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: getfield 136	com/squareup/okhttp/internal/DiskLruCache:journalFile	Ljava/io/File;
    //   8: invokespecial 444	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   11: invokestatic 347	com/squareup/okhttp/internal/okio/Okio:source	(Ljava/io/InputStream;)Lcom/squareup/okhttp/internal/okio/Source;
    //   14: invokestatic 447	com/squareup/okhttp/internal/okio/Okio:buffer	(Lcom/squareup/okhttp/internal/okio/Source;)Lcom/squareup/okhttp/internal/okio/BufferedSource;
    //   17: astore_1
    //   18: aload_1
    //   19: iconst_1
    //   20: invokeinterface 453 2 0
    //   25: astore_3
    //   26: aload_1
    //   27: iconst_1
    //   28: invokeinterface 453 2 0
    //   33: astore 4
    //   35: aload_1
    //   36: iconst_1
    //   37: invokeinterface 453 2 0
    //   42: astore 5
    //   44: aload_1
    //   45: iconst_1
    //   46: invokeinterface 453 2 0
    //   51: astore 6
    //   53: aload_1
    //   54: iconst_1
    //   55: invokeinterface 453 2 0
    //   60: astore 7
    //   62: ldc 29
    //   64: aload_3
    //   65: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifeq +54 -> 122
    //   71: ldc 38
    //   73: aload 4
    //   75: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   78: ifeq +44 -> 122
    //   81: aload_0
    //   82: getfield 129	com/squareup/okhttp/internal/DiskLruCache:appVersion	I
    //   85: invokestatic 464	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   88: aload 5
    //   90: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq +29 -> 122
    //   96: aload_0
    //   97: getfield 142	com/squareup/okhttp/internal/DiskLruCache:valueCount	I
    //   100: invokestatic 464	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   103: aload 6
    //   105: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   108: ifeq +14 -> 122
    //   111: ldc_w 466
    //   114: aload 7
    //   116: invokevirtual 459	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   119: ifne +77 -> 196
    //   122: new 151	java/io/IOException
    //   125: dup
    //   126: new 225	java/lang/StringBuilder
    //   129: dup
    //   130: invokespecial 226	java/lang/StringBuilder:<init>	()V
    //   133: ldc_w 468
    //   136: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: aload_3
    //   140: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc_w 470
    //   146: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 4
    //   151: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: ldc_w 470
    //   157: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: aload 6
    //   162: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc_w 470
    //   168: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: aload 7
    //   173: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: ldc_w 472
    //   179: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   185: invokespecial 317	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   188: athrow
    //   189: astore_2
    //   190: aload_1
    //   191: invokestatic 476	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   194: aload_2
    //   195: athrow
    //   196: iconst_0
    //   197: istore 8
    //   199: aload_0
    //   200: aload_1
    //   201: iconst_1
    //   202: invokeinterface 453 2 0
    //   207: invokespecial 479	com/squareup/okhttp/internal/DiskLruCache:readJournalLine	(Ljava/lang/String;)V
    //   210: iinc 8 1
    //   213: goto -14 -> 199
    //   216: astore 9
    //   218: aload_0
    //   219: iload 8
    //   221: aload_0
    //   222: getfield 90	com/squareup/okhttp/internal/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   225: invokevirtual 362	java/util/LinkedHashMap:size	()I
    //   228: isub
    //   229: putfield 191	com/squareup/okhttp/internal/DiskLruCache:redundantOpCount	I
    //   232: aload_1
    //   233: invokestatic 476	com/squareup/okhttp/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
    //   236: return
    //
    // Exception table:
    //   from	to	target	type
    //   18	122	189	finally
    //   122	189	189	finally
    //   199	210	189	finally
    //   218	232	189	finally
    //   199	210	216	java/io/EOFException
  }

  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1)
      throw new IOException("unexpected journal line: " + paramString);
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    String str2;
    if (k == -1)
    {
      str2 = paramString.substring(j);
      if ((i != "REMOVE".length()) || (!paramString.startsWith("REMOVE")))
        break label304;
      this.lruEntries.remove(str2);
      return;
    }
    label304: for (String str1 = paramString.substring(j, k); ; str1 = str2)
    {
      Entry localEntry = (Entry)this.lruEntries.get(str1);
      if (localEntry == null)
      {
        localEntry = new Entry(str1, null);
        this.lruEntries.put(str1, localEntry);
      }
      if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
      {
        String[] arrayOfString = paramString.substring(k + 1).split(" ");
        Entry.access$602(localEntry, true);
        Entry.access$702(localEntry, null);
        localEntry.setLengths(arrayOfString);
        return;
      }
      if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
      {
        Entry.access$702(localEntry, new Editor(localEntry, null));
        return;
      }
      if ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")))
        break;
      throw new IOException("unexpected journal line: " + paramString);
    }
  }

  private void rebuildJournal()
    throws IOException
  {
    monitorenter;
    BufferedSink localBufferedSink;
    while (true)
    {
      Entry localEntry;
      try
      {
        if (this.journalWriter == null)
          continue;
        this.journalWriter.close();
        localBufferedSink = Okio.buffer(Okio.sink(new FileOutputStream(this.journalFileTmp)));
        try
        {
          localBufferedSink.writeUtf8("libcore.io.DiskLruCache");
          localBufferedSink.writeUtf8("\n");
          localBufferedSink.writeUtf8("1");
          localBufferedSink.writeUtf8("\n");
          localBufferedSink.writeUtf8(Integer.toString(this.appVersion));
          localBufferedSink.writeUtf8("\n");
          localBufferedSink.writeUtf8(Integer.toString(this.valueCount));
          localBufferedSink.writeUtf8("\n");
          localBufferedSink.writeUtf8("\n");
          Iterator localIterator = this.lruEntries.values().iterator();
          if (!localIterator.hasNext())
            break;
          localEntry = (Entry)localIterator.next();
          if (localEntry.currentEditor != null)
          {
            localBufferedSink.writeUtf8("DIRTY " + localEntry.key + '\n');
            continue;
          }
        }
        finally
        {
          localBufferedSink.close();
        }
      }
      finally
      {
        monitorexit;
      }
      localBufferedSink.writeUtf8("CLEAN " + localEntry.key + localEntry.getLengths() + '\n');
    }
    localBufferedSink.close();
    if (this.journalFile.exists())
      renameTo(this.journalFile, this.journalFileBackup, true);
    renameTo(this.journalFileTmp, this.journalFile, false);
    this.journalFileBackup.delete();
    this.journalWriter = Okio.buffer(Okio.sink(new FileOutputStream(this.journalFile, true)));
    monitorexit;
  }

  private static void renameTo(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
      deleteIfExists(paramFile2);
    if (!paramFile1.renameTo(paramFile2))
      throw new IOException();
  }

  private void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize)
      remove((String)((Map.Entry)this.lruEntries.entrySet().iterator().next()).getKey());
  }

  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
      throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + paramString + "\"");
  }

  public void close()
    throws IOException
  {
    monitorenter;
    while (true)
    {
      int j;
      try
      {
        BufferedSink localBufferedSink = this.journalWriter;
        if (localBufferedSink == null)
          return;
        Object[] arrayOfObject = this.lruEntries.values().toArray();
        int i = arrayOfObject.length;
        j = 0;
        if (j >= i)
          continue;
        Entry localEntry = (Entry)arrayOfObject[j];
        if (localEntry.currentEditor != null)
        {
          localEntry.currentEditor.abort();
          break label95;
          trimToSize();
          this.journalWriter.close();
          this.journalWriter = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label95: j++;
    }
  }

  public void delete()
    throws IOException
  {
    close();
    Util.deleteContents(this.directory);
  }

  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }

  public void flush()
    throws IOException
  {
    monitorenter;
    try
    {
      checkNotClosed();
      trimToSize();
      this.journalWriter.flush();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Snapshot get(String paramString)
    throws IOException
  {
    monitorenter;
    while (true)
    {
      try
      {
        checkNotClosed();
        validateKey(paramString);
        Entry localEntry = (Entry)this.lruEntries.get(paramString);
        if (localEntry != null)
          continue;
        localSnapshot = null;
        return localSnapshot;
        if (localEntry.readable)
          continue;
        localSnapshot = null;
        continue;
        InputStream[] arrayOfInputStream = new InputStream[this.valueCount];
        int i = 0;
        int j;
        try
        {
          if (i >= this.valueCount)
            continue;
          arrayOfInputStream[i] = new FileInputStream(localEntry.getCleanFile(i));
          i++;
          continue;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          j = 0;
          if (j >= this.valueCount)
            break label222;
        }
        if (arrayOfInputStream[j] != null)
        {
          Util.closeQuietly(arrayOfInputStream[j]);
          j++;
          continue;
          this.redundantOpCount = (1 + this.redundantOpCount);
          this.journalWriter.writeUtf8("READ " + paramString + '\n');
          if (!journalRebuildRequired())
            continue;
          this.executorService.execute(this.cleanupRunnable);
          localSnapshot = new Snapshot(paramString, localEntry.sequenceNumber, arrayOfInputStream, localEntry.lengths, null);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label222: Snapshot localSnapshot = null;
    }
  }

  public File getDirectory()
  {
    return this.directory;
  }

  public long getMaxSize()
  {
    monitorenter;
    try
    {
      long l = this.maxSize;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean isClosed()
  {
    return this.journalWriter == null;
  }

  public boolean remove(String paramString)
    throws IOException
  {
    monitorenter;
    try
    {
      checkNotClosed();
      validateKey(paramString);
      Entry localEntry = (Entry)this.lruEntries.get(paramString);
      int i;
      if (localEntry != null)
      {
        Editor localEditor = localEntry.currentEditor;
        i = 0;
        if (localEditor == null)
          break label49;
      }
      for (int j = 0; ; j = 1)
      {
        return j;
        label49: 
        while (i < this.valueCount)
        {
          deleteIfExists(localEntry.getCleanFile(i));
          this.size -= localEntry.lengths[i];
          localEntry.lengths[i] = 0L;
          i++;
        }
        this.redundantOpCount = (1 + this.redundantOpCount);
        this.journalWriter.writeUtf8("REMOVE " + paramString + '\n');
        this.lruEntries.remove(paramString);
        if (!journalRebuildRequired())
          continue;
        this.executorService.execute(this.cleanupRunnable);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setMaxSize(long paramLong)
  {
    monitorenter;
    try
    {
      this.maxSize = paramLong;
      this.executorService.execute(this.cleanupRunnable);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public long size()
  {
    monitorenter;
    try
    {
      long l = this.size;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final class Editor
  {
    private boolean committed;
    private final DiskLruCache.Entry entry;
    private boolean hasErrors;
    private final boolean[] written;

    private Editor(DiskLruCache.Entry arg2)
    {
      DiskLruCache.Entry localEntry;
      this.entry = localEntry;
      if (DiskLruCache.Entry.access$600(localEntry));
      for (boolean[] arrayOfBoolean = null; ; arrayOfBoolean = new boolean[DiskLruCache.this.valueCount])
      {
        this.written = arrayOfBoolean;
        return;
      }
    }

    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }

    public void abortUnlessCommitted()
    {
      if (!this.committed);
      try
      {
        abort();
        return;
      }
      catch (IOException localIOException)
      {
      }
    }

    public void commit()
      throws IOException
    {
      if (this.hasErrors)
      {
        DiskLruCache.this.completeEdit(this, false);
        DiskLruCache.this.remove(DiskLruCache.Entry.access$1100(this.entry));
      }
      while (true)
      {
        this.committed = true;
        return;
        DiskLruCache.this.completeEdit(this, true);
      }
    }

    public String getString(int paramInt)
      throws IOException
    {
      InputStream localInputStream = newInputStream(paramInt);
      if (localInputStream != null)
        return DiskLruCache.access$1700(localInputStream);
      return null;
    }

    public InputStream newInputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$700(this.entry) != this)
          throw new IllegalStateException();
      }
      if (!DiskLruCache.Entry.access$600(this.entry))
      {
        monitorexit;
        return null;
      }
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(this.entry.getCleanFile(paramInt));
        monitorexit;
        return localFileInputStream;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        monitorexit;
      }
      return null;
    }

    public OutputStream newOutputStream(int paramInt)
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (DiskLruCache.Entry.access$700(this.entry) != this)
          throw new IllegalStateException();
      }
      if (!DiskLruCache.Entry.access$600(this.entry))
        this.written[paramInt] = true;
      File localFile = this.entry.getDirtyFile(paramInt);
      OutputStream localOutputStream;
      try
      {
        FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile);
        localObject2 = localFileOutputStream1;
        FaultHidingOutputStream localFaultHidingOutputStream = new FaultHidingOutputStream((OutputStream)localObject2, null);
        monitorexit;
        return localFaultHidingOutputStream;
      }
      catch (FileNotFoundException localFileNotFoundException1)
      {
        while (true)
        {
          Object localObject2;
          DiskLruCache.this.directory.mkdirs();
          try
          {
            FileOutputStream localFileOutputStream2 = new FileOutputStream(localFile);
            localObject2 = localFileOutputStream2;
          }
          catch (FileNotFoundException localFileNotFoundException2)
          {
            localOutputStream = DiskLruCache.NULL_OUTPUT_STREAM;
            monitorexit;
          }
        }
      }
      return (OutputStream)localOutputStream;
    }

    public void set(int paramInt, String paramString)
      throws IOException
    {
      BufferedSink localBufferedSink = Okio.buffer(Okio.sink(newOutputStream(paramInt)));
      localBufferedSink.writeUtf8(paramString);
      localBufferedSink.close();
    }

    private class FaultHidingOutputStream extends FilterOutputStream
    {
      private FaultHidingOutputStream(OutputStream arg2)
      {
        super();
      }

      public void close()
      {
        try
        {
          this.out.close();
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }

      public void flush()
      {
        try
        {
          this.out.flush();
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }

      public void write(int paramInt)
      {
        try
        {
          this.out.write(paramInt);
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }

      public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      {
        try
        {
          this.out.write(paramArrayOfByte, paramInt1, paramInt2);
          return;
        }
        catch (IOException localIOException)
        {
          DiskLruCache.Editor.access$2302(DiskLruCache.Editor.this, true);
        }
      }
    }
  }

  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;

    private Entry(String arg2)
    {
      Object localObject;
      this.key = localObject;
      this.lengths = new long[DiskLruCache.this.valueCount];
    }

    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }

    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount)
        throw invalidLengths(paramArrayOfString);
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i++;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }

    public File getCleanFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt);
    }

    public File getDirtyFile(int paramInt)
    {
      return new File(DiskLruCache.this.directory, this.key + "." + paramInt + ".tmp");
    }

    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (long l : this.lengths)
        localStringBuilder.append(' ').append(l);
      return localStringBuilder.toString();
    }
  }

  public final class Snapshot
    implements Closeable
  {
    private final InputStream[] ins;
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;

    private Snapshot(String paramLong, long arg3, InputStream[] paramArrayOfLong, long[] arg6)
    {
      this.key = paramLong;
      this.sequenceNumber = ???;
      this.ins = paramArrayOfLong;
      Object localObject;
      this.lengths = localObject;
    }

    public void close()
    {
      InputStream[] arrayOfInputStream = this.ins;
      int i = arrayOfInputStream.length;
      for (int j = 0; j < i; j++)
        Util.closeQuietly(arrayOfInputStream[j]);
    }

    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }

    public InputStream getInputStream(int paramInt)
    {
      return this.ins[paramInt];
    }

    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }

    public String getString(int paramInt)
      throws IOException
    {
      return DiskLruCache.access$1700(getInputStream(paramInt));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.DiskLruCache
 * JD-Core Version:    0.6.0
 */