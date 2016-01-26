package org.bdd.impl;

import org.bdd.BodyMassIndex;
import org.bdd.WeightCategory;
import org.bdd.WeightClassifier;

/**
 * Assesses to which weight category belongs a person using its BMI according to
 * the <a href="http://en.wikipedia.org/wiki/Body_mass_index#Categories">
 * classification defined by WHO</a>.
 * 
 *
 * @see WeightCategory
 */
public class WeightClassifierImpl implements WeightClassifier {

	/**
	 * Assesses to which weight category belongs a person using its BMI
	 * according to the <a
	 * href="http://en.wikipedia.org/wiki/Body_mass_index#Categories">
	 * classification defined by WHO</a>.
	 * 
	 * @param bmi
	 *            Body mass index.
	 * 
	 * @return Returns weight category.
	 */
	public WeightCategory assess(BodyMassIndex bmi) {
		double bmiValue = bmi.value();

		if (bmiValue < 16.0) {
			return WeightCategory.SEVERELY_UNDERWEIGHT;
		}//
		else if (16.0 <= bmiValue && bmiValue < 18.5) {
			return WeightCategory.UNDERWEIGHT;
		}//
		else if (18.5 <= bmiValue && bmiValue < 25) {
			return WeightCategory.NORMAL;
		}//
		else if (25 <= bmiValue && bmiValue < 30) {
			return WeightCategory.OVERWEIGHT;
		}//
		else if (30 <= bmiValue && bmiValue < 35) {
			return WeightCategory.OBESE_CLASS_1;
		}//
		else if (35 <= bmiValue && bmiValue < 40) {
			return WeightCategory.OBESE_CLASS_2;
		}
		// else if 40 <= bmiValue
		return WeightCategory.OBESE_CLASS_3;
	}
}
