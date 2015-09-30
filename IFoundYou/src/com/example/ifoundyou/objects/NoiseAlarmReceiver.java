package com.example.ifoundyou.objects;

import com.example.ifoundyou.Vars;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class NoiseAlarmReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		
		final Bundle bundle = intent.getExtras();
		try {
			if (bundle != null) {
				final Object[] pdusObj = (Object[])bundle.get("pdus");
				//
				for (int i = 0; i < pdusObj.length; i++) {
					//
					SmsMessage curMsg =  SmsMessage.createFromPdu((byte[])pdusObj[i]);
					
					String phoneNum = curMsg.getDisplayOriginatingAddress();
					String msg = curMsg.getDisplayMessageBody();
					
					//Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
					
					if(msg.contains("<Alert ")){
						
						//
						String[] msgArr = msg.split(" ");
						String[] Arr = msgArr[1].split(">");
						Toast.makeText(context, Arr[0], Toast.LENGTH_LONG).show();
						//
						if(Arr[0].equals("Alarm")){
							//
							Intent intAlarm = new Intent();
							intAlarm.setClassName("com.example.ifoundyou", "com.example.ifoundyou.NoiseAlarmAct");
							intAlarm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							
							context.startActivity(intAlarm);
							//Toast.makeText(context, Arr[0]+" is SEt", Toast.LENGTH_LONG).show();
						}
						
						if(Arr[0].equals("Lock")){
							if(Vars.getDeviceManager() != null)
								Vars.getDeviceManager().lockNow();
						}
					}
					//
//					Intent intAlarm = new Intent();
//					intAlarm.setClassName("com.example.ifoundyou", "com.example.ifoundyou.NoiseAlarmAct");
//					intAlarm.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					
//					context.startActivity(intAlarm);
				}
				//
			}
		} catch (Exception e) {
			Toast.makeText(context, "SMS not REceived", Toast.LENGTH_LONG).show();
		} 
	}

}
