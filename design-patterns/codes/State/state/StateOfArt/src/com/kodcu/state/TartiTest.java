package com.kodcu.state;

public class TartiTest {

	public static void main(String[] args) {
		Tarti tarti = new Tarti();

		System.out.println(tarti);

        tarti.jetonAt();
		tarti.olc();
        tarti.bilgileriVer();

		System.out.println(tarti);
        Tarti tarti2 = new Tarti();
        tarti2.jetonAt();
        tarti2.jetonAt();
        tarti2.olc();
        tarti2.olc();
        tarti2.bilgileriVer();
        tarti2.bilgileriVer();

        System.out.println(tarti);

        tarti.olc();
        tarti.bilgileriVer();

	}
}
