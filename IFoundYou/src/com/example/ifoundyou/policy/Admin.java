package com.example.ifoundyou.policy;

import com.example.ifoundyou.StolenShot;

import android.annotation.SuppressLint;
import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Admin extends DeviceAdminReceiver{
	
	void show(Context core, CharSequence msg){
		Toast.makeText(core, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onEnabled(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onEnabled(context, intent);
		show(context, "Admin Enabled");
	}

	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		// TODO Auto-generated method stub
		show(context, "Admin Disabled Request");
		return super.onDisableRequested(context, intent);
		
	}

	@Override
	public void onDisabled(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onDisabled(context, intent);
		show(context, "Admin Disabled");
	}

	@Override
	public void onPasswordChanged(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordChanged(context, intent);
	}

	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent intPic = new Intent(context, StolenShot.class);
		intPic.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intPic.putExtra(StolenShot.ACTION, "PasswordFailed");
		context.startActivity(intPic);
		
		super.onPasswordFailed(context, intent);
	}


	@Override
	public void onPasswordSucceeded(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordSucceeded(context, intent);
	}

	@SuppressLint("NewApi")
	@Override
	public void onPasswordExpiring(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onPasswordExpiring(context, intent);
	}

}
