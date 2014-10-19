package com.example.pathview.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil
{
  public static void unZipTo(InputStream paramInputStream, String paramString)
    throws Exception
  {
    File localFile1 = new File(paramString);
    if (!localFile1.exists())
      localFile1.mkdirs();
    ZipInputStream localZipInputStream = new ZipInputStream(paramInputStream);
    while (true)
    {
      ZipEntry localZipEntry = localZipInputStream.getNextEntry();
      if (localZipEntry == null)
        break;
      String str1 = localZipEntry.getName();
      if (localZipEntry.isDirectory())
      {
        String str2 = str1.substring(0, -1 + str1.length());
        new File(paramString + File.separator + str2).mkdirs();
        continue;
      }
      File localFile2 = new File(paramString + File.separator + str1);
      if (localFile2.exists())
        localFile2.delete();
      localFile2.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = localZipInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        localFileOutputStream.write(arrayOfByte, 0, i);
        localFileOutputStream.flush();
      }
      localFileOutputStream.close();
    }
    localZipInputStream.close();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.util.FileUtil
 * JD-Core Version:    0.6.0
 */