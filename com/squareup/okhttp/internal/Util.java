package com.squareup.okhttp.internal;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.okio.ByteString;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Source;
import com.squareup.okhttp.internal.spdy.Header;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public final class Util
{
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  public static final InputStream EMPTY_INPUT_STREAM;
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  public static final List<Protocol> HTTP2_AND_HTTP_11;
  public static final List<Protocol> HTTP2_SPDY3_AND_HTTP;
  public static final List<Protocol> SPDY3_AND_HTTP11;
  public static final Charset US_ASCII;
  public static final Charset UTF_8;

  static
  {
    EMPTY_INPUT_STREAM = new ByteArrayInputStream(EMPTY_BYTE_ARRAY);
    US_ASCII = Charset.forName("US-ASCII");
    UTF_8 = Charset.forName("UTF-8");
    Protocol[] arrayOfProtocol1 = new Protocol[3];
    arrayOfProtocol1[0] = Protocol.HTTP_2;
    arrayOfProtocol1[1] = Protocol.SPDY_3;
    arrayOfProtocol1[2] = Protocol.HTTP_11;
    HTTP2_SPDY3_AND_HTTP = immutableList(Arrays.asList(arrayOfProtocol1));
    Protocol[] arrayOfProtocol2 = new Protocol[2];
    arrayOfProtocol2[0] = Protocol.SPDY_3;
    arrayOfProtocol2[1] = Protocol.HTTP_11;
    SPDY3_AND_HTTP11 = immutableList(Arrays.asList(arrayOfProtocol2));
    Protocol[] arrayOfProtocol3 = new Protocol[2];
    arrayOfProtocol3[0] = Protocol.HTTP_2;
    arrayOfProtocol3[1] = Protocol.HTTP_11;
    HTTP2_AND_HTTP_11 = immutableList(Arrays.asList(arrayOfProtocol3));
  }

  public static void checkOffsetAndCount(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) < 0L) || (paramLong2 > paramLong1) || (paramLong1 - paramLong2 < paramLong3))
      throw new ArrayIndexOutOfBoundsException();
  }

  public static void closeAll(Closeable paramCloseable1, Closeable paramCloseable2)
    throws IOException
  {
    Object localObject = null;
    try
    {
      paramCloseable1.close();
      try
      {
        label8: paramCloseable2.close();
        if (localObject == null)
          return;
      }
      catch (Throwable localThrowable2)
      {
        while (true)
        {
          if (localObject != null)
            continue;
          localObject = localThrowable2;
        }
        if ((localObject instanceof IOException))
          throw ((IOException)localObject);
        if ((localObject instanceof RuntimeException))
          throw ((RuntimeException)localObject);
        if ((localObject instanceof Error))
          throw ((Error)localObject);
        throw new AssertionError(localObject);
      }
    }
    catch (Throwable localThrowable1)
    {
      break label8;
    }
  }

  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
    }
  }

  public static void closeQuietly(ServerSocket paramServerSocket)
  {
    if (paramServerSocket != null);
    try
    {
      paramServerSocket.close();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
    }
  }

  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null);
    try
    {
      paramSocket.close();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
    }
  }

  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[8192];
    int i = 0;
    while (true)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (j == -1)
        break;
      i += j;
      paramOutputStream.write(arrayOfByte, 0, j);
    }
    return i;
  }

  public static void deleteContents(File paramFile)
    throws IOException
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      throw new IOException("not a readable directory: " + paramFile);
    int i = arrayOfFile.length;
    for (int j = 0; j < i; j++)
    {
      File localFile = arrayOfFile[j];
      if (localFile.isDirectory())
        deleteContents(localFile);
      if (localFile.delete())
        continue;
      throw new IOException("failed to delete file: " + localFile);
    }
  }

  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static int getDefaultPort(String paramString)
  {
    if ("http".equals(paramString))
      return 80;
    if ("https".equals(paramString))
      return 443;
    return -1;
  }

  private static int getEffectivePort(String paramString, int paramInt)
  {
    if (paramInt != -1)
      return paramInt;
    return getDefaultPort(paramString);
  }

  public static int getEffectivePort(URI paramURI)
  {
    return getEffectivePort(paramURI.getScheme(), paramURI.getPort());
  }

  public static int getEffectivePort(URL paramURL)
  {
    return getEffectivePort(paramURL.getProtocol(), paramURL.getPort());
  }

  public static Protocol getProtocol(ByteString paramByteString)
    throws IOException
  {
    Protocol localProtocol;
    if (paramByteString == null)
    {
      localProtocol = Protocol.HTTP_11;
      return localProtocol;
    }
    Protocol[] arrayOfProtocol = Protocol.values();
    int i = arrayOfProtocol.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        break label49;
      localProtocol = arrayOfProtocol[j];
      if (localProtocol.name.equals(paramByteString))
        break;
    }
    label49: throw new IOException("Unexpected protocol: " + paramByteString.utf8());
  }

  public static String hash(String paramString)
  {
    try
    {
      String str = ByteString.of(MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"))).hex();
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new AssertionError(localNoSuchAlgorithmException);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new AssertionError(localUnsupportedEncodingException);
  }

  public static List<Header> headerEntries(String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfString.length / 2);
    for (int i = 0; i < paramArrayOfString.length; i += 2)
      localArrayList.add(new Header(paramArrayOfString[i], paramArrayOfString[(i + 1)]));
    return localArrayList;
  }

  public static <T> List<T> immutableList(List<T> paramList)
  {
    return Collections.unmodifiableList(new ArrayList(paramList));
  }

  public static <T> List<T> immutableList(T[] paramArrayOfT)
  {
    return Collections.unmodifiableList(Arrays.asList((Object[])paramArrayOfT.clone()));
  }

  public static OkBuffer readFully(Source paramSource)
    throws IOException
  {
    OkBuffer localOkBuffer = new OkBuffer();
    while (paramSource.read(localOkBuffer, 2048L) != -1L);
    paramSource.close();
    return localOkBuffer;
  }

  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    readFully(paramInputStream, paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 == 0);
    while (true)
    {
      return;
      if (paramInputStream == null)
        throw new NullPointerException("in == null");
      if (paramArrayOfByte == null)
        throw new NullPointerException("dst == null");
      checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
      while (paramInt2 > 0)
      {
        int i = paramInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
        if (i < 0)
          throw new EOFException();
        paramInt1 += i;
        paramInt2 -= i;
      }
    }
  }

  public static boolean skipAll(Source paramSource, int paramInt)
    throws IOException
  {
    long l = System.nanoTime();
    OkBuffer localOkBuffer = new OkBuffer();
    while (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - l) < paramInt)
    {
      if (paramSource.read(localOkBuffer, 2048L) == -1L)
        return true;
      localOkBuffer.clear();
    }
    return false;
  }

  public static ThreadFactory threadFactory(String paramString, boolean paramBoolean)
  {
    return new ThreadFactory(paramString, paramBoolean)
    {
      public Thread newThread(Runnable paramRunnable)
      {
        Thread localThread = new Thread(paramRunnable, this.val$name);
        localThread.setDaemon(this.val$daemon);
        return localThread;
      }
    };
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.Util
 * JD-Core Version:    0.6.0
 */