package com.example.ifoundyou;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.preference.PreferenceManager;

public class Vars extends Application {
	
	static final String POLICY = "Policy";
	static final String EMAILADD = "EmailAdd";
	static final String EMAILPASS = "EmailPass";
	static final String CELLNUM = "CellNum";
	static final String LATITUDE = "latitude";
	static final String LONGITUDE = "longitude";
	
	public static DevicePolicyManager dpm;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		dpm = null;
	}
	public static void setDeviceManager(DevicePolicyManager devPm){
		dpm = devPm;
	}
	public static DevicePolicyManager getDeviceManager(){
		return dpm;
	}
	//--------------------------------------------------------------------------------
	public static void setPowerStatus(Context core, String status){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString("PowerStatus", status).commit();
	}
	public static String getPowerStatus(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString("PowerStatus", "null");
	}
	
	public static void setPolicyStatus(Context core, boolean bool){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putBoolean(POLICY, bool).commit();
	}
	public static boolean getPolicyStatus(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getBoolean(POLICY, false);
	}
	//
	public static void setEmailAdd(Context core, String emailadd){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString(EMAILADD, emailadd).commit();
	}
	public static String getEmailAdd(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString(EMAILADD, "null");
	}
	//
	public static void setEmailPass(Context core, String pass){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString(EMAILPASS, pass).commit();
	}
	public static String getEmailPass(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString(EMAILPASS, "null");
	}
	//
	public static void setCellNum(Context core, String num){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString(CELLNUM, num).commit();
	}
	public static String getCellNum(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString(CELLNUM, "null");
	}
	//
	public static void setLatitude(Context core, String lat){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString(LATITUDE, lat).commit();
	}
	public static String getLatitude(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString(LATITUDE, "0");
	}
	//
	public static void setLongitude(Context core, String lng){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString(LONGITUDE, lng);
	}
	public static String getLongitude(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString(LONGITUDE, "0");
	}
}
