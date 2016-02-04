package com.kodcu.factory;

public class TestDrive {
 
	public static void main(String[] args) {
		ToyotaAdapazariFabrikasi factory = new ToyotaAdapazariFabrikasi();
		ToyotaSatisBayi store = new ToyotaSatisBayi(factory);

		Araba araba = store.arabaSiparisEt("auris");
		System.out.println("Siparis teslim edildi : " + araba.getName() + "\n");
		System.out.println(araba);
 
		araba = store.arabaSiparisEt("yaris");
		System.out.println("Siparis teslim edildi : " + araba.getName() + "\n");
		System.out.println(araba);
	}
}
