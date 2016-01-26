package org.bdd.calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.bdd.impl.ISUHealthRecord;
import org.jbehave.core.annotations.*;
import org.bdd.BodyMassIndex;
import org.bdd.WeightCategory;
import org.bdd.WeightClassifier;
import org.bdd.impl.BodyMassIndexImpl;
import org.bdd.impl.WeightClassifierImpl;

/**
 * Steps mappings used by the steps in the <code>*.story</code> files for the
 * Weight Classifier logic.
 * 
 */
public class WeightClassifierSteps {

	// --- Instance Variables --------------------------------------------------
	
	private WeightClassifier weightClassifier;

	// --- Constructors --------------------------------------------------------
	
	public WeightClassifierSteps() {
	}

    @BeforeScenario
    public void runBefore() {
        System.out.println(" ****** Before Scenario *****");
    }

	// --- Methods (JBehave) ---------------------------------------------------


	@Given("Weight Classifier is started")
    @Alias("Weight Classifier is started indeed")
	public void initializeWeightClassifier() {
		weightClassifier = new WeightClassifierImpl();
	}

    @When("the user pass to the calculator a value for mass <mass> kg")
    public void getTheMass(@Named("mass") float mass) {
        System.out.println(" Mass > " + mass);
    }


    @When("the user pass to the calculator a value for height <height> m")
    public void getTheHeight(@Named("height") float height) {
        System.out.println(" height > " + height);
    }

    @Then("the calculator shows that the value for the users's body mass index is <bmi>")
    public void getTheBMI(@Named("bmi") double bmi) {
        System.out.println(" bmi > " + bmi);
    }


    @Then("for the calculated bmi value <bmi>, the weight classifier shows: <weight-category>")
    @Alias("for the calculated bmi value $bmi, the weight classifier shows: $category")
	public void validateWeightCategoryForGivenBmiPlease(
			@Named("bmi") double bmiValue,
			@Named("weight-category") String expectedWeightCategory) {
		validateWeightCategoryForGivenBmi(bmiValue, expectedWeightCategory);
	}





    private void validateWeightCategoryForGivenBmi(
			@Named("bmi") double bmiValue,
			@Named("weight-category") String expectedWeightCategory) {
		BodyMassIndex bmi = new BodyMassIndexImpl(bmiValue);
		WeightCategory actualWeightCategory = weightClassifier.assess(bmi);
		assertThat(actualWeightCategory.getStringValue(),
				is(equalTo(expectedWeightCategory)));
	}

}
