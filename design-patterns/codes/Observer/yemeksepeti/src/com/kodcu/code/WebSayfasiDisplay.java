package com.kodcu.code;
	
public class WebSayfasiDisplay implements Observer, DisplayElement {
	private String urun;
	private double fiyat;
    private double sonFiyat;
	private Subject weatherData;
	
	public WebSayfasiDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

    public void update(String urun, double fiyat, double maksIndirimOrani) {
		this.urun = urun;
        this.fiyat = fiyat;
		this.sonFiyat = fiyat - (fiyat * ((maksIndirimOrani*0.01))*0.30);
		display();
	}
	
	public void display() {
		System.out.println("WebSayfasi  " + urun + " - Size ozel fiyat: " + sonFiyat);
	}
}
