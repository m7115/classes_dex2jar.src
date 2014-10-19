package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;
import java.io.File;

public class WXAppExtendObject
  implements WXMediaMessage.b
{
  public String extInfo;
  public byte[] fileData;
  public String filePath;

  public WXAppExtendObject()
  {
  }

  public WXAppExtendObject(String paramString1, String paramString2)
  {
    this.extInfo = paramString1;
    this.filePath = paramString2;
  }

  public WXAppExtendObject(String paramString, byte[] paramArrayOfByte)
  {
    this.extInfo = paramString;
    this.fileData = paramArrayOfByte;
  }

  public boolean checkArgs()
  {
    if (((this.extInfo == null) || (this.extInfo.length() == 0)) && ((this.filePath == null) || (this.filePath.length() == 0)) && ((this.fileData == null) || (this.fileData.length == 0)))
    {
      a.a("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, all arguments is null");
      return false;
    }
    if ((this.extInfo != null) && (this.extInfo.length() > 2048))
    {
      a.a("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, extInfo is invalid");
      return false;
    }
    if ((this.filePath != null) && (this.filePath.length() > 10240))
    {
      a.a("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, filePath is invalid");
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
        a.a("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileSize is too large");
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
    if ((this.fileData != null) && (this.fileData.length > 10485760))
    {
      a.a("MicroMsg.SDK.WXAppExtendObject", "checkArgs fail, fileData is too large");
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxappextendobject_extInfo", this.extInfo);
    paramBundle.putByteArray("_wxappextendobject_fileData", this.fileData);
    paramBundle.putString("_wxappextendobject_filePath", this.filePath);
  }

  public int type()
  {
    return 7;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.extInfo = paramBundle.getString("_wxappextendobject_extInfo");
    this.fileData = paramBundle.getByteArray("_wxappextendobject_fileData");
    this.filePath = paramBundle.getString("_wxappextendobject_filePath");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXAppExtendObject
 * JD-Core Version:    0.6.0
 */