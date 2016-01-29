package org.bdd;

/**
 * Body Mass Index Calculator.
 * 
 */
public interface BMICalculator {

	/**
	 * Calculates a body mass index.
	 * 
	 * @param healthRecord
	 *            Person's health record from which are taken the mass and
	 *            height needed for the calculation.
	 * 
	 * @return Returns the corresponding body mass index.
	 */
	BodyMassIndex calculate(HealthRecord healthRecord);
}
