package cn.domob.android.offerwall;

public abstract interface DomobOfferWallListener
{
  public abstract void onOfferWallLoadFailed(DomobOfferWallView paramDomobOfferWallView);

  public abstract void onOfferWallLoadFinished(DomobOfferWallView paramDomobOfferWallView);

  public abstract void onOfferWallLoadStart(DomobOfferWallView paramDomobOfferWallView);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.domob.android.offerwall.DomobOfferWallListener
 * JD-Core Version:    0.6.0
 */