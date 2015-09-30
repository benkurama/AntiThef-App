package com.example.ifoundyou;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.ifoundyou.fragments.MainMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class BaseActivity extends SlidingFragmentActivity{
	 // =========================================================================
	 // TODO Variables
	 // =========================================================================
	private String PageTitles;
	protected Fragment oFrag;
	
	public BaseActivity(String title){
		PageTitles = title;
	}
	 // =========================================================================
	 // TODO Variables
	 // =========================================================================

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(PageTitles);
		setBehindContentView(R.layout.frame_menu);
		
		if(savedInstanceState == null){
			//
			FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
			oFrag = new MainMenuFragment();
			ft.replace(R.id.frame_menu, oFrag);
			ft.commit();
		} else {
			oFrag = getSupportFragmentManager().findFragmentById(R.id.frame_menu);
		}
		//
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//
		getSupportActionBar().setHomeButtonEnabled(true);
	}
	
}
