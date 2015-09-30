package com.example.ifoundyou;

import java.util.Observable;
import java.util.Observer;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.ifoundyou.fragments.HomePageFragment;
import com.example.ifoundyou.fragments.MainMenuFragment;
import com.example.ifoundyou.objects.NetLocCoordinates;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.webkit.WebView;

public class MainContent extends BaseActivity implements Observer{
	// =========================================================================
	// TODO Variables
	// =========================================================================
	public String Latitude = "0";
	public String Longtide = "0";
	
	public MainContent() {
		super("Home Page");
	}
	// =========================================================================
	// TODO Life Cycles
	// =========================================================================
	@Override
	@SuppressLint("NewApi")
	@TargetApi(11)
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		getSlidingMenu().setMode(SlidingMenu.LEFT);
		getSlidingMenu().setShadowWidth(5);
		getSlidingMenu().setBehindScrollScale(0);
		getSlidingMenu().setFadeEnabled(false);
		//
		setContentView(R.layout.frame_content);
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.frame_content, new HomePageFragment()).commit();
		//
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.frame_menu, new MainMenuFragment()).commit();
		
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//
		new NetLocCoordinates(this);
	}
	
	public String getLatitude(){
		return Latitude;
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
	}
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		String[] coords = data.toString().split(",");
		SherlockFragment frag = (SherlockFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
		View view = frag.getView();
		
		Vars.setLatitude(this, coords[0]);
		
		((TextView) view.findViewById(R.id.tvLatitude)).setText(coords[0]);
		((TextView) view.findViewById(R.id.tvLongitude)).setText(coords[1]);
		//
		//((WebView) view.findViewById(R.id.wvWeb)).loadUrl("http://maps.googleapis.com/maps/api/staticmap?center="+coords[0]+","+coords[1]+"&zoom=14&size=400x400&maptype=roadmap"
				//+ "&markers=color:red|label:S|"+coords[0]+","+coords[1]);
		//wvWeb.loadUrl("http://maps.googleapis.com/maps/api/staticmap?center=Philippines,Manila&zoom=8&size=700x600&maptype=roadmap");
	}
}
