package com.kodcu.strategy;

public abstract class ITUzmani {
	KodYazabilme kodYazabilme;
	TestEdebilme testEdebilme;
	AnalizEdebilme analizEdebilme;

	public ITUzmani() {
	}

	public void setKodYazabilme(KodYazabilme kodYazabilme) {
		this.kodYazabilme = kodYazabilme;
	}

	public void setTestEdebilme(TestEdebilme testEdebilme) {
        this.testEdebilme = testEdebilme;
	}

	abstract void display();

	public void kodlayabilirMisin() {
		kodYazabilme.kodla();
	}

	public void testYapabilirMisin() {
		testEdebilme.testEt();
	}

	public void setAnalizEdebilme(AnalizEdebilme analizEdebilme) {
		this.analizEdebilme = analizEdebilme;
	}


}
