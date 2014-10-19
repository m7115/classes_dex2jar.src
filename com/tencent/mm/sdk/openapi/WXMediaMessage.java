package com.tencent.mm.sdk.openapi;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;
import java.io.ByteArrayOutputStream;

public final class WXMediaMessage
{
  public static final String ACTION_WXAPPMESSAGE = "com.tencent.mm.sdk.openapi.Intent.ACTION_WXAPPMESSAGE";
  public String description;
  public b mediaObject;
  public int sdkVer;
  public byte[] thumbData;
  public String title;

  public WXMediaMessage()
  {
    this(null);
  }

  public WXMediaMessage(b paramb)
  {
    this.mediaObject = paramb;
  }

  final boolean checkArgs()
  {
    if ((getType() == 8) && ((this.thumbData == null) || (this.thumbData.length == 0)))
    {
      a.a("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, thumbData should not be null when send emoji");
      return false;
    }
    if ((this.thumbData != null) && (this.thumbData.length > 32768))
    {
      a.a("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, thumbData is invalid");
      return false;
    }
    if ((this.title != null) && (this.title.length() > 512))
    {
      a.a("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, title is invalid");
      return false;
    }
    if ((this.description != null) && (this.description.length() > 1024))
    {
      a.a("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, description is invalid");
      return false;
    }
    if (this.mediaObject == null)
    {
      a.a("MicroMsg.SDK.WXMediaMessage", "checkArgs fail, mediaObject is null");
      return false;
    }
    return this.mediaObject.checkArgs();
  }

  public final int getType()
  {
    if (this.mediaObject == null)
      return 0;
    return this.mediaObject.type();
  }

  public final void setThumbImage(Bitmap paramBitmap)
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 85, localByteArrayOutputStream);
      this.thumbData = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      a.a("MicroMsg.SDK.WXMediaMessage", "put thumb failed");
    }
  }

  public static class a
  {
    public static Bundle a(WXMediaMessage paramWXMediaMessage)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("_wxobject_sdkVer", paramWXMediaMessage.sdkVer);
      localBundle.putString("_wxobject_title", paramWXMediaMessage.title);
      localBundle.putString("_wxobject_description", paramWXMediaMessage.description);
      localBundle.putByteArray("_wxobject_thumbdata", paramWXMediaMessage.thumbData);
      if (paramWXMediaMessage.mediaObject != null)
      {
        localBundle.putString("_wxobject_identifier_", paramWXMediaMessage.mediaObject.getClass().getName());
        paramWXMediaMessage.mediaObject.serialize(localBundle);
      }
      return localBundle;
    }

    public static WXMediaMessage a(Bundle paramBundle)
    {
      WXMediaMessage localWXMediaMessage = new WXMediaMessage();
      localWXMediaMessage.sdkVer = paramBundle.getInt("_wxobject_sdkVer");
      localWXMediaMessage.title = paramBundle.getString("_wxobject_title");
      localWXMediaMessage.description = paramBundle.getString("_wxobject_description");
      localWXMediaMessage.thumbData = paramBundle.getByteArray("_wxobject_thumbdata");
      String str = paramBundle.getString("_wxobject_identifier_");
      if ((str == null) || (str.length() <= 0))
        return localWXMediaMessage;
      try
      {
        localWXMediaMessage.mediaObject = ((WXMediaMessage.b)Class.forName(str).newInstance());
        localWXMediaMessage.mediaObject.unserialize(paramBundle);
        return localWXMediaMessage;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        a.a("MicroMsg.SDK.WXMediaMessage", "get media object from bundle failed: unknown ident " + str);
      }
      return localWXMediaMessage;
    }
  }

  public static abstract interface b
  {
    public abstract boolean checkArgs();

    public abstract void serialize(Bundle paramBundle);

    public abstract int type();

    public abstract void unserialize(Bundle paramBundle);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXMediaMessage
 * JD-Core Version:    0.6.0
 */