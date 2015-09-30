package com.example.ifoundyou;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.actionbarsherlock.app.SherlockActivity;

public class Splash extends SherlockActivity {
	
	private static int TIMEDELAYED = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(runner, TIMEDELAYED);
	}
	
	private void displayHomePage(){
		startActivity(new Intent(this, MainContent.class));
		finish();
	}
	
	private Runnable runner = new Runnable(){
		@Override
		public void run() {
			displayHomePage();
		}
	};
}
