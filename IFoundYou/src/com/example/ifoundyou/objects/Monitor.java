package com.example.ifoundyou.objects;

import java.util.Observable;

public class Monitor extends Observable {
	 // =========================================================================
	 // TODO Variables
	 // =========================================================================
	private String value = "";
	
	public Monitor(){
		
	}
	
	public void setLatLong(double lat, double lng){
		this.value = lat+","+lng;
	}
	
	public void Commit(){
		setChanged();
		notifyObservers(this.value);
		clearChanged();
	}
}
