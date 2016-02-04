package com.kodcu.factory;

public class ToyotaAdapazariFabrikasi {

	public Araba arabaUret(String type) {
		Araba araba = null;

		if (type.equals("yaris")) {
			araba = new Yaris();
		} else if (type.equals("corolla")) {
			araba = new Corolla();
		} else if (type.equals("auris")) {
			araba = new Auris();
		}
		return araba;
	}
}
