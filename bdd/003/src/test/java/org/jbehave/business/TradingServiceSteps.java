package org.jbehave.business;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.jbehave.core.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

@Steps
public class TradingServiceSteps {

	@Autowired
	private TradingService tradingService = null;
	
	private Stock stock;
	
	@Given("a stock of symbol <symbol> and a threshold of <threshold>")
	public void aStock(@Named("symbol")String symbol, @Named("threshold")double threshold) {
		stock = tradingService.addNewStock(symbol, threshold);
	}
	
	@When("the stock is traded at <price>")
	public void theStockIsTradedAt(@Named("price")double price) {
		stock.setTradeAt(price);
	}
	
	@Then("the alert status should be <status>")
	public void theAlertStatusShouldBe(@Named("status")StockAlertStatus status) {
		assertThat(stock.getStatus(), equalTo(status));
	}
	
}
