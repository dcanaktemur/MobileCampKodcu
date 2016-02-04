package com.kodcu.examples.venus.madde105.kodcu.questions;

import java.util.Date;

public class Toplam {

	public static void main(String[] args) {
		long sum = 0L; //Long --> long yapıldı döngüde long değil Long kulanıldığı için herseferinde döngüde obje oluşturuldu. Obje oluşturmak büyük maliyet.
		long first= System.currentTimeMillis();
		for (long i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;

			String cumle2 = "cumle"; // heap kullanıyor.

			String cumle =  new String("cumle"); // bunu kullanma her şeyde nesne oluşturuyor
		}
		System.out.println(sum);
        long last= System.currentTimeMillis();

		System.out.println((last-first)/1000 + ","+(last-first)%1000 +" seconds of sum operation");




	}
}
