package com.worklight.androidgap.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gcm.GCMBaseIntentService;

public class WLGCMIntentService extends GCMBaseIntentService
{
  public static final String GCM_ERROR = ".C2DM_ERROR";
  public static final String GCM_EXTRA_ALERT = "alert";
  public static final String GCM_EXTRA_BADGE = "badge";
  public static final String GCM_EXTRA_ERROR_ID = "errorId";
  public static final String GCM_EXTRA_MESSAGE = "message";
  public static final String GCM_EXTRA_PAYLOAD = "payload";
  public static final String GCM_EXTRA_REGISTRATION_ID = "registrationId";
  public static final String GCM_EXTRA_SOUND = "sound";
  public static final String GCM_MESSAGE = ".C2DM_MESSAGE";
  public static final String GCM_NOTIFICATION = ".NOTIFICATION";
  public static final String GCM_REGISTERED = ".C2DM_REGISTERED";
  public static final String GCM_UNREGISTERED = ".C2DM_UNREGISTERED";
  private static int RES_PUSH_NOTIFICATION_ICON = -1;
  private static int RES_PUSH_NOTIFICATION_TITLE = -1;
  private BroadcastReceiver resultReceiver;

  public WLGCMIntentService()
  {
  }

  public WLGCMIntentService(String paramString)
  {
  }

  private void generateNotification(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt, String paramString4, Intent paramIntent)
  {
  }

  private void playNotificationSound(Context paramContext, String paramString)
  {
  }

  protected String getNotificationTitle(Context paramContext)
  {
    return null;
  }

  public void notify(Context paramContext, String paramString)
  {
  }

  public void notify(Context paramContext, String paramString1, int paramInt, String paramString2, Intent paramIntent)
  {
  }

  public void onError(Context paramContext, String paramString)
  {
  }

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
  }

  protected boolean onRecoverableError(Context paramContext, String paramString)
  {
    return false;
  }

  public void onRegistered(Context paramContext, String paramString)
  {
  }

  protected void onUnhandled(Context paramContext, Intent paramIntent)
  {
  }

  protected void onUnregistered(Context paramContext, String paramString)
  {
  }

  protected void setBroadcastReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    this.resultReceiver = paramBroadcastReceiver;
  }

  protected void setNotificationIcon(int paramInt)
  {
    RES_PUSH_NOTIFICATION_ICON = paramInt;
  }

  protected void setResources()
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.worklight.androidgap.push.WLGCMIntentService
 * JD-Core Version:    0.6.0
 */