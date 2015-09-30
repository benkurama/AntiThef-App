package com.example.ifoundyou.fragments;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.ifoundyou.MainContent;
import com.example.ifoundyou.NoiseAlarmAct;
import com.example.ifoundyou.R;
import com.example.ifoundyou.StolenShot;
import com.example.ifoundyou.Vars;
import com.example.ifoundyou.objects.Mail;
import com.example.ifoundyou.policy.Admin;

public class MainMenuFragment extends SherlockFragment {
	// =========================================================================
	// TODO Variables
	// =========================================================================
	private CheckBox cbDeviceManager;
	private Button btnLock, btnSaveSet, btnViewCode;
	private EditText etEmailUser, etEmailPass, etCellNum;
	
	private SherlockFragmentActivity act;
	private MainContent core;
	
	DevicePolicyManager deviceManager;
	ActivityManager actManager;
	ComponentName compName;
	
	private String Latitude = "0";
	private String Longitude = "0";
	
	static final int RESULT_ENABLE = 1;
	
	private MediaPlayer thePlayer = null;
	// =========================================================================
	// TODO Overrides
	// =========================================================================
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		//
		return inflater.inflate(R.layout.fragment_main_menu, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//
		act = getSherlockActivity();
		core = (MainContent)act;
		
		deviceManager = (DevicePolicyManager)act.getSystemService(Context.DEVICE_POLICY_SERVICE);
		actManager = (ActivityManager)act.getSystemService(Context.ACTIVITY_SERVICE);
		compName = new ComponentName(act, Admin.class);
		
		cbDeviceManager = (CheckBox)getView().findViewById(R.id.cbDeviceManager);
		cbDeviceManager.setOnCheckedChangeListener(dmOnCheck);
		
		btnLock = (Button)getView().findViewById(R.id.btnLock);
		btnLock.setOnClickListener(btnClick);
		btnLock.setEnabled(false);
		//
		etEmailUser = (EditText)getView().findViewById(R.id.etEmailUser);
		etEmailPass = (EditText)getView().findViewById(R.id.etEmailPass);
		etCellNum = (EditText)getView().findViewById(R.id.etCellNum);
		
		btnSaveSet = (Button)getView().findViewById(R.id.btnSaveSet);
		btnSaveSet.setOnClickListener(btnSaveSetting);
		
		btnViewCode = (Button)getView().findViewById(R.id.btnViewCode);
		btnViewCode.setOnClickListener(btnViewListener);
		//
		isDeviceAdminOn();
		//
//		LocationManager lm = (LocationManager)core.getSystemService(Context.LOCATION_SERVICE);
//		
//		LocationListener ll = new LocationListener() {
//			@Override
//			public void onStatusChanged(String provider, int status, Bundle extras) {
//			}
//			@Override
//			public void onProviderEnabled(String provider) {
//			}
//			@Override
//			public void onProviderDisabled(String provider) {
//			}
//			@Override
//			public void onLocationChanged(Location location) {
//				Latitude = location.getLatitude()+"";
//				Longitude = location.getLongitude()+"";
//			}
//		};
//		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
		
		//
//		boolean network_enabled;
//		network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//		if(network_enabled){
//			btnLock.setText("Provider Enabled");
//		} else {
//			btnLock.setText("Provider Disabled");
//			
//			act.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//		}
		//
		//btnLock.setText(Vars.getPowerStatus(act));
		//
//		if(Vars.getPolicyStatus(act)){
//			btnLock.setText("Policy ON");
//		}else {
//			btnLock.setText("Policy OFF");
//		}
		etEmailUser.setText(Vars.getEmailAdd(act));
		etEmailPass.setText(Vars.getEmailPass(act));
		etCellNum.setText(Vars.getCellNum(act));
		//
	}
	// =========================================================================
	// TODO Implementation
	// =========================================================================
	private OnCheckedChangeListener dmOnCheck = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// 
			if(isChecked){
					enableAdmin();
			} else {
				deviceManager.removeActiveAdmin(compName);
				disable();
				btnLock.setEnabled(false);
			}
		}
	};
	
	private OnClickListener btnViewListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			act.getSupportFragmentManager().beginTransaction()
			.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
			.replace(R.id.frame_menu, new ViewDetailsFragment())
			.commit();
		}
	};
	
	private OnClickListener btnSaveSetting = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Vars.setEmailAdd(act, etEmailUser.getText().toString());
			Vars.setEmailPass(act, etEmailPass.getText().toString());
			Vars.setCellNum(act, etCellNum.getText().toString());
			Toast.makeText(act, "Save OK", Toast.LENGTH_LONG).show();
			//thePlayer.stop();
		}
	};
	
	private OnClickListener btnClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
//			Intent intent = new Intent(core, SendMailService.class);
//			intent.putExtra(SendMailService.ACTION, "Test");
//			intent.putExtra(SendMailService.LATITUDE, Latitude);
//			intent.putExtra(SendMailService.LONGITUDE, Longitude);
//			core.startService(intent);
			
			//startActivity(new Intent(act,StolenShot.class));
					//
			//Mail mail = new Mail("alvin.sison@redfoottech.com","redfoot123_");
//			Mail mail = new Mail("benkurama@gmail.com","aoitenshi001");
//			mail.setTo(new String[]{"jayson.agorilla@redfoottech.com"});
//			mail.setFrom("benkurama@gmail.com");
//			mail.setSubject("Hello Owner of Cellphone");
//			
//			mail.setBody("Is A Test");
//			//
//			try {
//				//
//				if(mail.send()){
//				} else {
//				}
//			} catch (Exception e) {
//				
//			}
			
			
			//TestAlarm();
			//startActivity(new Intent(act, NoiseAlarmAct.class));
			
			if(Vars.getPolicyStatus(act)){
				deviceManager.lockNow();
			}
		}
	};
	
//	public void TestAlarm(){
//		//
//		AudioManager audioManager = (AudioManager) act.getSystemService(Context.AUDIO_SERVICE);
//		
//		int maxVolumeMusic = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//	    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
//	    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolumeMusic,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
//		
//		Uri notify = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//		
//		thePlayer = MediaPlayer.create(act.getApplicationContext(), notify);
//
//		try {
//		    thePlayer.prepare();
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//
//		try {
//		    //thePlayer.setVolume(Float.parseFloat(Double.toString(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) / 7.0)),
//		                        //Float.parseFloat(Double.toString(audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) / 7.0)));
//		} catch (Exception e) {
//		    e.printStackTrace();
//		}
//
//		thePlayer.start();
//	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case RESULT_ENABLE:
			
			if(resultCode == Activity.RESULT_OK){
				isDeviceAdminOn();
			} else{
				cbDeviceManager.setText("Status: Disabled");
				cbDeviceManager.setChecked(false);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	// =========================================================================
	// TODO Main Functions
	// =========================================================================
	private void enableAdmin(){
		//
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "this is for Anti Theft App");
		startActivityForResult(intent, 1);
	}
	
	private boolean isDeviceAdminOn(){
		//
		boolean valid = deviceManager.isAdminActive(compName);
		
		if(valid){
			enable();
		} else {
			disable();
		}
		return valid;
	}
	// =========================================================================
	// TODO Sub Functions
	// =========================================================================
	private void enable(){
		if(!cbDeviceManager.isChecked())
			cbDeviceManager.setChecked(true);
		cbDeviceManager.setText("Status: Enabled");
		btnLock.setEnabled(true);
		Vars.setDeviceManager(deviceManager);
		Vars.setPolicyStatus(act, true);
	}
	
	private void disable(){
		if(!cbDeviceManager.isChecked())
			cbDeviceManager.setChecked(false);
		cbDeviceManager.setText("Status: Disabled");
		btnLock.setEnabled(false);
		Vars.setDeviceManager(null);
		Vars.setPolicyStatus(act, false);
	}
}
