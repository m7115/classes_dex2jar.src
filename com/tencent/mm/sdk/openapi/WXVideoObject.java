package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;

public class WXVideoObject
  implements WXMediaMessage.b
{
  public String videoLowBandUrl;
  public String videoUrl;

  public boolean checkArgs()
  {
    if (((this.videoUrl == null) || (this.videoUrl.length() == 0)) && ((this.videoLowBandUrl == null) || (this.videoLowBandUrl.length() == 0)))
    {
      a.a("MicroMsg.SDK.WXVideoObject", "both arguments are null");
      return false;
    }
    if ((this.videoUrl != null) && (this.videoUrl.length() > 10240))
    {
      a.a("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoUrl is too long");
      return false;
    }
    if ((this.videoLowBandUrl != null) && (this.videoLowBandUrl.length() > 10240))
    {
      a.a("MicroMsg.SDK.WXVideoObject", "checkArgs fail, videoLowBandUrl is too long");
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxvideoobject_videoUrl", this.videoUrl);
    paramBundle.putString("_wxvideoobject_videoLowBandUrl", this.videoLowBandUrl);
  }

  public int type()
  {
    return 4;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.videoUrl = paramBundle.getString("_wxvideoobject_videoUrl");
    this.videoLowBandUrl = paramBundle.getString("_wxvideoobject_videoLowBandUrl");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXVideoObject
 * JD-Core Version:    0.6.0
 */