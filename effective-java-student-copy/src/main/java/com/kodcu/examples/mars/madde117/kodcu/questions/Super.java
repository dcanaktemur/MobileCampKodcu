package com.kodcu.examples.mars.madde117.kodcu.questions;

public class Super {
	// Broken - constructor invokes an overridable method
	//hata : constructor içinde abstaract method çağırmak
	public Super() {
		// removed this ->> overrideMe();
	}

	public void overrideMe() {
	}
}
