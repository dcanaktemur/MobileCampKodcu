package com.kodcu.strategy;

public class ITUzmaniAyse extends ITUzmani {
	public ITUzmaniAyse() {
		kodYazabilme = new Kodlayamazki();
		testEdebilme = new TestUzmaniSenior();
	}

	public void display() {
		System.out.println("Merhaba Ben Ayse");
	}
}
