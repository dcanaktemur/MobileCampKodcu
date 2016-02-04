package com.kodcu.code;
	
public class GoogleGlassDisplay implements Observer, DisplayElement {
	private String urun;
	private double fiyat;
    private double sonFiyat;
	private Subject weatherData;

	public GoogleGlassDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

    public void update(String urun, double fiyat, double maksIndirimOrani) {
		this.urun = urun;
        this.fiyat = fiyat;
		this.sonFiyat = fiyat - (fiyat*0.70);
		display();
	}
	
	public void display() {
		System.out.println("Google Glass  " + urun + " - Size ozel fiyat: " + sonFiyat);
	}
}
