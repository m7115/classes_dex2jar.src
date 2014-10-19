package com.tencent.mm.sdk.platformtools;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

class h extends PhoneStateListener
{
  h(g paramg)
  {
  }

  public void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
  {
    super.onSignalStrengthsChanged(paramSignalStrength);
    if (g.a(this.a) == 2)
      g.a(paramSignalStrength.getCdmaDbm());
    if (g.a(this.a) == 1)
      g.a(-113 + 2 * paramSignalStrength.getGsmSignalStrength());
    if (g.b(this.a) != null)
      g.b(this.a).listen(g.c(this.a), 0);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.h
 * JD-Core Version:    0.6.0
 */