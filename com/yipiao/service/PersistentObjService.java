package com.yipiao.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class PersistentObjService
{
  public static String localPath = "";

  public static Object readObject(String paramString)
  {
    String str = localPath + paramString;
    Object localObject = null;
    try
    {
      ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream(str));
      localObject = localObjectInputStream.readObject();
      localObjectInputStream.close();
      return localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return localObject;
    }
    catch (IOException localIOException)
    {
      return localObject;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      return localObject;
    }
    catch (StreamCorruptedException localStreamCorruptedException)
    {
    }
    return localObject;
  }

  public static void saveObject(Object paramObject, String paramString)
  {
    String str = localPath + paramString;
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(str));
      localObjectOutputStream.writeObject(paramObject);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.PersistentObjService
 * JD-Core Version:    0.6.0
 */