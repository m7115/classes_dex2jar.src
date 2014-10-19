package com.tencent.mm.sdk.openapi;

import android.os.Bundle;
import com.tencent.mm.sdk.platformtools.a;

public class WXTextObject
  implements WXMediaMessage.b
{
  public String text;

  public WXTextObject()
  {
    this(null);
  }

  public WXTextObject(String paramString)
  {
    this.text = paramString;
  }

  public boolean checkArgs()
  {
    if ((this.text == null) || (this.text.length() == 0) || (this.text.length() > 10240))
    {
      a.a("MicroMsg.SDK.WXTextObject", "checkArgs fail, text is invalid");
      return false;
    }
    return true;
  }

  public void serialize(Bundle paramBundle)
  {
    paramBundle.putString("_wxtextobject_text", this.text);
  }

  public int type()
  {
    return 1;
  }

  public void unserialize(Bundle paramBundle)
  {
    this.text = paramBundle.getString("_wxtextobject_text");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.openapi.WXTextObject
 * JD-Core Version:    0.6.0
 */