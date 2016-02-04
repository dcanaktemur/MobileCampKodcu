package com.kodcu.strategy;

public class ITUzmaniDogus extends ITUzmani {
	public ITUzmaniDogus() {
		kodYazabilme = new Kodlayamazki();
		testEdebilme = new TestEdemezki();
		analizEdebilme = new AnalizUzmaniJunior();
	}

	public void display() {
		System.out.println("Merhaba Ben Ayse");
	}
}
