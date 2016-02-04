package com.kodcu.code;

public class DigiturkDisplay implements Observer, DisplayElement {
    double sonFiyat = 0.0;
    String urun;
	private YemekSepetiData yemekSepetiData;

	public DigiturkDisplay(YemekSepetiData yemekSepetiData) {
		this.yemekSepetiData = yemekSepetiData;
		yemekSepetiData.registerObserver(this);
	}

	public void update(String urun, double fiyat, double maksIndirimOrani) {
        this.sonFiyat = computeDigiturkIndirim(fiyat, maksIndirimOrani);
        this.urun = urun;
		display();
	}
	
	private double computeDigiturkIndirim(double fiyat, double maksIndirimOrani) {

		return fiyat - (fiyat * ((maksIndirimOrani*0.01))*0);
	}

	public void display() {
		System.out.println("Digiturk ozel fiyat " +  urun + " " + this.sonFiyat);
	}
}
