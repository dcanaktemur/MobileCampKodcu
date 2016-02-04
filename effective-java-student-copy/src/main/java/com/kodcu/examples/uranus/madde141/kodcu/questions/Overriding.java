
package com.kodcu.examples.uranus.madde141.kodcu.questions;

class Soda {
	//static String markaBildir() {return "soda";} // hepsi bunu çağırıyor static methodu override edemeyiz. diğerlerideki static de kaldırılır. Atomik işlerde statik kullan . state tutmayan şeylerde. al ver işlerde

	String markaBildir() {
		return "soda";
	}
}

class SparklingSoda extends Soda {

	 String markaBildir() {
		return "sparkling soda";
	}
}

class UludagGazoz extends SparklingSoda {

	String markaBildir() {
		return "Gazoz olma Efsane Ol";
	}
}

public class Overriding {
	public static void main(String[] args) {
		Soda[] sodas = { new Soda(), new SparklingSoda(), new UludagGazoz() };
		for (Soda soda : sodas)
			System.out.println(soda.markaBildir());
	}
}
