package org.bdd;

/**
 * Assesses to which weight category belongs a person using its BMI according to
 * the <a href="http://en.wikipedia.org/wiki/Body_mass_index#Categories">
 * classification defined by WHO</a>.
 */
public interface WeightClassifier {

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
	WeightCategory assess(BodyMassIndex bmi);
}
