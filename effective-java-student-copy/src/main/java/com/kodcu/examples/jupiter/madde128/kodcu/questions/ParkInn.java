// Generic union method with wildcard types - Pages 137-138
package com.kodcu.examples.jupiter.madde128.kodcu.questions;

import java.util.HashSet;
import java.util.Set;

public class ParkInn {

	//generic type E,T,P ->> hataları compile anında yakalamak için.

	public static <E> Set<E> birlestir(Set<? extends E> s1, Set<? extends E> s2) { // integer ve double numberdan türüyor dolayısıyla sadece e değil bir de ? extends E dersek E den olan tüm şeyler alınabilir.
		Set<E> result = new HashSet<E>(s1);
		result.addAll(s2);
		return result;
	}

	// Simple program to exercise flexible generic method
	public static void main(String[] args) {
		Set<Integer> integers = new HashSet<Integer>();
		integers.add(1);
		integers.add(3);
		integers.add(5);

		Set<Double> doubles = new HashSet<Double>();
		doubles.add(2.0);
		doubles.add(4.0);
		doubles.add(6.0);

		// TODO yorum satirlarini aciniz
		Set<Number> numbers = ParkInn.<Number>birlestir(integers, doubles); // compile da bu satıra kızması gayet normal
		System.out.println(numbers);
	}
}
