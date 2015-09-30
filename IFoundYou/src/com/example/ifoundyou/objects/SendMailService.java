package com.example.ifoundyou.objects;

import com.example.ifoundyou.Vars;

import android.app.IntentService;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SendMailService extends IntentService{
	// =========================================================================
	// TODO Variables
	// =========================================================================
	public static final String FILEPATH = "filepath";
	public static final String ACTION = "Action";
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	
	public SendMailService() {
		super("SendMailService");
		// 
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// 
		String filePath = intent.getExtras().getString(FILEPATH);
		//
		String Action = intent.getExtras().getString(ACTION);
		String Lat = intent.getExtras().getString(LATITUDE);
		String Lng = intent.getExtras().getString(LONGITUDE);
		//
//		Mail mail = new Mail("alvin.sison@redfoottech.com","redfoot123_");
//		mail.setTo(new String[]{"alvin.sison@redfoottech.com"});
//		mail.setFrom("benkurama@gmail.com");
//		mail.setSubject("Hello Owner of Cellphone");
		Mail mail = new Mail(Vars.getEmailAdd(getApplicationContext()),Vars.getEmailPass(getApplicationContext()));
		mail.setTo(new String[]{Vars.getEmailAdd(getApplicationContext())});
		mail.setFrom("benkurama@gmail.com");
		mail.setSubject("Phone Missing?");
		//mail.setBody("https://www.google.com/maps/@14.510161,121.214561,18z ");
		// -- >
		String BodyMessage = "";
		
		
		BodyMessage += "This link below is the actual location of your cellphone, kindly check it";
		
		BodyMessage += " \n\n";
		
		BodyMessage += "http://maps.googleapis.com/maps/api/staticmap?"+
		"center="+Vars.getLatitude(this)+","+Lng+"&zoom=14&size=700x600&maptype=roadmap"+
		"&markers=color:red|label:S|"+Vars.getLatitude(this)+","+Lng;
		
		BodyMessage += " \n\n";
		
		BodyMessage += "Trigger when: ";
		//
		if(Action.equals("Test"))
			BodyMessage += "For Testing Only";
		if(Action.equals("PasswordFailed"))
			BodyMessage += "Cellphone Password Failed";
		//
		// -- <	
		mail.setBody(BodyMessage);
		//
		try {
			//
			mail.addAttachment(filePath);
			//
			if(mail.send()){
			} else {
			}
		} catch (Exception e) {
			
		}
		
		//
//		SmsManager sms =  SmsManager.getDefault();
//		sms.sendTextMessage("09055870620", null, "May Snatcher po, Latitude: "+Lat+" Longitude: "+Lng, null, null);
		// 09178946528
		if(Lat != "0" || Lng != "0"){
			SmsManager.getDefault().sendTextMessage(Vars.getCellNum(getApplicationContext()), null, "Your phone is in, Latitude: "+Vars.getLatitude(this)+" Longitude: "+Lng, null, null);
		}
		
	}

}
