package com.example.ifoundyou.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.ifoundyou.MainContent;
import com.example.ifoundyou.R;

public class ViewDetailsFragment extends SherlockFragment{
	
	private SherlockFragmentActivity act;
	private MainContent core;
	
	private Button btnMenuBack;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 	Bundle savedInstanceState) {
		//
		return inflater.inflate(R.layout.fragment_details, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// 
		super.onActivityCreated(savedInstanceState);
		
		act = getSherlockActivity();
		core = (MainContent)act;
		//
		btnMenuBack = (Button)getView().findViewById(R.id.btnBackMenu);
		btnMenuBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				act.getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.slide_right_inback, R.anim.slide_right_outback)
				.replace(R.id.frame_menu, new MainMenuFragment())
				.commit();
			}
		});
	}

}
