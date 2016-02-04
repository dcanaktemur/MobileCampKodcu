package com.kodcu.factory;

import java.util.ArrayList;

abstract public class Araba {
	String name;

	ArrayList<String> ozellikleri = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void sigortaIslemleri() {
		System.out.println("Sigorta islemleri yapiliyor " + name);
	}

	public void plakaIslemleri() {
		System.out.println("Plaka Islemleri " + name);
	}

	public void krediIslemleri() {
		System.out.println("Kredi islemleri yapiliyor " + name);
	}

	public void teslimIslemleri() {
		System.out.println("Teslim islemleri yapiliyor" +
                " " + name);
	}

	public String toString() {

		StringBuffer display = new StringBuffer();
		display.append("---- " + name + " ----\n");

		for (String ozellik : ozellikleri) {
			display.append(ozellik + "\n");
		}
		return display.toString();
	}
}

