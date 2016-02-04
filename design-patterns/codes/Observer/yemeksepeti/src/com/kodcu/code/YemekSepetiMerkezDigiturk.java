package com.kodcu.code;

public class YemekSepetiMerkezDigiturk {

	public static void main(String[] args) {
		YemekSepetiData yemekSepetiData = new YemekSepetiData();
		WebSayfasiDisplay currentDisplay = new WebSayfasiDisplay(yemekSepetiData);
		MobilCihazlarDisplay mobilCihazlarDisplay = new MobilCihazlarDisplay(yemekSepetiData,2);
		DigiturkDisplay digiturkDisplay = new DigiturkDisplay(yemekSepetiData);

        yemekSepetiData.setUrun("Hamburger menu", 21, 5);
        yemekSepetiData.setUrun("Fasulyeli Pilav", 18, 10);
        yemekSepetiData.setUrun("Kofteci Ramizz", 25, 7);
	}
}
