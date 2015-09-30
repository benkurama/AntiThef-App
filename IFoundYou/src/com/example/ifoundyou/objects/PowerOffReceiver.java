package com.example.ifoundyou.objects;

import com.example.ifoundyou.StolenShot;
import com.example.ifoundyou.Vars;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class PowerOffReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub
		
//		synchronized (context) {
//			SmsManager sms =  SmsManager.getDefault();
//			sms.sendTextMessage("09055870620", null, "Your Cellphone is Power Off", null, null);
//			Vars.setPowerStatus(context, "Power is Off");
//		}
		
		//sms.sendTextMessage("5558", null, "Your Cellphone is Power Off", null, null);
		//
		
		//
//		Intent intPic = new Intent(context, StolenShot.class);
//		intPic.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		context.startActivity(intPic);
		//
		
		//
		
//		Mail mail = new Mail("alvin.sison@redfoottech.com","redfoot123_");
//		mail.setTo(new String[]{"alvin.sison@redfoottech.com"});
//		mail.setFrom("benkurama@gmail.com");
//		mail.setSubject("Hello Owner of Cellphone");
//		mail.setBody("Your Cellphone is Shutdown");
//		//
//		try {
//			//
//			//mail.addAttachment(filePath);
//			//
//			if(mail.send()){
//			} else {
//			}
//		} catch (Exception e) {
//			
//		}
		
		if(Vars.getPolicyStatus(context)){
			//
			Intent intentSplash = new Intent(context, StolenShot.class);
			intentSplash.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intentSplash.putExtra(StolenShot.ACTION, "Power Off");
			context.startActivity(intentSplash);
		}
		
		Vars.setPowerStatus(context, "Power Is Off");
	}

}

