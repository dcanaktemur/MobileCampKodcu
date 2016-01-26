package org.bdd.calculator;

import junit.framework.Assert;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.bdd.BodyMassIndex;
import org.bdd.HealthRecord;
import org.bdd.impl.ISUHealthRecord;
import org.bdd.impl.MetricBMICalculator;

/**
 * Steps mappings used by the steps in the <code>*.story</code> files for the
 * BMI Calculator logic.
 * 
 *
 */
public class MetricBMICalculatorSteps {

	// --- Instance Variables --------------------------------------------------
	
	private HealthRecord healthRecord;
	private MetricBMICalculator bmiCalculator;
	private BodyMassIndex bmi;

	// --- Constructors --------------------------------------------------------
	
	public MetricBMICalculatorSteps() {
	}

	// --- Methods (JBehave) ---------------------------------------------------
	
	@Given("a body mass index calculator")
	@Alias("BMI Calculator is started")
	public void initBMICalculator() {
		bmiCalculator = new MetricBMICalculator();
        initHealtRecordIfNull();
	}

	@When("a patient is with mass $weight kg and height $height m")
	@Alias("a doctor populates a patient's health record with mass $weight kg and height $height m")
	public void populateHealthRecord(@Named("weight") float weight,
                                     @Named("height") float height) {
		healthRecord = new ISUHealthRecord(height, weight);
		bmi = bmiCalculator.calculate(healthRecord);
	}

	@When("the user pass to the calculator a value for mass <mass> kg")
	public void userPopulatesMass(@Named("mass") float mass) {
		healthRecord.setWeight(mass);
	}

	@When("the user pass to the calculator a value for height <height> m")
	public void userPopulatesHeight(@Named("height") float height) {
		healthRecord.setHeight(height);
	}

	@Then("the calculator shows that the value for the users's body mass index is <bmi>")
	public void calculateAndVerifyBmi(@Named("bmi") double bmiValue) {
		bmi = bmiCalculator.calculate(healthRecord);
		Assert.assertEquals(bmiValue, bmi.value());
	}

	@Then("patient's body mass index is $bmi")
	public void checkBmi(@Named("bmi") double bmiValue) {
		Assert.assertEquals(bmiValue, bmi.value());
	}

	// --- Methods (Auxiliary) -------------------------------------------------

	private void initHealtRecordIfNull() {
		if (healthRecord == null) {
			healthRecord = new ISUHealthRecord();
		}
	}
}
