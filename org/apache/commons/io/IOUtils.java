package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;

public class IOUtils
{
  private static final int DEFAULT_BUFFER_SIZE = 4096;
  public static final char DIR_SEPARATOR = '\000';
  public static final char DIR_SEPARATOR_UNIX = '/';
  public static final char DIR_SEPARATOR_WINDOWS = '\\';
  public static final String LINE_SEPARATOR;
  public static final String LINE_SEPARATOR_UNIX = "\n";
  public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
  private static final int SKIP_BUFFER_SIZE = 2048;
  private static byte[] SKIP_BYTE_BUFFER;
  private static char[] SKIP_CHAR_BUFFER;

  static
  {
    StringBuilderWriter localStringBuilderWriter = new StringBuilderWriter(4);
    PrintWriter localPrintWriter = new PrintWriter(localStringBuilderWriter);
    localPrintWriter.println();
    LINE_SEPARATOR = localStringBuilderWriter.toString();
    localPrintWriter.close();
  }

  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void closeQuietly(InputStream paramInputStream)
  {
    closeQuietly(paramInputStream);
  }

  public static void closeQuietly(OutputStream paramOutputStream)
  {
    closeQuietly(paramOutputStream);
  }

  public static void closeQuietly(Reader paramReader)
  {
    closeQuietly(paramReader);
  }

  public static void closeQuietly(Writer paramWriter)
  {
    closeQuietly(paramWriter);
  }

  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null);
    try
    {
      paramSocket.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public static boolean contentEquals(InputStream paramInputStream1, InputStream paramInputStream2)
    throws IOException
  {
    if (!(paramInputStream1 instanceof BufferedInputStream))
      paramInputStream1 = new BufferedInputStream(paramInputStream1);
    if (!(paramInputStream2 instanceof BufferedInputStream))
      paramInputStream2 = new BufferedInputStream(paramInputStream2);
    for (int i = paramInputStream1.read(); -1 != i; i = paramInputStream1.read())
      if (i != paramInputStream2.read())
        return false;
    if (paramInputStream2.read() == -1);
    for (int j = 1; ; j = 0)
      return j;
  }

  public static boolean contentEquals(Reader paramReader1, Reader paramReader2)
    throws IOException
  {
    if (!(paramReader1 instanceof BufferedReader))
      paramReader1 = new BufferedReader(paramReader1);
    if (!(paramReader2 instanceof BufferedReader))
      paramReader2 = new BufferedReader(paramReader2);
    for (int i = paramReader1.read(); -1 != i; i = paramReader1.read())
      if (i != paramReader2.read())
        return false;
    if (paramReader2.read() == -1);
    for (int j = 1; ; j = 0)
      return j;
  }

  public static int copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    long l = copyLarge(paramInputStream, paramOutputStream);
    if (l > 2147483647L)
      return -1;
    return (int)l;
  }

  public static int copy(Reader paramReader, Writer paramWriter)
    throws IOException
  {
    long l = copyLarge(paramReader, paramWriter);
    if (l > 2147483647L)
      return -1;
    return (int)l;
  }

  public static void copy(InputStream paramInputStream, Writer paramWriter)
    throws IOException
  {
    copy(new InputStreamReader(paramInputStream), paramWriter);
  }

  public static void copy(InputStream paramInputStream, Writer paramWriter, String paramString)
    throws IOException
  {
    if (paramString == null)
    {
      copy(paramInputStream, paramWriter);
      return;
    }
    copy(new InputStreamReader(paramInputStream, paramString), paramWriter);
  }

  public static void copy(Reader paramReader, OutputStream paramOutputStream)
    throws IOException
  {
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream);
    copy(paramReader, localOutputStreamWriter);
    localOutputStreamWriter.flush();
  }

  public static void copy(Reader paramReader, OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramString == null)
    {
      copy(paramReader, paramOutputStream);
      return;
    }
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream, paramString);
    copy(paramReader, localOutputStreamWriter);
    localOutputStreamWriter.flush();
  }

  public static long copyLarge(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4096];
    long l = 0L;
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (-1 == i)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
      l += i;
    }
    return l;
  }

  public static long copyLarge(Reader paramReader, Writer paramWriter)
    throws IOException
  {
    char[] arrayOfChar = new char[4096];
    long l = 0L;
    while (true)
    {
      int i = paramReader.read(arrayOfChar);
      if (-1 == i)
        break;
      paramWriter.write(arrayOfChar, 0, i);
      l += i;
    }
    return l;
  }

  public static LineIterator lineIterator(InputStream paramInputStream, String paramString)
    throws IOException
  {
    if (paramString == null);
    for (InputStreamReader localInputStreamReader = new InputStreamReader(paramInputStream); ; localInputStreamReader = new InputStreamReader(paramInputStream, paramString))
      return new LineIterator(localInputStreamReader);
  }

  public static LineIterator lineIterator(Reader paramReader)
  {
    return new LineIterator(paramReader);
  }

  public static List<String> readLines(InputStream paramInputStream)
    throws IOException
  {
    return readLines(new InputStreamReader(paramInputStream));
  }

  public static List<String> readLines(InputStream paramInputStream, String paramString)
    throws IOException
  {
    if (paramString == null)
      return readLines(paramInputStream);
    return readLines(new InputStreamReader(paramInputStream, paramString));
  }

  public static List<String> readLines(Reader paramReader)
    throws IOException
  {
    BufferedReader localBufferedReader = new BufferedReader(paramReader);
    ArrayList localArrayList = new ArrayList();
    for (String str = localBufferedReader.readLine(); str != null; str = localBufferedReader.readLine())
      localArrayList.add(str);
    return localArrayList;
  }

  public static long skip(InputStream paramInputStream, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Skip count must be non-negative, actual: " + paramLong);
    if (SKIP_BYTE_BUFFER == null)
      SKIP_BYTE_BUFFER = new byte[2048];
    long l1 = paramLong;
    while (true)
    {
      long l2;
      if (l1 > 0L)
      {
        l2 = paramInputStream.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(l1, 2048L));
        if (l2 >= 0L);
      }
      else
      {
        return paramLong - l1;
      }
      l1 -= l2;
    }
  }

  public static long skip(Reader paramReader, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Skip count must be non-negative, actual: " + paramLong);
    if (SKIP_CHAR_BUFFER == null)
      SKIP_CHAR_BUFFER = new char[2048];
    long l1 = paramLong;
    while (true)
    {
      long l2;
      if (l1 > 0L)
      {
        l2 = paramReader.read(SKIP_CHAR_BUFFER, 0, (int)Math.min(l1, 2048L));
        if (l2 >= 0L);
      }
      else
      {
        return paramLong - l1;
      }
      l1 -= l2;
    }
  }

  public static void skipFully(InputStream paramInputStream, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("Bytes to skip must not be negative: " + paramLong);
    long l = skip(paramInputStream, paramLong);
    if (l != paramLong)
      throw new EOFException("Bytes to skip: " + paramLong + " actual: " + l);
  }

  public static void skipFully(Reader paramReader, long paramLong)
    throws IOException
  {
    long l = skip(paramReader, paramLong);
    if (l != paramLong)
      throw new EOFException("Bytes to skip: " + paramLong + " actual: " + l);
  }

  public static InputStream toBufferedInputStream(InputStream paramInputStream)
    throws IOException
  {
    return ByteArrayOutputStream.toBufferedInputStream(paramInputStream);
  }

  public static byte[] toByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(Reader paramReader)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramReader, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(Reader paramReader, String paramString)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramReader, localByteArrayOutputStream, paramString);
    return localByteArrayOutputStream.toByteArray();
  }

  @Deprecated
  public static byte[] toByteArray(String paramString)
    throws IOException
  {
    return paramString.getBytes();
  }

  public static char[] toCharArray(InputStream paramInputStream)
    throws IOException
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    copy(paramInputStream, localCharArrayWriter);
    return localCharArrayWriter.toCharArray();
  }

  public static char[] toCharArray(InputStream paramInputStream, String paramString)
    throws IOException
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    copy(paramInputStream, localCharArrayWriter, paramString);
    return localCharArrayWriter.toCharArray();
  }

  public static char[] toCharArray(Reader paramReader)
    throws IOException
  {
    CharArrayWriter localCharArrayWriter = new CharArrayWriter();
    copy(paramReader, localCharArrayWriter);
    return localCharArrayWriter.toCharArray();
  }

  public static InputStream toInputStream(CharSequence paramCharSequence)
  {
    return toInputStream(paramCharSequence.toString());
  }

  public static InputStream toInputStream(CharSequence paramCharSequence, String paramString)
    throws IOException
  {
    return toInputStream(paramCharSequence.toString(), paramString);
  }

  public static InputStream toInputStream(String paramString)
  {
    return new ByteArrayInputStream(paramString.getBytes());
  }

  public static InputStream toInputStream(String paramString1, String paramString2)
    throws IOException
  {
    if (paramString2 != null);
    for (byte[] arrayOfByte = paramString1.getBytes(paramString2); ; arrayOfByte = paramString1.getBytes())
      return new ByteArrayInputStream(arrayOfByte);
  }

  public static String toString(InputStream paramInputStream)
    throws IOException
  {
    StringBuilderWriter localStringBuilderWriter = new StringBuilderWriter();
    copy(paramInputStream, localStringBuilderWriter);
    return localStringBuilderWriter.toString();
  }

  public static String toString(InputStream paramInputStream, String paramString)
    throws IOException
  {
    StringBuilderWriter localStringBuilderWriter = new StringBuilderWriter();
    copy(paramInputStream, localStringBuilderWriter, paramString);
    return localStringBuilderWriter.toString();
  }

  public static String toString(Reader paramReader)
    throws IOException
  {
    StringBuilderWriter localStringBuilderWriter = new StringBuilderWriter();
    copy(paramReader, localStringBuilderWriter);
    return localStringBuilderWriter.toString();
  }

  @Deprecated
  public static String toString(byte[] paramArrayOfByte)
    throws IOException
  {
    return new String(paramArrayOfByte);
  }

  @Deprecated
  public static String toString(byte[] paramArrayOfByte, String paramString)
    throws IOException
  {
    if (paramString == null)
      return new String(paramArrayOfByte);
    return new String(paramArrayOfByte, paramString);
  }

  public static void write(CharSequence paramCharSequence, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramCharSequence != null)
      write(paramCharSequence.toString(), paramOutputStream);
  }

  public static void write(CharSequence paramCharSequence, OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramCharSequence != null)
      write(paramCharSequence.toString(), paramOutputStream, paramString);
  }

  public static void write(CharSequence paramCharSequence, Writer paramWriter)
    throws IOException
  {
    if (paramCharSequence != null)
      write(paramCharSequence.toString(), paramWriter);
  }

  public static void write(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramString != null)
      paramOutputStream.write(paramString.getBytes());
  }

  public static void write(String paramString1, OutputStream paramOutputStream, String paramString2)
    throws IOException
  {
    if (paramString1 != null)
    {
      if (paramString2 == null)
        write(paramString1, paramOutputStream);
    }
    else
      return;
    paramOutputStream.write(paramString1.getBytes(paramString2));
  }

  public static void write(String paramString, Writer paramWriter)
    throws IOException
  {
    if (paramString != null)
      paramWriter.write(paramString);
  }

  @Deprecated
  public static void write(StringBuffer paramStringBuffer, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramStringBuffer != null)
      paramOutputStream.write(paramStringBuffer.toString().getBytes());
  }

  @Deprecated
  public static void write(StringBuffer paramStringBuffer, OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramStringBuffer != null)
    {
      if (paramString == null)
        write(paramStringBuffer, paramOutputStream);
    }
    else
      return;
    paramOutputStream.write(paramStringBuffer.toString().getBytes(paramString));
  }

  @Deprecated
  public static void write(StringBuffer paramStringBuffer, Writer paramWriter)
    throws IOException
  {
    if (paramStringBuffer != null)
      paramWriter.write(paramStringBuffer.toString());
  }

  public static void write(byte[] paramArrayOfByte, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramArrayOfByte != null)
      paramOutputStream.write(paramArrayOfByte);
  }

  public static void write(byte[] paramArrayOfByte, Writer paramWriter)
    throws IOException
  {
    if (paramArrayOfByte != null)
      paramWriter.write(new String(paramArrayOfByte));
  }

  public static void write(byte[] paramArrayOfByte, Writer paramWriter, String paramString)
    throws IOException
  {
    if (paramArrayOfByte != null)
    {
      if (paramString == null)
        write(paramArrayOfByte, paramWriter);
    }
    else
      return;
    paramWriter.write(new String(paramArrayOfByte, paramString));
  }

  public static void write(char[] paramArrayOfChar, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramArrayOfChar != null)
      paramOutputStream.write(new String(paramArrayOfChar).getBytes());
  }

  public static void write(char[] paramArrayOfChar, OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    if (paramArrayOfChar != null)
    {
      if (paramString == null)
        write(paramArrayOfChar, paramOutputStream);
    }
    else
      return;
    paramOutputStream.write(new String(paramArrayOfChar).getBytes(paramString));
  }

  public static void write(char[] paramArrayOfChar, Writer paramWriter)
    throws IOException
  {
    if (paramArrayOfChar != null)
      paramWriter.write(paramArrayOfChar);
  }

  public static void writeLines(Collection<?> paramCollection, String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      if (paramString == null)
        paramString = LINE_SEPARATOR;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (localObject != null)
          paramOutputStream.write(localObject.toString().getBytes());
        paramOutputStream.write(paramString.getBytes());
      }
    }
  }

  public static void writeLines(Collection<?> paramCollection, String paramString1, OutputStream paramOutputStream, String paramString2)
    throws IOException
  {
    if (paramString2 == null)
      writeLines(paramCollection, paramString1, paramOutputStream);
    while (true)
    {
      return;
      if (paramCollection == null)
        continue;
      if (paramString1 == null)
        paramString1 = LINE_SEPARATOR;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (localObject != null)
          paramOutputStream.write(localObject.toString().getBytes(paramString2));
        paramOutputStream.write(paramString1.getBytes(paramString2));
      }
    }
  }

  public static void writeLines(Collection<?> paramCollection, String paramString, Writer paramWriter)
    throws IOException
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      if (paramString == null)
        paramString = LINE_SEPARATOR;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        if (localObject != null)
          paramWriter.write(localObject.toString());
        paramWriter.write(paramString);
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     org.apache.commons.io.IOUtils
 * JD-Core Version:    0.6.0
 */