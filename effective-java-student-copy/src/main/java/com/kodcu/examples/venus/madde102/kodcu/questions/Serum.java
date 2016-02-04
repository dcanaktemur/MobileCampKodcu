package com.kodcu.examples.venus.madde102.kodcu.questions;

public class Serum {
	private int water = 0; // (mL) required
	private int sodiumIon = 0 ; //  required
	private final int magnesium = 0; // optional
	private final int creatine = 0; // (g) optional
	private final int globulin = 0; // (mg) optional
	private final int carbohydrate = 0; // (g) optional

	public Serum() {

	}

	public Serum(int water,int sodiumIon) {
		this.water=water;
		this.sodiumIon=sodiumIon;
	}

	//Builder pattern requrired olanlar ile optinal olanlar ayrılıyor.
	//sınfın içinde sınıf oluyor.


	public static void main(String[] args) {
	 // Create A Serum
	}
}