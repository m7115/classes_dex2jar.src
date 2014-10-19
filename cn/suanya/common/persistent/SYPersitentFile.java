package cn.suanya.common.persistent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SYPersitentFile
  implements SYPersistent
{
  private String localPath = "";

  public SYPersitentFile(String paramString)
  {
    this.localPath = paramString;
  }

  // ERROR //
  public Object readObject(String paramString)
  {
    // Byte code:
    //   0: new 29	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   7: aload_0
    //   8: getfield 17	cn/suanya/common/persistent/SYPersitentFile:localPath	Ljava/lang/String;
    //   11: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: aload_1
    //   15: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: astore_2
    //   22: new 40	java/io/ObjectInputStream
    //   25: dup
    //   26: new 42	java/io/FileInputStream
    //   29: dup
    //   30: aload_2
    //   31: invokespecial 44	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   34: invokespecial 47	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   37: astore_3
    //   38: aload_3
    //   39: invokevirtual 50	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   42: astore 13
    //   44: aload 13
    //   46: astore 5
    //   48: aload_3
    //   49: invokevirtual 53	java/io/ObjectInputStream:close	()V
    //   52: aload 5
    //   54: areturn
    //   55: astore 11
    //   57: aconst_null
    //   58: astore 5
    //   60: aload 11
    //   62: astore 12
    //   64: aload 12
    //   66: invokevirtual 56	java/io/StreamCorruptedException:printStackTrace	()V
    //   69: aload 5
    //   71: areturn
    //   72: astore 9
    //   74: aconst_null
    //   75: astore 5
    //   77: aload 9
    //   79: astore 10
    //   81: aload 10
    //   83: invokevirtual 57	java/io/FileNotFoundException:printStackTrace	()V
    //   86: aload 5
    //   88: areturn
    //   89: astore 7
    //   91: aconst_null
    //   92: astore 5
    //   94: aload 7
    //   96: astore 8
    //   98: aload 8
    //   100: invokevirtual 58	java/io/IOException:printStackTrace	()V
    //   103: aload 5
    //   105: areturn
    //   106: astore 4
    //   108: aconst_null
    //   109: astore 5
    //   111: aload 4
    //   113: astore 6
    //   115: aload 6
    //   117: invokevirtual 59	java/lang/ClassNotFoundException:printStackTrace	()V
    //   120: aload 5
    //   122: areturn
    //   123: astore 6
    //   125: goto -10 -> 115
    //   128: astore 8
    //   130: goto -32 -> 98
    //   133: astore 10
    //   135: goto -54 -> 81
    //   138: astore 12
    //   140: goto -76 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   22	44	55	java/io/StreamCorruptedException
    //   22	44	72	java/io/FileNotFoundException
    //   22	44	89	java/io/IOException
    //   22	44	106	java/lang/ClassNotFoundException
    //   48	52	123	java/lang/ClassNotFoundException
    //   48	52	128	java/io/IOException
    //   48	52	133	java/io/FileNotFoundException
    //   48	52	138	java/io/StreamCorruptedException
  }

  public String readString(String paramString)
  {
    return null;
  }

  public void writeObject(String paramString, Object paramObject)
  {
    String str = this.localPath + paramString;
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(str));
      localObjectOutputStream.writeObject(paramObject);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public void writeString(String paramString1, String paramString2)
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.persistent.SYPersitentFile
 * JD-Core Version:    0.6.0
 */