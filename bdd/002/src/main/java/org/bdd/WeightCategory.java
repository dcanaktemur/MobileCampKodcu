package org.bdd;

/**
 * Weight categories defined by the World Health Organization (WHO).
 *  
 */
public enum WeightCategory {

	SEVERELY_UNDERWEIGHT("Severely Underweight"),

	UNDERWEIGHT("Underweight"),

	NORMAL("Normal"),

	OVERWEIGHT("Overweight"),

	OBESE_CLASS_1("Obese Class I"),

	OBESE_CLASS_2("Obese Class II"),

	OBESE_CLASS_3("Obese Class III");

	private String strValue;

	private WeightCategory(String strValue) {
		this.strValue = strValue;
	}

	public String getStringValue() {
		return strValue;
	}
}
