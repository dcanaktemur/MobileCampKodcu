package com.kodcu.code;

public class YemekSepetiMerkez {

	public static void main(String[] args) {
		YemekSepetiData yemekSepetiData = new YemekSepetiData();
	
		WebSayfasiDisplay currentDisplay =
			new WebSayfasiDisplay(yemekSepetiData);

		GoogleGlassDisplay googleGlassDisplay =
				new GoogleGlassDisplay(yemekSepetiData);

		MobilCihazlarDisplay mobilCihazlarDisplay = new MobilCihazlarDisplay(yemekSepetiData, 1);
        DigiturkDisplay digiturkDisplay = new DigiturkDisplay(yemekSepetiData);

        yemekSepetiData.setUrun("Hamburger menu", 21, 50); // %50 ye kadar indirim olabilir.
        yemekSepetiData.setUrun("Fasulyeli Pilav", 18, 10); // %10 ye kadar indirim olabilir.
        yemekSepetiData.setUrun("Kofteci Ramizz", 25, 7);// %7 ye kadar indirim olabilir.




	}
}
