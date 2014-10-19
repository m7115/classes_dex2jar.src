package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;
import java.io.File;

public class WXFileObject
  implements WXMediaMessage.b
{
  public byte[] fileData;
  public String filePath;

  public WXFileObject()
  {
    this.fileData = null;
    this.filePath = null;
  }

  public WXFileObject(String paramString)
  {
    this.filePath = paramString;
  }

  public WXFileObject(byte[] paramArrayOfByte)
  {
    this.fileData = paramArrayOfByte;
  }

  public boolean checkArgs()
  {
    if (((this.fileData == null) || (this.fileData.length == 0)) && ((this.filePath == null) || (this.filePath.length() == 0)))
    {
      a.a("MicroMsg.SDK.WXFileObject", "checkArgs fail, both arguments is null");
      return false;
    }
    if ((this.fileData != null) && (this.fileData.length > 10485760))
    {
      a.a("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileData is too large");
      return false;
    }
    if (this.filePath != null)
    {
      String str = this.filePath;
      int i;
      if ((str == null) || (str.length() == 0))
        i = 0;
      while (i > 10485760)
      {
        a.a("MicroMsg.SDK.WXFileObject", "checkArgs fail, fileSize is too large");
        return false;
        File localFile = new File(str);
        if (!localFile.exists())
        {
          i = 0;
          continue;
        }
        i = (int)localFile.length();
      }
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putByteArray("_wxfileobject_fileData", this.fileData);
    paramBundle.putString("_wxfileobject_filePath", this.filePath);
  }

  public void setFileData(byte[] paramArrayOfByte)
  {
    this.fileData = paramArrayOfByte;
  }

  public void setFilePath(String paramString)
  {
    this.filePath = paramString;
  }

  public int type()
  {
    return 6;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.fileData = paramBundle.getByteArray("_wxfileobject_fileData");
    this.filePath = paramBundle.getString("_wxfileobject_filePath");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXFileObject
 * JD-Core Version:    0.6.0
 */