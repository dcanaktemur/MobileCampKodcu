package com.kodcu.jbehave;

import java.util.Stack;

import com.kodcu.service.Stock;
import com.kodcu.service.TradingService;
import org.jbehave.core.annotations.*;
import org.jbehave.core.embedder.Embedder;
import junit.framework.Assert;

public class StackSteps extends Embedder {

    private TradingService service;
    private Stock stock;
 
    @Given("a stock and a threshold of $threshold")
    public void aStock(double threshold) {
        service = new TradingService();
        stock = service.newStock("STK", threshold);
    }
    
    @When("stock is traded at $price")
    public void theStockIsTraded(double price) {
        stock.tradeAt(price);
    }
 

    @Then("the alert status should be $status")
    public void theAlertStatusIs(String status) {

        Assert.assertEquals(stock.getStatus(), status);
}
    

}
