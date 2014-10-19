package com.example.pathview;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import cn.suanya.hc.service.PathService;
import com.example.pathview.util.TimeUtil;

public class AlarmDialogActivity extends Activity
{
  private MediaPlayer mediaPlayer;
  private PathService service;
  private Vibrator vibrator;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903041);
    Log.i("path - > 提醒广播", "activity");
    Bundle localBundle = getIntent().getExtras();
    this.service = PathService.getInstance();
    this.service.deleteAlarmStation(localBundle.getString("code"), localBundle.getInt("index"));
    Boolean localBoolean1 = Boolean.valueOf(localBundle.getBoolean("voice", true));
    Boolean localBoolean2 = Boolean.valueOf(localBundle.getBoolean("vibrate", true));
    if (localBoolean1.booleanValue())
    {
      AudioManager localAudioManager = (AudioManager)getSystemService("audio");
      localAudioManager.setStreamVolume(3, localAudioManager.getStreamMaxVolume(3), 8);
      this.mediaPlayer = MediaPlayer.create(this, 2131099648);
      this.mediaPlayer.setLooping(true);
      this.mediaPlayer.start();
    }
    if (localBoolean2.booleanValue())
    {
      this.vibrator = ((Vibrator)getSystemService("vibrator"));
      this.vibrator.vibrate(new long[] { 1000L, 100L, 100L, 1000L }, 0);
    }
    new AlertDialog.Builder(this).setTitle("提醒").setMessage(TimeUtil.getCurrentMins() - TimeUtil.getMinsByStr(localBundle.getString("alarmTime")) + "后到达站点：" + localBundle.getString("name")).setPositiveButton("确定", new DialogInterface.OnClickListener(localBoolean2)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        if ((AlarmDialogActivity.this.mediaPlayer != null) && (AlarmDialogActivity.this.mediaPlayer.isPlaying()))
          AlarmDialogActivity.this.mediaPlayer.stop();
        if ((this.val$isVibrate.booleanValue()) && (AlarmDialogActivity.this.vibrator != null))
          AlarmDialogActivity.this.vibrator.cancel();
        AlarmDialogActivity.this.finish();
      }
    }).show();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131492864, paramMenu);
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.AlarmDialogActivity
 * JD-Core Version:    0.6.0
 */