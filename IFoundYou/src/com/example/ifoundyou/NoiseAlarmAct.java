package com.example.ifoundyou;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;

public class NoiseAlarmAct extends SherlockActivity{

	private MediaPlayer Alarm;
	private Button btnStopAlarm;
	private Vibrator vibrate;
	private long pattern[]={0,200,100,300,400};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_noise_alarm);
		
		btnStopAlarm = 	(Button)findViewById(R.id.btnStopAlarm);
		btnStopAlarm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Alarm.stop();
				vibrate.cancel();
				finish();
			}
		});
		//
		AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		
		int maxVolumeMusic = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolumeMusic,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
		
		Uri notify = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		
		Alarm = MediaPlayer.create(getApplicationContext(), notify);
		//
		
		vibrate = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		

//		try {
//			Alarm.prepare();
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
		Alarm.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mp.start();
				vibrate.vibrate(pattern, 0);
			}
		});
	}

}
