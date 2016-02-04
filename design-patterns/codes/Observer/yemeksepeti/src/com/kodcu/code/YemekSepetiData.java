package com.kodcu.code;

import java.util.*;


public class YemekSepetiData implements Subject {
	private ArrayList<Observer> observers;
	private String urun;
	private double fiyat;
	private double maksIndirimOrani;
	
	public YemekSepetiData() {
		observers = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers() { //observer yani dinleyenleri setliyor.
		for (Observer observer : observers) {
			observer.update(urun, fiyat, maksIndirimOrani);
		}
	}
	
	public void measurementsChanged() {
		notifyObservers();
	}
	
	public void setUrun(String urun, double fiyat, double maksIndirimOrani) {
		this.urun = urun;
		this.fiyat = fiyat;
		this.maksIndirimOrani = maksIndirimOrani;
		measurementsChanged();
	}



}
