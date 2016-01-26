package org.bdd.impl;

import org.bdd.BodyMassIndex;

/**
 * Bean which holds a BMI value.
 * 
 */
public class BodyMassIndexImpl implements BodyMassIndex {

	private double value;

	public BodyMassIndexImpl(double value) {
		this.value = value;
	}

	public double value() {
		return value;
	}
}
