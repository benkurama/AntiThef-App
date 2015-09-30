package com.example.ifoundyou.objects;

import com.example.ifoundyou.Splash;
import com.example.ifoundyou.StolenShot;
import com.example.ifoundyou.Vars;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PowerOnReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		if(Vars.getPolicyStatus(context)){
			//
			Intent intentSplash = new Intent(context, StolenShot.class);
			intentSplash.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intentSplash.putExtra(StolenShot.ACTION, "Power On");
			context.startActivity(intentSplash);
			//
			//reserved codes
//			TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//			int simState =  tm.getSimState();
//			
//			switch (simState) {
//			case TelephonyManager.SIM_STATE_ABSENT:
//				Toast.makeText(context, "Sim Card is Removed", Toast.LENGTH_LONG).show();
//				break;
//			case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
//				Toast.makeText(context, "Sim Card network locked", Toast.LENGTH_LONG).show();
//				break;
//			case TelephonyManager.SIM_STATE_PIN_REQUIRED:
//				Toast.makeText(context, "Sim Card pin required", Toast.LENGTH_LONG).show();
//				break;
//			case TelephonyManager.SIM_STATE_PUK_REQUIRED:
//				Toast.makeText(context, "Sim Card puk required", Toast.LENGTH_LONG).show();
//				break;
//			case TelephonyManager.SIM_STATE_UNKNOWN:
//				Toast.makeText(context, "Sim Card Unknown", Toast.LENGTH_LONG).show();
//				break;
//			case TelephonyManager.SIM_STATE_READY:
//				Toast.makeText(context, "Sim Card is Present and ready", Toast.LENGTH_LONG).show();
//				break;
//			}
		}
		
		Vars.setPowerStatus(context, "Power Is On Now");
	}

}
