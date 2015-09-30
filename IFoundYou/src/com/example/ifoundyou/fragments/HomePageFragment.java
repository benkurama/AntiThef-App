package com.example.ifoundyou.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.ifoundyou.MainContent;
import com.example.ifoundyou.R;

public class HomePageFragment extends SherlockFragment{
//
	public String lat;
	
	private SherlockFragmentActivity act;
	private MainContent core;
	
	public TextView tvLatitude, tvLongitude;
	private WebView wvWeb;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//
		act = getSherlockActivity();
		core = (MainContent)act;
		//
		tvLatitude = (TextView)act.findViewById(R.id.tvLatitude);
		tvLongitude = (TextView)act.findViewById(R.id.tvLongitude);
		wvWeb = (WebView)act.findViewById(R.id.wvWeb);
		//wvWeb.loadUrl("http://maps.googleapis.com/maps/api/staticmap?center=Philippines,Manila&zoom=8&size=700x600&maptype=roadmap");
		//
//		LocationManager lm = (LocationManager)core.getSystemService(Context.LOCATION_SERVICE);
//		
//		LocationListener ll = new LocationListener() {
//			@Override
//			public void onStatusChanged(String provider, int status, Bundle extras) {
//			}
//			@Override
//			public void onProviderEnabled(String provider) {
//			}
//			@Override
//			public void onProviderDisabled(String provider) {
//			}
//			@Override
//			public void onLocationChanged(Location location) {
//				tvLatitude.setText(location.getLatitude()+"");
//				tvLongitude.setText(location.getLongitude()+"");
//			}
//		};
//		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
		
		//
//		AccountManager am = AccountManager.get(act);
//		Account[] acc = am.getAccountsByType("com.google");
		
//		boolean syncEne = ContentResolver.getSyncAutomatically(acc[0], ContactsContract.AUTHORITY);
//		
//		syncEne = syncEne;
		
//		Pattern ep = Patterns.EMAIL_ADDRESS;
//		String pos = "";
//		if(ep.matcher(acc[0].name).matches()){
//			pos = acc[0].name;
//		}
//		pos = pos;
	}
}
