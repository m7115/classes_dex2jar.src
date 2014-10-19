package com.worklight.androidgap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import com.worklight.androidgap.plugin.WLOptionsMenu;
import com.worklight.common.WLConfig;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.DroidGap;

public class WLDroidGap extends DroidGap
  implements WLDroidGapListener
{
  private static final String CLEAR_CACHE_NEXT_LOAD = "com.worklight.clearCacheNextLoad";
  private static final String ENABLE_SETTINGS_FLAG = "enableSettings";
  public static final String EXIT_ON_SKIN_LOADER = "exitOnSkinLoader";
  private static final String NATIVE_EMPTY_APP_HTML = "NativeEmptyApp.html";
  public static final String RESOURCES_CHECKSUM_PREF_KEY = "wlResourcesChecksum";
  public static final String SKIN_LOADER_HTML = "skinLoader.html";
  public static final String SKIN_NAME_PREF_KEY = "wlSkinName";
  public static final String WL_DEFAULT_SERVER_URL = "WLDefaultServerURL";
  private static boolean isForegound = false;
  private static WLConfig wlConfig;
  private boolean fatalErrorOccurred;
  private WLDroidGap.PrepareClientAsyncTask longPrepareTask;
  private WLOptionsMenu optionsMenu;

  private void doPrepareAssetsWork()
  {
  }

  public static WLConfig getWLConfig()
  {
    return wlConfig;
  }

  private boolean isClearCacheNextLoad()
  {
    return false;
  }

  public static boolean isForeGround()
  {
    return isForegound;
  }

  private void removeSessionCookies(CordovaWebView paramCordovaWebView)
  {
  }

  private void setClearCacheNextLoad(boolean paramBoolean)
  {
  }

  private static void setForeGround(boolean paramBoolean)
  {
    isForegound = paramBoolean;
  }

  private void testResourcesChecksum()
  {
  }

  protected void bindBrowser(CordovaWebView paramCordovaWebView)
  {
  }

  protected void bindBrowser(CordovaWebView paramCordovaWebView, boolean paramBoolean)
  {
  }

  public String getAppWebUrl(String paramString)
  {
    return null;
  }

  protected String getIntentDataInJSONFormat(Intent paramIntent)
  {
    return null;
  }

  public String getLocalStorageRoot()
  {
    return null;
  }

  public String getLocalStorageWebRoot()
  {
    return null;
  }

  public String getWebMainFilePath()
  {
    return null;
  }

  public String getWebUrl()
  {
    return null;
  }

  public void init()
  {
  }

  public void loadUrl(String paramString)
  {
  }

  public void loadUrl(String paramString, boolean paramBoolean)
  {
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public void onCreate(Bundle paramBundle)
  {
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return false;
  }

  public void onDestroy()
  {
  }

  protected void onNewIntent(Intent paramIntent)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    return false;
  }

  public void onPause()
  {
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return false;
  }

  public void onResume()
  {
  }

  public void onWLInitCompleted(Bundle paramBundle)
  {
  }

  @JavascriptInterface
  public void removeSplashScreen()
  {
    super.removeSplashScreen();
  }

  public void setClearCacheNextLoad()
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.worklight.androidgap.WLDroidGap
 * JD-Core Version:    0.6.0
 */