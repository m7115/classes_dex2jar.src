package com.tencent.mm.sdk.platformtools;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

class f extends PhoneStateListener
{
  f(e parame)
  {
  }

  public void onSignalStrengthChanged(int paramInt)
  {
    super.onSignalStrengthChanged(paramInt);
    e.a(-113 + paramInt * 2);
    if (e.a(this.a) != null)
      e.a(this.a).listen(e.b(this.a), 0);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.f
 * JD-Core Version:    0.6.0
 */