package com.example.ifoundyou.objects;

import java.util.Observer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

@SuppressLint("ServiceCast")
public class NetLocCoordinates { 
	 // =========================================================================
	 // TODO Variables
	 // =========================================================================
	private Activity Act;
	Monitor coordinates = new Monitor();
	 // =========================================================================
	 // TODO Constructor
	 // =========================================================================
	@SuppressLint("ServiceCast")
	public NetLocCoordinates(Activity act){
		//
		this.Act = act;
		coordinates.addObserver((Observer) this.Act);
		//
		LocationManager lm = (LocationManager)Act.getSystemService(Context.LOCATION_SERVICE);
		LocationListener ll = new LocationListener() {
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
			@Override
			public void onProviderEnabled(String provider) {
			}
			@Override
			public void onProviderDisabled(String provider) {
			}
			@Override
			public void onLocationChanged(Location location) {
				coordinates.setLatLong(location.getLatitude(), location.getLongitude());
				coordinates.Commit();
			}
		};
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
	}
}
