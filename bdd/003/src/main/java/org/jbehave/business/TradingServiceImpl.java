package org.jbehave.business;

import org.springframework.stereotype.Component;

@Component
public class TradingServiceImpl implements TradingService {

	public Stock addNewStock(String stockName, double threshold) {
		return new Stock(stockName, threshold);
	}

}
