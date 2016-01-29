package org.jbehave.web.acceptance;

import org.jbehave.business.Steps;
import org.jbehave.business.StockAlertStatus;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Steps
public class TradingServiceWebSteps {

    @Autowired
    @Qualifier("insertStockPage")
    private InsertStockPage insertStockPage;
    @Autowired
    @Qualifier("showStatusStockPage")
    private ShowStockStatusPage showStockStatusPage;

    @Given("trader starts web application")
    public void aNewStockCreated() {
        insertStockPage.open();
    }

    @When("trader inserts stock <symbol> with threshold <threshold> and trade at <tradeAt>")
    public void newStockDataIsProvided(@Named("symbol") String symbol, @Named("threshold") double threshold, @Named("tradeAt") double tradeAt) {
        insertStockPage.fillForm(symbol, Double.toString(threshold), Double.toString(tradeAt));
        insertStockPage.submitForm();
    }

    @Then("web page should show an <status> message")
    public void stockStatusShouldBeShowed(@Named("status") StockAlertStatus status) {
        assertThat(showStockStatusPage.getStockStatusMessage(), is(status.name()));
    }
}
