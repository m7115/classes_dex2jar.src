package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;

public class WXMusicObject
  implements WXMediaMessage.b
{
  public String musicLowBandUrl;
  public String musicUrl;

  public boolean checkArgs()
  {
    if (((this.musicUrl == null) || (this.musicUrl.length() == 0)) && ((this.musicLowBandUrl == null) || (this.musicLowBandUrl.length() == 0)))
    {
      a.a("MicroMsg.SDK.WXMusicObject", "both arguments are null");
      return false;
    }
    if ((this.musicUrl != null) && (this.musicUrl.length() > 10240))
    {
      a.a("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicUrl is too long");
      return false;
    }
    if ((this.musicLowBandUrl != null) && (this.musicLowBandUrl.length() > 10240))
    {
      a.a("MicroMsg.SDK.WXMusicObject", "checkArgs fail, musicLowBandUrl is too long");
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxmusicobject_musicUrl", this.musicUrl);
    paramBundle.putString("_wxmusicobject_musicLowBandUrl", this.musicLowBandUrl);
  }

  public int type()
  {
    return 3;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.musicUrl = paramBundle.getString("_wxmusicobject_musicUrl");
    this.musicLowBandUrl = paramBundle.getString("_wxmusicobject_musicLowBandUrl");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXMusicObject
 * JD-Core Version:    0.6.0
 */