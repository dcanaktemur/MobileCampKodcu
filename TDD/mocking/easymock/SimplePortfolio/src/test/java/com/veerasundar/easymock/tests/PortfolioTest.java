package com.veerasundar.easymock.tests;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.veerasundar.easymock.Portfolio;
import com.veerasundar.easymock.Stock;
import com.veerasundar.easymock.StockMarket;

public class PortfolioTest extends TestCase {

	private Portfolio portfolio;
	private StockMarket marketMock;

	@Before
	public void setUp() {
		portfolio = new Portfolio();
		portfolio.setName("Mehmet's portfolio.");
		marketMock = EasyMock.createMock(StockMarket.class);
		portfolio.setStockMarket(marketMock);
	}

	@Test
	public void testGetTotalValue() {

		/* = Setup our mock object with the expected values */
		EasyMock.expect(marketMock.getPrice("EBAY")).andReturn(42.00);
		EasyMock.replay(marketMock);

		/* = Now start testing our portfolio */
		Stock ebayStock = new Stock("EBAY", 2);
		portfolio.addStock(ebayStock);
		assertEquals(84.00, portfolio.getTotalValue());
	}

}
