package com.kodcu.code;

public class MobilCihazlarDisplay implements Observer, DisplayElement {
	private double indirimForAndroid = 0.6;
    private double indirimForIphone = 0.5;
	private double sonfiyat;
    private int telefonTipi;
    private String urun;

	private YemekSepetiData yemekSepetiData;

	public MobilCihazlarDisplay(YemekSepetiData yemekSepetiData, int telefonTipi) {
		this.yemekSepetiData = yemekSepetiData;
		yemekSepetiData.registerObserver(this);
        this.telefonTipi = telefonTipi;
	}

    public void update(String urun, double fiyat, double maksIndirimOrani) {
        this.urun = urun;
        if(telefonTipi == 1) {
            sonfiyat = fiyat - (fiyat * ((maksIndirimOrani*0.01))*indirimForAndroid);
        } else {
            sonfiyat = fiyat - (fiyat * ((maksIndirimOrani*0.01))*indirimForIphone);
        }

		display();
	}

	public void display() {

		if (telefonTipi == 1) {
			System.out.println("Android için özel indirimli fiyat " + urun +" "  + sonfiyat);
		} else {
            System.out.println("IPhone için özel indirimli fiyat " + urun + " " + sonfiyat);
		}
	}
}
