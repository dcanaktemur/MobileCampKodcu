package org.bdd;

/**
 * Interface declaring bean wrapping the height and weight of a person.
 * 
 */
public interface HealthRecord {

	float getHeight();

	void setHeight(float height);

	float getWeight();

	void setWeight(float weight);
}