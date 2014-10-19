package com.MobileTicket;

import android.app.Activity;
import android.os.Bundle;

public class WebPayActivity extends Activity
{
  private static boolean callJSFlag = false;

  public static boolean isCallJSFlag()
  {
    return callJSFlag;
  }

  public static void setCallJSFlag(boolean paramBoolean)
  {
    callJSFlag = paramBoolean;
  }

  public void onCreate(Bundle paramBundle)
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.MobileTicket.WebPayActivity
 * JD-Core Version:    0.6.0
 */